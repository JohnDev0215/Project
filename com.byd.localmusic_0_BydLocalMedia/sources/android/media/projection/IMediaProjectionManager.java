package android.media.projection;

import android.media.projection.IMediaProjection;
import android.media.projection.IMediaProjectionWatcherCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaProjectionManager extends IInterface {

    public static class Default implements IMediaProjectionManager {
        @Override // android.media.projection.IMediaProjectionManager
        public void addCallback(IMediaProjectionWatcherCallback iMediaProjectionWatcherCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.projection.IMediaProjectionManager
        public IMediaProjection createProjection(int i, String str, int i2, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.media.projection.IMediaProjectionManager
        public MediaProjectionInfo getActiveProjectionInfo() throws RemoteException {
            return null;
        }

        @Override // android.media.projection.IMediaProjectionManager
        public boolean hasProjectionPermission(int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.media.projection.IMediaProjectionManager
        public boolean isValidMediaProjection(IMediaProjection iMediaProjection) throws RemoteException {
            return false;
        }

        @Override // android.media.projection.IMediaProjectionManager
        public void removeCallback(IMediaProjectionWatcherCallback iMediaProjectionWatcherCallback) throws RemoteException {
        }

        @Override // android.media.projection.IMediaProjectionManager
        public void stopActiveProjection() throws RemoteException {
        }
    }

    void addCallback(IMediaProjectionWatcherCallback iMediaProjectionWatcherCallback) throws RemoteException;

    IMediaProjection createProjection(int i, String str, int i2, boolean z) throws RemoteException;

    MediaProjectionInfo getActiveProjectionInfo() throws RemoteException;

    boolean hasProjectionPermission(int i, String str) throws RemoteException;

    boolean isValidMediaProjection(IMediaProjection iMediaProjection) throws RemoteException;

    void removeCallback(IMediaProjectionWatcherCallback iMediaProjectionWatcherCallback) throws RemoteException;

    void stopActiveProjection() throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaProjectionManager {
        private static final String DESCRIPTOR = "android.media.projection.IMediaProjectionManager";
        static final int TRANSACTION_addCallback = 6;
        static final int TRANSACTION_createProjection = 2;
        static final int TRANSACTION_getActiveProjectionInfo = 4;
        static final int TRANSACTION_hasProjectionPermission = 1;
        static final int TRANSACTION_isValidMediaProjection = 3;
        static final int TRANSACTION_removeCallback = 7;
        static final int TRANSACTION_stopActiveProjection = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "hasProjectionPermission";
                case 2:
                    return "createProjection";
                case 3:
                    return "isValidMediaProjection";
                case 4:
                    return "getActiveProjectionInfo";
                case 5:
                    return "stopActiveProjection";
                case 6:
                    return "addCallback";
                case 7:
                    return "removeCallback";
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

        public static IMediaProjectionManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaProjectionManager)) {
                return (IMediaProjectionManager) iInterfaceQueryLocalInterface;
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
                    boolean zHasProjectionPermission = hasProjectionPermission(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasProjectionPermission ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaProjection iMediaProjectionCreateProjection = createProjection(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaProjectionCreateProjection != null ? iMediaProjectionCreateProjection.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsValidMediaProjection = isValidMediaProjection(IMediaProjection.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsValidMediaProjection ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    MediaProjectionInfo activeProjectionInfo = getActiveProjectionInfo();
                    parcel2.writeNoException();
                    if (activeProjectionInfo != null) {
                        parcel2.writeInt(1);
                        activeProjectionInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopActiveProjection();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCallback(IMediaProjectionWatcherCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCallback(IMediaProjectionWatcherCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaProjectionManager {
            public static IMediaProjectionManager sDefaultImpl;
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

            @Override // android.media.projection.IMediaProjectionManager
            public boolean hasProjectionPermission(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasProjectionPermission(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjectionManager
            public IMediaProjection createProjection(int i, String str, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createProjection(i, str, i2, z);
                    }
                    parcelObtain2.readException();
                    return IMediaProjection.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjectionManager
            public boolean isValidMediaProjection(IMediaProjection iMediaProjection) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaProjection != null ? iMediaProjection.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isValidMediaProjection(iMediaProjection);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjectionManager
            public MediaProjectionInfo getActiveProjectionInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveProjectionInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MediaProjectionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjectionManager
            public void stopActiveProjection() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopActiveProjection();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjectionManager
            public void addCallback(IMediaProjectionWatcherCallback iMediaProjectionWatcherCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaProjectionWatcherCallback != null ? iMediaProjectionWatcherCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCallback(iMediaProjectionWatcherCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjectionManager
            public void removeCallback(IMediaProjectionWatcherCallback iMediaProjectionWatcherCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaProjectionWatcherCallback != null ? iMediaProjectionWatcherCallback.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCallback(iMediaProjectionWatcherCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaProjectionManager iMediaProjectionManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaProjectionManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaProjectionManager;
            return true;
        }

        public static IMediaProjectionManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
