package com.concurrent;

import kotlin.Metadata;

/* JADX INFO: compiled from: RateLimiter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/concurrent/RateLimiter;", "", "tryConsume", "", "num", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface RateLimiter {
    boolean tryConsume(int num);

    /* JADX INFO: compiled from: RateLimiter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ boolean tryConsume$default(RateLimiter rateLimiter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryConsume");
            }
            if ((i2 & 1) != 0) {
                i = 1;
            }
            return rateLimiter.tryConsume(i);
        }
    }
}
