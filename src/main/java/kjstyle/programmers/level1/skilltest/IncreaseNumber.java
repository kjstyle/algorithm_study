package kjstyle.programmers.level1.skilltest;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 문제 설명
 * 함수 solution은 정수 x와 자연수 n을 입력 받아, x부터 시작해 x씩 증가하는 숫자를 n개 지니는 리스트를 리턴해야 합니다.
 * 다음 제한 조건을 보고, 조건을 만족하는 함수, solution을 완성해주세요.
 * <p>
 * 제한 조건
 * x는 -10000000 이상, 10000000 이하인 정수입니다.
 * n은 1000 이하인 자연수입니다.
 * <p>
 * 입출력 예
 * x 	 n	answer
 * 2	 5	[2,4,6,8,10]
 * 4	 3	[4,8,12]
 * -4	 2	[-4, -8]
 */
public class IncreaseNumber {

	@Test
	public void testCase1() {
		long[] expected = { 2, 4, 6, 8, 10 };
		Assert.assertArrayEquals(expected, this.solution(2, 5));
	}

	@Test
	public void testCase2() {
		long[] expected = { 4, 8, 12 };
		Assert.assertArrayEquals(expected, this.solution(4, 3));
	}

	@Test
	public void testCase3() {
		long[] expected = { -4, -8 };
		Assert.assertArrayEquals(expected, this.solution(-4, 2));
	}

	@Test
	public void testCase경계값() {
		long[] result = this.solution(10000000, 1000);
		System.out.println(Arrays.toString(result));
	}

	@Test
	public void testCase경계값2() {
		long[] result = this.solution(-10000000, 1000);
		System.out.println(Arrays.toString(result));
	}

	public long[] solution(int x, int n) {
		if (x < -10000000 || x > 10000000) {
			throw new IllegalArgumentException("x는 -10000000 이상, 10000000 이하인 정수입니다");
		}

		if (n > 1000) {
			throw new IllegalArgumentException("n은 1000 이하인 자연수입니다.");
		}

		long[] answer = new long[n];

		for (int i = 0; i < n; i++) {
			answer[i] = x * (i + 1L); // 1L이 아닌 경우 13,14번째 테스트에서 실패함
		}
		return answer;
	}
}
