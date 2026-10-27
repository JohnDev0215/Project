package android.security.identity;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.security.KeyChain;
import android.security.identity.CredstoreResultData;
import android.security.keystore.KeyProperties;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
class CredstoreIdentityCredential extends IdentityCredential {
    private static final String TAG = "CredstoreIdentityCredential";
    private ICredential mBinder;
    private int mCipherSuite;
    private Context mContext;
    private String mCredentialName;
    private int mEphemeralCounter;
    private int mReadersExpectedEphemeralCounter;
    private KeyPair mEphemeralKeyPair = null;
    private SecretKey mSecretKey = null;
    private SecretKey mReaderSecretKey = null;
    private boolean mAllowUsingExhaustedKeys = true;
    private boolean mOperationHandleSet = false;
    private long mOperationHandle = 0;

    CredstoreIdentityCredential(Context context, String str, int i, ICredential iCredential) {
        this.mContext = context;
        this.mCredentialName = str;
        this.mCipherSuite = i;
        this.mBinder = iCredential;
    }

    private void ensureEphemeralKeyPair() {
        if (this.mEphemeralKeyPair != null) {
            return;
        }
        try {
            byte[] bArrCreateEphemeralKeyPair = this.mBinder.createEphemeralKeyPair();
            char[] cArr = new char[0];
            KeyStore keyStore = KeyStore.getInstance(KeyChain.EXTRA_PKCS12);
            keyStore.load(new ByteArrayInputStream(bArrCreateEphemeralKeyPair), cArr);
            this.mEphemeralKeyPair = new KeyPair(keyStore.getCertificate("ephemeralKey").getPublicKey(), (PrivateKey) keyStore.getKey("ephemeralKey", cArr));
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        } catch (IOException e3) {
            e = e3;
            throw new RuntimeException("Unexpected exception ", e);
        } catch (KeyStoreException e4) {
            e = e4;
            throw new RuntimeException("Unexpected exception ", e);
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            throw new RuntimeException("Unexpected exception ", e);
        } catch (UnrecoverableKeyException e6) {
            e = e6;
            throw new RuntimeException("Unexpected exception ", e);
        } catch (CertificateException e7) {
            e = e7;
            throw new RuntimeException("Unexpected exception ", e);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public KeyPair createEphemeralKeyPair() {
        ensureEphemeralKeyPair();
        return this.mEphemeralKeyPair;
    }

    @Override // android.security.identity.IdentityCredential
    public void setReaderEphemeralPublicKey(PublicKey publicKey) throws InvalidKeyException {
        try {
            this.mBinder.setReaderEphemeralPublicKey(Util.publicKeyEncodeUncompressedForm(publicKey));
            ensureEphemeralKeyPair();
            try {
                KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
                keyAgreement.init(this.mEphemeralKeyPair.getPrivate());
                keyAgreement.doPhase(publicKey, true);
                byte[] bArrGenerateSecret = keyAgreement.generateSecret();
                byte[] bArr = new byte[0];
                byte[] bArr2 = {1};
                this.mSecretKey = new SecretKeySpec(Util.computeHkdf("HmacSha256", bArrGenerateSecret, bArr2, bArr, 32), KeyProperties.KEY_ALGORITHM_AES);
                bArr2[0] = 0;
                this.mReaderSecretKey = new SecretKeySpec(Util.computeHkdf("HmacSha256", bArrGenerateSecret, bArr2, bArr, 32), KeyProperties.KEY_ALGORITHM_AES);
                this.mEphemeralCounter = 1;
                this.mReadersExpectedEphemeralCounter = 1;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error performing key agreement", e);
            }
        } catch (RemoteException e2) {
            throw new RuntimeException("Unexpected RemoteException ", e2);
        } catch (ServiceSpecificException e3) {
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e3.errorCode, e3);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public byte[] encryptMessageToReader(byte[] bArr) {
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(12);
            byteBufferAllocate.putInt(0, 0);
            byteBufferAllocate.putInt(4, 1);
            byteBufferAllocate.putInt(8, this.mEphemeralCounter);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, this.mSecretKey, new GCMParameterSpec(128, byteBufferAllocate.array()));
            byte[] bArrDoFinal = cipher.doFinal(bArr);
            this.mEphemeralCounter++;
            return bArrDoFinal;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new RuntimeException("Error encrypting message", e);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public byte[] decryptMessageFromReader(byte[] bArr) throws MessageDecryptionException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(12);
        byteBufferAllocate.putInt(0, 0);
        byteBufferAllocate.putInt(4, 0);
        byteBufferAllocate.putInt(8, this.mReadersExpectedEphemeralCounter);
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, this.mReaderSecretKey, new GCMParameterSpec(128, byteBufferAllocate.array()));
            byte[] bArrDoFinal = cipher.doFinal(bArr);
            this.mReadersExpectedEphemeralCounter++;
            return bArrDoFinal;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new MessageDecryptionException("Error decrypting message", e);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public Collection<X509Certificate> getCredentialKeyCertificateChain() {
        try {
            try {
                Collection<? extends Certificate> collectionGenerateCertificates = CertificateFactory.getInstance("X.509").generateCertificates(new ByteArrayInputStream(this.mBinder.getCredentialKeyCertificateChain()));
                LinkedList linkedList = new LinkedList();
                Iterator<? extends Certificate> it = collectionGenerateCertificates.iterator();
                while (it.hasNext()) {
                    linkedList.add((X509Certificate) it.next());
                }
                return linkedList;
            } catch (CertificateException e) {
                throw new RuntimeException("Error decoding certificates", e);
            }
        } catch (RemoteException e2) {
            throw new RuntimeException("Unexpected RemoteException ", e2);
        } catch (ServiceSpecificException e3) {
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e3.errorCode, e3);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public void setAllowUsingExhaustedKeys(boolean z) {
        this.mAllowUsingExhaustedKeys = z;
    }

    @Override // android.security.identity.IdentityCredential
    public long getCredstoreOperationHandle() {
        if (!this.mOperationHandleSet) {
            try {
                this.mOperationHandle = this.mBinder.selectAuthKey(this.mAllowUsingExhaustedKeys);
                this.mOperationHandleSet = true;
            } catch (RemoteException e) {
                throw new RuntimeException("Unexpected RemoteException ", e);
            } catch (ServiceSpecificException e2) {
                int i = e2.errorCode;
                throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
            }
        }
        return this.mOperationHandle;
    }

    @Override // android.security.identity.IdentityCredential
    public ResultData getEntries(byte[] bArr, Map<String, Collection<String>> map, byte[] bArr2, byte[] bArr3) throws InvalidReaderSignatureException, EphemeralPublicKeyNotFoundException, SessionTranscriptMismatchException, InvalidRequestMessageException, NoAuthenticationKeyAvailableException {
        RequestNamespaceParcel[] requestNamespaceParcelArr = new RequestNamespaceParcel[map.size()];
        int i = 0;
        for (String str : map.keySet()) {
            Collection<String> collection = map.get(str);
            requestNamespaceParcelArr[i] = new RequestNamespaceParcel();
            requestNamespaceParcelArr[i].namespaceName = str;
            requestNamespaceParcelArr[i].entries = new RequestEntryParcel[collection.size()];
            int i2 = 0;
            for (String str2 : collection) {
                requestNamespaceParcelArr[i].entries[i2] = new RequestEntryParcel();
                requestNamespaceParcelArr[i].entries[i2].name = str2;
                i2++;
            }
            i++;
        }
        try {
            ICredential iCredential = this.mBinder;
            if (bArr == null) {
                bArr = new byte[0];
            }
            byte[] bArr4 = bArr;
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr5 = bArr2;
            if (bArr3 == null) {
                bArr3 = new byte[0];
            }
            GetEntriesResultParcel entries = iCredential.getEntries(bArr4, requestNamespaceParcelArr, bArr5, bArr3, this.mAllowUsingExhaustedKeys);
            byte[] bArr6 = entries.mac;
            if (bArr6 != null && bArr6.length == 0) {
                bArr6 = null;
            }
            CredstoreResultData.Builder builder = new CredstoreResultData.Builder(entries.staticAuthenticationData, entries.deviceNameSpaces, bArr6);
            for (ResultNamespaceParcel resultNamespaceParcel : entries.resultNamespaces) {
                for (ResultEntryParcel resultEntryParcel : resultNamespaceParcel.entries) {
                    if (resultEntryParcel.status == 0) {
                        builder.addEntry(resultNamespaceParcel.namespaceName, resultEntryParcel.name, resultEntryParcel.value);
                    } else {
                        builder.addErrorStatus(resultNamespaceParcel.namespaceName, resultEntryParcel.name, resultEntryParcel.status);
                    }
                }
            }
            return builder.build();
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            if (e2.errorCode == 5) {
                throw new EphemeralPublicKeyNotFoundException(e2.getMessage(), e2);
            }
            if (e2.errorCode == 7) {
                throw new InvalidReaderSignatureException(e2.getMessage(), e2);
            }
            if (e2.errorCode == 6) {
                throw new NoAuthenticationKeyAvailableException(e2.getMessage(), e2);
            }
            if (e2.errorCode == 10) {
                throw new InvalidRequestMessageException(e2.getMessage(), e2);
            }
            if (e2.errorCode == 11) {
                throw new SessionTranscriptMismatchException(e2.getMessage(), e2);
            }
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public void setAvailableAuthenticationKeys(int i, int i2) {
        try {
            this.mBinder.setAvailableAuthenticationKeys(i, i2);
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public Collection<X509Certificate> getAuthKeysNeedingCertification() {
        try {
            AuthKeyParcel[] authKeysNeedingCertification = this.mBinder.getAuthKeysNeedingCertification();
            LinkedList linkedList = new LinkedList();
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            for (AuthKeyParcel authKeyParcel : authKeysNeedingCertification) {
                Collection<? extends Certificate> collectionGenerateCertificates = certificateFactory.generateCertificates(new ByteArrayInputStream(authKeyParcel.x509cert));
                if (collectionGenerateCertificates.size() != 1) {
                    throw new RuntimeException("Returned blob yields more than one X509 cert");
                }
                linkedList.add((X509Certificate) collectionGenerateCertificates.iterator().next());
            }
            return linkedList;
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        } catch (CertificateException e3) {
            throw new RuntimeException("Error decoding authenticationKey", e3);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public void storeStaticAuthenticationData(X509Certificate x509Certificate, byte[] bArr) throws UnknownAuthenticationKeyException {
        try {
            AuthKeyParcel authKeyParcel = new AuthKeyParcel();
            authKeyParcel.x509cert = x509Certificate.getEncoded();
            this.mBinder.storeStaticAuthenticationData(authKeyParcel, bArr);
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            if (e2.errorCode == 9) {
                throw new UnknownAuthenticationKeyException(e2.getMessage(), e2);
            }
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        } catch (CertificateEncodingException e3) {
            throw new RuntimeException("Error encoding authenticationKey", e3);
        }
    }

    @Override // android.security.identity.IdentityCredential
    public int[] getAuthenticationDataUsageCount() {
        try {
            return this.mBinder.getAuthenticationDataUsageCount();
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        }
    }
}
