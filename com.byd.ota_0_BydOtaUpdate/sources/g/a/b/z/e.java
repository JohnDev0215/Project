package g.a.b.z;

import g.a.b.i;
import g.a.b.j0.v;
import g.a.b.j0.w;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v f5205a;

    public byte[] a(i iVar) {
        w wVar = (w) iVar;
        d dVar = new d();
        d dVar2 = new d();
        dVar.init(this.f5205a.b());
        BigInteger bigIntegerB = dVar.b(wVar.b());
        dVar2.init(this.f5205a.a());
        return g.a.f.a.m(g.a.f.b.a(b(), dVar2.b(wVar.a())), g.a.f.b.a(b(), bigIntegerB));
    }

    public int b() {
        return (this.f5205a.b().b().a().u() + 7) / 8;
    }

    public void c(i iVar) {
        this.f5205a = (v) iVar;
    }
}
