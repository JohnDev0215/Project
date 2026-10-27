package g.a.b.e0;

/* JADX INFO: loaded from: classes.dex */
public class w extends g.a.b.h {
    public static void c(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        bArr[3] = (byte) (bArr[3] & 15);
        bArr[7] = (byte) (bArr[7] & 15);
        bArr[11] = (byte) (bArr[11] & 15);
        bArr[15] = (byte) (bArr[15] & 15);
        bArr[4] = (byte) (bArr[4] & (-4));
        bArr[8] = (byte) (bArr[8] & (-4));
        bArr[12] = (byte) (bArr[12] & (-4));
    }

    @Override // g.a.b.h
    public byte[] a() {
        byte[] bArrA = super.a();
        c(bArrA);
        return bArrA;
    }

    @Override // g.a.b.h
    public void b(g.a.b.r rVar) {
        super.b(new g.a.b.r(rVar.a(), 256));
    }
}
