package X5;

import com.stingray.karaoke.car.player.PlayerStatus;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class P {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f10191a;

    static {
        int[] iArr = new int[PlayerStatus.values().length];
        try {
            iArr[PlayerStatus.STOPPED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PlayerStatus.PAUSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PlayerStatus.PLAYING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[PlayerStatus.COUNTDOWN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[PlayerStatus.BUFFERING.ordinal()] = 5;
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
        f10191a = iArr;
    }
}
