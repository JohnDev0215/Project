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
        this.analytics = Optional.fromNullable(builder.analytics);
        this.version = builder.version;
        this.text = Optional.fromNullable(builder.text);
        this.subtext = Optional.fromNullable(builder.subtext);
        this.cache = (CacheControlPolicy) Preconditions.checkNotNull(builder.cache, "Unexpected null field: cache");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SectionBase)) {
            return false;
        }
        SectionBase sectionBase = (SectionBase) obj;
        return Objects.equal(this.analytics, sectionBase.analytics) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(sectionBase.version)) && Objects.equal(this.text, sectionBase.text) && Objects.equal(this.subtext, sectionBase.subtext) && Objects.equal(this.cache, sectionBase.cache);
    }

    public int hashCode() {
        return Objects.hashCode(this.analytics, Integer.valueOf(this.version), this.text, this.subtext, this.cache);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("analytics", this.analytics).add("version", this.version).add("text", this.text).add("subtext", this.subtext).add("cache", this.cache).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<SectionBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.SECTIONBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
