package com.android.internal.os;

import android.os.Binder;
import android.os.Process;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.ArrayMap;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import com.android.internal.os.BinderCallsStats;
import com.android.internal.os.BinderInternal;
import com.android.internal.os.CachedDeviceState;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: classes4.dex */
public class BinderCallsStats implements BinderInternal.Observer {
    private static final int CALL_SESSIONS_POOL_SIZE = 100;
    private static final String DEBUG_ENTRY_PREFIX = "__DEBUG_";
    public static final boolean DEFAULT_TRACK_DIRECT_CALLING_UID = true;
    public static final boolean DEFAULT_TRACK_SCREEN_INTERACTIVE = false;
    public static final boolean DETAILED_TRACKING_DEFAULT = true;
    public static final boolean ENABLED_DEFAULT = true;
    private static final String EXCEPTION_COUNT_OVERFLOW_NAME = "overflow";
    public static final int MAX_BINDER_CALL_STATS_COUNT_DEFAULT = 1500;
    private static final int MAX_EXCEPTION_COUNT_SIZE = 50;
    private static final Class<? extends Binder> OVERFLOW_BINDER = OverflowBinder.class;
    private static final int OVERFLOW_DIRECT_CALLING_UID = -1;
    private static final boolean OVERFLOW_SCREEN_INTERACTIVE = false;
    private static final int OVERFLOW_TRANSACTION_CODE = -1;
    public static final int PERIODIC_SAMPLING_INTERVAL_DEFAULT = 1000;
    private static final String TAG = "BinderCallsStats";
    private CachedDeviceState.TimeInStateStopwatch mBatteryStopwatch;
    private CachedDeviceState.Readonly mDeviceState;
    private final Random mRandom;
    private boolean mDetailedTracking = true;
    private int mPeriodicSamplingInterval = 1000;
    private int mMaxBinderCallStatsCount = 1500;
    private final SparseArray<UidEntry> mUidEntries = new SparseArray<>();
    private final ArrayMap<String, Integer> mExceptionCounts = new ArrayMap<>();
    private final Queue<BinderInternal.CallSession> mCallSessionsPool = new ConcurrentLinkedQueue();
    private final Object mLock = new Object();
    private long mStartCurrentTime = System.currentTimeMillis();
    private long mStartElapsedTime = SystemClock.elapsedRealtime();
    private long mCallStatsCount = 0;
    private boolean mAddDebugEntries = false;
    private boolean mTrackDirectCallingUid = true;
    private boolean mTrackScreenInteractive = false;

    public static class ExportedCallStat {
        Class<? extends Binder> binderClass;
        public long callCount;
        public int callingUid;
        public String className;
        public long cpuTimeMicros;
        public long exceptionCount;
        public long latencyMicros;
        public long maxCpuTimeMicros;
        public long maxLatencyMicros;
        public long maxReplySizeBytes;
        public long maxRequestSizeBytes;
        public String methodName;
        public long recordedCallCount;
        public boolean screenInteractive;
        int transactionCode;
        public int workSourceUid;
    }

    private static class OverflowBinder extends Binder {
        private OverflowBinder() {
        }
    }

    public static class Injector {
        public Random getRandomGenerator() {
            return new Random();
        }
    }

    public BinderCallsStats(Injector injector) {
        this.mRandom = injector.getRandomGenerator();
    }

    public void setDeviceState(CachedDeviceState.Readonly readonly) {
        CachedDeviceState.TimeInStateStopwatch timeInStateStopwatch = this.mBatteryStopwatch;
        if (timeInStateStopwatch != null) {
            timeInStateStopwatch.close();
        }
        this.mDeviceState = readonly;
        this.mBatteryStopwatch = readonly.createTimeOnBatteryStopwatch();
    }

    @Override // com.android.internal.os.BinderInternal.Observer
    public BinderInternal.CallSession callStarted(Binder binder, int i, int i2) {
        CachedDeviceState.Readonly readonly = this.mDeviceState;
        if (readonly == null || readonly.isCharging()) {
            return null;
        }
        BinderInternal.CallSession callSessionObtainCallSession = obtainCallSession();
        callSessionObtainCallSession.binderClass = binder.getClass();
        callSessionObtainCallSession.transactionCode = i;
        callSessionObtainCallSession.exceptionThrown = false;
        callSessionObtainCallSession.cpuTimeStarted = -1L;
        callSessionObtainCallSession.timeStarted = -1L;
        if (shouldRecordDetailedData()) {
            callSessionObtainCallSession.cpuTimeStarted = getThreadTimeMicro();
            callSessionObtainCallSession.timeStarted = getElapsedRealtimeMicro();
        }
        return callSessionObtainCallSession;
    }

    private BinderInternal.CallSession obtainCallSession() {
        BinderInternal.CallSession callSessionPoll = this.mCallSessionsPool.poll();
        return callSessionPoll == null ? new BinderInternal.CallSession() : callSessionPoll;
    }

    @Override // com.android.internal.os.BinderInternal.Observer
    public void callEnded(BinderInternal.CallSession callSession, int i, int i2, int i3) throws Throwable {
        if (callSession == null) {
            return;
        }
        processCallEnded(callSession, i, i2, i3);
        if (this.mCallSessionsPool.size() < 100) {
            this.mCallSessionsPool.add(callSession);
        }
    }

    private void processCallEnded(BinderInternal.CallSession callSession, int i, int i2, int i3) throws Throwable {
        long threadTimeMicro;
        long elapsedRealtimeMicro;
        Object obj;
        boolean z = callSession.cpuTimeStarted >= 0;
        if (z) {
            threadTimeMicro = getThreadTimeMicro() - callSession.cpuTimeStarted;
            elapsedRealtimeMicro = getElapsedRealtimeMicro() - callSession.timeStarted;
        } else {
            threadTimeMicro = 0;
            elapsedRealtimeMicro = 0;
        }
        boolean zIsScreenInteractive = this.mTrackScreenInteractive ? this.mDeviceState.isScreenInteractive() : false;
        int callingUid = this.mTrackDirectCallingUid ? getCallingUid() : -1;
        Object obj2 = this.mLock;
        synchronized (obj2) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                CachedDeviceState.Readonly readonly = this.mDeviceState;
                if (readonly != null && !readonly.isCharging()) {
                    UidEntry uidEntry = getUidEntry(i3);
                    long j = 1;
                    uidEntry.callCount++;
                    if (z) {
                        uidEntry.cpuTimeMicros += threadTimeMicro;
                        uidEntry.recordedCallCount++;
                        long j2 = threadTimeMicro;
                        obj = obj2;
                        CallStat orCreate = uidEntry.getOrCreate(callingUid, callSession.binderClass, callSession.transactionCode, zIsScreenInteractive, this.mCallStatsCount >= ((long) this.mMaxBinderCallStatsCount));
                        if (orCreate.callCount == 0) {
                            this.mCallStatsCount++;
                        }
                        orCreate.callCount++;
                        orCreate.recordedCallCount++;
                        orCreate.cpuTimeMicros += j2;
                        orCreate.maxCpuTimeMicros = Math.max(orCreate.maxCpuTimeMicros, j2);
                        orCreate.latencyMicros += elapsedRealtimeMicro;
                        orCreate.maxLatencyMicros = Math.max(orCreate.maxLatencyMicros, elapsedRealtimeMicro);
                        if (this.mDetailedTracking) {
                            long j3 = orCreate.exceptionCount;
                            if (!callSession.exceptionThrown) {
                                j = 0;
                            }
                            orCreate.exceptionCount = j3 + j;
                            orCreate.maxRequestSizeBytes = Math.max(orCreate.maxRequestSizeBytes, i);
                            orCreate.maxReplySizeBytes = Math.max(orCreate.maxReplySizeBytes, i2);
                        }
                    } else {
                        obj = obj2;
                        CallStat callStat = uidEntry.get(callingUid, callSession.binderClass, callSession.transactionCode, zIsScreenInteractive);
                        if (callStat != null) {
                            callStat.callCount++;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private UidEntry getUidEntry(int i) {
        UidEntry uidEntry = this.mUidEntries.get(i);
        if (uidEntry != null) {
            return uidEntry;
        }
        UidEntry uidEntry2 = new UidEntry(i);
        this.mUidEntries.put(i, uidEntry2);
        return uidEntry2;
    }

    @Override // com.android.internal.os.BinderInternal.Observer
    public void callThrewException(BinderInternal.CallSession callSession, Exception exc) {
        if (callSession == null) {
            return;
        }
        int iIntValue = 1;
        callSession.exceptionThrown = true;
        try {
            String name = exc.getClass().getName();
            synchronized (this.mLock) {
                if (this.mExceptionCounts.size() >= 50) {
                    name = EXCEPTION_COUNT_OVERFLOW_NAME;
                }
                Integer num = this.mExceptionCounts.get(name);
                ArrayMap<String, Integer> arrayMap = this.mExceptionCounts;
                if (num != null) {
                    iIntValue = 1 + num.intValue();
                }
                arrayMap.put(name, Integer.valueOf(iIntValue));
            }
        } catch (RuntimeException unused) {
            Slog.wtf(TAG, "Unexpected exception while updating mExceptionCounts");
        }
    }

    private Method getDefaultTransactionNameMethod(Class<? extends Binder> cls) {
        try {
            return cls.getMethod("getDefaultTransactionName", Integer.TYPE);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private String resolveTransactionCode(Method method, int i) {
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(null, Integer.valueOf(i));
        } catch (ClassCastException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ExportedCallStat> getExportedCallStats() {
        if (!this.mDetailedTracking) {
            return new ArrayList<>();
        }
        ArrayList<ExportedCallStat> arrayList = new ArrayList<>();
        synchronized (this.mLock) {
            int size = this.mUidEntries.size();
            for (int i = 0; i < size; i++) {
                UidEntry uidEntryValueAt = this.mUidEntries.valueAt(i);
                for (CallStat callStat : uidEntryValueAt.getCallStatsList()) {
                    ExportedCallStat exportedCallStat = new ExportedCallStat();
                    exportedCallStat.workSourceUid = uidEntryValueAt.workSourceUid;
                    exportedCallStat.callingUid = callStat.callingUid;
                    exportedCallStat.className = callStat.binderClass.getName();
                    exportedCallStat.binderClass = callStat.binderClass;
                    exportedCallStat.transactionCode = callStat.transactionCode;
                    exportedCallStat.screenInteractive = callStat.screenInteractive;
                    exportedCallStat.cpuTimeMicros = callStat.cpuTimeMicros;
                    exportedCallStat.maxCpuTimeMicros = callStat.maxCpuTimeMicros;
                    exportedCallStat.latencyMicros = callStat.latencyMicros;
                    exportedCallStat.maxLatencyMicros = callStat.maxLatencyMicros;
                    exportedCallStat.recordedCallCount = callStat.recordedCallCount;
                    exportedCallStat.callCount = callStat.callCount;
                    exportedCallStat.maxRequestSizeBytes = callStat.maxRequestSizeBytes;
                    exportedCallStat.maxReplySizeBytes = callStat.maxReplySizeBytes;
                    exportedCallStat.exceptionCount = callStat.exceptionCount;
                    arrayList.add(exportedCallStat);
                }
            }
        }
        arrayList.sort(new Comparator() { // from class: com.android.internal.os.-$$Lambda$BinderCallsStats$6OT4SvUlJjWv3GG3n7NmzuqSn_k
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return BinderCallsStats.compareByBinderClassAndCode((BinderCallsStats.ExportedCallStat) obj, (BinderCallsStats.ExportedCallStat) obj2);
            }
        });
        for (ExportedCallStat exportedCallStat2 : arrayList) {
            String strResolveTransactionCode = resolveTransactionCode(getDefaultTransactionNameMethod(exportedCallStat2.binderClass), exportedCallStat2.transactionCode);
            if (strResolveTransactionCode == null) {
                strResolveTransactionCode = String.valueOf(exportedCallStat2.transactionCode);
            }
            exportedCallStat2.methodName = strResolveTransactionCode;
        }
        if (this.mAddDebugEntries && this.mBatteryStopwatch != null) {
            arrayList.add(createDebugEntry("start_time_millis", this.mStartElapsedTime));
            arrayList.add(createDebugEntry("end_time_millis", SystemClock.elapsedRealtime()));
            arrayList.add(createDebugEntry("battery_time_millis", this.mBatteryStopwatch.getMillis()));
            arrayList.add(createDebugEntry("sampling_interval", this.mPeriodicSamplingInterval));
        }
        return arrayList;
    }

    private ExportedCallStat createDebugEntry(String str, long j) {
        int iMyUid = Process.myUid();
        ExportedCallStat exportedCallStat = new ExportedCallStat();
        exportedCallStat.className = "";
        exportedCallStat.workSourceUid = iMyUid;
        exportedCallStat.callingUid = iMyUid;
        exportedCallStat.recordedCallCount = 1L;
        exportedCallStat.callCount = 1L;
        exportedCallStat.methodName = "__DEBUG_" + str;
        exportedCallStat.latencyMicros = j;
        return exportedCallStat;
    }

    public ArrayMap<String, Integer> getExportedExceptionStats() {
        ArrayMap<String, Integer> arrayMap;
        synchronized (this.mLock) {
            arrayMap = new ArrayMap<>(this.mExceptionCounts);
        }
        return arrayMap;
    }

    public void dump(PrintWriter printWriter, AppIdToPackageMap appIdToPackageMap, boolean z) {
        synchronized (this.mLock) {
            dumpLocked(printWriter, appIdToPackageMap, z);
        }
    }

    private void dumpLocked(PrintWriter printWriter, AppIdToPackageMap appIdToPackageMap, boolean z) {
        printWriter.print("Start time: ");
        printWriter.println(DateFormat.format("yyyy-MM-dd HH:mm:ss", this.mStartCurrentTime));
        printWriter.print("On battery time (ms): ");
        CachedDeviceState.TimeInStateStopwatch timeInStateStopwatch = this.mBatteryStopwatch;
        long j = 0;
        printWriter.println(timeInStateStopwatch != null ? timeInStateStopwatch.getMillis() : 0L);
        printWriter.println("Sampling interval period: " + this.mPeriodicSamplingInterval);
        List<UidEntry> arrayList = new ArrayList();
        int size = this.mUidEntries.size();
        long j2 = 0;
        long j3 = 0;
        for (int i = 0; i < size; i++) {
            UidEntry uidEntryValueAt = this.mUidEntries.valueAt(i);
            arrayList.add(uidEntryValueAt);
            j += uidEntryValueAt.cpuTimeMicros;
            j2 += uidEntryValueAt.recordedCallCount;
            j3 += uidEntryValueAt.callCount;
        }
        arrayList.sort(Comparator.comparingDouble(new ToDoubleFunction() { // from class: com.android.internal.os.-$$Lambda$BinderCallsStats$por-NUTZ5FeT7Gp7dGRHaGOxmXc
            /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 double, still in use, count: 1, list:
                  (r0v1 double) from 0x0006: RETURN (r0v1 double)
                	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
                	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
                	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:468)
                	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
                */
            @Override // java.util.function.ToDoubleFunction
            public final double applyAsDouble(java.lang.Object r1) {
                /*
                    r0 = this;
                    com.android.internal.os.BinderCallsStats$UidEntry r1 = (com.android.internal.os.BinderCallsStats.UidEntry) r1
                    double r0 = com.android.internal.os.BinderCallsStats.lambda$dumpLocked$0(r1)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.$$Lambda$BinderCallsStats$porNUTZ5FeT7Gp7dGRHaGOxmXc.applyAsDouble(java.lang.Object):double");
            }
        }).reversed());
        String str = "";
        String str2 = z ? "" : "(top 90% by cpu time) ";
        StringBuilder sb = new StringBuilder();
        printWriter.println("Per-UID raw data " + str2 + "(package/uid, worksource, call_desc, screen_interactive, cpu_time_micros, max_cpu_time_micros, latency_time_micros, max_latency_time_micros, exception_count, max_request_size_bytes, max_reply_size_bytes, recorded_call_count, call_count):");
        ArrayList<ExportedCallStat> exportedCallStats = getExportedCallStats();
        exportedCallStats.sort(new Comparator() { // from class: com.android.internal.os.-$$Lambda$BinderCallsStats$Pr85-6HhTBhjaOk50JenXUlo6J8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return BinderCallsStats.compareByCpuDesc((BinderCallsStats.ExportedCallStat) obj, (BinderCallsStats.ExportedCallStat) obj2);
            }
        });
        Iterator<ExportedCallStat> it = exportedCallStats.iterator();
        while (it.hasNext()) {
            ExportedCallStat next = it.next();
            Iterator<ExportedCallStat> it2 = it;
            if (next.methodName.startsWith("__DEBUG_")) {
                it = it2;
            } else {
                sb.setLength(0);
                String str3 = str;
                long j4 = j2;
                sb.append("    ").append(appIdToPackageMap.mapUid(next.callingUid)).append(',').append(appIdToPackageMap.mapUid(next.workSourceUid)).append(',').append(next.className).append('#').append(next.methodName).append(',').append(next.screenInteractive).append(',').append(next.cpuTimeMicros).append(',').append(next.maxCpuTimeMicros).append(',').append(next.latencyMicros).append(',').append(next.maxLatencyMicros).append(',').append(this.mDetailedTracking ? next.exceptionCount : 95L).append(',').append(this.mDetailedTracking ? next.maxRequestSizeBytes : 95L).append(',').append(this.mDetailedTracking ? next.maxReplySizeBytes : 95L).append(',').append(next.recordedCallCount).append(',').append(next.callCount);
                printWriter.println(sb);
                it = it2;
                str = str3;
                j2 = j4;
            }
        }
        String str4 = str;
        long j5 = j2;
        printWriter.println();
        printWriter.println("Per-UID Summary " + str2 + "(cpu_time, % of total cpu_time, recorded_call_count, call_count, package/uid):");
        if (!z) {
            arrayList = getHighestValues(arrayList, new ToDoubleFunction() { // from class: com.android.internal.os.-$$Lambda$BinderCallsStats$QCXlpCPi0HK14ZfCq4zDhE49hto
                /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 double, still in use, count: 1, list:
                      (r0v1 double) from 0x0006: RETURN (r0v1 double)
                    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                    	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
                    	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
                    	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:468)
                    	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
                    */
                @Override // java.util.function.ToDoubleFunction
                public final double applyAsDouble(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.internal.os.BinderCallsStats$UidEntry r1 = (com.android.internal.os.BinderCallsStats.UidEntry) r1
                        double r0 = com.android.internal.os.BinderCallsStats.lambda$dumpLocked$1(r1)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.$$Lambda$BinderCallsStats$QCXlpCPi0HK14ZfCq4zDhE49hto.applyAsDouble(java.lang.Object):double");
                }
            }, 0.9d);
        }
        for (UidEntry uidEntry : arrayList) {
            printWriter.println(String.format("  %10d %3.0f%% %8d %8d %s", Long.valueOf(uidEntry.cpuTimeMicros), Double.valueOf((uidEntry.cpuTimeMicros * 100.0d) / j), Long.valueOf(uidEntry.recordedCallCount), Long.valueOf(uidEntry.callCount), appIdToPackageMap.mapUid(uidEntry.workSourceUid)));
        }
        printWriter.println();
        printWriter.println(String.format("  Summary: total_cpu_time=%d, calls_count=%d, avg_call_cpu_time=%.0f", Long.valueOf(j), Long.valueOf(j3), Double.valueOf(j / j5)));
        printWriter.println();
        printWriter.println("Exceptions thrown (exception_count, class_name):");
        final ArrayList<Pair> arrayList2 = new ArrayList();
        this.mExceptionCounts.entrySet().iterator().forEachRemaining(new Consumer() { // from class: com.android.internal.os.-$$Lambda$BinderCallsStats$Fzb69nUYGbiUl0xbS-tFwogNWvc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                arrayList2.add(Pair.create((String) entry.getKey(), (Integer) entry.getValue()));
            }
        });
        arrayList2.sort(new Comparator() { // from class: com.android.internal.os.-$$Lambda$BinderCallsStats$bZEQTgQhqvQueivbDTVQu5Ww1dk
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Integer.compare(((Integer) ((Pair) obj2).second).intValue(), ((Integer) ((Pair) obj).second).intValue());
            }
        });
        for (Pair pair : arrayList2) {
            printWriter.println(String.format("  %6d %s", pair.second, pair.first));
        }
        if (this.mPeriodicSamplingInterval != 1) {
            printWriter.println(str4);
            printWriter.println("/!\\ Displayed data is sampled. See sampling interval at the top.");
        }
    }

    protected long getThreadTimeMicro() {
        return SystemClock.currentThreadTimeMicro();
    }

    protected int getCallingUid() {
        return Binder.getCallingUid();
    }

    protected long getElapsedRealtimeMicro() {
        return SystemClock.elapsedRealtimeNanos() / 1000;
    }

    protected boolean shouldRecordDetailedData() {
        return this.mRandom.nextInt() % this.mPeriodicSamplingInterval == 0;
    }

    public void setDetailedTracking(boolean z) {
        synchronized (this.mLock) {
            if (z != this.mDetailedTracking) {
                this.mDetailedTracking = z;
                reset();
            }
        }
    }

    public void setTrackScreenInteractive(boolean z) {
        synchronized (this.mLock) {
            if (z != this.mTrackScreenInteractive) {
                this.mTrackScreenInteractive = z;
                reset();
            }
        }
    }

    public void setTrackDirectCallerUid(boolean z) {
        synchronized (this.mLock) {
            if (z != this.mTrackDirectCallingUid) {
                this.mTrackDirectCallingUid = z;
                reset();
            }
        }
    }

    public void setAddDebugEntries(boolean z) {
        this.mAddDebugEntries = z;
    }

    public void setMaxBinderCallStats(int i) {
        if (i <= 0) {
            Slog.w(TAG, "Ignored invalid max value (value must be positive): " + i);
            return;
        }
        synchronized (this.mLock) {
            if (i != this.mMaxBinderCallStatsCount) {
                this.mMaxBinderCallStatsCount = i;
                reset();
            }
        }
    }

    public void setSamplingInterval(int i) {
        if (i <= 0) {
            Slog.w(TAG, "Ignored invalid sampling interval (value must be positive): " + i);
            return;
        }
        synchronized (this.mLock) {
            if (i != this.mPeriodicSamplingInterval) {
                this.mPeriodicSamplingInterval = i;
                reset();
            }
        }
    }

    public void reset() {
        synchronized (this.mLock) {
            this.mCallStatsCount = 0L;
            this.mUidEntries.clear();
            this.mExceptionCounts.clear();
            this.mStartCurrentTime = System.currentTimeMillis();
            this.mStartElapsedTime = SystemClock.elapsedRealtime();
            CachedDeviceState.TimeInStateStopwatch timeInStateStopwatch = this.mBatteryStopwatch;
            if (timeInStateStopwatch != null) {
                timeInStateStopwatch.reset();
            }
        }
    }

    public static class CallStat {
        public final Class<? extends Binder> binderClass;
        public long callCount;
        public final int callingUid;
        public long cpuTimeMicros;
        public long exceptionCount;
        public long latencyMicros;
        public long maxCpuTimeMicros;
        public long maxLatencyMicros;
        public long maxReplySizeBytes;
        public long maxRequestSizeBytes;
        public long recordedCallCount;
        public final boolean screenInteractive;
        public final int transactionCode;

        CallStat(int i, Class<? extends Binder> cls, int i2, boolean z) {
            this.callingUid = i;
            this.binderClass = cls;
            this.transactionCode = i2;
            this.screenInteractive = z;
        }
    }

    public static class CallStatKey {
        public Class<? extends Binder> binderClass;
        public int callingUid;
        private boolean screenInteractive;
        public int transactionCode;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            CallStatKey callStatKey = (CallStatKey) obj;
            return this.callingUid == callStatKey.callingUid && this.transactionCode == callStatKey.transactionCode && this.screenInteractive == callStatKey.screenInteractive && this.binderClass.equals(callStatKey.binderClass);
        }

        public int hashCode() {
            return (((((this.binderClass.hashCode() * 31) + this.transactionCode) * 31) + this.callingUid) * 31) + (this.screenInteractive ? 1231 : 1237);
        }
    }

    public static class UidEntry {
        public long callCount;
        public long cpuTimeMicros;
        private Map<CallStatKey, CallStat> mCallStats = new ArrayMap();
        private CallStatKey mTempKey = new CallStatKey();
        public long recordedCallCount;
        public int workSourceUid;

        UidEntry(int i) {
            this.workSourceUid = i;
        }

        CallStat get(int i, Class<? extends Binder> cls, int i2, boolean z) {
            this.mTempKey.callingUid = i;
            this.mTempKey.binderClass = cls;
            this.mTempKey.transactionCode = i2;
            this.mTempKey.screenInteractive = z;
            return this.mCallStats.get(this.mTempKey);
        }

        CallStat getOrCreate(int i, Class<? extends Binder> cls, int i2, boolean z, boolean z2) {
            CallStat callStat = get(i, cls, i2, z);
            if (callStat != null) {
                return callStat;
            }
            if (z2) {
                CallStat callStat2 = get(-1, BinderCallsStats.OVERFLOW_BINDER, -1, false);
                if (callStat2 != null) {
                    return callStat2;
                }
                cls = BinderCallsStats.OVERFLOW_BINDER;
                z = false;
                i = -1;
                i2 = -1;
            }
            CallStat callStat3 = new CallStat(i, cls, i2, z);
            CallStatKey callStatKey = new CallStatKey();
            callStatKey.callingUid = i;
            callStatKey.binderClass = cls;
            callStatKey.transactionCode = i2;
            callStatKey.screenInteractive = z;
            this.mCallStats.put(callStatKey, callStat3);
            return callStat3;
        }

        public Collection<CallStat> getCallStatsList() {
            return this.mCallStats.values();
        }

        public String toString() {
            return "UidEntry{cpuTimeMicros=" + this.cpuTimeMicros + ", callCount=" + this.callCount + ", mCallStats=" + this.mCallStats + '}';
        }

        public boolean equals(Object obj) {
            return this == obj || this.workSourceUid == ((UidEntry) obj).workSourceUid;
        }

        public int hashCode() {
            return this.workSourceUid;
        }
    }

    public SparseArray<UidEntry> getUidEntries() {
        return this.mUidEntries;
    }

    public ArrayMap<String, Integer> getExceptionCounts() {
        return this.mExceptionCounts;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> getHighestValues(List<T> list, ToDoubleFunction<T> toDoubleFunction, double d) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.sort(Comparator.comparingDouble(toDoubleFunction).reversed());
        Iterator<T> it = list.iterator();
        double dApplyAsDouble = 0.0d;
        double dApplyAsDouble2 = 0.0d;
        while (it.hasNext()) {
            dApplyAsDouble2 += toDoubleFunction.applyAsDouble(it.next());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (dApplyAsDouble > d * dApplyAsDouble2) {
                break;
            }
            arrayList2.add(obj);
            dApplyAsDouble += toDoubleFunction.applyAsDouble(obj);
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareByCpuDesc(ExportedCallStat exportedCallStat, ExportedCallStat exportedCallStat2) {
        return Long.compare(exportedCallStat2.cpuTimeMicros, exportedCallStat.cpuTimeMicros);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareByBinderClassAndCode(ExportedCallStat exportedCallStat, ExportedCallStat exportedCallStat2) {
        int iCompareTo = exportedCallStat.className.compareTo(exportedCallStat2.className);
        return iCompareTo != 0 ? iCompareTo : Integer.compare(exportedCallStat.transactionCode, exportedCallStat2.transactionCode);
    }
}
