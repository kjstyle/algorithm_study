package kjstyle.study.codility.lesson04;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 This is a demo task.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A of N integers,
 returns the smallest positive integer (greater than 0) that does not occur in A.
 배열A에 누락된 것으로 추정되는 가장 작은 양수(0초과를 리턴

 For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

 Given A = [1, 2, 3], the function should return 4. <- 0을 초과한 1부터 3까지 모두 연속된 숫자가

 Given A = [−1, −3], the function should return 1.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [−1,000,000..1,000,000].

 */
public class MissingInteger {

	public static void main(String[] args) {
		MissingInteger missingInteger = new MissingInteger();

		int[] A = {1, 3, 6, 4, 1, 2};
		Assert.assertEquals("given {1, 3, 6, 4, 1, 2}", 5, missingInteger.solution(A));

		int[] B = {-2, -3};
		Assert.assertEquals("given {-2,-3}", 1, missingInteger.solution(B));

		int[] C = {1, 2, 3};
		Assert.assertEquals("given {1, 2, 3}", 4, missingInteger.solution(C));

		int[] D = {2};
		Assert.assertEquals("given {2}", 1, missingInteger.solution(D));

		int[] E = {1};
		Assert.assertEquals("given {1}", 2, missingInteger.solution(E));

		int[] F = {4, 5, 6, 2};
		Assert.assertEquals("given {4, 5, 6, 2}", 1, missingInteger.solution(F));

		int[] G = {1};
		Assert.assertEquals("given {1}", 2, missingInteger.solution(G));

		int[] H = {90, 91, 92, 93};
		Assert.assertEquals("given {{90, 91, 92, 93}}", 1, missingInteger.solution(H));
	}

	/**
	 * 소팅한다음 이전수와 다음숫자 사이의 간격이 2면 이전수1 이 MissingInteger라고 접근했는데
	 * {1}
	 * {2,3}
	 * {2}
	 * 등 함정을 피하기 위해서 추가되는 조건문이 더럽게 늘어나서 기브업!
	 *
	 * @param A
	 * @return
	 */
	public int solutionTry01(int[] A) {

		int len = A.length;

		Arrays.sort(A);

		int max = A[len - 1] + 1;
		boolean hasNo1 = (A[len - 1] == 1) ? true : false;

		for (int i = 1; i < len; i++) {
			if (A[i - 1] == 1) {
				hasNo1 = true;
			}
			if ((A[i] - A[i - 1]) == 2) {
				if (A[i - 1] + 1 > 0 && hasNo1) {
					return A[i - 1] + 1;
				} else {
					return 1;
				}
			}
		}

		return (max > 0 && A[len - 1] != 2 && hasNo1) ? max : 1;
	}

	/**
	 * 시도1과 동일하게 정렬 후 지지고 볶으려다가 함정 케이스를 위해 if문 떡칠하다가 중도 포기
	 *
	 * @param A
	 * @return
	 */
	public int solutionTry02(int[] A) {

		int len = A.length;

		Arrays.sort(A);

		int lastNo = A[len - 1];
		if (lastNo <= 0 || lastNo == 2) {
			return 1;
		}

		if (lastNo == 1) {
			return 2;
		}

		boolean hasNo1 = false;

		for (int i = 1; i < len; i++) {
			if (A[i - 1] <= 0) {
				continue;
			} else {
				if (A[i - 1] == 1 || A[i] == 1) {
					hasNo1 = true;
				}
			}
			int diff = A[i] - A[i - 1];
			if (diff == 2) {
				if (hasNo1) {
					return A[i - 1] + 1;
				} else {
					return 1;
				}
			}
		}

		return lastNo + 1;
	}

	/**
	 * 최창후니 코드
	 *
	 * @param A
	 * @return
	 */
	public int solutionChnaghoon(int[] A) {
		boolean[] checker = new boolean[A.length + 1];
		int checkCount = 0;
		int num;
		for (int i = 0; i < A.length; i++) {
			num = A[i];
			if (num > 0 && num < checker.length) checker[num] = true;   //양수 일 때만 체크
		}

		for (int i = 1; i < checker.length; i++) {
			if (checker[i]) checkCount++;
			else return i;
		}
		return checkCount == (checker.length - 1) ? checker.length : 1;   //모두 만족하면 그 다음수 리턴 아니면 모두 음수이므로 양의 최소값 1 리턴
	}

	/**
	 * 시도3
	 * 요소개수1개 사이즈의 boolean형 배열을 선언하고, 발견되는 숫자의 index에 true로 변경한다음 어쩌고 접근했는데
	 * if문 많이지고 있고, 머리속으로 잘 안그려저서 중도 포기
	 *
	 * @param A
	 * @return
	 */
	public int solutionTry03(int[] A) {

		int len = A.length;
		boolean[] foundNumbers = new boolean[len + 1];
		int max = 0;

		for (int num : A) {
			if (num > 0 && num < len) {
				max = Math.max(num, max);
				foundNumbers[num - 1] = true;
			}
		}

		if (max < 1) {
			return 1;
		}

		if (max == 1) {
			return 2;
		}

		for (int i = 0; i < len; i++) {
			if (foundNumbers[i] == false) {
				return i + 1;
			}
		}
		return 1;
	}

	/**
	 * HashSet에 발견된 숫자를 담아서
	 * 1부터 정수최댓값까지 숫차적으로 HashSet에 들어있는지 확인해서
	 * 없는게 발견되면 바로 해당 index리턴
	 * 사실 인터넷 찾아서 참고해서 푼 것..ㅡㅡ
	 * 무식해보이지만 최악의 경우라도 최대 정수크기만큼까지만 루프를 도는거라 결국 O(N)
	 *
	 * @param A
	 * @return
	 */
	public int solution(int[] A) {
		Set<Integer> hs = new HashSet<>();

		for (int num : A) {
			hs.add(num);
		}

		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			if (hs.contains(i) == false) {
				return i;
			}
		}
		return 1; // 모두 음수인 경우에 for loop을 다 돌고 여기로 들어옴, 여기에서 리턴되는 경우는 21억번 다 돌아버린 것
	}
}