package cn.hutool.crypto.symmetric;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.e;
import cn.hutool.core.util.j;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: SymmetricDecryptor.java */
/* JADX INFO: loaded from: classes.dex */
public interface c {
    default byte[] decrypt(String str) {
        return decrypt(cn.hutool.crypto.d.c(str));
    }

    byte[] decrypt(byte[] bArr);

    default String decryptStr(byte[] bArr, Charset charset) {
        return j.u(decrypt(bArr), charset);
    }

    default byte[] decrypt(InputStream inputStream) throws IORuntimeException {
        return decrypt(e.g(inputStream));
    }

    default String decryptStr(byte[] bArr) {
        return decryptStr(bArr, cn.hutool.core.util.c.f2949b);
    }

    default String decryptStr(String str, Charset charset) {
        return j.u(decrypt(str), charset);
    }

    default String decryptStr(String str) {
        return decryptStr(str, cn.hutool.core.util.c.f2949b);
    }

    default String decryptStr(InputStream inputStream, Charset charset) {
        return j.u(decrypt(inputStream), charset);
    }

    default String decryptStr(InputStream inputStream) {
        return decryptStr(inputStream, cn.hutool.core.util.c.f2949b);
    }
}
