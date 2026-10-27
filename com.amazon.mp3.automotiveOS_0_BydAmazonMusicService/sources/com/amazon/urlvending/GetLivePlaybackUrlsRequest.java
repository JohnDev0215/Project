package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.GetLivePlaybackUrlsCustomerInfo;
import com.amazon.urlvending.GetLivePlaybackUrlsDeviceInfo;
import com.amazon.urlvending.GetLivePlaybackUrlsTitleInfo;
import com.amazon.urlvending.types.GetLivePlaybackUrlsConsumptionType;
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

/* JADX INFO: loaded from: classes5.dex */
public class GetLivePlaybackUrlsRequest {
    public final Optional<ImmutableMap<String, String>> adParams;
    public final Optional<String> capVideoQuality;
    public final Optional<GetLivePlaybackUrlsConsumptionType> consumptionType;
    public final Optional<GetLivePlaybackUrlsCustomerInfo> customerInfo;
    public final Optional<Boolean> daiDesired;
    public final Optional<GetLivePlaybackUrlsDeviceInfo> deviceInfo;
    public final Optional<String> marketplaceId;
    public final Optional<String> passthroughAdParams;
    public final Optional<String> playbackSessionTicket;
    public final Optional<GetLivePlaybackUrlsTitleInfo> titleInfo;
    public final Optional<Boolean> withDebugInfo;

    private GetLivePlaybackUrlsRequest(Builder builder) {
        this.customerInfo = Optional.fromNullable(builder.customerInfo);
        this.titleInfo = Optional.fromNullable(builder.titleInfo);
        this.adParams = Optional.fromNullable(builder.adParams);
        this.withDebugInfo = Optional.fromNullable(builder.withDebugInfo);
        this.consumptionType = Optional.fromNullable(builder.consumptionType);
        this.capVideoQuality = Optional.fromNullable(builder.capVideoQuality);
        this.marketplaceId = Optional.fromNullable(builder.marketplaceId);
        this.deviceInfo = Optional.fromNullable(builder.deviceInfo);
        this.daiDesired = Optional.fromNullable(builder.daiDesired);
        this.playbackSessionTicket = Optional.fromNullable(builder.playbackSessionTicket);
        this.passthroughAdParams = Optional.fromNullable(builder.passthroughAdParams);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsRequest)) {
            return false;
        }
        GetLivePlaybackUrlsRequest getLivePlaybackUrlsRequest = (GetLivePlaybackUrlsRequest) obj;
        return Objects.equal(this.customerInfo, getLivePlaybackUrlsRequest.customerInfo) && Objects.equal(this.titleInfo, getLivePlaybackUrlsRequest.titleInfo) && Objects.equal(this.adParams, getLivePlaybackUrlsRequest.adParams) && Objects.equal(this.withDebugInfo, getLivePlaybackUrlsRequest.withDebugInfo) && Objects.equal(this.consumptionType, getLivePlaybackUrlsRequest.consumptionType) && Objects.equal(this.capVideoQuality, getLivePlaybackUrlsRequest.capVideoQuality) && Objects.equal(this.marketplaceId, getLivePlaybackUrlsRequest.marketplaceId) && Objects.equal(this.deviceInfo, getLivePlaybackUrlsRequest.deviceInfo) && Objects.equal(this.daiDesired, getLivePlaybackUrlsRequest.daiDesired) && Objects.equal(this.playbackSessionTicket, getLivePlaybackUrlsRequest.playbackSessionTicket) && Objects.equal(this.passthroughAdParams, getLivePlaybackUrlsRequest.passthroughAdParams);
    }

    public int hashCode() {
        return Objects.hashCode(this.customerInfo, this.titleInfo, this.adParams, this.withDebugInfo, this.consumptionType, this.capVideoQuality, this.marketplaceId, this.deviceInfo, this.daiDesired, this.playbackSessionTicket, this.passthroughAdParams);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("customerInfo", this.customerInfo).add("titleInfo", this.titleInfo).add("adParams", this.adParams).add("withDebugInfo", this.withDebugInfo).add("consumptionType", this.consumptionType).add("capVideoQuality", this.capVideoQuality).add("marketplaceId", this.marketplaceId).add("deviceInfo", this.deviceInfo).add("daiDesired", this.daiDesired).add("playbackSessionTicket", this.playbackSessionTicket).add("passthroughAdParams", this.passthroughAdParams).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, String> adParams;
        public String capVideoQuality;
        public GetLivePlaybackUrlsConsumptionType consumptionType;
        public GetLivePlaybackUrlsCustomerInfo customerInfo;
        public Boolean daiDesired;
        public GetLivePlaybackUrlsDeviceInfo deviceInfo;
        public String marketplaceId;
        public String passthroughAdParams;
        public String playbackSessionTicket;
        public GetLivePlaybackUrlsTitleInfo titleInfo;
        public Boolean withDebugInfo;

        public GetLivePlaybackUrlsRequest build() {
            return new GetLivePlaybackUrlsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsRequest> {
        private final MapParser<String, String> mAdParamMapParser;
        private final EnumParser<GetLivePlaybackUrlsConsumptionType> mGetLivePlaybackUrlsConsumptionTypeParser;
        private final GetLivePlaybackUrlsCustomerInfo.Parser mGetLivePlaybackUrlsCustomerInfoParser;
        private final GetLivePlaybackUrlsDeviceInfo.Parser mGetLivePlaybackUrlsDeviceInfoParser;
        private final GetLivePlaybackUrlsTitleInfo.Parser mGetLivePlaybackUrlsTitleInfoParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.BooleanParser mbooleanParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetLivePlaybackUrlsDeviceInfoParser = new GetLivePlaybackUrlsDeviceInfo.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mAdParamMapParser = MapParser.newParser(stringParser, stringParser);
            this.mbooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mGetLivePlaybackUrlsTitleInfoParser = new GetLivePlaybackUrlsTitleInfo.Parser(objectMapper);
            this.mGetLivePlaybackUrlsConsumptionTypeParser = EnumParser.newParser(GetLivePlaybackUrlsConsumptionType.class);
            this.mStringParser = stringParser;
            this.mGetLivePlaybackUrlsCustomerInfoParser = new GetLivePlaybackUrlsCustomerInfo.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            GetLivePlaybackUrlsCustomerInfo getLivePlaybackUrlsCustomerInfo;
            GetLivePlaybackUrlsTitleInfo getLivePlaybackUrlsTitleInfo;
            ImmutableMap<String, String> immutableMap;
            Boolean bool;
            GetLivePlaybackUrlsConsumptionType getLivePlaybackUrlsConsumptionType;
            String str;
            String str2;
            GetLivePlaybackUrlsDeviceInfo getLivePlaybackUrlsDeviceInfo;
            Boolean bool2;
            String str3;
            String str4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2136076506:
                            if (next.equals("titleInfo")) {
                                b = 1;
                            }
                            break;
                        case -1806627959:
                            if (next.equals("adParams")) {
                                b = 2;
                            }
                            break;
                        case -1161269529:
                            if (next.equals("playbackSessionTicket")) {
                                b = 9;
                            }
                            break;
                        case -703791882:
                            if (next.equals("capVideoQuality")) {
                                b = 5;
                            }
                            break;
                        case -167715474:
                            if (next.equals("daiDesired")) {
                                b = 8;
                            }
                            break;
                        case 368095605:
                            if (next.equals("consumptionType")) {
                                b = 4;
                            }
                            break;
                        case 668466043:
                            if (next.equals("withDebugInfo")) {
                                b = 3;
                            }
                            break;
                        case 780852260:
                            if (next.equals("deviceInfo")) {
                                b = 7;
                            }
                            break;
                        case 900020652:
                            if (next.equals("customerInfo")) {
                                b = 0;
                            }
                            break;
                        case 915881277:
                            if (next.equals("passthroughAdParams")) {
                                b = 10;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 6;
                            }
                            break;
                    }
                    getLivePlaybackUrlsCustomerInfo = null;
                    str4 = null;
                    str3 = null;
                    bool2 = null;
                    getLivePlaybackUrlsDeviceInfo = null;
                    str2 = null;
                    str = null;
                    getLivePlaybackUrlsConsumptionType = null;
                    bool = null;
                    immutableMap = null;
                    getLivePlaybackUrlsTitleInfo = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            getLivePlaybackUrlsCustomerInfo = this.mGetLivePlaybackUrlsCustomerInfoParser.parse(jsonNode2);
                        }
                        builder.customerInfo = getLivePlaybackUrlsCustomerInfo;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            getLivePlaybackUrlsTitleInfo = this.mGetLivePlaybackUrlsTitleInfoParser.parse(jsonNode2);
                        }
                        builder.titleInfo = getLivePlaybackUrlsTitleInfo;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mAdParamMapParser.parse(jsonNode2);
                        }
                        builder.adParams = immutableMap;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            bool = this.mbooleanParser.parse(jsonNode2);
                        }
                        builder.withDebugInfo = bool;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            getLivePlaybackUrlsConsumptionType = (GetLivePlaybackUrlsConsumptionType) this.mGetLivePlaybackUrlsConsumptionTypeParser.parse(jsonNode2);
                        }
                        builder.consumptionType = getLivePlaybackUrlsConsumptionType;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.capVideoQuality = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            getLivePlaybackUrlsDeviceInfo = this.mGetLivePlaybackUrlsDeviceInfoParser.parse(jsonNode2);
                        }
                        builder.deviceInfo = getLivePlaybackUrlsDeviceInfo;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            bool2 = this.mbooleanParser.parse(jsonNode2);
                        }
                        builder.daiDesired = bool2;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playbackSessionTicket = str3;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.passthroughAdParams = str4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2136076506:
                                    if (currentName.equals("titleInfo")) {
                                        b = 1;
                                    }
                                    break;
                                case -1806627959:
                                    if (currentName.equals("adParams")) {
                                        b = 2;
                                    }
                                    break;
                                case -1161269529:
                                    if (currentName.equals("playbackSessionTicket")) {
                                        b = 9;
                                    }
                                    break;
                                case -703791882:
                                    if (currentName.equals("capVideoQuality")) {
                                        b = 5;
                                    }
                                    break;
                                case -167715474:
                                    if (currentName.equals("daiDesired")) {
                                        b = 8;
                                    }
                                    break;
                                case 368095605:
                                    if (currentName.equals("consumptionType")) {
                                        b = 4;
                                    }
                                    break;
                                case 668466043:
                                    if (currentName.equals("withDebugInfo")) {
                                        b = 3;
                                    }
                                    break;
                                case 780852260:
                                    if (currentName.equals("deviceInfo")) {
                                        b = 7;
                                    }
                                    break;
                                case 900020652:
                                    if (currentName.equals("customerInfo")) {
                                        b = 0;
                                    }
                                    break;
                                case 915881277:
                                    if (currentName.equals("passthroughAdParams")) {
                                        b = 10;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            GetLivePlaybackUrlsCustomerInfo getLivePlaybackUrlsCustomerInfo = null;
                            String str = null;
                            String str2 = null;
                            Boolean bool = null;
                            GetLivePlaybackUrlsDeviceInfo getLivePlaybackUrlsDeviceInfo = null;
                            String str3 = null;
                            String str4 = null;
                            GetLivePlaybackUrlsConsumptionType getLivePlaybackUrlsConsumptionType = null;
                            Boolean bool2 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            GetLivePlaybackUrlsTitleInfo getLivePlaybackUrlsTitleInfo = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        getLivePlaybackUrlsCustomerInfo = this.mGetLivePlaybackUrlsCustomerInfoParser.parse(jsonParser);
                                    }
                                    builder.customerInfo = getLivePlaybackUrlsCustomerInfo;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        getLivePlaybackUrlsTitleInfo = this.mGetLivePlaybackUrlsTitleInfoParser.parse(jsonParser);
                                    }
                                    builder.titleInfo = getLivePlaybackUrlsTitleInfo;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mAdParamMapParser.parse(jsonParser);
                                    }
                                    builder.adParams = immutableMap;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool2 = this.mbooleanParser.parse(jsonParser);
                                    }
                                    builder.withDebugInfo = bool2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        getLivePlaybackUrlsConsumptionType = (GetLivePlaybackUrlsConsumptionType) this.mGetLivePlaybackUrlsConsumptionTypeParser.parse(jsonParser);
                                    }
                                    builder.consumptionType = getLivePlaybackUrlsConsumptionType;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.capVideoQuality = str4;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.marketplaceId = str3;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        getLivePlaybackUrlsDeviceInfo = this.mGetLivePlaybackUrlsDeviceInfoParser.parse(jsonParser);
                                    }
                                    builder.deviceInfo = getLivePlaybackUrlsDeviceInfo;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mbooleanParser.parse(jsonParser);
                                    }
                                    builder.daiDesired = bool;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.playbackSessionTicket = str2;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.passthroughAdParams = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
