package kjstyle.study.codility.lesson02;

import org.junit.Assert;
import org.junit.Test;

/**
 n array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index,
 and the last element of the array is moved to the first place.
 For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]
 (elements are shifted right by one index and 6 is moved to the first place).

 The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.

 Write a function:

 class Solution { public int[] solution(int[] A, int K); }

 that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.

 For example, given

 A = [3, 8, 9, 7, 6]
 K = 3
 the function should return [9, 7, 6, 3, 8]. Three rotations were made:

 [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 For another example, given

 A = [0, 0, 0]
 K = 1
 the function should return [0, 0, 0]

 Given

 A = [1, 2, 3, 4]
 K = 4
 the function should return [1, 2, 3, 4]

 Assume that:

 N and K are integers within the range [0..100];
 each element of array A is an integer within the range [−1,000..1,000].
 In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.

 시계방향으로 K번 만큼 로테이션하는 문제
 */
public class CyclicRotation {

	@Test
	public void test() {
		int[] A = {3, 8, 9, 7, 6};
		int K = 14;
		int[] expected = { 8, 9, 7, 6, 3 };
		Assert.assertArrayEquals(expected, this.solution(A, K));
	}

	public int[] solution(int[] A, int K) {
		// K가 배열 사이즈보다 큰 경우 배열의 배수마다 본래 배치가 됨
		// 나머지연산으로 실제 쉬프팅할 수를 구함
		int realShiftCount = (K > A.length && A.length > 0) ? K % (A.length) : K;

		return this.shiftRightBySwapping(A, realShiftCount);
	}

	public int[] solution2(int[] A, int K) {
		int[] result = new int[A.length];
		// 쉬프팅이 배열의 수만큼 일어나면 제자리 -> 사실상 쉬프팅수는 원소개수로 나눈 나머지만큼 뿐
		// (현위치+쉬프팅수)%배열크기 -> 한바퀴 도는 알고리즘이 됨
		for (int i = 0; i < A.length; i++) {
			int movingPosition = (i + K) % A.length;
			result[movingPosition] = A[i];
		}
		return result;
	}

	private int[] shiftRightBySwapping(int[] inputArray, int shiftCount) {
		if (shiftCount < 1) {
			return inputArray;
		}
		for (int i = inputArray.length - 1; i > 0; i--) {
			int temp = inputArray[i - 1];
			inputArray[i - 1] = inputArray[i];
			inputArray[i] = temp;
		}
		return shiftRightBySwapping(inputArray, shiftCount - 1);
	}

	// 극한의 입력값에서 에러남
	private int[] shiftRight(int[] inputArray, int shiftCount) {
		if (shiftCount < 1) {
			return inputArray;
		} else {
			int[] result = new int[inputArray.length];
			result[0] = inputArray[inputArray.length - 1];
			for (int j = 1; j < inputArray.length; j++) {
				result[j] = inputArray[j - 1];
			}
			return shiftRight(result, shiftCount - 1);
		}
	}
}
