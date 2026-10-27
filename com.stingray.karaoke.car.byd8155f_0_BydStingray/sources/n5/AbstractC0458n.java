package N5;

import com.stingray.karaoke.car.player.PlayerStatus;

/* JADX INFO: renamed from: N5.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0458n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f4723a;

    static {
        int[] iArr = new int[PlayerStatus.values().length];
        try {
            iArr[PlayerStatus.STOPPED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PlayerStatus.PLAYING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PlayerStatus.PAUSED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[PlayerStatus.BUFFERING.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[PlayerStatus.COUNTDOWN.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[PlayerStatus.ERROR.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[PlayerStatus.READY.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[PlayerStatus.COUNTDOWN_ENDED.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        f4723a = iArr;
    }
}
