package e1;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class w extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.y f13817m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x f13818n;
    public final /* synthetic */ a1.i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f13819p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ long f13820q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(A7.y yVar, x xVar, a1.i iVar, long j3, long j10) {
        super(0);
        this.f13817m = yVar;
        this.f13818n = xVar;
        this.o = iVar;
        this.f13819p = j3;
        this.f13820q = j10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        x xVar = this.f13818n;
        z positionProvider = xVar.getPositionProvider();
        a1.k parentLayoutDirection = xVar.getParentLayoutDirection();
        this.f13817m.l = positionProvider.a(this.o, this.f13819p, parentLayoutDirection, this.f13820q);
        return m7.q.f19267a;
    }
}
