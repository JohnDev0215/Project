package com.fasterxml.uuid;

import androidx.core.internal.view.SupportMenu;
import java.io.IOException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class UUIDTimer {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) UUIDTimer.class);
    protected final UUIDClock _clock;
    private int _clockCounter;
    private int _clockSequence;
    private long _firstUnsafeTimestamp;
    private long _lastSystemTimestamp;
    private long _lastUsedTimestamp;
    protected final Random _random;
    protected final TimestampSynchronizer _syncer;

    public UUIDTimer(Random random, TimestampSynchronizer timestampSynchronizer) throws IOException {
        this(random, timestampSynchronizer, new UUIDClock());
    }

    public UUIDTimer(Random random, TimestampSynchronizer timestampSynchronizer, UUIDClock uUIDClock) throws IOException {
        this._lastSystemTimestamp = 0L;
        this._lastUsedTimestamp = 0L;
        this._firstUnsafeTimestamp = Long.MAX_VALUE;
        this._clockCounter = 0;
        this._random = random;
        this._syncer = timestampSynchronizer;
        this._clock = uUIDClock;
        initCounters(random);
        this._lastSystemTimestamp = 0L;
        this._lastUsedTimestamp = 0L;
        if (timestampSynchronizer != null) {
            long jInitialize = timestampSynchronizer.initialize();
            if (jInitialize > this._lastUsedTimestamp) {
                this._lastUsedTimestamp = jInitialize;
            }
        }
        this._firstUnsafeTimestamp = 0L;
    }

    private void initCounters(Random random) {
        int iNextInt = random.nextInt();
        this._clockSequence = iNextInt;
        this._clockCounter = (iNextInt >> 16) & 255;
    }

    public int getClockSequence() {
        return this._clockSequence & SupportMenu.USER_MASK;
    }

    public synchronized long getTimestamp() {
        long j;
        long jCurrentTimeMillis = this._clock.currentTimeMillis();
        if (jCurrentTimeMillis < this._lastSystemTimestamp) {
            logger.warn("System time going backwards! (got value {}, last {}", Long.valueOf(jCurrentTimeMillis), Long.valueOf(this._lastSystemTimestamp));
            this._lastSystemTimestamp = jCurrentTimeMillis;
        }
        long j2 = this._lastUsedTimestamp;
        if (jCurrentTimeMillis <= j2) {
            if (this._clockCounter >= 10000) {
                long j3 = j2 - jCurrentTimeMillis;
                j2++;
                logger.warn("Timestamp over-run: need to reinitialize random sequence");
                initCounters(this._random);
                if (j3 >= 100) {
                    slowDown(jCurrentTimeMillis, j3);
                }
            }
            jCurrentTimeMillis = j2;
        } else {
            this._clockCounter &= 255;
        }
        this._lastUsedTimestamp = jCurrentTimeMillis;
        TimestampSynchronizer timestampSynchronizer = this._syncer;
        if (timestampSynchronizer != null && jCurrentTimeMillis >= this._firstUnsafeTimestamp) {
            try {
                this._firstUnsafeTimestamp = timestampSynchronizer.update(jCurrentTimeMillis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to synchronize timestamp: " + e);
            }
        }
        int i = this._clockCounter;
        j = (jCurrentTimeMillis * 10000) + 122192928000000000L + ((long) i);
        this._clockCounter = i + 1;
        return j;
    }

    protected static void slowDown(long j, long j2) {
        long j3 = j2 / 100;
        long j4 = 2;
        if (j3 < 2) {
            j4 = 1;
        } else if (j3 >= 10) {
            j4 = j3 < 600 ? 3L : 5L;
        }
        logger.warn("Need to wait for {} milliseconds; virtual clock advanced too far in the future", Long.valueOf(j4));
        long j5 = j + j4;
        int i = 0;
        while (true) {
            try {
                Thread.sleep(j4);
            } catch (InterruptedException unused) {
            }
            i++;
            if (i > 50 || System.currentTimeMillis() >= j5) {
                return;
            } else {
                j4 = 1;
            }
        }
    }
}
