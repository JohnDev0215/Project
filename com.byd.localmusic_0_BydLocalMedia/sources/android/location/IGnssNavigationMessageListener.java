package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IGnssNavigationMessageListener extends IInterface {

    public static class Default implements IGnssNavigationMessageListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.location.IGnssNavigationMessageListener
        public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssNavigationMessage) throws RemoteException {
        }

        @Override // android.location.IGnssNavigationMessageListener
        public void onStatusChanged(int i) throws RemoteException {
        }
    }

    void onGnssNavigationMessageReceived(GnssNavigationMessage gnssNavigationMessage) throws RemoteException;

    void onStatusChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IGnssNavigationMessageListener {
        private static final String DESCRIPTOR = "android.location.IGnssNavigationMessageListener";
        static final int TRANSACTION_onGnssNavigationMessageReceived = 1;
        static final int TRANSACTION_onStatusChanged = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onGnssNavigationMessageReceived";
            }
            if (i != 2) {
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

        public static IGnssNavigationMessageListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGnssNavigationMessageListener)) {
                return (IGnssNavigationMessageListener) iInterfaceQueryLocalInterface;
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
                onGnssNavigationMessageReceived(parcel.readInt() != 0 ? GnssNavigationMessage.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onStatusChanged(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IGnssNavigationMessageListener {
            public static IGnssNavigationMessageListener sDefaultImpl;
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

            @Override // android.location.IGnssNavigationMessageListener
            public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssNavigationMessage) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (gnssNavigationMessage != null) {
                        parcelObtain.writeInt(1);
                        gnssNavigationMessage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGnssNavigationMessageReceived(gnssNavigationMessage);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.IGnssNavigationMessageListener
            public void onStatusChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStatusChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGnssNavigationMessageListener iGnssNavigationMessageListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGnssNavigationMessageListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGnssNavigationMessageListener;
            return true;
        }

        public static IGnssNavigationMessageListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
