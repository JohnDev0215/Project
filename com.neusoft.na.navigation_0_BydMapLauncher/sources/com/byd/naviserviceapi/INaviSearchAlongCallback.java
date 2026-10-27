package com.byd.naviserviceapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface INaviSearchAlongCallback extends IInterface {

    public static class Default implements INaviSearchAlongCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviSearchAlongCallback
        public void onResult(int code, List<Place> listPlace) throws RemoteException {
        }
    }

    void onResult(int code, List<Place> listPlace) throws RemoteException;

    public static abstract class Stub extends Binder implements INaviSearchAlongCallback {
        private static final String DESCRIPTOR = "com.byd.naviserviceapi.INaviSearchAlongCallback";
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviSearchAlongCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INaviSearchAlongCallback)) {
                return (INaviSearchAlongCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
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
            onResult(data.readInt(), data.createTypedArrayList(Place.CREATOR));
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements INaviSearchAlongCallback {
            public static INaviSearchAlongCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.naviserviceapi.INaviSearchAlongCallback
            public void onResult(int code, List<Place> listPlace) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeTypedList(listPlace);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResult(code, listPlace);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INaviSearchAlongCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INaviSearchAlongCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
