package g.a.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class l implements e {
    @Override // g.a.a.e
    public abstract q c();

    public byte[] e() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new p(byteArrayOutputStream).j(this);
        return byteArrayOutputStream.toByteArray();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            return c().equals(((e) obj).c());
        }
        return false;
    }

    public byte[] f(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (str.equals("DER")) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            new a1(byteArrayOutputStream).j(this);
        } else {
            if (!str.equals("DL")) {
                return e();
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            new n1(byteArrayOutputStream).j(this);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public int hashCode() {
        return c().hashCode();
    }
}
