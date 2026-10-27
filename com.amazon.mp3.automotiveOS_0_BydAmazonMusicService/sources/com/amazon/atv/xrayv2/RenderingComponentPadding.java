package com.amazon.atv.xrayv2;

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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RenderingComponentPadding {
    public final int paddingX;
    public final int paddingY;
    public final Unit unit;

    private RenderingComponentPadding(Builder builder) {
        this.unit = (Unit) Preconditions.checkNotNull(builder.unit, "Unexpected null field: unit");
        this.paddingX = builder.paddingX;
        this.paddingY = builder.paddingY;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RenderingComponentPadding)) {
            return false;
        }
        RenderingComponentPadding renderingComponentPadding = (RenderingComponentPadding) obj;
        return Objects.equal(this.unit, renderingComponentPadding.unit) && Objects.equal(Integer.valueOf(this.paddingX), Integer.valueOf(renderingComponentPadding.paddingX)) && Objects.equal(Integer.valueOf(this.paddingY), Integer.valueOf(renderingComponentPadding.paddingY));
    }

    public int hashCode() {
        return Objects.hashCode(this.unit, Integer.valueOf(this.paddingX), Integer.valueOf(this.paddingY));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("unit", this.unit).add("paddingX", this.paddingX).add("paddingY", this.paddingY).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public int paddingX;
        public int paddingY;
        public Unit unit;

        public RenderingComponentPadding build() {
            return new RenderingComponentPadding(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RenderingComponentPadding> {
        private final EnumParser<Unit> mUnitParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mUnitParser = EnumParser.newParser(Unit.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RenderingComponentPadding parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentPadding:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RenderingComponentPadding parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentPadding:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RenderingComponentPadding parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RenderingComponentPadding");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 3594628:
                            if (next.equals("unit")) {
                                b = 0;
                            }
                            break;
                        case 773277287:
                            if (next.equals("paddingX")) {
                                b = 1;
                            }
                            break;
                        case 773277288:
                            if (next.equals("paddingY")) {
                                b = 2;
                            }
                            break;
                    }
                    if (b == 0) {
                        builder.unit = jsonNode2.isNull() ? null : (Unit) this.mUnitParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field paddingX can't be null");
                        }
                        builder.paddingX = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field paddingY can't be null");
                        }
                        builder.paddingY = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RenderingComponentPadding so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.unit, this, "unit");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.paddingX), this, "paddingX");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.paddingY), this, "paddingY");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RenderingComponentPadding parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 3594628:
                                    if (currentName.equals("unit")) {
                                        b = 0;
                                    }
                                    break;
                                case 773277287:
                                    if (currentName.equals("paddingX")) {
                                        b = 1;
                                    }
                                    break;
                                case 773277288:
                                    if (currentName.equals("paddingY")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            if (b == 0) {
                                builder.unit = currentToken == JsonToken.VALUE_NULL ? null : (Unit) this.mUnitParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field paddingX can't be null");
                                }
                                builder.paddingX = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field paddingY can't be null");
                                }
                                builder.paddingY = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RenderingComponentPadding so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.unit, this, "unit");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.paddingX), this, "paddingX");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.paddingY), this, "paddingY");
                    return builder.build();
                }
            }
        }
    }
}
