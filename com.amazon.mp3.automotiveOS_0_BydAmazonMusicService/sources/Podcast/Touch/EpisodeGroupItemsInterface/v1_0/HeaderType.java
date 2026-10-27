package Podcast.Touch.EpisodeGroupItemsInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class HeaderType {
    public static final String SCROLLING = "SCROLLING";
    public static final String ANCHORED = "ANCHORED";
    private static final String[] values = {SCROLLING, ANCHORED};

    private HeaderType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
