package android.net.ip;

import android.net.Layer2InformationParcelable;
import android.net.NattKeepalivePacketDataParcelable;
import android.net.ProvisioningConfigurationParcelable;
import android.net.ProxyInfo;
import android.net.TcpKeepalivePacketDataParcelable;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.networkstack.metrics.NetworkIpProvisioningReported;

/* JADX INFO: loaded from: classes.dex */
public interface IIpClient extends IInterface {
    void addKeepalivePacketFilter(int i, TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable) throws RemoteException;

    void addNattKeepalivePacketFilter(int i, NattKeepalivePacketDataParcelable nattKeepalivePacketDataParcelable) throws RemoteException;

    void completedPreDhcpAction() throws RemoteException;

    void confirmConfiguration() throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void notifyPreconnectionComplete(boolean z) throws RemoteException;

    void readPacketFilterComplete(byte[] bArr) throws RemoteException;

    void removeKeepalivePacketFilter(int i) throws RemoteException;

    void setHttpProxy(ProxyInfo proxyInfo) throws RemoteException;

    void setL2KeyAndGroupHint(String str, String str2) throws RemoteException;

    void setMulticastFilter(boolean z) throws RemoteException;

    void setTcpBufferSizes(String str) throws RemoteException;

    void shutdown() throws RemoteException;

    void startProvisioning(ProvisioningConfigurationParcelable provisioningConfigurationParcelable) throws RemoteException;

    void stop() throws RemoteException;

    void updateLayer2Information(Layer2InformationParcelable layer2InformationParcelable) throws RemoteException;

    public static abstract class Stub extends Binder implements IIpClient {
        private static final String DESCRIPTOR = "android$net$ip$IIpClient".replace('$', '.');

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
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(str);
                    completedPreDhcpAction();
                    return true;
                case 2:
                    parcel.enforceInterface(str);
                    confirmConfiguration();
                    return true;
                case 3:
                    parcel.enforceInterface(str);
                    readPacketFilterComplete(parcel.createByteArray());
                    return true;
                case 4:
                    parcel.enforceInterface(str);
                    shutdown();
                    return true;
                case 5:
                    parcel.enforceInterface(str);
                    startProvisioning(parcel.readInt() != 0 ? ProvisioningConfigurationParcelable.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(str);
                    stop();
                    return true;
                case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                    parcel.enforceInterface(str);
                    setTcpBufferSizes(parcel.readString());
                    return true;
                case 8:
                    parcel.enforceInterface(str);
                    setHttpProxy(parcel.readInt() != 0 ? (ProxyInfo) ProxyInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(str);
                    setMulticastFilter(parcel.readInt() != 0);
                    return true;
                case 10:
                    parcel.enforceInterface(str);
                    addKeepalivePacketFilter(parcel.readInt(), parcel.readInt() != 0 ? TcpKeepalivePacketDataParcelable.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(str);
                    removeKeepalivePacketFilter(parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(str);
                    setL2KeyAndGroupHint(parcel.readString(), parcel.readString());
                    return true;
                case 13:
                    parcel.enforceInterface(str);
                    addNattKeepalivePacketFilter(parcel.readInt(), parcel.readInt() != 0 ? NattKeepalivePacketDataParcelable.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 14:
                    parcel.enforceInterface(str);
                    notifyPreconnectionComplete(parcel.readInt() != 0);
                    return true;
                case 15:
                    parcel.enforceInterface(str);
                    updateLayer2Information(parcel.readInt() != 0 ? Layer2InformationParcelable.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
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
}
