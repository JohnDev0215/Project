package com.android.certinstaller;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.Credentials;
import android.security.IKeyChainService;
import android.text.TextUtils;
import android.util.Log;
import com.android.org.bouncycastle.asn1.ASN1InputStream;
import com.android.org.bouncycastle.asn1.x509.BasicConstraints;
import com.android.org.conscrypt.TrustedCertificateStore;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class CredentialHelper {
    private HashMap<String, byte[]> mBundle;
    private List<X509Certificate> mCaCerts;
    private String mCertUsageSelected;
    private String mName;
    private String mReferrer;
    private int mUid;
    private X509Certificate mUserCert;
    private PrivateKey mUserKey;

    CredentialHelper() {
        this.mBundle = new HashMap<>();
        this.mName = "";
        this.mCertUsageSelected = "";
        this.mReferrer = "";
        this.mUid = -1;
        this.mCaCerts = new ArrayList();
    }

    CredentialHelper(Intent intent) {
        this.mBundle = new HashMap<>();
        this.mName = "";
        this.mCertUsageSelected = "";
        this.mReferrer = "";
        this.mUid = -1;
        this.mCaCerts = new ArrayList();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        String string = extras.getString("name");
        extras.remove("name");
        if (string != null) {
            this.mName = string;
        }
        String string2 = extras.getString("certificate_install_usage");
        extras.remove("certificate_install_usage");
        if (string2 != null) {
            setCertUsageSelectedAndUid(string2);
        } else {
            this.mUid = extras.getInt("install_as_uid", -1);
        }
        extras.remove("install_as_uid");
        String string3 = extras.getString("android.intent.extra.REFERRER");
        extras.remove("android.intent.extra.REFERRER");
        if (string3 != null) {
            this.mReferrer = string3;
        }
        Log.d("CredentialHelper", "# extras: " + extras.size());
        for (String str : extras.keySet()) {
            byte[] byteArray = extras.getByteArray(str);
            StringBuilder sb = new StringBuilder();
            sb.append("   ");
            sb.append(str);
            sb.append(": ");
            sb.append(byteArray == null ? -1 : byteArray.length);
            Log.d("CredentialHelper", sb.toString());
            this.mBundle.put(str, byteArray);
        }
        parseCert(getData("CERT"));
    }

    synchronized void onSaveStates(Bundle bundle) {
        try {
            bundle.putSerializable("data", this.mBundle);
            bundle.putString("name", this.mName);
            bundle.putInt("install_as_uid", this.mUid);
            if (this.mUserKey != null) {
                Log.d("CredentialHelper", "Key algorithm: " + this.mUserKey.getAlgorithm());
                bundle.putString("user_key_algorithm", this.mUserKey.getAlgorithm());
                bundle.putByteArray("USRPKEY_", this.mUserKey.getEncoded());
            }
            ArrayList arrayList = new ArrayList(this.mCaCerts.size() + 1);
            if (this.mUserCert != null) {
                arrayList.add(this.mUserCert.getEncoded());
            }
            Iterator<X509Certificate> it = this.mCaCerts.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getEncoded());
            }
            bundle.putByteArray("crts", Util.toBytes(arrayList));
        } catch (CertificateEncodingException e) {
            throw new AssertionError(e);
        }
    }

    void onRestoreStates(Bundle bundle) {
        this.mBundle = (HashMap) bundle.getSerializable("data");
        this.mName = bundle.getString("name");
        this.mUid = bundle.getInt("install_as_uid", -1);
        String string = bundle.getString("user_key_algorithm");
        byte[] byteArray = bundle.getByteArray("USRPKEY_");
        Log.d("CredentialHelper", "Loaded key algorithm: " + string);
        if (string != null && byteArray != null) {
            setPrivateKey(string, byteArray);
        }
        Iterator it = ((ArrayList) Util.fromBytes(bundle.getByteArray("crts"))).iterator();
        while (it.hasNext()) {
            parseCert((byte[]) it.next());
        }
    }

    private void parseCert(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
            if (isCa(x509Certificate)) {
                Log.d("CredentialHelper", "got a CA cert");
                this.mCaCerts.add(x509Certificate);
            } else {
                Log.d("CredentialHelper", "got a user cert");
                this.mUserCert = x509Certificate;
            }
        } catch (CertificateException e) {
            Log.w("CredentialHelper", "parseCert(): " + e);
        }
    }

    private boolean isCa(X509Certificate x509Certificate) {
        try {
            byte[] extensionValue = x509Certificate.getExtensionValue("2.5.29.19");
            if (extensionValue == null) {
                return false;
            }
            return BasicConstraints.getInstance(new ASN1InputStream(new ASN1InputStream(extensionValue).readObject().getOctets()).readObject()).isCA();
        } catch (IOException unused) {
            return false;
        }
    }

    boolean hasPkcs12KeyStore() {
        return this.mBundle.containsKey("PKCS12");
    }

    boolean hasPrivateKey() {
        return this.mBundle.containsKey("PKEY");
    }

    int getUidFromCertificateUsage(String str) {
        return "wifi".equals(str) ? 1010 : -1;
    }

    boolean hasUserCertificate() {
        return this.mUserCert != null;
    }

    boolean hasCaCerts() {
        return !this.mCaCerts.isEmpty();
    }

    boolean hasAnyForSystemInstall() {
        return this.mUserKey != null || hasUserCertificate() || hasCaCerts();
    }

    void setPrivateKey(String str, byte[] bArr) {
        try {
            this.mUserKey = KeyFactory.getInstance(str).generatePrivate(new PKCS8EncodedKeySpec(bArr));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (InvalidKeySpecException e2) {
            throw new AssertionError(e2);
        }
    }

    boolean containsAnyRawData() {
        return !this.mBundle.isEmpty();
    }

    byte[] getData(String str) {
        return this.mBundle.get(str);
    }

    void setName(String str) {
        this.mName = str;
    }

    String getName() {
        return this.mName;
    }

    void setCertUsageSelectedAndUid(String str) {
        this.mCertUsageSelected = str;
        this.mUid = getUidFromCertificateUsage(str);
    }

    String getCertUsageSelected() {
        return this.mCertUsageSelected;
    }

    boolean calledBySettings() {
        String str = this.mReferrer;
        return str != null && str.equals("com.android.settings");
    }

    Intent createSystemInstallIntent(Context context) {
        Intent intent = new Intent("com.android.credentials.INSTALL");
        intent.setComponent(ComponentName.unflattenFromString(context.getString(R.string.config_system_install_component)));
        intent.putExtra("install_as_uid", this.mUid);
        intent.putExtra("user_key_pair_name", this.mName);
        try {
            if (this.mUserKey != null) {
                intent.putExtra("user_private_key_data", this.mUserKey.getEncoded());
            }
            if (this.mUserCert != null) {
                intent.putExtra("user_certificate_data", Credentials.convertToPem(new Certificate[]{this.mUserCert}));
            }
            if (!this.mCaCerts.isEmpty()) {
                intent.putExtra("ca_certificates_data", Credentials.convertToPem((X509Certificate[]) this.mCaCerts.toArray(new X509Certificate[this.mCaCerts.size()])));
            }
            return intent;
        } catch (IOException e) {
            throw new AssertionError(e);
        } catch (CertificateEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    boolean installVpnAndAppsTrustAnchors(Context context, IKeyChainService iKeyChainService) {
        TrustedCertificateStore trustedCertificateStore = new TrustedCertificateStore();
        for (X509Certificate x509Certificate : this.mCaCerts) {
            try {
                byte[] encoded = x509Certificate.getEncoded();
                if (encoded != null) {
                    try {
                        iKeyChainService.installCaCertificate(encoded);
                        String certificateAlias = trustedCertificateStore.getCertificateAlias(x509Certificate);
                        if (certificateAlias == null) {
                            Log.e("CredentialHelper", "alias is null");
                            return false;
                        }
                        maybeApproveCaCert(context, certificateAlias);
                    } catch (RemoteException e) {
                        Log.w("CredentialHelper", "installCaCertsToKeyChain(): " + e);
                        return false;
                    }
                }
            } catch (CertificateEncodingException e2) {
                throw new AssertionError(e2);
            }
        }
        return true;
    }

    private void maybeApproveCaCert(Context context, String str) {
        if (((KeyguardManager) context.getSystemService(KeyguardManager.class)).isDeviceSecure(UserHandle.myUserId())) {
            ((DevicePolicyManager) context.getSystemService(DevicePolicyManager.class)).approveCaCert(str, UserHandle.myUserId(), true);
        }
    }

    boolean hasPassword() {
        if (!hasPkcs12KeyStore()) {
            return false;
        }
        try {
            return loadPkcs12Internal(new KeyStore.PasswordProtection(new char[0])) == null;
        } catch (Exception unused) {
            return true;
        }
    }

    boolean extractPkcs12(String str) {
        try {
            return extractPkcs12Internal(new KeyStore.PasswordProtection(str.toCharArray()));
        } catch (Exception e) {
            Log.w("CredentialHelper", "extractPkcs12(): " + e, e);
            return false;
        }
    }

    private boolean extractPkcs12Internal(KeyStore.PasswordProtection passwordProtection) throws Exception {
        KeyStore keyStoreLoadPkcs12Internal = loadPkcs12Internal(passwordProtection);
        Enumeration<String> enumerationAliases = keyStoreLoadPkcs12Internal.aliases();
        if (!enumerationAliases.hasMoreElements()) {
            Log.e("CredentialHelper", "PKCS12 file has no elements");
            return false;
        }
        while (enumerationAliases.hasMoreElements()) {
            String strNextElement = enumerationAliases.nextElement();
            if (keyStoreLoadPkcs12Internal.isKeyEntry(strNextElement)) {
                KeyStore.Entry entry = keyStoreLoadPkcs12Internal.getEntry(strNextElement, passwordProtection);
                Log.d("CredentialHelper", "extracted alias = " + strNextElement + ", entry=" + entry.getClass());
                if (entry instanceof KeyStore.PrivateKeyEntry) {
                    if (TextUtils.isEmpty(this.mName)) {
                        this.mName = strNextElement;
                    }
                    return installFrom((KeyStore.PrivateKeyEntry) entry);
                }
            } else {
                Log.d("CredentialHelper", "Skip non-key entry, alias = " + strNextElement);
            }
        }
        return true;
    }

    private KeyStore loadPkcs12Internal(KeyStore.PasswordProtection passwordProtection) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new ByteArrayInputStream(getData("PKCS12")), passwordProtection.getPassword());
        return keyStore;
    }

    private synchronized boolean installFrom(KeyStore.PrivateKeyEntry privateKeyEntry) {
        this.mUserKey = privateKeyEntry.getPrivateKey();
        this.mUserCert = (X509Certificate) privateKeyEntry.getCertificate();
        Certificate[] certificateChain = privateKeyEntry.getCertificateChain();
        Log.d("CredentialHelper", "# certs extracted = " + certificateChain.length);
        this.mCaCerts = new ArrayList(certificateChain.length);
        for (Certificate certificate : certificateChain) {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            if (isCa(x509Certificate)) {
                this.mCaCerts.add(x509Certificate);
            }
        }
        Log.d("CredentialHelper", "# ca certs extracted = " + this.mCaCerts.size());
        return true;
    }

    public boolean hasOnlyVpnAndAppsTrustAnchors() {
        return hasCaCerts() && this.mUid == -1 && this.mUserKey == null;
    }

    public String getReferrer() {
        return this.mReferrer;
    }
}
