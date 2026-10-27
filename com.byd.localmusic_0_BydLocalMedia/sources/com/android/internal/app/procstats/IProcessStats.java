package com.android.internal.app.procstats;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IProcessStats extends IInterface {

    public static class Default implements IProcessStats {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.app.procstats.IProcessStats
        public long getCommittedStats(long j, int i, boolean z, List<ParcelFileDescriptor> list) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.app.procstats.IProcessStats
        public long getCommittedStatsMerged(long j, int i, boolean z, List<ParcelFileDescriptor> list, ProcessStats processStats) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.app.procstats.IProcessStats
        public int getCurrentMemoryState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.procstats.IProcessStats
        public byte[] getCurrentStats(List<ParcelFileDescriptor> list) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.procstats.IProcessStats
        public long getMinAssociationDumpDuration() throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.app.procstats.IProcessStats
        public ParcelFileDescriptor getStatsOverTime(long j) throws RemoteException {
            return null;
        }
    }

    long getCommittedStats(long j, int i, boolean z, List<ParcelFileDescriptor> list) throws RemoteException;

    long getCommittedStatsMerged(long j, int i, boolean z, List<ParcelFileDescriptor> list, ProcessStats processStats) throws RemoteException;

    int getCurrentMemoryState() throws RemoteException;

    byte[] getCurrentStats(List<ParcelFileDescriptor> list) throws RemoteException;

    long getMinAssociationDumpDuration() throws RemoteException;

    ParcelFileDescriptor getStatsOverTime(long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IProcessStats {
        private static final String DESCRIPTOR = "com.android.internal.app.procstats.IProcessStats";
        static final int TRANSACTION_getCommittedStats = 4;
        static final int TRANSACTION_getCommittedStatsMerged = 5;
        static final int TRANSACTION_getCurrentMemoryState = 3;
        static final int TRANSACTION_getCurrentStats = 1;
        static final int TRANSACTION_getMinAssociationDumpDuration = 6;
        static final int TRANSACTION_getStatsOverTime = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getCurrentStats";
                case 2:
                    return "getStatsOverTime";
                case 3:
                    return "getCurrentMemoryState";
                case 4:
                    return "getCommittedStats";
                case 5:
                    return "getCommittedStatsMerged";
                case 6:
                    return "getMinAssociationDumpDuration";
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

        public static IProcessStats asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IProcessStats)) {
                return (IProcessStats) iInterfaceQueryLocalInterface;
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
                    byte[] currentStats = getCurrentStats(new ArrayList());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(currentStats);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor statsOverTime = getStatsOverTime(parcel.readLong());
                    parcel2.writeNoException();
                    if (statsOverTime != null) {
                        parcel2.writeInt(1);
                        statsOverTime.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentMemoryState = getCurrentMemoryState();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentMemoryState);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    long committedStats = getCommittedStats(parcel.readLong(), parcel.readInt(), parcel.readInt() != 0, new ArrayList());
                    parcel2.writeNoException();
                    parcel2.writeLong(committedStats);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    long j = parcel.readLong();
                    int i3 = parcel.readInt();
                    boolean z = parcel.readInt() != 0;
                    ArrayList arrayList = new ArrayList();
                    ProcessStats processStats = new ProcessStats();
                    long committedStatsMerged = getCommittedStatsMerged(j, i3, z, arrayList, processStats);
                    parcel2.writeNoException();
                    parcel2.writeLong(committedStatsMerged);
                    parcel2.writeInt(1);
                    processStats.writeToParcel(parcel2, 1);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    long minAssociationDumpDuration = getMinAssociationDumpDuration();
                    parcel2.writeNoException();
                    parcel2.writeLong(minAssociationDumpDuration);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IProcessStats {
            public static IProcessStats sDefaultImpl;
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

            @Override // com.android.internal.app.procstats.IProcessStats
            public byte[] getCurrentStats(List<ParcelFileDescriptor> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentStats(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.procstats.IProcessStats
            public ParcelFileDescriptor getStatsOverTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatsOverTime(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.procstats.IProcessStats
            public int getCurrentMemoryState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentMemoryState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.procstats.IProcessStats
            public long getCommittedStats(long j, int i, boolean z, List<ParcelFileDescriptor> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCommittedStats(j, i, z, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.procstats.IProcessStats
            public long getCommittedStatsMerged(long j, int i, boolean z, List<ParcelFileDescriptor> list, ProcessStats processStats) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        long committedStatsMerged = Stub.getDefaultImpl().getCommittedStatsMerged(j, i, z, list, processStats);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return committedStatsMerged;
                    }
                    parcelObtain2.readException();
                    long j2 = parcelObtain2.readLong();
                    if (parcelObtain2.readInt() != 0) {
                        processStats.readFromParcel(parcelObtain2);
                    }
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return j2;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.app.procstats.IProcessStats
            public long getMinAssociationDumpDuration() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMinAssociationDumpDuration();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProcessStats iProcessStats) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProcessStats == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProcessStats;
            return true;
        }

        public static IProcessStats getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
