package g.a.a.a2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class b extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.w2.a f3748c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n f3749d;

    public b(r rVar) {
        this.f3748c = g.a.a.w2.a.h(rVar.q(0));
        this.f3749d = n.n(rVar.q(1));
    }

    public b(g.a.a.w2.a aVar, byte[] bArr) {
        this.f3748c = aVar;
        this.f3749d = new y0(bArr);
    }

    public static b i(Object obj) {
        if (obj instanceof b) {
            return (b) obj;
        }
        if (obj != null) {
            return new b(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3748c);
        fVar.a(this.f3749d);
        return new c1(fVar);
    }

    public n g() {
        return this.f3749d;
    }

    public g.a.a.w2.a h() {
        return this.f3748c;
    }
}
