package kjstyle.woohyung;

import java.util.HashSet;

/**
 * 엘리베이터 운행횟수 문제
 */
public class Test03 {

	public int solution(int[] A, int[] B, int M, int X, int Y) {
		int cnt = A.length;
		int totalStop = 0;
		int i = 0;

		while (i < cnt) {
			int totalWeight = 0;
			int totalPeople = 0;

			HashSet floor = new HashSet();
			while (totalPeople < X && i < cnt && (totalWeight + A[i] <= Y)) {
				totalWeight += A[i];
				floor.add(B[i]);
				i++;
				totalPeople++;
			}
			totalStop = totalStop + floor.size() + 1;
		}
		return totalStop;
	}

}
