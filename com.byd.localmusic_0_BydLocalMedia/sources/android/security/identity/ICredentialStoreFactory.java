package android.security.identity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.identity.ICredentialStore;

/* JADX INFO: loaded from: classes3.dex */
public interface ICredentialStoreFactory extends IInterface {
    public static final int CREDENTIAL_STORE_TYPE_DEFAULT = 0;
    public static final int CREDENTIAL_STORE_TYPE_DIRECT_ACCESS = 1;

    public static class Default implements ICredentialStoreFactory {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.identity.ICredentialStoreFactory
        public ICredentialStore getCredentialStore(int i) throws RemoteException {
            return null;
        }
    }

    ICredentialStore getCredentialStore(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ICredentialStoreFactory {
        private static final String DESCRIPTOR = "android.security.identity.ICredentialStoreFactory";
        static final int TRANSACTION_getCredentialStore = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "getCredentialStore";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICredentialStoreFactory asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICredentialStoreFactory)) {
                return (ICredentialStoreFactory) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            ICredentialStore credentialStore = getCredentialStore(parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(credentialStore != null ? credentialStore.asBinder() : null);
            return true;
        }

        private static class Proxy implements ICredentialStoreFactory {
            public static ICredentialStoreFactory sDefaultImpl;
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

            @Override // android.security.identity.ICredentialStoreFactory
            public ICredentialStore getCredentialStore(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialStore(i);
                    }
                    parcelObtain2.readException();
                    return ICredentialStore.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICredentialStoreFactory iCredentialStoreFactory) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCredentialStoreFactory == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCredentialStoreFactory;
            return true;
        }

        public static ICredentialStoreFactory getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
