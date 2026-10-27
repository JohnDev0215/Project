package android.service.contentcapture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.contentcapture.IDataShareReadAdapter;

/* JADX INFO: loaded from: classes3.dex */
public interface IDataShareCallback extends IInterface {

    public static class Default implements IDataShareCallback {
        @Override // android.service.contentcapture.IDataShareCallback
        public void accept(IDataShareReadAdapter iDataShareReadAdapter) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.contentcapture.IDataShareCallback
        public void reject() throws RemoteException {
        }
    }

    void accept(IDataShareReadAdapter iDataShareReadAdapter) throws RemoteException;

    void reject() throws RemoteException;

    public static abstract class Stub extends Binder implements IDataShareCallback {
        private static final String DESCRIPTOR = "android.service.contentcapture.IDataShareCallback";
        static final int TRANSACTION_accept = 1;
        static final int TRANSACTION_reject = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "accept";
            }
            if (i != 2) {
                return null;
            }
            return "reject";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDataShareCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDataShareCallback)) {
                return (IDataShareCallback) iInterfaceQueryLocalInterface;
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
                accept(IDataShareReadAdapter.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                reject();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDataShareCallback {
            public static IDataShareCallback sDefaultImpl;
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

            @Override // android.service.contentcapture.IDataShareCallback
            public void accept(IDataShareReadAdapter iDataShareReadAdapter) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDataShareReadAdapter != null ? iDataShareReadAdapter.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().accept(iDataShareReadAdapter);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IDataShareCallback
            public void reject() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().reject();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataShareCallback iDataShareCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDataShareCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDataShareCallback;
            return true;
        }

        public static IDataShareCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
