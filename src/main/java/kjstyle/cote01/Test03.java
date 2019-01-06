package kjstyle.cote01;

import java.util.HashSet;

/**
 People are waiting for an elevator in a hotel. The elevator has limited
 capacity and you would like to analyse its movement.
 호텔의 사람들이 엘리베이터를 기다리고 있다. 엘리베이터는 수용량의 제한이 있고, 당신의 엘리베이터의 운행을 분석하길 원한고있다.

 The hotel has floors numbered from 0 (ground floor) to M. The elevator has
 a maximum capacity of X people and a weight limit of Y.
 호텔에는 지상층인 0층부터 M층까지 번호가 부여되어있다.
 일리베이터의 최대 수용량은 X명의 사람과 Y의 무게이다.

 There are N people gathered at the ground floor, standing in a queue for the elevator.
 지상층에 N명의 모여있는 사람들이 있고, 엘리베이터를 타기 위해 1개의 queue에 줄서있다.

 You are given every person's weight A[K] and target floor B[K].
 (That is, A[O] and B[O] represent the first person in the queue.)
 당신에게는 각 사람들의 무게 A[K]와 가고자하는 층수 B[K]의 정보가 주어져있다.
 (즉, A[0] 와 B[[0]는 queue 내의 첫번째 사람을 나타낸다.)

 People continue to enter the elevator, in the order of their position in the queue
 (and push the buttons for their target floors),
 queue 내의 각 위치의 순서에 따라 사람들은 계속해서 엘리베이터로 들어가고있고 (그리고 가고자하는 층수의 버튼을 누른다.)

 for as long as there is room for them.
 (The queue order cannot be changed even if there is room in the elevator for a particular person from the middle of the queue.)
 가능한 오랫동안 그들을 위한 room이 있다.??
 (queue의 중간에 특정사람을 위한 room이 있다하더라도, queue의 순서는 바꿔질 수 없다.)


 Then elevator goes up and stops at every selected fㅣoor, and finally returns to the ground floor.
 그리고나서 엘리베이터는 각각 선택된 층으로 올라가고 멈추고, 그리고 최종적으로 자상층으로 돌아온다.

 This process is repeated until there are no more people in the queue.
 이 프로세스는 queue에 더이상 사람이 없을 때까지 반복된다.

 The goal is to count the total number of times that the elevator stops
 목표는 엘리베이터가 멈춘 총 횟수를 세는 것이다.

 For example, consider a hotel with floors numbered from 0 to M = 5, with
 an elevator with a maximum capacity of X = 2 people and a weight limit of
 Y = 200. The weights A and target floors B are:
 예를들어,
 X=2 최대2명까지, Y=200 총200kg까지의 총중량제한을 가진 엘리베이터가 있는
 (= 한번에 최대 2명(X)까지 탑승가능하고, 탑승인원의 총중량제한은 200kg(Y) 이라는..)
 0부터 M=5로 번호가 매겨진 층을 가진 호텔이 있다고하면..
 중량 A 와 목표층수 B 는 아래와 같이 표현할 수 있다.
 A[0] = 60		B[0] = 2   => 0번째 queue의 사람은 60kg이고, 2층에 가려는 사람 (0층부터 시작한다잉)
 A[1] = 80		B[1] = 3   => 1번째 queue의 사람은 80kg이고, 3층에 가려는 사람
 A[2] = 40		B[2] = 5   => 2번째 queue의 사람은 40kg이고, 5층에 가려는 사람 (M=5니까 5층이 꼭대기층임)

 The elevator will take the first two passengers together, stop at the 2nd and
 3rd floors, then return to the ground floor. Then, it will take the last passenger,
 stop at the 5th floor and return to the ground floor.
 엘리베이터는 처음에는 2명은 승객을 함께 태울 것이고(중량에는 안걸리지만 최대인원수에 걸려서..2명까지 태움),
 2층(카운트 1번)과 3층(카운트 2번)에 멈출 것이다. 그리고나서 지상층(0층)(카운트 3번)으로 돌아올 것이다. 그리고나서 엘리베이터는 마지막(3번째)승객을 태우고,
 5층(카운트 4번)에 멈출거고 다시 지상층(0층)(카운트 5번)으로 돌아올 것이다.

 In total, the elevator will stop five times. Note that this number includes the last stop at the ground floor.
 종합해보면, 엘리베이터는 5번 멈출 것이다. 5번이라는 횟수는 마지막에 지상층에서 멈춘 것을 포함한 숫자이라는 것을 명심해라.

 Write a function:
 class Solution {
 public int solution(int[] A, intl B, int M, int x, int Y);
 }

 that, given arrays A and B consisting of N integers, and numbers X, Y and M as described above,
 returns the total number of times the elevator stops.
 즉, N개의 정수인 X,Y,M의 숫자로 구성된 A와 B 배열이 주어졌을 때, 엘리베이터가 멈춘 총횟수 리턴해라.

 For example, given the above data, the function should return 5, as explained above.
 예를들어, 위 데이터가 주이지면, 아래 설명에 따라 function은 5를 리턴해야한다..


 For example, given M = 3, X = 5, Y = 200 and the following arrays:
 예를들어, M=3(0층~4층), X=5(최대 5명까지), Y=200(한번에 200kg까지) 과 , 아래의 배열에 따르면,
 A[0] = 40		B[0] = 3
 A[1] = 40		B[1] = 3
 A[2] = 100		B[2] = 2
 A[3] = 80		B[2] = 2
 A[4] = 20		B[4] = 3

 the function should return 6, as the elevator will move in two stages:
 with the first three people and then with the two remaining people.
 function은 6을 리턴해야하고, 엘리베이터는 2 stage 내에서 움직일 것 이다.
 처음에 3명을 (40+40+100 = 180이고..3번 사람까지 태우면 중량초과), 그리고 2명을 남기고

 3층에 0번사람과 1번사람을 내려주고(카운트 1번)
 2층에 2번사람을 내려주고 (카운트 2번)
 다시 지상층으로 내려와서 3번사람과 4번사람을 태우고 (카운트 3번)
 2층에 3번사람을 내려주고 (카운트 4번)
 3층에 4번 사람으 내려주고 (카운트 5번)
 다시 지상층으로 내려온다 (카운트 6번)


 Write an efficient algorithm for the following assumptions:
 다음의 가정에 따라 효율적인 알고리즘을 작성하시오.

 - N, M and X are integers within the range [1..100,000];
 - Y is an integer within the range [1..1,000,000,000];
 - each element of array A is an integer within the range [1.Y;
 - each element of array B is an integer within the range [1.M].
 */
public class Test03 {

	public static void main(String[] args) {
		int A[] = {40, 40, 100, 80, 20};
		int B[] = {3, 3, 2, 2, 3};
		int M = 3; // 0층 ~ 3층까지
		int X = 5; // 한번에 최대 5명까지 가능
		int Y = 200; // 한번에 최대 200kg까지 가능

		Test03 test03 = new Test03();
		System.out.println(test03.solution(A, B, M, X, Y));
	}

	public int solution(int[] A, int[] B, int M, int X, int Y) {
		int cnt = A.length; // 줄서있는 사람의 수
		int totalStop = 0; // 총멈춘횟수
		int i = 0;

		while (i < cnt) { // 줄선 사람들 수만큼 도는데
			int totalWeight = 0;  // 1회 총 무게
			int totalPeople = 0;  // 1회 총 사람수

			HashSet floor = new HashSet(); // 현재 태운 사람들 중에 같은층으로 가는 사람들은 한번만 멈출거라 set에 담아서 중복을 제거

			// while을 돌며 태우기 시작하는데...
			// 탑승한 인원수는 X명 미만이어야하고
			// 줄선사람의 총인원수를 넘어서는 안되고
			// 탑승자의누적중량과 지금 한명 더 태우려는 사람까지 포함한 중량은 Y이하여야..
			// 줄선 사람들을 한명 씩 태울때마다 i++
			while (totalPeople < X && i < cnt && (totalWeight + A[i] <= Y)) {
				totalWeight += A[i];
				floor.add(B[i]); //현재 탑승객들의 목표층수를 set에 담는다.
				i++;
				totalPeople++;
			}

			// 현재 누적 멈춘수에 내려준 층수(set의 요소수)를 더하고 지상층으로 돌아오는 수(+1)
			totalStop = totalStop + floor.size() + 1;

			// 남은 줄선 사람들을 태워야하니 다시 while 시작
		}
		return totalStop;
	}
}
