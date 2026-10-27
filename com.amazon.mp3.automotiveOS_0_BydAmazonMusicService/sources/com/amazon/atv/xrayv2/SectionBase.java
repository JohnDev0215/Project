package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.CacheControlPolicy;
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
public abstract class SectionBase {
    public final Optional<Analytics> analytics;
    public final CacheControlPolicy cache;
    public final Optional<String> subtext;
    public final Optional<String> text;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public Analytics analytics;
        public CacheControlPolicy cache;
        public String subtext;
        public String text;
        public int version;
    }

    protected SectionBase(Builder builder) {
        this.cache = (CacheControlPolicy) Preconditions.checkNotNull(builder.cache, "Unexpected null field: cache");
        this.text = Optional.fromNullable(builder.text);
        this.version = builder.version;
        this.analytics = Optional.fromNullable(builder.analytics);
        this.subtext = Optional.fromNullable(builder.subtext);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SectionBase)) {
            return false;
        }
        SectionBase sectionBase = (SectionBase) obj;
        return Objects.equal(this.cache, sectionBase.cache) && Objects.equal(this.text, sectionBase.text) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(sectionBase.version)) && Objects.equal(this.analytics, sectionBase.analytics) && Objects.equal(this.subtext, sectionBase.subtext);
    }

    public int hashCode() {
        return Objects.hashCode(this.cache, this.text, Integer.valueOf(this.version), this.analytics, this.subtext);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cache", this.cache).add("text", this.text).add("version", this.version).add("analytics", this.analytics).add("subtext", this.subtext).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<SectionBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.SECTIONBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
