package Podcast.SaveInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class Sort {
    public static final String NEWEST_TO_OLDEST = "NEWEST_TO_OLDEST";
    public static final String OLDEST_TO_NEWEST = "OLDEST_TO_NEWEST";
    public static final String SHOW_NAME = "SHOW_NAME";
    public static final String DATE_ADDED = "DATE_ADDED";
    private static final String[] values = {"NEWEST_TO_OLDEST", "OLDEST_TO_NEWEST", DATE_ADDED, "SHOW_NAME"};

    private Sort() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
