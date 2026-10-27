package m6;

import com.stingray.karaoke.car.utils.LayoutMode;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f19251a;

    static {
        int[] iArr = new int[LayoutMode.values().length];
        try {
            iArr[LayoutMode.SQUARE_COMPACT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[LayoutMode.NARROW_COMPACT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[LayoutMode.WIDE_COMPACT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[LayoutMode.WIDE_ULTRA.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[LayoutMode.NARROW_ULTRA.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[LayoutMode.SQUARE_ULTRA.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[LayoutMode.WIDE_NORMAL.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[LayoutMode.NARROW_NORMAL.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr[LayoutMode.SQUARE_NORMAL.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        f19251a = iArr;
    }
}
