package ts.car.ncm.network;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IExtConnectivityServiceListener extends IInterface {
    void onAvailabilityChanged(IfaceInfo ifaceInfo) throws RemoteException;

    void onMoibleNetworkStateChanged(boolean z) throws RemoteException;

    public static class Default implements IExtConnectivityServiceListener {
        @Override // ts.car.ncm.network.IExtConnectivityServiceListener
        public void onAvailabilityChanged(IfaceInfo info) throws RemoteException {
        }

        @Override // ts.car.ncm.network.IExtConnectivityServiceListener
        public void onMoibleNetworkStateChanged(boolean enabled) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IExtConnectivityServiceListener {
        private static final String DESCRIPTOR = "ts.car.ncm.network.IExtConnectivityServiceListener";
        static final int TRANSACTION_onAvailabilityChanged = 1;
        static final int TRANSACTION_onMoibleNetworkStateChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExtConnectivityServiceListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IExtConnectivityServiceListener)) {
                return (IExtConnectivityServiceListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IfaceInfo _arg0;
            if (code != 1) {
                if (code != 2) {
                    if (code == 1598968902) {
                        reply.writeString(DESCRIPTOR);
                        return true;
                    }
                    return super.onTransact(code, data, reply, flags);
                }
                data.enforceInterface(DESCRIPTOR);
                boolean _arg02 = data.readInt() != 0;
                onMoibleNetworkStateChanged(_arg02);
                reply.writeNoException();
                return true;
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = IfaceInfo.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onAvailabilityChanged(_arg0);
            reply.writeNoException();
            if (_arg0 != null) {
                reply.writeInt(1);
                _arg0.writeToParcel(reply, 1);
            } else {
                reply.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IExtConnectivityServiceListener {
            public static IExtConnectivityServiceListener sDefaultImpl;
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

            @Override // ts.car.ncm.network.IExtConnectivityServiceListener
            public void onAvailabilityChanged(IfaceInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAvailabilityChanged(info);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        info.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.ncm.network.IExtConnectivityServiceListener
            public void onMoibleNetworkStateChanged(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMoibleNetworkStateChanged(enabled);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExtConnectivityServiceListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IExtConnectivityServiceListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
