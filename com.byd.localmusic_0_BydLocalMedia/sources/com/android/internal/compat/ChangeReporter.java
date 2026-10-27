package com.android.internal.compat;

import android.util.Log;
import android.util.Slog;
import com.android.internal.util.FrameworkStatsLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class ChangeReporter {
    public static final int SOURCE_APP_PROCESS = 1;
    public static final int SOURCE_SYSTEM_SERVER = 2;
    public static final int SOURCE_UNKNOWN_SOURCE = 0;
    public static final int STATE_DISABLED = 2;
    public static final int STATE_ENABLED = 1;
    public static final int STATE_LOGGED = 3;
    public static final int STATE_UNKNOWN_STATE = 0;
    private static final String TAG = "CompatibilityChangeReporter";
    private int mSource;
    private final Map<Integer, Set<ChangeReport>> mReportedChanges = new HashMap();
    private boolean mDebugLogAll = false;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Source {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    private static String stateToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "LOGGED" : "DISABLED" : "ENABLED";
    }

    private static final class ChangeReport {
        long mChangeId;
        int mState;

        ChangeReport(long j, int i) {
            this.mChangeId = j;
            this.mState = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ChangeReport changeReport = (ChangeReport) obj;
            return this.mChangeId == changeReport.mChangeId && this.mState == changeReport.mState;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.mChangeId), Integer.valueOf(this.mState));
        }
    }

    public ChangeReporter(int i) {
        this.mSource = i;
    }

    public void reportChange(int i, long j, int i2) {
        if (shouldWriteToStatsLog(i, j, i2)) {
            FrameworkStatsLog.write(228, i, j, i2, this.mSource);
        }
        if (shouldWriteToDebug(i, j, i2)) {
            debugLog(i, j, i2);
        }
        markAsReported(i, new ChangeReport(j, i2));
    }

    public void startDebugLogAll() {
        this.mDebugLogAll = true;
    }

    public void stopDebugLogAll() {
        this.mDebugLogAll = false;
    }

    public boolean shouldWriteToStatsLog(int i, long j, int i2) {
        return !isAlreadyReported(i, new ChangeReport(j, i2));
    }

    public boolean shouldWriteToDebug(int i, long j, int i2) {
        return this.mDebugLogAll || !isAlreadyReported(i, new ChangeReport(j, i2));
    }

    private boolean isAlreadyReported(int i, ChangeReport changeReport) {
        synchronized (this.mReportedChanges) {
            Set<ChangeReport> set = this.mReportedChanges.get(Integer.valueOf(i));
            if (set == null) {
                return false;
            }
            return set.contains(changeReport);
        }
    }

    private void markAsReported(int i, ChangeReport changeReport) {
        synchronized (this.mReportedChanges) {
            Set<ChangeReport> set = this.mReportedChanges.get(Integer.valueOf(i));
            if (set == null) {
                this.mReportedChanges.put(Integer.valueOf(i), new HashSet());
                set = this.mReportedChanges.get(Integer.valueOf(i));
            }
            set.add(changeReport);
        }
    }

    public void resetReportedChanges(int i) {
        synchronized (this.mReportedChanges) {
            this.mReportedChanges.remove(Integer.valueOf(i));
        }
    }

    private void debugLog(int i, long j, int i2) {
        String str = String.format("Compat change id reported: %d; UID %d; state: %s", Long.valueOf(j), Integer.valueOf(i), stateToString(i2));
        if (this.mSource == 2) {
            Slog.d(TAG, str);
        } else {
            Log.d(TAG, str);
        }
    }
}
