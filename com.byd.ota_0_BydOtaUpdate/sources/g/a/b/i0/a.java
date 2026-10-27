package g.a.b.i0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public interface a {
    int a(byte[] bArr) throws InvalidCipherTextException;

    int b(byte[] bArr, int i2);

    void c(SecureRandom secureRandom) throws IllegalArgumentException;
}
