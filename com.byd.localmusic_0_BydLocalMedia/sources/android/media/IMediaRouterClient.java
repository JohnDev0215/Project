package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaRouterClient extends IInterface {

    public static class Default implements IMediaRouterClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IMediaRouterClient
        public void onGlobalA2dpChanged(boolean z) throws RemoteException {
        }

        @Override // android.media.IMediaRouterClient
        public void onRestoreRoute() throws RemoteException {
        }

        @Override // android.media.IMediaRouterClient
        public void onSelectedRouteChanged(String str) throws RemoteException {
        }

        @Override // android.media.IMediaRouterClient
        public void onStateChanged() throws RemoteException {
        }
    }

    void onGlobalA2dpChanged(boolean z) throws RemoteException;

    void onRestoreRoute() throws RemoteException;

    void onSelectedRouteChanged(String str) throws RemoteException;

    void onStateChanged() throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaRouterClient {
        private static final String DESCRIPTOR = "android.media.IMediaRouterClient";
        static final int TRANSACTION_onGlobalA2dpChanged = 4;
        static final int TRANSACTION_onRestoreRoute = 2;
        static final int TRANSACTION_onSelectedRouteChanged = 3;
        static final int TRANSACTION_onStateChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onStateChanged";
            }
            if (i == 2) {
                return "onRestoreRoute";
            }
            if (i == 3) {
                return "onSelectedRouteChanged";
            }
            if (i != 4) {
                return null;
            }
            return "onGlobalA2dpChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaRouterClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaRouterClient)) {
                return (IMediaRouterClient) iInterfaceQueryLocalInterface;
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
                onStateChanged();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onRestoreRoute();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onSelectedRouteChanged(parcel.readString());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onGlobalA2dpChanged(parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMediaRouterClient {
            public static IMediaRouterClient sDefaultImpl;
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

            @Override // android.media.IMediaRouterClient
            public void onStateChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStateChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterClient
            public void onRestoreRoute() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRestoreRoute();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterClient
            public void onSelectedRouteChanged(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSelectedRouteChanged(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterClient
            public void onGlobalA2dpChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGlobalA2dpChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouterClient iMediaRouterClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaRouterClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaRouterClient;
            return true;
        }

        public static IMediaRouterClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
