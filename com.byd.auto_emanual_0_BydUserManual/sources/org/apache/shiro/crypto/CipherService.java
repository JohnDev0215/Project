package org.apache.shiro.crypto;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public interface CipherService {
    ByteSource decrypt(byte[] bArr, byte[] bArr2) throws CryptoException;

    void decrypt(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws CryptoException;

    ByteSource encrypt(byte[] bArr, byte[] bArr2) throws CryptoException;

    void encrypt(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws CryptoException;
}
