package android.window;

import android.app.ActivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.window.ITaskOrganizer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ITaskOrganizerController extends IInterface {

    public static class Default implements ITaskOrganizerController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public ActivityManager.RunningTaskInfo createRootTask(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public boolean deleteRootTask(WindowContainerToken windowContainerToken) throws RemoteException {
            return false;
        }

        @Override // android.window.ITaskOrganizerController
        public List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken windowContainerToken, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public WindowContainerToken getImeTarget(int i) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public List<ActivityManager.RunningTaskInfo> getRootTasks(int i, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public void registerTaskOrganizer(ITaskOrganizer iTaskOrganizer, int i) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizerController
        public void setInterceptBackPressedOnTaskRoot(ITaskOrganizer iTaskOrganizer, boolean z) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizerController
        public void setLaunchRoot(int i, WindowContainerToken windowContainerToken) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizerController
        public void unregisterTaskOrganizer(ITaskOrganizer iTaskOrganizer) throws RemoteException {
        }
    }

    ActivityManager.RunningTaskInfo createRootTask(int i, int i2) throws RemoteException;

    boolean deleteRootTask(WindowContainerToken windowContainerToken) throws RemoteException;

    List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken windowContainerToken, int[] iArr) throws RemoteException;

    WindowContainerToken getImeTarget(int i) throws RemoteException;

    List<ActivityManager.RunningTaskInfo> getRootTasks(int i, int[] iArr) throws RemoteException;

    void registerTaskOrganizer(ITaskOrganizer iTaskOrganizer, int i) throws RemoteException;

    void setInterceptBackPressedOnTaskRoot(ITaskOrganizer iTaskOrganizer, boolean z) throws RemoteException;

    void setLaunchRoot(int i, WindowContainerToken windowContainerToken) throws RemoteException;

    void unregisterTaskOrganizer(ITaskOrganizer iTaskOrganizer) throws RemoteException;

    public static abstract class Stub extends Binder implements ITaskOrganizerController {
        private static final String DESCRIPTOR = "android.window.ITaskOrganizerController";
        static final int TRANSACTION_createRootTask = 3;
        static final int TRANSACTION_deleteRootTask = 4;
        static final int TRANSACTION_getChildTasks = 5;
        static final int TRANSACTION_getImeTarget = 7;
        static final int TRANSACTION_getRootTasks = 6;
        static final int TRANSACTION_registerTaskOrganizer = 1;
        static final int TRANSACTION_setInterceptBackPressedOnTaskRoot = 9;
        static final int TRANSACTION_setLaunchRoot = 8;
        static final int TRANSACTION_unregisterTaskOrganizer = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerTaskOrganizer";
                case 2:
                    return "unregisterTaskOrganizer";
                case 3:
                    return "createRootTask";
                case 4:
                    return "deleteRootTask";
                case 5:
                    return "getChildTasks";
                case 6:
                    return "getRootTasks";
                case 7:
                    return "getImeTarget";
                case 8:
                    return "setLaunchRoot";
                case 9:
                    return "setInterceptBackPressedOnTaskRoot";
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

        public static ITaskOrganizerController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITaskOrganizerController)) {
                return (ITaskOrganizerController) iInterfaceQueryLocalInterface;
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
                    registerTaskOrganizer(ITaskOrganizer.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterTaskOrganizer(ITaskOrganizer.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    ActivityManager.RunningTaskInfo runningTaskInfoCreateRootTask = createRootTask(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (runningTaskInfoCreateRootTask != null) {
                        parcel2.writeInt(1);
                        runningTaskInfoCreateRootTask.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeleteRootTask = deleteRootTask(parcel.readInt() != 0 ? WindowContainerToken.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteRootTask ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ActivityManager.RunningTaskInfo> childTasks = getChildTasks(parcel.readInt() != 0 ? WindowContainerToken.CREATOR.createFromParcel(parcel) : null, parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(childTasks);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ActivityManager.RunningTaskInfo> rootTasks = getRootTasks(parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(rootTasks);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    WindowContainerToken imeTarget = getImeTarget(parcel.readInt());
                    parcel2.writeNoException();
                    if (imeTarget != null) {
                        parcel2.writeInt(1);
                        imeTarget.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLaunchRoot(parcel.readInt(), parcel.readInt() != 0 ? WindowContainerToken.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterceptBackPressedOnTaskRoot(ITaskOrganizer.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITaskOrganizerController {
            public static ITaskOrganizerController sDefaultImpl;
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

            @Override // android.window.ITaskOrganizerController
            public void registerTaskOrganizer(ITaskOrganizer iTaskOrganizer, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTaskOrganizer != null ? iTaskOrganizer.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTaskOrganizer(iTaskOrganizer, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public void unregisterTaskOrganizer(ITaskOrganizer iTaskOrganizer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTaskOrganizer != null ? iTaskOrganizer.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterTaskOrganizer(iTaskOrganizer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public ActivityManager.RunningTaskInfo createRootTask(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createRootTask(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public boolean deleteRootTask(WindowContainerToken windowContainerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (windowContainerToken != null) {
                        parcelObtain.writeInt(1);
                        windowContainerToken.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteRootTask(windowContainerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken windowContainerToken, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (windowContainerToken != null) {
                        parcelObtain.writeInt(1);
                        windowContainerToken.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChildTasks(windowContainerToken, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public List<ActivityManager.RunningTaskInfo> getRootTasks(int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRootTasks(i, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public WindowContainerToken getImeTarget(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImeTarget(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WindowContainerToken.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public void setLaunchRoot(int i, WindowContainerToken windowContainerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (windowContainerToken != null) {
                        parcelObtain.writeInt(1);
                        windowContainerToken.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLaunchRoot(i, windowContainerToken);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public void setInterceptBackPressedOnTaskRoot(ITaskOrganizer iTaskOrganizer, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTaskOrganizer != null ? iTaskOrganizer.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterceptBackPressedOnTaskRoot(iTaskOrganizer, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITaskOrganizerController iTaskOrganizerController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTaskOrganizerController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTaskOrganizerController;
            return true;
        }

        public static ITaskOrganizerController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
