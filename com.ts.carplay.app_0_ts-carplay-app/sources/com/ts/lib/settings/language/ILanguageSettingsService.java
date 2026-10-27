package com.ts.lib.settings.language;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.language.ILanguageSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface ILanguageSettingsService extends IInterface {
    int getLanguage() throws RemoteException;

    int setLanguage(int i) throws RemoteException;

    void subscribeLanguageCallback(ILanguageSettingsCallback iLanguageSettingsCallback) throws RemoteException;

    void unsubscribeLanguageCallback(ILanguageSettingsCallback iLanguageSettingsCallback) throws RemoteException;

    public static class Default implements ILanguageSettingsService {
        @Override // com.ts.lib.settings.language.ILanguageSettingsService
        public int getLanguage() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.language.ILanguageSettingsService
        public int setLanguage(int value) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.language.ILanguageSettingsService
        public void subscribeLanguageCallback(ILanguageSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.language.ILanguageSettingsService
        public void unsubscribeLanguageCallback(ILanguageSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ILanguageSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.language.ILanguageSettingsService";
        static final int TRANSACTION_getLanguage = 1;
        static final int TRANSACTION_setLanguage = 2;
        static final int TRANSACTION_subscribeLanguageCallback = 3;
        static final int TRANSACTION_unsubscribeLanguageCallback = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILanguageSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ILanguageSettingsService)) {
                return (ILanguageSettingsService) iin;
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
                int _result = getLanguage();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                int _result2 = setLanguage(_arg0);
                reply.writeNoException();
                reply.writeInt(_result2);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                ILanguageSettingsCallback _arg02 = ILanguageSettingsCallback.Stub.asInterface(data.readStrongBinder());
                subscribeLanguageCallback(_arg02);
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
            ILanguageSettingsCallback _arg03 = ILanguageSettingsCallback.Stub.asInterface(data.readStrongBinder());
            unsubscribeLanguageCallback(_arg03);
            return true;
        }

        private static class Proxy implements ILanguageSettingsService {
            public static ILanguageSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.language.ILanguageSettingsService
            public int getLanguage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLanguage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.language.ILanguageSettingsService
            public int setLanguage(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLanguage(value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.language.ILanguageSettingsService
            public void subscribeLanguageCallback(ILanguageSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeLanguageCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.language.ILanguageSettingsService
            public void unsubscribeLanguageCallback(ILanguageSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeLanguageCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILanguageSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ILanguageSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
