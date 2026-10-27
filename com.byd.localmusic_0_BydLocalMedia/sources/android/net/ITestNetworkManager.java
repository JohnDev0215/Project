package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ITestNetworkManager extends IInterface {

    public static class Default implements ITestNetworkManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.ITestNetworkManager
        public TestNetworkInterface createTapInterface() throws RemoteException {
            return null;
        }

        @Override // android.net.ITestNetworkManager
        public TestNetworkInterface createTunInterface(LinkAddress[] linkAddressArr) throws RemoteException {
            return null;
        }

        @Override // android.net.ITestNetworkManager
        public void setupTestNetwork(String str, LinkProperties linkProperties, boolean z, int[] iArr, IBinder iBinder) throws RemoteException {
        }

        @Override // android.net.ITestNetworkManager
        public void teardownTestNetwork(int i) throws RemoteException {
        }
    }

    TestNetworkInterface createTapInterface() throws RemoteException;

    TestNetworkInterface createTunInterface(LinkAddress[] linkAddressArr) throws RemoteException;

    void setupTestNetwork(String str, LinkProperties linkProperties, boolean z, int[] iArr, IBinder iBinder) throws RemoteException;

    void teardownTestNetwork(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ITestNetworkManager {
        private static final String DESCRIPTOR = "android.net.ITestNetworkManager";
        static final int TRANSACTION_createTapInterface = 2;
        static final int TRANSACTION_createTunInterface = 1;
        static final int TRANSACTION_setupTestNetwork = 3;
        static final int TRANSACTION_teardownTestNetwork = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "createTunInterface";
            }
            if (i == 2) {
                return "createTapInterface";
            }
            if (i == 3) {
                return "setupTestNetwork";
            }
            if (i != 4) {
                return null;
            }
            return "teardownTestNetwork";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITestNetworkManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITestNetworkManager)) {
                return (ITestNetworkManager) iInterfaceQueryLocalInterface;
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
                TestNetworkInterface testNetworkInterfaceCreateTunInterface = createTunInterface((LinkAddress[]) parcel.createTypedArray(LinkAddress.CREATOR));
                parcel2.writeNoException();
                if (testNetworkInterfaceCreateTunInterface != null) {
                    parcel2.writeInt(1);
                    testNetworkInterfaceCreateTunInterface.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                TestNetworkInterface testNetworkInterfaceCreateTapInterface = createTapInterface();
                parcel2.writeNoException();
                if (testNetworkInterfaceCreateTapInterface != null) {
                    parcel2.writeInt(1);
                    testNetworkInterfaceCreateTapInterface.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                setupTestNetwork(parcel.readString(), parcel.readInt() != 0 ? LinkProperties.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.createIntArray(), parcel.readStrongBinder());
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            teardownTestNetwork(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ITestNetworkManager {
            public static ITestNetworkManager sDefaultImpl;
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

            @Override // android.net.ITestNetworkManager
            public TestNetworkInterface createTunInterface(LinkAddress[] linkAddressArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(linkAddressArr, 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTunInterface(linkAddressArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? TestNetworkInterface.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ITestNetworkManager
            public TestNetworkInterface createTapInterface() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTapInterface();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? TestNetworkInterface.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ITestNetworkManager
            public void setupTestNetwork(String str, LinkProperties linkProperties, boolean z, int[] iArr, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    int i = 1;
                    if (linkProperties != null) {
                        parcelObtain.writeInt(1);
                        linkProperties.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setupTestNetwork(str, linkProperties, z, iArr, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ITestNetworkManager
            public void teardownTestNetwork(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().teardownTestNetwork(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITestNetworkManager iTestNetworkManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTestNetworkManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTestNetworkManager;
            return true;
        }

        public static ITestNetworkManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
