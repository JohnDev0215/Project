package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IRecoverySystemProgressListener extends IInterface {

    public static class Default implements IRecoverySystemProgressListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IRecoverySystemProgressListener
        public void onProgress(int i) throws RemoteException {
        }
    }

    void onProgress(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecoverySystemProgressListener {
        private static final String DESCRIPTOR = "android.os.IRecoverySystemProgressListener";
        static final int TRANSACTION_onProgress = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onProgress";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecoverySystemProgressListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecoverySystemProgressListener)) {
                return (IRecoverySystemProgressListener) iInterfaceQueryLocalInterface;
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
                onProgress(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRecoverySystemProgressListener {
            public static IRecoverySystemProgressListener sDefaultImpl;
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

            @Override // android.os.IRecoverySystemProgressListener
            public void onProgress(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProgress(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecoverySystemProgressListener iRecoverySystemProgressListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRecoverySystemProgressListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecoverySystemProgressListener;
            return true;
        }

        public static IRecoverySystemProgressListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
