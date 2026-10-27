package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IExpirationDateCallBack extends IInterface {

    public static class Default implements IExpirationDateCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IExpirationDateCallBack
        public void callBack(int i, long j, long j2) throws RemoteException {
        }
    }

    void callBack(int i, long j, long j2) throws RemoteException;

    public static abstract class Stub extends Binder implements IExpirationDateCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IExpirationDateCallBack";
        static final int TRANSACTION_callBack = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExpirationDateCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IExpirationDateCallBack)) {
                return (IExpirationDateCallBack) iInterfaceQueryLocalInterface;
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
            callBack(parcel.readInt(), parcel.readLong(), parcel.readLong());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IExpirationDateCallBack {
            public static IExpirationDateCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IExpirationDateCallBack
            public void callBack(int i, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callBack(i, j, j2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExpirationDateCallBack iExpirationDateCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iExpirationDateCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iExpirationDateCallBack;
            return true;
        }

        public static IExpirationDateCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
