package com.android.keychain;

import android.app.BroadcastOptions;
import android.app.IntentService;
import android.app.admin.SecurityLog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.StringParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.UserHandle;
import android.security.Credentials;
import android.security.IKeyChainService;
import android.security.KeyStore;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keystore.AttestationUtils;
import android.security.keystore.DeviceIdAttestationException;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.ParcelableKeyGenParameterSpec;
import android.security.keystore.StrongBoxUnavailableException;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.keychain.internal.ExistingKeysProvider;
import com.android.keychain.internal.GrantsDatabase;
import com.android.org.conscrypt.TrustedCertificateStore;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class KeyChainService extends IntentService {
    private final Set<Integer> ALLOWED_UIDS;
    private GrantsDatabase mGrantsDb;
    private final IKeyChainService.Stub mIKeyChainService;
    private Injector mInjector;
    private final KeyStore mKeyStore;

    public KeyChainService() {
        super(KeyChainService.class.getSimpleName());
        this.ALLOWED_UIDS = Collections.unmodifiableSet(new HashSet(Arrays.asList(-1, 1010)));
        this.mKeyStore = KeyStore.getInstance();
        this.mIKeyChainService = new IKeyChainService.Stub() { // from class: com.android.keychain.KeyChainService.1
            private final Context mContext;
            private final TrustedCertificateStore mTrustedCertificateStore = new TrustedCertificateStore();

            {
                this.mContext = KeyChainService.this;
            }

            public String requestPrivateKey(String str) {
                if (!hasGrant(str)) {
                    return null;
                }
                String str2 = "USRPKEY_" + str;
                int callingUid = KeyChainService.this.mInjector.getCallingUid();
                Log.i("KeyChain", String.format("UID %d will be granted access to %s", Integer.valueOf(callingUid), str2));
                return KeyChainService.this.mKeyStore.grant(str2, callingUid);
            }

            public byte[] getCertificate(String str) {
                if (!hasGrant(str)) {
                    return null;
                }
                return KeyChainService.this.mKeyStore.get("USRCERT_" + str);
            }

            public byte[] getCaCertificates(String str) {
                if (!hasGrant(str)) {
                    return null;
                }
                return KeyChainService.this.mKeyStore.get("CACERT_" + str);
            }

            public boolean isUserSelectable(String str) {
                validateAlias(str);
                return KeyChainService.this.mGrantsDb.isUserSelectable(str);
            }

            public void setUserSelectable(String str, boolean z) {
                validateAlias(str);
                checkSystemCaller();
                Log.i("KeyChain", String.format("Marking certificate %s as user-selectable: %b", str, Boolean.valueOf(z)));
                KeyChainService.this.mGrantsDb.setIsUserSelectable(str, z);
            }

            public int generateKeyPair(String str, ParcelableKeyGenParameterSpec parcelableKeyGenParameterSpec) {
                checkSystemCaller();
                KeyGenParameterSpec spec = parcelableKeyGenParameterSpec.getSpec();
                String keystoreAlias = spec.getKeystoreAlias();
                Log.i("KeyChain", String.format("About to generate key with alias %s, algorithm %s", keystoreAlias, str));
                if ("android:alias-selection-denied".equals(keystoreAlias)) {
                    throw new IllegalArgumentException("The alias specified for the key denotes a reserved value and cannot be used to name a key");
                }
                if (TextUtils.isEmpty(keystoreAlias) || spec.getUid() != -1) {
                    Log.e("KeyChain", "Cannot generate key pair with empty alias or specified uid.");
                    return 1;
                }
                if (spec.getAttestationChallenge() != null) {
                    Log.e("KeyChain", "Key generation request should not include an Attestation challenge.");
                    return 2;
                }
                if (!removeKeyPair(keystoreAlias)) {
                    Log.e("KeyChain", "Failed to remove previously-installed alias " + keystoreAlias);
                    return 7;
                }
                try {
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str, "AndroidKeyStore");
                    keyPairGenerator.initialize(spec);
                    if (keyPairGenerator.generateKeyPair() != null) {
                        return 0;
                    }
                    Log.e("KeyChain", "Key generation failed.");
                    return 7;
                } catch (StrongBoxUnavailableException e) {
                    Log.e("KeyChain", "StrongBox unavailable.", e);
                    return 6;
                } catch (InvalidAlgorithmParameterException e2) {
                    Log.e("KeyChain", "Invalid algorithm params", e2);
                    return 4;
                } catch (NoSuchAlgorithmException e3) {
                    Log.e("KeyChain", "Invalid algorithm requested", e3);
                    return 3;
                } catch (NoSuchProviderException e4) {
                    Log.e("KeyChain", "Could not find Keystore.", e4);
                    return 5;
                }
            }

            public int attestKey(String str, byte[] bArr, int[] iArr, KeymasterCertificateChain keymasterCertificateChain) {
                checkSystemCaller();
                validateAlias(str);
                if (bArr == null) {
                    Log.e("KeyChain", String.format("Missing attestation challenge for alias %s", str));
                    return 1;
                }
                if (Log.isLoggable("KeyChain", 3)) {
                    Log.d("KeyChain", String.format("About to attest key alias %s, challenge %s, flags %s", str, Base64.encodeToString(bArr, 2), Arrays.toString(iArr)));
                }
                try {
                    KeymasterArguments keymasterArgumentsPrepareAttestationArguments = AttestationUtils.prepareAttestationArguments(this.mContext, iArr, bArr);
                    int iCheckKeyChainStatus = checkKeyChainStatus(str, keymasterCertificateChain, keymasterArgumentsPrepareAttestationArguments);
                    if (iCheckKeyChainStatus == 3) {
                        try {
                            keymasterArgumentsPrepareAttestationArguments = AttestationUtils.prepareAttestationArgumentsIfMisprovisioned(this.mContext, iArr, bArr);
                            if (keymasterArgumentsPrepareAttestationArguments == null) {
                                return iCheckKeyChainStatus;
                            }
                        } catch (DeviceIdAttestationException e) {
                            Log.e("KeyChain", "Failed collecting attestation data during second attempt on misprovisioned device", e);
                            return 2;
                        }
                    }
                    return checkKeyChainStatus(str, keymasterCertificateChain, keymasterArgumentsPrepareAttestationArguments);
                } catch (DeviceIdAttestationException e2) {
                    Log.e("KeyChain", "Failed collecting attestation data", e2);
                    return 2;
                }
            }

            private int checkKeyChainStatus(String str, KeymasterCertificateChain keymasterCertificateChain, KeymasterArguments keymasterArguments) {
                int iAttestKey = KeyChainService.this.mKeyStore.attestKey("USRPKEY_" + str, keymasterArguments, keymasterCertificateChain);
                if (iAttestKey == 1) {
                    return 0;
                }
                Log.e("KeyChain", String.format("Failure attesting for key %s: %d", str, Integer.valueOf(iAttestKey)));
                return iAttestKey == -66 ? 3 : 4;
            }

            public boolean setKeyPairCertificate(String str, byte[] bArr, byte[] bArr2) {
                checkSystemCaller();
                if (!KeyChainService.this.mKeyStore.put("USRCERT_" + str, bArr, -1, 0)) {
                    Log.e("KeyChain", "Failed to import user certificate " + bArr);
                    return false;
                }
                if (bArr2 == null || bArr2.length <= 0) {
                    if (!KeyChainService.this.mKeyStore.delete("CACERT_" + str)) {
                        Log.e("KeyChain", "Failed to remove CA certificate chain for alias " + str);
                    }
                } else {
                    if (!KeyChainService.this.mKeyStore.put("CACERT_" + str, bArr2, -1, 0)) {
                        Log.e("KeyChain", "Failed to import certificate chain" + bArr2);
                        if (!KeyChainService.this.mKeyStore.delete("USRCERT_" + str)) {
                            Log.e("KeyChain", "Failed to clean up key chain after certificate chain importing failed");
                        }
                        return false;
                    }
                }
                if (Log.isLoggable("KeyChain", 3)) {
                    Log.d("KeyChain", String.format("Set certificate for key alias %s : user %s CA chain: %s", str, KeyChainService.emptyOrBase64Encoded(bArr), KeyChainService.emptyOrBase64Encoded(bArr2)));
                }
                KeyChainService.this.broadcastKeychainChange();
                KeyChainService.this.broadcastLegacyStorageChange();
                return true;
            }

            private void validateAlias(String str) {
                if (str == null) {
                    throw new NullPointerException("alias == null");
                }
            }

            private boolean hasGrant(String str) {
                validateAlias(str);
                int callingUid = KeyChainService.this.mInjector.getCallingUid();
                if (KeyChainService.this.mGrantsDb.hasGrant(callingUid, str)) {
                    return true;
                }
                Log.w("KeyChain", String.format("uid %d doesn't have permission to access the requested alias %s", Integer.valueOf(callingUid), str));
                return false;
            }

            public String installCaCertificate(byte[] bArr) {
                String certificateAlias;
                checkCertInstallerOrSystemCaller();
                boolean zIsSecurityLoggingEnabled = KeyChainService.this.mInjector.isSecurityLoggingEnabled();
                String name = null;
                try {
                    X509Certificate certificate = parseCertificate(bArr);
                    boolean zIsLoggable = Log.isLoggable("KeyChain", 3);
                    name = certificate.getSubjectX500Principal().getName("CANONICAL");
                    if (zIsLoggable) {
                        Log.d("KeyChain", String.format("Installing CA certificate: %s", name));
                    }
                    synchronized (this.mTrustedCertificateStore) {
                        this.mTrustedCertificateStore.installCertificate(certificate);
                        certificateAlias = this.mTrustedCertificateStore.getCertificateAlias(certificate);
                    }
                    if (zIsSecurityLoggingEnabled && name != null) {
                        KeyChainService.this.mInjector.writeSecurityEvent(210029, 1, name, Integer.valueOf(UserHandle.myUserId()));
                    }
                    if ("com.android.certinstaller".equals(callingPackage())) {
                        Log.d("KeyChain", String.format("Attempted installing %s (subject: %s) to KeyStore. Result: %b", certificateAlias, name, Boolean.valueOf(KeyChainService.this.mKeyStore.put(String.format("%s%s %s", "CACERT_", name, certificateAlias), bArr, 1000, 0))));
                    }
                    KeyChainService.this.broadcastLegacyStorageChange();
                    KeyChainService.this.broadcastTrustStoreChange();
                    return certificateAlias;
                } catch (IOException | CertificateException e) {
                    Log.w("KeyChain", "Failed installing CA certificate", e);
                    if (zIsSecurityLoggingEnabled && name != null) {
                        KeyChainService.this.mInjector.writeSecurityEvent(210029, 0, name, Integer.valueOf(UserHandle.myUserId()));
                    }
                    throw new IllegalStateException(e);
                }
            }

            public boolean installKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3, String str, int i) {
                checkCertInstallerOrSystemCaller();
                if (!"android:alias-selection-denied".equals(str)) {
                    if (!KeyChainService.this.ALLOWED_UIDS.contains(Integer.valueOf(i))) {
                        Log.e("KeyChain", String.format("Installing alias %s as UID %d is now allowed.", str, Integer.valueOf(i)));
                        return false;
                    }
                    if (bArr == null && bArr2 == null && bArr3 == null) {
                        Log.e("KeyChain", String.format("Nothing to install for alias %s", str));
                        return false;
                    }
                    if (i == 1010 && UserHandle.myUserId() != 0) {
                        Log.e("KeyChain", String.format("Installation into the WiFi Keystore should be called from the primary user, not user %d", Integer.valueOf(UserHandle.myUserId())));
                        return false;
                    }
                    if (Log.isLoggable("KeyChain", 3)) {
                        Log.d("KeyChain", String.format("Installing certificate and key to alias %s to uid %d: user cert %s CA chain: %s", str, Integer.valueOf(i), KeyChainService.emptyOrBase64Encoded(bArr2), KeyChainService.emptyOrBase64Encoded(bArr3)));
                    }
                    if (!removeKeyPair(str)) {
                        return false;
                    }
                    if (bArr != null) {
                        if (!KeyChainService.this.mKeyStore.importKey("USRPKEY_" + str, bArr, i, 0)) {
                            Log.e("KeyChain", "Failed to import private key " + str);
                            return false;
                        }
                    }
                    if (bArr2 != null) {
                        if (!KeyChainService.this.mKeyStore.put("USRCERT_" + str, bArr2, i, 0)) {
                            Log.e("KeyChain", "Failed to import user certificate " + bArr2);
                            if (!KeyChainService.this.mKeyStore.delete("USRPKEY_" + str)) {
                                Log.e("KeyChain", "Failed to delete private key after certificate importing failed");
                            }
                            return false;
                        }
                    }
                    if (bArr3 != null && bArr3.length > 0) {
                        if (!KeyChainService.this.mKeyStore.put("CACERT_" + str, bArr3, i, 0)) {
                            Log.e("KeyChain", "Failed to import certificate chain" + bArr3);
                            if (!removeKeyPair(str)) {
                                Log.e("KeyChain", "Failed to clean up key chain after certificate chain importing failed");
                            }
                            return false;
                        }
                    }
                    KeyChainService.this.broadcastKeychainChange();
                    KeyChainService.this.broadcastLegacyStorageChange();
                    return true;
                }
                throw new IllegalArgumentException("The alias specified for the key denotes a reserved value and cannot be used to name a key");
            }

            public boolean removeKeyPair(String str) {
                checkCertInstallerOrSystemCaller();
                if (!Credentials.deleteAllTypesForAlias(KeyChainService.this.mKeyStore, str)) {
                    return false;
                }
                Log.w("KeyChain", String.format("WARNING: Removing alias %s, existing grants will be revoked.", str));
                KeyChainService.this.mGrantsDb.removeAliasInformation(str);
                KeyChainService.this.broadcastKeychainChange();
                KeyChainService.this.broadcastLegacyStorageChange();
                return true;
            }

            private X509Certificate parseCertificate(byte[] bArr) throws CertificateException {
                return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
            }

            public boolean reset() {
                boolean z;
                checkSystemCaller();
                KeyChainService.this.mGrantsDb.removeAllAliasesInformation();
                synchronized (this.mTrustedCertificateStore) {
                    z = true;
                    for (String str : this.mTrustedCertificateStore.aliases()) {
                        if (TrustedCertificateStore.isUser(str) && !deleteCertificateEntry(str)) {
                            z = false;
                        }
                    }
                }
                KeyChainService.this.broadcastTrustStoreChange();
                KeyChainService.this.broadcastKeychainChange();
                KeyChainService.this.broadcastLegacyStorageChange();
                return z;
            }

            public boolean deleteCaCertificate(String str) {
                boolean zDeleteCertificateEntry;
                checkSystemCaller();
                Log.i("KeyChain", String.format("Deleting CA certificate %s", str));
                synchronized (this.mTrustedCertificateStore) {
                    zDeleteCertificateEntry = deleteCertificateEntry(str);
                }
                KeyChainService.this.broadcastTrustStoreChange();
                KeyChainService.this.broadcastLegacyStorageChange();
                return zDeleteCertificateEntry;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            private boolean deleteCertificateEntry(java.lang.String r10) {
                /*
                    r9 = this;
                    com.android.keychain.KeyChainService r0 = com.android.keychain.KeyChainService.this
                    com.android.keychain.KeyChainService$Injector r0 = com.android.keychain.KeyChainService.access$000(r0)
                    boolean r0 = r0.isSecurityLoggingEnabled()
                    if (r0 == 0) goto L23
                    com.android.org.conscrypt.TrustedCertificateStore r0 = r9.mTrustedCertificateStore
                    java.security.cert.Certificate r0 = r0.getCertificate(r10)
                    boolean r1 = r0 instanceof java.security.cert.X509Certificate
                    if (r1 == 0) goto L23
                    java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
                    javax.security.auth.x500.X500Principal r0 = r0.getSubjectX500Principal()
                    java.lang.String r1 = "CANONICAL"
                    java.lang.String r0 = r0.getName(r1)
                    goto L24
                L23:
                    r0 = 0
                L24:
                    r1 = 2
                    r2 = 3
                    r3 = 210030(0x3346e, float:2.94315E-40)
                    r4 = 1
                    r5 = 0
                    com.android.org.conscrypt.TrustedCertificateStore r6 = r9.mTrustedCertificateStore     // Catch: java.lang.Throwable -> L50
                    r6.deleteCertificateEntry(r10)     // Catch: java.lang.Throwable -> L50
                    if (r0 == 0) goto L4f
                    com.android.keychain.KeyChainService r6 = com.android.keychain.KeyChainService.this     // Catch: java.lang.Throwable -> L50
                    com.android.keychain.KeyChainService$Injector r6 = com.android.keychain.KeyChainService.access$000(r6)     // Catch: java.lang.Throwable -> L50
                    java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L50
                    java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L50
                    r7[r5] = r8     // Catch: java.lang.Throwable -> L50
                    r7[r4] = r0     // Catch: java.lang.Throwable -> L50
                    int r8 = android.os.UserHandle.myUserId()     // Catch: java.lang.Throwable -> L50
                    java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L50
                    r7[r1] = r8     // Catch: java.lang.Throwable -> L50
                    r6.writeSecurityEvent(r3, r7)     // Catch: java.lang.Throwable -> L50
                L4f:
                    return r4
                L50:
                    r6 = move-exception
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder
                    r7.<init>()
                    java.lang.String r8 = "Problem removing CA certificate "
                    r7.append(r8)
                    r7.append(r10)
                    java.lang.String r10 = r7.toString()
                    java.lang.String r7 = "KeyChain"
                    android.util.Log.w(r7, r10, r6)
                    if (r0 == 0) goto L86
                    com.android.keychain.KeyChainService r9 = com.android.keychain.KeyChainService.this
                    com.android.keychain.KeyChainService$Injector r9 = com.android.keychain.KeyChainService.access$000(r9)
                    java.lang.Object[] r10 = new java.lang.Object[r2]
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
                    r10[r5] = r2
                    r10[r4] = r0
                    int r0 = android.os.UserHandle.myUserId()
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    r10[r1] = r0
                    r9.writeSecurityEvent(r3, r10)
                L86:
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.keychain.KeyChainService.AnonymousClass1.deleteCertificateEntry(java.lang.String):boolean");
            }

            private void checkCertInstallerOrSystemCaller() {
                String strCallingPackage = callingPackage();
                if (isCallerWithSystemUid() || "com.android.certinstaller".equals(strCallingPackage)) {
                    return;
                }
                throw new SecurityException("Not system or cert installer package: " + strCallingPackage);
            }

            private void checkSystemCaller() {
                if (isCallerWithSystemUid()) {
                    return;
                }
                throw new SecurityException("Not system package: " + callingPackage());
            }

            private boolean isCallerWithSystemUid() {
                return UserHandle.isSameApp(KeyChainService.this.mInjector.getCallingUid(), 1000);
            }

            private String callingPackage() {
                return KeyChainService.this.getPackageManager().getNameForUid(KeyChainService.this.mInjector.getCallingUid());
            }

            public boolean hasGrant(int i, String str) {
                checkSystemCaller();
                return KeyChainService.this.mGrantsDb.hasGrant(i, str);
            }

            public void setGrant(int i, String str, boolean z) {
                checkSystemCaller();
                KeyChainService.this.mGrantsDb.setGrant(i, str, z);
                KeyChainService.this.broadcastPermissionChange(i, str, z);
                KeyChainService.this.broadcastLegacyStorageChange();
            }

            public StringParceledListSlice getUserCaAliases() {
                StringParceledListSlice stringParceledListSlice;
                synchronized (this.mTrustedCertificateStore) {
                    stringParceledListSlice = new StringParceledListSlice(new ArrayList(this.mTrustedCertificateStore.userAliases()));
                }
                return stringParceledListSlice;
            }

            public StringParceledListSlice getSystemCaAliases() {
                StringParceledListSlice stringParceledListSlice;
                synchronized (this.mTrustedCertificateStore) {
                    stringParceledListSlice = new StringParceledListSlice(new ArrayList(this.mTrustedCertificateStore.allSystemAliases()));
                }
                return stringParceledListSlice;
            }

            public boolean containsCaAlias(String str) {
                return this.mTrustedCertificateStore.containsAlias(str);
            }

            public byte[] getEncodedCaCertificate(String str, boolean z) {
                synchronized (this.mTrustedCertificateStore) {
                    X509Certificate x509Certificate = (X509Certificate) this.mTrustedCertificateStore.getCertificate(str, z);
                    if (x509Certificate == null) {
                        Log.w("KeyChain", "Could not find CA certificate " + str);
                        return null;
                    }
                    try {
                        return x509Certificate.getEncoded();
                    } catch (CertificateEncodingException unused) {
                        Log.w("KeyChain", "Error while encoding CA certificate " + str);
                        return null;
                    }
                }
            }

            public List<String> getCaCertificateChainAliases(String str, boolean z) {
                ArrayList arrayList;
                synchronized (this.mTrustedCertificateStore) {
                    try {
                        List certificateChain = this.mTrustedCertificateStore.getCertificateChain((X509Certificate) this.mTrustedCertificateStore.getCertificate(str, z));
                        arrayList = new ArrayList(certificateChain.size());
                        int size = certificateChain.size();
                        for (int i = 0; i < size; i++) {
                            String certificateAlias = this.mTrustedCertificateStore.getCertificateAlias((Certificate) certificateChain.get(i), true);
                            if (certificateAlias != null) {
                                arrayList.add(certificateAlias);
                            }
                        }
                    } catch (CertificateException unused) {
                        Log.w("KeyChain", "Error retrieving cert chain for root " + str);
                        return Collections.emptyList();
                    }
                }
                return arrayList;
            }
        };
        this.mInjector = new Injector();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mGrantsDb = new GrantsDatabase(this, new KeyStoreAliasesProvider(this.mKeyStore));
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mGrantsDb.destroy();
        this.mGrantsDb = null;
    }

    private static class KeyStoreAliasesProvider implements ExistingKeysProvider {
        private final KeyStore mKeyStore;

        KeyStoreAliasesProvider(KeyStore keyStore) {
            this.mKeyStore = keyStore;
        }

        @Override // com.android.keychain.internal.ExistingKeysProvider
        public List<String> getExistingKeyAliases() {
            ArrayList arrayList = new ArrayList();
            String[] list = this.mKeyStore.list("USRPKEY_");
            if (list == null) {
                return arrayList;
            }
            for (String str : list) {
                Log.w("KeyChain", "Got Alias from KeyStore: " + str);
                String strReplaceFirst = str.replaceFirst("^USRPKEY_", "");
                if (!strReplaceFirst.startsWith("synthetic_password_")) {
                    arrayList.add(strReplaceFirst);
                }
            }
            return arrayList;
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        if (IKeyChainService.class.getName().equals(intent.getAction())) {
            return this.mIKeyChainService;
        }
        return null;
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            this.mGrantsDb.purgeOldGrants(getPackageManager());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastLegacyStorageChange() {
        Intent intent = new Intent("android.security.STORAGE_CHANGED");
        BroadcastOptions broadcastOptionsMakeBasic = BroadcastOptions.makeBasic();
        broadcastOptionsMakeBasic.setMaxManifestReceiverApiLevel(25);
        sendBroadcastAsUser(intent, UserHandle.of(UserHandle.myUserId()), null, broadcastOptionsMakeBasic.toBundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastKeychainChange() {
        sendBroadcastAsUser(new Intent("android.security.action.KEYCHAIN_CHANGED"), UserHandle.of(UserHandle.myUserId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastTrustStoreChange() {
        sendBroadcastAsUser(new Intent("android.security.action.TRUST_STORE_CHANGED"), UserHandle.of(UserHandle.myUserId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastPermissionChange(int i, String str, boolean z) {
        String[] packagesForUid = getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null) {
            return;
        }
        for (String str2 : packagesForUid) {
            Intent intent = new Intent("android.security.action.KEY_ACCESS_CHANGED");
            intent.putExtra("android.security.extra.KEY_ALIAS", str);
            intent.putExtra("android.security.extra.KEY_ACCESSIBLE", z);
            intent.setPackage(str2);
            sendBroadcastAsUser(intent, UserHandle.of(UserHandle.myUserId()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String emptyOrBase64Encoded(byte[] bArr) {
        return bArr == null ? "" : Base64.encodeToString(bArr, 2);
    }

    @VisibleForTesting
    void setInjector(Injector injector) {
        this.mInjector = injector;
    }

    @VisibleForTesting
    static class Injector {
        Injector() {
        }

        public boolean isSecurityLoggingEnabled() {
            return SecurityLog.isLoggingEnabled();
        }

        public void writeSecurityEvent(int i, Object... objArr) {
            SecurityLog.writeEvent(i, objArr);
        }

        public int getCallingUid() {
            return Binder.getCallingUid();
        }
    }
}
