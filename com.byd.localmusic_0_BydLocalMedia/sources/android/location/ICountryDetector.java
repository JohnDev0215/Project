package android.location;

import android.location.ICountryListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ICountryDetector extends IInterface {

    public static class Default implements ICountryDetector {
        @Override // android.location.ICountryDetector
        public void addCountryListener(ICountryListener iCountryListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.location.ICountryDetector
        public Country detectCountry() throws RemoteException {
            return null;
        }

        @Override // android.location.ICountryDetector
        public void removeCountryListener(ICountryListener iCountryListener) throws RemoteException {
        }
    }

    void addCountryListener(ICountryListener iCountryListener) throws RemoteException;

    Country detectCountry() throws RemoteException;

    void removeCountryListener(ICountryListener iCountryListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ICountryDetector {
        private static final String DESCRIPTOR = "android.location.ICountryDetector";
        static final int TRANSACTION_addCountryListener = 2;
        static final int TRANSACTION_detectCountry = 1;
        static final int TRANSACTION_removeCountryListener = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "detectCountry";
            }
            if (i == 2) {
                return "addCountryListener";
            }
            if (i != 3) {
                return null;
            }
            return "removeCountryListener";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICountryDetector asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICountryDetector)) {
                return (ICountryDetector) iInterfaceQueryLocalInterface;
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
                Country countryDetectCountry = detectCountry();
                parcel2.writeNoException();
                if (countryDetectCountry != null) {
                    parcel2.writeInt(1);
                    countryDetectCountry.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                addCountryListener(ICountryListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
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
            removeCountryListener(ICountryListener.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ICountryDetector {
            public static ICountryDetector sDefaultImpl;
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

            @Override // android.location.ICountryDetector
            public Country detectCountry() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().detectCountry();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Country.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ICountryDetector
            public void addCountryListener(ICountryListener iCountryListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCountryListener != null ? iCountryListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCountryListener(iCountryListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ICountryDetector
            public void removeCountryListener(ICountryListener iCountryListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCountryListener != null ? iCountryListener.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCountryListener(iCountryListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICountryDetector iCountryDetector) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCountryDetector == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCountryDetector;
            return true;
        }

        public static ICountryDetector getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
