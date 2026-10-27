package com.byd.car.cabin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarCabinService extends IInterface {

    public static class Default implements ICarCabinService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public int getDeviceType() {
            return 0;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public long getPlatformInfo() {
            return 0L;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public int getScreenAreaWidth(int i, int i2) {
            return 0;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public String getUIName() {
            return null;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public int getUIStyle() {
            return 0;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public boolean isStyle(int i) {
            return false;
        }
    }

    public static abstract class Stub extends Binder implements ICarCabinService {
        private static final String DESCRIPTOR = "com.byd.car.cabin.ICarCabinService";
        public static final int TRANSACTION_getDeviceType = 2;
        public static final int TRANSACTION_getPlatformInfo = 1;
        public static final int TRANSACTION_getScreenAreaWidth = 6;
        public static final int TRANSACTION_getUIName = 5;
        public static final int TRANSACTION_getUIStyle = 3;
        public static final int TRANSACTION_isStyle = 4;

        public static class Proxy implements ICarCabinService {
            public static ICarCabinService sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public int getDeviceType() {
                int deviceType;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        deviceType = parcelObtain2.readInt();
                    } else {
                        deviceType = Stub.getDefaultImpl().getDeviceType();
                    }
                    return deviceType;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public long getPlatformInfo() {
                long platformInfo;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        platformInfo = parcelObtain2.readLong();
                    } else {
                        platformInfo = Stub.getDefaultImpl().getPlatformInfo();
                    }
                    return platformInfo;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public int getScreenAreaWidth(int i, int i2) {
                int screenAreaWidth;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        screenAreaWidth = parcelObtain2.readInt();
                    } else {
                        screenAreaWidth = Stub.getDefaultImpl().getScreenAreaWidth(i, i2);
                    }
                    return screenAreaWidth;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public String getUIName() {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = Stub.getDefaultImpl().getUIName();
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public int getUIStyle() {
                int uIStyle;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        uIStyle = parcelObtain2.readInt();
                    } else {
                        uIStyle = Stub.getDefaultImpl().getUIStyle();
                    }
                    return uIStyle;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public boolean isStyle(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isStyle(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarCabinService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarCabinService)) ? new Proxy(iBinder) : (ICarCabinService) iInterfaceQueryLocalInterface;
        }

        public static ICarCabinService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarCabinService iCarCabinService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarCabinService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarCabinService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    long platformInfo = getPlatformInfo();
                    parcel2.writeNoException();
                    parcel2.writeLong(platformInfo);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int deviceType = getDeviceType();
                    parcel2.writeNoException();
                    parcel2.writeInt(deviceType);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int uIStyle = getUIStyle();
                    parcel2.writeNoException();
                    parcel2.writeInt(uIStyle);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsStyle = isStyle(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsStyle ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String uIName = getUIName();
                    parcel2.writeNoException();
                    parcel2.writeString(uIName);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int screenAreaWidth = getScreenAreaWidth(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(screenAreaWidth);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int getDeviceType();

    long getPlatformInfo();

    int getScreenAreaWidth(int i, int i2);

    String getUIName();

    int getUIStyle();

    boolean isStyle(int i);
}
