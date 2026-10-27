package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Image;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XRayGraphComponent {
    public final Optional<String> color;
    public final Optional<ImmutableMap<String, Image>> imageMap;
    public final Optional<ImmutableMap<String, String>> textMap;
    public final double value;

    private XRayGraphComponent(Builder builder) {
        this.textMap = Optional.fromNullable(builder.textMap);
        this.value = builder.value;
        this.color = Optional.fromNullable(builder.color);
        this.imageMap = Optional.fromNullable(builder.imageMap);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayGraphComponent)) {
            return false;
        }
        XRayGraphComponent xRayGraphComponent = (XRayGraphComponent) obj;
        return Objects.equal(this.textMap, xRayGraphComponent.textMap) && Objects.equal(Double.valueOf(this.value), Double.valueOf(xRayGraphComponent.value)) && Objects.equal(this.color, xRayGraphComponent.color) && Objects.equal(this.imageMap, xRayGraphComponent.imageMap);
    }

    public int hashCode() {
        return Objects.hashCode(this.textMap, Double.valueOf(this.value), this.color, this.imageMap);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("textMap", this.textMap).add("value", this.value).add("color", this.color).add("imageMap", this.imageMap).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String color;
        public ImmutableMap<String, Image> imageMap;
        public ImmutableMap<String, String> textMap;
        public double value;

        public XRayGraphComponent build() {
            return new XRayGraphComponent(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayGraphComponent> {
        private final SimpleParsers.StringParser mColorHexCodeParser;
        private final MapParser<String, Image> mImageMapParser;
        private final MapParser<String, String> mTextMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTextMapParser = MapParser.newParser(stringParser, stringParser);
            this.mImageMapParser = MapParser.newParser(stringParser, new Image.Parser(objectMapper));
            this.mColorHexCodeParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayGraphComponent parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayGraphComponent:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayGraphComponent parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayGraphComponent:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayGraphComponent parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayGraphComponent");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1417855761:
                            if (next.equals("textMap")) {
                                b = 0;
                            }
                            break;
                        case -859618815:
                            if (next.equals("imageMap")) {
                                b = 3;
                            }
                            break;
                        case 94842723:
                            if (next.equals("color")) {
                                b = 2;
                            }
                            break;
                        case 111972721:
                            if (next.equals("value")) {
                                b = 1;
                            }
                            break;
                    }
                    ImmutableMap<String, Image> immutableMap = null;
                    ImmutableMap<String, String> immutableMap2 = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mTextMapParser.parse(jsonNode2);
                        }
                        builder.textMap = immutableMap2;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field value can't be null");
                        }
                        builder.value = SimpleParsers.parsePrimitiveDouble(jsonNode2);
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mColorHexCodeParser.parse(jsonNode2);
                        }
                        builder.color = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mImageMapParser.parse(jsonNode2);
                        }
                        builder.imageMap = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayGraphComponent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayGraphComponent parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1417855761:
                                    if (currentName.equals("textMap")) {
                                        b = 0;
                                    }
                                    break;
                                case -859618815:
                                    if (currentName.equals("imageMap")) {
                                        b = 3;
                                    }
                                    break;
                                case 94842723:
                                    if (currentName.equals("color")) {
                                        b = 2;
                                    }
                                    break;
                                case 111972721:
                                    if (currentName.equals("value")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ImmutableMap<String, Image> immutableMap = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap2 = this.mTextMapParser.parse(jsonParser);
                                }
                                builder.textMap = immutableMap2;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field value can't be null");
                                }
                                builder.value = SimpleParsers.parsePrimitiveDouble(jsonParser);
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mColorHexCodeParser.parse(jsonParser);
                                }
                                builder.color = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mImageMapParser.parse(jsonParser);
                                }
                                builder.imageMap = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayGraphComponent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
