package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IExternalVibratorService extends IInterface {
    public static final int SCALE_HIGH = 1;
    public static final int SCALE_LOW = -1;
    public static final int SCALE_MUTE = -100;
    public static final int SCALE_NONE = 0;
    public static final int SCALE_VERY_HIGH = 2;
    public static final int SCALE_VERY_LOW = -2;

    public static class Default implements IExternalVibratorService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IExternalVibratorService
        public int onExternalVibrationStart(ExternalVibration externalVibration) throws RemoteException {
            return 0;
        }

        @Override // android.os.IExternalVibratorService
        public void onExternalVibrationStop(ExternalVibration externalVibration) throws RemoteException {
        }
    }

    int onExternalVibrationStart(ExternalVibration externalVibration) throws RemoteException;

    void onExternalVibrationStop(ExternalVibration externalVibration) throws RemoteException;

    public static abstract class Stub extends Binder implements IExternalVibratorService {
        private static final String DESCRIPTOR = "android.os.IExternalVibratorService";
        static final int TRANSACTION_onExternalVibrationStart = 1;
        static final int TRANSACTION_onExternalVibrationStop = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onExternalVibrationStart";
            }
            if (i != 2) {
                return null;
            }
            return "onExternalVibrationStop";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExternalVibratorService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IExternalVibratorService)) {
                return (IExternalVibratorService) iInterfaceQueryLocalInterface;
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
                int iOnExternalVibrationStart = onExternalVibrationStart(parcel.readInt() != 0 ? ExternalVibration.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(iOnExternalVibrationStart);
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
            onExternalVibrationStop(parcel.readInt() != 0 ? ExternalVibration.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IExternalVibratorService {
            public static IExternalVibratorService sDefaultImpl;
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

            @Override // android.os.IExternalVibratorService
            public int onExternalVibrationStart(ExternalVibration externalVibration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (externalVibration != null) {
                        parcelObtain.writeInt(1);
                        externalVibration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onExternalVibrationStart(externalVibration);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IExternalVibratorService
            public void onExternalVibrationStop(ExternalVibration externalVibration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (externalVibration != null) {
                        parcelObtain.writeInt(1);
                        externalVibration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onExternalVibrationStop(externalVibration);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExternalVibratorService iExternalVibratorService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iExternalVibratorService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iExternalVibratorService;
            return true;
        }

        public static IExternalVibratorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
