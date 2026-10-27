package android.media;

import android.media.IRemoteDisplayCallback;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IRemoteDisplayProvider extends IInterface {

    public static class Default implements IRemoteDisplayProvider {
        @Override // android.media.IRemoteDisplayProvider
        public void adjustVolume(String str, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IRemoteDisplayProvider
        public void connect(String str) throws RemoteException {
        }

        @Override // android.media.IRemoteDisplayProvider
        public void disconnect(String str) throws RemoteException {
        }

        @Override // android.media.IRemoteDisplayProvider
        public void setCallback(IRemoteDisplayCallback iRemoteDisplayCallback) throws RemoteException {
        }

        @Override // android.media.IRemoteDisplayProvider
        public void setDiscoveryMode(int i) throws RemoteException {
        }

        @Override // android.media.IRemoteDisplayProvider
        public void setVolume(String str, int i) throws RemoteException {
        }
    }

    void adjustVolume(String str, int i) throws RemoteException;

    void connect(String str) throws RemoteException;

    void disconnect(String str) throws RemoteException;

    void setCallback(IRemoteDisplayCallback iRemoteDisplayCallback) throws RemoteException;

    void setDiscoveryMode(int i) throws RemoteException;

    void setVolume(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteDisplayProvider {
        private static final String DESCRIPTOR = "android.media.IRemoteDisplayProvider";
        static final int TRANSACTION_adjustVolume = 6;
        static final int TRANSACTION_connect = 3;
        static final int TRANSACTION_disconnect = 4;
        static final int TRANSACTION_setCallback = 1;
        static final int TRANSACTION_setDiscoveryMode = 2;
        static final int TRANSACTION_setVolume = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setCallback";
                case 2:
                    return "setDiscoveryMode";
                case 3:
                    return MediaMetrics.Value.CONNECT;
                case 4:
                    return MediaMetrics.Value.DISCONNECT;
                case 5:
                    return "setVolume";
                case 6:
                    return "adjustVolume";
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

        public static IRemoteDisplayProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRemoteDisplayProvider)) {
                return (IRemoteDisplayProvider) iInterfaceQueryLocalInterface;
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
                    setCallback(IRemoteDisplayCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDiscoveryMode(parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    connect(parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnect(parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVolume(parcel.readString(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    adjustVolume(parcel.readString(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRemoteDisplayProvider {
            public static IRemoteDisplayProvider sDefaultImpl;
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

            @Override // android.media.IRemoteDisplayProvider
            public void setCallback(IRemoteDisplayCallback iRemoteDisplayCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRemoteDisplayCallback != null ? iRemoteDisplayCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setCallback(iRemoteDisplayCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IRemoteDisplayProvider
            public void setDiscoveryMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setDiscoveryMode(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IRemoteDisplayProvider
            public void connect(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().connect(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IRemoteDisplayProvider
            public void disconnect(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disconnect(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IRemoteDisplayProvider
            public void setVolume(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setVolume(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IRemoteDisplayProvider
            public void adjustVolume(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().adjustVolume(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteDisplayProvider iRemoteDisplayProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteDisplayProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteDisplayProvider;
            return true;
        }

        public static IRemoteDisplayProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
