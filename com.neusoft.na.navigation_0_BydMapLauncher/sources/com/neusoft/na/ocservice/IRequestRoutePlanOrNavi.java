package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IRequestRoutePlanOrNavi extends IInterface {

    public static class Default implements IRequestRoutePlanOrNavi {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IRequestRoutePlanOrNavi
        public void requestRoutePlanOrNaviResult(int i, List<RouteInfo> list) throws RemoteException {
        }
    }

    void requestRoutePlanOrNaviResult(int i, List<RouteInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IRequestRoutePlanOrNavi {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IRequestRoutePlanOrNavi";
        static final int TRANSACTION_requestRoutePlanOrNaviResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRequestRoutePlanOrNavi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRequestRoutePlanOrNavi)) {
                return (IRequestRoutePlanOrNavi) iInterfaceQueryLocalInterface;
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
            requestRoutePlanOrNaviResult(parcel.readInt(), parcel.createTypedArrayList(RouteInfo.CREATOR));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IRequestRoutePlanOrNavi {
            public static IRequestRoutePlanOrNavi sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IRequestRoutePlanOrNavi
            public void requestRoutePlanOrNaviResult(int i, List<RouteInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestRoutePlanOrNaviResult(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRequestRoutePlanOrNavi == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRequestRoutePlanOrNavi;
            return true;
        }

        public static IRequestRoutePlanOrNavi getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
