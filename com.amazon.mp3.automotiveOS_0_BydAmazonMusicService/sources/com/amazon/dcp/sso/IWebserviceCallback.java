package com.amazon.dcp.sso;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes2.dex */
public interface IWebserviceCallback extends IInterface {

    /* JADX INFO: compiled from: DCP */
    public static abstract class Stub extends Binder implements IWebserviceCallback {
        private static final String DESCRIPTOR = "com.amazon.dcp.sso.IWebserviceCallback";
        public static final int TRANSACTION_onAuthenticationFailed = 3;
        public static final int TRANSACTION_onBadResponse = 4;
        public static final int TRANSACTION_onInvalidRequest = 5;
        public static final int TRANSACTION_onNetworkError = 2;
        public static final int TRANSACTION_onResponseReceived = 1;

        /* JADX INFO: compiled from: DCP */
        public static class Proxy implements IWebserviceCallback {
            public static IWebserviceCallback sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.dcp.sso.IWebserviceCallback
            public void onAuthenticationFailed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onAuthenticationFailed();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IWebserviceCallback
            public void onBadResponse() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onBadResponse();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IWebserviceCallback
            public void onInvalidRequest() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onInvalidRequest();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IWebserviceCallback
            public void onNetworkError() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onNetworkError();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IWebserviceCallback
            public void onResponseReceived(long j, Map map, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeMap(map);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onResponseReceived(j, map, bArr);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWebserviceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IWebserviceCallback)) ? new Proxy(iBinder) : (IWebserviceCallback) iInterfaceQueryLocalInterface;
        }

        public static IWebserviceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IWebserviceCallback iWebserviceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWebserviceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWebserviceCallback;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onResponseReceived(parcel.readLong(), parcel.readHashMap(getClass().getClassLoader()), parcel.createByteArray());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onNetworkError();
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onAuthenticationFailed();
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onBadResponse();
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onInvalidRequest();
            parcel2.writeNoException();
            return true;
        }
    }

    void onAuthenticationFailed() throws RemoteException;

    void onBadResponse() throws RemoteException;

    void onInvalidRequest() throws RemoteException;

    void onNetworkError() throws RemoteException;

    void onResponseReceived(long j, Map map, byte[] bArr) throws RemoteException;
}
