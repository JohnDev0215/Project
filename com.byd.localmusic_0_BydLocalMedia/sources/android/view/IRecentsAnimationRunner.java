package android.view;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IRecentsAnimationController;

/* JADX INFO: loaded from: classes3.dex */
public interface IRecentsAnimationRunner extends IInterface {

    public static class Default implements IRecentsAnimationRunner {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IRecentsAnimationRunner
        public void onAnimationCanceled(ActivityManager.TaskSnapshot taskSnapshot) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationRunner
        public void onAnimationStart(IRecentsAnimationController iRecentsAnimationController, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, Rect rect, Rect rect2) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationRunner
        public void onTaskAppeared(RemoteAnimationTarget remoteAnimationTarget) throws RemoteException {
        }
    }

    void onAnimationCanceled(ActivityManager.TaskSnapshot taskSnapshot) throws RemoteException;

    void onAnimationStart(IRecentsAnimationController iRecentsAnimationController, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, Rect rect, Rect rect2) throws RemoteException;

    void onTaskAppeared(RemoteAnimationTarget remoteAnimationTarget) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecentsAnimationRunner {
        private static final String DESCRIPTOR = "android.view.IRecentsAnimationRunner";
        static final int TRANSACTION_onAnimationCanceled = 2;
        static final int TRANSACTION_onAnimationStart = 3;
        static final int TRANSACTION_onTaskAppeared = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 2) {
                return "onAnimationCanceled";
            }
            if (i == 3) {
                return "onAnimationStart";
            }
            if (i != 4) {
                return null;
            }
            return "onTaskAppeared";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecentsAnimationRunner asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecentsAnimationRunner)) {
                return (IRecentsAnimationRunner) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onAnimationCanceled(parcel.readInt() != 0 ? ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onAnimationStart(IRecentsAnimationController.Stub.asInterface(parcel.readStrongBinder()), (RemoteAnimationTarget[]) parcel.createTypedArray(RemoteAnimationTarget.CREATOR), (RemoteAnimationTarget[]) parcel.createTypedArray(RemoteAnimationTarget.CREATOR), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onTaskAppeared(parcel.readInt() != 0 ? RemoteAnimationTarget.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRecentsAnimationRunner {
            public static IRecentsAnimationRunner sDefaultImpl;
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

            @Override // android.view.IRecentsAnimationRunner
            public void onAnimationCanceled(ActivityManager.TaskSnapshot taskSnapshot) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskSnapshot != null) {
                        parcelObtain.writeInt(1);
                        taskSnapshot.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAnimationCanceled(taskSnapshot);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationRunner
            public void onAnimationStart(IRecentsAnimationController iRecentsAnimationController, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, Rect rect, Rect rect2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRecentsAnimationController != null ? iRecentsAnimationController.asBinder() : null);
                    parcelObtain.writeTypedArray(remoteAnimationTargetArr, 0);
                    parcelObtain.writeTypedArray(remoteAnimationTargetArr2, 0);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect2 != null) {
                        parcelObtain.writeInt(1);
                        rect2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAnimationStart(iRecentsAnimationController, remoteAnimationTargetArr, remoteAnimationTargetArr2, rect, rect2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationRunner
            public void onTaskAppeared(RemoteAnimationTarget remoteAnimationTarget) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (remoteAnimationTarget != null) {
                        parcelObtain.writeInt(1);
                        remoteAnimationTarget.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTaskAppeared(remoteAnimationTarget);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecentsAnimationRunner iRecentsAnimationRunner) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRecentsAnimationRunner == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecentsAnimationRunner;
            return true;
        }

        public static IRecentsAnimationRunner getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
