package android.net.netstats.provider;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkStatsProvider extends IInterface {

    public static class Default implements INetworkStatsProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onRequestStatsUpdate(int i) throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onSetAlert(long j) throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onSetLimit(String str, long j) throws RemoteException {
        }
    }

    void onRequestStatsUpdate(int i) throws RemoteException;

    void onSetAlert(long j) throws RemoteException;

    void onSetLimit(String str, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkStatsProvider {
        private static final String DESCRIPTOR = "android.net.netstats.provider.INetworkStatsProvider";
        static final int TRANSACTION_onRequestStatsUpdate = 1;
        static final int TRANSACTION_onSetAlert = 3;
        static final int TRANSACTION_onSetLimit = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onRequestStatsUpdate";
            }
            if (i == 2) {
                return "onSetLimit";
            }
            if (i != 3) {
                return null;
            }
            return "onSetAlert";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkStatsProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkStatsProvider)) {
                return (INetworkStatsProvider) iInterfaceQueryLocalInterface;
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
                onRequestStatsUpdate(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onSetLimit(parcel.readString(), parcel.readLong());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onSetAlert(parcel.readLong());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements INetworkStatsProvider {
            public static INetworkStatsProvider sDefaultImpl;
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

            @Override // android.net.netstats.provider.INetworkStatsProvider
            public void onRequestStatsUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRequestStatsUpdate(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProvider
            public void onSetLimit(String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetLimit(str, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProvider
            public void onSetAlert(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetAlert(j);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkStatsProvider iNetworkStatsProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetworkStatsProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetworkStatsProvider;
            return true;
        }

        public static INetworkStatsProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
