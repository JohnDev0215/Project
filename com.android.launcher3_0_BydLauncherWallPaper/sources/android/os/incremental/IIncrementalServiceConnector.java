package android.os.incremental;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IIncrementalServiceConnector extends IInterface {

    public static class Default implements IIncrementalServiceConnector {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.incremental.IIncrementalServiceConnector
        public int setStorageParams(boolean z) throws RemoteException {
            return 0;
        }
    }

    int setStorageParams(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IIncrementalServiceConnector {
        private static final String DESCRIPTOR = "android.os.incremental.IIncrementalServiceConnector";
        static final int TRANSACTION_setStorageParams = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "setStorageParams";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIncrementalServiceConnector asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIncrementalServiceConnector)) {
                return (IIncrementalServiceConnector) iInterfaceQueryLocalInterface;
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
            int storageParams = setStorageParams(parcel.readInt() != 0);
            parcel2.writeNoException();
            parcel2.writeInt(storageParams);
            return true;
        }

        private static class Proxy implements IIncrementalServiceConnector {
            public static IIncrementalServiceConnector sDefaultImpl;
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

            @Override // android.os.incremental.IIncrementalServiceConnector
            public int setStorageParams(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setStorageParams(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIncrementalServiceConnector iIncrementalServiceConnector) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIncrementalServiceConnector == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIncrementalServiceConnector;
            return true;
        }

        public static IIncrementalServiceConnector getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
