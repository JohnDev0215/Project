package X5;

import com.stingray.karaoke.car.ui.player.PlayerSideMenuRoute;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f10089a;

    static {
        int[] iArr = new int[PlayerSideMenuRoute.values().length];
        try {
            iArr[PlayerSideMenuRoute.SongQueue.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PlayerSideMenuRoute.Explore.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PlayerSideMenuRoute.Artists.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[PlayerSideMenuRoute.Songs.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[PlayerSideMenuRoute.SongDetails.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        f10089a = iArr;
    }
}
