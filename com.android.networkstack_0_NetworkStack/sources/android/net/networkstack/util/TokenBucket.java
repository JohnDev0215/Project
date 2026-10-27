package android.net.networkstack.util;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public class TokenBucket {
    private int mAvailable;
    private final int mCapacity;
    private final int mFillDelta;
    private long mLastFill;

    public TokenBucket(int i, int i2, int i3) {
        Preconditions.checkArgumentPositive(i, "deltaMs must be strictly positive");
        this.mFillDelta = i;
        Preconditions.checkArgumentPositive(i2, "capacity must be strictly positive");
        this.mCapacity = i2;
        Preconditions.checkArgumentNonnegative(i3);
        this.mAvailable = Math.min(i3, this.mCapacity);
        this.mLastFill = scaledTime();
    }

    public TokenBucket(int i, int i2) {
        this(i, i2, i2);
    }

    public boolean get() {
        return get(1) == 1;
    }

    public int get(int i) {
        fill();
        if (i <= 0) {
            return 0;
        }
        int i2 = this.mAvailable;
        if (i > i2) {
            this.mAvailable = 0;
            return i2;
        }
        this.mAvailable = i2 - i;
        return i;
    }

    private void fill() {
        long jScaledTime = scaledTime();
        this.mAvailable = Math.min(this.mCapacity, this.mAvailable + ((int) (jScaledTime - this.mLastFill)));
        this.mLastFill = jScaledTime;
    }

    private long scaledTime() {
        return SystemClock.elapsedRealtime() / ((long) this.mFillDelta);
    }
}
