package kjstyle.study.codility.lesson03;

/**
 A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.

 Any integer P, such that 0 < P < N,
 splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

 The difference between the two parts is the value of:
 |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

 In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
 0~p-1까지 첫번째 부분의합과 p부터N-1까지 부분합의 절대차

 For example, consider array A such that:

 A[0] = 3
 A[1] = 1
 A[2] = 2
 A[3] = 4
 A[4] = 3
 We can split this tape in four places:

 P = 1, difference = |3 − 10| = 7  => P가 1이면 0~0까지 합 3, 1~4까지합 10 |3-10| = 7
 P = 2, difference = |4 − 9| = 5   => P가 2이면 0~1까지 합 4, 2~4까지합 9 |4-9| = 5
 P = 3, difference = |6 − 7| = 1
 P = 4, difference = |10 − 3| = 7
 Write a function:

 class Solution { public int solution(int[] A); }

 that, given a non-empty array A of N integers, returns the minimal difference that can be achieved.
 절대차값의 최솟값을 리턴하면 됨

 For example, given:

 A[0] = 3
 A[1] = 1
 A[2] = 2
 A[3] = 4
 A[4] = 3
 the function should return 1, as explained above.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [2..100,000];
 each element of array A is an integer within the range [−1,000..1,000].
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
	public int solutionTryAndFail(int[] A) {
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
	 * 각 엘리먼트 순서까지의 sum을 미리 담아두면 P로 자를 경우 i-1번째가 파트1이됨
	 * 전체 sum에서 part1까지의 누적합(=P-1까지의 누적합)을 빼면 part2의 누적합이 나옴
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
				sumEach[i] = A[i]; // 첫번째 루프에서는 0번째 엘리먼트가 곧 0번째까지의 합과 같다.
			} else {
				sumEach[i] = sumEach[i - 1] + A[i];
			}
		}

		long sumAll = sumEach[len - 1]; // 전체합은 sumEach의 마지막 엘리먼트 값임
		int diff;
		long sumPart2;
		long sumPart1;
		for (int P = 1; P < (len - 1); P++) {
			sumPart2 = sumAll - sumEach[P - 1];
			sumPart1 = sumEach[P - 1];
			diff = (int) Math.abs(sumPart2 - sumPart1);
			if (diff < min) {
				min = diff;
			}
		}
		return min;
	}
}