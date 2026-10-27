package com.ts.lib.settings.panorama;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IPanoramaSettingsCallback extends IInterface {
    void onACUStateChanged(int i) throws RemoteException;

    void onBackLineConfigChanged(int i) throws RemoteException;

    void onCameraStateChanged(int i, int i2) throws RemoteException;

    void onCameraUseChange(String str, boolean z) throws RemoteException;

    void onCarInfoChanged(int i) throws RemoteException;

    void onDisplayModeChanged(int i) throws RemoteException;

    void onEmergencyButtonStateChanged(int i) throws RemoteException;

    void onLVDSStateChanged(int i) throws RemoteException;

    void onPanOutputSignalChanged(int i) throws RemoteException;

    void onPanoAPAStateChanged(int i) throws RemoteException;

    void onPanoOutputStateChanged(int i) throws RemoteException;

    void onPanoRotationChanged(int i) throws RemoteException;

    void onPanoWorkStateChanged(int i) throws RemoteException;

    void onPanoramaOnlineStateChanged(int i) throws RemoteException;

    void onPanoramaTransparenceChanged(int i) throws RemoteException;

    void onRFCameraSwitchStateChanged(int i) throws RemoteException;

    public static class Default implements IPanoramaSettingsCallback {
        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoOutputStateChanged(int mode) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoWorkStateChanged(int mode) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanOutputSignalChanged(int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onBackLineConfigChanged(int mode) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoramaOnlineStateChanged(int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onRFCameraSwitchStateChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoRotationChanged(int value) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onLVDSStateChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onDisplayModeChanged(int mode) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onEmergencyButtonStateChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onACUStateChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onCameraUseChange(String packageName, boolean state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onCarInfoChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoramaTransparenceChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoAPAStateChanged(int state) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onCameraStateChanged(int cameraId, int state) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPanoramaSettingsCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.panorama.IPanoramaSettingsCallback";
        static final int TRANSACTION_onACUStateChanged = 11;
        static final int TRANSACTION_onBackLineConfigChanged = 4;
        static final int TRANSACTION_onCameraStateChanged = 16;
        static final int TRANSACTION_onCameraUseChange = 12;
        static final int TRANSACTION_onCarInfoChanged = 13;
        static final int TRANSACTION_onDisplayModeChanged = 9;
        static final int TRANSACTION_onEmergencyButtonStateChanged = 10;
        static final int TRANSACTION_onLVDSStateChanged = 8;
        static final int TRANSACTION_onPanOutputSignalChanged = 3;
        static final int TRANSACTION_onPanoAPAStateChanged = 15;
        static final int TRANSACTION_onPanoOutputStateChanged = 1;
        static final int TRANSACTION_onPanoRotationChanged = 7;
        static final int TRANSACTION_onPanoWorkStateChanged = 2;
        static final int TRANSACTION_onPanoramaOnlineStateChanged = 5;
        static final int TRANSACTION_onPanoramaTransparenceChanged = 14;
        static final int TRANSACTION_onRFCameraSwitchStateChanged = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPanoramaSettingsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IPanoramaSettingsCallback)) {
                return (IPanoramaSettingsCallback) iin;
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
                    onPanoOutputStateChanged(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    onPanoWorkStateChanged(_arg02);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onPanOutputSignalChanged(_arg03);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    onBackLineConfigChanged(_arg04);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    onPanoramaOnlineStateChanged(_arg05);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    onRFCameraSwitchStateChanged(_arg06);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    onPanoRotationChanged(_arg07);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    onLVDSStateChanged(_arg08);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    onDisplayModeChanged(_arg09);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    onEmergencyButtonStateChanged(_arg010);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg011 = data.readInt();
                    onACUStateChanged(_arg011);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    boolean _arg1 = data.readInt() != 0;
                    onCameraUseChange(_arg012, _arg1);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg013 = data.readInt();
                    onCarInfoChanged(_arg013);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    onPanoramaTransparenceChanged(_arg014);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg015 = data.readInt();
                    onPanoAPAStateChanged(_arg015);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg016 = data.readInt();
                    int _arg12 = data.readInt();
                    onCameraStateChanged(_arg016, _arg12);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IPanoramaSettingsCallback {
            public static IPanoramaSettingsCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onPanoOutputStateChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanoOutputStateChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onPanoWorkStateChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanoWorkStateChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onPanOutputSignalChanged(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanOutputSignalChanged(value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onBackLineConfigChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBackLineConfigChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onPanoramaOnlineStateChanged(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanoramaOnlineStateChanged(value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onRFCameraSwitchStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRFCameraSwitchStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onPanoRotationChanged(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanoRotationChanged(value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onLVDSStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLVDSStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onDisplayModeChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayModeChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onEmergencyButtonStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEmergencyButtonStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onACUStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onACUStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onCameraUseChange(String packageName, boolean state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(state ? 1 : 0);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCameraUseChange(packageName, state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onCarInfoChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarInfoChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onPanoramaTransparenceChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanoramaTransparenceChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onPanoAPAStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanoAPAStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
            public void onCameraStateChanged(int cameraId, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cameraId);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCameraStateChanged(cameraId, state);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPanoramaSettingsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IPanoramaSettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
