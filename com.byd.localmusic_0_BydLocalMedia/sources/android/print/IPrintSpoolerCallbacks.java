package android.print;

import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IPrintSpoolerCallbacks extends IInterface {

    public static class Default implements IPrintSpoolerCallbacks {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void customPrinterIconCacheCleared(int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void onCancelPrintJobResult(boolean z, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void onCustomPrinterIconCached(int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void onGetCustomPrinterIconResult(Icon icon, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void onGetPrintJobInfoResult(PrintJobInfo printJobInfo, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void onGetPrintJobInfosResult(List<PrintJobInfo> list, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void onSetPrintJobStateResult(boolean z, int i) throws RemoteException {
        }

        @Override // android.print.IPrintSpoolerCallbacks
        public void onSetPrintJobTagResult(boolean z, int i) throws RemoteException {
        }
    }

    void customPrinterIconCacheCleared(int i) throws RemoteException;

    void onCancelPrintJobResult(boolean z, int i) throws RemoteException;

    void onCustomPrinterIconCached(int i) throws RemoteException;

    void onGetCustomPrinterIconResult(Icon icon, int i) throws RemoteException;

    void onGetPrintJobInfoResult(PrintJobInfo printJobInfo, int i) throws RemoteException;

    void onGetPrintJobInfosResult(List<PrintJobInfo> list, int i) throws RemoteException;

    void onSetPrintJobStateResult(boolean z, int i) throws RemoteException;

    void onSetPrintJobTagResult(boolean z, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IPrintSpoolerCallbacks {
        private static final String DESCRIPTOR = "android.print.IPrintSpoolerCallbacks";
        static final int TRANSACTION_customPrinterIconCacheCleared = 8;
        static final int TRANSACTION_onCancelPrintJobResult = 2;
        static final int TRANSACTION_onCustomPrinterIconCached = 7;
        static final int TRANSACTION_onGetCustomPrinterIconResult = 6;
        static final int TRANSACTION_onGetPrintJobInfoResult = 5;
        static final int TRANSACTION_onGetPrintJobInfosResult = 1;
        static final int TRANSACTION_onSetPrintJobStateResult = 3;
        static final int TRANSACTION_onSetPrintJobTagResult = 4;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onGetPrintJobInfosResult";
                case 2:
                    return "onCancelPrintJobResult";
                case 3:
                    return "onSetPrintJobStateResult";
                case 4:
                    return "onSetPrintJobTagResult";
                case 5:
                    return "onGetPrintJobInfoResult";
                case 6:
                    return "onGetCustomPrinterIconResult";
                case 7:
                    return "onCustomPrinterIconCached";
                case 8:
                    return "customPrinterIconCacheCleared";
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

        public static IPrintSpoolerCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPrintSpoolerCallbacks)) {
                return (IPrintSpoolerCallbacks) iInterfaceQueryLocalInterface;
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
                    onGetPrintJobInfosResult(parcel.createTypedArrayList(PrintJobInfo.CREATOR), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCancelPrintJobResult(parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSetPrintJobStateResult(parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSetPrintJobTagResult(parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onGetPrintJobInfoResult(parcel.readInt() != 0 ? PrintJobInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onGetCustomPrinterIconResult(parcel.readInt() != 0 ? Icon.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCustomPrinterIconCached(parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    customPrinterIconCacheCleared(parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPrintSpoolerCallbacks {
            public static IPrintSpoolerCallbacks sDefaultImpl;
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

            @Override // android.print.IPrintSpoolerCallbacks
            public void onGetPrintJobInfosResult(List<PrintJobInfo> list, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetPrintJobInfosResult(list, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpoolerCallbacks
            public void onCancelPrintJobResult(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCancelPrintJobResult(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpoolerCallbacks
            public void onSetPrintJobStateResult(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetPrintJobStateResult(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpoolerCallbacks
            public void onSetPrintJobTagResult(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetPrintJobTagResult(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpoolerCallbacks
            public void onGetPrintJobInfoResult(PrintJobInfo printJobInfo, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobInfo != null) {
                        parcelObtain.writeInt(1);
                        printJobInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetPrintJobInfoResult(printJobInfo, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpoolerCallbacks
            public void onGetCustomPrinterIconResult(Icon icon, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (icon != null) {
                        parcelObtain.writeInt(1);
                        icon.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetCustomPrinterIconResult(icon, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpoolerCallbacks
            public void onCustomPrinterIconCached(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCustomPrinterIconCached(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IPrintSpoolerCallbacks
            public void customPrinterIconCacheCleared(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().customPrinterIconCacheCleared(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrintSpoolerCallbacks iPrintSpoolerCallbacks) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPrintSpoolerCallbacks == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPrintSpoolerCallbacks;
            return true;
        }

        public static IPrintSpoolerCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
