package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.t;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class d implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.a0.c f4764a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4765b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4766c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4767d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f4768e;

    public d(int i2) {
        this.f4764a = new g.a.b.a0.c(i2);
        this.f4765b = i2 / 8;
    }

    public final void a() {
        int byteLength = this.f4764a.getByteLength() - ((int) (this.f4768e % ((long) this.f4764a.getByteLength())));
        if (byteLength < 13) {
            byteLength += this.f4764a.getByteLength();
        }
        byte[] bArr = new byte[byteLength];
        bArr[0] = -128;
        g.a.f.f.t(this.f4768e * 8, bArr, byteLength - 12);
        this.f4764a.update(bArr, 0, byteLength);
    }

    public final byte[] b(byte[] bArr) {
        int length = (((bArr.length + this.f4764a.getByteLength()) - 1) / this.f4764a.getByteLength()) * this.f4764a.getByteLength();
        if (this.f4764a.getByteLength() - (bArr.length % this.f4764a.getByteLength()) < 13) {
            length += this.f4764a.getByteLength();
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = -128;
        g.a.f.f.g(bArr.length * 8, bArr2, length - 12);
        return bArr2;
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        if (this.f4766c == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (bArr.length - i2 < this.f4765b) {
            throw new OutputLengthException("Output buffer too short");
        }
        a();
        g.a.b.a0.c cVar = this.f4764a;
        byte[] bArr2 = this.f4767d;
        cVar.update(bArr2, 0, bArr2.length);
        this.f4768e = 0L;
        return this.f4764a.doFinal(bArr, i2);
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return "DSTU7564Mac";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4765b;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof s0)) {
            throw new IllegalArgumentException("Bad parameter passed");
        }
        byte[] bArrA = ((s0) iVar).a();
        this.f4767d = new byte[bArrA.length];
        this.f4766c = b(bArrA);
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4767d;
            if (i2 >= bArr.length) {
                g.a.b.a0.c cVar = this.f4764a;
                byte[] bArr2 = this.f4766c;
                cVar.update(bArr2, 0, bArr2.length);
                return;
            }
            bArr[i2] = (byte) (~bArrA[i2]);
            i2++;
        }
    }

    @Override // g.a.b.t
    public void reset() {
        this.f4768e = 0L;
        this.f4764a.reset();
        byte[] bArr = this.f4766c;
        if (bArr != null) {
            this.f4764a.update(bArr, 0, bArr.length);
        }
    }

    @Override // g.a.b.t
    public void update(byte b2) throws IllegalStateException {
        this.f4764a.update(b2);
        this.f4768e++;
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException {
        if (bArr.length - i2 < i3) {
            throw new DataLengthException("Input buffer too short");
        }
        if (this.f4766c != null) {
            this.f4764a.update(bArr, i2, i3);
            this.f4768e += (long) i3;
        } else {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
    }
}
