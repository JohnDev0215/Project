package com.amazon.atv.parentalcontrols.legacysupport;

import com.amazon.atv.parentalcontrols.Client;
import com.amazon.atv.parentalcontrols.CustomerClientRequest;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetLegacyClientParentalControlsSettingsRequest extends CustomerClientRequest {
    private GetLegacyClientParentalControlsSettingsRequest(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerClientRequest, com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLegacyClientParentalControlsSettingsRequest)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerClientRequest, com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerClientRequest, com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends CustomerClientRequest.Builder {
        public GetLegacyClientParentalControlsSettingsRequest build() {
            return new GetLegacyClientParentalControlsSettingsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLegacyClientParentalControlsSettingsRequest> {
        private final Client.Parser mClientParser;
        private final SimpleParsers.StringParser midentityContextParser;
        private final SimpleParsers.StringParser mlegacyCustomerIdParser;
        private final SimpleParsers.StringParser mmarketplaceIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mClientParser = new Client.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.midentityContextParser = stringParser;
            this.mlegacyCustomerIdParser = stringParser;
            this.mmarketplaceIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLegacyClientParentalControlsSettingsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLegacyClientParentalControlsSettingsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLegacyClientParentalControlsSettingsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLegacyClientParentalControlsSettingsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLegacyClientParentalControlsSettingsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLegacyClientParentalControlsSettingsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1581184615:
                            if (next.equals("customerId")) {
                                b = 3;
                            }
                            break;
                        case -1357712437:
                            if (next.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                b = 1;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 2;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    Client client = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mmarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            client = this.mClientParser.parse(jsonNode2);
                        }
                        builder.client = client;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.midentityContextParser.parse(jsonNode2);
                        }
                        builder.identityContext = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mlegacyCustomerIdParser.parse(jsonNode2);
                        }
                        builder.customerId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLegacyClientParentalControlsSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLegacyClientParentalControlsSettingsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1581184615:
                                    if (currentName.equals("customerId")) {
                                        b = 3;
                                    }
                                    break;
                                case -1357712437:
                                    if (currentName.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                        b = 1;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 2;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Client client = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mmarketplaceIdParser.parse(jsonParser);
                                }
                                builder.marketplaceId = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    client = this.mClientParser.parse(jsonParser);
                                }
                                builder.client = client;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.midentityContextParser.parse(jsonParser);
                                }
                                builder.identityContext = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mlegacyCustomerIdParser.parse(jsonParser);
                                }
                                builder.customerId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLegacyClientParentalControlsSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                    return builder.build();
                }
            }
        }
    }
}
