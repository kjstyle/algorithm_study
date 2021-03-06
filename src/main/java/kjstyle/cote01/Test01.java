package kjstyle.cote01;

/**
 A non-negative integer variable V is given. There are two actions available
 that modify its value:
 - if V is odd, subtract 1 from it;
 - if V is even, divide it by 2.
 These actions are performed until the value of V becomes 0.
 For example, if V initially contains value 28, it will become 0 after seven
 steps:
 - V contains value 28, which is even: divide by 2 and obtain 14;
 - V contains value 14, which is even: divide by 2 and obtain 7

 A non-negative integer variable V is given. There are two actions available
 that modify its value:
 - if V is odd, subtract 1 from it;
 - if V is even, divide it by 2.
 These actions are performed until the value of V becomes 0.
 For example, if V initially contains value 28, it will become 0 after seven
 steps:
 - V contains value 28, which is even: divide by 2 and obtain 14;
 - V contains value 14, which is even: divide by 2 and obtain 7
 - V contains value 7, which is odd: subtract 1 and obtain 6;
 - V contains value 6, which is even: divide by 2 and obtain 3;
 - v contains value 3, which is odd: subtract 1 and obtain 2;
 - V contains value 2, which is even: divide by 2 and obtain 1;
 - V contains value 1, which is odd: subtract 1 and obtain 0.
 Write a function:
 class Solution fpublic int solution(String S);
 that, given a zero-indexed string S consisting of N characters containing a
 binary representation of the initial value of variable V, returns the number of
 steps after which the value of V will become 0, as described above.
 */
public class Test01 {

	public static void main(String[] args) {
		System.out.println(Integer.parseInt("011100", 2));
		Test01 test01 = new Test01();
		System.out.println("기댓값 : 7 " + test01.solution("011100"));
	}

	/**
	 * solution
	 * 1. 소인수분해하면 되지 않을까했는데, 그냥 돌려도 O(n)인 상황이라 패스
	 * 2. 문제의도가 뭘까하다가 재귀적인 연산을 묻는 문제로 파악됨
	 * 3. 재귀함수가 종료되는 조건인 -> 다음 입력값이 0이 될때까지만 통제시키고 (if input == 0)
	 * 4. 재귀적으로 계속 돌리면 됨
	 *
	 * @param S
	 * @return
	 */
	public int solution(String S) {
		int V = Integer.parseInt(S, 2);
		return operate(V, 0);
	}

	/**
	 * 재귀호출로 동작하면서
	 * 1. 스텝을 증가시키고
	 * 2. 규칙에 맞는 연산을 하그 연산된 값을 다음 재귀호출의 입력을 넘기고
	 * 3. 증가된 스텝수도 같이 넘겨서 스텝의 수를 누적해서 증가되도록 함
	 *
	 * @param input
	 * @param stepCnt
	 * @return
	 */
	public int operate(int input, int stepCnt) {
		// 0이 될 때까지
		if (input == 0) {
			return stepCnt;
		}
		stepCnt++; // 아직 0이 안되어서 다음 재귀호출하기 전에 스텝수를 하나 증가시켜준다.

		if (input % 2 == 0) { // V가 짝수일 떄 -> 나누기 2
			return operate(input / 2, stepCnt);
		} else { // V가 홀수일 때 -> 빼기 1
			return operate(input - 1, stepCnt);
		}
	}
}
