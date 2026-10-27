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
public class RenderingComponentSize {
    public final int height;
    public final Unit unit;
    public final int width;

    private RenderingComponentSize(Builder builder) {
        this.height = builder.height;
        this.unit = (Unit) Preconditions.checkNotNull(builder.unit, "Unexpected null field: unit");
        this.width = builder.width;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RenderingComponentSize)) {
            return false;
        }
        RenderingComponentSize renderingComponentSize = (RenderingComponentSize) obj;
        return Objects.equal(Integer.valueOf(this.height), Integer.valueOf(renderingComponentSize.height)) && Objects.equal(this.unit, renderingComponentSize.unit) && Objects.equal(Integer.valueOf(this.width), Integer.valueOf(renderingComponentSize.width));
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.height), this.unit, Integer.valueOf(this.width));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("height", this.height).add("unit", this.unit).add("width", this.width).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public int height;
        public Unit unit;
        public int width;

        public RenderingComponentSize build() {
            return new RenderingComponentSize(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RenderingComponentSize> {
        private final EnumParser<Unit> mUnitParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mUnitParser = EnumParser.newParser(Unit.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RenderingComponentSize parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentSize:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RenderingComponentSize parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentSize:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RenderingComponentSize parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RenderingComponentSize");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1221029593) {
                        if (iHashCode != 3594628) {
                            if (iHashCode == 113126854 && next.equals("width")) {
                                b = 2;
                            }
                        } else if (next.equals("unit")) {
                            b = 1;
                        }
                    } else if (next.equals("height")) {
                        b = 0;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            builder.unit = jsonNode2.isNull() ? null : (Unit) this.mUnitParser.parse(jsonNode2);
                        } else if (b == 2) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field width can't be null");
                            }
                            builder.width = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field height can't be null");
                        }
                        builder.height = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RenderingComponentSize so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.height), this, "height");
            JsonParsingUtils.checkNotNull(builder.unit, this, "unit");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.width), this, "width");
            return builder.build();
        }

        @Nonnull
        private RenderingComponentSize parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -1221029593) {
                                if (iHashCode != 3594628) {
                                    if (iHashCode == 113126854 && currentName.equals("width")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("unit")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("height")) {
                                b = 0;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    builder.unit = currentToken == JsonToken.VALUE_NULL ? null : (Unit) this.mUnitParser.parse(jsonParser);
                                } else if (b == 2) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field width can't be null");
                                    }
                                    builder.width = SimpleParsers.parsePrimitiveInt(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field height can't be null");
                                }
                                builder.height = SimpleParsers.parsePrimitiveInt(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RenderingComponentSize so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.height), this, "height");
                    JsonParsingUtils.checkNotNull(builder.unit, this, "unit");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.width), this, "width");
                    return builder.build();
                }
            }
        }
    }
}
