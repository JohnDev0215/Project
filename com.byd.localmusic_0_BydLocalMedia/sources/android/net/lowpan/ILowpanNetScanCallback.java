package android.net.lowpan;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ILowpanNetScanCallback extends IInterface {

    public static class Default implements ILowpanNetScanCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.lowpan.ILowpanNetScanCallback
        public void onNetScanBeacon(LowpanBeaconInfo lowpanBeaconInfo) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanNetScanCallback
        public void onNetScanFinished() throws RemoteException {
        }
    }

    void onNetScanBeacon(LowpanBeaconInfo lowpanBeaconInfo) throws RemoteException;

    void onNetScanFinished() throws RemoteException;

    public static abstract class Stub extends Binder implements ILowpanNetScanCallback {
        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanNetScanCallback";
        static final int TRANSACTION_onNetScanBeacon = 1;
        static final int TRANSACTION_onNetScanFinished = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onNetScanBeacon";
            }
            if (i != 2) {
                return null;
            }
            return "onNetScanFinished";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILowpanNetScanCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILowpanNetScanCallback)) {
                return (ILowpanNetScanCallback) iInterfaceQueryLocalInterface;
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
                onNetScanBeacon(parcel.readInt() != 0 ? LowpanBeaconInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onNetScanFinished();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ILowpanNetScanCallback {
            public static ILowpanNetScanCallback sDefaultImpl;
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

            @Override // android.net.lowpan.ILowpanNetScanCallback
            public void onNetScanBeacon(LowpanBeaconInfo lowpanBeaconInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lowpanBeaconInfo != null) {
                        parcelObtain.writeInt(1);
                        lowpanBeaconInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNetScanBeacon(lowpanBeaconInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanNetScanCallback
            public void onNetScanFinished() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNetScanFinished();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILowpanNetScanCallback iLowpanNetScanCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLowpanNetScanCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLowpanNetScanCallback;
            return true;
        }

        public static ILowpanNetScanCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
