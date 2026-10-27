package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class z0 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5087c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.i f5088d;

    public z0(g.a.b.i iVar, byte[] bArr) {
        this(iVar, bArr, 0, bArr.length);
    }

    public z0(g.a.b.i iVar, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        this.f5087c = bArr2;
        this.f5088d = iVar;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
    }

    public g.a.b.i a() {
        return this.f5088d;
    }

    public byte[] b() {
        return this.f5087c;
    }
}
