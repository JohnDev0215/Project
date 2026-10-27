package com.android.internal.app;

import android.app.AsyncNotedAppOp;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IAppOpsAsyncNotedCallback extends IInterface {

    public static class Default implements IAppOpsAsyncNotedCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsAsyncNotedCallback
        public void opNoted(AsyncNotedAppOp asyncNotedAppOp) throws RemoteException {
        }
    }

    void opNoted(AsyncNotedAppOp asyncNotedAppOp) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppOpsAsyncNotedCallback {
        private static final String DESCRIPTOR = "com.android.internal.app.IAppOpsAsyncNotedCallback";
        static final int TRANSACTION_opNoted = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "opNoted";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppOpsAsyncNotedCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppOpsAsyncNotedCallback)) {
                return (IAppOpsAsyncNotedCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                opNoted(parcel.readInt() != 0 ? AsyncNotedAppOp.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAppOpsAsyncNotedCallback {
            public static IAppOpsAsyncNotedCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.internal.app.IAppOpsAsyncNotedCallback
            public void opNoted(AsyncNotedAppOp asyncNotedAppOp) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (asyncNotedAppOp != null) {
                        parcelObtain.writeInt(1);
                        asyncNotedAppOp.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().opNoted(asyncNotedAppOp);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppOpsAsyncNotedCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppOpsAsyncNotedCallback;
            return true;
        }

        public static IAppOpsAsyncNotedCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
