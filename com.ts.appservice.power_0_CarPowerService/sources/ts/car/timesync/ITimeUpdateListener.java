package ts.car.timesync;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ITimeUpdateListener extends IInterface {
    void onTimeUpdate(long j, String str) throws RemoteException;

    public static class Default implements ITimeUpdateListener {
        @Override // ts.car.timesync.ITimeUpdateListener
        public void onTimeUpdate(long millis, String timeZone) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITimeUpdateListener {
        private static final String DESCRIPTOR = "ts.car.timesync.ITimeUpdateListener";
        static final int TRANSACTION_onTimeUpdate = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITimeUpdateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ITimeUpdateListener)) {
                return (ITimeUpdateListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            long _arg0 = data.readLong();
            String _arg1 = data.readString();
            onTimeUpdate(_arg0, _arg1);
            return true;
        }

        private static class Proxy implements ITimeUpdateListener {
            public static ITimeUpdateListener sDefaultImpl;
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

            @Override // ts.car.timesync.ITimeUpdateListener
            public void onTimeUpdate(long millis, String timeZone) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(millis);
                    _data.writeString(timeZone);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTimeUpdate(millis, timeZone);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeUpdateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ITimeUpdateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
