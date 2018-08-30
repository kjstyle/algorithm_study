package kjstyle.study.codility.lesson03;

/**
 * Created by kjstyle on 2018. 8. 29..
 */
public class FrogJump {

	public static void main(String[] args) {
		FrogJump frogJump = new FrogJump();
		int result = frogJump.solution(10, 71, 30);
		System.out.println(result);
		System.out.println((71 - 10) * 1.0 / 30);
		System.out.println(Math.ceil(2.4));
	}

	/**
	 * 첫번째 생각나는대로 코딩한 방법으로
	 * O(n) 이 나와서 패스~
	 *
	 * @param X
	 * @param Y
	 * @param D
	 * @return
	 */
	public int solutionByLoop(int X, int Y, int D) {

		int jumpCount = 0;
		int movingDistance = X;
		while (movingDistance < Y) {
			jumpCount++;
			movingDistance += D;
		}
		return jumpCount;
	}

	/**
	 * 나누기의 몫을 이용한 방법
	 * 시간복잡도를 O(1)을 충족하고 공간복잡도 O(0)
	 * 정수로만 나누기를 하면 소수점 버림을 한다
	 * 나눈 결과가 2.xxx 가 나올 경우, 실제 올림처리를 하면 target 이상을 뛰어넘을 수 있다
	 * 그래서 실제 움지여야할 거리(Y-X)에 0.1을 곱해서 실수로 만든다음 단위거리로 나눠서 실수 결과를 얻은다음 ceil로 올림처리
	 * 이럴 경우 몫이 딱 떨어지는 경우 동등조건에 해당하니 통과되고
	 * 소수점 이하 숫자가 발생하는 경우에는 한번 더 점프해야하니 올림처리로 타켓거리를 넘어설 수 있게 된다.
	 *
	 * @param X
	 * @param Y
	 * @param D
	 * @return
	 */
	public int solution(int X, int Y, int D) {
		return (int) Math.ceil((Y - X) * 1.0 / D);
	}
}
