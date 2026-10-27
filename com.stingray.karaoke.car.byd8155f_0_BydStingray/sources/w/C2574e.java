package w;

import C0.U;
import C0.V;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: w.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2574e extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V[] f23098m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2575f f23099n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f23100p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2574e(V[] vArr, C2575f c2575f, int i7, int i10) {
        super(1);
        this.f23098m = vArr;
        this.f23099n = c2575f;
        this.o = i7;
        this.f23100p = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        U u4 = (U) obj;
        for (V v10 : this.f23098m) {
            if (v10 != null) {
                long jA = this.f23099n.f23101a.f23106b.a(H7.H.c(v10.l, v10.f871m), H7.H.c(this.o, this.f23100p), a1.k.l);
                U.d(u4, v10, (int) (jA >> 32), (int) (jA & 4294967295L));
            }
        }
        return m7.q.f19267a;
    }
}
