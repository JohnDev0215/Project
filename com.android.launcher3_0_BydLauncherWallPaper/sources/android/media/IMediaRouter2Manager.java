package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaRouter2Manager extends IInterface {

    public static class Default implements IMediaRouter2Manager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyPreferredFeaturesChanged(String str, List<String> list) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRequestFailed(int i, int i2) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) throws RemoteException {
        }
    }

    void notifyPreferredFeaturesChanged(String str, List<String> list) throws RemoteException;

    void notifyRequestFailed(int i, int i2) throws RemoteException;

    void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException;

    void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaRouter2Manager {
        private static final String DESCRIPTOR = "android.media.IMediaRouter2Manager";
        static final int TRANSACTION_notifyPreferredFeaturesChanged = 4;
        static final int TRANSACTION_notifyRequestFailed = 8;
        static final int TRANSACTION_notifyRoutesAdded = 5;
        static final int TRANSACTION_notifyRoutesChanged = 7;
        static final int TRANSACTION_notifyRoutesRemoved = 6;
        static final int TRANSACTION_notifySessionCreated = 1;
        static final int TRANSACTION_notifySessionReleased = 3;
        static final int TRANSACTION_notifySessionUpdated = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "notifySessionCreated";
                case 2:
                    return "notifySessionUpdated";
                case 3:
                    return "notifySessionReleased";
                case 4:
                    return "notifyPreferredFeaturesChanged";
                case 5:
                    return "notifyRoutesAdded";
                case 6:
                    return "notifyRoutesRemoved";
                case 7:
                    return "notifyRoutesChanged";
                case 8:
                    return "notifyRequestFailed";
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

        public static IMediaRouter2Manager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaRouter2Manager)) {
                return (IMediaRouter2Manager) iInterfaceQueryLocalInterface;
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
                    notifySessionCreated(parcel.readInt(), parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySessionUpdated(parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySessionReleased(parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPreferredFeaturesChanged(parcel.readString(), parcel.createStringArrayList());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRoutesAdded(parcel.createTypedArrayList(MediaRoute2Info.CREATOR));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRoutesRemoved(parcel.createTypedArrayList(MediaRoute2Info.CREATOR));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRoutesChanged(parcel.createTypedArrayList(MediaRoute2Info.CREATOR));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRequestFailed(parcel.readInt(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaRouter2Manager {
            public static IMediaRouter2Manager sDefaultImpl;
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

            @Override // android.media.IMediaRouter2Manager
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
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionCreated(i, routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
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
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionUpdated(routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
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
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySessionReleased(routingSessionInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyPreferredFeaturesChanged(String str, List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringList(list);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyPreferredFeaturesChanged(str, list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRoutesAdded(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRoutesRemoved(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRoutesChanged(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRequestFailed(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyRequestFailed(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouter2Manager iMediaRouter2Manager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaRouter2Manager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaRouter2Manager;
            return true;
        }

        public static IMediaRouter2Manager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
