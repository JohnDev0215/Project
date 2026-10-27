package com.byd.music.protocol.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.music.protocol.model.base.ProtocolBaseModel;
import com.byd.music.protocol.model.base.ProtocolModel;

/* JADX INFO: loaded from: classes5.dex */
public interface IProtocolListener extends IInterface {

    public static class Default implements IProtocolListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.music.protocol.listener.IProtocolListener
        public void onModelReceived(ProtocolBaseModel protocolBaseModel) throws RemoteException {
        }

        @Override // com.byd.music.protocol.listener.IProtocolListener
        public ProtocolModel onSyncGetSystemData(ProtocolBaseModel protocolBaseModel) throws RemoteException {
            return null;
        }
    }

    void onModelReceived(ProtocolBaseModel protocolBaseModel) throws RemoteException;

    ProtocolModel onSyncGetSystemData(ProtocolBaseModel protocolBaseModel) throws RemoteException;

    public static abstract class Stub extends Binder implements IProtocolListener {
        private static final String DESCRIPTOR = "com.byd.music.protocol.listener.IProtocolListener";
        static final int TRANSACTION_onModelReceived = 1;
        static final int TRANSACTION_onSyncGetSystemData = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProtocolListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IProtocolListener)) {
                return (IProtocolListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onModelReceived(parcel.readInt() != 0 ? ProtocolBaseModel.CREATOR.createFromParcel(parcel) : null);
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
            ProtocolModel protocolModelOnSyncGetSystemData = onSyncGetSystemData(parcel.readInt() != 0 ? ProtocolBaseModel.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            if (protocolModelOnSyncGetSystemData != null) {
                parcel2.writeInt(1);
                protocolModelOnSyncGetSystemData.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IProtocolListener {
            public static IProtocolListener sDefaultImpl;
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

            @Override // com.byd.music.protocol.listener.IProtocolListener
            public void onModelReceived(ProtocolBaseModel protocolBaseModel) throws RemoteException {
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
                        Stub.getDefaultImpl().onModelReceived(protocolBaseModel);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.music.protocol.listener.IProtocolListener
            public ProtocolModel onSyncGetSystemData(ProtocolBaseModel protocolBaseModel) throws RemoteException {
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
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onSyncGetSystemData(protocolBaseModel);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ProtocolModel.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProtocolListener iProtocolListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProtocolListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProtocolListener;
            return true;
        }

        public static IProtocolListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
