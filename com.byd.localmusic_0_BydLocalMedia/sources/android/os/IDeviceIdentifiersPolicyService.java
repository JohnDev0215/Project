package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceIdentifiersPolicyService extends IInterface {

    public static class Default implements IDeviceIdentifiersPolicyService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IDeviceIdentifiersPolicyService
        public String getSerial() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdentifiersPolicyService
        public String getSerialForPackage(String str, String str2) throws RemoteException {
            return null;
        }
    }

    String getSerial() throws RemoteException;

    String getSerialForPackage(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeviceIdentifiersPolicyService {
        private static final String DESCRIPTOR = "android.os.IDeviceIdentifiersPolicyService";
        static final int TRANSACTION_getSerial = 1;
        static final int TRANSACTION_getSerialForPackage = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getSerial";
            }
            if (i != 2) {
                return null;
            }
            return "getSerialForPackage";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceIdentifiersPolicyService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDeviceIdentifiersPolicyService)) {
                return (IDeviceIdentifiersPolicyService) iInterfaceQueryLocalInterface;
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
                String serial = getSerial();
                parcel2.writeNoException();
                parcel2.writeString(serial);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            String serialForPackage = getSerialForPackage(parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(serialForPackage);
            return true;
        }

        private static class Proxy implements IDeviceIdentifiersPolicyService {
            public static IDeviceIdentifiersPolicyService sDefaultImpl;
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

            @Override // android.os.IDeviceIdentifiersPolicyService
            public String getSerial() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSerial();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdentifiersPolicyService
            public String getSerialForPackage(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSerialForPackage(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceIdentifiersPolicyService iDeviceIdentifiersPolicyService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDeviceIdentifiersPolicyService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDeviceIdentifiersPolicyService;
            return true;
        }

        public static IDeviceIdentifiersPolicyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
