package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IUpdateEngineCallback extends IInterface {

    public static class Default implements IUpdateEngineCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IUpdateEngineCallback
        public void onPayloadApplicationComplete(int i) throws RemoteException {
        }

        @Override // android.os.IUpdateEngineCallback
        public void onStatusUpdate(int i, float f) throws RemoteException {
        }
    }

    void onPayloadApplicationComplete(int i) throws RemoteException;

    void onStatusUpdate(int i, float f) throws RemoteException;

    public static abstract class Stub extends Binder implements IUpdateEngineCallback {
        private static final String DESCRIPTOR = "android.os.IUpdateEngineCallback";
        static final int TRANSACTION_onPayloadApplicationComplete = 2;
        static final int TRANSACTION_onStatusUpdate = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onStatusUpdate";
            }
            if (i != 2) {
                return null;
            }
            return "onPayloadApplicationComplete";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUpdateEngineCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUpdateEngineCallback)) {
                return (IUpdateEngineCallback) iInterfaceQueryLocalInterface;
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
                onStatusUpdate(parcel.readInt(), parcel.readFloat());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onPayloadApplicationComplete(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IUpdateEngineCallback {
            public static IUpdateEngineCallback sDefaultImpl;
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

            @Override // android.os.IUpdateEngineCallback
            public void onStatusUpdate(int i, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStatusUpdate(i, f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUpdateEngineCallback
            public void onPayloadApplicationComplete(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPayloadApplicationComplete(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUpdateEngineCallback iUpdateEngineCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUpdateEngineCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUpdateEngineCallback;
            return true;
        }

        public static IUpdateEngineCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
