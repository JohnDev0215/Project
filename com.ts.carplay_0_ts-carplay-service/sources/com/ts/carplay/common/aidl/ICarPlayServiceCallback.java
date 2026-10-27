package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayServiceCallback extends IInterface {
    void onAppStateChanged(int i, int i2, int i3) throws RemoteException;

    void onRequestVideoFocusChanged(int i) throws RemoteException;

    void onSupportFeaturesChanged(int i) throws RemoteException;

    public static class Default implements ICarPlayServiceCallback {
        @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
        public void onRequestVideoFocusChanged(int requestType) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
        public void onSupportFeaturesChanged(int supportsFeatures) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
        public void onAppStateChanged(int appState, int entity, int speechMode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarPlayServiceCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.ICarPlayServiceCallback";
        static final int TRANSACTION_onAppStateChanged = 3;
        static final int TRANSACTION_onRequestVideoFocusChanged = 1;
        static final int TRANSACTION_onSupportFeaturesChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPlayServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPlayServiceCallback)) {
                return (ICarPlayServiceCallback) iin;
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
                onRequestVideoFocusChanged(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onSupportFeaturesChanged(_arg02);
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
            int _arg03 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            onAppStateChanged(_arg03, _arg1, _arg2);
            return true;
        }

        private static class Proxy implements ICarPlayServiceCallback {
            public static ICarPlayServiceCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
            public void onRequestVideoFocusChanged(int requestType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(requestType);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestVideoFocusChanged(requestType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
            public void onSupportFeaturesChanged(int supportsFeatures) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(supportsFeatures);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSupportFeaturesChanged(supportsFeatures);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
            public void onAppStateChanged(int appState, int entity, int speechMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appState);
                    _data.writeInt(entity);
                    _data.writeInt(speechMode);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppStateChanged(appState, entity, speechMode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarPlayServiceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarPlayServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
