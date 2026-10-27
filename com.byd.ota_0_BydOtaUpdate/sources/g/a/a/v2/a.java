package g.a.a.v2;

import g.a.a.c1;
import g.a.a.e;
import g.a.a.f;
import g.a.a.l;
import g.a.a.m;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class a extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f4082c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f4083d;

    public a(r rVar) {
        this.f4082c = (m) rVar.q(0);
        this.f4083d = rVar.q(1);
    }

    public static a g(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(r.n(obj));
        }
        throw new IllegalArgumentException("null value in getInstance()");
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        f fVar = new f();
        fVar.a(this.f4082c);
        fVar.a(this.f4083d);
        return new c1(fVar);
    }

    public m h() {
        return this.f4082c;
    }

    public e i() {
        return this.f4083d;
    }
}
