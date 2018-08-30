package kjstyle.study.codility.lesson03;

/**
 * Created by kjstyle on 2018. 8. 29..
 */
public class TapeEquilibrium {

	public static void main(String[] args) {
		TapeEquilibrium tapeEquilibrium = new TapeEquilibrium();
		int[] A = {3, 1, 2, 4, 3};
		int result = tapeEquilibrium.solution(A);
		System.out.println(result);
	}

	/**
	 * 일단 O(n^2) 으로 탈락
	 *
	 * @param A
	 * @return
	 */
	public int solution1(int[] A) {
		int min = Integer.MAX_VALUE;

		int len = A.length;

		long sumPart1 = 0;
		long sumPart2 = 0;
		int diff = 0;

		for (int P = 1; P < (len - 1); P++) {
			sumPart1 = 0;
			sumPart2 = 0;
			for (int j = 0; j < len; j++) {
				if (j < P) {
					sumPart1 += A[j];
				} else {
					sumPart2 += A[j];
				}
			}
			diff = (int) Math.abs(sumPart2 - sumPart1);
			if (diff < min) {
				min = diff;
			}
		}

		return min;
	}

	/**
	 * 각 엘리먼트 순서까지의 summation을 미리 담아두면 P로 자를 경우 i-1번째가 파트1이됨
	 * 전체 summnation에서 part1까지의 누적합(=P-1까지의 누적합)을 빼면 part2의 누적합이 나옴
	 * 즉, part2의합 = (전체누적합 - part1의합)
	 * part1의합 = sum[p-1] <== P로 자르면 part1은 p-1까지의 누적합이 됨
	 * diff = 절대값( (전체누적합 - sum[p-1]) - sum[p-1] )
	 *
	 * @param A
	 * @return
	 */
	public int solution(int[] A) {
		int len = A.length;
		if (len == 2) {
			return A[1] - A[0];
		}

		int min = Integer.MAX_VALUE;

		long[] sumEach = new long[len];
		for (int i = 0; i < len; i++) {
			if (i == 0) {
				sumEach[i] = A[i];
			} else {
				sumEach[i] = sumEach[i - 1] + A[i];
			}
		}

		long sumAll = sumEach[len - 1];
		int diff = 0;

		for (int P = 1; P < (len - 1); P++) {
			diff = (int) Math.abs((sumAll - sumEach[P - 1]) - sumEach[P - 1]);
			if (diff < min) {
				min = diff;
			}
		}
		return min;
	}
}