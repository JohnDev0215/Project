package g.a.a.a2;

import g.a.a.c1;
import g.a.a.i1;
import g.a.a.l;
import g.a.a.q;
import g.a.a.r;
import g.a.a.t0;
import java.math.BigInteger;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class h extends l {
    public final String E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final BigInteger f3759c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g.a.a.w2.a f3760d;
    public final g.a.a.h s;
    public final g.a.a.h x;
    public final f y;

    public h(r rVar) {
        this.f3759c = g.a.a.j.n(rVar.q(0)).q();
        this.f3760d = g.a.a.w2.a.h(rVar.q(1));
        this.s = g.a.a.h.q(rVar.q(2));
        this.x = g.a.a.h.q(rVar.q(3));
        this.y = f.g(rVar.q(4));
        this.E3 = rVar.size() == 6 ? i1.n(rVar.q(5)).d() : null;
    }

    public h(g.a.a.w2.a aVar, Date date, Date date2, f fVar, String str) {
        this.f3759c = BigInteger.valueOf(1L);
        this.f3760d = aVar;
        this.s = new t0(date);
        this.x = new t0(date2);
        this.y = fVar;
        this.E3 = str;
    }

    public static h h(Object obj) {
        if (obj instanceof h) {
            return (h) obj;
        }
        if (obj != null) {
            return new h(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(this.f3759c));
        fVar.a(this.f3760d);
        fVar.a(this.s);
        fVar.a(this.x);
        fVar.a(this.y);
        String str = this.E3;
        if (str != null) {
            fVar.a(new i1(str));
        }
        return new c1(fVar);
    }

    public g.a.a.h g() {
        return this.s;
    }

    public g.a.a.w2.a i() {
        return this.f3760d;
    }

    public g.a.a.h j() {
        return this.x;
    }

    public f k() {
        return this.y;
    }
}
