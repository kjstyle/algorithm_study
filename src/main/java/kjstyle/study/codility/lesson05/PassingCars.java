package kjstyle.study.codility.lesson05;

/**
 * Created by kjstyle on 2018. 9. 3..
 */
public class PassingCars {

	public static void main(String[] args) {
		int[] A = {0, 1, 0, 1, 1};
		PassingCars passingCars = new PassingCars();
		int result = passingCars.solution(A);
		System.out.println("A -> expected result : 5 = " + result);
	}

	public int solution(int[] A) {
		int len = A.length;
		int[] couuntNo1FromPosToEnd = new int[len];
		int countCombination = 0;

		int foundNo1 = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (A[i] == 1) {
				foundNo1++;
			}
			if (A[i] == 0) {
				countCombination += foundNo1;
				if (countCombination > 1000000000) {
					return -1;
				}
			}
		}

		return countCombination;
	}
}
