package android.net.lowpan;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ILowpanEnergyScanCallback extends IInterface {

    public static class Default implements ILowpanEnergyScanCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.lowpan.ILowpanEnergyScanCallback
        public void onEnergyScanFinished() throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanEnergyScanCallback
        public void onEnergyScanResult(int i, int i2) throws RemoteException {
        }
    }

    void onEnergyScanFinished() throws RemoteException;

    void onEnergyScanResult(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements ILowpanEnergyScanCallback {
        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanEnergyScanCallback";
        static final int TRANSACTION_onEnergyScanFinished = 2;
        static final int TRANSACTION_onEnergyScanResult = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onEnergyScanResult";
            }
            if (i != 2) {
                return null;
            }
            return "onEnergyScanFinished";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILowpanEnergyScanCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILowpanEnergyScanCallback)) {
                return (ILowpanEnergyScanCallback) iInterfaceQueryLocalInterface;
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
                onEnergyScanResult(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onEnergyScanFinished();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ILowpanEnergyScanCallback {
            public static ILowpanEnergyScanCallback sDefaultImpl;
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

            @Override // android.net.lowpan.ILowpanEnergyScanCallback
            public void onEnergyScanResult(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEnergyScanResult(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanEnergyScanCallback
            public void onEnergyScanFinished() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEnergyScanFinished();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILowpanEnergyScanCallback iLowpanEnergyScanCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLowpanEnergyScanCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLowpanEnergyScanCallback;
            return true;
        }

        public static ILowpanEnergyScanCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
