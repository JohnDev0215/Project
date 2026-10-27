package com.google.common.cache;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, Object> VALUE_PARSERS;
    long accessExpirationDuration;

    @CheckForNull
    TimeUnit accessExpirationTimeUnit;

    @CheckForNull
    Integer concurrencyLevel;

    @CheckForNull
    Integer initialCapacity;

    @CheckForNull
    LocalCache.Strength keyStrength;

    @CheckForNull
    Long maximumSize;

    @CheckForNull
    Long maximumWeight;

    @CheckForNull
    Boolean recordStats;
    long refreshDuration;

    @CheckForNull
    TimeUnit refreshTimeUnit;
    private final String specification;

    @CheckForNull
    LocalCache.Strength valueStrength;
    long writeExpirationDuration;

    @CheckForNull
    TimeUnit writeExpirationTimeUnit;

    static {
        ImmutableMap.Builder builderPut = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser());
        LocalCache.Strength strength = LocalCache.Strength.WEAK;
        VALUE_PARSERS = builderPut.put("weakKeys", new KeyStrengthParser(strength)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(strength)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit));
    }

    public boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
        return Objects.equal(this.initialCapacity, cacheBuilderSpec.initialCapacity) && Objects.equal(this.maximumSize, cacheBuilderSpec.maximumSize) && Objects.equal(this.maximumWeight, cacheBuilderSpec.maximumWeight) && Objects.equal(this.concurrencyLevel, cacheBuilderSpec.concurrencyLevel) && Objects.equal(this.keyStrength, cacheBuilderSpec.keyStrength) && Objects.equal(this.valueStrength, cacheBuilderSpec.valueStrength) && Objects.equal(this.recordStats, cacheBuilderSpec.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(cacheBuilderSpec.writeExpirationDuration, cacheBuilderSpec.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(cacheBuilderSpec.accessExpirationDuration, cacheBuilderSpec.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(cacheBuilderSpec.refreshDuration, cacheBuilderSpec.refreshTimeUnit));
    }

    @CheckForNull
    private static Long durationInNanos(long j, @CheckForNull TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return Long.valueOf(timeUnit.toNanos(j));
    }

    static abstract class IntegerParser {
        IntegerParser() {
        }
    }

    static abstract class LongParser {
        LongParser() {
        }
    }

    static class InitialCapacityParser extends IntegerParser {
        InitialCapacityParser() {
        }
    }

    static class MaximumSizeParser extends LongParser {
        MaximumSizeParser() {
        }
    }

    static class MaximumWeightParser extends LongParser {
        MaximumWeightParser() {
        }
    }

    static class ConcurrencyLevelParser extends IntegerParser {
        ConcurrencyLevelParser() {
        }
    }

    static class KeyStrengthParser {
        private final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }
    }

    static class ValueStrengthParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }
    }

    static class RecordStatsParser {
        RecordStatsParser() {
        }
    }

    static abstract class DurationParser {
        DurationParser() {
        }
    }

    static class AccessDurationParser extends DurationParser {
        AccessDurationParser() {
        }
    }

    static class WriteDurationParser extends DurationParser {
        WriteDurationParser() {
        }
    }

    static class RefreshDurationParser extends DurationParser {
        RefreshDurationParser() {
        }
    }
}
