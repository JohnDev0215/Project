package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.networkstack.metrics.NetworkIpProvisioningReported;

/* JADX INFO: loaded from: classes.dex */
public interface INetdUnsolicitedEventListener extends IInterface {
    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void onInterfaceAdded(String str) throws RemoteException;

    void onInterfaceAddressRemoved(String str, String str2, int i, int i2) throws RemoteException;

    void onInterfaceAddressUpdated(String str, String str2, int i, int i2) throws RemoteException;

    void onInterfaceChanged(String str, boolean z) throws RemoteException;

    void onInterfaceClassActivityChanged(boolean z, int i, long j, int i2) throws RemoteException;

    void onInterfaceDnsServerInfo(String str, long j, String[] strArr) throws RemoteException;

    void onInterfaceLinkStateChanged(String str, boolean z) throws RemoteException;

    void onInterfaceRemoved(String str) throws RemoteException;

    void onQuotaLimitReached(String str, String str2) throws RemoteException;

    void onRouteChanged(boolean z, String str, String str2, String str3) throws RemoteException;

    void onStrictCleartextDetected(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements INetdUnsolicitedEventListener {
        private static final String DESCRIPTOR = "android$net$INetdUnsolicitedEventListener".replace('$', '.');

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
                    onInterfaceClassActivityChanged(parcel.readInt() != 0, parcel.readInt(), parcel.readLong(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(str);
                    onQuotaLimitReached(parcel.readString(), parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(str);
                    onInterfaceDnsServerInfo(parcel.readString(), parcel.readLong(), parcel.createStringArray());
                    return true;
                case 4:
                    parcel.enforceInterface(str);
                    onInterfaceAddressUpdated(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(str);
                    onInterfaceAddressRemoved(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(str);
                    onInterfaceAdded(parcel.readString());
                    return true;
                case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                    parcel.enforceInterface(str);
                    onInterfaceRemoved(parcel.readString());
                    return true;
                case 8:
                    parcel.enforceInterface(str);
                    onInterfaceChanged(parcel.readString(), parcel.readInt() != 0);
                    return true;
                case 9:
                    parcel.enforceInterface(str);
                    onInterfaceLinkStateChanged(parcel.readString(), parcel.readInt() != 0);
                    return true;
                case 10:
                    parcel.enforceInterface(str);
                    onRouteChanged(parcel.readInt() != 0, parcel.readString(), parcel.readString(), parcel.readString());
                    return true;
                case 11:
                    parcel.enforceInterface(str);
                    onStrictCleartextDetected(parcel.readInt(), parcel.readString());
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
