package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IDataLoaderStatusListener extends IInterface {
    public static final int DATA_LOADER_BOUND = 1;
    public static final int DATA_LOADER_CREATED = 2;
    public static final int DATA_LOADER_DESTROYED = 0;
    public static final int DATA_LOADER_IMAGE_NOT_READY = 6;
    public static final int DATA_LOADER_IMAGE_READY = 5;
    public static final int DATA_LOADER_STARTED = 3;
    public static final int DATA_LOADER_STOPPED = 4;
    public static final int DATA_LOADER_UNAVAILABLE = 7;
    public static final int DATA_LOADER_UNRECOVERABLE = 8;

    public static class Default implements IDataLoaderStatusListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.IDataLoaderStatusListener
        public void onStatusChanged(int i, int i2) throws RemoteException {
        }
    }

    void onStatusChanged(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataLoaderStatusListener {
        private static final String DESCRIPTOR = "android.content.pm.IDataLoaderStatusListener";
        static final int TRANSACTION_onStatusChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onStatusChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDataLoaderStatusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDataLoaderStatusListener)) {
                return (IDataLoaderStatusListener) iInterfaceQueryLocalInterface;
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
                onStatusChanged(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDataLoaderStatusListener {
            public static IDataLoaderStatusListener sDefaultImpl;
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

            @Override // android.content.pm.IDataLoaderStatusListener
            public void onStatusChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStatusChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataLoaderStatusListener iDataLoaderStatusListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDataLoaderStatusListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDataLoaderStatusListener;
            return true;
        }

        public static IDataLoaderStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
