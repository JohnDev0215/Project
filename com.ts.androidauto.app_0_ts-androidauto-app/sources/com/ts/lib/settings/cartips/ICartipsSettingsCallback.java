package com.ts.lib.settings.cartips;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ICartipsSettingsCallback extends IInterface {
    void onCarTipsSoundTypeChanged(int i) throws RemoteException;

    public static class Default implements ICartipsSettingsCallback {
        @Override // com.ts.lib.settings.cartips.ICartipsSettingsCallback
        public void onCarTipsSoundTypeChanged(int value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICartipsSettingsCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.cartips.ICartipsSettingsCallback";
        static final int TRANSACTION_onCarTipsSoundTypeChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICartipsSettingsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICartipsSettingsCallback)) {
                return (ICartipsSettingsCallback) iin;
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
            int _arg0 = data.readInt();
            onCarTipsSoundTypeChanged(_arg0);
            return true;
        }

        private static class Proxy implements ICartipsSettingsCallback {
            public static ICartipsSettingsCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.cartips.ICartipsSettingsCallback
            public void onCarTipsSoundTypeChanged(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarTipsSoundTypeChanged(value);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICartipsSettingsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICartipsSettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
