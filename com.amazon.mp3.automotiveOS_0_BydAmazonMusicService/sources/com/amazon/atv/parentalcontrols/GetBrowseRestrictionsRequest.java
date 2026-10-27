package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.Client;
import com.amazon.atv.parentalcontrols.CustomerClientRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class GetBrowseRestrictionsRequest extends CustomerClientRequest {
    public final boolean kidFriendly;
    public final boolean onlyAllowedRatings;
    public final BrowseType type;

    private GetBrowseRestrictionsRequest(Builder builder) {
        super(builder);
        this.kidFriendly = builder.kidFriendly;
        this.type = (BrowseType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.onlyAllowedRatings = builder.onlyAllowedRatings;
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerClientRequest, com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetBrowseRestrictionsRequest)) {
            return false;
        }
        GetBrowseRestrictionsRequest getBrowseRestrictionsRequest = (GetBrowseRestrictionsRequest) obj;
        return super.equals(obj) && Objects.equal(Boolean.valueOf(this.kidFriendly), Boolean.valueOf(getBrowseRestrictionsRequest.kidFriendly)) && Objects.equal(this.type, getBrowseRestrictionsRequest.type) && Objects.equal(Boolean.valueOf(this.onlyAllowedRatings), Boolean.valueOf(getBrowseRestrictionsRequest.onlyAllowedRatings));
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerClientRequest, com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Boolean.valueOf(this.kidFriendly), this.type, Boolean.valueOf(this.onlyAllowedRatings));
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerClientRequest, com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("kidFriendly", this.kidFriendly).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("onlyAllowedRatings", this.onlyAllowedRatings).toString();
    }

    @NotThreadSafe
    public static class Builder extends CustomerClientRequest.Builder {
        public boolean kidFriendly;
        public boolean onlyAllowedRatings;
        public BrowseType type;

        public GetBrowseRestrictionsRequest build() {
            return new GetBrowseRestrictionsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetBrowseRestrictionsRequest> {
        private final Client.Parser mClientParser;
        private final EnumParser<BrowseType> mbrowseTypeParser;
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
            this.mbrowseTypeParser = EnumParser.newParser(BrowseType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetBrowseRestrictionsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetBrowseRestrictionsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetBrowseRestrictionsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetBrowseRestrictionsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetBrowseRestrictionsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetBrowseRestrictionsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1943963014:
                            if (next.equals("onlyAllowedRatings")) {
                                b = 6;
                            }
                            break;
                        case -1581184615:
                            if (next.equals("customerId")) {
                                b = 5;
                            }
                            break;
                        case -1357712437:
                            if (next.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                b = 1;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 4;
                            }
                            break;
                        case 125420945:
                            if (next.equals("kidFriendly")) {
                                b = 2;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 3;
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
                    BrowseType browseType = null;
                    String str3 = null;
                    Client client = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mmarketplaceIdParser.parse(jsonNode2);
                            }
                            builder.marketplaceId = str;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                client = this.mClientParser.parse(jsonNode2);
                            }
                            builder.client = client;
                            break;
                        case 2:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field kidFriendly can't be null");
                            }
                            builder.kidFriendly = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                str3 = this.midentityContextParser.parse(jsonNode2);
                            }
                            builder.identityContext = str3;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                browseType = (BrowseType) this.mbrowseTypeParser.parse(jsonNode2);
                            }
                            builder.type = browseType;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mlegacyCustomerIdParser.parse(jsonNode2);
                            }
                            builder.customerId = str2;
                            break;
                        case 6:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field onlyAllowedRatings can't be null");
                            }
                            builder.onlyAllowedRatings = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetBrowseRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.kidFriendly), this, "kidFriendly");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.onlyAllowedRatings), this, "onlyAllowedRatings");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetBrowseRestrictionsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1943963014:
                                    if (currentName.equals("onlyAllowedRatings")) {
                                        b = 6;
                                    }
                                    break;
                                case -1581184615:
                                    if (currentName.equals("customerId")) {
                                        b = 5;
                                    }
                                    break;
                                case -1357712437:
                                    if (currentName.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                        b = 1;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 4;
                                    }
                                    break;
                                case 125420945:
                                    if (currentName.equals("kidFriendly")) {
                                        b = 2;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 3;
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
                            BrowseType browseType = null;
                            String str3 = null;
                            Client client = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mmarketplaceIdParser.parse(jsonParser);
                                    }
                                    builder.marketplaceId = str;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        client = this.mClientParser.parse(jsonParser);
                                    }
                                    builder.client = client;
                                    break;
                                case 2:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field kidFriendly can't be null");
                                    }
                                    builder.kidFriendly = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.midentityContextParser.parse(jsonParser);
                                    }
                                    builder.identityContext = str3;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        browseType = (BrowseType) this.mbrowseTypeParser.parse(jsonParser);
                                    }
                                    builder.type = browseType;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mlegacyCustomerIdParser.parse(jsonParser);
                                    }
                                    builder.customerId = str2;
                                    break;
                                case 6:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field onlyAllowedRatings can't be null");
                                    }
                                    builder.onlyAllowedRatings = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetBrowseRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.kidFriendly), this, "kidFriendly");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.onlyAllowedRatings), this, "onlyAllowedRatings");
                    return builder.build();
                }
            }
        }
    }
}
