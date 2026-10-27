package com.ts.ota.otasdkmgr;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IUpdateListener extends IInterface {
    void onDownloadBackupFail(int i) throws RemoteException;

    void onDownloadBackupProgress(int i) throws RemoteException;

    void onDownloadBackupSuccess() throws RemoteException;

    void onNotify(Bundle bundle) throws RemoteException;

    void onTboxConnectState(int i) throws RemoteException;

    void onTboxExportLogProgress(int i) throws RemoteException;

    void onTboxExportLogResult(int i) throws RemoteException;

    void onTboxUpdateProgress(int i) throws RemoteException;

    void onTboxUpdateResult(int i) throws RemoteException;

    void onUpdateFail(int i) throws RemoteException;

    void onUpdateProgress(int i) throws RemoteException;

    void onUpdateSuccess() throws RemoteException;

    public static class Default implements IUpdateListener {
        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onUpdateSuccess() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onUpdateProgress(int progress) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onUpdateFail(int normalCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onDownloadBackupSuccess() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onDownloadBackupProgress(int progress) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onDownloadBackupFail(int errorCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onTboxUpdateResult(int result) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onTboxUpdateProgress(int progress) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onTboxConnectState(int tboxState) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onTboxExportLogProgress(int progress) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onTboxExportLogResult(int result) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IUpdateListener
        public void onNotify(Bundle notify) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IUpdateListener {
        private static final String DESCRIPTOR = "com.ts.ota.otasdkmgr.IUpdateListener";
        static final int TRANSACTION_onDownloadBackupFail = 6;
        static final int TRANSACTION_onDownloadBackupProgress = 5;
        static final int TRANSACTION_onDownloadBackupSuccess = 4;
        static final int TRANSACTION_onNotify = 12;
        static final int TRANSACTION_onTboxConnectState = 9;
        static final int TRANSACTION_onTboxExportLogProgress = 10;
        static final int TRANSACTION_onTboxExportLogResult = 11;
        static final int TRANSACTION_onTboxUpdateProgress = 8;
        static final int TRANSACTION_onTboxUpdateResult = 7;
        static final int TRANSACTION_onUpdateFail = 3;
        static final int TRANSACTION_onUpdateProgress = 2;
        static final int TRANSACTION_onUpdateSuccess = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUpdateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IUpdateListener)) {
                return (IUpdateListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onUpdateSuccess();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    onUpdateProgress(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onUpdateFail(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onDownloadBackupSuccess();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    onDownloadBackupProgress(_arg04);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    onDownloadBackupFail(_arg05);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    onTboxUpdateResult(_arg06);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    onTboxUpdateProgress(_arg07);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    onTboxConnectState(_arg08);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    onTboxExportLogProgress(_arg09);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    onTboxExportLogResult(_arg010);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onNotify(_arg0);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IUpdateListener {
            public static IUpdateListener sDefaultImpl;
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

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onUpdateSuccess() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUpdateSuccess();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onUpdateProgress(int progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(progress);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUpdateProgress(progress);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onUpdateFail(int normalCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(normalCode);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUpdateFail(normalCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onDownloadBackupSuccess() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadBackupSuccess();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onDownloadBackupProgress(int progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(progress);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadBackupProgress(progress);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onDownloadBackupFail(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadBackupFail(errorCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onTboxUpdateResult(int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTboxUpdateResult(result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onTboxUpdateProgress(int progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(progress);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTboxUpdateProgress(progress);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onTboxConnectState(int tboxState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(tboxState);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTboxConnectState(tboxState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onTboxExportLogProgress(int progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(progress);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTboxExportLogProgress(progress);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onTboxExportLogResult(int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTboxExportLogResult(result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IUpdateListener
            public void onNotify(Bundle notify) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notify != null) {
                        _data.writeInt(1);
                        notify.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotify(notify);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUpdateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IUpdateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
