package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IGetFavoriteListCallBack extends IInterface {

    public static class Default implements IGetFavoriteListCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IGetFavoriteListCallBack
        public void callback(int i, int i2, List<FavoriteItem> list) throws RemoteException {
        }
    }

    void callback(int i, int i2, List<FavoriteItem> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetFavoriteListCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IGetFavoriteListCallBack";
        static final int TRANSACTION_callback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGetFavoriteListCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGetFavoriteListCallBack)) {
                return (IGetFavoriteListCallBack) iInterfaceQueryLocalInterface;
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
            callback(parcel.readInt(), parcel.readInt(), parcel.createTypedArrayList(FavoriteItem.CREATOR));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IGetFavoriteListCallBack {
            public static IGetFavoriteListCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IGetFavoriteListCallBack
            public void callback(int i, int i2, List<FavoriteItem> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callback(i, i2, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetFavoriteListCallBack iGetFavoriteListCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGetFavoriteListCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGetFavoriteListCallBack;
            return true;
        }

        public static IGetFavoriteListCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
