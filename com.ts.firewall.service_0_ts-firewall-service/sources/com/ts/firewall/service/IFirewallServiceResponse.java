package com.ts.firewall.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.firewall.service.data.ResultCode;
import com.ts.firewall.service.data.ResultType;

/* JADX INFO: loaded from: classes.dex */
public interface IFirewallServiceResponse extends IInterface {
    void onGetLogResponse(int i, int i2, int i3, String str) throws RemoteException;

    void onGetRuleResponse(int i, int i2, int i3, String str) throws RemoteException;

    void onRequestFinished(ResultType resultType, ResultCode resultCode) throws RemoteException;

    public static class Default implements IFirewallServiceResponse {
        @Override // com.ts.firewall.service.IFirewallServiceResponse
        public void onRequestFinished(ResultType type, ResultCode code) throws RemoteException {
        }

        @Override // com.ts.firewall.service.IFirewallServiceResponse
        public void onGetRuleResponse(int serial, int segmentNum, int index, String rule) throws RemoteException {
        }

        @Override // com.ts.firewall.service.IFirewallServiceResponse
        public void onGetLogResponse(int serial, int segmentNum, int index, String log) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IFirewallServiceResponse {
        private static final String DESCRIPTOR = "com.ts.firewall.service.IFirewallServiceResponse";
        static final int TRANSACTION_onGetLogResponse = 3;
        static final int TRANSACTION_onGetRuleResponse = 2;
        static final int TRANSACTION_onRequestFinished = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IFirewallServiceResponse asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IFirewallServiceResponse)) {
                return (IFirewallServiceResponse) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ResultType _arg0;
            ResultCode _arg1;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = ResultType.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                if (data.readInt() != 0) {
                    _arg1 = ResultCode.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                onRequestFinished(_arg0, _arg1);
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                int _arg12 = data.readInt();
                int _arg2 = data.readInt();
                String _arg3 = data.readString();
                onGetRuleResponse(_arg02, _arg12, _arg2, _arg3);
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
            int _arg03 = data.readInt();
            int _arg13 = data.readInt();
            int _arg22 = data.readInt();
            String _arg32 = data.readString();
            onGetLogResponse(_arg03, _arg13, _arg22, _arg32);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IFirewallServiceResponse {
            public static IFirewallServiceResponse sDefaultImpl;
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

            @Override // com.ts.firewall.service.IFirewallServiceResponse
            public void onRequestFinished(ResultType type, ResultCode code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (type != null) {
                        _data.writeInt(1);
                        type.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (code != null) {
                        _data.writeInt(1);
                        code.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestFinished(type, code);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceResponse
            public void onGetRuleResponse(int serial, int segmentNum, int index, String rule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(segmentNum);
                    _data.writeInt(index);
                    _data.writeString(rule);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetRuleResponse(serial, segmentNum, index, rule);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceResponse
            public void onGetLogResponse(int serial, int segmentNum, int index, String log) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(segmentNum);
                    _data.writeInt(index);
                    _data.writeString(log);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetLogResponse(serial, segmentNum, index, log);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFirewallServiceResponse impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IFirewallServiceResponse getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
