package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAndroidAutoSessionStatusCallback extends IInterface {
    void onProjectionAppStatesChanged(int i, boolean z) throws RemoteException;

    void onSessionConnectStatusChanged(int i, int i2, String str, int i3) throws RemoteException;

    void onSessionStatusChanged(int i, int i2, String str) throws RemoteException;

    public static class Default implements IAndroidAutoSessionStatusCallback {
        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onSessionStatusChanged(int linkStatus, int errorCode, String message) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onProjectionAppStatesChanged(int appType, boolean enabled) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onSessionConnectStatusChanged(int linkStatus, int errorCode, String message, int projectionConnectType) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAndroidAutoSessionStatusCallback {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback";
        static final int TRANSACTION_onProjectionAppStatesChanged = 2;
        static final int TRANSACTION_onSessionConnectStatusChanged = 3;
        static final int TRANSACTION_onSessionStatusChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAndroidAutoSessionStatusCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAndroidAutoSessionStatusCallback)) {
                return (IAndroidAutoSessionStatusCallback) iin;
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
                int _arg0 = data.readInt();
                int _arg1 = data.readInt();
                String _arg2 = data.readString();
                onSessionStatusChanged(_arg0, _arg1, _arg2);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                boolean _arg12 = data.readInt() != 0;
                onProjectionAppStatesChanged(_arg02, _arg12);
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
            int _arg03 = data.readInt();
            int _arg13 = data.readInt();
            String _arg22 = data.readString();
            int _arg3 = data.readInt();
            onSessionConnectStatusChanged(_arg03, _arg13, _arg22, _arg3);
            return true;
        }

        private static class Proxy implements IAndroidAutoSessionStatusCallback {
            public static IAndroidAutoSessionStatusCallback sDefaultImpl;
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

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
            public void onSessionStatusChanged(int linkStatus, int errorCode, String message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(linkStatus);
                    _data.writeInt(errorCode);
                    _data.writeString(message);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSessionStatusChanged(linkStatus, errorCode, message);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
            public void onProjectionAppStatesChanged(int appType, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appType);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionAppStatesChanged(appType, enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
            public void onSessionConnectStatusChanged(int linkStatus, int errorCode, String message, int projectionConnectType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(linkStatus);
                    _data.writeInt(errorCode);
                    _data.writeString(message);
                    _data.writeInt(projectionConnectType);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSessionConnectStatusChanged(linkStatus, errorCode, message, projectionConnectType);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAndroidAutoSessionStatusCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAndroidAutoSessionStatusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
