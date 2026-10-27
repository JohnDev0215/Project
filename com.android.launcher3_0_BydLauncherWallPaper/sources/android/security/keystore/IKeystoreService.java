package android.security.keystore;

import android.app.slice.Slice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.KeymasterBlob;
import android.security.keystore.ICredstoreTokenCallback;
import android.security.keystore.IKeystoreCertificateChainCallback;
import android.security.keystore.IKeystoreExportKeyCallback;
import android.security.keystore.IKeystoreKeyCharacteristicsCallback;
import android.security.keystore.IKeystoreOperationResultCallback;
import android.security.keystore.IKeystoreResponseCallback;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IKeystoreService extends IInterface {

    public static class Default implements IKeystoreService {
        @Override // android.security.keystore.IKeystoreService
        public int abort(IKeystoreResponseCallback iKeystoreResponseCallback, IBinder iBinder) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int addAuthToken(byte[] bArr) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int addRngEntropy(IKeystoreResponseCallback iKeystoreResponseCallback, byte[] bArr, int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.keystore.IKeystoreService
        public int attestDeviceIds(IKeystoreCertificateChainCallback iKeystoreCertificateChainCallback, KeymasterArguments keymasterArguments) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int attestKey(IKeystoreCertificateChainCallback iKeystoreCertificateChainCallback, String str, KeymasterArguments keymasterArguments) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int begin(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, String str, int i, boolean z, KeymasterArguments keymasterArguments, byte[] bArr, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int cancelConfirmationPrompt(IBinder iBinder) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int clear_uid(long j) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int del(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int exist(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int exportKey(IKeystoreExportKeyCallback iKeystoreExportKeyCallback, String str, int i, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int finish(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr, byte[] bArr2, byte[] bArr3) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int generateKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterArguments keymasterArguments, byte[] bArr, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public byte[] get(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.security.keystore.IKeystoreService
        public int getKeyCharacteristics(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int getState(int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public void getTokensForCredstore(long j, long j2, int i, ICredstoreTokenCallback iCredstoreTokenCallback) throws RemoteException {
        }

        @Override // android.security.keystore.IKeystoreService
        public long getmtime(String str, int i) throws RemoteException {
            return 0L;
        }

        @Override // android.security.keystore.IKeystoreService
        public String grant(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.security.keystore.IKeystoreService
        public int importKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterArguments keymasterArguments, int i, byte[] bArr, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int importWrappedKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, byte[] bArr, String str2, byte[] bArr2, KeymasterArguments keymasterArguments, long j, long j2) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int insert(String str, byte[] bArr, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public boolean isConfirmationPromptSupported() throws RemoteException {
            return false;
        }

        @Override // android.security.keystore.IKeystoreService
        public int isEmpty(int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int is_hardware_backed(String str) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public String[] list(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.security.keystore.IKeystoreService
        public int listUidsOfAuthBoundKeys(List<String> list) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int lock(int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int onDeviceOffBody() throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int onKeyguardVisibilityChanged(boolean z, int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int onUserAdded(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int onUserPasswordChanged(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int onUserRemoved(int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int presentConfirmationPrompt(IBinder iBinder, String str, byte[] bArr, String str2, int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int ungrant(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int unlock(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // android.security.keystore.IKeystoreService
        public int update(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr) throws RemoteException {
            return 0;
        }
    }

    int abort(IKeystoreResponseCallback iKeystoreResponseCallback, IBinder iBinder) throws RemoteException;

    int addAuthToken(byte[] bArr) throws RemoteException;

    int addRngEntropy(IKeystoreResponseCallback iKeystoreResponseCallback, byte[] bArr, int i) throws RemoteException;

    int attestDeviceIds(IKeystoreCertificateChainCallback iKeystoreCertificateChainCallback, KeymasterArguments keymasterArguments) throws RemoteException;

    int attestKey(IKeystoreCertificateChainCallback iKeystoreCertificateChainCallback, String str, KeymasterArguments keymasterArguments) throws RemoteException;

    int begin(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, String str, int i, boolean z, KeymasterArguments keymasterArguments, byte[] bArr, int i2) throws RemoteException;

    int cancelConfirmationPrompt(IBinder iBinder) throws RemoteException;

    int clear_uid(long j) throws RemoteException;

    int del(String str, int i) throws RemoteException;

    int exist(String str, int i) throws RemoteException;

    int exportKey(IKeystoreExportKeyCallback iKeystoreExportKeyCallback, String str, int i, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i2) throws RemoteException;

    int finish(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr, byte[] bArr2, byte[] bArr3) throws RemoteException;

    int generateKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterArguments keymasterArguments, byte[] bArr, int i, int i2) throws RemoteException;

    byte[] get(String str, int i) throws RemoteException;

    int getKeyCharacteristics(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i) throws RemoteException;

    int getState(int i) throws RemoteException;

    void getTokensForCredstore(long j, long j2, int i, ICredstoreTokenCallback iCredstoreTokenCallback) throws RemoteException;

    long getmtime(String str, int i) throws RemoteException;

    String grant(String str, int i) throws RemoteException;

    int importKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterArguments keymasterArguments, int i, byte[] bArr, int i2, int i3) throws RemoteException;

    int importWrappedKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, byte[] bArr, String str2, byte[] bArr2, KeymasterArguments keymasterArguments, long j, long j2) throws RemoteException;

    int insert(String str, byte[] bArr, int i, int i2) throws RemoteException;

    boolean isConfirmationPromptSupported() throws RemoteException;

    int isEmpty(int i) throws RemoteException;

    int is_hardware_backed(String str) throws RemoteException;

    String[] list(String str, int i) throws RemoteException;

    int listUidsOfAuthBoundKeys(List<String> list) throws RemoteException;

    int lock(int i) throws RemoteException;

    int onDeviceOffBody() throws RemoteException;

    int onKeyguardVisibilityChanged(boolean z, int i) throws RemoteException;

    int onUserAdded(int i, int i2) throws RemoteException;

    int onUserPasswordChanged(int i, String str) throws RemoteException;

    int onUserRemoved(int i) throws RemoteException;

    int presentConfirmationPrompt(IBinder iBinder, String str, byte[] bArr, String str2, int i) throws RemoteException;

    int ungrant(String str, int i) throws RemoteException;

    int unlock(int i, String str) throws RemoteException;

    int update(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeystoreService {
        private static final String DESCRIPTOR = "android.security.keystore.IKeystoreService";
        static final int TRANSACTION_abort = 24;
        static final int TRANSACTION_addAuthToken = 25;
        static final int TRANSACTION_addRngEntropy = 16;
        static final int TRANSACTION_attestDeviceIds = 29;
        static final int TRANSACTION_attestKey = 28;
        static final int TRANSACTION_begin = 21;
        static final int TRANSACTION_cancelConfirmationPrompt = 33;
        static final int TRANSACTION_clear_uid = 15;
        static final int TRANSACTION_del = 4;
        static final int TRANSACTION_exist = 5;
        static final int TRANSACTION_exportKey = 20;
        static final int TRANSACTION_finish = 23;
        static final int TRANSACTION_generateKey = 17;
        static final int TRANSACTION_get = 2;
        static final int TRANSACTION_getKeyCharacteristics = 18;
        static final int TRANSACTION_getState = 1;
        static final int TRANSACTION_getTokensForCredstore = 37;
        static final int TRANSACTION_getmtime = 13;
        static final int TRANSACTION_grant = 11;
        static final int TRANSACTION_importKey = 19;
        static final int TRANSACTION_importWrappedKey = 31;
        static final int TRANSACTION_insert = 3;
        static final int TRANSACTION_isConfirmationPromptSupported = 34;
        static final int TRANSACTION_isEmpty = 10;
        static final int TRANSACTION_is_hardware_backed = 14;
        static final int TRANSACTION_list = 6;
        static final int TRANSACTION_listUidsOfAuthBoundKeys = 36;
        static final int TRANSACTION_lock = 8;
        static final int TRANSACTION_onDeviceOffBody = 30;
        static final int TRANSACTION_onKeyguardVisibilityChanged = 35;
        static final int TRANSACTION_onUserAdded = 26;
        static final int TRANSACTION_onUserPasswordChanged = 7;
        static final int TRANSACTION_onUserRemoved = 27;
        static final int TRANSACTION_presentConfirmationPrompt = 32;
        static final int TRANSACTION_ungrant = 12;
        static final int TRANSACTION_unlock = 9;
        static final int TRANSACTION_update = 22;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getState";
                case 2:
                    return "get";
                case 3:
                    return "insert";
                case 4:
                    return "del";
                case 5:
                    return "exist";
                case 6:
                    return Slice.HINT_LIST;
                case 7:
                    return "onUserPasswordChanged";
                case 8:
                    return "lock";
                case 9:
                    return "unlock";
                case 10:
                    return "isEmpty";
                case 11:
                    return "grant";
                case 12:
                    return "ungrant";
                case 13:
                    return "getmtime";
                case 14:
                    return "is_hardware_backed";
                case 15:
                    return "clear_uid";
                case 16:
                    return "addRngEntropy";
                case 17:
                    return "generateKey";
                case 18:
                    return "getKeyCharacteristics";
                case 19:
                    return "importKey";
                case 20:
                    return "exportKey";
                case 21:
                    return "begin";
                case 22:
                    return "update";
                case 23:
                    return "finish";
                case 24:
                    return "abort";
                case 25:
                    return "addAuthToken";
                case 26:
                    return "onUserAdded";
                case 27:
                    return "onUserRemoved";
                case 28:
                    return "attestKey";
                case 29:
                    return "attestDeviceIds";
                case 30:
                    return "onDeviceOffBody";
                case 31:
                    return "importWrappedKey";
                case 32:
                    return "presentConfirmationPrompt";
                case 33:
                    return "cancelConfirmationPrompt";
                case 34:
                    return "isConfirmationPromptSupported";
                case 35:
                    return "onKeyguardVisibilityChanged";
                case 36:
                    return "listUidsOfAuthBoundKeys";
                case 37:
                    return "getTokensForCredstore";
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

        public static IKeystoreService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeystoreService)) {
                return (IKeystoreService) iInterfaceQueryLocalInterface;
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
                    int state = getState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(state);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArr = get(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArr);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iInsert = insert(parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iInsert);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iDel = del(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iDel);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iExist = exist(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iExist);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] list = list(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(list);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iOnUserPasswordChanged = onUserPasswordChanged(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iOnUserPasswordChanged);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iLock = lock(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iLock);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUnlock = unlock(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUnlock);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iIsEmpty = isEmpty(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iIsEmpty);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strGrant = grant(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(strGrant);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUngrant = ungrant(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUngrant);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    long j = getmtime(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(j);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iIs_hardware_backed = is_hardware_backed(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iIs_hardware_backed);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iClear_uid = clear_uid(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(iClear_uid);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAddRngEntropy = addRngEntropy(IKeystoreResponseCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddRngEntropy);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iGenerateKey = generateKey(IKeystoreKeyCharacteristicsCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iGenerateKey);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int keyCharacteristics = getKeyCharacteristics(IKeystoreKeyCharacteristicsCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? KeymasterBlob.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? KeymasterBlob.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(keyCharacteristics);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iImportKey = importKey(IKeystoreKeyCharacteristicsCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iImportKey);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iExportKey = exportKey(IKeystoreExportKeyCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? KeymasterBlob.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? KeymasterBlob.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iExportKey);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iBegin = begin(IKeystoreOperationResultCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iBegin);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUpdate = update(IKeystoreOperationResultCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUpdate);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iFinish = finish(IKeystoreOperationResultCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iFinish);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAbort = abort(IKeystoreResponseCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAbort);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAddAuthToken = addAuthToken(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddAuthToken);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iOnUserAdded = onUserAdded(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iOnUserAdded);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iOnUserRemoved = onUserRemoved(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iOnUserRemoved);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAttestKey = attestKey(IKeystoreCertificateChainCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iAttestKey);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAttestDeviceIds = attestDeviceIds(IKeystoreCertificateChainCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iAttestDeviceIds);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iOnDeviceOffBody = onDeviceOffBody();
                    parcel2.writeNoException();
                    parcel2.writeInt(iOnDeviceOffBody);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iImportWrappedKey = importWrappedKey(IKeystoreKeyCharacteristicsCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0 ? KeymasterArguments.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(iImportWrappedKey);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPresentConfirmationPrompt = presentConfirmationPrompt(parcel.readStrongBinder(), parcel.readString(), parcel.createByteArray(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iPresentConfirmationPrompt);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCancelConfirmationPrompt = cancelConfirmationPrompt(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCancelConfirmationPrompt);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsConfirmationPromptSupported = isConfirmationPromptSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsConfirmationPromptSupported ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iOnKeyguardVisibilityChanged = onKeyguardVisibilityChanged(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iOnKeyguardVisibilityChanged);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    ArrayList arrayList = new ArrayList();
                    int iListUidsOfAuthBoundKeys = listUidsOfAuthBoundKeys(arrayList);
                    parcel2.writeNoException();
                    parcel2.writeInt(iListUidsOfAuthBoundKeys);
                    parcel2.writeStringList(arrayList);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    getTokensForCredstore(parcel.readLong(), parcel.readLong(), parcel.readInt(), ICredstoreTokenCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IKeystoreService {
            public static IKeystoreService sDefaultImpl;
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

            @Override // android.security.keystore.IKeystoreService
            public int getState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public byte[] get(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().get(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int insert(String str, byte[] bArr, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().insert(str, bArr, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int del(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().del(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int exist(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().exist(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public String[] list(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().list(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int onUserPasswordChanged(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onUserPasswordChanged(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int lock(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().lock(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int unlock(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unlock(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int isEmpty(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEmpty(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public String grant(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().grant(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int ungrant(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ungrant(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public long getmtime(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getmtime(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int is_hardware_backed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().is_hardware_backed(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int clear_uid(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clear_uid(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int addRngEntropy(IKeystoreResponseCallback iKeystoreResponseCallback, byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreResponseCallback != null ? iKeystoreResponseCallback.asBinder() : null);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addRngEntropy(iKeystoreResponseCallback, bArr, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int generateKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterArguments keymasterArguments, byte[] bArr, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreKeyCharacteristicsCallback != null ? iKeystoreKeyCharacteristicsCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateKey(iKeystoreKeyCharacteristicsCallback, str, keymasterArguments, bArr, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int getKeyCharacteristics(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreKeyCharacteristicsCallback != null ? iKeystoreKeyCharacteristicsCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (keymasterBlob != null) {
                        parcelObtain.writeInt(1);
                        keymasterBlob.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (keymasterBlob2 != null) {
                        parcelObtain.writeInt(1);
                        keymasterBlob2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyCharacteristics(iKeystoreKeyCharacteristicsCallback, str, keymasterBlob, keymasterBlob2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int importKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, KeymasterArguments keymasterArguments, int i, byte[] bArr, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreKeyCharacteristicsCallback != null ? iKeystoreKeyCharacteristicsCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importKey(iKeystoreKeyCharacteristicsCallback, str, keymasterArguments, i, bArr, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int exportKey(IKeystoreExportKeyCallback iKeystoreExportKeyCallback, String str, int i, KeymasterBlob keymasterBlob, KeymasterBlob keymasterBlob2, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreExportKeyCallback != null ? iKeystoreExportKeyCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (keymasterBlob != null) {
                        parcelObtain.writeInt(1);
                        keymasterBlob.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (keymasterBlob2 != null) {
                        parcelObtain.writeInt(1);
                        keymasterBlob2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().exportKey(iKeystoreExportKeyCallback, str, i, keymasterBlob, keymasterBlob2, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int begin(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, String str, int i, boolean z, KeymasterArguments keymasterArguments, byte[] bArr, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreOperationResultCallback != null ? iKeystoreOperationResultCallback.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().begin(iKeystoreOperationResultCallback, iBinder, str, i, z, keymasterArguments, bArr, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int update(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreOperationResultCallback != null ? iKeystoreOperationResultCallback.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().update(iKeystoreOperationResultCallback, iBinder, keymasterArguments, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int finish(IKeystoreOperationResultCallback iKeystoreOperationResultCallback, IBinder iBinder, KeymasterArguments keymasterArguments, byte[] bArr, byte[] bArr2, byte[] bArr3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreOperationResultCallback != null ? iKeystoreOperationResultCallback.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    parcelObtain.writeByteArray(bArr3);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().finish(iKeystoreOperationResultCallback, iBinder, keymasterArguments, bArr, bArr2, bArr3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int abort(IKeystoreResponseCallback iKeystoreResponseCallback, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreResponseCallback != null ? iKeystoreResponseCallback.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().abort(iKeystoreResponseCallback, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int addAuthToken(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAuthToken(bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int onUserAdded(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onUserAdded(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int onUserRemoved(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onUserRemoved(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int attestKey(IKeystoreCertificateChainCallback iKeystoreCertificateChainCallback, String str, KeymasterArguments keymasterArguments) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreCertificateChainCallback != null ? iKeystoreCertificateChainCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attestKey(iKeystoreCertificateChainCallback, str, keymasterArguments);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int attestDeviceIds(IKeystoreCertificateChainCallback iKeystoreCertificateChainCallback, KeymasterArguments keymasterArguments) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreCertificateChainCallback != null ? iKeystoreCertificateChainCallback.asBinder() : null);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attestDeviceIds(iKeystoreCertificateChainCallback, keymasterArguments);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int onDeviceOffBody() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onDeviceOffBody();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int importWrappedKey(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback, String str, byte[] bArr, String str2, byte[] bArr2, KeymasterArguments keymasterArguments, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeystoreKeyCharacteristicsCallback != null ? iKeystoreKeyCharacteristicsCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeByteArray(bArr2);
                    if (keymasterArguments != null) {
                        parcelObtain.writeInt(1);
                        keymasterArguments.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importWrappedKey(iKeystoreKeyCharacteristicsCallback, str, bArr, str2, bArr2, keymasterArguments, j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int presentConfirmationPrompt(IBinder iBinder, String str, byte[] bArr, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().presentConfirmationPrompt(iBinder, str, bArr, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int cancelConfirmationPrompt(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelConfirmationPrompt(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public boolean isConfirmationPromptSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConfirmationPromptSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int onKeyguardVisibilityChanged(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onKeyguardVisibilityChanged(z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public int listUidsOfAuthBoundKeys(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listUidsOfAuthBoundKeys(list);
                    }
                    parcelObtain2.readException();
                    int i = parcelObtain2.readInt();
                    parcelObtain2.readStringList(list);
                    return i;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.keystore.IKeystoreService
            public void getTokensForCredstore(long j, long j2, int i, ICredstoreTokenCallback iCredstoreTokenCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCredstoreTokenCallback != null ? iCredstoreTokenCallback.asBinder() : null);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getTokensForCredstore(j, j2, i, iCredstoreTokenCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeystoreService iKeystoreService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeystoreService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeystoreService;
            return true;
        }

        public static IKeystoreService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
