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
    void onLeasesChanged(List<DhcpLeaseParcelable> list) throws RemoteException;

    void onNewPrefixRequest(IpPrefix ipPrefix) throws RemoteException;

    public static abstract class Stub extends Binder implements IDhcpEventCallbacks {
        private static final String DESCRIPTOR = "android$net$dhcp$IDhcpEventCallbacks".replace('$', '.');

        public static IDhcpEventCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDhcpEventCallbacks)) {
                return (IDhcpEventCallbacks) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IDhcpEventCallbacks {
            public static IDhcpEventCallbacks sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.dhcp.IDhcpEventCallbacks
            public void onLeasesChanged(List<DhcpLeaseParcelable> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLeasesChanged(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.dhcp.IDhcpEventCallbacks
            public void onNewPrefixRequest(IpPrefix ipPrefix) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ipPrefix != null) {
                        parcelObtain.writeInt(1);
                        ipPrefix.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNewPrefixRequest(ipPrefix);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static IDhcpEventCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
