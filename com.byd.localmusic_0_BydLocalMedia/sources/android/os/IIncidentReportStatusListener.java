package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IIncidentReportStatusListener extends IInterface {
    public static final int STATUS_FINISHED = 2;
    public static final int STATUS_STARTING = 1;

    public static class Default implements IIncidentReportStatusListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IIncidentReportStatusListener
        public void onReportFailed() throws RemoteException {
        }

        @Override // android.os.IIncidentReportStatusListener
        public void onReportFinished() throws RemoteException {
        }

        @Override // android.os.IIncidentReportStatusListener
        public void onReportSectionStatus(int i, int i2) throws RemoteException {
        }

        @Override // android.os.IIncidentReportStatusListener
        public void onReportStarted() throws RemoteException {
        }
    }

    void onReportFailed() throws RemoteException;

    void onReportFinished() throws RemoteException;

    void onReportSectionStatus(int i, int i2) throws RemoteException;

    void onReportStarted() throws RemoteException;

    public static abstract class Stub extends Binder implements IIncidentReportStatusListener {
        private static final String DESCRIPTOR = "android.os.IIncidentReportStatusListener";
        static final int TRANSACTION_onReportFailed = 4;
        static final int TRANSACTION_onReportFinished = 3;
        static final int TRANSACTION_onReportSectionStatus = 2;
        static final int TRANSACTION_onReportStarted = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onReportStarted";
            }
            if (i == 2) {
                return "onReportSectionStatus";
            }
            if (i == 3) {
                return "onReportFinished";
            }
            if (i != 4) {
                return null;
            }
            return "onReportFailed";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIncidentReportStatusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIncidentReportStatusListener)) {
                return (IIncidentReportStatusListener) iInterfaceQueryLocalInterface;
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
                onReportStarted();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onReportSectionStatus(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onReportFinished();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onReportFailed();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IIncidentReportStatusListener {
            public static IIncidentReportStatusListener sDefaultImpl;
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

            @Override // android.os.IIncidentReportStatusListener
            public void onReportStarted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReportStarted();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentReportStatusListener
            public void onReportSectionStatus(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReportSectionStatus(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentReportStatusListener
            public void onReportFinished() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReportFinished();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentReportStatusListener
            public void onReportFailed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReportFailed();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIncidentReportStatusListener iIncidentReportStatusListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIncidentReportStatusListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIncidentReportStatusListener;
            return true;
        }

        public static IIncidentReportStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
