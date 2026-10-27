package android.media.projection;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaProjectionWatcherCallback extends IInterface {

    public static class Default implements IMediaProjectionWatcherCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.projection.IMediaProjectionWatcherCallback
        public void onStart(MediaProjectionInfo mediaProjectionInfo) throws RemoteException {
        }

        @Override // android.media.projection.IMediaProjectionWatcherCallback
        public void onStop(MediaProjectionInfo mediaProjectionInfo) throws RemoteException {
        }
    }

    void onStart(MediaProjectionInfo mediaProjectionInfo) throws RemoteException;

    void onStop(MediaProjectionInfo mediaProjectionInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaProjectionWatcherCallback {
        private static final String DESCRIPTOR = "android.media.projection.IMediaProjectionWatcherCallback";
        static final int TRANSACTION_onStart = 1;
        static final int TRANSACTION_onStop = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onStart";
            }
            if (i != 2) {
                return null;
            }
            return "onStop";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaProjectionWatcherCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaProjectionWatcherCallback)) {
                return (IMediaProjectionWatcherCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onStart(parcel.readInt() != 0 ? MediaProjectionInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onStop(parcel.readInt() != 0 ? MediaProjectionInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMediaProjectionWatcherCallback {
            public static IMediaProjectionWatcherCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.media.projection.IMediaProjectionWatcherCallback
            public void onStart(MediaProjectionInfo mediaProjectionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mediaProjectionInfo != null) {
                        parcelObtain.writeInt(1);
                        mediaProjectionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStart(mediaProjectionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjectionWatcherCallback
            public void onStop(MediaProjectionInfo mediaProjectionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mediaProjectionInfo != null) {
                        parcelObtain.writeInt(1);
                        mediaProjectionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStop(mediaProjectionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaProjectionWatcherCallback iMediaProjectionWatcherCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaProjectionWatcherCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaProjectionWatcherCallback;
            return true;
        }

        public static IMediaProjectionWatcherCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
