package com.byd.car.locale;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarLocaleService extends IInterface {

    public static class Default implements ICarLocaleService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.locale.ICarLocaleService
        public int getCountryCode() {
            return 0;
        }

        @Override // com.byd.car.locale.ICarLocaleService
        public String getCountryDomain() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarLocaleService {
        private static final String DESCRIPTOR = "com.byd.car.locale.ICarLocaleService";
        public static final int TRANSACTION_getCountryCode = 2;
        public static final int TRANSACTION_getCountryDomain = 1;

        public static class Proxy implements ICarLocaleService {
            public static ICarLocaleService sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.locale.ICarLocaleService
            public int getCountryCode() {
                int countryCode;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        countryCode = parcelObtain2.readInt();
                    } else {
                        countryCode = Stub.getDefaultImpl().getCountryCode();
                    }
                    return countryCode;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.locale.ICarLocaleService
            public String getCountryDomain() {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getCountryDomain();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarLocaleService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarLocaleService)) ? new Proxy(iBinder) : (ICarLocaleService) iInterfaceQueryLocalInterface;
        }

        public static ICarLocaleService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarLocaleService iCarLocaleService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarLocaleService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarLocaleService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String countryDomain = getCountryDomain();
                parcel2.writeNoException();
                parcel2.writeString(countryDomain);
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            int countryCode = getCountryCode();
            parcel2.writeNoException();
            parcel2.writeInt(countryCode);
            return true;
        }
    }

    int getCountryCode();

    String getCountryDomain();
}
