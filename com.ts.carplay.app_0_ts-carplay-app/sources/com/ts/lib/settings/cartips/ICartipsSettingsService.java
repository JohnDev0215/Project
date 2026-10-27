package com.ts.lib.settings.cartips;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.cartips.ICartipsSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface ICartipsSettingsService extends IInterface {
    int getCarTipsSoundType() throws RemoteException;

    int setCarTipsSoundType(int i) throws RemoteException;

    void subscribeCartipsCallback(ICartipsSettingsCallback iCartipsSettingsCallback) throws RemoteException;

    void unsubscribeCartipsCallback(ICartipsSettingsCallback iCartipsSettingsCallback) throws RemoteException;

    public static class Default implements ICartipsSettingsService {
        @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
        public int setCarTipsSoundType(int value) throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
        public int getCarTipsSoundType() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
        public void subscribeCartipsCallback(ICartipsSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
        public void unsubscribeCartipsCallback(ICartipsSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICartipsSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.cartips.ICartipsSettingsService";
        static final int TRANSACTION_getCarTipsSoundType = 2;
        static final int TRANSACTION_setCarTipsSoundType = 1;
        static final int TRANSACTION_subscribeCartipsCallback = 3;
        static final int TRANSACTION_unsubscribeCartipsCallback = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICartipsSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICartipsSettingsService)) {
                return (ICartipsSettingsService) iin;
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
                int _result = setCarTipsSoundType(_arg0);
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _result2 = getCarTipsSoundType();
                reply.writeNoException();
                reply.writeInt(_result2);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                ICartipsSettingsCallback _arg02 = ICartipsSettingsCallback.Stub.asInterface(data.readStrongBinder());
                subscribeCartipsCallback(_arg02);
                return true;
            }
            if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            ICartipsSettingsCallback _arg03 = ICartipsSettingsCallback.Stub.asInterface(data.readStrongBinder());
            unsubscribeCartipsCallback(_arg03);
            return true;
        }

        private static class Proxy implements ICartipsSettingsService {
            public static ICartipsSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
            public int setCarTipsSoundType(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCarTipsSoundType(value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
            public int getCarTipsSoundType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarTipsSoundType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
            public void subscribeCartipsCallback(ICartipsSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeCartipsCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.cartips.ICartipsSettingsService
            public void unsubscribeCartipsCallback(ICartipsSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeCartipsCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICartipsSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICartipsSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
