package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.androidauto.sdk.aidl.data.IfNavigationPositionData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationStateData;

/* JADX INFO: loaded from: classes.dex */
public interface INavigationDataCallback extends IInterface {
    void onNavigationCurrentPosition(IfNavigationPositionData ifNavigationPositionData) throws RemoteException;

    void onNavigationState(IfNavigationStateData ifNavigationStateData) throws RemoteException;

    void onNavigationStatusChanged(int i) throws RemoteException;

    public static class Default implements INavigationDataCallback {
        @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
        public void onNavigationStatusChanged(int status) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
        public void onNavigationState(IfNavigationStateData ifNavigationStateData) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
        public void onNavigationCurrentPosition(IfNavigationPositionData ifNavigationPositionData) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements INavigationDataCallback {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.INavigationDataCallback";
        static final int TRANSACTION_onNavigationCurrentPosition = 3;
        static final int TRANSACTION_onNavigationState = 2;
        static final int TRANSACTION_onNavigationStatusChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INavigationDataCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INavigationDataCallback)) {
                return (INavigationDataCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IfNavigationStateData _arg0;
            IfNavigationPositionData _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg03 = data.readInt();
                onNavigationStatusChanged(_arg03);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = IfNavigationStateData.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onNavigationState(_arg0);
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
            if (data.readInt() != 0) {
                _arg02 = IfNavigationPositionData.CREATOR.createFromParcel(data);
            } else {
                _arg02 = null;
            }
            onNavigationCurrentPosition(_arg02);
            return true;
        }

        private static class Proxy implements INavigationDataCallback {
            public static INavigationDataCallback sDefaultImpl;
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

            @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
            public void onNavigationStatusChanged(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNavigationStatusChanged(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
            public void onNavigationState(IfNavigationStateData ifNavigationStateData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ifNavigationStateData != null) {
                        _data.writeInt(1);
                        ifNavigationStateData.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNavigationState(ifNavigationStateData);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
            public void onNavigationCurrentPosition(IfNavigationPositionData ifNavigationPositionData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ifNavigationPositionData != null) {
                        _data.writeInt(1);
                        ifNavigationPositionData.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNavigationCurrentPosition(ifNavigationPositionData);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INavigationDataCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static INavigationDataCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
