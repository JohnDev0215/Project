package android.media;

import android.media.session.MediaSession;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IRemoteVolumeController extends IInterface {

    public static class Default implements IRemoteVolumeController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IRemoteVolumeController
        public void remoteVolumeChanged(MediaSession.Token token, int i) throws RemoteException {
        }

        @Override // android.media.IRemoteVolumeController
        public void updateRemoteController(MediaSession.Token token) throws RemoteException {
        }
    }

    void remoteVolumeChanged(MediaSession.Token token, int i) throws RemoteException;

    void updateRemoteController(MediaSession.Token token) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteVolumeController {
        private static final String DESCRIPTOR = "android.media.IRemoteVolumeController";
        static final int TRANSACTION_remoteVolumeChanged = 1;
        static final int TRANSACTION_updateRemoteController = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "remoteVolumeChanged";
            }
            if (i != 2) {
                return null;
            }
            return "updateRemoteController";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteVolumeController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRemoteVolumeController)) {
                return (IRemoteVolumeController) iInterfaceQueryLocalInterface;
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
                remoteVolumeChanged(parcel.readInt() != 0 ? MediaSession.Token.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                updateRemoteController(parcel.readInt() != 0 ? MediaSession.Token.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRemoteVolumeController {
            public static IRemoteVolumeController sDefaultImpl;
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

            @Override // android.media.IRemoteVolumeController
            public void remoteVolumeChanged(MediaSession.Token token, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (token != null) {
                        parcelObtain.writeInt(1);
                        token.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().remoteVolumeChanged(token, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IRemoteVolumeController
            public void updateRemoteController(MediaSession.Token token) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (token != null) {
                        parcelObtain.writeInt(1);
                        token.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateRemoteController(token);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteVolumeController iRemoteVolumeController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteVolumeController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteVolumeController;
            return true;
        }

        public static IRemoteVolumeController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
