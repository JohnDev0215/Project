package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.avtitleactionaggregationservice.model.TitleActionAggregationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TitleActionAggregationRetryableException extends TitleActionAggregationException {

    @NotThreadSafe
    public static abstract class Builder extends TitleActionAggregationException.Builder {
    }

    protected TitleActionAggregationRetryableException(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.avtitleactionaggregationservice.model.TitleActionAggregationException
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleActionAggregationRetryableException)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.avtitleactionaggregationservice.model.TitleActionAggregationException
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.avtitleactionaggregationservice.model.TitleActionAggregationException
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<TitleActionAggregationRetryableException> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.TITLEACTIONAGGREGATIONRETRYABLEEXCEPTION_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
