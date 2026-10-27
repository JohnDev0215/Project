package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TitleActions {
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public int version;
    }

    protected TitleActions(Builder builder) {
        this.version = builder.version;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TitleActions) {
            return Objects.equal(Integer.valueOf(this.version), Integer.valueOf(((TitleActions) obj).version));
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.version));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<TitleActions> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.TITLEACTIONS_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
