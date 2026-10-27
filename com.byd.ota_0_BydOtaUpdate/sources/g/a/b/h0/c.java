package g.a.b.h0;

import g.a.b.j0.w0;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class c implements g.a.b.h0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.e f4843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4844b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4845c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4846d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4848f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.i f4849g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4850h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f4851i = new a();
    public a j = new a();

    public class a extends ByteArrayOutputStream {
        public a() {
        }

        public byte[] c() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public c(g.a.b.e eVar) {
        this.f4843a = eVar;
        int iB = eVar.b();
        this.f4844b = iB;
        this.f4850h = new byte[iB];
        if (iB != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
    }

    @Override // g.a.b.h0.a
    public void a(byte[] bArr, int i2, int i3) {
        this.f4851i.write(bArr, i2, i3);
    }

    @Override // g.a.b.h0.a
    public byte[] b() {
        int i2 = this.f4848f;
        byte[] bArr = new byte[i2];
        System.arraycopy(this.f4850h, 0, bArr, 0, i2);
        return bArr;
    }

    public final int c(byte[] bArr, int i2, int i3, byte[] bArr2) {
        g.a.b.g0.a aVar = new g.a.b.g0.a(this.f4843a, this.f4848f * 8);
        aVar.init(this.f4849g);
        byte[] bArr3 = new byte[16];
        if (f()) {
            bArr3[0] = (byte) (bArr3[0] | 64);
        }
        int i4 = 2;
        bArr3[0] = (byte) (bArr3[0] | ((((aVar.getMacSize() - 2) / 2) & 7) << 3));
        byte b2 = bArr3[0];
        byte[] bArr4 = this.f4846d;
        bArr3[0] = (byte) (b2 | (((15 - bArr4.length) - 1) & 7));
        System.arraycopy(bArr4, 0, bArr3, 1, bArr4.length);
        int i5 = i3;
        int i6 = 1;
        while (i5 > 0) {
            bArr3[16 - i6] = (byte) (i5 & 255);
            i5 >>>= 8;
            i6++;
        }
        aVar.update(bArr3, 0, 16);
        if (f()) {
            int iE = e();
            if (iE < 65280) {
                aVar.update((byte) (iE >> 8));
                aVar.update((byte) iE);
            } else {
                aVar.update((byte) -1);
                aVar.update((byte) -2);
                aVar.update((byte) (iE >> 24));
                aVar.update((byte) (iE >> 16));
                aVar.update((byte) (iE >> 8));
                aVar.update((byte) iE);
                i4 = 6;
            }
            byte[] bArr5 = this.f4847e;
            if (bArr5 != null) {
                aVar.update(bArr5, 0, bArr5.length);
            }
            if (this.f4851i.size() > 0) {
                aVar.update(this.f4851i.c(), 0, this.f4851i.size());
            }
            int i7 = (i4 + iE) % 16;
            if (i7 != 0) {
                while (i7 != 16) {
                    aVar.update((byte) 0);
                    i7++;
                }
            }
        }
        aVar.update(bArr, i2, i3);
        return aVar.doFinal(bArr2, 0);
    }

    public String d() {
        return this.f4843a.getAlgorithmName() + "/CCM";
    }

    @Override // g.a.b.h0.a
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException {
        int iH = h(this.j.c(), 0, this.j.size(), bArr, i2);
        i();
        return iH;
    }

    public final int e() {
        int size = this.f4851i.size();
        byte[] bArr = this.f4847e;
        return size + (bArr == null ? 0 : bArr.length);
    }

    public final boolean f() {
        return e() > 0;
    }

    public void g(byte b2) {
        this.f4851i.write(b2);
    }

    @Override // g.a.b.h0.a
    public int getOutputSize(int i2) {
        int size = i2 + this.j.size();
        if (this.f4845c) {
            return size + this.f4848f;
        }
        int i3 = this.f4848f;
        if (size < i3) {
            return 0;
        }
        return size - i3;
    }

    @Override // g.a.b.h0.a
    public g.a.b.e getUnderlyingCipher() {
        return this.f4843a;
    }

    @Override // g.a.b.h0.a
    public int getUpdateOutputSize(int i2) {
        return 0;
    }

    public int h(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws InvalidCipherTextException, IllegalStateException, DataLengthException {
        int i5;
        if (this.f4849g == null) {
            throw new IllegalStateException("CCM cipher unitialized.");
        }
        byte[] bArr3 = this.f4846d;
        int length = 15 - bArr3.length;
        if (length < 4 && i3 >= (1 << (length * 8))) {
            throw new IllegalStateException("CCM packet too large for choice of q.");
        }
        byte[] bArr4 = new byte[this.f4844b];
        bArr4[0] = (byte) ((length - 1) & 7);
        System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
        v vVar = new v(this.f4843a);
        vVar.init(this.f4845c, new w0(this.f4849g, bArr4));
        if (!this.f4845c) {
            int i6 = this.f4848f;
            if (i3 < i6) {
                throw new InvalidCipherTextException("data too short");
            }
            int i7 = i3 - i6;
            if (bArr2.length < i7 + i4) {
                throw new OutputLengthException("Output buffer too short.");
            }
            int i8 = i2 + i7;
            System.arraycopy(bArr, i8, this.f4850h, 0, i6);
            byte[] bArr5 = this.f4850h;
            vVar.a(bArr5, 0, bArr5, 0);
            int i9 = this.f4848f;
            while (true) {
                byte[] bArr6 = this.f4850h;
                if (i9 == bArr6.length) {
                    break;
                }
                bArr6[i9] = 0;
                i9++;
            }
            int i10 = i2;
            int i11 = i4;
            while (true) {
                i5 = this.f4844b;
                if (i10 >= i8 - i5) {
                    break;
                }
                vVar.a(bArr, i10, bArr2, i11);
                int i12 = this.f4844b;
                i11 += i12;
                i10 += i12;
            }
            byte[] bArr7 = new byte[i5];
            int i13 = i7 - (i10 - i2);
            System.arraycopy(bArr, i10, bArr7, 0, i13);
            vVar.a(bArr7, 0, bArr7, 0);
            System.arraycopy(bArr7, 0, bArr2, i11, i13);
            byte[] bArr8 = new byte[this.f4844b];
            c(bArr2, i4, i7, bArr8);
            if (g.a.f.a.q(this.f4850h, bArr8)) {
                return i7;
            }
            throw new InvalidCipherTextException("mac check in CCM failed");
        }
        int i14 = this.f4848f + i3;
        if (bArr2.length < i14 + i4) {
            throw new OutputLengthException("Output buffer too short.");
        }
        c(bArr, i2, i3, this.f4850h);
        byte[] bArr9 = new byte[this.f4844b];
        vVar.a(this.f4850h, 0, bArr9, 0);
        int i15 = i2;
        int i16 = i4;
        while (true) {
            int i17 = i2 + i3;
            int i18 = this.f4844b;
            if (i15 >= i17 - i18) {
                byte[] bArr10 = new byte[i18];
                int i19 = i17 - i15;
                System.arraycopy(bArr, i15, bArr10, 0, i19);
                vVar.a(bArr10, 0, bArr10, 0);
                System.arraycopy(bArr10, 0, bArr2, i16, i19);
                System.arraycopy(bArr9, 0, bArr2, i4 + i3, this.f4848f);
                return i14;
            }
            vVar.a(bArr, i15, bArr2, i16);
            int i20 = this.f4844b;
            i16 += i20;
            i15 += i20;
        }
    }

    public void i() {
        this.f4843a.reset();
        this.f4851i.reset();
        this.j.reset();
    }

    @Override // g.a.b.h0.a
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.i iVarB;
        this.f4845c = z;
        if (iVar instanceof g.a.b.j0.a) {
            g.a.b.j0.a aVar = (g.a.b.j0.a) iVar;
            this.f4846d = aVar.d();
            this.f4847e = aVar.a();
            this.f4848f = aVar.c() / 8;
            iVarB = aVar.b();
        } else {
            if (!(iVar instanceof w0)) {
                throw new IllegalArgumentException("invalid parameters passed to CCM: " + iVar.getClass().getName());
            }
            w0 w0Var = (w0) iVar;
            this.f4846d = w0Var.a();
            this.f4847e = null;
            this.f4848f = this.f4850h.length / 2;
            iVarB = w0Var.b();
        }
        if (iVarB != null) {
            this.f4849g = iVarB;
        }
        byte[] bArr = this.f4846d;
        if (bArr == null || bArr.length < 7 || bArr.length > 13) {
            throw new IllegalArgumentException("nonce must have length from 7 to 13 octets");
        }
        i();
    }

    @Override // g.a.b.h0.a
    public int processByte(byte b2, byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        this.j.write(b2);
        return 0;
    }

    @Override // g.a.b.h0.a
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalStateException, DataLengthException {
        if (bArr.length < i2 + i3) {
            throw new DataLengthException("Input buffer too short");
        }
        this.j.write(bArr, i2, i3);
        return 0;
    }
}
