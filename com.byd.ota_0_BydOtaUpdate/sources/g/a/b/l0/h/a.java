package g.a.b.l0.h;

import g.a.b.j0.s0;
import g.a.b.t;

/* JADX INFO: loaded from: classes.dex */
public class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f5108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f5109b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5110c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.l0.c f5111d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f5112e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5113f;

    public a(t tVar, int i2, g.a.b.l0.c cVar, byte[] bArr, byte[] bArr2) {
        if (i2 > d.b(tVar)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (cVar.entropySize() < i2) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this.f5113f = i2;
        this.f5111d = cVar;
        this.f5112e = tVar;
        byte[] bArrN = g.a.f.a.n(c(), bArr2, bArr);
        byte[] bArr3 = new byte[tVar.getMacSize()];
        this.f5108a = bArr3;
        byte[] bArr4 = new byte[bArr3.length];
        this.f5109b = bArr4;
        g.a.f.a.u(bArr4, (byte) 1);
        d(bArrN);
        this.f5110c = 1L;
    }

    @Override // g.a.b.l0.h.c
    public int a(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        if (length > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        }
        if (this.f5110c > 140737488355328L) {
            return -1;
        }
        if (z) {
            b(bArr2);
            bArr2 = null;
        }
        if (bArr2 != null) {
            d(bArr2);
        }
        int length2 = bArr.length;
        byte[] bArr3 = new byte[length2];
        int length3 = bArr.length / this.f5109b.length;
        this.f5112e.init(new s0(this.f5108a));
        for (int i2 = 0; i2 < length3; i2++) {
            t tVar = this.f5112e;
            byte[] bArr4 = this.f5109b;
            tVar.update(bArr4, 0, bArr4.length);
            this.f5112e.doFinal(this.f5109b, 0);
            byte[] bArr5 = this.f5109b;
            System.arraycopy(bArr5, 0, bArr3, bArr5.length * i2, bArr5.length);
        }
        byte[] bArr6 = this.f5109b;
        if (bArr6.length * length3 < length2) {
            this.f5112e.update(bArr6, 0, bArr6.length);
            this.f5112e.doFinal(this.f5109b, 0);
            byte[] bArr7 = this.f5109b;
            System.arraycopy(bArr7, 0, bArr3, bArr7.length * length3, length2 - (length3 * bArr7.length));
        }
        d(bArr2);
        this.f5110c++;
        System.arraycopy(bArr3, 0, bArr, 0, bArr.length);
        return length;
    }

    @Override // g.a.b.l0.h.c
    public void b(byte[] bArr) {
        d(g.a.f.a.m(c(), bArr));
        this.f5110c = 1L;
    }

    public final byte[] c() {
        byte[] entropy = this.f5111d.getEntropy();
        if (entropy.length >= (this.f5113f + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    public final void d(byte[] bArr) {
        e(bArr, (byte) 0);
        if (bArr != null) {
            e(bArr, (byte) 1);
        }
    }

    public final void e(byte[] bArr, byte b2) {
        this.f5112e.init(new s0(this.f5108a));
        t tVar = this.f5112e;
        byte[] bArr2 = this.f5109b;
        tVar.update(bArr2, 0, bArr2.length);
        this.f5112e.update(b2);
        if (bArr != null) {
            this.f5112e.update(bArr, 0, bArr.length);
        }
        this.f5112e.doFinal(this.f5108a, 0);
        this.f5112e.init(new s0(this.f5108a));
        t tVar2 = this.f5112e;
        byte[] bArr3 = this.f5109b;
        tVar2.update(bArr3, 0, bArr3.length);
        this.f5112e.doFinal(this.f5109b, 0);
    }
}
