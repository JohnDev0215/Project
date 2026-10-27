package android.net.wifi.nl80211;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IScanEvent extends IInterface {

    public static class Default implements IScanEvent {
        @Override // android.net.wifi.nl80211.IScanEvent
        public void OnScanFailed() throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IScanEvent
        public void OnScanResultReady() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void OnScanFailed() throws RemoteException;

    void OnScanResultReady() throws RemoteException;

    public static abstract class Stub extends Binder implements IScanEvent {
        private static final String DESCRIPTOR = "android.net.wifi.nl80211.IScanEvent";
        static final int TRANSACTION_OnScanFailed = 2;
        static final int TRANSACTION_OnScanResultReady = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "OnScanResultReady";
            }
            if (i != 2) {
                return null;
            }
            return "OnScanFailed";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IScanEvent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IScanEvent)) {
                return (IScanEvent) iInterfaceQueryLocalInterface;
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
                OnScanResultReady();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                OnScanFailed();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IScanEvent {
            public static IScanEvent sDefaultImpl;
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

            @Override // android.net.wifi.nl80211.IScanEvent
            public void OnScanResultReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnScanResultReady();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IScanEvent
            public void OnScanFailed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnScanFailed();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScanEvent iScanEvent) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iScanEvent == null) {
                return false;
            }
            Proxy.sDefaultImpl = iScanEvent;
            return true;
        }

        public static IScanEvent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
