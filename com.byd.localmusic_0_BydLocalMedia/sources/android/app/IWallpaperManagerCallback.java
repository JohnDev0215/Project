package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IWallpaperManagerCallback extends IInterface {

    public static class Default implements IWallpaperManagerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperChanged() throws RemoteException {
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperColorsChanged(WallpaperColors wallpaperColors, int i, int i2) throws RemoteException {
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperListChanged() throws RemoteException {
        }
    }

    void onWallpaperChanged() throws RemoteException;

    void onWallpaperColorsChanged(WallpaperColors wallpaperColors, int i, int i2) throws RemoteException;

    void onWallpaperListChanged() throws RemoteException;

    public static abstract class Stub extends Binder implements IWallpaperManagerCallback {
        private static final String DESCRIPTOR = "android.app.IWallpaperManagerCallback";
        static final int TRANSACTION_onWallpaperChanged = 1;
        static final int TRANSACTION_onWallpaperColorsChanged = 2;
        static final int TRANSACTION_onWallpaperListChanged = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onWallpaperChanged";
            }
            if (i == 2) {
                return "onWallpaperColorsChanged";
            }
            if (i != 3) {
                return null;
            }
            return "onWallpaperListChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWallpaperManagerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWallpaperManagerCallback)) {
                return (IWallpaperManagerCallback) iInterfaceQueryLocalInterface;
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
                onWallpaperChanged();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onWallpaperColorsChanged(parcel.readInt() != 0 ? WallpaperColors.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onWallpaperListChanged();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IWallpaperManagerCallback {
            public static IWallpaperManagerCallback sDefaultImpl;
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

            @Override // android.app.IWallpaperManagerCallback
            public void onWallpaperChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWallpaperChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManagerCallback
            public void onWallpaperColorsChanged(WallpaperColors wallpaperColors, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (wallpaperColors != null) {
                        parcelObtain.writeInt(1);
                        wallpaperColors.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWallpaperColorsChanged(wallpaperColors, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManagerCallback
            public void onWallpaperListChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWallpaperListChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWallpaperManagerCallback iWallpaperManagerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWallpaperManagerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWallpaperManagerCallback;
            return true;
        }

        public static IWallpaperManagerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
