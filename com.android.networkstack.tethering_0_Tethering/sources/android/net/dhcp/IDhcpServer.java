package android.net.dhcp;

import android.net.INetworkStackStatusCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IDhcpServer extends IInterface {
    void startWithCallbacks(INetworkStackStatusCallback iNetworkStackStatusCallback, IDhcpEventCallbacks iDhcpEventCallbacks) throws RemoteException;

    void stop(INetworkStackStatusCallback iNetworkStackStatusCallback) throws RemoteException;

    void updateParams(DhcpServingParamsParcel dhcpServingParamsParcel, INetworkStackStatusCallback iNetworkStackStatusCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IDhcpServer {
        private static final String DESCRIPTOR = "android$net$dhcp$IDhcpServer".replace('$', '.');

        public static IDhcpServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDhcpServer)) {
                return (IDhcpServer) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IDhcpServer {
            public static IDhcpServer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.dhcp.IDhcpServer
            public void startWithCallbacks(INetworkStackStatusCallback iNetworkStackStatusCallback, IDhcpEventCallbacks iDhcpEventCallbacks) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNetworkStackStatusCallback != null ? iNetworkStackStatusCallback.asBinder() : null);
                    parcelObtain.writeStrongBinder(iDhcpEventCallbacks != null ? iDhcpEventCallbacks.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startWithCallbacks(iNetworkStackStatusCallback, iDhcpEventCallbacks);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.dhcp.IDhcpServer
            public void updateParams(DhcpServingParamsParcel dhcpServingParamsParcel, INetworkStackStatusCallback iNetworkStackStatusCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dhcpServingParamsParcel != null) {
                        parcelObtain.writeInt(1);
                        dhcpServingParamsParcel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iNetworkStackStatusCallback != null ? iNetworkStackStatusCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateParams(dhcpServingParamsParcel, iNetworkStackStatusCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.dhcp.IDhcpServer
            public void stop(INetworkStackStatusCallback iNetworkStackStatusCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNetworkStackStatusCallback != null ? iNetworkStackStatusCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stop(iNetworkStackStatusCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static IDhcpServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
