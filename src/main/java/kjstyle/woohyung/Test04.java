package kjstyle.woohyung;

/**
 * Created by kjstyle on 30/12/2018.
 */
public class Test04 {

	public static void main(String[] args) {

		Test04 test04 = new Test04();

		int[] A = {1, 2, 5, 9, 9};
		int X = 2;

		System.out.println(test04.solution(A, X));
	}

	int solution(int[] A, int X) {
		int N = A.length;
		if (N == 0) {
			return -1;
		}
		int l = 0;
		int r = N - 1;
		while (l == 0 && l < r) { // 수정 1
			int m = (l + r) / 2;
			if (A[m] > X) {
				r = m - 1;
			} else if (A[m] < X) { // 수정 2
				l = m;
			}
		}
		if (A[l] == X) {
			return l;
		}
		return -1;
	}

	int solutionOriginal(int[] A, int X) {
		int N = A.length;
		if (N == 0) {
			return -1;
		}
		int l = 0;
		int r = N - 1;
		while (l < r) {
			int m = (l + r) / 2;
			if (A[m] > X) {
				r = m - 1;
			} else {
				l = m;
			}
		}
		if (A[l] == X) {
			return l;
		}
		return -1;
	}
}
