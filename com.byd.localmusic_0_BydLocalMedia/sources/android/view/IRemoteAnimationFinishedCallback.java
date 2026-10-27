package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IRemoteAnimationFinishedCallback extends IInterface {

    public static class Default implements IRemoteAnimationFinishedCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IRemoteAnimationFinishedCallback
        public void onAnimationFinished() throws RemoteException {
        }
    }

    void onAnimationFinished() throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteAnimationFinishedCallback {
        private static final String DESCRIPTOR = "android.view.IRemoteAnimationFinishedCallback";
        static final int TRANSACTION_onAnimationFinished = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onAnimationFinished";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteAnimationFinishedCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRemoteAnimationFinishedCallback)) {
                return (IRemoteAnimationFinishedCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onAnimationFinished();
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IRemoteAnimationFinishedCallback {
            public static IRemoteAnimationFinishedCallback sDefaultImpl;
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

            @Override // android.view.IRemoteAnimationFinishedCallback
            public void onAnimationFinished() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAnimationFinished();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteAnimationFinishedCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteAnimationFinishedCallback;
            return true;
        }

        public static IRemoteAnimationFinishedCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
