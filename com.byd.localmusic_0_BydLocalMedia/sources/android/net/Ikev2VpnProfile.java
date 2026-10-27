package android.net;

import android.os.Process;
import android.security.KeyStore;
import android.security.keystore.AndroidKeyStoreProvider;
import android.security.keystore.KeyProperties;
import com.android.internal.net.VpnProfile;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class Ikev2VpnProfile extends PlatformVpnProfile {
    public static final List<String> DEFAULT_ALGORITHMS = Collections.unmodifiableList(Arrays.asList(IpSecAlgorithm.CRYPT_AES_CBC, IpSecAlgorithm.AUTH_HMAC_SHA256, IpSecAlgorithm.AUTH_HMAC_SHA384, IpSecAlgorithm.AUTH_HMAC_SHA512, IpSecAlgorithm.AUTH_CRYPT_AES_GCM));
    private static final String EMPTY_CERT = "";
    private static final String MISSING_PARAM_MSG_TMPL = "Required parameter was not provided: %s";
    public static final String PREFIX_INLINE = "INLINE:";
    public static final String PREFIX_KEYSTORE_ALIAS = "KEYSTORE_ALIAS:";
    private final List<String> mAllowedAlgorithms;
    private final boolean mIsBypassable;
    private final boolean mIsMetered;
    private final boolean mIsRestrictedToTestNetworks;
    private final int mMaxMtu;
    private final String mPassword;
    private final byte[] mPresharedKey;
    private final ProxyInfo mProxyInfo;
    private final PrivateKey mRsaPrivateKey;
    private final String mServerAddr;
    private final X509Certificate mServerRootCaCert;
    private final X509Certificate mUserCert;
    private final String mUserIdentity;
    private final String mUsername;

    private Ikev2VpnProfile(int i, String str, String str2, byte[] bArr, X509Certificate x509Certificate, String str3, String str4, PrivateKey privateKey, X509Certificate x509Certificate2, ProxyInfo proxyInfo, List<String> list, boolean z, boolean z2, int i2, boolean z3) {
        super(i);
        checkNotNull(str, MISSING_PARAM_MSG_TMPL, "Server address");
        checkNotNull(str2, MISSING_PARAM_MSG_TMPL, "User Identity");
        checkNotNull(list, MISSING_PARAM_MSG_TMPL, "Allowed Algorithms");
        this.mServerAddr = str;
        this.mUserIdentity = str2;
        this.mPresharedKey = bArr == null ? null : Arrays.copyOf(bArr, bArr.length);
        this.mServerRootCaCert = x509Certificate;
        this.mUsername = str3;
        this.mPassword = str4;
        this.mRsaPrivateKey = privateKey;
        this.mUserCert = x509Certificate2;
        this.mProxyInfo = new ProxyInfo(proxyInfo);
        this.mAllowedAlgorithms = Collections.unmodifiableList(new ArrayList(list));
        this.mIsBypassable = z;
        this.mIsMetered = z2;
        this.mMaxMtu = i2;
        this.mIsRestrictedToTestNetworks = z3;
        validate();
    }

    private void validate() {
        Preconditions.checkStringNotEmpty(this.mServerAddr, MISSING_PARAM_MSG_TMPL, "Server Address");
        Preconditions.checkStringNotEmpty(this.mUserIdentity, MISSING_PARAM_MSG_TMPL, "User Identity");
        if (this.mMaxMtu < 1280) {
            throw new IllegalArgumentException("Max MTU must be at least1280");
        }
        int i = this.mType;
        if (i == 6) {
            checkNotNull(this.mUsername, MISSING_PARAM_MSG_TMPL, "Username");
            checkNotNull(this.mPassword, MISSING_PARAM_MSG_TMPL, "Password");
            X509Certificate x509Certificate = this.mServerRootCaCert;
            if (x509Certificate != null) {
                checkCert(x509Certificate);
            }
        } else if (i == 7) {
            checkNotNull(this.mPresharedKey, MISSING_PARAM_MSG_TMPL, "Preshared Key");
        } else if (i == 8) {
            checkNotNull(this.mUserCert, MISSING_PARAM_MSG_TMPL, "User cert");
            checkNotNull(this.mRsaPrivateKey, MISSING_PARAM_MSG_TMPL, "RSA Private key");
            checkCert(this.mUserCert);
            X509Certificate x509Certificate2 = this.mServerRootCaCert;
            if (x509Certificate2 != null) {
                checkCert(x509Certificate2);
            }
        } else {
            throw new IllegalArgumentException("Invalid auth method set");
        }
        validateAllowedAlgorithms(this.mAllowedAlgorithms);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateAllowedAlgorithms(List<String> list) {
        VpnProfile.validateAllowedAlgorithms(list);
        if (list.contains(IpSecAlgorithm.AUTH_HMAC_MD5) || list.contains(IpSecAlgorithm.AUTH_HMAC_SHA1)) {
            throw new IllegalArgumentException("Algorithm not supported for IKEv2 VPN profiles");
        }
        if (!hasAeadAlgorithms(list) && !hasNormalModeAlgorithms(list)) {
            throw new IllegalArgumentException("Algorithm set missing support for Auth, Crypt or both");
        }
    }

    public static boolean hasAeadAlgorithms(List<String> list) {
        return list.contains(IpSecAlgorithm.AUTH_CRYPT_AES_GCM);
    }

    public static boolean hasNormalModeAlgorithms(List<String> list) {
        return list.contains(IpSecAlgorithm.CRYPT_AES_CBC) && (list.contains(IpSecAlgorithm.AUTH_HMAC_SHA256) || list.contains(IpSecAlgorithm.AUTH_HMAC_SHA384) || list.contains(IpSecAlgorithm.AUTH_HMAC_SHA512));
    }

    public String getServerAddr() {
        return this.mServerAddr;
    }

    public String getUserIdentity() {
        return this.mUserIdentity;
    }

    public byte[] getPresharedKey() {
        byte[] bArr = this.mPresharedKey;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public X509Certificate getServerRootCaCert() {
        return this.mServerRootCaCert;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public PrivateKey getRsaPrivateKey() {
        return this.mRsaPrivateKey;
    }

    public X509Certificate getUserCert() {
        return this.mUserCert;
    }

    public ProxyInfo getProxyInfo() {
        return this.mProxyInfo;
    }

    public List<String> getAllowedAlgorithms() {
        return this.mAllowedAlgorithms;
    }

    public boolean isBypassable() {
        return this.mIsBypassable;
    }

    public boolean isMetered() {
        return this.mIsMetered;
    }

    public int getMaxMtu() {
        return this.mMaxMtu;
    }

    public boolean isRestrictedToTestNetworks() {
        return this.mIsRestrictedToTestNetworks;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), this.mServerAddr, this.mUserIdentity, Integer.valueOf(Arrays.hashCode(this.mPresharedKey)), this.mServerRootCaCert, this.mUsername, this.mPassword, this.mRsaPrivateKey, this.mUserCert, this.mProxyInfo, this.mAllowedAlgorithms, Boolean.valueOf(this.mIsBypassable), Boolean.valueOf(this.mIsMetered), Integer.valueOf(this.mMaxMtu), Boolean.valueOf(this.mIsRestrictedToTestNetworks));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Ikev2VpnProfile)) {
            return false;
        }
        Ikev2VpnProfile ikev2VpnProfile = (Ikev2VpnProfile) obj;
        return this.mType == ikev2VpnProfile.mType && Objects.equals(this.mServerAddr, ikev2VpnProfile.mServerAddr) && Objects.equals(this.mUserIdentity, ikev2VpnProfile.mUserIdentity) && Arrays.equals(this.mPresharedKey, ikev2VpnProfile.mPresharedKey) && Objects.equals(this.mServerRootCaCert, ikev2VpnProfile.mServerRootCaCert) && Objects.equals(this.mUsername, ikev2VpnProfile.mUsername) && Objects.equals(this.mPassword, ikev2VpnProfile.mPassword) && Objects.equals(this.mRsaPrivateKey, ikev2VpnProfile.mRsaPrivateKey) && Objects.equals(this.mUserCert, ikev2VpnProfile.mUserCert) && Objects.equals(this.mProxyInfo, ikev2VpnProfile.mProxyInfo) && Objects.equals(this.mAllowedAlgorithms, ikev2VpnProfile.mAllowedAlgorithms) && this.mIsBypassable == ikev2VpnProfile.mIsBypassable && this.mIsMetered == ikev2VpnProfile.mIsMetered && this.mMaxMtu == ikev2VpnProfile.mMaxMtu && this.mIsRestrictedToTestNetworks == ikev2VpnProfile.mIsRestrictedToTestNetworks;
    }

    @Override // android.net.PlatformVpnProfile
    public VpnProfile toVpnProfile() throws GeneralSecurityException, IOException {
        VpnProfile vpnProfile = new VpnProfile("", this.mIsRestrictedToTestNetworks);
        vpnProfile.type = this.mType;
        vpnProfile.server = this.mServerAddr;
        vpnProfile.ipsecIdentifier = this.mUserIdentity;
        vpnProfile.proxy = this.mProxyInfo;
        vpnProfile.setAllowedAlgorithms(this.mAllowedAlgorithms);
        vpnProfile.isBypassable = this.mIsBypassable;
        vpnProfile.isMetered = this.mIsMetered;
        vpnProfile.maxMtu = this.mMaxMtu;
        vpnProfile.areAuthParamsInline = true;
        vpnProfile.saveLogin = true;
        int i = this.mType;
        if (i == 6) {
            vpnProfile.username = this.mUsername;
            vpnProfile.password = this.mPassword;
            X509Certificate x509Certificate = this.mServerRootCaCert;
            vpnProfile.ipsecCaCert = x509Certificate != null ? certificateToPemString(x509Certificate) : "";
        } else if (i == 7) {
            vpnProfile.ipsecSecret = encodeForIpsecSecret(this.mPresharedKey);
        } else if (i == 8) {
            vpnProfile.ipsecUserCert = certificateToPemString(this.mUserCert);
            vpnProfile.ipsecSecret = PREFIX_INLINE + encodeForIpsecSecret(this.mRsaPrivateKey.getEncoded());
            X509Certificate x509Certificate2 = this.mServerRootCaCert;
            vpnProfile.ipsecCaCert = x509Certificate2 != null ? certificateToPemString(x509Certificate2) : "";
        } else {
            throw new IllegalArgumentException("Invalid auth method set");
        }
        return vpnProfile;
    }

    public static Ikev2VpnProfile fromVpnProfile(VpnProfile vpnProfile) throws GeneralSecurityException, IOException {
        return fromVpnProfile(vpnProfile, null);
    }

    public static Ikev2VpnProfile fromVpnProfile(VpnProfile vpnProfile, KeyStore keyStore) throws GeneralSecurityException, IOException {
        PrivateKey privateKey;
        Builder builder = new Builder(vpnProfile.server, vpnProfile.ipsecIdentifier);
        builder.setProxy(vpnProfile.proxy);
        builder.setAllowedAlgorithms(vpnProfile.getAllowedAlgorithms());
        builder.setBypassable(vpnProfile.isBypassable);
        builder.setMetered(vpnProfile.isMetered);
        builder.setMaxMtu(vpnProfile.maxMtu);
        if (vpnProfile.isRestrictedToTestNetworks) {
            builder.restrictToTestNetworks();
        }
        int i = vpnProfile.type;
        if (i == 6) {
            builder.setAuthUsernamePassword(vpnProfile.username, vpnProfile.password, certificateFromPemString(vpnProfile.ipsecCaCert));
        } else if (i == 7) {
            builder.setAuthPsk(decodeFromIpsecSecret(vpnProfile.ipsecSecret));
        } else if (i == 8) {
            if (vpnProfile.ipsecSecret.startsWith(PREFIX_KEYSTORE_ALIAS)) {
                Objects.requireNonNull(keyStore, "Missing Keystore for aliased PrivateKey");
                privateKey = AndroidKeyStoreProvider.loadAndroidKeyStorePrivateKeyFromKeystore(keyStore, vpnProfile.ipsecSecret.substring(15), Process.myUid());
            } else if (vpnProfile.ipsecSecret.startsWith(PREFIX_INLINE)) {
                privateKey = getPrivateKey(vpnProfile.ipsecSecret.substring(7));
            } else {
                throw new IllegalArgumentException("Invalid RSA private key prefix");
            }
            builder.setAuthDigitalSignature(certificateFromPemString(vpnProfile.ipsecUserCert), privateKey, certificateFromPemString(vpnProfile.ipsecCaCert));
        } else {
            throw new IllegalArgumentException("Invalid auth method set");
        }
        return builder.build();
    }

    public static boolean isValidVpnProfile(VpnProfile vpnProfile) {
        if (!vpnProfile.server.isEmpty() && !vpnProfile.ipsecIdentifier.isEmpty()) {
            int i = vpnProfile.type;
            if (i != 6) {
                return i != 7 ? (i != 8 || vpnProfile.ipsecSecret.isEmpty() || vpnProfile.ipsecUserCert.isEmpty()) ? false : true : !vpnProfile.ipsecSecret.isEmpty();
            }
            if (!vpnProfile.username.isEmpty() && !vpnProfile.password.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static String certificateToPemString(X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        return x509Certificate == null ? "" : new String(android.security.Credentials.convertToPem(x509Certificate), StandardCharsets.US_ASCII);
    }

    private static X509Certificate certificateFromPemString(String str) throws CertificateException {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            List<X509Certificate> listConvertFromPem = android.security.Credentials.convertFromPem(str.getBytes(StandardCharsets.US_ASCII));
            if (listConvertFromPem.isEmpty()) {
                return null;
            }
            return listConvertFromPem.get(0);
        } catch (IOException e) {
            throw new CertificateException(e);
        }
    }

    public static String encodeForIpsecSecret(byte[] bArr) {
        checkNotNull(bArr, MISSING_PARAM_MSG_TMPL, "secret");
        return Base64.getEncoder().encodeToString(bArr);
    }

    private static byte[] decodeFromIpsecSecret(String str) {
        checkNotNull(str, MISSING_PARAM_MSG_TMPL, "encoded");
        return Base64.getDecoder().decode(str);
    }

    private static PrivateKey getPrivateKey(String str) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(decodeFromIpsecSecret(str)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkCert(X509Certificate x509Certificate) {
        try {
            certificateToPemString(x509Certificate);
        } catch (IOException | GeneralSecurityException unused) {
            throw new IllegalArgumentException("Certificate could not be encoded");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T checkNotNull(T t, String str, Object... objArr) {
        return (T) Objects.requireNonNull(t, String.format(str, objArr));
    }

    public static final class Builder {
        private String mPassword;
        private byte[] mPresharedKey;
        private ProxyInfo mProxyInfo;
        private PrivateKey mRsaPrivateKey;
        private final String mServerAddr;
        private X509Certificate mServerRootCaCert;
        private X509Certificate mUserCert;
        private final String mUserIdentity;
        private String mUsername;
        private int mType = -1;
        private List<String> mAllowedAlgorithms = Ikev2VpnProfile.DEFAULT_ALGORITHMS;
        private boolean mIsBypassable = false;
        private boolean mIsMetered = true;
        private int mMaxMtu = 1360;
        private boolean mIsRestrictedToTestNetworks = false;

        public Builder(String str, String str2) {
            Ikev2VpnProfile.checkNotNull(str, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "serverAddr");
            Ikev2VpnProfile.checkNotNull(str2, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "identity");
            this.mServerAddr = str;
            this.mUserIdentity = str2;
        }

        private void resetAuthParams() {
            this.mPresharedKey = null;
            this.mServerRootCaCert = null;
            this.mUsername = null;
            this.mPassword = null;
            this.mRsaPrivateKey = null;
            this.mUserCert = null;
        }

        public Builder setAuthUsernamePassword(String str, String str2, X509Certificate x509Certificate) {
            Ikev2VpnProfile.checkNotNull(str, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "user");
            Ikev2VpnProfile.checkNotNull(str2, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "pass");
            if (x509Certificate != null) {
                Ikev2VpnProfile.checkCert(x509Certificate);
            }
            resetAuthParams();
            this.mUsername = str;
            this.mPassword = str2;
            this.mServerRootCaCert = x509Certificate;
            this.mType = 6;
            return this;
        }

        public Builder setAuthDigitalSignature(X509Certificate x509Certificate, PrivateKey privateKey, X509Certificate x509Certificate2) {
            Ikev2VpnProfile.checkNotNull(x509Certificate, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "userCert");
            Ikev2VpnProfile.checkNotNull(privateKey, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "key");
            Ikev2VpnProfile.checkCert(x509Certificate);
            if (x509Certificate2 != null) {
                Ikev2VpnProfile.checkCert(x509Certificate2);
            }
            resetAuthParams();
            this.mUserCert = x509Certificate;
            this.mRsaPrivateKey = privateKey;
            this.mServerRootCaCert = x509Certificate2;
            this.mType = 8;
            return this;
        }

        public Builder setAuthPsk(byte[] bArr) {
            Ikev2VpnProfile.checkNotNull(bArr, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "psk");
            resetAuthParams();
            this.mPresharedKey = bArr;
            this.mType = 7;
            return this;
        }

        public Builder setBypassable(boolean z) {
            this.mIsBypassable = z;
            return this;
        }

        public Builder setProxy(ProxyInfo proxyInfo) {
            this.mProxyInfo = proxyInfo;
            return this;
        }

        public Builder setMaxMtu(int i) {
            if (i < 1280) {
                throw new IllegalArgumentException("Max MTU must be at least 1280");
            }
            this.mMaxMtu = i;
            return this;
        }

        public Builder setMetered(boolean z) {
            this.mIsMetered = z;
            return this;
        }

        public Builder setAllowedAlgorithms(List<String> list) {
            Ikev2VpnProfile.checkNotNull(list, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "algorithmNames");
            Ikev2VpnProfile.validateAllowedAlgorithms(list);
            this.mAllowedAlgorithms = list;
            return this;
        }

        public Builder restrictToTestNetworks() {
            this.mIsRestrictedToTestNetworks = true;
            return this;
        }

        public Ikev2VpnProfile build() {
            return new Ikev2VpnProfile(this.mType, this.mServerAddr, this.mUserIdentity, this.mPresharedKey, this.mServerRootCaCert, this.mUsername, this.mPassword, this.mRsaPrivateKey, this.mUserCert, this.mProxyInfo, this.mAllowedAlgorithms, this.mIsBypassable, this.mIsMetered, this.mMaxMtu, this.mIsRestrictedToTestNetworks);
        }
    }
}
