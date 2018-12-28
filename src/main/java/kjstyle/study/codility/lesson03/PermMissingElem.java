package kjstyle.study.codility.lesson03;

/**
 An array A consisting of N different integers is given.
 The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.

 Your goal is to find that missing element.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A, returns the value of the missing element.

 For example, given array A such that:

 A[0] = 2
 A[1] = 3
 A[2] = 1
 A[3] = 5
 the function should return 4, as it is the missing element.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [0..100,000];
 the elements of A are all distinct;
 each element of array A is an integer within the range [1..(N + 1)].

 1~ N+1 까지의 집합이 있고
 집합 내에서 빠진 숫자를 찾는 문제
 */
public class PermMissingElem {

	public static void main(String[] args) {
		PermMissingElem permMissingElem = new PermMissingElem();
		int[] A = {2, 3, 1, 5};
		int lostNum = permMissingElem.solution(A);
		System.out.println(lostNum);
	}

	public int solution(int[] A) {
		int lastNum = A.length + 1; // 배열 내 엘리먼트 개수 + 1 => N+1 => 순서대로 나열했을 때 가장 큰수
		long countOneToLast = lastNum; // 끝수 = 빠진 아이까지 포함한 전체 개수 = N+1
		long sumOfElements = 0;
		// 현재 배열 내의 엘리먼트를 모두 더해서 sumOfElements에 저장
		for (int num : A) {
			sumOfElements += num;
		}
		// 1부터 9까지 합의 빠르게 구하는 방법은 ((1+9)*9)/2 -> (첫번째수+마지막수)*요소의개수/2
		// sumOneToLastNum은 빠진아이까지 포함된 엘리먼트의 총합임
		long sumOneToLastNum = ((1 + lastNum) * countOneToLast) / 2;

		// 빠진아이까지 포함된 본래의 총합에서 현재 배열내 엘리먼트값의 총합을 빼면 -> 빠진 아이를 찾을 수 있음
		int lostNum = (int) (sumOneToLastNum - sumOfElements);
		return lostNum;
	}
}
