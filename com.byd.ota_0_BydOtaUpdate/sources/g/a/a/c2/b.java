package g.a.a.c2;

import g.a.a.c1;
import g.a.a.f;
import g.a.a.j;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class b extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f3789c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3790d;

    public b(r rVar) {
        this.f3789c = n.n(rVar.q(0)).p();
        this.f3790d = rVar.size() == 2 ? j.n(rVar.q(1)).q().intValue() : 12;
    }

    public b(byte[] bArr, int i2) {
        this.f3789c = g.a.f.a.f(bArr);
        this.f3790d = i2;
    }

    public static b h(Object obj) {
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
        f fVar = new f();
        fVar.a(new y0(this.f3789c));
        int i2 = this.f3790d;
        if (i2 != 12) {
            fVar.a(new j(i2));
        }
        return new c1(fVar);
    }

    public int g() {
        return this.f3790d;
    }

    public byte[] i() {
        return g.a.f.a.f(this.f3789c);
    }
}
