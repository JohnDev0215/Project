package com.ts.lib.settings.cluster;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IClusterSettingsCallback extends IInterface {
    void onDayNightModeChanged(int i) throws RemoteException;

    void onINSAdasCenterDisplayChanged(int i) throws RemoteException;

    void onINSCenterDisplay(int i) throws RemoteException;

    public static class Default implements IClusterSettingsCallback {
        @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
        public void onINSAdasCenterDisplayChanged(int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
        public void onINSCenterDisplay(int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
        public void onDayNightModeChanged(int mode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IClusterSettingsCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.cluster.IClusterSettingsCallback";
        static final int TRANSACTION_onDayNightModeChanged = 3;
        static final int TRANSACTION_onINSAdasCenterDisplayChanged = 1;
        static final int TRANSACTION_onINSCenterDisplay = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IClusterSettingsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IClusterSettingsCallback)) {
                return (IClusterSettingsCallback) iin;
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
                onINSAdasCenterDisplayChanged(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onINSCenterDisplay(_arg02);
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
            int _arg03 = data.readInt();
            onDayNightModeChanged(_arg03);
            return true;
        }

        private static class Proxy implements IClusterSettingsCallback {
            public static IClusterSettingsCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
            public void onINSAdasCenterDisplayChanged(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onINSAdasCenterDisplayChanged(value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
            public void onINSCenterDisplay(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onINSCenterDisplay(value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
            public void onDayNightModeChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDayNightModeChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IClusterSettingsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IClusterSettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
