package X5;

import com.stingray.karaoke.car.ui.player.SideMenuType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f10179a;

    static {
        int[] iArr = new int[SideMenuType.values().length];
        try {
            iArr[SideMenuType.SONG_QUEUE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[SideMenuType.COMPANION.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f10179a = iArr;
    }
}
