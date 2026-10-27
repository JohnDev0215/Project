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
        public String getCarType(int vehicleId) throws RemoteException {
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

            public Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getBrand() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBrand();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public CarBodyConfig getCarBodyConfig() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarBodyConfig();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CarBodyConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getCarType(int vehicleId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(vehicleId);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarType(vehicleId);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public int getDriverSeat() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriverSeat();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
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
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIoTCardNumber();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getManufactor() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getManufactor();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getMcuVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMcuVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getSerialNumber() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSerialNumber();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getSocVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSocVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getVehicleIccid() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleIccid();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getVehicleType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.carinfo.ICarInfoService
            public String getVehicleVin() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleVin();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarInfoService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarInfoService)) ? new Proxy(obj) : (ICarInfoService) iInterfaceQueryLocalInterface;
        }

        public static ICarInfoService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarInfoService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int driverSeat = getDriverSeat();
                    reply.writeNoException();
                    reply.writeInt(driverSeat);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String vehicleType = getVehicleType();
                    reply.writeNoException();
                    reply.writeString(vehicleType);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    CarBodyConfig carBodyConfig = getCarBodyConfig();
                    reply.writeNoException();
                    if (carBodyConfig != null) {
                        reply.writeInt(1);
                        carBodyConfig.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String carType = getCarType(data.readInt());
                    reply.writeNoException();
                    reply.writeString(carType);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String serialNumber = getSerialNumber();
                    reply.writeNoException();
                    reply.writeString(serialNumber);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String ioTCardNumber = getIoTCardNumber();
                    reply.writeNoException();
                    reply.writeString(ioTCardNumber);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String brand = getBrand();
                    reply.writeNoException();
                    reply.writeString(brand);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String manufactor = getManufactor();
                    reply.writeNoException();
                    reply.writeString(manufactor);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String vehicleVin = getVehicleVin();
                    reply.writeNoException();
                    reply.writeString(vehicleVin);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String mcuVersion = getMcuVersion();
                    reply.writeNoException();
                    reply.writeString(mcuVersion);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String socVersion = getSocVersion();
                    reply.writeNoException();
                    reply.writeString(socVersion);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String vehicleIccid = getVehicleIccid();
                    reply.writeNoException();
                    reply.writeString(vehicleIccid);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    String getBrand() throws RemoteException;

    CarBodyConfig getCarBodyConfig() throws RemoteException;

    String getCarType(int vehicleId) throws RemoteException;

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
