package R;

import K.AbstractC0334d;
import O.InterfaceC0480k;
import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class F extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7240m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f7241n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7242p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f7243q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ F(long j3, N0.L l, InterfaceC2785n interfaceC2785n, int i7, int i10) {
        super(2);
        this.f7240m = i10;
        this.f7241n = j3;
        this.o = l;
        this.f7242p = interfaceC2785n;
        this.f7243q = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7240m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f7243q | 1);
                N0.L l = (N0.L) this.o;
                InterfaceC2785n interfaceC2785n = (InterfaceC2785n) this.f7242p;
                l9.d.o(this.f7241n, l, interfaceC2785n, (C0761q) obj, iZ);
                break;
            case 1:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f7243q | 1);
                N0.L l10 = (N0.L) this.o;
                InterfaceC2785n interfaceC2785n2 = (InterfaceC2785n) this.f7242p;
                O.b(this.f7241n, l10, interfaceC2785n2, (C0761q) obj, iZ2);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.f7243q | 1);
                long j3 = this.f7241n;
                AbstractC0334d.a((InterfaceC0480k) this.o, (g0.o) this.f7242p, j3, (C0761q) obj, iZ3);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(InterfaceC0480k interfaceC0480k, g0.o oVar, long j3, int i7) {
        super(2);
        this.f7240m = 2;
        this.o = interfaceC0480k;
        this.f7242p = oVar;
        this.f7241n = j3;
        this.f7243q = i7;
    }
}
