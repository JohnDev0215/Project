package android.window;

import android.app.ActivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;

/* JADX INFO: loaded from: classes3.dex */
public interface ITaskOrganizer extends IInterface {

    public static class Default implements ITaskOrganizer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.window.ITaskOrganizer
        public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
        }
    }

    void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) throws RemoteException;

    void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements ITaskOrganizer {
        private static final String DESCRIPTOR = "android.window.ITaskOrganizer";
        static final int TRANSACTION_onBackPressedOnTaskRoot = 4;
        static final int TRANSACTION_onTaskAppeared = 1;
        static final int TRANSACTION_onTaskInfoChanged = 3;
        static final int TRANSACTION_onTaskVanished = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onTaskAppeared";
            }
            if (i == 2) {
                return "onTaskVanished";
            }
            if (i == 3) {
                return "onTaskInfoChanged";
            }
            if (i != 4) {
                return null;
            }
            return "onBackPressedOnTaskRoot";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITaskOrganizer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITaskOrganizer)) {
                return (ITaskOrganizer) iInterfaceQueryLocalInterface;
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
                onTaskAppeared(parcel.readInt() != 0 ? ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? SurfaceControl.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onTaskVanished(parcel.readInt() != 0 ? ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onTaskInfoChanged(parcel.readInt() != 0 ? ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onBackPressedOnTaskRoot(parcel.readInt() != 0 ? ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ITaskOrganizer {
            public static ITaskOrganizer sDefaultImpl;
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

            @Override // android.window.ITaskOrganizer
            public void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (runningTaskInfo != null) {
                        parcelObtain.writeInt(1);
                        runningTaskInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (surfaceControl != null) {
                        parcelObtain.writeInt(1);
                        surfaceControl.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTaskAppeared(runningTaskInfo, surfaceControl);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (runningTaskInfo != null) {
                        parcelObtain.writeInt(1);
                        runningTaskInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTaskVanished(runningTaskInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (runningTaskInfo != null) {
                        parcelObtain.writeInt(1);
                        runningTaskInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTaskInfoChanged(runningTaskInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (runningTaskInfo != null) {
                        parcelObtain.writeInt(1);
                        runningTaskInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBackPressedOnTaskRoot(runningTaskInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITaskOrganizer iTaskOrganizer) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTaskOrganizer == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTaskOrganizer;
            return true;
        }

        public static ITaskOrganizer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
