package android.os;

/* JADX INFO: loaded from: classes2.dex */
public interface IProcessInfoService extends IInterface {

    public static class Default implements IProcessInfoService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IProcessInfoService
        public void getProcessStatesAndOomScoresFromPids(int[] iArr, int[] iArr2, int[] iArr3) throws RemoteException {
        }

        @Override // android.os.IProcessInfoService
        public void getProcessStatesFromPids(int[] iArr, int[] iArr2) throws RemoteException {
        }
    }

    void getProcessStatesAndOomScoresFromPids(int[] iArr, int[] iArr2, int[] iArr3) throws RemoteException;

    void getProcessStatesFromPids(int[] iArr, int[] iArr2) throws RemoteException;

    public static abstract class Stub extends Binder implements IProcessInfoService {
        private static final String DESCRIPTOR = "android.os.IProcessInfoService";
        static final int TRANSACTION_getProcessStatesAndOomScoresFromPids = 2;
        static final int TRANSACTION_getProcessStatesFromPids = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getProcessStatesFromPids";
            }
            if (i != 2) {
                return null;
            }
            return "getProcessStatesAndOomScoresFromPids";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProcessInfoService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IProcessInfoService)) {
                return (IProcessInfoService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int[] iArr;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int[] iArrCreateIntArray = parcel.createIntArray();
                int i3 = parcel.readInt();
                iArr = i3 >= 0 ? new int[i3] : null;
                getProcessStatesFromPids(iArrCreateIntArray, iArr);
                parcel2.writeNoException();
                parcel2.writeIntArray(iArr);
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
            int[] iArrCreateIntArray2 = parcel.createIntArray();
            int i4 = parcel.readInt();
            int[] iArr2 = i4 < 0 ? null : new int[i4];
            int i5 = parcel.readInt();
            iArr = i5 >= 0 ? new int[i5] : null;
            getProcessStatesAndOomScoresFromPids(iArrCreateIntArray2, iArr2, iArr);
            parcel2.writeNoException();
            parcel2.writeIntArray(iArr2);
            parcel2.writeIntArray(iArr);
            return true;
        }

        private static class Proxy implements IProcessInfoService {
            public static IProcessInfoService sDefaultImpl;
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

            @Override // android.os.IProcessInfoService
            public void getProcessStatesFromPids(int[] iArr, int[] iArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (iArr2 == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr2.length);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getProcessStatesFromPids(iArr, iArr2);
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.readIntArray(iArr2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IProcessInfoService
            public void getProcessStatesAndOomScoresFromPids(int[] iArr, int[] iArr2, int[] iArr3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (iArr2 == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr2.length);
                    }
                    if (iArr3 == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr3.length);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getProcessStatesAndOomScoresFromPids(iArr, iArr2, iArr3);
                        return;
                    }
                    parcelObtain2.readException();
                    parcelObtain2.readIntArray(iArr2);
                    parcelObtain2.readIntArray(iArr3);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProcessInfoService iProcessInfoService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProcessInfoService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProcessInfoService;
            return true;
        }

        public static IProcessInfoService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
