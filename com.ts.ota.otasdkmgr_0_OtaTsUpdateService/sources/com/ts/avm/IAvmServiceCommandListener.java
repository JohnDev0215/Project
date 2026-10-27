package com.ts.avm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: loaded from: classes.dex */
public interface IAvmServiceCommandListener extends IInterface {
    void onCommandReceived(CmdBean cmdBean) throws RemoteException;

    public static class Default implements IAvmServiceCommandListener {
        @Override // com.ts.avm.IAvmServiceCommandListener
        public void onCommandReceived(CmdBean cmd) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAvmServiceCommandListener {
        private static final String DESCRIPTOR = "com.ts.avm.IAvmServiceCommandListener";
        static final int TRANSACTION_onCommandReceived = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAvmServiceCommandListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAvmServiceCommandListener)) {
                return (IAvmServiceCommandListener) iin;
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
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = CmdBean.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onCommandReceived(_arg0);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IAvmServiceCommandListener {
            public static IAvmServiceCommandListener sDefaultImpl;
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

            @Override // com.ts.avm.IAvmServiceCommandListener
            public void onCommandReceived(CmdBean cmd) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCommandReceived(cmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAvmServiceCommandListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAvmServiceCommandListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
