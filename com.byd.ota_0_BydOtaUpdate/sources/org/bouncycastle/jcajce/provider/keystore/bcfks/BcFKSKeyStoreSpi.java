package org.bouncycastle.jcajce.provider.keystore.bcfks;

import g.a.a.a2.c;
import g.a.a.a2.e;
import g.a.a.m;
import g.a.a.o2.b;
import g.a.a.p2.f;
import g.a.a.p2.h;
import g.a.a.p2.k;
import g.a.a.p2.l;
import g.a.a.p2.n;
import g.a.a.p2.p;
import g.a.a.w0;
import g.a.a.w2.a;
import g.a.a.w2.a0;
import g.a.a.w2.g;
import g.a.b.a0.w;
import g.a.b.a0.x;
import g.a.b.e0.v;
import g.a.b.e0.y;
import g.a.b.j;
import g.a.b.j0.s0;
import g.a.b.n0.d;
import g.a.b.u;
import g.a.f.i;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.bouncycastle.jcajce.BCFKSStoreParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class BcFKSKeyStoreSpi extends KeyStoreSpi {
    private static final BigInteger CERTIFICATE;
    private static final BigInteger PRIVATE_KEY;
    private static final BigInteger PROTECTED_PRIVATE_KEY;
    private static final BigInteger PROTECTED_SECRET_KEY;
    private static final BigInteger SECRET_KEY;
    private static final Map<String, m> oidMap;
    private static final Map<m, String> publicAlgMap;
    private Date creationDate;
    private a hmacAlgorithm;
    private h hmacPkbdAlgorithm;
    private Date lastModifiedDate;
    private final BouncyCastleProvider provider;
    private final Map<String, e> entries = new HashMap();
    private final Map<String, PrivateKey> privateKeyCache = new HashMap();

    public static class Def extends BcFKSKeyStoreSpi {
        public Def() {
            super(null);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Enumeration engineAliases() {
            return super.engineAliases();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineContainsAlias(String str) {
            return super.engineContainsAlias(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineDeleteEntry(String str) throws KeyStoreException {
            super.engineDeleteEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate engineGetCertificate(String str) {
            return super.engineGetCertificate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ String engineGetCertificateAlias(Certificate certificate) {
            return super.engineGetCertificateAlias(certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate[] engineGetCertificateChain(String str) {
            return super.engineGetCertificateChain(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Date engineGetCreationDate(String str) {
            return super.engineGetCreationDate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            return super.engineGetKey(str, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsCertificateEntry(String str) {
            return super.engineIsCertificateEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsKeyEntry(String str) {
            return super.engineIsKeyEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineLoad(InputStream inputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineLoad(inputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
            super.engineSetCertificateEntry(str, certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, key, cArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, bArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ int engineSize() {
            return super.engineSize();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(OutputStream outputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(outputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(loadStoreParameter);
        }
    }

    public static class DefShared extends SharedKeyStoreSpi {
        public DefShared() {
            super(null);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Enumeration engineAliases() {
            return super.engineAliases();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineContainsAlias(String str) {
            return super.engineContainsAlias(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi.SharedKeyStoreSpi, org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineDeleteEntry(String str) throws KeyStoreException {
            super.engineDeleteEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate engineGetCertificate(String str) {
            return super.engineGetCertificate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ String engineGetCertificateAlias(Certificate certificate) {
            return super.engineGetCertificateAlias(certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate[] engineGetCertificateChain(String str) {
            return super.engineGetCertificateChain(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Date engineGetCreationDate(String str) {
            return super.engineGetCreationDate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi.SharedKeyStoreSpi, org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            return super.engineGetKey(str, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsCertificateEntry(String str) {
            return super.engineIsCertificateEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsKeyEntry(String str) {
            return super.engineIsKeyEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineLoad(InputStream inputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineLoad(inputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
            super.engineSetCertificateEntry(str, certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, key, cArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, bArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ int engineSize() {
            return super.engineSize();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(OutputStream outputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(outputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(loadStoreParameter);
        }
    }

    public static class ExtKeyStoreException extends KeyStoreException {
        private final Throwable cause;

        public ExtKeyStoreException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public static class SharedKeyStoreSpi extends BcFKSKeyStoreSpi implements n, a0 {
        private final Map<String, byte[]> cache;
        private final byte[] seedKey;

        public SharedKeyStoreSpi(BouncyCastleProvider bouncyCastleProvider) {
            super(bouncyCastleProvider);
            try {
                byte[] bArr = new byte[32];
                this.seedKey = bArr;
                (bouncyCastleProvider != null ? SecureRandom.getInstance("DEFAULT", bouncyCastleProvider) : SecureRandom.getInstance("DEFAULT")).nextBytes(bArr);
                this.cache = new HashMap();
            } catch (NoSuchAlgorithmException e2) {
                throw new IllegalArgumentException("can't create cert factory - " + e2.toString());
            }
        }

        private byte[] calculateMac(String str, char[] cArr) throws NoSuchAlgorithmException, InvalidKeyException {
            return y.i(cArr != null ? g.a.f.a.m(i.j(cArr), i.i(str)) : g.a.f.a.m(this.seedKey, i.i(str)), this.seedKey, 16384, 8, 1, 32);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public void engineDeleteEntry(String str) throws KeyStoreException {
            this.cache.remove(str);
            super.engineDeleteEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            try {
                byte[] bArrCalculateMac = calculateMac(str, cArr);
                if (!this.cache.containsKey(str) || g.a.f.a.q(this.cache.get(str), bArrCalculateMac)) {
                    Key keyEngineGetKey = super.engineGetKey(str, cArr);
                    if (keyEngineGetKey != null && !this.cache.containsKey(str)) {
                        this.cache.put(str, bArrCalculateMac);
                    }
                    return keyEngineGetKey;
                }
                throw new UnrecoverableKeyException("unable to recover key (" + str + ")");
            } catch (InvalidKeyException e2) {
                throw new UnrecoverableKeyException("unable to recover key (" + str + "): " + e2.getMessage());
            }
        }
    }

    public static class Std extends BcFKSKeyStoreSpi {
        public Std() {
            super(new BouncyCastleProvider());
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Enumeration engineAliases() {
            return super.engineAliases();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineContainsAlias(String str) {
            return super.engineContainsAlias(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineDeleteEntry(String str) throws KeyStoreException {
            super.engineDeleteEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate engineGetCertificate(String str) {
            return super.engineGetCertificate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ String engineGetCertificateAlias(Certificate certificate) {
            return super.engineGetCertificateAlias(certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate[] engineGetCertificateChain(String str) {
            return super.engineGetCertificateChain(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Date engineGetCreationDate(String str) {
            return super.engineGetCreationDate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            return super.engineGetKey(str, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsCertificateEntry(String str) {
            return super.engineIsCertificateEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsKeyEntry(String str) {
            return super.engineIsKeyEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineLoad(InputStream inputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineLoad(inputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
            super.engineSetCertificateEntry(str, certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, key, cArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, bArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ int engineSize() {
            return super.engineSize();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(OutputStream outputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(outputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(loadStoreParameter);
        }
    }

    public static class StdShared extends SharedKeyStoreSpi {
        public StdShared() {
            super(new BouncyCastleProvider());
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Enumeration engineAliases() {
            return super.engineAliases();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineContainsAlias(String str) {
            return super.engineContainsAlias(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi.SharedKeyStoreSpi, org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineDeleteEntry(String str) throws KeyStoreException {
            super.engineDeleteEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate engineGetCertificate(String str) {
            return super.engineGetCertificate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ String engineGetCertificateAlias(Certificate certificate) {
            return super.engineGetCertificateAlias(certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate[] engineGetCertificateChain(String str) {
            return super.engineGetCertificateChain(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Date engineGetCreationDate(String str) {
            return super.engineGetCreationDate(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi.SharedKeyStoreSpi, org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            return super.engineGetKey(str, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsCertificateEntry(String str) {
            return super.engineIsCertificateEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsKeyEntry(String str) {
            return super.engineIsKeyEntry(str);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineLoad(InputStream inputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineLoad(inputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
            super.engineSetCertificateEntry(str, certificate);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, key, cArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
            super.engineSetKeyEntry(str, bArr, certificateArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ int engineSize() {
            return super.engineSize();
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(OutputStream outputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(outputStream, cArr);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, CertificateException {
            super.engineStore(loadStoreParameter);
        }
    }

    static {
        HashMap map = new HashMap();
        oidMap = map;
        HashMap map2 = new HashMap();
        publicAlgMap = map2;
        m mVar = b.f3947h;
        map.put("DESEDE", mVar);
        map.put("TRIPLEDES", mVar);
        map.put("TDEA", mVar);
        map.put("HMACSHA1", n.Q);
        map.put("HMACSHA224", n.R);
        map.put("HMACSHA256", n.S);
        map.put("HMACSHA384", n.T);
        map.put("HMACSHA512", n.U);
        map.put("SEED", g.a.a.j2.a.f3880a);
        map.put("CAMELLIA.128", g.a.a.n2.a.f3931a);
        map.put("CAMELLIA.192", g.a.a.n2.a.f3932b);
        map.put("CAMELLIA.256", g.a.a.n2.a.f3933c);
        map.put("ARIA.128", g.a.a.m2.a.f3927h);
        map.put("ARIA.192", g.a.a.m2.a.m);
        map.put("ARIA.256", g.a.a.m2.a.r);
        map2.put(n.f3978e, "RSA");
        map2.put(g.a.a.x2.m.z2, "EC");
        map2.put(b.l, "DH");
        map2.put(n.w, "DH");
        map2.put(g.a.a.x2.m.j3, "DSA");
        CERTIFICATE = BigInteger.valueOf(0L);
        PRIVATE_KEY = BigInteger.valueOf(1L);
        SECRET_KEY = BigInteger.valueOf(2L);
        PROTECTED_PRIVATE_KEY = BigInteger.valueOf(3L);
        PROTECTED_SECRET_KEY = BigInteger.valueOf(4L);
    }

    public BcFKSKeyStoreSpi(BouncyCastleProvider bouncyCastleProvider) {
        this.provider = bouncyCastleProvider;
    }

    private byte[] calculateMac(byte[] bArr, a aVar, h hVar, char[] cArr) throws NoSuchAlgorithmException, IOException {
        String strR = aVar.g().r();
        BouncyCastleProvider bouncyCastleProvider = this.provider;
        Mac mac = bouncyCastleProvider != null ? Mac.getInstance(strR, bouncyCastleProvider) : Mac.getInstance(strR);
        try {
            if (cArr == null) {
                cArr = new char[0];
            }
            mac.init(new SecretKeySpec(generateKey(hVar, "INTEGRITY_CHECK", cArr, -1), strR));
            return mac.doFinal(bArr);
        } catch (InvalidKeyException e2) {
            throw new IOException("Cannot set up MAC calculation: " + e2.getMessage());
        }
    }

    private Cipher createCipher(String str, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        BouncyCastleProvider bouncyCastleProvider = this.provider;
        Cipher cipher = bouncyCastleProvider == null ? Cipher.getInstance(str) : Cipher.getInstance(str, bouncyCastleProvider);
        cipher.init(1, new SecretKeySpec(bArr, "AES"));
        return cipher;
    }

    private c createPrivateKeySequence(f fVar, Certificate[] certificateArr) throws CertificateEncodingException {
        g[] gVarArr = new g[certificateArr.length];
        for (int i2 = 0; i2 != certificateArr.length; i2++) {
            gVarArr[i2] = g.h(certificateArr[i2].getEncoded());
        }
        return new c(fVar, gVarArr);
    }

    private Certificate decodeCertificate(Object obj) {
        BouncyCastleProvider bouncyCastleProvider = this.provider;
        if (bouncyCastleProvider != null) {
            try {
                return CertificateFactory.getInstance("X.509", bouncyCastleProvider).generateCertificate(new ByteArrayInputStream(g.h(obj).e()));
            } catch (Exception unused) {
                return null;
            }
        }
        try {
            return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(g.h(obj).e()));
        } catch (Exception unused2) {
            return null;
        }
    }

    private byte[] decryptData(String str, a aVar, char[] cArr, byte[] bArr) throws IOException {
        Cipher cipher;
        if (!aVar.g().equals(n.G)) {
            throw new IOException("BCFKS KeyStore cannot recognize protection algorithm.");
        }
        k kVarH = k.h(aVar.j());
        g.a.a.p2.g gVarG = kVarH.g();
        try {
            AlgorithmParameters algorithmParameters = null;
            if (gVarG.g().equals(g.a.a.l2.b.P)) {
                BouncyCastleProvider bouncyCastleProvider = this.provider;
                if (bouncyCastleProvider == null) {
                    cipher = Cipher.getInstance("AES/CCM/NoPadding");
                    algorithmParameters = AlgorithmParameters.getInstance("CCM");
                } else {
                    cipher = Cipher.getInstance("AES/CCM/NoPadding", bouncyCastleProvider);
                    algorithmParameters = AlgorithmParameters.getInstance("CCM", this.provider);
                }
                algorithmParameters.init(g.a.a.c2.a.h(gVarG.i()).e());
            } else {
                if (!gVarG.g().equals(g.a.a.l2.b.Q)) {
                    throw new IOException("BCFKS KeyStore cannot recognize protection encryption algorithm.");
                }
                BouncyCastleProvider bouncyCastleProvider2 = this.provider;
                cipher = bouncyCastleProvider2 == null ? Cipher.getInstance("AESKWP") : Cipher.getInstance("AESKWP", bouncyCastleProvider2);
            }
            h hVarI = kVarH.i();
            if (cArr == null) {
                cArr = new char[0];
            }
            cipher.init(2, new SecretKeySpec(generateKey(hVarI, str, cArr, 32), "AES"), algorithmParameters);
            return cipher.doFinal(bArr);
        } catch (IOException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new IOException(e3.toString());
        }
    }

    private Date extractCreationDate(e eVar, Date date) {
        try {
            return eVar.g().p();
        } catch (ParseException unused) {
            return date;
        }
    }

    private byte[] generateKey(h hVar, String str, char[] cArr, int i2) throws IOException {
        byte[] bArrA = u.a(cArr);
        byte[] bArrA2 = u.a(str.toCharArray());
        if (g.a.a.k2.c.L.equals(hVar.g())) {
            g.a.a.k2.f fVarI = g.a.a.k2.f.i(hVar.i());
            if (fVarI.j() != null) {
                i2 = fVarI.j().intValue();
            } else if (i2 == -1) {
                throw new IOException("no keyLength found in ScryptParams");
            }
            return y.i(g.a.f.a.m(bArrA, bArrA2), fVarI.l(), fVarI.h().intValue(), fVarI.g().intValue(), fVarI.g().intValue(), i2);
        }
        if (!hVar.g().equals(n.H)) {
            throw new IOException("BCFKS KeyStore: unrecognized MAC PBKD.");
        }
        l lVarG = l.g(hVar.i());
        if (lVarG.i() != null) {
            i2 = lVarG.i().intValue();
        } else if (i2 == -1) {
            throw new IOException("no keyLength found in PBKDF2Params");
        }
        if (lVarG.j().g().equals(n.U)) {
            v vVar = new v(new x());
            vVar.g(g.a.f.a.m(bArrA, bArrA2), lVarG.k(), lVarG.h().intValue());
            return ((s0) vVar.e(i2 * 8)).a();
        }
        if (lVarG.j().g().equals(g.a.a.l2.b.r)) {
            v vVar2 = new v(new w(512));
            vVar2.g(g.a.f.a.m(bArrA, bArrA2), lVarG.k(), lVarG.h().intValue());
            return ((s0) vVar2.e(i2 * 8)).a();
        }
        throw new IOException("BCFKS KeyStore: unrecognized MAC PBKD PRF: " + lVarG.j().g());
    }

    private h generatePkbdAlgorithmIdentifier(m mVar, int i2) {
        byte[] bArr = new byte[64];
        getDefaultSecureRandom().nextBytes(bArr);
        m mVar2 = n.H;
        if (mVar2.equals(mVar)) {
            return new h(mVar2, new l(bArr, 51200, i2, new a(n.U, w0.f4105c)));
        }
        throw new IllegalStateException("unknown derivation algorithm: " + mVar);
    }

    private h generatePkbdAlgorithmIdentifier(h hVar, int i2) {
        m mVar = g.a.a.k2.c.L;
        boolean zEquals = mVar.equals(hVar.g());
        g.a.a.e eVarI = hVar.i();
        if (zEquals) {
            g.a.a.k2.f fVarI = g.a.a.k2.f.i(eVarI);
            byte[] bArr = new byte[fVarI.l().length];
            getDefaultSecureRandom().nextBytes(bArr);
            return new h(mVar, new g.a.a.k2.f(bArr, fVarI.h(), fVarI.g(), fVarI.k(), BigInteger.valueOf(i2)));
        }
        l lVarG = l.g(eVarI);
        byte[] bArr2 = new byte[lVarG.k().length];
        getDefaultSecureRandom().nextBytes(bArr2);
        return new h(n.H, new l(bArr2, lVarG.h().intValue(), i2, lVarG.j()));
    }

    private h generatePkbdAlgorithmIdentifier(g.a.b.n0.c cVar, int i2) {
        m mVar = g.a.a.k2.c.L;
        if (mVar.equals(cVar.a())) {
            d dVar = (d) cVar;
            byte[] bArr = new byte[dVar.e()];
            getDefaultSecureRandom().nextBytes(bArr);
            return new h(mVar, new g.a.a.k2.f(bArr, dVar.c(), dVar.b(), dVar.d(), i2));
        }
        g.a.b.n0.b bVar = (g.a.b.n0.b) cVar;
        byte[] bArr2 = new byte[bVar.d()];
        getDefaultSecureRandom().nextBytes(bArr2);
        return new h(n.H, new l(bArr2, bVar.b(), i2, bVar.c()));
    }

    private SecureRandom getDefaultSecureRandom() {
        return j.b();
    }

    private static String getPublicKeyAlg(m mVar) {
        String str = publicAlgMap.get(mVar);
        return str != null ? str : mVar.r();
    }

    private void verifyMac(byte[] bArr, g.a.a.a2.j jVar, char[] cArr) throws NoSuchAlgorithmException, IOException {
        if (!g.a.f.a.q(calculateMac(bArr, jVar.i(), jVar.j(), cArr), jVar.h())) {
            throw new IOException("BCFKS KeyStore corrupted: MAC calculation failed.");
        }
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration<String> engineAliases() {
        final Iterator it = new HashSet(this.entries.keySet()).iterator();
        return new Enumeration() { // from class: org.bouncycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi.1
            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                return it.next();
            }
        };
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineContainsAlias(String str) {
        Objects.requireNonNull(str, "alias value is null");
        return this.entries.containsKey(str);
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String str) throws KeyStoreException {
        if (this.entries.get(str) == null) {
            return;
        }
        this.privateKeyCache.remove(str);
        this.entries.remove(str);
        this.lastModifiedDate = new Date();
    }

    @Override // java.security.KeyStoreSpi
    public Certificate engineGetCertificate(String str) {
        e eVar = this.entries.get(str);
        if (eVar == null) {
            return null;
        }
        if (eVar.l().equals(PRIVATE_KEY) || eVar.l().equals(PROTECTED_PRIVATE_KEY)) {
            return decodeCertificate(c.i(eVar.h()).g()[0]);
        }
        if (eVar.l().equals(CERTIFICATE)) {
            return decodeCertificate(eVar.h());
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public String engineGetCertificateAlias(Certificate certificate) {
        if (certificate == null) {
            return null;
        }
        try {
            byte[] encoded = certificate.getEncoded();
            for (String str : this.entries.keySet()) {
                e eVar = this.entries.get(str);
                if (eVar.l().equals(CERTIFICATE)) {
                    if (g.a.f.a.a(eVar.h(), encoded)) {
                        return str;
                    }
                } else if (eVar.l().equals(PRIVATE_KEY) || eVar.l().equals(PROTECTED_PRIVATE_KEY)) {
                    try {
                        if (g.a.f.a.a(c.i(eVar.h()).g()[0].c().e(), encoded)) {
                            return str;
                        }
                    } catch (IOException unused) {
                        continue;
                    }
                }
            }
        } catch (CertificateEncodingException unused2) {
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.security.KeyStoreSpi
    public Certificate[] engineGetCertificateChain(String str) {
        e eVar = this.entries.get(str);
        if (eVar == null) {
            return null;
        }
        if (!eVar.l().equals(PRIVATE_KEY) && !eVar.l().equals(PROTECTED_PRIVATE_KEY)) {
            return null;
        }
        g[] gVarArrG = c.i(eVar.h()).g();
        int length = gVarArrG.length;
        X509Certificate[] x509CertificateArr = new X509Certificate[length];
        for (int i2 = 0; i2 != length; i2++) {
            x509CertificateArr[i2] = decodeCertificate(gVarArrG[i2]);
        }
        return x509CertificateArr;
    }

    @Override // java.security.KeyStoreSpi
    public Date engineGetCreationDate(String str) {
        e eVar = this.entries.get(str);
        if (eVar == null) {
            return null;
        }
        try {
            return eVar.k().p();
        } catch (ParseException unused) {
            return new Date();
        }
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String str, char[] cArr) throws UnrecoverableKeyException, NoSuchAlgorithmException {
        e eVar = this.entries.get(str);
        if (eVar == null) {
            return null;
        }
        if (eVar.l().equals(PRIVATE_KEY) || eVar.l().equals(PROTECTED_PRIVATE_KEY)) {
            PrivateKey privateKey = this.privateKeyCache.get(str);
            if (privateKey != null) {
                return privateKey;
            }
            f fVarI = f.i(c.i(eVar.h()).h());
            try {
                p pVarG = p.g(decryptData("PRIVATE_KEY_ENCRYPTION", fVarI.h(), cArr, fVarI.g()));
                PrivateKey privateKeyGeneratePrivate = (this.provider != null ? KeyFactory.getInstance(pVarG.h().g().r(), this.provider) : KeyFactory.getInstance(getPublicKeyAlg(pVarG.h().g()))).generatePrivate(new PKCS8EncodedKeySpec(pVarG.e()));
                this.privateKeyCache.put(str, privateKeyGeneratePrivate);
                return privateKeyGeneratePrivate;
            } catch (Exception e2) {
                throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover private key (" + str + "): " + e2.getMessage());
            }
        }
        if (!eVar.l().equals(SECRET_KEY) && !eVar.l().equals(PROTECTED_SECRET_KEY)) {
            throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover secret key (" + str + "): type not recognized");
        }
        g.a.a.a2.d dVarH = g.a.a.a2.d.h(eVar.h());
        try {
            g.a.a.a2.k kVarG = g.a.a.a2.k.g(decryptData("SECRET_KEY_ENCRYPTION", dVarH.i(), cArr, dVarH.g()));
            return (this.provider != null ? SecretKeyFactory.getInstance(kVarG.h().r(), this.provider) : SecretKeyFactory.getInstance(kVarG.h().r())).generateSecret(new SecretKeySpec(kVarG.i(), kVarG.h().r()));
        } catch (Exception e3) {
            throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover secret key (" + str + "): " + e3.getMessage());
        }
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsCertificateEntry(String str) {
        e eVar = this.entries.get(str);
        if (eVar != null) {
            return eVar.l().equals(CERTIFICATE);
        }
        return false;
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsKeyEntry(String str) {
        e eVar = this.entries.get(str);
        if (eVar == null) {
            return false;
        }
        BigInteger bigIntegerL = eVar.l();
        return bigIntegerL.equals(PRIVATE_KEY) || bigIntegerL.equals(SECRET_KEY) || bigIntegerL.equals(PROTECTED_PRIVATE_KEY) || bigIntegerL.equals(PROTECTED_SECRET_KEY);
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream inputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
        g.a.a.a2.h hVarH;
        this.entries.clear();
        this.privateKeyCache.clear();
        this.creationDate = null;
        this.lastModifiedDate = null;
        this.hmacAlgorithm = null;
        if (inputStream == null) {
            Date date = new Date();
            this.creationDate = date;
            this.lastModifiedDate = date;
            this.hmacAlgorithm = new a(n.U, w0.f4105c);
            this.hmacPkbdAlgorithm = generatePkbdAlgorithmIdentifier(n.H, 64);
            return;
        }
        try {
            g.a.a.a2.g gVarG = g.a.a.a2.g.g(new g.a.a.i(inputStream).M());
            g.a.a.a2.i iVarH = gVarG.h();
            if (iVarH.i() != 0) {
                throw new IOException("BCFKS KeyStore unable to recognize integrity check.");
            }
            g.a.a.a2.j jVarG = g.a.a.a2.j.g(iVarH.h());
            this.hmacAlgorithm = jVarG.i();
            this.hmacPkbdAlgorithm = jVarG.j();
            verifyMac(gVarG.i().c().e(), jVarG, cArr);
            g.a.a.e eVarI = gVarG.i();
            if (eVarI instanceof g.a.a.a2.b) {
                g.a.a.a2.b bVar = (g.a.a.a2.b) eVarI;
                hVarH = g.a.a.a2.h.h(decryptData("STORE_ENCRYPTION", bVar.h(), cArr, bVar.g().p()));
            } else {
                hVarH = g.a.a.a2.h.h(eVarI);
            }
            try {
                this.creationDate = hVarH.g().p();
                this.lastModifiedDate = hVarH.j().p();
                if (!hVarH.i().equals(this.hmacAlgorithm)) {
                    throw new IOException("BCFKS KeyStore storeData integrity algorithm does not match store integrity algorithm.");
                }
                Iterator<g.a.a.e> it = hVarH.k().iterator();
                while (it.hasNext()) {
                    e eVarJ = e.j(it.next());
                    this.entries.put(eVarJ.i(), eVarJ);
                }
            } catch (ParseException unused) {
                throw new IOException("BCFKS KeyStore unable to parse store data information.");
            }
        } catch (Exception e2) {
            throw new IOException(e2.getMessage());
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
        Date dateExtractCreationDate;
        e eVar = this.entries.get(str);
        Date date = new Date();
        if (eVar == null) {
            dateExtractCreationDate = date;
        } else {
            if (!eVar.l().equals(CERTIFICATE)) {
                throw new KeyStoreException("BCFKS KeyStore already has a key entry with alias " + str);
            }
            dateExtractCreationDate = extractCreationDate(eVar, date);
        }
        try {
            this.entries.put(str, new e(CERTIFICATE, str, dateExtractCreationDate, date, certificate.getEncoded(), null));
            this.lastModifiedDate = date;
        } catch (CertificateEncodingException e2) {
            throw new ExtKeyStoreException("BCFKS KeyStore unable to handle certificate: " + e2.getMessage(), e2);
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
        g.a.a.a2.k kVar;
        Date date = new Date();
        e eVar = this.entries.get(str);
        Date dateExtractCreationDate = eVar != null ? extractCreationDate(eVar, date) : date;
        this.privateKeyCache.remove(str);
        if (key instanceof PrivateKey) {
            if (certificateArr == null) {
                throw new KeyStoreException("BCFKS KeyStore requires a certificate chain for private key storage.");
            }
            try {
                byte[] encoded = key.getEncoded();
                h hVarGeneratePkbdAlgorithmIdentifier = generatePkbdAlgorithmIdentifier(n.H, 32);
                if (cArr == null) {
                    cArr = new char[0];
                }
                Cipher cipherCreateCipher = createCipher("AES/CCM/NoPadding", generateKey(hVarGeneratePkbdAlgorithmIdentifier, "PRIVATE_KEY_ENCRYPTION", cArr, 32));
                byte[] bArrDoFinal = cipherCreateCipher.doFinal(encoded);
                AlgorithmParameters parameters = cipherCreateCipher.getParameters();
                this.entries.put(str, new e(PRIVATE_KEY, str, dateExtractCreationDate, date, createPrivateKeySequence(new f(new a(n.G, parameters != null ? new k(hVarGeneratePkbdAlgorithmIdentifier, new g.a.a.p2.g(g.a.a.l2.b.P, g.a.a.c2.a.h(parameters.getEncoded()))) : new k(hVarGeneratePkbdAlgorithmIdentifier, new g.a.a.p2.g(g.a.a.l2.b.Q, null))), bArrDoFinal), certificateArr).e(), null));
            } catch (Exception e2) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing private key: " + e2.toString(), e2);
            }
        } else {
            if (!(key instanceof SecretKey)) {
                throw new KeyStoreException("BCFKS KeyStore unable to recognize key.");
            }
            if (certificateArr != null) {
                throw new KeyStoreException("BCFKS KeyStore cannot store certificate chain with secret key.");
            }
            try {
                byte[] encoded2 = key.getEncoded();
                h hVarGeneratePkbdAlgorithmIdentifier2 = generatePkbdAlgorithmIdentifier(n.H, 32);
                if (cArr == null) {
                    cArr = new char[0];
                }
                Cipher cipherCreateCipher2 = createCipher("AES/CCM/NoPadding", generateKey(hVarGeneratePkbdAlgorithmIdentifier2, "SECRET_KEY_ENCRYPTION", cArr, 32));
                String strK = i.k(key.getAlgorithm());
                if (strK.indexOf("AES") > -1) {
                    kVar = new g.a.a.a2.k(g.a.a.l2.b.s, encoded2);
                } else {
                    Map<String, m> map = oidMap;
                    m mVar = map.get(strK);
                    if (mVar != null) {
                        kVar = new g.a.a.a2.k(mVar, encoded2);
                    } else {
                        m mVar2 = map.get(strK + "." + (encoded2.length * 8));
                        if (mVar2 == null) {
                            throw new KeyStoreException("BCFKS KeyStore cannot recognize secret key (" + strK + ") for storage.");
                        }
                        kVar = new g.a.a.a2.k(mVar2, encoded2);
                    }
                }
                byte[] bArrDoFinal2 = cipherCreateCipher2.doFinal(kVar.e());
                AlgorithmParameters parameters2 = cipherCreateCipher2.getParameters();
                this.entries.put(str, new e(SECRET_KEY, str, dateExtractCreationDate, date, new g.a.a.a2.d(new a(n.G, parameters2 != null ? new k(hVarGeneratePkbdAlgorithmIdentifier2, new g.a.a.p2.g(g.a.a.l2.b.P, g.a.a.c2.a.h(parameters2.getEncoded()))) : new k(hVarGeneratePkbdAlgorithmIdentifier2, new g.a.a.p2.g(g.a.a.l2.b.Q, null))), bArrDoFinal2).e(), null));
            } catch (Exception e3) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing private key: " + e3.toString(), e3);
            }
        }
        this.lastModifiedDate = date;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
        Date date = new Date();
        e eVar = this.entries.get(str);
        Date dateExtractCreationDate = eVar != null ? extractCreationDate(eVar, date) : date;
        if (certificateArr != null) {
            try {
                f fVarI = f.i(bArr);
                try {
                    this.privateKeyCache.remove(str);
                    this.entries.put(str, new e(PROTECTED_PRIVATE_KEY, str, dateExtractCreationDate, date, createPrivateKeySequence(fVarI, certificateArr).e(), null));
                } catch (Exception e2) {
                    throw new ExtKeyStoreException("BCFKS KeyStore exception storing protected private key: " + e2.toString(), e2);
                }
            } catch (Exception e3) {
                throw new ExtKeyStoreException("BCFKS KeyStore private key encoding must be an EncryptedPrivateKeyInfo.", e3);
            }
        } else {
            try {
                this.entries.put(str, new e(PROTECTED_SECRET_KEY, str, dateExtractCreationDate, date, bArr, null));
            } catch (Exception e4) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing protected private key: " + e4.toString(), e4);
            }
        }
        this.lastModifiedDate = date;
    }

    @Override // java.security.KeyStoreSpi
    public int engineSize() {
        return this.entries.size();
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream outputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
        h hVar;
        BigInteger bigIntegerI;
        e[] eVarArr = (e[]) this.entries.values().toArray(new e[this.entries.size()]);
        h hVarGeneratePkbdAlgorithmIdentifier = generatePkbdAlgorithmIdentifier(this.hmacPkbdAlgorithm, 32);
        byte[] bArrGenerateKey = generateKey(hVarGeneratePkbdAlgorithmIdentifier, "STORE_ENCRYPTION", cArr != null ? cArr : new char[0], 32);
        g.a.a.a2.h hVar2 = new g.a.a.a2.h(this.hmacAlgorithm, this.creationDate, this.lastModifiedDate, new g.a.a.a2.f(eVarArr), null);
        try {
            BouncyCastleProvider bouncyCastleProvider = this.provider;
            Cipher cipher = bouncyCastleProvider == null ? Cipher.getInstance("AES/CCM/NoPadding") : Cipher.getInstance("AES/CCM/NoPadding", bouncyCastleProvider);
            cipher.init(1, new SecretKeySpec(bArrGenerateKey, "AES"));
            g.a.a.a2.b bVar = new g.a.a.a2.b(new a(n.G, new k(hVarGeneratePkbdAlgorithmIdentifier, new g.a.a.p2.g(g.a.a.l2.b.P, g.a.a.c2.a.h(cipher.getParameters().getEncoded())))), cipher.doFinal(hVar2.e()));
            if (g.a.a.k2.c.L.equals(this.hmacPkbdAlgorithm.g())) {
                g.a.a.k2.f fVarI = g.a.a.k2.f.i(this.hmacPkbdAlgorithm.i());
                hVar = this.hmacPkbdAlgorithm;
                bigIntegerI = fVarI.j();
            } else {
                l lVarG = l.g(this.hmacPkbdAlgorithm.i());
                hVar = this.hmacPkbdAlgorithm;
                bigIntegerI = lVarG.i();
            }
            this.hmacPkbdAlgorithm = generatePkbdAlgorithmIdentifier(hVar, bigIntegerI.intValue());
            outputStream.write(new g.a.a.a2.g(bVar, new g.a.a.a2.i(new g.a.a.a2.j(this.hmacAlgorithm, this.hmacPkbdAlgorithm, calculateMac(bVar.e(), this.hmacAlgorithm, this.hmacPkbdAlgorithm, cArr)))).e());
            outputStream.flush();
        } catch (InvalidKeyException e2) {
            throw new IOException(e2.toString());
        } catch (BadPaddingException e3) {
            throw new IOException(e3.toString());
        } catch (IllegalBlockSizeException e4) {
            throw new IOException(e4.toString());
        } catch (NoSuchPaddingException e5) {
            throw new NoSuchAlgorithmException(e5.toString());
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, CertificateException {
        char[] password;
        if (loadStoreParameter == null) {
            throw new IllegalArgumentException("'parameter' arg cannot be null");
        }
        if (!(loadStoreParameter instanceof BCFKSStoreParameter)) {
            throw new IllegalArgumentException("no support for 'parameter' of type " + loadStoreParameter.getClass().getName());
        }
        BCFKSStoreParameter bCFKSStoreParameter = (BCFKSStoreParameter) loadStoreParameter;
        KeyStore.ProtectionParameter protectionParameter = bCFKSStoreParameter.getProtectionParameter();
        if (protectionParameter == null) {
            password = null;
        } else if (protectionParameter instanceof KeyStore.PasswordProtection) {
            password = ((KeyStore.PasswordProtection) protectionParameter).getPassword();
        } else {
            if (!(protectionParameter instanceof KeyStore.CallbackHandlerProtection)) {
                throw new IllegalArgumentException("no support for protection parameter of type " + protectionParameter.getClass().getName());
            }
            CallbackHandler callbackHandler = ((KeyStore.CallbackHandlerProtection) protectionParameter).getCallbackHandler();
            PasswordCallback passwordCallback = new PasswordCallback("password: ", false);
            try {
                callbackHandler.handle(new Callback[]{passwordCallback});
                password = passwordCallback.getPassword();
            } catch (UnsupportedCallbackException e2) {
                throw new IllegalArgumentException("PasswordCallback not recognised: " + e2.getMessage(), e2);
            }
        }
        bCFKSStoreParameter.getStorePBKDFConfig().a().equals(g.a.a.k2.c.L);
        this.hmacPkbdAlgorithm = generatePkbdAlgorithmIdentifier(bCFKSStoreParameter.getStorePBKDFConfig(), 64);
        engineStore(bCFKSStoreParameter.getOutputStream(), password);
    }
}
