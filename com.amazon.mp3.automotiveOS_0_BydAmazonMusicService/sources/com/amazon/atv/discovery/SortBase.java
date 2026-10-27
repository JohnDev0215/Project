package com.amazon.atv.discovery;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SortBase {
    public final String text;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public String text;
        public int version;
    }

    protected SortBase(Builder builder) {
        this.version = builder.version;
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SortBase)) {
            return false;
        }
        SortBase sortBase = (SortBase) obj;
        return Objects.equal(Integer.valueOf(this.version), Integer.valueOf(sortBase.version)) && Objects.equal(this.text, sortBase.text);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.version), this.text);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).add("text", this.text).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<SortBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.SORTBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
