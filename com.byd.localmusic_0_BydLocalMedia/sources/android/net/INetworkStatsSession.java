package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkStatsSession extends IInterface {

    public static class Default implements INetworkStatsSession {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.INetworkStatsSession
        public void close() throws RemoteException {
        }

        @Override // android.net.INetworkStatsSession
        public NetworkStats getDeviceSummaryForNetwork(NetworkTemplate networkTemplate, long j, long j2) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsSession
        public NetworkStatsHistory getHistoryForNetwork(NetworkTemplate networkTemplate, int i) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsSession
        public NetworkStatsHistory getHistoryForUid(NetworkTemplate networkTemplate, int i, int i2, int i3, int i4) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsSession
        public NetworkStatsHistory getHistoryIntervalForUid(NetworkTemplate networkTemplate, int i, int i2, int i3, int i4, long j, long j2) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsSession
        public int[] getRelevantUids() throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsSession
        public NetworkStats getSummaryForAllUid(NetworkTemplate networkTemplate, long j, long j2, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.net.INetworkStatsSession
        public NetworkStats getSummaryForNetwork(NetworkTemplate networkTemplate, long j, long j2) throws RemoteException {
            return null;
        }
    }

    void close() throws RemoteException;

    NetworkStats getDeviceSummaryForNetwork(NetworkTemplate networkTemplate, long j, long j2) throws RemoteException;

    NetworkStatsHistory getHistoryForNetwork(NetworkTemplate networkTemplate, int i) throws RemoteException;

    NetworkStatsHistory getHistoryForUid(NetworkTemplate networkTemplate, int i, int i2, int i3, int i4) throws RemoteException;

    NetworkStatsHistory getHistoryIntervalForUid(NetworkTemplate networkTemplate, int i, int i2, int i3, int i4, long j, long j2) throws RemoteException;

    int[] getRelevantUids() throws RemoteException;

    NetworkStats getSummaryForAllUid(NetworkTemplate networkTemplate, long j, long j2, boolean z) throws RemoteException;

    NetworkStats getSummaryForNetwork(NetworkTemplate networkTemplate, long j, long j2) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkStatsSession {
        private static final String DESCRIPTOR = "android.net.INetworkStatsSession";
        static final int TRANSACTION_close = 8;
        static final int TRANSACTION_getDeviceSummaryForNetwork = 1;
        static final int TRANSACTION_getHistoryForNetwork = 3;
        static final int TRANSACTION_getHistoryForUid = 5;
        static final int TRANSACTION_getHistoryIntervalForUid = 6;
        static final int TRANSACTION_getRelevantUids = 7;
        static final int TRANSACTION_getSummaryForAllUid = 4;
        static final int TRANSACTION_getSummaryForNetwork = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getDeviceSummaryForNetwork";
                case 2:
                    return "getSummaryForNetwork";
                case 3:
                    return "getHistoryForNetwork";
                case 4:
                    return "getSummaryForAllUid";
                case 5:
                    return "getHistoryForUid";
                case 6:
                    return "getHistoryIntervalForUid";
                case 7:
                    return "getRelevantUids";
                case 8:
                    return "close";
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

        public static INetworkStatsSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkStatsSession)) {
                return (INetworkStatsSession) iInterfaceQueryLocalInterface;
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
                    NetworkStats deviceSummaryForNetwork = getDeviceSummaryForNetwork(parcel.readInt() != 0 ? NetworkTemplate.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    if (deviceSummaryForNetwork != null) {
                        parcel2.writeInt(1);
                        deviceSummaryForNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStats summaryForNetwork = getSummaryForNetwork(parcel.readInt() != 0 ? NetworkTemplate.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    if (summaryForNetwork != null) {
                        parcel2.writeInt(1);
                        summaryForNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStatsHistory historyForNetwork = getHistoryForNetwork(parcel.readInt() != 0 ? NetworkTemplate.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (historyForNetwork != null) {
                        parcel2.writeInt(1);
                        historyForNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStats summaryForAllUid = getSummaryForAllUid(parcel.readInt() != 0 ? NetworkTemplate.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readLong(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (summaryForAllUid != null) {
                        parcel2.writeInt(1);
                        summaryForAllUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStatsHistory historyForUid = getHistoryForUid(parcel.readInt() != 0 ? NetworkTemplate.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (historyForUid != null) {
                        parcel2.writeInt(1);
                        historyForUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStatsHistory historyIntervalForUid = getHistoryIntervalForUid(parcel.readInt() != 0 ? NetworkTemplate.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    if (historyIntervalForUid != null) {
                        parcel2.writeInt(1);
                        historyIntervalForUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] relevantUids = getRelevantUids();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(relevantUids);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    close();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INetworkStatsSession {
            public static INetworkStatsSession sDefaultImpl;
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

            @Override // android.net.INetworkStatsSession
            public NetworkStats getDeviceSummaryForNetwork(NetworkTemplate networkTemplate, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkTemplate != null) {
                        parcelObtain.writeInt(1);
                        networkTemplate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceSummaryForNetwork(networkTemplate, j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsSession
            public NetworkStats getSummaryForNetwork(NetworkTemplate networkTemplate, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkTemplate != null) {
                        parcelObtain.writeInt(1);
                        networkTemplate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSummaryForNetwork(networkTemplate, j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsSession
            public NetworkStatsHistory getHistoryForNetwork(NetworkTemplate networkTemplate, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkTemplate != null) {
                        parcelObtain.writeInt(1);
                        networkTemplate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoryForNetwork(networkTemplate, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStatsHistory.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsSession
            public NetworkStats getSummaryForAllUid(NetworkTemplate networkTemplate, long j, long j2, boolean z) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (networkTemplate != null) {
                        parcelObtain.writeInt(1);
                        networkTemplate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        NetworkStats summaryForAllUid = Stub.getDefaultImpl().getSummaryForAllUid(networkTemplate, j, j2, z);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return summaryForAllUid;
                    }
                    parcelObtain2.readException();
                    NetworkStats networkStatsCreateFromParcel = parcelObtain2.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return networkStatsCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.net.INetworkStatsSession
            public NetworkStatsHistory getHistoryForUid(NetworkTemplate networkTemplate, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkTemplate != null) {
                        parcelObtain.writeInt(1);
                        networkTemplate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoryForUid(networkTemplate, i, i2, i3, i4);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStatsHistory.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsSession
            public NetworkStatsHistory getHistoryIntervalForUid(NetworkTemplate networkTemplate, int i, int i2, int i3, int i4, long j, long j2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkTemplate != null) {
                        parcelObtain.writeInt(1);
                        networkTemplate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        NetworkStatsHistory historyIntervalForUid = Stub.getDefaultImpl().getHistoryIntervalForUid(networkTemplate, i, i2, i3, i4, j, j2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return historyIntervalForUid;
                    }
                    parcelObtain2.readException();
                    NetworkStatsHistory networkStatsHistoryCreateFromParcel = parcelObtain2.readInt() != 0 ? NetworkStatsHistory.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return networkStatsHistoryCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.net.INetworkStatsSession
            public int[] getRelevantUids() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRelevantUids();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkStatsSession
            public void close() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().close();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkStatsSession iNetworkStatsSession) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetworkStatsSession == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetworkStatsSession;
            return true;
        }

        public static INetworkStatsSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
