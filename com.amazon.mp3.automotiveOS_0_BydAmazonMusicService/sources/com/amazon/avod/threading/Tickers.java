package com.amazon.avod.threading;

import android.os.SystemClock;
import com.google.common.base.Ticker;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Tickers {
    private static final Ticker ANDROID_TICKER = new Ticker() { // from class: com.amazon.avod.threading.Tickers.1
        @Override // com.google.common.base.Ticker
        public long read() {
            return TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
        }
    };

    public static Ticker androidTicker() {
        return ANDROID_TICKER;
    }

    public static Ticker systemTicker() {
        return Ticker.systemTicker();
    }

    private Tickers() {
    }
}
