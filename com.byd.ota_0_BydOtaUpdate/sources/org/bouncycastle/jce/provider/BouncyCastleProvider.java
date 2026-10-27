package org.bouncycastle.jce.provider;

import g.a.a.m;
import g.a.a.p2.p;
import g.a.a.w2.u;
import g.a.e.a.e;
import g.a.e.c.a.f.b;
import g.a.e.c.a.f.c;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

/* JADX INFO: loaded from: classes.dex */
public final class BouncyCastleProvider extends Provider implements ConfigurableProvider {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f5954c = "BouncyCastle Security Provider v1.60";
    public static final ProviderConfiguration CONFIGURATION = new g.a.c.d.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map f5955d = new HashMap();
    public static final String[] s = {"PBEPBKDF1", "PBEPBKDF2", "PBEPKCS12", "TLSKDF", "SCRYPT"};
    public static final String[] x = {"SipHash", "Poly1305"};
    public static final String[] y = {"AES", "ARC4", "ARIA", "Blowfish", "Camellia", "CAST5", "CAST6", "ChaCha", "DES", "DESede", "GOST28147", "Grainv1", "Grain128", "HC128", "HC256", "IDEA", "Noekeon", "RC2", "RC5", "RC6", "Rijndael", "Salsa20", "SEED", "Serpent", "Shacal2", "Skipjack", "SM4", "TEA", "Twofish", "Threefish", "VMPC", "VMPCKSA3", "XTEA", "XSalsa20", "OpenSSLPBKDF", "DSTU7624", "GOST3412_2015"};
    public static final String[] E3 = {"X509", "IES"};
    public static final String[] F3 = {"DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145", "GM"};
    public static final String[] G3 = {"GOST3411", "Keccak", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", "SHA256", "SHA384", "SHA512", "SHA3", "Skein", "SM3", "Tiger", "Whirlpool", "Blake2b", "Blake2s", "DSTU7564"};
    public static final String PROVIDER_NAME = "BC";
    public static final String[] H3 = {PROVIDER_NAME, "BCFKS", "PKCS12"};
    public static final String[] I3 = {"DRBG"};

    public class a implements PrivilegedAction {
        public a() {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            BouncyCastleProvider.this.f();
            return null;
        }
    }

    public BouncyCastleProvider() {
        super(PROVIDER_NAME, 1.6d, f5954c);
        AccessController.doPrivileged(new a());
    }

    public static AsymmetricKeyInfoConverter a(m mVar) {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter;
        Map map = f5955d;
        synchronized (map) {
            asymmetricKeyInfoConverter = (AsymmetricKeyInfoConverter) map.get(mVar);
        }
        return asymmetricKeyInfoConverter;
    }

    public static PrivateKey getPrivateKey(p pVar) throws IOException {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverterA = a(pVar.h().g());
        if (asymmetricKeyInfoConverterA == null) {
            return null;
        }
        return asymmetricKeyInfoConverterA.generatePrivate(pVar);
    }

    public static PublicKey getPublicKey(u uVar) throws IOException {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverterA = a(uVar.g().g());
        if (asymmetricKeyInfoConverterA == null) {
            return null;
        }
        return asymmetricKeyInfoConverterA.generatePublic(uVar);
    }

    @Override // org.bouncycastle.jcajce.provider.config.ConfigurableProvider
    public void addAlgorithm(String str, m mVar, String str2) {
        addAlgorithm(str + "." + mVar, str2);
        addAlgorithm(str + ".OID." + mVar, str2);
    }

    @Override // org.bouncycastle.jcajce.provider.config.ConfigurableProvider
    public void addAlgorithm(String str, String str2) {
        if (!containsKey(str)) {
            put(str, str2);
            return;
        }
        throw new IllegalStateException("duplicate provider key (" + str + ") found");
    }

    @Override // org.bouncycastle.jcajce.provider.config.ConfigurableProvider
    public void addAttributes(String str, Map<String, String> map) {
        for (String str2 : map.keySet()) {
            String str3 = str + " " + str2;
            if (containsKey(str3)) {
                throw new IllegalStateException("duplicate provider attribute key (" + str3 + ") found");
            }
            put(str3, map.get(str2));
        }
    }

    @Override // org.bouncycastle.jcajce.provider.config.ConfigurableProvider
    public void addKeyInfoConverter(m mVar, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        Map map = f5955d;
        synchronized (map) {
            map.put(mVar, asymmetricKeyInfoConverter);
        }
    }

    public final void b(String str, String[] strArr) {
        for (int i2 = 0; i2 != strArr.length; i2++) {
            Class clsLoadClass = ClassUtil.loadClass(BouncyCastleProvider.class, str + strArr[i2] + "$Mappings");
            if (clsLoadClass != null) {
                try {
                    ((AlgorithmProvider) clsLoadClass.newInstance()).configure(this);
                } catch (Exception e2) {
                    throw new InternalError("cannot create instance of " + str + strArr[i2] + "$Mappings : " + e2);
                }
            }
        }
    }

    public final void e() {
        addKeyInfoConverter(e.r, new g.a.e.c.a.d.a());
        addKeyInfoConverter(e.v, new g.a.e.c.a.b.a());
        addKeyInfoConverter(e.w, new b());
        addKeyInfoConverter(e.B, new c());
        addKeyInfoConverter(e.m, new g.a.e.c.a.a.b());
        addKeyInfoConverter(e.n, new g.a.e.c.a.a.a());
        addKeyInfoConverter(e.f5569a, new g.a.e.c.a.c.a());
    }

    public final void f() {
        b("org.bouncycastle.jcajce.provider.digest.", G3);
        b("org.bouncycastle.jcajce.provider.symmetric.", s);
        b("org.bouncycastle.jcajce.provider.symmetric.", x);
        b("org.bouncycastle.jcajce.provider.symmetric.", y);
        b("org.bouncycastle.jcajce.provider.asymmetric.", E3);
        b("org.bouncycastle.jcajce.provider.asymmetric.", F3);
        b("org.bouncycastle.jcajce.provider.keystore.", H3);
        b("org.bouncycastle.jcajce.provider.drbg.", I3);
        e();
        put("X509Store.CERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertCollection");
        put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreAttrCertCollection");
        put("X509Store.CRL/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCRLCollection");
        put("X509Store.CERTIFICATEPAIR/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertPairCollection");
        put("X509Store.CERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCerts");
        put("X509Store.CRL/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCRLs");
        put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPAttrCerts");
        put("X509Store.CERTIFICATEPAIR/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCertPairs");
        put("X509StreamParser.CERTIFICATE", "org.bouncycastle.jce.provider.X509CertParser");
        put("X509StreamParser.ATTRIBUTECERTIFICATE", "org.bouncycastle.jce.provider.X509AttrCertParser");
        put("X509StreamParser.CRL", "org.bouncycastle.jce.provider.X509CRLParser");
        put("X509StreamParser.CERTIFICATEPAIR", "org.bouncycastle.jce.provider.X509CertPairParser");
        put("Cipher.BROKENPBEWITHMD5ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        put("Cipher.BROKENPBEWITHSHA1ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        put("CertPathValidator.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathValidatorSpi");
        put("CertPathBuilder.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathBuilderSpi");
        put("CertPathValidator.RFC3280", "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.RFC3280", "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertPathValidator.PKIX", "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.PKIX", "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertStore.Collection", "org.bouncycastle.jce.provider.CertStoreCollectionSpi");
        put("CertStore.LDAP", "org.bouncycastle.jce.provider.X509LDAPCertStoreSpi");
        put("CertStore.Multi", "org.bouncycastle.jce.provider.MultiCertStoreSpi");
        put("Alg.Alias.CertStore.X509LDAP", "LDAP");
    }

    @Override // org.bouncycastle.jcajce.provider.config.ConfigurableProvider
    public boolean hasAlgorithm(String str, String str2) {
        if (!containsKey(str + "." + str2)) {
            if (!containsKey("Alg.Alias." + str + "." + str2)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.jcajce.provider.config.ConfigurableProvider
    public void setParameter(String str, Object obj) {
        ProviderConfiguration providerConfiguration = CONFIGURATION;
        synchronized (providerConfiguration) {
            ((g.a.c.d.a) providerConfiguration).a(str, obj);
        }
    }
}
