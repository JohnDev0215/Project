package g.a.a.w2;

import g.a.a.c1;
import g.a.a.y0;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class j extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4125c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f4126d;

    public j(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        this.f4126d = a.h(enumerationR.nextElement());
        this.f4125c = g.a.a.n.n(enumerationR.nextElement()).p();
    }

    public j(a aVar, byte[] bArr) {
        this.f4125c = g.a.f.a.f(bArr);
        this.f4126d = aVar;
    }

    public static j i(Object obj) {
        if (obj instanceof j) {
            return (j) obj;
        }
        if (obj != null) {
            return new j(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4126d);
        fVar.a(new y0(this.f4125c));
        return new c1(fVar);
    }

    public a g() {
        return this.f4126d;
    }

    public byte[] h() {
        return g.a.f.a.f(this.f4125c);
    }
}
