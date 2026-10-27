package android.view;

import android.app.ActivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IRecentsAnimationController extends IInterface {

    public static class Default implements IRecentsAnimationController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IRecentsAnimationController
        public void cleanupScreenshot() throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void finish(boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void hideCurrentInputMethod() throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public boolean removeTask(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IRecentsAnimationController
        public ActivityManager.TaskSnapshot screenshotTask(int i) throws RemoteException {
            return null;
        }

        @Override // android.view.IRecentsAnimationController
        public void setAnimationTargetsBehindSystemBars(boolean z) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void setDeferCancelUntilNextTransition(boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void setInputConsumerEnabled(boolean z) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void setWillFinishToHome(boolean z) throws RemoteException {
        }
    }

    void cleanupScreenshot() throws RemoteException;

    void finish(boolean z, boolean z2) throws RemoteException;

    void hideCurrentInputMethod() throws RemoteException;

    boolean removeTask(int i) throws RemoteException;

    ActivityManager.TaskSnapshot screenshotTask(int i) throws RemoteException;

    void setAnimationTargetsBehindSystemBars(boolean z) throws RemoteException;

    void setDeferCancelUntilNextTransition(boolean z, boolean z2) throws RemoteException;

    void setInputConsumerEnabled(boolean z) throws RemoteException;

    void setWillFinishToHome(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecentsAnimationController {
        private static final String DESCRIPTOR = "android.view.IRecentsAnimationController";
        static final int TRANSACTION_cleanupScreenshot = 6;
        static final int TRANSACTION_finish = 2;
        static final int TRANSACTION_hideCurrentInputMethod = 5;
        static final int TRANSACTION_removeTask = 9;
        static final int TRANSACTION_screenshotTask = 1;
        static final int TRANSACTION_setAnimationTargetsBehindSystemBars = 4;
        static final int TRANSACTION_setDeferCancelUntilNextTransition = 7;
        static final int TRANSACTION_setInputConsumerEnabled = 3;
        static final int TRANSACTION_setWillFinishToHome = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "screenshotTask";
                case 2:
                    return "finish";
                case 3:
                    return "setInputConsumerEnabled";
                case 4:
                    return "setAnimationTargetsBehindSystemBars";
                case 5:
                    return "hideCurrentInputMethod";
                case 6:
                    return "cleanupScreenshot";
                case 7:
                    return "setDeferCancelUntilNextTransition";
                case 8:
                    return "setWillFinishToHome";
                case 9:
                    return "removeTask";
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

        public static IRecentsAnimationController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecentsAnimationController)) {
                return (IRecentsAnimationController) iInterfaceQueryLocalInterface;
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
                    ActivityManager.TaskSnapshot taskSnapshotScreenshotTask = screenshotTask(parcel.readInt());
                    parcel2.writeNoException();
                    if (taskSnapshotScreenshotTask != null) {
                        parcel2.writeInt(1);
                        taskSnapshotScreenshotTask.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    finish(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInputConsumerEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAnimationTargetsBehindSystemBars(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideCurrentInputMethod();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    cleanupScreenshot();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDeferCancelUntilNextTransition(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWillFinishToHome(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveTask = removeTask(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveTask ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRecentsAnimationController {
            public static IRecentsAnimationController sDefaultImpl;
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

            @Override // android.view.IRecentsAnimationController
            public ActivityManager.TaskSnapshot screenshotTask(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().screenshotTask(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void finish(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finish(z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setInputConsumerEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInputConsumerEnabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setAnimationTargetsBehindSystemBars(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAnimationTargetsBehindSystemBars(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void hideCurrentInputMethod() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideCurrentInputMethod();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void cleanupScreenshot() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cleanupScreenshot();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setDeferCancelUntilNextTransition(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDeferCancelUntilNextTransition(z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setWillFinishToHome(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWillFinishToHome(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public boolean removeTask(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeTask(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecentsAnimationController iRecentsAnimationController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRecentsAnimationController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecentsAnimationController;
            return true;
        }

        public static IRecentsAnimationController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
