package com.vivid.spotify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes5.dex */
public interface IRequestCallBack extends IInterface {
    public static final int ERROR_CURRENT_MEDIA_IS_NULL = 4;
    public static final int ERROR_MEDIA_IS_COLLECTED = 9;
    public static final int ERROR_MEDIA_IS_NOT_COLLECTED = 10;
    public static final int ERROR_MEDIA_UNSUPPORT_FAVORITE = 6;
    public static final int ERROR_NO_NEXT_SONG = 8;
    public static final int ERROR_NO_PREV_SONG = 13;
    public static final int ERROR_REPEAT_SET_MODE = 11;
    public static final int ERROR_REQUEST_TIMEOUT = 7;
    public static final int ERROR_SERVICE_DISCONNECTED = 1;
    public static final int ERROR_UNKNOW = 5;
    public static final int ERROR_UNSUPPORT_MODE = 3;
    public static final int ERROR_UNSUPPORT_OPERATION = 12;
    public static final int ERROR_USER_NOT_LOGGED_IN = 2;

    public static class Default implements IRequestCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.vivid.spotify.IRequestCallBack
        public void onError(int i) throws RemoteException {
        }

        @Override // com.vivid.spotify.IRequestCallBack
        public void onSuccess() throws RemoteException {
        }
    }

    void onError(int i) throws RemoteException;

    void onSuccess() throws RemoteException;

    public static abstract class Stub extends Binder implements IRequestCallBack {
        private static final String DESCRIPTOR = "com.vivid.spotify.IRequestCallBack";
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRequestCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRequestCallBack)) {
                return (IRequestCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSuccess();
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onError(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IRequestCallBack {
            public static IRequestCallBack sDefaultImpl;
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

            @Override // com.vivid.spotify.IRequestCallBack
            public void onSuccess() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.IRequestCallBack
            public void onError(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRequestCallBack iRequestCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRequestCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRequestCallBack;
            return true;
        }

        public static IRequestCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
