package com.ts.ota.otasdkmgr;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAbsMessageCallback extends IInterface {
    void onAndroidOtaState(int i) throws RemoteException;

    void onAppointmentTime(int i) throws RemoteException;

    void onClearDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) throws RemoteException;

    void onCloudServiceListener(int i, Bundle bundle) throws RemoteException;

    void onEcuInfo(List<EcuInfo> list) throws RemoteException;

    void onGetAQSlot(int i) throws RemoteException;

    void onGetDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) throws RemoteException;

    void onQnxOtaState(int i) throws RemoteException;

    void onSendCanNM(int i) throws RemoteException;

    void onSetAQSlot(int i) throws RemoteException;

    void onUpdateFlag(int i) throws RemoteException;

    public static class Default implements IAbsMessageCallback {
        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onEcuInfo(List<EcuInfo> ecuInfoList) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onGetAQSlot(int slot) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onSetAQSlot(int resultCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onAppointmentTime(int resultCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onUpdateFlag(int resultCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onQnxOtaState(int resultCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onAndroidOtaState(int resultCode) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onCloudServiceListener(int type, Bundle bundle) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onGetDiagnosticTroubleCode(List<DiagnosticAidlInfo> info) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onClearDiagnosticTroubleCode(List<DiagnosticAidlInfo> info) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
        public void onSendCanNM(int resultCode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAbsMessageCallback {
        private static final String DESCRIPTOR = "com.ts.ota.otasdkmgr.IAbsMessageCallback";
        static final int TRANSACTION_onAndroidOtaState = 7;
        static final int TRANSACTION_onAppointmentTime = 4;
        static final int TRANSACTION_onClearDiagnosticTroubleCode = 10;
        static final int TRANSACTION_onCloudServiceListener = 8;
        static final int TRANSACTION_onEcuInfo = 1;
        static final int TRANSACTION_onGetAQSlot = 2;
        static final int TRANSACTION_onGetDiagnosticTroubleCode = 9;
        static final int TRANSACTION_onQnxOtaState = 6;
        static final int TRANSACTION_onSendCanNM = 11;
        static final int TRANSACTION_onSetAQSlot = 3;
        static final int TRANSACTION_onUpdateFlag = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAbsMessageCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAbsMessageCallback)) {
                return (IAbsMessageCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg1;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    List<EcuInfo> _arg0 = data.createTypedArrayList(EcuInfo.CREATOR);
                    onEcuInfo(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    onGetAQSlot(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onSetAQSlot(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    onAppointmentTime(_arg04);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    onUpdateFlag(_arg05);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    onQnxOtaState(_arg06);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    onAndroidOtaState(_arg07);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    onCloudServiceListener(_arg08, _arg1);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    List<DiagnosticAidlInfo> _arg09 = data.createTypedArrayList(DiagnosticAidlInfo.CREATOR);
                    onGetDiagnosticTroubleCode(_arg09);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    List<DiagnosticAidlInfo> _arg010 = data.createTypedArrayList(DiagnosticAidlInfo.CREATOR);
                    onClearDiagnosticTroubleCode(_arg010);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg011 = data.readInt();
                    onSendCanNM(_arg011);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAbsMessageCallback {
            public static IAbsMessageCallback sDefaultImpl;
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

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onEcuInfo(List<EcuInfo> ecuInfoList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(ecuInfoList);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEcuInfo(ecuInfoList);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onGetAQSlot(int slot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slot);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetAQSlot(slot);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onSetAQSlot(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSetAQSlot(resultCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onAppointmentTime(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppointmentTime(resultCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onUpdateFlag(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUpdateFlag(resultCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onQnxOtaState(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQnxOtaState(resultCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onAndroidOtaState(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAndroidOtaState(resultCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onCloudServiceListener(int type, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCloudServiceListener(type, bundle);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onGetDiagnosticTroubleCode(List<DiagnosticAidlInfo> info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(info);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetDiagnosticTroubleCode(info);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onClearDiagnosticTroubleCode(List<DiagnosticAidlInfo> info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(info);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onClearDiagnosticTroubleCode(info);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IAbsMessageCallback
            public void onSendCanNM(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSendCanNM(resultCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAbsMessageCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAbsMessageCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
