package c.a.b;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;

/* JADX INFO: compiled from: CipherWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Cipher f3084a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AlgorithmParameterSpec f3085b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SecureRandom f3086c;

    public a(String str) {
        this(h.a(str));
    }

    public Cipher a() {
        return this.f3084a;
    }

    public a b(int i2, Key key) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = this.f3084a;
        AlgorithmParameterSpec algorithmParameterSpec = this.f3085b;
        SecureRandom secureRandom = this.f3086c;
        if (algorithmParameterSpec != null) {
            if (secureRandom != null) {
                cipher.init(i2, key, algorithmParameterSpec, secureRandom);
            } else {
                cipher.init(i2, key, algorithmParameterSpec);
            }
        } else if (secureRandom != null) {
            cipher.init(i2, key, secureRandom);
        } else {
            cipher.init(i2, key);
        }
        return this;
    }

    public a c(AlgorithmParameterSpec algorithmParameterSpec) {
        this.f3085b = algorithmParameterSpec;
        return this;
    }

    public a(Cipher cipher) {
        this.f3084a = cipher;
    }
}
