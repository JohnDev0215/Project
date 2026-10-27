package com.ts.lib.settings.microphone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.microphone.IMicSettingsCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IMicSettingsService extends IInterface {
    int getChargeStatus() throws RemoteException;

    String getMicUsePackageName() throws RemoteException;

    int getPowerStatus() throws RemoteException;

    void subscribeMicCallback(IMicSettingsCallback iMicSettingsCallback) throws RemoteException;

    void unsubscribeMicCallback(IMicSettingsCallback iMicSettingsCallback) throws RemoteException;

    public static class Default implements IMicSettingsService {
        @Override // com.ts.lib.settings.microphone.IMicSettingsService
        public int getChargeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.microphone.IMicSettingsService
        public int getPowerStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.lib.settings.microphone.IMicSettingsService
        public String getMicUsePackageName() throws RemoteException {
            return null;
        }

        @Override // com.ts.lib.settings.microphone.IMicSettingsService
        public void subscribeMicCallback(IMicSettingsCallback callback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.microphone.IMicSettingsService
        public void unsubscribeMicCallback(IMicSettingsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMicSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.microphone.IMicSettingsService";
        static final int TRANSACTION_getChargeStatus = 1;
        static final int TRANSACTION_getMicUsePackageName = 3;
        static final int TRANSACTION_getPowerStatus = 2;
        static final int TRANSACTION_subscribeMicCallback = 4;
        static final int TRANSACTION_unsubscribeMicCallback = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMicSettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IMicSettingsService)) {
                return (IMicSettingsService) iin;
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
                int _result = getChargeStatus();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _result2 = getPowerStatus();
                reply.writeNoException();
                reply.writeInt(_result2);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String _result3 = getMicUsePackageName();
                reply.writeNoException();
                reply.writeString(_result3);
                return true;
            }
            if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                IMicSettingsCallback _arg0 = IMicSettingsCallback.Stub.asInterface(data.readStrongBinder());
                subscribeMicCallback(_arg0);
                return true;
            }
            if (code != 5) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            IMicSettingsCallback _arg02 = IMicSettingsCallback.Stub.asInterface(data.readStrongBinder());
            unsubscribeMicCallback(_arg02);
            return true;
        }

        private static class Proxy implements IMicSettingsService {
            public static IMicSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.microphone.IMicSettingsService
            public int getChargeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.microphone.IMicSettingsService
            public int getPowerStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.microphone.IMicSettingsService
            public String getMicUsePackageName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicUsePackageName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.microphone.IMicSettingsService
            public void subscribeMicCallback(IMicSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribeMicCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.lib.settings.microphone.IMicSettingsService
            public void unsubscribeMicCallback(IMicSettingsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribeMicCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMicSettingsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IMicSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
