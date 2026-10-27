package b.b.g.n.i;

import b.b.g.n.q.i;
import b.b.g.n.q.m;

/* JADX INFO: compiled from: AtmLampColorsPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends b.b.m.e.g<b.b.g.q.f.a.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public m f1029a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f1030b;

    public int c() {
        return this.f1029a.o0(this.f1029a.m0());
    }

    public void d() {
        this.f1029a = m.p0();
        this.f1030b = i.k();
    }

    public void e(int i) {
        this.f1029a.E2(this.f1029a.m0(), i, 0);
        b.b.g.r.e.i(20005);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1029a = null;
        this.f1030b = null;
    }
}
