package com.android.internal.app.procstats;

import android.app.job.JobInfo;
import android.bluetooth.BluetoothHidDevice;
import android.media.AudioSystem;
import android.os.Parcel;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.SettingsStringUtil;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.DebugUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseLongArray;
import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;
import android.util.proto.ProtoUtils;
import com.android.internal.accessibility.common.ShortcutConstants;
import com.android.internal.app.ProcessMap;
import com.android.internal.app.procstats.ProcessStats;
import com.android.internal.content.NativeLibraryHelper;
import java.io.PrintWriter;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public final class ProcessState {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_PARCEL = false;
    private static final String TAG = "ProcessStats";
    private boolean mActive;
    private long mAvgCachedKillPss;
    private ProcessState mCommonProcess;
    private int mCurCombinedState;
    private boolean mDead;
    private final DurationsTable mDurations;
    private int mLastPssState;
    private long mLastPssTime;
    private long mMaxCachedKillPss;
    private long mMinCachedKillPss;
    private boolean mMultiPackage;
    private final String mName;
    private int mNumActiveServices;
    private int mNumCachedKill;
    private int mNumExcessiveCpu;
    private int mNumStartedServices;
    private final String mPackage;
    private final PssTable mPssTable;
    private long mStartTime;
    private final ProcessStats mStats;
    private long mTmpTotalTime;
    private long mTotalRunningDuration;
    private final long[] mTotalRunningPss;
    private long mTotalRunningStartTime;
    private final int mUid;
    private final long mVersion;
    public ProcessState tmpFoundSubProc;
    public int tmpNumInUse;
    static final int[] PROCESS_STATE_TO_STATE = {0, 0, 1, 2, 2, 2, 2, 3, 3, 4, 5, 7, 1, 8, 9, 10, 11, 12, 11, 13};
    public static final Comparator<ProcessState> COMPARATOR = new Comparator<ProcessState>() { // from class: com.android.internal.app.procstats.ProcessState.1
        @Override // java.util.Comparator
        public int compare(ProcessState processState, ProcessState processState2) {
            if (processState.mTmpTotalTime < processState2.mTmpTotalTime) {
                return -1;
            }
            return processState.mTmpTotalTime > processState2.mTmpTotalTime ? 1 : 0;
        }
    };

    static class PssAggr {
        long pss = 0;
        long samples = 0;

        PssAggr() {
        }

        void add(long j, long j2) {
            double d = this.pss;
            long j3 = this.samples;
            this.pss = ((long) ((d * j3) + (j * j2))) / (j3 + j2);
            this.samples = j3 + j2;
        }
    }

    public ProcessState(ProcessStats processStats, String str, int i, long j, String str2) {
        this.mTotalRunningPss = new long[10];
        this.mCurCombinedState = -1;
        this.mLastPssState = -1;
        this.mStats = processStats;
        this.mName = str2;
        this.mCommonProcess = this;
        this.mPackage = str;
        this.mUid = i;
        this.mVersion = j;
        this.mDurations = new DurationsTable(processStats.mTableData);
        this.mPssTable = new PssTable(processStats.mTableData);
    }

    public ProcessState(ProcessState processState, String str, int i, long j, String str2, long j2) {
        this.mTotalRunningPss = new long[10];
        this.mCurCombinedState = -1;
        this.mLastPssState = -1;
        this.mStats = processState.mStats;
        this.mName = str2;
        this.mCommonProcess = processState;
        this.mPackage = str;
        this.mUid = i;
        this.mVersion = j;
        int i2 = processState.mCurCombinedState;
        this.mCurCombinedState = i2;
        this.mStartTime = j2;
        if (i2 != -1) {
            this.mTotalRunningStartTime = j2;
        }
        this.mDurations = new DurationsTable(processState.mStats.mTableData);
        this.mPssTable = new PssTable(processState.mStats.mTableData);
    }

    public ProcessState clone(long j) {
        ProcessState processState = new ProcessState(this, this.mPackage, this.mUid, this.mVersion, this.mName, j);
        processState.mDurations.addDurations(this.mDurations);
        processState.mPssTable.copyFrom(this.mPssTable, 10);
        System.arraycopy(this.mTotalRunningPss, 0, processState.mTotalRunningPss, 0, 10);
        processState.mTotalRunningDuration = getTotalRunningDuration(j);
        processState.mNumExcessiveCpu = this.mNumExcessiveCpu;
        processState.mNumCachedKill = this.mNumCachedKill;
        processState.mMinCachedKillPss = this.mMinCachedKillPss;
        processState.mAvgCachedKillPss = this.mAvgCachedKillPss;
        processState.mMaxCachedKillPss = this.mMaxCachedKillPss;
        processState.mActive = this.mActive;
        processState.mNumActiveServices = this.mNumActiveServices;
        processState.mNumStartedServices = this.mNumStartedServices;
        return processState;
    }

    public String getName() {
        return this.mName;
    }

    public ProcessState getCommonProcess() {
        return this.mCommonProcess;
    }

    public void makeStandalone() {
        this.mCommonProcess = this;
    }

    public String getPackage() {
        return this.mPackage;
    }

    public int getUid() {
        return this.mUid;
    }

    public long getVersion() {
        return this.mVersion;
    }

    public boolean isMultiPackage() {
        return this.mMultiPackage;
    }

    public void setMultiPackage(boolean z) {
        this.mMultiPackage = z;
    }

    public int getDurationsBucketCount() {
        return this.mDurations.getKeyCount();
    }

    public void add(ProcessState processState) {
        this.mDurations.addDurations(processState.mDurations);
        this.mPssTable.mergeStats(processState.mPssTable);
        this.mNumExcessiveCpu += processState.mNumExcessiveCpu;
        int i = processState.mNumCachedKill;
        if (i > 0) {
            addCachedKill(i, processState.mMinCachedKillPss, processState.mAvgCachedKillPss, processState.mMaxCachedKillPss);
        }
    }

    public void resetSafely(long j) {
        this.mDurations.resetTable();
        this.mPssTable.resetTable();
        this.mStartTime = j;
        this.mLastPssState = -1;
        this.mLastPssTime = 0L;
        this.mNumExcessiveCpu = 0;
        this.mNumCachedKill = 0;
        this.mMaxCachedKillPss = 0L;
        this.mAvgCachedKillPss = 0L;
        this.mMinCachedKillPss = 0L;
    }

    public void makeDead() {
        this.mDead = true;
    }

    private void ensureNotDead() {
        if (this.mDead) {
            Slog.w("ProcessStats", "ProcessState dead: name=" + this.mName + " pkg=" + this.mPackage + " uid=" + this.mUid + " common.name=" + this.mCommonProcess.mName);
        }
    }

    public void writeToParcel(Parcel parcel, long j) {
        parcel.writeInt(this.mMultiPackage ? 1 : 0);
        this.mDurations.writeToParcel(parcel);
        this.mPssTable.writeToParcel(parcel);
        for (int i = 0; i < 10; i++) {
            parcel.writeLong(this.mTotalRunningPss[i]);
        }
        parcel.writeLong(getTotalRunningDuration(j));
        parcel.writeInt(0);
        parcel.writeInt(this.mNumExcessiveCpu);
        parcel.writeInt(this.mNumCachedKill);
        if (this.mNumCachedKill > 0) {
            parcel.writeLong(this.mMinCachedKillPss);
            parcel.writeLong(this.mAvgCachedKillPss);
            parcel.writeLong(this.mMaxCachedKillPss);
        }
    }

    public boolean readFromParcel(Parcel parcel, boolean z) {
        boolean z2 = parcel.readInt() != 0;
        if (z) {
            this.mMultiPackage = z2;
        }
        if (!this.mDurations.readFromParcel(parcel) || !this.mPssTable.readFromParcel(parcel)) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            this.mTotalRunningPss[i] = parcel.readLong();
        }
        this.mTotalRunningDuration = parcel.readLong();
        parcel.readInt();
        this.mNumExcessiveCpu = parcel.readInt();
        int i2 = parcel.readInt();
        this.mNumCachedKill = i2;
        if (i2 > 0) {
            this.mMinCachedKillPss = parcel.readLong();
            this.mAvgCachedKillPss = parcel.readLong();
            this.mMaxCachedKillPss = parcel.readLong();
        } else {
            this.mMaxCachedKillPss = 0L;
            this.mAvgCachedKillPss = 0L;
            this.mMinCachedKillPss = 0L;
        }
        return true;
    }

    public void makeActive() {
        ensureNotDead();
        this.mActive = true;
    }

    public void makeInactive() {
        this.mActive = false;
    }

    public boolean isInUse() {
        return this.mActive || this.mNumActiveServices > 0 || this.mNumStartedServices > 0 || this.mCurCombinedState != -1;
    }

    public boolean isActive() {
        return this.mActive;
    }

    public boolean hasAnyData() {
        return (this.mDurations.getKeyCount() == 0 && this.mCurCombinedState == -1 && this.mPssTable.getKeyCount() == 0 && this.mTotalRunningPss[0] == 0) ? false : true;
    }

    public void setState(int i, int i2, long j, ArrayMap<String, ProcessStats.ProcessStateHolder> arrayMap) {
        int i3;
        if (i < 0) {
            i3 = this.mNumStartedServices > 0 ? (i2 * 14) + 6 : -1;
        } else {
            i3 = (i2 * 14) + PROCESS_STATE_TO_STATE[i];
        }
        this.mCommonProcess.setCombinedState(i3, j);
        if (this.mCommonProcess.mMultiPackage && arrayMap != null) {
            for (int size = arrayMap.size() - 1; size >= 0; size--) {
                pullFixedProc(arrayMap, size).setCombinedState(i3, j);
            }
        }
    }

    public void setCombinedState(int i, long j) {
        ensureNotDead();
        if (this.mDead || this.mCurCombinedState == i) {
            return;
        }
        commitStateTime(j);
        if (i == -1) {
            this.mTotalRunningDuration += j - this.mTotalRunningStartTime;
            this.mTotalRunningStartTime = 0L;
        } else if (this.mCurCombinedState == -1) {
            this.mTotalRunningDuration = 0L;
            this.mTotalRunningStartTime = j;
            for (int i2 = 9; i2 >= 0; i2--) {
                this.mTotalRunningPss[i2] = 0;
            }
        }
        this.mCurCombinedState = i;
    }

    public int getCombinedState() {
        return this.mCurCombinedState;
    }

    public void commitStateTime(long j) {
        int i = this.mCurCombinedState;
        if (i != -1) {
            long j2 = j - this.mStartTime;
            if (j2 > 0) {
                this.mDurations.addDuration(i, j2);
            }
            this.mTotalRunningDuration += j - this.mTotalRunningStartTime;
            this.mTotalRunningStartTime = j;
        }
        this.mStartTime = j;
    }

    public void incActiveServices(String str) {
        ProcessState processState = this.mCommonProcess;
        if (processState != this) {
            processState.incActiveServices(str);
        }
        this.mNumActiveServices++;
    }

    public void decActiveServices(String str) {
        ProcessState processState = this.mCommonProcess;
        if (processState != this) {
            processState.decActiveServices(str);
        }
        int i = this.mNumActiveServices - 1;
        this.mNumActiveServices = i;
        if (i < 0) {
            Slog.wtfStack("ProcessStats", "Proc active services underrun: pkg=" + this.mPackage + " uid=" + this.mUid + " proc=" + this.mName + " service=" + str);
            this.mNumActiveServices = 0;
        }
    }

    public void incStartedServices(int i, long j, String str) {
        ProcessState processState = this.mCommonProcess;
        if (processState != this) {
            processState.incStartedServices(i, j, str);
        }
        int i2 = this.mNumStartedServices + 1;
        this.mNumStartedServices = i2;
        if (i2 == 1 && this.mCurCombinedState == -1) {
            setCombinedState((i * 14) + 6, j);
        }
    }

    public void decStartedServices(int i, long j, String str) {
        ProcessState processState = this.mCommonProcess;
        if (processState != this) {
            processState.decStartedServices(i, j, str);
        }
        int i2 = this.mNumStartedServices - 1;
        this.mNumStartedServices = i2;
        if (i2 == 0 && this.mCurCombinedState % 14 == 6) {
            setCombinedState(-1, j);
            return;
        }
        if (this.mNumStartedServices < 0) {
            Slog.wtfStack("ProcessStats", "Proc started services underrun: pkg=" + this.mPackage + " uid=" + this.mUid + " name=" + this.mName);
            this.mNumStartedServices = 0;
        }
    }

    public void addPss(long j, long j2, long j3, boolean z, int i, long j4, ArrayMap<String, ProcessStats.ProcessStateHolder> arrayMap) {
        ensureNotDead();
        if (i == 0) {
            this.mStats.mInternalSinglePssCount++;
            this.mStats.mInternalSinglePssTime += j4;
        } else if (i == 1) {
            this.mStats.mInternalAllMemPssCount++;
            this.mStats.mInternalAllMemPssTime += j4;
        } else if (i == 2) {
            this.mStats.mInternalAllPollPssCount++;
            this.mStats.mInternalAllPollPssTime += j4;
        } else if (i == 3) {
            this.mStats.mExternalPssCount++;
            this.mStats.mExternalPssTime += j4;
        } else if (i == 4) {
            this.mStats.mExternalSlowPssCount++;
            this.mStats.mExternalSlowPssTime += j4;
        }
        if (z || this.mLastPssState != this.mCurCombinedState || SystemClock.uptimeMillis() >= this.mLastPssTime + JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS) {
            this.mLastPssState = this.mCurCombinedState;
            this.mLastPssTime = SystemClock.uptimeMillis();
            int i2 = this.mCurCombinedState;
            if (i2 != -1) {
                this.mCommonProcess.mPssTable.mergeStats(i2, 1, j, j, j, j2, j2, j2, j3, j3, j3);
                PssTable.mergeStats(this.mCommonProcess.mTotalRunningPss, 0, 1, j, j, j, j2, j2, j2, j3, j3, j3);
                if (this.mCommonProcess.mMultiPackage && arrayMap != null) {
                    for (int size = arrayMap.size() - 1; size >= 0; size--) {
                        ProcessState processStatePullFixedProc = pullFixedProc(arrayMap, size);
                        processStatePullFixedProc.mPssTable.mergeStats(this.mCurCombinedState, 1, j, j, j, j2, j2, j2, j3, j3, j3);
                        PssTable.mergeStats(processStatePullFixedProc.mTotalRunningPss, 0, 1, j, j, j, j2, j2, j2, j3, j3, j3);
                    }
                }
            }
        }
    }

    public void reportExcessiveCpu(ArrayMap<String, ProcessStats.ProcessStateHolder> arrayMap) {
        ensureNotDead();
        ProcessState processState = this.mCommonProcess;
        processState.mNumExcessiveCpu++;
        if (processState.mMultiPackage) {
            for (int size = arrayMap.size() - 1; size >= 0; size--) {
                pullFixedProc(arrayMap, size).mNumExcessiveCpu++;
            }
        }
    }

    private void addCachedKill(int i, long j, long j2, long j3) {
        if (this.mNumCachedKill <= 0) {
            this.mNumCachedKill = i;
            this.mMinCachedKillPss = j;
            this.mAvgCachedKillPss = j2;
            this.mMaxCachedKillPss = j3;
            return;
        }
        if (j < this.mMinCachedKillPss) {
            this.mMinCachedKillPss = j;
        }
        if (j3 > this.mMaxCachedKillPss) {
            this.mMaxCachedKillPss = j3;
        }
        double d = this.mAvgCachedKillPss;
        int i2 = this.mNumCachedKill;
        this.mAvgCachedKillPss = (long) (((d * ((double) i2)) + j2) / ((double) (i2 + i)));
        this.mNumCachedKill = i2 + i;
    }

    public void reportCachedKill(ArrayMap<String, ProcessStats.ProcessStateHolder> arrayMap, long j) {
        ensureNotDead();
        this.mCommonProcess.addCachedKill(1, j, j, j);
        if (this.mCommonProcess.mMultiPackage) {
            for (int size = arrayMap.size() - 1; size >= 0; size--) {
                pullFixedProc(arrayMap, size).addCachedKill(1, j, j, j);
            }
        }
    }

    public ProcessState pullFixedProc(String str) {
        if (!this.mMultiPackage) {
            return this;
        }
        LongSparseArray<ProcessStats.PackageState> longSparseArray = this.mStats.mPackages.get(str, this.mUid);
        if (longSparseArray == null) {
            throw new IllegalStateException("Didn't find package " + str + " / " + this.mUid);
        }
        ProcessStats.PackageState packageState = longSparseArray.get(this.mVersion);
        if (packageState == null) {
            throw new IllegalStateException("Didn't find package " + str + " / " + this.mUid + " vers " + this.mVersion);
        }
        ProcessState processState = packageState.mProcesses.get(this.mName);
        if (processState != null) {
            return processState;
        }
        throw new IllegalStateException("Didn't create per-package process " + this.mName + " in pkg " + str + " / " + this.mUid + " vers " + this.mVersion);
    }

    private ProcessState pullFixedProc(ArrayMap<String, ProcessStats.ProcessStateHolder> arrayMap, int i) {
        ProcessStats.ProcessStateHolder processStateHolderValueAt = arrayMap.valueAt(i);
        ProcessState processStateLocked = processStateHolderValueAt.state;
        if (this.mDead && processStateLocked.mCommonProcess != processStateLocked) {
            Log.wtf("ProcessStats", "Pulling dead proc: name=" + this.mName + " pkg=" + this.mPackage + " uid=" + this.mUid + " common.name=" + this.mCommonProcess.mName);
            processStateLocked = this.mStats.getProcessStateLocked(processStateLocked.mPackage, processStateLocked.mUid, processStateLocked.mVersion, processStateLocked.mName);
        }
        if (processStateLocked.mMultiPackage) {
            LongSparseArray<ProcessStats.PackageState> longSparseArray = this.mStats.mPackages.get(arrayMap.keyAt(i), processStateLocked.mUid);
            if (longSparseArray == null) {
                throw new IllegalStateException("No existing package " + arrayMap.keyAt(i) + "/" + processStateLocked.mUid + " for multi-proc " + processStateLocked.mName);
            }
            ProcessStats.PackageState packageState = longSparseArray.get(processStateLocked.mVersion);
            if (packageState == null) {
                throw new IllegalStateException("No existing package " + arrayMap.keyAt(i) + "/" + processStateLocked.mUid + " for multi-proc " + processStateLocked.mName + " version " + processStateLocked.mVersion);
            }
            String str = processStateLocked.mName;
            processStateLocked = packageState.mProcesses.get(processStateLocked.mName);
            if (processStateLocked == null) {
                throw new IllegalStateException("Didn't create per-package process " + str + " in pkg " + packageState.mPackageName + "/" + packageState.mUid);
            }
            processStateHolderValueAt.state = processStateLocked;
        }
        return processStateLocked;
    }

    public long getTotalRunningDuration(long j) {
        long j2 = this.mTotalRunningDuration;
        long j3 = this.mTotalRunningStartTime;
        return j2 + (j3 != 0 ? j - j3 : 0L);
    }

    public long getDuration(int i, long j) {
        long valueForId = this.mDurations.getValueForId((byte) i);
        return this.mCurCombinedState == i ? valueForId + (j - this.mStartTime) : valueForId;
    }

    public long getPssSampleCount(int i) {
        return this.mPssTable.getValueForId((byte) i, 0);
    }

    public long getPssMinimum(int i) {
        return this.mPssTable.getValueForId((byte) i, 1);
    }

    public long getPssAverage(int i) {
        return this.mPssTable.getValueForId((byte) i, 2);
    }

    public long getPssMaximum(int i) {
        return this.mPssTable.getValueForId((byte) i, 3);
    }

    public long getPssUssMinimum(int i) {
        return this.mPssTable.getValueForId((byte) i, 4);
    }

    public long getPssUssAverage(int i) {
        return this.mPssTable.getValueForId((byte) i, 5);
    }

    public long getPssUssMaximum(int i) {
        return this.mPssTable.getValueForId((byte) i, 6);
    }

    public long getPssRssMinimum(int i) {
        return this.mPssTable.getValueForId((byte) i, 7);
    }

    public long getPssRssAverage(int i) {
        return this.mPssTable.getValueForId((byte) i, 8);
    }

    public long getPssRssMaximum(int i) {
        return this.mPssTable.getValueForId((byte) i, 9);
    }

    public void aggregatePss(ProcessStats.TotalMemoryUseCollection totalMemoryUseCollection, long j) {
        boolean z;
        boolean z2;
        boolean z3;
        long pssAverage;
        PssAggr pssAggr = new PssAggr();
        PssAggr pssAggr2 = new PssAggr();
        PssAggr pssAggr3 = new PssAggr();
        int i = 0;
        boolean z4 = false;
        while (i < this.mDurations.getKeyCount()) {
            byte idFromKey = SparseMappingTable.getIdFromKey(this.mDurations.getKeyAt(i));
            int i2 = idFromKey % BluetoothHidDevice.ERROR_RSP_UNKNOWN;
            int i3 = i;
            long pssSampleCount = getPssSampleCount(idFromKey);
            if (pssSampleCount > 0) {
                long pssAverage2 = getPssAverage(idFromKey);
                if (i2 <= 2) {
                    pssAggr.add(pssAverage2, pssSampleCount);
                } else if (i2 <= 7) {
                    pssAggr2.add(pssAverage2, pssSampleCount);
                } else {
                    pssAggr3.add(pssAverage2, pssSampleCount);
                }
                z4 = true;
            }
            i = i3 + 1;
        }
        if (z4) {
            if (pssAggr.samples >= 3 || pssAggr2.samples <= 0) {
                z = false;
            } else {
                pssAggr.add(pssAggr2.pss, pssAggr2.samples);
                z = true;
            }
            if (pssAggr.samples >= 3 || pssAggr3.samples <= 0) {
                z2 = false;
            } else {
                pssAggr.add(pssAggr3.pss, pssAggr3.samples);
                z2 = true;
            }
            if (pssAggr2.samples >= 3 || pssAggr3.samples <= 0) {
                z3 = false;
            } else {
                pssAggr2.add(pssAggr3.pss, pssAggr3.samples);
                z3 = true;
            }
            if (pssAggr2.samples < 3 && !z && pssAggr.samples > 0) {
                pssAggr2.add(pssAggr.pss, pssAggr.samples);
            }
            if (pssAggr3.samples < 3 && !z3 && pssAggr2.samples > 0) {
                pssAggr3.add(pssAggr2.pss, pssAggr2.samples);
            }
            if (pssAggr3.samples < 3 && !z2 && pssAggr.samples > 0) {
                pssAggr3.add(pssAggr.pss, pssAggr.samples);
            }
            int i4 = 0;
            while (i4 < this.mDurations.getKeyCount()) {
                int keyAt = this.mDurations.getKeyAt(i4);
                byte idFromKey2 = SparseMappingTable.getIdFromKey(keyAt);
                long value = this.mDurations.getValue(keyAt);
                if (this.mCurCombinedState == idFromKey2) {
                    value += j - this.mStartTime;
                }
                int i5 = idFromKey2 % BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                long[] jArr = totalMemoryUseCollection.processStateTime;
                jArr[i5] = jArr[i5] + value;
                long pssSampleCount2 = getPssSampleCount(idFromKey2);
                if (pssSampleCount2 > 0) {
                    pssAverage = getPssAverage(idFromKey2);
                } else if (i5 <= 2) {
                    pssSampleCount2 = pssAggr.samples;
                    pssAverage = pssAggr.pss;
                } else if (i5 <= 7) {
                    pssSampleCount2 = pssAggr2.samples;
                    pssAverage = pssAggr2.pss;
                } else {
                    pssSampleCount2 = pssAggr3.samples;
                    pssAverage = pssAggr3.pss;
                }
                PssAggr pssAggr4 = pssAggr;
                double d = pssAverage;
                totalMemoryUseCollection.processStatePss[i5] = (long) (((totalMemoryUseCollection.processStatePss[i5] * ((double) totalMemoryUseCollection.processStateSamples[i5])) + (pssSampleCount2 * d)) / (((long) totalMemoryUseCollection.processStateSamples[i5]) + pssSampleCount2));
                int[] iArr = totalMemoryUseCollection.processStateSamples;
                iArr[i5] = (int) (((long) iArr[i5]) + pssSampleCount2);
                double[] dArr = totalMemoryUseCollection.processStateWeight;
                dArr[i5] = dArr[i5] + (d * value);
                i4++;
                pssAggr = pssAggr4;
                pssAggr2 = pssAggr2;
            }
        }
    }

    public long computeProcessTimeLocked(int[] iArr, int[] iArr2, int[] iArr3, long j) {
        long duration = 0;
        for (int i : iArr) {
            for (int i2 : iArr2) {
                for (int i3 : iArr3) {
                    duration += getDuration(((i + i2) * 14) + i3, j);
                }
            }
        }
        this.mTmpTotalTime = duration;
        return duration;
    }

    public void dumpSummary(PrintWriter printWriter, String str, String str2, int[] iArr, int[] iArr2, int[] iArr3, long j, long j2) {
        printWriter.print(str);
        printWriter.print("* ");
        if (str2 != null) {
            printWriter.print(str2);
        }
        printWriter.print(this.mName);
        printWriter.print(" / ");
        UserHandle.formatUid(printWriter, this.mUid);
        printWriter.print(" / v");
        printWriter.print(this.mVersion);
        printWriter.println(SettingsStringUtil.DELIMITER);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABEL_TOTAL, iArr, iArr2, iArr3, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[0], iArr, iArr2, new int[]{0}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[1], iArr, iArr2, new int[]{1}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[2], iArr, iArr2, new int[]{2}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[3], iArr, iArr2, new int[]{3}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[4], iArr, iArr2, new int[]{4}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[5], iArr, iArr2, new int[]{5}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[6], iArr, iArr2, new int[]{6}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[7], iArr, iArr2, new int[]{7}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[8], iArr, iArr2, new int[]{8}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[9], iArr, iArr2, new int[]{9}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABELS[10], iArr, iArr2, new int[]{10}, j, j2, true);
        dumpProcessSummaryDetails(printWriter, str, DumpUtils.STATE_LABEL_CACHED, iArr, iArr2, new int[]{11, 12, 13}, j, j2, true);
    }

    public void dumpProcessState(PrintWriter printWriter, String str, int[] iArr, int[] iArr2, int[] iArr3, long j) {
        int i;
        String str2;
        int i2 = 0;
        long j2 = 0;
        int i3 = -1;
        while (i2 < iArr.length) {
            int i4 = 0;
            int i5 = -1;
            while (i4 < iArr2.length) {
                int i6 = i5;
                int i7 = 0;
                while (i7 < iArr3.length) {
                    int i8 = iArr[i2];
                    int i9 = iArr2[i4];
                    int i10 = ((i8 + i9) * 14) + iArr3[i7];
                    int i11 = i2;
                    int i12 = i4;
                    long valueForId = this.mDurations.getValueForId((byte) i10);
                    long j3 = j2;
                    if (this.mCurCombinedState == i10) {
                        valueForId += j - this.mStartTime;
                        str2 = " (running)";
                    } else {
                        str2 = "";
                    }
                    long j4 = valueForId;
                    if (j4 != 0) {
                        printWriter.print(str);
                        if (iArr.length > 1) {
                            DumpUtils.printScreenLabel(printWriter, i3 != i8 ? i8 : -1);
                            i3 = i8;
                        }
                        if (iArr2.length > 1) {
                            DumpUtils.printMemLabel(printWriter, i6 != i9 ? i9 : -1, '/');
                            i6 = i9;
                        }
                        printWriter.print(DumpUtils.STATE_LABELS[iArr3[i7]]);
                        printWriter.print(": ");
                        TimeUtils.formatDuration(j4, printWriter);
                        printWriter.println(str2);
                        j2 = j3 + j4;
                    } else {
                        j2 = j3;
                    }
                    i7++;
                    i2 = i11;
                    i4 = i12;
                }
                i4++;
                i5 = i6;
            }
            i2++;
        }
        if (j2 != 0) {
            printWriter.print(str);
            if (iArr.length > 1) {
                i = -1;
                DumpUtils.printScreenLabel(printWriter, -1);
            } else {
                i = -1;
            }
            if (iArr2.length > 1) {
                DumpUtils.printMemLabel(printWriter, i, '/');
            }
            printWriter.print(DumpUtils.STATE_LABEL_TOTAL);
            printWriter.print(": ");
            TimeUtils.formatDuration(j2, printWriter);
            printWriter.println();
        }
    }

    public void dumpPss(PrintWriter printWriter, String str, int[] iArr, int[] iArr2, int[] iArr3, long j) {
        int i;
        int[] iArr4 = iArr;
        int i2 = 0;
        boolean z = false;
        int i3 = -1;
        while (i2 < iArr4.length) {
            int i4 = 0;
            int i5 = -1;
            while (i4 < iArr2.length) {
                int i6 = 0;
                while (i6 < iArr3.length) {
                    int i7 = iArr4[i2];
                    int i8 = iArr2[i4];
                    int key = this.mPssTable.getKey((byte) (((i7 + i8) * 14) + iArr3[i6]));
                    if (key == -1) {
                        i = i2;
                    } else {
                        long[] arrayForKey = this.mPssTable.getArrayForKey(key);
                        int indexFromKey = SparseMappingTable.getIndexFromKey(key);
                        i = i2;
                        if (!z) {
                            printWriter.print(str);
                            printWriter.print("PSS/USS (");
                            printWriter.print(this.mPssTable.getKeyCount());
                            printWriter.println(" entries):");
                            z = true;
                        }
                        printWriter.print(str);
                        printWriter.print("  ");
                        if (iArr4.length > 1) {
                            DumpUtils.printScreenLabel(printWriter, i3 != i7 ? i7 : -1);
                            i3 = i7;
                        }
                        if (iArr2.length > 1) {
                            DumpUtils.printMemLabel(printWriter, i5 != i8 ? i8 : -1, '/');
                            i5 = i8;
                        }
                        printWriter.print(DumpUtils.STATE_LABELS[iArr3[i6]]);
                        printWriter.print(": ");
                        dumpPssSamples(printWriter, arrayForKey, indexFromKey);
                        printWriter.println();
                    }
                    i6++;
                    iArr4 = iArr;
                    i2 = i;
                }
                i4++;
                iArr4 = iArr;
            }
            i2++;
            iArr4 = iArr;
        }
        long totalRunningDuration = getTotalRunningDuration(j);
        if (totalRunningDuration != 0) {
            printWriter.print(str);
            printWriter.print("Cur time ");
            TimeUtils.formatDuration(totalRunningDuration, printWriter);
            if (this.mTotalRunningStartTime != 0) {
                printWriter.print(" (running)");
            }
            if (this.mTotalRunningPss[0] != 0) {
                printWriter.print(": ");
                dumpPssSamples(printWriter, this.mTotalRunningPss, 0);
            }
            printWriter.println();
        }
        if (this.mNumExcessiveCpu != 0) {
            printWriter.print(str);
            printWriter.print("Killed for excessive CPU use: ");
            printWriter.print(this.mNumExcessiveCpu);
            printWriter.println(" times");
        }
        if (this.mNumCachedKill != 0) {
            printWriter.print(str);
            printWriter.print("Killed from cached state: ");
            printWriter.print(this.mNumCachedKill);
            printWriter.print(" times from pss ");
            DebugUtils.printSizeValue(printWriter, this.mMinCachedKillPss * 1024);
            printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            DebugUtils.printSizeValue(printWriter, this.mAvgCachedKillPss * 1024);
            printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            DebugUtils.printSizeValue(printWriter, this.mMaxCachedKillPss * 1024);
            printWriter.println();
        }
    }

    public static void dumpPssSamples(PrintWriter printWriter, long[] jArr, int i) {
        DebugUtils.printSizeValue(printWriter, jArr[i + 1] * 1024);
        printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
        DebugUtils.printSizeValue(printWriter, jArr[i + 2] * 1024);
        printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
        DebugUtils.printSizeValue(printWriter, jArr[i + 3] * 1024);
        printWriter.print("/");
        DebugUtils.printSizeValue(printWriter, jArr[i + 4] * 1024);
        printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
        DebugUtils.printSizeValue(printWriter, jArr[i + 5] * 1024);
        printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
        DebugUtils.printSizeValue(printWriter, jArr[i + 6] * 1024);
        printWriter.print("/");
        DebugUtils.printSizeValue(printWriter, jArr[i + 7] * 1024);
        printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
        DebugUtils.printSizeValue(printWriter, jArr[i + 8] * 1024);
        printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
        DebugUtils.printSizeValue(printWriter, jArr[i + 9] * 1024);
        printWriter.print(" over ");
        printWriter.print(jArr[i + 0]);
    }

    private void dumpProcessSummaryDetails(PrintWriter printWriter, String str, String str2, int[] iArr, int[] iArr2, int[] iArr3, long j, long j2, boolean z) {
        ProcessStats.ProcessDataCollection processDataCollection = new ProcessStats.ProcessDataCollection(iArr, iArr2, iArr3);
        computeProcessData(processDataCollection, j);
        if ((processDataCollection.totalTime / j2) * 100.0d >= 0.005d || processDataCollection.numPss != 0) {
            if (str != null) {
                printWriter.print(str);
            }
            if (str2 != null) {
                printWriter.print("  ");
                printWriter.print(str2);
                printWriter.print(": ");
            }
            processDataCollection.print(printWriter, j2, z);
            if (str != null) {
                printWriter.println();
            }
        }
    }

    public void dumpInternalLocked(PrintWriter printWriter, String str, boolean z) {
        if (z) {
            printWriter.print(str);
            printWriter.print("myID=");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.print(" mCommonProcess=");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.mCommonProcess)));
            printWriter.print(" mPackage=");
            printWriter.println(this.mPackage);
            if (this.mMultiPackage) {
                printWriter.print(str);
                printWriter.print("mMultiPackage=");
                printWriter.println(this.mMultiPackage);
            }
            if (this != this.mCommonProcess) {
                printWriter.print(str);
                printWriter.print("Common Proc: ");
                printWriter.print(this.mCommonProcess.mName);
                printWriter.print("/");
                printWriter.print(this.mCommonProcess.mUid);
                printWriter.print(" pkg=");
                printWriter.println(this.mCommonProcess.mPackage);
            }
        }
        if (this.mActive) {
            printWriter.print(str);
            printWriter.print("mActive=");
            printWriter.println(this.mActive);
        }
        if (this.mDead) {
            printWriter.print(str);
            printWriter.print("mDead=");
            printWriter.println(this.mDead);
        }
        if (this.mNumActiveServices == 0 && this.mNumStartedServices == 0) {
            return;
        }
        printWriter.print(str);
        printWriter.print("mNumActiveServices=");
        printWriter.print(this.mNumActiveServices);
        printWriter.print(" mNumStartedServices=");
        printWriter.println(this.mNumStartedServices);
    }

    public void computeProcessData(ProcessStats.ProcessDataCollection processDataCollection, long j) {
        long j2;
        int i;
        int i2;
        int i3;
        long j3;
        long j4 = 0;
        processDataCollection.totalTime = 0L;
        processDataCollection.maxRss = 0L;
        processDataCollection.avgRss = 0L;
        processDataCollection.minRss = 0L;
        processDataCollection.maxUss = 0L;
        processDataCollection.avgUss = 0L;
        processDataCollection.minUss = 0L;
        processDataCollection.maxPss = 0L;
        processDataCollection.avgPss = 0L;
        processDataCollection.minPss = 0L;
        processDataCollection.numPss = 0L;
        int i4 = 0;
        while (i4 < processDataCollection.screenStates.length) {
            int i5 = 0;
            while (i5 < processDataCollection.memStates.length) {
                int i6 = 0;
                while (i6 < processDataCollection.procStates.length) {
                    int i7 = ((processDataCollection.screenStates[i4] + processDataCollection.memStates[i5]) * 14) + processDataCollection.procStates[i6];
                    processDataCollection.totalTime += getDuration(i7, j);
                    long pssSampleCount = getPssSampleCount(i7);
                    if (pssSampleCount > j4) {
                        long pssMinimum = getPssMinimum(i7);
                        i = i4;
                        long pssAverage = getPssAverage(i7);
                        long pssMaximum = getPssMaximum(i7);
                        long pssUssMinimum = getPssUssMinimum(i7);
                        i2 = i5;
                        i3 = i6;
                        long pssUssAverage = getPssUssAverage(i7);
                        long pssUssMaximum = getPssUssMaximum(i7);
                        long pssRssMinimum = getPssRssMinimum(i7);
                        long pssRssAverage = getPssRssAverage(i7);
                        long pssRssMaximum = getPssRssMaximum(i7);
                        j2 = 0;
                        if (processDataCollection.numPss == 0) {
                            processDataCollection.minPss = pssMinimum;
                            processDataCollection.avgPss = pssAverage;
                            processDataCollection.maxPss = pssMaximum;
                            processDataCollection.minUss = pssUssMinimum;
                            processDataCollection.avgUss = pssUssAverage;
                            processDataCollection.maxUss = pssUssMaximum;
                            processDataCollection.minRss = pssRssMinimum;
                            processDataCollection.avgRss = pssRssAverage;
                            processDataCollection.maxRss = pssRssMaximum;
                            j3 = pssSampleCount;
                        } else {
                            if (pssMinimum < processDataCollection.minPss) {
                                processDataCollection.minPss = pssMinimum;
                            }
                            j3 = pssSampleCount;
                            double d = j3;
                            processDataCollection.avgPss = (long) (((processDataCollection.avgPss * processDataCollection.numPss) + (pssAverage * d)) / (processDataCollection.numPss + j3));
                            if (pssMaximum > processDataCollection.maxPss) {
                                processDataCollection.maxPss = pssMaximum;
                            }
                            if (pssUssMinimum < processDataCollection.minUss) {
                                processDataCollection.minUss = pssUssMinimum;
                            }
                            processDataCollection.avgUss = (long) (((processDataCollection.avgUss * processDataCollection.numPss) + (pssUssAverage * d)) / (processDataCollection.numPss + j3));
                            if (pssUssMaximum > processDataCollection.maxUss) {
                                processDataCollection.maxUss = pssUssMaximum;
                            }
                            if (pssRssMinimum < processDataCollection.minRss) {
                                processDataCollection.minRss = pssRssMinimum;
                            }
                            processDataCollection.avgRss = (long) (((processDataCollection.avgRss * processDataCollection.numPss) + (pssRssAverage * d)) / (processDataCollection.numPss + j3));
                            if (pssRssMaximum > processDataCollection.maxRss) {
                                processDataCollection.maxRss = pssRssMaximum;
                            }
                        }
                        processDataCollection.numPss += j3;
                    } else {
                        j2 = j4;
                        i = i4;
                        i2 = i5;
                        i3 = i6;
                    }
                    i6 = i3 + 1;
                    i4 = i;
                    j4 = j2;
                    i5 = i2;
                }
                i5++;
            }
            i4++;
        }
    }

    public void dumpCsv(PrintWriter printWriter, boolean z, int[] iArr, boolean z2, int[] iArr2, boolean z3, int[] iArr3, long j) {
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        int[] iArr6 = iArr3;
        int length = z ? iArr4.length : 1;
        int length2 = z2 ? iArr5.length : 1;
        int length3 = z3 ? iArr6.length : 1;
        int i = 0;
        while (i < length) {
            int i2 = 0;
            while (i2 < length2) {
                int i3 = 0;
                while (i3 < length3) {
                    int i4 = z ? iArr4[i] : 0;
                    int i5 = z2 ? iArr5[i2] : 0;
                    int i6 = z3 ? iArr6[i3] : 0;
                    int length4 = z ? 1 : iArr4.length;
                    int length5 = z2 ? 1 : iArr5.length;
                    int length6 = z3 ? 1 : iArr6.length;
                    int i7 = length;
                    int i8 = length2;
                    long j2 = 0;
                    int i9 = length3;
                    int i10 = 0;
                    while (i10 < length4) {
                        long duration = j2;
                        int i11 = 0;
                        while (i11 < length5) {
                            int i12 = 0;
                            while (i12 < length6) {
                                duration += getDuration(((i4 + (z ? 0 : iArr4[i10]) + i5 + (z2 ? 0 : iArr5[i11])) * 14) + i6 + (z3 ? 0 : iArr6[i12]), j);
                                i12++;
                                iArr4 = iArr;
                                iArr5 = iArr2;
                                iArr6 = iArr3;
                                length5 = length5;
                            }
                            i11++;
                            iArr4 = iArr;
                            iArr5 = iArr2;
                            iArr6 = iArr3;
                            length5 = length5;
                        }
                        i10++;
                        iArr4 = iArr;
                        iArr5 = iArr2;
                        iArr6 = iArr3;
                        length5 = length5;
                        j2 = duration;
                    }
                    printWriter.print("\t");
                    printWriter.print(j2);
                    i3++;
                    iArr4 = iArr;
                    iArr5 = iArr2;
                    iArr6 = iArr3;
                    length3 = i9;
                    length = i7;
                    length2 = i8;
                }
                i2++;
                iArr4 = iArr;
                iArr5 = iArr2;
                iArr6 = iArr3;
            }
            i++;
            iArr4 = iArr;
            iArr5 = iArr2;
            iArr6 = iArr3;
        }
    }

    public void dumpPackageProcCheckin(PrintWriter printWriter, String str, int i, long j, String str2, long j2) {
        printWriter.print("pkgproc,");
        printWriter.print(str);
        printWriter.print(",");
        printWriter.print(i);
        printWriter.print(",");
        printWriter.print(j);
        printWriter.print(",");
        printWriter.print(DumpUtils.collapseString(str, str2));
        dumpAllStateCheckin(printWriter, j2);
        printWriter.println();
        if (this.mPssTable.getKeyCount() > 0) {
            printWriter.print("pkgpss,");
            printWriter.print(str);
            printWriter.print(",");
            printWriter.print(i);
            printWriter.print(",");
            printWriter.print(j);
            printWriter.print(",");
            printWriter.print(DumpUtils.collapseString(str, str2));
            dumpAllPssCheckin(printWriter);
            printWriter.println();
        }
        if (this.mTotalRunningPss[0] != 0) {
            printWriter.print("pkgrun,");
            printWriter.print(str);
            printWriter.print(",");
            printWriter.print(i);
            printWriter.print(",");
            printWriter.print(j);
            printWriter.print(",");
            printWriter.print(DumpUtils.collapseString(str, str2));
            printWriter.print(",");
            printWriter.print(getTotalRunningDuration(j2));
            printWriter.print(",");
            dumpPssSamplesCheckin(printWriter, this.mTotalRunningPss, 0);
            printWriter.println();
        }
        if (this.mNumExcessiveCpu > 0 || this.mNumCachedKill > 0) {
            printWriter.print("pkgkills,");
            printWriter.print(str);
            printWriter.print(",");
            printWriter.print(i);
            printWriter.print(",");
            printWriter.print(j);
            printWriter.print(",");
            printWriter.print(DumpUtils.collapseString(str, str2));
            printWriter.print(",");
            printWriter.print(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
            printWriter.print(",");
            printWriter.print(this.mNumExcessiveCpu);
            printWriter.print(",");
            printWriter.print(this.mNumCachedKill);
            printWriter.print(",");
            printWriter.print(this.mMinCachedKillPss);
            printWriter.print(SettingsStringUtil.DELIMITER);
            printWriter.print(this.mAvgCachedKillPss);
            printWriter.print(SettingsStringUtil.DELIMITER);
            printWriter.print(this.mMaxCachedKillPss);
            printWriter.println();
        }
    }

    public void dumpProcCheckin(PrintWriter printWriter, String str, int i, long j) {
        if (this.mDurations.getKeyCount() > 0) {
            printWriter.print("proc,");
            printWriter.print(str);
            printWriter.print(",");
            printWriter.print(i);
            dumpAllStateCheckin(printWriter, j);
            printWriter.println();
        }
        if (this.mPssTable.getKeyCount() > 0) {
            printWriter.print("pss,");
            printWriter.print(str);
            printWriter.print(",");
            printWriter.print(i);
            dumpAllPssCheckin(printWriter);
            printWriter.println();
        }
        if (this.mTotalRunningPss[0] != 0) {
            printWriter.print("procrun,");
            printWriter.print(str);
            printWriter.print(",");
            printWriter.print(i);
            printWriter.print(",");
            printWriter.print(getTotalRunningDuration(j));
            printWriter.print(",");
            dumpPssSamplesCheckin(printWriter, this.mTotalRunningPss, 0);
            printWriter.println();
        }
        if (this.mNumExcessiveCpu > 0 || this.mNumCachedKill > 0) {
            printWriter.print("kills,");
            printWriter.print(str);
            printWriter.print(",");
            printWriter.print(i);
            printWriter.print(",");
            printWriter.print(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
            printWriter.print(",");
            printWriter.print(this.mNumExcessiveCpu);
            printWriter.print(",");
            printWriter.print(this.mNumCachedKill);
            printWriter.print(",");
            printWriter.print(this.mMinCachedKillPss);
            printWriter.print(SettingsStringUtil.DELIMITER);
            printWriter.print(this.mAvgCachedKillPss);
            printWriter.print(SettingsStringUtil.DELIMITER);
            printWriter.print(this.mMaxCachedKillPss);
            printWriter.println();
        }
    }

    public void dumpAllStateCheckin(PrintWriter printWriter, long j) {
        int i;
        boolean z = false;
        for (int i2 = 0; i2 < this.mDurations.getKeyCount(); i2++) {
            int keyAt = this.mDurations.getKeyAt(i2);
            byte idFromKey = SparseMappingTable.getIdFromKey(keyAt);
            long value = this.mDurations.getValue(keyAt);
            if (this.mCurCombinedState == idFromKey) {
                value += j - this.mStartTime;
                z = true;
            }
            DumpUtils.printProcStateTagAndValue(printWriter, idFromKey, value);
        }
        if (z || (i = this.mCurCombinedState) == -1) {
            return;
        }
        DumpUtils.printProcStateTagAndValue(printWriter, i, j - this.mStartTime);
    }

    public void dumpAllPssCheckin(PrintWriter printWriter) {
        int keyCount = this.mPssTable.getKeyCount();
        for (int i = 0; i < keyCount; i++) {
            int keyAt = this.mPssTable.getKeyAt(i);
            byte idFromKey = SparseMappingTable.getIdFromKey(keyAt);
            printWriter.print(',');
            DumpUtils.printProcStateTag(printWriter, idFromKey);
            printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
            dumpPssSamplesCheckin(printWriter, this.mPssTable.getArrayForKey(keyAt), SparseMappingTable.getIndexFromKey(keyAt));
        }
    }

    public static void dumpPssSamplesCheckin(PrintWriter printWriter, long[] jArr, int i) {
        printWriter.print(jArr[i + 0]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 1]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 2]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 3]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 4]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 5]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 6]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 7]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 8]);
        printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
        printWriter.print(jArr[i + 9]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("ProcessState{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" ");
        sb.append(this.mName);
        sb.append("/");
        sb.append(this.mUid);
        sb.append(" pkg=");
        sb.append(this.mPackage);
        if (this.mMultiPackage) {
            sb.append(" (multi)");
        }
        if (this.mCommonProcess != this) {
            sb.append(" (sub)");
        }
        sb.append("}");
        return sb.toString();
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j, String str, int i, long j2) {
        long j3;
        long j4;
        int i2;
        int i3;
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1138166333441L, str);
        protoOutputStream.write(1120986464258L, i);
        if (this.mNumExcessiveCpu > 0 || this.mNumCachedKill > 0) {
            long jStart2 = protoOutputStream.start(1146756268035L);
            protoOutputStream.write(1120986464257L, this.mNumExcessiveCpu);
            protoOutputStream.write(1120986464258L, this.mNumCachedKill);
            j3 = jStart;
            ProtoUtils.toAggStatsProto(protoOutputStream, 1146756268035L, this.mMinCachedKillPss, this.mAvgCachedKillPss, this.mMaxCachedKillPss);
            protoOutputStream.end(jStart2);
        } else {
            j3 = jStart;
        }
        SparseLongArray sparseLongArray = new SparseLongArray();
        boolean z = false;
        for (int i4 = 0; i4 < this.mDurations.getKeyCount(); i4++) {
            int keyAt = this.mDurations.getKeyAt(i4);
            byte idFromKey = SparseMappingTable.getIdFromKey(keyAt);
            long value = this.mDurations.getValue(keyAt);
            if (this.mCurCombinedState == idFromKey) {
                value += j2 - this.mStartTime;
                z = true;
            }
            sparseLongArray.put(idFromKey, value);
        }
        if (!z && (i3 = this.mCurCombinedState) != -1) {
            sparseLongArray.put(i3, j2 - this.mStartTime);
        }
        int i5 = 0;
        while (true) {
            j4 = 2246267895813L;
            if (i5 >= this.mPssTable.getKeyCount()) {
                break;
            }
            int keyAt2 = this.mPssTable.getKeyAt(i5);
            byte idFromKey2 = SparseMappingTable.getIdFromKey(keyAt2);
            if (sparseLongArray.indexOfKey(idFromKey2) < 0) {
                i2 = i5;
            } else {
                long jStart3 = protoOutputStream.start(2246267895813L);
                i2 = i5;
                DumpUtils.printProcStateTagProto(protoOutputStream, 1159641169921L, 1159641169922L, 1159641169923L, idFromKey2);
                long j5 = sparseLongArray.get(idFromKey2);
                sparseLongArray.delete(idFromKey2);
                protoOutputStream.write(1112396529668L, j5);
                this.mPssTable.writeStatsToProtoForKey(protoOutputStream, keyAt2);
                protoOutputStream.end(jStart3);
            }
            i5 = i2 + 1;
        }
        long j6 = 1112396529668L;
        int i6 = 0;
        while (i6 < sparseLongArray.size()) {
            long jStart4 = protoOutputStream.start(j4);
            int i7 = i6;
            DumpUtils.printProcStateTagProto(protoOutputStream, 1159641169921L, 1159641169922L, 1159641169923L, sparseLongArray.keyAt(i6));
            protoOutputStream.write(1112396529668L, sparseLongArray.valueAt(i7));
            protoOutputStream.end(jStart4);
            i6 = i7 + 1;
            j6 = 1112396529668L;
            j4 = j4;
        }
        long j7 = j6;
        long totalRunningDuration = getTotalRunningDuration(j2);
        if (totalRunningDuration > 0) {
            long jStart5 = protoOutputStream.start(1146756268038L);
            protoOutputStream.write(j7, totalRunningDuration);
            long[] jArr = this.mTotalRunningPss;
            if (jArr[0] != 0) {
                PssTable.writeStatsToProto(protoOutputStream, jArr, 0);
            }
            protoOutputStream.end(jStart5);
        }
        protoOutputStream.end(j3);
    }

    static void writeCompressedProcessName(ProtoOutputStream protoOutputStream, long j, String str, String str2, boolean z) {
        if (z) {
            protoOutputStream.write(j, str);
            return;
        }
        if (TextUtils.equals(str, str2)) {
            return;
        }
        if (str.startsWith(str2)) {
            int length = str2.length();
            if (str.charAt(length) == ':') {
                protoOutputStream.write(j, str.substring(length));
                return;
            }
        }
        protoOutputStream.write(j, str);
    }

    public void dumpAggregatedProtoForStatsd(ProtoOutputStream protoOutputStream, long j, String str, int i, long j2, ProcessMap<ArraySet<ProcessStats.PackageState>> processMap, SparseArray<ArraySet<String>> sparseArray) {
        long j3;
        int i2;
        SparseLongArray sparseLongArray = new SparseLongArray();
        int i3 = 0;
        boolean z = false;
        for (int i4 = 0; i4 < this.mDurations.getKeyCount(); i4++) {
            int keyAt = this.mDurations.getKeyAt(i4);
            byte idFromKey = SparseMappingTable.getIdFromKey(keyAt);
            int iAggregateCurrentProcessState = DumpUtils.aggregateCurrentProcessState(idFromKey);
            long value = this.mDurations.getValue(keyAt);
            if (this.mCurCombinedState == idFromKey) {
                value += j2 - this.mStartTime;
                z = true;
            }
            int iIndexOfKey = sparseLongArray.indexOfKey(iAggregateCurrentProcessState);
            if (iIndexOfKey >= 0) {
                sparseLongArray.put(iAggregateCurrentProcessState, value + sparseLongArray.valueAt(iIndexOfKey));
            } else {
                sparseLongArray.put(iAggregateCurrentProcessState, value);
            }
        }
        if (!z && (i2 = this.mCurCombinedState) != -1) {
            int iAggregateCurrentProcessState2 = DumpUtils.aggregateCurrentProcessState(i2);
            int iIndexOfKey2 = sparseLongArray.indexOfKey(iAggregateCurrentProcessState2);
            if (iIndexOfKey2 >= 0) {
                sparseLongArray.put(iAggregateCurrentProcessState2, (j2 - this.mStartTime) + sparseLongArray.valueAt(iIndexOfKey2));
            } else {
                sparseLongArray.put(iAggregateCurrentProcessState2, j2 - this.mStartTime);
            }
        }
        SparseLongArray sparseLongArray2 = new SparseLongArray();
        SparseLongArray sparseLongArray3 = new SparseLongArray();
        for (int i5 = 0; i5 < this.mPssTable.getKeyCount(); i5++) {
            int keyAt2 = this.mPssTable.getKeyAt(i5);
            byte idFromKey2 = SparseMappingTable.getIdFromKey(keyAt2);
            int iAggregateCurrentProcessState3 = DumpUtils.aggregateCurrentProcessState(idFromKey2);
            if (sparseLongArray.indexOfKey(iAggregateCurrentProcessState3) >= 0) {
                long[] rssMeanAndMax = this.mPssTable.getRssMeanAndMax(keyAt2);
                long valueForId = rssMeanAndMax[0] * this.mDurations.getValueForId(idFromKey2);
                if (sparseLongArray2.indexOfKey(iAggregateCurrentProcessState3) >= 0) {
                    sparseLongArray2.put(iAggregateCurrentProcessState3, valueForId + sparseLongArray2.get(iAggregateCurrentProcessState3));
                } else {
                    sparseLongArray2.put(iAggregateCurrentProcessState3, valueForId);
                }
                if (sparseLongArray3.indexOfKey(iAggregateCurrentProcessState3) >= 0 && sparseLongArray3.get(iAggregateCurrentProcessState3) < rssMeanAndMax[1]) {
                    sparseLongArray3.put(iAggregateCurrentProcessState3, rssMeanAndMax[1]);
                } else if (sparseLongArray3.indexOfKey(iAggregateCurrentProcessState3) < 0) {
                    sparseLongArray3.put(iAggregateCurrentProcessState3, rssMeanAndMax[1]);
                }
            }
        }
        for (int i6 = 0; i6 < sparseLongArray.size(); i6++) {
            int iKeyAt = sparseLongArray.keyAt(i6);
            if (sparseLongArray2.indexOfKey(iKeyAt) >= 0) {
                long j4 = sparseLongArray.get(iKeyAt);
                if (j4 > 0) {
                    j3 = sparseLongArray2.get(iKeyAt) / j4;
                } else {
                    j3 = sparseLongArray2.get(iKeyAt);
                }
                sparseLongArray2.put(iKeyAt, j3);
            }
        }
        long jStart = protoOutputStream.start(j);
        writeCompressedProcessName(protoOutputStream, 1138166333441L, str, this.mPackage, this.mMultiPackage || sparseArray.get(this.mUid).size() > 1);
        protoOutputStream.write(1120986464258L, i);
        while (i3 < sparseLongArray.size()) {
            long jStart2 = protoOutputStream.start(2246267895813L);
            int iKeyAt2 = sparseLongArray.keyAt(i3);
            DumpUtils.printAggregatedProcStateTagProto(protoOutputStream, 1159641169921L, 1159641169930L, iKeyAt2);
            protoOutputStream.write(1112396529668L, sparseLongArray.get(iKeyAt2));
            ProtoUtils.toAggStatsProto(protoOutputStream, 1146756268040L, 0L, 0L, 0L, (int) sparseLongArray2.get(iKeyAt2), (int) sparseLongArray3.get(iKeyAt2));
            protoOutputStream.end(jStart2);
            i3++;
            sparseLongArray3 = sparseLongArray3;
            sparseLongArray2 = sparseLongArray2;
            jStart = jStart;
        }
        this.mStats.dumpFilteredAssociationStatesProtoForProc(protoOutputStream, 2246267895815L, j2, this, processMap, sparseArray);
        protoOutputStream.end(jStart);
    }
}
