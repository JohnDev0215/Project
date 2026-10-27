package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ISpecialPoiNaviCallback extends IInterface {

    public static class Default implements ISpecialPoiNaviCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.ISpecialPoiNaviCallback
        public void callback(int i, RoutePlanInfo routePlanInfo) throws RemoteException {
        }
    }

    void callback(int i, RoutePlanInfo routePlanInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements ISpecialPoiNaviCallback {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.ISpecialPoiNaviCallback";
        static final int TRANSACTION_callback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISpecialPoiNaviCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISpecialPoiNaviCallback)) {
                return (ISpecialPoiNaviCallback) iInterfaceQueryLocalInterface;
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
            callback(parcel.readInt(), parcel.readInt() != 0 ? RoutePlanInfo.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISpecialPoiNaviCallback {
            public static ISpecialPoiNaviCallback sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.ISpecialPoiNaviCallback
            public void callback(int i, RoutePlanInfo routePlanInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (routePlanInfo != null) {
                        parcelObtain.writeInt(1);
                        routePlanInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callback(i, routePlanInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISpecialPoiNaviCallback iSpecialPoiNaviCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSpecialPoiNaviCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSpecialPoiNaviCallback;
            return true;
        }

        public static ISpecialPoiNaviCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
