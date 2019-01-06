package kjstyle.cote01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 John likes to travel. He has visited a lot of cities over many years.
 John은 여행을 좋아한다. 그는 몇해에 걸처 수많은 도시를 방문했다.

 Whenever he visits a city, he takes a few photos and saves them on his computer.
 도시를 방문할 때마다, 그는 몇장의 사진을 촬영하고, 그 사진을 그의 컴퓨터에 저장한다.

 Each photo has a name with an extension ("jpg", "png" or jpeg")
 and there is a record of the name of the city where the photo was taken and the time and date the photo;
 for example: "photo.jpg,Warsaw, 2013-09-05 14:08:15"
 각 사진은 jpg,png,jpeg의 확장자를 가진 이름을 가지며
 사진이 촬영된 도시와 사진 시간과 일자의 record가 있다.

 Note that, in some rare cases,
 photos from different locations may share the time and date,
 due to timezone differences.
 드믄 경우지만, 다른 장소의 사진이 서로다른 timezone 때문에 시간과 일자가 섞일 수 있다.


 John notices that his way of filing photos on his computer has become a mess.
 John은 그의 컴퓨터 상의 사진파일링의 방법이 엉망이 되었음을 알게되었다.

 He wants to reorganize the photos.
 그는 사진들을 재구성하고자 한다.

 First he decides to group the photos by city,
 then, within each such group, sort the photos by the time
 they were taken and finally assign consecutive natural numbers to the
 photos, starting from 1.
 첫번째로, 그는 도시를 기준으로 사진을 그루핑하기로 결심했다.
 각 그룹내에서는
 그 사진들이 촬영된 시간을 기주능로 정렬을 시키고,
 최종적으로는 1부터 시작하는 자연수의 연속된 번호를 할당한다.

 Afterwards he intends to rename all the photos.
 이후에 그는 모든 사진을 rename하려고 하는 것이다.

 The new name of each photo should begin with the name of the city followed by the number
 already assigned to that photo.
 각 사진의 새 파일명은 사진에 이미 할당된 숫자에 따라 도시의 이름을 가지고 시작해야한다.

 The number of every photo in each group should have the same length
 (equal to the length of the largest number in this group),
 thus, John needs to add some leading zeros to the numbers.
 각 그룹 내의 각 사진의 숫자는 같은 자릿수(length)를 가져야한다.
 (해당 그룹에서 가장 큰 숫자의 자릿수와 같으면 됨)
 따라서,John은 숫자앞에 붙는 몇개의 0을 추가해야한다.

 The new name of the photo should end with the extension, which should remain the same.
 새 이름의 사진은 동일한 확장자로 끝나야한다.

 Your task is to help John by finding the new name of each photo.
 당신의 작업은 각 사진의 새이름을 찾는 것에 대해 John을 돕는 것이다

 Each of John's photos has the format:
 각 사진은 다음의 포맷이다.
 "<<photoname<extension>>, <<city_name>>, yyyy-mm-dd hh:mm:ss"
 , where<photoname>>", <<extension>>"and "<<city name>>" consist

 "사진.확장자, 도시명, yyyy-mm-dd hh:mm:ss"

 only of letters of the English alphabet and supply the name of the photo,
 the file name extension and the city name, respectively. Be aware that the
 names of the photos may not be unique
 각기 사진,파일명의 확장자,도시명이 영문 알파멧의 문자만으로 지어져야한다.
 사진 파일들의 이름이 유일하지 않을 수도 있음에 주의해라.


 Write a function:
 다음과 같은 함수를 작성하시오.
 class Solution {
 public String solution(String s);
 }


 that, given a string representing the list of M photos,
 returns the string representing the list of the new names of all photos
 (the order of photos should stay the same)
 M개의 사진의 리스트가 표현된 문자열이 주이지고,
 모든 사진의 새이름의 리스트가 표현된 문자열이 리턴된다.
 (사진의 순서는 동일하게 유지된다.)


 For example, given a string
 예들들어, 다음의 문자열이 주어지면,

 photo.jpg, Warsaw, 2013-09-05 14:08: 15
 john.png, London, 2015-06-20 15:13:22
 myFriends.png, Warsaw, 2013-09-05 14:07:13
 Eiffel.jpg, Paris, 2015-07-23 08:03:02
 pisatower.jpg, Paris, 2015-07-22 23:59:59
 BOB. jpg, London, 2015-08-05 00:02:03
 notredame.png, Paris, 2015-09-01 12:00:00
 me.jpg, Warsaw, 2013-09-06 15:40:22
 a.png, Warsaw, 2016-02-13 13:33:50
 b.jpg, Warsaw, 2016-01-02 15:12:22
 c.jpg, Warsaw, 2016-01-02 14:34:30
 d.ipa. Warsaw. 2016-01-02 15: 15:01
 e.png, Warsaw, 2016-01-02 09:49:09
 f.png, Warsaw, 2016-01-02 10:55:32
 g-jpg, Warsaw, 2016-03-29 22:13: 11



 your function should return
 당신이 만든 함수는 아래와 같이 리턴해야한다.

 Warsaw02.jpg
 Londonl.png
 Warsaw01.png
 Paris2.jpg
 Parisl.jpg
 London2. jpg
 Paris3.png
 Warsawo 3.jpg
 Warsawo9.png
 Warsaw07. jpg
 Warsaw06.ipg
 Warsaw08.jpg
 Warsaw04.png
 Warsaw05.png
 Warsawl0.jpg


 as there are ten photos taken in Warsaw (numbered from 01 to 10),
 10개의 warsaw에서 찍은 사진이 있어서 01 부터 10까지 채번되었고

 two photos in London (numbered from 1 to 2)
 런던에서의 2장의 사진으로 1부터 2까지로 채번되었고

 and three photos in Paris (numbered from 1 to 3)
 파리에서의 3장의 사진으로 1부터 3까지 채번되었다

 The new names of the photos are returned in the same order as in the given string.
 주어진 문자열의 동일한 순서대로, 새로운 사진의 이름들이 리턴되어야한다.

 Assume that:
 가정은 :

 - M is an integer within the range [1..100]
 M은 1~100까지 내의 정수이다.

 - Each year is an integer within the range [2000..2020];
 각 연도는 2000~2020 내의 정수이다

 - Each line of the input string is of the format
 "くくphotoname>>·<<extension>>, <<city-name>> , yyyy-mm-dd hh:mm:ss"
 and lines are separated withnewline characters;
 입력된 문자열의 각 행은 다음의 포맷이다
 "사진명.확장자 도시명, yyyy-mm-dd hh:mm:ss"
 라인들은 newline character로 구분되어진다

 - Each photo name (without extension)) and city name
 consists only of at least 1 and at most 20 letters from the English alphabet;
 각 사진명(확장자를 제외한)과 도시명은 오직 영문 알파벳을 사용한 최소 1개 부터 최대 20글자로 구성되어야한다.

 - Each name of the city starts with a capital letter and is followed by lower case letters;
 각 도시의 이름은 대문자로 시작하고, 그 뒤의 글자들은 소문자이다.

 - No two photos from the same location share the same date and time
 동일한 장소에서 동일한 날짜와 시간을 가진 2장의 사진은 존재하지 않는다.

 - Each extension is jpg" png" or "jpeg
 확장자는 jpg, png, jpeg 뿐이다.


 In your solution, focus on correctness.
 너의 솔루션은, 정확성에 포커스를 맞춰라.

 */
public class Test02 {

	public static void main(String[] args) {
		String S = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
			"john.png, London, 2015-06-20 15:13:22\n" +
			"myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
			"Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
			"pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
			"BOB.jpg, London, 2015-08-05 00:02:03\n" +
			"notredame.png, Paris, 2015-09-01 12:00:00\n" +
			"me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
			"a.png, Warsaw, 2016-02-13 13:33:50\n" +
			"b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
			"c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
			"d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
			"e.png, Warsaw, 2016-01-02 09:49:09\n" +
			"f.png, Warsaw, 2016-01-02 10:55:32\n" +
			"g.jpg, Warsaw, 2016-02-29 22:13:11";

		Test02 test02 = new Test02();
		System.out.println(test02.solution(S));
	}

	public String solution(String S) {

		String[] rows = S.split("[\\r\n]+");
		int rowsCount = rows.length;

		List<Photo> photoList = new ArrayList<>();

		for (int i = 0; i < rowsCount; i++) {
			String[] cols = rows[i].split(",");
			Date created;
			try {
				created = convertDateFromString(cols[2]);
			} catch (ParseException e) {
				System.out.println("날짜형태가 잘못되어 프로그램을 종료합니다 => " + i + "번째 열, 날짜 => " + cols[2]);
				return "";
			}
			String extension = cols[0].split("\\.")[1];
			photoList.add(new Photo(i, cols[0], cols[1], created, extension));
		}

		Map<String, List<Photo>> groupByCityMap = photoList
			.stream()
			.sorted(comparing((Photo p) -> p.created)) // 파일 생성일 순으로 정렬을 먼저하고
			.collect(Collectors.groupingBy(Photo::getCity)); // 도시명으로 그루핑


		List<Photo> resultList = new ArrayList<>();

		groupByCityMap.forEach((city, list) -> {
			int listCount = list.size();
			int serial = (int) (Math.log10(listCount) + 1); // 자릿수를 구하고
			for (int i = 0; i < listCount; i++) {
				String newIndexNo = String.format("%0" + serial + "d", (i + 1)); // 자릿수에 맞춰서 0으로 채워진 번호를 생성
				String newFileName = city + newIndexNo; // 새파일명(확장자제외)을 만듬 -> 확장자는 setter에서 구현.. TODO : 도메인 클래스로 이관필요
				list.get(i).setNewFileName(newFileName);
				resultList.add(list.get(i));
			}
		});

		resultList.sort(Comparator.comparing(Photo::getOriginalIndex)); // 본래 정렬 순서대로 다시 정렬

		StringBuilder result = new StringBuilder();

		for (Photo photo : resultList) {
			result.append(photo.getNewFileName());
			result.append("\n");
		}

		return result.toString();
	}

	private Date convertDateFromString(String strDate) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
	}

	public class Photo {
		private int originalIndex;
		private String originalFileName;
		private String city;
		private Date created;
		private String newFileName;
		private String extension;

		public Photo(int originalIndex, String originalFileName, String city, Date created, String extension) {
			this.originalIndex = originalIndex;
			this.originalFileName = originalFileName.trim();
			this.city = city.trim();
			this.created = created;
			this.extension = extension.trim();
		}

		public int getOriginalIndex() {
			return originalIndex;
		}

		public String getOriginalFileName() {
			return originalFileName;
		}

		public String getCity() {
			return city;
		}

		public Date getCreated() {
			return created;
		}

		public String getNewFileName() {
			return newFileName;
		}

		public void setNewFileName(String newFileName) {
			this.newFileName = newFileName + "." + this.extension;
		}

		@Override
		public String toString() {
			return "Photo{" +
				"originalIndex=" + originalIndex +
				", originalFileName='" + originalFileName + '\'' +
				", city='" + city + '\'' +
				", created=" + created +
				", newFileName='" + newFileName + '\'' +
				", extension='" + extension + '\'' +
				'}';
		}
	}
}
