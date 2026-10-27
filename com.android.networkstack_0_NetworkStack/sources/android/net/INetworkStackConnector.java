package android.net;

import android.net.IIpMemoryStoreCallbacks;
import android.net.INetworkMonitorCallbacks;
import android.net.dhcp.DhcpServingParamsParcel;
import android.net.dhcp.IDhcpServerCallbacks;
import android.net.ip.IIpClientCallbacks;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface INetworkStackConnector extends IInterface {
    void fetchIpMemoryStore(IIpMemoryStoreCallbacks iIpMemoryStoreCallbacks) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void makeDhcpServer(String str, DhcpServingParamsParcel dhcpServingParamsParcel, IDhcpServerCallbacks iDhcpServerCallbacks) throws RemoteException;

    void makeIpClient(String str, IIpClientCallbacks iIpClientCallbacks) throws RemoteException;

    void makeNetworkMonitor(Network network, String str, INetworkMonitorCallbacks iNetworkMonitorCallbacks) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkStackConnector {
        private static final String DESCRIPTOR = "android$net$INetworkStackConnector".replace('$', '.');

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
                makeDhcpServer(parcel.readString(), parcel.readInt() != 0 ? DhcpServingParamsParcel.CREATOR.createFromParcel(parcel) : null, IDhcpServerCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(str);
                makeNetworkMonitor(parcel.readInt() != 0 ? (Network) Network.CREATOR.createFromParcel(parcel) : null, parcel.readString(), INetworkMonitorCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(str);
                makeIpClient(parcel.readString(), IIpClientCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(str);
                fetchIpMemoryStore(IIpMemoryStoreCallbacks.Stub.asInterface(parcel.readStrongBinder()));
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
