package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayStateCallback extends IInterface {
    void onAppStateChanged(int i, boolean z, int i2) throws RemoteException;

    void onCarPlayMediaFocusStateChanged(boolean z) throws RemoteException;

    void onCarPlaySessionConnectStateChanged(int i, boolean z, String str, boolean z2) throws RemoteException;

    void onSessionConnectStateChanged(int i, boolean z, String str) throws RemoteException;

    void onSessionStateChanged(int i) throws RemoteException;

    public static class Default implements ICarPlayStateCallback {
        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onSessionStateChanged(int status) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onSessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onCarPlaySessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onAppStateChanged(int appState, boolean enable, int speechMode) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onCarPlayMediaFocusStateChanged(boolean isCarPlayMedia) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarPlayStateCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.ICarPlayStateCallback";
        static final int TRANSACTION_onAppStateChanged = 4;
        static final int TRANSACTION_onCarPlayMediaFocusStateChanged = 5;
        static final int TRANSACTION_onCarPlaySessionConnectStateChanged = 3;
        static final int TRANSACTION_onSessionConnectStateChanged = 2;
        static final int TRANSACTION_onSessionStateChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPlayStateCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPlayStateCallback)) {
                return (ICarPlayStateCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg1;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                onSessionStateChanged(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                _arg1 = data.readInt() != 0;
                String _arg2 = data.readString();
                onSessionConnectStateChanged(_arg02, _arg1, _arg2);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int _arg03 = data.readInt();
                boolean _arg12 = data.readInt() != 0;
                String _arg22 = data.readString();
                _arg1 = data.readInt() != 0;
                onCarPlaySessionConnectStateChanged(_arg03, _arg12, _arg22, _arg1);
                return true;
            }
            if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                int _arg04 = data.readInt();
                _arg1 = data.readInt() != 0;
                int _arg23 = data.readInt();
                onAppStateChanged(_arg04, _arg1, _arg23);
                return true;
            }
            if (code != 5) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            _arg1 = data.readInt() != 0;
            boolean _arg05 = _arg1;
            onCarPlayMediaFocusStateChanged(_arg05);
            return true;
        }

        private static class Proxy implements ICarPlayStateCallback {
            public static ICarPlayStateCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
            public void onSessionStateChanged(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSessionStateChanged(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
            public void onSessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    _data.writeInt(isOldConnection ? 1 : 0);
                    _data.writeString(clientAddr);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSessionConnectStateChanged(status, isOldConnection, clientAddr);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
            public void onCarPlaySessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    _data.writeInt(isOldConnection ? 1 : 0);
                    _data.writeString(clientAddr);
                    _data.writeInt(isUserConnect ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarPlaySessionConnectStateChanged(status, isOldConnection, clientAddr, isUserConnect);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
            public void onAppStateChanged(int appState, boolean enable, int speechMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appState);
                    _data.writeInt(enable ? 1 : 0);
                    _data.writeInt(speechMode);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppStateChanged(appState, enable, speechMode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
            public void onCarPlayMediaFocusStateChanged(boolean isCarPlayMedia) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isCarPlayMedia ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarPlayMediaFocusStateChanged(isCarPlayMedia);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarPlayStateCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarPlayStateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
