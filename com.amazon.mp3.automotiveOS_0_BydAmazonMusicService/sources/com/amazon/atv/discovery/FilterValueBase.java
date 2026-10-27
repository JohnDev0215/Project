package com.amazon.atv.discovery;

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
        this.analytics = Optional.fromNullable(builder.analytics);
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.currentlyApplied = builder.currentlyApplied;
        this.version = builder.version;
        this.prefetchPolicy = Optional.fromNullable(builder.prefetchPolicy);
        this.value = (String) Preconditions.checkNotNull(builder.value, "Unexpected null field: value");
        this.expectedItemCount = builder.expectedItemCount;
        this.enabled = builder.enabled;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilterValueBase)) {
            return false;
        }
        FilterValueBase filterValueBase = (FilterValueBase) obj;
        return Objects.equal(this.analytics, filterValueBase.analytics) && Objects.equal(this.text, filterValueBase.text) && Objects.equal(Boolean.valueOf(this.currentlyApplied), Boolean.valueOf(filterValueBase.currentlyApplied)) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(filterValueBase.version)) && Objects.equal(this.prefetchPolicy, filterValueBase.prefetchPolicy) && Objects.equal(this.value, filterValueBase.value) && Objects.equal(Integer.valueOf(this.expectedItemCount), Integer.valueOf(filterValueBase.expectedItemCount)) && Objects.equal(Boolean.valueOf(this.enabled), Boolean.valueOf(filterValueBase.enabled));
    }

    public int hashCode() {
        return Objects.hashCode(this.analytics, this.text, Boolean.valueOf(this.currentlyApplied), Integer.valueOf(this.version), this.prefetchPolicy, this.value, Integer.valueOf(this.expectedItemCount), Boolean.valueOf(this.enabled));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("analytics", this.analytics).add("text", this.text).add("currentlyApplied", this.currentlyApplied).add("version", this.version).add("prefetchPolicy", this.prefetchPolicy).add("value", this.value).add("expectedItemCount", this.expectedItemCount).add("enabled", this.enabled).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<FilterValueBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.FILTERVALUEBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
