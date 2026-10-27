package android.net.dhcp;

import android.net.IpPrefix;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDhcpEventCallbacks extends IInterface {
    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void onLeasesChanged(List<DhcpLeaseParcelable> list) throws RemoteException;

    void onNewPrefixRequest(IpPrefix ipPrefix) throws RemoteException;

    public static abstract class Stub extends Binder implements IDhcpEventCallbacks {
        private static final String DESCRIPTOR = "android$net$dhcp$IDhcpEventCallbacks".replace('$', '.');

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
                onLeasesChanged(parcel.createTypedArrayList(DhcpLeaseParcelable.CREATOR));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(str);
                onNewPrefixRequest(parcel.readInt() != 0 ? (IpPrefix) IpPrefix.CREATOR.createFromParcel(parcel) : null);
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
