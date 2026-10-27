package ts.car.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public interface IKeyEventCallback extends IInterface {
    void onKeyEvent(KeyEvent keyEvent, int i, int i2) throws RemoteException;

    void onKeyLongPress(KeyEvent keyEvent, int i, int i2) throws RemoteException;

    public static class Default implements IKeyEventCallback {
        @Override // ts.car.input.IKeyEventCallback
        public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) throws RemoteException {
        }

        @Override // ts.car.input.IKeyEventCallback
        public void onKeyLongPress(KeyEvent event, int sceneType, int targetDisplay) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IKeyEventCallback {
        private static final String DESCRIPTOR = "ts.car.input.IKeyEventCallback";
        static final int TRANSACTION_onKeyEvent = 1;
        static final int TRANSACTION_onKeyLongPress = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyEventCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IKeyEventCallback)) {
                return (IKeyEventCallback) iin;
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
            KeyEvent _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (KeyEvent) KeyEvent.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                int _arg1 = data.readInt();
                int _arg2 = data.readInt();
                onKeyEvent(_arg0, _arg1, _arg2);
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
            if (data.readInt() != 0) {
                _arg02 = (KeyEvent) KeyEvent.CREATOR.createFromParcel(data);
            } else {
                _arg02 = null;
            }
            int _arg12 = data.readInt();
            int _arg22 = data.readInt();
            onKeyLongPress(_arg02, _arg12, _arg22);
            return true;
        }

        private static class Proxy implements IKeyEventCallback {
            public static IKeyEventCallback sDefaultImpl;
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

            @Override // ts.car.input.IKeyEventCallback
            public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) throws RemoteException {
                Parcel _data = Parcel.obtain();
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
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyEvent(event, sceneType, targetDisplay);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IKeyEventCallback
            public void onKeyLongPress(KeyEvent event, int sceneType, int targetDisplay) throws RemoteException {
                Parcel _data = Parcel.obtain();
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
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyLongPress(event, sceneType, targetDisplay);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyEventCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IKeyEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
