package com.ecarx.xui.adaptapi.bt1.gattserver;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.bt1.gattserver.IGattServerCallback;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IGattServer extends IInterface {
    List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid parcelUuid) throws RemoteException;

    List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) throws RemoteException;

    List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException;

    int getGattServerConnectionState() throws RemoteException;

    boolean isGattServerServiceReady() throws RemoteException;

    boolean registerGattServerCallback(IGattServerCallback iGattServerCallback) throws RemoteException;

    boolean reqGattServerAddCharacteristic(ParcelUuid parcelUuid, int i, int i2) throws RemoteException;

    boolean reqGattServerAddDescriptor(ParcelUuid parcelUuid, int i) throws RemoteException;

    boolean reqGattServerBeginServiceDeclaration(int i, ParcelUuid parcelUuid) throws RemoteException;

    boolean reqGattServerClearServices() throws RemoteException;

    boolean reqGattServerDisconnect(String str) throws RemoteException;

    boolean reqGattServerEndServiceDeclaration() throws RemoteException;

    boolean reqGattServerListen(boolean z) throws RemoteException;

    boolean reqGattServerRemoveService(int i, ParcelUuid parcelUuid) throws RemoteException;

    boolean reqGattServerSendNotification(String str, int i, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, boolean z, byte[] bArr) throws RemoteException;

    boolean reqGattServerSendResponse(String str, int i, int i2, int i3, byte[] bArr) throws RemoteException;

    boolean unregisterGattServerCallback(IGattServerCallback iGattServerCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IGattServer {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer";
        static final int TRANSACTION_getGattAddedGattCharacteristicUuidList = 16;
        static final int TRANSACTION_getGattAddedGattDescriptorUuidList = 17;
        static final int TRANSACTION_getGattAddedGattServiceUuidList = 15;
        static final int TRANSACTION_getGattServerConnectionState = 4;
        static final int TRANSACTION_isGattServerServiceReady = 1;
        static final int TRANSACTION_registerGattServerCallback = 2;
        static final int TRANSACTION_reqGattServerAddCharacteristic = 7;
        static final int TRANSACTION_reqGattServerAddDescriptor = 8;
        static final int TRANSACTION_reqGattServerBeginServiceDeclaration = 6;
        static final int TRANSACTION_reqGattServerClearServices = 11;
        static final int TRANSACTION_reqGattServerDisconnect = 5;
        static final int TRANSACTION_reqGattServerEndServiceDeclaration = 9;
        static final int TRANSACTION_reqGattServerListen = 12;
        static final int TRANSACTION_reqGattServerRemoveService = 10;
        static final int TRANSACTION_reqGattServerSendNotification = 14;
        static final int TRANSACTION_reqGattServerSendResponse = 13;
        static final int TRANSACTION_unregisterGattServerCallback = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGattServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGattServer)) {
                return (IGattServer) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsGattServerServiceReady = isGattServerServiceReady();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsGattServerServiceReady ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterGattServerCallback = registerGattServerCallback(IGattServerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterGattServerCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterGattServerCallback = unregisterGattServerCallback(IGattServerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterGattServerCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int gattServerConnectionState = getGattServerConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(gattServerConnectionState);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerDisconnect = reqGattServerDisconnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerDisconnect ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerBeginServiceDeclaration = reqGattServerBeginServiceDeclaration(parcel.readInt(), parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerBeginServiceDeclaration ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerAddCharacteristic = reqGattServerAddCharacteristic(parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerAddCharacteristic ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerAddDescriptor = reqGattServerAddDescriptor(parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerAddDescriptor ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerEndServiceDeclaration = reqGattServerEndServiceDeclaration();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerEndServiceDeclaration ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerRemoveService = reqGattServerRemoveService(parcel.readInt(), parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerRemoveService ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerClearServices = reqGattServerClearServices();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerClearServices ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerListen = reqGattServerListen(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerListen ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerSendResponse = reqGattServerSendResponse(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerSendResponse ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqGattServerSendNotification = reqGattServerSendNotification(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqGattServerSendNotification ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ParcelUuid> gattAddedGattServiceUuidList = getGattAddedGattServiceUuidList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(gattAddedGattServiceUuidList);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ParcelUuid> gattAddedGattCharacteristicUuidList = getGattAddedGattCharacteristicUuidList(parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(gattAddedGattCharacteristicUuidList);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ParcelUuid> gattAddedGattDescriptorUuidList = getGattAddedGattDescriptorUuidList(parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(gattAddedGattDescriptorUuidList);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IGattServer {
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

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean isGattServerServiceReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean registerGattServerCallback(IGattServerCallback iGattServerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGattServerCallback != null ? iGattServerCallback.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean unregisterGattServerCallback(IGattServerCallback iGattServerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGattServerCallback != null ? iGattServerCallback.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public int getGattServerConnectionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerDisconnect(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerBeginServiceDeclaration(int i, ParcelUuid parcelUuid) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerAddCharacteristic(ParcelUuid parcelUuid, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerAddDescriptor(ParcelUuid parcelUuid, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerEndServiceDeclaration() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerRemoveService(int i, ParcelUuid parcelUuid) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerClearServices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerListen(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerSendResponse(String str, int i, int i2, int i3, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeByteArray(bArr);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public boolean reqGattServerSendNotification(String str, int i, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, boolean z, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelUuid2 != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeByteArray(bArr);
                    this.mRemote.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ParcelUuid.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid parcelUuid) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ParcelUuid.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer
            public List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelUuid2 != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.mRemote.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ParcelUuid.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
