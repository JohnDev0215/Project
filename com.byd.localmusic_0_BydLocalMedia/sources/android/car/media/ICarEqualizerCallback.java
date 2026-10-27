package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarEqualizerCallback extends IInterface {

    public static class Default implements ICarEqualizerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.media.ICarEqualizerCallback
        public void onBandLevelChanged(int i) throws RemoteException {
        }

        @Override // android.car.media.ICarEqualizerCallback
        public void onPresetChanged() throws RemoteException {
        }
    }

    void onBandLevelChanged(int i) throws RemoteException;

    void onPresetChanged() throws RemoteException;

    public static abstract class Stub extends Binder implements ICarEqualizerCallback {
        private static final String DESCRIPTOR = "android.car.media.ICarEqualizerCallback";
        static final int TRANSACTION_onBandLevelChanged = 2;
        static final int TRANSACTION_onPresetChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarEqualizerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarEqualizerCallback)) {
                return (ICarEqualizerCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onPresetChanged();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onBandLevelChanged(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICarEqualizerCallback {
            public static ICarEqualizerCallback sDefaultImpl;
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

            @Override // android.car.media.ICarEqualizerCallback
            public void onPresetChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPresetChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizerCallback
            public void onBandLevelChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBandLevelChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarEqualizerCallback iCarEqualizerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarEqualizerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarEqualizerCallback;
            return true;
        }

        public static ICarEqualizerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
