package com.amazon.atv.title;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Title {
    public final Optional<String> version;

    @NotThreadSafe
    public static abstract class Builder {
        public String version;
    }

    protected Title(Builder builder) {
        this.version = Optional.fromNullable(builder.version);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Title) {
            return Objects.equal(this.version, ((Title) obj).version);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.version);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<Title> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.TITLE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
