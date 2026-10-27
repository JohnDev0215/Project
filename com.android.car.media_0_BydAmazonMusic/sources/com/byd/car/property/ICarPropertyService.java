package com.byd.car.property;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.byd.car.property.ICarPropertyListener;
import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPropertyService extends IInterface {

    public static class Default implements ICarPropertyService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public Response getProperties(String[] strArr) {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public Response getProperty(String str) {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public List<CarPropertyConfig> getPropertyConfigs(String[] strArr) {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public void registerValueCallback(ICarPropertyListener iCarPropertyListener, String[] strArr) {
        }

        @Override // com.byd.car.property.ICarPropertyService
        public Status setProperties(CarPropertyValue[] carPropertyValueArr) {
            return null;
        }

        @Override // com.byd.car.property.ICarPropertyService
        public void unregisterValueCallback(ICarPropertyListener iCarPropertyListener, String[] strArr) {
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

            @Override // com.byd.car.property.ICarPropertyService
            public Response getProperties(String[] strArr) {
                Response responseCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        responseCreateFromParcel = parcelObtain2.readInt() != 0 ? Response.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        responseCreateFromParcel = Stub.getDefaultImpl().getProperties(strArr);
                    }
                    return responseCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public Response getProperty(String str) {
                Response responseCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        responseCreateFromParcel = parcelObtain2.readInt() != 0 ? Response.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        responseCreateFromParcel = Stub.getDefaultImpl().getProperty(str);
                    }
                    return responseCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public List<CarPropertyConfig> getPropertyConfigs(String[] strArr) {
                List<CarPropertyConfig> listCreateTypedArrayList;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        listCreateTypedArrayList = parcelObtain2.createTypedArrayList(CarPropertyConfig.CREATOR);
                    } else {
                        listCreateTypedArrayList = Stub.getDefaultImpl().getPropertyConfigs(strArr);
                    }
                    return listCreateTypedArrayList;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public void registerValueCallback(ICarPropertyListener iCarPropertyListener, String[] strArr) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCarPropertyListener != null ? iCarPropertyListener.asBinder() : null);
                    parcelObtain.writeStringArray(strArr);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerValueCallback(iCarPropertyListener, strArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public Status setProperties(CarPropertyValue[] carPropertyValueArr) {
                Status statusCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(carPropertyValueArr, 0);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        statusCreateFromParcel = parcelObtain2.readInt() != 0 ? Status.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        statusCreateFromParcel = Stub.getDefaultImpl().setProperties(carPropertyValueArr);
                    }
                    return statusCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.property.ICarPropertyService
            public void unregisterValueCallback(ICarPropertyListener iCarPropertyListener, String[] strArr) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCarPropertyListener != null ? iCarPropertyListener.asBinder() : null);
                    parcelObtain.writeStringArray(strArr);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterValueCallback(iCarPropertyListener, strArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPropertyService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarPropertyService)) ? new Proxy(iBinder) : (ICarPropertyService) iInterfaceQueryLocalInterface;
        }

        public static ICarPropertyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarPropertyService iCarPropertyService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarPropertyService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarPropertyService;
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
                    Status properties = setProperties((CarPropertyValue[]) parcel.createTypedArray(CarPropertyValue.CREATOR));
                    parcel2.writeNoException();
                    if (properties != null) {
                        parcel2.writeInt(1);
                        properties.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    Response property = getProperty(parcel.readString());
                    parcel2.writeNoException();
                    if (property != null) {
                        parcel2.writeInt(1);
                        property.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    Response properties2 = getProperties(parcel.createStringArray());
                    parcel2.writeNoException();
                    if (properties2 != null) {
                        parcel2.writeInt(1);
                        properties2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CarPropertyConfig> propertyConfigs = getPropertyConfigs(parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(propertyConfigs);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerValueCallback(ICarPropertyListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterValueCallback(ICarPropertyListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Response getProperties(String[] strArr);

    Response getProperty(String str);

    List<CarPropertyConfig> getPropertyConfigs(String[] strArr);

    void registerValueCallback(ICarPropertyListener iCarPropertyListener, String[] strArr);

    Status setProperties(CarPropertyValue[] carPropertyValueArr);

    void unregisterValueCallback(ICarPropertyListener iCarPropertyListener, String[] strArr);
}
