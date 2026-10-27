package g.a.e.b.c;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public short[][][] f5602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public short[][][] f5603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public short[][] f5604f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public short[] f5605g;

    public a(byte b2, byte b3, short[][][] sArr, short[][][] sArr2, short[][] sArr3, short[] sArr4) {
        int i2 = b2 & 255;
        this.f5599a = i2;
        int i3 = b3 & 255;
        this.f5600b = i3;
        this.f5601c = i3 - i2;
        this.f5602d = sArr;
        this.f5603e = sArr2;
        this.f5604f = sArr3;
        this.f5605g = sArr4;
    }

    public short[][][] a() {
        return this.f5602d;
    }

    public short[][][] b() {
        return this.f5603e;
    }

    public short[] c() {
        return this.f5605g;
    }

    public short[][] d() {
        return this.f5604f;
    }

    public int e() {
        return this.f5601c;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f5599a == aVar.f() && this.f5600b == aVar.g() && this.f5601c == aVar.e() && g.a.e.b.c.f.a.k(this.f5602d, aVar.a()) && g.a.e.b.c.f.a.k(this.f5603e, aVar.b()) && g.a.e.b.c.f.a.j(this.f5604f, aVar.d()) && g.a.e.b.c.f.a.i(this.f5605g, aVar.c());
    }

    public int f() {
        return this.f5599a;
    }

    public int g() {
        return this.f5600b;
    }

    public int hashCode() {
        return (((((((((((this.f5599a * 37) + this.f5600b) * 37) + this.f5601c) * 37) + g.a.f.a.G(this.f5602d)) * 37) + g.a.f.a.G(this.f5603e)) * 37) + g.a.f.a.F(this.f5604f)) * 37) + g.a.f.a.E(this.f5605g);
    }
}
