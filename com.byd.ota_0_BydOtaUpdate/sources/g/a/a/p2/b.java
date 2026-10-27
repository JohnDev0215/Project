package g.a.a.p2;

import g.a.a.c1;
import g.a.a.h1;

/* JADX INFO: loaded from: classes.dex */
public class b extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.m f3956c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.e f3957d;

    public b(g.a.a.m mVar, g.a.a.e eVar) {
        this.f3956c = mVar;
        this.f3957d = eVar;
    }

    public b(g.a.a.r rVar) {
        this.f3956c = (g.a.a.m) rVar.q(0);
        this.f3957d = ((h1) rVar.q(1)).p();
    }

    public static b i(Object obj) {
        if (obj instanceof b) {
            return (b) obj;
        }
        if (obj != null) {
            return new b(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3956c);
        fVar.a(new h1(0, this.f3957d));
        return new c1(fVar);
    }

    public g.a.a.m g() {
        return this.f3956c;
    }

    public g.a.a.e h() {
        return this.f3957d;
    }
}
