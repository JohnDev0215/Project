package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IUserRestrictionsListener extends IInterface {

    public static class Default implements IUserRestrictionsListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IUserRestrictionsListener
        public void onUserRestrictionsChanged(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }
    }

    void onUserRestrictionsChanged(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    public static abstract class Stub extends Binder implements IUserRestrictionsListener {
        private static final String DESCRIPTOR = "android.os.IUserRestrictionsListener";
        static final int TRANSACTION_onUserRestrictionsChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onUserRestrictionsChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserRestrictionsListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUserRestrictionsListener)) {
                return (IUserRestrictionsListener) iInterfaceQueryLocalInterface;
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
                onUserRestrictionsChanged(parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IUserRestrictionsListener {
            public static IUserRestrictionsListener sDefaultImpl;
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

            @Override // android.os.IUserRestrictionsListener
            public void onUserRestrictionsChanged(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        parcelObtain.writeInt(1);
                        bundle2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUserRestrictionsChanged(i, bundle, bundle2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserRestrictionsListener iUserRestrictionsListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUserRestrictionsListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUserRestrictionsListener;
            return true;
        }

        public static IUserRestrictionsListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
