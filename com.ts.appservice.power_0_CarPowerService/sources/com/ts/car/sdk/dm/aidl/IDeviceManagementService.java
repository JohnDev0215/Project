package com.ts.car.sdk.dm.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.car.sdk.dm.aidl.ICheckResultCallback;
import com.ts.car.sdk.dm.aidl.IDeviceManagementListener;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDeviceManagementService extends IInterface {
    boolean cancelWirelessCarPlay(String str) throws RemoteException;

    void checkCarPlayWirelessCapability(String str, ICheckResultCallback iCheckResultCallback) throws RemoteException;

    int connect(String str, int i) throws RemoteException;

    int connectByRequest(ConnectRequest connectRequest) throws RemoteException;

    boolean deleteDevice(String str) throws RemoteException;

    int disconnect(String str, int i) throws RemoteException;

    List<DmDevice> getDeviceList() throws RemoteException;

    List<DmDevice> getOnlineDeviceList() throws RemoteException;

    void registerListener(IDeviceManagementListener iDeviceManagementListener) throws RemoteException;

    void unregisterListener(IDeviceManagementListener iDeviceManagementListener) throws RemoteException;

    public static class Default implements IDeviceManagementService {
        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public void registerListener(IDeviceManagementListener listener) throws RemoteException {
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public void unregisterListener(IDeviceManagementListener listener) throws RemoteException {
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public List<DmDevice> getDeviceList() throws RemoteException {
            return null;
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public List<DmDevice> getOnlineDeviceList() throws RemoteException {
            return null;
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public boolean deleteDevice(String deviceId) throws RemoteException {
            return false;
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public int connect(String deviceId, int type) throws RemoteException {
            return 0;
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public int connectByRequest(ConnectRequest connectRequest) throws RemoteException {
            return 0;
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public int disconnect(String deviceId, int type) throws RemoteException {
            return 0;
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public void checkCarPlayWirelessCapability(String mac, ICheckResultCallback callback) throws RemoteException {
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
        public boolean cancelWirelessCarPlay(String mac) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDeviceManagementService {
        private static final String DESCRIPTOR = "com.ts.car.sdk.dm.aidl.IDeviceManagementService";
        static final int TRANSACTION_cancelWirelessCarPlay = 10;
        static final int TRANSACTION_checkCarPlayWirelessCapability = 9;
        static final int TRANSACTION_connect = 6;
        static final int TRANSACTION_connectByRequest = 7;
        static final int TRANSACTION_deleteDevice = 5;
        static final int TRANSACTION_disconnect = 8;
        static final int TRANSACTION_getDeviceList = 3;
        static final int TRANSACTION_getOnlineDeviceList = 4;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_unregisterListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceManagementService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IDeviceManagementService)) {
                return (IDeviceManagementService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ConnectRequest connectRequestCreateFromParcel;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IDeviceManagementListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IDeviceManagementListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DmDevice> deviceList = getDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(deviceList);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DmDevice> onlineDeviceList = getOnlineDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(onlineDeviceList);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeleteDevice = deleteDevice(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteDevice ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iConnect = connect(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iConnect);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        connectRequestCreateFromParcel = ConnectRequest.CREATOR.createFromParcel(parcel);
                    } else {
                        connectRequestCreateFromParcel = null;
                    }
                    int iConnectByRequest = connectByRequest(connectRequestCreateFromParcel);
                    parcel2.writeNoException();
                    parcel2.writeInt(iConnectByRequest);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iDisconnect = disconnect(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iDisconnect);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    checkCarPlayWirelessCapability(parcel.readString(), ICheckResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelWirelessCarPlay = cancelWirelessCarPlay(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelWirelessCarPlay ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDeviceManagementService {
            public static IDeviceManagementService sDefaultImpl;
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

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public void registerListener(IDeviceManagementListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public void unregisterListener(IDeviceManagementListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public List<DmDevice> getDeviceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceList();
                    }
                    _reply.readException();
                    List<DmDevice> _result = _reply.createTypedArrayList(DmDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public List<DmDevice> getOnlineDeviceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOnlineDeviceList();
                    }
                    _reply.readException();
                    List<DmDevice> _result = _reply.createTypedArrayList(DmDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public boolean deleteDevice(String deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteDevice(deviceId);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public int connect(String deviceId, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connect(deviceId, type);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public int connectByRequest(ConnectRequest connectRequest) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectRequest != null) {
                        _data.writeInt(1);
                        connectRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectByRequest(connectRequest);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public int disconnect(String deviceId, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnect(deviceId, type);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public void checkCarPlayWirelessCapability(String mac, ICheckResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().checkCarPlayWirelessCapability(mac, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementService
            public boolean cancelWirelessCarPlay(String mac) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelWirelessCarPlay(mac);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceManagementService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IDeviceManagementService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
