package com.byd.music.protocol;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.music.protocol.listener.IProtocolCallback;
import com.byd.music.protocol.model.base.ProtocolBaseModel;

/* JADX INFO: loaded from: classes5.dex */
public interface IProtocolAidlInterface extends IInterface {

    public static class Default implements IProtocolAidlInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.music.protocol.IProtocolAidlInterface
        public void registCallBack(IProtocolCallback iProtocolCallback) throws RemoteException {
        }

        @Override // com.byd.music.protocol.IProtocolAidlInterface
        public void setProtocolModelData(ProtocolBaseModel protocolBaseModel) throws RemoteException {
        }
    }

    void registCallBack(IProtocolCallback iProtocolCallback) throws RemoteException;

    void setProtocolModelData(ProtocolBaseModel protocolBaseModel) throws RemoteException;

    public static abstract class Stub extends Binder implements IProtocolAidlInterface {
        private static final String DESCRIPTOR = "com.byd.music.protocol.IProtocolAidlInterface";
        static final int TRANSACTION_registCallBack = 2;
        static final int TRANSACTION_setProtocolModelData = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProtocolAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IProtocolAidlInterface)) {
                return (IProtocolAidlInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                setProtocolModelData(parcel.readInt() != 0 ? ProtocolBaseModel.CREATOR.createFromParcel(parcel) : null);
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
            registCallBack(IProtocolCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IProtocolAidlInterface {
            public static IProtocolAidlInterface sDefaultImpl;
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

            @Override // com.byd.music.protocol.IProtocolAidlInterface
            public void setProtocolModelData(ProtocolBaseModel protocolBaseModel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (protocolBaseModel != null) {
                        parcelObtain.writeInt(1);
                        protocolBaseModel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setProtocolModelData(protocolBaseModel);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.music.protocol.IProtocolAidlInterface
            public void registCallBack(IProtocolCallback iProtocolCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iProtocolCallback != null ? iProtocolCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registCallBack(iProtocolCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProtocolAidlInterface iProtocolAidlInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProtocolAidlInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProtocolAidlInterface;
            return true;
        }

        public static IProtocolAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
