package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface INetdEventCallback extends IInterface {
    public static final int CALLBACK_CALLER_CONNECTIVITY_SERVICE = 0;
    public static final int CALLBACK_CALLER_DEVICE_POLICY = 1;
    public static final int CALLBACK_CALLER_NETWORK_WATCHLIST = 2;

    public static class Default implements INetdEventCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.INetdEventCallback
        public void onConnectEvent(String str, int i, long j, int i2) throws RemoteException {
        }

        @Override // android.net.INetdEventCallback
        public void onDnsEvent(int i, int i2, int i3, String str, String[] strArr, int i4, long j, int i5) throws RemoteException {
        }

        @Override // android.net.INetdEventCallback
        public void onNat64PrefixEvent(int i, boolean z, String str, int i2) throws RemoteException {
        }

        @Override // android.net.INetdEventCallback
        public void onPrivateDnsValidationEvent(int i, String str, String str2, boolean z) throws RemoteException {
        }
    }

    void onConnectEvent(String str, int i, long j, int i2) throws RemoteException;

    void onDnsEvent(int i, int i2, int i3, String str, String[] strArr, int i4, long j, int i5) throws RemoteException;

    void onNat64PrefixEvent(int i, boolean z, String str, int i2) throws RemoteException;

    void onPrivateDnsValidationEvent(int i, String str, String str2, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements INetdEventCallback {
        private static final String DESCRIPTOR = "android.net.INetdEventCallback";
        static final int TRANSACTION_onConnectEvent = 4;
        static final int TRANSACTION_onDnsEvent = 1;
        static final int TRANSACTION_onNat64PrefixEvent = 2;
        static final int TRANSACTION_onPrivateDnsValidationEvent = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onDnsEvent";
            }
            if (i == 2) {
                return "onNat64PrefixEvent";
            }
            if (i == 3) {
                return "onPrivateDnsValidationEvent";
            }
            if (i != 4) {
                return null;
            }
            return "onConnectEvent";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetdEventCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetdEventCallback)) {
                return (INetdEventCallback) iInterfaceQueryLocalInterface;
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
                onDnsEvent(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.createStringArray(), parcel.readInt(), parcel.readLong(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onNat64PrefixEvent(parcel.readInt(), parcel.readInt() != 0, parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onPrivateDnsValidationEvent(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onConnectEvent(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements INetdEventCallback {
            public static INetdEventCallback sDefaultImpl;
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

            @Override // android.net.INetdEventCallback
            public void onDnsEvent(int i, int i2, int i3, String str, String[] strArr, int i4, long j, int i5) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i5);
                    try {
                        if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                            parcelObtain.recycle();
                        } else {
                            Stub.getDefaultImpl().onDnsEvent(i, i2, i3, str, strArr, i4, j, i5);
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.net.INetdEventCallback
            public void onNat64PrefixEvent(int i, boolean z, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNat64PrefixEvent(i, z, str, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetdEventCallback
            public void onPrivateDnsValidationEvent(int i, String str, String str2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrivateDnsValidationEvent(i, str, str2, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetdEventCallback
            public void onConnectEvent(String str, int i, long j, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectEvent(str, i, j, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetdEventCallback iNetdEventCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetdEventCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetdEventCallback;
            return true;
        }

        public static INetdEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
