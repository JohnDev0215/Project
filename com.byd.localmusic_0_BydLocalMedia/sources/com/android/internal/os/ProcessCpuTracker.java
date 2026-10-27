package com.android.internal.os;

import android.os.Process;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.SettingsStringUtil;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Slog;
import android.util.proto.ProtoOutputStream;
import com.android.internal.os.BatteryStatsImpl;
import com.android.internal.util.FastPrintWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ProcessCpuTracker {
    private static final boolean DEBUG = false;
    static final int PROCESS_FULL_STAT_MAJOR_FAULTS = 2;
    static final int PROCESS_FULL_STAT_MINOR_FAULTS = 1;
    static final int PROCESS_FULL_STAT_STIME = 4;
    static final int PROCESS_FULL_STAT_UTIME = 3;
    static final int PROCESS_FULL_STAT_VSIZE = 5;
    static final int PROCESS_STAT_MAJOR_FAULTS = 1;
    static final int PROCESS_STAT_MINOR_FAULTS = 0;
    static final int PROCESS_STAT_STIME = 3;
    static final int PROCESS_STAT_UTIME = 2;
    private static final String TAG = "ProcessCpuTracker";
    private static final boolean localLOGV = false;
    private long mBaseIdleTime;
    private long mBaseIoWaitTime;
    private long mBaseIrqTime;
    private long mBaseSoftIrqTime;
    private long mBaseSystemTime;
    private long mBaseUserTime;
    private int[] mCurPids;
    private int[] mCurThreadPids;
    private long mCurrentSampleRealTime;
    private long mCurrentSampleTime;
    private long mCurrentSampleWallTime;
    private final boolean mIncludeThreads;
    private long mLastSampleRealTime;
    private long mLastSampleTime;
    private long mLastSampleWallTime;
    private int mRelIdleTime;
    private int mRelIoWaitTime;
    private int mRelIrqTime;
    private int mRelSoftIrqTime;
    private boolean mRelStatsAreGood;
    private int mRelSystemTime;
    private int mRelUserTime;
    private boolean mWorkingProcsSorted;
    private static final int[] PROCESS_STATS_FORMAT = {32, 544, 32, 32, 32, 32, 32, 32, 32, 8224, 32, 8224, 32, 8224, 8224};
    private static final int[] PROCESS_FULL_STATS_FORMAT = {32, 4640, 32, 32, 32, 32, 32, 32, 32, 8224, 32, 8224, 32, 8224, 8224, 32, 32, 32, 32, 32, 32, 32, 8224};
    private static final int[] SYSTEM_CPU_FORMAT = {288, 8224, 8224, 8224, 8224, 8224, 8224, 8224};
    private static final int[] LOAD_AVERAGE_FORMAT = {16416, 16416, 16416};
    private static final Comparator<Stats> sLoadComparator = new Comparator<Stats>() { // from class: com.android.internal.os.ProcessCpuTracker.1
        @Override // java.util.Comparator
        public final int compare(Stats stats, Stats stats2) {
            int i = stats.rel_utime + stats.rel_stime;
            int i2 = stats2.rel_utime + stats2.rel_stime;
            if (i != i2) {
                return i > i2 ? -1 : 1;
            }
            if (stats.added != stats2.added) {
                return stats.added ? -1 : 1;
            }
            if (stats.removed != stats2.removed) {
                return stats.added ? -1 : 1;
            }
            return 0;
        }
    };
    private final long[] mProcessStatsData = new long[4];
    private final long[] mSinglePidStatsData = new long[4];
    private final String[] mProcessFullStatsStringData = new String[6];
    private final long[] mProcessFullStatsData = new long[6];
    private final long[] mSystemCpuData = new long[7];
    private final float[] mLoadAverageData = new float[3];
    private float mLoad1 = 0.0f;
    private float mLoad5 = 0.0f;
    private float mLoad15 = 0.0f;
    private final ArrayList<Stats> mProcStats = new ArrayList<>();
    private final ArrayList<Stats> mWorkingProcs = new ArrayList<>();
    private boolean mFirst = true;
    private final long mJiffyMillis = 1000 / Os.sysconf(OsConstants._SC_CLK_TCK);

    public interface FilterStats {
        boolean needed(Stats stats);
    }

    public void onLoadChanged(float f, float f2, float f3) {
    }

    public int onMeasureProcessName(String str) {
        return 0;
    }

    public static class Stats {
        public boolean active;
        public boolean added;
        public String baseName;
        public long base_majfaults;
        public long base_minfaults;
        public long base_stime;
        public long base_uptime;
        public long base_utime;
        public BatteryStatsImpl.Uid.Proc batteryStats;
        final String cmdlineFile;
        public boolean interesting;
        public String name;
        public int nameWidth;
        public final int pid;
        public int rel_majfaults;
        public int rel_minfaults;
        public int rel_stime;
        public long rel_uptime;
        public int rel_utime;
        public boolean removed;
        final String statFile;
        final ArrayList<Stats> threadStats;
        final String threadsDir;
        public final int uid;
        public long vsize;
        public boolean working;
        final ArrayList<Stats> workingThreads;

        Stats(int i, int i2, boolean z) {
            this.pid = i;
            if (i2 < 0) {
                File file = new File("/proc", Integer.toString(this.pid));
                this.uid = getUid(file.toString());
                this.statFile = new File(file, "stat").toString();
                this.cmdlineFile = new File(file, "cmdline").toString();
                this.threadsDir = new File(file, "task").toString();
                if (z) {
                    this.threadStats = new ArrayList<>();
                    this.workingThreads = new ArrayList<>();
                    return;
                } else {
                    this.threadStats = null;
                    this.workingThreads = null;
                    return;
                }
            }
            File file2 = new File(new File(new File("/proc", Integer.toString(i2)), "task"), Integer.toString(this.pid));
            this.uid = getUid(file2.toString());
            this.statFile = new File(file2, "stat").toString();
            this.cmdlineFile = null;
            this.threadsDir = null;
            this.threadStats = null;
            this.workingThreads = null;
        }

        private static int getUid(String str) {
            try {
                return Os.stat(str).st_uid;
            } catch (ErrnoException e) {
                Slog.w(ProcessCpuTracker.TAG, "Failed to stat(" + str + "): " + e);
                return -1;
            }
        }
    }

    public ProcessCpuTracker(boolean z) {
        this.mIncludeThreads = z;
    }

    public void init() {
        this.mFirst = true;
        update();
    }

    public void update() {
        long j;
        long j2;
        long jUptimeMillis = SystemClock.uptimeMillis();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long[] jArr = this.mSystemCpuData;
        if (Process.readProcFile("/proc/stat", SYSTEM_CPU_FORMAT, null, jArr, null)) {
            long j3 = jArr[0] + jArr[1];
            long j4 = this.mJiffyMillis;
            long j5 = j3 * j4;
            long j6 = jArr[2] * j4;
            long j7 = jArr[3] * j4;
            long j8 = jArr[4] * j4;
            j2 = jCurrentTimeMillis;
            long j9 = jArr[5] * j4;
            long j10 = j4 * jArr[6];
            j = jElapsedRealtime;
            this.mRelUserTime = (int) (j5 - this.mBaseUserTime);
            this.mRelSystemTime = (int) (j6 - this.mBaseSystemTime);
            this.mRelIoWaitTime = (int) (j8 - this.mBaseIoWaitTime);
            this.mRelIrqTime = (int) (j9 - this.mBaseIrqTime);
            this.mRelSoftIrqTime = (int) (j10 - this.mBaseSoftIrqTime);
            this.mRelIdleTime = (int) (j7 - this.mBaseIdleTime);
            this.mRelStatsAreGood = true;
            this.mBaseUserTime = j5;
            this.mBaseSystemTime = j6;
            this.mBaseIoWaitTime = j8;
            this.mBaseIrqTime = j9;
            this.mBaseSoftIrqTime = j10;
            this.mBaseIdleTime = j7;
        } else {
            j = jElapsedRealtime;
            j2 = jCurrentTimeMillis;
        }
        this.mLastSampleTime = this.mCurrentSampleTime;
        this.mCurrentSampleTime = jUptimeMillis;
        this.mLastSampleRealTime = this.mCurrentSampleRealTime;
        this.mCurrentSampleRealTime = j;
        this.mLastSampleWallTime = this.mCurrentSampleWallTime;
        this.mCurrentSampleWallTime = j2;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            this.mCurPids = collectStats("/proc", -1, this.mFirst, this.mCurPids, this.mProcStats);
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            float[] fArr = this.mLoadAverageData;
            if (Process.readProcFile("/proc/loadavg", LOAD_AVERAGE_FORMAT, null, null, fArr)) {
                float f = fArr[0];
                float f2 = fArr[1];
                float f3 = fArr[2];
                if (f != this.mLoad1 || f2 != this.mLoad5 || f3 != this.mLoad15) {
                    this.mLoad1 = f;
                    this.mLoad5 = f2;
                    this.mLoad15 = f3;
                    onLoadChanged(f, f2, f3);
                }
            }
            this.mWorkingProcsSorted = false;
            this.mFirst = false;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f9  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] collectStats(java.lang.String r31, int r32, boolean r33, int[] r34, java.util.ArrayList<com.android.internal.os.ProcessCpuTracker.Stats> r35) {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.ProcessCpuTracker.collectStats(java.lang.String, int, boolean, int[], java.util.ArrayList):int[]");
    }

    public long getCpuTimeForPid(int i) {
        synchronized (this.mSinglePidStatsData) {
            String str = "/proc/" + i + "/stat";
            long[] jArr = this.mSinglePidStatsData;
            if (!Process.readProcFile(str, PROCESS_STATS_FORMAT, null, jArr, null)) {
                return 0L;
            }
            return (jArr[2] + jArr[3]) * this.mJiffyMillis;
        }
    }

    public final int getLastUserTime() {
        return this.mRelUserTime;
    }

    public final int getLastSystemTime() {
        return this.mRelSystemTime;
    }

    public final int getLastIoWaitTime() {
        return this.mRelIoWaitTime;
    }

    public final int getLastIrqTime() {
        return this.mRelIrqTime;
    }

    public final int getLastSoftIrqTime() {
        return this.mRelSoftIrqTime;
    }

    public final int getLastIdleTime() {
        return this.mRelIdleTime;
    }

    public final boolean hasGoodLastStats() {
        return this.mRelStatsAreGood;
    }

    public final float getTotalCpuPercent() {
        int i = this.mRelUserTime;
        int i2 = this.mRelSystemTime;
        int i3 = i + i2 + this.mRelIrqTime + this.mRelIdleTime;
        if (i3 <= 0) {
            return 0.0f;
        }
        return (((i + i2) + r3) * 100.0f) / i3;
    }

    final void buildWorkingProcs() {
        if (this.mWorkingProcsSorted) {
            return;
        }
        this.mWorkingProcs.clear();
        int size = this.mProcStats.size();
        for (int i = 0; i < size; i++) {
            Stats stats = this.mProcStats.get(i);
            if (stats.working) {
                this.mWorkingProcs.add(stats);
                if (stats.threadStats != null && stats.threadStats.size() > 1) {
                    stats.workingThreads.clear();
                    int size2 = stats.threadStats.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        Stats stats2 = stats.threadStats.get(i2);
                        if (stats2.working) {
                            stats.workingThreads.add(stats2);
                        }
                    }
                    Collections.sort(stats.workingThreads, sLoadComparator);
                }
            }
        }
        Collections.sort(this.mWorkingProcs, sLoadComparator);
        this.mWorkingProcsSorted = true;
    }

    public final int countStats() {
        return this.mProcStats.size();
    }

    public final Stats getStats(int i) {
        return this.mProcStats.get(i);
    }

    public final List<Stats> getStats(FilterStats filterStats) {
        ArrayList arrayList = new ArrayList(this.mProcStats.size());
        int size = this.mProcStats.size();
        for (int i = 0; i < size; i++) {
            Stats stats = this.mProcStats.get(i);
            if (filterStats.needed(stats)) {
                arrayList.add(stats);
            }
        }
        return arrayList;
    }

    public final int countWorkingStats() {
        buildWorkingProcs();
        return this.mWorkingProcs.size();
    }

    public final Stats getWorkingStats(int i) {
        return this.mWorkingProcs.get(i);
    }

    public final void dumpProto(FileDescriptor fileDescriptor) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        ProtoOutputStream protoOutputStream = new ProtoOutputStream(fileDescriptor);
        long jStart = protoOutputStream.start(1146756268033L);
        protoOutputStream.write(1108101562369L, this.mLoad1);
        protoOutputStream.write(1108101562370L, this.mLoad5);
        protoOutputStream.write(1108101562371L, this.mLoad15);
        protoOutputStream.end(jStart);
        buildWorkingProcs();
        protoOutputStream.write(1112396529666L, jUptimeMillis);
        protoOutputStream.write(1112396529667L, this.mLastSampleTime);
        protoOutputStream.write(1112396529668L, this.mCurrentSampleTime);
        protoOutputStream.write(1112396529669L, this.mLastSampleRealTime);
        protoOutputStream.write(1112396529670L, this.mCurrentSampleRealTime);
        protoOutputStream.write(1112396529671L, this.mLastSampleWallTime);
        protoOutputStream.write(1112396529672L, this.mCurrentSampleWallTime);
        protoOutputStream.write(1120986464265L, this.mRelUserTime);
        protoOutputStream.write(1120986464266L, this.mRelSystemTime);
        protoOutputStream.write(1120986464267L, this.mRelIoWaitTime);
        protoOutputStream.write(1120986464268L, this.mRelIrqTime);
        protoOutputStream.write(1120986464269L, this.mRelSoftIrqTime);
        protoOutputStream.write(1120986464270L, this.mRelIdleTime);
        protoOutputStream.write(1120986464271L, this.mRelUserTime + this.mRelSystemTime + this.mRelIoWaitTime + this.mRelIrqTime + this.mRelSoftIrqTime + this.mRelIdleTime);
        for (Stats stats : this.mWorkingProcs) {
            dumpProcessCpuProto(protoOutputStream, stats, null);
            if (!stats.removed && stats.workingThreads != null) {
                Iterator<Stats> it = stats.workingThreads.iterator();
                while (it.hasNext()) {
                    dumpProcessCpuProto(protoOutputStream, it.next(), stats);
                }
            }
        }
        protoOutputStream.flush();
    }

    private static void dumpProcessCpuProto(ProtoOutputStream protoOutputStream, Stats stats, Stats stats2) {
        long jStart = protoOutputStream.start(2246267895824L);
        protoOutputStream.write(1120986464257L, stats.uid);
        protoOutputStream.write(1120986464258L, stats.pid);
        protoOutputStream.write(1138166333443L, stats.name);
        protoOutputStream.write(1133871366148L, stats.added);
        protoOutputStream.write(1133871366149L, stats.removed);
        protoOutputStream.write(1120986464262L, stats.rel_uptime);
        protoOutputStream.write(1120986464263L, stats.rel_utime);
        protoOutputStream.write(1120986464264L, stats.rel_stime);
        protoOutputStream.write(1120986464265L, stats.rel_minfaults);
        protoOutputStream.write(1120986464266L, stats.rel_majfaults);
        if (stats2 != null) {
            protoOutputStream.write(1120986464267L, stats2.pid);
        }
        protoOutputStream.end(jStart);
    }

    public final String printCurrentLoad() {
        StringWriter stringWriter = new StringWriter();
        FastPrintWriter fastPrintWriter = new FastPrintWriter((Writer) stringWriter, false, 128);
        fastPrintWriter.print("Load: ");
        fastPrintWriter.print(this.mLoad1);
        fastPrintWriter.print(" / ");
        fastPrintWriter.print(this.mLoad5);
        fastPrintWriter.print(" / ");
        fastPrintWriter.println(this.mLoad15);
        fastPrintWriter.flush();
        return stringWriter.toString();
    }

    public final String printCurrentState(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        buildWorkingProcs();
        StringWriter stringWriter = new StringWriter();
        int i = 0;
        FastPrintWriter fastPrintWriter = new FastPrintWriter((Writer) stringWriter, false, 1024);
        fastPrintWriter.print("CPU usage from ");
        long j2 = this.mLastSampleTime;
        if (j > j2) {
            fastPrintWriter.print(j - j2);
            fastPrintWriter.print("ms to ");
            fastPrintWriter.print(j - this.mCurrentSampleTime);
            fastPrintWriter.print("ms ago");
        } else {
            fastPrintWriter.print(j2 - j);
            fastPrintWriter.print("ms to ");
            fastPrintWriter.print(this.mCurrentSampleTime - j);
            fastPrintWriter.print("ms later");
        }
        fastPrintWriter.print(" (");
        fastPrintWriter.print(simpleDateFormat.format(new Date(this.mLastSampleWallTime)));
        fastPrintWriter.print(" to ");
        fastPrintWriter.print(simpleDateFormat.format(new Date(this.mCurrentSampleWallTime)));
        fastPrintWriter.print(")");
        long j3 = this.mCurrentSampleTime - this.mLastSampleTime;
        long j4 = this.mCurrentSampleRealTime - this.mLastSampleRealTime;
        long j5 = j4 > 0 ? (j3 * 100) / j4 : 0L;
        if (j5 != 100) {
            fastPrintWriter.print(" with ");
            fastPrintWriter.print(j5);
            fastPrintWriter.print("% awake");
        }
        fastPrintWriter.println(SettingsStringUtil.DELIMITER);
        int i2 = this.mRelUserTime + this.mRelSystemTime + this.mRelIoWaitTime + this.mRelIrqTime + this.mRelSoftIrqTime + this.mRelIdleTime;
        int size = this.mWorkingProcs.size();
        int i3 = 0;
        while (i3 < size) {
            Stats stats = this.mWorkingProcs.get(i3);
            int i4 = i3;
            int i5 = size;
            int i6 = i;
            printProcessCPU(fastPrintWriter, stats.added ? " +" : stats.removed ? " -" : "  ", stats.pid, stats.name, (int) stats.rel_uptime, stats.rel_utime, stats.rel_stime, 0, 0, 0, stats.rel_minfaults, stats.rel_majfaults);
            Stats stats2 = stats;
            if (!stats2.removed && stats2.workingThreads != null) {
                int size2 = stats2.workingThreads.size();
                int i7 = i6;
                while (i7 < size2) {
                    Stats stats3 = stats2.workingThreads.get(i7);
                    printProcessCPU(fastPrintWriter, stats3.added ? "   +" : stats3.removed ? "   -" : "    ", stats3.pid, stats3.name, (int) stats2.rel_uptime, stats3.rel_utime, stats3.rel_stime, 0, 0, 0, 0, 0);
                    i7++;
                    size2 = size2;
                    stats2 = stats2;
                }
            }
            i3 = i4 + 1;
            size = i5;
            i = i6;
        }
        printProcessCPU(fastPrintWriter, "", -1, "TOTAL", i2, this.mRelUserTime, this.mRelSystemTime, this.mRelIoWaitTime, this.mRelIrqTime, this.mRelSoftIrqTime, 0, 0);
        fastPrintWriter.flush();
        return stringWriter.toString();
    }

    public final String printCurrentState(long j, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Stats stats;
        int size = i;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        buildWorkingProcs();
        StringWriter stringWriter = new StringWriter();
        int i7 = 0;
        FastPrintWriter fastPrintWriter = new FastPrintWriter((Writer) stringWriter, false, 1024);
        fastPrintWriter.print("CPU usage from ");
        long j2 = this.mLastSampleTime;
        if (j > j2) {
            fastPrintWriter.print(j - j2);
            fastPrintWriter.print("ms to ");
            fastPrintWriter.print(j - this.mCurrentSampleTime);
            fastPrintWriter.print("ms ago");
        } else {
            fastPrintWriter.print(j2 - j);
            fastPrintWriter.print("ms to ");
            fastPrintWriter.print(this.mCurrentSampleTime - j);
            fastPrintWriter.print("ms later");
        }
        fastPrintWriter.print(" (");
        fastPrintWriter.print(simpleDateFormat.format(new Date(this.mLastSampleWallTime)));
        fastPrintWriter.print(" to ");
        fastPrintWriter.print(simpleDateFormat.format(new Date(this.mCurrentSampleWallTime)));
        fastPrintWriter.print(")");
        long j3 = this.mCurrentSampleTime - this.mLastSampleTime;
        long j4 = this.mCurrentSampleRealTime - this.mLastSampleRealTime;
        long j5 = j4 > 0 ? (j3 * 100) / j4 : 0L;
        if (j5 != 100) {
            fastPrintWriter.print(" with ");
            fastPrintWriter.print(j5);
            fastPrintWriter.print("% awake");
        }
        fastPrintWriter.println(SettingsStringUtil.DELIMITER);
        int i8 = this.mRelUserTime + this.mRelSystemTime + this.mRelIoWaitTime + this.mRelIrqTime + this.mRelSoftIrqTime + this.mRelIdleTime;
        if (size == -1 || size > this.mWorkingProcs.size()) {
            size = this.mWorkingProcs.size();
        }
        int i9 = size;
        int i10 = 0;
        while (i10 < i9) {
            Stats stats2 = this.mWorkingProcs.get(i10);
            if (stats2 == null) {
                i2 = i10;
                i3 = i9;
                i4 = i7;
            } else {
                i2 = i10;
                i3 = i9;
                i4 = i7;
                printProcessCPU(fastPrintWriter, stats2.added ? " +" : stats2.removed ? " -" : "  ", stats2.pid, stats2.name, (int) stats2.rel_uptime, stats2.rel_utime, stats2.rel_stime, 0, 0, 0, stats2.rel_minfaults, stats2.rel_majfaults);
                Stats stats3 = stats2;
                if (!stats3.removed && stats3.workingThreads != null) {
                    int size2 = stats3.workingThreads.size();
                    int i11 = i4;
                    while (i11 < size2) {
                        Stats stats4 = stats3.workingThreads.get(i11);
                        if (stats4 == null) {
                            i5 = i11;
                            i6 = size2;
                            stats = stats3;
                        } else {
                            i5 = i11;
                            i6 = size2;
                            stats = stats3;
                            printProcessCPU(fastPrintWriter, stats4.added ? "   +" : stats4.removed ? "   -" : "    ", stats4.pid, stats4.name, (int) stats3.rel_uptime, stats4.rel_utime, stats4.rel_stime, 0, 0, 0, 0, 0);
                        }
                        i11 = i5 + 1;
                        size2 = i6;
                        stats3 = stats;
                    }
                }
            }
            i10 = i2 + 1;
            i9 = i3;
            i7 = i4;
        }
        printProcessCPU(fastPrintWriter, "", -1, "TOTAL", i8, this.mRelUserTime, this.mRelSystemTime, this.mRelIoWaitTime, this.mRelIrqTime, this.mRelSoftIrqTime, 0, 0);
        fastPrintWriter.flush();
        return stringWriter.toString();
    }

    private void printRatio(PrintWriter printWriter, long j, long j2) {
        long j3 = (j * 1000) / j2;
        long j4 = j3 / 10;
        printWriter.print(j4);
        if (j4 < 10) {
            long j5 = j3 - (j4 * 10);
            if (j5 != 0) {
                printWriter.print('.');
                printWriter.print(j5);
            }
        }
    }

    private void printProcessCPU(PrintWriter printWriter, String str, int i, String str2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        printWriter.print(str);
        long j = i2 == 0 ? 1 : i2;
        printRatio(printWriter, i3 + i4 + i5 + i6 + i7, j);
        printWriter.print("% ");
        if (i >= 0) {
            printWriter.print(i);
            printWriter.print("/");
        }
        printWriter.print(str2);
        printWriter.print(": ");
        printRatio(printWriter, i3, j);
        printWriter.print("% user + ");
        printRatio(printWriter, i4, j);
        printWriter.print("% kernel");
        if (i5 > 0) {
            printWriter.print(" + ");
            printRatio(printWriter, i5, j);
            printWriter.print("% iowait");
        }
        if (i6 > 0) {
            printWriter.print(" + ");
            printRatio(printWriter, i6, j);
            printWriter.print("% irq");
        }
        if (i7 > 0) {
            printWriter.print(" + ");
            printRatio(printWriter, i7, j);
            printWriter.print("% softirq");
        }
        if (i8 > 0 || i9 > 0) {
            printWriter.print(" / faults:");
            if (i8 > 0) {
                printWriter.print(" ");
                printWriter.print(i8);
                printWriter.print(" minor");
            }
            if (i9 > 0) {
                printWriter.print(" ");
                printWriter.print(i9);
                printWriter.print(" major");
            }
        }
        printWriter.println();
    }

    private void getName(Stats stats, String str) {
        String str2 = stats.name;
        if (stats.name == null || stats.name.equals("app_process") || stats.name.equals("<pre-initialized>")) {
            String terminatedProcFile = ProcStatsUtil.readTerminatedProcFile(str, (byte) 0);
            if (terminatedProcFile != null && terminatedProcFile.length() > 1) {
                int iLastIndexOf = terminatedProcFile.lastIndexOf("/");
                if (iLastIndexOf > 0 && iLastIndexOf < terminatedProcFile.length() - 1) {
                    terminatedProcFile = terminatedProcFile.substring(iLastIndexOf + 1);
                }
                str2 = terminatedProcFile;
            }
            if (str2 == null) {
                str2 = stats.baseName;
            }
        }
        if (stats.name == null || !str2.equals(stats.name)) {
            stats.name = str2;
            stats.nameWidth = onMeasureProcessName(stats.name);
        }
    }
}
