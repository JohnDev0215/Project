package X5;

import com.stingray.karaoke.car.ui.player.PlaybackType;

/* JADX INFO: renamed from: X5.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0860g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f10255a;

    static {
        int[] iArr = new int[PlaybackType.values().length];
        try {
            iArr[PlaybackType.PLAY_PAUSE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PlaybackType.SKIP_NEXT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PlaybackType.SKIP_PREVIOUS.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        f10255a = iArr;
    }
}
