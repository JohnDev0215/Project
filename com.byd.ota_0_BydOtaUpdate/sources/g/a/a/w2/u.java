package g.a.a.w2;

import g.a.a.c1;
import g.a.a.o0;
import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class u extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f4142c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o0 f4143d;

    public u(g.a.a.r rVar) {
        if (rVar.size() == 2) {
            Enumeration enumerationR = rVar.r();
            this.f4142c = a.h(enumerationR.nextElement());
            this.f4143d = o0.t(enumerationR.nextElement());
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }
    }

    public u(a aVar, g.a.a.e eVar) throws IOException {
        this.f4143d = new o0(eVar);
        this.f4142c = aVar;
    }

    public u(a aVar, byte[] bArr) {
        this.f4143d = new o0(bArr);
        this.f4142c = aVar;
    }

    public static u i(Object obj) {
        if (obj instanceof u) {
            return (u) obj;
        }
        if (obj != null) {
            return new u(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4142c);
        fVar.a(this.f4143d);
        return new c1(fVar);
    }

    public a g() {
        return this.f4142c;
    }

    public a h() {
        return this.f4142c;
    }

    public o0 j() {
        return this.f4143d;
    }

    public g.a.a.q k() throws IOException {
        return g.a.a.q.j(this.f4143d.q());
    }
}
