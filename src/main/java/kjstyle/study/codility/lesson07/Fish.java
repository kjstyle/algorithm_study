package kjstyle.study.codility.lesson07;

import java.util.Stack;

/**
 * You are given two non-empty arrays A and B consisting of N integers.
 * Arrays A and B represent N voracious fish in a river, ordered downstream along the flow of the river.
 * <p>
 * The fish are numbered from 0 to N − 1.
 * If P and Q are two fish and P < Q, then fish P is initially upstream of fish Q.
 * Initially, each fish has a unique position.
 * <p>
 * Fish number P is represented by A[P] and B[P].
 * Array A contains the sizes of the fish. All its elements are unique.
 * Array B contains the directions of the fish. It contains only 0s and/or 1s, where:
 * <p>
 * 0 represents a fish flowing upstream,
 * 1 represents a fish flowing downstream.
 * If two fish move in opposite directions and there are no other (living) fish between them,
 * they will eventually meet each other. Then only one fish can stay alive
 * − the larger fish eats the smaller one. More precisely,
 * we say that two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0,
 * and there are no living fish between them. After they meet:
 * <p>
 * If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
 * If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.
 * We assume that all the fish are flowing at the same speed. That is,
 * fish moving in the same direction never meet.
 * The goal is to calculate the number of fish that will stay alive.
 * <p>
 * For example, consider arrays A and B such that:
 * <p>
 * A[0] = 4    B[0] = 0
 * A[1] = 3    B[1] = 1
 * A[2] = 2    B[2] = 0
 * A[3] = 1    B[3] = 0
 * A[4] = 5    B[4] = 0
 * Initially all the fish are alive and all except fish number 1 are moving upstream.
 * Fish number 1 meets fish number 2 and eats it, then it meets fish number 3 and eats it too.
 * Finally, it meets fish number 4 and is eaten by it.
 * The remaining two fish, number 0 and 4, never meet and therefore stay alive.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A, int[] B); }
 * <p>
 * that, given two non-empty arrays A and B consisting of N integers,
 * returns the number of fish that will stay alive.
 * <p>
 * For example, given the arrays shown above, the function should return 2, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [0..1,000,000,000];
 * each element of array B is an integer that can have one of the following values: 0, 1;
 * the elements of A are all distinct.
 * <p>
 * index가 작을수록 상류에 위치한 물고기고, 위치는 유니크함
 * A배열 => 물고기들이 있고, 값은 물고기 크기를 나타냄
 * B배열 => 물고기들의 방향을 나타내고, 0은 상류로 이동하는 것을, 1은 하류로 이동하는 것을
 * 물고기의 속도는 모두 같아서 동일 방향으로 이동하는 물고기가 만날 수 없음
 * 서로 만났을 때 큰물고기가 작은 물고기를 잡아먹는다.
 * <p>
 * 물고기 수만큼 루프를 돌면서
 * 내려가는 물고기라면 스택에 담고 다음 물고기로 이동
 * 올라가는 물고기라면 스택에서 내려가는 물고기를 하나씩 꺼내봐서(peek) 지금 물고기와 대결을 시킴
 * - 올라가는 물고기가 더 크면 -> 내려가는 물고기를 잡아먹고 -> 스택에서 다음 내려가는 물고기를 꺼내서 대결 시킴 -> 스택이 빌 때까지 반복
 * - 내려가는 물고기가 더 크면 -> 현재 index의 올라가는 물고리르 잡아먹고 -> 바깥루프 기준으로 돎
 */
public class Fish {

	public static void main(String[] args) {
		Fish fish = new Fish();

		int[] A = {4, 3, 2, 1, 5};
		int[] B = {0, 1, 0, 0, 0};

		System.out.println("살아남은 물고기는 2여야, 실제결과 => " + fish.solution(A, B));

	}

	public int solution(int[] A, int[] B) {
		Stack<Integer> downFish = new Stack<Integer>();

		int fishCount = A.length;
		int aliveFish = fishCount;

		for (int i = 0; i < fishCount; i++) {
			if (B[i] == 1) {
				downFish.push(i);
			} else {
				while (true) {
					if (downFish.isEmpty()) {
						break;
					}
					int peekDownFishIndex = downFish.peek();
					if (A[i] > A[peekDownFishIndex]) {
						downFish.pop();
						aliveFish--;
						continue;
					} else {
						aliveFish--;
						break;
					}
				}
			}
		}
		return aliveFish;
	}

	public int solutionB(int[] A, int[] B) {
		Stack<Integer> downstreamFishes = new Stack<Integer>();

		int fishCount = A.length;
		int aliveFish = fishCount;

		for (int i = 0; i < fishCount; i++) {
			if (B[i] == 0) { // i번째 물고기가 상류로 가는 물고기이면
				while (true) {
					if (downstreamFishes.isEmpty()) { // 내려가는 물고기가 없으면 지금 올라가는 물고는 그냥 산다고 보면됨
						break;
					}
					// 내려가는 스택에 쌓여있는 top에 있는 물고기를 하나 peek해서
					int downFishIndex = downstreamFishes.peek();

					// 현재 index의 올라가는 물고가가 더 큰 물고기라면
					// 다운피쉬를 잡아먹고 다음
					// 다운피쉬를 꺼내서 대결하기 위해 루프를 추가로 돎(continue).
					if (A[downFishIndex] < A[i]) {
						downstreamFishes.pop();
						aliveFish--; // 내려가는 물고기가 잡혀먹혔으니 살아남은 물고기 수에서 -1
						continue;
					} else { // 내려가는 물고기가 더 크다면, 다음 물고기로 이동해야하니 break
						aliveFish--; // 올라가는 물고기가 잡혀먹혔으니 살아남은 물고기 수에서 -1
						break;
					}
				}
			} else { // i번째 물고기가 하류로 내려가는 물고기라면 스택에 push
				downstreamFishes.push(i);
			}
		}
		return aliveFish;
	}
}
