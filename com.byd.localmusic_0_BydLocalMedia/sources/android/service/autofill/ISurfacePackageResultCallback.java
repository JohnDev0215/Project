package android.service.autofill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;

/* JADX INFO: loaded from: classes3.dex */
public interface ISurfacePackageResultCallback extends IInterface {

    public static class Default implements ISurfacePackageResultCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.autofill.ISurfacePackageResultCallback
        public void onResult(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException {
        }
    }

    void onResult(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException;

    public static abstract class Stub extends Binder implements ISurfacePackageResultCallback {
        private static final String DESCRIPTOR = "android.service.autofill.ISurfacePackageResultCallback";
        static final int TRANSACTION_onResult = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onResult";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISurfacePackageResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISurfacePackageResultCallback)) {
                return (ISurfacePackageResultCallback) iInterfaceQueryLocalInterface;
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
                onResult(parcel.readInt() != 0 ? SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISurfacePackageResultCallback {
            public static ISurfacePackageResultCallback sDefaultImpl;
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

            @Override // android.service.autofill.ISurfacePackageResultCallback
            public void onResult(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surfacePackage != null) {
                        parcelObtain.writeInt(1);
                        surfacePackage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResult(surfacePackage);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISurfacePackageResultCallback iSurfacePackageResultCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSurfacePackageResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSurfacePackageResultCallback;
            return true;
        }

        public static ISurfacePackageResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
