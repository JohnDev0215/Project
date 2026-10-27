package g.a.b;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public abstract class v implements e, w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f5198a;

    public v(e eVar) {
        this.f5198a = eVar;
    }

    public abstract byte c(byte b2);

    public e d() {
        return this.f5198a;
    }

    @Override // g.a.b.w
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException {
        int i5 = i2 + i3;
        if (i5 > bArr.length) {
            throw new DataLengthException("input buffer too small");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        while (i2 < i5) {
            bArr2[i4] = c(bArr[i2]);
            i4++;
            i2++;
        }
        return i3;
    }
}
