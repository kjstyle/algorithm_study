package kjstyle.study.codility.lesson06;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a function
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns the number of distinct values in array A.
 * <p>
 * For example, given array A consisting of six elements such that:
 * <p>
 * A[0] = 2    A[1] = 1    A[2] = 1
 * A[3] = 2    A[4] = 3    A[5] = 1
 * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 */
public class Distinct {

	@Test
	public void test() {
		int[] A = {2, 1, 1, 2, 3, 1};
		Assert.assertEquals(Arrays.toString(A), 3, this.solution(A));

		int[] B = {1};
		Assert.assertEquals(Arrays.toString(B), 1, this.solution(B));
	}

	/**
	 * 레슨 카테고리의 의도에 맞게 정렬시킨 다음 문제를 해결하는 방법
	 *
	 * @param A
	 * @return
	 */
	public int solution(int[] A) {
		if (A.length == 0) return 0;
		if (A.length == 1) return 1;

		Arrays.sort(A);

		int size = A.length;
		int count = 1;

		for (int i = 1; i < size; i++) {
			if (A[i] != A[i - 1]) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 중복을 허용하지 않는 Set의 특성을 이용
	 *
	 * @param A
	 * @return
	 */
	private int solutionA(int[] A) {
		if (A.length == 0) return 0;
		if (A.length == 1) return 1;

		Set<Integer> distinctSet = new HashSet<Integer>();

		for (int i : A) {
			distinctSet.add(i);
		}

		return distinctSet.size();
	}
}
