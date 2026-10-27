package com.ts.lib.settings.dms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IDmsSettingsCallback extends IInterface {
    void onAttentionDetectChanged(int i) throws RemoteException;

    void onAttentionSwitchChanged(int i) throws RemoteException;

    void onBlockStatusChanged(int i) throws RemoteException;

    void onDmsModeChanged(int i) throws RemoteException;

    void onDmsSwitchChanged(int i) throws RemoteException;

    void onDriverNotResponse() throws RemoteException;

    void onFatigueDetectChanged(int i) throws RemoteException;

    void onFatigueSwitchChanged(int i) throws RemoteException;

    void onNotifyDmsServerState(int i) throws RemoteException;

    void onRestoreFatigueDetect() throws RemoteException;

    public static class Default implements IDmsSettingsCallback {
        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onDmsSwitchChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onFatigueSwitchChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onAttentionSwitchChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onFatigueDetectChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onAttentionDetectChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onBlockStatusChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onDriverNotResponse() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onRestoreFatigueDetect() throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onNotifyDmsServerState(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onDmsModeChanged(int mode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDmsSettingsCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.dms.IDmsSettingsCallback";
        static final int TRANSACTION_onAttentionDetectChanged = 5;
        static final int TRANSACTION_onAttentionSwitchChanged = 3;
        static final int TRANSACTION_onBlockStatusChanged = 6;
        static final int TRANSACTION_onDmsModeChanged = 10;
        static final int TRANSACTION_onDmsSwitchChanged = 1;
        static final int TRANSACTION_onDriverNotResponse = 7;
        static final int TRANSACTION_onFatigueDetectChanged = 4;
        static final int TRANSACTION_onFatigueSwitchChanged = 2;
        static final int TRANSACTION_onNotifyDmsServerState = 9;
        static final int TRANSACTION_onRestoreFatigueDetect = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDmsSettingsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IDmsSettingsCallback)) {
                return (IDmsSettingsCallback) iin;
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
                    onDmsSwitchChanged(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    onFatigueSwitchChanged(_arg02);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onAttentionSwitchChanged(_arg03);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    onFatigueDetectChanged(_arg04);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    onAttentionDetectChanged(_arg05);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    onBlockStatusChanged(_arg06);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onDriverNotResponse();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    onRestoreFatigueDetect();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    onNotifyDmsServerState(_arg07);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    onDmsModeChanged(_arg08);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IDmsSettingsCallback {
            public static IDmsSettingsCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onDmsSwitchChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDmsSwitchChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onFatigueSwitchChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFatigueSwitchChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onAttentionSwitchChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAttentionSwitchChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onFatigueDetectChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFatigueDetectChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onAttentionDetectChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAttentionDetectChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onBlockStatusChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBlockStatusChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onDriverNotResponse() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDriverNotResponse();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onRestoreFatigueDetect() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRestoreFatigueDetect();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onNotifyDmsServerState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyDmsServerState(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
            public void onDmsModeChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDmsModeChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDmsSettingsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IDmsSettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
