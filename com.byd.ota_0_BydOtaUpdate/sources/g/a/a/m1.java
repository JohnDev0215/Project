package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class m1 extends b {
    public m1(byte[] bArr, int i2) {
        super(bArr, i2);
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        byte[] bArr = this.f3768d;
        int length = bArr.length + 1;
        byte[] bArr2 = new byte[length];
        bArr2[0] = (byte) r();
        System.arraycopy(bArr, 0, bArr2, 1, length - 1);
        pVar.g(3, bArr2);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3768d.length + 1) + 1 + this.f3768d.length + 1;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }
}
