package kjstyle.woohyung;

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
 Whenever he visits a city, he takes a few photos and saves them on his
 computer. Each photo has a name with an extension ("jpg", "png" or
 jpeg") and there is a record of the name of the city where the photo was
 taken and the time and date the photo; for example: "photo.jpg,
 Warsaw, 2013-09-05 14:08:15"
 Note that, in some rare cases, photos from different locations may share
 the time and date, due to timezone differences.
 John notices that his way of filing photos on his computer has become a
 mess. He wants to reorganize the photos. First he decides to group the
 photos by city, then, within each such group, sort the photos by the time
 they were taken and finally assign consecutive natural numbers to the
 photos, starting from 1. Afterwards he intends to rename all the photos.
 The new name of each photo should begin with the name of the city
 followed by the number already assigned to that photo. The number of
 every photo in each group should have the same length (equal to the lengt
 of the largest number in this group), thus, John needs to add some leading
 zeros to the numbers. The new name of the photo should end with the
 extension, which should remain the same.
 Your task is to help John by finding the new name of each photo.
 Each of John's photos has the format: "<<photoname
 <extension>>, <<city_name>>, yyyy-mm-dd hh :mm:ss", where
 <photoname>>", <<extension>>"and "<<city name>>" consist


 your function should return
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
 as there are ten photos taken in Warsaw (numbered from 01 to 10), tw
 photos in London (numbered from 1 to 2) and three photos in Paris
 (numbered from 1 to 3)
 The new names of the photos are returned in the same order as in the
 given string
 Assume that:
 M is an integer within the range [1.100]
 . Each year is an integer within the range [2000..2020];
 . Each line of the input string is of the format"
 くくphotoname>>·<<extension>>, <<city-name>> ,
 yyyy-mm-dd hh:mm: ss" and lines are separated with
 newline characters;
 Each photo name (without extension)) and city name
 consists only of at least 1 and at most 20 letters from the
 English alphabet;
 . Each name of the city starts with a capital letter and is
 followed by lower case letters;
 No two photos from the same location share the same date
 and time
 Each extension is jpg" png" or "jpeg
 In your solution, focus on correctness.
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
			.sorted(comparing((Photo p) -> p.created))
			.collect(Collectors.groupingBy(Photo::getCity));


		List<Photo> resultList = new ArrayList<>();

		groupByCityMap.forEach((city, list) -> {
			int listCount = list.size();
			int serial = (int) (Math.log10(listCount) + 1);
			for (int i = 0; i < listCount; i++) {
				String newIndexNo = String.format("%0" + serial + "d", (i + 1));
				String newFileName = city + newIndexNo;
				list.get(i).setNewFileName(newFileName);
				resultList.add(list.get(i));
			}
		});

		resultList.sort(Comparator.comparing(Photo::getOriginalIndex));

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
