package android.os.incremental;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IStorageHealthListener extends IInterface {
    public static final int HEALTH_STATUS_BLOCKED = 2;
    public static final int HEALTH_STATUS_OK = 0;
    public static final int HEALTH_STATUS_READS_PENDING = 1;
    public static final int HEALTH_STATUS_UNHEALTHY = 3;

    public static class Default implements IStorageHealthListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.incremental.IStorageHealthListener
        public void onHealthStatus(int i, int i2) throws RemoteException {
        }
    }

    void onHealthStatus(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IStorageHealthListener {
        private static final String DESCRIPTOR = "android.os.incremental.IStorageHealthListener";
        static final int TRANSACTION_onHealthStatus = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onHealthStatus";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStorageHealthListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IStorageHealthListener)) {
                return (IStorageHealthListener) iInterfaceQueryLocalInterface;
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
                onHealthStatus(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IStorageHealthListener {
            public static IStorageHealthListener sDefaultImpl;
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

            @Override // android.os.incremental.IStorageHealthListener
            public void onHealthStatus(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onHealthStatus(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStorageHealthListener iStorageHealthListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iStorageHealthListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iStorageHealthListener;
            return true;
        }

        public static IStorageHealthListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
