package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;

/* JADX INFO: loaded from: classes.dex */
public interface IKeyguardCallback extends IInterface {

    public static class Default implements IKeyguardCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.admin.IKeyguardCallback
        public void onDismiss() throws RemoteException {
        }

        @Override // android.app.admin.IKeyguardCallback
        public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException {
        }
    }

    void onDismiss() throws RemoteException;

    void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeyguardCallback {
        private static final String DESCRIPTOR = "android.app.admin.IKeyguardCallback";
        static final int TRANSACTION_onDismiss = 2;
        static final int TRANSACTION_onRemoteContentReady = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onRemoteContentReady";
            }
            if (i != 2) {
                return null;
            }
            return "onDismiss";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyguardCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeyguardCallback)) {
                return (IKeyguardCallback) iInterfaceQueryLocalInterface;
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
                onRemoteContentReady(parcel.readInt() != 0 ? SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onDismiss();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IKeyguardCallback {
            public static IKeyguardCallback sDefaultImpl;
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

            @Override // android.app.admin.IKeyguardCallback
            public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException {
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
                    Stub.getDefaultImpl().onRemoteContentReady(surfacePackage);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.admin.IKeyguardCallback
            public void onDismiss() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDismiss();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyguardCallback iKeyguardCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeyguardCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeyguardCallback;
            return true;
        }

        public static IKeyguardCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
