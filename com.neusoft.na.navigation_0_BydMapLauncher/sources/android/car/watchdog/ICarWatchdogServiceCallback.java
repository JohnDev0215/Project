package android.car.watchdog;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarWatchdogServiceCallback extends IInterface {

    public static class Default implements ICarWatchdogServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.watchdog.ICarWatchdogServiceCallback
        public void onCheckHealthStatus(int i, int i2) throws RemoteException {
        }

        @Override // android.car.watchdog.ICarWatchdogServiceCallback
        public void onPrepareProcessTermination() throws RemoteException {
        }
    }

    void onCheckHealthStatus(int i, int i2) throws RemoteException;

    void onPrepareProcessTermination() throws RemoteException;

    public static abstract class Stub extends Binder implements ICarWatchdogServiceCallback {
        private static final String DESCRIPTOR = "android.car.watchdog.ICarWatchdogServiceCallback";
        static final int TRANSACTION_onCheckHealthStatus = 1;
        static final int TRANSACTION_onPrepareProcessTermination = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarWatchdogServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarWatchdogServiceCallback)) {
                return (ICarWatchdogServiceCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onCheckHealthStatus(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onPrepareProcessTermination();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICarWatchdogServiceCallback {
            public static ICarWatchdogServiceCallback sDefaultImpl;
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

            @Override // android.car.watchdog.ICarWatchdogServiceCallback
            public void onCheckHealthStatus(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCheckHealthStatus(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.watchdog.ICarWatchdogServiceCallback
            public void onPrepareProcessTermination() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrepareProcessTermination();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarWatchdogServiceCallback iCarWatchdogServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarWatchdogServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarWatchdogServiceCallback;
            return true;
        }

        public static ICarWatchdogServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
