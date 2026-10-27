package com.ts.lib.settings.boot;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAppBootSettingsService extends IInterface {
    boolean getStartupAppEnable(String str) throws RemoteException;

    List<BootApp> getStartupAppList() throws RemoteException;

    int setStartupAppEnable(String str, boolean z) throws RemoteException;

    public static class Default implements IAppBootSettingsService {
        @Override // com.ts.lib.settings.boot.IAppBootSettingsService
        public List<BootApp> getStartupAppList() throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.settings.boot.IAppBootSettingsService
        public boolean getStartupAppEnable(String pkg) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.settings.boot.IAppBootSettingsService
        public int setStartupAppEnable(String pkg, boolean enable) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAppBootSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.boot.IAppBootSettingsService";
        static final int TRANSACTION_getStartupAppEnable = 2;
        static final int TRANSACTION_getStartupAppList = 1;
        static final int TRANSACTION_setStartupAppEnable = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppBootSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAppBootSettingsService)) {
                return (IAppBootSettingsService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                List<BootApp> startupAppList = getStartupAppList();
                parcel2.writeNoException();
                parcel2.writeTypedList(startupAppList);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean startupAppEnable = getStartupAppEnable(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(startupAppEnable ? 1 : 0);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            int startupAppEnable2 = setStartupAppEnable(parcel.readString(), parcel.readInt() != 0);
            parcel2.writeNoException();
            parcel2.writeInt(startupAppEnable2);
            return true;
        }

        private static class Proxy implements IAppBootSettingsService {
            public static IAppBootSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.boot.IAppBootSettingsService
            public List<BootApp> getStartupAppList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStartupAppList();
                    }
                    _reply.readException();
                    List<BootApp> _result = _reply.createTypedArrayList(BootApp.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.boot.IAppBootSettingsService
            public boolean getStartupAppEnable(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStartupAppEnable(pkg);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.boot.IAppBootSettingsService
            public int setStartupAppEnable(String pkg, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setStartupAppEnable(pkg, enable);
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

        public static boolean setDefaultImpl(IAppBootSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAppBootSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
