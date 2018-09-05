package kjstyle.study.codility.lesson05;

import java.util.HashMap;

/**
 * Created by kjstyle on 2018. 9. 5..
 */
public class GenomicRangeQuery {

	public static void main(String[] args) {
		GenomicRangeQuery genomicRangeQuery = new GenomicRangeQuery();
		int[] P = {2, 5, 0};
		int[] Q = {4, 5, 6};

		int[] result = genomicRangeQuery.solution("CAGCCTA", P, Q);
		for (int num : result) {
			System.out.println(num);
		}
	}

	public int[] solutionTry01(String S, int[] P, int[] Q) {

		int[] impactFatorsInString = new int[S.length()];

		HashMap<Character, Integer> impactFatorMap = new HashMap<Character, Integer>();
		impactFatorMap.put('A', 1);
		impactFatorMap.put('C', 2);
		impactFatorMap.put('G', 3);
		impactFatorMap.put('T', 4);

		for (int i = 0; i < S.length(); i++) {
			impactFatorsInString[i] = impactFatorMap.get(S.charAt(i));
		}

		int[] found = new int[P.length];
		int minInLoop;

		for (int i = 0; i < P.length; i++) {
			minInLoop = Integer.MAX_VALUE;
			for (int j = P[i]; j <= Q[i]; j++) {
				minInLoop = Math.min(impactFatorsInString[j], minInLoop);
			}
			found[i] = minInLoop;
		}
		return found;
	}

	public int[] solutionOnline(String S, int[] P, int[] Q) {
		int N = S.length();
		int M = P.length;

		int[] result = new int[M];
		// 2차원 배열에 index 증가에 따른 char 별 누적 카운트
		// [0] = A, [1] = C, [2] = G, [3] = T
		int[][] accumulation = new int[4][N + 1];

		for (int i = 0; i < N; i++) {
			// 문자를 찾으면 누적 카운트 ++
			switch (S.charAt(i)) {
				case 'A':
					accumulation[0][i + 1]++;
					break;
				case 'C':
					accumulation[1][i + 1]++;
					break;
				case 'G':
					accumulation[2][i + 1]++;
					break;
				case 'T':
					accumulation[3][i + 1]++;
					break;
			}

			// 마지막 반복 제외
			if (i < N - 1) {
				// 현재 index 까지의 누적 카운트를 다음 index에도 누적
				for (int j = 0; j < 4; j++) {
					accumulation[j][i + 2] = accumulation[j][i + 1];
				}
			}
		}


		for (int i = 0; i < M; i++) {
			int start = P[i];
			int end = Q[i];

			for (int j = 0; j < 4; j++) {
				// 끝 지점과 시작 지점의 누적 카운트가 다르다면 (= 해당 문자가 있다면)
				if (accumulation[j][start] != accumulation[j][end + 1]) {
					// 발견시 최소값에 삽입
					result[i] = j + 1;
					// 최소 factor 부터 확인하므로, 발견시 이후 factor는 확인할 필요 없음
					break;
				}
			}
		}

		return result;
	}

	/**
	 * 구간합(prefix sum)문제로
	 * 2차원 정수형 배열을 만들고 X축은 문자(A,C,G,T), Y축은 0번째 여백을 제외하고 S의 길이만큼 잡아서
	 * S를 chartAt으로 하나씩 꺼내와서 발견되는 문자에 대해 i+1번째 행에 해당 문자열에 +1씩 해주고, 해당행의 숫자를 i+2번째에 그대로 복붙
	 * 다음 행(=다음 루프) 때 다음 문자를 꺼내와서 동일하게 처리하면
	 * 범위를 잡고 누적합의 결과가 변화가 있을 경우(시작범위 바로 전부터 종료범위 값을 비교해서 다르면 변화했던 것=출현했던 것=출현하면 +1을 해주니까=이값은 누적되서 카운팅되니까)
	 * 출현했다고 판단 가능하고
	 * 작은 impact factor부터(A->C->G->T) 범위 내 이런 변화여부를 조사해서 발견되면 그 놈이 범위 내 최소 임팩트 팩터가 됨
	 *
	 * @param S
	 * @param P
	 * @param Q
	 * @return
	 */
	public int[] solution(String S, int[] P, int[] Q) {
		int N = S.length();
		int[][] accumulation = new int[4][N + 1];
		int[] result = new int[P.length];
		int tempImpactFactor;
		for (int i = 0; i < N; i++) {
			// i번째 문자의 impact factor에 해당하는 값을 +1 해줌
			tempImpactFactor = this.getImpactFactorByChar(S.charAt(i));
			accumulation[tempImpactFactor][i + 1]++;

			// 마지막 루프 때는 패스~~ 그 다음 행에 누적 연산을 넘겨줄 필요가 없으니
			// 뒤에서 세번째 까지만 누적합 이관을 해줘야 다다음번이 끝순번임
			if (i < N - 1) {
				for (int j = 0; j < 4; j++) {

					accumulation[j][i + 2] = accumulation[j][i + 1];
				}
			}
		}

		for (int i = 0; i < P.length; i++) {
			int start = P[i];
			int end = Q[i];

			// A-> C -> G -> T 순서대로 범위시작바로 전과 범위끝 누적합의 변화가 발견되는지 검사
			// 작은숫자에서 큰숫자로 이동하니 출현이 감지(누접합 값이 달라진 경우)될 때 해당 문자의 인덱스+1을 i번째 결과에 담고 내부 루프를 빠져나감
			// 그게 최소 impact factor
			for (int j = 0; j < 4; j++) {
				if (accumulation[j][start] != accumulation[j][end + 1]) {
					result[i] = j + 1;
					break;
				}
			}
		}

		return result;
	}

	private int getImpactFactorByChar(char c) {
		switch (c) {
			case 'A':
				return 0;
			case 'C':
				return 1;
			case 'G':
				return 2;
			case 'T':
				return 3;
		}
		return -1;
	}
}
