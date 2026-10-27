package k3;

import android.view.Surface;

/* JADX INFO: renamed from: k3.K, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1617K implements X {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1628b0 f17820m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Surface f17821n;

    public /* synthetic */ C1617K(C1628b0 c1628b0, Surface surface, int i7) {
        this.l = i7;
        this.f17820m = c1628b0;
        this.f17821n = surface;
    }

    @Override // k3.X
    public final void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                interfaceC1655m.s(this.f17820m.c, i7, this.f17821n);
                break;
            default:
                interfaceC1655m.s(this.f17820m.c, i7, this.f17821n);
                break;
        }
    }
}
