package kjstyle.study.codility.lesson02;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kjstyle on 2018. 8. 29..
 */
public class OddOccurrencesInArray {

	public static void main(String[] args) {
		int[] A = {9, 3, 9, 3, 9, 7, 9};
		OddOccurrencesInArray oddOccurrencesInArray = new OddOccurrencesInArray();
		int oddOccurrenceNumber = oddOccurrencesInArray.solution(A);
		System.out.println(oddOccurrenceNumber);

		int oddOccurrenceNumberByBitOperation = oddOccurrencesInArray.solutionByBitOperation(A);
		System.out.println(oddOccurrenceNumberByBitOperation);

		int oddOccurrenceNumberByBlog = oddOccurrencesInArray.solutionOnBlog(A);
		System.out.println(oddOccurrenceNumberByBlog);
	}

	/**
	 * set에 담아서 이미 set에 존재하면 제거하는 형태로 구현함
	 *
	 * @param A
	 * @return
	 */
	public int solution(int[] A) {
		Set<Integer> occurenceSet = new HashSet<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (occurenceSet.contains(A[i])) {
				occurenceSet.remove(A[i]);
			} else {
				occurenceSet.add(A[i]);
			}
		}
		return (Integer) occurenceSet.toArray()[0];
	}

	/**
	 * 블로그에 소개된 set을 이용한 해결방법
	 * set의 첫 요소를 가지고 오는 더 쉽고 명쾌한 방법이 있었음
	 *
	 * @param A
	 * @return
	 */
	public int solutionOnBlog(int[] A) {
		Set<Integer> occurenceSet = new HashSet<Integer>();
		for (int num : A) {
			if (occurenceSet.contains(num)) {
				occurenceSet.remove(num);
			} else {
				occurenceSet.add(num);
			}
		}
		return occurenceSet.iterator().next();
	}

	/**
	 * 비트연산을 통한 동일 숫자가 XOR연산될 때 0으로 소거되는 것을 이용해서 동일 숫자가 짝수 번 만큼 반복되지 않은 숫자만 남게되는...
	 * 9 ^ 9 = 0 이됨
	 * XOR 연산은 이진수 각 자리의 숫자가 같으면 0, 다르면 1이 됨 -> 즉 같은 숫자가 나중에 연산되더라도 결국 짝수번 만큼 동일 숫자가 XOR 되면 0으로 소거됨
	 *
	 * @param A
	 * @return
	 */
	public int solutionByBitOperation(int[] A) {
		int temp = 0;
		for (int num : A) {
			temp = temp ^ num;
		}
		return temp;
	}
}
