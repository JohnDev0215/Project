package android.os;

import android.os.ITwilightCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarTwilightManager extends IInterface {

    public static class Default implements ICarTwilightManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.ICarTwilightManager
        public long getSunriseTimeMillis() throws RemoteException {
            return 0L;
        }

        @Override // android.os.ICarTwilightManager
        public long getSunsetTimeMillis() throws RemoteException {
            return 0L;
        }

        @Override // android.os.ICarTwilightManager
        public boolean isNight() throws RemoteException {
            return false;
        }

        @Override // android.os.ICarTwilightManager
        public void subscribeTwilightCallback(ITwilightCallback iTwilightCallback) throws RemoteException {
        }

        @Override // android.os.ICarTwilightManager
        public void unsubscribeTwilightCallback(ITwilightCallback iTwilightCallback) throws RemoteException {
        }
    }

    long getSunriseTimeMillis() throws RemoteException;

    long getSunsetTimeMillis() throws RemoteException;

    boolean isNight() throws RemoteException;

    void subscribeTwilightCallback(ITwilightCallback iTwilightCallback) throws RemoteException;

    void unsubscribeTwilightCallback(ITwilightCallback iTwilightCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarTwilightManager {
        private static final String DESCRIPTOR = "android.os.ICarTwilightManager";
        static final int TRANSACTION_getSunriseTimeMillis = 4;
        static final int TRANSACTION_getSunsetTimeMillis = 5;
        static final int TRANSACTION_isNight = 3;
        static final int TRANSACTION_subscribeTwilightCallback = 1;
        static final int TRANSACTION_unsubscribeTwilightCallback = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "subscribeTwilightCallback";
            }
            if (i == 2) {
                return "unsubscribeTwilightCallback";
            }
            if (i == 3) {
                return "isNight";
            }
            if (i == 4) {
                return "getSunriseTimeMillis";
            }
            if (i != 5) {
                return null;
            }
            return "getSunsetTimeMillis";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTwilightManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarTwilightManager)) {
                return (ICarTwilightManager) iInterfaceQueryLocalInterface;
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
                subscribeTwilightCallback(ITwilightCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unsubscribeTwilightCallback(ITwilightCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zIsNight = isNight();
                parcel2.writeNoException();
                parcel2.writeInt(zIsNight ? 1 : 0);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                long sunriseTimeMillis = getSunriseTimeMillis();
                parcel2.writeNoException();
                parcel2.writeLong(sunriseTimeMillis);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            long sunsetTimeMillis = getSunsetTimeMillis();
            parcel2.writeNoException();
            parcel2.writeLong(sunsetTimeMillis);
            return true;
        }

        private static class Proxy implements ICarTwilightManager {
            public static ICarTwilightManager sDefaultImpl;
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

            @Override // android.os.ICarTwilightManager
            public void subscribeTwilightCallback(ITwilightCallback iTwilightCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTwilightCallback != null ? iTwilightCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().subscribeTwilightCallback(iTwilightCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.ICarTwilightManager
            public void unsubscribeTwilightCallback(ITwilightCallback iTwilightCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTwilightCallback != null ? iTwilightCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unsubscribeTwilightCallback(iTwilightCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.ICarTwilightManager
            public boolean isNight() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNight();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.ICarTwilightManager
            public long getSunriseTimeMillis() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSunriseTimeMillis();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.ICarTwilightManager
            public long getSunsetTimeMillis() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSunsetTimeMillis();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarTwilightManager iCarTwilightManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarTwilightManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarTwilightManager;
            return true;
        }

        public static ICarTwilightManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
