package android.print;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.print.IPrintSpoolerCallbacks;
import android.print.IPrintSpoolerClient;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IPrintSpooler extends IInterface {

    public static class Default implements IPrintSpooler {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.print.IPrintSpooler
        public void clearCustomPrinterIconCache(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void createPrintJob(PrintJobInfo printJobInfo) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void getCustomPrinterIcon(PrinterId printerId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void getPrintJobInfo(PrintJobId printJobId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i, int i2) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void getPrintJobInfos(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, ComponentName componentName, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void onCustomPrinterIconLoaded(PrinterId printerId, Icon icon, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void pruneApprovedPrintServices(List<ComponentName> list) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void removeObsoletePrintJobs() throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setClient(IPrintSpoolerClient iPrintSpoolerClient) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setPrintJobCancelling(PrintJobId printJobId, boolean z) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setPrintJobState(PrintJobId printJobId, int i, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i2) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setPrintJobTag(PrintJobId printJobId, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setProgress(PrintJobId printJobId, float f) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setStatus(PrintJobId printJobId, CharSequence charSequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setStatusRes(PrintJobId printJobId, int i, CharSequence charSequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void writePrintJobData(ParcelFileDescriptor parcelFileDescriptor, PrintJobId printJobId) throws RemoteException {
        }
    }

    void clearCustomPrinterIconCache(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void createPrintJob(PrintJobInfo printJobInfo) throws RemoteException;

    void getCustomPrinterIcon(PrinterId printerId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void getPrintJobInfo(PrintJobId printJobId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i, int i2) throws RemoteException;

    void getPrintJobInfos(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, ComponentName componentName, int i, int i2, int i3) throws RemoteException;

    void onCustomPrinterIconLoaded(PrinterId printerId, Icon icon, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void pruneApprovedPrintServices(List<ComponentName> list) throws RemoteException;

    void removeObsoletePrintJobs() throws RemoteException;

    void setClient(IPrintSpoolerClient iPrintSpoolerClient) throws RemoteException;

    void setPrintJobCancelling(PrintJobId printJobId, boolean z) throws RemoteException;

    void setPrintJobState(PrintJobId printJobId, int i, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i2) throws RemoteException;

    void setPrintJobTag(PrintJobId printJobId, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void setProgress(PrintJobId printJobId, float f) throws RemoteException;

    void setStatus(PrintJobId printJobId, CharSequence charSequence) throws RemoteException;

    void setStatusRes(PrintJobId printJobId, int i, CharSequence charSequence) throws RemoteException;

    void writePrintJobData(ParcelFileDescriptor parcelFileDescriptor, PrintJobId printJobId) throws RemoteException;

    public static abstract class Stub extends Binder implements IPrintSpooler {
        private static final String DESCRIPTOR = "android.print.IPrintSpooler";
        static final int TRANSACTION_clearCustomPrinterIconCache = 11;
        static final int TRANSACTION_createPrintJob = 4;
        static final int TRANSACTION_getCustomPrinterIcon = 10;
        static final int TRANSACTION_getPrintJobInfo = 3;
        static final int TRANSACTION_getPrintJobInfos = 2;
        static final int TRANSACTION_onCustomPrinterIconLoaded = 9;
        static final int TRANSACTION_pruneApprovedPrintServices = 16;
        static final int TRANSACTION_removeObsoletePrintJobs = 1;
        static final int TRANSACTION_setClient = 14;
        static final int TRANSACTION_setPrintJobCancelling = 15;
        static final int TRANSACTION_setPrintJobState = 5;
        static final int TRANSACTION_setPrintJobTag = 12;
        static final int TRANSACTION_setProgress = 6;
        static final int TRANSACTION_setStatus = 7;
        static final int TRANSACTION_setStatusRes = 8;
        static final int TRANSACTION_writePrintJobData = 13;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "removeObsoletePrintJobs";
                case 2:
                    return "getPrintJobInfos";
                case 3:
                    return "getPrintJobInfo";
                case 4:
                    return "createPrintJob";
                case 5:
                    return "setPrintJobState";
                case 6:
                    return "setProgress";
                case 7:
                    return "setStatus";
                case 8:
                    return "setStatusRes";
                case 9:
                    return "onCustomPrinterIconLoaded";
                case 10:
                    return "getCustomPrinterIcon";
                case 11:
                    return "clearCustomPrinterIconCache";
                case 12:
                    return "setPrintJobTag";
                case 13:
                    return "writePrintJobData";
                case 14:
                    return "setClient";
                case 15:
                    return "setPrintJobCancelling";
                case 16:
                    return "pruneApprovedPrintServices";
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

        public static IPrintSpooler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPrintSpooler)) {
                return (IPrintSpooler) iInterfaceQueryLocalInterface;
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
                    removeObsoletePrintJobs();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPrintJobInfos(IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPrintJobInfo(parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null, IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    createPrintJob(parcel.readInt() != 0 ? PrintJobInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPrintJobState(parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString(), IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setProgress(parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null, parcel.readFloat());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setStatus(parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setStatusRes(parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCustomPrinterIconLoaded(parcel.readInt() != 0 ? PrinterId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Icon.CREATOR.createFromParcel(parcel) : null, IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    getCustomPrinterIcon(parcel.readInt() != 0 ? PrinterId.CREATOR.createFromParcel(parcel) : null, IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearCustomPrinterIconCache(IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPrintJobTag(parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null, parcel.readString(), IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    writePrintJobData(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setClient(IPrintSpoolerClient.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPrintJobCancelling(parcel.readInt() != 0 ? PrintJobId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    pruneApprovedPrintServices(parcel.createTypedArrayList(ComponentName.CREATOR));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPrintSpooler {
            public static IPrintSpooler sDefaultImpl;
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

            @Override // android.print.IPrintSpooler
            public void removeObsoletePrintJobs() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeObsoletePrintJobs();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void getPrintJobInfos(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, ComponentName componentName, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPrintSpoolerCallbacks != null ? iPrintSpoolerCallbacks.asBinder() : null);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getPrintJobInfos(iPrintSpoolerCallbacks, componentName, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void getPrintJobInfo(PrintJobId printJobId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iPrintSpoolerCallbacks != null ? iPrintSpoolerCallbacks.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getPrintJobInfo(printJobId, iPrintSpoolerCallbacks, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void createPrintJob(PrintJobInfo printJobInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobInfo != null) {
                        parcelObtain.writeInt(1);
                        printJobInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createPrintJob(printJobInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setPrintJobState(PrintJobId printJobId, int i, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iPrintSpoolerCallbacks != null ? iPrintSpoolerCallbacks.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setPrintJobState(printJobId, i, str, iPrintSpoolerCallbacks, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setProgress(PrintJobId printJobId, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setProgress(printJobId, f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setStatus(PrintJobId printJobId, CharSequence charSequence) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setStatus(printJobId, charSequence);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setStatusRes(PrintJobId printJobId, int i, CharSequence charSequence) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setStatusRes(printJobId, i, charSequence);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void onCustomPrinterIconLoaded(PrinterId printerId, Icon icon, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printerId != null) {
                        parcelObtain.writeInt(1);
                        printerId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (icon != null) {
                        parcelObtain.writeInt(1);
                        icon.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iPrintSpoolerCallbacks != null ? iPrintSpoolerCallbacks.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCustomPrinterIconLoaded(printerId, icon, iPrintSpoolerCallbacks, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void getCustomPrinterIcon(PrinterId printerId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printerId != null) {
                        parcelObtain.writeInt(1);
                        printerId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iPrintSpoolerCallbacks != null ? iPrintSpoolerCallbacks.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getCustomPrinterIcon(printerId, iPrintSpoolerCallbacks, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void clearCustomPrinterIconCache(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPrintSpoolerCallbacks != null ? iPrintSpoolerCallbacks.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().clearCustomPrinterIconCache(iPrintSpoolerCallbacks, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setPrintJobTag(PrintJobId printJobId, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iPrintSpoolerCallbacks != null ? iPrintSpoolerCallbacks.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setPrintJobTag(printJobId, str, iPrintSpoolerCallbacks, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void writePrintJobData(ParcelFileDescriptor parcelFileDescriptor, PrintJobId printJobId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().writePrintJobData(parcelFileDescriptor, printJobId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setClient(IPrintSpoolerClient iPrintSpoolerClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPrintSpoolerClient != null ? iPrintSpoolerClient.asBinder() : null);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setClient(iPrintSpoolerClient);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setPrintJobCancelling(PrintJobId printJobId, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        parcelObtain.writeInt(1);
                        printJobId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setPrintJobCancelling(printJobId, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void pruneApprovedPrintServices(List<ComponentName> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().pruneApprovedPrintServices(list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrintSpooler iPrintSpooler) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPrintSpooler == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPrintSpooler;
            return true;
        }

        public static IPrintSpooler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
