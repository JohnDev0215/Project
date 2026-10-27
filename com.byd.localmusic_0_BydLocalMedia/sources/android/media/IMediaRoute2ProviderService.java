package android.media;

import android.media.IMediaRoute2ProviderServiceCallback;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaRoute2ProviderService extends IInterface {

    public static class Default implements IMediaRoute2ProviderService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void deselectRoute(long j, String str, String str2) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void releaseSession(long j, String str) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void requestCreateSession(long j, String str, String str2, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void selectRoute(long j, String str, String str2) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setCallback(IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setRouteVolume(long j, String str, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setSessionVolume(long j, String str, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void transferToRoute(long j, String str, String str2) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void updateDiscoveryPreference(RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException {
        }
    }

    void deselectRoute(long j, String str, String str2) throws RemoteException;

    void releaseSession(long j, String str) throws RemoteException;

    void requestCreateSession(long j, String str, String str2, Bundle bundle) throws RemoteException;

    void selectRoute(long j, String str, String str2) throws RemoteException;

    void setCallback(IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback) throws RemoteException;

    void setRouteVolume(long j, String str, int i) throws RemoteException;

    void setSessionVolume(long j, String str, int i) throws RemoteException;

    void transferToRoute(long j, String str, String str2) throws RemoteException;

    void updateDiscoveryPreference(RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaRoute2ProviderService {
        private static final String DESCRIPTOR = "android.media.IMediaRoute2ProviderService";
        static final int TRANSACTION_deselectRoute = 6;
        static final int TRANSACTION_releaseSession = 9;
        static final int TRANSACTION_requestCreateSession = 4;
        static final int TRANSACTION_selectRoute = 5;
        static final int TRANSACTION_setCallback = 1;
        static final int TRANSACTION_setRouteVolume = 3;
        static final int TRANSACTION_setSessionVolume = 8;
        static final int TRANSACTION_transferToRoute = 7;
        static final int TRANSACTION_updateDiscoveryPreference = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setCallback";
                case 2:
                    return "updateDiscoveryPreference";
                case 3:
                    return "setRouteVolume";
                case 4:
                    return "requestCreateSession";
                case 5:
                    return "selectRoute";
                case 6:
                    return "deselectRoute";
                case 7:
                    return "transferToRoute";
                case 8:
                    return "setSessionVolume";
                case 9:
                    return "releaseSession";
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

        public static IMediaRoute2ProviderService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaRoute2ProviderService)) {
                return (IMediaRoute2ProviderService) iInterfaceQueryLocalInterface;
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
                    setCallback(IMediaRoute2ProviderServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateDiscoveryPreference(parcel.readInt() != 0 ? RouteDiscoveryPreference.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRouteVolume(parcel.readLong(), parcel.readString(), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCreateSession(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    selectRoute(parcel.readLong(), parcel.readString(), parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    deselectRoute(parcel.readLong(), parcel.readString(), parcel.readString());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    transferToRoute(parcel.readLong(), parcel.readString(), parcel.readString());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSessionVolume(parcel.readLong(), parcel.readString(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseSession(parcel.readLong(), parcel.readString());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaRoute2ProviderService {
            public static IMediaRoute2ProviderService sDefaultImpl;
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

            @Override // android.media.IMediaRoute2ProviderService
            public void setCallback(IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRoute2ProviderServiceCallback != null ? iMediaRoute2ProviderServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setCallback(iMediaRoute2ProviderServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void updateDiscoveryPreference(RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (routeDiscoveryPreference != null) {
                        parcelObtain.writeInt(1);
                        routeDiscoveryPreference.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateDiscoveryPreference(routeDiscoveryPreference);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void setRouteVolume(long j, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setRouteVolume(j, str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void requestCreateSession(long j, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestCreateSession(j, str, str2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void selectRoute(long j, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().selectRoute(j, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void deselectRoute(long j, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().deselectRoute(j, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void transferToRoute(long j, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().transferToRoute(j, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void setSessionVolume(long j, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setSessionVolume(j, str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void releaseSession(long j, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().releaseSession(j, str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRoute2ProviderService iMediaRoute2ProviderService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaRoute2ProviderService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaRoute2ProviderService;
            return true;
        }

        public static IMediaRoute2ProviderService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
