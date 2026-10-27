package com.byd.car.cabin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarCabinService extends IInterface {

    public static class Default implements ICarCabinService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public int getDeviceType() throws RemoteException {
            return 0;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public long getPlatformInfo() throws RemoteException {
            return 0L;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public int getScreenAreaWidth(int device, int area) throws RemoteException {
            return 0;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public String getUIName() throws RemoteException {
            return null;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public int getUIStyle() throws RemoteException {
            return 0;
        }

        @Override // com.byd.car.cabin.ICarCabinService
        public boolean isStyle(int style) throws RemoteException {
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

            public Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public int getDeviceType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceType();
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

            @Override // com.byd.car.cabin.ICarCabinService
            public long getPlatformInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlatformInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public int getScreenAreaWidth(int device, int area) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(device);
                    parcelObtain.writeInt(area);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScreenAreaWidth(device, area);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public String getUIName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUIName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public int getUIStyle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUIStyle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.cabin.ICarCabinService
            public boolean isStyle(int style) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(style);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isStyle(style);
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

        public static ICarCabinService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarCabinService)) ? new Proxy(obj) : (ICarCabinService) iInterfaceQueryLocalInterface;
        }

        public static ICarCabinService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarCabinService impl) {
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
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
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

    int getDeviceType() throws RemoteException;

    long getPlatformInfo() throws RemoteException;

    int getScreenAreaWidth(int device, int area) throws RemoteException;

    String getUIName() throws RemoteException;

    int getUIStyle() throws RemoteException;

    boolean isStyle(int style) throws RemoteException;
}
