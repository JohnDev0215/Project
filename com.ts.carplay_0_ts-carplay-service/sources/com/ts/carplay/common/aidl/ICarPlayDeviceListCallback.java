package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.carplay.common.data.CarPlayDevice;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayDeviceListCallback extends IInterface {
    void onCarPlayDeviceStatusChanged(CarPlayDevice carPlayDevice) throws RemoteException;

    void onCarPlayOobStartEvent(byte[] bArr) throws RemoteException;

    void onDeviceMerged(CarPlayDevice carPlayDevice) throws RemoteException;

    void onRequestProjection(CarPlayDevice carPlayDevice, int i) throws RemoteException;

    void oniPhoneAttachedOverUsb(CarPlayDevice carPlayDevice, boolean z) throws RemoteException;

    void oniPhoneDetachedOverUsb(CarPlayDevice carPlayDevice) throws RemoteException;

    public static class Default implements ICarPlayDeviceListCallback {
        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onDeviceMerged(CarPlayDevice device) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onCarPlayDeviceStatusChanged(CarPlayDevice device) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onCarPlayOobStartEvent(byte[] macAddress) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onRequestProjection(CarPlayDevice device, int type) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void oniPhoneAttachedOverUsb(CarPlayDevice device, boolean supported) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void oniPhoneDetachedOverUsb(CarPlayDevice device) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarPlayDeviceListCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.ICarPlayDeviceListCallback";
        static final int TRANSACTION_onCarPlayDeviceStatusChanged = 2;
        static final int TRANSACTION_onCarPlayOobStartEvent = 3;
        static final int TRANSACTION_onDeviceMerged = 1;
        static final int TRANSACTION_onRequestProjection = 4;
        static final int TRANSACTION_oniPhoneAttachedOverUsb = 5;
        static final int TRANSACTION_oniPhoneDetachedOverUsb = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPlayDeviceListCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPlayDeviceListCallback)) {
                return (ICarPlayDeviceListCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CarPlayDevice _arg0;
            CarPlayDevice _arg02;
            CarPlayDevice _arg03;
            CarPlayDevice _arg04;
            CarPlayDevice _arg05;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = CarPlayDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onDeviceMerged(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = CarPlayDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    onCarPlayDeviceStatusChanged(_arg02);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg06 = data.createByteArray();
                    onCarPlayOobStartEvent(_arg06);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = CarPlayDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    int _arg1 = data.readInt();
                    onRequestProjection(_arg03, _arg1);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = CarPlayDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    boolean _arg12 = data.readInt() != 0;
                    oniPhoneAttachedOverUsb(_arg04, _arg12);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg05 = CarPlayDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    oniPhoneDetachedOverUsb(_arg05);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ICarPlayDeviceListCallback {
            public static ICarPlayDeviceListCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
            public void onDeviceMerged(CarPlayDevice device) throws RemoteException {
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
                        Stub.getDefaultImpl().onDeviceMerged(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
            public void onCarPlayDeviceStatusChanged(CarPlayDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarPlayDeviceStatusChanged(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
            public void onCarPlayOobStartEvent(byte[] macAddress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(macAddress);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarPlayOobStartEvent(macAddress);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
            public void onRequestProjection(CarPlayDevice device, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestProjection(device, type);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
            public void oniPhoneAttachedOverUsb(CarPlayDevice device, boolean supported) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(supported ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().oniPhoneAttachedOverUsb(device, supported);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
            public void oniPhoneDetachedOverUsb(CarPlayDevice device) throws RemoteException {
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
                        Stub.getDefaultImpl().oniPhoneDetachedOverUsb(device);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarPlayDeviceListCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarPlayDeviceListCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
