package com.ecarx.xui.adaptapi.bt1.settings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.bt1.common.BtDevice;
import com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IBtSettings extends IInterface {
    boolean cancelBtDiscovery() throws RemoteException;

    String getBtLocalAddress() throws RemoteException;

    String getBtLocalName() throws RemoteException;

    String getBtRemoteDeviceName(String str) throws RemoteException;

    int getBtRemoteUuids(String str) throws RemoteException;

    int getBtRoleMode() throws RemoteException;

    int getBtState() throws RemoteException;

    String getServiceVersionName() throws RemoteException;

    boolean isBluetoothServiceReady() throws RemoteException;

    boolean isBtAutoConnectEnable() throws RemoteException;

    boolean isBtDiscoverable() throws RemoteException;

    boolean isBtDiscovering() throws RemoteException;

    boolean isBtEnabled() throws RemoteException;

    boolean registerBtCallback(IBtSettingsCallback iBtSettingsCallback) throws RemoteException;

    int reqBtConnectHfpA2dp(String str) throws RemoteException;

    int reqBtDisconnectAll() throws RemoteException;

    List<BtDevice> reqBtFoundDevices() throws RemoteException;

    boolean reqBtPair(String str) throws RemoteException;

    List<BtDevice> reqBtPairedDevices() throws RemoteException;

    boolean reqBtPairedDevicesAsync() throws RemoteException;

    boolean reqBtUnpair(String str) throws RemoteException;

    void setBtAutoConnectMode(boolean z) throws RemoteException;

    boolean setBtDiscoverableTimeout(int i) throws RemoteException;

    boolean setBtEnable(boolean z) throws RemoteException;

    boolean setBtLocalName(String str) throws RemoteException;

    boolean startBtDiscovery() throws RemoteException;

    boolean switchBtRoleMode(int i) throws RemoteException;

    boolean unregisterBtCallback(IBtSettingsCallback iBtSettingsCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IBtSettings {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.bt1.settings.IBtSettings";
        static final int TRANSACTION_cancelBtDiscovery = 8;
        static final int TRANSACTION_getBtLocalAddress = 16;
        static final int TRANSACTION_getBtLocalName = 14;
        static final int TRANSACTION_getBtRemoteDeviceName = 15;
        static final int TRANSACTION_getBtRemoteUuids = 26;
        static final int TRANSACTION_getBtRoleMode = 28;
        static final int TRANSACTION_getBtState = 19;
        static final int TRANSACTION_getServiceVersionName = 4;
        static final int TRANSACTION_isBluetoothServiceReady = 1;
        static final int TRANSACTION_isBtAutoConnectEnable = 23;
        static final int TRANSACTION_isBtDiscoverable = 21;
        static final int TRANSACTION_isBtDiscovering = 20;
        static final int TRANSACTION_isBtEnabled = 18;
        static final int TRANSACTION_registerBtCallback = 2;
        static final int TRANSACTION_reqBtConnectHfpA2dp = 24;
        static final int TRANSACTION_reqBtDisconnectAll = 25;
        static final int TRANSACTION_reqBtFoundDevices = 13;
        static final int TRANSACTION_reqBtPair = 9;
        static final int TRANSACTION_reqBtPairedDevices = 11;
        static final int TRANSACTION_reqBtPairedDevicesAsync = 12;
        static final int TRANSACTION_reqBtUnpair = 10;
        static final int TRANSACTION_setBtAutoConnectMode = 22;
        static final int TRANSACTION_setBtDiscoverableTimeout = 6;
        static final int TRANSACTION_setBtEnable = 5;
        static final int TRANSACTION_setBtLocalName = 17;
        static final int TRANSACTION_startBtDiscovery = 7;
        static final int TRANSACTION_switchBtRoleMode = 27;
        static final int TRANSACTION_unregisterBtCallback = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBtSettings asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBtSettings)) {
                return (IBtSettings) iInterfaceQueryLocalInterface;
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
                    boolean zIsBluetoothServiceReady = isBluetoothServiceReady();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBluetoothServiceReady ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterBtCallback = registerBtCallback(IBtSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterBtCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterBtCallback = unregisterBtCallback(IBtSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterBtCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String serviceVersionName = getServiceVersionName();
                    parcel2.writeNoException();
                    parcel2.writeString(serviceVersionName);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean btEnable = setBtEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(btEnable ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean btDiscoverableTimeout = setBtDiscoverableTimeout(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(btDiscoverableTimeout ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartBtDiscovery = startBtDiscovery();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartBtDiscovery ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelBtDiscovery = cancelBtDiscovery();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelBtDiscovery ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqBtPair = reqBtPair(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqBtPair ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqBtUnpair = reqBtUnpair(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqBtUnpair ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BtDevice> listReqBtPairedDevices = reqBtPairedDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listReqBtPairedDevices);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqBtPairedDevicesAsync = reqBtPairedDevicesAsync();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqBtPairedDevicesAsync ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BtDevice> listReqBtFoundDevices = reqBtFoundDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listReqBtFoundDevices);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String btLocalName = getBtLocalName();
                    parcel2.writeNoException();
                    parcel2.writeString(btLocalName);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String btRemoteDeviceName = getBtRemoteDeviceName(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(btRemoteDeviceName);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    String btLocalAddress = getBtLocalAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(btLocalAddress);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean btLocalName2 = setBtLocalName(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(btLocalName2 ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBtEnabled = isBtEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBtEnabled ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int btState = getBtState();
                    parcel2.writeNoException();
                    parcel2.writeInt(btState);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBtDiscovering = isBtDiscovering();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBtDiscovering ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBtDiscoverable = isBtDiscoverable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBtDiscoverable ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBtAutoConnectMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBtAutoConnectEnable = isBtAutoConnectEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBtAutoConnectEnable ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqBtConnectHfpA2dp = reqBtConnectHfpA2dp(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqBtConnectHfpA2dp);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqBtDisconnectAll = reqBtDisconnectAll();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqBtDisconnectAll);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int btRemoteUuids = getBtRemoteUuids(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(btRemoteUuids);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSwitchBtRoleMode = switchBtRoleMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSwitchBtRoleMode ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int btRoleMode = getBtRoleMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(btRoleMode);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBtSettings {
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

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean isBluetoothServiceReady() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean registerBtCallback(IBtSettingsCallback iBtSettingsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBtSettingsCallback != null ? iBtSettingsCallback.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean unregisterBtCallback(IBtSettingsCallback iBtSettingsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBtSettingsCallback != null ? iBtSettingsCallback.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public String getServiceVersionName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean setBtEnable(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean setBtDiscoverableTimeout(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean startBtDiscovery() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean cancelBtDiscovery() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean reqBtPair(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean reqBtUnpair(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public List<BtDevice> reqBtPairedDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BtDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean reqBtPairedDevicesAsync() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public List<BtDevice> reqBtFoundDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BtDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public String getBtLocalName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public String getBtRemoteDeviceName(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public String getBtLocalAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean setBtLocalName(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean isBtEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public int getBtState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean isBtDiscovering() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean isBtDiscoverable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public void setBtAutoConnectMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean isBtAutoConnectEnable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public int reqBtConnectHfpA2dp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(24, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public int reqBtDisconnectAll() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public int getBtRemoteUuids(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(26, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public boolean switchBtRoleMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(27, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettings
            public int getBtRoleMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
