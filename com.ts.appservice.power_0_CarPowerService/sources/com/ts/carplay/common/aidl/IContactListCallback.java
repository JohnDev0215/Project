package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IContactListCallback extends IInterface {
    void onFavoriteListChanged(List<FavoriteContactListInfo> list) throws RemoteException;

    void onFavoriteListCountChanged(int i) throws RemoteException;

    void onRecentListChanged(List<RecentContactListInfo> list) throws RemoteException;

    void onRecentListCountChanged(int i) throws RemoteException;

    public static class Default implements IContactListCallback {
        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onRecentListCountChanged(int listCount) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onRecentListChanged(List<RecentContactListInfo> list) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onFavoriteListCountChanged(int listCount) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onFavoriteListChanged(List<FavoriteContactListInfo> list) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IContactListCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.IContactListCallback";
        static final int TRANSACTION_onFavoriteListChanged = 4;
        static final int TRANSACTION_onFavoriteListCountChanged = 3;
        static final int TRANSACTION_onRecentListChanged = 2;
        static final int TRANSACTION_onRecentListCountChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContactListCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IContactListCallback)) {
                return (IContactListCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                onRecentListCountChanged(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                List<RecentContactListInfo> _arg02 = data.createTypedArrayList(RecentContactListInfo.CREATOR);
                onRecentListChanged(_arg02);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int _arg03 = data.readInt();
                onFavoriteListCountChanged(_arg03);
                return true;
            }
            if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            List<FavoriteContactListInfo> _arg04 = data.createTypedArrayList(FavoriteContactListInfo.CREATOR);
            onFavoriteListChanged(_arg04);
            return true;
        }

        private static class Proxy implements IContactListCallback {
            public static IContactListCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.IContactListCallback
            public void onRecentListCountChanged(int listCount) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(listCount);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecentListCountChanged(listCount);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.IContactListCallback
            public void onRecentListChanged(List<RecentContactListInfo> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(list);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecentListChanged(list);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.IContactListCallback
            public void onFavoriteListCountChanged(int listCount) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(listCount);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFavoriteListCountChanged(listCount);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.IContactListCallback
            public void onFavoriteListChanged(List<FavoriteContactListInfo> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(list);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFavoriteListChanged(list);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContactListCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IContactListCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
