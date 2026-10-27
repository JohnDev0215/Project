package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoServiceCallback extends IInterface {
    void onClusterVideoUpdateUiConfigReply(boolean z) throws RemoteException;

    void onRequestClusterVideoFocus(int i) throws RemoteException;

    void onRequestVideoFocus(int i) throws RemoteException;

    public static class Default implements IAndroidAutoServiceCallback {
        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
        public void onRequestVideoFocus(int requestType) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
        public void onRequestClusterVideoFocus(int requestType) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
        public void onClusterVideoUpdateUiConfigReply(boolean replyOk) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAndroidAutoServiceCallback {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback";
        static final int TRANSACTION_onClusterVideoUpdateUiConfigReply = 3;
        static final int TRANSACTION_onRequestClusterVideoFocus = 2;
        static final int TRANSACTION_onRequestVideoFocus = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAndroidAutoServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAndroidAutoServiceCallback)) {
                return (IAndroidAutoServiceCallback) iin;
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
                onRequestVideoFocus(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onRequestClusterVideoFocus(_arg02);
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
            boolean _arg03 = data.readInt() != 0;
            onClusterVideoUpdateUiConfigReply(_arg03);
            return true;
        }

        private static class Proxy implements IAndroidAutoServiceCallback {
            public static IAndroidAutoServiceCallback sDefaultImpl;
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

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
            public void onRequestVideoFocus(int requestType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(requestType);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestVideoFocus(requestType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
            public void onRequestClusterVideoFocus(int requestType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(requestType);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestClusterVideoFocus(requestType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
            public void onClusterVideoUpdateUiConfigReply(boolean replyOk) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(replyOk ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onClusterVideoUpdateUiConfigReply(replyOk);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAndroidAutoServiceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAndroidAutoServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
