package Q5;

import T.C0735d;
import T.C0761q;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class K0 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6420m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6421n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6422p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6423q;

    public /* synthetic */ K0(g0.o oVar, Object obj, long j3, Object obj2, int i7, int i10) {
        this.l = i10;
        this.f6420m = oVar;
        this.f6421n = obj;
        this.o = j3;
        this.f6422p = obj2;
        this.f6423q = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6423q | 1);
                long j3 = this.o;
                N0.L l = (N0.L) this.f6422p;
                w0.c.s(this.f6420m, (String) this.f6421n, j3, l, (C0761q) obj, iZ);
                break;
            case 1:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6423q | 1);
                long j10 = this.o;
                N0.L l10 = (N0.L) this.f6422p;
                com.bumptech.glide.c.w(this.f6420m, (String) this.f6421n, j10, l10, (C0761q) obj, iZ2);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.f6423q | 1);
                b0.b bVar = (b0.b) this.f6422p;
                AbstractC2673a.i(this.f6420m, (InterfaceC2772a) this.f6421n, this.o, bVar, (C0761q) obj, iZ3);
                break;
        }
        return m7.q.f19267a;
    }
}
