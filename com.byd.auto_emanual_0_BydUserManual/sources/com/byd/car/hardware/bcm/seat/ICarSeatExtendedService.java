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
        public Result getSeatExtendedConfig(int areaId, long seatExtended) throws RemoteException {
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

            public Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.car.hardware.bcm.seat.ICarSeatExtendedService
            public Result getSeatExtendedConfig(int areaId, long seatExtended) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(areaId);
                    parcelObtain.writeLong(seatExtended);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeatExtendedConfig(areaId, seatExtended);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.hardware.bcm.seat.ICarSeatExtendedService
            public Result getSeatLayout() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeatLayout();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarSeatExtendedService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarSeatExtendedService)) ? new Proxy(obj) : (ICarSeatExtendedService) iInterfaceQueryLocalInterface;
        }

        public static ICarSeatExtendedService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarSeatExtendedService impl) {
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
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                Result seatLayout = getSeatLayout();
                reply.writeNoException();
                if (seatLayout != null) {
                    reply.writeInt(1);
                    seatLayout.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            }
            if (code != 2) {
                if (code != 1598968902) {
                    return super.onTransact(code, data, reply, flags);
                }
                reply.writeString(DESCRIPTOR);
                return true;
            }
            data.enforceInterface(DESCRIPTOR);
            Result seatExtendedConfig = getSeatExtendedConfig(data.readInt(), data.readLong());
            reply.writeNoException();
            if (seatExtendedConfig != null) {
                reply.writeInt(1);
                seatExtendedConfig.writeToParcel(reply, 1);
            } else {
                reply.writeInt(0);
            }
            return true;
        }
    }

    Result getSeatExtendedConfig(int areaId, long seatExtended) throws RemoteException;

    Result getSeatLayout() throws RemoteException;
}
