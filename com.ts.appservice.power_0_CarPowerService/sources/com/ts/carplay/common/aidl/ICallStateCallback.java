package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.carplay.common.data.CallStateInfo;

/* JADX INFO: loaded from: classes.dex */
public interface ICallStateCallback extends IInterface {
    void onCallStateInfoChanged(CallStateInfo callStateInfo) throws RemoteException;

    public static class Default implements ICallStateCallback {
        @Override // com.ts.carplay.common.aidl.ICallStateCallback
        public void onCallStateInfoChanged(CallStateInfo info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICallStateCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.ICallStateCallback";
        static final int TRANSACTION_onCallStateInfoChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICallStateCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICallStateCallback)) {
                return (ICallStateCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CallStateInfo _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = CallStateInfo.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onCallStateInfoChanged(_arg0);
            return true;
        }

        private static class Proxy implements ICallStateCallback {
            public static ICallStateCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.ICallStateCallback
            public void onCallStateInfoChanged(CallStateInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallStateInfoChanged(info);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICallStateCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICallStateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
