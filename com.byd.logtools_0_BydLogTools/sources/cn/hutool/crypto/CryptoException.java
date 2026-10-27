package cn.hutool.crypto;

/* JADX INFO: loaded from: classes.dex */
public class CryptoException extends RuntimeException {
    private static final long serialVersionUID = 8068509879445395353L;

    public CryptoException(Throwable th) {
        super(cn.hutool.core.exceptions.a.a(th), th);
    }

    public CryptoException(String str) {
        super(str);
    }

    public CryptoException(String str, Object... objArr) {
        super(c.a.a.d.b.g(str, objArr));
    }

    public CryptoException(String str, Throwable th) {
        super(str, th);
    }

    public CryptoException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }

    public CryptoException(Throwable th, String str, Object... objArr) {
        super(c.a.a.d.b.g(str, objArr), th);
    }
}
