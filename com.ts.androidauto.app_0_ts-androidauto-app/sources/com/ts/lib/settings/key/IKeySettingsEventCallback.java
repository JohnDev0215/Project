package com.ts.lib.settings.key;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes2.dex */
public interface IKeySettingsEventCallback extends IInterface {
    void onKeyEvent(KeyEvent keyEvent, int i, int i2) throws RemoteException;

    public static class Default implements IKeySettingsEventCallback {
        @Override // com.ts.lib.settings.key.IKeySettingsEventCallback
        public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IKeySettingsEventCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.key.IKeySettingsEventCallback";
        static final int TRANSACTION_onKeyEvent = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeySettingsEventCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IKeySettingsEventCallback)) {
                return (IKeySettingsEventCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            KeyEvent _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = (KeyEvent) KeyEvent.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            onKeyEvent(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IKeySettingsEventCallback {
            public static IKeySettingsEventCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.key.IKeySettingsEventCallback
            public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sceneType);
                    _data.writeInt(targetDisplay);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyEvent(event, sceneType, targetDisplay);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeySettingsEventCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IKeySettingsEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
