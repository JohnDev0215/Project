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
public abstract class PageBase {
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

    protected PageBase(Builder builder) {
        this.analytics = Optional.fromNullable(builder.analytics);
        this.text = Optional.fromNullable(builder.text);
        this.version = builder.version;
        this.subtext = Optional.fromNullable(builder.subtext);
        this.cache = (CacheControlPolicy) Preconditions.checkNotNull(builder.cache, "Unexpected null field: cache");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PageBase)) {
            return false;
        }
        PageBase pageBase = (PageBase) obj;
        return Objects.equal(this.analytics, pageBase.analytics) && Objects.equal(this.text, pageBase.text) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(pageBase.version)) && Objects.equal(this.subtext, pageBase.subtext) && Objects.equal(this.cache, pageBase.cache);
    }

    public int hashCode() {
        return Objects.hashCode(this.analytics, this.text, Integer.valueOf(this.version), this.subtext, this.cache);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("analytics", this.analytics).add("text", this.text).add("version", this.version).add("subtext", this.subtext).add("cache", this.cache).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<PageBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.PAGEBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
