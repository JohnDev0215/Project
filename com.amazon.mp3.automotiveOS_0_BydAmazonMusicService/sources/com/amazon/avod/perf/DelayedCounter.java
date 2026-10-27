package com.amazon.avod.perf;

import com.google.common.collect.ImmutableList;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class DelayedCounter {
    private static final Reporter REPORTER = new Reporter();
    private final AtomicLong mAtomicLong;
    private final String mCounterName;
    private final Reporter mReporter;
    private final ImmutableList<String> mTypeList;

    public DelayedCounter(@Nonnull String str, @Nonnull ImmutableList<String> immutableList) {
        this(REPORTER, str, immutableList);
    }

    DelayedCounter(@Nonnull Reporter reporter, @Nonnull String str, @Nonnull ImmutableList<String> immutableList) {
        this.mAtomicLong = new AtomicLong(0L);
        this.mReporter = reporter;
        this.mCounterName = str;
        this.mTypeList = immutableList;
    }

    public void increment(long j) {
        this.mAtomicLong.addAndGet(j);
    }

    @ThreadSafe
    static class Reporter {
        Reporter() {
        }
    }
}
