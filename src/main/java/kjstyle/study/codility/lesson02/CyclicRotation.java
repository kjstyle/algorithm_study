package kjstyle.study.codility.lesson02;

/**
 * Created by kjstyle on 2018. 8. 28..
 */
public class CyclicRotation {

	public static void main(String[] args) {
		CyclicRotation cyclicRotation = new CyclicRotation();
		int[] A = {3, 8, 9, 7, 6};
		int K = 14;
		int[] result = cyclicRotation.solution2(A, K);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]);
			System.out.print(",");
		}
	}

	public int[] solution(int[] A, int K) {

		// K가 배열 사이즈보다 큰 경우 배열의 배수마다 본래 배치가 됨
		// 나머지연산으로 실제 쉬프팅할 수를 구함
		int realShiftCount = (K > A.length && A.length > 0) ? K % (A.length) : K;

		return this.shiftRightBySwapping(A, realShiftCount);
	}

	public int[] solution2(int[] A, int K) {
		int[] result = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			int moviingPos = (i + K) % A.length;
			result[moviingPos] = A[i];
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
