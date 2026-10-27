package android.car.hardware;

import android.car.hardware.ICarSensorEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarSensor extends IInterface {

    public static class Default implements ICarSensor {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.hardware.ICarSensor
        public CarSensorEvent getLatestSensorEvent(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.ICarSensor
        public CarSensorConfig getSensorConfig(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.ICarSensor
        public int[] getSupportedSensors() throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.ICarSensor
        public boolean registerOrUpdateSensorListener(int i, int i2, ICarSensorEventListener iCarSensorEventListener) throws RemoteException {
            return false;
        }

        @Override // android.car.hardware.ICarSensor
        public void unregisterSensorListener(int i, ICarSensorEventListener iCarSensorEventListener) throws RemoteException {
        }
    }

    CarSensorEvent getLatestSensorEvent(int i) throws RemoteException;

    CarSensorConfig getSensorConfig(int i) throws RemoteException;

    int[] getSupportedSensors() throws RemoteException;

    boolean registerOrUpdateSensorListener(int i, int i2, ICarSensorEventListener iCarSensorEventListener) throws RemoteException;

    void unregisterSensorListener(int i, ICarSensorEventListener iCarSensorEventListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarSensor {
        private static final String DESCRIPTOR = "android.car.hardware.ICarSensor";
        static final int TRANSACTION_getLatestSensorEvent = 3;
        static final int TRANSACTION_getSensorConfig = 5;
        static final int TRANSACTION_getSupportedSensors = 1;
        static final int TRANSACTION_registerOrUpdateSensorListener = 2;
        static final int TRANSACTION_unregisterSensorListener = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarSensor asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarSensor)) {
                return (ICarSensor) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int[] supportedSensors = getSupportedSensors();
                parcel2.writeNoException();
                parcel2.writeIntArray(supportedSensors);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zRegisterOrUpdateSensorListener = registerOrUpdateSensorListener(parcel.readInt(), parcel.readInt(), ICarSensorEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zRegisterOrUpdateSensorListener ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                CarSensorEvent latestSensorEvent = getLatestSensorEvent(parcel.readInt());
                parcel2.writeNoException();
                if (latestSensorEvent != null) {
                    parcel2.writeInt(1);
                    latestSensorEvent.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterSensorListener(parcel.readInt(), ICarSensorEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
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
            CarSensorConfig sensorConfig = getSensorConfig(parcel.readInt());
            parcel2.writeNoException();
            if (sensorConfig != null) {
                parcel2.writeInt(1);
                sensorConfig.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements ICarSensor {
            public static ICarSensor sDefaultImpl;
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

            @Override // android.car.hardware.ICarSensor
            public int[] getSupportedSensors() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedSensors();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public boolean registerOrUpdateSensorListener(int i, int i2, ICarSensorEventListener iCarSensorEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iCarSensorEventListener != null ? iCarSensorEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerOrUpdateSensorListener(i, i2, iCarSensorEventListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public CarSensorEvent getLatestSensorEvent(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestSensorEvent(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CarSensorEvent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public void unregisterSensorListener(int i, ICarSensorEventListener iCarSensorEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCarSensorEventListener != null ? iCarSensorEventListener.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSensorListener(i, iCarSensorEventListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public CarSensorConfig getSensorConfig(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSensorConfig(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CarSensorConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarSensor iCarSensor) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarSensor == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarSensor;
            return true;
        }

        public static ICarSensor getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
