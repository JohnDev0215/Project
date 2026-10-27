package g.a.a.d2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.m;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class c extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public n f3805c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f3806d;

    public c(r rVar) {
        Enumeration enumerationR = rVar.r();
        this.f3805c = (n) enumerationR.nextElement();
        this.f3806d = (m) enumerationR.nextElement();
    }

    public c(byte[] bArr, m mVar) {
        this.f3805c = new y0(bArr);
        this.f3806d = mVar;
    }

    public static c i(Object obj) {
        if (obj instanceof c) {
            return (c) obj;
        }
        if (obj != null) {
            return new c(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3805c);
        fVar.a(this.f3806d);
        return new c1(fVar);
    }

    public m g() {
        return this.f3806d;
    }

    public byte[] h() {
        return g.a.f.a.f(this.f3805c.p());
    }
}
