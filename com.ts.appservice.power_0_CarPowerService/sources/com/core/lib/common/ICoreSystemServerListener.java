package com.core.lib.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICoreSystemServerListener extends IInterface {
    void onServiceStarted(String str) throws RemoteException;

    void onServiceStopped(String str) throws RemoteException;

    public static class Default implements ICoreSystemServerListener {
        @Override // com.core.lib.common.ICoreSystemServerListener
        public void onServiceStarted(String serviceName) throws RemoteException {
        }

        @Override // com.core.lib.common.ICoreSystemServerListener
        public void onServiceStopped(String serviceName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICoreSystemServerListener {
        private static final String DESCRIPTOR = "com.core.lib.common.ICoreSystemServerListener";
        static final int TRANSACTION_onServiceStarted = 1;
        static final int TRANSACTION_onServiceStopped = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICoreSystemServerListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICoreSystemServerListener)) {
                return (ICoreSystemServerListener) iin;
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
                onServiceStarted(_arg0);
                return true;
            }
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            String _arg02 = data.readString();
            onServiceStopped(_arg02);
            return true;
        }

        private static class Proxy implements ICoreSystemServerListener {
            public static ICoreSystemServerListener sDefaultImpl;
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

            @Override // com.core.lib.common.ICoreSystemServerListener
            public void onServiceStarted(String serviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onServiceStarted(serviceName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.core.lib.common.ICoreSystemServerListener
            public void onServiceStopped(String serviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onServiceStopped(serviceName);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICoreSystemServerListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICoreSystemServerListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
