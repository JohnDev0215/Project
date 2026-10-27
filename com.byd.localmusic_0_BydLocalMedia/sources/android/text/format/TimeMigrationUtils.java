package android.text.format;

/* JADX INFO: loaded from: classes3.dex */
public class TimeMigrationUtils {
    private TimeMigrationUtils() {
    }

    public static String formatMillisWithFixedFormat(long j) {
        return new TimeFormatter().formatMillisWithFixedFormat(j);
    }
}
