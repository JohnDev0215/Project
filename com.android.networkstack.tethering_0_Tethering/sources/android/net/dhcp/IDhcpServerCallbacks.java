package android.net.dhcp;

import android.net.dhcp.IDhcpServer;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IDhcpServerCallbacks extends IInterface {
    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void onDhcpServerCreated(int i, IDhcpServer iDhcpServer) throws RemoteException;

    public static abstract class Stub extends Binder implements IDhcpServerCallbacks {
        private static final String DESCRIPTOR = "android$net$dhcp$IDhcpServerCallbacks".replace('$', '.');

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
                onDhcpServerCreated(parcel.readInt(), IDhcpServer.Stub.asInterface(parcel.readStrongBinder()));
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
