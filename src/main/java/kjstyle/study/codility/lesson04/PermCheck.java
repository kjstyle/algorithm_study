package kjstyle.study.codility.lesson04;

import java.util.Arrays;

/**
 * Created by kjstyle on 2018. 8. 30..
 */
public class PermCheck {

	public static void main(String[] args) {
		int[] A = {2};
		PermCheck permCheck = new PermCheck();
		int result = permCheck.solution(A);
		System.out.println(result);
	}

	/**
	 * 합은 맞더라도 순열이 아닌 경우는 감지하지 못함! 땡!
	 *
	 * @param A
	 * @return
	 */
	public int solutionFail01(int[] A) {
		long elementSum = 0;
		int max = 0;
		for (int num : A) {
			max = Math.max(max, num);
			elementSum += num;
		}
		// 시작값은 무조건 1
		long originalSum = (1 + max) * max / 2;
		return (originalSum == elementSum) ? 1 : 0;
	}

	public int solution(int[] A) {
		int len = A.length;

		Arrays.sort(A);
		for (int i = 1; i < len; i++) {
			if ((A[i] - A[i - 1]) != 1) {
				return 0;
			}
		}
		// loop를 통과했고 배열의 길이와 마지막 요소의 값이 같을 경우 순열로 인정
		// 놓친 케이스 = 2 인 경우 1이 누락된 순열임
		return (len == A[len - 1]) ? 1 : 0;
	}
}
