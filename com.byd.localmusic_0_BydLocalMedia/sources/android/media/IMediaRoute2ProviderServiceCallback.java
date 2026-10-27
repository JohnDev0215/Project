package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaRoute2ProviderServiceCallback extends IInterface {

    public static class Default implements IMediaRoute2ProviderServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifyRequestFailed(long j, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifySessionCreated(long j, RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void updateState(MediaRoute2ProviderInfo mediaRoute2ProviderInfo) throws RemoteException {
        }
    }

    void notifyRequestFailed(long j, int i) throws RemoteException;

    void notifySessionCreated(long j, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void updateState(MediaRoute2ProviderInfo mediaRoute2ProviderInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaRoute2ProviderServiceCallback {
        private static final String DESCRIPTOR = "android.media.IMediaRoute2ProviderServiceCallback";
        static final int TRANSACTION_notifyRequestFailed = 5;
        static final int TRANSACTION_notifySessionCreated = 2;
        static final int TRANSACTION_notifySessionReleased = 4;
        static final int TRANSACTION_notifySessionUpdated = 3;
        static final int TRANSACTION_updateState = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "updateState";
            }
            if (i == 2) {
                return "notifySessionCreated";
            }
            if (i == 3) {
                return "notifySessionUpdated";
            }
            if (i == 4) {
                return "notifySessionReleased";
            }
            if (i != 5) {
                return null;
            }
            return "notifyRequestFailed";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaRoute2ProviderServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaRoute2ProviderServiceCallback)) {
                return (IMediaRoute2ProviderServiceCallback) iInterfaceQueryLocalInterface;
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
                updateState(parcel.readInt() != 0 ? MediaRoute2ProviderInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                notifySessionCreated(parcel.readLong(), parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                notifySessionUpdated(parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                notifySessionReleased(parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyRequestFailed(parcel.readLong(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMediaRoute2ProviderServiceCallback {
            public static IMediaRoute2ProviderServiceCallback sDefaultImpl;
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

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void updateState(MediaRoute2ProviderInfo mediaRoute2ProviderInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mediaRoute2ProviderInfo != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2ProviderInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateState(mediaRoute2ProviderInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifySessionCreated(long j, RoutingSessionInfo routingSessionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionCreated(j, routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionUpdated(routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionReleased(routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifyRequestFailed(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRequestFailed(j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaRoute2ProviderServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaRoute2ProviderServiceCallback;
            return true;
        }

        public static IMediaRoute2ProviderServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
