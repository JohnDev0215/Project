package android.security;

import android.app.ActivityThread;
import android.app.Application;
import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.os.UserHandle;
import android.security.keymaster.ExportResult;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.KeymasterBlob;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keymaster.KeymasterDefs;
import android.security.keymaster.OperationResult;
import android.security.keystore.IKeystoreCertificateChainCallback;
import android.security.keystore.IKeystoreExportKeyCallback;
import android.security.keystore.IKeystoreKeyCharacteristicsCallback;
import android.security.keystore.IKeystoreOperationResultCallback;
import android.security.keystore.IKeystoreResponseCallback;
import android.security.keystore.IKeystoreService;
import android.security.keystore.KeyExpiredException;
import android.security.keystore.KeyNotYetValidException;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.security.keystore.KeystoreResponse;
import android.security.keystore.UserNotAuthenticatedException;
import android.util.Log;
import com.android.org.bouncycastle.asn1.ASN1InputStream;
import com.android.org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.ToIntFunction;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AlgorithmId;

/* JADX INFO: loaded from: classes3.dex */
public class KeyStore {
    public static final int CANNOT_ATTEST_IDS = -66;
    public static final int CONFIRMATIONUI_ABORTED = 2;
    public static final int CONFIRMATIONUI_CANCELED = 1;
    public static final int CONFIRMATIONUI_IGNORED = 4;
    public static final int CONFIRMATIONUI_OK = 0;
    public static final int CONFIRMATIONUI_OPERATION_PENDING = 3;
    public static final int CONFIRMATIONUI_SYSTEM_ERROR = 5;
    public static final int CONFIRMATIONUI_UIERROR = 65536;
    public static final int CONFIRMATIONUI_UIERROR_MALFORMED_UTF8_ENCODING = 65539;
    public static final int CONFIRMATIONUI_UIERROR_MESSAGE_TOO_LONG = 65538;
    public static final int CONFIRMATIONUI_UIERROR_MISSING_GLYPH = 65537;
    public static final int CONFIRMATIONUI_UNEXPECTED = 7;
    public static final int CONFIRMATIONUI_UNIMPLEMENTED = 6;
    public static final int FLAG_CRITICAL_TO_DEVICE_ENCRYPTION = 8;
    public static final int FLAG_ENCRYPTED = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_SOFTWARE = 2;
    public static final int FLAG_STRONGBOX = 16;
    public static final int HARDWARE_TYPE_UNAVAILABLE = -68;
    public static final int KEY_ALREADY_EXISTS = 16;
    public static final int KEY_NOT_FOUND = 7;
    public static final int KEY_PERMANENTLY_INVALIDATED = 17;
    public static final int LOCKED = 2;
    public static final int NO_ERROR = 1;
    public static final int OP_AUTH_NEEDED = 15;
    public static final int PERMISSION_DENIED = 6;
    public static final int PROTOCOL_ERROR = 5;
    public static final int SYSTEM_ERROR = 4;
    private static final String TAG = "KeyStore";
    public static final int UID_SELF = -1;
    public static final int UNDEFINED_ACTION = 9;
    public static final int UNINITIALIZED = 3;
    public static final int VALUE_CORRUPTED = 8;
    public static final int WRONG_PASSWORD = 10;
    private final IKeystoreService mBinder;
    private IBinder mToken;
    private int mError = 1;
    private final Context mContext = getApplicationContext();

    public enum State {
        UNLOCKED,
        LOCKED,
        UNINITIALIZED
    }

    private KeyStore(IKeystoreService iKeystoreService) {
        this.mBinder = iKeystoreService;
    }

    public static Context getApplicationContext() {
        Application applicationCurrentApplication = ActivityThread.currentApplication();
        if (applicationCurrentApplication != null) {
            return applicationCurrentApplication;
        }
        throw new IllegalStateException("Failed to obtain application Context from ActivityThread");
    }

    public static KeyStore getInstance() {
        return new KeyStore(IKeystoreService.Stub.asInterface(ServiceManager.getService("android.security.keystore")));
    }

    private synchronized IBinder getToken() {
        if (this.mToken == null) {
            this.mToken = new Binder();
        }
        return this.mToken;
    }

    public State state(int i) {
        try {
            int state = this.mBinder.getState(i);
            if (state == 1) {
                return State.UNLOCKED;
            }
            if (state == 2) {
                return State.LOCKED;
            }
            if (state == 3) {
                return State.UNINITIALIZED;
            }
            throw new AssertionError(this.mError);
        } catch (RemoteException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            throw new AssertionError(e);
        } catch (NullPointerException e2) {
            Log.w(TAG, "state: keystore null ", e2);
            return State.UNINITIALIZED;
        }
    }

    public State state() {
        return state(UserHandle.myUserId());
    }

    public boolean isUnlocked() {
        return state() == State.UNLOCKED;
    }

    public byte[] get(String str, int i) {
        return get(str, i, false);
    }

    public byte[] get(String str) {
        return get(str, -1);
    }

    public byte[] get(String str, int i, boolean z) {
        if (str == null) {
            str = "";
        }
        try {
            return this.mBinder.get(str, i);
        } catch (RemoteException e) {
            e = e;
            Log.w(TAG, "Cannot connect to keystore", e);
            return null;
        } catch (ServiceSpecificException e2) {
            if (!z || e2.errorCode != 7) {
                Log.w(TAG, "KeyStore exception", e2);
            }
            return null;
        } catch (NullPointerException e3) {
            e = e3;
            Log.w(TAG, "Cannot connect to keystore", e);
            return null;
        }
    }

    public byte[] get(String str, boolean z) {
        return get(str, -1, z);
    }

    public boolean put(String str, byte[] bArr, int i, int i2) {
        return insert(str, bArr, i, i2) == 1;
    }

    public int insert(String str, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (RemoteException | NullPointerException e) {
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            }
        }
        int iInsert = this.mBinder.insert(str, bArr, i, i2);
        if (iInsert != 16) {
            return iInsert;
        }
        this.mBinder.del(str, i);
        return this.mBinder.insert(str, bArr, i, i2);
    }

    int delete2(String str, int i) {
        try {
            return this.mBinder.del(str, i);
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return 4;
        }
    }

    public boolean delete(String str, int i) {
        int iDelete2 = delete2(str, i);
        return iDelete2 == 1 || iDelete2 == 7;
    }

    public boolean delete(String str) {
        return delete(str, -1);
    }

    public boolean contains(String str, int i) {
        try {
            return this.mBinder.exist(str, i) == 1;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public boolean contains(String str) {
        return contains(str, -1);
    }

    public String[] list(String str, int i) {
        try {
            return this.mBinder.list(str, i);
        } catch (RemoteException e) {
            e = e;
            Log.w(TAG, "Cannot connect to keystore", e);
            return null;
        } catch (ServiceSpecificException e2) {
            Log.w(TAG, "KeyStore exception", e2);
            return null;
        } catch (NullPointerException e3) {
            e = e3;
            Log.w(TAG, "Cannot connect to keystore", e);
            return null;
        }
    }

    public int[] listUidsOfAuthBoundKeys() {
        ArrayList arrayList = new ArrayList();
        try {
            int iListUidsOfAuthBoundKeys = this.mBinder.listUidsOfAuthBoundKeys(arrayList);
            if (iListUidsOfAuthBoundKeys == 1) {
                return arrayList.stream().mapToInt(new ToIntFunction() { // from class: android.security.-$$Lambda$KeyStore$wddj3-hVVrg0MkscpMtYt3BzY8Y
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return Integer.parseInt((String) obj);
                    }
                }).toArray();
            }
            Log.w(TAG, String.format("listUidsOfAuthBoundKeys failed with error code %d", Integer.valueOf(iListUidsOfAuthBoundKeys)));
            return null;
        } catch (RemoteException e) {
            e = e;
            Log.w(TAG, "Cannot connect to keystore", e);
            return null;
        } catch (ServiceSpecificException e2) {
            Log.w(TAG, "KeyStore exception", e2);
            return null;
        } catch (NullPointerException e3) {
            e = e3;
            Log.w(TAG, "Cannot connect to keystore", e);
            return null;
        }
    }

    public String[] list(String str) {
        return list(str, -1);
    }

    public boolean lock(int i) {
        try {
            return this.mBinder.lock(i) == 1;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public boolean lock() {
        return lock(UserHandle.myUserId());
    }

    public boolean unlock(int i, String str) {
        if (str == null) {
            str = "";
        }
        try {
            int iUnlock = this.mBinder.unlock(i, str);
            this.mError = iUnlock;
            return iUnlock == 1;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public boolean unlock(String str) {
        return unlock(UserHandle.getUserId(Process.myUid()), str);
    }

    public boolean isEmpty(int i) {
        try {
            return this.mBinder.isEmpty(i) != 0;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public boolean isEmpty() {
        return isEmpty(UserHandle.myUserId());
    }

    public String grant(String str, int i) {
        try {
            String strGrant = this.mBinder.grant(str, i);
            if (strGrant == "") {
                return null;
            }
            return strGrant;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return null;
        }
    }

    public boolean ungrant(String str, int i) {
        try {
            return this.mBinder.ungrant(str, i) == 1;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public long getmtime(String str, int i) {
        try {
            long j = this.mBinder.getmtime(str, i);
            if (j == -1) {
                return -1L;
            }
            return j * 1000;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return -1L;
        }
    }

    public long getmtime(String str) {
        return getmtime(str, -1);
    }

    public boolean isHardwareBacked() {
        return isHardwareBacked(KeyProperties.KEY_ALGORITHM_RSA);
    }

    public boolean isHardwareBacked(String str) {
        try {
            return this.mBinder.is_hardware_backed(str.toUpperCase(Locale.US)) == 1;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public boolean clearUid(int i) {
        try {
            return this.mBinder.clear_uid((long) i) == 1;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public int getLastError() {
        return this.mError;
    }

    public boolean addRngEntropy(byte[] bArr, int i) {
        KeystoreResultPromise keystoreResultPromise = new KeystoreResultPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(keystoreResultPromise, 0);
                if (this.mBinder.addRngEntropy(keystoreResultPromise, bArr, i) == 1) {
                    return ((KeystoreResponse) interruptedPreservingGet(keystoreResultPromise.getFuture())).getErrorCode() == 1;
                }
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
            } catch (ExecutionException e3) {
                Log.e(TAG, "AddRngEntropy completed with exception", e3);
            }
            return false;
        } finally {
            this.mBinder.asBinder().unlinkToDeath(keystoreResultPromise, 0);
        }
    }

    private class KeyCharacteristicsCallbackResult {
        private KeyCharacteristics keyCharacteristics;
        private KeystoreResponse keystoreResponse;

        public KeyCharacteristicsCallbackResult(KeystoreResponse keystoreResponse, KeyCharacteristics keyCharacteristics) {
            this.keystoreResponse = keystoreResponse;
            this.keyCharacteristics = keyCharacteristics;
        }

        public KeystoreResponse getKeystoreResponse() {
            return this.keystoreResponse;
        }

        public void setKeystoreResponse(KeystoreResponse keystoreResponse) {
            this.keystoreResponse = keystoreResponse;
        }

        public KeyCharacteristics getKeyCharacteristics() {
            return this.keyCharacteristics;
        }

        public void setKeyCharacteristics(KeyCharacteristics keyCharacteristics) {
            this.keyCharacteristics = keyCharacteristics;
        }
    }

    private class KeyCharacteristicsPromise extends IKeystoreKeyCharacteristicsCallback.Stub implements IBinder.DeathRecipient {
        private final CompletableFuture<KeyCharacteristicsCallbackResult> future;

        private KeyCharacteristicsPromise() {
            this.future = new CompletableFuture<>();
        }

        @Override // android.security.keystore.IKeystoreKeyCharacteristicsCallback
        public void onFinished(KeystoreResponse keystoreResponse, KeyCharacteristics keyCharacteristics) throws RemoteException {
            this.future.complete(KeyStore.this.new KeyCharacteristicsCallbackResult(keystoreResponse, keyCharacteristics));
        }

        public final CompletableFuture<KeyCharacteristicsCallbackResult> getFuture() {
            return this.future;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.future.completeExceptionally(new RemoteException("Keystore died"));
        }
    }

    private int generateKeyInternal(String str, KeymasterArguments keymasterArguments, byte[] bArr, int i, int i2, KeyCharacteristics keyCharacteristics) throws ExecutionException, RemoteException {
        KeyCharacteristicsPromise keyCharacteristicsPromise = new KeyCharacteristicsPromise();
        try {
            this.mBinder.asBinder().linkToDeath(keyCharacteristicsPromise, 0);
            int iGenerateKey = this.mBinder.generateKey(keyCharacteristicsPromise, str, keymasterArguments, bArr, i, i2);
            if (iGenerateKey != 1) {
                Log.e(TAG, "generateKeyInternal failed on request " + iGenerateKey);
                return iGenerateKey;
            }
            KeyCharacteristicsCallbackResult keyCharacteristicsCallbackResult = (KeyCharacteristicsCallbackResult) interruptedPreservingGet(keyCharacteristicsPromise.getFuture());
            this.mBinder.asBinder().unlinkToDeath(keyCharacteristicsPromise, 0);
            int errorCode = keyCharacteristicsCallbackResult.getKeystoreResponse().getErrorCode();
            if (errorCode != 1) {
                Log.e(TAG, "generateKeyInternal failed on response " + errorCode);
                return errorCode;
            }
            KeyCharacteristics keyCharacteristics2 = keyCharacteristicsCallbackResult.getKeyCharacteristics();
            if (keyCharacteristics2 == null) {
                Log.e(TAG, "generateKeyInternal got empty key characteristics " + errorCode);
                return 4;
            }
            keyCharacteristics.shallowCopyFrom(keyCharacteristics2);
            return 1;
        } finally {
            this.mBinder.asBinder().unlinkToDeath(keyCharacteristicsPromise, 0);
        }
    }

    public int generateKey(String str, KeymasterArguments keymasterArguments, byte[] bArr, int i, int i2, KeyCharacteristics keyCharacteristics) {
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (ExecutionException e3) {
                Log.e(TAG, "generateKey completed with exception", e3);
                return 4;
            }
        }
        if (keymasterArguments == null) {
            keymasterArguments = new KeymasterArguments();
        }
        int iGenerateKeyInternal = generateKeyInternal(str, keymasterArguments, bArr, i, i2, keyCharacteristics);
        if (iGenerateKeyInternal != 16) {
            return iGenerateKeyInternal;
        }
        this.mBinder.del(str, i);
        return generateKeyInternal(str, keymasterArguments, bArr, i, i2, keyCharacteristics);
    }

    public int generateKey(String str, KeymasterArguments keymasterArguments, byte[] bArr, int i, KeyCharacteristics keyCharacteristics) {
        return generateKey(str, keymasterArguments, bArr, -1, i, keyCharacteristics);
    }

    public int getKeyCharacteristics(String str, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i, KeyCharacteristics keyCharacteristics) {
        int keyCharacteristics2;
        KeyCharacteristicsPromise keyCharacteristicsPromise = new KeyCharacteristicsPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(keyCharacteristicsPromise, 0);
                if (keymasterBlob == null) {
                    keymasterBlob = new KeymasterBlob(new byte[0]);
                }
                KeymasterBlob keymasterBlob3 = keymasterBlob;
                if (keymasterBlob2 == null) {
                    keymasterBlob2 = new KeymasterBlob(new byte[0]);
                }
                keyCharacteristics2 = this.mBinder.getKeyCharacteristics(keyCharacteristicsPromise, str, keymasterBlob3, keymasterBlob2, i);
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
            } catch (ExecutionException e3) {
                Log.e(TAG, "GetKeyCharacteristics completed with exception", e3);
            }
            if (keyCharacteristics2 != 1) {
                return keyCharacteristics2;
            }
            KeyCharacteristicsCallbackResult keyCharacteristicsCallbackResult = (KeyCharacteristicsCallbackResult) interruptedPreservingGet(keyCharacteristicsPromise.getFuture());
            int errorCode = keyCharacteristicsCallbackResult.getKeystoreResponse().getErrorCode();
            if (errorCode != 1) {
                return errorCode;
            }
            KeyCharacteristics keyCharacteristics3 = keyCharacteristicsCallbackResult.getKeyCharacteristics();
            if (keyCharacteristics3 != null) {
                keyCharacteristics.shallowCopyFrom(keyCharacteristics3);
                return 1;
            }
            return 4;
        } finally {
            this.mBinder.asBinder().unlinkToDeath(keyCharacteristicsPromise, 0);
        }
    }

    public int getKeyCharacteristics(String str, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, KeyCharacteristics keyCharacteristics) {
        return getKeyCharacteristics(str, keymasterBlob, keymasterBlob2, -1, keyCharacteristics);
    }

    private int importKeyInternal(String str, KeymasterArguments keymasterArguments, int i, byte[] bArr, int i2, int i3, KeyCharacteristics keyCharacteristics) throws ExecutionException, RemoteException {
        KeyCharacteristicsPromise keyCharacteristicsPromise = new KeyCharacteristicsPromise();
        this.mBinder.asBinder().linkToDeath(keyCharacteristicsPromise, 0);
        try {
            int iImportKey = this.mBinder.importKey(keyCharacteristicsPromise, str, keymasterArguments, i, bArr, i2, i3);
            if (iImportKey == 1) {
                KeyCharacteristicsCallbackResult keyCharacteristicsCallbackResult = (KeyCharacteristicsCallbackResult) interruptedPreservingGet(keyCharacteristicsPromise.getFuture());
                int errorCode = keyCharacteristicsCallbackResult.getKeystoreResponse().getErrorCode();
                if (errorCode != 1) {
                    return errorCode;
                }
                KeyCharacteristics keyCharacteristics2 = keyCharacteristicsCallbackResult.getKeyCharacteristics();
                if (keyCharacteristics2 != null) {
                    keyCharacteristics.shallowCopyFrom(keyCharacteristics2);
                    return 1;
                }
                iImportKey = 4;
            }
            return iImportKey;
        } finally {
            this.mBinder.asBinder().unlinkToDeath(keyCharacteristicsPromise, 0);
        }
    }

    public int importKey(String str, KeymasterArguments keymasterArguments, int i, byte[] bArr, int i2, int i3, KeyCharacteristics keyCharacteristics) {
        try {
            int iImportKeyInternal = importKeyInternal(str, keymasterArguments, i, bArr, i2, i3, keyCharacteristics);
            if (iImportKeyInternal != 16) {
                return iImportKeyInternal;
            }
            this.mBinder.del(str, i2);
            return importKeyInternal(str, keymasterArguments, i, bArr, i2, i3, keyCharacteristics);
        } catch (RemoteException e) {
            e = e;
            Log.w(TAG, "Cannot connect to keystore", e);
            return 4;
        } catch (NullPointerException e2) {
            e = e2;
            Log.w(TAG, "Cannot connect to keystore", e);
            return 4;
        } catch (ExecutionException e3) {
            Log.e(TAG, "ImportKey completed with exception", e3);
            return 4;
        }
    }

    public int importKey(String str, KeymasterArguments keymasterArguments, int i, byte[] bArr, int i2, KeyCharacteristics keyCharacteristics) {
        return importKey(str, keymasterArguments, i, bArr, -1, i2, keyCharacteristics);
    }

    private String getAlgorithmFromPKCS8(byte[] bArr) {
        try {
            return new AlgorithmId(new ObjectIdentifier(PrivateKeyInfo.getInstance(new ASN1InputStream(new ByteArrayInputStream(bArr)).readObject()).getPrivateKeyAlgorithm().getAlgorithm().getId())).getName();
        } catch (IOException e) {
            Log.e(TAG, "getAlgorithmFromPKCS8 Failed to parse key data");
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    private KeymasterArguments makeLegacyArguments(String str) {
        KeymasterArguments keymasterArguments = new KeymasterArguments();
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_ALGORITHM, KeyProperties.KeyAlgorithm.toKeymasterAsymmetricKeyAlgorithm(str));
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PURPOSE, 2);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PURPOSE, 3);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PURPOSE, 0);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PURPOSE, 1);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PADDING, 1);
        if (str.equalsIgnoreCase(KeyProperties.KEY_ALGORITHM_RSA)) {
            keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PADDING, 2);
            keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PADDING, 4);
            keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PADDING, 5);
            keymasterArguments.addEnum(KeymasterDefs.KM_TAG_PADDING, 3);
        }
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_DIGEST, 0);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_DIGEST, 1);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_DIGEST, 2);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_DIGEST, 3);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_DIGEST, 4);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_DIGEST, 5);
        keymasterArguments.addEnum(KeymasterDefs.KM_TAG_DIGEST, 6);
        keymasterArguments.addBoolean(KeymasterDefs.KM_TAG_NO_AUTH_REQUIRED);
        keymasterArguments.addDate(KeymasterDefs.KM_TAG_ORIGINATION_EXPIRE_DATETIME, new Date(Long.MAX_VALUE));
        keymasterArguments.addDate(KeymasterDefs.KM_TAG_USAGE_EXPIRE_DATETIME, new Date(Long.MAX_VALUE));
        keymasterArguments.addDate(KeymasterDefs.KM_TAG_ACTIVE_DATETIME, new Date(0L));
        return keymasterArguments;
    }

    public boolean importKey(String str, byte[] bArr, int i, int i2) {
        String algorithmFromPKCS8 = getAlgorithmFromPKCS8(bArr);
        if (algorithmFromPKCS8 == null) {
            return false;
        }
        int iImportKey = importKey(str, makeLegacyArguments(algorithmFromPKCS8), 1, bArr, i, i2, new KeyCharacteristics());
        if (iImportKey == 1) {
            return true;
        }
        Log.e(TAG, Log.getStackTraceString(new KeyStoreException(iImportKey, "legacy key import failed")));
        return false;
    }

    private int importWrappedKeyInternal(String str, byte[] bArr, String str2, byte[] bArr2, KeymasterArguments keymasterArguments, long j, long j2, KeyCharacteristics keyCharacteristics) throws ExecutionException, RemoteException {
        KeyCharacteristicsPromise keyCharacteristicsPromise = new KeyCharacteristicsPromise();
        this.mBinder.asBinder().linkToDeath(keyCharacteristicsPromise, 0);
        try {
            int iImportWrappedKey = this.mBinder.importWrappedKey(keyCharacteristicsPromise, str, bArr, str2, bArr2, keymasterArguments, j, j2);
            if (iImportWrappedKey == 1) {
                KeyCharacteristicsCallbackResult keyCharacteristicsCallbackResult = (KeyCharacteristicsCallbackResult) interruptedPreservingGet(keyCharacteristicsPromise.getFuture());
                int errorCode = keyCharacteristicsCallbackResult.getKeystoreResponse().getErrorCode();
                if (errorCode != 1) {
                    return errorCode;
                }
                KeyCharacteristics keyCharacteristics2 = keyCharacteristicsCallbackResult.getKeyCharacteristics();
                if (keyCharacteristics2 != null) {
                    keyCharacteristics.shallowCopyFrom(keyCharacteristics2);
                    return 1;
                }
                iImportWrappedKey = 4;
            }
            return iImportWrappedKey;
        } finally {
            this.mBinder.asBinder().unlinkToDeath(keyCharacteristicsPromise, 0);
        }
    }

    public int importWrappedKey(String str, byte[] bArr, String str2, byte[] bArr2, KeymasterArguments keymasterArguments, long j, long j2, int i, KeyCharacteristics keyCharacteristics) {
        try {
            int iImportWrappedKeyInternal = importWrappedKeyInternal(str, bArr, str2, bArr2, keymasterArguments, j, j2, keyCharacteristics);
            if (iImportWrappedKeyInternal != 16) {
                return iImportWrappedKeyInternal;
            }
            try {
                this.mBinder.del(str, -1);
                return importWrappedKeyInternal(str, bArr, str2, bArr2, keymasterArguments, j, j2, keyCharacteristics);
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (ExecutionException e3) {
                e = e3;
                Log.e(TAG, "ImportWrappedKey completed with exception", e);
                return 4;
            }
        } catch (RemoteException e4) {
            e = e4;
            Log.w(TAG, "Cannot connect to keystore", e);
            return 4;
        } catch (NullPointerException e5) {
            e = e5;
            Log.w(TAG, "Cannot connect to keystore", e);
            return 4;
        } catch (ExecutionException e6) {
            e = e6;
        }
    }

    private class ExportKeyPromise extends IKeystoreExportKeyCallback.Stub implements IBinder.DeathRecipient {
        private final CompletableFuture<ExportResult> future;

        private ExportKeyPromise() {
            this.future = new CompletableFuture<>();
        }

        @Override // android.security.keystore.IKeystoreExportKeyCallback
        public void onFinished(ExportResult exportResult) throws RemoteException {
            this.future.complete(exportResult);
        }

        public final CompletableFuture<ExportResult> getFuture() {
            return this.future;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.future.completeExceptionally(new RemoteException("Keystore died"));
        }
    }

    public ExportResult exportKey(String str, int i, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i2) {
        ExportKeyPromise exportKeyPromise = new ExportKeyPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(exportKeyPromise, 0);
                int iExportKey = this.mBinder.exportKey(exportKeyPromise, str, i, keymasterBlob != null ? keymasterBlob : new KeymasterBlob(new byte[0]), keymasterBlob2 != null ? keymasterBlob2 : new KeymasterBlob(new byte[0]), i2);
                return iExportKey == 1 ? (ExportResult) interruptedPreservingGet(exportKeyPromise.getFuture()) : new ExportResult(iExportKey);
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (ExecutionException e3) {
                Log.e(TAG, "ExportKey completed with exception", e3);
                return null;
            }
        } finally {
            this.mBinder.asBinder().unlinkToDeath(exportKeyPromise, 0);
        }
    }

    public ExportResult exportKey(String str, int i, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2) {
        return exportKey(str, i, keymasterBlob, keymasterBlob2, -1);
    }

    private class OperationPromise extends IKeystoreOperationResultCallback.Stub implements IBinder.DeathRecipient {
        private final CompletableFuture<OperationResult> future;

        private OperationPromise() {
            this.future = new CompletableFuture<>();
        }

        @Override // android.security.keystore.IKeystoreOperationResultCallback
        public void onFinished(OperationResult operationResult) throws RemoteException {
            this.future.complete(operationResult);
        }

        public final CompletableFuture<OperationResult> getFuture() {
            return this.future;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.future.completeExceptionally(new RemoteException("Keystore died"));
        }
    }

    public OperationResult begin(String str, int i, boolean z, KeymasterArguments keymasterArguments, byte[] bArr, int i2) {
        OperationPromise operationPromise = new OperationPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(operationPromise, 0);
                int iBegin = this.mBinder.begin(operationPromise, getToken(), str, i, z, keymasterArguments != null ? keymasterArguments : new KeymasterArguments(), bArr != null ? bArr : new byte[0], i2);
                return iBegin == 1 ? (OperationResult) interruptedPreservingGet(operationPromise.getFuture()) : new OperationResult(iBegin);
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (ExecutionException e3) {
                Log.e(TAG, "Begin completed with exception", e3);
                return null;
            }
        } finally {
            this.mBinder.asBinder().unlinkToDeath(operationPromise, 0);
        }
    }

    public OperationResult begin(String str, int i, boolean z, KeymasterArguments keymasterArguments, byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        byte[] bArr2 = bArr;
        if (keymasterArguments == null) {
            keymasterArguments = new KeymasterArguments();
        }
        return begin(str, i, z, keymasterArguments, bArr2, -1);
    }

    public OperationResult update(IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr) {
        OperationPromise operationPromise = new OperationPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(operationPromise, 0);
                if (keymasterArguments == null) {
                    keymasterArguments = new KeymasterArguments();
                }
                if (bArr == null) {
                    bArr = new byte[0];
                }
                int iUpdate = this.mBinder.update(operationPromise, iBinder, keymasterArguments, bArr);
                return iUpdate == 1 ? (OperationResult) interruptedPreservingGet(operationPromise.getFuture()) : new OperationResult(iUpdate);
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (ExecutionException e3) {
                Log.e(TAG, "Update completed with exception", e3);
                return null;
            }
        } finally {
            this.mBinder.asBinder().unlinkToDeath(operationPromise, 0);
        }
    }

    public OperationResult finish(IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        OperationPromise operationPromise = new OperationPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(operationPromise, 0);
                int iFinish = this.mBinder.finish(operationPromise, iBinder, keymasterArguments != null ? keymasterArguments : new KeymasterArguments(), bArr != null ? bArr : new byte[0], bArr2 != null ? bArr2 : new byte[0], bArr3 != null ? bArr3 : new byte[0]);
                return iFinish == 1 ? (OperationResult) interruptedPreservingGet(operationPromise.getFuture()) : new OperationResult(iFinish);
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return null;
            } catch (ExecutionException e3) {
                Log.e(TAG, "Finish completed with exception", e3);
                return null;
            }
        } finally {
            this.mBinder.asBinder().unlinkToDeath(operationPromise, 0);
        }
    }

    public OperationResult finish(IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr) {
        return finish(iBinder, keymasterArguments, null, bArr, null);
    }

    private class KeystoreResultPromise extends IKeystoreResponseCallback.Stub implements IBinder.DeathRecipient {
        private final CompletableFuture<KeystoreResponse> future;

        private KeystoreResultPromise() {
            this.future = new CompletableFuture<>();
        }

        @Override // android.security.keystore.IKeystoreResponseCallback
        public void onFinished(KeystoreResponse keystoreResponse) throws RemoteException {
            this.future.complete(keystoreResponse);
        }

        public final CompletableFuture<KeystoreResponse> getFuture() {
            return this.future;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.future.completeExceptionally(new RemoteException("Keystore died"));
        }
    }

    public int abort(IBinder iBinder) {
        KeystoreResultPromise keystoreResultPromise = new KeystoreResultPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(keystoreResultPromise, 0);
                int iAbort = this.mBinder.abort(keystoreResultPromise, iBinder);
                if (iAbort == 1) {
                    iAbort = ((KeystoreResponse) interruptedPreservingGet(keystoreResultPromise.getFuture())).getErrorCode();
                }
                return iAbort;
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (ExecutionException e3) {
                Log.e(TAG, "Abort completed with exception", e3);
                return 4;
            }
        } finally {
            this.mBinder.asBinder().unlinkToDeath(keystoreResultPromise, 0);
        }
    }

    public int addAuthToken(byte[] bArr) {
        try {
            return this.mBinder.addAuthToken(bArr);
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return 4;
        }
    }

    public boolean onUserPasswordChanged(int i, String str) {
        if (str == null) {
            str = "";
        }
        try {
            return this.mBinder.onUserPasswordChanged(i, str) == 1;
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public void onUserAdded(int i, int i2) {
        try {
            this.mBinder.onUserAdded(i, i2);
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
        }
    }

    public void onUserAdded(int i) {
        onUserAdded(i, -1);
    }

    public void onUserRemoved(int i) {
        try {
            this.mBinder.onUserRemoved(i);
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
        }
    }

    public boolean onUserPasswordChanged(String str) {
        return onUserPasswordChanged(UserHandle.getUserId(Process.myUid()), str);
    }

    public void onUserLockedStateChanged(int i, boolean z) {
        try {
            this.mBinder.onKeyguardVisibilityChanged(z, i);
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Failed to update user locked state " + i, e);
        }
    }

    private class KeyAttestationCallbackResult {
        private KeymasterCertificateChain certificateChain;
        private KeystoreResponse keystoreResponse;

        public KeyAttestationCallbackResult(KeystoreResponse keystoreResponse, KeymasterCertificateChain keymasterCertificateChain) {
            this.keystoreResponse = keystoreResponse;
            this.certificateChain = keymasterCertificateChain;
        }

        public KeystoreResponse getKeystoreResponse() {
            return this.keystoreResponse;
        }

        public void setKeystoreResponse(KeystoreResponse keystoreResponse) {
            this.keystoreResponse = keystoreResponse;
        }

        public KeymasterCertificateChain getCertificateChain() {
            return this.certificateChain;
        }

        public void setCertificateChain(KeymasterCertificateChain keymasterCertificateChain) {
            this.certificateChain = keymasterCertificateChain;
        }
    }

    private class CertificateChainPromise extends IKeystoreCertificateChainCallback.Stub implements IBinder.DeathRecipient {
        private final CompletableFuture<KeyAttestationCallbackResult> future;

        private CertificateChainPromise() {
            this.future = new CompletableFuture<>();
        }

        @Override // android.security.keystore.IKeystoreCertificateChainCallback
        public void onFinished(KeystoreResponse keystoreResponse, KeymasterCertificateChain keymasterCertificateChain) throws RemoteException {
            this.future.complete(KeyStore.this.new KeyAttestationCallbackResult(keystoreResponse, keymasterCertificateChain));
        }

        public final CompletableFuture<KeyAttestationCallbackResult> getFuture() {
            return this.future;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.future.completeExceptionally(new RemoteException("Keystore died"));
        }
    }

    public int attestKey(String str, KeymasterArguments keymasterArguments, KeymasterCertificateChain keymasterCertificateChain) {
        CertificateChainPromise certificateChainPromise = new CertificateChainPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(certificateChainPromise, 0);
                if (keymasterArguments == null) {
                    keymasterArguments = new KeymasterArguments();
                }
                if (keymasterCertificateChain == null) {
                    keymasterCertificateChain = new KeymasterCertificateChain();
                }
                int iAttestKey = this.mBinder.attestKey(certificateChainPromise, str, keymasterArguments);
                if (iAttestKey != 1) {
                    return iAttestKey;
                }
                KeyAttestationCallbackResult keyAttestationCallbackResult = (KeyAttestationCallbackResult) interruptedPreservingGet(certificateChainPromise.getFuture());
                int errorCode = keyAttestationCallbackResult.getKeystoreResponse().getErrorCode();
                if (errorCode == 1) {
                    keymasterCertificateChain.shallowCopyFrom(keyAttestationCallbackResult.getCertificateChain());
                }
                return errorCode;
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (ExecutionException e3) {
                Log.e(TAG, "AttestKey completed with exception", e3);
                return 4;
            }
        } finally {
            this.mBinder.asBinder().unlinkToDeath(certificateChainPromise, 0);
        }
    }

    public int attestDeviceIds(KeymasterArguments keymasterArguments, KeymasterCertificateChain keymasterCertificateChain) {
        CertificateChainPromise certificateChainPromise = new CertificateChainPromise();
        try {
            try {
                this.mBinder.asBinder().linkToDeath(certificateChainPromise, 0);
                if (keymasterArguments == null) {
                    keymasterArguments = new KeymasterArguments();
                }
                if (keymasterCertificateChain == null) {
                    keymasterCertificateChain = new KeymasterCertificateChain();
                }
                int iAttestDeviceIds = this.mBinder.attestDeviceIds(certificateChainPromise, keymasterArguments);
                if (iAttestDeviceIds != 1) {
                    return iAttestDeviceIds;
                }
                KeyAttestationCallbackResult keyAttestationCallbackResult = (KeyAttestationCallbackResult) interruptedPreservingGet(certificateChainPromise.getFuture());
                int errorCode = keyAttestationCallbackResult.getKeystoreResponse().getErrorCode();
                if (errorCode == 1) {
                    keymasterCertificateChain.shallowCopyFrom(keyAttestationCallbackResult.getCertificateChain());
                }
                return errorCode;
            } catch (RemoteException e) {
                e = e;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (NullPointerException e2) {
                e = e2;
                Log.w(TAG, "Cannot connect to keystore", e);
                return 4;
            } catch (ExecutionException e3) {
                Log.e(TAG, "AttestDevicdeIds completed with exception", e3);
                return 4;
            }
        } finally {
            this.mBinder.asBinder().unlinkToDeath(certificateChainPromise, 0);
        }
    }

    public void onDeviceOffBody() {
        try {
            this.mBinder.onDeviceOffBody();
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
        }
    }

    public int presentConfirmationPrompt(IBinder iBinder, String str, byte[] bArr, String str2, int i) {
        try {
            return this.mBinder.presentConfirmationPrompt(iBinder, str, bArr, str2, i);
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return 5;
        }
    }

    public int cancelConfirmationPrompt(IBinder iBinder) {
        try {
            return this.mBinder.cancelConfirmationPrompt(iBinder);
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return 5;
        }
    }

    public boolean isConfirmationPromptSupported() {
        try {
            return this.mBinder.isConfirmationPromptSupported();
        } catch (RemoteException | NullPointerException e) {
            Log.w(TAG, "Cannot connect to keystore", e);
            return false;
        }
    }

    public static KeyStoreException getKeyStoreException(int i) {
        if (i <= 0) {
            if (i == -16) {
                return new KeyStoreException(i, "Invalid user authentication validity duration");
            }
            return new KeyStoreException(i, KeymasterDefs.getErrorMessage(i));
        }
        if (i == 1) {
            return new KeyStoreException(i, "OK");
        }
        if (i == 2) {
            return new KeyStoreException(i, "User authentication required");
        }
        if (i == 3) {
            return new KeyStoreException(i, "Keystore not initialized");
        }
        if (i == 4) {
            return new KeyStoreException(i, "System error");
        }
        if (i == 6) {
            return new KeyStoreException(i, "Permission denied");
        }
        if (i == 7) {
            return new KeyStoreException(i, "Key not found");
        }
        if (i == 8) {
            return new KeyStoreException(i, "Key blob corrupted");
        }
        if (i == 15) {
            return new KeyStoreException(i, "Operation requires authorization");
        }
        if (i == 17) {
            return new KeyStoreException(i, "Key permanently invalidated");
        }
        return new KeyStoreException(i, String.valueOf(i));
    }

    public InvalidKeyException getInvalidKeyException(String str, int i, KeyStoreException keyStoreException) {
        int errorCode = keyStoreException.getErrorCode();
        if (errorCode == 2) {
            return new UserNotAuthenticatedException();
        }
        if (errorCode != 3) {
            if (errorCode != 15) {
                switch (errorCode) {
                    case -26:
                        break;
                    case -25:
                        return new KeyExpiredException();
                    case -24:
                        return new KeyNotYetValidException();
                    default:
                        return new InvalidKeyException("Keystore operation failed", keyStoreException);
                }
            }
            KeyCharacteristics keyCharacteristics = new KeyCharacteristics();
            int keyCharacteristics2 = getKeyCharacteristics(str, null, null, i, keyCharacteristics);
            boolean z = true;
            if (keyCharacteristics2 != 1) {
                return new InvalidKeyException("Failed to obtained key characteristics", getKeyStoreException(keyCharacteristics2));
            }
            List<BigInteger> unsignedLongs = keyCharacteristics.getUnsignedLongs(KeymasterDefs.KM_TAG_USER_SECURE_ID);
            if (unsignedLongs.isEmpty()) {
                return new KeyPermanentlyInvalidatedException();
            }
            long secureUserId = GateKeeper.getSecureUserId();
            if (secureUserId != 0 && unsignedLongs.contains(KeymasterArguments.toUint64(secureUserId))) {
                return new UserNotAuthenticatedException();
            }
            long[] authenticatorIds = ((BiometricManager) this.mContext.getSystemService(BiometricManager.class)).getAuthenticatorIds();
            int length = authenticatorIds.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    if (unsignedLongs.contains(KeymasterArguments.toUint64(authenticatorIds[i2]))) {
                        i2++;
                    } else {
                        z = false;
                    }
                }
            }
            if (z) {
                return new UserNotAuthenticatedException();
            }
            return new KeyPermanentlyInvalidatedException();
        }
        return new KeyPermanentlyInvalidatedException();
    }

    public InvalidKeyException getInvalidKeyException(String str, int i, int i2) {
        return getInvalidKeyException(str, i, getKeyStoreException(i2));
    }

    private static <R> R interruptedPreservingGet(CompletableFuture<R> completableFuture) throws ExecutionException {
        R r;
        boolean z = false;
        while (true) {
            try {
                r = completableFuture.get();
                if (!z) {
                    break;
                }
                Thread.currentThread().interrupt();
                break;
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        return r;
    }
}
