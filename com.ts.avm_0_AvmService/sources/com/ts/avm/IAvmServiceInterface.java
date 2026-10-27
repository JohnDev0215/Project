package com.ts.avm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.IAvmCallListener;
import com.ts.avm.IAvmServiceCommandListener;
import com.ts.avm.IAvmServiceListener;
import com.ts.avm.bean.AvmCallInfoBean;
import com.ts.avm.bean.CmdBean;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAvmServiceInterface extends IInterface {
    int getAvmStatus() throws RemoteException;

    int registerAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException;

    int registerCallStatusListener(IAvmCallListener iAvmCallListener) throws RemoteException;

    int registerCommandListener(IAvmServiceCommandListener iAvmServiceCommandListener) throws RemoteException;

    int sendCallInfo(String str, String str2, String str3, int i, int i2, int i3, boolean z) throws RemoteException;

    void sendCommand(CmdBean cmdBean) throws RemoteException;

    int sendMultiCallInfo(List<AvmCallInfoBean> list) throws RemoteException;

    void startAvm() throws RemoteException;

    void stopAvm() throws RemoteException;

    int unregisterAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException;

    int unregisterCallStatusListener(IAvmCallListener iAvmCallListener) throws RemoteException;

    public static class Default implements IAvmServiceInterface {
        @Override // com.ts.avm.IAvmServiceInterface
        public int getAvmStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int unregisterAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void startAvm() throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void stopAvm() throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void sendCommand(CmdBean cmd) throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerCommandListener(IAvmServiceCommandListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int sendCallInfo(String phoneNum, String contactName, String address, int callTime, int callType, int callState, boolean isMute) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerCallStatusListener(IAvmCallListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int unregisterCallStatusListener(IAvmCallListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int sendMultiCallInfo(List<AvmCallInfoBean> list) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAvmServiceInterface {
        private static final String DESCRIPTOR = "com.ts.avm.IAvmServiceInterface";
        static final int TRANSACTION_getAvmStatus = 1;
        static final int TRANSACTION_registerAvmStatusListener = 2;
        static final int TRANSACTION_registerCallStatusListener = 9;
        static final int TRANSACTION_registerCommandListener = 7;
        static final int TRANSACTION_sendCallInfo = 8;
        static final int TRANSACTION_sendCommand = 6;
        static final int TRANSACTION_sendMultiCallInfo = 11;
        static final int TRANSACTION_startAvm = 4;
        static final int TRANSACTION_stopAvm = 5;
        static final int TRANSACTION_unregisterAvmStatusListener = 3;
        static final int TRANSACTION_unregisterCallStatusListener = 10;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAvmServiceInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAvmServiceInterface)) {
                return (IAvmServiceInterface) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CmdBean _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getAvmStatus();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmServiceListener _arg02 = IAvmServiceListener.Stub.asInterface(data.readStrongBinder());
                    int _result2 = registerAvmStatusListener(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmServiceListener _arg03 = IAvmServiceListener.Stub.asInterface(data.readStrongBinder());
                    int _result3 = unregisterAvmStatusListener(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    startAvm();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    stopAvm();
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = CmdBean.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    sendCommand(_arg0);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmServiceCommandListener _arg04 = IAvmServiceCommandListener.Stub.asInterface(data.readStrongBinder());
                    int _result4 = registerCommandListener(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    String _arg1 = data.readString();
                    String _arg2 = data.readString();
                    int _arg3 = data.readInt();
                    int _arg4 = data.readInt();
                    int _arg5 = data.readInt();
                    boolean _arg6 = data.readInt() != 0;
                    int _result5 = sendCallInfo(_arg05, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6);
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmCallListener _arg06 = IAvmCallListener.Stub.asInterface(data.readStrongBinder());
                    int _result6 = registerCallStatusListener(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    IAvmCallListener _arg07 = IAvmCallListener.Stub.asInterface(data.readStrongBinder());
                    int _result7 = unregisterCallStatusListener(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    List<AvmCallInfoBean> _arg08 = data.createTypedArrayList(AvmCallInfoBean.CREATOR);
                    int _result8 = sendMultiCallInfo(_arg08);
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAvmServiceInterface {
            public static IAvmServiceInterface sDefaultImpl;
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

            @Override // com.ts.avm.IAvmServiceInterface
            public int getAvmStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvmStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int registerAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAvmStatusListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int unregisterAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterAvmStatusListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void startAvm() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startAvm();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void stopAvm() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopAvm();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void sendCommand(CmdBean cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cmd != null) {
                        _data.writeInt(1);
                        cmd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendCommand(cmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int registerCommandListener(IAvmServiceCommandListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCommandListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int sendCallInfo(String phoneNum, String contactName, String address, int callTime, int callType, int callState, boolean isMute) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeString(phoneNum);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(contactName);
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(address);
                } catch (Throwable th4) {
                    th = th4;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeInt(callTime);
                } catch (Throwable th5) {
                    th = th5;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeInt(callType);
                    _data.writeInt(callState);
                    _data.writeInt(isMute ? 1 : 0);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        int iSendCallInfo = Stub.getDefaultImpl().sendCallInfo(phoneNum, contactName, address, callTime, callType, callState, isMute);
                        _reply.recycle();
                        _data.recycle();
                        return iSendCallInfo;
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int registerCallStatusListener(IAvmCallListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallStatusListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int unregisterCallStatusListener(IAvmCallListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterCallStatusListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int sendMultiCallInfo(List<AvmCallInfoBean> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(list);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendMultiCallInfo(list);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAvmServiceInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAvmServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
