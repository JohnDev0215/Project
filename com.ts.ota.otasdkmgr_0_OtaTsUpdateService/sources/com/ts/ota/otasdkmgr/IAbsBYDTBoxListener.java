package com.ts.ota.otasdkmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAbsBYDTBoxListener extends IInterface {
    void onAppActivateResult(int i, int i2) throws RemoteException;

    void onDeleteAllNfcKeyResult(int i, int i2) throws RemoteException;

    void onDeleteNfcKeyByKeyNoResult(String str, int i) throws RemoteException;

    void onDeleteNfcKeyResult(int i, int i2) throws RemoteException;

    void onDeleteOwnIphoneKeyResult(String str, int i) throws RemoteException;

    void onKeyListPageRefresh() throws RemoteException;

    void onKeyListResponse(int i, List<DigitalKeyBeanSdk> list) throws RemoteException;

    void onMobileNetworkStateChanged(int i) throws RemoteException;

    void onNfcOperationCommand(int i) throws RemoteException;

    public static class Default implements IAbsBYDTBoxListener {
        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onMobileNetworkStateChanged(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onAppActivateResult(int result, int appTag) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onKeyListResponse(int totalNo, List<DigitalKeyBeanSdk> resp) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteNfcKeyResult(int op, int result) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteAllNfcKeyResult(int type, int result) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteNfcKeyByKeyNoResult(String keyNo, int result) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onDeleteOwnIphoneKeyResult(String keyNo, int result) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onKeyListPageRefresh() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
        public void onNfcOperationCommand(int cmd) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAbsBYDTBoxListener {
        private static final String DESCRIPTOR = "com.ts.ota.otasdkmgr.IAbsBYDTBoxListener";
        static final int TRANSACTION_onAppActivateResult = 2;
        static final int TRANSACTION_onDeleteAllNfcKeyResult = 5;
        static final int TRANSACTION_onDeleteNfcKeyByKeyNoResult = 6;
        static final int TRANSACTION_onDeleteNfcKeyResult = 4;
        static final int TRANSACTION_onDeleteOwnIphoneKeyResult = 7;
        static final int TRANSACTION_onKeyListPageRefresh = 8;
        static final int TRANSACTION_onKeyListResponse = 3;
        static final int TRANSACTION_onMobileNetworkStateChanged = 1;
        static final int TRANSACTION_onNfcOperationCommand = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAbsBYDTBoxListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAbsBYDTBoxListener)) {
                return (IAbsBYDTBoxListener) iin;
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
                    onMobileNetworkStateChanged(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int _arg1 = data.readInt();
                    onAppActivateResult(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    List<DigitalKeyBeanSdk> _arg12 = data.createTypedArrayList(DigitalKeyBeanSdk.CREATOR);
                    onKeyListResponse(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _arg13 = data.readInt();
                    onDeleteNfcKeyResult(_arg04, _arg13);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    int _arg14 = data.readInt();
                    onDeleteAllNfcKeyResult(_arg05, _arg14);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    int _arg15 = data.readInt();
                    onDeleteNfcKeyByKeyNoResult(_arg06, _arg15);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    int _arg16 = data.readInt();
                    onDeleteOwnIphoneKeyResult(_arg07, _arg16);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    onKeyListPageRefresh();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    onNfcOperationCommand(_arg08);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAbsBYDTBoxListener {
            public static IAbsBYDTBoxListener sDefaultImpl;
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

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onMobileNetworkStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMobileNetworkStateChanged(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onAppActivateResult(int result, int appTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(result);
                    _data.writeInt(appTag);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppActivateResult(result, appTag);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onKeyListResponse(int totalNo, List<DigitalKeyBeanSdk> resp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(totalNo);
                    _data.writeTypedList(resp);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyListResponse(totalNo, resp);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onDeleteNfcKeyResult(int op, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(op);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeleteNfcKeyResult(op, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onDeleteAllNfcKeyResult(int type, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeleteAllNfcKeyResult(type, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onDeleteNfcKeyByKeyNoResult(String keyNo, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(keyNo);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeleteNfcKeyByKeyNoResult(keyNo, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onDeleteOwnIphoneKeyResult(String keyNo, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(keyNo);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeleteOwnIphoneKeyResult(keyNo, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onKeyListPageRefresh() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyListPageRefresh();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsBYDTBoxListener
            public void onNfcOperationCommand(int cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cmd);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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
        }

        public static boolean setDefaultImpl(IAbsBYDTBoxListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAbsBYDTBoxListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
