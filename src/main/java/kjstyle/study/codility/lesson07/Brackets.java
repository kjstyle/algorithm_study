package kjstyle.study.codility.lesson07;

import java.util.Stack;

/**
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 * <p>
 * S is empty;
 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(String S); }
 * <p>
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 * <p>
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]",
 * the function should return 0, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..200,000];
 * string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 * <p>
 * 괄호 쌍이 맞는가를 찾는 문제로
 * 전형적인 스택 문제
 * 여는 괄호들은 무조건 push
 * 닫는 괄호들을 만나면 스택에서 pop (스택에 있는 녀석들은 모두 여는 괄호들만 있을거라..)해서 동일한 종류의 괄호가 아니면 쌍이 안맞는거니 0을 리턴하고 끝
 * 루프 내에서 return될 일이 없으면 쌍이 알맞은 거라고 판단하고 1을 리턴
 */
public class Brackets {

	public static void main(String[] args) {

		Brackets brackets = new Brackets();

		String S = "{[()()]}";
		System.out.println("result is expected 1, actual is " + brackets.solution(S));

		String T = "";
		System.out.println("result is expected 1, actual is " + brackets.solution(T));

		String U = "{[()()]}}";
		System.out.println("result is expected 0, actual is " + brackets.solution(U));

		String V = "[{[()()]}}";
		System.out.println("result is expected 0, actual is " + brackets.solution(U));

		String W = "{{{{";
		System.out.println("result is expected 0, actual is " + brackets.solution(W));
	}

	public int solution(String S) {
		Stack<Character> bracketStack = new Stack<Character>();

		int size = S.length();

		for (int i = 0; i < size; i++) {
			char c = S.charAt(i);

			if (c == '(' || c == '{' || c == '[') {
				bracketStack.push(c);
			} else {
				// 발견된 닫는 괄호가 있는데 스택이 비어있어도 쌍이 안맞는 것!!
				// pop하기 전에 걸러줘야함.
				if (bracketStack.isEmpty()) {
					return 0;
				}
				char pop = bracketStack.pop();
				if (pop == '(' && c != ')') {
					return 0;
				}
				if (pop == '{' && c != '}') {
					return 0;
				}
				if (pop == '[' && c != ']') {
					return 0;
				}
			}
		}
		// 쌍이 모두 맞다면, 스택은 비어있어야함
		return (bracketStack.isEmpty()) ? 1 : 0;
	}
}
