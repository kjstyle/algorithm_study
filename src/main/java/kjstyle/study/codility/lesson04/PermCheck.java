package kjstyle.study.codility.lesson04;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 A non-empty array A consisting of N integers is given.
 A permutation is a sequence containing each element from 1 to N once, and only once.
 For example, array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 A[3] = 2
 is a permutation, but array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 is not a permutation, because value 2 is missing.

 The goal is to check whether array A is a permutation.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A, returns 1 if array A is a permutation and 0 if it is not.

 For example, given array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 A[3] = 2
 the function should return 1.

 Given array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 the function should return 0.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [1..1,000,000,000].
 */
public class PermCheck {

	@Test
	public void test() {
		int[] A = {4, 1, 3};
		Assert.assertEquals(Arrays.toString(A), 2, this.solution(A));
	}

	public int solution(int[] A) {
		int len = A.length;

		// 정렬시킨 후
		Arrays.sort(A);
		for (int i = 1; i < len; i++) {
			// 현재 엘리먼트와 이전 엘리먼트 사이의 간격이 1이 아닌 경우 지금 엘리먼트 바로 직전의 수가 누락된 녀석임
			if ((A[i] - A[i - 1]) != 1) {
				return A[i] - 1;
			}
		}
		// loop를 통과했고 배열의 길이와 마지막 요소의 값이 같을 경우 순열로 인정
		// 놓친 케이스 = 2 인 경우 1이 누락된 순열임
		return (len == A[len - 1]) ? 1 : 0;
	}

	/**
	 * 합은 맞더라도 순열이 아닌 경우는 감지하지 못함! 땡!
	 *
	 * @param A
	 * @return
	 */
	public int solutionFail01(int[] A) {
		long elementSum = 0;
		int max = 0;
		for (int num : A) {
			max = Math.max(max, num);
			elementSum += num;
		}
		// 시작값은 무조건 1
		long originalSum = (1 + max) * max / 2;
		return (originalSum == elementSum) ? 1 : 0;
	}
}
