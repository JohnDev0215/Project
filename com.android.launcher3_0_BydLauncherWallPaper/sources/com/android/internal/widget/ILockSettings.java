package com.android.internal.widget;

import android.app.PendingIntent;
import android.app.trust.IStrongAuthTracker;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.keystore.recovery.KeyChainProtectionParams;
import android.security.keystore.recovery.KeyChainSnapshot;
import android.security.keystore.recovery.RecoveryCertPath;
import android.security.keystore.recovery.WrappedApplicationKey;
import com.android.internal.widget.ICheckCredentialProgressCallback;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface ILockSettings extends IInterface {

    public static class Default implements ILockSettings {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public VerifyCredentialResponse checkCredential(LockscreenCredential lockscreenCredential, int i, ICheckCredentialProgressCallback iCheckCredentialProgressCallback) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public boolean checkVoldPassword(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.ILockSettings
        public void closeSession(String str) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public String generateKey(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public String generateKeyWithMetadata(String str, byte[] bArr) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public boolean getBoolean(String str, boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.ILockSettings
        public int getCredentialType(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.widget.ILockSettings
        public byte[] getHashFactor(LockscreenCredential lockscreenCredential, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public String getKey(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public KeyChainSnapshot getKeyChainSnapshot() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public long getLong(String str, long j, int i) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.widget.ILockSettings
        public int[] getRecoverySecretTypes() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public Map getRecoveryStatus() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public boolean getSeparateProfileChallengeEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.ILockSettings
        public String getString(String str, String str2, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public int getStrongAuthForUser(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.widget.ILockSettings
        public boolean hasPendingEscrowToken(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.ILockSettings
        public boolean hasSecureLockScreen() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.ILockSettings
        public String importKey(String str, byte[] bArr) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public String importKeyWithMetadata(String str, byte[] bArr, byte[] bArr2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public void initRecoveryServiceWithSigFile(String str, byte[] bArr, byte[] bArr2) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public Map recoverKeyChainSnapshot(String str, byte[] bArr, List<WrappedApplicationKey> list) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public void registerStrongAuthTracker(IStrongAuthTracker iStrongAuthTracker) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void removeCachedUnifiedChallenge(int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void removeKey(String str) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void reportSuccessfulBiometricUnlock(boolean z, int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void requireStrongAuth(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void resetKeyStore(int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void scheduleNonStrongBiometricIdleTimeout(int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setBoolean(String str, boolean z, int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public boolean setLockCredential(LockscreenCredential lockscreenCredential, LockscreenCredential lockscreenCredential2, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setLong(String str, long j, int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setRecoverySecretTypes(int[] iArr) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setRecoveryStatus(String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setSeparateProfileChallengeEnabled(int i, boolean z, LockscreenCredential lockscreenCredential) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setServerParams(byte[] bArr) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setSnapshotCreatedPendingIntent(PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void setString(String str, String str2, int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public byte[] startRecoverySessionWithCertPath(String str, String str2, RecoveryCertPath recoveryCertPath, byte[] bArr, byte[] bArr2, List<KeyChainProtectionParams> list) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public void systemReady() throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public boolean tryUnlockWithCachedUnifiedChallenge(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.ILockSettings
        public void unregisterStrongAuthTracker(IStrongAuthTracker iStrongAuthTracker) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public void userPresent(int i) throws RemoteException {
        }

        @Override // com.android.internal.widget.ILockSettings
        public VerifyCredentialResponse verifyCredential(LockscreenCredential lockscreenCredential, long j, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.ILockSettings
        public VerifyCredentialResponse verifyTiedProfileChallenge(LockscreenCredential lockscreenCredential, long j, int i) throws RemoteException {
            return null;
        }
    }

    VerifyCredentialResponse checkCredential(LockscreenCredential lockscreenCredential, int i, ICheckCredentialProgressCallback iCheckCredentialProgressCallback) throws RemoteException;

    boolean checkVoldPassword(int i) throws RemoteException;

    void closeSession(String str) throws RemoteException;

    String generateKey(String str) throws RemoteException;

    String generateKeyWithMetadata(String str, byte[] bArr) throws RemoteException;

    boolean getBoolean(String str, boolean z, int i) throws RemoteException;

    int getCredentialType(int i) throws RemoteException;

    byte[] getHashFactor(LockscreenCredential lockscreenCredential, int i) throws RemoteException;

    String getKey(String str) throws RemoteException;

    KeyChainSnapshot getKeyChainSnapshot() throws RemoteException;

    long getLong(String str, long j, int i) throws RemoteException;

    int[] getRecoverySecretTypes() throws RemoteException;

    Map getRecoveryStatus() throws RemoteException;

    boolean getSeparateProfileChallengeEnabled(int i) throws RemoteException;

    String getString(String str, String str2, int i) throws RemoteException;

    int getStrongAuthForUser(int i) throws RemoteException;

    boolean hasPendingEscrowToken(int i) throws RemoteException;

    boolean hasSecureLockScreen() throws RemoteException;

    String importKey(String str, byte[] bArr) throws RemoteException;

    String importKeyWithMetadata(String str, byte[] bArr, byte[] bArr2) throws RemoteException;

    void initRecoveryServiceWithSigFile(String str, byte[] bArr, byte[] bArr2) throws RemoteException;

    Map recoverKeyChainSnapshot(String str, byte[] bArr, List<WrappedApplicationKey> list) throws RemoteException;

    void registerStrongAuthTracker(IStrongAuthTracker iStrongAuthTracker) throws RemoteException;

    void removeCachedUnifiedChallenge(int i) throws RemoteException;

    void removeKey(String str) throws RemoteException;

    void reportSuccessfulBiometricUnlock(boolean z, int i) throws RemoteException;

    void requireStrongAuth(int i, int i2) throws RemoteException;

    void resetKeyStore(int i) throws RemoteException;

    void scheduleNonStrongBiometricIdleTimeout(int i) throws RemoteException;

    void setBoolean(String str, boolean z, int i) throws RemoteException;

    boolean setLockCredential(LockscreenCredential lockscreenCredential, LockscreenCredential lockscreenCredential2, int i) throws RemoteException;

    void setLong(String str, long j, int i) throws RemoteException;

    void setRecoverySecretTypes(int[] iArr) throws RemoteException;

    void setRecoveryStatus(String str, int i) throws RemoteException;

    void setSeparateProfileChallengeEnabled(int i, boolean z, LockscreenCredential lockscreenCredential) throws RemoteException;

    void setServerParams(byte[] bArr) throws RemoteException;

    void setSnapshotCreatedPendingIntent(PendingIntent pendingIntent) throws RemoteException;

    void setString(String str, String str2, int i) throws RemoteException;

    byte[] startRecoverySessionWithCertPath(String str, String str2, RecoveryCertPath recoveryCertPath, byte[] bArr, byte[] bArr2, List<KeyChainProtectionParams> list) throws RemoteException;

    void systemReady() throws RemoteException;

    boolean tryUnlockWithCachedUnifiedChallenge(int i) throws RemoteException;

    void unregisterStrongAuthTracker(IStrongAuthTracker iStrongAuthTracker) throws RemoteException;

    void userPresent(int i) throws RemoteException;

    VerifyCredentialResponse verifyCredential(LockscreenCredential lockscreenCredential, long j, int i) throws RemoteException;

    VerifyCredentialResponse verifyTiedProfileChallenge(LockscreenCredential lockscreenCredential, long j, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ILockSettings {
        private static final String DESCRIPTOR = "com.android.internal.widget.ILockSettings";
        static final int TRANSACTION_checkCredential = 9;
        static final int TRANSACTION_checkVoldPassword = 12;
        static final int TRANSACTION_closeSession = 42;
        static final int TRANSACTION_generateKey = 28;
        static final int TRANSACTION_generateKeyWithMetadata = 29;
        static final int TRANSACTION_getBoolean = 4;
        static final int TRANSACTION_getCredentialType = 13;
        static final int TRANSACTION_getHashFactor = 14;
        static final int TRANSACTION_getKey = 32;
        static final int TRANSACTION_getKeyChainSnapshot = 27;
        static final int TRANSACTION_getLong = 5;
        static final int TRANSACTION_getRecoverySecretTypes = 39;
        static final int TRANSACTION_getRecoveryStatus = 37;
        static final int TRANSACTION_getSeparateProfileChallengeEnabled = 16;
        static final int TRANSACTION_getString = 6;
        static final int TRANSACTION_getStrongAuthForUser = 24;
        static final int TRANSACTION_hasPendingEscrowToken = 25;
        static final int TRANSACTION_hasSecureLockScreen = 43;
        static final int TRANSACTION_importKey = 30;
        static final int TRANSACTION_importKeyWithMetadata = 31;
        static final int TRANSACTION_initRecoveryServiceWithSigFile = 26;
        static final int TRANSACTION_recoverKeyChainSnapshot = 41;
        static final int TRANSACTION_registerStrongAuthTracker = 17;
        static final int TRANSACTION_removeCachedUnifiedChallenge = 45;
        static final int TRANSACTION_removeKey = 33;
        static final int TRANSACTION_reportSuccessfulBiometricUnlock = 20;
        static final int TRANSACTION_requireStrongAuth = 19;
        static final int TRANSACTION_resetKeyStore = 8;
        static final int TRANSACTION_scheduleNonStrongBiometricIdleTimeout = 21;
        static final int TRANSACTION_setBoolean = 1;
        static final int TRANSACTION_setLockCredential = 7;
        static final int TRANSACTION_setLong = 2;
        static final int TRANSACTION_setRecoverySecretTypes = 38;
        static final int TRANSACTION_setRecoveryStatus = 36;
        static final int TRANSACTION_setSeparateProfileChallengeEnabled = 15;
        static final int TRANSACTION_setServerParams = 35;
        static final int TRANSACTION_setSnapshotCreatedPendingIntent = 34;
        static final int TRANSACTION_setString = 3;
        static final int TRANSACTION_startRecoverySessionWithCertPath = 40;
        static final int TRANSACTION_systemReady = 22;
        static final int TRANSACTION_tryUnlockWithCachedUnifiedChallenge = 44;
        static final int TRANSACTION_unregisterStrongAuthTracker = 18;
        static final int TRANSACTION_userPresent = 23;
        static final int TRANSACTION_verifyCredential = 10;
        static final int TRANSACTION_verifyTiedProfileChallenge = 11;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setBoolean";
                case 2:
                    return "setLong";
                case 3:
                    return "setString";
                case 4:
                    return "getBoolean";
                case 5:
                    return "getLong";
                case 6:
                    return "getString";
                case 7:
                    return "setLockCredential";
                case 8:
                    return "resetKeyStore";
                case 9:
                    return "checkCredential";
                case 10:
                    return "verifyCredential";
                case 11:
                    return "verifyTiedProfileChallenge";
                case 12:
                    return "checkVoldPassword";
                case 13:
                    return "getCredentialType";
                case 14:
                    return "getHashFactor";
                case 15:
                    return "setSeparateProfileChallengeEnabled";
                case 16:
                    return "getSeparateProfileChallengeEnabled";
                case 17:
                    return "registerStrongAuthTracker";
                case 18:
                    return "unregisterStrongAuthTracker";
                case 19:
                    return "requireStrongAuth";
                case 20:
                    return "reportSuccessfulBiometricUnlock";
                case 21:
                    return "scheduleNonStrongBiometricIdleTimeout";
                case 22:
                    return "systemReady";
                case 23:
                    return "userPresent";
                case 24:
                    return "getStrongAuthForUser";
                case 25:
                    return "hasPendingEscrowToken";
                case 26:
                    return "initRecoveryServiceWithSigFile";
                case 27:
                    return "getKeyChainSnapshot";
                case 28:
                    return "generateKey";
                case 29:
                    return "generateKeyWithMetadata";
                case 30:
                    return "importKey";
                case 31:
                    return "importKeyWithMetadata";
                case 32:
                    return "getKey";
                case 33:
                    return "removeKey";
                case 34:
                    return "setSnapshotCreatedPendingIntent";
                case 35:
                    return "setServerParams";
                case 36:
                    return "setRecoveryStatus";
                case 37:
                    return "getRecoveryStatus";
                case 38:
                    return "setRecoverySecretTypes";
                case 39:
                    return "getRecoverySecretTypes";
                case 40:
                    return "startRecoverySessionWithCertPath";
                case 41:
                    return "recoverKeyChainSnapshot";
                case 42:
                    return "closeSession";
                case 43:
                    return "hasSecureLockScreen";
                case 44:
                    return "tryUnlockWithCachedUnifiedChallenge";
                case 45:
                    return "removeCachedUnifiedChallenge";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILockSettings asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILockSettings)) {
                return (ILockSettings) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBoolean(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLong(parcel.readString(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setString(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean z = getBoolean(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(z ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    long j = getLong(parcel.readString(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(j);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string = getString(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(string);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean lockCredential = setLockCredential(parcel.readInt() != 0 ? LockscreenCredential.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? LockscreenCredential.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(lockCredential ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetKeyStore(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    VerifyCredentialResponse verifyCredentialResponseCheckCredential = checkCredential(parcel.readInt() != 0 ? LockscreenCredential.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), ICheckCredentialProgressCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (verifyCredentialResponseCheckCredential != null) {
                        parcel2.writeInt(1);
                        verifyCredentialResponseCheckCredential.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    VerifyCredentialResponse verifyCredentialResponseVerifyCredential = verifyCredential(parcel.readInt() != 0 ? LockscreenCredential.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    if (verifyCredentialResponseVerifyCredential != null) {
                        parcel2.writeInt(1);
                        verifyCredentialResponseVerifyCredential.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    VerifyCredentialResponse verifyCredentialResponseVerifyTiedProfileChallenge = verifyTiedProfileChallenge(parcel.readInt() != 0 ? LockscreenCredential.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    if (verifyCredentialResponseVerifyTiedProfileChallenge != null) {
                        parcel2.writeInt(1);
                        verifyCredentialResponseVerifyTiedProfileChallenge.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCheckVoldPassword = checkVoldPassword(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCheckVoldPassword ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int credentialType = getCredentialType(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(credentialType);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] hashFactor = getHashFactor(parcel.readInt() != 0 ? LockscreenCredential.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(hashFactor);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSeparateProfileChallengeEnabled(parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0 ? LockscreenCredential.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean separateProfileChallengeEnabled = getSeparateProfileChallengeEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(separateProfileChallengeEnabled ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerStrongAuthTracker(IStrongAuthTracker.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterStrongAuthTracker(IStrongAuthTracker.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    requireStrongAuth(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportSuccessfulBiometricUnlock(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    scheduleNonStrongBiometricIdleTimeout(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    systemReady();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    userPresent(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int strongAuthForUser = getStrongAuthForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(strongAuthForUser);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasPendingEscrowToken = hasPendingEscrowToken(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasPendingEscrowToken ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    initRecoveryServiceWithSigFile(parcel.readString(), parcel.createByteArray(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    KeyChainSnapshot keyChainSnapshot = getKeyChainSnapshot();
                    parcel2.writeNoException();
                    if (keyChainSnapshot != null) {
                        parcel2.writeInt(1);
                        keyChainSnapshot.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strGenerateKey = generateKey(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strGenerateKey);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strGenerateKeyWithMetadata = generateKeyWithMetadata(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeString(strGenerateKeyWithMetadata);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strImportKey = importKey(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeString(strImportKey);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strImportKeyWithMetadata = importKeyWithMetadata(parcel.readString(), parcel.createByteArray(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeString(strImportKeyWithMetadata);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    String key = getKey(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(key);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeKey(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSnapshotCreatedPendingIntent(parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    setServerParams(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRecoveryStatus(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    Map recoveryStatus = getRecoveryStatus();
                    parcel2.writeNoException();
                    parcel2.writeMap(recoveryStatus);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRecoverySecretTypes(parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] recoverySecretTypes = getRecoverySecretTypes();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(recoverySecretTypes);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrStartRecoverySessionWithCertPath = startRecoverySessionWithCertPath(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? RecoveryCertPath.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray(), parcel.createByteArray(), parcel.createTypedArrayList(KeyChainProtectionParams.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrStartRecoverySessionWithCertPath);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    Map mapRecoverKeyChainSnapshot = recoverKeyChainSnapshot(parcel.readString(), parcel.createByteArray(), parcel.createTypedArrayList(WrappedApplicationKey.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeMap(mapRecoverKeyChainSnapshot);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeSession(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasSecureLockScreen = hasSecureLockScreen();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasSecureLockScreen ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zTryUnlockWithCachedUnifiedChallenge = tryUnlockWithCachedUnifiedChallenge(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zTryUnlockWithCachedUnifiedChallenge ? 1 : 0);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCachedUnifiedChallenge(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ILockSettings {
            public static ILockSettings sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setBoolean(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBoolean(str, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setLong(String str, long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLong(str, j, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setString(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setString(str, str2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public boolean getBoolean(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBoolean(str, z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public long getLong(String str, long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLong(str, j, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public String getString(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getString(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public boolean setLockCredential(LockscreenCredential lockscreenCredential, LockscreenCredential lockscreenCredential2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lockscreenCredential != null) {
                        parcelObtain.writeInt(1);
                        lockscreenCredential.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (lockscreenCredential2 != null) {
                        parcelObtain.writeInt(1);
                        lockscreenCredential2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLockCredential(lockscreenCredential, lockscreenCredential2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void resetKeyStore(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetKeyStore(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public VerifyCredentialResponse checkCredential(LockscreenCredential lockscreenCredential, int i, ICheckCredentialProgressCallback iCheckCredentialProgressCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lockscreenCredential != null) {
                        parcelObtain.writeInt(1);
                        lockscreenCredential.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCheckCredentialProgressCallback != null ? iCheckCredentialProgressCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkCredential(lockscreenCredential, i, iCheckCredentialProgressCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VerifyCredentialResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public VerifyCredentialResponse verifyCredential(LockscreenCredential lockscreenCredential, long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lockscreenCredential != null) {
                        parcelObtain.writeInt(1);
                        lockscreenCredential.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyCredential(lockscreenCredential, j, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VerifyCredentialResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public VerifyCredentialResponse verifyTiedProfileChallenge(LockscreenCredential lockscreenCredential, long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lockscreenCredential != null) {
                        parcelObtain.writeInt(1);
                        lockscreenCredential.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyTiedProfileChallenge(lockscreenCredential, j, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VerifyCredentialResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public boolean checkVoldPassword(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkVoldPassword(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public int getCredentialType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialType(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public byte[] getHashFactor(LockscreenCredential lockscreenCredential, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lockscreenCredential != null) {
                        parcelObtain.writeInt(1);
                        lockscreenCredential.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHashFactor(lockscreenCredential, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setSeparateProfileChallengeEnabled(int i, boolean z, LockscreenCredential lockscreenCredential) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (lockscreenCredential != null) {
                        parcelObtain.writeInt(1);
                        lockscreenCredential.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSeparateProfileChallengeEnabled(i, z, lockscreenCredential);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public boolean getSeparateProfileChallengeEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeparateProfileChallengeEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void registerStrongAuthTracker(IStrongAuthTracker iStrongAuthTracker) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iStrongAuthTracker != null ? iStrongAuthTracker.asBinder() : null);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerStrongAuthTracker(iStrongAuthTracker);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void unregisterStrongAuthTracker(IStrongAuthTracker iStrongAuthTracker) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iStrongAuthTracker != null ? iStrongAuthTracker.asBinder() : null);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterStrongAuthTracker(iStrongAuthTracker);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void requireStrongAuth(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requireStrongAuth(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void reportSuccessfulBiometricUnlock(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportSuccessfulBiometricUnlock(z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void scheduleNonStrongBiometricIdleTimeout(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().scheduleNonStrongBiometricIdleTimeout(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void systemReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().systemReady();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void userPresent(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().userPresent(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public int getStrongAuthForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStrongAuthForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public boolean hasPendingEscrowToken(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasPendingEscrowToken(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void initRecoveryServiceWithSigFile(String str, byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initRecoveryServiceWithSigFile(str, bArr, bArr2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public KeyChainSnapshot getKeyChainSnapshot() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyChainSnapshot();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? KeyChainSnapshot.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public String generateKey(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateKey(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public String generateKeyWithMetadata(String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateKeyWithMetadata(str, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public String importKey(String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importKey(str, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public String importKeyWithMetadata(String str, byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importKeyWithMetadata(str, bArr, bArr2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public String getKey(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKey(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void removeKey(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeKey(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setSnapshotCreatedPendingIntent(PendingIntent pendingIntent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSnapshotCreatedPendingIntent(pendingIntent);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setServerParams(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setServerParams(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setRecoveryStatus(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRecoveryStatus(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public Map getRecoveryStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecoveryStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void setRecoverySecretTypes(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRecoverySecretTypes(iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public int[] getRecoverySecretTypes() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecoverySecretTypes();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public byte[] startRecoverySessionWithCertPath(String str, String str2, RecoveryCertPath recoveryCertPath, byte[] bArr, byte[] bArr2, List<KeyChainProtectionParams> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (recoveryCertPath != null) {
                        parcelObtain.writeInt(1);
                        recoveryCertPath.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startRecoverySessionWithCertPath(str, str2, recoveryCertPath, bArr, bArr2, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public Map recoverKeyChainSnapshot(String str, byte[] bArr, List<WrappedApplicationKey> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().recoverKeyChainSnapshot(str, bArr, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void closeSession(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeSession(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public boolean hasSecureLockScreen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasSecureLockScreen();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public boolean tryUnlockWithCachedUnifiedChallenge(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tryUnlockWithCachedUnifiedChallenge(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.ILockSettings
            public void removeCachedUnifiedChallenge(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCachedUnifiedChallenge(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILockSettings iLockSettings) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLockSettings == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLockSettings;
            return true;
        }

        public static ILockSettings getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
