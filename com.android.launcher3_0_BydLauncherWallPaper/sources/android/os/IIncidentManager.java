package android.os;

import android.os.IIncidentDumpCallback;
import android.os.IIncidentReportStatusListener;
import android.os.IncidentManager;
import java.io.FileDescriptor;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IIncidentManager extends IInterface {

    public static class Default implements IIncidentManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IIncidentManager
        public void deleteAllIncidentReports(String str) throws RemoteException {
        }

        @Override // android.os.IIncidentManager
        public void deleteIncidentReports(String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.os.IIncidentManager
        public IncidentManager.IncidentReport getIncidentReport(String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // android.os.IIncidentManager
        public List<String> getIncidentReportList(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.os.IIncidentManager
        public void registerSection(int i, String str, IIncidentDumpCallback iIncidentDumpCallback) throws RemoteException {
        }

        @Override // android.os.IIncidentManager
        public void reportIncident(IncidentReportArgs incidentReportArgs) throws RemoteException {
        }

        @Override // android.os.IIncidentManager
        public void reportIncidentToDumpstate(FileDescriptor fileDescriptor, IIncidentReportStatusListener iIncidentReportStatusListener) throws RemoteException {
        }

        @Override // android.os.IIncidentManager
        public void reportIncidentToStream(IncidentReportArgs incidentReportArgs, IIncidentReportStatusListener iIncidentReportStatusListener, FileDescriptor fileDescriptor) throws RemoteException {
        }

        @Override // android.os.IIncidentManager
        public void systemRunning() throws RemoteException {
        }

        @Override // android.os.IIncidentManager
        public void unregisterSection(int i) throws RemoteException {
        }
    }

    void deleteAllIncidentReports(String str) throws RemoteException;

    void deleteIncidentReports(String str, String str2, String str3) throws RemoteException;

    IncidentManager.IncidentReport getIncidentReport(String str, String str2, String str3) throws RemoteException;

    List<String> getIncidentReportList(String str, String str2) throws RemoteException;

    void registerSection(int i, String str, IIncidentDumpCallback iIncidentDumpCallback) throws RemoteException;

    void reportIncident(IncidentReportArgs incidentReportArgs) throws RemoteException;

    void reportIncidentToDumpstate(FileDescriptor fileDescriptor, IIncidentReportStatusListener iIncidentReportStatusListener) throws RemoteException;

    void reportIncidentToStream(IncidentReportArgs incidentReportArgs, IIncidentReportStatusListener iIncidentReportStatusListener, FileDescriptor fileDescriptor) throws RemoteException;

    void systemRunning() throws RemoteException;

    void unregisterSection(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IIncidentManager {
        private static final String DESCRIPTOR = "android.os.IIncidentManager";
        static final int TRANSACTION_deleteAllIncidentReports = 10;
        static final int TRANSACTION_deleteIncidentReports = 9;
        static final int TRANSACTION_getIncidentReport = 8;
        static final int TRANSACTION_getIncidentReportList = 7;
        static final int TRANSACTION_registerSection = 4;
        static final int TRANSACTION_reportIncident = 1;
        static final int TRANSACTION_reportIncidentToDumpstate = 3;
        static final int TRANSACTION_reportIncidentToStream = 2;
        static final int TRANSACTION_systemRunning = 6;
        static final int TRANSACTION_unregisterSection = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "reportIncident";
                case 2:
                    return "reportIncidentToStream";
                case 3:
                    return "reportIncidentToDumpstate";
                case 4:
                    return "registerSection";
                case 5:
                    return "unregisterSection";
                case 6:
                    return "systemRunning";
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

        public static IIncidentManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIncidentManager)) {
                return (IIncidentManager) iInterfaceQueryLocalInterface;
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
                    reportIncident(parcel.readInt() != 0 ? IncidentReportArgs.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportIncidentToStream(parcel.readInt() != 0 ? IncidentReportArgs.CREATOR.createFromParcel(parcel) : null, IIncidentReportStatusListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readRawFileDescriptor());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportIncidentToDumpstate(parcel.readRawFileDescriptor(), IIncidentReportStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerSection(parcel.readInt(), parcel.readString(), IIncidentDumpCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterSection(parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    systemRunning();
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

        private static class Proxy implements IIncidentManager {
            public static IIncidentManager sDefaultImpl;
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

            @Override // android.os.IIncidentManager
            public void reportIncident(IncidentReportArgs incidentReportArgs) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (incidentReportArgs != null) {
                        parcelObtain.writeInt(1);
                        incidentReportArgs.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().reportIncident(incidentReportArgs);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentManager
            public void reportIncidentToStream(IncidentReportArgs incidentReportArgs, IIncidentReportStatusListener iIncidentReportStatusListener, FileDescriptor fileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (incidentReportArgs != null) {
                        parcelObtain.writeInt(1);
                        incidentReportArgs.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iIncidentReportStatusListener != null ? iIncidentReportStatusListener.asBinder() : null);
                    parcelObtain.writeRawFileDescriptor(fileDescriptor);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().reportIncidentToStream(incidentReportArgs, iIncidentReportStatusListener, fileDescriptor);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentManager
            public void reportIncidentToDumpstate(FileDescriptor fileDescriptor, IIncidentReportStatusListener iIncidentReportStatusListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeRawFileDescriptor(fileDescriptor);
                    parcelObtain.writeStrongBinder(iIncidentReportStatusListener != null ? iIncidentReportStatusListener.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().reportIncidentToDumpstate(fileDescriptor, iIncidentReportStatusListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentManager
            public void registerSection(int i, String str, IIncidentDumpCallback iIncidentDumpCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iIncidentDumpCallback != null ? iIncidentDumpCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerSection(i, str, iIncidentDumpCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentManager
            public void unregisterSection(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterSection(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentManager
            public void systemRunning() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().systemRunning();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IIncidentManager
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

            @Override // android.os.IIncidentManager
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

            @Override // android.os.IIncidentManager
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

            @Override // android.os.IIncidentManager
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

        public static boolean setDefaultImpl(IIncidentManager iIncidentManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIncidentManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIncidentManager;
            return true;
        }

        public static IIncidentManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
