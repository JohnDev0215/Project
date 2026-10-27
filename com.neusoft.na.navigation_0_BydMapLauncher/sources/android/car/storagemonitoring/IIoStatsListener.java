package android.car.storagemonitoring;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IIoStatsListener extends IInterface {

    public static class Default implements IIoStatsListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.storagemonitoring.IIoStatsListener
        public void onSnapshot(IoStats ioStats) throws RemoteException {
        }
    }

    void onSnapshot(IoStats ioStats) throws RemoteException;

    public static abstract class Stub extends Binder implements IIoStatsListener {
        private static final String DESCRIPTOR = "android.car.storagemonitoring.IIoStatsListener";
        static final int TRANSACTION_onSnapshot = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIoStatsListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIoStatsListener)) {
                return (IIoStatsListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSnapshot(parcel.readInt() != 0 ? IoStats.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IIoStatsListener {
            public static IIoStatsListener sDefaultImpl;
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

            @Override // android.car.storagemonitoring.IIoStatsListener
            public void onSnapshot(IoStats ioStats) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ioStats != null) {
                        parcelObtain.writeInt(1);
                        ioStats.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSnapshot(ioStats);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIoStatsListener iIoStatsListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIoStatsListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIoStatsListener;
            return true;
        }

        public static IIoStatsListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
