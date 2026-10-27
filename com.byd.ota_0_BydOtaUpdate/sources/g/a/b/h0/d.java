package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class d extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4854c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4855d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4856e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4857f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.e f4858g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4859h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4860i;

    public d(g.a.b.e eVar, int i2) {
        super(eVar);
        this.f4858g = null;
        this.f4858g = eVar;
        this.f4857f = i2 / 8;
        this.f4853b = new byte[eVar.b()];
        this.f4854c = new byte[eVar.b()];
        this.f4855d = new byte[eVar.b()];
        this.f4856e = new byte[this.f4857f];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, this.f4857f, bArr2, i3);
        return this.f4857f;
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4857f;
    }

    @Override // g.a.b.v
    public byte c(byte b2) throws IllegalStateException, DataLengthException {
        return this.f4859h ? f(b2) : e(b2);
    }

    public final byte e(byte b2) {
        if (this.f4860i == 0) {
            this.f4858g.a(this.f4854c, 0, this.f4855d, 0);
        }
        byte[] bArr = this.f4856e;
        int i2 = this.f4860i;
        bArr[i2] = b2;
        byte[] bArr2 = this.f4855d;
        int i3 = i2 + 1;
        this.f4860i = i3;
        byte b3 = (byte) (b2 ^ bArr2[i2]);
        int i4 = this.f4857f;
        if (i3 == i4) {
            this.f4860i = 0;
            byte[] bArr3 = this.f4854c;
            System.arraycopy(bArr3, i4, bArr3, 0, bArr3.length - i4);
            byte[] bArr4 = this.f4856e;
            byte[] bArr5 = this.f4854c;
            int length = bArr5.length;
            int i5 = this.f4857f;
            System.arraycopy(bArr4, 0, bArr5, length - i5, i5);
        }
        return b3;
    }

    public final byte f(byte b2) {
        if (this.f4860i == 0) {
            this.f4858g.a(this.f4854c, 0, this.f4855d, 0);
        }
        byte[] bArr = this.f4855d;
        int i2 = this.f4860i;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        byte[] bArr2 = this.f4856e;
        int i3 = i2 + 1;
        this.f4860i = i3;
        bArr2[i2] = b3;
        int i4 = this.f4857f;
        if (i3 == i4) {
            this.f4860i = 0;
            byte[] bArr3 = this.f4854c;
            System.arraycopy(bArr3, i4, bArr3, 0, bArr3.length - i4);
            byte[] bArr4 = this.f4856e;
            byte[] bArr5 = this.f4854c;
            int length = bArr5.length;
            int i5 = this.f4857f;
            System.arraycopy(bArr4, 0, bArr5, length - i5, i5);
        }
        return b3;
    }

    public byte[] g() {
        return g.a.f.a.f(this.f4854c);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4858g.getAlgorithmName() + "/CFB" + (this.f4857f * 8);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        this.f4859h = z;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            int length = bArrA.length;
            byte[] bArr = this.f4853b;
            if (length < bArr.length) {
                System.arraycopy(bArrA, 0, bArr, bArr.length - bArrA.length, bArrA.length);
                int i2 = 0;
                while (true) {
                    byte[] bArr2 = this.f4853b;
                    if (i2 >= bArr2.length - bArrA.length) {
                        break;
                    }
                    bArr2[i2] = 0;
                    i2++;
                }
            } else {
                System.arraycopy(bArrA, 0, bArr, 0, bArr.length);
            }
            reset();
            if (w0Var.b() == null) {
                return;
            }
            eVar = this.f4858g;
            iVar = w0Var.b();
        } else {
            reset();
            if (iVar == null) {
                return;
            } else {
                eVar = this.f4858g;
            }
        }
        eVar.init(true, iVar);
    }

    @Override // g.a.b.e
    public void reset() {
        byte[] bArr = this.f4853b;
        System.arraycopy(bArr, 0, this.f4854c, 0, bArr.length);
        g.a.f.a.u(this.f4856e, (byte) 0);
        this.f4860i = 0;
        this.f4858g.reset();
    }
}
