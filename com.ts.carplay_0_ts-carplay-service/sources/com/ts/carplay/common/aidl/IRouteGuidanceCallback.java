package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.carplay.common.data.RouteGuidanceInfo;

/* JADX INFO: loaded from: classes.dex */
public interface IRouteGuidanceCallback extends IInterface {
    void onRouteGuidanceInfoChanged(RouteGuidanceInfo routeGuidanceInfo) throws RemoteException;

    public static class Default implements IRouteGuidanceCallback {
        @Override // com.ts.carplay.common.aidl.IRouteGuidanceCallback
        public void onRouteGuidanceInfoChanged(RouteGuidanceInfo info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IRouteGuidanceCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.IRouteGuidanceCallback";
        static final int TRANSACTION_onRouteGuidanceInfoChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRouteGuidanceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IRouteGuidanceCallback)) {
                return (IRouteGuidanceCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RouteGuidanceInfo _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = RouteGuidanceInfo.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onRouteGuidanceInfoChanged(_arg0);
            return true;
        }

        private static class Proxy implements IRouteGuidanceCallback {
            public static IRouteGuidanceCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.IRouteGuidanceCallback
            public void onRouteGuidanceInfoChanged(RouteGuidanceInfo info) throws RemoteException {
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
                        Stub.getDefaultImpl().onRouteGuidanceInfoChanged(info);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRouteGuidanceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IRouteGuidanceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
