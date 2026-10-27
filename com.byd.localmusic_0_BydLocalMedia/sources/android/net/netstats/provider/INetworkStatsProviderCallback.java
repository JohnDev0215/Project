package android.net.netstats.provider;

import android.net.NetworkStats;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkStatsProviderCallback extends IInterface {

    public static class Default implements INetworkStatsProviderCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void notifyAlertReached() throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void notifyLimitReached() throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void notifyStatsUpdated(int i, NetworkStats networkStats, NetworkStats networkStats2) throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void unregister() throws RemoteException {
        }
    }

    void notifyAlertReached() throws RemoteException;

    void notifyLimitReached() throws RemoteException;

    void notifyStatsUpdated(int i, NetworkStats networkStats, NetworkStats networkStats2) throws RemoteException;

    void unregister() throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkStatsProviderCallback {
        private static final String DESCRIPTOR = "android.net.netstats.provider.INetworkStatsProviderCallback";
        static final int TRANSACTION_notifyAlertReached = 2;
        static final int TRANSACTION_notifyLimitReached = 3;
        static final int TRANSACTION_notifyStatsUpdated = 1;
        static final int TRANSACTION_unregister = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "notifyStatsUpdated";
            }
            if (i == 2) {
                return "notifyAlertReached";
            }
            if (i == 3) {
                return "notifyLimitReached";
            }
            if (i != 4) {
                return null;
            }
            return "unregister";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkStatsProviderCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkStatsProviderCallback)) {
                return (INetworkStatsProviderCallback) iInterfaceQueryLocalInterface;
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
                notifyStatsUpdated(parcel.readInt(), parcel.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyAlertReached();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyLimitReached();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                unregister();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements INetworkStatsProviderCallback {
            public static INetworkStatsProviderCallback sDefaultImpl;
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

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void notifyStatsUpdated(int i, NetworkStats networkStats, NetworkStats networkStats2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (networkStats != null) {
                        parcelObtain.writeInt(1);
                        networkStats.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (networkStats2 != null) {
                        parcelObtain.writeInt(1);
                        networkStats2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyStatsUpdated(i, networkStats, networkStats2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void notifyAlertReached() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyAlertReached();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void notifyLimitReached() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyLimitReached();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void unregister() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregister();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkStatsProviderCallback iNetworkStatsProviderCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetworkStatsProviderCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetworkStatsProviderCallback;
            return true;
        }

        public static INetworkStatsProviderCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
