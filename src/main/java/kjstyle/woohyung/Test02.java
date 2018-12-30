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
 * Created by kjstyle on 30/12/2018.
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
