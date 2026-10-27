package com.android.car.setupwizardlib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes5.dex */
public interface IInitialLockSetupService extends IInterface {
    int checkValidLock(int i, byte[] bArr) throws RemoteException;

    LockConfig getLockConfig(int i) throws RemoteException;

    int getServiceVersion() throws RemoteException;

    int setLock(int i, byte[] bArr) throws RemoteException;

    public static class Default implements IInitialLockSetupService {
        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public int getServiceVersion() throws RemoteException {
            return 0;
        }

        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public LockConfig getLockConfig(int lockType) throws RemoteException {
            return null;
        }

        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public int checkValidLock(int lockType, byte[] password) throws RemoteException {
            return 0;
        }

        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public int setLock(int lockType, byte[] password) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IInitialLockSetupService {
        private static final String DESCRIPTOR = "com.android.car.setupwizardlib.IInitialLockSetupService";
        static final int TRANSACTION_checkValidLock = 3;
        static final int TRANSACTION_getLockConfig = 2;
        static final int TRANSACTION_getServiceVersion = 1;
        static final int TRANSACTION_setLock = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInitialLockSetupService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IInitialLockSetupService)) {
                return (IInitialLockSetupService) iin;
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
                int _result = getServiceVersion();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                LockConfig _result2 = getLockConfig(_arg0);
                reply.writeNoException();
                if (_result2 != null) {
                    reply.writeInt(1);
                    _result2.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                byte[] _arg1 = data.createByteArray();
                int _result3 = checkValidLock(_arg02, _arg1);
                reply.writeNoException();
                reply.writeInt(_result3);
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
            int _arg03 = data.readInt();
            byte[] _arg12 = data.createByteArray();
            int _result4 = setLock(_arg03, _arg12);
            reply.writeNoException();
            reply.writeInt(_result4);
            return true;
        }

        private static class Proxy implements IInitialLockSetupService {
            public static IInitialLockSetupService sDefaultImpl;
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

            @Override // com.android.car.setupwizardlib.IInitialLockSetupService
            public int getServiceVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServiceVersion();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.car.setupwizardlib.IInitialLockSetupService
            public LockConfig getLockConfig(int lockType) throws RemoteException {
                LockConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(lockType);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLockConfig(lockType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LockConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.car.setupwizardlib.IInitialLockSetupService
            public int checkValidLock(int lockType, byte[] password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(lockType);
                    _data.writeByteArray(password);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkValidLock(lockType, password);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.car.setupwizardlib.IInitialLockSetupService
            public int setLock(int lockType, byte[] password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(lockType);
                    _data.writeByteArray(password);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLock(lockType, password);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInitialLockSetupService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IInitialLockSetupService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
