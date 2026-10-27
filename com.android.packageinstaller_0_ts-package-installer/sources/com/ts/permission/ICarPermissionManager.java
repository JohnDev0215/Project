package com.ts.permission;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPermissionManager extends IInterface {
    int checkPermission(String str, String str2) throws RemoteException;

    void handlePackagePostInstall(String str, boolean z) throws RemoteException;

    void installPackageByPi(String str, String str2) throws RemoteException;

    void installStageByPms(String str, String str2) throws RemoteException;

    public static class Default implements ICarPermissionManager {
        @Override // com.ts.permission.ICarPermissionManager
        public int checkPermission(String packageName, String permission) throws RemoteException {
            return 0;
        }

        @Override // com.ts.permission.ICarPermissionManager
        public void installPackageByPi(String packageName, String installerPackageName) throws RemoteException {
        }

        @Override // com.ts.permission.ICarPermissionManager
        public void installStageByPms(String packageName, String installerPackageName) throws RemoteException {
        }

        @Override // com.ts.permission.ICarPermissionManager
        public void handlePackagePostInstall(String packageName, boolean success) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarPermissionManager {
        private static final String DESCRIPTOR = "com.ts.permission.ICarPermissionManager";
        static final int TRANSACTION_checkPermission = 1;
        static final int TRANSACTION_handlePackagePostInstall = 4;
        static final int TRANSACTION_installPackageByPi = 2;
        static final int TRANSACTION_installStageByPms = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPermissionManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPermissionManager)) {
                return (ICarPermissionManager) iin;
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
                String _arg0 = data.readString();
                String _arg1 = data.readString();
                int _result = checkPermission(_arg0, _arg1);
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                String _arg02 = data.readString();
                String _arg12 = data.readString();
                installPackageByPi(_arg02, _arg12);
                reply.writeNoException();
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String _arg03 = data.readString();
                String _arg13 = data.readString();
                installStageByPms(_arg03, _arg13);
                reply.writeNoException();
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
            String _arg04 = data.readString();
            boolean _arg14 = data.readInt() != 0;
            handlePackagePostInstall(_arg04, _arg14);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ICarPermissionManager {
            public static ICarPermissionManager sDefaultImpl;
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

            @Override // com.ts.permission.ICarPermissionManager
            public int checkPermission(String packageName, String permission) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permission);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkPermission(packageName, permission);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.permission.ICarPermissionManager
            public void installPackageByPi(String packageName, String installerPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(installerPackageName);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().installPackageByPi(packageName, installerPackageName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.permission.ICarPermissionManager
            public void installStageByPms(String packageName, String installerPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(installerPackageName);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().installStageByPms(packageName, installerPackageName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.permission.ICarPermissionManager
            public void handlePackagePostInstall(String packageName, boolean success) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(success ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handlePackagePostInstall(packageName, success);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarPermissionManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarPermissionManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
