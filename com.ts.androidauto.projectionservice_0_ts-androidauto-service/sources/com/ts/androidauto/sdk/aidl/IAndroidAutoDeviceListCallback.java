package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoDeviceListCallback extends IInterface {
    void onAndroidDetachedOverUsb(AndroidAutoDevice androidAutoDevice) throws RemoteException;

    void onDeviceStatusChanged(AndroidAutoDevice androidAutoDevice) throws RemoteException;

    void onDevicesMerged(AndroidAutoDevice androidAutoDevice) throws RemoteException;

    void onRequestProjection(AndroidAutoDevice androidAutoDevice, int i) throws RemoteException;

    public static class Default implements IAndroidAutoDeviceListCallback {
        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onRequestProjection(AndroidAutoDevice device, int type) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onDevicesMerged(AndroidAutoDevice device) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onDeviceStatusChanged(AndroidAutoDevice device) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onAndroidDetachedOverUsb(AndroidAutoDevice device) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAndroidAutoDeviceListCallback {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback";
        static final int TRANSACTION_onAndroidDetachedOverUsb = 4;
        static final int TRANSACTION_onDeviceStatusChanged = 3;
        static final int TRANSACTION_onDevicesMerged = 2;
        static final int TRANSACTION_onRequestProjection = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAndroidAutoDeviceListCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAndroidAutoDeviceListCallback)) {
                return (IAndroidAutoDeviceListCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            AndroidAutoDevice _arg0;
            AndroidAutoDevice _arg02;
            AndroidAutoDevice _arg03;
            AndroidAutoDevice _arg04;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = AndroidAutoDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                int _arg1 = data.readInt();
                onRequestProjection(_arg0, _arg1);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg02 = AndroidAutoDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                onDevicesMerged(_arg02);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg03 = AndroidAutoDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg03 = null;
                }
                onDeviceStatusChanged(_arg03);
                return true;
            }
            if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg04 = AndroidAutoDevice.CREATOR.createFromParcel(data);
            } else {
                _arg04 = null;
            }
            onAndroidDetachedOverUsb(_arg04);
            return true;
        }

        private static class Proxy implements IAndroidAutoDeviceListCallback {
            public static IAndroidAutoDeviceListCallback sDefaultImpl;
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

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
            public void onRequestProjection(AndroidAutoDevice device, int type) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestProjection(device, type);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
            public void onDevicesMerged(AndroidAutoDevice device) throws RemoteException {
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
                        Stub.getDefaultImpl().onDevicesMerged(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
            public void onDeviceStatusChanged(AndroidAutoDevice device) throws RemoteException {
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

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
            public void onAndroidDetachedOverUsb(AndroidAutoDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAndroidDetachedOverUsb(device);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAndroidAutoDeviceListCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAndroidAutoDeviceListCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
