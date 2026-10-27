package U5;

import com.stingray.karaoke.car.utils.LayoutMode;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f9176a;

    static {
        int[] iArr = new int[LayoutMode.values().length];
        try {
            iArr[LayoutMode.WIDE_ULTRA.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[LayoutMode.WIDE_NORMAL.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[LayoutMode.SQUARE_NORMAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        f9176a = iArr;
    }
}
