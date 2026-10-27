package PlaybackInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class AudioStreamFormat {
    public static final String HLS = "HLS";
    public static final String PROGRESSIVE = "PROGRESSIVE";
    private static final String[] values = {HLS, PROGRESSIVE};

    private AudioStreamFormat() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
