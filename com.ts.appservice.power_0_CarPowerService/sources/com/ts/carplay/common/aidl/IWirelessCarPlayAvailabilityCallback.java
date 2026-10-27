package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IWirelessCarPlayAvailabilityCallback extends IInterface {
    void onWirelessCarPlayAvailabilityChanged(String str, boolean z) throws RemoteException;

    public static class Default implements IWirelessCarPlayAvailabilityCallback {
        @Override // com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback
        public void onWirelessCarPlayAvailabilityChanged(String btAddr, boolean enabled) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IWirelessCarPlayAvailabilityCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback";
        static final int TRANSACTION_onWirelessCarPlayAvailabilityChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWirelessCarPlayAvailabilityCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IWirelessCarPlayAvailabilityCallback)) {
                return (IWirelessCarPlayAvailabilityCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            boolean _arg1 = data.readInt() != 0;
            onWirelessCarPlayAvailabilityChanged(_arg0, _arg1);
            return true;
        }

        private static class Proxy implements IWirelessCarPlayAvailabilityCallback {
            public static IWirelessCarPlayAvailabilityCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback
            public void onWirelessCarPlayAvailabilityChanged(String btAddr, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(btAddr);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWirelessCarPlayAvailabilityChanged(btAddr, enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWirelessCarPlayAvailabilityCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IWirelessCarPlayAvailabilityCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
