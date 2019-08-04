package kjstyle.study.codility.lesson06;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * An array A consisting of N integers is given.
 * A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
 * <p>
 * A[P] + A[Q] > A[R],
 * A[Q] + A[R] > A[P],
 * A[R] + A[P] > A[Q].
 * For example, consider array A such that:
 * <p>
 * A[0] = 10    A[1] = 2    A[2] = 5
 * A[3] = 1     A[4] = 8    A[5] = 20
 * Triplet (0, 2, 4) is triangular.
 * 0    2   4
 * <p>
 * 10 + 5 > 8
 * 8 + 10 > 5
 * 5 + 8 > 10
 * <p>
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers,
 * returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 10    A[1] = 2    A[2] = 5
 * A[3] = 1     A[4] = 8    A[5] = 20
 * <p>
 * the function should return 1, as explained above. Given array A such that:
 * <p>
 * A[0] = 10    A[1] = 50    A[2] = 5
 * A[3] = 1
 * the function should return 0.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 * <p>
 * A[P] + A[Q] > A[R],
 * A[Q] + A[R] > A[P],
 * A[R] + A[P] > A[Q].
 * 위 조건을 만족하는 세숫자가 존재하면 1을 리턴, 없으면 0을 리턴
 * 이또한 조합의 문제지만, 3중 루프각...
 * <p>
 * 정렬을 이용하면...
 * P < Q < R
 * 중간Q + 마지막R > 첫값P
 * 첫값P + 마지막R > 중간Q
 * 을 보장
 * <p>
 * 즉, 첫값P + 중간Q > 마지막R 인 것만 증명하면 됨
 */
public class Triangle {

	@Test
	public void test() {
		int[] A = {10, 2, 5, 1, 8, 20};
		Assert.assertEquals(Arrays.toString(A), 1, this.solution(A));

		int[] B = {10, 50, 5, 1};
		Assert.assertEquals(Arrays.toString(B), 0, this.solution(B));

		int[] C = {5, 3, 3};
		Assert.assertEquals(Arrays.toString(C), 1, this.solution(C));
	}

	public int solution(int[] A) {
		int N = A.length;
		Arrays.sort(A);

		for (int i = 2; i < N; i++) {
			// A[i-2] + A[i-1] 이 MAX_INT를 넘을 수 있어서 비교 내에서만 double로 형변환해서 더함
			if ((double) A[i - 2] + (double) A[i - 1] > A[i]) {
				return 1;
			}
		}
		return 0;
	}
}
