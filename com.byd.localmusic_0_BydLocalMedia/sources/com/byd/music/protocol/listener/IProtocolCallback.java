package com.byd.music.protocol.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.music.protocol.model.base.ProtocolErrorModel;

/* JADX INFO: loaded from: classes5.dex */
public interface IProtocolCallback extends IInterface {

    public static class Default implements IProtocolCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.music.protocol.listener.IProtocolCallback
        public void onFail(ProtocolErrorModel protocolErrorModel) throws RemoteException {
        }

        @Override // com.byd.music.protocol.listener.IProtocolCallback
        public void onJSONResult(String str) throws RemoteException {
        }

        @Override // com.byd.music.protocol.listener.IProtocolCallback
        public void onSuccess(String str) throws RemoteException {
        }
    }

    void onFail(ProtocolErrorModel protocolErrorModel) throws RemoteException;

    void onJSONResult(String str) throws RemoteException;

    void onSuccess(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IProtocolCallback {
        private static final String DESCRIPTOR = "com.byd.music.protocol.listener.IProtocolCallback";
        static final int TRANSACTION_onFail = 1;
        static final int TRANSACTION_onJSONResult = 2;
        static final int TRANSACTION_onSuccess = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProtocolCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IProtocolCallback)) {
                return (IProtocolCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onFail(parcel.readInt() != 0 ? ProtocolErrorModel.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onJSONResult(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onSuccess(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IProtocolCallback {
            public static IProtocolCallback sDefaultImpl;
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

            @Override // com.byd.music.protocol.listener.IProtocolCallback
            public void onFail(ProtocolErrorModel protocolErrorModel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (protocolErrorModel != null) {
                        parcelObtain.writeInt(1);
                        protocolErrorModel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFail(protocolErrorModel);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.music.protocol.listener.IProtocolCallback
            public void onJSONResult(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJSONResult(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.music.protocol.listener.IProtocolCallback
            public void onSuccess(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProtocolCallback iProtocolCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProtocolCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProtocolCallback;
            return true;
        }

        public static IProtocolCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
