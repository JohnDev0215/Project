package com.ts.lib.settings.key;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.key.IKeySettingsCallback;
import com.ts.lib.settings.key.IKeySettingsEventCallback;
import com.ts.lib.settings.key.IKeySettingsLongPressEventCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IKeySettingsService extends IInterface {
    void addHighPriorityScene(int i) throws RemoteException;

    void removeHighPriorityScene(int i) throws RemoteException;

    void subscribeKeyCallback(IKeySettingsCallback iKeySettingsCallback) throws RemoteException;

    void subscribeSceneKeyCallback(IKeySettingsEventCallback iKeySettingsEventCallback) throws RemoteException;

    void subscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback iKeySettingsLongPressEventCallback) throws RemoteException;

    void unsubscribeKeyCallback(IKeySettingsCallback iKeySettingsCallback) throws RemoteException;

    void unsubscribeSceneKeyCallback(IKeySettingsEventCallback iKeySettingsEventCallback) throws RemoteException;

    void unsubscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback iKeySettingsLongPressEventCallback) throws RemoteException;

    public static class Default implements IKeySettingsService {
        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void subscribeKeyCallback(IKeySettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void subscribeSceneKeyCallback(IKeySettingsEventCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void subscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void unsubscribeKeyCallback(IKeySettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void unsubscribeSceneKeyCallback(IKeySettingsEventCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void unsubscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void addHighPriorityScene(int sceneId) throws RemoteException {
        }

        @Override // com.ts.lib.settings.key.IKeySettingsService
        public void removeHighPriorityScene(int sceneId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IKeySettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.key.IKeySettingsService";
        static final int TRANSACTION_addHighPriorityScene = 7;
        static final int TRANSACTION_removeHighPriorityScene = 8;
        static final int TRANSACTION_subscribeKeyCallback = 1;
        static final int TRANSACTION_subscribeSceneKeyCallback = 2;
        static final int TRANSACTION_subscribeSceneLongPressKeyCallback = 3;
        static final int TRANSACTION_unsubscribeKeyCallback = 4;
        static final int TRANSACTION_unsubscribeSceneKeyCallback = 5;
        static final int TRANSACTION_unsubscribeSceneLongPressKeyCallback = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeySettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IKeySettingsService)) {
                return (IKeySettingsService) iin;
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
                    IKeySettingsCallback _arg0 = IKeySettingsCallback.Stub.asInterface(data.readStrongBinder());
                    subscribeKeyCallback(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IKeySettingsEventCallback _arg02 = IKeySettingsEventCallback.Stub.asInterface(data.readStrongBinder());
                    subscribeSceneKeyCallback(_arg02);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IKeySettingsLongPressEventCallback _arg03 = IKeySettingsLongPressEventCallback.Stub.asInterface(data.readStrongBinder());
                    subscribeSceneLongPressKeyCallback(_arg03);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    IKeySettingsCallback _arg04 = IKeySettingsCallback.Stub.asInterface(data.readStrongBinder());
                    unsubscribeKeyCallback(_arg04);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    IKeySettingsEventCallback _arg05 = IKeySettingsEventCallback.Stub.asInterface(data.readStrongBinder());
                    unsubscribeSceneKeyCallback(_arg05);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    IKeySettingsLongPressEventCallback _arg06 = IKeySettingsLongPressEventCallback.Stub.asInterface(data.readStrongBinder());
                    unsubscribeSceneLongPressKeyCallback(_arg06);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    addHighPriorityScene(_arg07);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    removeHighPriorityScene(_arg08);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IKeySettingsService {
            public static IKeySettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void subscribeKeyCallback(IKeySettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeKeyCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void subscribeSceneKeyCallback(IKeySettingsEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeSceneKeyCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void subscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeSceneLongPressKeyCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void unsubscribeKeyCallback(IKeySettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeKeyCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void unsubscribeSceneKeyCallback(IKeySettingsEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeSceneKeyCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void unsubscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeSceneLongPressKeyCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void addHighPriorityScene(int sceneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sceneId);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addHighPriorityScene(sceneId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.key.IKeySettingsService
            public void removeHighPriorityScene(int sceneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sceneId);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeHighPriorityScene(sceneId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeySettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IKeySettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
