package com.byd.car.hardware.bcm.seat;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.car.hardware.Result;

/* JADX INFO: loaded from: classes.dex */
public interface ICarSeatExtendedService extends IInterface {

    public static class Default implements ICarSeatExtendedService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.hardware.bcm.seat.ICarSeatExtendedService
        public Result getSeatExtendedConfig(int i, long j) throws RemoteException {
            return null;
        }

        @Override // com.byd.car.hardware.bcm.seat.ICarSeatExtendedService
        public Result getSeatLayout() throws RemoteException {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarSeatExtendedService {
        private static final String DESCRIPTOR = "com.byd.car.hardware.bcm.seat.ICarSeatExtendedService";
        public static final int TRANSACTION_getSeatExtendedConfig = 2;
        public static final int TRANSACTION_getSeatLayout = 1;

        public static class Proxy implements ICarSeatExtendedService {
            public static ICarSeatExtendedService sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.car.hardware.bcm.seat.ICarSeatExtendedService
            public Result getSeatExtendedConfig(int i, long j) throws RemoteException {
                Result resultCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        resultCreateFromParcel = parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        resultCreateFromParcel = Stub.getDefaultImpl().getSeatExtendedConfig(i, j);
                    }
                    return resultCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.hardware.bcm.seat.ICarSeatExtendedService
            public Result getSeatLayout() throws RemoteException {
                Result resultCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        resultCreateFromParcel = parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        resultCreateFromParcel = Stub.getDefaultImpl().getSeatLayout();
                    }
                    return resultCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarSeatExtendedService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarSeatExtendedService)) ? new Proxy(iBinder) : (ICarSeatExtendedService) iInterfaceQueryLocalInterface;
        }

        public static ICarSeatExtendedService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarSeatExtendedService iCarSeatExtendedService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarSeatExtendedService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarSeatExtendedService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                Result seatLayout = getSeatLayout();
                parcel2.writeNoException();
                if (seatLayout != null) {
                    parcel2.writeInt(1);
                    seatLayout.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
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
            Result seatExtendedConfig = getSeatExtendedConfig(parcel.readInt(), parcel.readLong());
            parcel2.writeNoException();
            if (seatExtendedConfig != null) {
                parcel2.writeInt(1);
                seatExtendedConfig.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    Result getSeatExtendedConfig(int i, long j) throws RemoteException;

    Result getSeatLayout() throws RemoteException;
}
