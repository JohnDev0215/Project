package com.amazon.avod.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Date;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleParsers {

    public static class BlobParser implements JacksonJsonParser<ByteBuffer> {
        public static final BlobParser INSTANCE = new BlobParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ByteBuffer parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isTextual()) {
                throw new JsonContractException("expected blob");
            }
            return ByteBuffer.wrap(jsonNode.binaryValue());
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ByteBuffer parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_STRING) {
                throw new JsonContractException("expected blob");
            }
            return ByteBuffer.wrap(jsonParser.getBinaryValue());
        }
    }

    public static class StringParser implements JacksonJsonParser<String> {
        public static final StringParser INSTANCE = new StringParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public String parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isTextual()) {
                throw new JsonContractException("expected string");
            }
            return jsonNode.textValue();
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public String parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_STRING && currentToken != JsonToken.FIELD_NAME) {
                throw new JsonContractException("Expected string", jsonParser.getCurrentLocation());
            }
            return jsonParser.getText();
        }
    }

    public static class IntegerParser implements JacksonJsonParser<Integer> {
        public static final IntegerParser INSTANCE = new IntegerParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Integer parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isInt()) {
                throw new JsonContractException("expected int");
            }
            return Integer.valueOf(jsonNode.intValue());
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Integer parse(JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NUMBER_INT) {
                throw new JsonContractException("expected int", jsonParser.getCurrentLocation());
            }
            return Integer.valueOf(jsonParser.getIntValue());
        }
    }

    public static class LongParser implements JacksonJsonParser<Long> {
        public static final LongParser INSTANCE = new LongParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Long parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isIntegralNumber()) {
                throw new JsonContractException("expected long");
            }
            return Long.valueOf(jsonNode.longValue());
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Long parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NUMBER_INT) {
                throw new JsonContractException("expected long", jsonParser.getCurrentLocation());
            }
            return Long.valueOf(jsonParser.getLongValue());
        }
    }

    public static class DoubleParser implements JacksonJsonParser<Double> {
        public static final DoubleParser INSTANCE = new DoubleParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Double parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isDouble()) {
                throw new JsonContractException("expected double");
            }
            return Double.valueOf(jsonNode.doubleValue());
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Double parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NUMBER_FLOAT) {
                throw new JsonContractException("expected double", jsonParser.getCurrentLocation());
            }
            return Double.valueOf(jsonParser.getDoubleValue());
        }
    }

    public static class DateParser implements JacksonJsonParser<Date> {
        public static final DateParser INSTANCE = new DateParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Date parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isNumber()) {
                throw new JsonContractException("expected date");
            }
            return new Date((long) (jsonNode.doubleValue() * 1000.0d));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Date parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
                throw new JsonContractException("expected date");
            }
            return new Date((long) (jsonParser.getValueAsDouble() * 1000.0d));
        }
    }

    public static class BigDecimalParser implements JacksonJsonParser<BigDecimal> {
        public static final BigDecimalParser INSTANCE = new BigDecimalParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BigDecimal parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isNumber()) {
                throw new JsonContractException("expected big decimal");
            }
            return jsonNode.decimalValue();
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BigDecimal parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_NUMBER_FLOAT && currentToken != JsonToken.VALUE_NUMBER_INT) {
                throw new JsonContractException("expected big decimal");
            }
            return jsonParser.getDecimalValue();
        }
    }

    public static class BooleanParser implements JacksonJsonParser<Boolean> {
        public static final BooleanParser INSTANCE = new BooleanParser();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Boolean parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonNode, "jsonNode");
            if (!jsonNode.isBoolean()) {
                throw new JsonContractException("expected boolean");
            }
            return Boolean.valueOf(jsonNode.booleanValue());
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Boolean parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Preconditions.checkNotNull(jsonParser, "parser");
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_FALSE && currentToken != JsonToken.VALUE_TRUE) {
                throw new JsonContractException("expected boolean");
            }
            return Boolean.valueOf(jsonParser.getBooleanValue());
        }
    }

    public static final int parsePrimitiveInt(@Nonnull JsonParser jsonParser) throws IOException {
        Preconditions.checkNotNull(jsonParser, "parser");
        return jsonParser.getIntValue();
    }

    public static final long parsePrimitiveLong(@Nonnull JsonParser jsonParser) throws IOException {
        Preconditions.checkNotNull(jsonParser, "parser");
        return jsonParser.getLongValue();
    }

    public static final float parsePrimitiveFloat(@Nonnull JsonParser jsonParser) throws IOException {
        Preconditions.checkNotNull(jsonParser, "parser");
        return jsonParser.getFloatValue();
    }

    public static final double parsePrimitiveDouble(@Nonnull JsonParser jsonParser) throws IOException {
        Preconditions.checkNotNull(jsonParser, "parser");
        return jsonParser.getDoubleValue();
    }

    public static final boolean parsePrimitiveBoolean(@Nonnull JsonParser jsonParser) throws IOException {
        Preconditions.checkNotNull(jsonParser, "parser");
        return jsonParser.getBooleanValue();
    }

    public static final int parsePrimitiveInt(@Nonnull JsonNode jsonNode) {
        Preconditions.checkNotNull(jsonNode, "jsonNode");
        return jsonNode.intValue();
    }

    public static final long parsePrimitiveLong(@Nonnull JsonNode jsonNode) {
        Preconditions.checkNotNull(jsonNode, "jsonNode");
        return jsonNode.longValue();
    }

    public static final float parsePrimitiveFloat(@Nonnull JsonNode jsonNode) {
        Preconditions.checkNotNull(jsonNode, "jsonNode");
        return (float) jsonNode.doubleValue();
    }

    public static final double parsePrimitiveDouble(@Nonnull JsonNode jsonNode) {
        return jsonNode.doubleValue();
    }

    public static final boolean parsePrimitiveBoolean(@Nonnull JsonNode jsonNode) {
        Preconditions.checkNotNull(jsonNode, "jsonNode");
        return jsonNode.booleanValue();
    }
}
