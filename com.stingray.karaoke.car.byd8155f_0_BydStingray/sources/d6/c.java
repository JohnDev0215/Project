package D6;

import com.stingray.shared.logs.segment.player.ContentCompletedReason;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f1207a;

    static {
        int[] iArr = new int[ContentCompletedReason.values().length];
        try {
            iArr[ContentCompletedReason.COMPLETED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[ContentCompletedReason.SKIPPED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[ContentCompletedReason.SONG_CHANGED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[ContentCompletedReason.REMOVED_FROM_QUEUE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[ContentCompletedReason.STOPPED.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        f1207a = iArr;
    }
}
