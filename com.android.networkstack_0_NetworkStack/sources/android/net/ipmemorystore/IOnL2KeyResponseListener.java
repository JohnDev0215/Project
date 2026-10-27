package android.net.ipmemorystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IOnL2KeyResponseListener extends IInterface {
    void onL2KeyResponse(StatusParcelable statusParcelable, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnL2KeyResponseListener {
        private static final String DESCRIPTOR = "android$net$ipmemorystore$IOnL2KeyResponseListener".replace('$', '.');

        public static IOnL2KeyResponseListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOnL2KeyResponseListener)) {
                return (IOnL2KeyResponseListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IOnL2KeyResponseListener {
            public static IOnL2KeyResponseListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.ipmemorystore.IOnL2KeyResponseListener
            public void onL2KeyResponse(StatusParcelable statusParcelable, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (statusParcelable != null) {
                        parcelObtain.writeInt(1);
                        statusParcelable.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onL2KeyResponse(statusParcelable, str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static IOnL2KeyResponseListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
