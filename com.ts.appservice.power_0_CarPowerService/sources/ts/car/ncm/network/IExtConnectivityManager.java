package ts.car.ncm.network;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import ts.car.ncm.network.IExtConnectivityServiceListener;

/* JADX INFO: loaded from: classes.dex */
public interface IExtConnectivityManager extends IInterface {
    boolean enableInterface(String str, boolean z) throws RemoteException;

    boolean enableMobileNetwork(boolean z) throws RemoteException;

    IfaceInfo getIfaceInfo(String str) throws RemoteException;

    List<IfaceInfo> getIfaceInfos() throws RemoteException;

    boolean getMobileNetworkEnable() throws RemoteException;

    void registerListener(IExtConnectivityServiceListener iExtConnectivityServiceListener) throws RemoteException;

    void unregisterListener(IExtConnectivityServiceListener iExtConnectivityServiceListener) throws RemoteException;

    public static class Default implements IExtConnectivityManager {
        @Override // ts.car.ncm.network.IExtConnectivityManager
        public IfaceInfo getIfaceInfo(String iface) throws RemoteException {
            return null;
        }

        @Override // ts.car.ncm.network.IExtConnectivityManager
        public List<IfaceInfo> getIfaceInfos() throws RemoteException {
            return null;
        }

        @Override // ts.car.ncm.network.IExtConnectivityManager
        public void registerListener(IExtConnectivityServiceListener listener) throws RemoteException {
        }

        @Override // ts.car.ncm.network.IExtConnectivityManager
        public void unregisterListener(IExtConnectivityServiceListener listener) throws RemoteException {
        }

        @Override // ts.car.ncm.network.IExtConnectivityManager
        public boolean enableMobileNetwork(boolean enable) throws RemoteException {
            return false;
        }

        @Override // ts.car.ncm.network.IExtConnectivityManager
        public boolean enableInterface(String ifaceName, boolean enable) throws RemoteException {
            return false;
        }

        @Override // ts.car.ncm.network.IExtConnectivityManager
        public boolean getMobileNetworkEnable() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IExtConnectivityManager {
        private static final String DESCRIPTOR = "ts.car.ncm.network.IExtConnectivityManager";
        static final int TRANSACTION_enableInterface = 6;
        static final int TRANSACTION_enableMobileNetwork = 5;
        static final int TRANSACTION_getIfaceInfo = 1;
        static final int TRANSACTION_getIfaceInfos = 2;
        static final int TRANSACTION_getMobileNetworkEnable = 7;
        static final int TRANSACTION_registerListener = 3;
        static final int TRANSACTION_unregisterListener = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExtConnectivityManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IExtConnectivityManager)) {
                return (IExtConnectivityManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    IfaceInfo ifaceInfo = getIfaceInfo(parcel.readString());
                    parcel2.writeNoException();
                    if (ifaceInfo != null) {
                        parcel2.writeInt(1);
                        ifaceInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IfaceInfo> ifaceInfos = getIfaceInfos();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(ifaceInfos);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IExtConnectivityServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IExtConnectivityServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableMobileNetwork = enableMobileNetwork(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableMobileNetwork ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableInterface = enableInterface(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableInterface ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean mobileNetworkEnable = getMobileNetworkEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(mobileNetworkEnable ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IExtConnectivityManager {
            public static IExtConnectivityManager sDefaultImpl;
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

            @Override // ts.car.ncm.network.IExtConnectivityManager
            public IfaceInfo getIfaceInfo(String iface) throws RemoteException {
                IfaceInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIfaceInfo(iface);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IfaceInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.ncm.network.IExtConnectivityManager
            public List<IfaceInfo> getIfaceInfos() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIfaceInfos();
                    }
                    _reply.readException();
                    List<IfaceInfo> _result = _reply.createTypedArrayList(IfaceInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.ncm.network.IExtConnectivityManager
            public void registerListener(IExtConnectivityServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.ncm.network.IExtConnectivityManager
            public void unregisterListener(IExtConnectivityServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.ncm.network.IExtConnectivityManager
            public boolean enableMobileNetwork(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableMobileNetwork(enable);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.ncm.network.IExtConnectivityManager
            public boolean enableInterface(String ifaceName, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifaceName);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableInterface(ifaceName, enable);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.ncm.network.IExtConnectivityManager
            public boolean getMobileNetworkEnable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMobileNetworkEnable();
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

        public static boolean setDefaultImpl(IExtConnectivityManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IExtConnectivityManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
