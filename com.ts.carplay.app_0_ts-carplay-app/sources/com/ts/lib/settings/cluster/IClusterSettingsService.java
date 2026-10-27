package com.ts.lib.settings.cluster;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.cluster.IClusterSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IClusterSettingsService extends IInterface {
    int getDayNightMode() throws RemoteException;

    int getINSAdasCenterDisplay() throws RemoteException;

    int getINSCenterDisplay() throws RemoteException;

    int setDayNightMode(int i) throws RemoteException;

    int setINSAdasCenterDisplay(int i) throws RemoteException;

    int setINSCenterDisplay(int i) throws RemoteException;

    int setOverseasMapSupplier(int i) throws RemoteException;

    int setSpeedUpCountState(int i) throws RemoteException;

    int setTimeFormat(int i) throws RemoteException;

    void subscribeClusterCallback(IClusterSettingsCallback iClusterSettingsCallback) throws RemoteException;

    void unsubscribeClusterCallback(IClusterSettingsCallback iClusterSettingsCallback) throws RemoteException;

    public static class Default implements IClusterSettingsService {
        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int setINSAdasCenterDisplay(int value) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int getINSAdasCenterDisplay() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int getINSCenterDisplay() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int setINSCenterDisplay(int value) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int setDayNightMode(int mode) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int getDayNightMode() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int setTimeFormat(int value) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int setSpeedUpCountState(int state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public int setOverseasMapSupplier(int state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public void subscribeClusterCallback(IClusterSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsService
        public void unsubscribeClusterCallback(IClusterSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IClusterSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.cluster.IClusterSettingsService";
        static final int TRANSACTION_getDayNightMode = 6;
        static final int TRANSACTION_getINSAdasCenterDisplay = 2;
        static final int TRANSACTION_getINSCenterDisplay = 3;
        static final int TRANSACTION_setDayNightMode = 5;
        static final int TRANSACTION_setINSAdasCenterDisplay = 1;
        static final int TRANSACTION_setINSCenterDisplay = 4;
        static final int TRANSACTION_setOverseasMapSupplier = 9;
        static final int TRANSACTION_setSpeedUpCountState = 8;
        static final int TRANSACTION_setTimeFormat = 7;
        static final int TRANSACTION_subscribeClusterCallback = 10;
        static final int TRANSACTION_unsubscribeClusterCallback = 11;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IClusterSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IClusterSettingsService)) {
                return (IClusterSettingsService) iin;
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
                    int _arg0 = data.readInt();
                    int _result = setINSAdasCenterDisplay(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = getINSAdasCenterDisplay();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getINSCenterDisplay();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int _result4 = setINSCenterDisplay(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    int _result5 = setDayNightMode(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getDayNightMode();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _result7 = setTimeFormat(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    int _result8 = setSpeedUpCountState(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    int _result9 = setOverseasMapSupplier(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    IClusterSettingsCallback _arg07 = IClusterSettingsCallback.Stub.asInterface(data.readStrongBinder());
                    subscribeClusterCallback(_arg07);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    IClusterSettingsCallback _arg08 = IClusterSettingsCallback.Stub.asInterface(data.readStrongBinder());
                    unsubscribeClusterCallback(_arg08);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IClusterSettingsService {
            public static IClusterSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int setINSAdasCenterDisplay(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setINSAdasCenterDisplay(value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int getINSAdasCenterDisplay() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getINSAdasCenterDisplay();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int getINSCenterDisplay() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getINSCenterDisplay();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int setINSCenterDisplay(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setINSCenterDisplay(value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int setDayNightMode(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDayNightMode(mode);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int getDayNightMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDayNightMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int setTimeFormat(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTimeFormat(value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int setSpeedUpCountState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSpeedUpCountState(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public int setOverseasMapSupplier(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOverseasMapSupplier(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public void subscribeClusterCallback(IClusterSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeClusterCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsService
            public void unsubscribeClusterCallback(IClusterSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeClusterCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IClusterSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IClusterSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
