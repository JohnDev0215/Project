package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.CustomerRequest;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetPinInfoRequest extends CustomerRequest {
    private GetPinInfoRequest(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetPinInfoRequest)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends CustomerRequest.Builder {
        public GetPinInfoRequest build() {
            return new GetPinInfoRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetPinInfoRequest> {
        private final SimpleParsers.StringParser midentityContextParser;
        private final SimpleParsers.StringParser mlegacyCustomerIdParser;
        private final SimpleParsers.StringParser mmarketplaceIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.midentityContextParser = stringParser;
            this.mlegacyCustomerIdParser = stringParser;
            this.mmarketplaceIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetPinInfoRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPinInfoRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetPinInfoRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPinInfoRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetPinInfoRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetPinInfoRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1581184615) {
                        if (iHashCode != 197700369) {
                            if (iHashCode == 1412836550 && next.equals("marketplaceId")) {
                                b = 0;
                            }
                        } else if (next.equals("identityContext")) {
                            b = 1;
                        }
                    } else if (next.equals("customerId")) {
                        b = 2;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mmarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.midentityContextParser.parse(jsonNode2);
                        }
                        builder.identityContext = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mlegacyCustomerIdParser.parse(jsonNode2);
                        }
                        builder.customerId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetPinInfoRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            return builder.build();
        }

        @Nonnull
        private GetPinInfoRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1581184615) {
                                if (iHashCode != 197700369) {
                                    if (iHashCode == 1412836550 && currentName.equals("marketplaceId")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("identityContext")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("customerId")) {
                                b = 2;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mmarketplaceIdParser.parse(jsonParser);
                                }
                                builder.marketplaceId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.midentityContextParser.parse(jsonParser);
                                }
                                builder.identityContext = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mlegacyCustomerIdParser.parse(jsonParser);
                                }
                                builder.customerId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetPinInfoRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    return builder.build();
                }
            }
        }
    }
}
