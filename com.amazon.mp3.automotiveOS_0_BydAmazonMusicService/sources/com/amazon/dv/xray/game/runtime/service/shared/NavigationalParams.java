package com.amazon.dv.xray.game.runtime.service.shared;

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
public class NavigationalParams {
    public final String clientCacheKey;
    public final String gameTabWidgetId;

    private NavigationalParams(Builder builder) {
        this.clientCacheKey = (String) Preconditions.checkNotNull(builder.clientCacheKey, "Unexpected null field: clientCacheKey");
        this.gameTabWidgetId = (String) Preconditions.checkNotNull(builder.gameTabWidgetId, "Unexpected null field: gameTabWidgetId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NavigationalParams)) {
            return false;
        }
        NavigationalParams navigationalParams = (NavigationalParams) obj;
        return Objects.equal(this.clientCacheKey, navigationalParams.clientCacheKey) && Objects.equal(this.gameTabWidgetId, navigationalParams.gameTabWidgetId);
    }

    public int hashCode() {
        return Objects.hashCode(this.clientCacheKey, this.gameTabWidgetId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("clientCacheKey", this.clientCacheKey).add("gameTabWidgetId", this.gameTabWidgetId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String clientCacheKey;
        public String gameTabWidgetId;

        public NavigationalParams build() {
            return new NavigationalParams(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<NavigationalParams> {
        private final SimpleParsers.StringParser mStringValueParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringValueParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public NavigationalParams parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NavigationalParams:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public NavigationalParams parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NavigationalParams:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private NavigationalParams parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "NavigationalParams");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -289960856) {
                        if (iHashCode == 817263074 && next.equals("gameTabWidgetId")) {
                            b = 1;
                        }
                    } else if (next.equals("clientCacheKey")) {
                        b = 0;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringValueParser.parse(jsonNode2);
                        }
                        builder.clientCacheKey = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringValueParser.parse(jsonNode2);
                        }
                        builder.gameTabWidgetId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NavigationalParams so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.clientCacheKey, this, "clientCacheKey");
            JsonParsingUtils.checkNotNull(builder.gameTabWidgetId, this, "gameTabWidgetId");
            return builder.build();
        }

        @Nonnull
        private NavigationalParams parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -289960856) {
                                if (iHashCode == 817263074 && currentName.equals("gameTabWidgetId")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("clientCacheKey")) {
                                b = 0;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringValueParser.parse(jsonParser);
                                }
                                builder.clientCacheKey = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringValueParser.parse(jsonParser);
                                }
                                builder.gameTabWidgetId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NavigationalParams so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.clientCacheKey, this, "clientCacheKey");
                    JsonParsingUtils.checkNotNull(builder.gameTabWidgetId, this, "gameTabWidgetId");
                    return builder.build();
                }
            }
        }
    }
}
