package g.a.b.l0.h;

import g.a.b.n;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f5114a = {1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Hashtable f5115b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public n f5116c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f5117d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f5118e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f5119f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.l0.c f5120g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5121h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5122i;

    static {
        Hashtable hashtable = new Hashtable();
        f5115b = hashtable;
        hashtable.put("SHA-1", g.a.f.d.b(440));
        hashtable.put("SHA-224", g.a.f.d.b(440));
        hashtable.put("SHA-256", g.a.f.d.b(440));
        hashtable.put("SHA-512/256", g.a.f.d.b(440));
        hashtable.put("SHA-512/224", g.a.f.d.b(440));
        hashtable.put("SHA-384", g.a.f.d.b(888));
        hashtable.put("SHA-512", g.a.f.d.b(888));
    }

    public b(n nVar, int i2, g.a.b.l0.c cVar, byte[] bArr, byte[] bArr2) {
        if (i2 > d.a(nVar)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (cVar.entropySize() < i2) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this.f5116c = nVar;
        this.f5120g = cVar;
        this.f5121h = i2;
        this.f5122i = ((Integer) f5115b.get(nVar.getAlgorithmName())).intValue();
        byte[] bArrC = d.c(this.f5116c, g.a.f.a.n(e(), bArr2, bArr), this.f5122i);
        this.f5117d = bArrC;
        byte[] bArr3 = new byte[bArrC.length + 1];
        System.arraycopy(bArrC, 0, bArr3, 1, bArrC.length);
        this.f5118e = d.c(this.f5116c, bArr3, this.f5122i);
        this.f5119f = 1L;
    }

    @Override // g.a.b.l0.h.c
    public int a(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        if (length > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        }
        if (this.f5119f > 140737488355328L) {
            return -1;
        }
        if (z) {
            b(bArr2);
            bArr2 = null;
        }
        if (bArr2 != null) {
            byte[] bArr3 = this.f5117d;
            byte[] bArr4 = new byte[bArr3.length + 1 + bArr2.length];
            bArr4[0] = 2;
            System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
            System.arraycopy(bArr2, 0, bArr4, this.f5117d.length + 1, bArr2.length);
            c(this.f5117d, f(bArr4));
        }
        byte[] bArrG = g(this.f5117d, length);
        byte[] bArr5 = this.f5117d;
        byte[] bArr6 = new byte[bArr5.length + 1];
        System.arraycopy(bArr5, 0, bArr6, 1, bArr5.length);
        bArr6[0] = 3;
        c(this.f5117d, f(bArr6));
        c(this.f5117d, this.f5118e);
        c(this.f5117d, new byte[]{(byte) (r6 >> 24), (byte) (r6 >> 16), (byte) (r6 >> 8), (byte) this.f5119f});
        this.f5119f++;
        System.arraycopy(bArrG, 0, bArr, 0, bArr.length);
        return length;
    }

    @Override // g.a.b.l0.h.c
    public void b(byte[] bArr) {
        byte[] bArrC = d.c(this.f5116c, g.a.f.a.o(f5114a, this.f5117d, e(), bArr), this.f5122i);
        this.f5117d = bArrC;
        byte[] bArr2 = new byte[bArrC.length + 1];
        bArr2[0] = 0;
        System.arraycopy(bArrC, 0, bArr2, 1, bArrC.length);
        this.f5118e = d.c(this.f5116c, bArr2, this.f5122i);
        this.f5119f = 1L;
    }

    public final void c(byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        for (int i3 = 1; i3 <= bArr2.length; i3++) {
            int i4 = (bArr[bArr.length - i3] & 255) + (bArr2[bArr2.length - i3] & 255) + i2;
            i2 = i4 > 255 ? 1 : 0;
            bArr[bArr.length - i3] = (byte) i4;
        }
        for (int length = bArr2.length + 1; length <= bArr.length; length++) {
            int i5 = (bArr[bArr.length - length] & 255) + i2;
            i2 = i5 > 255 ? 1 : 0;
            bArr[bArr.length - length] = (byte) i5;
        }
    }

    public final void d(byte[] bArr, byte[] bArr2) {
        this.f5116c.update(bArr, 0, bArr.length);
        this.f5116c.doFinal(bArr2, 0);
    }

    public final byte[] e() {
        byte[] entropy = this.f5120g.getEntropy();
        if (entropy.length >= (this.f5121h + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    public final byte[] f(byte[] bArr) {
        byte[] bArr2 = new byte[this.f5116c.getDigestSize()];
        d(bArr, bArr2);
        return bArr2;
    }

    public final byte[] g(byte[] bArr, int i2) {
        int i3 = i2 / 8;
        int digestSize = i3 / this.f5116c.getDigestSize();
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = new byte[i3];
        int digestSize2 = this.f5116c.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        for (int i4 = 0; i4 <= digestSize; i4++) {
            d(bArr2, bArr4);
            int i5 = i4 * digestSize2;
            int i6 = i3 - i5;
            if (i6 > digestSize2) {
                i6 = digestSize2;
            }
            System.arraycopy(bArr4, 0, bArr3, i5, i6);
            c(bArr2, f5114a);
        }
        return bArr3;
    }
}
