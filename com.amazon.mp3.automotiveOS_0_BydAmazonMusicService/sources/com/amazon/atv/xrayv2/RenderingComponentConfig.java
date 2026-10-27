package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.RenderingComponentPadding;
import com.amazon.atv.xrayv2.RenderingComponentPosition;
import com.amazon.atv.xrayv2.RenderingComponentSize;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class RenderingComponentConfig {
    public final RenderingComponentPadding padding;
    public final RenderingComponentPosition position;
    public final RenderingComponentSize size;

    private RenderingComponentConfig(Builder builder) {
        this.size = (RenderingComponentSize) Preconditions.checkNotNull(builder.size, "Unexpected null field: size");
        this.padding = (RenderingComponentPadding) Preconditions.checkNotNull(builder.padding, "Unexpected null field: padding");
        this.position = (RenderingComponentPosition) Preconditions.checkNotNull(builder.position, "Unexpected null field: position");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RenderingComponentConfig)) {
            return false;
        }
        RenderingComponentConfig renderingComponentConfig = (RenderingComponentConfig) obj;
        return Objects.equal(this.size, renderingComponentConfig.size) && Objects.equal(this.padding, renderingComponentConfig.padding) && Objects.equal(this.position, renderingComponentConfig.position);
    }

    public int hashCode() {
        return Objects.hashCode(this.size, this.padding, this.position);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("size", this.size).add("padding", this.padding).add("position", this.position).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public RenderingComponentPadding padding;
        public RenderingComponentPosition position;
        public RenderingComponentSize size;

        public RenderingComponentConfig build() {
            return new RenderingComponentConfig(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RenderingComponentConfig> {
        private final RenderingComponentPadding.Parser mRenderingComponentPaddingParser;
        private final RenderingComponentPosition.Parser mRenderingComponentPositionParser;
        private final RenderingComponentSize.Parser mRenderingComponentSizeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRenderingComponentPaddingParser = new RenderingComponentPadding.Parser(objectMapper);
            this.mRenderingComponentSizeParser = new RenderingComponentSize.Parser(objectMapper);
            this.mRenderingComponentPositionParser = new RenderingComponentPosition.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RenderingComponentConfig parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentConfig:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RenderingComponentConfig parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentConfig:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RenderingComponentConfig parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RenderingComponentConfig");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -806339567) {
                        if (iHashCode != 3530753) {
                            if (iHashCode == 747804969 && next.equals("position")) {
                                b = 2;
                            }
                        } else if (next.equals("size")) {
                            b = 0;
                        }
                    } else if (next.equals("padding")) {
                        b = 1;
                    }
                    RenderingComponentPosition renderingComponentPosition = null;
                    RenderingComponentSize renderingComponentSize = null;
                    RenderingComponentPadding renderingComponentPadding = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            renderingComponentSize = this.mRenderingComponentSizeParser.parse(jsonNode2);
                        }
                        builder.size = renderingComponentSize;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            renderingComponentPadding = this.mRenderingComponentPaddingParser.parse(jsonNode2);
                        }
                        builder.padding = renderingComponentPadding;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            renderingComponentPosition = this.mRenderingComponentPositionParser.parse(jsonNode2);
                        }
                        builder.position = renderingComponentPosition;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RenderingComponentConfig so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.size, this, "size");
            JsonParsingUtils.checkNotNull(builder.padding, this, "padding");
            JsonParsingUtils.checkNotNull(builder.position, this, "position");
            return builder.build();
        }

        @Nonnull
        private RenderingComponentConfig parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -806339567) {
                                if (iHashCode != 3530753) {
                                    if (iHashCode == 747804969 && currentName.equals("position")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("size")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("padding")) {
                                b = 1;
                            }
                            RenderingComponentPosition renderingComponentPosition = null;
                            RenderingComponentSize renderingComponentSize = null;
                            RenderingComponentPadding renderingComponentPadding = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    renderingComponentSize = this.mRenderingComponentSizeParser.parse(jsonParser);
                                }
                                builder.size = renderingComponentSize;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    renderingComponentPadding = this.mRenderingComponentPaddingParser.parse(jsonParser);
                                }
                                builder.padding = renderingComponentPadding;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    renderingComponentPosition = this.mRenderingComponentPositionParser.parse(jsonParser);
                                }
                                builder.position = renderingComponentPosition;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RenderingComponentConfig so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.size, this, "size");
                    JsonParsingUtils.checkNotNull(builder.padding, this, "padding");
                    JsonParsingUtils.checkNotNull(builder.position, this, "position");
                    return builder.build();
                }
            }
        }
    }
}
