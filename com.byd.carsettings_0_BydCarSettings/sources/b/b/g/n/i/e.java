package b.b.g.n.i;

import b.b.g.p.d;

/* JADX INFO: compiled from: SingleSeekbarPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b.b.m.e.g<b.b.g.q.f.a.f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1036a = "e";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.p.d f1037b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.g.n.q.h f1038c;

    public int c() {
        return this.f1037b.o(getV().getContext());
    }

    public int d() {
        int iD = this.f1038c.d();
        if (iD <= 0) {
            iD = 8;
        }
        return iD > 11 ? iD - 11 : iD;
    }

    public void e() {
        b.b.g.p.d dVarA = d.g.a();
        this.f1037b = dVarA;
        dVarA.W(getV().getContext());
        this.f1038c = b.b.g.n.q.h.m();
    }

    public void f(int i) {
        this.f1037b.v0(getV().getContext(), i);
    }

    public void g(int i) {
        this.f1038c.N(i);
        b.b.m.c.a.a(f1036a, "设置仪表亮度：" + i);
        b.b.g.r.e.i(70001);
    }
}
