package com.ts.ota.otasdkmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ITestCallback extends IInterface {
    void onAutomaticNetworkChange(int i) throws RemoteException;

    void onCloudServiceConnectStateChanged(int i) throws RemoteException;

    void onGetApnParamFromTbox(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws RemoteException;

    void onGetTboxNetworkState(int i, int i2) throws RemoteException;

    void onGetTrafficFromTbox(int i, String str, String str2, long j, long j2, long j3, long j4) throws RemoteException;

    void onManuallyStationedNetworkChange(String str, int i, int i2, String str2, int i3) throws RemoteException;

    void onNetworkOperatorList(TestInfo testInfo) throws RemoteException;

    void onNetworkOperatorModeChange(int i) throws RemoteException;

    void onOperatorInfoChange(List<NetworkOperatorAidl> list) throws RemoteException;

    void onSimCardStateChange(int i) throws RemoteException;

    void onSyncApnParamResult(int i) throws RemoteException;

    void onTboxCmd2Ivi(int i) throws RemoteException;

    void onTboxRestartChanged(int i) throws RemoteException;

    void onUpdateTrafficData() throws RemoteException;

    public static class Default implements ITestCallback {
        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onNetworkOperatorList(TestInfo code) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onTboxRestartChanged(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onCloudServiceConnectStateChanged(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onUpdateTrafficData() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onTboxCmd2Ivi(int cmd) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onNetworkOperatorModeChange(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onOperatorInfoChange(List<NetworkOperatorAidl> list) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onManuallyStationedNetworkChange(String data, int curNetType, int state, String lastData, int lastNetType) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onAutomaticNetworkChange(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onSimCardStateChange(int status) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onSyncApnParamResult(int resultCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onGetApnParamFromTbox(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onGetTrafficFromTbox(int apnType, String iccid, String time, long dayUpTraffic, long dayDownTraffic, long monthUpTraffic, long monthDownTraffic) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.ITestCallback
        public void onGetTboxNetworkState(int apn, int state) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITestCallback {
        private static final String DESCRIPTOR = "com.ts.ota.otasdkmgr.ITestCallback";
        static final int TRANSACTION_onAutomaticNetworkChange = 9;
        static final int TRANSACTION_onCloudServiceConnectStateChanged = 3;
        static final int TRANSACTION_onGetApnParamFromTbox = 12;
        static final int TRANSACTION_onGetTboxNetworkState = 14;
        static final int TRANSACTION_onGetTrafficFromTbox = 13;
        static final int TRANSACTION_onManuallyStationedNetworkChange = 8;
        static final int TRANSACTION_onNetworkOperatorList = 1;
        static final int TRANSACTION_onNetworkOperatorModeChange = 6;
        static final int TRANSACTION_onOperatorInfoChange = 7;
        static final int TRANSACTION_onSimCardStateChange = 10;
        static final int TRANSACTION_onSyncApnParamResult = 11;
        static final int TRANSACTION_onTboxCmd2Ivi = 5;
        static final int TRANSACTION_onTboxRestartChanged = 2;
        static final int TRANSACTION_onUpdateTrafficData = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITestCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ITestCallback)) {
                return (ITestCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            TestInfo _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = TestInfo.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onNetworkOperatorList(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    onTboxRestartChanged(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onCloudServiceConnectStateChanged(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onUpdateTrafficData();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    onTboxCmd2Ivi(_arg04);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    onNetworkOperatorModeChange(_arg05);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    List<NetworkOperatorAidl> _arg06 = data.createTypedArrayList(NetworkOperatorAidl.CREATOR);
                    onOperatorInfoChange(_arg06);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    String _arg3 = data.readString();
                    int _arg4 = data.readInt();
                    onManuallyStationedNetworkChange(_arg07, _arg1, _arg2, _arg3, _arg4);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    onAutomaticNetworkChange(_arg08);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    onSimCardStateChange(_arg09);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    onSyncApnParamResult(_arg010);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    String _arg12 = data.readString();
                    String _arg22 = data.readString();
                    String _arg32 = data.readString();
                    String _arg42 = data.readString();
                    String _arg5 = data.readString();
                    String _arg6 = data.readString();
                    onGetApnParamFromTbox(_arg011, _arg12, _arg22, _arg32, _arg42, _arg5, _arg6);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    String _arg13 = data.readString();
                    String _arg23 = data.readString();
                    long _arg33 = data.readLong();
                    long _arg43 = data.readLong();
                    long _arg52 = data.readLong();
                    long _arg62 = data.readLong();
                    onGetTrafficFromTbox(_arg012, _arg13, _arg23, _arg33, _arg43, _arg52, _arg62);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg013 = data.readInt();
                    int _arg14 = data.readInt();
                    onGetTboxNetworkState(_arg013, _arg14);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITestCallback {
            public static ITestCallback sDefaultImpl;
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

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onNetworkOperatorList(TestInfo code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (code != null) {
                        _data.writeInt(1);
                        code.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNetworkOperatorList(code);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onTboxRestartChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTboxRestartChanged(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onCloudServiceConnectStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCloudServiceConnectStateChanged(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onUpdateTrafficData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUpdateTrafficData();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onTboxCmd2Ivi(int cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cmd);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTboxCmd2Ivi(cmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onNetworkOperatorModeChange(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNetworkOperatorModeChange(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onOperatorInfoChange(List<NetworkOperatorAidl> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(list);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOperatorInfoChange(list);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onManuallyStationedNetworkChange(String data, int curNetType, int state, String lastData, int lastNetType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeInt(curNetType);
                    _data.writeInt(state);
                    _data.writeString(lastData);
                    _data.writeInt(lastNetType);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onManuallyStationedNetworkChange(data, curNetType, state, lastData, lastNetType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onAutomaticNetworkChange(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAutomaticNetworkChange(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onSimCardStateChange(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSimCardStateChange(status);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onSyncApnParamResult(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSyncApnParamResult(resultCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onGetApnParamFromTbox(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeString(apn);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(username);
                    try {
                        _data.writeString(pwd);
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(apnType);
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(apnProtocol);
                        _data.writeString(proxy);
                        _data.writeString(port);
                        boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().onGetApnParamFromTbox(apn, username, pwd, apnType, apnProtocol, proxy, port);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onGetTrafficFromTbox(int apnType, String iccid, String time, long dayUpTraffic, long dayDownTraffic, long monthUpTraffic, long monthDownTraffic) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeInt(apnType);
                    _data.writeString(iccid);
                    _data.writeString(time);
                    _data.writeLong(dayUpTraffic);
                    _data.writeLong(dayDownTraffic);
                    _data.writeLong(monthUpTraffic);
                    _data.writeLong(monthDownTraffic);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetTrafficFromTbox(apnType, iccid, time, dayUpTraffic, dayDownTraffic, monthUpTraffic, monthDownTraffic);
                        _reply.recycle();
                        _data.recycle();
                    } else {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.ts.ota.otasdkmgr.ITestCallback
            public void onGetTboxNetworkState(int apn, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(apn);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetTboxNetworkState(apn, state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITestCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ITestCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
