package g.a.a.o2;

import g.a.a.c1;
import g.a.a.f;
import g.a.a.j;
import g.a.a.l;
import g.a.a.q;
import g.a.a.r;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class a extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f3938c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f3939d;

    public a(r rVar) {
        Enumeration enumerationR = rVar.r();
        this.f3938c = (j) enumerationR.nextElement();
        this.f3939d = (j) enumerationR.nextElement();
    }

    public a(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f3938c = new j(bigInteger);
        this.f3939d = new j(bigInteger2);
    }

    public static a h(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        f fVar = new f();
        fVar.a(this.f3938c);
        fVar.a(this.f3939d);
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.f3939d.p();
    }

    public BigInteger i() {
        return this.f3938c.p();
    }
}
