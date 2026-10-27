package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class Sort {
    public static final String NEWEST_TO_OLDEST = "NEWEST_TO_OLDEST";
    public static final String OLDEST_TO_NEWEST = "OLDEST_TO_NEWEST";
    private static final String[] values = {"NEWEST_TO_OLDEST", "OLDEST_TO_NEWEST"};

    private Sort() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
