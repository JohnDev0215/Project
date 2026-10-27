package g.a.a.a2;

import g.a.a.c1;
import g.a.a.i1;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.t0;
import g.a.a.y0;
import java.math.BigInteger;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class e extends l {
    public final String E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final BigInteger f3754c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3755d;
    public final g.a.a.h s;
    public final g.a.a.h x;
    public final n y;

    public e(r rVar) {
        this.f3754c = g.a.a.j.n(rVar.q(0)).q();
        this.f3755d = i1.n(rVar.q(1)).d();
        this.s = g.a.a.h.q(rVar.q(2));
        this.x = g.a.a.h.q(rVar.q(3));
        this.y = n.n(rVar.q(4));
        this.E3 = rVar.size() == 6 ? i1.n(rVar.q(5)).d() : null;
    }

    public e(BigInteger bigInteger, String str, Date date, Date date2, byte[] bArr, String str2) {
        this.f3754c = bigInteger;
        this.f3755d = str;
        this.s = new t0(date);
        this.x = new t0(date2);
        this.y = new y0(g.a.f.a.f(bArr));
        this.E3 = str2;
    }

    public static e j(Object obj) {
        if (obj instanceof e) {
            return (e) obj;
        }
        if (obj != null) {
            return new e(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(this.f3754c));
        fVar.a(new i1(this.f3755d));
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

    public byte[] h() {
        return g.a.f.a.f(this.y.p());
    }

    public String i() {
        return this.f3755d;
    }

    public g.a.a.h k() {
        return this.x;
    }

    public BigInteger l() {
        return this.f3754c;
    }
}
