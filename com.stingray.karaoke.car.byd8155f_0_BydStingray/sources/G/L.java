package G;

import A.C0004b;
import Q.AbstractC0539x;
import Q.C0516l;
import Q.h1;
import T.C0735d;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class L extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2510m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2511n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f2512p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f2513q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f2514r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ L(Object obj, Object obj2, Object obj3, Object obj4, int i7, int i10) {
        super(2);
        this.f2510m = i10;
        this.o = obj;
        this.f2512p = obj2;
        this.f2513q = obj3;
        this.f2514r = obj4;
        this.f2511n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2510m) {
            case 0:
                ((Number) obj2).intValue();
                E.a((H7.r) this.o, (g0.o) this.f2512p, (V) this.f2513q, (InterfaceC2785n) this.f2514r, (C0761q) obj, C0735d.Z(this.f2511n | 1));
                break;
            case 1:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f2511n | 1);
                b0.b bVar = (b0.b) this.f2514r;
                AbstractC0539x.a((C0516l) this.o, (Q.S) this.f2512p, (h1) this.f2513q, bVar, (C0761q) obj, iZ);
                break;
            case 2:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f2511n) | 1;
                Object obj3 = this.f2513q;
                Object obj4 = this.f2514r;
                ((b0.b) this.o).j(this.f2512p, obj3, obj4, (C0761q) obj, iZ2);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.f2511n | 1);
                C0004b c0004b = (C0004b) this.f2514r;
                z.n.c((z.g) this.o, (InterfaceC2772a) this.f2513q, (g0.o) this.f2512p, c0004b, (C0761q) obj, iZ3);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(z.g gVar, InterfaceC2772a interfaceC2772a, g0.o oVar, C0004b c0004b, int i7) {
        super(2);
        this.f2510m = 3;
        this.o = gVar;
        this.f2513q = interfaceC2772a;
        this.f2512p = oVar;
        this.f2514r = c0004b;
        this.f2511n = i7;
    }
}
