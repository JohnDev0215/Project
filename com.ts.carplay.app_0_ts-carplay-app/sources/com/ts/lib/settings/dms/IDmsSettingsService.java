package com.ts.lib.settings.dms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.dms.IDmsSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IDmsSettingsService extends IInterface {
    void getDmsSwitchChanged() throws RemoteException;

    void queryAttentionDetect() throws RemoteException;

    void queryDmsMode() throws RemoteException;

    void queryDmsServiceStatus() throws RemoteException;

    void queryFatigueDetect() throws RemoteException;

    void setAttentionDetect(int i) throws RemoteException;

    void setDmsMode(int i) throws RemoteException;

    void setDmsSwtichStatus(int i) throws RemoteException;

    void setFatigueDetect(int i) throws RemoteException;

    void setPauseFatigueDetect() throws RemoteException;

    void subscribeDmsCallback(IDmsSettingsCallback iDmsSettingsCallback) throws RemoteException;

    void unsubscribeDmsCallback(IDmsSettingsCallback iDmsSettingsCallback) throws RemoteException;

    public static class Default implements IDmsSettingsService {
        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void queryDmsServiceStatus() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void setDmsSwtichStatus(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void setPauseFatigueDetect() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void setFatigueDetect(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void setAttentionDetect(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void getDmsSwitchChanged() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void queryFatigueDetect() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void queryAttentionDetect() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void setDmsMode(int mode) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void queryDmsMode() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void subscribeDmsCallback(IDmsSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsService
        public void unsubscribeDmsCallback(IDmsSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDmsSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.dms.IDmsSettingsService";
        static final int TRANSACTION_getDmsSwitchChanged = 6;
        static final int TRANSACTION_queryAttentionDetect = 8;
        static final int TRANSACTION_queryDmsMode = 10;
        static final int TRANSACTION_queryDmsServiceStatus = 1;
        static final int TRANSACTION_queryFatigueDetect = 7;
        static final int TRANSACTION_setAttentionDetect = 5;
        static final int TRANSACTION_setDmsMode = 9;
        static final int TRANSACTION_setDmsSwtichStatus = 2;
        static final int TRANSACTION_setFatigueDetect = 4;
        static final int TRANSACTION_setPauseFatigueDetect = 3;
        static final int TRANSACTION_subscribeDmsCallback = 11;
        static final int TRANSACTION_unsubscribeDmsCallback = 12;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDmsSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IDmsSettingsService)) {
                return (IDmsSettingsService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    queryDmsServiceStatus();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    setDmsSwtichStatus(_arg0);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    setPauseFatigueDetect();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    setFatigueDetect(_arg02);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    setAttentionDetect(_arg03);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    getDmsSwitchChanged();
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    queryFatigueDetect();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    queryAttentionDetect();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    setDmsMode(_arg04);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    queryDmsMode();
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    IDmsSettingsCallback _arg05 = IDmsSettingsCallback.Stub.asInterface(data.readStrongBinder());
                    subscribeDmsCallback(_arg05);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    IDmsSettingsCallback _arg06 = IDmsSettingsCallback.Stub.asInterface(data.readStrongBinder());
                    unsubscribeDmsCallback(_arg06);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IDmsSettingsService {
            public static IDmsSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void queryDmsServiceStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryDmsServiceStatus();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void setDmsSwtichStatus(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDmsSwtichStatus(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void setPauseFatigueDetect() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPauseFatigueDetect();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void setFatigueDetect(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFatigueDetect(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void setAttentionDetect(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAttentionDetect(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void getDmsSwitchChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDmsSwitchChanged();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void queryFatigueDetect() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryFatigueDetect();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void queryAttentionDetect() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryAttentionDetect();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void setDmsMode(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDmsMode(mode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void queryDmsMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryDmsMode();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void subscribeDmsCallback(IDmsSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeDmsCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsService
            public void unsubscribeDmsCallback(IDmsSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeDmsCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDmsSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IDmsSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
