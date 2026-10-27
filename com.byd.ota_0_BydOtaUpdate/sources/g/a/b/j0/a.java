package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class a implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5007c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f5008d;
    public s0 s;
    public int x;

    public a(s0 s0Var, int i2, byte[] bArr) {
        this(s0Var, i2, bArr, null);
    }

    public a(s0 s0Var, int i2, byte[] bArr, byte[] bArr2) {
        this.s = s0Var;
        this.f5008d = bArr;
        this.x = i2;
        this.f5007c = bArr2;
    }

    public byte[] a() {
        return this.f5007c;
    }

    public s0 b() {
        return this.s;
    }

    public int c() {
        return this.x;
    }

    public byte[] d() {
        return this.f5008d;
    }
}
