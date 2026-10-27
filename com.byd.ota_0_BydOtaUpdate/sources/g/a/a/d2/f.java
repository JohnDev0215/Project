package g.a.a.d2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.m;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class f extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f3815c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f3816d;
    public m s;

    public f(m mVar, m mVar2) {
        this.f3815c = mVar;
        this.f3816d = mVar2;
        this.s = null;
    }

    public f(m mVar, m mVar2, m mVar3) {
        this.f3815c = mVar;
        this.f3816d = mVar2;
        this.s = mVar3;
    }

    public f(r rVar) {
        this.f3815c = (m) rVar.q(0);
        this.f3816d = (m) rVar.q(1);
        if (rVar.size() > 2) {
            this.s = (m) rVar.q(2);
        }
    }

    public static f i(Object obj) {
        if (obj instanceof f) {
            return (f) obj;
        }
        if (obj != null) {
            return new f(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3815c);
        fVar.a(this.f3816d);
        m mVar = this.s;
        if (mVar != null) {
            fVar.a(mVar);
        }
        return new c1(fVar);
    }

    public m g() {
        return this.f3816d;
    }

    public m h() {
        return this.s;
    }

    public m j() {
        return this.f3815c;
    }
}
