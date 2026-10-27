package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IIncidentAuthListener extends IInterface {

    public static class Default implements IIncidentAuthListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IIncidentAuthListener
        public void onReportApproved() throws RemoteException {
        }

        @Override // android.os.IIncidentAuthListener
        public void onReportDenied() throws RemoteException {
        }
    }

    void onReportApproved() throws RemoteException;

    void onReportDenied() throws RemoteException;

    public static abstract class Stub extends Binder implements IIncidentAuthListener {
        private static final String DESCRIPTOR = "android.os.IIncidentAuthListener";
        static final int TRANSACTION_onReportApproved = 1;
        static final int TRANSACTION_onReportDenied = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onReportApproved";
            }
            if (i != 2) {
                return null;
            }
            return "onReportDenied";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIncidentAuthListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIncidentAuthListener)) {
                return (IIncidentAuthListener) iInterfaceQueryLocalInterface;
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
                onReportApproved();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onReportDenied();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IIncidentAuthListener {
            public static IIncidentAuthListener sDefaultImpl;
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

            @Override // android.os.IIncidentAuthListener
            public void onReportApproved() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReportApproved();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentAuthListener
            public void onReportDenied() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReportDenied();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIncidentAuthListener iIncidentAuthListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIncidentAuthListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIncidentAuthListener;
            return true;
        }

        public static IIncidentAuthListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
