package b.b.g.n.i;

import b.b.g.n.q.i;
import b.b.g.n.q.m;

/* JADX INFO: compiled from: AtmLampBrightnessPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b.b.m.e.g<b.b.g.q.f.a.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public m f1027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f1028b;

    public int c() {
        return this.f1027a.n0(this.f1027a.m0());
    }

    public void d() {
        this.f1027a = m.p0();
        this.f1028b = i.k();
    }

    public void e(int i) {
        this.f1027a.D2(this.f1027a.m0(), i, 0);
        b.b.g.r.e.i(20004);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1027a = null;
        this.f1028b = null;
    }
}
