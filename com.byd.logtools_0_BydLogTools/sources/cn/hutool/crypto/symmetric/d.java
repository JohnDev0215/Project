package cn.hutool.crypto.symmetric;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.e;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: SymmetricEncryptor.java */
/* JADX INFO: loaded from: classes.dex */
public interface d {
    default byte[] encrypt(String str, String str2) {
        return encrypt(c.a.a.d.b.a(str, str2));
    }

    byte[] encrypt(byte[] bArr);

    default String encryptBase64(byte[] bArr) {
        return c.a.a.a.b.b(encrypt(bArr));
    }

    default String encryptHex(byte[] bArr) {
        return cn.hutool.core.util.d.d(encrypt(bArr));
    }

    default byte[] encrypt(String str, Charset charset) {
        return encrypt(c.a.a.d.b.b(str, charset));
    }

    default String encryptBase64(String str, String str2) {
        return c.a.a.a.b.b(encrypt(str, str2));
    }

    default String encryptHex(String str, String str2) {
        return cn.hutool.core.util.d.d(encrypt(str, str2));
    }

    default byte[] encrypt(String str) {
        return encrypt(c.a.a.d.b.b(str, cn.hutool.core.util.c.f2949b));
    }

    default String encryptBase64(String str, Charset charset) {
        return c.a.a.a.b.b(encrypt(str, charset));
    }

    default String encryptHex(String str, Charset charset) {
        return cn.hutool.core.util.d.d(encrypt(str, charset));
    }

    default byte[] encrypt(InputStream inputStream) throws IORuntimeException {
        return encrypt(e.g(inputStream));
    }

    default String encryptBase64(String str) {
        return c.a.a.a.b.b(encrypt(str));
    }

    default String encryptHex(String str) {
        return cn.hutool.core.util.d.d(encrypt(str));
    }

    default String encryptBase64(InputStream inputStream) {
        return c.a.a.a.b.b(encrypt(inputStream));
    }

    default String encryptHex(InputStream inputStream) {
        return cn.hutool.core.util.d.d(encrypt(inputStream));
    }
}
