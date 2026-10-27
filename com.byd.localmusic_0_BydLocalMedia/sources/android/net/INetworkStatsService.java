package android.net;

import android.net.INetworkStatsSession;
import android.net.netstats.provider.INetworkStatsProvider;
import android.net.netstats.provider.INetworkStatsProviderCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.net.VpnInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkStatsService extends IInterface {

    public static class Default implements INetworkStatsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public void forceUpdate() throws RemoteException {
        }

        @Override // android.net.INetworkStatsService
        public void forceUpdateIfaces(Network[] networkArr, NetworkState[] networkStateArr, String str, VpnInfo[] vpnInfoArr) throws RemoteException {
        }

        @Override // android.net.INetworkStatsService
        public NetworkStats getDataLayerSnapshotForUid(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public NetworkStats getDetailedUidStats(String[] strArr) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public long getIfaceStats(String str, int i) throws RemoteException {
            return 0L;
        }

        @Override // android.net.INetworkStatsService
        public String[] getMobileIfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public long getTotalStats(int i) throws RemoteException {
            return 0L;
        }

        @Override // android.net.INetworkStatsService
        public long getUidStats(int i, int i2) throws RemoteException {
            return 0L;
        }

        @Override // android.net.INetworkStatsService
        public void incrementOperationCount(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.net.INetworkStatsService
        public INetworkStatsSession openSession() throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public INetworkStatsSession openSessionForUsageStats(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public INetworkStatsProviderCallback registerNetworkStatsProvider(String str, INetworkStatsProvider iNetworkStatsProvider) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public DataUsageRequest registerUsageCallback(String str, DataUsageRequest dataUsageRequest, Messenger messenger, IBinder iBinder) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsService
        public void unregisterUsageRequest(DataUsageRequest dataUsageRequest) throws RemoteException {
        }
    }

    void forceUpdate() throws RemoteException;

    void forceUpdateIfaces(Network[] networkArr, NetworkState[] networkStateArr, String str, VpnInfo[] vpnInfoArr) throws RemoteException;

    NetworkStats getDataLayerSnapshotForUid(int i) throws RemoteException;

    NetworkStats getDetailedUidStats(String[] strArr) throws RemoteException;

    long getIfaceStats(String str, int i) throws RemoteException;

    String[] getMobileIfaces() throws RemoteException;

    long getTotalStats(int i) throws RemoteException;

    long getUidStats(int i, int i2) throws RemoteException;

    void incrementOperationCount(int i, int i2, int i3) throws RemoteException;

    INetworkStatsSession openSession() throws RemoteException;

    INetworkStatsSession openSessionForUsageStats(int i, String str) throws RemoteException;

    INetworkStatsProviderCallback registerNetworkStatsProvider(String str, INetworkStatsProvider iNetworkStatsProvider) throws RemoteException;

    DataUsageRequest registerUsageCallback(String str, DataUsageRequest dataUsageRequest, Messenger messenger, IBinder iBinder) throws RemoteException;

    void unregisterUsageRequest(DataUsageRequest dataUsageRequest) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkStatsService {
        private static final String DESCRIPTOR = "android.net.INetworkStatsService";
        static final int TRANSACTION_forceUpdate = 8;
        static final int TRANSACTION_forceUpdateIfaces = 7;
        static final int TRANSACTION_getDataLayerSnapshotForUid = 3;
        static final int TRANSACTION_getDetailedUidStats = 4;
        static final int TRANSACTION_getIfaceStats = 12;
        static final int TRANSACTION_getMobileIfaces = 5;
        static final int TRANSACTION_getTotalStats = 13;
        static final int TRANSACTION_getUidStats = 11;
        static final int TRANSACTION_incrementOperationCount = 6;
        static final int TRANSACTION_openSession = 1;
        static final int TRANSACTION_openSessionForUsageStats = 2;
        static final int TRANSACTION_registerNetworkStatsProvider = 14;
        static final int TRANSACTION_registerUsageCallback = 9;
        static final int TRANSACTION_unregisterUsageRequest = 10;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "openSession";
                case 2:
                    return "openSessionForUsageStats";
                case 3:
                    return "getDataLayerSnapshotForUid";
                case 4:
                    return "getDetailedUidStats";
                case 5:
                    return "getMobileIfaces";
                case 6:
                    return "incrementOperationCount";
                case 7:
                    return "forceUpdateIfaces";
                case 8:
                    return "forceUpdate";
                case 9:
                    return "registerUsageCallback";
                case 10:
                    return "unregisterUsageRequest";
                case 11:
                    return "getUidStats";
                case 12:
                    return "getIfaceStats";
                case 13:
                    return "getTotalStats";
                case 14:
                    return "registerNetworkStatsProvider";
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

        public static INetworkStatsService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkStatsService)) {
                return (INetworkStatsService) iInterfaceQueryLocalInterface;
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
                    INetworkStatsSession iNetworkStatsSessionOpenSession = openSession();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iNetworkStatsSessionOpenSession != null ? iNetworkStatsSessionOpenSession.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    INetworkStatsSession iNetworkStatsSessionOpenSessionForUsageStats = openSessionForUsageStats(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iNetworkStatsSessionOpenSessionForUsageStats != null ? iNetworkStatsSessionOpenSessionForUsageStats.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStats dataLayerSnapshotForUid = getDataLayerSnapshotForUid(parcel.readInt());
                    parcel2.writeNoException();
                    if (dataLayerSnapshotForUid != null) {
                        parcel2.writeInt(1);
                        dataLayerSnapshotForUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStats detailedUidStats = getDetailedUidStats(parcel.createStringArray());
                    parcel2.writeNoException();
                    if (detailedUidStats != null) {
                        parcel2.writeInt(1);
                        detailedUidStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] mobileIfaces = getMobileIfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(mobileIfaces);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    incrementOperationCount(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    forceUpdateIfaces((Network[]) parcel.createTypedArray(Network.CREATOR), (NetworkState[]) parcel.createTypedArray(NetworkState.CREATOR), parcel.readString(), (VpnInfo[]) parcel.createTypedArray(VpnInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    forceUpdate();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    DataUsageRequest dataUsageRequestRegisterUsageCallback = registerUsageCallback(parcel.readString(), parcel.readInt() != 0 ? DataUsageRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder());
                    parcel2.writeNoException();
                    if (dataUsageRequestRegisterUsageCallback != null) {
                        parcel2.writeInt(1);
                        dataUsageRequestRegisterUsageCallback.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterUsageRequest(parcel.readInt() != 0 ? DataUsageRequest.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    long uidStats = getUidStats(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(uidStats);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    long ifaceStats = getIfaceStats(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(ifaceStats);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    long totalStats = getTotalStats(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(totalStats);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    INetworkStatsProviderCallback iNetworkStatsProviderCallbackRegisterNetworkStatsProvider = registerNetworkStatsProvider(parcel.readString(), INetworkStatsProvider.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iNetworkStatsProviderCallbackRegisterNetworkStatsProvider != null ? iNetworkStatsProviderCallbackRegisterNetworkStatsProvider.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INetworkStatsService {
            public static INetworkStatsService sDefaultImpl;
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

            @Override // android.net.INetworkStatsService
            public INetworkStatsSession openSession() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openSession();
                    }
                    parcelObtain2.readException();
                    return INetworkStatsSession.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public INetworkStatsSession openSessionForUsageStats(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openSessionForUsageStats(i, str);
                    }
                    parcelObtain2.readException();
                    return INetworkStatsSession.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public NetworkStats getDataLayerSnapshotForUid(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataLayerSnapshotForUid(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public NetworkStats getDetailedUidStats(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDetailedUidStats(strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public String[] getMobileIfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMobileIfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public void incrementOperationCount(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().incrementOperationCount(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public void forceUpdateIfaces(Network[] networkArr, NetworkState[] networkStateArr, String str, VpnInfo[] vpnInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(networkArr, 0);
                    parcelObtain.writeTypedArray(networkStateArr, 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedArray(vpnInfoArr, 0);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().forceUpdateIfaces(networkArr, networkStateArr, str, vpnInfoArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public void forceUpdate() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().forceUpdate();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public DataUsageRequest registerUsageCallback(String str, DataUsageRequest dataUsageRequest, Messenger messenger, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (dataUsageRequest != null) {
                        parcelObtain.writeInt(1);
                        dataUsageRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerUsageCallback(str, dataUsageRequest, messenger, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? DataUsageRequest.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public void unregisterUsageRequest(DataUsageRequest dataUsageRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dataUsageRequest != null) {
                        parcelObtain.writeInt(1);
                        dataUsageRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterUsageRequest(dataUsageRequest);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public long getUidStats(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUidStats(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public long getIfaceStats(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIfaceStats(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public long getTotalStats(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotalStats(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsService
            public INetworkStatsProviderCallback registerNetworkStatsProvider(String str, INetworkStatsProvider iNetworkStatsProvider) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iNetworkStatsProvider != null ? iNetworkStatsProvider.asBinder() : null);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerNetworkStatsProvider(str, iNetworkStatsProvider);
                    }
                    parcelObtain2.readException();
                    return INetworkStatsProviderCallback.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkStatsService iNetworkStatsService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetworkStatsService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetworkStatsService;
            return true;
        }

        public static INetworkStatsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
