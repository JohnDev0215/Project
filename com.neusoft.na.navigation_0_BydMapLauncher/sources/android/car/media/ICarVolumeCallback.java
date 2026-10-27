package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarVolumeCallback extends IInterface {

    public static class Default implements ICarVolumeCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.media.ICarVolumeCallback
        public void onGroupVolumeChanged(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.car.media.ICarVolumeCallback
        public void onMasterMuteChanged(int i, int i2) throws RemoteException {
        }
    }

    void onGroupVolumeChanged(int i, int i2, int i3) throws RemoteException;

    void onMasterMuteChanged(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarVolumeCallback {
        private static final String DESCRIPTOR = "android.car.media.ICarVolumeCallback";
        static final int TRANSACTION_onGroupVolumeChanged = 1;
        static final int TRANSACTION_onMasterMuteChanged = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarVolumeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarVolumeCallback)) {
                return (ICarVolumeCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onGroupVolumeChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onMasterMuteChanged(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICarVolumeCallback {
            public static ICarVolumeCallback sDefaultImpl;
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

            @Override // android.car.media.ICarVolumeCallback
            public void onGroupVolumeChanged(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGroupVolumeChanged(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarVolumeCallback
            public void onMasterMuteChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMasterMuteChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarVolumeCallback iCarVolumeCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarVolumeCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarVolumeCallback;
            return true;
        }

        public static ICarVolumeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
