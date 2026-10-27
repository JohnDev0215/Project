package android.os;

import android.os.IIncidentAuthListener;
import android.os.IncidentManager;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IIncidentCompanion extends IInterface {

    public static class Default implements IIncidentCompanion {
        @Override // android.os.IIncidentCompanion
        public void approveReport(String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IIncidentCompanion
        public void authorizeReport(int i, String str, String str2, String str3, int i2, IIncidentAuthListener iIncidentAuthListener) throws RemoteException {
        }

        @Override // android.os.IIncidentCompanion
        public void cancelAuthorization(IIncidentAuthListener iIncidentAuthListener) throws RemoteException {
        }

        @Override // android.os.IIncidentCompanion
        public void deleteAllIncidentReports(String str) throws RemoteException {
        }

        @Override // android.os.IIncidentCompanion
        public void deleteIncidentReports(String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.os.IIncidentCompanion
        public void denyReport(String str) throws RemoteException {
        }

        @Override // android.os.IIncidentCompanion
        public IncidentManager.IncidentReport getIncidentReport(String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // android.os.IIncidentCompanion
        public List<String> getIncidentReportList(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.os.IIncidentCompanion
        public List<String> getPendingReports() throws RemoteException {
            return null;
        }

        @Override // android.os.IIncidentCompanion
        public void sendReportReadyBroadcast(String str, String str2) throws RemoteException {
        }
    }

    void approveReport(String str) throws RemoteException;

    void authorizeReport(int i, String str, String str2, String str3, int i2, IIncidentAuthListener iIncidentAuthListener) throws RemoteException;

    void cancelAuthorization(IIncidentAuthListener iIncidentAuthListener) throws RemoteException;

    void deleteAllIncidentReports(String str) throws RemoteException;

    void deleteIncidentReports(String str, String str2, String str3) throws RemoteException;

    void denyReport(String str) throws RemoteException;

    IncidentManager.IncidentReport getIncidentReport(String str, String str2, String str3) throws RemoteException;

    List<String> getIncidentReportList(String str, String str2) throws RemoteException;

    List<String> getPendingReports() throws RemoteException;

    void sendReportReadyBroadcast(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IIncidentCompanion {
        private static final String DESCRIPTOR = "android.os.IIncidentCompanion";
        static final int TRANSACTION_approveReport = 5;
        static final int TRANSACTION_authorizeReport = 1;
        static final int TRANSACTION_cancelAuthorization = 2;
        static final int TRANSACTION_deleteAllIncidentReports = 10;
        static final int TRANSACTION_deleteIncidentReports = 9;
        static final int TRANSACTION_denyReport = 6;
        static final int TRANSACTION_getIncidentReport = 8;
        static final int TRANSACTION_getIncidentReportList = 7;
        static final int TRANSACTION_getPendingReports = 4;
        static final int TRANSACTION_sendReportReadyBroadcast = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "authorizeReport";
                case 2:
                    return "cancelAuthorization";
                case 3:
                    return "sendReportReadyBroadcast";
                case 4:
                    return "getPendingReports";
                case 5:
                    return "approveReport";
                case 6:
                    return "denyReport";
                case 7:
                    return "getIncidentReportList";
                case 8:
                    return "getIncidentReport";
                case 9:
                    return "deleteIncidentReports";
                case 10:
                    return "deleteAllIncidentReports";
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

        public static IIncidentCompanion asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIncidentCompanion)) {
                return (IIncidentCompanion) iInterfaceQueryLocalInterface;
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
                    authorizeReport(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), IIncidentAuthListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelAuthorization(IIncidentAuthListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendReportReadyBroadcast(parcel.readString(), parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> pendingReports = getPendingReports();
                    parcel2.writeNoException();
                    parcel2.writeStringList(pendingReports);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    approveReport(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    denyReport(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> incidentReportList = getIncidentReportList(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringList(incidentReportList);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    IncidentManager.IncidentReport incidentReport = getIncidentReport(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (incidentReport != null) {
                        parcel2.writeInt(1);
                        incidentReport.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteIncidentReports(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteAllIncidentReports(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IIncidentCompanion {
            public static IIncidentCompanion sDefaultImpl;
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

            @Override // android.os.IIncidentCompanion
            public void authorizeReport(int i, String str, String str2, String str3, int i2, IIncidentAuthListener iIncidentAuthListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iIncidentAuthListener != null ? iIncidentAuthListener.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().authorizeReport(i, str, str2, str3, i2, iIncidentAuthListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public void cancelAuthorization(IIncidentAuthListener iIncidentAuthListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iIncidentAuthListener != null ? iIncidentAuthListener.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelAuthorization(iIncidentAuthListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public void sendReportReadyBroadcast(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendReportReadyBroadcast(str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public List<String> getPendingReports() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPendingReports();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public void approveReport(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().approveReport(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public void denyReport(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().denyReport(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public List<String> getIncidentReportList(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIncidentReportList(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public IncidentManager.IncidentReport getIncidentReport(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIncidentReport(str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IncidentManager.IncidentReport.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public void deleteIncidentReports(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteIncidentReports(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentCompanion
            public void deleteAllIncidentReports(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteAllIncidentReports(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIncidentCompanion iIncidentCompanion) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIncidentCompanion == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIncidentCompanion;
            return true;
        }

        public static IIncidentCompanion getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
