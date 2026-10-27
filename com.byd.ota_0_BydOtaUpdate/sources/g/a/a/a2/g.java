package g.a.a.a2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class g extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.e f3757c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f3758d;

    public g(b bVar, i iVar) {
        this.f3757c = bVar;
        this.f3758d = iVar;
    }

    public g(r rVar) {
        if (rVar.size() != 2) {
            throw new IllegalArgumentException("malformed sequence");
        }
        g.a.a.e eVarQ = rVar.q(0);
        if (!(eVarQ instanceof b) && !(eVarQ instanceof h)) {
            r rVarN = r.n(eVarQ);
            eVarQ = rVarN.size() == 2 ? b.i(rVarN) : h.h(rVarN);
        }
        this.f3757c = eVarQ;
        this.f3758d = i.g(rVar.q(1));
    }

    public static g g(Object obj) {
        if (obj instanceof g) {
            return (g) obj;
        }
        if (obj != null) {
            return new g(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3757c);
        fVar.a(this.f3758d);
        return new c1(fVar);
    }

    public i h() {
        return this.f3758d;
    }

    public g.a.a.e i() {
        return this.f3757c;
    }
}
