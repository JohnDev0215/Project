package ts.car.dcm.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import ts.car.dcm.common.data.AbnormalDevice;
import ts.car.dcm.common.data.ConnDevice;
import ts.car.dcm.common.data.WifiConfigInfo;

/* JADX INFO: loaded from: classes.dex */
public interface IDmListener extends IInterface {
    void notifyBtOobPairComplete(String str, int i) throws RemoteException;

    void onAbnormalDeviceStatusChanged(AbnormalDevice abnormalDevice, int i) throws RemoteException;

    void onBtConnected(String str, int i, int i2) throws RemoteException;

    void onBtDisconnected(String str, int i, int i2) throws RemoteException;

    void onBtProfileChanged(String str, int i, int i2, int i3) throws RemoteException;

    void onBtStateChanged(int i, int i2) throws RemoteException;

    void onDeviceAccept(ConnDevice connDevice) throws RemoteException;

    void onDeviceAttach(ConnDevice connDevice, int i) throws RemoteException;

    void onDeviceCapabilityChecked(String str, int i, boolean z, int i2) throws RemoteException;

    void onDeviceDetach(ConnDevice connDevice, int i) throws RemoteException;

    void onDeviceStatusChanged(ConnDevice connDevice) throws RemoteException;

    void onDevicesMerged(String str, String str2, ConnDevice connDevice) throws RemoteException;

    void onIncomingPhoneChanged(ConnDevice connDevice) throws RemoteException;

    void onNotifyMessage(int i, String str) throws RemoteException;

    void onOutgoingPhoneChanged(ConnDevice connDevice) throws RemoteException;

    void onPhoneStateChanged(String str, int i) throws RemoteException;

    void onProjectionConnectionChanged(ConnDevice connDevice, int i, int i2, int i3) throws RemoteException;

    void onProjectionServiceReady() throws RemoteException;

    void onProjectionServiceStatus(int i, boolean z) throws RemoteException;

    void onRequestProjection(ConnDevice connDevice, int i) throws RemoteException;

    void onSelectOutgoingPhone(List<ConnDevice> list) throws RemoteException;

    void onWifiApInfo(WifiConfigInfo wifiConfigInfo, int i, int i2) throws RemoteException;

    void onWirelessCarplayDetected(String str, int i) throws RemoteException;

    void onWirelessProjectionFound(ConnDevice connDevice) throws RemoteException;

    public static class Default implements IDmListener {
        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceAccept(ConnDevice device) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onRequestProjection(ConnDevice device, int projectionType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceStatusChanged(ConnDevice device) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDevicesMerged(String oneDevId, String anotherDevId, ConnDevice device) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onOutgoingPhoneChanged(ConnDevice device) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onIncomingPhoneChanged(ConnDevice device) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onProjectionConnectionChanged(ConnDevice device, int capa, int status, int reason) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onSelectOutgoingPhone(List<ConnDevice> devs) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onNotifyMessage(int msgId, String msg) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtStateChanged(int state, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtDisconnected(String mac, int result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtConnected(String mac, int result, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtProfileChanged(String mac, int profile, int state, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onPhoneStateChanged(String devId, int state) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onWirelessCarplayDetected(String mac, int status) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onWirelessProjectionFound(ConnDevice device) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onWifiApInfo(WifiConfigInfo config, int status, int srvType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceAttach(ConnDevice device, int connType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceDetach(ConnDevice device, int connType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onProjectionServiceReady() throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onProjectionServiceStatus(int serviceType, boolean status) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onAbnormalDeviceStatusChanged(AbnormalDevice device, int status) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceCapabilityChecked(String devId, int capability, boolean isSupport, int reason) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void notifyBtOobPairComplete(String mac, int status) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDmListener {
        private static final String DESCRIPTOR = "ts.car.dcm.common.aidl.IDmListener";
        static final int TRANSACTION_notifyBtOobPairComplete = 24;
        static final int TRANSACTION_onAbnormalDeviceStatusChanged = 22;
        static final int TRANSACTION_onBtConnected = 12;
        static final int TRANSACTION_onBtDisconnected = 11;
        static final int TRANSACTION_onBtProfileChanged = 13;
        static final int TRANSACTION_onBtStateChanged = 10;
        static final int TRANSACTION_onDeviceAccept = 1;
        static final int TRANSACTION_onDeviceAttach = 18;
        static final int TRANSACTION_onDeviceCapabilityChecked = 23;
        static final int TRANSACTION_onDeviceDetach = 19;
        static final int TRANSACTION_onDeviceStatusChanged = 3;
        static final int TRANSACTION_onDevicesMerged = 4;
        static final int TRANSACTION_onIncomingPhoneChanged = 6;
        static final int TRANSACTION_onNotifyMessage = 9;
        static final int TRANSACTION_onOutgoingPhoneChanged = 5;
        static final int TRANSACTION_onPhoneStateChanged = 14;
        static final int TRANSACTION_onProjectionConnectionChanged = 7;
        static final int TRANSACTION_onProjectionServiceReady = 20;
        static final int TRANSACTION_onProjectionServiceStatus = 21;
        static final int TRANSACTION_onRequestProjection = 2;
        static final int TRANSACTION_onSelectOutgoingPhone = 8;
        static final int TRANSACTION_onWifiApInfo = 17;
        static final int TRANSACTION_onWirelessCarplayDetected = 15;
        static final int TRANSACTION_onWirelessProjectionFound = 16;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDmListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IDmListener)) {
                return (IDmListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ConnDevice _arg0;
            ConnDevice _arg02;
            ConnDevice _arg03;
            ConnDevice _arg2;
            ConnDevice _arg04;
            ConnDevice _arg05;
            ConnDevice _arg06;
            ConnDevice _arg07;
            WifiConfigInfo _arg08;
            ConnDevice _arg09;
            ConnDevice _arg010;
            boolean _arg22;
            AbnormalDevice _arg011;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onDeviceAccept(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    int _arg1 = data.readInt();
                    onRequestProjection(_arg02, _arg1);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    onDeviceStatusChanged(_arg03);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    String _arg12 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    onDevicesMerged(_arg012, _arg12, _arg2);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    onOutgoingPhoneChanged(_arg04);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg05 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    onIncomingPhoneChanged(_arg05);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg06 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg06 = null;
                    }
                    int _arg13 = data.readInt();
                    int _arg23 = data.readInt();
                    int _arg3 = data.readInt();
                    onProjectionConnectionChanged(_arg06, _arg13, _arg23, _arg3);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    List<ConnDevice> _arg013 = data.createTypedArrayList(ConnDevice.CREATOR);
                    onSelectOutgoingPhone(_arg013);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    String _arg14 = data.readString();
                    onNotifyMessage(_arg014, _arg14);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg015 = data.readInt();
                    int _arg15 = data.readInt();
                    onBtStateChanged(_arg015, _arg15);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg016 = data.readString();
                    int _arg16 = data.readInt();
                    onBtDisconnected(_arg016, _arg16, data.readInt());
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg017 = data.readString();
                    int _arg17 = data.readInt();
                    onBtConnected(_arg017, _arg17, data.readInt());
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg018 = data.readString();
                    int _arg18 = data.readInt();
                    int _arg24 = data.readInt();
                    int _arg32 = data.readInt();
                    onBtProfileChanged(_arg018, _arg18, _arg24, _arg32);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg019 = data.readString();
                    int _arg19 = data.readInt();
                    onPhoneStateChanged(_arg019, _arg19);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg020 = data.readString();
                    int _arg110 = data.readInt();
                    onWirelessCarplayDetected(_arg020, _arg110);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg07 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg07 = null;
                    }
                    onWirelessProjectionFound(_arg07);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg08 = WifiConfigInfo.CREATOR.createFromParcel(data);
                    } else {
                        _arg08 = null;
                    }
                    int _arg111 = data.readInt();
                    onWifiApInfo(_arg08, _arg111, data.readInt());
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg09 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg09 = null;
                    }
                    int _arg112 = data.readInt();
                    onDeviceAttach(_arg09, _arg112);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg010 = ConnDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg010 = null;
                    }
                    int _arg113 = data.readInt();
                    onDeviceDetach(_arg010, _arg113);
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    onProjectionServiceReady();
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg021 = data.readInt();
                    _arg22 = data.readInt() != 0;
                    onProjectionServiceStatus(_arg021, _arg22);
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg011 = AbnormalDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg011 = null;
                    }
                    int _arg114 = data.readInt();
                    onAbnormalDeviceStatusChanged(_arg011, _arg114);
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg022 = data.readString();
                    int _arg115 = data.readInt();
                    _arg22 = data.readInt() != 0;
                    int _arg33 = data.readInt();
                    onDeviceCapabilityChecked(_arg022, _arg115, _arg22, _arg33);
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg023 = data.readString();
                    int _arg116 = data.readInt();
                    notifyBtOobPairComplete(_arg023, _arg116);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IDmListener {
            public static IDmListener sDefaultImpl;
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

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onDeviceAccept(ConnDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceAccept(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onRequestProjection(ConnDevice device, int projectionType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(projectionType);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestProjection(device, projectionType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onDeviceStatusChanged(ConnDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceStatusChanged(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onDevicesMerged(String oneDevId, String anotherDevId, ConnDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(oneDevId);
                    _data.writeString(anotherDevId);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDevicesMerged(oneDevId, anotherDevId, device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onOutgoingPhoneChanged(ConnDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOutgoingPhoneChanged(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onIncomingPhoneChanged(ConnDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIncomingPhoneChanged(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onProjectionConnectionChanged(ConnDevice device, int capa, int status, int reason) throws RemoteException {
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
                    _data.writeInt(status);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionConnectionChanged(device, capa, status, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onSelectOutgoingPhone(List<ConnDevice> devs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(devs);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSelectOutgoingPhone(devs);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onNotifyMessage(int msgId, String msg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(msgId);
                    _data.writeString(msg);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyMessage(msgId, msg);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onBtStateChanged(int state, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtStateChanged(state, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onBtDisconnected(String mac, int result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(result);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtDisconnected(mac, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onBtConnected(String mac, int result, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(result);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtConnected(mac, result, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onBtProfileChanged(String mac, int profile, int state, int srvType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(profile);
                    _data.writeInt(state);
                    _data.writeInt(srvType);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtProfileChanged(mac, profile, state, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onPhoneStateChanged(String devId, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPhoneStateChanged(devId, state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onWirelessCarplayDetected(String mac, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWirelessCarplayDetected(mac, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onWirelessProjectionFound(ConnDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWirelessProjectionFound(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
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
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWifiApInfo(config, status, srvType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onDeviceAttach(ConnDevice device, int connType) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceAttach(device, connType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onDeviceDetach(ConnDevice device, int connType) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceDetach(device, connType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onProjectionServiceReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionServiceReady();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onProjectionServiceStatus(int serviceType, boolean status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceType);
                    _data.writeInt(status ? 1 : 0);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionServiceStatus(serviceType, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onAbnormalDeviceStatusChanged(AbnormalDevice device, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAbnormalDeviceStatusChanged(device, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void onDeviceCapabilityChecked(String devId, int capability, boolean isSupport, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(capability);
                    _data.writeInt(isSupport ? 1 : 0);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceCapabilityChecked(devId, capability, isSupport, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IDmListener
            public void notifyBtOobPairComplete(String mac, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyBtOobPairComplete(mac, status);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDmListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IDmListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
