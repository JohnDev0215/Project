package com.amazon.atvtransitiontimecodeservice;

import com.amazon.atvtransitiontimecodeservice.types.ElementType;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SkipElement {
    public final Optional<Long> buttonShowtimeMs;
    public final Optional<ElementType> elementType;
    public final Optional<Long> endTimecodeMs;
    public final Optional<Long> startTimecodeMs;

    private SkipElement(Builder builder) {
        this.startTimecodeMs = Optional.fromNullable(builder.startTimecodeMs);
        this.endTimecodeMs = Optional.fromNullable(builder.endTimecodeMs);
        this.buttonShowtimeMs = Optional.fromNullable(builder.buttonShowtimeMs);
        this.elementType = Optional.fromNullable(builder.elementType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SkipElement)) {
            return false;
        }
        SkipElement skipElement = (SkipElement) obj;
        return Objects.equal(this.startTimecodeMs, skipElement.startTimecodeMs) && Objects.equal(this.endTimecodeMs, skipElement.endTimecodeMs) && Objects.equal(this.buttonShowtimeMs, skipElement.buttonShowtimeMs) && Objects.equal(this.elementType, skipElement.elementType);
    }

    public int hashCode() {
        return Objects.hashCode(this.startTimecodeMs, this.endTimecodeMs, this.buttonShowtimeMs, this.elementType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("startTimecodeMs", this.startTimecodeMs).add("endTimecodeMs", this.endTimecodeMs).add("buttonShowtimeMs", this.buttonShowtimeMs).add("elementType", this.elementType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long buttonShowtimeMs;
        public ElementType elementType;
        public Long endTimecodeMs;
        public Long startTimecodeMs;

        public SkipElement build() {
            return new SkipElement(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SkipElement> {
        private final EnumParser<ElementType> mElementTypeParser;
        private final SimpleParsers.LongParser mLongParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mElementTypeParser = EnumParser.newParser(ElementType.class);
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SkipElement parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SkipElement:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SkipElement parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SkipElement:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SkipElement parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SkipElement");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1148370046:
                            if (next.equals("startTimecodeMs")) {
                                b = 0;
                            }
                            break;
                        case 75101026:
                            if (next.equals("buttonShowtimeMs")) {
                                b = 2;
                            }
                            break;
                        case 673924182:
                            if (next.equals("elementType")) {
                                b = 3;
                            }
                            break;
                        case 1383564475:
                            if (next.equals("endTimecodeMs")) {
                                b = 1;
                            }
                            break;
                    }
                    ElementType elementType = null;
                    Long l = null;
                    Long l2 = null;
                    Long l3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.startTimecodeMs = l;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            l2 = this.mLongParser.parse(jsonNode2);
                        }
                        builder.endTimecodeMs = l2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            l3 = this.mLongParser.parse(jsonNode2);
                        }
                        builder.buttonShowtimeMs = l3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            elementType = (ElementType) this.mElementTypeParser.parse(jsonNode2);
                        }
                        builder.elementType = elementType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SkipElement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SkipElement parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        JsonToken currentToken = jsonParser.getCurrentToken();
                        byte b = -1;
                        try {
                            switch (currentName.hashCode()) {
                                case -1148370046:
                                    if (currentName.equals("startTimecodeMs")) {
                                        b = 0;
                                    }
                                    break;
                                case 75101026:
                                    if (currentName.equals("buttonShowtimeMs")) {
                                        b = 2;
                                    }
                                    break;
                                case 673924182:
                                    if (currentName.equals("elementType")) {
                                        b = 3;
                                    }
                                    break;
                                case 1383564475:
                                    if (currentName.equals("endTimecodeMs")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ElementType elementType = null;
                            Long l = null;
                            Long l2 = null;
                            Long l3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mLongParser.parse(jsonParser);
                                }
                                builder.startTimecodeMs = l;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l2 = this.mLongParser.parse(jsonParser);
                                }
                                builder.endTimecodeMs = l2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l3 = this.mLongParser.parse(jsonParser);
                                }
                                builder.buttonShowtimeMs = l3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    elementType = (ElementType) this.mElementTypeParser.parse(jsonParser);
                                }
                                builder.elementType = elementType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SkipElement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
