package com.ts.lib.settings.panorama;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.panorama.IPanoramaSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IPanoramaSettingsService extends IInterface {
    int getBackLineConfig() throws RemoteException;

    String getCameraUsePackageName() throws RemoteException;

    int getDisplayMode() throws RemoteException;

    int getPanoOutputSignal() throws RemoteException;

    int getPanoOutputState() throws RemoteException;

    int getPanoRotation() throws RemoteException;

    int getPanoWorkState() throws RemoteException;

    boolean isPanoramaSetting() throws RemoteException;

    boolean isValideCamera(int i) throws RemoteException;

    void requestCameraState(int i) throws RemoteException;

    void subscribePanoramaCallback(IPanoramaSettingsCallback iPanoramaSettingsCallback) throws RemoteException;

    void unsubscribePanoramaCallback(IPanoramaSettingsCallback iPanoramaSettingsCallback) throws RemoteException;

    public static class Default implements IPanoramaSettingsService {
        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public int getPanoOutputSignal() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public int getPanoWorkState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public int getBackLineConfig() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public int getPanoOutputState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public int getPanoRotation() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public int getDisplayMode() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public boolean isValideCamera(int cameraId) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public boolean isPanoramaSetting() throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public String getCameraUsePackageName() throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public void requestCameraState(int cameraId) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public void subscribePanoramaCallback(IPanoramaSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
        public void unsubscribePanoramaCallback(IPanoramaSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPanoramaSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.panorama.IPanoramaSettingsService";
        static final int TRANSACTION_getBackLineConfig = 3;
        static final int TRANSACTION_getCameraUsePackageName = 9;
        static final int TRANSACTION_getDisplayMode = 6;
        static final int TRANSACTION_getPanoOutputSignal = 1;
        static final int TRANSACTION_getPanoOutputState = 4;
        static final int TRANSACTION_getPanoRotation = 5;
        static final int TRANSACTION_getPanoWorkState = 2;
        static final int TRANSACTION_isPanoramaSetting = 8;
        static final int TRANSACTION_isValideCamera = 7;
        static final int TRANSACTION_requestCameraState = 10;
        static final int TRANSACTION_subscribePanoramaCallback = 11;
        static final int TRANSACTION_unsubscribePanoramaCallback = 12;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPanoramaSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IPanoramaSettingsService)) {
                return (IPanoramaSettingsService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int panoOutputSignal = getPanoOutputSignal();
                    parcel2.writeNoException();
                    parcel2.writeInt(panoOutputSignal);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int panoWorkState = getPanoWorkState();
                    parcel2.writeNoException();
                    parcel2.writeInt(panoWorkState);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int backLineConfig = getBackLineConfig();
                    parcel2.writeNoException();
                    parcel2.writeInt(backLineConfig);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int panoOutputState = getPanoOutputState();
                    parcel2.writeNoException();
                    parcel2.writeInt(panoOutputState);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int panoRotation = getPanoRotation();
                    parcel2.writeNoException();
                    parcel2.writeInt(panoRotation);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int displayMode = getDisplayMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(displayMode);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsValideCamera = isValideCamera(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsValideCamera ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPanoramaSetting = isPanoramaSetting();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPanoramaSetting ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cameraUsePackageName = getCameraUsePackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(cameraUsePackageName);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCameraState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    subscribePanoramaCallback(IPanoramaSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    unsubscribePanoramaCallback(IPanoramaSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPanoramaSettingsService {
            public static IPanoramaSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public int getPanoOutputSignal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPanoOutputSignal();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public int getPanoWorkState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPanoWorkState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public int getBackLineConfig() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackLineConfig();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public int getPanoOutputState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPanoOutputState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public int getPanoRotation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPanoRotation();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public int getDisplayMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public boolean isValideCamera(int cameraId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cameraId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isValideCamera(cameraId);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public boolean isPanoramaSetting() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPanoramaSetting();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public String getCameraUsePackageName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraUsePackageName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public void requestCameraState(int cameraId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cameraId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCameraState(cameraId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public void subscribePanoramaCallback(IPanoramaSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribePanoramaCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.panorama.IPanoramaSettingsService
            public void unsubscribePanoramaCallback(IPanoramaSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribePanoramaCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPanoramaSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IPanoramaSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
