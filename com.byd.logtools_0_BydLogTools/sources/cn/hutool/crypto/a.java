package cn.hutool.crypto;

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
    private final Cipher f2958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AlgorithmParameterSpec f2959b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SecureRandom f2960c;

    public a(String str) {
        this(d.a(str));
    }

    public Cipher a() {
        return this.f2958a;
    }

    public a b(int i, Key key) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = this.f2958a;
        AlgorithmParameterSpec algorithmParameterSpec = this.f2959b;
        SecureRandom secureRandom = this.f2960c;
        if (algorithmParameterSpec != null) {
            if (secureRandom != null) {
                cipher.init(i, key, algorithmParameterSpec, secureRandom);
            } else {
                cipher.init(i, key, algorithmParameterSpec);
            }
        } else if (secureRandom != null) {
            cipher.init(i, key, secureRandom);
        } else {
            cipher.init(i, key);
        }
        return this;
    }

    public a c(AlgorithmParameterSpec algorithmParameterSpec) {
        this.f2959b = algorithmParameterSpec;
        return this;
    }

    public a d(SecureRandom secureRandom) {
        this.f2960c = secureRandom;
        return this;
    }

    public a(Cipher cipher) {
        this.f2958a = cipher;
    }
}
