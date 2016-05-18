package common;

public class AppComparators {

	private static AppComparators _instance = null;

	public static AppComparators Instance() {
		if (_instance == null) {
			synchronized (AppComparators.class) {
				_instance = new AppComparators();
			}
		}
		return _instance;
	}

	// public Comparator<DiscussionInfo> ComparatorNewest = new
	// Comparator<DiscussionInfo>() {
	//
	// public int compare(DiscussionInfo object1, DiscussionInfo object2) {
	// // 2012-02-21 12:36:46
	// SimpleDateFormat dateformat = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	// Date dt1 = new Date();
	// Date dt2 = new Date();
	// try {
	// dt1 = dateformat.parse(object1.add_date);
	// dt2 = dateformat.parse(object2.add_date);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// // String date1[] = object1.add_date.split(" ");
	// // String d1 = date1[0].replaceAll("-", "/");
	// // String date2[] = object2.add_date.split(" ");
	// // String d2 = date2[0].replaceAll("-", "/");
	// // Utils.LogInfo("DATE 1------->>>" + date1[0]);
	// // Utils.LogInfo("DATE 2------->>>" + date2[0]);
	// // Date dt1 = Utils.ConvertToDateComarator(d1);
	// // Date dt2 = Utils.ConvertToDateComarator(d2);
	//
	// return dt2.compareTo(dt1);
	// }
	// };
	// public Comparator<DiscussionInfo> ComparatorSize = new
	// Comparator<DiscussionInfo>() {
	//
	// public int compare(DiscussionInfo object1, DiscussionInfo object2) {
	// int d1 = 0, d2 = 0;
	// if (object1.loadComments(object1.Discussion_ID) != null) {
	// d1 = object1.loadComments(object1.Discussion_ID).size();
	// }
	// if (object2.loadComments(object2.Discussion_ID) != null) {
	// d2 = object2.loadComments(object2.Discussion_ID).size();
	// }
	// // (numero.intValue() == other.numero) ? 0 : (numero < other.numero)
	// // ? -1 : 1;
	// return (d1 == d2) ? 0 : (d1 > d2) ? -1 : 1;
	//
	// }
	// };
	// public Comparator<QuizInfo> ComparatorMcqNewest = new
	// Comparator<QuizInfo>() {
	//
	// public int compare(QuizInfo object1, QuizInfo object2) {
	// String date1[] = object1.add_date.split(" ");
	// String d1 = date1[0].replaceAll("-", "/");
	// String date2[] = object2.add_date.split(" ");
	// String d2 = date2[0].replaceAll("-", "/");
	// Utils.LogInfo("DATE 1------->>>" + date1[0]);
	// Utils.LogInfo("DATE 2------->>>" + date2[0]);
	// Date dt1 = Utils.ConvertToDateComarator(d1);
	// Date dt2 = Utils.ConvertToDateComarator(d2);
	//
	// return dt2.compareTo(dt1);
	// }
	// };
	//
	// public Comparator<ScoreCardInfo> scorecardComparator = new
	// Comparator<ScoreCardInfo>() {
	//
	// public int compare(ScoreCardInfo object1, ScoreCardInfo object2) {
	// int d1 = object1.completed;
	// int d2 = object2.completed;
	// int size1 = d1;
	// int size2 = d2;
	// return size1 > size2 ? -1 : 1;
	// }
	// };

}
