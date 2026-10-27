package K;

import F0.C0223q;
import H7.InterfaceC0321f;
import T.C0753m;
import T.C0761q;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class v0 extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0331b0 f3423m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ O.K f3424n;
    public final /* synthetic */ T0.y o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f3425p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f3426q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T0.s f3427r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ N0 f3428s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ C0365x f3429t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f3430u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v0(C0331b0 c0331b0, O.K k10, T0.y yVar, boolean z6, boolean z10, T0.s sVar, N0 n02, C0365x c0365x, int i7) {
        super(3);
        this.f3423m = c0331b0;
        this.f3424n = k10;
        this.o = yVar;
        this.f3425p = z6;
        this.f3426q = z10;
        this.f3427r = sVar;
        this.f3428s = n02;
        this.f3429t = c0365x;
        this.f3430u = i7;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        c0761q.Q(851809892);
        Object objG = c0761q.G();
        T.S s6 = C0753m.f8270a;
        if (objG == s6) {
            objG = new O.Q();
            c0761q.a0(objG);
        }
        O.Q q4 = (O.Q) objG;
        Object objG2 = c0761q.G();
        if (objG2 == s6) {
            objG2 = new N();
            c0761q.a0(objG2);
        }
        C0331b0 c0331b0 = this.f3423m;
        O.K k10 = this.f3424n;
        T0.y yVar = this.o;
        T0.s sVar = this.f3427r;
        N0 n02 = this.f3428s;
        u0 u0Var = new u0(c0331b0, k10, yVar, this.f3425p, this.f3426q, q4, sVar, n02, (N) objG2, this.f3429t, this.f3430u);
        g0.l lVar = g0.l.f14684a;
        boolean zH = c0761q.h(u0Var);
        Object objG3 = c0761q.G();
        if (zH || objG3 == s6) {
            objG3 = new C0223q(1, u0Var, u0.class, "process", "process-ZmokQxo(Landroid/view/KeyEvent;)Z", 0, 4);
            c0761q.a0(objG3);
        }
        g0.o oVarA = androidx.compose.ui.input.key.a.a(lVar, (InterfaceC2782k) ((InterfaceC0321f) objG3));
        c0761q.p(false);
        return oVarA;
    }
}
