package android.net.wifi.nl80211;

import android.net.wifi.nl80211.IApInterface;
import android.net.wifi.nl80211.IClientInterface;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IInterfaceEventCallback extends IInterface {

    public static class Default implements IInterfaceEventCallback {
        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnApInterfaceReady(IApInterface iApInterface) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnApTorndownEvent(IApInterface iApInterface) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnClientInterfaceReady(IClientInterface iClientInterface) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnClientTorndownEvent(IClientInterface iClientInterface) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void OnApInterfaceReady(IApInterface iApInterface) throws RemoteException;

    void OnApTorndownEvent(IApInterface iApInterface) throws RemoteException;

    void OnClientInterfaceReady(IClientInterface iClientInterface) throws RemoteException;

    void OnClientTorndownEvent(IClientInterface iClientInterface) throws RemoteException;

    public static abstract class Stub extends Binder implements IInterfaceEventCallback {
        private static final String DESCRIPTOR = "android.net.wifi.nl80211.IInterfaceEventCallback";
        static final int TRANSACTION_OnApInterfaceReady = 2;
        static final int TRANSACTION_OnApTorndownEvent = 4;
        static final int TRANSACTION_OnClientInterfaceReady = 1;
        static final int TRANSACTION_OnClientTorndownEvent = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "OnClientInterfaceReady";
            }
            if (i == 2) {
                return "OnApInterfaceReady";
            }
            if (i == 3) {
                return "OnClientTorndownEvent";
            }
            if (i != 4) {
                return null;
            }
            return "OnApTorndownEvent";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInterfaceEventCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInterfaceEventCallback)) {
                return (IInterfaceEventCallback) iInterfaceQueryLocalInterface;
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
                OnClientInterfaceReady(IClientInterface.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                OnApInterfaceReady(IApInterface.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                OnClientTorndownEvent(IClientInterface.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                OnApTorndownEvent(IApInterface.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInterfaceEventCallback {
            public static IInterfaceEventCallback sDefaultImpl;
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

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnClientInterfaceReady(IClientInterface iClientInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iClientInterface != null ? iClientInterface.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnClientInterfaceReady(iClientInterface);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnApInterfaceReady(IApInterface iApInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iApInterface != null ? iApInterface.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnApInterfaceReady(iApInterface);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnClientTorndownEvent(IClientInterface iClientInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iClientInterface != null ? iClientInterface.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnClientTorndownEvent(iClientInterface);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnApTorndownEvent(IApInterface iApInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iApInterface != null ? iApInterface.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnApTorndownEvent(iApInterface);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInterfaceEventCallback iInterfaceEventCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInterfaceEventCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInterfaceEventCallback;
            return true;
        }

        public static IInterfaceEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
