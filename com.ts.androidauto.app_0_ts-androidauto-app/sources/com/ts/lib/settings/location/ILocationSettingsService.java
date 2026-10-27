package com.ts.lib.settings.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.location.ILocationSettingsCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface ILocationSettingsService extends IInterface {
    String[] getLocUsePackageName() throws RemoteException;

    void subscribeLocationCallback(ILocationSettingsCallback iLocationSettingsCallback) throws RemoteException;

    void unsubscribeLocationCallback(ILocationSettingsCallback iLocationSettingsCallback) throws RemoteException;

    public static class Default implements ILocationSettingsService {
        @Override // com.ts.lib.settings.location.ILocationSettingsService
        public String[] getLocUsePackageName() throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.settings.location.ILocationSettingsService
        public void subscribeLocationCallback(ILocationSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.location.ILocationSettingsService
        public void unsubscribeLocationCallback(ILocationSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ILocationSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.location.ILocationSettingsService";
        static final int TRANSACTION_getLocUsePackageName = 1;
        static final int TRANSACTION_subscribeLocationCallback = 2;
        static final int TRANSACTION_unsubscribeLocationCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ILocationSettingsService)) {
                return (ILocationSettingsService) iin;
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
                String[] _result = getLocUsePackageName();
                reply.writeNoException();
                reply.writeStringArray(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                ILocationSettingsCallback _arg0 = ILocationSettingsCallback.Stub.asInterface(data.readStrongBinder());
                subscribeLocationCallback(_arg0);
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
            ILocationSettingsCallback _arg02 = ILocationSettingsCallback.Stub.asInterface(data.readStrongBinder());
            unsubscribeLocationCallback(_arg02);
            return true;
        }

        private static class Proxy implements ILocationSettingsService {
            public static ILocationSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.location.ILocationSettingsService
            public String[] getLocUsePackageName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocUsePackageName();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.location.ILocationSettingsService
            public void subscribeLocationCallback(ILocationSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeLocationCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.location.ILocationSettingsService
            public void unsubscribeLocationCallback(ILocationSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeLocationCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ILocationSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
