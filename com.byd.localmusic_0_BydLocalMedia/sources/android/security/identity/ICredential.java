package android.security.identity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface ICredential extends IInterface {
    public static final int STATUS_NOT_IN_REQUEST_MESSAGE = 3;
    public static final int STATUS_NOT_REQUESTED = 2;
    public static final int STATUS_NO_ACCESS_CONTROL_PROFILES = 6;
    public static final int STATUS_NO_SUCH_ENTRY = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_READER_AUTHENTICATION_FAILED = 5;
    public static final int STATUS_USER_AUTHENTICATION_FAILED = 4;

    public static class Default implements ICredential {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.identity.ICredential
        public byte[] createEphemeralKeyPair() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public byte[] deleteCredential() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public AuthKeyParcel[] getAuthKeysNeedingCertification() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public int[] getAuthenticationDataUsageCount() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public byte[] getCredentialKeyCertificateChain() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public GetEntriesResultParcel getEntries(byte[] bArr, RequestNamespaceParcel[] requestNamespaceParcelArr, byte[] bArr2, byte[] bArr3, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public long selectAuthKey(boolean z) throws RemoteException {
            return 0L;
        }

        @Override // android.security.identity.ICredential
        public void setAvailableAuthenticationKeys(int i, int i2) throws RemoteException {
        }

        @Override // android.security.identity.ICredential
        public void setReaderEphemeralPublicKey(byte[] bArr) throws RemoteException {
        }

        @Override // android.security.identity.ICredential
        public void storeStaticAuthenticationData(AuthKeyParcel authKeyParcel, byte[] bArr) throws RemoteException {
        }
    }

    byte[] createEphemeralKeyPair() throws RemoteException;

    byte[] deleteCredential() throws RemoteException;

    AuthKeyParcel[] getAuthKeysNeedingCertification() throws RemoteException;

    int[] getAuthenticationDataUsageCount() throws RemoteException;

    byte[] getCredentialKeyCertificateChain() throws RemoteException;

    GetEntriesResultParcel getEntries(byte[] bArr, RequestNamespaceParcel[] requestNamespaceParcelArr, byte[] bArr2, byte[] bArr3, boolean z) throws RemoteException;

    long selectAuthKey(boolean z) throws RemoteException;

    void setAvailableAuthenticationKeys(int i, int i2) throws RemoteException;

    void setReaderEphemeralPublicKey(byte[] bArr) throws RemoteException;

    void storeStaticAuthenticationData(AuthKeyParcel authKeyParcel, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ICredential {
        private static final String DESCRIPTOR = "android.security.identity.ICredential";
        static final int TRANSACTION_createEphemeralKeyPair = 1;
        static final int TRANSACTION_deleteCredential = 3;
        static final int TRANSACTION_getAuthKeysNeedingCertification = 8;
        static final int TRANSACTION_getAuthenticationDataUsageCount = 10;
        static final int TRANSACTION_getCredentialKeyCertificateChain = 4;
        static final int TRANSACTION_getEntries = 6;
        static final int TRANSACTION_selectAuthKey = 5;
        static final int TRANSACTION_setAvailableAuthenticationKeys = 7;
        static final int TRANSACTION_setReaderEphemeralPublicKey = 2;
        static final int TRANSACTION_storeStaticAuthenticationData = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "createEphemeralKeyPair";
                case 2:
                    return "setReaderEphemeralPublicKey";
                case 3:
                    return "deleteCredential";
                case 4:
                    return "getCredentialKeyCertificateChain";
                case 5:
                    return "selectAuthKey";
                case 6:
                    return "getEntries";
                case 7:
                    return "setAvailableAuthenticationKeys";
                case 8:
                    return "getAuthKeysNeedingCertification";
                case 9:
                    return "storeStaticAuthenticationData";
                case 10:
                    return "getAuthenticationDataUsageCount";
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

        public static ICredential asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICredential)) {
                return (ICredential) iInterfaceQueryLocalInterface;
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
                    byte[] bArrCreateEphemeralKeyPair = createEphemeralKeyPair();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrCreateEphemeralKeyPair);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReaderEphemeralPublicKey(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrDeleteCredential = deleteCredential();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrDeleteCredential);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] credentialKeyCertificateChain = getCredentialKeyCertificateChain();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(credentialKeyCertificateChain);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jSelectAuthKey = selectAuthKey(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeLong(jSelectAuthKey);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    GetEntriesResultParcel entries = getEntries(parcel.createByteArray(), (RequestNamespaceParcel[]) parcel.createTypedArray(RequestNamespaceParcel.CREATOR), parcel.createByteArray(), parcel.createByteArray(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (entries != null) {
                        parcel2.writeInt(1);
                        entries.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAvailableAuthenticationKeys(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    AuthKeyParcel[] authKeysNeedingCertification = getAuthKeysNeedingCertification();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(authKeysNeedingCertification, 1);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    storeStaticAuthenticationData(parcel.readInt() != 0 ? AuthKeyParcel.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] authenticationDataUsageCount = getAuthenticationDataUsageCount();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(authenticationDataUsageCount);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICredential {
            public static ICredential sDefaultImpl;
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

            @Override // android.security.identity.ICredential
            public byte[] createEphemeralKeyPair() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createEphemeralKeyPair();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public void setReaderEphemeralPublicKey(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReaderEphemeralPublicKey(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public byte[] deleteCredential() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteCredential();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public byte[] getCredentialKeyCertificateChain() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialKeyCertificateChain();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public long selectAuthKey(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectAuthKey(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public GetEntriesResultParcel getEntries(byte[] bArr, RequestNamespaceParcel[] requestNamespaceParcelArr, byte[] bArr2, byte[] bArr3, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeTypedArray(requestNamespaceParcelArr, 0);
                    parcelObtain.writeByteArray(bArr2);
                    parcelObtain.writeByteArray(bArr3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEntries(bArr, requestNamespaceParcelArr, bArr2, bArr3, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? GetEntriesResultParcel.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public void setAvailableAuthenticationKeys(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAvailableAuthenticationKeys(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public AuthKeyParcel[] getAuthKeysNeedingCertification() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthKeysNeedingCertification();
                    }
                    parcelObtain2.readException();
                    return (AuthKeyParcel[]) parcelObtain2.createTypedArray(AuthKeyParcel.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public void storeStaticAuthenticationData(AuthKeyParcel authKeyParcel, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (authKeyParcel != null) {
                        parcelObtain.writeInt(1);
                        authKeyParcel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().storeStaticAuthenticationData(authKeyParcel, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public int[] getAuthenticationDataUsageCount() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthenticationDataUsageCount();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICredential iCredential) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCredential == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCredential;
            return true;
        }

        public static ICredential getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
