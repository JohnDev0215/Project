package com.ts.avm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.IAvmServiceCommandListener;
import com.ts.avm.IAvmServiceListener;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: loaded from: classes.dex */
public interface IAvmServiceInterface extends IInterface {
    int getAvmStatus() throws RemoteException;

    int registerAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException;

    int registerCommandListener(IAvmServiceCommandListener iAvmServiceCommandListener) throws RemoteException;

    void sendCommand(CmdBean cmdBean) throws RemoteException;

    void startAvm() throws RemoteException;

    void stopAvm() throws RemoteException;

    int unregisterAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException;

    public static class Default implements IAvmServiceInterface {
        @Override // com.ts.avm.IAvmServiceInterface
        public int getAvmStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int unregisterAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void startAvm() throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void stopAvm() throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void sendCommand(CmdBean cmd) throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerCommandListener(IAvmServiceCommandListener listener) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAvmServiceInterface {
        private static final String DESCRIPTOR = "com.ts.avm.IAvmServiceInterface";
        static final int TRANSACTION_getAvmStatus = 1;
        static final int TRANSACTION_registerAvmStatusListener = 2;
        static final int TRANSACTION_registerCommandListener = 7;
        static final int TRANSACTION_sendCommand = 6;
        static final int TRANSACTION_startAvm = 4;
        static final int TRANSACTION_stopAvm = 5;
        static final int TRANSACTION_unregisterAvmStatusListener = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAvmServiceInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAvmServiceInterface)) {
                return (IAvmServiceInterface) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CmdBean _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getAvmStatus();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmServiceListener _arg02 = IAvmServiceListener.Stub.asInterface(data.readStrongBinder());
                    int _result2 = registerAvmStatusListener(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmServiceListener _arg03 = IAvmServiceListener.Stub.asInterface(data.readStrongBinder());
                    int _result3 = unregisterAvmStatusListener(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    startAvm();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    stopAvm();
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = CmdBean.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    sendCommand(_arg0);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmServiceCommandListener _arg04 = IAvmServiceCommandListener.Stub.asInterface(data.readStrongBinder());
                    int _result4 = registerCommandListener(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAvmServiceInterface {
            public static IAvmServiceInterface sDefaultImpl;
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

            @Override // com.ts.avm.IAvmServiceInterface
            public int getAvmStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvmStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int registerAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAvmStatusListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int unregisterAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterAvmStatusListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void startAvm() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startAvm();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void stopAvm() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopAvm();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void sendCommand(CmdBean cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cmd != null) {
                        _data.writeInt(1);
                        cmd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendCommand(cmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int registerCommandListener(IAvmServiceCommandListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCommandListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAvmServiceInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAvmServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
