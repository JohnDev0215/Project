package g.a.b.l0;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static byte[] a(c cVar, int i2) {
        byte[] bArr = new byte[i2];
        if (i2 * 8 <= cVar.entropySize()) {
            System.arraycopy(cVar.getEntropy(), 0, bArr, 0, i2);
        } else {
            int iEntropySize = cVar.entropySize() / 8;
            for (int i3 = 0; i3 < i2; i3 += iEntropySize) {
                byte[] entropy = cVar.getEntropy();
                int i4 = i2 - i3;
                if (entropy.length <= i4) {
                    System.arraycopy(entropy, 0, bArr, i3, entropy.length);
                } else {
                    System.arraycopy(entropy, 0, bArr, i3, i4);
                }
            }
        }
        return bArr;
    }
}
