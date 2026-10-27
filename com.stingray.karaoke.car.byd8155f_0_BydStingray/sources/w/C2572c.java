package w;

import T.C0735d;
import T.C0761q;
import x.x0;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: w.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2572c extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23089m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x0 f23090n;
    public final /* synthetic */ g0.o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ b0.b f23091p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f23092q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ m7.c f23093r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f23094s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f23095t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2572c(x0 x0Var, g0.o oVar, InterfaceC2782k interfaceC2782k, g0.c cVar, InterfaceC2782k interfaceC2782k2, b0.b bVar, int i7) {
        super(2);
        this.f23090n = x0Var;
        this.o = oVar;
        this.f23093r = interfaceC2782k;
        this.f23095t = cVar;
        this.f23094s = interfaceC2782k2;
        this.f23091p = bVar;
        this.f23092q = i7;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [A7.o, z7.k] */
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f23089m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f23092q | 1);
                b0.b bVar = this.f23091p;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f23093r;
                g0.c cVar = (g0.c) this.f23095t;
                h6.j.a(this.f23090n, this.o, interfaceC2782k, cVar, (InterfaceC2782k) this.f23094s, bVar, (C0761q) obj, iZ);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f23092q | 1);
                b0.b bVar2 = this.f23091p;
                androidx.compose.animation.a.d(this.f23090n, (A7.o) this.f23093r, this.o, (F) this.f23094s, (G) this.f23095t, bVar2, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C2572c(x0 x0Var, InterfaceC2782k interfaceC2782k, g0.o oVar, F f10, G g7, b0.b bVar, int i7) {
        super(2);
        this.f23090n = x0Var;
        this.f23093r = (A7.o) interfaceC2782k;
        this.o = oVar;
        this.f23094s = f10;
        this.f23095t = g7;
        this.f23091p = bVar;
        this.f23092q = i7;
    }
}
