package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IDumpstateListener extends IInterface {
    public static final int BUGREPORT_ERROR_ANOTHER_REPORT_IN_PROGRESS = 5;
    public static final int BUGREPORT_ERROR_INVALID_INPUT = 1;
    public static final int BUGREPORT_ERROR_RUNTIME_ERROR = 2;
    public static final int BUGREPORT_ERROR_USER_CONSENT_TIMED_OUT = 4;
    public static final int BUGREPORT_ERROR_USER_DENIED_CONSENT = 3;

    public static class Default implements IDumpstateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IDumpstateListener
        public void onError(int i) throws RemoteException {
        }

        @Override // android.os.IDumpstateListener
        public void onFinished() throws RemoteException {
        }

        @Override // android.os.IDumpstateListener
        public void onProgress(int i) throws RemoteException {
        }

        @Override // android.os.IDumpstateListener
        public void onScreenshotTaken(boolean z) throws RemoteException {
        }

        @Override // android.os.IDumpstateListener
        public void onUiIntensiveBugreportDumpsFinished(String str) throws RemoteException {
        }
    }

    void onError(int i) throws RemoteException;

    void onFinished() throws RemoteException;

    void onProgress(int i) throws RemoteException;

    void onScreenshotTaken(boolean z) throws RemoteException;

    void onUiIntensiveBugreportDumpsFinished(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IDumpstateListener {
        private static final String DESCRIPTOR = "android.os.IDumpstateListener";
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onFinished = 3;
        static final int TRANSACTION_onProgress = 1;
        static final int TRANSACTION_onScreenshotTaken = 4;
        static final int TRANSACTION_onUiIntensiveBugreportDumpsFinished = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onProgress";
            }
            if (i == 2) {
                return "onError";
            }
            if (i == 3) {
                return "onFinished";
            }
            if (i == 4) {
                return "onScreenshotTaken";
            }
            if (i != 5) {
                return null;
            }
            return "onUiIntensiveBugreportDumpsFinished";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDumpstateListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDumpstateListener)) {
                return (IDumpstateListener) iInterfaceQueryLocalInterface;
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
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onFinished();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onScreenshotTaken(parcel.readInt() != 0);
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onUiIntensiveBugreportDumpsFinished(parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDumpstateListener {
            public static IDumpstateListener sDefaultImpl;
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

            @Override // android.os.IDumpstateListener
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

            @Override // android.os.IDumpstateListener
            public void onError(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDumpstateListener
            public void onFinished() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFinished();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDumpstateListener
            public void onScreenshotTaken(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onScreenshotTaken(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDumpstateListener
            public void onUiIntensiveBugreportDumpsFinished(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUiIntensiveBugreportDumpsFinished(str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDumpstateListener iDumpstateListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDumpstateListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDumpstateListener;
            return true;
        }

        public static IDumpstateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
