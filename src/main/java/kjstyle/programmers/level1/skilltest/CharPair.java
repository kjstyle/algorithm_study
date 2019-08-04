package kjstyle.programmers.level1.skilltest;

import org.junit.Assert;
import org.junit.Test;

/**
 * 문제 설명
 * 대문자와 소문자가 섞여있는 문자열 s가 주어집니다. s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return 하는 solution를 완성하세요. 'p', 'y' 모두 하나도 없는 경우는 항상 True를 리턴합니다. 단, 개수를 비교할 때 대문자와 소문자는 구별하지 않습니다.
 * <p>
 * 예를 들어 s가 pPoooyY면 true를 return하고 Pyy라면 false를 return합니다.
 * <p>
 * 제한사항
 * 문자열 s의 길이 : 50 이하의 자연수
 * 문자열 s는 알파벳으로만 이루어져 있습니다.
 */
public class CharPair {

	@Test
	public void test() {
		String s1 = "pPoooyY";
		Assert.assertTrue(solution(s1));

		String s2 = "Pyy";
		Assert.assertFalse(solution(s2));
	}

	public boolean solution(String s) {
		int pCount = 0;
		int yCount = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ('p' == c || 'P' == c) {
				pCount++;
			}
			if ('y' == c || 'Y' == c) {
				yCount++;
			}
		}

		if (pCount == yCount) {
			return true;
		} else {
			return false;
		}
	}
}
