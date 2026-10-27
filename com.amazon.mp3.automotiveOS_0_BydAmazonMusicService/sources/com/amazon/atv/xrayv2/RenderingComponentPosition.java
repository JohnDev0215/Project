package com.amazon.atv.xrayv2;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class RenderingComponentPosition {
    public final HorizontalPosition horizontal;
    public final VerticalPosition vertical;

    private RenderingComponentPosition(Builder builder) {
        this.vertical = (VerticalPosition) Preconditions.checkNotNull(builder.vertical, "Unexpected null field: vertical");
        this.horizontal = (HorizontalPosition) Preconditions.checkNotNull(builder.horizontal, "Unexpected null field: horizontal");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RenderingComponentPosition)) {
            return false;
        }
        RenderingComponentPosition renderingComponentPosition = (RenderingComponentPosition) obj;
        return Objects.equal(this.vertical, renderingComponentPosition.vertical) && Objects.equal(this.horizontal, renderingComponentPosition.horizontal);
    }

    public int hashCode() {
        return Objects.hashCode(this.vertical, this.horizontal);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("vertical", this.vertical).add("horizontal", this.horizontal).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public HorizontalPosition horizontal;
        public VerticalPosition vertical;

        public RenderingComponentPosition build() {
            return new RenderingComponentPosition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RenderingComponentPosition> {
        private final EnumParser<HorizontalPosition> mHorizontalPositionParser;
        private final EnumParser<VerticalPosition> mVerticalPositionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mVerticalPositionParser = EnumParser.newParser(VerticalPosition.class);
            this.mHorizontalPositionParser = EnumParser.newParser(HorizontalPosition.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RenderingComponentPosition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentPosition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RenderingComponentPosition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RenderingComponentPosition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RenderingComponentPosition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RenderingComponentPosition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1984141450) {
                        if (iHashCode == 1387629604 && next.equals("horizontal")) {
                            b = 1;
                        }
                    } else if (next.equals("vertical")) {
                        b = 0;
                    }
                    HorizontalPosition horizontalPosition = null;
                    VerticalPosition verticalPosition = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            verticalPosition = (VerticalPosition) this.mVerticalPositionParser.parse(jsonNode2);
                        }
                        builder.vertical = verticalPosition;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            horizontalPosition = (HorizontalPosition) this.mHorizontalPositionParser.parse(jsonNode2);
                        }
                        builder.horizontal = horizontalPosition;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RenderingComponentPosition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.vertical, this, "vertical");
            JsonParsingUtils.checkNotNull(builder.horizontal, this, "horizontal");
            return builder.build();
        }

        @Nonnull
        private RenderingComponentPosition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1984141450) {
                                if (iHashCode == 1387629604 && currentName.equals("horizontal")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("vertical")) {
                                b = 0;
                            }
                            HorizontalPosition horizontalPosition = null;
                            VerticalPosition verticalPosition = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    verticalPosition = (VerticalPosition) this.mVerticalPositionParser.parse(jsonParser);
                                }
                                builder.vertical = verticalPosition;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    horizontalPosition = (HorizontalPosition) this.mHorizontalPositionParser.parse(jsonParser);
                                }
                                builder.horizontal = horizontalPosition;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RenderingComponentPosition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.vertical, this, "vertical");
                    JsonParsingUtils.checkNotNull(builder.horizontal, this, "horizontal");
                    return builder.build();
                }
            }
        }
    }
}
