package android.content.pm;

import android.content.pm.IDataLoader;
import android.content.pm.IDataLoaderStatusListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IDataLoaderManager extends IInterface {

    public static class Default implements IDataLoaderManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.IDataLoaderManager
        public boolean bindToDataLoader(int i, DataLoaderParamsParcel dataLoaderParamsParcel, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IDataLoaderManager
        public IDataLoader getDataLoader(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IDataLoaderManager
        public void unbindFromDataLoader(int i) throws RemoteException {
        }
    }

    boolean bindToDataLoader(int i, DataLoaderParamsParcel dataLoaderParamsParcel, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException;

    IDataLoader getDataLoader(int i) throws RemoteException;

    void unbindFromDataLoader(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataLoaderManager {
        private static final String DESCRIPTOR = "android.content.pm.IDataLoaderManager";
        static final int TRANSACTION_bindToDataLoader = 1;
        static final int TRANSACTION_getDataLoader = 2;
        static final int TRANSACTION_unbindFromDataLoader = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "bindToDataLoader";
            }
            if (i == 2) {
                return "getDataLoader";
            }
            if (i != 3) {
                return null;
            }
            return "unbindFromDataLoader";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDataLoaderManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDataLoaderManager)) {
                return (IDataLoaderManager) iInterfaceQueryLocalInterface;
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
                boolean zBindToDataLoader = bindToDataLoader(parcel.readInt(), parcel.readInt() != 0 ? DataLoaderParamsParcel.CREATOR.createFromParcel(parcel) : null, IDataLoaderStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zBindToDataLoader ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                IDataLoader dataLoader = getDataLoader(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(dataLoader != null ? dataLoader.asBinder() : null);
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
            unbindFromDataLoader(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IDataLoaderManager {
            public static IDataLoaderManager sDefaultImpl;
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

            @Override // android.content.pm.IDataLoaderManager
            public boolean bindToDataLoader(int i, DataLoaderParamsParcel dataLoaderParamsParcel, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (dataLoaderParamsParcel != null) {
                        parcelObtain.writeInt(1);
                        dataLoaderParamsParcel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iDataLoaderStatusListener != null ? iDataLoaderStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().bindToDataLoader(i, dataLoaderParamsParcel, iDataLoaderStatusListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IDataLoaderManager
            public IDataLoader getDataLoader(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataLoader(i);
                    }
                    parcelObtain2.readException();
                    return IDataLoader.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IDataLoaderManager
            public void unbindFromDataLoader(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unbindFromDataLoader(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataLoaderManager iDataLoaderManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDataLoaderManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDataLoaderManager;
            return true;
        }

        public static IDataLoaderManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
