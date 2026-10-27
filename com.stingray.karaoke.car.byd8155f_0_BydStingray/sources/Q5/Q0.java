package Q5;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Q0 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ a1.b f6505m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T.X f6506n;

    public /* synthetic */ Q0(a1.b bVar, T.X x8, int i7) {
        this.l = i7;
        this.f6505m = bVar;
        this.f6506n = x8;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                C0.r rVar = (C0.r) obj;
                A7.m.f(rVar, "coordinates");
                this.f6506n.setValue(new a1.e(this.f6505m.p0((int) (rVar.L() >> 32))));
                break;
            case 1:
                C0.r rVar2 = (C0.r) obj;
                A7.m.f(rVar2, "layoutCoordinates");
                this.f6506n.setValue(new a1.e(this.f6505m.p0((int) (rVar2.L() >> 32))));
                break;
            case 2:
                C0.r rVar3 = (C0.r) obj;
                A7.m.f(rVar3, "coordinates");
                this.f6506n.setValue(new a1.e(this.f6505m.p0((int) (rVar3.L() & 4294967295L))));
                break;
            case 3:
                C0.r rVar4 = (C0.r) obj;
                A7.m.f(rVar4, "coordinates");
                this.f6506n.setValue(new a1.e(this.f6505m.p0((int) (rVar4.L() & 4294967295L))));
                break;
            case 4:
                this.f6506n.setValue(new a1.e(this.f6505m.p0((int) (((a1.j) obj).f11037a >> 32))));
                break;
            default:
                C0.r rVar5 = (C0.r) obj;
                A7.m.f(rVar5, "coordinates");
                this.f6506n.setValue(new a1.e(this.f6505m.p0((int) (rVar5.L() & 4294967295L))));
                break;
        }
        return m7.q.f19267a;
    }
}
