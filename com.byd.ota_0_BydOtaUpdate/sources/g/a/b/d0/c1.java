package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class c1 implements g.a.b.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f4443a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4444b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte f4445c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4446d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4447e;

    public void a(byte[] bArr, byte[] bArr2) {
        this.f4445c = (byte) 0;
        this.f4444b = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            this.f4444b[i2] = (byte) i2;
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr3 = this.f4444b;
            int i4 = i3 & 255;
            byte b2 = bArr3[(this.f4445c + bArr3[i4] + bArr[i3 % bArr.length]) & 255];
            this.f4445c = b2;
            byte b3 = bArr3[i4];
            bArr3[i4] = bArr3[b2 & 255];
            bArr3[b2 & 255] = b3;
        }
        for (int i5 = 0; i5 < 768; i5++) {
            byte[] bArr4 = this.f4444b;
            int i6 = i5 & 255;
            byte b4 = bArr4[(this.f4445c + bArr4[i6] + bArr2[i5 % bArr2.length]) & 255];
            this.f4445c = b4;
            byte b5 = bArr4[i6];
            bArr4[i6] = bArr4[b4 & 255];
            bArr4[b4 & 255] = b5;
        }
        this.f4443a = (byte) 0;
    }

    @Override // g.a.b.w
    public String getAlgorithmName() {
        return "VMPC";
    }

    @Override // g.a.b.w
    public void init(boolean z, g.a.b.i iVar) {
        if (!(iVar instanceof g.a.b.j0.w0)) {
            throw new IllegalArgumentException("VMPC init parameters must include an IV");
        }
        g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
        if (!(w0Var.b() instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        }
        g.a.b.j0.s0 s0Var = (g.a.b.j0.s0) w0Var.b();
        byte[] bArrA = w0Var.a();
        this.f4446d = bArrA;
        if (bArrA == null || bArrA.length < 1 || bArrA.length > 768) {
            throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
        }
        byte[] bArrA2 = s0Var.a();
        this.f4447e = bArrA2;
        a(bArrA2, this.f4446d);
    }

    @Override // g.a.b.w
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            byte[] bArr3 = this.f4444b;
            byte b2 = this.f4445c;
            byte b3 = this.f4443a;
            byte b4 = bArr3[(b2 + bArr3[b3 & 255]) & 255];
            this.f4445c = b4;
            byte b5 = bArr3[(bArr3[bArr3[b4 & 255] & 255] + 1) & 255];
            byte b6 = bArr3[b3 & 255];
            bArr3[b3 & 255] = bArr3[b4 & 255];
            bArr3[b4 & 255] = b6;
            this.f4443a = (byte) ((b3 + 1) & 255);
            bArr2[i5 + i4] = (byte) (bArr[i5 + i2] ^ b5);
        }
        return i3;
    }

    @Override // g.a.b.w
    public void reset() {
        a(this.f4447e, this.f4446d);
    }
}
