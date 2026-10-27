package com.core.lib.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.core.lib.common.ICoreSystemServerListener;

/* JADX INFO: loaded from: classes.dex */
public interface ICoreSystemServer extends IInterface {
    boolean addService(String str, IBinder iBinder) throws RemoteException;

    boolean addServiceListener(ICoreSystemServerListener iCoreSystemServerListener) throws RemoteException;

    IBinder getService(String str) throws RemoteException;

    boolean isServiceReady(String str) throws RemoteException;

    boolean isServiceSupport(String str) throws RemoteException;

    boolean notifyServiceReady(String str, IBinder iBinder) throws RemoteException;

    boolean removeServiceListener(ICoreSystemServerListener iCoreSystemServerListener) throws RemoteException;

    public static class Default implements ICoreSystemServer {
        @Override // com.core.lib.common.ICoreSystemServer
        public boolean addService(String serviceName, IBinder service) throws RemoteException {
            return false;
        }

        @Override // com.core.lib.common.ICoreSystemServer
        public IBinder getService(String serviceName) throws RemoteException {
            return null;
        }

        @Override // com.core.lib.common.ICoreSystemServer
        public boolean addServiceListener(ICoreSystemServerListener listener) throws RemoteException {
            return false;
        }

        @Override // com.core.lib.common.ICoreSystemServer
        public boolean removeServiceListener(ICoreSystemServerListener listener) throws RemoteException {
            return false;
        }

        @Override // com.core.lib.common.ICoreSystemServer
        public boolean isServiceSupport(String serviceName) throws RemoteException {
            return false;
        }

        @Override // com.core.lib.common.ICoreSystemServer
        public boolean isServiceReady(String serviceName) throws RemoteException {
            return false;
        }

        @Override // com.core.lib.common.ICoreSystemServer
        public boolean notifyServiceReady(String serviceName, IBinder binder) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICoreSystemServer {
        private static final String DESCRIPTOR = "com.core.lib.common.ICoreSystemServer";
        static final int TRANSACTION_addService = 1;
        static final int TRANSACTION_addServiceListener = 3;
        static final int TRANSACTION_getService = 2;
        static final int TRANSACTION_isServiceReady = 6;
        static final int TRANSACTION_isServiceSupport = 5;
        static final int TRANSACTION_notifyServiceReady = 7;
        static final int TRANSACTION_removeServiceListener = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICoreSystemServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICoreSystemServer)) {
                return (ICoreSystemServer) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddService = addService(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddService ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder service = getService(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(service);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddServiceListener = addServiceListener(ICoreSystemServerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddServiceListener ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveServiceListener = removeServiceListener(ICoreSystemServerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveServiceListener ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsServiceSupport = isServiceSupport(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsServiceSupport ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsServiceReady = isServiceReady(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsServiceReady ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zNotifyServiceReady = notifyServiceReady(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(zNotifyServiceReady ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICoreSystemServer {
            public static ICoreSystemServer sDefaultImpl;
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

            @Override // com.core.lib.common.ICoreSystemServer
            public boolean addService(String serviceName, IBinder service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    _data.writeStrongBinder(service);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addService(serviceName, service);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.core.lib.common.ICoreSystemServer
            public IBinder getService(String serviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getService(serviceName);
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.core.lib.common.ICoreSystemServer
            public boolean addServiceListener(ICoreSystemServerListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addServiceListener(listener);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.core.lib.common.ICoreSystemServer
            public boolean removeServiceListener(ICoreSystemServerListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeServiceListener(listener);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.core.lib.common.ICoreSystemServer
            public boolean isServiceSupport(String serviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isServiceSupport(serviceName);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.core.lib.common.ICoreSystemServer
            public boolean isServiceReady(String serviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isServiceReady(serviceName);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.core.lib.common.ICoreSystemServer
            public boolean notifyServiceReady(String serviceName, IBinder binder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    _data.writeStrongBinder(binder);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyServiceReady(serviceName, binder);
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

        public static boolean setDefaultImpl(ICoreSystemServer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICoreSystemServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
