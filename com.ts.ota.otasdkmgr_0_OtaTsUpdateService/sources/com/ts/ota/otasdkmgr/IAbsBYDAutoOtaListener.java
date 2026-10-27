package com.ts.ota.otasdkmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAbsBYDAutoOtaListener extends IInterface {
    void on7dfExistState(boolean z) throws RemoteException;

    void onBackupAreaDownloadState(int i) throws RemoteException;

    void onBcmOtaState(int i) throws RemoteException;

    void onDcState(int i) throws RemoteException;

    void onIg1RelayState(int i) throws RemoteException;

    void onIg3RelayState(int i) throws RemoteException;

    void onIg4RelayState(int i) throws RemoteException;

    void onLFDoorLockStatus(int i) throws RemoteException;

    void onNfcOperationCommand(int i) throws RemoteException;

    void onProhibitPowerOnState(int i) throws RemoteException;

    void onSmartPowerState(int i) throws RemoteException;

    void onSocValueChanged(int i) throws RemoteException;

    void onStartKeyState(int i) throws RemoteException;

    void onTurnOffState(int i) throws RemoteException;

    public static class Default implements IAbsBYDAutoOtaListener {
        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onTurnOffState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onSmartPowerState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onDcState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onIg3RelayState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onBackupAreaDownloadState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void on7dfExistState(boolean exist) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onBcmOtaState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onLFDoorLockStatus(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onProhibitPowerOnState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onStartKeyState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onIg1RelayState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onIg4RelayState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onNfcOperationCommand(int cmd) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
        public void onSocValueChanged(int value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAbsBYDAutoOtaListener {
        private static final String DESCRIPTOR = "com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener";
        static final int TRANSACTION_on7dfExistState = 6;
        static final int TRANSACTION_onBackupAreaDownloadState = 5;
        static final int TRANSACTION_onBcmOtaState = 7;
        static final int TRANSACTION_onDcState = 3;
        static final int TRANSACTION_onIg1RelayState = 11;
        static final int TRANSACTION_onIg3RelayState = 4;
        static final int TRANSACTION_onIg4RelayState = 12;
        static final int TRANSACTION_onLFDoorLockStatus = 8;
        static final int TRANSACTION_onNfcOperationCommand = 13;
        static final int TRANSACTION_onProhibitPowerOnState = 9;
        static final int TRANSACTION_onSmartPowerState = 2;
        static final int TRANSACTION_onSocValueChanged = 14;
        static final int TRANSACTION_onStartKeyState = 10;
        static final int TRANSACTION_onTurnOffState = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAbsBYDAutoOtaListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAbsBYDAutoOtaListener)) {
                return (IAbsBYDAutoOtaListener) iin;
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
                    onTurnOffState(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    onSmartPowerState(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onDcState(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    onIg3RelayState(_arg04);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    onBackupAreaDownloadState(_arg05);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg06 = data.readInt() != 0;
                    on7dfExistState(_arg06);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    onBcmOtaState(_arg07);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    onLFDoorLockStatus(_arg08);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    onProhibitPowerOnState(_arg09);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    onStartKeyState(_arg010);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg011 = data.readInt();
                    onIg1RelayState(_arg011);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    onIg4RelayState(_arg012);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg013 = data.readInt();
                    onNfcOperationCommand(_arg013);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    onSocValueChanged(_arg014);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAbsBYDAutoOtaListener {
            public static IAbsBYDAutoOtaListener sDefaultImpl;
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

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onTurnOffState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTurnOffState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onSmartPowerState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSmartPowerState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onDcState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDcState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onIg3RelayState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIg3RelayState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onBackupAreaDownloadState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBackupAreaDownloadState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void on7dfExistState(boolean exist) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(exist ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().on7dfExistState(exist);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onBcmOtaState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBcmOtaState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onLFDoorLockStatus(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLFDoorLockStatus(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onProhibitPowerOnState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProhibitPowerOnState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onStartKeyState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStartKeyState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onIg1RelayState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIg1RelayState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onIg4RelayState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIg4RelayState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onNfcOperationCommand(int cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cmd);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNfcOperationCommand(cmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener
            public void onSocValueChanged(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSocValueChanged(value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAbsBYDAutoOtaListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAbsBYDAutoOtaListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
