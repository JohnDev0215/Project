package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaRouter2 extends IInterface {

    public static class Default implements IMediaRouter2 {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IMediaRouter2
        public void notifyRouterRegistered(List<MediaRoute2Info> list, RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionInfoChanged(RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void requestCreateSessionByManager(long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }
    }

    void notifyRouterRegistered(List<MediaRoute2Info> list, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException;

    void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionInfoChanged(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void requestCreateSessionByManager(long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaRouter2 {
        private static final String DESCRIPTOR = "android.media.IMediaRouter2";
        static final int TRANSACTION_notifyRouterRegistered = 1;
        static final int TRANSACTION_notifyRoutesAdded = 2;
        static final int TRANSACTION_notifyRoutesChanged = 4;
        static final int TRANSACTION_notifyRoutesRemoved = 3;
        static final int TRANSACTION_notifySessionCreated = 5;
        static final int TRANSACTION_notifySessionInfoChanged = 6;
        static final int TRANSACTION_notifySessionReleased = 7;
        static final int TRANSACTION_requestCreateSessionByManager = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "notifyRouterRegistered";
                case 2:
                    return "notifyRoutesAdded";
                case 3:
                    return "notifyRoutesRemoved";
                case 4:
                    return "notifyRoutesChanged";
                case 5:
                    return "notifySessionCreated";
                case 6:
                    return "notifySessionInfoChanged";
                case 7:
                    return "notifySessionReleased";
                case 8:
                    return "requestCreateSessionByManager";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaRouter2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaRouter2)) {
                return (IMediaRouter2) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRouterRegistered(parcel.createTypedArrayList(MediaRoute2Info.CREATOR), parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRoutesAdded(parcel.createTypedArrayList(MediaRoute2Info.CREATOR));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRoutesRemoved(parcel.createTypedArrayList(MediaRoute2Info.CREATOR));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRoutesChanged(parcel.createTypedArrayList(MediaRoute2Info.CREATOR));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySessionCreated(parcel.readInt(), parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySessionInfoChanged(parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySessionReleased(parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCreateSessionByManager(parcel.readLong(), parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaRouter2 {
            public static IMediaRouter2 sDefaultImpl;
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

            @Override // android.media.IMediaRouter2
            public void notifyRouterRegistered(List<MediaRoute2Info> list, RoutingSessionInfo routingSessionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRouterRegistered(list, routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRoutesAdded(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRoutesRemoved(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRoutesChanged(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionCreated(i, routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifySessionInfoChanged(RoutingSessionInfo routingSessionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionInfoChanged(routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
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
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionReleased(routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void requestCreateSessionByManager(long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException {
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
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestCreateSessionByManager(j, routingSessionInfo, mediaRoute2Info);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouter2 iMediaRouter2) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaRouter2 == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaRouter2;
            return true;
        }

        public static IMediaRouter2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
