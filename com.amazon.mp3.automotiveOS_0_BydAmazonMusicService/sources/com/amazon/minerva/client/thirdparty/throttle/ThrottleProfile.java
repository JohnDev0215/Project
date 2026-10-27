package com.amazon.minerva.client.thirdparty.throttle;

/* JADX INFO: loaded from: classes3.dex */
public class ThrottleProfile {
    private double mCredit;
    private long mTimestampInMillis;

    public ThrottleProfile(long j, double d) {
        setTimestamp(j);
        setCredit(d);
    }

    public long getTimestamp() {
        return this.mTimestampInMillis;
    }

    public void setTimestamp(long j) {
        this.mTimestampInMillis = j;
    }

    public double getCredit() {
        return this.mCredit;
    }

    public void setCredit(double d) {
        if (d < 0.0d) {
            throw new IllegalArgumentException("Credit is invalid!");
        }
        this.mCredit = d;
    }
}
