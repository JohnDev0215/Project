package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IResourceManagerClient extends IInterface {

    public static class Default implements IResourceManagerClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IResourceManagerClient
        public String getName() throws RemoteException {
            return null;
        }

        @Override // android.media.IResourceManagerClient
        public boolean reclaimResource() throws RemoteException {
            return false;
        }
    }

    String getName() throws RemoteException;

    boolean reclaimResource() throws RemoteException;

    public static abstract class Stub extends Binder implements IResourceManagerClient {
        private static final String DESCRIPTOR = "android.media.IResourceManagerClient";
        static final int TRANSACTION_getName = 2;
        static final int TRANSACTION_reclaimResource = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "reclaimResource";
            }
            if (i != 2) {
                return null;
            }
            return "getName";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IResourceManagerClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IResourceManagerClient)) {
                return (IResourceManagerClient) iInterfaceQueryLocalInterface;
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
                boolean zReclaimResource = reclaimResource();
                parcel2.writeNoException();
                parcel2.writeInt(zReclaimResource ? 1 : 0);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            String name = getName();
            parcel2.writeNoException();
            parcel2.writeString(name);
            return true;
        }

        private static class Proxy implements IResourceManagerClient {
            public static IResourceManagerClient sDefaultImpl;
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

            @Override // android.media.IResourceManagerClient
            public boolean reclaimResource() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reclaimResource();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IResourceManagerClient
            public String getName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IResourceManagerClient iResourceManagerClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iResourceManagerClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iResourceManagerClient;
            return true;
        }

        public static IResourceManagerClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
