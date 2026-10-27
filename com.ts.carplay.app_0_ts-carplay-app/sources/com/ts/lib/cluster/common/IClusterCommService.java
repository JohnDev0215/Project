package com.ts.lib.cluster.common;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.cluster.common.IClusterCommCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IClusterCommService extends IInterface {
    boolean registerCallback(int i, IClusterCommCallback iClusterCommCallback) throws RemoteException;

    Bundle requestCacheData(int i, int i2) throws RemoteException;

    void sendCommand(int i, Bundle bundle) throws RemoteException;

    boolean unregisterCallback(int i, IClusterCommCallback iClusterCommCallback) throws RemoteException;

    public static class Default implements IClusterCommService {
        @Override // com.ts.lib.cluster.common.IClusterCommService
        public void sendCommand(int commandId, Bundle bundle) throws RemoteException {
        }

        @Override // com.ts.lib.cluster.common.IClusterCommService
        public boolean registerCallback(int commandId, IClusterCommCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.cluster.common.IClusterCommService
        public boolean unregisterCallback(int commandId, IClusterCommCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.cluster.common.IClusterCommService
        public Bundle requestCacheData(int commandId, int dataType) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IClusterCommService {
        private static final String DESCRIPTOR = "com.ts.lib.cluster.common.IClusterCommService";
        static final int TRANSACTION_registerCallback = 2;
        static final int TRANSACTION_requestCacheData = 4;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_unregisterCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IClusterCommService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IClusterCommService)) {
                return (IClusterCommService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int i3 = parcel.readInt();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                sendCommand(i3, bundle);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zRegisterCallback = registerCallback(parcel.readInt(), IClusterCommCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zRegisterCallback ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zUnregisterCallback = unregisterCallback(parcel.readInt(), IClusterCommCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zUnregisterCallback ? 1 : 0);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            Bundle bundleRequestCacheData = requestCacheData(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            if (bundleRequestCacheData != null) {
                parcel2.writeInt(1);
                bundleRequestCacheData.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IClusterCommService {
            public static IClusterCommService sDefaultImpl;
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

            @Override // com.ts.lib.cluster.common.IClusterCommService
            public void sendCommand(int commandId, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(commandId);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendCommand(commandId, bundle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.cluster.common.IClusterCommService
            public boolean registerCallback(int commandId, IClusterCommCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(commandId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(commandId, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.cluster.common.IClusterCommService
            public boolean unregisterCallback(int commandId, IClusterCommCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(commandId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterCallback(commandId, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.cluster.common.IClusterCommService
            public Bundle requestCacheData(int commandId, int dataType) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(commandId);
                    _data.writeInt(dataType);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestCacheData(commandId, dataType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IClusterCommService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IClusterCommService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
