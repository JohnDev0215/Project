package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IBatteryPropertiesRegistrar extends IInterface {

    public static class Default implements IBatteryPropertiesRegistrar {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IBatteryPropertiesRegistrar
        public int getProperty(int i, BatteryProperty batteryProperty) throws RemoteException {
            return 0;
        }

        @Override // android.os.IBatteryPropertiesRegistrar
        public void scheduleUpdate() throws RemoteException {
        }
    }

    int getProperty(int i, BatteryProperty batteryProperty) throws RemoteException;

    void scheduleUpdate() throws RemoteException;

    public static abstract class Stub extends Binder implements IBatteryPropertiesRegistrar {
        private static final String DESCRIPTOR = "android.os.IBatteryPropertiesRegistrar";
        static final int TRANSACTION_getProperty = 1;
        static final int TRANSACTION_scheduleUpdate = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getProperty";
            }
            if (i != 2) {
                return null;
            }
            return "scheduleUpdate";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBatteryPropertiesRegistrar asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBatteryPropertiesRegistrar)) {
                return (IBatteryPropertiesRegistrar) iInterfaceQueryLocalInterface;
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
                if (i == 2) {
                    parcel.enforceInterface(DESCRIPTOR);
                    scheduleUpdate();
                    return true;
                }
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            int i3 = parcel.readInt();
            BatteryProperty batteryProperty = new BatteryProperty();
            int property = getProperty(i3, batteryProperty);
            parcel2.writeNoException();
            parcel2.writeInt(property);
            parcel2.writeInt(1);
            batteryProperty.writeToParcel(parcel2, 1);
            return true;
        }

        private static class Proxy implements IBatteryPropertiesRegistrar {
            public static IBatteryPropertiesRegistrar sDefaultImpl;
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

            @Override // android.os.IBatteryPropertiesRegistrar
            public int getProperty(int i, BatteryProperty batteryProperty) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProperty(i, batteryProperty);
                    }
                    parcelObtain2.readException();
                    int i2 = parcelObtain2.readInt();
                    if (parcelObtain2.readInt() != 0) {
                        batteryProperty.readFromParcel(parcelObtain2);
                    }
                    return i2;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IBatteryPropertiesRegistrar
            public void scheduleUpdate() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().scheduleUpdate();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBatteryPropertiesRegistrar iBatteryPropertiesRegistrar) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBatteryPropertiesRegistrar == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBatteryPropertiesRegistrar;
            return true;
        }

        public static IBatteryPropertiesRegistrar getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
