package android.app.usage;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes.dex */
public final class UsageStats implements Parcelable {
    public static final Parcelable.Creator<UsageStats> CREATOR = new Parcelable.Creator<UsageStats>() { // from class: android.app.usage.UsageStats.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UsageStats createFromParcel(Parcel parcel) {
            UsageStats usageStats = new UsageStats();
            usageStats.mPackageName = parcel.readString();
            usageStats.mBeginTimeStamp = parcel.readLong();
            usageStats.mEndTimeStamp = parcel.readLong();
            usageStats.mLastTimeUsed = parcel.readLong();
            usageStats.mLastTimeVisible = parcel.readLong();
            usageStats.mLastTimeForegroundServiceUsed = parcel.readLong();
            usageStats.mTotalTimeInForeground = parcel.readLong();
            usageStats.mTotalTimeVisible = parcel.readLong();
            usageStats.mTotalTimeForegroundServiceUsed = parcel.readLong();
            usageStats.mLaunchCount = parcel.readInt();
            usageStats.mAppLaunchCount = parcel.readInt();
            usageStats.mLastEvent = parcel.readInt();
            Bundle bundle = parcel.readBundle();
            if (bundle != null) {
                usageStats.mChooserCounts = new ArrayMap<>();
                for (String str : bundle.keySet()) {
                    if (!usageStats.mChooserCounts.containsKey(str)) {
                        usageStats.mChooserCounts.put(str, new ArrayMap<>());
                    }
                    Bundle bundle2 = bundle.getBundle(str);
                    if (bundle2 != null) {
                        for (String str2 : bundle2.keySet()) {
                            int i = bundle2.getInt(str2);
                            if (i > 0) {
                                usageStats.mChooserCounts.get(str).put(str2, Integer.valueOf(i));
                            }
                        }
                    }
                }
            }
            readSparseIntArray(parcel, usageStats.mActivities);
            readBundleToEventMap(parcel.readBundle(), usageStats.mForegroundServices);
            return usageStats;
        }

        private void readSparseIntArray(Parcel parcel, SparseIntArray sparseIntArray) {
            int i = parcel.readInt();
            for (int i2 = 0; i2 < i; i2++) {
                sparseIntArray.put(parcel.readInt(), parcel.readInt());
            }
        }

        private void readBundleToEventMap(Bundle bundle, ArrayMap<String, Integer> arrayMap) {
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    arrayMap.put(str, Integer.valueOf(bundle.getInt(str)));
                }
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UsageStats[] newArray(int i) {
            return new UsageStats[i];
        }
    };
    public SparseIntArray mActivities;
    public int mAppLaunchCount;
    public long mBeginTimeStamp;
    public ArrayMap<String, ArrayMap<String, Integer>> mChooserCounts;
    public SparseArray<SparseIntArray> mChooserCountsObfuscated;
    public long mEndTimeStamp;
    public ArrayMap<String, Integer> mForegroundServices;

    @Deprecated
    public int mLastEvent;
    public long mLastTimeForegroundServiceUsed;
    public long mLastTimeUsed;
    public long mLastTimeVisible;
    public int mLaunchCount;
    public String mPackageName;
    public int mPackageToken;
    public long mTotalTimeForegroundServiceUsed;
    public long mTotalTimeInForeground;
    public long mTotalTimeVisible;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UsageStats() {
        this.mPackageToken = -1;
        this.mActivities = new SparseIntArray();
        this.mForegroundServices = new ArrayMap<>();
        this.mChooserCounts = new ArrayMap<>();
        this.mChooserCountsObfuscated = new SparseArray<>();
    }

    public UsageStats(UsageStats usageStats) {
        this.mPackageToken = -1;
        this.mActivities = new SparseIntArray();
        this.mForegroundServices = new ArrayMap<>();
        this.mChooserCounts = new ArrayMap<>();
        this.mChooserCountsObfuscated = new SparseArray<>();
        this.mPackageName = usageStats.mPackageName;
        this.mBeginTimeStamp = usageStats.mBeginTimeStamp;
        this.mEndTimeStamp = usageStats.mEndTimeStamp;
        this.mLastTimeUsed = usageStats.mLastTimeUsed;
        this.mLastTimeVisible = usageStats.mLastTimeVisible;
        this.mLastTimeForegroundServiceUsed = usageStats.mLastTimeForegroundServiceUsed;
        this.mTotalTimeInForeground = usageStats.mTotalTimeInForeground;
        this.mTotalTimeVisible = usageStats.mTotalTimeVisible;
        this.mTotalTimeForegroundServiceUsed = usageStats.mTotalTimeForegroundServiceUsed;
        this.mLaunchCount = usageStats.mLaunchCount;
        this.mAppLaunchCount = usageStats.mAppLaunchCount;
        this.mLastEvent = usageStats.mLastEvent;
        this.mActivities = usageStats.mActivities;
        this.mForegroundServices = usageStats.mForegroundServices;
        this.mChooserCounts = usageStats.mChooserCounts;
    }

    public UsageStats getObfuscatedForInstantApp() {
        UsageStats usageStats = new UsageStats(this);
        usageStats.mPackageName = UsageEvents.INSTANT_APP_PACKAGE_NAME;
        return usageStats;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public long getFirstTimeStamp() {
        return this.mBeginTimeStamp;
    }

    public long getLastTimeStamp() {
        return this.mEndTimeStamp;
    }

    public long getLastTimeUsed() {
        return this.mLastTimeUsed;
    }

    public long getLastTimeVisible() {
        return this.mLastTimeVisible;
    }

    public long getTotalTimeInForeground() {
        return this.mTotalTimeInForeground;
    }

    public long getTotalTimeVisible() {
        return this.mTotalTimeVisible;
    }

    public long getLastTimeForegroundServiceUsed() {
        return this.mLastTimeForegroundServiceUsed;
    }

    public long getTotalTimeForegroundServiceUsed() {
        return this.mTotalTimeForegroundServiceUsed;
    }

    @SystemApi
    public int getAppLaunchCount() {
        return this.mAppLaunchCount;
    }

    private void mergeEventMap(SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2) {
        int size = sparseIntArray2.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = sparseIntArray2.keyAt(i);
            int iValueAt = sparseIntArray2.valueAt(i);
            int iIndexOfKey = sparseIntArray.indexOfKey(iKeyAt);
            if (iIndexOfKey >= 0) {
                sparseIntArray.put(iKeyAt, Math.max(sparseIntArray.valueAt(iIndexOfKey), iValueAt));
            } else {
                sparseIntArray.put(iKeyAt, iValueAt);
            }
        }
    }

    private void mergeEventMap(ArrayMap<String, Integer> arrayMap, ArrayMap<String, Integer> arrayMap2) {
        int size = arrayMap2.size();
        for (int i = 0; i < size; i++) {
            String strKeyAt = arrayMap2.keyAt(i);
            Integer numValueAt = arrayMap2.valueAt(i);
            if (arrayMap.containsKey(strKeyAt)) {
                arrayMap.put(strKeyAt, Integer.valueOf(Math.max(arrayMap.get(strKeyAt).intValue(), numValueAt.intValue())));
            } else {
                arrayMap.put(strKeyAt, numValueAt);
            }
        }
    }

    public void add(UsageStats usageStats) {
        if (!this.mPackageName.equals(usageStats.mPackageName)) {
            throw new IllegalArgumentException("Can't merge UsageStats for package '" + this.mPackageName + "' with UsageStats for package '" + usageStats.mPackageName + "'.");
        }
        if (usageStats.mBeginTimeStamp > this.mBeginTimeStamp) {
            mergeEventMap(this.mActivities, usageStats.mActivities);
            mergeEventMap(this.mForegroundServices, usageStats.mForegroundServices);
            this.mLastTimeUsed = Math.max(this.mLastTimeUsed, usageStats.mLastTimeUsed);
            this.mLastTimeVisible = Math.max(this.mLastTimeVisible, usageStats.mLastTimeVisible);
            this.mLastTimeForegroundServiceUsed = Math.max(this.mLastTimeForegroundServiceUsed, usageStats.mLastTimeForegroundServiceUsed);
        }
        this.mBeginTimeStamp = Math.min(this.mBeginTimeStamp, usageStats.mBeginTimeStamp);
        this.mEndTimeStamp = Math.max(this.mEndTimeStamp, usageStats.mEndTimeStamp);
        this.mTotalTimeInForeground += usageStats.mTotalTimeInForeground;
        this.mTotalTimeVisible += usageStats.mTotalTimeVisible;
        this.mTotalTimeForegroundServiceUsed += usageStats.mTotalTimeForegroundServiceUsed;
        this.mLaunchCount += usageStats.mLaunchCount;
        this.mAppLaunchCount += usageStats.mAppLaunchCount;
        if (this.mChooserCounts == null) {
            this.mChooserCounts = usageStats.mChooserCounts;
            return;
        }
        ArrayMap<String, ArrayMap<String, Integer>> arrayMap = usageStats.mChooserCounts;
        if (arrayMap != null) {
            int size = arrayMap.size();
            for (int i = 0; i < size; i++) {
                String strKeyAt = usageStats.mChooserCounts.keyAt(i);
                ArrayMap<String, Integer> arrayMapValueAt = usageStats.mChooserCounts.valueAt(i);
                if (!this.mChooserCounts.containsKey(strKeyAt) || this.mChooserCounts.get(strKeyAt) == null) {
                    this.mChooserCounts.put(strKeyAt, arrayMapValueAt);
                } else {
                    int size2 = arrayMapValueAt.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        String strKeyAt2 = arrayMapValueAt.keyAt(i2);
                        this.mChooserCounts.get(strKeyAt).put(strKeyAt2, Integer.valueOf(this.mChooserCounts.get(strKeyAt).getOrDefault(strKeyAt2, 0).intValue() + arrayMapValueAt.valueAt(i2).intValue()));
                    }
                }
            }
        }
    }

    private boolean hasForegroundActivity() {
        int size = this.mActivities.size();
        for (int i = 0; i < size; i++) {
            if (this.mActivities.valueAt(i) == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean hasVisibleActivity() {
        int size = this.mActivities.size();
        for (int i = 0; i < size; i++) {
            int iValueAt = this.mActivities.valueAt(i);
            if (iValueAt == 1 || iValueAt == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean anyForegroundServiceStarted() {
        return !this.mForegroundServices.isEmpty();
    }

    private void incrementTimeUsed(long j) {
        long j2 = this.mLastTimeUsed;
        if (j > j2) {
            this.mTotalTimeInForeground += j - j2;
            this.mLastTimeUsed = j;
        }
    }

    private void incrementTimeVisible(long j) {
        long j2 = this.mLastTimeVisible;
        if (j > j2) {
            this.mTotalTimeVisible += j - j2;
            this.mLastTimeVisible = j;
        }
    }

    private void incrementServiceTimeUsed(long j) {
        long j2 = this.mLastTimeForegroundServiceUsed;
        if (j > j2) {
            this.mTotalTimeForegroundServiceUsed += j - j2;
            this.mLastTimeForegroundServiceUsed = j;
        }
    }

    private void updateActivity(String str, long j, int i, int i2) {
        if (i == 1 || i == 2 || i == 23 || i == 24) {
            int iIndexOfKey = this.mActivities.indexOfKey(i2);
            if (iIndexOfKey >= 0) {
                int iValueAt = this.mActivities.valueAt(iIndexOfKey);
                if (iValueAt == 1) {
                    incrementTimeUsed(j);
                    incrementTimeVisible(j);
                } else if (iValueAt == 2) {
                    incrementTimeVisible(j);
                }
            }
            if (i == 1) {
                if (!hasVisibleActivity()) {
                    this.mLastTimeUsed = j;
                    this.mLastTimeVisible = j;
                } else if (!hasForegroundActivity()) {
                    this.mLastTimeUsed = j;
                }
                this.mActivities.put(i2, i);
                return;
            }
            if (i == 2) {
                if (!hasVisibleActivity()) {
                    this.mLastTimeVisible = j;
                }
                this.mActivities.put(i2, i);
            } else if (i == 23 || i == 24) {
                this.mActivities.delete(i2);
            }
        }
    }

    private void updateForegroundService(String str, long j, int i) {
        int iIntValue;
        if (i == 20 || i == 19) {
            Integer num = this.mForegroundServices.get(str);
            if (num != null && ((iIntValue = num.intValue()) == 19 || iIntValue == 21)) {
                incrementServiceTimeUsed(j);
            }
            if (i != 19) {
                if (i != 20) {
                    return;
                }
                this.mForegroundServices.remove(str);
            } else {
                if (!anyForegroundServiceStarted()) {
                    this.mLastTimeForegroundServiceUsed = j;
                }
                this.mForegroundServices.put(str, Integer.valueOf(i));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void update(java.lang.String r3, long r4, int r6, int r7) {
        /*
            r2 = this;
            r0 = 1
            if (r6 == r0) goto L73
            r1 = 2
            if (r6 == r1) goto L73
            r1 = 3
            if (r6 == r1) goto L60
            r1 = 7
            if (r6 == r1) goto L47
            switch(r6) {
                case 19: goto L43;
                case 20: goto L43;
                case 21: goto L37;
                case 22: goto L2d;
                case 23: goto L73;
                case 24: goto L73;
                case 25: goto L11;
                case 26: goto L11;
                default: goto Lf;
            }
        Lf:
            goto L76
        L11:
            boolean r3 = r2.hasForegroundActivity()
            if (r3 == 0) goto L1a
            r2.incrementTimeUsed(r4)
        L1a:
            boolean r3 = r2.hasVisibleActivity()
            if (r3 == 0) goto L23
            r2.incrementTimeVisible(r4)
        L23:
            boolean r3 = r2.anyForegroundServiceStarted()
            if (r3 == 0) goto L76
            r2.incrementServiceTimeUsed(r4)
            goto L76
        L2d:
            boolean r3 = r2.anyForegroundServiceStarted()
            if (r3 == 0) goto L76
            r2.incrementServiceTimeUsed(r4)
            goto L76
        L37:
            r2.mLastTimeForegroundServiceUsed = r4
            android.util.ArrayMap<java.lang.String, java.lang.Integer> r7 = r2.mForegroundServices
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            r7.put(r3, r1)
            goto L76
        L43:
            r2.updateForegroundService(r3, r4, r6)
            goto L76
        L47:
            boolean r3 = r2.hasForegroundActivity()
            if (r3 == 0) goto L51
            r2.incrementTimeUsed(r4)
            goto L53
        L51:
            r2.mLastTimeUsed = r4
        L53:
            boolean r3 = r2.hasVisibleActivity()
            if (r3 == 0) goto L5d
            r2.incrementTimeVisible(r4)
            goto L76
        L5d:
            r2.mLastTimeVisible = r4
            goto L76
        L60:
            boolean r3 = r2.hasForegroundActivity()
            if (r3 == 0) goto L69
            r2.incrementTimeUsed(r4)
        L69:
            boolean r3 = r2.hasVisibleActivity()
            if (r3 == 0) goto L76
            r2.incrementTimeVisible(r4)
            goto L76
        L73:
            r2.updateActivity(r3, r4, r6, r7)
        L76:
            r2.mEndTimeStamp = r4
            if (r6 != r0) goto L7f
            int r3 = r2.mLaunchCount
            int r3 = r3 + r0
            r2.mLaunchCount = r3
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.usage.UsageStats.update(java.lang.String, long, int, int):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPackageName);
        parcel.writeLong(this.mBeginTimeStamp);
        parcel.writeLong(this.mEndTimeStamp);
        parcel.writeLong(this.mLastTimeUsed);
        parcel.writeLong(this.mLastTimeVisible);
        parcel.writeLong(this.mLastTimeForegroundServiceUsed);
        parcel.writeLong(this.mTotalTimeInForeground);
        parcel.writeLong(this.mTotalTimeVisible);
        parcel.writeLong(this.mTotalTimeForegroundServiceUsed);
        parcel.writeInt(this.mLaunchCount);
        parcel.writeInt(this.mAppLaunchCount);
        parcel.writeInt(this.mLastEvent);
        Bundle bundle = new Bundle();
        ArrayMap<String, ArrayMap<String, Integer>> arrayMap = this.mChooserCounts;
        if (arrayMap != null) {
            int size = arrayMap.size();
            for (int i2 = 0; i2 < size; i2++) {
                String strKeyAt = this.mChooserCounts.keyAt(i2);
                ArrayMap<String, Integer> arrayMapValueAt = this.mChooserCounts.valueAt(i2);
                Bundle bundle2 = new Bundle();
                int size2 = arrayMapValueAt.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    bundle2.putInt(arrayMapValueAt.keyAt(i3), arrayMapValueAt.valueAt(i3).intValue());
                }
                bundle.putBundle(strKeyAt, bundle2);
            }
        }
        parcel.writeBundle(bundle);
        writeSparseIntArray(parcel, this.mActivities);
        parcel.writeBundle(eventMapToBundle(this.mForegroundServices));
    }

    private void writeSparseIntArray(Parcel parcel, SparseIntArray sparseIntArray) {
        int size = sparseIntArray.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; i++) {
            parcel.writeInt(sparseIntArray.keyAt(i));
            parcel.writeInt(sparseIntArray.valueAt(i));
        }
    }

    private Bundle eventMapToBundle(ArrayMap<String, Integer> arrayMap) {
        Bundle bundle = new Bundle();
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            bundle.putInt(arrayMap.keyAt(i), arrayMap.valueAt(i).intValue());
        }
        return bundle;
    }
}
