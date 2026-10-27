package android.car.hardware.property;

import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.ICarPropertyEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarProperty extends IInterface {

    public static class Default implements ICarProperty {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.hardware.property.ICarProperty
        public CarPropertyValue getProperty(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.property.ICarProperty
        public List<CarPropertyConfig> getPropertyList() throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.property.ICarProperty
        public String getReadPermission(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.property.ICarProperty
        public String getWritePermission(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.property.ICarProperty
        public void registerListener(int i, float f, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException {
        }

        @Override // android.car.hardware.property.ICarProperty
        public void setProperty(CarPropertyValue carPropertyValue, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException {
        }

        @Override // android.car.hardware.property.ICarProperty
        public void unregisterListener(int i, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException {
        }
    }

    CarPropertyValue getProperty(int i, int i2) throws RemoteException;

    List<CarPropertyConfig> getPropertyList() throws RemoteException;

    String getReadPermission(int i) throws RemoteException;

    String getWritePermission(int i) throws RemoteException;

    void registerListener(int i, float f, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException;

    void setProperty(CarPropertyValue carPropertyValue, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException;

    void unregisterListener(int i, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarProperty {
        private static final String DESCRIPTOR = "android.car.hardware.property.ICarProperty";
        static final int TRANSACTION_getProperty = 4;
        static final int TRANSACTION_getPropertyList = 3;
        static final int TRANSACTION_getReadPermission = 6;
        static final int TRANSACTION_getWritePermission = 7;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_setProperty = 5;
        static final int TRANSACTION_unregisterListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarProperty asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarProperty)) {
                return (ICarProperty) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
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
                    registerListener(parcel.readInt(), parcel.readFloat(), ICarPropertyEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(parcel.readInt(), ICarPropertyEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CarPropertyConfig> propertyList = getPropertyList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(propertyList);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    CarPropertyValue property = getProperty(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (property != null) {
                        parcel2.writeInt(1);
                        property.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setProperty(parcel.readInt() != 0 ? CarPropertyValue.CREATOR.createFromParcel(parcel) : null, ICarPropertyEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readPermission = getReadPermission(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(readPermission);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String writePermission = getWritePermission(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(writePermission);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICarProperty {
            public static ICarProperty sDefaultImpl;
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

            @Override // android.car.hardware.property.ICarProperty
            public void registerListener(int i, float f, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeStrongBinder(iCarPropertyEventListener != null ? iCarPropertyEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(i, f, iCarPropertyEventListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public void unregisterListener(int i, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCarPropertyEventListener != null ? iCarPropertyEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(i, iCarPropertyEventListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public List<CarPropertyConfig> getPropertyList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPropertyList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(CarPropertyConfig.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public CarPropertyValue getProperty(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProperty(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CarPropertyValue.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public void setProperty(CarPropertyValue carPropertyValue, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (carPropertyValue != null) {
                        parcelObtain.writeInt(1);
                        carPropertyValue.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iCarPropertyEventListener != null ? iCarPropertyEventListener.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setProperty(carPropertyValue, iCarPropertyEventListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public String getReadPermission(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReadPermission(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public String getWritePermission(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWritePermission(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarProperty iCarProperty) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarProperty == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarProperty;
            return true;
        }

        public static ICarProperty getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
