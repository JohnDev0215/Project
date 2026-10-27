package android.net;

import android.net.ConnectivityDiagnosticsManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IConnectivityDiagnosticsCallback extends IInterface {

    public static class Default implements IConnectivityDiagnosticsCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.IConnectivityDiagnosticsCallback
        public void onConnectivityReportAvailable(ConnectivityDiagnosticsManager.ConnectivityReport connectivityReport) throws RemoteException {
        }

        @Override // android.net.IConnectivityDiagnosticsCallback
        public void onDataStallSuspected(ConnectivityDiagnosticsManager.DataStallReport dataStallReport) throws RemoteException {
        }

        @Override // android.net.IConnectivityDiagnosticsCallback
        public void onNetworkConnectivityReported(Network network, boolean z) throws RemoteException {
        }
    }

    void onConnectivityReportAvailable(ConnectivityDiagnosticsManager.ConnectivityReport connectivityReport) throws RemoteException;

    void onDataStallSuspected(ConnectivityDiagnosticsManager.DataStallReport dataStallReport) throws RemoteException;

    void onNetworkConnectivityReported(Network network, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IConnectivityDiagnosticsCallback {
        private static final String DESCRIPTOR = "android.net.IConnectivityDiagnosticsCallback";
        static final int TRANSACTION_onConnectivityReportAvailable = 1;
        static final int TRANSACTION_onDataStallSuspected = 2;
        static final int TRANSACTION_onNetworkConnectivityReported = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onConnectivityReportAvailable";
            }
            if (i == 2) {
                return "onDataStallSuspected";
            }
            if (i != 3) {
                return null;
            }
            return "onNetworkConnectivityReported";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConnectivityDiagnosticsCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IConnectivityDiagnosticsCallback)) {
                return (IConnectivityDiagnosticsCallback) iInterfaceQueryLocalInterface;
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
                onConnectivityReportAvailable(parcel.readInt() != 0 ? ConnectivityDiagnosticsManager.ConnectivityReport.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onDataStallSuspected(parcel.readInt() != 0 ? ConnectivityDiagnosticsManager.DataStallReport.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onNetworkConnectivityReported(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IConnectivityDiagnosticsCallback {
            public static IConnectivityDiagnosticsCallback sDefaultImpl;
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

            @Override // android.net.IConnectivityDiagnosticsCallback
            public void onConnectivityReportAvailable(ConnectivityDiagnosticsManager.ConnectivityReport connectivityReport) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectivityReport != null) {
                        parcelObtain.writeInt(1);
                        connectivityReport.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectivityReportAvailable(connectivityReport);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityDiagnosticsCallback
            public void onDataStallSuspected(ConnectivityDiagnosticsManager.DataStallReport dataStallReport) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dataStallReport != null) {
                        parcelObtain.writeInt(1);
                        dataStallReport.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataStallSuspected(dataStallReport);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityDiagnosticsCallback
            public void onNetworkConnectivityReported(Network network, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNetworkConnectivityReported(network, z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectivityDiagnosticsCallback iConnectivityDiagnosticsCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iConnectivityDiagnosticsCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iConnectivityDiagnosticsCallback;
            return true;
        }

        public static IConnectivityDiagnosticsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
