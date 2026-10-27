package k6;

import T.C0773y;
import T.S;
import com.stingray.karaoke.car.utils.LayoutMode;

/* JADX INFO: renamed from: k6.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1692D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0773y f18238a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0773y f18239b;
    public static final C0773y c;
    public static final C0773y d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0773y f18240e;

    static {
        A3.v vVar = new A3.v(9);
        S s6 = S.f8224q;
        f18238a = new C0773y(s6, vVar);
        f18239b = new C0773y(s6, new A3.v(10));
        c = new C0773y(s6, new A3.v(10));
        d = new C0773y(s6, new A3.v(11));
        f18240e = new C0773y(s6, new A3.v(12));
    }

    public static final boolean a(LayoutMode layoutMode) {
        A7.m.f(layoutMode, "<this>");
        return layoutMode == LayoutMode.WIDE_COMPACT || layoutMode == LayoutMode.NARROW_COMPACT || layoutMode == LayoutMode.SQUARE_COMPACT;
    }

    public static final boolean b(LayoutMode layoutMode) {
        A7.m.f(layoutMode, "<this>");
        return layoutMode == LayoutMode.NARROW_COMPACT || layoutMode == LayoutMode.NARROW_NORMAL || layoutMode == LayoutMode.NARROW_ULTRA;
    }

    public static final boolean c(LayoutMode layoutMode) {
        A7.m.f(layoutMode, "<this>");
        return layoutMode == LayoutMode.WIDE_COMPACT || layoutMode == LayoutMode.WIDE_NORMAL || layoutMode == LayoutMode.WIDE_ULTRA;
    }
}
