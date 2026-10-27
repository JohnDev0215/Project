package F0;

import C0.C0114v;
import T.C0735d;
import T.C0752l0;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import androidx.compose.ui.node.Owner;

/* JADX INFO: renamed from: F0.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0227s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final T.R0 f2256a = new T.R0(U.f2134u);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final T.R0 f2257b = new T.R0(U.f2135v);
    public static final T.R0 c = new T.R0(U.f2136w);
    public static final T.R0 d = new T.R0(U.f2137x);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final T.R0 f2258e = new T.R0(U.f2114C);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final T.R0 f2259f = new T.R0(U.f2138y);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final T.R0 f2260g = new T.R0(U.f2139z);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final T.R0 f2261h = new T.R0(U.B);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final T.R0 f2262i = new T.R0(U.f2113A);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final T.R0 f2263j = new T.R0(U.f2115D);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final T.R0 f2264k = new T.R0(U.f2116E);
    public static final T.R0 l = new T.R0(U.f2117F);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final T.R0 f2265m = new T.R0(U.f2121J);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final T.R0 f2266n = new T.R0(U.f2120I);
    public static final T.R0 o = new T.R0(U.f2122K);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final T.R0 f2267p = new T.R0(U.f2123L);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final T.R0 f2268q = new T.R0(U.f2124M);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final T.R0 f2269r = new T.R0(U.f2125N);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final T.R0 f2270s = new T.R0(U.f2118G);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0773y f2271t = new C0773y(T.S.f8224q, U.f2119H);

    public static final void a(Owner owner, C0210j0 c0210j0, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(874662829);
        if ((i7 & 6) == 0) {
            i10 = ((i7 & 8) == 0 ? c0761q.f(owner) : c0761q.h(owner) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= (i7 & 64) == 0 ? c0761q.f(c0210j0) : c0761q.h(c0210j0) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(bVar) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            C0752l0 c0752l0A = f2256a.a(owner.getAccessibilityManager());
            C0752l0 c0752l0A2 = f2257b.a(owner.getAutofill());
            C0752l0 c0752l0A3 = c.a(owner.getAutofillTree());
            C0752l0 c0752l0A4 = d.a(owner.getClipboardManager());
            C0752l0 c0752l0A5 = f2259f.a(owner.getDensity());
            C0752l0 c0752l0A6 = f2260g.a(owner.getFocusOwner());
            C0752l0 c0752l0A7 = f2261h.a(owner.getFontLoader());
            c0752l0A7.f8269f = false;
            C0752l0 c0752l0A8 = f2262i.a(owner.getFontFamilyResolver());
            c0752l0A8.f8269f = false;
            C0735d.b(new C0752l0[]{c0752l0A, c0752l0A2, c0752l0A3, c0752l0A4, c0752l0A5, c0752l0A6, c0752l0A7, c0752l0A8, f2263j.a(owner.getHapticFeedBack()), f2264k.a(owner.getInputModeManager()), l.a(owner.getLayoutDirection()), f2265m.a(owner.getTextInputService()), f2266n.a(owner.getSoftwareKeyboardController()), o.a(owner.getTextToolbar()), f2267p.a(c0210j0), f2268q.a(owner.getViewConfiguration()), f2269r.a(owner.getWindowInfo()), f2270s.a(owner.getPointerIconService()), f2258e.a(owner.getGraphicsContext())}, bVar, c0761q, ((i10 >> 3) & 112) | 8);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(owner, c0210j0, bVar, i7, 2);
        }
    }

    public static final void b(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
