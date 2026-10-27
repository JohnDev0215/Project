package android.net.ipmemorystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IOnBlobRetrievedListener extends IInterface {
    void onBlobRetrieved(StatusParcelable statusParcelable, String str, String str2, Blob blob) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnBlobRetrievedListener {
        private static final String DESCRIPTOR = "android$net$ipmemorystore$IOnBlobRetrievedListener".replace('$', '.');

        public static IOnBlobRetrievedListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOnBlobRetrievedListener)) {
                return (IOnBlobRetrievedListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IOnBlobRetrievedListener {
            public static IOnBlobRetrievedListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.ipmemorystore.IOnBlobRetrievedListener
            public void onBlobRetrieved(StatusParcelable statusParcelable, String str, String str2, Blob blob) throws RemoteException {
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
                    parcelObtain.writeString(str2);
                    if (blob != null) {
                        parcelObtain.writeInt(1);
                        blob.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBlobRetrieved(statusParcelable, str, str2, blob);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static IOnBlobRetrievedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
