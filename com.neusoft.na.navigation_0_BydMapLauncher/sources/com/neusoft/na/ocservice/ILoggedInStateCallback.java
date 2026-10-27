package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ILoggedInStateCallback extends IInterface {

    public static class Default implements ILoggedInStateCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.ILoggedInStateCallback
        public void callback(boolean z, String str) throws RemoteException {
        }
    }

    void callback(boolean z, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ILoggedInStateCallback {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.ILoggedInStateCallback";
        static final int TRANSACTION_callback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILoggedInStateCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILoggedInStateCallback)) {
                return (ILoggedInStateCallback) iInterfaceQueryLocalInterface;
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
            callback(parcel.readInt() != 0, parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ILoggedInStateCallback {
            public static ILoggedInStateCallback sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.ILoggedInStateCallback
            public void callback(boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callback(z, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILoggedInStateCallback iLoggedInStateCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLoggedInStateCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLoggedInStateCallback;
            return true;
        }

        public static ILoggedInStateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
