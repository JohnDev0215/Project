package com.amazon.identity.auth.device.callback;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.identity.auth.device.callback.IRemoteCallback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface IGenericIPC extends IInterface {

    /* JADX INFO: compiled from: DCP */
    public static abstract class Stub extends Binder implements IGenericIPC {
        private static final String DESCRIPTOR = "com.amazon.identity.auth.device.callback.IGenericIPC";
        public static final int TRANSACTION_call = 1;

        /* JADX INFO: compiled from: DCP */
        public static class Proxy implements IGenericIPC {
            public static IGenericIPC sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.identity.auth.device.callback.IGenericIPC
            public void call(String str, Bundle bundle, IRemoteCallback iRemoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().call(str, bundle, iRemoteCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGenericIPC asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IGenericIPC)) ? new Proxy(iBinder) : (IGenericIPC) iInterfaceQueryLocalInterface;
        }

        public static IGenericIPC getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IGenericIPC iGenericIPC) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGenericIPC == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGenericIPC;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            call(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void call(String str, Bundle bundle, IRemoteCallback iRemoteCallback) throws RemoteException;
}
