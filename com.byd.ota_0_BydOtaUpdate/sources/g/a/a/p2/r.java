package g.a.a.p2;

import g.a.a.c1;
import g.a.a.h1;
import g.a.a.w0;
import g.a.a.x;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class r extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g.a.a.w2.a f3989c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g.a.a.w2.a f3990d;
    public static final g.a.a.w2.a s;
    public g.a.a.w2.a E3;
    public g.a.a.w2.a x;
    public g.a.a.w2.a y;

    static {
        g.a.a.w2.a aVar = new g.a.a.w2.a(g.a.a.o2.b.f3948i, w0.f4105c);
        f3989c = aVar;
        f3990d = new g.a.a.w2.a(n.l, aVar);
        s = new g.a.a.w2.a(n.m, new y0(new byte[0]));
    }

    public r() {
        this.x = f3989c;
        this.y = f3990d;
        this.E3 = s;
    }

    public r(g.a.a.r rVar) {
        this.x = f3989c;
        this.y = f3990d;
        this.E3 = s;
        for (int i2 = 0; i2 != rVar.size(); i2++) {
            x xVar = (x) rVar.q(i2);
            int iQ = xVar.q();
            if (iQ == 0) {
                this.x = g.a.a.w2.a.i(xVar, true);
            } else if (iQ == 1) {
                this.y = g.a.a.w2.a.i(xVar, true);
            } else {
                if (iQ != 2) {
                    throw new IllegalArgumentException("unknown tag");
                }
                this.E3 = g.a.a.w2.a.i(xVar, true);
            }
        }
    }

    public r(g.a.a.w2.a aVar, g.a.a.w2.a aVar2, g.a.a.w2.a aVar3) {
        this.x = aVar;
        this.y = aVar2;
        this.E3 = aVar3;
    }

    public static r h(Object obj) {
        if (obj instanceof r) {
            return (r) obj;
        }
        if (obj != null) {
            return new r(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        if (!this.x.equals(f3989c)) {
            fVar.a(new h1(true, 0, this.x));
        }
        if (!this.y.equals(f3990d)) {
            fVar.a(new h1(true, 1, this.y));
        }
        if (!this.E3.equals(s)) {
            fVar.a(new h1(true, 2, this.E3));
        }
        return new c1(fVar);
    }

    public g.a.a.w2.a g() {
        return this.x;
    }

    public g.a.a.w2.a i() {
        return this.y;
    }

    public g.a.a.w2.a j() {
        return this.E3;
    }
}
