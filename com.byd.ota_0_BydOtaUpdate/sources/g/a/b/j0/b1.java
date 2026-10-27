package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class b1 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5011c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5012d;

    public b1(byte[] bArr, int i2) {
        if (bArr.length > 255) {
            throw new IllegalArgumentException("RC5 key length can be no greater than 255");
        }
        byte[] bArr2 = new byte[bArr.length];
        this.f5011c = bArr2;
        this.f5012d = i2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public byte[] a() {
        return this.f5011c;
    }

    public int b() {
        return this.f5012d;
    }
}
