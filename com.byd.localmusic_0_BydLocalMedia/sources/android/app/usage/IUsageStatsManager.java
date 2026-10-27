package android.app.usage;

import android.app.PendingIntent;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IUsageStatsManager extends IInterface {

    public static class Default implements IUsageStatsManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public void forceUsageSourceSettingRead() throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public int getAppStandbyBucket(String str, String str2, int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.usage.IUsageStatsManager
        public ParceledListSlice getAppStandbyBuckets(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public int getUsageSource() throws RemoteException {
            return 0;
        }

        @Override // android.app.usage.IUsageStatsManager
        public boolean isAppInactive(String str, int i, String str2) throws RemoteException {
            return false;
        }

        @Override // android.app.usage.IUsageStatsManager
        public void onCarrierPrivilegedAppsChanged() throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public ParceledListSlice queryConfigurationStats(int i, long j, long j2, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public ParceledListSlice queryEventStats(int i, long j, long j2, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public UsageEvents queryEvents(long j, long j2, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public UsageEvents queryEventsForPackage(long j, long j2, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public UsageEvents queryEventsForPackageForUser(long j, long j2, int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public UsageEvents queryEventsForUser(long j, long j2, int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public ParceledListSlice queryUsageStats(int i, long j, long j2, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.usage.IUsageStatsManager
        public void registerAppUsageLimitObserver(int i, String[] strArr, long j, long j2, PendingIntent pendingIntent, String str) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void registerAppUsageObserver(int i, String[] strArr, long j, PendingIntent pendingIntent, String str) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void registerUsageSessionObserver(int i, String[] strArr, long j, long j2, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void reportChooserSelection(String str, int i, String str2, String[] strArr, String str3) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void reportPastUsageStart(IBinder iBinder, String str, long j, String str2) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void reportUsageStart(IBinder iBinder, String str, String str2) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void reportUsageStop(IBinder iBinder, String str, String str2) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void setAppInactive(String str, boolean z, int i) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void setAppStandbyBucket(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void setAppStandbyBuckets(ParceledListSlice parceledListSlice, int i) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void unregisterAppUsageLimitObserver(int i, String str) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void unregisterAppUsageObserver(int i, String str) throws RemoteException {
        }

        @Override // android.app.usage.IUsageStatsManager
        public void unregisterUsageSessionObserver(int i, String str) throws RemoteException {
        }
    }

    void forceUsageSourceSettingRead() throws RemoteException;

    int getAppStandbyBucket(String str, String str2, int i) throws RemoteException;

    ParceledListSlice getAppStandbyBuckets(String str, int i) throws RemoteException;

    int getUsageSource() throws RemoteException;

    boolean isAppInactive(String str, int i, String str2) throws RemoteException;

    void onCarrierPrivilegedAppsChanged() throws RemoteException;

    ParceledListSlice queryConfigurationStats(int i, long j, long j2, String str) throws RemoteException;

    ParceledListSlice queryEventStats(int i, long j, long j2, String str) throws RemoteException;

    UsageEvents queryEvents(long j, long j2, String str) throws RemoteException;

    UsageEvents queryEventsForPackage(long j, long j2, String str) throws RemoteException;

    UsageEvents queryEventsForPackageForUser(long j, long j2, int i, String str, String str2) throws RemoteException;

    UsageEvents queryEventsForUser(long j, long j2, int i, String str) throws RemoteException;

    ParceledListSlice queryUsageStats(int i, long j, long j2, String str) throws RemoteException;

    void registerAppUsageLimitObserver(int i, String[] strArr, long j, long j2, PendingIntent pendingIntent, String str) throws RemoteException;

    void registerAppUsageObserver(int i, String[] strArr, long j, PendingIntent pendingIntent, String str) throws RemoteException;

    void registerUsageSessionObserver(int i, String[] strArr, long j, long j2, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str) throws RemoteException;

    void reportChooserSelection(String str, int i, String str2, String[] strArr, String str3) throws RemoteException;

    void reportPastUsageStart(IBinder iBinder, String str, long j, String str2) throws RemoteException;

    void reportUsageStart(IBinder iBinder, String str, String str2) throws RemoteException;

    void reportUsageStop(IBinder iBinder, String str, String str2) throws RemoteException;

    void setAppInactive(String str, boolean z, int i) throws RemoteException;

    void setAppStandbyBucket(String str, int i, int i2) throws RemoteException;

    void setAppStandbyBuckets(ParceledListSlice parceledListSlice, int i) throws RemoteException;

    void unregisterAppUsageLimitObserver(int i, String str) throws RemoteException;

    void unregisterAppUsageObserver(int i, String str) throws RemoteException;

    void unregisterUsageSessionObserver(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IUsageStatsManager {
        private static final String DESCRIPTOR = "android.app.usage.IUsageStatsManager";
        static final int TRANSACTION_forceUsageSourceSettingRead = 26;
        static final int TRANSACTION_getAppStandbyBucket = 12;
        static final int TRANSACTION_getAppStandbyBuckets = 14;
        static final int TRANSACTION_getUsageSource = 25;
        static final int TRANSACTION_isAppInactive = 9;
        static final int TRANSACTION_onCarrierPrivilegedAppsChanged = 10;
        static final int TRANSACTION_queryConfigurationStats = 2;
        static final int TRANSACTION_queryEventStats = 3;
        static final int TRANSACTION_queryEvents = 4;
        static final int TRANSACTION_queryEventsForPackage = 5;
        static final int TRANSACTION_queryEventsForPackageForUser = 7;
        static final int TRANSACTION_queryEventsForUser = 6;
        static final int TRANSACTION_queryUsageStats = 1;
        static final int TRANSACTION_registerAppUsageLimitObserver = 20;
        static final int TRANSACTION_registerAppUsageObserver = 16;
        static final int TRANSACTION_registerUsageSessionObserver = 18;
        static final int TRANSACTION_reportChooserSelection = 11;
        static final int TRANSACTION_reportPastUsageStart = 23;
        static final int TRANSACTION_reportUsageStart = 22;
        static final int TRANSACTION_reportUsageStop = 24;
        static final int TRANSACTION_setAppInactive = 8;
        static final int TRANSACTION_setAppStandbyBucket = 13;
        static final int TRANSACTION_setAppStandbyBuckets = 15;
        static final int TRANSACTION_unregisterAppUsageLimitObserver = 21;
        static final int TRANSACTION_unregisterAppUsageObserver = 17;
        static final int TRANSACTION_unregisterUsageSessionObserver = 19;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "queryUsageStats";
                case 2:
                    return "queryConfigurationStats";
                case 3:
                    return "queryEventStats";
                case 4:
                    return "queryEvents";
                case 5:
                    return "queryEventsForPackage";
                case 6:
                    return "queryEventsForUser";
                case 7:
                    return "queryEventsForPackageForUser";
                case 8:
                    return "setAppInactive";
                case 9:
                    return "isAppInactive";
                case 10:
                    return "onCarrierPrivilegedAppsChanged";
                case 11:
                    return "reportChooserSelection";
                case 12:
                    return "getAppStandbyBucket";
                case 13:
                    return "setAppStandbyBucket";
                case 14:
                    return "getAppStandbyBuckets";
                case 15:
                    return "setAppStandbyBuckets";
                case 16:
                    return "registerAppUsageObserver";
                case 17:
                    return "unregisterAppUsageObserver";
                case 18:
                    return "registerUsageSessionObserver";
                case 19:
                    return "unregisterUsageSessionObserver";
                case 20:
                    return "registerAppUsageLimitObserver";
                case 21:
                    return "unregisterAppUsageLimitObserver";
                case 22:
                    return "reportUsageStart";
                case 23:
                    return "reportPastUsageStart";
                case 24:
                    return "reportUsageStop";
                case 25:
                    return "getUsageSource";
                case 26:
                    return "forceUsageSourceSettingRead";
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

        public static IUsageStatsManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUsageStatsManager)) {
                return (IUsageStatsManager) iInterfaceQueryLocalInterface;
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
                    ParceledListSlice parceledListSliceQueryUsageStats = queryUsageStats(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryUsageStats != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryUsageStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryConfigurationStats = queryConfigurationStats(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryConfigurationStats != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryConfigurationStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryEventStats = queryEventStats(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryEventStats != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryEventStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsageEvents usageEventsQueryEvents = queryEvents(parcel.readLong(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    if (usageEventsQueryEvents != null) {
                        parcel2.writeInt(1);
                        usageEventsQueryEvents.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsageEvents usageEventsQueryEventsForPackage = queryEventsForPackage(parcel.readLong(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    if (usageEventsQueryEventsForPackage != null) {
                        parcel2.writeInt(1);
                        usageEventsQueryEventsForPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsageEvents usageEventsQueryEventsForUser = queryEventsForUser(parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (usageEventsQueryEventsForUser != null) {
                        parcel2.writeInt(1);
                        usageEventsQueryEventsForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsageEvents usageEventsQueryEventsForPackageForUser = queryEventsForPackageForUser(parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (usageEventsQueryEventsForPackageForUser != null) {
                        parcel2.writeInt(1);
                        usageEventsQueryEventsForPackageForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppInactive(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAppInactive = isAppInactive(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAppInactive ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCarrierPrivilegedAppsChanged();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportChooserSelection(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.createStringArray(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int appStandbyBucket = getAppStandbyBucket(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(appStandbyBucket);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppStandbyBucket(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice appStandbyBuckets = getAppStandbyBuckets(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (appStandbyBuckets != null) {
                        parcel2.writeInt(1);
                        appStandbyBuckets.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppStandbyBuckets(parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerAppUsageObserver(parcel.readInt(), parcel.createStringArray(), parcel.readLong(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterAppUsageObserver(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerUsageSessionObserver(parcel.readInt(), parcel.createStringArray(), parcel.readLong(), parcel.readLong(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterUsageSessionObserver(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerAppUsageLimitObserver(parcel.readInt(), parcel.createStringArray(), parcel.readLong(), parcel.readLong(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterAppUsageLimitObserver(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportUsageStart(parcel.readStrongBinder(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportPastUsageStart(parcel.readStrongBinder(), parcel.readString(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportUsageStop(parcel.readStrongBinder(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int usageSource = getUsageSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(usageSource);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    forceUsageSourceSettingRead();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IUsageStatsManager {
            public static IUsageStatsManager sDefaultImpl;
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

            @Override // android.app.usage.IUsageStatsManager
            public ParceledListSlice queryUsageStats(int i, long j, long j2, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeString(str);
                    try {
                        if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            ParceledListSlice parceledListSliceQueryUsageStats = Stub.getDefaultImpl().queryUsageStats(i, j, j2, str);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return parceledListSliceQueryUsageStats;
                        }
                        parcelObtain2.readException();
                        ParceledListSlice parceledListSliceCreateFromParcel = parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return parceledListSliceCreateFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public ParceledListSlice queryConfigurationStats(int i, long j, long j2, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeString(str);
                    try {
                        if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            ParceledListSlice parceledListSliceQueryConfigurationStats = Stub.getDefaultImpl().queryConfigurationStats(i, j, j2, str);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return parceledListSliceQueryConfigurationStats;
                        }
                        parcelObtain2.readException();
                        ParceledListSlice parceledListSliceCreateFromParcel = parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return parceledListSliceCreateFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public ParceledListSlice queryEventStats(int i, long j, long j2, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeString(str);
                    try {
                        if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            ParceledListSlice parceledListSliceQueryEventStats = Stub.getDefaultImpl().queryEventStats(i, j, j2, str);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return parceledListSliceQueryEventStats;
                        }
                        parcelObtain2.readException();
                        ParceledListSlice parceledListSliceCreateFromParcel = parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return parceledListSliceCreateFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public UsageEvents queryEvents(long j, long j2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryEvents(j, j2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UsageEvents.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public UsageEvents queryEventsForPackage(long j, long j2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryEventsForPackage(j, j2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UsageEvents.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public UsageEvents queryEventsForUser(long j, long j2, int i, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    try {
                        if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            UsageEvents usageEventsQueryEventsForUser = Stub.getDefaultImpl().queryEventsForUser(j, j2, i, str);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return usageEventsQueryEventsForUser;
                        }
                        parcelObtain2.readException();
                        UsageEvents usageEventsCreateFromParcel = parcelObtain2.readInt() != 0 ? UsageEvents.CREATOR.createFromParcel(parcelObtain2) : null;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return usageEventsCreateFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public UsageEvents queryEventsForPackageForUser(long j, long j2, int i, String str, String str2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        UsageEvents usageEventsQueryEventsForPackageForUser = Stub.getDefaultImpl().queryEventsForPackageForUser(j, j2, i, str, str2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return usageEventsQueryEventsForPackageForUser;
                    }
                    parcelObtain2.readException();
                    UsageEvents usageEventsCreateFromParcel = parcelObtain2.readInt() != 0 ? UsageEvents.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return usageEventsCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void setAppInactive(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppInactive(str, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public boolean isAppInactive(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAppInactive(str, i, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void onCarrierPrivilegedAppsChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarrierPrivilegedAppsChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void reportChooserSelection(String str, int i, String str2, String[] strArr, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportChooserSelection(str, i, str2, strArr, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public int getAppStandbyBucket(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppStandbyBucket(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void setAppStandbyBucket(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppStandbyBucket(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public ParceledListSlice getAppStandbyBuckets(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppStandbyBuckets(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void setAppStandbyBuckets(ParceledListSlice parceledListSlice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppStandbyBuckets(parceledListSlice, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void registerAppUsageObserver(int i, String[] strArr, long j, PendingIntent pendingIntent, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeLong(j);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    try {
                        if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().registerAppUsageObserver(i, strArr, j, pendingIntent, str);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void unregisterAppUsageObserver(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterAppUsageObserver(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void registerUsageSessionObserver(int i, String[] strArr, long j, long j2, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (pendingIntent2 != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerUsageSessionObserver(i, strArr, j, j2, pendingIntent, pendingIntent2, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void unregisterUsageSessionObserver(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterUsageSessionObserver(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void registerAppUsageLimitObserver(int i, String[] strArr, long j, long j2, PendingIntent pendingIntent, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerAppUsageLimitObserver(i, strArr, j, j2, pendingIntent, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void unregisterAppUsageLimitObserver(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterAppUsageLimitObserver(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void reportUsageStart(IBinder iBinder, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportUsageStart(iBinder, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void reportPastUsageStart(IBinder iBinder, String str, long j, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportPastUsageStart(iBinder, str, j, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void reportUsageStop(IBinder iBinder, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportUsageStop(iBinder, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public int getUsageSource() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUsageSource();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.usage.IUsageStatsManager
            public void forceUsageSourceSettingRead() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().forceUsageSourceSettingRead();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUsageStatsManager iUsageStatsManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUsageStatsManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUsageStatsManager;
            return true;
        }

        public static IUsageStatsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
