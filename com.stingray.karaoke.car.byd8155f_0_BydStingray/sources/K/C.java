package K;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class C extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3127m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ O.K f3128n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C(O.K k10, int i7) {
        super(0);
        this.f3127m = i7;
        this.f3128n = k10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3127m) {
            case 0:
                this.f3128n.l();
                break;
            case 1:
                this.f3128n.f(true);
                break;
            case 2:
                this.f3128n.b(true);
                break;
            case 3:
                this.f3128n.d();
                break;
            case 4:
                O.K k10 = this.f3128n;
                k10.b(true);
                k10.k();
                break;
            case 5:
                O.K k11 = this.f3128n;
                k11.d();
                k11.k();
                break;
            case 6:
                O.K k12 = this.f3128n;
                k12.l();
                k12.k();
                break;
            default:
                this.f3128n.m();
                break;
        }
        return m7.q.f19267a;
    }
}
