package F0;

import S8.AbstractC0727y;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class U extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2140m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final U f2128n = new U(0, 0);
    public static final U o = new U(0, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final U f2129p = new U(0, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final U f2130q = new U(0, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final U f2131r = new U(0, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final U f2132s = new U(0, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final U f2133t = new U(0, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final U f2134u = new U(0, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final U f2135v = new U(0, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final U f2136w = new U(0, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final U f2137x = new U(0, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final U f2138y = new U(0, 11);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final U f2139z = new U(0, 12);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final U f2113A = new U(0, 13);
    public static final U B = new U(0, 14);

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final U f2114C = new U(0, 15);

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final U f2115D = new U(0, 16);

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final U f2116E = new U(0, 17);

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final U f2117F = new U(0, 18);

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final U f2118G = new U(0, 19);

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final U f2119H = new U(0, 20);

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final U f2120I = new U(0, 21);

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final U f2121J = new U(0, 22);

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final U f2122K = new U(0, 23);

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final U f2123L = new U(0, 24);

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final U f2124M = new U(0, 25);

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final U f2125N = new U(0, 26);

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static final U f2126O = new U(0, 27);

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public static final U f2127P = new U(0, 28);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ U(int i7, int i10) {
        super(i7);
        this.f2140m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        Choreographer choreographer;
        switch (this.f2140m) {
            case 0:
                AndroidCompositionLocals_androidKt.b("LocalConfiguration");
                throw null;
            case 1:
                AndroidCompositionLocals_androidKt.b("LocalContext");
                throw null;
            case 2:
                AndroidCompositionLocals_androidKt.b("LocalImageVectorCache");
                throw null;
            case 3:
                AndroidCompositionLocals_androidKt.b("LocalResourceIdCache");
                throw null;
            case 4:
                AndroidCompositionLocals_androidKt.b("LocalSavedStateRegistryOwner");
                throw null;
            case 5:
                AndroidCompositionLocals_androidKt.b("LocalView");
                throw null;
            case 6:
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    choreographer = Choreographer.getInstance();
                } else {
                    Z8.e eVar = S8.E.f8084a;
                    choreographer = (Choreographer) AbstractC0727y.A(X8.m.f10573a, new C0200e0(2, null));
                }
                C0204g0 c0204g0 = new C0204g0(choreographer, Handler.createAsync(Looper.getMainLooper()));
                return h6.q.x(c0204g0, c0204g0.f2204w);
            case 7:
            case 8:
                return null;
            case 9:
                AbstractC0227s0.b("LocalAutofillTree");
                throw null;
            case 10:
                AbstractC0227s0.b("LocalClipboardManager");
                throw null;
            case 11:
                AbstractC0227s0.b("LocalDensity");
                throw null;
            case 12:
                AbstractC0227s0.b("LocalFocusManager");
                throw null;
            case 13:
                AbstractC0227s0.b("LocalFontFamilyResolver");
                throw null;
            case 14:
                AbstractC0227s0.b("LocalFontLoader");
                throw null;
            case 15:
                AbstractC0227s0.b("LocalGraphicsContext");
                throw null;
            case 16:
                AbstractC0227s0.b("LocalHapticFeedback");
                throw null;
            case 17:
                AbstractC0227s0.b("LocalInputManager");
                throw null;
            case 18:
                AbstractC0227s0.b("LocalLayoutDirection");
                throw null;
            case 19:
                return null;
            case 20:
                return Boolean.FALSE;
            case 21:
            case 22:
                return null;
            case 23:
                AbstractC0227s0.b("LocalTextToolbar");
                throw null;
            case 24:
                AbstractC0227s0.b("LocalUriHandler");
                throw null;
            case 25:
                AbstractC0227s0.b("LocalViewConfiguration");
                throw null;
            case 26:
                AbstractC0227s0.b("LocalWindowInfo");
                throw null;
            case 27:
                return Boolean.FALSE;
            default:
                return null;
        }
    }
}
