package PlaybackInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class MediaChangeReason {
    public static final String PLAYBACK_SESSION_INITIATED = "PLAYBACK_SESSION_INITIATED";
    public static final String NEXT_COMMAND = "NEXT_COMMAND";
    public static final String PREV_COMMAND = "PREV_COMMAND";
    public static final String MEDIA_FINISHED = "MEDIA_FINISHED";
    public static final String RESTORE_TRACK = "RESTORE_TRACK";
    private static final String[] values = {PLAYBACK_SESSION_INITIATED, NEXT_COMMAND, PREV_COMMAND, MEDIA_FINISHED, RESTORE_TRACK};

    private MediaChangeReason() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
