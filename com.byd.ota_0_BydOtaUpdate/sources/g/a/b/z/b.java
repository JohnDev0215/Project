package g.a.b.z;

import g.a.b.i;
import g.a.b.j0.l;
import g.a.b.j0.m;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l f5202a;

    public byte[] a(i iVar) {
        m mVar = (m) iVar;
        a aVar = new a();
        a aVar2 = new a();
        aVar.init(this.f5202a.b());
        BigInteger bigIntegerB = aVar.b(mVar.b());
        aVar2.init(this.f5202a.a());
        return g.a.f.a.m(g.a.f.b.a(b(), aVar2.b(mVar.a())), g.a.f.b.a(b(), bigIntegerB));
    }

    public int b() {
        return (this.f5202a.b().b().f().bitLength() + 7) / 8;
    }

    public void c(i iVar) {
        this.f5202a = (l) iVar;
    }
}
