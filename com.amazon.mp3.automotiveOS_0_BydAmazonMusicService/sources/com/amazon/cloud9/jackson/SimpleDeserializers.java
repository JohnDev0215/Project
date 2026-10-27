package com.amazon.cloud9.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleDeserializers {
    @CheckForNull
    public static final String deserializeString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return jsonParser.getText();
    }

    @CheckForNull
    public static final Integer deserializeInteger(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Integer.valueOf(jsonParser.getIntValue());
    }

    @CheckForNull
    public static final Long deserializeLong(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Long.valueOf(jsonParser.getLongValue());
    }

    @CheckForNull
    public static final Float deserializeFloat(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Float.valueOf(jsonParser.getFloatValue());
    }

    @CheckForNull
    public static final Double deserializeDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Double.valueOf(jsonParser.getDoubleValue());
    }

    @SuppressWarnings(justification = "Return value is expected to be nullable.", value = {"NP_BOOLEAN_RETURN_NULL"})
    @CheckForNull
    public static final Boolean deserializeBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return Boolean.valueOf(jsonParser.getBooleanValue());
    }

    @CheckForNull
    public static final int deserializePrimitiveInt(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return jsonParser.getIntValue();
    }

    @CheckForNull
    public static final long deserializePrimitiveLong(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return jsonParser.getLongValue();
    }

    @CheckForNull
    public static final double deserializePrimitiveDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return jsonParser.getDoubleValue();
    }

    @CheckForNull
    public static final boolean deserializePrimitiveBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return jsonParser.getBooleanValue();
    }

    @CheckForNull
    public static final Date deserializeDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return new Date((long) (jsonParser.getDoubleValue() * 1000.0d));
    }

    @CheckForNull
    public static final BigDecimal deserializeBigDecimal(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return jsonParser.getDecimalValue();
    }
}
