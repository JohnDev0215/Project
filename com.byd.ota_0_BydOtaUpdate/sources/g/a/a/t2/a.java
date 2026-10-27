package g.a.a.t2;

import g.a.a.c1;
import g.a.a.j;
import g.a.a.l;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class a extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4054c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4055d;
    public int s;
    public int x;

    public a(r rVar) {
        this.f4054c = j.n(rVar.q(0)).p().intValue();
        if (rVar.q(1) instanceof j) {
            this.f4055d = ((j) rVar.q(1)).p().intValue();
        } else {
            if (!(rVar.q(1) instanceof r)) {
                throw new IllegalArgumentException("object parse error");
            }
            r rVarN = r.n(rVar.q(1));
            this.f4055d = j.n(rVarN.q(0)).p().intValue();
            this.s = j.n(rVarN.q(1)).p().intValue();
            this.x = j.n(rVarN.q(2)).p().intValue();
        }
    }

    public static a g(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new j(this.f4054c));
        if (this.s == 0) {
            fVar.a(new j(this.f4055d));
        } else {
            g.a.a.f fVar2 = new g.a.a.f();
            fVar2.a(new j(this.f4055d));
            fVar2.a(new j(this.s));
            fVar2.a(new j(this.x));
            fVar.a(new c1(fVar2));
        }
        return new c1(fVar);
    }

    public int h() {
        return this.f4055d;
    }

    public int i() {
        return this.s;
    }

    public int j() {
        return this.x;
    }

    public int k() {
        return this.f4054c;
    }
}
