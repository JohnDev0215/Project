package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5065a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f5066b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5067c;

    public u(byte[] bArr, int i2) {
        this(bArr, i2, -1);
    }

    public u(byte[] bArr, int i2, int i3) {
        this.f5066b = bArr;
        this.f5067c = i2;
        this.f5065a = i3;
    }

    public int a() {
        return this.f5067c;
    }

    public byte[] b() {
        return this.f5066b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        if (uVar.f5067c != this.f5067c) {
            return false;
        }
        return g.a.f.a.a(this.f5066b, uVar.f5066b);
    }

    public int hashCode() {
        return this.f5067c ^ g.a.f.a.z(this.f5066b);
    }
}
