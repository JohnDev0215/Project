package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class s0 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5062c;

    public s0(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public s0(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        this.f5062c = bArr2;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
    }

    public byte[] a() {
        return this.f5062c;
    }
}
