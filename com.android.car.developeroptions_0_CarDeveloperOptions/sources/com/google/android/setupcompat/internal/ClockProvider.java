package com.google.android.setupcompat.internal;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ClockProvider {
    private static final Ticker SYSTEM_TICKER;
    private static Ticker ticker;

    public interface Supplier<T> {
        T get();
    }

    public static long timeInNanos() {
        return ticker.read();
    }

    public static long timeInMillis() {
        return TimeUnit.NANOSECONDS.toMillis(timeInNanos());
    }

    public static void resetInstance() {
        ticker = SYSTEM_TICKER;
    }

    public static void setInstance(final Supplier<Long> supplier) {
        ticker = new Ticker() { // from class: com.google.android.setupcompat.internal.-$$Lambda$ClockProvider$yv5Dt-Hvw2C6wuTWjvKViPvtokI
            @Override // com.google.android.setupcompat.internal.Ticker
            public final long read() {
                return ((Long) supplier.get()).longValue();
            }
        };
    }

    static {
        $$Lambda$ClockProvider$Xhv6ez4NTBFUn6cNE_oxYP2IXvc __lambda_clockprovider_xhv6ez4ntbfun6cne_oxyp2ixvc = new Ticker() { // from class: com.google.android.setupcompat.internal.-$$Lambda$ClockProvider$Xhv6ez4NTBFUn6cNE_oxYP2IXvc
            @Override // com.google.android.setupcompat.internal.Ticker
            public final long read() {
                return System.nanoTime();
            }
        };
        SYSTEM_TICKER = __lambda_clockprovider_xhv6ez4ntbfun6cne_oxyp2ixvc;
        ticker = __lambda_clockprovider_xhv6ez4ntbfun6cne_oxyp2ixvc;
    }
}
