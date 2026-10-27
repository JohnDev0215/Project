package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IControlsSubscription extends IInterface {

    public static class Default implements IControlsSubscription {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.controls.IControlsSubscription
        public void cancel() throws RemoteException {
        }

        @Override // android.service.controls.IControlsSubscription
        public void request(long j) throws RemoteException {
        }
    }

    void cancel() throws RemoteException;

    void request(long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IControlsSubscription {
        private static final String DESCRIPTOR = "android.service.controls.IControlsSubscription";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_request = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "request";
            }
            if (i != 2) {
                return null;
            }
            return "cancel";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControlsSubscription asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IControlsSubscription)) {
                return (IControlsSubscription) iInterfaceQueryLocalInterface;
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
                request(parcel.readLong());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                cancel();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IControlsSubscription {
            public static IControlsSubscription sDefaultImpl;
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

            @Override // android.service.controls.IControlsSubscription
            public void request(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().request(j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscription
            public void cancel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancel();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IControlsSubscription iControlsSubscription) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iControlsSubscription == null) {
                return false;
            }
            Proxy.sDefaultImpl = iControlsSubscription;
            return true;
        }

        public static IControlsSubscription getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
