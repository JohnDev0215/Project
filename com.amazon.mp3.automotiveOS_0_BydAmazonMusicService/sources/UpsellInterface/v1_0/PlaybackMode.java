package UpsellInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackMode {
    public static final String FULL_ACCESS = "FULL_ACCESS";
    public static final String NONE = "NONE";
    private static final String[] values = {FULL_ACCESS, NONE};

    private PlaybackMode() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
