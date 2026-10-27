package g.a.a;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class u1 implements Enumeration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f4075a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f4076b = a();

    public u1(byte[] bArr) {
        this.f4075a = new i(bArr, true);
    }

    public final Object a() {
        try {
            return this.f4075a.M();
        } catch (IOException e2) {
            throw new ASN1ParsingException("malformed DER construction: " + e2, e2);
        }
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.f4076b != null;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        Object obj = this.f4076b;
        this.f4076b = a();
        return obj;
    }
}
