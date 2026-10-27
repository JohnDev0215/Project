package com.ts.lib.qnx;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.qnx.IQnxCallBack;

/* JADX INFO: loaded from: classes.dex */
public interface IQnx extends IInterface {
    boolean notifyDiLinkStarted() throws RemoteException;

    Bundle receiveMessage(int i, int i2, String str, Bundle bundle) throws RemoteException;

    boolean registerCallback(int i, int i2, IQnxCallBack iQnxCallBack) throws RemoteException;

    boolean sendMessage(int i, int i2, String str, Bundle bundle) throws RemoteException;

    boolean unregisterCallback(int i, int i2, IQnxCallBack iQnxCallBack) throws RemoteException;

    public static class Default implements IQnx {
        @Override // com.ts.lib.qnx.IQnx
        public boolean sendMessage(int msgType, int subType, String context, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.qnx.IQnx
        public boolean notifyDiLinkStarted() throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.qnx.IQnx
        public Bundle receiveMessage(int msgType, int subType, String context, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.qnx.IQnx
        public boolean registerCallback(int msgType, int subType, IQnxCallBack callback) throws RemoteException {
            return false;
        }

        @Override // com.ts.lib.qnx.IQnx
        public boolean unregisterCallback(int msgType, int subType, IQnxCallBack callback) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IQnx {
        private static final String DESCRIPTOR = "com.ts.lib.qnx.IQnx";
        static final int TRANSACTION_notifyDiLinkStarted = 2;
        static final int TRANSACTION_receiveMessage = 3;
        static final int TRANSACTION_registerCallback = 4;
        static final int TRANSACTION_sendMessage = 1;
        static final int TRANSACTION_unregisterCallback = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IQnx asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IQnx)) {
                return (IQnx) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle;
            Bundle bundle2;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int i3 = parcel.readInt();
                int i4 = parcel.readInt();
                String string = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                boolean zSendMessage = sendMessage(i3, i4, string, bundle);
                parcel2.writeNoException();
                parcel2.writeInt(zSendMessage ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zNotifyDiLinkStarted = notifyDiLinkStarted();
                parcel2.writeNoException();
                parcel2.writeInt(zNotifyDiLinkStarted ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                int i5 = parcel.readInt();
                int i6 = parcel.readInt();
                String string2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle2 = null;
                }
                Bundle bundleReceiveMessage = receiveMessage(i5, i6, string2, bundle2);
                parcel2.writeNoException();
                if (bundleReceiveMessage != null) {
                    parcel2.writeInt(1);
                    bundleReceiveMessage.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zRegisterCallback = registerCallback(parcel.readInt(), parcel.readInt(), IQnxCallBack.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zRegisterCallback ? 1 : 0);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zUnregisterCallback = unregisterCallback(parcel.readInt(), parcel.readInt(), IQnxCallBack.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeInt(zUnregisterCallback ? 1 : 0);
            return true;
        }

        private static class Proxy implements IQnx {
            public static IQnx sDefaultImpl;
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

            @Override // com.ts.lib.qnx.IQnx
            public boolean sendMessage(int msgType, int subType, String context, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(msgType);
                    _data.writeInt(subType);
                    _data.writeString(context);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendMessage(msgType, subType, context, bundle);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.qnx.IQnx
            public boolean notifyDiLinkStarted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyDiLinkStarted();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.qnx.IQnx
            public Bundle receiveMessage(int msgType, int subType, String context, Bundle bundle) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(msgType);
                    _data.writeInt(subType);
                    _data.writeString(context);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().receiveMessage(msgType, subType, context, bundle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.qnx.IQnx
            public boolean registerCallback(int msgType, int subType, IQnxCallBack callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(msgType);
                    _data.writeInt(subType);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(msgType, subType, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.qnx.IQnx
            public boolean unregisterCallback(int msgType, int subType, IQnxCallBack callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(msgType);
                    _data.writeInt(subType);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterCallback(msgType, subType, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQnx impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IQnx getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
