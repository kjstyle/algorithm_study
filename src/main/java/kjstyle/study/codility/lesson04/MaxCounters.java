package kjstyle.study.codility.lesson04;


/**
 * 맥스카운터
 * A[K] = X 에서 1 <= X <= N 사이라면, X의 카운터를 increase()함수를 통해 카운트를 1개 올리고,
 * A[K] = X 에서 X = N+1 즉, N초과의 숫자라면, 각 숫자의 카운터를 가장 큰 카운트수로 모두 일괄 변경한다.
 * 핵심은, 맥스카운터로 모두 일괄 업데이트 하는걸 무식하게 하면 timeout으로 탈락
 * tempMax와 doneMax(N을 넘어선 숫자가 발견되었을 때 요소 중 최대값)을 잘 관리해서
 * 돌면서 doneManx 보다 작은 놈이 발견되면 이때 doneMax로 올려주고,
 * 이미 지나가벼렸을 녀석들 중에 doneMax보다 작은넘들은 한번더 돌면서 변경해주면 끝
 * 근데, 졸라 어렵다..
 * 무식하게 X > N일 때 배열 모든 요소를 현재 max값을 일괄 업데이트했는데, 이러면 10000개 요소가 넘을 경우 100% timeout걸려서 시간복잡도에서 탈락!!
 */
public class MaxCounters {

	public static void main(String[] args) {
		MaxCounters maxCounters = new MaxCounters();
		int N = 5;
		int[] A = {3, 4, 4, 6, 1, 4, 4};
		int[] result = maxCounters.solution(N, A);
		for (int num : result) {
			System.out.println(num);
		}
	}

	public int[] solution(int N, int[] A) {
		int[] counters = new int[N];
		int tempMax = 0;
		int doneMax = 0;

		for (int X : A) {
			if (X > N) {
				doneMax = tempMax;
			} else {
				if (counters[X - 1] < doneMax) {
					counters[X - 1] = doneMax;
				}
				counters[X - 1]++;
				tempMax = Math.max(counters[X - 1], tempMax);
			}
		}

		for (int i = 0; i < N; i++) {
			if (counters[i] < doneMax) {
				counters[i] = doneMax;
			}
		}
		return counters;
	}
}
