package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoAppCallback extends IInterface {
    void onClusterVideoFocusStatusChanged(int i) throws RemoteException;

    void onLastAvailableDeviceChanged(String str, int i) throws RemoteException;

    void onProjectionBluetoothAddressChanged(String str) throws RemoteException;

    void onVideoFocusStatusChanged(int i) throws RemoteException;

    public static class Default implements IAndroidAutoAppCallback {
        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onLastAvailableDeviceChanged(String deviceId, int capability) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onVideoFocusStatusChanged(int videoFocusStatus) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onClusterVideoFocusStatusChanged(int videoFocusStatus) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onProjectionBluetoothAddressChanged(String address) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAndroidAutoAppCallback {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback";
        static final int TRANSACTION_onClusterVideoFocusStatusChanged = 3;
        static final int TRANSACTION_onLastAvailableDeviceChanged = 1;
        static final int TRANSACTION_onProjectionBluetoothAddressChanged = 4;
        static final int TRANSACTION_onVideoFocusStatusChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAndroidAutoAppCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAndroidAutoAppCallback)) {
                return (IAndroidAutoAppCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                String _arg0 = data.readString();
                int _arg1 = data.readInt();
                onLastAvailableDeviceChanged(_arg0, _arg1);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onVideoFocusStatusChanged(_arg02);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int _arg03 = data.readInt();
                onClusterVideoFocusStatusChanged(_arg03);
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
            String _arg04 = data.readString();
            onProjectionBluetoothAddressChanged(_arg04);
            return true;
        }

        private static class Proxy implements IAndroidAutoAppCallback {
            public static IAndroidAutoAppCallback sDefaultImpl;
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

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
            public void onLastAvailableDeviceChanged(String deviceId, int capability) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    _data.writeInt(capability);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLastAvailableDeviceChanged(deviceId, capability);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
            public void onVideoFocusStatusChanged(int videoFocusStatus) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(videoFocusStatus);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVideoFocusStatusChanged(videoFocusStatus);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
            public void onClusterVideoFocusStatusChanged(int videoFocusStatus) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(videoFocusStatus);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onClusterVideoFocusStatusChanged(videoFocusStatus);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
            public void onProjectionBluetoothAddressChanged(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionBluetoothAddressChanged(address);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAndroidAutoAppCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAndroidAutoAppCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
