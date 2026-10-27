package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IContextHubTransactionCallback extends IInterface {

    public static class Default implements IContextHubTransactionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.location.IContextHubTransactionCallback
        public void onQueryResponse(int i, List<NanoAppState> list) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubTransactionCallback
        public void onTransactionComplete(int i) throws RemoteException {
        }
    }

    void onQueryResponse(int i, List<NanoAppState> list) throws RemoteException;

    void onTransactionComplete(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IContextHubTransactionCallback {
        private static final String DESCRIPTOR = "android.hardware.location.IContextHubTransactionCallback";
        static final int TRANSACTION_onQueryResponse = 1;
        static final int TRANSACTION_onTransactionComplete = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onQueryResponse";
            }
            if (i != 2) {
                return null;
            }
            return "onTransactionComplete";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContextHubTransactionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContextHubTransactionCallback)) {
                return (IContextHubTransactionCallback) iInterfaceQueryLocalInterface;
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
                onQueryResponse(parcel.readInt(), parcel.createTypedArrayList(NanoAppState.CREATOR));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onTransactionComplete(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IContextHubTransactionCallback {
            public static IContextHubTransactionCallback sDefaultImpl;
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

            @Override // android.hardware.location.IContextHubTransactionCallback
            public void onQueryResponse(int i, List<NanoAppState> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onQueryResponse(i, list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubTransactionCallback
            public void onTransactionComplete(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTransactionComplete(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContextHubTransactionCallback iContextHubTransactionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContextHubTransactionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContextHubTransactionCallback;
            return true;
        }

        public static IContextHubTransactionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
