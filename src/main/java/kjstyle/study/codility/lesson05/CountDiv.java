package kjstyle.study.codility.lesson05;

/**
 * Created by kjstyle on 2018. 9. 4..
 */
public class CountDiv {

	public static void main(String[] args) {
		CountDiv countDiv = new CountDiv();
		System.out.println("[1,1,11] : 0 = " + countDiv.solution(1, 1, 11));
		System.out.println("[6,11,2] : 3 = " + countDiv.solution(6, 11, 2));
		System.out.println("[0, 0, 11] : 1 = " + countDiv.solution(0, 0, 11));
		System.out.println("[10, 10, 5] : 1 = " + countDiv.solution(10, 10, 5));
		System.out.println("[11, 14, 2] : 2 = " + countDiv.solution(11, 14, 2));

		System.out.println("0%11 = " + 0 % 11);

	}

	/**
	 * A를 K로 나눈 몫과 B를 K로 나눈 몫의 차만 구하면 될 줄 알았지만 함정케이스를 통과 못함
	 *
	 * @param A
	 * @param B
	 * @param K
	 * @return
	 */
	public int solutionTry01(int A, int B, int K) {
		if (A == B) {
			if (A == 0) return 1;
			else {
				return (A % K == 0) ? 1 : 0;
			}
		}

		int firstQuotient = A / K;
		int lastQuotient = B / K;
		int diff = lastQuotient - firstQuotient;
		return diff + 1;
	}

	/**
	 * 역시나 무식하게 풀었더니 큰 입력값에 대해 timeout발생
	 *
	 * @param A
	 * @param B
	 * @param K
	 * @return
	 */
	public int solutionTrt02(int A, int B, int K) {
		int devidedCount = 0;
		for (int i = A; i <= B; i++) {
			if (i % K == 0) devidedCount++;
		}
		return devidedCount;
	}

	/**
	 * 범위의 시작하는 값인 A는 나누어 떨어지는 여부가 중요 -> 카운트에 포함될되는지에 영향을 줌
	 * 범위 끝 값인 B는 나눈 몫이 나누어떨어지든 소수점이 발생하든지 간에 5.xxx든 5.0이든 5는 범위 끝값으로 유효
	 * 나눈 값이 2 .. 5.xxx  라면 5-2 인 3이 발견되는 배수의 갯수가 됨
	 * 단, 두수가 같은데 나누어 떨어지는 경우 그냥 1로 카운팅
	 * 단 , 0 % 11 = 0 , 즉 0을 어떤 수로 나누면 나누어 떨어진다고 봐야하는게 최고 함정이었음.
	 *
	 * @param A
	 * @param B
	 * @param K
	 * @return
	 */
	public int solution(int A, int B, int K) {
		int devidedCount = 0;
		if (A % K == 0) devidedCount++;
		if (A != B) {
			int firstQuotient = A / K;
			int lastQuotient = B / K;
			int diff = lastQuotient - firstQuotient;
			devidedCount += diff;
		}
		return devidedCount;
	}
}
