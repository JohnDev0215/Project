package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class w0 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5076c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.i f5077d;

    public w0(g.a.b.i iVar, byte[] bArr) {
        this(iVar, bArr, 0, bArr.length);
    }

    public w0(g.a.b.i iVar, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        this.f5076c = bArr2;
        this.f5077d = iVar;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
    }

    public byte[] a() {
        return this.f5076c;
    }

    public g.a.b.i b() {
        return this.f5077d;
    }
}
