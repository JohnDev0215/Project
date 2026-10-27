package android.security.identity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.identity.ICredential;
import android.security.identity.IWritableCredential;

/* JADX INFO: loaded from: classes3.dex */
public interface ICredentialStore extends IInterface {
    public static final int ERROR_ALREADY_PERSONALIZED = 2;
    public static final int ERROR_AUTHENTICATION_KEY_NOT_FOUND = 9;
    public static final int ERROR_CIPHER_SUITE_NOT_SUPPORTED = 4;
    public static final int ERROR_DOCUMENT_TYPE_NOT_SUPPORTED = 8;
    public static final int ERROR_EPHEMERAL_PUBLIC_KEY_NOT_FOUND = 5;
    public static final int ERROR_GENERIC = 1;
    public static final int ERROR_INVALID_ITEMS_REQUEST_MESSAGE = 10;
    public static final int ERROR_INVALID_READER_SIGNATURE = 7;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NO_AUTHENTICATION_KEY_AVAILABLE = 6;
    public static final int ERROR_NO_SUCH_CREDENTIAL = 3;
    public static final int ERROR_SESSION_TRANSCRIPT_MISMATCH = 11;

    public static class Default implements ICredentialStore {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.identity.ICredentialStore
        public IWritableCredential createCredential(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredentialStore
        public ICredential getCredentialByName(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredentialStore
        public SecurityHardwareInfoParcel getSecurityHardwareInfo() throws RemoteException {
            return null;
        }
    }

    IWritableCredential createCredential(String str, String str2) throws RemoteException;

    ICredential getCredentialByName(String str, int i) throws RemoteException;

    SecurityHardwareInfoParcel getSecurityHardwareInfo() throws RemoteException;

    public static abstract class Stub extends Binder implements ICredentialStore {
        private static final String DESCRIPTOR = "android.security.identity.ICredentialStore";
        static final int TRANSACTION_createCredential = 2;
        static final int TRANSACTION_getCredentialByName = 3;
        static final int TRANSACTION_getSecurityHardwareInfo = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getSecurityHardwareInfo";
            }
            if (i == 2) {
                return "createCredential";
            }
            if (i != 3) {
                return null;
            }
            return "getCredentialByName";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICredentialStore asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICredentialStore)) {
                return (ICredentialStore) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                SecurityHardwareInfoParcel securityHardwareInfo = getSecurityHardwareInfo();
                parcel2.writeNoException();
                if (securityHardwareInfo != null) {
                    parcel2.writeInt(1);
                    securityHardwareInfo.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                IWritableCredential iWritableCredentialCreateCredential = createCredential(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(iWritableCredentialCreateCredential != null ? iWritableCredentialCreateCredential.asBinder() : null);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            ICredential credentialByName = getCredentialByName(parcel.readString(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(credentialByName != null ? credentialByName.asBinder() : null);
            return true;
        }

        private static class Proxy implements ICredentialStore {
            public static ICredentialStore sDefaultImpl;
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

            @Override // android.security.identity.ICredentialStore
            public SecurityHardwareInfoParcel getSecurityHardwareInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSecurityHardwareInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SecurityHardwareInfoParcel.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredentialStore
            public IWritableCredential createCredential(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createCredential(str, str2);
                    }
                    parcelObtain2.readException();
                    return IWritableCredential.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.ICredentialStore
            public ICredential getCredentialByName(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialByName(str, i);
                    }
                    parcelObtain2.readException();
                    return ICredential.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICredentialStore iCredentialStore) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCredentialStore == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCredentialStore;
            return true;
        }

        public static ICredentialStore getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
