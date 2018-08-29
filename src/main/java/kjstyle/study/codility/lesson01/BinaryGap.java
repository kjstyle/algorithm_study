package kjstyle.study.codility.lesson01;

/**
 * Created by kjstyle on 2018. 8. 27..
 */
public class BinaryGap {

	public int solution(int N) {
		if (N < 0) {
			return -1;
		}

		String binaryString = Integer.toBinaryString(N);
		int len = binaryString.length();
		int zeroCount = 0;
		int maxCount = 0;
		for (int i = 0 ; i < len ; i++ ) {
			if ('1' == binaryString.charAt(i)) {
				maxCount = Math.max(maxCount,zeroCount);
				zeroCount = 0;
			} else {
				zeroCount ++;
			}
		}
		return maxCount;
	}

	public static void main(String[] args) {
		BinaryGap binaryGap = new BinaryGap();
		int result = binaryGap.solution(328);
		System.out.println(result);
	}
}
