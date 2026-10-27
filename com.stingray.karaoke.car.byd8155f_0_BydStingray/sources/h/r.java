package h;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15135m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x f15136n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(x xVar, int i7) {
        super(0);
        this.f15135m = i7;
        this.f15136n = xVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f15135m) {
            case 0:
                this.f15136n.c();
                break;
            case 1:
                this.f15136n.b();
                break;
            default:
                this.f15136n.c();
                break;
        }
        return m7.q.f19267a;
    }
}
