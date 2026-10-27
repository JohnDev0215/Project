package g.a.a.x2;

import g.a.a.q;

/* JADX INFO: loaded from: classes.dex */
public class e extends g.a.a.l implements g.a.a.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public q f4183c;

    public e(g.a.a.k kVar) {
        this.f4183c = null;
        this.f4183c = kVar;
    }

    public e(g.a.a.m mVar) {
        this.f4183c = null;
        this.f4183c = mVar;
    }

    public e(q qVar) {
        this.f4183c = null;
        this.f4183c = qVar;
    }

    public e(g gVar) {
        this.f4183c = null;
        this.f4183c = gVar.c();
    }

    public static e g(Object obj) {
        if (obj == null || (obj instanceof e)) {
            return (e) obj;
        }
        if (obj instanceof q) {
            return new e((q) obj);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("unknown object in getInstance()");
        }
        try {
            return new e(q.j((byte[]) obj));
        } catch (Exception e2) {
            throw new IllegalArgumentException("unable to parse encoded data: " + e2.getMessage());
        }
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return this.f4183c;
    }

    public q h() {
        return this.f4183c;
    }

    public boolean i() {
        return this.f4183c instanceof g.a.a.k;
    }

    public boolean j() {
        return this.f4183c instanceof g.a.a.m;
    }
}
