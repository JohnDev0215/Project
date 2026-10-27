package com.ts.lib.settings.key;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IKeySettingsCallback extends IInterface {
    void onIdlerWheel(int i) throws RemoteException;

    void onKeyClick(int i, int i2) throws RemoteException;

    public static class Default implements IKeySettingsCallback {
        @Override // com.ts.lib.settings.key.IKeySettingsCallback
        public void onIdlerWheel(int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsCallback
        public void onKeyClick(int keyValue, int click) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IKeySettingsCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.key.IKeySettingsCallback";
        static final int TRANSACTION_onIdlerWheel = 1;
        static final int TRANSACTION_onKeyClick = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeySettingsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IKeySettingsCallback)) {
                return (IKeySettingsCallback) iin;
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
                onIdlerWheel(_arg0);
                return true;
            }
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            int _arg02 = data.readInt();
            int _arg1 = data.readInt();
            onKeyClick(_arg02, _arg1);
            return true;
        }

        private static class Proxy implements IKeySettingsCallback {
            public static IKeySettingsCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.key.IKeySettingsCallback
            public void onIdlerWheel(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIdlerWheel(value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsCallback
            public void onKeyClick(int keyValue, int click) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(keyValue);
                    _data.writeInt(click);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyClick(keyValue, click);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeySettingsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IKeySettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
