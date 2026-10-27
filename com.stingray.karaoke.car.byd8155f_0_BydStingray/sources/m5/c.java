package M5;

import com.stingray.karaoke.car.utils.LayoutMode;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f4199a;

    static {
        int[] iArr = new int[LayoutMode.values().length];
        try {
            iArr[LayoutMode.NARROW_COMPACT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[LayoutMode.NARROW_NORMAL.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[LayoutMode.SQUARE_NORMAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[LayoutMode.NARROW_ULTRA.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[LayoutMode.SQUARE_COMPACT.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[LayoutMode.WIDE_COMPACT.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        f4199a = iArr;
    }
}
