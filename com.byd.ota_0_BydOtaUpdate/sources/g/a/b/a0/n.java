package g.a.b.a0;

import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class n implements g.a.b.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ByteArrayOutputStream f4329a = new ByteArrayOutputStream();

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        byte[] byteArray = this.f4329a.toByteArray();
        System.arraycopy(byteArray, 0, bArr, i2, byteArray.length);
        reset();
        return byteArray.length;
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "NULL";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return this.f4329a.size();
    }

    @Override // g.a.b.n
    public void reset() {
        this.f4329a.reset();
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        this.f4329a.write(b2);
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        this.f4329a.write(bArr, i2, i3);
    }
}
