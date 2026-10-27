package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class g0 implements g.a.b.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4487a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4488b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4489c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4490d = null;

    public final void a(byte[] bArr) {
        this.f4490d = bArr;
        this.f4488b = 0;
        this.f4489c = 0;
        if (this.f4487a == null) {
            this.f4487a = new byte[256];
        }
        for (int i2 = 0; i2 < 256; i2++) {
            this.f4487a[i2] = (byte) i2;
        }
        int length = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            int i5 = bArr[length] & 255;
            byte[] bArr2 = this.f4487a;
            i3 = (i5 + bArr2[i4] + i3) & 255;
            byte b2 = bArr2[i4];
            bArr2[i4] = bArr2[i3];
            bArr2[i3] = b2;
            length = (length + 1) % bArr.length;
        }
    }

    @Override // g.a.b.w
    public String getAlgorithmName() {
        return "RC4";
    }

    @Override // g.a.b.w
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            byte[] bArrA = ((g.a.b.j0.s0) iVar).a();
            this.f4490d = bArrA;
            a(bArrA);
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + iVar.getClass().getName());
        }
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
            int i6 = (this.f4488b + 1) & 255;
            this.f4488b = i6;
            byte[] bArr3 = this.f4487a;
            int i7 = (bArr3[i6] + this.f4489c) & 255;
            this.f4489c = i7;
            byte b2 = bArr3[i6];
            bArr3[i6] = bArr3[i7];
            bArr3[i7] = b2;
            bArr2[i5 + i4] = (byte) (bArr3[(bArr3[i6] + bArr3[i7]) & 255] ^ bArr[i5 + i2]);
        }
        return i3;
    }

    @Override // g.a.b.w
    public void reset() {
        a(this.f4490d);
    }
}
