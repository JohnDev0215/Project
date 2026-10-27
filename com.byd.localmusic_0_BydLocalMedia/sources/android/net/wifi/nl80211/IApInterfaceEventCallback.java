package android.net.wifi.nl80211;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IApInterfaceEventCallback extends IInterface {
    public static final int BANDWIDTH_160 = 6;
    public static final int BANDWIDTH_20 = 2;
    public static final int BANDWIDTH_20_NOHT = 1;
    public static final int BANDWIDTH_40 = 3;
    public static final int BANDWIDTH_80 = 4;
    public static final int BANDWIDTH_80P80 = 5;
    public static final int BANDWIDTH_INVALID = 0;

    public static class Default implements IApInterfaceEventCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
        public void onConnectedClientsChanged(NativeWifiClient nativeWifiClient, boolean z) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
        public void onSoftApChannelSwitched(int i, int i2) throws RemoteException {
        }
    }

    void onConnectedClientsChanged(NativeWifiClient nativeWifiClient, boolean z) throws RemoteException;

    void onSoftApChannelSwitched(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IApInterfaceEventCallback {
        private static final String DESCRIPTOR = "android.net.wifi.nl80211.IApInterfaceEventCallback";
        static final int TRANSACTION_onConnectedClientsChanged = 1;
        static final int TRANSACTION_onSoftApChannelSwitched = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onConnectedClientsChanged";
            }
            if (i != 2) {
                return null;
            }
            return "onSoftApChannelSwitched";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IApInterfaceEventCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IApInterfaceEventCallback)) {
                return (IApInterfaceEventCallback) iInterfaceQueryLocalInterface;
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
                onConnectedClientsChanged(parcel.readInt() != 0 ? NativeWifiClient.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onSoftApChannelSwitched(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IApInterfaceEventCallback {
            public static IApInterfaceEventCallback sDefaultImpl;
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

            @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
            public void onConnectedClientsChanged(NativeWifiClient nativeWifiClient, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (nativeWifiClient != null) {
                        parcelObtain.writeInt(1);
                        nativeWifiClient.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectedClientsChanged(nativeWifiClient, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
            public void onSoftApChannelSwitched(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSoftApChannelSwitched(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IApInterfaceEventCallback iApInterfaceEventCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iApInterfaceEventCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iApInterfaceEventCallback;
            return true;
        }

        public static IApInterfaceEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
