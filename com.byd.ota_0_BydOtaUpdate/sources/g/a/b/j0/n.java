package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f5042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5043b;

    public n(byte[] bArr, int i2) {
        this.f5042a = bArr;
        this.f5043b = i2;
    }

    public int a() {
        return this.f5043b;
    }

    public byte[] b() {
        return this.f5042a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (nVar.f5043b != this.f5043b) {
            return false;
        }
        return g.a.f.a.a(this.f5042a, nVar.f5042a);
    }

    public int hashCode() {
        return this.f5043b ^ g.a.f.a.z(this.f5042a);
    }
}
