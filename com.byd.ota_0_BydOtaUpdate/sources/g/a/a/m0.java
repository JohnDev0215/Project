package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class m0 extends a {
    public m0(boolean z, int i2, byte[] bArr) {
        super(z, i2, bArr);
    }

    @Override // g.a.a.a, g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.f(this.f3735c ? 96 : 64, this.f3736d, this.s);
    }

    public String toString() {
        String strD;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        if (k()) {
            stringBuffer.append("CONSTRUCTED ");
        }
        stringBuffer.append("APPLICATION ");
        stringBuffer.append(Integer.toString(n()));
        stringBuffer.append("]");
        if (this.s != null) {
            stringBuffer.append(" #");
            strD = g.a.f.j.d.d(this.s);
        } else {
            strD = " #null";
        }
        stringBuffer.append(strD);
        stringBuffer.append(" ");
        return stringBuffer.toString();
    }
}
