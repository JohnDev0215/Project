package com.byd.car.property;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.car.property.ICarPropertyListener;
import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPropertyService extends IInterface {

    public static class Default implements ICarPropertyService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public Response getProperties(String[] propertiesKeys) throws RemoteException {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public Response getProperty(String propertyKey) throws RemoteException {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public List<CarPropertyConfig> getPropertyConfigs(String[] propertiesKeys) throws RemoteException {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public void registerValueCallback(ICarPropertyListener listener, String[] propertiesKeys) throws RemoteException {
        }

        @Override // com.byd.car.property.ICarPropertyService
        public Status setProperties(CarPropertyValue[] values) throws RemoteException {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public void unregisterValueCallback(ICarPropertyListener listener, String[] propertiesKeys) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements ICarPropertyService {
        private static final String DESCRIPTOR = "com.byd.car.property.ICarPropertyService";
        public static final int TRANSACTION_getProperties = 3;
        public static final int TRANSACTION_getProperty = 2;
        public static final int TRANSACTION_getPropertyConfigs = 4;
        public static final int TRANSACTION_registerValueCallback = 5;
        public static final int TRANSACTION_setProperties = 1;
        public static final int TRANSACTION_unregisterValueCallback = 6;

        public static class Proxy implements ICarPropertyService {
            public static ICarPropertyService sDefaultImpl;
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

            @Override // com.byd.car.property.ICarPropertyService
            public Response getProperties(String[] propertiesKeys) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(propertiesKeys);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProperties(propertiesKeys);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Response.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public Response getProperty(String propertyKey) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(propertyKey);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProperty(propertyKey);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Response.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public List<CarPropertyConfig> getPropertyConfigs(String[] propertiesKeys) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(propertiesKeys);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPropertyConfigs(propertiesKeys);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(CarPropertyConfig.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public void registerValueCallback(ICarPropertyListener listener, String[] propertiesKeys) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    parcelObtain.writeStringArray(propertiesKeys);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerValueCallback(listener, propertiesKeys);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public Status setProperties(CarPropertyValue[] values) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(values, 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setProperties(values);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Status.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public void unregisterValueCallback(ICarPropertyListener listener, String[] propertiesKeys) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    parcelObtain.writeStringArray(propertiesKeys);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterValueCallback(listener, propertiesKeys);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPropertyService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarPropertyService)) ? new Proxy(obj) : (ICarPropertyService) iInterfaceQueryLocalInterface;
        }

        public static ICarPropertyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarPropertyService impl) {
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
                    Status properties = setProperties((CarPropertyValue[]) data.createTypedArray(CarPropertyValue.CREATOR));
                    reply.writeNoException();
                    if (properties != null) {
                        reply.writeInt(1);
                        properties.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    Response property = getProperty(data.readString());
                    reply.writeNoException();
                    if (property != null) {
                        reply.writeInt(1);
                        property.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    Response properties2 = getProperties(data.createStringArray());
                    reply.writeNoException();
                    if (properties2 != null) {
                        reply.writeInt(1);
                        properties2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    List<CarPropertyConfig> propertyConfigs = getPropertyConfigs(data.createStringArray());
                    reply.writeNoException();
                    reply.writeTypedList(propertyConfigs);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    registerValueCallback(ICarPropertyListener.Stub.asInterface(data.readStrongBinder()), data.createStringArray());
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterValueCallback(ICarPropertyListener.Stub.asInterface(data.readStrongBinder()), data.createStringArray());
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    Response getProperties(String[] propertiesKeys) throws RemoteException;

    Response getProperty(String propertyKey) throws RemoteException;

    List<CarPropertyConfig> getPropertyConfigs(String[] propertiesKeys) throws RemoteException;

    void registerValueCallback(ICarPropertyListener listener, String[] propertiesKeys) throws RemoteException;

    Status setProperties(CarPropertyValue[] values) throws RemoteException;

    void unregisterValueCallback(ICarPropertyListener listener, String[] propertiesKeys) throws RemoteException;
}
