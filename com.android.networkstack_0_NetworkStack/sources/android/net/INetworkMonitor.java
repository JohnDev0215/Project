package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.networkstack.metrics.NetworkIpProvisioningReported;

/* JADX INFO: loaded from: classes.dex */
public interface INetworkMonitor extends IInterface {
    void forceReevaluation(int i) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void launchCaptivePortalApp() throws RemoteException;

    void notifyCaptivePortalAppFinished(int i) throws RemoteException;

    void notifyDnsResponse(int i) throws RemoteException;

    void notifyLinkPropertiesChanged(LinkProperties linkProperties) throws RemoteException;

    void notifyNetworkCapabilitiesChanged(NetworkCapabilities networkCapabilities) throws RemoteException;

    void notifyNetworkConnected(LinkProperties linkProperties, NetworkCapabilities networkCapabilities) throws RemoteException;

    void notifyNetworkDisconnected() throws RemoteException;

    void notifyPrivateDnsChanged(PrivateDnsConfigParcel privateDnsConfigParcel) throws RemoteException;

    void setAcceptPartialConnectivity() throws RemoteException;

    void start() throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkMonitor {
        private static final String DESCRIPTOR = "android$net$INetworkMonitor".replace('$', '.');

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
                    start();
                    return true;
                case 2:
                    parcel.enforceInterface(str);
                    launchCaptivePortalApp();
                    return true;
                case 3:
                    parcel.enforceInterface(str);
                    notifyCaptivePortalAppFinished(parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(str);
                    setAcceptPartialConnectivity();
                    return true;
                case 5:
                    parcel.enforceInterface(str);
                    forceReevaluation(parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(str);
                    notifyPrivateDnsChanged(parcel.readInt() != 0 ? PrivateDnsConfigParcel.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                    parcel.enforceInterface(str);
                    notifyDnsResponse(parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(str);
                    notifyNetworkConnected(parcel.readInt() != 0 ? (LinkProperties) LinkProperties.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (NetworkCapabilities) NetworkCapabilities.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(str);
                    notifyNetworkDisconnected();
                    return true;
                case 10:
                    parcel.enforceInterface(str);
                    notifyLinkPropertiesChanged(parcel.readInt() != 0 ? (LinkProperties) LinkProperties.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(str);
                    notifyNetworkCapabilitiesChanged(parcel.readInt() != 0 ? (NetworkCapabilities) NetworkCapabilities.CREATOR.createFromParcel(parcel) : null);
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
