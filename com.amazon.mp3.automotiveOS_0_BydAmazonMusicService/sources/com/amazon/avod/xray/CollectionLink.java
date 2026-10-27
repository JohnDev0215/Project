package com.amazon.avod.xray;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class CollectionLink {
    public final int collectionIndex;
    public final String widgetId;

    private CollectionLink(Builder builder) {
        this.widgetId = (String) Preconditions.checkNotNull(builder.widgetId, "Unexpected null field: widgetId");
        this.collectionIndex = builder.collectionIndex;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionLink)) {
            return false;
        }
        CollectionLink collectionLink = (CollectionLink) obj;
        return Objects.equal(this.widgetId, collectionLink.widgetId) && Objects.equal(Integer.valueOf(this.collectionIndex), Integer.valueOf(collectionLink.collectionIndex));
    }

    public int hashCode() {
        return Objects.hashCode(this.widgetId, Integer.valueOf(this.collectionIndex));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("widgetId", this.widgetId).add("collectionIndex", this.collectionIndex).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public int collectionIndex;
        public String widgetId;

        public CollectionLink build() {
            return new CollectionLink(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CollectionLink> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CollectionLink parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CollectionLink:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CollectionLink parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CollectionLink:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CollectionLink parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CollectionLink");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1399201153) {
                        if (iHashCode == 1039989972 && next.equals("collectionIndex")) {
                            b = 1;
                        }
                    } else if (next.equals("widgetId")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.widgetId = jsonNode2.isNull() ? null : this.mStringParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field collectionIndex can't be null");
                        }
                        builder.collectionIndex = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CollectionLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.widgetId, this, "widgetId");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.collectionIndex), this, "collectionIndex");
            return builder.build();
        }

        @Nonnull
        private CollectionLink parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1399201153) {
                                if (iHashCode == 1039989972 && currentName.equals("collectionIndex")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("widgetId")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.widgetId = currentToken == JsonToken.VALUE_NULL ? null : this.mStringParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field collectionIndex can't be null");
                                }
                                builder.collectionIndex = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CollectionLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.widgetId, this, "widgetId");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.collectionIndex), this, "collectionIndex");
                    return builder.build();
                }
            }
        }
    }
}
