package ts.car.dcm.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.common.data.WifiConfigInfo;

/* JADX INFO: loaded from: classes.dex */
public interface IPcmListener extends IInterface {
    void notifyBtOobPairComplete(String str, int i, int i2) throws RemoteException;

    void onBtConnected(String str, int i, int i2) throws RemoteException;

    void onBtDisconnected(String str, int i, int i2) throws RemoteException;

    void onBtProfileChanged(String str, int i, int i2, int i3) throws RemoteException;

    void onBtStateChanged(int i, int i2) throws RemoteException;

    void onCheckUsbDeviceName(CmDevice cmDevice, int i) throws RemoteException;

    void onConnectProjection(CmDevice cmDevice, ProjectionLinkParameter projectionLinkParameter, int i) throws RemoteException;

    void onDeleteProjectionData(String str, int i) throws RemoteException;

    void onDeviceConnectionUpdated(CmDevice cmDevice, int i, int i2, int i3) throws RemoteException;

    void onDisconnectIapSession(String str, int i) throws RemoteException;

    void onDisconnectProjection(String str, int i, int i2, int i3) throws RemoteException;

    void onDisconnectedOtherBtDevices(String str, int i, int i2) throws RemoteException;

    void onEnableFunction(int i, boolean z, int i2) throws RemoteException;

    void onEnableUsbAoaFinished(CmDevice cmDevice, int i, boolean z, int i2) throws RemoteException;

    void onEnableUsbAoaStarting(CmDevice cmDevice, int i, int i2) throws RemoteException;

    void onIap2BtDeviceConnected(CmDevice cmDevice, int i, int i2) throws RemoteException;

    void onResetUsbFinished(String str, boolean z, int i) throws RemoteException;

    void onResetUsbStarting(String str, int i) throws RemoteException;

    void onShowProjection(boolean z, int i) throws RemoteException;

    void onSwitchAaConnection(CmDevice cmDevice, CmDevice cmDevice2, int i) throws RemoteException;

    void onSwitchConnection(CmDevice cmDevice, int i, int i2) throws RemoteException;

    void onSwitchProjectionConnection(CmDevice cmDevice, int i, int i2) throws RemoteException;

    void onUsbRoleSwitchFinished(CmDevice cmDevice, int i, boolean z, int i2) throws RemoteException;

    void onUsbRoleSwitchStarting(String str, int i, int i2) throws RemoteException;

    void onWifiApInfo(WifiConfigInfo wifiConfigInfo, int i, int i2) throws RemoteException;

    void onWifiConnectStatus(String str, int i, int i2) throws RemoteException;

    public static class Default implements IPcmListener {
        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onConnectProjection(CmDevice device, ProjectionLinkParameter plp, int srv) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDisconnectProjection(String devId, int capa, int srv, int reason) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onSwitchProjectionConnection(CmDevice device, int capa, int srv) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onSwitchAaConnection(CmDevice device, CmDevice device2, int srv) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDeleteProjectionData(String devId, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDeviceConnectionUpdated(CmDevice device, int connType, int reason, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onShowProjection(boolean flg, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onEnableFunction(int funId, boolean status, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onUsbRoleSwitchStarting(String devId, int mode, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onUsbRoleSwitchFinished(CmDevice dev, int mode, boolean result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onResetUsbStarting(String devId, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onResetUsbFinished(String devId, boolean result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onEnableUsbAoaStarting(CmDevice dev, int mode, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onEnableUsbAoaFinished(CmDevice dev, int mode, boolean result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtStateChanged(int state, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtDisconnected(String mac, int result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtConnected(String mac, int result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtProfileChanged(String mac, int profile, int state, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDisconnectedOtherBtDevices(String mac, int result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onIap2BtDeviceConnected(CmDevice dev, int reason, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void notifyBtOobPairComplete(String mac, int status, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onWifiApInfo(WifiConfigInfo config, int status, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onWifiConnectStatus(String mac, int status, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onSwitchConnection(CmDevice device, int capa, int srv) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onCheckUsbDeviceName(CmDevice device, int srv) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDisconnectIapSession(String devId, int srv) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPcmListener {
        private static final String DESCRIPTOR = "ts.car.dcm.common.aidl.IPcmListener";
        static final int TRANSACTION_notifyBtOobPairComplete = 21;
        static final int TRANSACTION_onBtConnected = 17;
        static final int TRANSACTION_onBtDisconnected = 16;
        static final int TRANSACTION_onBtProfileChanged = 18;
        static final int TRANSACTION_onBtStateChanged = 15;
        static final int TRANSACTION_onCheckUsbDeviceName = 25;
        static final int TRANSACTION_onConnectProjection = 1;
        static final int TRANSACTION_onDeleteProjectionData = 5;
        static final int TRANSACTION_onDeviceConnectionUpdated = 6;
        static final int TRANSACTION_onDisconnectIapSession = 26;
        static final int TRANSACTION_onDisconnectProjection = 2;
        static final int TRANSACTION_onDisconnectedOtherBtDevices = 19;
        static final int TRANSACTION_onEnableFunction = 8;
        static final int TRANSACTION_onEnableUsbAoaFinished = 14;
        static final int TRANSACTION_onEnableUsbAoaStarting = 13;
        static final int TRANSACTION_onIap2BtDeviceConnected = 20;
        static final int TRANSACTION_onResetUsbFinished = 12;
        static final int TRANSACTION_onResetUsbStarting = 11;
        static final int TRANSACTION_onShowProjection = 7;
        static final int TRANSACTION_onSwitchAaConnection = 4;
        static final int TRANSACTION_onSwitchConnection = 24;
        static final int TRANSACTION_onSwitchProjectionConnection = 3;
        static final int TRANSACTION_onUsbRoleSwitchFinished = 10;
        static final int TRANSACTION_onUsbRoleSwitchStarting = 9;
        static final int TRANSACTION_onWifiApInfo = 22;
        static final int TRANSACTION_onWifiConnectStatus = 23;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPcmListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IPcmListener)) {
                return (IPcmListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CmDevice _arg0;
            ProjectionLinkParameter _arg1;
            CmDevice _arg02;
            CmDevice _arg03;
            CmDevice _arg12;
            CmDevice _arg04;
            boolean _arg2;
            CmDevice _arg05;
            CmDevice _arg06;
            CmDevice _arg07;
            CmDevice _arg08;
            WifiConfigInfo _arg09;
            CmDevice _arg010;
            CmDevice _arg011;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = ProjectionLinkParameter.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    onConnectProjection(_arg0, _arg1, data.readInt());
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    int _arg13 = data.readInt();
                    int _arg22 = data.readInt();
                    int _arg3 = data.readInt();
                    onDisconnectProjection(_arg012, _arg13, _arg22, _arg3);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    int _arg14 = data.readInt();
                    onSwitchProjectionConnection(_arg02, _arg14, data.readInt());
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg12 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    onSwitchAaConnection(_arg03, _arg12, data.readInt());
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg013 = data.readString();
                    int _arg15 = data.readInt();
                    onDeleteProjectionData(_arg013, _arg15);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    int _arg16 = data.readInt();
                    int _arg23 = data.readInt();
                    int _arg32 = data.readInt();
                    onDeviceConnectionUpdated(_arg04, _arg16, _arg23, _arg32);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    _arg2 = data.readInt() != 0;
                    int _arg17 = data.readInt();
                    onShowProjection(_arg2, _arg17);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    _arg2 = data.readInt() != 0;
                    onEnableFunction(_arg014, _arg2, data.readInt());
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg015 = data.readString();
                    int _arg18 = data.readInt();
                    onUsbRoleSwitchStarting(_arg015, _arg18, data.readInt());
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg05 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    int _arg19 = data.readInt();
                    _arg2 = data.readInt() != 0;
                    int _arg33 = data.readInt();
                    onUsbRoleSwitchFinished(_arg05, _arg19, _arg2, _arg33);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg016 = data.readString();
                    int _arg110 = data.readInt();
                    onResetUsbStarting(_arg016, _arg110);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg017 = data.readString();
                    _arg2 = data.readInt() != 0;
                    onResetUsbFinished(_arg017, _arg2, data.readInt());
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg06 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg06 = null;
                    }
                    int _arg111 = data.readInt();
                    onEnableUsbAoaStarting(_arg06, _arg111, data.readInt());
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg07 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg07 = null;
                    }
                    int _arg112 = data.readInt();
                    _arg2 = data.readInt() != 0;
                    int _arg34 = data.readInt();
                    onEnableUsbAoaFinished(_arg07, _arg112, _arg2, _arg34);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg018 = data.readInt();
                    int _arg113 = data.readInt();
                    onBtStateChanged(_arg018, _arg113);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg019 = data.readString();
                    int _arg114 = data.readInt();
                    onBtDisconnected(_arg019, _arg114, data.readInt());
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg020 = data.readString();
                    int _arg115 = data.readInt();
                    onBtConnected(_arg020, _arg115, data.readInt());
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg021 = data.readString();
                    int _arg116 = data.readInt();
                    int _arg24 = data.readInt();
                    int _arg35 = data.readInt();
                    onBtProfileChanged(_arg021, _arg116, _arg24, _arg35);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg022 = data.readString();
                    int _arg117 = data.readInt();
                    onDisconnectedOtherBtDevices(_arg022, _arg117, data.readInt());
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg08 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg08 = null;
                    }
                    int _arg118 = data.readInt();
                    onIap2BtDeviceConnected(_arg08, _arg118, data.readInt());
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg023 = data.readString();
                    int _arg119 = data.readInt();
                    notifyBtOobPairComplete(_arg023, _arg119, data.readInt());
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg09 = WifiConfigInfo.CREATOR.createFromParcel(data);
                    } else {
                        _arg09 = null;
                    }
                    int _arg120 = data.readInt();
                    onWifiApInfo(_arg09, _arg120, data.readInt());
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg024 = data.readString();
                    int _arg121 = data.readInt();
                    onWifiConnectStatus(_arg024, _arg121, data.readInt());
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg010 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg010 = null;
                    }
                    int _arg122 = data.readInt();
                    onSwitchConnection(_arg010, _arg122, data.readInt());
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg011 = CmDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg011 = null;
                    }
                    int _arg123 = data.readInt();
                    onCheckUsbDeviceName(_arg011, _arg123);
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg025 = data.readString();
                    int _arg124 = data.readInt();
                    onDisconnectIapSession(_arg025, _arg124);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IPcmListener {
            public static IPcmListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onConnectProjection(CmDevice device, ProjectionLinkParameter plp, int srv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (plp != null) {
                        _data.writeInt(1);
                        plp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(srv);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnectProjection(device, plp, srv);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onDisconnectProjection(String devId, int capa, int srv, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(capa);
                    _data.writeInt(srv);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisconnectProjection(devId, capa, srv, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onSwitchProjectionConnection(CmDevice device, int capa, int srv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(capa);
                    _data.writeInt(srv);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSwitchProjectionConnection(device, capa, srv);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onSwitchAaConnection(CmDevice device, CmDevice device2, int srv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (device2 != null) {
                        _data.writeInt(1);
                        device2.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(srv);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSwitchAaConnection(device, device2, srv);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onDeleteProjectionData(String devId, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeleteProjectionData(devId, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onDeviceConnectionUpdated(CmDevice device, int connType, int reason, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(connType);
                    _data.writeInt(reason);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceConnectionUpdated(device, connType, reason, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onShowProjection(boolean flg, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flg ? 1 : 0);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onShowProjection(flg, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onEnableFunction(int funId, boolean status, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(funId);
                    _data.writeInt(status ? 1 : 0);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnableFunction(funId, status, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onUsbRoleSwitchStarting(String devId, int mode, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(mode);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUsbRoleSwitchStarting(devId, mode, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onUsbRoleSwitchFinished(CmDevice dev, int mode, boolean result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dev != null) {
                        _data.writeInt(1);
                        dev.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(mode);
                    _data.writeInt(result ? 1 : 0);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUsbRoleSwitchFinished(dev, mode, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onResetUsbStarting(String devId, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResetUsbStarting(devId, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onResetUsbFinished(String devId, boolean result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(result ? 1 : 0);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResetUsbFinished(devId, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onEnableUsbAoaStarting(CmDevice dev, int mode, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dev != null) {
                        _data.writeInt(1);
                        dev.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(mode);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnableUsbAoaStarting(dev, mode, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onEnableUsbAoaFinished(CmDevice dev, int mode, boolean result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dev != null) {
                        _data.writeInt(1);
                        dev.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(mode);
                    _data.writeInt(result ? 1 : 0);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnableUsbAoaFinished(dev, mode, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onBtStateChanged(int state, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtStateChanged(state, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onBtDisconnected(String mac, int result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(result);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtDisconnected(mac, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onBtConnected(String mac, int result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(result);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtConnected(mac, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onBtProfileChanged(String mac, int profile, int state, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(profile);
                    _data.writeInt(state);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtProfileChanged(mac, profile, state, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onDisconnectedOtherBtDevices(String mac, int result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(result);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisconnectedOtherBtDevices(mac, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onIap2BtDeviceConnected(CmDevice dev, int reason, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dev != null) {
                        _data.writeInt(1);
                        dev.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIap2BtDeviceConnected(dev, reason, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void notifyBtOobPairComplete(String mac, int status, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(status);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyBtOobPairComplete(mac, status, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onWifiApInfo(WifiConfigInfo config, int status, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(status);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWifiApInfo(config, status, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onWifiConnectStatus(String mac, int status, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(status);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWifiConnectStatus(mac, status, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onSwitchConnection(CmDevice device, int capa, int srv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(capa);
                    _data.writeInt(srv);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSwitchConnection(device, capa, srv);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onCheckUsbDeviceName(CmDevice device, int srv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(srv);
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCheckUsbDeviceName(device, srv);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IPcmListener
            public void onDisconnectIapSession(String devId, int srv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(srv);
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisconnectIapSession(devId, srv);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPcmListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IPcmListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
