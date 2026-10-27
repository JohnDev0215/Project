package l0;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: l0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1728s extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f18356m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1727r f18357n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1728s(C1727r c1727r, int i7) {
        super(0);
        this.f18356m = i7;
        this.f18357n = c1727r;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f18356m) {
            case 0:
                this.f18357n.H0();
                break;
            default:
                C1727r c1727r = this.f18357n;
                if (c1727r.l.f14695x) {
                    AbstractC1713d.z(c1727r);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
