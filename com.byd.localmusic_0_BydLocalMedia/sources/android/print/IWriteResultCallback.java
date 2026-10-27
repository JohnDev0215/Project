package android.print;

import android.os.Binder;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public interface IWriteResultCallback extends IInterface {

    public static class Default implements IWriteResultCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.print.IWriteResultCallback
        public void onWriteCanceled(int i) throws RemoteException {
        }

        @Override // android.print.IWriteResultCallback
        public void onWriteFailed(CharSequence charSequence, int i) throws RemoteException {
        }

        @Override // android.print.IWriteResultCallback
        public void onWriteFinished(PageRange[] pageRangeArr, int i) throws RemoteException {
        }

        @Override // android.print.IWriteResultCallback
        public void onWriteStarted(ICancellationSignal iCancellationSignal, int i) throws RemoteException {
        }
    }

    void onWriteCanceled(int i) throws RemoteException;

    void onWriteFailed(CharSequence charSequence, int i) throws RemoteException;

    void onWriteFinished(PageRange[] pageRangeArr, int i) throws RemoteException;

    void onWriteStarted(ICancellationSignal iCancellationSignal, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IWriteResultCallback {
        private static final String DESCRIPTOR = "android.print.IWriteResultCallback";
        static final int TRANSACTION_onWriteCanceled = 4;
        static final int TRANSACTION_onWriteFailed = 3;
        static final int TRANSACTION_onWriteFinished = 2;
        static final int TRANSACTION_onWriteStarted = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onWriteStarted";
            }
            if (i == 2) {
                return "onWriteFinished";
            }
            if (i == 3) {
                return "onWriteFailed";
            }
            if (i != 4) {
                return null;
            }
            return "onWriteCanceled";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWriteResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWriteResultCallback)) {
                return (IWriteResultCallback) iInterfaceQueryLocalInterface;
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
                onWriteStarted(ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onWriteFinished((PageRange[]) parcel.createTypedArray(PageRange.CREATOR), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onWriteFailed(parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onWriteCanceled(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IWriteResultCallback {
            public static IWriteResultCallback sDefaultImpl;
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

            @Override // android.print.IWriteResultCallback
            public void onWriteStarted(ICancellationSignal iCancellationSignal, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCancellationSignal != null ? iCancellationSignal.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWriteStarted(iCancellationSignal, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IWriteResultCallback
            public void onWriteFinished(PageRange[] pageRangeArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(pageRangeArr, 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWriteFinished(pageRangeArr, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IWriteResultCallback
            public void onWriteFailed(CharSequence charSequence, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWriteFailed(charSequence, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.print.IWriteResultCallback
            public void onWriteCanceled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWriteCanceled(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWriteResultCallback iWriteResultCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWriteResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWriteResultCallback;
            return true;
        }

        public static IWriteResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
