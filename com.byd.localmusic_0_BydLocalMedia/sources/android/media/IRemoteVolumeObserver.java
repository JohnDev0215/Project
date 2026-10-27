package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IRemoteVolumeObserver extends IInterface {

    public static class Default implements IRemoteVolumeObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IRemoteVolumeObserver
        public void dispatchRemoteVolumeUpdate(int i, int i2) throws RemoteException {
        }
    }

    void dispatchRemoteVolumeUpdate(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteVolumeObserver {
        private static final String DESCRIPTOR = "android.media.IRemoteVolumeObserver";
        static final int TRANSACTION_dispatchRemoteVolumeUpdate = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "dispatchRemoteVolumeUpdate";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteVolumeObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRemoteVolumeObserver)) {
                return (IRemoteVolumeObserver) iInterfaceQueryLocalInterface;
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
                dispatchRemoteVolumeUpdate(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRemoteVolumeObserver {
            public static IRemoteVolumeObserver sDefaultImpl;
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

            @Override // android.media.IRemoteVolumeObserver
            public void dispatchRemoteVolumeUpdate(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchRemoteVolumeUpdate(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteVolumeObserver iRemoteVolumeObserver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteVolumeObserver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteVolumeObserver;
            return true;
        }

        public static IRemoteVolumeObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
