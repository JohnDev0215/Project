package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface INetworkStackStatusCallback extends IInterface {
    void onStatusAvailable(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkStackStatusCallback {
        private static final String DESCRIPTOR = "android$net$INetworkStackStatusCallback".replace('$', '.');

        public static INetworkStackStatusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkStackStatusCallback)) {
                return (INetworkStackStatusCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements INetworkStackStatusCallback {
            public static INetworkStackStatusCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.INetworkStackStatusCallback
            public void onStatusAvailable(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStatusAvailable(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static INetworkStackStatusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
