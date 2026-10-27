package com.ts.car.sdk.dm.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IDeviceManagementListener extends IInterface {
    void onDeviceChanged(DmDevice dmDevice) throws RemoteException;

    void onDeviceDetachedOverUsb(DmDevice dmDevice) throws RemoteException;

    void onDeviceProjectionRequested(boolean z, ConnectRequest connectRequest) throws RemoteException;

    public static class Default implements IDeviceManagementListener {
        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
        public void onDeviceChanged(DmDevice device) throws RemoteException {
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
        public void onDeviceProjectionRequested(boolean supported, ConnectRequest request) throws RemoteException {
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
        public void onDeviceDetachedOverUsb(DmDevice device) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDeviceManagementListener {
        private static final String DESCRIPTOR = "com.ts.car.sdk.dm.aidl.IDeviceManagementListener";
        static final int TRANSACTION_onDeviceChanged = 1;
        static final int TRANSACTION_onDeviceDetachedOverUsb = 3;
        static final int TRANSACTION_onDeviceProjectionRequested = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceManagementListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IDeviceManagementListener)) {
                return (IDeviceManagementListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            DmDevice _arg0;
            ConnectRequest _arg1;
            DmDevice _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = DmDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onDeviceChanged(_arg0);
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                boolean _arg03 = data.readInt() != 0;
                if (data.readInt() != 0) {
                    _arg1 = ConnectRequest.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                onDeviceProjectionRequested(_arg03, _arg1);
                reply.writeNoException();
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg02 = DmDevice.CREATOR.createFromParcel(data);
            } else {
                _arg02 = null;
            }
            onDeviceDetachedOverUsb(_arg02);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IDeviceManagementListener {
            public static IDeviceManagementListener sDefaultImpl;
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

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
            public void onDeviceChanged(DmDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceChanged(device);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
            public void onDeviceProjectionRequested(boolean supported, ConnectRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(supported ? 1 : 0);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceProjectionRequested(supported, request);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
            public void onDeviceDetachedOverUsb(DmDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceDetachedOverUsb(device);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceManagementListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IDeviceManagementListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
