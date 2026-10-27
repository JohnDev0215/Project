package com.concurrent;

import com.amazon.music.media.playback.util.Clock;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TokenBucketRateLimiter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0017\u0010\f\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\nR\u0016\u0010\u0013\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\nR\u0016\u0010\u0014\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/concurrent/TokenBucketRateLimiter;", "Lcom/concurrent/RateLimiter;", "", "refill", "", "num", "", "tryConsume", "", "capacity", "J", "numPerRefill", "refillPeriod", "getRefillPeriod", "()J", "Lcom/amazon/music/media/playback/util/Clock;", "clock", "Lcom/amazon/music/media/playback/util/Clock;", "numAvailable", "lastRefillTimeMS", "refillPeriodMS", "Ljava/util/concurrent/TimeUnit;", "refillPeriodUnit", "<init>", "(JJJLjava/util/concurrent/TimeUnit;Lcom/amazon/music/media/playback/util/Clock;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TokenBucketRateLimiter implements RateLimiter {
    private final long capacity;
    private final Clock clock;
    private long lastRefillTimeMS;
    private long numAvailable;
    private final long numPerRefill;
    private final long refillPeriod;
    private long refillPeriodMS;

    public TokenBucketRateLimiter(long j, long j2, long j3, TimeUnit refillPeriodUnit, Clock clock) {
        Intrinsics.checkNotNullParameter(refillPeriodUnit, "refillPeriodUnit");
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.capacity = j;
        this.numPerRefill = j2;
        this.refillPeriod = j3;
        this.clock = clock;
        this.numAvailable = j;
        this.lastRefillTimeMS = clock.getTime();
        this.refillPeriodMS = TimeUnit.MILLISECONDS.convert(j3, refillPeriodUnit);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TokenBucketRateLimiter(long j, long j2, long j3, TimeUnit timeUnit, Clock clock, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Clock clock2;
        long j4 = (i & 1) != 0 ? 1L : j;
        long j5 = (i & 2) != 0 ? 1L : j2;
        if ((i & 16) != 0) {
            Clock SYSTEM_CURRENT_TIME_MILLIS_CLOCK = Clock.SYSTEM_CURRENT_TIME_MILLIS_CLOCK;
            Intrinsics.checkNotNullExpressionValue(SYSTEM_CURRENT_TIME_MILLIS_CLOCK, "SYSTEM_CURRENT_TIME_MILLIS_CLOCK");
            clock2 = SYSTEM_CURRENT_TIME_MILLIS_CLOCK;
        } else {
            clock2 = clock;
        }
        this(j4, j5, j3, timeUnit, clock2);
    }

    @Override // com.concurrent.RateLimiter
    public synchronized boolean tryConsume(int num) {
        boolean z;
        refill();
        long j = this.numAvailable;
        long j2 = num;
        if (j < j2) {
            z = false;
        } else {
            this.numAvailable = j - j2;
            z = true;
        }
        return z;
    }

    private final void refill() {
        long time = this.clock.getTime();
        if (time > this.lastRefillTimeMS + this.refillPeriodMS) {
            this.numAvailable = Math.min(this.capacity, this.numAvailable + this.numPerRefill);
            this.lastRefillTimeMS = time;
        }
    }
}
