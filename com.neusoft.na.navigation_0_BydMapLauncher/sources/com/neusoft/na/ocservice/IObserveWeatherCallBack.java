package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IObserveWeatherCallBack extends IInterface {

    public static class Default implements IObserveWeatherCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IObserveWeatherCallBack
        public void callBack(int i, int i2, ObserveWeatherInfo observeWeatherInfo) throws RemoteException {
        }
    }

    void callBack(int i, int i2, ObserveWeatherInfo observeWeatherInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IObserveWeatherCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IObserveWeatherCallBack";
        static final int TRANSACTION_callBack = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IObserveWeatherCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IObserveWeatherCallBack)) {
                return (IObserveWeatherCallBack) iInterfaceQueryLocalInterface;
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
            callBack(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? ObserveWeatherInfo.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IObserveWeatherCallBack {
            public static IObserveWeatherCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IObserveWeatherCallBack
            public void callBack(int i, int i2, ObserveWeatherInfo observeWeatherInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (observeWeatherInfo != null) {
                        parcelObtain.writeInt(1);
                        observeWeatherInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callBack(i, i2, observeWeatherInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IObserveWeatherCallBack iObserveWeatherCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iObserveWeatherCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iObserveWeatherCallBack;
            return true;
        }

        public static IObserveWeatherCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
