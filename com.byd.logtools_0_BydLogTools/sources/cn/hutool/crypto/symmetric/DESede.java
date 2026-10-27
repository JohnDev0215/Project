package cn.hutool.crypto.symmetric;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class DESede extends SymmetricCrypto {
    private static final long serialVersionUID = 1;

    public DESede() {
        super(SymmetricAlgorithm.DESede);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ byte[] decrypt(InputStream inputStream) throws IORuntimeException {
        return super.decrypt(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ byte[] decrypt(String str) {
        return super.decrypt(str);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ String decryptStr(InputStream inputStream) {
        return super.decryptStr(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ String decryptStr(InputStream inputStream, Charset charset) {
        return super.decryptStr(inputStream, charset);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ String decryptStr(String str) {
        return super.decryptStr(str);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ String decryptStr(String str, Charset charset) {
        return super.decryptStr(str, charset);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ String decryptStr(byte[] bArr) {
        return super.decryptStr(bArr);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.c
    public /* bridge */ /* synthetic */ String decryptStr(byte[] bArr, Charset charset) {
        return super.decryptStr(bArr, charset);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(InputStream inputStream) throws IORuntimeException {
        return super.encrypt(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(String str) {
        return super.encrypt(str);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(String str, String str2) {
        return super.encrypt(str, str2);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(String str, Charset charset) {
        return super.encrypt(str, charset);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(InputStream inputStream) {
        return super.encryptBase64(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(String str) {
        return super.encryptBase64(str);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(String str, String str2) {
        return super.encryptBase64(str, str2);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(String str, Charset charset) {
        return super.encryptBase64(str, charset);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(byte[] bArr) {
        return super.encryptBase64(bArr);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(InputStream inputStream) {
        return super.encryptHex(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(String str) {
        return super.encryptHex(str);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(String str, String str2) {
        return super.encryptHex(str, str2);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(String str, Charset charset) {
        return super.encryptHex(str, charset);
    }

    @Override // cn.hutool.crypto.symmetric.SymmetricCrypto, cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(byte[] bArr) {
        return super.encryptHex(bArr);
    }

    public DESede(byte[] bArr) {
        super(SymmetricAlgorithm.DESede, bArr);
    }

    public DESede(Mode mode, Padding padding) {
        this(mode.name(), padding.name());
    }

    public DESede(Mode mode, Padding padding, byte[] bArr) {
        this(mode, padding, bArr, (byte[]) null);
    }

    public DESede(Mode mode, Padding padding, byte[] bArr, byte[] bArr2) {
        this(mode.name(), padding.name(), bArr, bArr2);
    }

    public DESede(Mode mode, Padding padding, SecretKey secretKey) {
        this(mode, padding, secretKey, (IvParameterSpec) null);
    }

    public DESede(Mode mode, Padding padding, SecretKey secretKey, IvParameterSpec ivParameterSpec) {
        this(mode.name(), padding.name(), secretKey, ivParameterSpec);
    }

    public DESede(String str, String str2) {
        this(str, str2, (byte[]) null);
    }

    public DESede(String str, String str2, byte[] bArr) {
        this(str, str2, bArr, (byte[]) null);
    }

    public DESede(String str, String str2, byte[] bArr, byte[] bArr2) {
        this(str, str2, cn.hutool.crypto.d.e(SymmetricAlgorithm.DESede.getValue(), bArr), bArr2 == null ? null : new IvParameterSpec(bArr2));
    }

    public DESede(String str, String str2, SecretKey secretKey) {
        this(str, str2, secretKey, (IvParameterSpec) null);
    }

    public DESede(String str, String str2, SecretKey secretKey, IvParameterSpec ivParameterSpec) {
        super(c.a.a.d.b.g("{}/{}/{}", SymmetricAlgorithm.DESede.getValue(), str, str2), secretKey, ivParameterSpec);
    }
}
