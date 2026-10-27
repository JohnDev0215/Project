package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TitleActionAggregationException {
    public final Optional<String> message;

    @NotThreadSafe
    public static abstract class Builder {
        public String message;
    }

    protected TitleActionAggregationException(Builder builder) {
        this.message = Optional.fromNullable(builder.message);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TitleActionAggregationException) {
            return Objects.equal(this.message, ((TitleActionAggregationException) obj).message);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.message);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("message", this.message).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<TitleActionAggregationException> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.TITLEACTIONAGGREGATIONEXCEPTION_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
