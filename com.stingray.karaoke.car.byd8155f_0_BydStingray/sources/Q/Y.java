package Q;

import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ U f5758m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5759n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(U u4, boolean z6) {
        super(2);
        this.f5758m = u4;
        this.f5759n = z6;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        p0.d dVar = (p0.d) obj;
        long j3 = ((m0.c) obj2).f18880a;
        C0497b0 c0497b0 = C0497b0.f5820a;
        dVar.Q(this.f5758m.a(this.f5759n, true), dVar.y(C0497b0.f5821b) / 2.0f, (120 & 4) != 0 ? dVar.S() : j3, 1.0f, (120 & 16) != 0 ? p0.g.f20641a : null, null, 3);
        return m7.q.f19267a;
    }
}
