package kjstyle.study.codility.lesson04;

import java.util.HashSet;

/**
 * 문제
 * 1차원 좌표가 있다 치고 개구리가 강을 건너기 위해 잎이 강위로 떨어지는데
 * 떨어진 잎은 움직이지 않고, 매초 잎이 하나씩 떨어짐
 * A[K] = 좌표, 형태고 K는 초다 1초,2초,3초...
 * 강을 건널 수 있는 타킷좌표가 X이고 X가 5라면 , K초 때 떨어져있는 잎의 자표들은 1,2,3,4,5 모두 떨어진 상태여야 하나씩 밟고 갈 수 있음
 * 즉 K뻔째 때 1부터 시작하는 좌표가 X까지 순열을 충족했을 때 강을 건널 수있고 이때 최소 K값을 구하는 문제
 */
public class FrogRiverOne {

	public static void main(String[] args) {
		int X = 2;
		int[] A = {2, 2, 1, 2, 2};

		FrogRiverOne frogRiverOne = new FrogRiverOne();
		int result = frogRiverOne.solution(X, A);
		System.out.println(result);
	}

	public int solution(int X, int[] A) {
		int len = A.length;
		HashSet<Integer> hs = new HashSet<Integer>();

		for (int i = 0; i < len; i++) {
			if (A[i] > X) {
				continue;
			}
			hs.add(A[i]);
			if (hs.size() >= X) {
				return i;
			}
		}
		return -1;
	}
}