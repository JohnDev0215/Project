package g.a.a.p2;

/* JADX INFO: loaded from: classes.dex */
public class h extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.w2.a f3966c;

    public h(g.a.a.m mVar, g.a.a.e eVar) {
        this.f3966c = new g.a.a.w2.a(mVar, eVar);
    }

    public h(g.a.a.r rVar) {
        this.f3966c = g.a.a.w2.a.h(rVar);
    }

    public static h h(Object obj) {
        if (obj instanceof h) {
            return (h) obj;
        }
        if (obj != null) {
            return new h(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.f3966c.c();
    }

    public g.a.a.m g() {
        return this.f3966c.g();
    }

    public g.a.a.e i() {
        return this.f3966c.j();
    }
}
