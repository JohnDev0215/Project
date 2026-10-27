package s0;

import h2.AbstractC1347d;
import l3.C1764u;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: s0.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2328B extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f21955m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2329C f21956n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2328B(C2329C c2329c, int i7) {
        super(1);
        this.f21955m = i7;
        this.f21956n = c2329c;
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [A7.o, z7.a] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f21955m) {
            case 0:
                C2329C c2329c = this.f21956n;
                c2329c.d = true;
                c2329c.f21959f.a();
                return m7.q.f19267a;
            default:
                p0.d dVar = (p0.d) obj;
                C2329C c2329c2 = this.f21956n;
                C2335b c2335b = c2329c2.f21957b;
                float f10 = c2329c2.f21964k;
                float f11 = c2329c2.l;
                h3.y yVarF = dVar.F();
                long jY = yVarF.y();
                yVarF.m().m();
                try {
                    ((C1764u) yVarF.f15499m).T(f10, f11, 0L);
                    c2335b.a(dVar);
                    AbstractC1347d.o(yVarF, jY);
                    return m7.q.f19267a;
                } catch (Throwable th) {
                    AbstractC1347d.o(yVarF, jY);
                    throw th;
                }
        }
    }
}
