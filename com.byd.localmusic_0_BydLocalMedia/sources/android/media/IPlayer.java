package android.media;

import android.media.VolumeShaper;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Telephony;
import com.byd.music.utils.mediahelper.AudioPlayerService;

/* JADX INFO: loaded from: classes2.dex */
public interface IPlayer extends IInterface {

    public static class Default implements IPlayer {
        @Override // android.media.IPlayer
        public void applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IPlayer
        public void pause() throws RemoteException {
        }

        @Override // android.media.IPlayer
        public void setPan(float f) throws RemoteException {
        }

        @Override // android.media.IPlayer
        public void setStartDelayMs(int i) throws RemoteException {
        }

        @Override // android.media.IPlayer
        public void setVolume(float f) throws RemoteException {
        }

        @Override // android.media.IPlayer
        public void start() throws RemoteException {
        }

        @Override // android.media.IPlayer
        public void stop() throws RemoteException {
        }
    }

    void applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation) throws RemoteException;

    void pause() throws RemoteException;

    void setPan(float f) throws RemoteException;

    void setStartDelayMs(int i) throws RemoteException;

    void setVolume(float f) throws RemoteException;

    void start() throws RemoteException;

    void stop() throws RemoteException;

    public static abstract class Stub extends Binder implements IPlayer {
        private static final String DESCRIPTOR = "android.media.IPlayer";
        static final int TRANSACTION_applyVolumeShaper = 7;
        static final int TRANSACTION_pause = 2;
        static final int TRANSACTION_setPan = 5;
        static final int TRANSACTION_setStartDelayMs = 6;
        static final int TRANSACTION_setVolume = 4;
        static final int TRANSACTION_start = 1;
        static final int TRANSACTION_stop = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return Telephony.BaseMmsColumns.START;
                case 2:
                    return AudioPlayerService.ACTION_PAUSE;
                case 3:
                    return "stop";
                case 4:
                    return "setVolume";
                case 5:
                    return "setPan";
                case 6:
                    return "setStartDelayMs";
                case 7:
                    return "applyVolumeShaper";
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

        public static IPlayer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPlayer)) {
                return (IPlayer) iInterfaceQueryLocalInterface;
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
                    start();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    pause();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    stop();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVolume(parcel.readFloat());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPan(parcel.readFloat());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setStartDelayMs(parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyVolumeShaper(parcel.readInt() != 0 ? VolumeShaper.Configuration.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? VolumeShaper.Operation.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPlayer {
            public static IPlayer sDefaultImpl;
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

            @Override // android.media.IPlayer
            public void start() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().start();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IPlayer
            public void pause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().pause();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IPlayer
            public void stop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stop();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IPlayer
            public void setVolume(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setVolume(f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IPlayer
            public void setPan(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setPan(f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IPlayer
            public void setStartDelayMs(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setStartDelayMs(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IPlayer
            public void applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (configuration != null) {
                        parcelObtain.writeInt(1);
                        configuration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (operation != null) {
                        parcelObtain.writeInt(1);
                        operation.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().applyVolumeShaper(configuration, operation);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlayer iPlayer) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPlayer == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPlayer;
            return true;
        }

        public static IPlayer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
