package Podcast.FollowInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class Sort {
    public static final String SHOW_NAME = "SHOW_NAME";
    public static final String DATE_FOLLOWED = "DATE_FOLLOWED";
    private static final String[] values = {DATE_FOLLOWED, "SHOW_NAME"};

    private Sort() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
