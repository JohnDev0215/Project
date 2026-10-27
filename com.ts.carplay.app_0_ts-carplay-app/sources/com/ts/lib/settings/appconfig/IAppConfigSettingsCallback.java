package com.ts.lib.settings.appconfig;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAppConfigSettingsCallback extends IInterface {
    void onChangeConfigArea(int i) throws RemoteException;

    void onChangedCpaa(int i) throws RemoteException;

    void onStatusChangeCarType(boolean z) throws RemoteException;

    public static class Default implements IAppConfigSettingsCallback {
        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
        public void onChangeConfigArea(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
        public void onChangedCpaa(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
        public void onStatusChangeCarType(boolean status) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAppConfigSettingsCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.appconfig.IAppConfigSettingsCallback";
        static final int TRANSACTION_onChangeConfigArea = 1;
        static final int TRANSACTION_onChangedCpaa = 2;
        static final int TRANSACTION_onStatusChangeCarType = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppConfigSettingsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAppConfigSettingsCallback)) {
                return (IAppConfigSettingsCallback) iin;
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
                int _arg0 = data.readInt();
                onChangeConfigArea(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onChangedCpaa(_arg02);
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            boolean _arg03 = data.readInt() != 0;
            onStatusChangeCarType(_arg03);
            return true;
        }

        private static class Proxy implements IAppConfigSettingsCallback {
            public static IAppConfigSettingsCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
            public void onChangeConfigArea(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChangeConfigArea(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
            public void onChangedCpaa(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChangedCpaa(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
            public void onStatusChangeCarType(boolean status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStatusChangeCarType(status);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppConfigSettingsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAppConfigSettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
