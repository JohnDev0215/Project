package com.ts.lib.settings.vradapter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.vradapter.ILocalVrSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public interface ILocalVrSettingsService extends IInterface {
    void doStartVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException;

    void doStopVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException;

    int getVrState() throws RemoteException;

    public static class Default implements ILocalVrSettingsService {
        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public int getVrState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public void doStartVr(ILocalVrSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public void doStopVr(ILocalVrSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ILocalVrSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.vradapter.ILocalVrSettingsService";
        static final int TRANSACTION_doStartVr = 2;
        static final int TRANSACTION_doStopVr = 3;
        static final int TRANSACTION_getVrState = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocalVrSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ILocalVrSettingsService)) {
                return (ILocalVrSettingsService) iin;
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
                int _result = getVrState();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                ILocalVrSettingsCallback _arg0 = ILocalVrSettingsCallback.Stub.asInterface(data.readStrongBinder());
                doStartVr(_arg0);
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            ILocalVrSettingsCallback _arg02 = ILocalVrSettingsCallback.Stub.asInterface(data.readStrongBinder());
            doStopVr(_arg02);
            return true;
        }

        private static class Proxy implements ILocalVrSettingsService {
            public static ILocalVrSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
            public int getVrState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVrState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
            public void doStartVr(ILocalVrSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().doStartVr(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
            public void doStopVr(ILocalVrSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().doStopVr(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocalVrSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ILocalVrSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
