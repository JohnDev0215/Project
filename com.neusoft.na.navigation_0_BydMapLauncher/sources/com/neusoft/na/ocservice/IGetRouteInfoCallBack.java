package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IGetRouteInfoCallBack extends IInterface {

    public static class Default implements IGetRouteInfoCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IGetRouteInfoCallBack
        public void getRouteInfoResult(int i, PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list, List<PoiInfo> list2) throws RemoteException {
        }
    }

    void getRouteInfoResult(int i, PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list, List<PoiInfo> list2) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetRouteInfoCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IGetRouteInfoCallBack";
        static final int TRANSACTION_getRouteInfoResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGetRouteInfoCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGetRouteInfoCallBack)) {
                return (IGetRouteInfoCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            getRouteInfoResult(parcel.readInt(), parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(RouteInfo.CREATOR), parcel.createTypedArrayList(PoiInfo.CREATOR));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IGetRouteInfoCallBack {
            public static IGetRouteInfoCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IGetRouteInfoCallBack
            public void getRouteInfoResult(int i, PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list, List<PoiInfo> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (poiInfo != null) {
                        parcelObtain.writeInt(1);
                        poiInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (poiInfo2 != null) {
                        parcelObtain.writeInt(1);
                        poiInfo2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeTypedList(list2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getRouteInfoResult(i, poiInfo, poiInfo2, list, list2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetRouteInfoCallBack iGetRouteInfoCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGetRouteInfoCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGetRouteInfoCallBack;
            return true;
        }

        public static IGetRouteInfoCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
