package com.amazon.music.media.playback.util;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsTimer {
    private long elapsedTime;
    private final String name;
    private long startTime;

    public MetricsTimer(String str) {
        this.name = str;
    }

    public synchronized void start() {
        if (this.startTime == 0) {
            this.startTime = Clock.now();
        }
    }

    public synchronized void stop() {
        if (this.startTime != 0) {
            this.elapsedTime += Clock.now() - this.startTime;
            this.startTime = 0L;
        }
    }

    public synchronized long getElapsedTimeMillis() {
        if (this.startTime != 0) {
            return (this.elapsedTime + Clock.now()) - this.startTime;
        }
        return this.elapsedTime;
    }

    public synchronized void addElapsedTimeMillis(long j) {
        this.elapsedTime += j;
    }

    public boolean isRunning() {
        return this.startTime != 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetricsTimer{name='");
        sb.append(this.name);
        sb.append("', elapsed=");
        sb.append(StringUtils.getDurationString(getElapsedTimeMillis()));
        sb.append(", running=");
        sb.append(this.startTime != 0);
        sb.append("}");
        return sb.toString();
    }
}
