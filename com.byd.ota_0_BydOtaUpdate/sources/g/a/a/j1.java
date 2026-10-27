package g.a.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class j1 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final char[] f3878c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f3879d;

    public j1(byte[] bArr) {
        this.f3879d = g.a.f.a.f(bArr);
    }

    @Override // g.a.a.w
    public String d() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new p(byteArrayOutputStream).j(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i2 = 0; i2 != byteArray.length; i2++) {
                char[] cArr = f3878c;
                stringBuffer.append(cArr[(byteArray[i2] >>> 4) & 15]);
                stringBuffer.append(cArr[byteArray[i2] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException unused) {
            throw new ASN1ParsingException("internal error encoding UniversalString");
        }
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof j1) {
            return g.a.f.a.a(this.f3879d, ((j1) qVar).f3879d);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(28, n());
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3879d);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3879d.length) + 1 + this.f3879d.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public byte[] n() {
        return g.a.f.a.f(this.f3879d);
    }

    public String toString() {
        return d();
    }
}
