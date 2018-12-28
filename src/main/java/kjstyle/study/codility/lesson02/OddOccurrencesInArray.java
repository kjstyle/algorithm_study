package kjstyle.study.codility.lesson02;

import java.util.HashSet;
import java.util.Set;

/**
 A non-empty array A consisting of N integers is given.
 The array contains an odd number of elements,
 and each element of the array can be paired with another element that has the same value,
 except for one element that is left unpaired.

 For example, in array A such that:

 A[0] = 9  A[1] = 3  A[2] = 9
 A[3] = 3  A[4] = 9  A[5] = 7
 A[6] = 9
 the elements at indexes 0 and 2 have value 9,
 the elements at indexes 1 and 3 have value 3,
 the elements at indexes 4 and 6 have value 9,
 the element at index 5 has value 7 and is unpaired.
 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A consisting of N integers fulfilling the above conditions,
 returns the value of the unpaired element.

 For example, given array A such that:

 A[0] = 9  A[1] = 3  A[2] = 9
 A[3] = 3  A[4] = 9  A[5] = 7
 A[6] = 9
 the function should return 7, as explained in the example above.

 Write an efficient algorithm for the following assumptions:

 N is an odd integer within the range [1..1,000,000];
 each element of array A is an integer within the range [1..1,000,000,000];
 all but one of the values in A occur an even number of times.

 짝지가 없는 수를 찾아라.
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
