package android.media.projection;

import android.media.projection.IMediaProjectionCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Telephony;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaProjection extends IInterface {

    public static class Default implements IMediaProjection {
        @Override // android.media.projection.IMediaProjection
        public int applyVirtualDisplayFlags(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.projection.IMediaProjection
        public boolean canProjectAudio() throws RemoteException {
            return false;
        }

        @Override // android.media.projection.IMediaProjection
        public boolean canProjectSecureVideo() throws RemoteException {
            return false;
        }

        @Override // android.media.projection.IMediaProjection
        public boolean canProjectVideo() throws RemoteException {
            return false;
        }

        @Override // android.media.projection.IMediaProjection
        public void registerCallback(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException {
        }

        @Override // android.media.projection.IMediaProjection
        public void start(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException {
        }

        @Override // android.media.projection.IMediaProjection
        public void stop() throws RemoteException {
        }

        @Override // android.media.projection.IMediaProjection
        public void unregisterCallback(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException {
        }
    }

    int applyVirtualDisplayFlags(int i) throws RemoteException;

    boolean canProjectAudio() throws RemoteException;

    boolean canProjectSecureVideo() throws RemoteException;

    boolean canProjectVideo() throws RemoteException;

    void registerCallback(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException;

    void start(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallback(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaProjection {
        private static final String DESCRIPTOR = "android.media.projection.IMediaProjection";
        static final int TRANSACTION_applyVirtualDisplayFlags = 6;
        static final int TRANSACTION_canProjectAudio = 3;
        static final int TRANSACTION_canProjectSecureVideo = 5;
        static final int TRANSACTION_canProjectVideo = 4;
        static final int TRANSACTION_registerCallback = 7;
        static final int TRANSACTION_start = 1;
        static final int TRANSACTION_stop = 2;
        static final int TRANSACTION_unregisterCallback = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return Telephony.BaseMmsColumns.START;
                case 2:
                    return "stop";
                case 3:
                    return "canProjectAudio";
                case 4:
                    return "canProjectVideo";
                case 5:
                    return "canProjectSecureVideo";
                case 6:
                    return "applyVirtualDisplayFlags";
                case 7:
                    return "registerCallback";
                case 8:
                    return "unregisterCallback";
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

        public static IMediaProjection asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaProjection)) {
                return (IMediaProjection) iInterfaceQueryLocalInterface;
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
                    start(IMediaProjectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    stop();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanProjectAudio = canProjectAudio();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanProjectAudio ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanProjectVideo = canProjectVideo();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanProjectVideo ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanProjectSecureVideo = canProjectSecureVideo();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanProjectSecureVideo ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iApplyVirtualDisplayFlags = applyVirtualDisplayFlags(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iApplyVirtualDisplayFlags);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(IMediaProjectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(IMediaProjectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaProjection {
            public static IMediaProjection sDefaultImpl;
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

            @Override // android.media.projection.IMediaProjection
            public void start(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaProjectionCallback != null ? iMediaProjectionCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().start(iMediaProjectionCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjection
            public void stop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stop();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjection
            public boolean canProjectAudio() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canProjectAudio();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjection
            public boolean canProjectVideo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canProjectVideo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjection
            public boolean canProjectSecureVideo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canProjectSecureVideo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjection
            public int applyVirtualDisplayFlags(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().applyVirtualDisplayFlags(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjection
            public void registerCallback(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaProjectionCallback != null ? iMediaProjectionCallback.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iMediaProjectionCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.projection.IMediaProjection
            public void unregisterCallback(IMediaProjectionCallback iMediaProjectionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaProjectionCallback != null ? iMediaProjectionCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCallback(iMediaProjectionCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaProjection iMediaProjection) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaProjection == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaProjection;
            return true;
        }

        public static IMediaProjection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
