package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IRemoteAnimationFinishedCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IRemoteAnimationRunner extends IInterface {

    public static class Default implements IRemoteAnimationRunner {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IRemoteAnimationRunner
        public void onAnimationCancelled() throws RemoteException {
        }

        @Override // android.view.IRemoteAnimationRunner
        public void onAnimationStart(RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) throws RemoteException {
        }
    }

    void onAnimationCancelled() throws RemoteException;

    void onAnimationStart(RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteAnimationRunner {
        private static final String DESCRIPTOR = "android.view.IRemoteAnimationRunner";
        static final int TRANSACTION_onAnimationCancelled = 2;
        static final int TRANSACTION_onAnimationStart = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onAnimationStart";
            }
            if (i != 2) {
                return null;
            }
            return "onAnimationCancelled";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteAnimationRunner asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRemoteAnimationRunner)) {
                return (IRemoteAnimationRunner) iInterfaceQueryLocalInterface;
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
                onAnimationStart((RemoteAnimationTarget[]) parcel.createTypedArray(RemoteAnimationTarget.CREATOR), (RemoteAnimationTarget[]) parcel.createTypedArray(RemoteAnimationTarget.CREATOR), IRemoteAnimationFinishedCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onAnimationCancelled();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRemoteAnimationRunner {
            public static IRemoteAnimationRunner sDefaultImpl;
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

            @Override // android.view.IRemoteAnimationRunner
            public void onAnimationStart(RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(remoteAnimationTargetArr, 0);
                    parcelObtain.writeTypedArray(remoteAnimationTargetArr2, 0);
                    parcelObtain.writeStrongBinder(iRemoteAnimationFinishedCallback != null ? iRemoteAnimationFinishedCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAnimationStart(remoteAnimationTargetArr, remoteAnimationTargetArr2, iRemoteAnimationFinishedCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IRemoteAnimationRunner
            public void onAnimationCancelled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAnimationCancelled();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteAnimationRunner iRemoteAnimationRunner) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteAnimationRunner == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteAnimationRunner;
            return true;
        }

        public static IRemoteAnimationRunner getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
