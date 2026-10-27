package G;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2505m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f2506n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f2507p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f2508q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f2509r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(I i7, Object obj, int i10, Object obj2, int i11) {
        super(2);
        this.f2508q = i7;
        this.f2506n = obj;
        this.o = i10;
        this.f2509r = obj2;
        this.f2507p = i11;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.f2505m;
        C0761q c0761q = (C0761q) obj;
        ((Number) obj2).intValue();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f2507p | 1);
                E.d((I) this.f2508q, this.f2506n, this.o, this.f2509r, c0761q, iZ);
                break;
            case 1:
                int iZ2 = C0735d.Z(this.f2507p | 1);
                E.b(this.f2506n, this.o, (S) this.f2508q, (b0.b) this.f2509r, c0761q, iZ2);
                break;
            default:
                int iZ3 = C0735d.Z(this.o | 1);
                androidx.compose.ui.viewinterop.a.a((InterfaceC2782k) this.f2508q, (g0.o) this.f2506n, (InterfaceC2782k) this.f2509r, c0761q, iZ3, this.f2507p);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(Object obj, int i7, S s6, b0.b bVar, int i10) {
        super(2);
        this.f2506n = obj;
        this.o = i7;
        this.f2508q = s6;
        this.f2509r = bVar;
        this.f2507p = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(InterfaceC2782k interfaceC2782k, g0.o oVar, InterfaceC2782k interfaceC2782k2, int i7, int i10) {
        super(2);
        this.f2508q = interfaceC2782k;
        this.f2506n = oVar;
        this.f2509r = interfaceC2782k2;
        this.o = i7;
        this.f2507p = i10;
    }
}
