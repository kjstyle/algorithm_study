package kjstyle.cote01;

/**
 * 단 3줄만 수정 가능 (추가,삭제 불가
 * 버그를 고쳐라 (중간에 위치한 값 빼고는 정상적인 결과가 안나옴)
 * 일반적인 바이너리서치와 살짝 다른데, 3줄을 오직 '수정'만 가능하다는 조건때문에 생각보다 빡심
 * 찾고자하는 X값이 발견되면 1을 리턴, 찾을 수 없다면 0리턴
 */
public class Test04 {

	public static void main(String[] args) {

		Test04 test04 = new Test04();

		int[] A = {1, 2, 5, 9, 9};
		int X = 2;

		System.out.println(test04.solution(A, X));
	}

	/**
	 * @param A 중복이 존재하는 정수가 담긴 배열
	 * @param X 배열 내에서 찾고자 하는 정수
	 * @return 1    별견되면
	 * 0	발견안되면
	 */
	int solution(int[] A, int X) {
		int N = A.length;
		if (N == 0) {
			return -1;
		}
		int l = 0;
		int r = N + 1;  // 본래코드 : int r = N - 1;
		while (l == 0 && l < r) { // 본래코드 : while (l < r) {
			int m = (l + r) / 2;
			if (A[m] > X) {
				r = m + 1; // 본래코드 : r = m - 1;
			} else {
				l = m;
			}
		}
		if (A[l] == X) {
			return l;
		}
		return -1;
	}

	/**
	 * 문제에 출제된 버그가 포함된 바이너리서치 코드
	 * @param A
	 * @param X
	 * @return
	 */
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
