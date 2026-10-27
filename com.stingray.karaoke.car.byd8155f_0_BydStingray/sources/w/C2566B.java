package w;

import A.C0022k;
import C0.U;
import C0.V;
import n0.AbstractC1887p;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: w.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2566B extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23047m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f23048n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f23049p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f23050q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2566B(Object obj, long j3, long j10, Object obj2, int i7) {
        super(1);
        this.f23047m = i7;
        this.f23049p = obj;
        this.f23048n = j3;
        this.o = j10;
        this.f23050q = obj2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f23047m) {
            case 0:
                U u4 = (U) obj;
                long j3 = this.f23048n;
                long j10 = this.o;
                C0022k c0022k = (C0022k) this.f23050q;
                V v10 = (V) this.f23049p;
                u4.getClass();
                long jD = AbstractC2673a.d(((int) (j3 >> 32)) + ((int) (j10 >> 32)), ((int) (j3 & 4294967295L)) + ((int) (j10 & 4294967295L)));
                U.a(u4, v10);
                v10.l0(a1.h.d(jD, v10.f873p), 0.0f, c0022k);
                break;
            default:
                E0.H h10 = (E0.H) obj;
                h10.a();
                p0.d.X(h10, (AbstractC1887p) this.f23049p, this.f23048n, this.o, 0.0f, (p0.e) this.f23050q, 104);
                break;
        }
        return m7.q.f19267a;
    }
}
