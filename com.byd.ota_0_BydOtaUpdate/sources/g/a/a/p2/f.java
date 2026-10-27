package g.a.a.p2;

import g.a.a.c1;
import g.a.a.y0;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class f extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.w2.a f3963c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.n f3964d;

    public f(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        this.f3963c = g.a.a.w2.a.h(enumerationR.nextElement());
        this.f3964d = g.a.a.n.n(enumerationR.nextElement());
    }

    public f(g.a.a.w2.a aVar, byte[] bArr) {
        this.f3963c = aVar;
        this.f3964d = new y0(bArr);
    }

    public static f i(Object obj) {
        if (obj instanceof f) {
            return (f) obj;
        }
        if (obj != null) {
            return new f(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3963c);
        fVar.a(this.f3964d);
        return new c1(fVar);
    }

    public byte[] g() {
        return this.f3964d.p();
    }

    public g.a.a.w2.a h() {
        return this.f3963c;
    }
}
