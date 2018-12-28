package kjstyle.study.codility.lesson06;

import java.util.Arrays;

/**
 * A non-empty array A consisting of N integers is given.
 * The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
 * <p>
 * For example, array A such that:
 * <p>
 * A[0] = -3
 * A[1] = 1
 * A[2] = 2
 * A[3] = -2
 * A[4] = 5
 * A[5] = 6
 * contains the following example triplets:
 * <p>
 * (0, 1, 2), product is −3 * 1 * 2 = −6
 * (1, 2, 4), product is 1 * 2 * 5 = 10
 * (2, 4, 5), product is 2 * 5 * 6 = 60
 * Your goal is to find the maximal product of any triplet.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a non-empty array A, returns the value of the maximal product of any triplet.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = -3
 * A[1] = 1
 * A[2] = 2
 * A[3] = -2
 * A[4] = 5
 * A[5] = 6
 * the function should return 60, as the product of triplet (2, 4, 5) is maximal.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [3..100,000];
 * each element of array A is an integer within the range [−1,000..1,000].
 * 수학적으로는 조합(6개중 중복되지 않게 순서에 상관없이 3개를 뽑는..)의 문제이지만 조합으로 재귀호출할 경우 시간복잡도를 해결하지 못함
 * 모두 양수일 경우 정렬을 시켜서 뒤에서 부터 3개의 수를 곱하면 그게 최댓값이 나옴
 * 모두 음수인 경우 -> 가장 작은값 3개를 곱함
 * 음수양수가 섞여있을 경우 -> 0,1번째 엘리먼트가 음수이고 마지막 엘리먼트가 양수인 경우만 또다른 최대를 구해봐서 맨뒤 3개 곱과 비교해서 큰 놈을 리턴
 */
public class MaxProductOfThree {
	public static void main(String[] args) {
		MaxProductOfThree maxProductOfThree = new MaxProductOfThree();
		int[] A = {-3, 1, 2, -2, 5, 6};
		System.out.println("기댓값 : 60");
		System.out.println("실제값 : " + maxProductOfThree.solution(A));

		int[] B = {-10, -2, -4};
		System.out.println("B 기댓값 : -80");
		System.out.println("B 실제값 : " + maxProductOfThree.solution(B));

		int[] C = {-5, 5, -5, 4};
		System.out.println("C 기댓값 : 125");
		System.out.println("C 실제값 : " + maxProductOfThree.solution(C));
	}

	public int solution(int[] A) {
		if (A.length < 3) return 0;
		Arrays.sort(A);
		int result = A[A.length - 1] * A[A.length - 2] * A[A.length - 3];

		if (A[0] < 0 && A[1] < 0 && A[A.length - 1] > 0) {
			int temp = A[0] * A[1] * A[A.length - 1];
			result = (temp > result) ? temp : result;
		}
		return result;
	}
}
