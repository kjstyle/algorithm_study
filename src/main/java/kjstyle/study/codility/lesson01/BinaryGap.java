package kjstyle.study.codility.lesson01;

import org.junit.Assert;
import org.junit.Test;

/**
 * Binary Gap
 A binary gap within a positive integer N is any maximal sequence of consecutive zeros
 that is surrounded by ones at both ends in the binary representation of N.
 For example, number 9 has binary representation 1001 and contains a binary gap of length 2.
 The number 529 has binary representation 1000010001 and contains two binary gaps:
 one of length 4 and one of length 3.
 The number 20 has binary representation 10100 and contains one binary gap of length 1.
 The number 15 has binary representation 1111 and has no binary gaps.
 The number 32 has binary representation 100000 and has no binary gaps.
 Write a function:
 class Solution { public int solution(int N); }

 that, given a positive integer N, returns the length of its longest binary gap.
 The function should return 0 if N doesn't contain a binary gap.

 For example, given N = 1041 the function should return 5,
 because N has binary representation 10000010001 and so its longest binary gap is of length 5.
 Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [1..2,147,483,647].

 1과 1사이의 채워졌있는 0의 개수를 zeroCount라고 하면
 zeroCount가 가장 큰 것을 찾으면 되는 문제
 */
public class BinaryGap {

	@Test
	public void test() {
		Assert.assertEquals(2, this.solution(328));
	}

	public int solution(int N) {
		if (N < 0) {
			return -1;
		}

		String binaryString = Integer.toBinaryString(N);
		int len = binaryString.length();
		int zeroCount = 0;
		int maxCount = 0;
		for (int i = 0; i < len; i++) {
			if ('1' == binaryString.charAt(i)) {
				maxCount = Math.max(maxCount, zeroCount);
				zeroCount = 0;
			} else {
				zeroCount++;
			}
		}
		return maxCount;
	}
}
