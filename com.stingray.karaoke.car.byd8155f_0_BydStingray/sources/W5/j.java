package W5;

import H7.H;
import T.C0735d;
import T.C0761q;
import c6.C1034a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f6.h f9833m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f6.g f9834n;
    public final /* synthetic */ e6.h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1034a f9835p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ f6.o f9836q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f9837r;

    public /* synthetic */ j(f6.h hVar, f6.g gVar, e6.h hVar2, C1034a c1034a, f6.o oVar, int i7, int i10) {
        this.l = i10;
        this.f9833m = hVar;
        this.f9834n = gVar;
        this.o = hVar2;
        this.f9835p = c1034a;
        this.f9836q = oVar;
        this.f9837r = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).intValue();
                H.e(this.f9833m, this.f9834n, this.o, this.f9835p, this.f9836q, (C0761q) obj, C0735d.Z(this.f9837r | 1));
                break;
            default:
                ((Integer) obj2).intValue();
                P8.n.e(this.f9833m, this.f9834n, this.o, this.f9835p, this.f9836q, (C0761q) obj, C0735d.Z(this.f9837r | 1));
                break;
        }
        return m7.q.f19267a;
    }
}
