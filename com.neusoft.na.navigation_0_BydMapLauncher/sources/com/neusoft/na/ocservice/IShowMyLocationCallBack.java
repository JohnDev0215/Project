package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IShowMyLocationCallBack extends IInterface {

    public static class Default implements IShowMyLocationCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IShowMyLocationCallBack
        public void showMyLocationResult(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, double d, double d2) throws RemoteException {
        }
    }

    void showMyLocationResult(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, double d, double d2) throws RemoteException;

    public static abstract class Stub extends Binder implements IShowMyLocationCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IShowMyLocationCallBack";
        static final int TRANSACTION_showMyLocationResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IShowMyLocationCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IShowMyLocationCallBack)) {
                return (IShowMyLocationCallBack) iInterfaceQueryLocalInterface;
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
            showMyLocationResult(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IShowMyLocationCallBack {
            public static IShowMyLocationCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IShowMyLocationCallBack
            public void showMyLocationResult(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, double d, double d2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeString(str5);
                    parcelObtain.writeString(str6);
                    parcelObtain.writeString(str7);
                    parcelObtain.writeString(str8);
                    parcelObtain.writeString(str9);
                    parcelObtain.writeDouble(d);
                    parcelObtain.writeDouble(d2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showMyLocationResult(i, str, str2, str3, str4, str5, str6, str7, str8, str9, d, d2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IShowMyLocationCallBack iShowMyLocationCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iShowMyLocationCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iShowMyLocationCallBack;
            return true;
        }

        public static IShowMyLocationCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
