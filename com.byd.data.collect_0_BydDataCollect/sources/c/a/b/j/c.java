package c.a.b.j;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: compiled from: AES.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends f {
    private static final long serialVersionUID = 1;

    public c() {
        super(e.AES);
    }

    public c(c.a.b.e eVar, c.a.b.f fVar, byte[] bArr) {
        this(eVar, fVar, bArr, (byte[]) null);
    }

    public c(c.a.b.e eVar, c.a.b.f fVar, byte[] bArr, byte[] bArr2) {
        this(eVar.name(), fVar.name(), bArr, bArr2);
    }

    public c(String str, String str2, byte[] bArr, byte[] bArr2) {
        this(str, str2, c.a.b.d.f(e.AES.a(), bArr), c.a.a.g.g.a(bArr2) ? null : new IvParameterSpec(bArr2));
    }

    public c(String str, String str2, SecretKey secretKey, AlgorithmParameterSpec algorithmParameterSpec) {
        super(c.a.a.f.b.f("AES/{}/{}", str, str2), secretKey, algorithmParameterSpec);
    }
}
