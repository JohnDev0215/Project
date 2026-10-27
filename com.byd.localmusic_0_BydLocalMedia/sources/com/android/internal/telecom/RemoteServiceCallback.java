package com.android.internal.telecom;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface RemoteServiceCallback extends IInterface {

    public static class Default implements RemoteServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.RemoteServiceCallback
        public void onError() throws RemoteException {
        }

        @Override // com.android.internal.telecom.RemoteServiceCallback
        public void onResult(List<ComponentName> list, List<IBinder> list2) throws RemoteException {
        }
    }

    void onError() throws RemoteException;

    void onResult(List<ComponentName> list, List<IBinder> list2) throws RemoteException;

    public static abstract class Stub extends Binder implements RemoteServiceCallback {
        private static final String DESCRIPTOR = "com.android.internal.telecom.RemoteServiceCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onResult = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onError";
            }
            if (i != 2) {
                return null;
            }
            return "onResult";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static RemoteServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof RemoteServiceCallback)) {
                return (RemoteServiceCallback) iInterfaceQueryLocalInterface;
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
                onError();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onResult(parcel.createTypedArrayList(ComponentName.CREATOR), parcel.createBinderArrayList());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements RemoteServiceCallback {
            public static RemoteServiceCallback sDefaultImpl;
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

            @Override // com.android.internal.telecom.RemoteServiceCallback
            public void onError() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.RemoteServiceCallback
            public void onResult(List<ComponentName> list, List<IBinder> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeBinderList(list2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResult(list, list2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(RemoteServiceCallback remoteServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (remoteServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = remoteServiceCallback;
            return true;
        }

        public static RemoteServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
