package com.ts.lib.settings.appconfig;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.appconfig.IAppConfigSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IAppConfigSettingsService extends IInterface {
    int setCarType(int i) throws RemoteException;

    int setConfigArea(String str) throws RemoteException;

    int setCpaaInstall(boolean z) throws RemoteException;

    int setCpaaInstallType(int i, boolean z) throws RemoteException;

    void setDefaultLanguages(int i) throws RemoteException;

    void subscribeAppConfigCallback(IAppConfigSettingsCallback iAppConfigSettingsCallback) throws RemoteException;

    void unsubscribeAppConfigCallback(IAppConfigSettingsCallback iAppConfigSettingsCallback) throws RemoteException;

    public static class Default implements IAppConfigSettingsService {
        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
        public int setConfigArea(String code) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
        public int setCpaaInstall(boolean status) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
        public int setCpaaInstallType(int type, boolean status) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
        public int setCarType(int code) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
        public void setDefaultLanguages(int countryCode) throws RemoteException {
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
        public void subscribeAppConfigCallback(IAppConfigSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
        public void unsubscribeAppConfigCallback(IAppConfigSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAppConfigSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.appconfig.IAppConfigSettingsService";
        static final int TRANSACTION_setCarType = 4;
        static final int TRANSACTION_setConfigArea = 1;
        static final int TRANSACTION_setCpaaInstall = 2;
        static final int TRANSACTION_setCpaaInstallType = 3;
        static final int TRANSACTION_setDefaultLanguages = 5;
        static final int TRANSACTION_subscribeAppConfigCallback = 6;
        static final int TRANSACTION_unsubscribeAppConfigCallback = 7;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppConfigSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAppConfigSettingsService)) {
                return (IAppConfigSettingsService) iin;
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
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _result = setConfigArea(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    _arg1 = data.readInt() != 0;
                    int _result2 = setCpaaInstall(_arg1);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    _arg1 = data.readInt() != 0;
                    int _result3 = setCpaaInstallType(_arg02, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    int _result4 = setCarType(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    setDefaultLanguages(_arg04);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    IAppConfigSettingsCallback _arg05 = IAppConfigSettingsCallback.Stub.asInterface(data.readStrongBinder());
                    subscribeAppConfigCallback(_arg05);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    IAppConfigSettingsCallback _arg06 = IAppConfigSettingsCallback.Stub.asInterface(data.readStrongBinder());
                    unsubscribeAppConfigCallback(_arg06);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAppConfigSettingsService {
            public static IAppConfigSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
            public int setConfigArea(String code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(code);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConfigArea(code);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
            public int setCpaaInstall(boolean status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCpaaInstall(status);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
            public int setCpaaInstallType(int type, boolean status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeInt(status ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCpaaInstallType(type, status);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
            public int setCarType(int code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCarType(code);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
            public void setDefaultLanguages(int countryCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(countryCode);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultLanguages(countryCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
            public void subscribeAppConfigCallback(IAppConfigSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeAppConfigCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsService
            public void unsubscribeAppConfigCallback(IAppConfigSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeAppConfigCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppConfigSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAppConfigSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
