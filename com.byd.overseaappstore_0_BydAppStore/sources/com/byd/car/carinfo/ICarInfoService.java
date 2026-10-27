package com.byd.car.carinfo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarInfoService extends IInterface {

    public static class Default implements ICarInfoService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getBrand() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public CarBodyConfig getCarBodyConfig() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getCarType(int i) throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public int getDriverSeat() throws RemoteException {
            return 0;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getIoTCardNumber() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getManufactor() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getMcuVersion() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getSerialNumber() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getSocVersion() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getVehicleIccid() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getVehicleType() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.carinfo.ICarInfoService
        public String getVehicleVin() throws RemoteException {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarInfoService {
        private static final String DESCRIPTOR = "com.byd.car.carinfo.ICarInfoService";
        public static final int TRANSACTION_getBrand = 7;
        public static final int TRANSACTION_getCarBodyConfig = 3;
        public static final int TRANSACTION_getCarType = 4;
        public static final int TRANSACTION_getDriverSeat = 1;
        public static final int TRANSACTION_getIoTCardNumber = 6;
        public static final int TRANSACTION_getManufactor = 8;
        public static final int TRANSACTION_getMcuVersion = 10;
        public static final int TRANSACTION_getSerialNumber = 5;
        public static final int TRANSACTION_getSocVersion = 11;
        public static final int TRANSACTION_getVehicleIccid = 12;
        public static final int TRANSACTION_getVehicleType = 2;
        public static final int TRANSACTION_getVehicleVin = 9;

        public static class Proxy implements ICarInfoService {
            public static ICarInfoService sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getBrand() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getBrand();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public CarBodyConfig getCarBodyConfig() throws RemoteException {
                CarBodyConfig carBodyConfigCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        carBodyConfigCreateFromParcel = parcelObtain2.readInt() != 0 ? CarBodyConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        carBodyConfigCreateFromParcel = Stub.getDefaultImpl().getCarBodyConfig();
                    }
                    return carBodyConfigCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getCarType(int i) throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getCarType(i);
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public int getDriverSeat() throws RemoteException {
                int driverSeat;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        driverSeat = parcelObtain2.readInt();
                    } else {
                        driverSeat = Stub.getDefaultImpl().getDriverSeat();
                    }
                    return driverSeat;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getIoTCardNumber() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getIoTCardNumber();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getManufactor() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getManufactor();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getMcuVersion() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getMcuVersion();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getSerialNumber() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getSerialNumber();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getSocVersion() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getSocVersion();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getVehicleIccid() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getVehicleIccid();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getVehicleType() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getVehicleType();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getVehicleVin() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getVehicleVin();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarInfoService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarInfoService)) ? new Proxy(iBinder) : (ICarInfoService) iInterfaceQueryLocalInterface;
        }

        public static ICarInfoService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarInfoService iCarInfoService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarInfoService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarInfoService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int driverSeat = getDriverSeat();
                    parcel2.writeNoException();
                    parcel2.writeInt(driverSeat);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String vehicleType = getVehicleType();
                    parcel2.writeNoException();
                    parcel2.writeString(vehicleType);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    CarBodyConfig carBodyConfig = getCarBodyConfig();
                    parcel2.writeNoException();
                    if (carBodyConfig != null) {
                        parcel2.writeInt(1);
                        carBodyConfig.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String carType = getCarType(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(carType);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String serialNumber = getSerialNumber();
                    parcel2.writeNoException();
                    parcel2.writeString(serialNumber);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String ioTCardNumber = getIoTCardNumber();
                    parcel2.writeNoException();
                    parcel2.writeString(ioTCardNumber);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String brand = getBrand();
                    parcel2.writeNoException();
                    parcel2.writeString(brand);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String manufactor = getManufactor();
                    parcel2.writeNoException();
                    parcel2.writeString(manufactor);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String vehicleVin = getVehicleVin();
                    parcel2.writeNoException();
                    parcel2.writeString(vehicleVin);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String mcuVersion = getMcuVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(mcuVersion);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String socVersion = getSocVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(socVersion);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String vehicleIccid = getVehicleIccid();
                    parcel2.writeNoException();
                    parcel2.writeString(vehicleIccid);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String getBrand() throws RemoteException;

    CarBodyConfig getCarBodyConfig() throws RemoteException;

    String getCarType(int i) throws RemoteException;

    int getDriverSeat() throws RemoteException;

    String getIoTCardNumber() throws RemoteException;

    String getManufactor() throws RemoteException;

    String getMcuVersion() throws RemoteException;

    String getSerialNumber() throws RemoteException;

    String getSocVersion() throws RemoteException;

    String getVehicleIccid() throws RemoteException;

    String getVehicleType() throws RemoteException;

    String getVehicleVin() throws RemoteException;
}
