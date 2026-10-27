package com.android.internal.app.procstats;

import android.os.Parcel;
import android.os.SystemClock;
import android.os.UserHandle;
import android.telephony.SmsManager;
import android.util.ArrayMap;
import android.util.Pair;
import android.util.Slog;
import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;
import com.android.internal.accessibility.common.ShortcutConstants;
import com.android.internal.app.procstats.ProcessStats;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class AssociationState {
    private static final boolean DEBUG = false;
    private static final String TAG = "ProcessStats";
    private static final boolean VALIDATE_TIMES = false;
    private final String mName;
    private final ProcessStats.PackageState mPackageState;
    private ProcessState mProc;
    private final String mProcessName;
    private final ProcessStats mProcessStats;
    final ArrayMap<SourceKey, SourceState> mSources = new ArrayMap<>();
    private int mTotalActiveCount;
    private long mTotalActiveDuration;
    private int mTotalActiveNesting;
    private long mTotalActiveStartUptime;
    private int mTotalCount;
    private long mTotalDuration;
    private int mTotalNesting;
    private long mTotalStartUptime;
    private static final SourceKey sTmpSourceKey = new SourceKey(0, null, null);
    static final Comparator<Pair<SourceKey, SourceDumpContainer>> ASSOCIATION_COMPARATOR = new Comparator() { // from class: com.android.internal.app.procstats.-$$Lambda$AssociationState$GUEd32urJjRbdmMFhH0zE1uwg50
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return AssociationState.lambda$static$0((Pair) obj, (Pair) obj2);
        }
    };

    static /* synthetic */ int access$108(AssociationState associationState) {
        int i = associationState.mTotalActiveNesting;
        associationState.mTotalActiveNesting = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(AssociationState associationState) {
        int i = associationState.mTotalActiveNesting;
        associationState.mTotalActiveNesting = i - 1;
        return i;
    }

    static /* synthetic */ int access$208(AssociationState associationState) {
        int i = associationState.mTotalActiveCount;
        associationState.mTotalActiveCount = i + 1;
        return i;
    }

    static /* synthetic */ long access$414(AssociationState associationState, long j) {
        long j2 = associationState.mTotalActiveDuration + j;
        associationState.mTotalActiveDuration = j2;
        return j2;
    }

    static /* synthetic */ int access$510(AssociationState associationState) {
        int i = associationState.mTotalNesting;
        associationState.mTotalNesting = i - 1;
        return i;
    }

    static /* synthetic */ long access$614(AssociationState associationState, long j) {
        long j2 = associationState.mTotalDuration + j;
        associationState.mTotalDuration = j2;
        return j2;
    }

    public final class SourceState {
        int mActiveCount;
        long mActiveDuration;
        DurationsTable mActiveDurations;
        long mActiveStartUptime;
        int mCount;
        long mDuration;
        boolean mInTrackingList;
        final SourceKey mKey;
        int mNesting;
        long mStartUptime;
        long mTrackingUptime;
        int mProcStateSeq = -1;
        int mProcState = -1;
        int mActiveProcState = -1;

        SourceState(SourceKey sourceKey) {
            this.mKey = sourceKey;
        }

        public AssociationState getAssociationState() {
            return AssociationState.this;
        }

        public String getProcessName() {
            return this.mKey.mProcess;
        }

        public int getUid() {
            return this.mKey.mUid;
        }

        public void trackProcState(int i, int i2, long j) {
            int i3 = ProcessState.PROCESS_STATE_TO_STATE[i];
            if (i2 != this.mProcStateSeq) {
                this.mProcStateSeq = i2;
                this.mProcState = i3;
            } else if (i3 < this.mProcState) {
                this.mProcState = i3;
            }
            if (i3 >= 9 || this.mInTrackingList) {
                return;
            }
            this.mInTrackingList = true;
            this.mTrackingUptime = j;
            AssociationState.this.mProcessStats.mTrackingAssociations.add(this);
        }

        public void stop() {
            int i = this.mNesting - 1;
            this.mNesting = i;
            if (i == 0) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                this.mDuration += jUptimeMillis - this.mStartUptime;
                stopTracking(jUptimeMillis);
            }
        }

        void startActive(long j) {
            if (this.mInTrackingList) {
                if (this.mActiveStartUptime == 0) {
                    this.mActiveStartUptime = j;
                    this.mActiveCount++;
                    AssociationState.access$108(AssociationState.this);
                    if (AssociationState.this.mTotalActiveNesting == 1) {
                        AssociationState.access$208(AssociationState.this);
                        AssociationState.this.mTotalActiveStartUptime = j;
                    }
                }
                int i = this.mActiveProcState;
                if (i != this.mProcState) {
                    if (i != -1) {
                        long j2 = (this.mActiveDuration + j) - this.mActiveStartUptime;
                        this.mActiveStartUptime = j;
                        if (j2 != 0) {
                            if (this.mActiveDurations == null) {
                                makeDurations();
                            }
                            this.mActiveDurations.addDuration(this.mActiveProcState, j2);
                            this.mActiveDuration = 0L;
                        }
                    }
                    this.mActiveProcState = this.mProcState;
                    return;
                }
                return;
            }
            Slog.wtf("ProcessStats", "startActive while not tracking: " + this);
        }

        void stopActive(long j) {
            if (this.mActiveStartUptime != 0) {
                if (!this.mInTrackingList) {
                    Slog.wtf("ProcessStats", "stopActive while not tracking: " + this);
                }
                long j2 = j - this.mActiveStartUptime;
                this.mActiveStartUptime = 0L;
                DurationsTable durationsTable = this.mActiveDurations;
                if (durationsTable != null) {
                    durationsTable.addDuration(this.mActiveProcState, j2);
                } else {
                    this.mActiveDuration += j2;
                }
                AssociationState.access$110(AssociationState.this);
                if (AssociationState.this.mTotalActiveNesting == 0) {
                    AssociationState associationState = AssociationState.this;
                    AssociationState.access$414(associationState, j - associationState.mTotalActiveStartUptime);
                    AssociationState.this.mTotalActiveStartUptime = 0L;
                }
            }
        }

        void makeDurations() {
            this.mActiveDurations = new DurationsTable(AssociationState.this.mProcessStats.mTableData);
        }

        void stopTracking(long j) {
            AssociationState.access$510(AssociationState.this);
            if (AssociationState.this.mTotalNesting == 0) {
                AssociationState associationState = AssociationState.this;
                AssociationState.access$614(associationState, j - associationState.mTotalStartUptime);
            }
            stopActive(j);
            if (this.mInTrackingList) {
                this.mInTrackingList = false;
                this.mProcState = -1;
                ArrayList<SourceState> arrayList = AssociationState.this.mProcessStats.mTrackingAssociations;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    if (arrayList.get(size) == this) {
                        arrayList.remove(size);
                        return;
                    }
                }
                Slog.wtf("ProcessStats", "Stop tracking didn't find in tracking list: " + this);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("SourceState{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(this.mKey.mProcess).append("/").append(this.mKey.mUid);
            if (this.mProcState != -1) {
                sb.append(" ").append(DumpUtils.STATE_NAMES[this.mProcState]).append(" #").append(this.mProcStateSeq);
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public final class SourceDumpContainer {
        public long mActiveTime;
        public final SourceState mState;
        public long mTotalTime;

        public SourceDumpContainer(SourceState sourceState) {
            this.mState = sourceState;
        }
    }

    public static final class SourceKey {
        String mPackage;
        String mProcess;
        int mUid;

        SourceKey(int i, String str, String str2) {
            this.mUid = i;
            this.mProcess = str;
            this.mPackage = str2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SourceKey)) {
                return false;
            }
            SourceKey sourceKey = (SourceKey) obj;
            return sourceKey.mUid == this.mUid && Objects.equals(sourceKey.mProcess, this.mProcess) && Objects.equals(sourceKey.mPackage, this.mPackage);
        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.mUid);
            String str = this.mProcess;
            int iHashCode2 = iHashCode ^ (str == null ? 0 : str.hashCode());
            String str2 = this.mPackage;
            return iHashCode2 ^ (str2 != null ? str2.hashCode() * 33 : 0);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("SourceKey{");
            UserHandle.formatUid(sb, this.mUid);
            sb.append(' ');
            sb.append(this.mProcess);
            sb.append(' ');
            sb.append(this.mPackage);
            sb.append('}');
            return sb.toString();
        }
    }

    public AssociationState(ProcessStats processStats, ProcessStats.PackageState packageState, String str, String str2, ProcessState processState) {
        this.mProcessStats = processStats;
        this.mPackageState = packageState;
        this.mName = str;
        this.mProcessName = str2;
        this.mProc = processState;
    }

    public int getUid() {
        return this.mPackageState.mUid;
    }

    public String getPackage() {
        return this.mPackageState.mPackageName;
    }

    public String getProcessName() {
        return this.mProcessName;
    }

    public String getName() {
        return this.mName;
    }

    public ProcessState getProcess() {
        return this.mProc;
    }

    public void setProcess(ProcessState processState) {
        this.mProc = processState;
    }

    public long getTotalDuration(long j) {
        return this.mTotalDuration + (this.mTotalNesting > 0 ? j - this.mTotalStartUptime : 0L);
    }

    public long getActiveDuration(long j) {
        return this.mTotalActiveDuration + (this.mTotalActiveNesting > 0 ? j - this.mTotalActiveStartUptime : 0L);
    }

    public SourceState startSource(int i, String str, String str2) {
        SourceState sourceState;
        SourceKey sourceKey = sTmpSourceKey;
        synchronized (sourceKey) {
            sourceKey.mUid = i;
            sourceKey.mProcess = str;
            sourceKey.mPackage = str2;
            sourceState = this.mSources.get(sourceKey);
        }
        if (sourceState == null) {
            SourceKey sourceKey2 = new SourceKey(i, str, str2);
            sourceState = new SourceState(sourceKey2);
            this.mSources.put(sourceKey2, sourceState);
        }
        sourceState.mNesting++;
        if (sourceState.mNesting == 1) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            sourceState.mCount++;
            sourceState.mStartUptime = jUptimeMillis;
            int i2 = this.mTotalNesting + 1;
            this.mTotalNesting = i2;
            if (i2 == 1) {
                this.mTotalCount++;
                this.mTotalStartUptime = jUptimeMillis;
            }
        }
        return sourceState;
    }

    public void add(AssociationState associationState) {
        this.mTotalCount += associationState.mTotalCount;
        this.mTotalDuration += associationState.mTotalDuration;
        this.mTotalActiveCount += associationState.mTotalActiveCount;
        this.mTotalActiveDuration += associationState.mTotalActiveDuration;
        for (int size = associationState.mSources.size() - 1; size >= 0; size--) {
            SourceKey sourceKeyKeyAt = associationState.mSources.keyAt(size);
            SourceState sourceStateValueAt = associationState.mSources.valueAt(size);
            SourceState sourceState = this.mSources.get(sourceKeyKeyAt);
            if (sourceState == null) {
                sourceState = new SourceState(sourceKeyKeyAt);
                this.mSources.put(sourceKeyKeyAt, sourceState);
            }
            sourceState.mCount += sourceStateValueAt.mCount;
            sourceState.mDuration += sourceStateValueAt.mDuration;
            sourceState.mActiveCount += sourceStateValueAt.mActiveCount;
            if (sourceStateValueAt.mActiveDuration != 0 || sourceStateValueAt.mActiveDurations != null) {
                if (sourceState.mActiveDurations != null) {
                    if (sourceStateValueAt.mActiveDurations != null) {
                        sourceState.mActiveDurations.addDurations(sourceStateValueAt.mActiveDurations);
                    } else {
                        sourceState.mActiveDurations.addDuration(sourceStateValueAt.mActiveProcState, sourceStateValueAt.mActiveDuration);
                    }
                } else if (sourceStateValueAt.mActiveDurations != null) {
                    sourceState.makeDurations();
                    sourceState.mActiveDurations.addDurations(sourceStateValueAt.mActiveDurations);
                    if (sourceState.mActiveDuration != 0) {
                        sourceState.mActiveDurations.addDuration(sourceState.mActiveProcState, sourceState.mActiveDuration);
                        sourceState.mActiveDuration = 0L;
                        sourceState.mActiveProcState = -1;
                    }
                } else if (sourceState.mActiveDuration != 0) {
                    if (sourceState.mActiveProcState == sourceStateValueAt.mActiveProcState) {
                        sourceState.mActiveDuration += sourceStateValueAt.mActiveDuration;
                    } else {
                        sourceState.makeDurations();
                        sourceState.mActiveDurations.addDuration(sourceState.mActiveProcState, sourceState.mActiveDuration);
                        sourceState.mActiveDurations.addDuration(sourceStateValueAt.mActiveProcState, sourceStateValueAt.mActiveDuration);
                        sourceState.mActiveDuration = 0L;
                        sourceState.mActiveProcState = -1;
                    }
                } else {
                    sourceState.mActiveProcState = sourceStateValueAt.mActiveProcState;
                    sourceState.mActiveDuration = sourceStateValueAt.mActiveDuration;
                }
            }
        }
    }

    public boolean isInUse() {
        return this.mTotalNesting > 0;
    }

    public void resetSafely(long j) {
        if (!isInUse()) {
            this.mSources.clear();
            this.mTotalActiveCount = 0;
            this.mTotalCount = 0;
        } else {
            for (int size = this.mSources.size() - 1; size >= 0; size--) {
                SourceState sourceStateValueAt = this.mSources.valueAt(size);
                if (sourceStateValueAt.mNesting > 0) {
                    sourceStateValueAt.mCount = 1;
                    sourceStateValueAt.mStartUptime = j;
                    sourceStateValueAt.mDuration = 0L;
                    if (sourceStateValueAt.mActiveStartUptime > 0) {
                        sourceStateValueAt.mActiveCount = 1;
                        sourceStateValueAt.mActiveStartUptime = j;
                    } else {
                        sourceStateValueAt.mActiveCount = 0;
                    }
                    sourceStateValueAt.mActiveDuration = 0L;
                    sourceStateValueAt.mActiveDurations = null;
                } else {
                    this.mSources.removeAt(size);
                }
            }
            this.mTotalCount = 1;
            this.mTotalStartUptime = j;
            if (this.mTotalActiveNesting > 0) {
                this.mTotalActiveCount = 1;
                this.mTotalActiveStartUptime = j;
            } else {
                this.mTotalActiveCount = 0;
            }
        }
        this.mTotalActiveDuration = 0L;
        this.mTotalDuration = 0L;
    }

    public void writeToParcel(ProcessStats processStats, Parcel parcel, long j) {
        parcel.writeInt(this.mTotalCount);
        parcel.writeLong(this.mTotalDuration);
        parcel.writeInt(this.mTotalActiveCount);
        parcel.writeLong(this.mTotalActiveDuration);
        int size = this.mSources.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; i++) {
            SourceKey sourceKeyKeyAt = this.mSources.keyAt(i);
            SourceState sourceStateValueAt = this.mSources.valueAt(i);
            parcel.writeInt(sourceKeyKeyAt.mUid);
            processStats.writeCommonString(parcel, sourceKeyKeyAt.mProcess);
            processStats.writeCommonString(parcel, sourceKeyKeyAt.mPackage);
            parcel.writeInt(sourceStateValueAt.mCount);
            parcel.writeLong(sourceStateValueAt.mDuration);
            parcel.writeInt(sourceStateValueAt.mActiveCount);
            if (sourceStateValueAt.mActiveDurations != null) {
                parcel.writeInt(1);
                sourceStateValueAt.mActiveDurations.writeToParcel(parcel);
            } else {
                parcel.writeInt(0);
                parcel.writeInt(sourceStateValueAt.mActiveProcState);
                parcel.writeLong(sourceStateValueAt.mActiveDuration);
            }
        }
    }

    public String readFromParcel(ProcessStats processStats, Parcel parcel, int i) {
        this.mTotalCount = parcel.readInt();
        this.mTotalDuration = parcel.readLong();
        this.mTotalActiveCount = parcel.readInt();
        this.mTotalActiveDuration = parcel.readLong();
        int i2 = parcel.readInt();
        if (i2 < 0 || i2 > 100000) {
            return "Association with bad src count: " + i2;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            SourceKey sourceKey = new SourceKey(parcel.readInt(), processStats.readCommonString(parcel, i), processStats.readCommonString(parcel, i));
            SourceState sourceState = new SourceState(sourceKey);
            sourceState.mCount = parcel.readInt();
            sourceState.mDuration = parcel.readLong();
            sourceState.mActiveCount = parcel.readInt();
            if (parcel.readInt() != 0) {
                sourceState.makeDurations();
                if (!sourceState.mActiveDurations.readFromParcel(parcel)) {
                    return "Duration table corrupt: " + sourceKey + " <- " + sourceState;
                }
            } else {
                sourceState.mActiveProcState = parcel.readInt();
                sourceState.mActiveDuration = parcel.readLong();
            }
            this.mSources.put(sourceKey, sourceState);
        }
        return null;
    }

    public void commitStateTime(long j) {
        if (isInUse()) {
            for (int size = this.mSources.size() - 1; size >= 0; size--) {
                SourceState sourceStateValueAt = this.mSources.valueAt(size);
                if (sourceStateValueAt.mNesting > 0) {
                    sourceStateValueAt.mDuration += j - sourceStateValueAt.mStartUptime;
                    sourceStateValueAt.mStartUptime = j;
                }
                if (sourceStateValueAt.mActiveStartUptime > 0) {
                    long j2 = j - sourceStateValueAt.mActiveStartUptime;
                    sourceStateValueAt.mActiveStartUptime = j;
                    if (sourceStateValueAt.mActiveDurations != null) {
                        sourceStateValueAt.mActiveDurations.addDuration(sourceStateValueAt.mActiveProcState, j2);
                    } else {
                        sourceStateValueAt.mActiveDuration += j2;
                    }
                }
            }
            if (this.mTotalNesting > 0) {
                this.mTotalDuration += j - this.mTotalStartUptime;
                this.mTotalStartUptime = j;
            }
            if (this.mTotalActiveNesting > 0) {
                this.mTotalActiveDuration += j - this.mTotalActiveStartUptime;
                this.mTotalActiveStartUptime = j;
            }
        }
    }

    public boolean hasProcessOrPackage(String str) {
        if (this.mProcessName.equals(str)) {
            return true;
        }
        int size = this.mSources.size();
        for (int i = 0; i < size; i++) {
            SourceKey sourceKeyKeyAt = this.mSources.keyAt(i);
            if (str.equals(sourceKeyKeyAt.mProcess) || str.equals(sourceKeyKeyAt.mPackage)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ int lambda$static$0(Pair pair, Pair pair2) {
        int iCompareTo;
        if (((SourceDumpContainer) pair.second).mActiveTime != ((SourceDumpContainer) pair2.second).mActiveTime) {
            return ((SourceDumpContainer) pair.second).mActiveTime > ((SourceDumpContainer) pair2.second).mActiveTime ? -1 : 1;
        }
        if (((SourceDumpContainer) pair.second).mTotalTime != ((SourceDumpContainer) pair2.second).mTotalTime) {
            return ((SourceDumpContainer) pair.second).mTotalTime > ((SourceDumpContainer) pair2.second).mTotalTime ? -1 : 1;
        }
        if (((SourceKey) pair.first).mUid != ((SourceKey) pair2.first).mUid) {
            return ((SourceKey) pair.first).mUid < ((SourceKey) pair2.first).mUid ? -1 : 1;
        }
        if (((SourceKey) pair.first).mProcess == ((SourceKey) pair2.first).mProcess || (iCompareTo = ((SourceKey) pair.first).mProcess.compareTo(((SourceKey) pair2.first).mProcess)) == 0) {
            return 0;
        }
        return iCompareTo;
    }

    public ArrayList<Pair<SourceKey, SourceDumpContainer>> createSortedAssociations(long j, long j2) {
        int size = this.mSources.size();
        ArrayList<Pair<SourceKey, SourceDumpContainer>> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            SourceState sourceStateValueAt = this.mSources.valueAt(i);
            SourceDumpContainer sourceDumpContainer = new SourceDumpContainer(sourceStateValueAt);
            long j3 = sourceStateValueAt.mDuration;
            if (sourceStateValueAt.mNesting > 0) {
                j3 += j - sourceStateValueAt.mStartUptime;
            }
            sourceDumpContainer.mTotalTime = j3;
            sourceDumpContainer.mActiveTime = dumpTime(null, null, sourceStateValueAt, j2, j, false, false);
            if (sourceDumpContainer.mActiveTime < 0) {
                sourceDumpContainer.mActiveTime = -sourceDumpContainer.mActiveTime;
            }
            arrayList.add(new Pair<>(this.mSources.keyAt(i), sourceDumpContainer));
        }
        Collections.sort(arrayList, ASSOCIATION_COMPARATOR);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x015c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void dumpStats(java.io.PrintWriter r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.util.ArrayList<android.util.Pair<com.android.internal.app.procstats.AssociationState.SourceKey, com.android.internal.app.procstats.AssociationState.SourceDumpContainer>> r27, long r28, long r30, java.lang.String r32, boolean r33, boolean r34) {
        /*
            Method dump skipped, instruction units count: 673
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.procstats.AssociationState.dumpStats(java.io.PrintWriter, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, long, long, java.lang.String, boolean, boolean):void");
    }

    void dumpActiveDurationSummary(PrintWriter printWriter, SourceState sourceState, long j, long j2, boolean z) {
        long jDumpTime = dumpTime(null, null, sourceState, j, j2, false, false);
        if (jDumpTime < 0) {
            jDumpTime = -jDumpTime;
        }
        if (z) {
            TimeUtils.formatDuration(jDumpTime, printWriter);
            printWriter.print(" / ");
        } else {
            printWriter.print("time ");
        }
        DumpUtils.printPercent(printWriter, jDumpTime / j);
        if (sourceState.mActiveStartUptime > 0) {
            printWriter.print(" (running)");
        }
        printWriter.println();
    }

    long dumpTime(PrintWriter printWriter, String str, SourceState sourceState, long j, long j2, boolean z, boolean z2) {
        long valueForId;
        String str2;
        int i = 0;
        long j3 = 0;
        boolean z3 = false;
        long j4 = 0;
        while (i < 14) {
            if (sourceState.mActiveDurations != null) {
                valueForId = sourceState.mActiveDurations.getValueForId((byte) i);
            } else {
                valueForId = sourceState.mActiveProcState == i ? sourceState.mActiveDuration : j3;
            }
            if (sourceState.mActiveStartUptime == j3 || sourceState.mActiveProcState != i) {
                str2 = null;
            } else {
                valueForId += j2 - sourceState.mActiveStartUptime;
                z3 = true;
                str2 = " (running)";
            }
            if (valueForId != j3) {
                if (printWriter != null) {
                    printWriter.print(str);
                    printWriter.print(DumpUtils.STATE_LABELS[i]);
                    printWriter.print(": ");
                    if (z2) {
                        TimeUtils.formatDuration(valueForId, printWriter);
                        printWriter.print(" / ");
                    } else {
                        printWriter.print("time ");
                    }
                    DumpUtils.printPercent(printWriter, valueForId / j);
                    if (str2 != null) {
                        printWriter.print(str2);
                    }
                    printWriter.println();
                }
                j4 += valueForId;
            }
            i++;
            j3 = 0;
        }
        return z3 ? -j4 : j4;
    }

    public void dumpTimesCheckin(PrintWriter printWriter, String str, int i, long j, String str2, long j2) {
        int size = this.mSources.size();
        for (int i2 = 0; i2 < size; i2++) {
            SourceKey sourceKeyKeyAt = this.mSources.keyAt(i2);
            SourceState sourceStateValueAt = this.mSources.valueAt(i2);
            printWriter.print("pkgasc");
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(str);
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(i);
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(j);
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(str2);
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(sourceKeyKeyAt.mProcess);
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(sourceKeyKeyAt.mUid);
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(sourceStateValueAt.mCount);
            long j3 = sourceStateValueAt.mDuration;
            if (sourceStateValueAt.mNesting > 0) {
                j3 += j2 - sourceStateValueAt.mStartUptime;
            }
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(j3);
            printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
            printWriter.print(sourceStateValueAt.mActiveCount);
            long j4 = sourceStateValueAt.mActiveStartUptime != 0 ? j2 - sourceStateValueAt.mActiveStartUptime : 0L;
            if (sourceStateValueAt.mActiveDurations != null) {
                int keyCount = sourceStateValueAt.mActiveDurations.getKeyCount();
                for (int i3 = 0; i3 < keyCount; i3++) {
                    int keyAt = sourceStateValueAt.mActiveDurations.getKeyAt(i3);
                    long value = sourceStateValueAt.mActiveDurations.getValue(keyAt);
                    if (keyAt == sourceStateValueAt.mActiveProcState) {
                        value += j4;
                    }
                    byte idFromKey = SparseMappingTable.getIdFromKey(keyAt);
                    printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
                    DumpUtils.printArrayEntry(printWriter, DumpUtils.STATE_TAGS, idFromKey, 1);
                    printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
                    printWriter.print(value);
                }
            } else {
                long j5 = sourceStateValueAt.mActiveDuration + j4;
                if (j5 != 0) {
                    printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
                    DumpUtils.printArrayEntry(printWriter, DumpUtils.STATE_TAGS, sourceStateValueAt.mActiveProcState, 1);
                    printWriter.print(ShortcutConstants.SERVICES_SEPARATOR);
                    printWriter.print(j5);
                }
            }
            printWriter.println();
        }
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j, long j2) {
        int i;
        int i2;
        long j3;
        long j4;
        AssociationState associationState = this;
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1138166333441L, associationState.mName);
        protoOutputStream.write(1120986464259L, associationState.mTotalCount);
        protoOutputStream.write(1112396529668L, associationState.getTotalDuration(j2));
        int i3 = associationState.mTotalActiveCount;
        if (i3 != 0) {
            protoOutputStream.write(1120986464261L, i3);
            protoOutputStream.write(1112396529670L, associationState.getActiveDuration(j2));
        }
        int size = associationState.mSources.size();
        int i4 = 0;
        while (i4 < size) {
            SourceKey sourceKeyKeyAt = associationState.mSources.keyAt(i4);
            SourceState sourceStateValueAt = associationState.mSources.valueAt(i4);
            long jStart2 = protoOutputStream.start(2246267895810L);
            protoOutputStream.write(1138166333442L, sourceKeyKeyAt.mProcess);
            protoOutputStream.write(1138166333447L, sourceKeyKeyAt.mPackage);
            protoOutputStream.write(1120986464257L, sourceKeyKeyAt.mUid);
            protoOutputStream.write(1120986464259L, sourceStateValueAt.mCount);
            long j5 = sourceStateValueAt.mDuration;
            if (sourceStateValueAt.mNesting > 0) {
                j5 += j2 - sourceStateValueAt.mStartUptime;
            }
            protoOutputStream.write(1112396529668L, j5);
            if (sourceStateValueAt.mActiveCount != 0) {
                protoOutputStream.write(1120986464261L, sourceStateValueAt.mActiveCount);
            }
            long j6 = sourceStateValueAt.mActiveStartUptime != 0 ? j2 - sourceStateValueAt.mActiveStartUptime : 0L;
            if (sourceStateValueAt.mActiveDurations != null) {
                int keyCount = sourceStateValueAt.mActiveDurations.getKeyCount();
                long j7 = jStart2;
                int i5 = 0;
                while (i5 < keyCount) {
                    int keyAt = sourceStateValueAt.mActiveDurations.getKeyAt(i5);
                    long value = sourceStateValueAt.mActiveDurations.getValue(keyAt);
                    if (keyAt == sourceStateValueAt.mActiveProcState) {
                        value += j6;
                    }
                    byte idFromKey = SparseMappingTable.getIdFromKey(keyAt);
                    int i6 = i4;
                    long jStart3 = protoOutputStream.start(2246267895814L);
                    DumpUtils.printProto(protoOutputStream, 1159641169921L, DumpUtils.STATE_PROTO_ENUMS, idFromKey, 1);
                    protoOutputStream.write(1112396529666L, value);
                    protoOutputStream.end(jStart3);
                    i5++;
                    keyCount = keyCount;
                    i4 = i6;
                    size = size;
                    sourceStateValueAt = sourceStateValueAt;
                    j7 = j7;
                }
                i = i4;
                i2 = size;
                j3 = j7;
                j4 = 1120986464261L;
            } else {
                i = i4;
                i2 = size;
                j3 = jStart2;
                j4 = 1120986464261L;
                long j8 = j6 + sourceStateValueAt.mActiveDuration;
                if (j8 != 0) {
                    long jStart4 = protoOutputStream.start(2246267895814L);
                    DumpUtils.printProto(protoOutputStream, 1159641169921L, DumpUtils.STATE_PROTO_ENUMS, sourceStateValueAt.mActiveProcState, 1);
                    protoOutputStream.write(1112396529666L, j8);
                    protoOutputStream.end(jStart4);
                }
            }
            protoOutputStream.end(j3);
            i4 = i + 1;
            associationState = this;
            size = i2;
        }
        protoOutputStream.end(jStart);
    }

    public String toString() {
        return "AssociationState{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.mName + " pkg=" + this.mPackageState.mPackageName + " proc=" + Integer.toHexString(System.identityHashCode(this.mProc)) + "}";
    }
}
