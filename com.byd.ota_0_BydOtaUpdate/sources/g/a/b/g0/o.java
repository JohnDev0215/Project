package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.t;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class o implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f4826a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte f4827b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4828c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte f4829d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4830e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4831f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4832g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte f4833h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte f4834i;
    public byte j;
    public byte k;

    public final void a(byte[] bArr, byte[] bArr2) {
        this.f4829d = (byte) 0;
        this.f4828c = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            this.f4828c[i2] = (byte) i2;
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr3 = this.f4828c;
            int i4 = i3 & 255;
            byte b2 = bArr3[(this.f4829d + bArr3[i4] + bArr[i3 % bArr.length]) & 255];
            this.f4829d = b2;
            byte b3 = bArr3[i4];
            bArr3[i4] = bArr3[b2 & 255];
            bArr3[b2 & 255] = b3;
        }
        for (int i5 = 0; i5 < 768; i5++) {
            byte[] bArr4 = this.f4828c;
            int i6 = i5 & 255;
            byte b4 = bArr4[(this.f4829d + bArr4[i6] + bArr2[i5 % bArr2.length]) & 255];
            this.f4829d = b4;
            byte b5 = bArr4[i6];
            bArr4[i6] = bArr4[b4 & 255];
            bArr4[b4 & 255] = b5;
        }
        this.f4827b = (byte) 0;
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        for (int i3 = 1; i3 < 25; i3++) {
            byte[] bArr2 = this.f4828c;
            byte b2 = this.f4829d;
            byte b3 = this.f4827b;
            byte b4 = bArr2[(b2 + bArr2[b3 & 255]) & 255];
            this.f4829d = b4;
            byte b5 = this.k;
            byte b6 = this.j;
            byte b7 = bArr2[(b5 + b6 + i3) & 255];
            this.k = b7;
            byte b8 = this.f4834i;
            byte b9 = bArr2[(b6 + b8 + i3) & 255];
            this.j = b9;
            byte b10 = this.f4833h;
            byte b11 = bArr2[(b8 + b10 + i3) & 255];
            this.f4834i = b11;
            byte b12 = bArr2[(b10 + b4 + i3) & 255];
            this.f4833h = b12;
            byte[] bArr3 = this.f4830e;
            byte b13 = this.f4826a;
            bArr3[b13 & 31] = (byte) (b12 ^ bArr3[b13 & 31]);
            bArr3[(b13 + 1) & 31] = (byte) (b11 ^ bArr3[(b13 + 1) & 31]);
            bArr3[(b13 + 2) & 31] = (byte) (b9 ^ bArr3[(b13 + 2) & 31]);
            bArr3[(b13 + 3) & 31] = (byte) (b7 ^ bArr3[(b13 + 3) & 31]);
            this.f4826a = (byte) ((b13 + 4) & 31);
            byte b14 = bArr2[b3 & 255];
            bArr2[b3 & 255] = bArr2[b4 & 255];
            bArr2[b4 & 255] = b14;
            this.f4827b = (byte) ((b3 + 1) & 255);
        }
        for (int i4 = 0; i4 < 768; i4++) {
            byte[] bArr4 = this.f4828c;
            int i5 = i4 & 255;
            byte b15 = bArr4[(this.f4829d + bArr4[i5] + this.f4830e[i4 & 31]) & 255];
            this.f4829d = b15;
            byte b16 = bArr4[i5];
            bArr4[i5] = bArr4[b15 & 255];
            bArr4[b15 & 255] = b16;
        }
        byte[] bArr5 = new byte[20];
        for (int i6 = 0; i6 < 20; i6++) {
            byte[] bArr6 = this.f4828c;
            int i7 = i6 & 255;
            byte b17 = bArr6[(this.f4829d + bArr6[i7]) & 255];
            this.f4829d = b17;
            bArr5[i6] = bArr6[(bArr6[bArr6[b17 & 255] & 255] + 1) & 255];
            byte b18 = bArr6[i7];
            bArr6[i7] = bArr6[b17 & 255];
            bArr6[b17 & 255] = b18;
        }
        System.arraycopy(bArr5, 0, bArr, i2, 20);
        reset();
        return 20;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return 20;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof w0)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        w0 w0Var = (w0) iVar;
        s0 s0Var = (s0) w0Var.b();
        if (!(w0Var.b() instanceof s0)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        byte[] bArrA = w0Var.a();
        this.f4831f = bArrA;
        if (bArrA == null || bArrA.length < 1 || bArrA.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.f4832g = s0Var.a();
        reset();
    }

    @Override // g.a.b.t
    public void reset() {
        a(this.f4832g, this.f4831f);
        this.f4827b = (byte) 0;
        this.k = (byte) 0;
        this.j = (byte) 0;
        this.f4834i = (byte) 0;
        this.f4833h = (byte) 0;
        this.f4826a = (byte) 0;
        this.f4830e = new byte[32];
        for (int i2 = 0; i2 < 32; i2++) {
            this.f4830e[i2] = 0;
        }
    }

    @Override // g.a.b.t
    public void update(byte b2) throws IllegalStateException {
        byte[] bArr = this.f4828c;
        byte b3 = this.f4829d;
        byte b4 = this.f4827b;
        byte b5 = bArr[(b3 + bArr[b4 & 255]) & 255];
        this.f4829d = b5;
        byte b6 = (byte) (b2 ^ bArr[(bArr[bArr[b5 & 255] & 255] + 1) & 255]);
        byte b7 = this.k;
        byte b8 = this.j;
        byte b9 = bArr[(b7 + b8) & 255];
        this.k = b9;
        byte b10 = this.f4834i;
        byte b11 = bArr[(b8 + b10) & 255];
        this.j = b11;
        byte b12 = this.f4833h;
        byte b13 = bArr[(b10 + b12) & 255];
        this.f4834i = b13;
        byte b14 = bArr[(b12 + b5 + b6) & 255];
        this.f4833h = b14;
        byte[] bArr2 = this.f4830e;
        byte b15 = this.f4826a;
        bArr2[b15 & 31] = (byte) (b14 ^ bArr2[b15 & 31]);
        bArr2[(b15 + 1) & 31] = (byte) (b13 ^ bArr2[(b15 + 1) & 31]);
        bArr2[(b15 + 2) & 31] = (byte) (b11 ^ bArr2[(b15 + 2) & 31]);
        bArr2[(b15 + 3) & 31] = (byte) (b9 ^ bArr2[(b15 + 3) & 31]);
        this.f4826a = (byte) ((b15 + 4) & 31);
        byte b16 = bArr[b4 & 255];
        bArr[b4 & 255] = bArr[b5 & 255];
        bArr[b5 & 255] = b16;
        this.f4827b = (byte) ((b4 + 1) & 255);
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException {
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i4 = 0; i4 < i3; i4++) {
            update(bArr[i2 + i4]);
        }
    }
}
