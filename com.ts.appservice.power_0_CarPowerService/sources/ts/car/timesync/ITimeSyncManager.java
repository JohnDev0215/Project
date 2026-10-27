package ts.car.timesync;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ts.car.timesync.ITimeUpdateListener;

/* JADX INFO: loaded from: classes.dex */
public interface ITimeSyncManager extends IInterface {
    void registerTimeUpdate(ITimeUpdateListener iTimeUpdateListener) throws RemoteException;

    void setTime(long j, String str, int i) throws RemoteException;

    void unregisterTimeUpdate(ITimeUpdateListener iTimeUpdateListener) throws RemoteException;

    public static class Default implements ITimeSyncManager {
        @Override // ts.car.timesync.ITimeSyncManager
        public void setTime(long millis, String timeZone, int type) throws RemoteException {
        }

        @Override // ts.car.timesync.ITimeSyncManager
        public void registerTimeUpdate(ITimeUpdateListener listener) throws RemoteException {
        }

        @Override // ts.car.timesync.ITimeSyncManager
        public void unregisterTimeUpdate(ITimeUpdateListener listener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITimeSyncManager {
        private static final String DESCRIPTOR = "ts.car.timesync.ITimeSyncManager";
        static final int TRANSACTION_registerTimeUpdate = 2;
        static final int TRANSACTION_setTime = 1;
        static final int TRANSACTION_unregisterTimeUpdate = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITimeSyncManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ITimeSyncManager)) {
                return (ITimeSyncManager) iin;
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
                long _arg0 = data.readLong();
                String _arg1 = data.readString();
                int _arg2 = data.readInt();
                setTime(_arg0, _arg1, _arg2);
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                ITimeUpdateListener _arg02 = ITimeUpdateListener.Stub.asInterface(data.readStrongBinder());
                registerTimeUpdate(_arg02);
                reply.writeNoException();
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
            ITimeUpdateListener _arg03 = ITimeUpdateListener.Stub.asInterface(data.readStrongBinder());
            unregisterTimeUpdate(_arg03);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ITimeSyncManager {
            public static ITimeSyncManager sDefaultImpl;
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

            @Override // ts.car.timesync.ITimeSyncManager
            public void setTime(long millis, String timeZone, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(millis);
                    _data.writeString(timeZone);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTime(millis, timeZone, type);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.timesync.ITimeSyncManager
            public void registerTimeUpdate(ITimeUpdateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTimeUpdate(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.timesync.ITimeSyncManager
            public void unregisterTimeUpdate(ITimeUpdateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterTimeUpdate(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeSyncManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ITimeSyncManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
