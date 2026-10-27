package android.media;

import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaHTTPConnection extends IInterface {

    public static class Default implements IMediaHTTPConnection {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IMediaHTTPConnection
        public IBinder connect(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaHTTPConnection
        public void disconnect() throws RemoteException {
        }

        @Override // android.media.IMediaHTTPConnection
        public String getMIMEType() throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaHTTPConnection
        public long getSize() throws RemoteException {
            return 0L;
        }

        @Override // android.media.IMediaHTTPConnection
        public String getUri() throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaHTTPConnection
        public int readAt(long j, int i) throws RemoteException {
            return 0;
        }
    }

    IBinder connect(String str, String str2) throws RemoteException;

    void disconnect() throws RemoteException;

    String getMIMEType() throws RemoteException;

    long getSize() throws RemoteException;

    String getUri() throws RemoteException;

    int readAt(long j, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaHTTPConnection {
        private static final String DESCRIPTOR = "android.media.IMediaHTTPConnection";
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getMIMEType = 5;
        static final int TRANSACTION_getSize = 4;
        static final int TRANSACTION_getUri = 6;
        static final int TRANSACTION_readAt = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return MediaMetrics.Value.CONNECT;
                case 2:
                    return MediaMetrics.Value.DISCONNECT;
                case 3:
                    return "readAt";
                case 4:
                    return "getSize";
                case 5:
                    return "getMIMEType";
                case 6:
                    return "getUri";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaHTTPConnection asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaHTTPConnection)) {
                return (IMediaHTTPConnection) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder iBinderConnect = connect(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iBinderConnect);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnect();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int at = readAt(parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(at);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    long size = getSize();
                    parcel2.writeNoException();
                    parcel2.writeLong(size);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String mIMEType = getMIMEType();
                    parcel2.writeNoException();
                    parcel2.writeString(mIMEType);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String uri = getUri();
                    parcel2.writeNoException();
                    parcel2.writeString(uri);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaHTTPConnection {
            public static IMediaHTTPConnection sDefaultImpl;
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

            @Override // android.media.IMediaHTTPConnection
            public IBinder connect(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connect(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaHTTPConnection
            public void disconnect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnect();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaHTTPConnection
            public int readAt(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().readAt(j, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaHTTPConnection
            public long getSize() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSize();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaHTTPConnection
            public String getMIMEType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMIMEType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaHTTPConnection
            public String getUri() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUri();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaHTTPConnection iMediaHTTPConnection) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaHTTPConnection == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaHTTPConnection;
            return true;
        }

        public static IMediaHTTPConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
