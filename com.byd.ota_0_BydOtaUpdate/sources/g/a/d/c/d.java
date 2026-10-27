package g.a.d.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class d implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f5558a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f5559b;

    public d(a aVar, e eVar) {
        this.f5558a = aVar;
        this.f5559b = eVar;
    }

    @Override // g.a.d.c.f
    public e a() {
        return this.f5559b;
    }

    @Override // g.a.d.c.a
    public int b() {
        return this.f5558a.b() * this.f5559b.b();
    }

    @Override // g.a.d.c.a
    public BigInteger c() {
        return this.f5558a.c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f5558a.equals(dVar.f5558a) && this.f5559b.equals(dVar.f5559b);
    }

    public int hashCode() {
        return this.f5558a.hashCode() ^ g.a.f.d.a(this.f5559b.hashCode(), 16);
    }
}
