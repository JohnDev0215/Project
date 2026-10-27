package g.a.a.p2;

import g.a.a.o1;
import g.a.a.q1;
import g.a.a.x;

/* JADX INFO: loaded from: classes.dex */
public class v extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.m f3997c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.e f3998d;
    public g.a.a.t s;

    public v(g.a.a.m mVar, g.a.a.e eVar, g.a.a.t tVar) {
        this.f3997c = mVar;
        this.f3998d = eVar;
        this.s = tVar;
    }

    public v(g.a.a.r rVar) {
        this.f3997c = (g.a.a.m) rVar.q(0);
        this.f3998d = ((x) rVar.q(1)).p();
        if (rVar.size() == 3) {
            this.s = (g.a.a.t) rVar.q(2);
        }
    }

    public static v j(Object obj) {
        if (obj instanceof v) {
            return (v) obj;
        }
        if (obj != null) {
            return new v(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3997c);
        fVar.a(new q1(true, 0, this.f3998d));
        g.a.a.t tVar = this.s;
        if (tVar != null) {
            fVar.a(tVar);
        }
        return new o1(fVar);
    }

    public g.a.a.t g() {
        return this.s;
    }

    public g.a.a.m h() {
        return this.f3997c;
    }

    public g.a.a.e i() {
        return this.f3998d;
    }
}
