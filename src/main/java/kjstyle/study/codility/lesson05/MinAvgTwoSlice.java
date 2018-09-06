package kjstyle.study.codility.lesson05;

/**
 * Created by kjstyle on 2018. 9. 6..
 */
public class MinAvgTwoSlice {

	public static void main(String[] args) {
		MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();
		int[] A = {4, 2, 2, 5, 1, 5, 8};
		int result = minAvgTwoSlice.solution(A);
		System.out.println(result);

		int[] B = {10000, -10000};
		int resultB = minAvgTwoSlice.solution(B);
		System.out.println(resultB);

		int[] C = {-3, -5, -8, -4, -10};
		int resultC = minAvgTwoSlice.solution(C);
		System.out.println(resultC);

	}

	public int solutionTry01(int[] A) {

		int len = A.length;
		long[] accSum = new long[len];
		int[][] prefixAvg = new int[len][len];

		for (int i = 0; i < len; i++) {
			if (i > 0) {
				accSum[i] = accSum[i - 1] + A[i];
			} else {
				accSum[i] = A[i];
			}
		}

		double tempPartialSum;
		double tempAvg;
		double minAvg = Integer.MAX_VALUE;
		int minAvgIndex = 0;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (i > 0) {
					tempPartialSum = accSum[j] - accSum[i - 1];
				} else {
					tempPartialSum = accSum[j];
				}
				tempAvg = tempPartialSum / (j - i + 1);
				if (tempAvg < minAvg) {
					minAvg = tempAvg;
					minAvgIndex = i;
				}
			}
		}
		return minAvgIndex;
	}

	/**
	 * 2개의 쌍, 최대로 3개의 쌍까지 엮여서 최소평균을 앞에서부터 순차적으로
	 * 현 위치 + 다음위치 평균과 현위치 ~ 다다음위치까지 평균 중 작은놈을
	 * 현재까지 랭크된 가장 작은평균하고 비교해서, 더 작은 놈을 다시 가장 작은 평균값과 그 시점의 인덱스를 기록
	 * 이렇게 한바퀴만 돌고나서
	 * 마지막에 루프내에서 놓쳤던 조합인 마지막과 마지막바로전 두 놈의 평균과 그간 구했던 가장작은평균을 비교해서
	 * 마지막 두놈의 평균이 더 작으면 -> N-1 번이 가장 작은 평균값을 갖는 시작위치
	 * 반대라면 돌면서 기재했던 가장 작은 평균이 발견된 i값을 리턴하면 됨
	 * 구간합
	 *
	 * @param A
	 * @return
	 */
	public int solution(int[] A) {
		final int N = A.length;

		float minAvg = Integer.MAX_VALUE;
		int minStartIdx = 0;
		for (int i = 0; i < N - 2; i++) {
			float avgOf2 = (A[i + 1] + A[i]) / 2.0f; // 현재위치와 그 다음놈의 평균
			float avgOf3 = (A[i + 2] + A[i + 1] + A[i]) / 3.0f; // 현재위치와 다다음 놈까지의 평균

			float smallAvg = Math.min(avgOf2, avgOf3); // 다음까지와 다다음까지의 평균 중 작은놈을 선택

			//지금까지 발견된 가장 작은 평균보다 작으면 최소 평균값과 index를 갱신
			if (smallAvg < minAvg) {
				minAvg = smallAvg;
				minStartIdx = i;
			}
		}

		// 위 루프에서 N-2 전까지만 돌기에
		// 마지막 두수(N-2번째와 N-1번째)의 평균과 앞에서까지 구한 최소 평균값과 비교
		float lastAvg = (A[N - 2] + A[N - 1]) / 2.0f;
		if (lastAvg < minAvg) {
			minStartIdx = N - 2;
		}
		return minStartIdx;
	}
}
