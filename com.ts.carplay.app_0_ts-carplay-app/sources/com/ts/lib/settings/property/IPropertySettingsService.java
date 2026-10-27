package com.ts.lib.settings.property;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IPropertySettingsService extends IInterface {
    String getBtContact(String str) throws RemoteException;

    boolean getSettingsBoolean(String str) throws RemoteException;

    int getSettingsInt(String str) throws RemoteException;

    String getSettingsString(String str) throws RemoteException;

    String getSystemPersist(String str) throws RemoteException;

    boolean getSystemPersistBoolean(String str) throws RemoteException;

    int getSystemPersistInt(String str) throws RemoteException;

    void setSettingsBoolean(String str, int i) throws RemoteException;

    void setSettingsInt(String str, int i) throws RemoteException;

    void setSystemPersist(String str, String str2) throws RemoteException;

    public static class Default implements IPropertySettingsService {
        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public String getSystemPersist(String key) throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public int getSystemPersistInt(String key) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public boolean getSystemPersistBoolean(String key) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public int getSettingsInt(String key) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public boolean getSettingsBoolean(String key) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public String getSettingsString(String key) throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public String getBtContact(String address) throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public void setSettingsInt(String key, int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public void setSettingsBoolean(String key, int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.property.IPropertySettingsService
        public void setSystemPersist(String key, String value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPropertySettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.property.IPropertySettingsService";
        static final int TRANSACTION_getBtContact = 7;
        static final int TRANSACTION_getSettingsBoolean = 5;
        static final int TRANSACTION_getSettingsInt = 4;
        static final int TRANSACTION_getSettingsString = 6;
        static final int TRANSACTION_getSystemPersist = 1;
        static final int TRANSACTION_getSystemPersistBoolean = 3;
        static final int TRANSACTION_getSystemPersistInt = 2;
        static final int TRANSACTION_setSettingsBoolean = 9;
        static final int TRANSACTION_setSettingsInt = 8;
        static final int TRANSACTION_setSystemPersist = 10;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPropertySettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IPropertySettingsService)) {
                return (IPropertySettingsService) iin;
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
                    String systemPersist = getSystemPersist(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(systemPersist);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int systemPersistInt = getSystemPersistInt(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(systemPersistInt);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean systemPersistBoolean = getSystemPersistBoolean(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(systemPersistBoolean ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int settingsInt = getSettingsInt(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(settingsInt);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean settingsBoolean = getSettingsBoolean(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(settingsBoolean ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String settingsString = getSettingsString(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(settingsString);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String btContact = getBtContact(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(btContact);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSettingsInt(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSettingsBoolean(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSystemPersist(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPropertySettingsService {
            public static IPropertySettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public String getSystemPersist(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPersist(key);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public int getSystemPersistInt(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPersistInt(key);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public boolean getSystemPersistBoolean(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPersistBoolean(key);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public int getSettingsInt(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsInt(key);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public boolean getSettingsBoolean(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsBoolean(key);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public String getSettingsString(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsString(key);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public String getBtContact(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBtContact(address);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public void setSettingsInt(String key, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSettingsInt(key, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public void setSettingsBoolean(String key, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSettingsBoolean(key, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.property.IPropertySettingsService
            public void setSystemPersist(String key, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSystemPersist(key, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPropertySettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IPropertySettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
