package k3;

import e2.C1206n;

/* JADX INFO: renamed from: k3.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1678y implements X, h2.m, h2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1628b0 f18159m;

    public /* synthetic */ C1678y(C1628b0 c1628b0, int i7) {
        this.l = i7;
        this.f18159m = c1628b0;
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 18:
                u4.Q(this.f18159m.f17921v);
                break;
            default:
                u4.Q(this.f18159m.f17921v);
                break;
        }
    }

    @Override // h2.m
    public void c(Object obj, C1206n c1206n) {
        this.f18159m.getClass();
        ((e2.U) obj).E(new e2.T(c1206n));
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                interfaceC1655m.s(this.f18159m.c, i7, null);
                break;
            case 1:
                interfaceC1655m.s(this.f18159m.c, i7, null);
                break;
            case 2:
                C1628b0 c1628b0 = this.f18159m;
                interfaceC1655m.s(c1628b0.c, i7, c1628b0.f17922w);
                break;
            case 3:
                interfaceC1655m.c1(this.f18159m.c, i7);
                break;
            case 4:
                interfaceC1655m.w(this.f18159m.c, i7);
                break;
            case 5:
                interfaceC1655m.i0(this.f18159m.c, i7);
                break;
            case 6:
                interfaceC1655m.j1(this.f18159m.c, i7);
                break;
            case 7:
                interfaceC1655m.X0(this.f18159m.c, i7);
                break;
            case 8:
            default:
                interfaceC1655m.s(this.f18159m.c, i7, null);
                break;
            case 9:
                interfaceC1655m.H0(this.f18159m.c, i7);
                break;
            case 10:
                interfaceC1655m.v(this.f18159m.c, i7);
                break;
            case 11:
                interfaceC1655m.K0(this.f18159m.c, i7);
                break;
            case 12:
                interfaceC1655m.Z0(this.f18159m.c, i7);
                break;
            case 13:
                interfaceC1655m.f0(this.f18159m.c, i7);
                break;
            case 14:
                interfaceC1655m.f1(this.f18159m.c, i7);
                break;
            case 15:
                interfaceC1655m.e0(this.f18159m.c, i7);
                break;
            case 16:
                interfaceC1655m.U(this.f18159m.c, i7);
                break;
            case 17:
                interfaceC1655m.b0(this.f18159m.c, i7);
                break;
        }
    }
}
