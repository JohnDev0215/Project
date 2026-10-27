package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface ILauncherManager extends IInterface {

    public static class Default implements ILauncherManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.ILauncherManager
        public boolean isFirstBoot() throws RemoteException {
            return false;
        }

        @Override // android.os.ILauncherManager
        public void setDefaultLauncher(String str) throws RemoteException {
        }
    }

    boolean isFirstBoot() throws RemoteException;

    void setDefaultLauncher(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ILauncherManager {
        private static final String DESCRIPTOR = "android.os.ILauncherManager";
        static final int TRANSACTION_isFirstBoot = 2;
        static final int TRANSACTION_setDefaultLauncher = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setDefaultLauncher";
            }
            if (i != 2) {
                return null;
            }
            return "isFirstBoot";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILauncherManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILauncherManager)) {
                return (ILauncherManager) iInterfaceQueryLocalInterface;
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
                setDefaultLauncher(parcel.readString());
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
            boolean zIsFirstBoot = isFirstBoot();
            parcel2.writeNoException();
            parcel2.writeInt(zIsFirstBoot ? 1 : 0);
            return true;
        }

        private static class Proxy implements ILauncherManager {
            public static ILauncherManager sDefaultImpl;
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

            @Override // android.os.ILauncherManager
            public void setDefaultLauncher(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setDefaultLauncher(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.ILauncherManager
            public boolean isFirstBoot() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFirstBoot();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILauncherManager iLauncherManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLauncherManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLauncherManager;
            return true;
        }

        public static ILauncherManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
