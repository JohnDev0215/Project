package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FilterValueBase {
    public final Optional<Analytics> analytics;
    public final boolean currentlyApplied;
    public final boolean enabled;
    public final int expectedItemCount;
    public final Optional<PrefetchPolicy> prefetchPolicy;
    public final String text;
    public final String value;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public Analytics analytics;
        public boolean currentlyApplied;
        public boolean enabled;
        public int expectedItemCount;
        public PrefetchPolicy prefetchPolicy;
        public String text;
        public String value;
        public int version;
    }

    protected FilterValueBase(Builder builder) {
        this.prefetchPolicy = Optional.fromNullable(builder.prefetchPolicy);
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.version = builder.version;
        this.value = (String) Preconditions.checkNotNull(builder.value, "Unexpected null field: value");
        this.enabled = builder.enabled;
        this.analytics = Optional.fromNullable(builder.analytics);
        this.currentlyApplied = builder.currentlyApplied;
        this.expectedItemCount = builder.expectedItemCount;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilterValueBase)) {
            return false;
        }
        FilterValueBase filterValueBase = (FilterValueBase) obj;
        return Objects.equal(this.prefetchPolicy, filterValueBase.prefetchPolicy) && Objects.equal(this.text, filterValueBase.text) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(filterValueBase.version)) && Objects.equal(this.value, filterValueBase.value) && Objects.equal(Boolean.valueOf(this.enabled), Boolean.valueOf(filterValueBase.enabled)) && Objects.equal(this.analytics, filterValueBase.analytics) && Objects.equal(Boolean.valueOf(this.currentlyApplied), Boolean.valueOf(filterValueBase.currentlyApplied)) && Objects.equal(Integer.valueOf(this.expectedItemCount), Integer.valueOf(filterValueBase.expectedItemCount));
    }

    public int hashCode() {
        return Objects.hashCode(this.prefetchPolicy, this.text, Integer.valueOf(this.version), this.value, Boolean.valueOf(this.enabled), this.analytics, Boolean.valueOf(this.currentlyApplied), Integer.valueOf(this.expectedItemCount));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("prefetchPolicy", this.prefetchPolicy).add("text", this.text).add("version", this.version).add("value", this.value).add("enabled", this.enabled).add("analytics", this.analytics).add("currentlyApplied", this.currentlyApplied).add("expectedItemCount", this.expectedItemCount).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<FilterValueBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.FILTERVALUEBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
