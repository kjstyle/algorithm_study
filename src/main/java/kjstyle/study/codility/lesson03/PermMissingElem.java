package kjstyle.study.codility.lesson03;

/**
 * Created by kjstyle on 2018. 8. 29..
 */
public class PermMissingElem {

	public static void main(String[] args) {
		PermMissingElem permMissingElem = new PermMissingElem();
		int[] A = {2, 3, 1, 5};
		int[] B = new int[100000];
		for (int i = 0; i < 100000; i++) {
			if (i == 99) continue;
			B[i] = i;
		}
		int lostNum = permMissingElem.solution(B);
		System.out.println(lostNum);
	}

	public int solution(int[] A) {
		int lastNum = A.length + 1;
		long countOneToLast = lastNum;
		long sumElement = 0;
		for (int num : A) {
			sumElement += num;
		}
		long sumOneToLastNum = ((1 + lastNum) * countOneToLast) / 2;
		int lostNum = (int) (sumOneToLastNum - sumElement);
		return lostNum;
	}
}
