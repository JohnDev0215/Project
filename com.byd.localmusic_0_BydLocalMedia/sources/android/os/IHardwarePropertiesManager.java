package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IHardwarePropertiesManager extends IInterface {

    public static class Default implements IHardwarePropertiesManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IHardwarePropertiesManager
        public CpuUsageInfo[] getCpuUsages(String str) throws RemoteException {
            return null;
        }

        @Override // android.os.IHardwarePropertiesManager
        public float[] getDeviceTemperatures(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.os.IHardwarePropertiesManager
        public float[] getFanSpeeds(String str) throws RemoteException {
            return null;
        }
    }

    CpuUsageInfo[] getCpuUsages(String str) throws RemoteException;

    float[] getDeviceTemperatures(String str, int i, int i2) throws RemoteException;

    float[] getFanSpeeds(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IHardwarePropertiesManager {
        private static final String DESCRIPTOR = "android.os.IHardwarePropertiesManager";
        static final int TRANSACTION_getCpuUsages = 2;
        static final int TRANSACTION_getDeviceTemperatures = 1;
        static final int TRANSACTION_getFanSpeeds = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getDeviceTemperatures";
            }
            if (i == 2) {
                return "getCpuUsages";
            }
            if (i != 3) {
                return null;
            }
            return "getFanSpeeds";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHardwarePropertiesManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHardwarePropertiesManager)) {
                return (IHardwarePropertiesManager) iInterfaceQueryLocalInterface;
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
                float[] deviceTemperatures = getDeviceTemperatures(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeFloatArray(deviceTemperatures);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                CpuUsageInfo[] cpuUsages = getCpuUsages(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedArray(cpuUsages, 1);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            float[] fanSpeeds = getFanSpeeds(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeFloatArray(fanSpeeds);
            return true;
        }

        private static class Proxy implements IHardwarePropertiesManager {
            public static IHardwarePropertiesManager sDefaultImpl;
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

            @Override // android.os.IHardwarePropertiesManager
            public float[] getDeviceTemperatures(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceTemperatures(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createFloatArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IHardwarePropertiesManager
            public CpuUsageInfo[] getCpuUsages(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCpuUsages(str);
                    }
                    parcelObtain2.readException();
                    return (CpuUsageInfo[]) parcelObtain2.createTypedArray(CpuUsageInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IHardwarePropertiesManager
            public float[] getFanSpeeds(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFanSpeeds(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createFloatArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHardwarePropertiesManager iHardwarePropertiesManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iHardwarePropertiesManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHardwarePropertiesManager;
            return true;
        }

        public static IHardwarePropertiesManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
