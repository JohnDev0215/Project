package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IAddItemToFavoritesCallBack extends IInterface {

    public static class Default implements IAddItemToFavoritesCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IAddItemToFavoritesCallBack
        public void callback(int i, int i2, String str) throws RemoteException {
        }
    }

    void callback(int i, int i2, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IAddItemToFavoritesCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IAddItemToFavoritesCallBack";
        static final int TRANSACTION_callback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAddItemToFavoritesCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAddItemToFavoritesCallBack)) {
                return (IAddItemToFavoritesCallBack) iInterfaceQueryLocalInterface;
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
            callback(parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IAddItemToFavoritesCallBack {
            public static IAddItemToFavoritesCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IAddItemToFavoritesCallBack
            public void callback(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callback(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAddItemToFavoritesCallBack iAddItemToFavoritesCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAddItemToFavoritesCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAddItemToFavoritesCallBack;
            return true;
        }

        public static IAddItemToFavoritesCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
