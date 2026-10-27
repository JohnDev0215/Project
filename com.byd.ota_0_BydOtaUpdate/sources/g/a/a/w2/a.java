package g.a.a.w2;

import g.a.a.c1;

/* JADX INFO: loaded from: classes.dex */
public class a extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.m f4109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.e f4110d;

    public a(g.a.a.m mVar) {
        this.f4109c = mVar;
    }

    public a(g.a.a.m mVar, g.a.a.e eVar) {
        this.f4109c = mVar;
        this.f4110d = eVar;
    }

    public a(g.a.a.r rVar) {
        if (rVar.size() >= 1 && rVar.size() <= 2) {
            this.f4109c = g.a.a.m.s(rVar.q(0));
            this.f4110d = rVar.size() == 2 ? rVar.q(1) : null;
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }
    }

    public static a h(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(g.a.a.r.n(obj));
        }
        return null;
    }

    public static a i(g.a.a.x xVar, boolean z) {
        return h(g.a.a.r.o(xVar, z));
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4109c);
        g.a.a.e eVar = this.f4110d;
        if (eVar != null) {
            fVar.a(eVar);
        }
        return new c1(fVar);
    }

    public g.a.a.m g() {
        return this.f4109c;
    }

    public g.a.a.e j() {
        return this.f4110d;
    }
}
