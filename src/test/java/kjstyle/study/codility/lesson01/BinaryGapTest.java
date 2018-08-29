package kjstyle.study.codility.lesson01;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by kjstyle on 2018. 8. 27..
 */

public class BinaryGapTest {

	@Test
	public void testSolution() {

		BinaryGap binaryGap = new BinaryGap();
		int result = binaryGap.solution(1041);
		System.out.println(result);

		assertEquals(result,5);
	}
}
