package android.net.ipmemorystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IOnStatusAndCountListener extends IInterface {
    void onComplete(StatusParcelable statusParcelable, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnStatusAndCountListener {
        private static final String DESCRIPTOR = "android$net$ipmemorystore$IOnStatusAndCountListener".replace('$', '.');

        public static IOnStatusAndCountListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOnStatusAndCountListener)) {
                return (IOnStatusAndCountListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IOnStatusAndCountListener {
            public static IOnStatusAndCountListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.ipmemorystore.IOnStatusAndCountListener
            public void onComplete(StatusParcelable statusParcelable, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (statusParcelable != null) {
                        parcelObtain.writeInt(1);
                        statusParcelable.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onComplete(statusParcelable, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static IOnStatusAndCountListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
