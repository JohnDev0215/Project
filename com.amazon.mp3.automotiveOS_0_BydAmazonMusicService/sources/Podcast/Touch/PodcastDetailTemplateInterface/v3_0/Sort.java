package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

/* JADX INFO: loaded from: classes.dex */
public class Sort {
    public static final String NEWEST_TO_OLDEST = "NEWEST_TO_OLDEST";
    public static final String OLDEST_TO_NEWEST = "OLDEST_TO_NEWEST";
    public static final String NEWEST_TO_OLDEST_SEASON = "NEWEST_TO_OLDEST_SEASON";
    public static final String OLDEST_TO_NEWEST_SEASON = "OLDEST_TO_NEWEST_SEASON";
    private static final String[] values = {"NEWEST_TO_OLDEST", "OLDEST_TO_NEWEST", NEWEST_TO_OLDEST_SEASON, OLDEST_TO_NEWEST_SEASON};

    private Sort() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
