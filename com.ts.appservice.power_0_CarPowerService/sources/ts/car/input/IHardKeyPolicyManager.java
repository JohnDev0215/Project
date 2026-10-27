package ts.car.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;
import ts.car.input.IKeyEventCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IHardKeyPolicyManager extends IInterface {
    void addKeyEventCallBack(IKeyEventCallback iKeyEventCallback, int i) throws RemoteException;

    void addLongPressCallBack(IKeyEventCallback iKeyEventCallback, int i) throws RemoteException;

    void processHardKeyFromPolicy(KeyEvent keyEvent, int i) throws RemoteException;

    void removeAllKeyEventCallBack() throws RemoteException;

    void removeKeyEventCallBack(IKeyEventCallback iKeyEventCallback, int i) throws RemoteException;

    void removeKeyEventCbk(IKeyEventCallback iKeyEventCallback) throws RemoteException;

    void removeLongPressCallBack(IKeyEventCallback iKeyEventCallback, int i) throws RemoteException;

    void removeLongPressCbk(IKeyEventCallback iKeyEventCallback) throws RemoteException;

    public static class Default implements IHardKeyPolicyManager {
        @Override // ts.car.input.IHardKeyPolicyManager
        public void addKeyEventCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
        }

        @Override // ts.car.input.IHardKeyPolicyManager
        public void removeKeyEventCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
        }

        @Override // ts.car.input.IHardKeyPolicyManager
        public void removeKeyEventCbk(IKeyEventCallback callback) throws RemoteException {
        }

        @Override // ts.car.input.IHardKeyPolicyManager
        public void addLongPressCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
        }

        @Override // ts.car.input.IHardKeyPolicyManager
        public void removeLongPressCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
        }

        @Override // ts.car.input.IHardKeyPolicyManager
        public void removeLongPressCbk(IKeyEventCallback callback) throws RemoteException {
        }

        @Override // ts.car.input.IHardKeyPolicyManager
        public void removeAllKeyEventCallBack() throws RemoteException {
        }

        @Override // ts.car.input.IHardKeyPolicyManager
        public void processHardKeyFromPolicy(KeyEvent keyEvent, int targetDisplay) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IHardKeyPolicyManager {
        private static final String DESCRIPTOR = "ts.car.input.IHardKeyPolicyManager";
        static final int TRANSACTION_addKeyEventCallBack = 1;
        static final int TRANSACTION_addLongPressCallBack = 4;
        static final int TRANSACTION_processHardKeyFromPolicy = 8;
        static final int TRANSACTION_removeAllKeyEventCallBack = 7;
        static final int TRANSACTION_removeKeyEventCallBack = 2;
        static final int TRANSACTION_removeKeyEventCbk = 3;
        static final int TRANSACTION_removeLongPressCallBack = 5;
        static final int TRANSACTION_removeLongPressCbk = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHardKeyPolicyManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IHardKeyPolicyManager)) {
                return (IHardKeyPolicyManager) iin;
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
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    IKeyEventCallback _arg02 = IKeyEventCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg1 = data.readInt();
                    addKeyEventCallBack(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IKeyEventCallback _arg03 = IKeyEventCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg12 = data.readInt();
                    removeKeyEventCallBack(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IKeyEventCallback _arg04 = IKeyEventCallback.Stub.asInterface(data.readStrongBinder());
                    removeKeyEventCbk(_arg04);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    IKeyEventCallback _arg05 = IKeyEventCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg13 = data.readInt();
                    addLongPressCallBack(_arg05, _arg13);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    IKeyEventCallback _arg06 = IKeyEventCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg14 = data.readInt();
                    removeLongPressCallBack(_arg06, _arg14);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    IKeyEventCallback _arg07 = IKeyEventCallback.Stub.asInterface(data.readStrongBinder());
                    removeLongPressCbk(_arg07);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    removeAllKeyEventCallBack();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (KeyEvent) KeyEvent.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    int _arg15 = data.readInt();
                    processHardKeyFromPolicy(_arg0, _arg15);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IHardKeyPolicyManager {
            public static IHardKeyPolicyManager sDefaultImpl;
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

            @Override // ts.car.input.IHardKeyPolicyManager
            public void addKeyEventCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(sceneType);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addKeyEventCallBack(callback, sceneType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IHardKeyPolicyManager
            public void removeKeyEventCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(sceneType);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeKeyEventCallBack(callback, sceneType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IHardKeyPolicyManager
            public void removeKeyEventCbk(IKeyEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeKeyEventCbk(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IHardKeyPolicyManager
            public void addLongPressCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(sceneType);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addLongPressCallBack(callback, sceneType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IHardKeyPolicyManager
            public void removeLongPressCallBack(IKeyEventCallback callback, int sceneType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(sceneType);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeLongPressCallBack(callback, sceneType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IHardKeyPolicyManager
            public void removeLongPressCbk(IKeyEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeLongPressCbk(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IHardKeyPolicyManager
            public void removeAllKeyEventCallBack() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAllKeyEventCallBack();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.input.IHardKeyPolicyManager
            public void processHardKeyFromPolicy(KeyEvent keyEvent, int targetDisplay) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keyEvent != null) {
                        _data.writeInt(1);
                        keyEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(targetDisplay);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().processHardKeyFromPolicy(keyEvent, targetDisplay);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHardKeyPolicyManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IHardKeyPolicyManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
