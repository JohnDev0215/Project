package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.Client;
import com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
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
public class GetIntlAllowedRatingsRequest extends InternationalCustomerClientRequest {
    public final String currentTerritory;
    public final boolean includeRestrictedRatings;

    private GetIntlAllowedRatingsRequest(Builder builder) {
        super(builder);
        this.currentTerritory = (String) Preconditions.checkNotNull(builder.currentTerritory, "Unexpected null field: currentTerritory");
        this.includeRestrictedRatings = builder.includeRestrictedRatings;
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest, com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetIntlAllowedRatingsRequest)) {
            return false;
        }
        GetIntlAllowedRatingsRequest getIntlAllowedRatingsRequest = (GetIntlAllowedRatingsRequest) obj;
        return super.equals(obj) && Objects.equal(this.currentTerritory, getIntlAllowedRatingsRequest.currentTerritory) && Objects.equal(Boolean.valueOf(this.includeRestrictedRatings), Boolean.valueOf(getIntlAllowedRatingsRequest.includeRestrictedRatings));
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest, com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.currentTerritory, Boolean.valueOf(this.includeRestrictedRatings));
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest, com.amazon.atv.parentalcontrols.InternationalCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("currentTerritory", this.currentTerritory).add("includeRestrictedRatings", this.includeRestrictedRatings).toString();
    }

    @NotThreadSafe
    public static class Builder extends InternationalCustomerClientRequest.Builder {
        public String currentTerritory;
        public boolean includeRestrictedRatings;

        public GetIntlAllowedRatingsRequest build() {
            return new GetIntlAllowedRatingsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetIntlAllowedRatingsRequest> {
        private final Client.Parser mClientParser;
        private final SimpleParsers.StringParser mcurrentTerritoryParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mClientParser = new Client.Parser(objectMapper);
            this.mcurrentTerritoryParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetIntlAllowedRatingsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlAllowedRatingsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetIntlAllowedRatingsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlAllowedRatingsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetIntlAllowedRatingsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetIntlAllowedRatingsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1357712437) {
                        if (iHashCode != -976788831) {
                            if (iHashCode == 889705939 && next.equals("includeRestrictedRatings")) {
                                b = 2;
                            }
                        } else if (next.equals("currentTerritory")) {
                            b = 1;
                        }
                    } else if (next.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                        b = 0;
                    }
                    String str = null;
                    Client client = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            client = this.mClientParser.parse(jsonNode2);
                        }
                        builder.client = client;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mcurrentTerritoryParser.parse(jsonNode2);
                        }
                        builder.currentTerritory = str;
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field includeRestrictedRatings can't be null");
                        }
                        builder.includeRestrictedRatings = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetIntlAllowedRatingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            JsonParsingUtils.checkNotNull(builder.currentTerritory, this, "currentTerritory");
            return builder.build();
        }

        @Nonnull
        private GetIntlAllowedRatingsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1357712437) {
                                if (iHashCode != -976788831) {
                                    if (iHashCode == 889705939 && currentName.equals("includeRestrictedRatings")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("currentTerritory")) {
                                    b = 1;
                                }
                            } else if (currentName.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                b = 0;
                            }
                            String str = null;
                            Client client = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    client = this.mClientParser.parse(jsonParser);
                                }
                                builder.client = client;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mcurrentTerritoryParser.parse(jsonParser);
                                }
                                builder.currentTerritory = str;
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field includeRestrictedRatings can't be null");
                                }
                                builder.includeRestrictedRatings = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetIntlAllowedRatingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                    JsonParsingUtils.checkNotNull(builder.currentTerritory, this, "currentTerritory");
                    return builder.build();
                }
            }
        }
    }
}
