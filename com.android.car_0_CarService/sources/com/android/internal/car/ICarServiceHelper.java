package com.android.internal.car;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ICarServiceHelper extends IInterface {
    int forceSuspend(int i) throws RemoteException;

    void setDisplayWhitelistForUser(int i, int[] iArr) throws RemoteException;

    void setPassengerDisplays(int[] iArr) throws RemoteException;

    void setSourcePreferredComponents(boolean z, List<ComponentName> list) throws RemoteException;

    public static class Default implements ICarServiceHelper {
        @Override // com.android.internal.car.ICarServiceHelper
        public int forceSuspend(int timeoutMs) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.car.ICarServiceHelper
        public void setDisplayWhitelistForUser(int userId, int[] displayIds) throws RemoteException {
        }

        @Override // com.android.internal.car.ICarServiceHelper
        public void setPassengerDisplays(int[] displayIds) throws RemoteException {
        }

        @Override // com.android.internal.car.ICarServiceHelper
        public void setSourcePreferredComponents(boolean enableSourcePreferred, List<ComponentName> sourcePreferredComponents) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarServiceHelper {
        private static final String DESCRIPTOR = "com.android.internal.car.ICarServiceHelper";
        static final int TRANSACTION_forceSuspend = 1;
        static final int TRANSACTION_setDisplayWhitelistForUser = 2;
        static final int TRANSACTION_setPassengerDisplays = 3;
        static final int TRANSACTION_setSourcePreferredComponents = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarServiceHelper asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarServiceHelper)) {
                return (ICarServiceHelper) iin;
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
                int _result = forceSuspend(_arg0);
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                int[] _arg1 = data.createIntArray();
                setDisplayWhitelistForUser(_arg02, _arg1);
                reply.writeNoException();
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int[] _arg03 = data.createIntArray();
                setPassengerDisplays(_arg03);
                reply.writeNoException();
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
            boolean _arg04 = data.readInt() != 0;
            List<ComponentName> _arg12 = data.createTypedArrayList(ComponentName.CREATOR);
            setSourcePreferredComponents(_arg04, _arg12);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ICarServiceHelper {
            public static ICarServiceHelper sDefaultImpl;
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

            @Override // com.android.internal.car.ICarServiceHelper
            public int forceSuspend(int timeoutMs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(timeoutMs);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().forceSuspend(timeoutMs);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.car.ICarServiceHelper
            public void setDisplayWhitelistForUser(int userId, int[] displayIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeIntArray(displayIds);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisplayWhitelistForUser(userId, displayIds);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.car.ICarServiceHelper
            public void setPassengerDisplays(int[] displayIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(displayIds);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPassengerDisplays(displayIds);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.car.ICarServiceHelper
            public void setSourcePreferredComponents(boolean enableSourcePreferred, List<ComponentName> sourcePreferredComponents) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enableSourcePreferred ? 1 : 0);
                    _data.writeTypedList(sourcePreferredComponents);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSourcePreferredComponents(enableSourcePreferred, sourcePreferredComponents);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarServiceHelper impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarServiceHelper getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
