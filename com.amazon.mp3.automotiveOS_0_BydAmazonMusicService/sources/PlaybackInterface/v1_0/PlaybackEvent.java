package PlaybackInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackEvent {
    public static final String END_OF_QUEUE_ON_FINISHED = "END_OF_QUEUE_ON_FINISHED";
    public static final String END_OF_QUEUE_ON_NEXT = "END_OF_QUEUE_ON_NEXT";
    public static final String END_OF_QUEUE_ON_PREV = "END_OF_QUEUE_ON_PREV";
    public static final String UPSELL = "UPSELL";
    private static final String[] values = {END_OF_QUEUE_ON_FINISHED, END_OF_QUEUE_ON_NEXT, END_OF_QUEUE_ON_PREV, UPSELL};

    private PlaybackEvent() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
