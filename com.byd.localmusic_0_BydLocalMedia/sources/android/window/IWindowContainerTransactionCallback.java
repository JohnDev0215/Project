package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;

/* JADX INFO: loaded from: classes3.dex */
public interface IWindowContainerTransactionCallback extends IInterface {

    public static class Default implements IWindowContainerTransactionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.window.IWindowContainerTransactionCallback
        public void onTransactionReady(int i, SurfaceControl.Transaction transaction) throws RemoteException {
        }
    }

    void onTransactionReady(int i, SurfaceControl.Transaction transaction) throws RemoteException;

    public static abstract class Stub extends Binder implements IWindowContainerTransactionCallback {
        private static final String DESCRIPTOR = "android.window.IWindowContainerTransactionCallback";
        static final int TRANSACTION_onTransactionReady = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onTransactionReady";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowContainerTransactionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindowContainerTransactionCallback)) {
                return (IWindowContainerTransactionCallback) iInterfaceQueryLocalInterface;
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
                onTransactionReady(parcel.readInt(), parcel.readInt() != 0 ? SurfaceControl.Transaction.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IWindowContainerTransactionCallback {
            public static IWindowContainerTransactionCallback sDefaultImpl;
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

            @Override // android.window.IWindowContainerTransactionCallback
            public void onTransactionReady(int i, SurfaceControl.Transaction transaction) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (transaction != null) {
                        parcelObtain.writeInt(1);
                        transaction.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTransactionReady(i, transaction);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowContainerTransactionCallback iWindowContainerTransactionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindowContainerTransactionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindowContainerTransactionCallback;
            return true;
        }

        public static IWindowContainerTransactionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
