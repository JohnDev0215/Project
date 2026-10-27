package N5;

import com.stingray.karaoke.car.player.SongQueuePosition;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f4702a;

    static {
        int[] iArr = new int[SongQueuePosition.values().length];
        try {
            iArr[SongQueuePosition.SING_NOW.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[SongQueuePosition.SING_NEXT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[SongQueuePosition.SING_LAST.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[SongQueuePosition.SCORE_NOW.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        f4702a = iArr;
    }
}
