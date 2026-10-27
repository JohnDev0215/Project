package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IVoldListener extends IInterface {

    public static class Default implements IVoldListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IVoldListener
        public void onDiskCreated(String str, int i) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onDiskDestroyed(String str) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onDiskMetadataChanged(String str, long j, String str2, String str3) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onDiskScanned(String str) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onVolumeCreated(String str, int i, String str2, String str3, int i2, String str4) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onVolumeDestroyed(String str) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onVolumeInternalPathChanged(String str, String str2) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onVolumeMetadataChanged(String str, String str2, String str3, String str4) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onVolumePathChanged(String str, String str2) throws RemoteException {
        }

        @Override // android.os.IVoldListener
        public void onVolumeStateChanged(String str, int i) throws RemoteException {
        }
    }

    void onDiskCreated(String str, int i) throws RemoteException;

    void onDiskDestroyed(String str) throws RemoteException;

    void onDiskMetadataChanged(String str, long j, String str2, String str3) throws RemoteException;

    void onDiskScanned(String str) throws RemoteException;

    void onVolumeCreated(String str, int i, String str2, String str3, int i2, String str4) throws RemoteException;

    void onVolumeDestroyed(String str) throws RemoteException;

    void onVolumeInternalPathChanged(String str, String str2) throws RemoteException;

    void onVolumeMetadataChanged(String str, String str2, String str3, String str4) throws RemoteException;

    void onVolumePathChanged(String str, String str2) throws RemoteException;

    void onVolumeStateChanged(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IVoldListener {
        private static final String DESCRIPTOR = "android.os.IVoldListener";
        static final int TRANSACTION_onDiskCreated = 1;
        static final int TRANSACTION_onDiskDestroyed = 4;
        static final int TRANSACTION_onDiskMetadataChanged = 3;
        static final int TRANSACTION_onDiskScanned = 2;
        static final int TRANSACTION_onVolumeCreated = 5;
        static final int TRANSACTION_onVolumeDestroyed = 10;
        static final int TRANSACTION_onVolumeInternalPathChanged = 9;
        static final int TRANSACTION_onVolumeMetadataChanged = 7;
        static final int TRANSACTION_onVolumePathChanged = 8;
        static final int TRANSACTION_onVolumeStateChanged = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onDiskCreated";
                case 2:
                    return "onDiskScanned";
                case 3:
                    return "onDiskMetadataChanged";
                case 4:
                    return "onDiskDestroyed";
                case 5:
                    return "onVolumeCreated";
                case 6:
                    return "onVolumeStateChanged";
                case 7:
                    return "onVolumeMetadataChanged";
                case 8:
                    return "onVolumePathChanged";
                case 9:
                    return "onVolumeInternalPathChanged";
                case 10:
                    return "onVolumeDestroyed";
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

        public static IVoldListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVoldListener)) {
                return (IVoldListener) iInterfaceQueryLocalInterface;
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
                    onDiskCreated(parcel.readString(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDiskScanned(parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDiskMetadataChanged(parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDiskDestroyed(parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeCreated(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeStateChanged(parcel.readString(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeMetadataChanged(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumePathChanged(parcel.readString(), parcel.readString());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeInternalPathChanged(parcel.readString(), parcel.readString());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeDestroyed(parcel.readString());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IVoldListener {
            public static IVoldListener sDefaultImpl;
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

            @Override // android.os.IVoldListener
            public void onDiskCreated(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDiskCreated(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onDiskScanned(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDiskScanned(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onDiskMetadataChanged(String str, long j, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDiskMetadataChanged(str, j, str2, str3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onDiskDestroyed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDiskDestroyed(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onVolumeCreated(String str, int i, String str2, String str3, int i2, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str4);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVolumeCreated(str, i, str2, str3, i2, str4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onVolumeStateChanged(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVolumeStateChanged(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onVolumeMetadataChanged(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVolumeMetadataChanged(str, str2, str3, str4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onVolumePathChanged(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVolumePathChanged(str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onVolumeInternalPathChanged(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVolumeInternalPathChanged(str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVoldListener
            public void onVolumeDestroyed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVolumeDestroyed(str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoldListener iVoldListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iVoldListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVoldListener;
            return true;
        }

        public static IVoldListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
