package android.net;

import android.net.ipmemorystore.Blob;
import android.net.ipmemorystore.IOnBlobRetrievedListener;
import android.net.ipmemorystore.IOnL2KeyResponseListener;
import android.net.ipmemorystore.IOnNetworkAttributesRetrievedListener;
import android.net.ipmemorystore.IOnSameL3NetworkResponseListener;
import android.net.ipmemorystore.IOnStatusAndCountListener;
import android.net.ipmemorystore.IOnStatusListener;
import android.net.ipmemorystore.NetworkAttributesParcelable;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IIpMemoryStore extends IInterface {
    public static final String HASH = "31826566143ef882d67fac9f24566f73df4907b4";
    public static final int VERSION = 7;

    void delete(String str, boolean z, IOnStatusAndCountListener iOnStatusAndCountListener) throws RemoteException;

    void deleteCluster(String str, boolean z, IOnStatusAndCountListener iOnStatusAndCountListener) throws RemoteException;

    void factoryReset() throws RemoteException;

    void findL2Key(NetworkAttributesParcelable networkAttributesParcelable, IOnL2KeyResponseListener iOnL2KeyResponseListener) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void isSameNetwork(String str, String str2, IOnSameL3NetworkResponseListener iOnSameL3NetworkResponseListener) throws RemoteException;

    void retrieveBlob(String str, String str2, String str3, IOnBlobRetrievedListener iOnBlobRetrievedListener) throws RemoteException;

    void retrieveNetworkAttributes(String str, IOnNetworkAttributesRetrievedListener iOnNetworkAttributesRetrievedListener) throws RemoteException;

    void storeBlob(String str, String str2, String str3, Blob blob, IOnStatusListener iOnStatusListener) throws RemoteException;

    void storeNetworkAttributes(String str, NetworkAttributesParcelable networkAttributesParcelable, IOnStatusListener iOnStatusListener) throws RemoteException;

    public static class Default implements IIpMemoryStore {
        @Override // android.net.IIpMemoryStore
        public void storeNetworkAttributes(String l2Key, NetworkAttributesParcelable attributes, IOnStatusListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void storeBlob(String l2Key, String clientId, String name, Blob data, IOnStatusListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void findL2Key(NetworkAttributesParcelable attributes, IOnL2KeyResponseListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void isSameNetwork(String l2Key1, String l2Key2, IOnSameL3NetworkResponseListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void retrieveNetworkAttributes(String l2Key, IOnNetworkAttributesRetrievedListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void retrieveBlob(String l2Key, String clientId, String name, IOnBlobRetrievedListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void factoryReset() throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void delete(String l2Key, boolean needWipe, IOnStatusAndCountListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public void deleteCluster(String cluster, boolean needWipe, IOnStatusAndCountListener listener) throws RemoteException {
        }

        @Override // android.net.IIpMemoryStore
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.net.IIpMemoryStore
        public String getInterfaceHash() {
            return INetd.NEXTHOP_NONE;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IIpMemoryStore {
        private static final String DESCRIPTOR = "android$net$IIpMemoryStore".replace('$', '.');
        static final int TRANSACTION_delete = 8;
        static final int TRANSACTION_deleteCluster = 9;
        static final int TRANSACTION_factoryReset = 7;
        static final int TRANSACTION_findL2Key = 3;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_isSameNetwork = 4;
        static final int TRANSACTION_retrieveBlob = 6;
        static final int TRANSACTION_retrieveNetworkAttributes = 5;
        static final int TRANSACTION_storeBlob = 2;
        static final int TRANSACTION_storeNetworkAttributes = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIpMemoryStore asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IIpMemoryStore)) {
                return (IIpMemoryStore) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            NetworkAttributesParcelable _arg1;
            Blob _arg3;
            NetworkAttributesParcelable _arg0;
            boolean _arg12;
            String descriptor = DESCRIPTOR;
            if (code == 1598968902) {
                reply.writeString(descriptor);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(descriptor);
                    String _arg02 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = NetworkAttributesParcelable.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    IOnStatusListener _arg2 = IOnStatusListener.Stub.asInterface(data.readStrongBinder());
                    storeNetworkAttributes(_arg02, _arg1, _arg2);
                    return true;
                case 2:
                    data.enforceInterface(descriptor);
                    String _arg03 = data.readString();
                    String _arg13 = data.readString();
                    String _arg22 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = Blob.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    IOnStatusListener _arg4 = IOnStatusListener.Stub.asInterface(data.readStrongBinder());
                    storeBlob(_arg03, _arg13, _arg22, _arg3, _arg4);
                    return true;
                case 3:
                    data.enforceInterface(descriptor);
                    if (data.readInt() != 0) {
                        _arg0 = NetworkAttributesParcelable.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    findL2Key(_arg0, IOnL2KeyResponseListener.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 4:
                    data.enforceInterface(descriptor);
                    String _arg04 = data.readString();
                    String _arg14 = data.readString();
                    IOnSameL3NetworkResponseListener _arg23 = IOnSameL3NetworkResponseListener.Stub.asInterface(data.readStrongBinder());
                    isSameNetwork(_arg04, _arg14, _arg23);
                    return true;
                case TRANSACTION_retrieveNetworkAttributes /* 5 */:
                    data.enforceInterface(descriptor);
                    String _arg05 = data.readString();
                    retrieveNetworkAttributes(_arg05, IOnNetworkAttributesRetrievedListener.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 6:
                    data.enforceInterface(descriptor);
                    String _arg06 = data.readString();
                    String _arg15 = data.readString();
                    String _arg24 = data.readString();
                    IOnBlobRetrievedListener _arg32 = IOnBlobRetrievedListener.Stub.asInterface(data.readStrongBinder());
                    retrieveBlob(_arg06, _arg15, _arg24, _arg32);
                    return true;
                case 7:
                    data.enforceInterface(descriptor);
                    factoryReset();
                    return true;
                case 8:
                    data.enforceInterface(descriptor);
                    String _arg07 = data.readString();
                    _arg12 = data.readInt() != 0;
                    IOnStatusAndCountListener _arg25 = IOnStatusAndCountListener.Stub.asInterface(data.readStrongBinder());
                    delete(_arg07, _arg12, _arg25);
                    return true;
                case TRANSACTION_deleteCluster /* 9 */:
                    data.enforceInterface(descriptor);
                    String _arg08 = data.readString();
                    _arg12 = data.readInt() != 0;
                    IOnStatusAndCountListener _arg26 = IOnStatusAndCountListener.Stub.asInterface(data.readStrongBinder());
                    deleteCluster(_arg08, _arg12, _arg26);
                    return true;
                default:
                    switch (code) {
                        case TRANSACTION_getInterfaceHash /* 16777214 */:
                            data.enforceInterface(descriptor);
                            reply.writeNoException();
                            reply.writeString(getInterfaceHash());
                            return true;
                        case TRANSACTION_getInterfaceVersion /* 16777215 */:
                            data.enforceInterface(descriptor);
                            reply.writeNoException();
                            reply.writeInt(getInterfaceVersion());
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        private static class Proxy implements IIpMemoryStore {
            public static IIpMemoryStore sDefaultImpl;
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

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

            @Override // android.net.IIpMemoryStore
            public void storeNetworkAttributes(String l2Key, NetworkAttributesParcelable attributes, IOnStatusListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(l2Key);
                    if (attributes != null) {
                        _data.writeInt(1);
                        attributes.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().storeNetworkAttributes(l2Key, attributes, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void storeBlob(String l2Key, String clientId, String name, Blob data, IOnStatusListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(l2Key);
                    _data.writeString(clientId);
                    _data.writeString(name);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().storeBlob(l2Key, clientId, name, data, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void findL2Key(NetworkAttributesParcelable attributes, IOnL2KeyResponseListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (attributes != null) {
                        _data.writeInt(1);
                        attributes.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().findL2Key(attributes, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void isSameNetwork(String l2Key1, String l2Key2, IOnSameL3NetworkResponseListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(l2Key1);
                    _data.writeString(l2Key2);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isSameNetwork(l2Key1, l2Key2, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void retrieveNetworkAttributes(String l2Key, IOnNetworkAttributesRetrievedListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(l2Key);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retrieveNetworkAttributes, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retrieveNetworkAttributes(l2Key, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void retrieveBlob(String l2Key, String clientId, String name, IOnBlobRetrievedListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(l2Key);
                    _data.writeString(clientId);
                    _data.writeString(name);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retrieveBlob(l2Key, clientId, name, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void factoryReset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().factoryReset();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void delete(String l2Key, boolean needWipe, IOnStatusAndCountListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(l2Key);
                    _data.writeInt(needWipe ? 1 : 0);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().delete(l2Key, needWipe, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public void deleteCluster(String cluster, boolean needWipe, IOnStatusAndCountListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(cluster);
                    _data.writeInt(needWipe ? 1 : 0);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_deleteCluster, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteCluster(cluster, needWipe, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.IIpMemoryStore
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(Stub.DESCRIPTOR);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_getInterfaceVersion, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceVersion();
                        }
                        reply.readException();
                        this.mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedVersion;
            }

            @Override // android.net.IIpMemoryStore
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(this.mCachedHash)) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(Stub.DESCRIPTOR);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_getInterfaceHash, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceHash();
                        }
                        reply.readException();
                        this.mCachedHash = reply.readString();
                        reply.recycle();
                        data.recycle();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedHash;
            }
        }

        public static boolean setDefaultImpl(IIpMemoryStore impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IIpMemoryStore getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
