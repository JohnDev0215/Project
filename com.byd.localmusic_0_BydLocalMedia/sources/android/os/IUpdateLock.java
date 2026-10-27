package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IUpdateLock extends IInterface {

    public static class Default implements IUpdateLock {
        @Override // android.os.IUpdateLock
        public void acquireUpdateLock(IBinder iBinder, String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IUpdateLock
        public void releaseUpdateLock(IBinder iBinder) throws RemoteException {
        }
    }

    void acquireUpdateLock(IBinder iBinder, String str) throws RemoteException;

    void releaseUpdateLock(IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements IUpdateLock {
        private static final String DESCRIPTOR = "android.os.IUpdateLock";
        static final int TRANSACTION_acquireUpdateLock = 1;
        static final int TRANSACTION_releaseUpdateLock = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "acquireUpdateLock";
            }
            if (i != 2) {
                return null;
            }
            return "releaseUpdateLock";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUpdateLock asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUpdateLock)) {
                return (IUpdateLock) iInterfaceQueryLocalInterface;
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
                acquireUpdateLock(parcel.readStrongBinder(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            releaseUpdateLock(parcel.readStrongBinder());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IUpdateLock {
            public static IUpdateLock sDefaultImpl;
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

            @Override // android.os.IUpdateLock
            public void acquireUpdateLock(IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acquireUpdateLock(iBinder, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUpdateLock
            public void releaseUpdateLock(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseUpdateLock(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUpdateLock iUpdateLock) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUpdateLock == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUpdateLock;
            return true;
        }

        public static IUpdateLock getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
