package android.net.ipmemorystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IOnSameL3NetworkResponseListener extends IInterface {
    void onSameL3NetworkResponse(StatusParcelable statusParcelable, SameL3NetworkResponseParcelable sameL3NetworkResponseParcelable) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnSameL3NetworkResponseListener {
        private static final String DESCRIPTOR = "android$net$ipmemorystore$IOnSameL3NetworkResponseListener".replace('$', '.');

        public static IOnSameL3NetworkResponseListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOnSameL3NetworkResponseListener)) {
                return (IOnSameL3NetworkResponseListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IOnSameL3NetworkResponseListener {
            public static IOnSameL3NetworkResponseListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.ipmemorystore.IOnSameL3NetworkResponseListener
            public void onSameL3NetworkResponse(StatusParcelable statusParcelable, SameL3NetworkResponseParcelable sameL3NetworkResponseParcelable) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (statusParcelable != null) {
                        parcelObtain.writeInt(1);
                        statusParcelable.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (sameL3NetworkResponseParcelable != null) {
                        parcelObtain.writeInt(1);
                        sameL3NetworkResponseParcelable.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSameL3NetworkResponse(statusParcelable, sameL3NetworkResponseParcelable);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static IOnSameL3NetworkResponseListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
