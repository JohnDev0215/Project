package com.amazon.music.media.playback.player;

import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/* JADX INFO: loaded from: classes4.dex */
public class PositionTracker {
    private ScheduledFuture<?> alertFuture;
    private boolean alertInBackground;
    private long checkpointPosition;
    private long checkpointTime;
    private long lastAlertPosition;
    private long nextAlertTime;
    private float runSpeed = 0.0f;
    private final Map<Runnable, Long> alerts = new HashMap();
    private boolean expectingAlert = false;
    private Runnable alertRunnable = new Runnable() { // from class: com.amazon.music.media.playback.player.PositionTracker.1
        @Override // java.lang.Runnable
        public void run() {
            PositionTracker.this.expectingAlert = false;
            PositionTracker.this.checkAlerts();
        }
    };

    public void set(long j, long j2, float f) {
        this.checkpointPosition = j;
        this.checkpointTime = j2;
        this.runSpeed = f;
        checkAlerts();
    }

    public void copyFrom(PositionTracker positionTracker) {
        set(positionTracker.checkpointPosition, positionTracker.checkpointTime, positionTracker.runSpeed);
    }

    public void start() {
        setRunSpeed(1.0f);
    }

    public void pause() {
        setRunSpeed(0.0f);
    }

    public void start(long j) {
        set(j, Clock.now(), 1.0f);
    }

    public void pause(long j) {
        set(j, Clock.now(), 0.0f);
    }

    public void setPosition(long j) {
        this.checkpointPosition = j;
        this.checkpointTime = Clock.now();
        checkAlerts();
    }

    public long getCurrentPosition() {
        return this.checkpointPosition + ((long) ((Clock.now() - this.checkpointTime) * this.runSpeed));
    }

    public long getCheckpointPosition() {
        return this.checkpointPosition;
    }

    public float getRunSpeed() {
        return this.runSpeed;
    }

    public void setRunSpeed(float f) {
        long jNow = Clock.now();
        this.checkpointPosition += (long) ((jNow - this.checkpointTime) * this.runSpeed);
        this.checkpointTime = jNow;
        this.runSpeed = f;
        checkAlerts();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e A[Catch: all -> 0x00b0, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0015, B:7:0x0020, B:9:0x0026, B:15:0x0041, B:18:0x0049, B:19:0x004e, B:21:0x0059, B:23:0x005f, B:28:0x006d, B:30:0x0081, B:32:0x0087, B:36:0x0098, B:34:0x008e, B:35:0x0095), top: B:46:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkAlerts() {
        /*
            r14 = this;
            java.util.Map<java.lang.Runnable, java.lang.Long> r0 = r14.alerts
            monitor-enter(r0)
            long r1 = r14.getCurrentPosition()     // Catch: java.lang.Throwable -> Lb0
            java.util.Map<java.lang.Runnable, java.lang.Long> r3 = r14.alerts     // Catch: java.lang.Throwable -> Lb0
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> Lb0
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r6 = 0
            if (r3 != 0) goto L58
            java.util.Map<java.lang.Runnable, java.lang.Long> r3 = r14.alerts     // Catch: java.lang.Throwable -> Lb0
            java.util.Set r3 = r3.entrySet()     // Catch: java.lang.Throwable -> Lb0
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> Lb0
            r7 = r4
        L20:
            boolean r9 = r3.hasNext()     // Catch: java.lang.Throwable -> Lb0
            if (r9 == 0) goto L59
            java.lang.Object r9 = r3.next()     // Catch: java.lang.Throwable -> Lb0
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9     // Catch: java.lang.Throwable -> Lb0
            java.lang.Object r10 = r9.getValue()     // Catch: java.lang.Throwable -> Lb0
            java.lang.Long r10 = (java.lang.Long) r10     // Catch: java.lang.Throwable -> Lb0
            long r10 = r10.longValue()     // Catch: java.lang.Throwable -> Lb0
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 < 0) goto L3b
            goto L20
        L3b:
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 <= 0) goto L41
            r7 = r10
            goto L20
        L41:
            long r12 = r14.lastAlertPosition     // Catch: java.lang.Throwable -> Lb0
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 <= 0) goto L20
            if (r6 != 0) goto L4e
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lb0
            r6.<init>()     // Catch: java.lang.Throwable -> Lb0
        L4e:
            java.lang.Object r9 = r9.getKey()     // Catch: java.lang.Throwable -> Lb0
            java.lang.Runnable r9 = (java.lang.Runnable) r9     // Catch: java.lang.Throwable -> Lb0
            r6.add(r9)     // Catch: java.lang.Throwable -> Lb0
            goto L20
        L58:
            r7 = r4
        L59:
            r14.lastAlertPosition = r1     // Catch: java.lang.Throwable -> Lb0
            int r3 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r3 >= 0) goto L6a
            float r3 = r14.getRunSpeed()     // Catch: java.lang.Throwable -> Lb0
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L6a
            r3 = 1
            goto L6b
        L6a:
            r3 = 0
        L6b:
            if (r3 == 0) goto L95
            long r3 = com.amazon.music.media.playback.util.Clock.now()     // Catch: java.lang.Throwable -> Lb0
            long r7 = r7 - r1
            float r1 = (float) r7     // Catch: java.lang.Throwable -> Lb0
            float r2 = r14.getRunSpeed()     // Catch: java.lang.Throwable -> Lb0
            float r1 = r1 / r2
            long r1 = (long) r1     // Catch: java.lang.Throwable -> Lb0
            long r1 = r1 + r3
            r7 = 10
            long r7 = r7 + r1
            boolean r5 = r14.expectingAlert     // Catch: java.lang.Throwable -> Lb0
            if (r5 == 0) goto L8e
            long r9 = r14.nextAlertTime     // Catch: java.lang.Throwable -> Lb0
            int r5 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r5 < 0) goto L8e
            r11 = 20
            long r11 = r11 + r1
            int r5 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r5 <= 0) goto L98
        L8e:
            r14.nextAlertTime = r1     // Catch: java.lang.Throwable -> Lb0
            long r7 = r7 - r3
            r14.postAlertCallback(r7)     // Catch: java.lang.Throwable -> Lb0
            goto L98
        L95:
            r14.cancelAlertCallback()     // Catch: java.lang.Throwable -> Lb0
        L98:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb0
            if (r6 == 0) goto Laf
            java.util.Iterator r0 = r6.iterator()
        L9f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Laf
            java.lang.Object r1 = r0.next()
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            r1.run()
            goto L9f
        Laf:
            return
        Lb0:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.player.PositionTracker.checkAlerts():void");
    }

    public void alertAtPosition(long j, Runnable runnable) {
        synchronized (this.alerts) {
            this.alerts.remove(runnable);
        }
        checkAlerts();
        synchronized (this.alerts) {
            this.alerts.put(runnable, Long.valueOf(j));
        }
        checkAlerts();
    }

    public void cancelAlert(Runnable runnable) {
        synchronized (this.alerts) {
            this.alerts.remove(runnable);
        }
        checkAlerts();
    }

    private void postAlertCallback(long j) {
        cancelAlertCallback();
        this.expectingAlert = true;
        if (this.alertInBackground) {
            this.alertFuture = ThreadUtils.postInBackgroundDelayed(this.alertRunnable, j);
        } else {
            ThreadUtils.postOnMainThreadDelayed(this.alertRunnable, j);
        }
    }

    private void cancelAlertCallback() {
        if (this.expectingAlert) {
            ScheduledFuture<?> scheduledFuture = this.alertFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                this.alertFuture = null;
            } else {
                ThreadUtils.cancelOnMainThread(this.alertRunnable);
            }
            this.expectingAlert = false;
        }
    }

    public String toString() {
        return "PositionTracker{checkpointPosition=" + StringUtils.getDurationString(this.checkpointPosition) + ", checkpointTime=" + this.checkpointTime + ", checkpointElapsedTime=" + StringUtils.getDurationString(Clock.now() - this.checkpointTime) + ", runSpeed=" + this.runSpeed + ", currentPosition=" + StringUtils.getDurationString(getCurrentPosition()) + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PositionTracker positionTracker = (PositionTracker) obj;
        return this.checkpointPosition == positionTracker.checkpointPosition && this.checkpointTime == positionTracker.checkpointTime && Float.compare(positionTracker.runSpeed, this.runSpeed) == 0;
    }

    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        long j = this.checkpointPosition;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.checkpointTime;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        float f = this.runSpeed;
        return i2 + (f != 0.0f ? Float.floatToIntBits(f) : 0);
    }
}
