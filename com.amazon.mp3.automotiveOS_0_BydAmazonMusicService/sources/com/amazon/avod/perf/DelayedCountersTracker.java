package com.amazon.avod.perf;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class DelayedCountersTracker {
    private static final CounterFactory COUNTER_FACTORY = new CounterFactory();
    private final CounterFactory mCounterFactory;

    @GuardedBy("mMap")
    private final Map<CounterKey, DelayedCounter> mMap;

    public DelayedCountersTracker() {
        this(COUNTER_FACTORY);
    }

    DelayedCountersTracker(@Nonnull CounterFactory counterFactory) {
        this.mMap = Maps.newHashMap();
        this.mCounterFactory = counterFactory;
    }

    public void incrementCounter(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, long j) {
        getCounter(str, immutableList).increment(j);
    }

    @Nonnull
    private DelayedCounter getCounter(String str, ImmutableList<String> immutableList) {
        synchronized (this.mMap) {
            CounterKey counterKey = new CounterKey(str, immutableList);
            DelayedCounter delayedCounter = this.mMap.get(counterKey);
            if (delayedCounter != null) {
                return delayedCounter;
            }
            DelayedCounter delayedCounterCreate = this.mCounterFactory.create(str, immutableList);
            this.mMap.put(counterKey, delayedCounterCreate);
            return delayedCounterCreate;
        }
    }

    @ThreadSafe
    static class CounterFactory {
        CounterFactory() {
        }

        public DelayedCounter create(String str, ImmutableList<String> immutableList) {
            return new DelayedCounter(str, immutableList);
        }
    }

    private static class CounterKey {
        private final String mCounterName;
        private final ImmutableList<String> mTypeList;

        public CounterKey(@Nonnull String str, @Nonnull ImmutableList<String> immutableList) {
            this.mCounterName = str;
            this.mTypeList = immutableList;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CounterKey)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            CounterKey counterKey = (CounterKey) obj;
            return Objects.equal(this.mCounterName, counterKey.mCounterName) && Objects.equal(this.mTypeList, counterKey.mTypeList);
        }

        public int hashCode() {
            return Objects.hashCode(this.mCounterName, this.mTypeList);
        }
    }
}
