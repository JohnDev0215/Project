package c.a.b.j;

import c.a.a.g.k;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: SymmetricDecryptor.java */
/* JADX INFO: loaded from: classes.dex */
public interface g {
    default byte[] b(String str) {
        return g(c.a.b.h.c(str));
    }

    default String e(String str) {
        return h(str, c.a.a.g.c.f3076b);
    }

    byte[] g(byte[] bArr);

    default String h(String str, Charset charset) {
        return k.t(b(str), charset);
    }
}
