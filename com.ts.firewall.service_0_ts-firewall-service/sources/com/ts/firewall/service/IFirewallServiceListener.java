package com.ts.firewall.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IFirewallServiceListener extends IInterface {
    void onFirewallStatusChange(boolean z) throws RemoteException;

    void onWhiteListStatusChange(boolean z) throws RemoteException;

    public static class Default implements IFirewallServiceListener {
        @Override // com.ts.firewall.service.IFirewallServiceListener
        public void onFirewallStatusChange(boolean state) throws RemoteException {
        }

        @Override // com.ts.firewall.service.IFirewallServiceListener
        public void onWhiteListStatusChange(boolean enabled) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IFirewallServiceListener {
        private static final String DESCRIPTOR = "com.ts.firewall.service.IFirewallServiceListener";
        static final int TRANSACTION_onFirewallStatusChange = 1;
        static final int TRANSACTION_onWhiteListStatusChange = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IFirewallServiceListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IFirewallServiceListener)) {
                return (IFirewallServiceListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                _arg0 = data.readInt() != 0;
                onFirewallStatusChange(_arg0);
                reply.writeNoException();
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
            _arg0 = data.readInt() != 0;
            onWhiteListStatusChange(_arg0);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IFirewallServiceListener {
            public static IFirewallServiceListener sDefaultImpl;
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

            @Override // com.ts.firewall.service.IFirewallServiceListener
            public void onFirewallStatusChange(boolean state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFirewallStatusChange(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceListener
            public void onWhiteListStatusChange(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWhiteListStatusChange(enabled);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFirewallServiceListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IFirewallServiceListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
