package N5;

import com.stingray.karaoke.car.player.PlayerStatus;

/* JADX INFO: renamed from: N5.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0465v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f4775a;

    static {
        int[] iArr = new int[PlayerStatus.values().length];
        try {
            iArr[PlayerStatus.ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        f4775a = iArr;
    }
}
