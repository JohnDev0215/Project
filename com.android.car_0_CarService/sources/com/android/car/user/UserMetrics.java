package com.android.car.user;

import android.car.user.CarUserManager;
import android.util.LocalLog;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseLongArray;
import android.util.TimeUtils;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes3.dex */
final class UserMetrics {
    private static final int INITIAL_CAPACITY = 2;
    private static final int LOG_SIZE = 10;
    private static final String TAG = UserMetrics.class.getSimpleName();
    private int mHalResponseTime;
    private SparseArray<UserStartingMetric> mUserStartingMetrics;
    private SparseArray<UserStoppingMetric> mUserStoppingMetrics;
    private final Object mLock = new Object();
    private final LocalLog mUserStartedLogs = new LocalLog(10);
    private final LocalLog mUserStoppedLogs = new LocalLog(10);
    private final SparseLongArray mFirstUserUnlockDuration = new SparseLongArray(1);

    UserMetrics() {
    }

    public void onEvent(int eventType, long timestampMs, int fromUserId, int toUserId) {
        synchronized (this.mLock) {
            try {
                switch (eventType) {
                    case 1:
                        onUserStartingEventLocked(timestampMs, toUserId);
                        return;
                    case 2:
                        onUserSwitchingEventLocked(timestampMs, fromUserId, toUserId);
                        return;
                    case 3:
                        onUserUnlockingEventLocked(timestampMs, toUserId);
                        return;
                    case 4:
                        onUserUnlockedEventLocked(timestampMs, toUserId);
                        return;
                    case 5:
                        onUserStoppingEventLocked(timestampMs, toUserId);
                        return;
                    case 6:
                        onUserStoppedEventLocked(timestampMs, toUserId);
                        return;
                    default:
                        Slog.w(TAG, "Invalid event: " + CarUserManager.lifecycleEventTypeToString(eventType));
                        return;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void logFirstUnlockedUser(int userId, long timestampMs, long duration, int halResponseTime) {
        synchronized (this.mLock) {
            this.mHalResponseTime = halResponseTime;
            this.mFirstUserUnlockDuration.put(userId, duration);
        }
    }

    SparseArray<UserStartingMetric> getUserStartMetrics() {
        SparseArray<UserStartingMetric> sparseArray;
        synchronized (this.mLock) {
            sparseArray = this.mUserStartingMetrics;
        }
        return sparseArray;
    }

    SparseArray<UserStoppingMetric> getUserStopMetrics() {
        SparseArray<UserStoppingMetric> sparseArray;
        synchronized (this.mLock) {
            sparseArray = this.mUserStoppingMetrics;
        }
        return sparseArray;
    }

    private void onUserStartingEventLocked(long timestampMs, int userId) {
        if (this.mUserStartingMetrics == null) {
            this.mUserStartingMetrics = new SparseArray<>(2);
        }
        UserStartingMetric existingMetrics = this.mUserStartingMetrics.get(userId);
        if (existingMetrics != null) {
            Slog.w(TAG, "user re-started: " + existingMetrics);
            finishUserStartingLocked(existingMetrics, false);
        }
        this.mUserStartingMetrics.put(userId, new UserStartingMetric(userId, timestampMs));
    }

    private void onUserSwitchingEventLocked(long timestampMs, int fromUserId, int toUserId) {
        UserStartingMetric metrics = (UserStartingMetric) getExistingMetricsLocked(this.mUserStartingMetrics, toUserId);
        if (metrics == null) {
            return;
        }
        metrics.switchFromUserId = fromUserId;
        metrics.switchTime = timestampMs;
    }

    private void onUserUnlockingEventLocked(long timestampMs, int userId) {
        UserStartingMetric metrics = (UserStartingMetric) getExistingMetricsLocked(this.mUserStartingMetrics, userId);
        if (metrics == null) {
            return;
        }
        metrics.unlockingTime = timestampMs;
    }

    private void onUserUnlockedEventLocked(long timestampMs, int userId) {
        UserStartingMetric metrics = (UserStartingMetric) getExistingMetricsLocked(this.mUserStartingMetrics, userId);
        if (metrics == null) {
            return;
        }
        metrics.unlockedTime = timestampMs;
        finishUserStartingLocked(metrics, true);
    }

    private void onUserStoppingEventLocked(long timestampMs, int userId) {
        if (this.mUserStoppingMetrics == null) {
            this.mUserStoppingMetrics = new SparseArray<>(2);
        }
        UserStoppingMetric existingMetrics = this.mUserStoppingMetrics.get(userId);
        if (existingMetrics != null) {
            Slog.w(TAG, "user re-stopped: " + existingMetrics);
            finishUserStoppingLocked(existingMetrics, false);
        }
        this.mUserStoppingMetrics.put(userId, new UserStoppingMetric(userId, timestampMs));
    }

    private void onUserStoppedEventLocked(long timestampMs, int userId) {
        UserStoppingMetric metrics = (UserStoppingMetric) getExistingMetricsLocked(this.mUserStoppingMetrics, userId);
        if (metrics == null) {
            return;
        }
        metrics.shutdownTime = timestampMs;
        finishUserStoppingLocked(metrics, true);
    }

    private <T extends BaseUserMetric> T getExistingMetricsLocked(SparseArray<? extends BaseUserMetric> metrics, int userId) {
        if (metrics == null) {
            Slog.w(TAG, "getExistingMetricsLocked() should not pass null metrics, except on tests");
            return null;
        }
        T t = (T) metrics.get(userId);
        if (t == null) {
            String name = metrics == this.mUserStartingMetrics ? "starting" : "stopping";
            Slog.w(TAG, "no " + name + " metrics for user " + userId);
        }
        return t;
    }

    private void removeExistingMetricsLogged(SparseArray<? extends BaseUserMetric> metrics, int userId) {
        metrics.remove(userId);
        if (metrics.size() != 0) {
            return;
        }
        if (metrics == this.mUserStartingMetrics) {
            this.mUserStartingMetrics = null;
        } else {
            this.mUserStoppingMetrics = null;
        }
    }

    private void finishUserStartingLocked(UserStartingMetric metrics, boolean removeMetric) {
        this.mUserStartedLogs.log(metrics.toString());
        if (removeMetric) {
            removeExistingMetricsLogged(this.mUserStartingMetrics, metrics.userId);
        }
    }

    private void finishUserStoppingLocked(UserStoppingMetric metrics, boolean removeMetric) {
        this.mUserStoppedLogs.log(metrics.toString());
        if (removeMetric) {
            removeExistingMetricsLogged(this.mUserStoppingMetrics, metrics.userId);
        }
    }

    public void dump(PrintWriter pw) {
        pw.println("* User Metrics *");
        synchronized (this.mLock) {
            if (this.mFirstUserUnlockDuration.size() == 0) {
                pw.println("First user not unlocked yet");
            } else {
                pw.printf("First user (%d) unlocked in ", Integer.valueOf(this.mFirstUserUnlockDuration.keyAt(0)));
                TimeUtils.formatDuration(this.mFirstUserUnlockDuration.valueAt(0), pw);
                pw.println();
            }
            dump(pw, "starting", this.mUserStartingMetrics);
            dump(pw, "stopping", this.mUserStoppingMetrics);
            pw.printf("Last %d started users\n", 10);
            this.mUserStartedLogs.dump("  ", pw);
            pw.printf("Last %d stopped users\n", 10);
            this.mUserStoppedLogs.dump("  ", pw);
            pw.print("HAL response time: ");
            if (this.mHalResponseTime == 0) {
                pw.print("N/A");
            } else if (this.mHalResponseTime < 0) {
                pw.print("not replied yet, sent at ");
                TimeUtils.formatUptime(-this.mHalResponseTime);
            } else {
                TimeUtils.formatDuration(this.mHalResponseTime, pw);
            }
            pw.println();
        }
    }

    public void dumpFirstUserUnlockDuration(PrintWriter pw) {
        synchronized (this.mLock) {
            if (this.mFirstUserUnlockDuration.size() == 0) {
                pw.println(-1);
            } else {
                pw.println(this.mFirstUserUnlockDuration.valueAt(0));
            }
        }
    }

    private void dump(PrintWriter pw, String message, SparseArray<? extends BaseUserMetric> metrics) {
        if (metrics == null) {
            pw.printf("%sno users %s\n", "  ", message);
            return;
        }
        int size = metrics.size();
        pw.printf("%d users %s\n", Integer.valueOf(size), message);
        for (int i = 0; i < size; i++) {
            BaseUserMetric metric = metrics.valueAt(i);
            pw.printf("%s%d: ", "  ", Integer.valueOf(i));
            metric.dump(pw);
            pw.println();
        }
    }

    private abstract class BaseUserMetric {
        public final int userId;

        abstract void dump(PrintWriter printWriter);

        protected BaseUserMetric(int userId) {
            this.userId = userId;
        }

        public String toString() {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            dump(pw);
            pw.flush();
            return sw.toString();
        }
    }

    final class UserStartingMetric extends BaseUserMetric {
        public final long startTime;
        public int switchFromUserId;
        public long switchTime;
        public long unlockedTime;
        public long unlockingTime;

        UserStartingMetric(int userId, long startTime) {
            super(userId);
            this.startTime = startTime;
        }

        @Override // com.android.car.user.UserMetrics.BaseUserMetric
        public void dump(PrintWriter pw) {
            pw.printf("user=%d start=", Integer.valueOf(this.userId));
            TimeUtils.dumpTime(pw, this.startTime);
            long j = this.switchTime;
            if (j > 0) {
                long delta = j - this.startTime;
                pw.print(" switch");
                int i = this.switchFromUserId;
                if (i != 0) {
                    pw.printf("(from %d)", Integer.valueOf(i));
                }
                pw.print('=');
                TimeUtils.formatDuration(delta, pw);
            }
            long j2 = this.unlockingTime;
            if (j2 > 0) {
                long delta2 = j2 - this.startTime;
                pw.print(" unlocking=");
                TimeUtils.formatDuration(delta2, pw);
            }
            long delta3 = this.unlockedTime;
            if (delta3 > 0) {
                long delta4 = delta3 - this.startTime;
                pw.print(" unlocked=");
                TimeUtils.formatDuration(delta4, pw);
            }
        }
    }

    final class UserStoppingMetric extends BaseUserMetric {
        public long shutdownTime;
        public final long stopTime;

        UserStoppingMetric(int userId, long stopTime) {
            super(userId);
            this.stopTime = stopTime;
        }

        @Override // com.android.car.user.UserMetrics.BaseUserMetric
        public void dump(PrintWriter pw) {
            pw.printf("user=%d stop=", Integer.valueOf(this.userId));
            TimeUtils.dumpTime(pw, this.stopTime);
            long j = this.shutdownTime;
            if (j > 0) {
                long delta = j - this.stopTime;
                pw.print(" shutdown=");
                TimeUtils.formatDuration(delta, pw);
            }
        }
    }
}
