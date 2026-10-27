package g.a.a.w2;

import g.a.a.c1;
import g.a.a.h1;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class v extends g.a.a.l {
    public g.a.a.r E3;
    public n F3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f4144c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.w2.a f4145d;
    public g.a.a.v2.c s;
    public x x;
    public x y;

    public static class b extends g.a.a.l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public g.a.a.r f4146c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public n f4147d;

        public b(g.a.a.r rVar) {
            if (rVar.size() >= 2 && rVar.size() <= 3) {
                this.f4146c = rVar;
                return;
            }
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }

        public static b h(Object obj) {
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
            return this.f4146c;
        }

        public n g() {
            if (this.f4147d == null && this.f4146c.size() == 3) {
                this.f4147d = n.h(this.f4146c.q(2));
            }
            return this.f4147d;
        }

        public x i() {
            return x.h(this.f4146c.q(1));
        }

        public g.a.a.j j() {
            return g.a.a.j.n(this.f4146c.q(0));
        }

        public boolean k() {
            return this.f4146c.size() == 3;
        }
    }

    public class c implements Enumeration {
        public c() {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            throw new NoSuchElementException("Empty Enumeration");
        }
    }

    public class d implements Enumeration {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Enumeration f4149a;

        public d(Enumeration enumeration) {
            this.f4149a = enumeration;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f4149a.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return b.h(this.f4149a.nextElement());
        }
    }

    public v(g.a.a.r rVar) {
        if (rVar.size() < 3 || rVar.size() > 7) {
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }
        int i2 = 0;
        if (rVar.q(0) instanceof g.a.a.j) {
            this.f4144c = g.a.a.j.n(rVar.q(0));
            i2 = 1;
        } else {
            this.f4144c = null;
        }
        int i3 = i2 + 1;
        this.f4145d = g.a.a.w2.a.h(rVar.q(i2));
        int i4 = i3 + 1;
        this.s = g.a.a.v2.c.g(rVar.q(i3));
        int i5 = i4 + 1;
        this.x = x.h(rVar.q(i4));
        if (i5 < rVar.size() && ((rVar.q(i5) instanceof g.a.a.y) || (rVar.q(i5) instanceof g.a.a.h) || (rVar.q(i5) instanceof x))) {
            this.y = x.h(rVar.q(i5));
            i5++;
        }
        if (i5 < rVar.size() && !(rVar.q(i5) instanceof g.a.a.x)) {
            this.E3 = g.a.a.r.n(rVar.q(i5));
            i5++;
        }
        if (i5 >= rVar.size() || !(rVar.q(i5) instanceof g.a.a.x)) {
            return;
        }
        this.F3 = n.h(g.a.a.r.o((g.a.a.x) rVar.q(i5), true));
    }

    public static v h(Object obj) {
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
        g.a.a.j jVar = this.f4144c;
        if (jVar != null) {
            fVar.a(jVar);
        }
        fVar.a(this.f4145d);
        fVar.a(this.s);
        fVar.a(this.x);
        x xVar = this.y;
        if (xVar != null) {
            fVar.a(xVar);
        }
        g.a.a.r rVar = this.E3;
        if (rVar != null) {
            fVar.a(rVar);
        }
        if (this.F3 != null) {
            fVar.a(new h1(0, this.F3));
        }
        return new c1(fVar);
    }

    public n g() {
        return this.F3;
    }

    public g.a.a.v2.c i() {
        return this.s;
    }

    public x j() {
        return this.y;
    }

    public Enumeration k() {
        g.a.a.r rVar = this.E3;
        return rVar == null ? new c() : new d(rVar.r());
    }

    public g.a.a.w2.a l() {
        return this.f4145d;
    }

    public x m() {
        return this.x;
    }

    public int n() {
        g.a.a.j jVar = this.f4144c;
        if (jVar == null) {
            return 1;
        }
        return jVar.q().intValue() + 1;
    }
}
