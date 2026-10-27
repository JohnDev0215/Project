package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ISyncAdapterUnsyncableAccountCallback extends IInterface {

    public static class Default implements ISyncAdapterUnsyncableAccountCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.ISyncAdapterUnsyncableAccountCallback
        public void onUnsyncableAccountDone(boolean z) throws RemoteException {
        }
    }

    void onUnsyncableAccountDone(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements ISyncAdapterUnsyncableAccountCallback {
        private static final String DESCRIPTOR = "android.content.ISyncAdapterUnsyncableAccountCallback";
        static final int TRANSACTION_onUnsyncableAccountDone = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onUnsyncableAccountDone";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISyncAdapterUnsyncableAccountCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISyncAdapterUnsyncableAccountCallback)) {
                return (ISyncAdapterUnsyncableAccountCallback) iInterfaceQueryLocalInterface;
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
                onUnsyncableAccountDone(parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISyncAdapterUnsyncableAccountCallback {
            public static ISyncAdapterUnsyncableAccountCallback sDefaultImpl;
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

            @Override // android.content.ISyncAdapterUnsyncableAccountCallback
            public void onUnsyncableAccountDone(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUnsyncableAccountDone(z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISyncAdapterUnsyncableAccountCallback iSyncAdapterUnsyncableAccountCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSyncAdapterUnsyncableAccountCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSyncAdapterUnsyncableAccountCallback;
            return true;
        }

        public static ISyncAdapterUnsyncableAccountCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
