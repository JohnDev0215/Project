package C0;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f885m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f886n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f887p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f888q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b0(G.I i7, int i10, Object obj, int i11, int i12) {
        super(2);
        this.f885m = i12;
        this.f887p = i7;
        this.f886n = i10;
        this.f888q = obj;
        this.o = i11;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.f885m;
        C0761q c0761q = (C0761q) obj;
        ((Number) obj2).intValue();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f886n | 1);
                c0.d((g0.o) this.f887p, (InterfaceC2785n) this.f888q, c0761q, iZ, this.o);
                break;
            case 1:
                int iZ2 = C0735d.Z(this.o | 1);
                ((E.l) this.f887p).e(this.f886n, this.f888q, c0761q, iZ2);
                break;
            default:
                int iZ3 = C0735d.Z(this.o | 1);
                ((F.h) this.f887p).e(this.f886n, this.f888q, c0761q, iZ3);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(g0.o oVar, InterfaceC2785n interfaceC2785n, int i7, int i10) {
        super(2);
        this.f885m = 0;
        this.f887p = oVar;
        this.f888q = interfaceC2785n;
        this.f886n = i7;
        this.o = i10;
    }
}
