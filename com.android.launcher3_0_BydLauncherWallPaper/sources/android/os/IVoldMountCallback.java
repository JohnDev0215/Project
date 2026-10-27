package android.os;

import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public interface IVoldMountCallback extends IInterface {

    public static class Default implements IVoldMountCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IVoldMountCallback
        public boolean onVolumeChecking(FileDescriptor fileDescriptor, String str, String str2) throws RemoteException {
            return false;
        }
    }

    boolean onVolumeChecking(FileDescriptor fileDescriptor, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IVoldMountCallback {
        private static final String DESCRIPTOR = "android.os.IVoldMountCallback";
        static final int TRANSACTION_onVolumeChecking = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onVolumeChecking";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVoldMountCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVoldMountCallback)) {
                return (IVoldMountCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zOnVolumeChecking = onVolumeChecking(parcel.readRawFileDescriptor(), parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(zOnVolumeChecking ? 1 : 0);
            return true;
        }

        private static class Proxy implements IVoldMountCallback {
            public static IVoldMountCallback sDefaultImpl;
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

            @Override // android.os.IVoldMountCallback
            public boolean onVolumeChecking(FileDescriptor fileDescriptor, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeRawFileDescriptor(fileDescriptor);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onVolumeChecking(fileDescriptor, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoldMountCallback iVoldMountCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iVoldMountCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVoldMountCallback;
            return true;
        }

        public static IVoldMountCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
