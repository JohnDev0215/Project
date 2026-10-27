package android.printservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.print.PrintJobInfo;
import android.print.PrinterId;
import android.printservice.IPrintServiceClient;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IPrintService extends IInterface {

    public static class Default implements IPrintService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.printservice.IPrintService
        public void createPrinterDiscoverySession() throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void destroyPrinterDiscoverySession() throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void onPrintJobQueued(PrintJobInfo printJobInfo) throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void requestCancelPrintJob(PrintJobInfo printJobInfo) throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void requestCustomPrinterIcon(PrinterId printerId) throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void setClient(IPrintServiceClient iPrintServiceClient) throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void startPrinterDiscovery(List<PrinterId> list) throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void startPrinterStateTracking(PrinterId printerId) throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void stopPrinterDiscovery() throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void stopPrinterStateTracking(PrinterId printerId) throws RemoteException {
        }

        @Override // android.printservice.IPrintService
        public void validatePrinters(List<PrinterId> list) throws RemoteException {
        }
    }

    void createPrinterDiscoverySession() throws RemoteException;

    void destroyPrinterDiscoverySession() throws RemoteException;

    void onPrintJobQueued(PrintJobInfo printJobInfo) throws RemoteException;

    void requestCancelPrintJob(PrintJobInfo printJobInfo) throws RemoteException;

    void requestCustomPrinterIcon(PrinterId printerId) throws RemoteException;

    void setClient(IPrintServiceClient iPrintServiceClient) throws RemoteException;

    void startPrinterDiscovery(List<PrinterId> list) throws RemoteException;

    void startPrinterStateTracking(PrinterId printerId) throws RemoteException;

    void stopPrinterDiscovery() throws RemoteException;

    void stopPrinterStateTracking(PrinterId printerId) throws RemoteException;

    void validatePrinters(List<PrinterId> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IPrintService {
        private static final String DESCRIPTOR = "android.printservice.IPrintService";
        static final int TRANSACTION_createPrinterDiscoverySession = 4;
        static final int TRANSACTION_destroyPrinterDiscoverySession = 11;
        static final int TRANSACTION_onPrintJobQueued = 3;
        static final int TRANSACTION_requestCancelPrintJob = 2;
        static final int TRANSACTION_requestCustomPrinterIcon = 9;
        static final int TRANSACTION_setClient = 1;
        static final int TRANSACTION_startPrinterDiscovery = 5;
        static final int TRANSACTION_startPrinterStateTracking = 8;
        static final int TRANSACTION_stopPrinterDiscovery = 6;
        static final int TRANSACTION_stopPrinterStateTracking = 10;
        static final int TRANSACTION_validatePrinters = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setClient";
                case 2:
                    return "requestCancelPrintJob";
                case 3:
                    return "onPrintJobQueued";
                case 4:
                    return "createPrinterDiscoverySession";
                case 5:
                    return "startPrinterDiscovery";
                case 6:
                    return "stopPrinterDiscovery";
                case 7:
                    return "validatePrinters";
                case 8:
                    return "startPrinterStateTracking";
                case 9:
                    return "requestCustomPrinterIcon";
                case 10:
                    return "stopPrinterStateTracking";
                case 11:
                    return "destroyPrinterDiscoverySession";
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

        public static IPrintService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPrintService)) {
                return (IPrintService) iInterfaceQueryLocalInterface;
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
                    setClient(IPrintServiceClient.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCancelPrintJob(parcel.readInt() != 0 ? PrintJobInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrintJobQueued(parcel.readInt() != 0 ? PrintJobInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    createPrinterDiscoverySession();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    startPrinterDiscovery(parcel.createTypedArrayList(PrinterId.CREATOR));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopPrinterDiscovery();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    validatePrinters(parcel.createTypedArrayList(PrinterId.CREATOR));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    startPrinterStateTracking(parcel.readInt() != 0 ? PrinterId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCustomPrinterIcon(parcel.readInt() != 0 ? PrinterId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopPrinterStateTracking(parcel.readInt() != 0 ? PrinterId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyPrinterDiscoverySession();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPrintService {
            public static IPrintService sDefaultImpl;
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

            @Override // android.printservice.IPrintService
            public void setClient(IPrintServiceClient iPrintServiceClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPrintServiceClient != null ? iPrintServiceClient.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setClient(iPrintServiceClient);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void requestCancelPrintJob(PrintJobInfo printJobInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobInfo != null) {
                        parcelObtain.writeInt(1);
                        printJobInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestCancelPrintJob(printJobInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void onPrintJobQueued(PrintJobInfo printJobInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobInfo != null) {
                        parcelObtain.writeInt(1);
                        printJobInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrintJobQueued(printJobInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void createPrinterDiscoverySession() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createPrinterDiscoverySession();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void startPrinterDiscovery(List<PrinterId> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startPrinterDiscovery(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void stopPrinterDiscovery() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopPrinterDiscovery();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void validatePrinters(List<PrinterId> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().validatePrinters(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void startPrinterStateTracking(PrinterId printerId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printerId != null) {
                        parcelObtain.writeInt(1);
                        printerId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startPrinterStateTracking(printerId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void requestCustomPrinterIcon(PrinterId printerId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printerId != null) {
                        parcelObtain.writeInt(1);
                        printerId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestCustomPrinterIcon(printerId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void stopPrinterStateTracking(PrinterId printerId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printerId != null) {
                        parcelObtain.writeInt(1);
                        printerId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopPrinterStateTracking(printerId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.printservice.IPrintService
            public void destroyPrinterDiscoverySession() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().destroyPrinterDiscoverySession();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrintService iPrintService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPrintService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPrintService;
            return true;
        }

        public static IPrintService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
