package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IGetGuideInfoCallBack extends IInterface {

    public static class Default implements IGetGuideInfoCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.IGetGuideInfoCallBack
        public void getGuideInfoResult(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, float f, int i7, int i8, int i9, String str3, int i10, int i11, int i12, int i13) throws RemoteException {
        }
    }

    void getGuideInfoResult(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, float f, int i7, int i8, int i9, String str3, int i10, int i11, int i12, int i13) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetGuideInfoCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.IGetGuideInfoCallBack";
        static final int TRANSACTION_getGuideInfoResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGetGuideInfoCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGetGuideInfoCallBack)) {
                return (IGetGuideInfoCallBack) iInterfaceQueryLocalInterface;
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
            getGuideInfoResult(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readFloat(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IGetGuideInfoCallBack {
            public static IGetGuideInfoCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.IGetGuideInfoCallBack
            public void getGuideInfoResult(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, float f, int i7, int i8, int i9, String str3, int i10, int i11, int i12, int i13) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeInt(i7);
                    parcelObtain.writeInt(i8);
                    parcelObtain.writeInt(i9);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    parcelObtain.writeInt(i13);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getGuideInfoResult(i, i2, i3, i4, i5, i6, str, str2, f, i7, i8, i9, str3, i10, i11, i12, i13);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetGuideInfoCallBack iGetGuideInfoCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGetGuideInfoCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGetGuideInfoCallBack;
            return true;
        }

        public static IGetGuideInfoCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
