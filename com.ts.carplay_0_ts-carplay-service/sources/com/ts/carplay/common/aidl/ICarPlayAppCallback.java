package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayAppCallback extends IInterface {
    void onAltRequestSetup() throws RemoteException;

    void onAuxInUpdateState(int i) throws RemoteException;

    void onCarPlayDisplayStatusChanged(boolean z) throws RemoteException;

    void onEnhancedSiriParametersChanged(int i, String str) throws RemoteException;

    void onRequestViewArea(int i) throws RemoteException;

    void onSuggestUi(int i) throws RemoteException;

    public static class Default implements ICarPlayAppCallback {
        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onCarPlayDisplayStatusChanged(boolean isShow) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onEnhancedSiriParametersChanged(int voiceActivationMode, String voiceModelLanguage) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onSuggestUi(int url) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onAuxInUpdateState(int state) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onAltRequestSetup() throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onRequestViewArea(int index) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarPlayAppCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.ICarPlayAppCallback";
        static final int TRANSACTION_onAltRequestSetup = 5;
        static final int TRANSACTION_onAuxInUpdateState = 4;
        static final int TRANSACTION_onCarPlayDisplayStatusChanged = 1;
        static final int TRANSACTION_onEnhancedSiriParametersChanged = 2;
        static final int TRANSACTION_onRequestViewArea = 6;
        static final int TRANSACTION_onSuggestUi = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPlayAppCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPlayAppCallback)) {
                return (ICarPlayAppCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0 = data.readInt() != 0;
                    onCarPlayDisplayStatusChanged(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    String _arg1 = data.readString();
                    onEnhancedSiriParametersChanged(_arg02, _arg1);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onSuggestUi(_arg03);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    onAuxInUpdateState(_arg04);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onAltRequestSetup();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    onRequestViewArea(_arg05);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ICarPlayAppCallback {
            public static ICarPlayAppCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
            public void onCarPlayDisplayStatusChanged(boolean isShow) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isShow ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarPlayDisplayStatusChanged(isShow);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
            public void onEnhancedSiriParametersChanged(int voiceActivationMode, String voiceModelLanguage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(voiceActivationMode);
                    _data.writeString(voiceModelLanguage);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnhancedSiriParametersChanged(voiceActivationMode, voiceModelLanguage);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
            public void onSuggestUi(int url) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(url);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuggestUi(url);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
            public void onAuxInUpdateState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuxInUpdateState(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
            public void onAltRequestSetup() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAltRequestSetup();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
            public void onRequestViewArea(int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(index);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestViewArea(index);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarPlayAppCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarPlayAppCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
