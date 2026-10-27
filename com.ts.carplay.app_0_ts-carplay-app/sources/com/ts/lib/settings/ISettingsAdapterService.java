package com.ts.lib.settings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ISettingsAdapterService extends IInterface {
    IBinder getSettingsAdapterService(String str) throws RemoteException;

    boolean isServiceBound() throws RemoteException;

    public static class Default implements ISettingsAdapterService {
        @Override // com.ts.lib.settings.ISettingsAdapterService
        public boolean isServiceBound() throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.settings.ISettingsAdapterService
        public IBinder getSettingsAdapterService(String serviceName) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISettingsAdapterService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.ISettingsAdapterService";
        static final int TRANSACTION_getSettingsAdapterService = 2;
        static final int TRANSACTION_isServiceBound = 1;

        public Stub() {
            attachInterface(this, "com.ts.lib.settings.ISettingsAdapterService");
        }

        public static ISettingsAdapterService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.ts.lib.settings.ISettingsAdapterService");
            if (iin != null && (iin instanceof ISettingsAdapterService)) {
                return (ISettingsAdapterService) iin;
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
                parcel.enforceInterface("com.ts.lib.settings.ISettingsAdapterService");
                boolean zIsServiceBound = isServiceBound();
                parcel2.writeNoException();
                parcel2.writeInt(zIsServiceBound ? 1 : 0);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ts.lib.settings.ISettingsAdapterService");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ts.lib.settings.ISettingsAdapterService");
            IBinder settingsAdapterService = getSettingsAdapterService(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(settingsAdapterService);
            return true;
        }

        private static class Proxy implements ISettingsAdapterService {
            public static ISettingsAdapterService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.ts.lib.settings.ISettingsAdapterService";
            }

            @Override // com.ts.lib.settings.ISettingsAdapterService
            public boolean isServiceBound() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.ts.lib.settings.ISettingsAdapterService");
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isServiceBound();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.ISettingsAdapterService
            public IBinder getSettingsAdapterService(String serviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.ts.lib.settings.ISettingsAdapterService");
                    _data.writeString(serviceName);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsAdapterService(serviceName);
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISettingsAdapterService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ISettingsAdapterService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
