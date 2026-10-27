package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.t;

/* JADX INFO: loaded from: classes.dex */
public class k implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.n f4803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4804b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4805c = new byte[64];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4806d = new byte[64];

    public k(g.a.b.n nVar) {
        this.f4803a = nVar;
        this.f4804b = nVar.getDigestSize();
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) {
        int i3 = this.f4804b;
        byte[] bArr2 = new byte[i3];
        this.f4803a.doFinal(bArr2, 0);
        g.a.b.n nVar = this.f4803a;
        byte[] bArr3 = this.f4806d;
        nVar.update(bArr3, 0, bArr3.length);
        this.f4803a.update(bArr2, 0, i3);
        int iDoFinal = this.f4803a.doFinal(bArr, i2);
        reset();
        return iDoFinal;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return this.f4803a.getAlgorithmName() + "/HMAC";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4804b;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) {
        this.f4803a.reset();
        byte[] bArrA = ((s0) iVar).a();
        if (bArrA.length <= 64) {
            System.arraycopy(bArrA, 0, this.f4805c, 0, bArrA.length);
            int length = bArrA.length;
            while (true) {
                byte[] bArr = this.f4805c;
                if (length >= bArr.length) {
                    break;
                }
                bArr[length] = 0;
                length++;
            }
        } else {
            this.f4803a.update(bArrA, 0, bArrA.length);
            this.f4803a.doFinal(this.f4805c, 0);
            int i2 = this.f4804b;
            while (true) {
                byte[] bArr2 = this.f4805c;
                if (i2 >= bArr2.length) {
                    break;
                }
                bArr2[i2] = 0;
                i2++;
            }
        }
        byte[] bArr3 = this.f4805c;
        byte[] bArr4 = new byte[bArr3.length];
        this.f4806d = bArr4;
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        int i3 = 0;
        while (true) {
            byte[] bArr5 = this.f4805c;
            if (i3 >= bArr5.length) {
                break;
            }
            bArr5[i3] = (byte) (bArr5[i3] ^ 54);
            i3++;
        }
        int i4 = 0;
        while (true) {
            byte[] bArr6 = this.f4806d;
            if (i4 >= bArr6.length) {
                g.a.b.n nVar = this.f4803a;
                byte[] bArr7 = this.f4805c;
                nVar.update(bArr7, 0, bArr7.length);
                return;
            }
            bArr6[i4] = (byte) (bArr6[i4] ^ 92);
            i4++;
        }
    }

    @Override // g.a.b.t
    public void reset() {
        this.f4803a.reset();
        g.a.b.n nVar = this.f4803a;
        byte[] bArr = this.f4805c;
        nVar.update(bArr, 0, bArr.length);
    }

    @Override // g.a.b.t
    public void update(byte b2) {
        this.f4803a.update(b2);
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) {
        this.f4803a.update(bArr, i2, i3);
    }
}
