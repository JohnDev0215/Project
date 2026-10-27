package android.net.dhcp;

import android.net.INetworkStackStatusCallback;
import android.net.dhcp.IDhcpEventCallbacks;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IDhcpServer extends IInterface {
    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void start(INetworkStackStatusCallback iNetworkStackStatusCallback) throws RemoteException;

    void startWithCallbacks(INetworkStackStatusCallback iNetworkStackStatusCallback, IDhcpEventCallbacks iDhcpEventCallbacks) throws RemoteException;

    void stop(INetworkStackStatusCallback iNetworkStackStatusCallback) throws RemoteException;

    void updateParams(DhcpServingParamsParcel dhcpServingParamsParcel, INetworkStackStatusCallback iNetworkStackStatusCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IDhcpServer {
        private static final String DESCRIPTOR = "android$net$dhcp$IDhcpServer".replace('$', '.');

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String str = DESCRIPTOR;
            if (i == 1) {
                parcel.enforceInterface(str);
                start(INetworkStackStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(str);
                updateParams(parcel.readInt() != 0 ? DhcpServingParamsParcel.CREATOR.createFromParcel(parcel) : null, INetworkStackStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(str);
                stop(INetworkStackStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(str);
                startWithCallbacks(INetworkStackStatusCallback.Stub.asInterface(parcel.readStrongBinder()), IDhcpEventCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            switch (i) {
                case 16777214:
                    parcel.enforceInterface(str);
                    parcel2.writeNoException();
                    parcel2.writeString(getInterfaceHash());
                    return true;
                case 16777215:
                    parcel.enforceInterface(str);
                    parcel2.writeNoException();
                    parcel2.writeInt(getInterfaceVersion());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }
}
