package android.net.lowpan;

import android.net.IpPrefix;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ILowpanInterfaceListener extends IInterface {

    public static class Default implements ILowpanInterfaceListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onConnectedChanged(boolean z) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onEnabledChanged(boolean z) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onLinkAddressAdded(String str) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onLinkAddressRemoved(String str) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onLinkNetworkAdded(IpPrefix ipPrefix) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onLinkNetworkRemoved(IpPrefix ipPrefix) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onLowpanIdentityChanged(LowpanIdentity lowpanIdentity) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onReceiveFromCommissioner(byte[] bArr) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onRoleChanged(String str) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onStateChanged(String str) throws RemoteException {
        }

        @Override // android.net.lowpan.ILowpanInterfaceListener
        public void onUpChanged(boolean z) throws RemoteException {
        }
    }

    void onConnectedChanged(boolean z) throws RemoteException;

    void onEnabledChanged(boolean z) throws RemoteException;

    void onLinkAddressAdded(String str) throws RemoteException;

    void onLinkAddressRemoved(String str) throws RemoteException;

    void onLinkNetworkAdded(IpPrefix ipPrefix) throws RemoteException;

    void onLinkNetworkRemoved(IpPrefix ipPrefix) throws RemoteException;

    void onLowpanIdentityChanged(LowpanIdentity lowpanIdentity) throws RemoteException;

    void onReceiveFromCommissioner(byte[] bArr) throws RemoteException;

    void onRoleChanged(String str) throws RemoteException;

    void onStateChanged(String str) throws RemoteException;

    void onUpChanged(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements ILowpanInterfaceListener {
        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanInterfaceListener";
        static final int TRANSACTION_onConnectedChanged = 2;
        static final int TRANSACTION_onEnabledChanged = 1;
        static final int TRANSACTION_onLinkAddressAdded = 9;
        static final int TRANSACTION_onLinkAddressRemoved = 10;
        static final int TRANSACTION_onLinkNetworkAdded = 7;
        static final int TRANSACTION_onLinkNetworkRemoved = 8;
        static final int TRANSACTION_onLowpanIdentityChanged = 6;
        static final int TRANSACTION_onReceiveFromCommissioner = 11;
        static final int TRANSACTION_onRoleChanged = 4;
        static final int TRANSACTION_onStateChanged = 5;
        static final int TRANSACTION_onUpChanged = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onEnabledChanged";
                case 2:
                    return "onConnectedChanged";
                case 3:
                    return "onUpChanged";
                case 4:
                    return "onRoleChanged";
                case 5:
                    return "onStateChanged";
                case 6:
                    return "onLowpanIdentityChanged";
                case 7:
                    return "onLinkNetworkAdded";
                case 8:
                    return "onLinkNetworkRemoved";
                case 9:
                    return "onLinkAddressAdded";
                case 10:
                    return "onLinkAddressRemoved";
                case 11:
                    return "onReceiveFromCommissioner";
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

        public static ILowpanInterfaceListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILowpanInterfaceListener)) {
                return (ILowpanInterfaceListener) iInterfaceQueryLocalInterface;
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
                    onEnabledChanged(parcel.readInt() != 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConnectedChanged(parcel.readInt() != 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUpChanged(parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRoleChanged(parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStateChanged(parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLowpanIdentityChanged(parcel.readInt() != 0 ? LowpanIdentity.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLinkNetworkAdded(parcel.readInt() != 0 ? IpPrefix.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLinkNetworkRemoved(parcel.readInt() != 0 ? IpPrefix.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLinkAddressAdded(parcel.readString());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLinkAddressRemoved(parcel.readString());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReceiveFromCommissioner(parcel.createByteArray());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ILowpanInterfaceListener {
            public static ILowpanInterfaceListener sDefaultImpl;
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

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onEnabledChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEnabledChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onConnectedChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectedChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onUpChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUpChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onRoleChanged(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRoleChanged(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onStateChanged(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStateChanged(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onLowpanIdentityChanged(LowpanIdentity lowpanIdentity) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lowpanIdentity != null) {
                        parcelObtain.writeInt(1);
                        lowpanIdentity.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLowpanIdentityChanged(lowpanIdentity);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onLinkNetworkAdded(IpPrefix ipPrefix) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ipPrefix != null) {
                        parcelObtain.writeInt(1);
                        ipPrefix.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLinkNetworkAdded(ipPrefix);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onLinkNetworkRemoved(IpPrefix ipPrefix) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ipPrefix != null) {
                        parcelObtain.writeInt(1);
                        ipPrefix.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLinkNetworkRemoved(ipPrefix);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onLinkAddressAdded(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLinkAddressAdded(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onLinkAddressRemoved(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLinkAddressRemoved(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.lowpan.ILowpanInterfaceListener
            public void onReceiveFromCommissioner(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReceiveFromCommissioner(bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILowpanInterfaceListener iLowpanInterfaceListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLowpanInterfaceListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLowpanInterfaceListener;
            return true;
        }

        public static ILowpanInterfaceListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
