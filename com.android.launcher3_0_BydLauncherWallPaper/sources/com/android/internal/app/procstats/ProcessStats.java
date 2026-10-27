package com.android.internal.app.procstats;

import android.content.ComponentName;
import android.os.Debug;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.SettingsStringUtil;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.DebugUtils;
import android.util.LongSparseArray;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;
import com.android.internal.app.ProcessMap;
import com.android.internal.app.procstats.AssociationState;
import com.android.internal.app.procstats.IProcessStats;
import com.android.internal.app.procstats.ProcessStats;
import com.android.internal.content.NativeLibraryHelper;
import dalvik.system.VMRuntime;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class ProcessStats implements Parcelable {
    public static final int ADD_PSS_EXTERNAL = 3;
    public static final int ADD_PSS_EXTERNAL_SLOW = 4;
    public static final int ADD_PSS_INTERNAL_ALL_MEM = 1;
    public static final int ADD_PSS_INTERNAL_ALL_POLL = 2;
    public static final int ADD_PSS_INTERNAL_SINGLE = 0;
    public static final int ADJ_COUNT = 8;
    public static final int ADJ_MEM_FACTOR_COUNT = 4;
    public static final int ADJ_MEM_FACTOR_CRITICAL = 3;
    public static final int ADJ_MEM_FACTOR_LOW = 2;
    public static final int ADJ_MEM_FACTOR_MODERATE = 1;
    public static final int ADJ_MEM_FACTOR_NORMAL = 0;
    public static final int ADJ_NOTHING = -1;
    public static final int ADJ_SCREEN_MOD = 4;
    public static final int ADJ_SCREEN_OFF = 0;
    public static final int ADJ_SCREEN_ON = 4;
    public static long COMMIT_PERIOD = 10800000;
    public static long COMMIT_UPTIME_PERIOD = 3600000;
    static final boolean DEBUG = false;
    static final boolean DEBUG_PARCEL = false;
    public static final int FLAG_COMPLETE = 1;
    public static final int FLAG_SHUTDOWN = 2;
    public static final int FLAG_SYSPROPS = 4;
    private static final long INVERSE_PROC_STATE_WARNING_MIN_INTERVAL_MS = 10000;
    private static final int MAGIC = 1347638356;
    private static final int PARCEL_VERSION = 38;
    public static final int PSS_AVERAGE = 2;
    public static final int PSS_COUNT = 10;
    public static final int PSS_MAXIMUM = 3;
    public static final int PSS_MINIMUM = 1;
    public static final int PSS_RSS_AVERAGE = 8;
    public static final int PSS_RSS_MAXIMUM = 9;
    public static final int PSS_RSS_MINIMUM = 7;
    public static final int PSS_SAMPLE_COUNT = 0;
    public static final int PSS_USS_AVERAGE = 5;
    public static final int PSS_USS_MAXIMUM = 6;
    public static final int PSS_USS_MINIMUM = 4;
    public static final int REPORT_ALL = 15;
    public static final int REPORT_PKG_ASC_STATS = 8;
    public static final int REPORT_PKG_PROC_STATS = 2;
    public static final int REPORT_PKG_STATS = 14;
    public static final int REPORT_PKG_SVC_STATS = 4;
    public static final int REPORT_PROC_STATS = 1;
    public static final String SERVICE_NAME = "procstats";
    public static final int STATE_BACKUP = 4;
    public static final int STATE_CACHED_ACTIVITY = 11;
    public static final int STATE_CACHED_ACTIVITY_CLIENT = 12;
    public static final int STATE_CACHED_EMPTY = 13;
    public static final int STATE_COUNT = 14;
    public static final int STATE_HEAVY_WEIGHT = 8;
    public static final int STATE_HOME = 9;
    public static final int STATE_IMPORTANT_BACKGROUND = 3;
    public static final int STATE_IMPORTANT_FOREGROUND = 2;
    public static final int STATE_LAST_ACTIVITY = 10;
    public static final int STATE_NOTHING = -1;
    public static final int STATE_PERSISTENT = 0;
    public static final int STATE_RECEIVER = 7;
    public static final int STATE_SERVICE = 5;
    public static final int STATE_SERVICE_RESTARTING = 6;
    public static final int STATE_TOP = 1;
    public static final int SYS_MEM_USAGE_CACHED_AVERAGE = 2;
    public static final int SYS_MEM_USAGE_CACHED_MAXIMUM = 3;
    public static final int SYS_MEM_USAGE_CACHED_MINIMUM = 1;
    public static final int SYS_MEM_USAGE_COUNT = 16;
    public static final int SYS_MEM_USAGE_FREE_AVERAGE = 5;
    public static final int SYS_MEM_USAGE_FREE_MAXIMUM = 6;
    public static final int SYS_MEM_USAGE_FREE_MINIMUM = 4;
    public static final int SYS_MEM_USAGE_KERNEL_AVERAGE = 11;
    public static final int SYS_MEM_USAGE_KERNEL_MAXIMUM = 12;
    public static final int SYS_MEM_USAGE_KERNEL_MINIMUM = 10;
    public static final int SYS_MEM_USAGE_NATIVE_AVERAGE = 14;
    public static final int SYS_MEM_USAGE_NATIVE_MAXIMUM = 15;
    public static final int SYS_MEM_USAGE_NATIVE_MINIMUM = 13;
    public static final int SYS_MEM_USAGE_SAMPLE_COUNT = 0;
    public static final int SYS_MEM_USAGE_ZRAM_AVERAGE = 8;
    public static final int SYS_MEM_USAGE_ZRAM_MAXIMUM = 9;
    public static final int SYS_MEM_USAGE_ZRAM_MINIMUM = 7;
    public static final String TAG = "ProcessStats";
    ArrayMap<String, Integer> mCommonStringToIndex;
    public long mExternalPssCount;
    public long mExternalPssTime;
    public long mExternalSlowPssCount;
    public long mExternalSlowPssTime;
    public int mFlags;
    boolean mHasSwappedOutPss;
    ArrayList<String> mIndexToCommonString;
    public long mInternalAllMemPssCount;
    public long mInternalAllMemPssTime;
    public long mInternalAllPollPssCount;
    public long mInternalAllPollPssTime;
    public long mInternalSinglePssCount;
    public long mInternalSinglePssTime;
    public int mMemFactor;
    public final long[] mMemFactorDurations;
    private long mNextInverseProcStateWarningUptime;
    public int mNumAggregated;
    public final ProcessMap<LongSparseArray<PackageState>> mPackages;
    private final ArrayList<String> mPageTypeLabels;
    private final ArrayList<Integer> mPageTypeNodes;
    private final ArrayList<int[]> mPageTypeSizes;
    private final ArrayList<String> mPageTypeZones;
    public final ProcessMap<ProcessState> mProcesses;
    public String mReadError;
    boolean mRunning;
    String mRuntime;
    private int mSkippedInverseProcStateWarningCount;
    public long mStartTime;
    public final SysMemUsageTable mSysMemUsage;
    public final long[] mSysMemUsageArgs;
    public final SparseMappingTable mTableData;
    public long mTimePeriodEndRealtime;
    public long mTimePeriodEndUptime;
    public long mTimePeriodStartClock;
    public String mTimePeriodStartClockStr;
    public long mTimePeriodStartRealtime;
    public long mTimePeriodStartUptime;
    public final ArrayList<AssociationState.SourceState> mTrackingAssociations;
    public static final int[] ALL_MEM_ADJ = {0, 1, 2, 3};
    public static final int[] ALL_SCREEN_ADJ = {0, 4};
    public static final int[] NON_CACHED_PROC_STATES = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public static final int[] BACKGROUND_PROC_STATES = {2, 3, 4, 8, 5, 6, 7};
    public static final int[] ALL_PROC_STATES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    public static final int[] OPTIONS = {1, 2, 4, 8, 14, 15};
    public static final String[] OPTIONS_STR = {"proc", "pkg-proc", "pkg-svc", "pkg-asc", "pkg-all", "all"};
    private static final Pattern sPageTypeRegex = Pattern.compile("^Node\\s+(\\d+),.* zone\\s+(\\w+),.* type\\s+(\\w+)\\s+([\\s\\d]+?)\\s*$");
    public static final Parcelable.Creator<ProcessStats> CREATOR = new Parcelable.Creator<ProcessStats>() { // from class: com.android.internal.app.procstats.ProcessStats.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProcessStats createFromParcel(Parcel parcel) {
            return new ProcessStats(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProcessStats[] newArray(int i) {
            return new ProcessStats[i];
        }
    };
    static final int[] BAD_TABLE = new int[0];
    static final Comparator<AssociationDumpContainer> ASSOCIATION_COMPARATOR = new Comparator() { // from class: com.android.internal.app.procstats.-$$Lambda$ProcessStats$My6SJeerwYHn44J8MTsAXl8rgpM
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ProcessStats.lambda$static$0((ProcessStats.AssociationDumpContainer) obj, (ProcessStats.AssociationDumpContainer) obj2);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProcessStats(boolean z) throws Throwable {
        this.mPackages = new ProcessMap<>();
        this.mProcesses = new ProcessMap<>();
        this.mTrackingAssociations = new ArrayList<>();
        this.mMemFactorDurations = new long[8];
        this.mMemFactor = -1;
        this.mNumAggregated = 1;
        SparseMappingTable sparseMappingTable = new SparseMappingTable();
        this.mTableData = sparseMappingTable;
        this.mSysMemUsageArgs = new long[16];
        this.mSysMemUsage = new SysMemUsageTable(sparseMappingTable);
        this.mPageTypeNodes = new ArrayList<>();
        this.mPageTypeZones = new ArrayList<>();
        this.mPageTypeLabels = new ArrayList<>();
        this.mPageTypeSizes = new ArrayList<>();
        this.mRunning = z;
        reset();
        if (z) {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(Process.myPid(), memoryInfo);
            this.mHasSwappedOutPss = memoryInfo.hasSwappedOutPss();
        }
    }

    public ProcessStats(Parcel parcel) throws Throwable {
        this.mPackages = new ProcessMap<>();
        this.mProcesses = new ProcessMap<>();
        this.mTrackingAssociations = new ArrayList<>();
        this.mMemFactorDurations = new long[8];
        this.mMemFactor = -1;
        this.mNumAggregated = 1;
        SparseMappingTable sparseMappingTable = new SparseMappingTable();
        this.mTableData = sparseMappingTable;
        this.mSysMemUsageArgs = new long[16];
        this.mSysMemUsage = new SysMemUsageTable(sparseMappingTable);
        this.mPageTypeNodes = new ArrayList<>();
        this.mPageTypeZones = new ArrayList<>();
        this.mPageTypeLabels = new ArrayList<>();
        this.mPageTypeSizes = new ArrayList<>();
        reset();
        readFromParcel(parcel);
    }

    public ProcessStats() {
        this(false);
    }

    public void add(ProcessStats processStats) {
        ArrayMap<String, SparseArray<ProcessState>> arrayMap;
        SparseArray<ProcessState> sparseArray;
        PackageState packageState;
        int i;
        SparseArray<LongSparseArray<PackageState>> sparseArray2;
        int i2;
        ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> arrayMap2;
        ProcessStats processStats2 = processStats;
        ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map = processStats2.mPackages.getMap();
        int i3 = 0;
        while (i3 < map.size()) {
            String strKeyAt = map.keyAt(i3);
            SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt = map.valueAt(i3);
            for (int i4 = 0; i4 < sparseArrayValueAt.size(); i4++) {
                int iKeyAt = sparseArrayValueAt.keyAt(i4);
                LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt.valueAt(i4);
                int i5 = 0;
                while (i5 < longSparseArrayValueAt.size()) {
                    long jKeyAt = longSparseArrayValueAt.keyAt(i5);
                    PackageState packageStateValueAt = longSparseArrayValueAt.valueAt(i5);
                    int size = packageStateValueAt.mProcesses.size();
                    int size2 = packageStateValueAt.mServices.size();
                    int size3 = packageStateValueAt.mAssociations.size();
                    LongSparseArray<PackageState> longSparseArray = longSparseArrayValueAt;
                    int i6 = 0;
                    while (i6 < size) {
                        int i7 = size2;
                        ProcessState processStateValueAt = packageStateValueAt.mProcesses.valueAt(i6);
                        int i8 = size;
                        if (processStateValueAt.getCommonProcess() != processStateValueAt) {
                            arrayMap2 = map;
                            sparseArray2 = sparseArrayValueAt;
                            i2 = i7;
                            packageState = packageStateValueAt;
                            long j = jKeyAt;
                            i = i5;
                            ProcessState processStateLocked = getProcessStateLocked(strKeyAt, iKeyAt, jKeyAt, processStateValueAt.getName());
                            if (processStateLocked.getCommonProcess() == processStateLocked) {
                                processStateLocked.setMultiPackage(true);
                                long jUptimeMillis = SystemClock.uptimeMillis();
                                jKeyAt = j;
                                PackageState packageStateLocked = getPackageStateLocked(strKeyAt, iKeyAt, jKeyAt);
                                processStateLocked = processStateLocked.clone(jUptimeMillis);
                                packageStateLocked.mProcesses.put(processStateLocked.getName(), processStateLocked);
                            } else {
                                jKeyAt = j;
                            }
                            processStateLocked.add(processStateValueAt);
                        } else {
                            packageState = packageStateValueAt;
                            i = i5;
                            sparseArray2 = sparseArrayValueAt;
                            i2 = i7;
                            arrayMap2 = map;
                        }
                        i6++;
                        packageStateValueAt = packageState;
                        size2 = i2;
                        map = arrayMap2;
                        size = i8;
                        sparseArrayValueAt = sparseArray2;
                        i5 = i;
                    }
                    PackageState packageState2 = packageStateValueAt;
                    int i9 = i5;
                    ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> arrayMap3 = map;
                    SparseArray<LongSparseArray<PackageState>> sparseArray3 = sparseArrayValueAt;
                    int i10 = 0;
                    for (int i11 = size2; i10 < i11; i11 = i11) {
                        ServiceState serviceStateValueAt = packageState2.mServices.valueAt(i10);
                        getServiceStateLocked(strKeyAt, iKeyAt, jKeyAt, serviceStateValueAt.getProcessName(), serviceStateValueAt.getName()).add(serviceStateValueAt);
                        i10++;
                    }
                    long j2 = jKeyAt;
                    for (int i12 = 0; i12 < size3; i12++) {
                        AssociationState associationStateValueAt = packageState2.mAssociations.valueAt(i12);
                        getAssociationStateLocked(strKeyAt, iKeyAt, j2, associationStateValueAt.getProcessName(), associationStateValueAt.getName()).add(associationStateValueAt);
                    }
                    i5 = i9 + 1;
                    longSparseArrayValueAt = longSparseArray;
                    map = arrayMap3;
                    sparseArrayValueAt = sparseArray3;
                }
            }
            i3++;
            processStats2 = processStats;
        }
        ArrayMap<String, SparseArray<ProcessState>> map2 = processStats2.mProcesses.getMap();
        for (int i13 = 0; i13 < map2.size(); i13++) {
            SparseArray<ProcessState> sparseArrayValueAt2 = map2.valueAt(i13);
            int i14 = 0;
            while (i14 < sparseArrayValueAt2.size()) {
                int iKeyAt2 = sparseArrayValueAt2.keyAt(i14);
                ProcessState processStateValueAt2 = sparseArrayValueAt2.valueAt(i14);
                String name = processStateValueAt2.getName();
                String str = processStateValueAt2.getPackage();
                long version = processStateValueAt2.getVersion();
                ProcessState processState = this.mProcesses.get(name, iKeyAt2);
                if (processState == null) {
                    arrayMap = map2;
                    sparseArray = sparseArrayValueAt2;
                    ProcessState processState2 = new ProcessState(this, str, iKeyAt2, version, name);
                    this.mProcesses.put(name, iKeyAt2, processState2);
                    PackageState packageStateLocked2 = getPackageStateLocked(str, iKeyAt2, version);
                    if (!packageStateLocked2.mProcesses.containsKey(name)) {
                        packageStateLocked2.mProcesses.put(name, processState2);
                    }
                    processState = processState2;
                } else {
                    arrayMap = map2;
                    sparseArray = sparseArrayValueAt2;
                }
                processState.add(processStateValueAt2);
                i14++;
                map2 = arrayMap;
                sparseArrayValueAt2 = sparseArray;
            }
        }
        for (int i15 = 0; i15 < 8; i15++) {
            long[] jArr = this.mMemFactorDurations;
            jArr[i15] = jArr[i15] + processStats2.mMemFactorDurations[i15];
        }
        this.mSysMemUsage.mergeStats(processStats2.mSysMemUsage);
        this.mNumAggregated += processStats2.mNumAggregated;
        long j3 = processStats2.mTimePeriodStartClock;
        if (j3 < this.mTimePeriodStartClock) {
            this.mTimePeriodStartClock = j3;
            this.mTimePeriodStartClockStr = processStats2.mTimePeriodStartClockStr;
        }
        this.mTimePeriodEndRealtime += processStats2.mTimePeriodEndRealtime - processStats2.mTimePeriodStartRealtime;
        this.mTimePeriodEndUptime += processStats2.mTimePeriodEndUptime - processStats2.mTimePeriodStartUptime;
        this.mInternalSinglePssCount += processStats2.mInternalSinglePssCount;
        this.mInternalSinglePssTime += processStats2.mInternalSinglePssTime;
        this.mInternalAllMemPssCount += processStats2.mInternalAllMemPssCount;
        this.mInternalAllMemPssTime += processStats2.mInternalAllMemPssTime;
        this.mInternalAllPollPssCount += processStats2.mInternalAllPollPssCount;
        this.mInternalAllPollPssTime += processStats2.mInternalAllPollPssTime;
        this.mExternalPssCount += processStats2.mExternalPssCount;
        this.mExternalPssTime += processStats2.mExternalPssTime;
        this.mExternalSlowPssCount += processStats2.mExternalSlowPssCount;
        this.mExternalSlowPssTime += processStats2.mExternalSlowPssTime;
        this.mHasSwappedOutPss |= processStats2.mHasSwappedOutPss;
    }

    public void addSysMemUsage(long j, long j2, long j3, long j4, long j5) {
        int i = this.mMemFactor;
        if (i != -1) {
            int i2 = i * 14;
            this.mSysMemUsageArgs[0] = 1;
            int i3 = 0;
            while (i3 < 3) {
                long[] jArr = this.mSysMemUsageArgs;
                int i4 = i3 + 1;
                jArr[i4] = j;
                jArr[i3 + 4] = j2;
                jArr[i3 + 7] = j3;
                jArr[i3 + 10] = j4;
                jArr[i3 + 13] = j5;
                i3 = i4;
            }
            this.mSysMemUsage.mergeStats(i2, this.mSysMemUsageArgs, 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void computeTotalMemoryUse(com.android.internal.app.procstats.ProcessStats.TotalMemoryUseCollection r13, long r14) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.procstats.ProcessStats.computeTotalMemoryUse(com.android.internal.app.procstats.ProcessStats$TotalMemoryUseCollection, long):void");
    }

    public void reset() throws Throwable {
        resetCommon();
        this.mPackages.getMap().clear();
        this.mProcesses.getMap().clear();
        this.mMemFactor = -1;
        this.mStartTime = 0L;
    }

    public void resetSafely() throws Throwable {
        resetCommon();
        long jUptimeMillis = SystemClock.uptimeMillis();
        ArrayMap<String, SparseArray<ProcessState>> map = this.mProcesses.getMap();
        for (int size = map.size() - 1; size >= 0; size--) {
            SparseArray<ProcessState> sparseArrayValueAt = map.valueAt(size);
            for (int size2 = sparseArrayValueAt.size() - 1; size2 >= 0; size2--) {
                sparseArrayValueAt.valueAt(size2).tmpNumInUse = 0;
            }
        }
        ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map2 = this.mPackages.getMap();
        for (int size3 = map2.size() - 1; size3 >= 0; size3--) {
            SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt2 = map2.valueAt(size3);
            for (int size4 = sparseArrayValueAt2.size() - 1; size4 >= 0; size4--) {
                LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt2.valueAt(size4);
                for (int size5 = longSparseArrayValueAt.size() - 1; size5 >= 0; size5--) {
                    PackageState packageStateValueAt = longSparseArrayValueAt.valueAt(size5);
                    for (int size6 = packageStateValueAt.mProcesses.size() - 1; size6 >= 0; size6--) {
                        ProcessState processStateValueAt = packageStateValueAt.mProcesses.valueAt(size6);
                        if (processStateValueAt.isInUse()) {
                            processStateValueAt.resetSafely(jUptimeMillis);
                            processStateValueAt.getCommonProcess().tmpNumInUse++;
                            processStateValueAt.getCommonProcess().tmpFoundSubProc = processStateValueAt;
                        } else {
                            packageStateValueAt.mProcesses.valueAt(size6).makeDead();
                            packageStateValueAt.mProcesses.removeAt(size6);
                        }
                    }
                    for (int size7 = packageStateValueAt.mServices.size() - 1; size7 >= 0; size7--) {
                        ServiceState serviceStateValueAt = packageStateValueAt.mServices.valueAt(size7);
                        if (serviceStateValueAt.isInUse()) {
                            serviceStateValueAt.resetSafely(jUptimeMillis);
                        } else {
                            packageStateValueAt.mServices.removeAt(size7);
                        }
                    }
                    for (int size8 = packageStateValueAt.mAssociations.size() - 1; size8 >= 0; size8--) {
                        AssociationState associationStateValueAt = packageStateValueAt.mAssociations.valueAt(size8);
                        if (associationStateValueAt.isInUse()) {
                            associationStateValueAt.resetSafely(jUptimeMillis);
                        } else {
                            packageStateValueAt.mAssociations.removeAt(size8);
                        }
                    }
                    if (packageStateValueAt.mProcesses.size() <= 0 && packageStateValueAt.mServices.size() <= 0 && packageStateValueAt.mAssociations.size() <= 0) {
                        longSparseArrayValueAt.removeAt(size5);
                    }
                }
                if (longSparseArrayValueAt.size() <= 0) {
                    sparseArrayValueAt2.removeAt(size4);
                }
            }
            if (sparseArrayValueAt2.size() <= 0) {
                map2.removeAt(size3);
            }
        }
        for (int size9 = map.size() - 1; size9 >= 0; size9--) {
            SparseArray<ProcessState> sparseArrayValueAt3 = map.valueAt(size9);
            for (int size10 = sparseArrayValueAt3.size() - 1; size10 >= 0; size10--) {
                ProcessState processStateValueAt2 = sparseArrayValueAt3.valueAt(size10);
                if (processStateValueAt2.isInUse() || processStateValueAt2.tmpNumInUse > 0) {
                    if (!processStateValueAt2.isActive() && processStateValueAt2.isMultiPackage() && processStateValueAt2.tmpNumInUse == 1) {
                        ProcessState processState = processStateValueAt2.tmpFoundSubProc;
                        processState.makeStandalone();
                        sparseArrayValueAt3.setValueAt(size10, processState);
                    } else {
                        processStateValueAt2.resetSafely(jUptimeMillis);
                    }
                } else {
                    processStateValueAt2.makeDead();
                    sparseArrayValueAt3.removeAt(size10);
                }
            }
            if (sparseArrayValueAt3.size() <= 0) {
                map.removeAt(size9);
            }
        }
        this.mStartTime = jUptimeMillis;
    }

    private void resetCommon() throws Throwable {
        this.mNumAggregated = 1;
        this.mTimePeriodStartClock = System.currentTimeMillis();
        buildTimePeriodStartClockStr();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.mTimePeriodEndRealtime = jElapsedRealtime;
        this.mTimePeriodStartRealtime = jElapsedRealtime;
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.mTimePeriodEndUptime = jUptimeMillis;
        this.mTimePeriodStartUptime = jUptimeMillis;
        this.mInternalSinglePssCount = 0L;
        this.mInternalSinglePssTime = 0L;
        this.mInternalAllMemPssCount = 0L;
        this.mInternalAllMemPssTime = 0L;
        this.mInternalAllPollPssCount = 0L;
        this.mInternalAllPollPssTime = 0L;
        this.mExternalPssCount = 0L;
        this.mExternalPssTime = 0L;
        this.mExternalSlowPssCount = 0L;
        this.mExternalSlowPssTime = 0L;
        this.mTableData.reset();
        Arrays.fill(this.mMemFactorDurations, 0L);
        this.mSysMemUsage.resetTable();
        this.mStartTime = 0L;
        this.mReadError = null;
        this.mFlags = 0;
        evaluateSystemProperties(true);
        updateFragmentation();
    }

    public boolean evaluateSystemProperties(boolean z) {
        String str = SystemProperties.get("persist.sys.dalvik.vm.lib.2", VMRuntime.getRuntime().vmLibrary());
        if (Objects.equals(str, this.mRuntime)) {
            return false;
        }
        if (!z) {
            return true;
        }
        this.mRuntime = str;
        return true;
    }

    private void buildTimePeriodStartClockStr() {
        this.mTimePeriodStartClockStr = DateFormat.format("yyyy-MM-dd-HH-mm-ss", this.mTimePeriodStartClock).toString();
    }

    public void updateFragmentation() throws Throwable {
        Integer numValueOf;
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/pagetypeinfo"));
                try {
                    Matcher matcher = sPageTypeRegex.matcher("");
                    this.mPageTypeNodes.clear();
                    this.mPageTypeZones.clear();
                    this.mPageTypeLabels.clear();
                    this.mPageTypeSizes.clear();
                    while (true) {
                        String line = bufferedReader2.readLine();
                        if (line != null) {
                            matcher.reset(line);
                            if (matcher.matches() && (numValueOf = Integer.valueOf(matcher.group(1), 10)) != null) {
                                this.mPageTypeNodes.add(numValueOf);
                                this.mPageTypeZones.add(matcher.group(2));
                                this.mPageTypeLabels.add(matcher.group(3));
                                this.mPageTypeSizes.add(splitAndParseNumbers(matcher.group(4)));
                            }
                        } else {
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (IOException unused) {
                                return;
                            }
                        }
                    }
                } catch (IOException unused2) {
                    bufferedReader = bufferedReader2;
                    this.mPageTypeNodes.clear();
                    this.mPageTypeZones.clear();
                    this.mPageTypeLabels.clear();
                    this.mPageTypeSizes.clear();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused5) {
        }
    }

    private static int[] splitAndParseNumbers(String str) {
        int length = str.length();
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < '0' || cCharAt > '9') {
                z = false;
            } else if (!z) {
                i++;
                z = true;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            char cCharAt2 = str.charAt(i5);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                if (z) {
                    iArr[i4] = i3;
                    i4++;
                    z = false;
                }
            } else if (z) {
                i3 = (i3 * 10) + (cCharAt2 - '0');
            } else {
                i3 = cCharAt2 - '0';
                z = true;
            }
        }
        if (i > 0) {
            iArr[i - 1] = i3;
        }
        return iArr;
    }

    private void writeCompactedLongArray(Parcel parcel, long[] jArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            long j = jArr[i2];
            if (j < 0) {
                Slog.w(TAG, "Time val negative: " + j);
                j = 0;
            }
            if (j <= 2147483647L) {
                parcel.writeInt((int) j);
            } else {
                parcel.writeInt(~((int) (2147483647L & (j >> 32))));
                parcel.writeInt((int) (j & 4294967295L));
            }
        }
    }

    private void readCompactedLongArray(Parcel parcel, int i, long[] jArr, int i2) {
        if (i <= 10) {
            parcel.readLongArray(jArr);
            return;
        }
        int length = jArr.length;
        if (i2 > length) {
            throw new RuntimeException("bad array lengths: got " + i2 + " array is " + length);
        }
        int i3 = 0;
        while (i3 < i2) {
            int i4 = parcel.readInt();
            if (i4 >= 0) {
                jArr[i3] = i4;
            } else {
                jArr[i3] = ((long) parcel.readInt()) | (((long) (~i4)) << 32);
            }
            i3++;
        }
        while (i3 < length) {
            jArr[i3] = 0;
            i3++;
        }
    }

    void writeCommonString(Parcel parcel, String str) {
        Integer num = this.mCommonStringToIndex.get(str);
        if (num != null) {
            parcel.writeInt(num.intValue());
            return;
        }
        Integer numValueOf = Integer.valueOf(this.mCommonStringToIndex.size());
        this.mCommonStringToIndex.put(str, numValueOf);
        parcel.writeInt(~numValueOf.intValue());
        parcel.writeString(str);
    }

    String readCommonString(Parcel parcel, int i) {
        if (i <= 9) {
            return parcel.readString();
        }
        int i2 = parcel.readInt();
        if (i2 >= 0) {
            return this.mIndexToCommonString.get(i2);
        }
        int i3 = ~i2;
        String string = parcel.readString();
        while (this.mIndexToCommonString.size() <= i3) {
            this.mIndexToCommonString.add(null);
        }
        this.mIndexToCommonString.set(i3, string);
        return string;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        writeToParcel(parcel, SystemClock.uptimeMillis(), i);
    }

    public void writeToParcel(Parcel parcel, long j, int i) {
        parcel.writeInt(MAGIC);
        parcel.writeInt(38);
        parcel.writeInt(14);
        parcel.writeInt(8);
        parcel.writeInt(10);
        parcel.writeInt(16);
        parcel.writeInt(4096);
        this.mCommonStringToIndex = new ArrayMap<>(this.mProcesses.size());
        ArrayMap<String, SparseArray<ProcessState>> map = this.mProcesses.getMap();
        int size = map.size();
        for (int i2 = 0; i2 < size; i2++) {
            SparseArray<ProcessState> sparseArrayValueAt = map.valueAt(i2);
            int size2 = sparseArrayValueAt.size();
            for (int i3 = 0; i3 < size2; i3++) {
                sparseArrayValueAt.valueAt(i3).commitStateTime(j);
            }
        }
        ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map2 = this.mPackages.getMap();
        int size3 = map2.size();
        for (int i4 = 0; i4 < size3; i4++) {
            SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt2 = map2.valueAt(i4);
            int size4 = sparseArrayValueAt2.size();
            for (int i5 = 0; i5 < size4; i5++) {
                LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt2.valueAt(i5);
                int size5 = longSparseArrayValueAt.size();
                int i6 = 0;
                while (i6 < size5) {
                    PackageState packageStateValueAt = longSparseArrayValueAt.valueAt(i6);
                    SparseArray<LongSparseArray<PackageState>> sparseArray = sparseArrayValueAt2;
                    int size6 = packageStateValueAt.mProcesses.size();
                    int i7 = size4;
                    int i8 = 0;
                    while (i8 < size6) {
                        int i9 = size6;
                        ProcessState processStateValueAt = packageStateValueAt.mProcesses.valueAt(i8);
                        LongSparseArray<PackageState> longSparseArray = longSparseArrayValueAt;
                        if (processStateValueAt.getCommonProcess() != processStateValueAt) {
                            processStateValueAt.commitStateTime(j);
                        }
                        i8++;
                        size6 = i9;
                        longSparseArrayValueAt = longSparseArray;
                    }
                    LongSparseArray<PackageState> longSparseArray2 = longSparseArrayValueAt;
                    int size7 = packageStateValueAt.mServices.size();
                    for (int i10 = 0; i10 < size7; i10++) {
                        packageStateValueAt.mServices.valueAt(i10).commitStateTime(j);
                    }
                    int size8 = packageStateValueAt.mAssociations.size();
                    for (int i11 = 0; i11 < size8; i11++) {
                        packageStateValueAt.mAssociations.valueAt(i11).commitStateTime(j);
                    }
                    i6++;
                    sparseArrayValueAt2 = sparseArray;
                    size4 = i7;
                    longSparseArrayValueAt = longSparseArray2;
                }
            }
        }
        parcel.writeInt(this.mNumAggregated);
        parcel.writeLong(this.mTimePeriodStartClock);
        parcel.writeLong(this.mTimePeriodStartRealtime);
        parcel.writeLong(this.mTimePeriodEndRealtime);
        parcel.writeLong(this.mTimePeriodStartUptime);
        parcel.writeLong(this.mTimePeriodEndUptime);
        parcel.writeLong(this.mInternalSinglePssCount);
        parcel.writeLong(this.mInternalSinglePssTime);
        parcel.writeLong(this.mInternalAllMemPssCount);
        parcel.writeLong(this.mInternalAllMemPssTime);
        parcel.writeLong(this.mInternalAllPollPssCount);
        parcel.writeLong(this.mInternalAllPollPssTime);
        parcel.writeLong(this.mExternalPssCount);
        parcel.writeLong(this.mExternalPssTime);
        parcel.writeLong(this.mExternalSlowPssCount);
        parcel.writeLong(this.mExternalSlowPssTime);
        parcel.writeString(this.mRuntime);
        parcel.writeInt(this.mHasSwappedOutPss ? 1 : 0);
        parcel.writeInt(this.mFlags);
        this.mTableData.writeToParcel(parcel);
        int i12 = this.mMemFactor;
        if (i12 != -1) {
            long[] jArr = this.mMemFactorDurations;
            jArr[i12] = jArr[i12] + (j - this.mStartTime);
            this.mStartTime = j;
        }
        long[] jArr2 = this.mMemFactorDurations;
        writeCompactedLongArray(parcel, jArr2, jArr2.length);
        this.mSysMemUsage.writeToParcel(parcel);
        parcel.writeInt(size);
        for (int i13 = 0; i13 < size; i13++) {
            writeCommonString(parcel, map.keyAt(i13));
            SparseArray<ProcessState> sparseArrayValueAt3 = map.valueAt(i13);
            int size9 = sparseArrayValueAt3.size();
            parcel.writeInt(size9);
            for (int i14 = 0; i14 < size9; i14++) {
                parcel.writeInt(sparseArrayValueAt3.keyAt(i14));
                ProcessState processStateValueAt2 = sparseArrayValueAt3.valueAt(i14);
                writeCommonString(parcel, processStateValueAt2.getPackage());
                parcel.writeLong(processStateValueAt2.getVersion());
                processStateValueAt2.writeToParcel(parcel, j);
            }
        }
        parcel.writeInt(size3);
        for (int i15 = 0; i15 < size3; i15++) {
            writeCommonString(parcel, map2.keyAt(i15));
            SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt4 = map2.valueAt(i15);
            int size10 = sparseArrayValueAt4.size();
            parcel.writeInt(size10);
            for (int i16 = 0; i16 < size10; i16++) {
                parcel.writeInt(sparseArrayValueAt4.keyAt(i16));
                LongSparseArray<PackageState> longSparseArrayValueAt2 = sparseArrayValueAt4.valueAt(i16);
                int size11 = longSparseArrayValueAt2.size();
                parcel.writeInt(size11);
                int i17 = 0;
                while (i17 < size11) {
                    parcel.writeLong(longSparseArrayValueAt2.keyAt(i17));
                    PackageState packageStateValueAt2 = longSparseArrayValueAt2.valueAt(i17);
                    int size12 = packageStateValueAt2.mProcesses.size();
                    parcel.writeInt(size12);
                    int i18 = 0;
                    while (i18 < size12) {
                        SparseArray<LongSparseArray<PackageState>> sparseArray2 = sparseArrayValueAt4;
                        writeCommonString(parcel, packageStateValueAt2.mProcesses.keyAt(i18));
                        ProcessState processStateValueAt3 = packageStateValueAt2.mProcesses.valueAt(i18);
                        int i19 = size10;
                        if (processStateValueAt3.getCommonProcess() == processStateValueAt3) {
                            parcel.writeInt(0);
                        } else {
                            parcel.writeInt(1);
                            processStateValueAt3.writeToParcel(parcel, j);
                        }
                        i18++;
                        sparseArrayValueAt4 = sparseArray2;
                        size10 = i19;
                    }
                    SparseArray<LongSparseArray<PackageState>> sparseArray3 = sparseArrayValueAt4;
                    int i20 = size10;
                    int size13 = packageStateValueAt2.mServices.size();
                    parcel.writeInt(size13);
                    for (int i21 = 0; i21 < size13; i21++) {
                        parcel.writeString(packageStateValueAt2.mServices.keyAt(i21));
                        ServiceState serviceStateValueAt = packageStateValueAt2.mServices.valueAt(i21);
                        writeCommonString(parcel, serviceStateValueAt.getProcessName());
                        serviceStateValueAt.writeToParcel(parcel, j);
                    }
                    int size14 = packageStateValueAt2.mAssociations.size();
                    parcel.writeInt(size14);
                    for (int i22 = 0; i22 < size14; i22++) {
                        writeCommonString(parcel, packageStateValueAt2.mAssociations.keyAt(i22));
                        AssociationState associationStateValueAt = packageStateValueAt2.mAssociations.valueAt(i22);
                        writeCommonString(parcel, associationStateValueAt.getProcessName());
                        associationStateValueAt.writeToParcel(this, parcel, j);
                    }
                    i17++;
                    sparseArrayValueAt4 = sparseArray3;
                    size10 = i20;
                }
            }
        }
        int size15 = this.mPageTypeLabels.size();
        parcel.writeInt(size15);
        for (int i23 = 0; i23 < size15; i23++) {
            parcel.writeInt(this.mPageTypeNodes.get(i23).intValue());
            parcel.writeString(this.mPageTypeZones.get(i23));
            parcel.writeString(this.mPageTypeLabels.get(i23));
            parcel.writeIntArray(this.mPageTypeSizes.get(i23));
        }
        this.mCommonStringToIndex = null;
    }

    private boolean readCheckedInt(Parcel parcel, int i, String str) {
        int i2 = parcel.readInt();
        if (i2 == i) {
            return true;
        }
        this.mReadError = "bad " + str + ": " + i2;
        return false;
    }

    static byte[] readFully(InputStream inputStream, int[] iArr) throws IOException {
        int iAvailable = inputStream.available();
        byte[] bArr = new byte[iAvailable > 0 ? iAvailable + 1 : 16384];
        int i = 0;
        while (true) {
            int i2 = inputStream.read(bArr, i, bArr.length - i);
            if (i2 < 0) {
                iArr[0] = i;
                return bArr;
            }
            i += i2;
            if (i >= bArr.length) {
                byte[] bArr2 = new byte[i + 16384];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
            }
        }
    }

    public void read(InputStream inputStream) throws Throwable {
        try {
            int[] iArr = new int[1];
            byte[] fully = readFully(inputStream, iArr);
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.unmarshall(fully, 0, iArr[0]);
            parcelObtain.setDataPosition(0);
            inputStream.close();
            readFromParcel(parcelObtain);
        } catch (IOException e) {
            this.mReadError = "caught exception: " + e;
        }
    }

    public void readFromParcel(Parcel parcel) throws Throwable {
        int i;
        String str;
        String str2;
        int i2;
        String str3;
        int i3;
        boolean z = false;
        boolean z2 = this.mPackages.getMap().size() > 0 || this.mProcesses.getMap().size() > 0;
        if (z2) {
            resetSafely();
        }
        if (!readCheckedInt(parcel, MAGIC, "magic number")) {
            return;
        }
        int i4 = parcel.readInt();
        if (i4 != 38) {
            this.mReadError = "bad version: " + i4;
            return;
        }
        if (!readCheckedInt(parcel, 14, "state count") || !readCheckedInt(parcel, 8, "adj count") || !readCheckedInt(parcel, 10, "pss count") || !readCheckedInt(parcel, 16, "sys mem usage count") || !readCheckedInt(parcel, 4096, "longs size")) {
            return;
        }
        this.mIndexToCommonString = new ArrayList<>();
        this.mNumAggregated = parcel.readInt();
        this.mTimePeriodStartClock = parcel.readLong();
        buildTimePeriodStartClockStr();
        this.mTimePeriodStartRealtime = parcel.readLong();
        this.mTimePeriodEndRealtime = parcel.readLong();
        this.mTimePeriodStartUptime = parcel.readLong();
        this.mTimePeriodEndUptime = parcel.readLong();
        this.mInternalSinglePssCount = parcel.readLong();
        this.mInternalSinglePssTime = parcel.readLong();
        this.mInternalAllMemPssCount = parcel.readLong();
        this.mInternalAllMemPssTime = parcel.readLong();
        this.mInternalAllPollPssCount = parcel.readLong();
        this.mInternalAllPollPssTime = parcel.readLong();
        this.mExternalPssCount = parcel.readLong();
        this.mExternalPssTime = parcel.readLong();
        this.mExternalSlowPssCount = parcel.readLong();
        this.mExternalSlowPssTime = parcel.readLong();
        this.mRuntime = parcel.readString();
        this.mHasSwappedOutPss = parcel.readInt() != 0;
        this.mFlags = parcel.readInt();
        this.mTableData.readFromParcel(parcel);
        long[] jArr = this.mMemFactorDurations;
        readCompactedLongArray(parcel, i4, jArr, jArr.length);
        if (!this.mSysMemUsage.readFromParcel(parcel)) {
            return;
        }
        int i5 = parcel.readInt();
        if (i5 < 0) {
            this.mReadError = "bad process count: " + i5;
            return;
        }
        while (true) {
            String str4 = "bad uid count: ";
            if (i5 > 0) {
                int i6 = i5 - 1;
                String commonString = readCommonString(parcel, i4);
                if (commonString == null) {
                    this.mReadError = "bad process name";
                    return;
                }
                int i7 = parcel.readInt();
                if (i7 < 0) {
                    this.mReadError = "bad uid count: " + i7;
                    return;
                }
                while (i7 > 0) {
                    int i8 = i7 - 1;
                    int i9 = parcel.readInt();
                    if (i9 < 0) {
                        this.mReadError = "bad uid: " + i9;
                        return;
                    }
                    String commonString2 = readCommonString(parcel, i4);
                    if (commonString2 == null) {
                        this.mReadError = "bad process package name";
                        return;
                    }
                    long j = parcel.readLong();
                    ProcessState processState = z2 ? this.mProcesses.get(commonString, i9) : null;
                    if (processState != null) {
                        if (!processState.readFromParcel(parcel, z)) {
                            return;
                        }
                        str3 = commonString;
                        i3 = i9;
                    } else {
                        str3 = commonString;
                        i3 = i9;
                        ProcessState processState2 = new ProcessState(this, commonString2, i9, j, str3);
                        if (!processState2.readFromParcel(parcel, true)) {
                            return;
                        } else {
                            processState = processState2;
                        }
                    }
                    String str5 = str3;
                    this.mProcesses.put(str5, i3, processState);
                    commonString = str5;
                    i7 = i8;
                    z = false;
                }
                i5 = i6;
            } else {
                int i10 = parcel.readInt();
                if (i10 < 0) {
                    this.mReadError = "bad package count: " + i10;
                    return;
                }
                while (i10 > 0) {
                    int i11 = i10 - 1;
                    String commonString3 = readCommonString(parcel, i4);
                    if (commonString3 == null) {
                        this.mReadError = "bad package name";
                        return;
                    }
                    int i12 = parcel.readInt();
                    if (i12 < 0) {
                        this.mReadError = str4 + i12;
                        return;
                    }
                    while (i12 > 0) {
                        int i13 = i12 - 1;
                        int i14 = parcel.readInt();
                        if (i14 < 0) {
                            this.mReadError = "bad uid: " + i14;
                            return;
                        }
                        int i15 = parcel.readInt();
                        if (i15 < 0) {
                            this.mReadError = "bad versions count: " + i15;
                            return;
                        }
                        while (i15 > 0) {
                            int i16 = i15 - 1;
                            long j2 = parcel.readLong();
                            int i17 = i14;
                            String str6 = str4;
                            PackageState packageState = new PackageState(this, commonString3, i14, j2);
                            LongSparseArray<PackageState> longSparseArray = this.mPackages.get(commonString3, i17);
                            if (longSparseArray == null) {
                                longSparseArray = new LongSparseArray<>();
                                this.mPackages.put(commonString3, i17, longSparseArray);
                            }
                            longSparseArray.put(j2, packageState);
                            int i18 = parcel.readInt();
                            if (i18 < 0) {
                                this.mReadError = "bad package process count: " + i18;
                                return;
                            }
                            while (i18 > 0) {
                                i18--;
                                String commonString4 = readCommonString(parcel, i4);
                                if (commonString4 == null) {
                                    this.mReadError = "bad package process name";
                                    return;
                                }
                                int i19 = parcel.readInt();
                                ProcessState processState3 = this.mProcesses.get(commonString4, i17);
                                if (processState3 == null) {
                                    this.mReadError = "no common proc: " + commonString4;
                                    return;
                                }
                                if (i19 != 0) {
                                    ProcessState processState4 = z2 ? packageState.mProcesses.get(commonString4) : null;
                                    if (processState4 != null) {
                                        i2 = i11;
                                        if (!processState4.readFromParcel(parcel, false)) {
                                            return;
                                        }
                                    } else {
                                        i2 = i11;
                                        processState4 = new ProcessState(processState3, commonString3, i17, j2, commonString4, 0L);
                                        if (!processState4.readFromParcel(parcel, true)) {
                                            return;
                                        }
                                    }
                                    packageState.mProcesses.put(commonString4, processState4);
                                } else {
                                    i2 = i11;
                                    packageState.mProcesses.put(commonString4, processState3);
                                }
                                i11 = i2;
                            }
                            int i20 = i11;
                            int i21 = parcel.readInt();
                            if (i21 < 0) {
                                this.mReadError = "bad package service count: " + i21;
                                return;
                            }
                            while (i21 > 0) {
                                int i22 = i21 - 1;
                                String string = parcel.readString();
                                if (string == null) {
                                    this.mReadError = "bad package service name";
                                    return;
                                }
                                String commonString5 = i4 > 9 ? readCommonString(parcel, i4) : null;
                                ServiceState serviceState = z2 ? packageState.mServices.get(string) : null;
                                if (serviceState == null) {
                                    str2 = string;
                                    serviceState = new ServiceState(this, commonString3, string, commonString5, null);
                                } else {
                                    str2 = string;
                                }
                                if (!serviceState.readFromParcel(parcel)) {
                                    return;
                                }
                                packageState.mServices.put(str2, serviceState);
                                i21 = i22;
                            }
                            int i23 = parcel.readInt();
                            if (i23 < 0) {
                                this.mReadError = "bad package association count: " + i23;
                                return;
                            }
                            while (i23 > 0) {
                                int i24 = i23 - 1;
                                String commonString6 = readCommonString(parcel, i4);
                                if (commonString6 == null) {
                                    this.mReadError = "bad package association name";
                                    return;
                                }
                                String commonString7 = readCommonString(parcel, i4);
                                AssociationState associationState = z2 ? packageState.mAssociations.get(commonString6) : null;
                                if (associationState == null) {
                                    i = i24;
                                    str = commonString6;
                                    associationState = new AssociationState(this, packageState, commonString6, commonString7, null);
                                } else {
                                    i = i24;
                                    str = commonString6;
                                }
                                String fromParcel = associationState.readFromParcel(this, parcel, i4);
                                if (fromParcel != null) {
                                    this.mReadError = fromParcel;
                                    return;
                                } else {
                                    packageState.mAssociations.put(str, associationState);
                                    i23 = i;
                                }
                            }
                            i14 = i17;
                            i15 = i16;
                            i11 = i20;
                            str4 = str6;
                        }
                        i12 = i13;
                    }
                    i10 = i11;
                }
                int i25 = parcel.readInt();
                this.mPageTypeNodes.clear();
                this.mPageTypeNodes.ensureCapacity(i25);
                this.mPageTypeZones.clear();
                this.mPageTypeZones.ensureCapacity(i25);
                this.mPageTypeLabels.clear();
                this.mPageTypeLabels.ensureCapacity(i25);
                this.mPageTypeSizes.clear();
                this.mPageTypeSizes.ensureCapacity(i25);
                for (int i26 = 0; i26 < i25; i26++) {
                    this.mPageTypeNodes.add(Integer.valueOf(parcel.readInt()));
                    this.mPageTypeZones.add(parcel.readString());
                    this.mPageTypeLabels.add(parcel.readString());
                    this.mPageTypeSizes.add(parcel.createIntArray());
                }
                this.mIndexToCommonString = null;
                return;
            }
        }
    }

    public PackageState getPackageStateLocked(String str, int i, long j) {
        LongSparseArray<PackageState> longSparseArray = this.mPackages.get(str, i);
        if (longSparseArray == null) {
            longSparseArray = new LongSparseArray<>();
            this.mPackages.put(str, i, longSparseArray);
        }
        PackageState packageState = longSparseArray.get(j);
        if (packageState != null) {
            return packageState;
        }
        PackageState packageState2 = new PackageState(this, str, i, j);
        longSparseArray.put(j, packageState2);
        return packageState2;
    }

    public ProcessState getProcessStateLocked(String str, int i, long j, String str2) {
        return getProcessStateLocked(getPackageStateLocked(str, i, j), str2);
    }

    public ProcessState getProcessStateLocked(PackageState packageState, String str) {
        ProcessState processState;
        ProcessState processState2 = packageState.mProcesses.get(str);
        if (processState2 != null) {
            return processState2;
        }
        ProcessState processState3 = this.mProcesses.get(str, packageState.mUid);
        if (processState3 == null) {
            processState3 = new ProcessState(this, packageState.mPackageName, packageState.mUid, packageState.mVersionCode, str);
            this.mProcesses.put(str, packageState.mUid, processState3);
        }
        ProcessState processState4 = processState3;
        if (!processState4.isMultiPackage()) {
            if (!packageState.mPackageName.equals(processState4.getPackage()) || packageState.mVersionCode != processState4.getVersion()) {
                processState4.setMultiPackage(true);
                long jUptimeMillis = SystemClock.uptimeMillis();
                PackageState packageStateLocked = getPackageStateLocked(processState4.getPackage(), packageState.mUid, processState4.getVersion());
                if (packageStateLocked != null) {
                    ProcessState processStateClone = processState4.clone(jUptimeMillis);
                    packageStateLocked.mProcesses.put(processState4.getName(), processStateClone);
                    for (int size = packageStateLocked.mServices.size() - 1; size >= 0; size--) {
                        ServiceState serviceStateValueAt = packageStateLocked.mServices.valueAt(size);
                        if (serviceStateValueAt.getProcess() == processState4) {
                            serviceStateValueAt.setProcess(processStateClone);
                        }
                    }
                    for (int size2 = packageStateLocked.mAssociations.size() - 1; size2 >= 0; size2--) {
                        AssociationState associationStateValueAt = packageStateLocked.mAssociations.valueAt(size2);
                        if (associationStateValueAt.getProcess() == processState4) {
                            associationStateValueAt.setProcess(processStateClone);
                        }
                    }
                } else {
                    Slog.w(TAG, "Cloning proc state: no package state " + processState4.getPackage() + "/" + packageState.mUid + " for proc " + processState4.getName());
                }
                processState = new ProcessState(processState4, packageState.mPackageName, packageState.mUid, packageState.mVersionCode, str, jUptimeMillis);
            }
            packageState.mProcesses.put(str, processState4);
            return processState4;
        }
        processState = new ProcessState(processState4, packageState.mPackageName, packageState.mUid, packageState.mVersionCode, str, SystemClock.uptimeMillis());
        processState4 = processState;
        packageState.mProcesses.put(str, processState4);
        return processState4;
    }

    public ServiceState getServiceStateLocked(String str, int i, long j, String str2, String str3) {
        PackageState packageStateLocked = getPackageStateLocked(str, i, j);
        ServiceState serviceState = packageStateLocked.mServices.get(str3);
        if (serviceState != null) {
            return serviceState;
        }
        ServiceState serviceState2 = new ServiceState(this, str, str3, str2, str2 != null ? getProcessStateLocked(str, i, j, str2) : null);
        packageStateLocked.mServices.put(str3, serviceState2);
        return serviceState2;
    }

    public AssociationState getAssociationStateLocked(String str, int i, long j, String str2, String str3) {
        PackageState packageStateLocked = getPackageStateLocked(str, i, j);
        AssociationState associationState = packageStateLocked.mAssociations.get(str3);
        if (associationState != null) {
            return associationState;
        }
        AssociationState associationState2 = new AssociationState(this, packageStateLocked, str3, str2, str2 != null ? getProcessStateLocked(str, i, j, str2) : null);
        packageStateLocked.mAssociations.put(str3, associationState2);
        return associationState2;
    }

    public void updateTrackingAssociationsLocked(int i, long j) {
        for (int size = this.mTrackingAssociations.size() - 1; size >= 0; size--) {
            AssociationState.SourceState sourceState = this.mTrackingAssociations.get(size);
            if (sourceState.mProcStateSeq != i || sourceState.mProcState >= 9) {
                sourceState.stopActive(j);
                sourceState.mInTrackingList = false;
                sourceState.mProcState = -1;
                this.mTrackingAssociations.remove(size);
            } else {
                ProcessState process = sourceState.getAssociationState().getProcess();
                if (process != null) {
                    int combinedState = process.getCombinedState() % 14;
                    if (sourceState.mProcState == combinedState) {
                        sourceState.startActive(j);
                    } else {
                        sourceState.stopActive(j);
                        if (sourceState.mProcState < combinedState) {
                            long jUptimeMillis = SystemClock.uptimeMillis();
                            if (this.mNextInverseProcStateWarningUptime > jUptimeMillis) {
                                this.mSkippedInverseProcStateWarningCount++;
                            } else {
                                Slog.w(TAG, "Tracking association " + sourceState + " whose proc state " + sourceState.mProcState + " is better than process " + process + " proc state " + combinedState + " (" + this.mSkippedInverseProcStateWarningCount + " skipped)");
                                this.mSkippedInverseProcStateWarningCount = 0;
                                this.mNextInverseProcStateWarningUptime = jUptimeMillis + 10000;
                            }
                        }
                    }
                } else {
                    Slog.wtf(TAG, "Tracking association without process: " + sourceState + " in " + sourceState.getAssociationState());
                }
            }
        }
    }

    final class AssociationDumpContainer {
        long mActiveTime;
        ArrayList<Pair<AssociationState.SourceKey, AssociationState.SourceDumpContainer>> mSources;
        final AssociationState mState;
        long mTotalTime;

        AssociationDumpContainer(AssociationState associationState) {
            this.mState = associationState;
        }
    }

    static /* synthetic */ int lambda$static$0(AssociationDumpContainer associationDumpContainer, AssociationDumpContainer associationDumpContainer2) {
        int iCompareTo = associationDumpContainer.mState.getProcessName().compareTo(associationDumpContainer2.mState.getProcessName());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (associationDumpContainer.mActiveTime != associationDumpContainer2.mActiveTime) {
            return associationDumpContainer.mActiveTime > associationDumpContainer2.mActiveTime ? -1 : 1;
        }
        if (associationDumpContainer.mTotalTime != associationDumpContainer2.mTotalTime) {
            return associationDumpContainer.mTotalTime > associationDumpContainer2.mTotalTime ? -1 : 1;
        }
        int iCompareTo2 = associationDumpContainer.mState.getName().compareTo(associationDumpContainer2.mState.getName());
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        return 0;
    }

    public void dumpLocked(PrintWriter printWriter, String str, long j, boolean z, boolean z2, boolean z3, boolean z4, int i) {
        boolean z5;
        ProcessStats processStats;
        PrintWriter printWriter2;
        String str2;
        SparseArray<ProcessState> sparseArray;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        int i2;
        boolean z6;
        boolean z7;
        int i3;
        String str8;
        boolean z8;
        String str9;
        int i4;
        String str10;
        String str11;
        int i5;
        int i6;
        String str12;
        String str13;
        String str14;
        String str15;
        long j2;
        String str16;
        String str17;
        String str18;
        String str19;
        ProcessStats processStats2;
        int i7;
        String str20;
        int i8;
        int i9;
        ArrayList arrayList;
        int i10;
        int i11;
        int i12;
        String str21;
        String str22;
        int i13;
        String str23;
        String str24;
        String str25;
        String str26;
        int i14;
        String str27;
        String str28;
        boolean z9;
        ProcessStats processStats3 = this;
        boolean z10 = z3;
        long jDumpSingleTime = DumpUtils.dumpSingleTime(null, null, processStats3.mMemFactorDurations, processStats3.mMemFactor, processStats3.mStartTime, j);
        printWriter.print("          Start time: ");
        printWriter.print(DateFormat.format("yyyy-MM-dd HH:mm:ss", processStats3.mTimePeriodStartClock));
        printWriter.println();
        printWriter.print("        Total uptime: ");
        TimeUtils.formatDuration((processStats3.mRunning ? SystemClock.uptimeMillis() : processStats3.mTimePeriodEndUptime) - processStats3.mTimePeriodStartUptime, printWriter);
        printWriter.println();
        printWriter.print("  Total elapsed time: ");
        TimeUtils.formatDuration((processStats3.mRunning ? SystemClock.elapsedRealtime() : processStats3.mTimePeriodEndRealtime) - processStats3.mTimePeriodStartRealtime, printWriter);
        if ((processStats3.mFlags & 2) != 0) {
            printWriter.print(" (shutdown)");
            z5 = false;
        } else {
            z5 = true;
        }
        if ((processStats3.mFlags & 4) != 0) {
            printWriter.print(" (sysprops)");
            z5 = false;
        }
        if ((processStats3.mFlags & 1) != 0) {
            printWriter.print(" (complete)");
            z5 = false;
        }
        if (z5) {
            printWriter.print(" (partial)");
        }
        if (processStats3.mHasSwappedOutPss) {
            printWriter.print(" (swapped-out-pss)");
        }
        printWriter.print(' ');
        printWriter.print(processStats3.mRuntime);
        printWriter.println();
        printWriter.print("     Aggregated over: ");
        printWriter.println(processStats3.mNumAggregated);
        if (processStats3.mSysMemUsage.getKeyCount() > 0) {
            printWriter.println();
            printWriter.println("System memory usage:");
            processStats3.mSysMemUsage.dump(printWriter, "  ", ALL_SCREEN_ADJ, ALL_MEM_ADJ);
        }
        int i15 = i & 14;
        String str29 = "      (Not active: ";
        String str30 = "        ";
        String str31 = " entries)";
        String str32 = " / ";
        String str33 = "  * ";
        String str34 = ")";
        String str35 = SettingsStringUtil.DELIMITER;
        if (i15 != 0) {
            ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map = processStats3.mPackages.getMap();
            int i16 = 0;
            boolean z11 = false;
            while (i16 < map.size()) {
                String strKeyAt = map.keyAt(i16);
                ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> arrayMap = map;
                SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt = map.valueAt(i16);
                int i17 = i16;
                String str36 = str34;
                int i18 = 0;
                while (i18 < sparseArrayValueAt.size()) {
                    int iKeyAt = sparseArrayValueAt.keyAt(i18);
                    SparseArray<LongSparseArray<PackageState>> sparseArray2 = sparseArrayValueAt;
                    LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt.valueAt(i18);
                    int i19 = i18;
                    String str37 = str31;
                    int i20 = 0;
                    while (i20 < longSparseArrayValueAt.size()) {
                        String str38 = str30;
                        long jKeyAt = longSparseArrayValueAt.keyAt(i20);
                        LongSparseArray<PackageState> longSparseArray = longSparseArrayValueAt;
                        PackageState packageStateValueAt = longSparseArrayValueAt.valueAt(i20);
                        String str39 = str29;
                        int size = packageStateValueAt.mProcesses.size();
                        long j3 = jDumpSingleTime;
                        int size2 = packageStateValueAt.mServices.size();
                        int size3 = packageStateValueAt.mAssociations.size();
                        boolean z12 = str == null || str.equals(strKeyAt);
                        int i21 = i20;
                        int i22 = 0;
                        if (z12) {
                            z7 = false;
                        } else {
                            while (true) {
                                if (i22 >= size) {
                                    z9 = false;
                                    break;
                                } else {
                                    if (str.equals(packageStateValueAt.mProcesses.valueAt(i22).getName())) {
                                        z9 = true;
                                        break;
                                    }
                                    i22++;
                                }
                            }
                            if (z9) {
                                z7 = z9;
                                i22 = 0;
                            } else {
                                int i23 = 0;
                                while (true) {
                                    z7 = z9;
                                    if (i23 >= size3) {
                                        i22 = 0;
                                        break;
                                    } else if (packageStateValueAt.mAssociations.valueAt(i23).hasProcessOrPackage(str)) {
                                        i22 = 1;
                                        break;
                                    } else {
                                        i23++;
                                        z9 = z7;
                                    }
                                }
                                if (i22 == 0) {
                                    str8 = strKeyAt;
                                    str9 = str35;
                                    i4 = iKeyAt;
                                    str10 = str33;
                                    str11 = str32;
                                    str18 = str36;
                                    str19 = str38;
                                    j2 = j3;
                                    processStats2 = this;
                                    i7 = i17;
                                    str16 = str39;
                                    str17 = str37;
                                }
                                str35 = str9;
                                str36 = str18;
                                i17 = i7;
                                str37 = str17;
                                str29 = str16;
                                iKeyAt = i4;
                                str32 = str11;
                                str33 = str10;
                                jDumpSingleTime = j2;
                                str30 = str19;
                                i20 = i21 + 1;
                                processStats3 = processStats2;
                                longSparseArrayValueAt = longSparseArray;
                                strKeyAt = str8;
                            }
                        }
                        if (size > 0 || size2 > 0 || size3 > 0) {
                            if (!z11) {
                                printWriter.println();
                                printWriter.println("Per-Package Stats:");
                                z11 = true;
                            }
                            printWriter.print(str33);
                            printWriter.print(strKeyAt);
                            printWriter.print(str32);
                            UserHandle.formatUid(printWriter, iKeyAt);
                            printWriter.print(" / v");
                            printWriter.print(jKeyAt);
                            printWriter.println(str35);
                        }
                        boolean z13 = z11;
                        if ((i & 2) == 0 || i22 != 0) {
                            i3 = i22;
                            str8 = strKeyAt;
                            z8 = z13;
                            str9 = str35;
                            i4 = iKeyAt;
                            str10 = str33;
                            str11 = str32;
                            i5 = size2;
                            i6 = size3;
                            str12 = str36;
                            str13 = str37;
                            str14 = str38;
                            str15 = str39;
                            j2 = j3;
                        } else {
                            if (!z || z10) {
                                i3 = i22;
                                str8 = strKeyAt;
                                z8 = z13;
                                str9 = str35;
                                i4 = iKeyAt;
                                str10 = str33;
                                str11 = str32;
                                i5 = size2;
                                i6 = size3;
                                str26 = str36;
                                str13 = str37;
                                str15 = str39;
                                j2 = j3;
                                int i24 = 0;
                                while (i24 < size) {
                                    ProcessState processStateValueAt = packageStateValueAt.mProcesses.valueAt(i24);
                                    if (!z12 && !str.equals(processStateValueAt.getName())) {
                                        str27 = str26;
                                        i14 = size;
                                        str28 = str38;
                                    } else if (z4 && !processStateValueAt.isInUse()) {
                                        printWriter.print(str15);
                                        printWriter.print(packageStateValueAt.mProcesses.keyAt(i24));
                                        printWriter.println(str26);
                                        str27 = str26;
                                        i14 = size;
                                        str28 = str38;
                                    } else {
                                        printWriter.print("      Process ");
                                        printWriter.print(packageStateValueAt.mProcesses.keyAt(i24));
                                        if (processStateValueAt.getCommonProcess().isMultiPackage()) {
                                            printWriter.print(" (multi, ");
                                        } else {
                                            printWriter.print(" (unique, ");
                                        }
                                        printWriter.print(processStateValueAt.getDurationsBucketCount());
                                        printWriter.print(str13);
                                        printWriter.println(str9);
                                        int[] iArr = ALL_SCREEN_ADJ;
                                        int[] iArr2 = ALL_MEM_ADJ;
                                        int[] iArr3 = ALL_PROC_STATES;
                                        i14 = size;
                                        processStateValueAt.dumpProcessState(printWriter, "        ", iArr, iArr2, iArr3, j);
                                        processStateValueAt.dumpPss(printWriter, "        ", iArr, iArr2, iArr3, j);
                                        str27 = str26;
                                        str28 = str38;
                                        processStateValueAt.dumpInternalLocked(printWriter, str28, z3);
                                    }
                                    i24++;
                                    str26 = str27;
                                    str38 = str28;
                                    size = i14;
                                }
                            } else {
                                ArrayList arrayList2 = new ArrayList();
                                int i25 = 0;
                                while (i25 < size) {
                                    String str40 = strKeyAt;
                                    ProcessState processStateValueAt2 = packageStateValueAt.mProcesses.valueAt(i25);
                                    boolean z14 = z13;
                                    if ((z12 || str.equals(processStateValueAt2.getName())) && (!z4 || processStateValueAt2.isInUse())) {
                                        arrayList2.add(processStateValueAt2);
                                    }
                                    i25++;
                                    strKeyAt = str40;
                                    z13 = z14;
                                }
                                str8 = strKeyAt;
                                z8 = z13;
                                i3 = i22;
                                str9 = str35;
                                i4 = iKeyAt;
                                str26 = str36;
                                str11 = str32;
                                str10 = str33;
                                str13 = str37;
                                str15 = str39;
                                i5 = size2;
                                i6 = size3;
                                j2 = j3;
                                DumpUtils.dumpProcessSummaryLocked(printWriter, "      ", "Prc ", arrayList2, ALL_SCREEN_ADJ, ALL_MEM_ADJ, NON_CACHED_PROC_STATES, j, j2);
                            }
                            str12 = str26;
                            str14 = str38;
                            z10 = z3;
                        }
                        String str41 = "        Process: ";
                        if ((i & 4) != 0 && i3 == 0) {
                            int i26 = 0;
                            int i27 = i5;
                            while (i26 < i27) {
                                ServiceState serviceStateValueAt = packageStateValueAt.mServices.valueAt(i26);
                                if (!z12 && !str.equals(serviceStateValueAt.getProcessName())) {
                                    str23 = str15;
                                    str21 = str13;
                                    i12 = i26;
                                    str22 = str12;
                                    i13 = i27;
                                    str24 = str14;
                                    str25 = str41;
                                } else if (z4 && !serviceStateValueAt.isInUse()) {
                                    printWriter.print("      (Not active service: ");
                                    printWriter.print(packageStateValueAt.mServices.keyAt(i26));
                                    printWriter.println(str12);
                                    str23 = str15;
                                    str21 = str13;
                                    i12 = i26;
                                    str22 = str12;
                                    i13 = i27;
                                    str24 = str14;
                                    str25 = str41;
                                } else {
                                    if (z10) {
                                        printWriter.print("      Service ");
                                    } else {
                                        printWriter.print("      * Svc ");
                                    }
                                    printWriter.print(packageStateValueAt.mServices.keyAt(i26));
                                    printWriter.println(str9);
                                    printWriter.print(str41);
                                    printWriter.println(serviceStateValueAt.getProcessName());
                                    i12 = i26;
                                    str21 = str13;
                                    str22 = str12;
                                    i13 = i27;
                                    str23 = str15;
                                    str24 = str14;
                                    str25 = str41;
                                    serviceStateValueAt.dumpStats(printWriter, "        ", "          ", "    ", j, j2, z, z3);
                                }
                                i26 = i12 + 1;
                                str41 = str25;
                                str12 = str22;
                                i27 = i13;
                                str13 = str21;
                                str15 = str23;
                                str14 = str24;
                            }
                        }
                        str16 = str15;
                        str17 = str13;
                        str18 = str12;
                        str19 = str14;
                        String str42 = str41;
                        if ((i & 8) != 0) {
                            int i28 = i6;
                            ArrayList arrayList3 = new ArrayList(i28);
                            int i29 = 0;
                            while (i29 < i28) {
                                AssociationState associationStateValueAt = packageStateValueAt.mAssociations.valueAt(i29);
                                if (z12 || str.equals(associationStateValueAt.getProcessName()) || (i3 != 0 && associationStateValueAt.hasProcessOrPackage(str))) {
                                    AssociationDumpContainer associationDumpContainer = new AssociationDumpContainer(associationStateValueAt);
                                    i10 = i28;
                                    i11 = i17;
                                    associationDumpContainer.mSources = associationStateValueAt.createSortedAssociations(j, j2);
                                    associationDumpContainer.mTotalTime = associationStateValueAt.getTotalDuration(j);
                                    associationDumpContainer.mActiveTime = associationStateValueAt.getActiveDuration(j);
                                    arrayList3.add(associationDumpContainer);
                                } else {
                                    i10 = i28;
                                    i11 = i17;
                                }
                                i29++;
                                i17 = i11;
                                i28 = i10;
                            }
                            i7 = i17;
                            Collections.sort(arrayList3, ASSOCIATION_COMPARATOR);
                            int size4 = arrayList3.size();
                            int i30 = 0;
                            while (i30 < size4) {
                                AssociationDumpContainer associationDumpContainer2 = (AssociationDumpContainer) arrayList3.get(i30);
                                AssociationState associationState = associationDumpContainer2.mState;
                                if (z4 && !associationState.isInUse()) {
                                    printWriter.print("      (Not active association: ");
                                    printWriter.print(packageStateValueAt.mAssociations.keyAt(i30));
                                    printWriter.println(str18);
                                    str20 = str42;
                                    i8 = size4;
                                    i9 = i30;
                                    arrayList = arrayList3;
                                } else {
                                    if (z10) {
                                        printWriter.print("      Association ");
                                    } else {
                                        printWriter.print("      * Asc ");
                                    }
                                    printWriter.print(associationDumpContainer2.mState.getName());
                                    printWriter.println(str9);
                                    printWriter.print(str42);
                                    printWriter.println(associationState.getProcessName());
                                    str20 = str42;
                                    i8 = size4;
                                    i9 = i30;
                                    arrayList = arrayList3;
                                    associationState.dumpStats(printWriter, "        ", "          ", "    ", associationDumpContainer2.mSources, j, j2, (i3 == 0 || z12 || z7 || associationState.getProcessName().equals(str)) ? null : str, z2, z3);
                                }
                                i30 = i9 + 1;
                                str42 = str20;
                                size4 = i8;
                                arrayList3 = arrayList;
                            }
                            processStats2 = this;
                        } else {
                            processStats2 = this;
                            i7 = i17;
                        }
                        z11 = z8;
                        str35 = str9;
                        str36 = str18;
                        i17 = i7;
                        str37 = str17;
                        str29 = str16;
                        iKeyAt = i4;
                        str32 = str11;
                        str33 = str10;
                        jDumpSingleTime = j2;
                        str30 = str19;
                        i20 = i21 + 1;
                        processStats3 = processStats2;
                        longSparseArrayValueAt = longSparseArray;
                        strKeyAt = str8;
                    }
                    str31 = str37;
                    i18 = i19 + 1;
                    processStats3 = processStats3;
                    sparseArrayValueAt = sparseArray2;
                    strKeyAt = strKeyAt;
                }
                str34 = str36;
                i16 = i17 + 1;
                processStats3 = processStats3;
                map = arrayMap;
            }
        }
        ProcessStats processStats4 = processStats3;
        String str43 = str35;
        String str44 = str34;
        String str45 = str33;
        String str46 = str32;
        String str47 = str31;
        String str48 = str30;
        String str49 = str29;
        long j4 = jDumpSingleTime;
        if ((i & 1) != 0) {
            ArrayMap<String, SparseArray<ProcessState>> map2 = processStats4.mProcesses.getMap();
            int i31 = 0;
            int i32 = 0;
            boolean z15 = false;
            int i33 = 0;
            while (i33 < map2.size()) {
                String strKeyAt2 = map2.keyAt(i33);
                SparseArray<ProcessState> sparseArrayValueAt2 = map2.valueAt(i33);
                int i34 = 0;
                while (i34 < sparseArrayValueAt2.size()) {
                    int iKeyAt2 = sparseArrayValueAt2.keyAt(i34);
                    int i35 = i32 + 1;
                    ProcessState processStateValueAt3 = sparseArrayValueAt2.valueAt(i34);
                    if (processStateValueAt3.hasAnyData() && processStateValueAt3.isMultiPackage() && (str == null || str.equals(strKeyAt2) || str.equals(processStateValueAt3.getPackage()))) {
                        int i36 = i31 + 1;
                        printWriter.println();
                        if (z15) {
                            z6 = z15;
                        } else {
                            printWriter.println("Multi-Package Common Processes:");
                            z6 = true;
                        }
                        if (z4 && !processStateValueAt3.isInUse()) {
                            String str50 = str49;
                            printWriter.print(str50);
                            printWriter.print(strKeyAt2);
                            printWriter.println(str44);
                            str4 = str50;
                            i2 = i34;
                            str2 = strKeyAt2;
                            sparseArray = sparseArrayValueAt2;
                            str3 = str47;
                            str5 = str46;
                            str6 = str45;
                            str7 = str48;
                        } else {
                            String str51 = str45;
                            printWriter.print(str51);
                            printWriter.print(strKeyAt2);
                            String str52 = str46;
                            printWriter.print(str52);
                            UserHandle.formatUid(printWriter, iKeyAt2);
                            printWriter.print(" (");
                            printWriter.print(processStateValueAt3.getDurationsBucketCount());
                            String str53 = str47;
                            printWriter.print(str53);
                            printWriter.println(str43);
                            int[] iArr4 = ALL_SCREEN_ADJ;
                            int[] iArr5 = ALL_MEM_ADJ;
                            int[] iArr6 = ALL_PROC_STATES;
                            str5 = str52;
                            str6 = str51;
                            str4 = str49;
                            str3 = str53;
                            i2 = i34;
                            str2 = strKeyAt2;
                            sparseArray = sparseArrayValueAt2;
                            processStateValueAt3.dumpProcessState(printWriter, "        ", iArr4, iArr5, iArr6, j);
                            processStateValueAt3.dumpPss(printWriter, "        ", iArr4, iArr5, iArr6, j);
                            str7 = str48;
                            processStateValueAt3.dumpInternalLocked(printWriter, str7, z10);
                        }
                        i31 = i36;
                        z15 = z6;
                    } else {
                        str2 = strKeyAt2;
                        sparseArray = sparseArrayValueAt2;
                        str3 = str47;
                        str4 = str49;
                        str5 = str46;
                        str6 = str45;
                        str7 = str48;
                        i2 = i34;
                    }
                    i34 = i2 + 1;
                    str48 = str7;
                    i32 = i35;
                    str46 = str5;
                    str45 = str6;
                    str49 = str4;
                    strKeyAt2 = str2;
                    sparseArrayValueAt2 = sparseArray;
                    str47 = str3;
                }
                i33++;
                str47 = str47;
            }
            printWriter.print("  Total procs: ");
            printWriter.print(i31);
            printWriter.print(" shown of ");
            printWriter.print(i32);
            printWriter.println(" total");
        }
        if (z10) {
            printWriter.println();
            if (processStats4.mTrackingAssociations.size() > 0) {
                printWriter.println();
                printWriter.println("Tracking associations:");
                for (int i37 = 0; i37 < processStats4.mTrackingAssociations.size(); i37++) {
                    AssociationState.SourceState sourceState = processStats4.mTrackingAssociations.get(i37);
                    AssociationState associationState2 = sourceState.getAssociationState();
                    printWriter.print("  #");
                    printWriter.print(i37);
                    printWriter.print(": ");
                    printWriter.print(associationState2.getProcessName());
                    printWriter.print("/");
                    UserHandle.formatUid(printWriter, associationState2.getUid());
                    printWriter.print(" <- ");
                    printWriter.print(sourceState.getProcessName());
                    printWriter.print("/");
                    UserHandle.formatUid(printWriter, sourceState.getUid());
                    printWriter.println(str43);
                    printWriter.print("    Tracking for: ");
                    TimeUtils.formatDuration(j - sourceState.mTrackingUptime, printWriter);
                    printWriter.println();
                    printWriter.print("    Component: ");
                    printWriter.print(new ComponentName(associationState2.getPackage(), associationState2.getName()).flattenToShortString());
                    printWriter.println();
                    printWriter.print("    Proc state: ");
                    if (sourceState.mProcState != -1) {
                        printWriter.print(DumpUtils.STATE_NAMES[sourceState.mProcState]);
                    } else {
                        printWriter.print("--");
                    }
                    printWriter.print(" #");
                    printWriter.println(sourceState.mProcStateSeq);
                    printWriter.print("    Process: ");
                    printWriter.println(associationState2.getProcess());
                    if (sourceState.mActiveCount > 0) {
                        printWriter.print("    Active count ");
                        printWriter.print(sourceState.mActiveCount);
                        printWriter.print(": ");
                        associationState2.dumpActiveDurationSummary(printWriter, sourceState, j4, j, z3);
                        printWriter.println();
                    }
                }
            }
        }
        printWriter.println();
        if (z) {
            printWriter.println("Process summary:");
            processStats = processStats4;
            printWriter2 = printWriter;
            dumpSummaryLocked(printWriter, str, j, z4);
        } else {
            processStats = processStats4;
            printWriter2 = printWriter;
            processStats.dumpTotalsLocked(printWriter2, j);
        }
        if (z3) {
            printWriter.println();
            printWriter2.println("Internal state:");
            printWriter2.print("  mRunning=");
            printWriter2.println(processStats.mRunning);
        }
        if (str == null) {
            dumpFragmentationLocked(printWriter);
        }
    }

    public void dumpSummaryLocked(PrintWriter printWriter, String str, long j, boolean z) {
        dumpFilteredSummaryLocked(printWriter, null, "  ", null, ALL_SCREEN_ADJ, ALL_MEM_ADJ, ALL_PROC_STATES, NON_CACHED_PROC_STATES, j, DumpUtils.dumpSingleTime(null, null, this.mMemFactorDurations, this.mMemFactor, this.mStartTime, j), str, z);
        printWriter.println();
        dumpTotalsLocked(printWriter, j);
    }

    private void dumpFragmentationLocked(PrintWriter printWriter) {
        printWriter.println();
        printWriter.println("Available pages by page size:");
        int size = this.mPageTypeLabels.size();
        for (int i = 0; i < size; i++) {
            printWriter.format("Node %3d Zone %7s  %14s ", this.mPageTypeNodes.get(i), this.mPageTypeZones.get(i), this.mPageTypeLabels.get(i));
            int[] iArr = this.mPageTypeSizes.get(i);
            int length = iArr == null ? 0 : iArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                printWriter.format("%6d", Integer.valueOf(iArr[i2]));
            }
            printWriter.println();
        }
    }

    long printMemoryCategory(PrintWriter printWriter, String str, String str2, double d, long j, long j2, int i) {
        if (d == 0.0d) {
            return j2;
        }
        long j3 = (long) ((d * 1024.0d) / j);
        printWriter.print(str);
        printWriter.print(str2);
        printWriter.print(": ");
        DebugUtils.printSizeValue(printWriter, j3);
        printWriter.print(" (");
        printWriter.print(i);
        printWriter.print(" samples)");
        printWriter.println();
        return j2 + j3;
    }

    void dumpTotalsLocked(PrintWriter printWriter, long j) {
        printWriter.println("Run time Stats:");
        DumpUtils.dumpSingleTime(printWriter, "  ", this.mMemFactorDurations, this.mMemFactor, this.mStartTime, j);
        printWriter.println();
        printWriter.println("Memory usage:");
        TotalMemoryUseCollection totalMemoryUseCollection = new TotalMemoryUseCollection(ALL_SCREEN_ADJ, ALL_MEM_ADJ);
        computeTotalMemoryUse(totalMemoryUseCollection, j);
        long jPrintMemoryCategory = printMemoryCategory(printWriter, "  ", "Native ", totalMemoryUseCollection.sysMemNativeWeight, totalMemoryUseCollection.totalTime, printMemoryCategory(printWriter, "  ", "Kernel ", totalMemoryUseCollection.sysMemKernelWeight, totalMemoryUseCollection.totalTime, 0L, totalMemoryUseCollection.sysMemSamples), totalMemoryUseCollection.sysMemSamples);
        for (int i = 0; i < 14; i++) {
            if (i != 6) {
                jPrintMemoryCategory = printMemoryCategory(printWriter, "  ", DumpUtils.STATE_NAMES[i], totalMemoryUseCollection.processStateWeight[i], totalMemoryUseCollection.totalTime, jPrintMemoryCategory, totalMemoryUseCollection.processStateSamples[i]);
            }
        }
        long jPrintMemoryCategory2 = printMemoryCategory(printWriter, "  ", "Z-Ram  ", totalMemoryUseCollection.sysMemZRamWeight, totalMemoryUseCollection.totalTime, printMemoryCategory(printWriter, "  ", "Free   ", totalMemoryUseCollection.sysMemFreeWeight, totalMemoryUseCollection.totalTime, printMemoryCategory(printWriter, "  ", "Cached ", totalMemoryUseCollection.sysMemCachedWeight, totalMemoryUseCollection.totalTime, jPrintMemoryCategory, totalMemoryUseCollection.sysMemSamples), totalMemoryUseCollection.sysMemSamples), totalMemoryUseCollection.sysMemSamples);
        printWriter.print("  TOTAL  : ");
        DebugUtils.printSizeValue(printWriter, jPrintMemoryCategory2);
        printWriter.println();
        printMemoryCategory(printWriter, "  ", DumpUtils.STATE_NAMES[6], totalMemoryUseCollection.processStateWeight[6], totalMemoryUseCollection.totalTime, jPrintMemoryCategory2, totalMemoryUseCollection.processStateSamples[6]);
        printWriter.println();
        printWriter.println("PSS collection stats:");
        printWriter.print("  Internal Single: ");
        printWriter.print(this.mInternalSinglePssCount);
        printWriter.print("x over ");
        TimeUtils.formatDuration(this.mInternalSinglePssTime, printWriter);
        printWriter.println();
        printWriter.print("  Internal All Procs (Memory Change): ");
        printWriter.print(this.mInternalAllMemPssCount);
        printWriter.print("x over ");
        TimeUtils.formatDuration(this.mInternalAllMemPssTime, printWriter);
        printWriter.println();
        printWriter.print("  Internal All Procs (Polling): ");
        printWriter.print(this.mInternalAllPollPssCount);
        printWriter.print("x over ");
        TimeUtils.formatDuration(this.mInternalAllPollPssTime, printWriter);
        printWriter.println();
        printWriter.print("  External: ");
        printWriter.print(this.mExternalPssCount);
        printWriter.print("x over ");
        TimeUtils.formatDuration(this.mExternalPssTime, printWriter);
        printWriter.println();
        printWriter.print("  External Slow: ");
        printWriter.print(this.mExternalSlowPssCount);
        printWriter.print("x over ");
        TimeUtils.formatDuration(this.mExternalSlowPssTime, printWriter);
        printWriter.println();
    }

    void dumpFilteredSummaryLocked(PrintWriter printWriter, String str, String str2, String str3, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, long j, long j2, String str4, boolean z) {
        ArrayList<ProcessState> arrayListCollectProcessesLocked = collectProcessesLocked(iArr, iArr2, iArr3, iArr4, j, str4, z);
        if (arrayListCollectProcessesLocked.size() > 0) {
            if (str != null) {
                printWriter.println();
                printWriter.println(str);
            }
            DumpUtils.dumpProcessSummaryLocked(printWriter, str2, str3, arrayListCollectProcessesLocked, iArr, iArr2, iArr4, j, j2);
        }
    }

    public ArrayList<ProcessState> collectProcessesLocked(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, long j, String str, boolean z) {
        ArraySet arraySet = new ArraySet();
        ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map = this.mPackages.getMap();
        for (int i = 0; i < map.size(); i++) {
            String strKeyAt = map.keyAt(i);
            SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt = map.valueAt(i);
            for (int i2 = 0; i2 < sparseArrayValueAt.size(); i2++) {
                LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt.valueAt(i2);
                int size = longSparseArrayValueAt.size();
                for (int i3 = 0; i3 < size; i3++) {
                    PackageState packageStateValueAt = longSparseArrayValueAt.valueAt(i3);
                    int size2 = packageStateValueAt.mProcesses.size();
                    boolean z2 = str == null || str.equals(strKeyAt);
                    for (int i4 = 0; i4 < size2; i4++) {
                        ProcessState processStateValueAt = packageStateValueAt.mProcesses.valueAt(i4);
                        if ((z2 || str.equals(processStateValueAt.getName())) && (!z || processStateValueAt.isInUse())) {
                            arraySet.add(processStateValueAt.getCommonProcess());
                        }
                    }
                }
            }
        }
        ArrayList<ProcessState> arrayList = new ArrayList<>(arraySet.size());
        for (int i5 = 0; i5 < arraySet.size(); i5++) {
            ProcessState processState = (ProcessState) arraySet.valueAt(i5);
            if (processState.computeProcessTimeLocked(iArr, iArr2, iArr3, j) > 0) {
                arrayList.add(processState);
                if (iArr3 != iArr4) {
                    processState.computeProcessTimeLocked(iArr, iArr2, iArr4, j);
                }
            }
        }
        Collections.sort(arrayList, ProcessState.COMPARATOR);
        return arrayList;
    }

    public void dumpCheckinLocked(PrintWriter printWriter, String str, int i) {
        boolean z;
        String str2 = str;
        long jUptimeMillis = SystemClock.uptimeMillis();
        ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map = this.mPackages.getMap();
        printWriter.println("vers,5");
        printWriter.print("period,");
        printWriter.print(this.mTimePeriodStartClockStr);
        String str3 = SmsManager.REGEX_PREFIX_DELIMITER;
        printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
        printWriter.print(this.mTimePeriodStartRealtime);
        printWriter.print(SmsManager.REGEX_PREFIX_DELIMITER);
        printWriter.print(this.mRunning ? SystemClock.elapsedRealtime() : this.mTimePeriodEndRealtime);
        int i2 = 1;
        if ((this.mFlags & 2) != 0) {
            printWriter.print(",shutdown");
            z = false;
        } else {
            z = true;
        }
        if ((this.mFlags & 4) != 0) {
            printWriter.print(",sysprops");
            z = false;
        }
        if ((this.mFlags & 1) != 0) {
            printWriter.print(",complete");
            z = false;
        }
        if (z) {
            printWriter.print(",partial");
        }
        if (this.mHasSwappedOutPss) {
            printWriter.print(",swapped-out-pss");
        }
        printWriter.println();
        printWriter.print("config,");
        printWriter.println(this.mRuntime);
        if ((i & 14) != 0) {
            int i3 = 0;
            while (i3 < map.size()) {
                String strKeyAt = map.keyAt(i3);
                if (str2 == null || str2.equals(strKeyAt)) {
                    SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt = map.valueAt(i3);
                    int i4 = 0;
                    while (i4 < sparseArrayValueAt.size()) {
                        int iKeyAt = sparseArrayValueAt.keyAt(i4);
                        LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt.valueAt(i4);
                        int i5 = 0;
                        while (i5 < longSparseArrayValueAt.size()) {
                            long jKeyAt = longSparseArrayValueAt.keyAt(i5);
                            PackageState packageStateValueAt = longSparseArrayValueAt.valueAt(i5);
                            int size = packageStateValueAt.mProcesses.size();
                            int size2 = packageStateValueAt.mServices.size();
                            int i6 = i5;
                            int size3 = packageStateValueAt.mAssociations.size();
                            if ((i & 2) != 0) {
                                int i7 = 0;
                                while (i7 < size) {
                                    packageStateValueAt.mProcesses.valueAt(i7).dumpPackageProcCheckin(printWriter, strKeyAt, iKeyAt, jKeyAt, packageStateValueAt.mProcesses.keyAt(i7), jUptimeMillis);
                                    i7++;
                                    size2 = size2;
                                    strKeyAt = strKeyAt;
                                    packageStateValueAt = packageStateValueAt;
                                    size3 = size3;
                                    i3 = i3;
                                    size = size;
                                    map = map;
                                    str3 = str3;
                                    longSparseArrayValueAt = longSparseArrayValueAt;
                                    sparseArrayValueAt = sparseArrayValueAt;
                                    i4 = i4;
                                }
                            }
                            LongSparseArray<PackageState> longSparseArray = longSparseArrayValueAt;
                            SparseArray<LongSparseArray<PackageState>> sparseArray = sparseArrayValueAt;
                            int i8 = i4;
                            String str4 = strKeyAt;
                            int i9 = size2;
                            int i10 = i3;
                            ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> arrayMap = map;
                            String str5 = str3;
                            PackageState packageState = packageStateValueAt;
                            int i11 = size3;
                            if ((i & 4) != 0) {
                                for (int i12 = 0; i12 < i9; i12++) {
                                    packageState.mServices.valueAt(i12).dumpTimesCheckin(printWriter, str4, iKeyAt, jKeyAt, DumpUtils.collapseString(str4, packageState.mServices.keyAt(i12)), jUptimeMillis);
                                }
                            }
                            if ((i & 8) != 0) {
                                for (int i13 = 0; i13 < i11; i13++) {
                                    packageState.mAssociations.valueAt(i13).dumpTimesCheckin(printWriter, str4, iKeyAt, jKeyAt, DumpUtils.collapseString(str4, packageState.mAssociations.keyAt(i13)), jUptimeMillis);
                                }
                            }
                            i5 = i6 + 1;
                            strKeyAt = str4;
                            i3 = i10;
                            map = arrayMap;
                            str3 = str5;
                            longSparseArrayValueAt = longSparseArray;
                            sparseArrayValueAt = sparseArray;
                            i4 = i8;
                        }
                        i4++;
                    }
                }
                i3++;
                str2 = str;
                map = map;
                str3 = str3;
                i2 = 1;
            }
        }
        String str6 = str3;
        int i14 = i2;
        if ((i & 1) != 0) {
            ArrayMap<String, SparseArray<ProcessState>> map2 = this.mProcesses.getMap();
            for (int i15 = 0; i15 < map2.size(); i15++) {
                String strKeyAt2 = map2.keyAt(i15);
                SparseArray<ProcessState> sparseArrayValueAt2 = map2.valueAt(i15);
                for (int i16 = 0; i16 < sparseArrayValueAt2.size(); i16++) {
                    sparseArrayValueAt2.valueAt(i16).dumpProcCheckin(printWriter, strKeyAt2, sparseArrayValueAt2.keyAt(i16), jUptimeMillis);
                }
            }
        }
        printWriter.print("total");
        DumpUtils.dumpAdjTimesCheckin(printWriter, SmsManager.REGEX_PREFIX_DELIMITER, this.mMemFactorDurations, this.mMemFactor, this.mStartTime, jUptimeMillis);
        printWriter.println();
        int keyCount = this.mSysMemUsage.getKeyCount();
        if (keyCount > 0) {
            printWriter.print("sysmemusage");
            int i17 = 0;
            while (i17 < keyCount) {
                int keyAt = this.mSysMemUsage.getKeyAt(i17);
                byte idFromKey = SparseMappingTable.getIdFromKey(keyAt);
                String str7 = str6;
                printWriter.print(str7);
                DumpUtils.printProcStateTag(printWriter, idFromKey);
                for (int i18 = 0; i18 < 16; i18++) {
                    if (i18 > i14) {
                        printWriter.print(SettingsStringUtil.DELIMITER);
                    }
                    printWriter.print(this.mSysMemUsage.getValue(keyAt, i18));
                }
                i17++;
                str6 = str7;
            }
        }
        String str8 = str6;
        printWriter.println();
        TotalMemoryUseCollection totalMemoryUseCollection = new TotalMemoryUseCollection(ALL_SCREEN_ADJ, ALL_MEM_ADJ);
        computeTotalMemoryUse(totalMemoryUseCollection, jUptimeMillis);
        printWriter.print("weights,");
        printWriter.print(totalMemoryUseCollection.totalTime);
        printWriter.print(str8);
        printWriter.print(totalMemoryUseCollection.sysMemCachedWeight);
        printWriter.print(SettingsStringUtil.DELIMITER);
        printWriter.print(totalMemoryUseCollection.sysMemSamples);
        printWriter.print(str8);
        printWriter.print(totalMemoryUseCollection.sysMemFreeWeight);
        printWriter.print(SettingsStringUtil.DELIMITER);
        printWriter.print(totalMemoryUseCollection.sysMemSamples);
        printWriter.print(str8);
        printWriter.print(totalMemoryUseCollection.sysMemZRamWeight);
        printWriter.print(SettingsStringUtil.DELIMITER);
        printWriter.print(totalMemoryUseCollection.sysMemSamples);
        printWriter.print(str8);
        printWriter.print(totalMemoryUseCollection.sysMemKernelWeight);
        printWriter.print(SettingsStringUtil.DELIMITER);
        printWriter.print(totalMemoryUseCollection.sysMemSamples);
        printWriter.print(str8);
        printWriter.print(totalMemoryUseCollection.sysMemNativeWeight);
        printWriter.print(SettingsStringUtil.DELIMITER);
        printWriter.print(totalMemoryUseCollection.sysMemSamples);
        for (int i19 = 0; i19 < 14; i19++) {
            printWriter.print(str8);
            printWriter.print(totalMemoryUseCollection.processStateWeight[i19]);
            printWriter.print(SettingsStringUtil.DELIMITER);
            printWriter.print(totalMemoryUseCollection.processStateSamples[i19]);
        }
        printWriter.println();
        int size4 = this.mPageTypeLabels.size();
        for (int i20 = 0; i20 < size4; i20++) {
            printWriter.print("availablepages,");
            printWriter.print(this.mPageTypeLabels.get(i20));
            printWriter.print(str8);
            printWriter.print(this.mPageTypeZones.get(i20));
            printWriter.print(str8);
            int[] iArr = this.mPageTypeSizes.get(i20);
            int length = iArr == null ? 0 : iArr.length;
            for (int i21 = 0; i21 < length; i21++) {
                if (i21 != 0) {
                    printWriter.print(str8);
                }
                printWriter.print(iArr[i21]);
            }
            printWriter.println();
        }
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j, int i) {
        dumpProtoPreamble(protoOutputStream);
        int size = this.mPageTypeLabels.size();
        for (int i2 = 0; i2 < size; i2++) {
            long jStart = protoOutputStream.start(2246267895818L);
            protoOutputStream.write(1120986464257L, this.mPageTypeNodes.get(i2).intValue());
            protoOutputStream.write(1138166333442L, this.mPageTypeZones.get(i2));
            protoOutputStream.write(1138166333443L, this.mPageTypeLabels.get(i2));
            int[] iArr = this.mPageTypeSizes.get(i2);
            int length = iArr == null ? 0 : iArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                protoOutputStream.write(2220498092036L, iArr[i3]);
            }
            protoOutputStream.end(jStart);
        }
        ArrayMap<String, SparseArray<ProcessState>> map = this.mProcesses.getMap();
        if ((i & 1) != 0) {
            for (int i4 = 0; i4 < map.size(); i4++) {
                String strKeyAt = map.keyAt(i4);
                SparseArray<ProcessState> sparseArrayValueAt = map.valueAt(i4);
                for (int i5 = 0; i5 < sparseArrayValueAt.size(); i5++) {
                    sparseArrayValueAt.valueAt(i5).dumpDebug(protoOutputStream, 2246267895816L, strKeyAt, sparseArrayValueAt.keyAt(i5), j);
                }
            }
        }
        if ((i & 14) != 0) {
            ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map2 = this.mPackages.getMap();
            for (int i6 = 0; i6 < map2.size(); i6++) {
                SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt2 = map2.valueAt(i6);
                for (int i7 = 0; i7 < sparseArrayValueAt2.size(); i7++) {
                    LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt2.valueAt(i7);
                    for (int i8 = 0; i8 < longSparseArrayValueAt.size(); i8++) {
                        longSparseArrayValueAt.valueAt(i8).dumpDebug(protoOutputStream, 2246267895817L, j, i);
                    }
                }
            }
        }
    }

    public void dumpAggregatedProtoForStatsd(ProtoOutputStream[] protoOutputStreamArr, long j) {
        int i = 0;
        dumpProtoPreamble(protoOutputStreamArr[0]);
        ArrayMap<String, SparseArray<ProcessState>> map = this.mProcesses.getMap();
        ProcessMap<ArraySet<PackageState>> processMap = new ProcessMap<>();
        SparseArray<ArraySet<String>> sparseArray = new SparseArray<>();
        collectProcessPackageMaps(null, false, processMap, sparseArray);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= map.size()) {
                break;
            }
            String strKeyAt = map.keyAt(i3);
            if (protoOutputStreamArr[i2].getRawSize() > j) {
                i2++;
                if (i2 >= protoOutputStreamArr.length) {
                    Object[] objArr = new Object[2];
                    objArr[i] = Integer.valueOf(i3);
                    objArr[1] = Integer.valueOf(map.size());
                    Slog.d(TAG, String.format("Dropping process indices from %d to %d from statsd proto (too large)", objArr));
                    break;
                }
                dumpProtoPreamble(protoOutputStreamArr[i2]);
            }
            int i4 = i2;
            SparseArray<ProcessState> sparseArrayValueAt = map.valueAt(i3);
            int i5 = i;
            while (i5 < sparseArrayValueAt.size()) {
                sparseArrayValueAt.valueAt(i5).dumpAggregatedProtoForStatsd(protoOutputStreamArr[i4], 2246267895816L, strKeyAt, sparseArrayValueAt.keyAt(i5), this.mTimePeriodEndRealtime, processMap, sparseArray);
                i5++;
                sparseArrayValueAt = sparseArrayValueAt;
                i3 = i3;
                map = map;
            }
            i3++;
            i2 = i4;
            map = map;
            i = 0;
        }
        for (int i6 = 0; i6 <= i2; i6++) {
            protoOutputStreamArr[i6].flush();
        }
    }

    private void dumpProtoPreamble(ProtoOutputStream protoOutputStream) {
        boolean z;
        protoOutputStream.write(1112396529665L, this.mTimePeriodStartRealtime);
        protoOutputStream.write(1112396529666L, this.mRunning ? SystemClock.elapsedRealtime() : this.mTimePeriodEndRealtime);
        protoOutputStream.write(1112396529667L, this.mTimePeriodStartUptime);
        protoOutputStream.write(1112396529668L, this.mTimePeriodEndUptime);
        protoOutputStream.write(1138166333445L, this.mRuntime);
        protoOutputStream.write(1133871366150L, this.mHasSwappedOutPss);
        boolean z2 = false;
        if ((this.mFlags & 2) != 0) {
            protoOutputStream.write(2259152797703L, 3);
            z = false;
        } else {
            z = true;
        }
        if ((this.mFlags & 4) != 0) {
            protoOutputStream.write(2259152797703L, 4);
            z = false;
        }
        if ((this.mFlags & 1) != 0) {
            protoOutputStream.write(2259152797703L, 1);
        } else {
            z2 = z;
        }
        if (z2) {
            protoOutputStream.write(2259152797703L, 2);
        }
    }

    private void collectProcessPackageMaps(String str, boolean z, ProcessMap<ArraySet<PackageState>> processMap, SparseArray<ArraySet<String>> sparseArray) {
        ArraySet<PackageState> arraySet;
        ArrayMap<String, SparseArray<LongSparseArray<PackageState>>> map = this.mPackages.getMap();
        int i = 1;
        int size = map.size() - 1;
        while (size >= 0) {
            String strKeyAt = map.keyAt(size);
            SparseArray<LongSparseArray<PackageState>> sparseArrayValueAt = map.valueAt(size);
            int size2 = sparseArrayValueAt.size() - i;
            while (size2 >= 0) {
                LongSparseArray<PackageState> longSparseArrayValueAt = sparseArrayValueAt.valueAt(size2);
                int size3 = longSparseArrayValueAt.size() - i;
                while (size3 >= 0) {
                    PackageState packageStateValueAt = longSparseArrayValueAt.valueAt(size3);
                    int i2 = (str == null || str.equals(strKeyAt)) ? i : 0;
                    for (int size4 = packageStateValueAt.mProcesses.size() - i; size4 >= 0; size4--) {
                        ProcessState processStateValueAt = packageStateValueAt.mProcesses.valueAt(size4);
                        if ((i2 != 0 || str.equals(processStateValueAt.getName())) && (!z || processStateValueAt.isInUse())) {
                            String name = processStateValueAt.getName();
                            int uid = processStateValueAt.getUid();
                            ArraySet<PackageState> arraySet2 = processMap.get(name, uid);
                            if (arraySet2 == null) {
                                arraySet = new ArraySet<>();
                                processMap.put(name, uid, arraySet);
                            } else {
                                arraySet = arraySet2;
                            }
                            arraySet.add(packageStateValueAt);
                            ArraySet<String> arraySet3 = sparseArray.get(uid);
                            if (arraySet3 == null) {
                                arraySet3 = new ArraySet<>();
                                sparseArray.put(uid, arraySet3);
                            }
                            arraySet3.add(packageStateValueAt.mPackageName);
                        }
                    }
                    size3--;
                    i = 1;
                }
                size2--;
                i = 1;
            }
            size--;
            i = 1;
        }
    }

    public void dumpFilteredAssociationStatesProtoForProc(ProtoOutputStream protoOutputStream, long j, long j2, ProcessState processState, ProcessMap<ArraySet<PackageState>> processMap, SparseArray<ArraySet<String>> sparseArray) {
        if (processState.isMultiPackage() && processState.getCommonProcess() != processState) {
            return;
        }
        ArrayMap arrayMap = new ArrayMap();
        String name = processState.getName();
        int uid = processState.getUid();
        processState.getVersion();
        ArraySet<PackageState> arraySet = processMap.get(name, uid);
        if (arraySet == null || arraySet.isEmpty()) {
            return;
        }
        int size = arraySet.size() - 1;
        while (true) {
            char c = 0;
            if (size < 0) {
                break;
            }
            ArrayMap<String, AssociationState> arrayMap2 = arraySet.valueAt(size).mAssociations;
            int size2 = arrayMap2.size() - 1;
            while (size2 >= 0) {
                AssociationState associationStateValueAt = arrayMap2.valueAt(size2);
                if (TextUtils.equals(associationStateValueAt.getProcessName(), name)) {
                    ArrayMap<AssociationState.SourceKey, AssociationState.SourceState> arrayMap3 = associationStateValueAt.mSources;
                    int size3 = arrayMap3.size() - 1;
                    while (size3 >= 0) {
                        AssociationState.SourceKey sourceKeyKeyAt = arrayMap3.keyAt(size3);
                        AssociationState.SourceState sourceStateValueAt = arrayMap3.valueAt(size3);
                        long[] jArr = (long[]) arrayMap.get(sourceKeyKeyAt);
                        if (jArr == null) {
                            jArr = new long[2];
                            arrayMap.put(sourceKeyKeyAt, jArr);
                        }
                        String str = name;
                        ArraySet<PackageState> arraySet2 = arraySet;
                        jArr[c] = jArr[c] + sourceStateValueAt.mDuration;
                        int i = size3;
                        jArr[1] = jArr[1] + ((long) sourceStateValueAt.mCount);
                        if (sourceStateValueAt.mNesting > 0) {
                            jArr[0] = jArr[0] + (j2 - sourceStateValueAt.mStartUptime);
                        }
                        size3 = i - 1;
                        c = 0;
                        name = str;
                        arraySet = arraySet2;
                    }
                }
                size2--;
                c = 0;
                name = name;
                arraySet = arraySet;
            }
            size--;
        }
        IProcessStats iProcessStatsAsInterface = IProcessStats.Stub.asInterface(ServiceManager.getService(SERVICE_NAME));
        if (iProcessStatsAsInterface != null) {
            try {
                long minAssociationDumpDuration = iProcessStatsAsInterface.getMinAssociationDumpDuration();
                if (minAssociationDumpDuration > 0) {
                    for (int size4 = arrayMap.size() - 1; size4 >= 0; size4--) {
                        if (((long[]) arrayMap.valueAt(size4))[0] < minAssociationDumpDuration) {
                            arrayMap.removeAt(size4);
                        }
                    }
                }
            } catch (RemoteException unused) {
            }
        }
        if (arrayMap.isEmpty()) {
            return;
        }
        for (int size5 = arrayMap.size() - 1; size5 >= 0; size5--) {
            AssociationState.SourceKey sourceKey = (AssociationState.SourceKey) arrayMap.keyAt(size5);
            long[] jArr2 = (long[]) arrayMap.valueAt(size5);
            long jStart = protoOutputStream.start(j);
            int iIndexOfKey = sparseArray.indexOfKey(sourceKey.mUid);
            ProcessState.writeCompressedProcessName(protoOutputStream, 1138166333441L, sourceKey.mProcess, sourceKey.mPackage, iIndexOfKey >= 0 && sparseArray.valueAt(iIndexOfKey).size() > 1);
            protoOutputStream.write(1120986464261L, sourceKey.mUid);
            protoOutputStream.write(1120986464259L, (int) jArr2[1]);
            protoOutputStream.write(1120986464260L, (int) (jArr2[0] / 1000));
            protoOutputStream.end(jStart);
        }
    }

    public static final class ProcessStateHolder {
        public final long appVersion;
        public PackageState pkg;
        public ProcessState state;

        public ProcessStateHolder(long j) {
            this.appVersion = j;
        }
    }

    public static final class PackageState {
        public final String mPackageName;
        public final ProcessStats mProcessStats;
        public final int mUid;
        public final long mVersionCode;
        public final ArrayMap<String, ProcessState> mProcesses = new ArrayMap<>();
        public final ArrayMap<String, ServiceState> mServices = new ArrayMap<>();
        public final ArrayMap<String, AssociationState> mAssociations = new ArrayMap<>();

        public PackageState(ProcessStats processStats, String str, int i, long j) {
            this.mProcessStats = processStats;
            this.mUid = i;
            this.mPackageName = str;
            this.mVersionCode = j;
        }

        public AssociationState getAssociationStateLocked(ProcessState processState, String str) {
            AssociationState associationState = this.mAssociations.get(str);
            if (associationState != null) {
                if (processState != null) {
                    associationState.setProcess(processState);
                }
                return associationState;
            }
            AssociationState associationState2 = new AssociationState(this.mProcessStats, this, str, processState.getName(), processState);
            this.mAssociations.put(str, associationState2);
            return associationState2;
        }

        public void dumpDebug(ProtoOutputStream protoOutputStream, long j, long j2, int i) {
            long jStart = protoOutputStream.start(j);
            protoOutputStream.write(1138166333441L, this.mPackageName);
            protoOutputStream.write(1120986464258L, this.mUid);
            protoOutputStream.write(1112396529667L, this.mVersionCode);
            if ((i & 2) != 0) {
                for (int i2 = 0; i2 < this.mProcesses.size(); i2++) {
                    this.mProcesses.valueAt(i2).dumpDebug(protoOutputStream, 2246267895812L, this.mProcesses.keyAt(i2), this.mUid, j2);
                }
            }
            if ((i & 4) != 0) {
                for (int i3 = 0; i3 < this.mServices.size(); i3++) {
                    this.mServices.valueAt(i3).dumpDebug(protoOutputStream, 2246267895813L, j2);
                }
            }
            if ((i & 8) != 0) {
                for (int i4 = 0; i4 < this.mAssociations.size(); i4++) {
                    this.mAssociations.valueAt(i4).dumpDebug(protoOutputStream, 2246267895814L, j2);
                }
            }
            protoOutputStream.end(jStart);
        }
    }

    public static final class ProcessDataCollection {
        public long avgPss;
        public long avgRss;
        public long avgUss;
        public long maxPss;
        public long maxRss;
        public long maxUss;
        final int[] memStates;
        public long minPss;
        public long minRss;
        public long minUss;
        public long numPss;
        final int[] procStates;
        final int[] screenStates;
        public long totalTime;

        public ProcessDataCollection(int[] iArr, int[] iArr2, int[] iArr3) {
            this.screenStates = iArr;
            this.memStates = iArr2;
            this.procStates = iArr3;
        }

        void print(PrintWriter printWriter, long j, boolean z) {
            if (this.totalTime > j) {
                printWriter.print("*");
            }
            DumpUtils.printPercent(printWriter, this.totalTime / j);
            if (this.numPss > 0) {
                printWriter.print(" (");
                DebugUtils.printSizeValue(printWriter, this.minPss * 1024);
                printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                DebugUtils.printSizeValue(printWriter, this.avgPss * 1024);
                printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                DebugUtils.printSizeValue(printWriter, this.maxPss * 1024);
                printWriter.print("/");
                DebugUtils.printSizeValue(printWriter, this.minUss * 1024);
                printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                DebugUtils.printSizeValue(printWriter, this.avgUss * 1024);
                printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                DebugUtils.printSizeValue(printWriter, this.maxUss * 1024);
                printWriter.print("/");
                DebugUtils.printSizeValue(printWriter, this.minRss * 1024);
                printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                DebugUtils.printSizeValue(printWriter, this.avgRss * 1024);
                printWriter.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                DebugUtils.printSizeValue(printWriter, this.maxRss * 1024);
                if (z) {
                    printWriter.print(" over ");
                    printWriter.print(this.numPss);
                }
                printWriter.print(")");
            }
        }
    }

    public static class TotalMemoryUseCollection {
        public boolean hasSwappedOutPss;
        final int[] memStates;
        final int[] screenStates;
        public double sysMemCachedWeight;
        public double sysMemFreeWeight;
        public double sysMemKernelWeight;
        public double sysMemNativeWeight;
        public int sysMemSamples;
        public double sysMemZRamWeight;
        public long totalTime;
        public long[] processStatePss = new long[14];
        public double[] processStateWeight = new double[14];
        public long[] processStateTime = new long[14];
        public int[] processStateSamples = new int[14];
        public long[] sysMemUsage = new long[16];

        public TotalMemoryUseCollection(int[] iArr, int[] iArr2) {
            this.screenStates = iArr;
            this.memStates = iArr2;
        }
    }
}
