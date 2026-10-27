package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IBatteryStatusCallback extends IInterface {
    void onBatteryStatusNotification(int i, int i2, boolean z) throws RemoteException;

    public static class Default implements IBatteryStatusCallback {
        @Override // com.ts.androidauto.sdk.aidl.IBatteryStatusCallback
        public void onBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IBatteryStatusCallback {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.IBatteryStatusCallback";
        static final int TRANSACTION_onBatteryStatusNotification = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBatteryStatusCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IBatteryStatusCallback)) {
                return (IBatteryStatusCallback) iin;
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
            int _arg1 = data.readInt();
            boolean _arg2 = data.readInt() != 0;
            onBatteryStatusNotification(_arg0, _arg1, _arg2);
            return true;
        }

        private static class Proxy implements IBatteryStatusCallback {
            public static IBatteryStatusCallback sDefaultImpl;
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

            @Override // com.ts.androidauto.sdk.aidl.IBatteryStatusCallback
            public void onBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(batteryLevel);
                    _data.writeInt(timeRemainingS);
                    _data.writeInt(batteryCritical ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBatteryStatusNotification(batteryLevel, timeRemainingS, batteryCritical);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBatteryStatusCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IBatteryStatusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
