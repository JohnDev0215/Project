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
public abstract class SortOptionBase {
    public final Optional<Analytics> analytics;
    public final boolean currentlyApplied;
    public final SortOrder order;
    public final Optional<PrefetchPolicy> prefetchPolicy;
    public final String text;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public Analytics analytics;
        public boolean currentlyApplied;
        public SortOrder order;
        public PrefetchPolicy prefetchPolicy;
        public String text;
        public int version;
    }

    protected SortOptionBase(Builder builder) {
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.version = builder.version;
        this.order = (SortOrder) Preconditions.checkNotNull(builder.order, "Unexpected null field: order");
        this.prefetchPolicy = Optional.fromNullable(builder.prefetchPolicy);
        this.analytics = Optional.fromNullable(builder.analytics);
        this.currentlyApplied = builder.currentlyApplied;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SortOptionBase)) {
            return false;
        }
        SortOptionBase sortOptionBase = (SortOptionBase) obj;
        return Objects.equal(this.text, sortOptionBase.text) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(sortOptionBase.version)) && Objects.equal(this.order, sortOptionBase.order) && Objects.equal(this.prefetchPolicy, sortOptionBase.prefetchPolicy) && Objects.equal(this.analytics, sortOptionBase.analytics) && Objects.equal(Boolean.valueOf(this.currentlyApplied), Boolean.valueOf(sortOptionBase.currentlyApplied));
    }

    public int hashCode() {
        return Objects.hashCode(this.text, Integer.valueOf(this.version), this.order, this.prefetchPolicy, this.analytics, Boolean.valueOf(this.currentlyApplied));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.text).add("version", this.version).add("order", this.order).add("prefetchPolicy", this.prefetchPolicy).add("analytics", this.analytics).add("currentlyApplied", this.currentlyApplied).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<SortOptionBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.SORTOPTIONBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
