package com.ts.lib.settings.theme;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.theme.IThemeSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IThemeSettingsService extends IInterface {
    int getINSTheme() throws RemoteException;

    int setINSTheme(int i) throws RemoteException;

    void subscribeThemeCallback(IThemeSettingsCallback iThemeSettingsCallback) throws RemoteException;

    void unsubscribeThemeCallback(IThemeSettingsCallback iThemeSettingsCallback) throws RemoteException;

    public static class Default implements IThemeSettingsService {
        @Override // com.ts.lib.settings.theme.IThemeSettingsService
        public int getINSTheme() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.theme.IThemeSettingsService
        public int setINSTheme(int value) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.theme.IThemeSettingsService
        public void subscribeThemeCallback(IThemeSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.theme.IThemeSettingsService
        public void unsubscribeThemeCallback(IThemeSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IThemeSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.theme.IThemeSettingsService";
        static final int TRANSACTION_getINSTheme = 1;
        static final int TRANSACTION_setINSTheme = 2;
        static final int TRANSACTION_subscribeThemeCallback = 3;
        static final int TRANSACTION_unsubscribeThemeCallback = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IThemeSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IThemeSettingsService)) {
                return (IThemeSettingsService) iin;
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
                int _result = getINSTheme();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                int _result2 = setINSTheme(_arg0);
                reply.writeNoException();
                reply.writeInt(_result2);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                IThemeSettingsCallback _arg02 = IThemeSettingsCallback.Stub.asInterface(data.readStrongBinder());
                subscribeThemeCallback(_arg02);
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
            IThemeSettingsCallback _arg03 = IThemeSettingsCallback.Stub.asInterface(data.readStrongBinder());
            unsubscribeThemeCallback(_arg03);
            return true;
        }

        private static class Proxy implements IThemeSettingsService {
            public static IThemeSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.theme.IThemeSettingsService
            public int getINSTheme() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getINSTheme();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.theme.IThemeSettingsService
            public int setINSTheme(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setINSTheme(value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.theme.IThemeSettingsService
            public void subscribeThemeCallback(IThemeSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeThemeCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.theme.IThemeSettingsService
            public void unsubscribeThemeCallback(IThemeSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeThemeCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IThemeSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IThemeSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
