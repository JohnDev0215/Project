package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;
import android.window.IDisplayAreaOrganizerController;
import android.window.ITaskOrganizerController;
import android.window.IWindowContainerTransactionCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IWindowOrganizerController extends IInterface {

    public static class Default implements IWindowOrganizerController {
        @Override // android.window.IWindowOrganizerController
        public int applySyncTransaction(WindowContainerTransaction windowContainerTransaction, IWindowContainerTransactionCallback iWindowContainerTransactionCallback) throws RemoteException {
            return 0;
        }

        @Override // android.window.IWindowOrganizerController
        public void applyTransaction(WindowContainerTransaction windowContainerTransaction) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.window.IWindowOrganizerController
        public IDisplayAreaOrganizerController getDisplayAreaOrganizerController() throws RemoteException {
            return null;
        }

        @Override // android.window.IWindowOrganizerController
        public ITaskOrganizerController getTaskOrganizerController() throws RemoteException {
            return null;
        }

        @Override // android.window.IWindowOrganizerController
        public boolean takeScreenshot(WindowContainerToken windowContainerToken, SurfaceControl surfaceControl) throws RemoteException {
            return false;
        }
    }

    int applySyncTransaction(WindowContainerTransaction windowContainerTransaction, IWindowContainerTransactionCallback iWindowContainerTransactionCallback) throws RemoteException;

    void applyTransaction(WindowContainerTransaction windowContainerTransaction) throws RemoteException;

    IDisplayAreaOrganizerController getDisplayAreaOrganizerController() throws RemoteException;

    ITaskOrganizerController getTaskOrganizerController() throws RemoteException;

    boolean takeScreenshot(WindowContainerToken windowContainerToken, SurfaceControl surfaceControl) throws RemoteException;

    public static abstract class Stub extends Binder implements IWindowOrganizerController {
        private static final String DESCRIPTOR = "android.window.IWindowOrganizerController";
        static final int TRANSACTION_applySyncTransaction = 2;
        static final int TRANSACTION_applyTransaction = 1;
        static final int TRANSACTION_getDisplayAreaOrganizerController = 4;
        static final int TRANSACTION_getTaskOrganizerController = 3;
        static final int TRANSACTION_takeScreenshot = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "applyTransaction";
            }
            if (i == 2) {
                return "applySyncTransaction";
            }
            if (i == 3) {
                return "getTaskOrganizerController";
            }
            if (i == 4) {
                return "getDisplayAreaOrganizerController";
            }
            if (i != 5) {
                return null;
            }
            return "takeScreenshot";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowOrganizerController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindowOrganizerController)) {
                return (IWindowOrganizerController) iInterfaceQueryLocalInterface;
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
                applyTransaction(parcel.readInt() != 0 ? WindowContainerTransaction.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int iApplySyncTransaction = applySyncTransaction(parcel.readInt() != 0 ? WindowContainerTransaction.CREATOR.createFromParcel(parcel) : null, IWindowContainerTransactionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(iApplySyncTransaction);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                ITaskOrganizerController taskOrganizerController = getTaskOrganizerController();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(taskOrganizerController != null ? taskOrganizerController.asBinder() : null);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                IDisplayAreaOrganizerController displayAreaOrganizerController = getDisplayAreaOrganizerController();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(displayAreaOrganizerController != null ? displayAreaOrganizerController.asBinder() : null);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            WindowContainerToken windowContainerTokenCreateFromParcel = parcel.readInt() != 0 ? WindowContainerToken.CREATOR.createFromParcel(parcel) : null;
            SurfaceControl surfaceControl = new SurfaceControl();
            boolean zTakeScreenshot = takeScreenshot(windowContainerTokenCreateFromParcel, surfaceControl);
            parcel2.writeNoException();
            parcel2.writeInt(zTakeScreenshot ? 1 : 0);
            parcel2.writeInt(1);
            surfaceControl.writeToParcel(parcel2, 1);
            return true;
        }

        private static class Proxy implements IWindowOrganizerController {
            public static IWindowOrganizerController sDefaultImpl;
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

            @Override // android.window.IWindowOrganizerController
            public void applyTransaction(WindowContainerTransaction windowContainerTransaction) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (windowContainerTransaction != null) {
                        parcelObtain.writeInt(1);
                        windowContainerTransaction.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyTransaction(windowContainerTransaction);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public int applySyncTransaction(WindowContainerTransaction windowContainerTransaction, IWindowContainerTransactionCallback iWindowContainerTransactionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (windowContainerTransaction != null) {
                        parcelObtain.writeInt(1);
                        windowContainerTransaction.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iWindowContainerTransactionCallback != null ? iWindowContainerTransactionCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().applySyncTransaction(windowContainerTransaction, iWindowContainerTransactionCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public ITaskOrganizerController getTaskOrganizerController() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTaskOrganizerController();
                    }
                    parcelObtain2.readException();
                    return ITaskOrganizerController.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public IDisplayAreaOrganizerController getDisplayAreaOrganizerController() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayAreaOrganizerController();
                    }
                    parcelObtain2.readException();
                    return IDisplayAreaOrganizerController.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public boolean takeScreenshot(WindowContainerToken windowContainerToken, SurfaceControl surfaceControl) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (windowContainerToken != null) {
                        parcelObtain.writeInt(1);
                        windowContainerToken.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().takeScreenshot(windowContainerToken, surfaceControl);
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    if (parcelObtain2.readInt() != 0) {
                        surfaceControl.readFromParcel(parcelObtain2);
                    }
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowOrganizerController iWindowOrganizerController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindowOrganizerController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindowOrganizerController;
            return true;
        }

        public static IWindowOrganizerController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
