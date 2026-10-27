package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.Features;
import com.amazon.avtitleactionaggregationservice.model.TitleActionsDataRequest;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class GetTitleActionsDataRequest extends TitleActionsDataRequest {
    private GetTitleActionsDataRequest(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.avtitleactionaggregationservice.model.TitleActionsDataRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTitleActionsDataRequest)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.avtitleactionaggregationservice.model.TitleActionsDataRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.avtitleactionaggregationservice.model.TitleActionsDataRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends TitleActionsDataRequest.Builder {
        public GetTitleActionsDataRequest build() {
            return new GetTitleActionsDataRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetTitleActionsDataRequest> {
        private final SimpleParsers.StringParser mCustomerIdParser;
        private final SimpleParsers.StringParser mDeviceIdParser;
        private final SimpleParsers.StringParser mDeviceTypeIdParser;
        private final ListParser<DebugMode> mEnabledDebugModesParser;
        private final Features.Parser mFeaturesParser;
        private final SimpleParsers.StringParser mGeoTokenParser;
        private final ListParser<String> mIdentifiersParser;
        private final SimpleParsers.StringParser mIdentityContextParser;
        private final SimpleParsers.StringParser mIpAddressParser;
        private final ListParser<String> mLocaleListParser;
        private final SimpleParsers.StringParser mMarketplaceIdParser;
        private final SimpleParsers.StringParser mTerritoryParser;
        private final MapParser<String, String> mWeblabOverridesParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mFeaturesParser = new Features.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mIdentifiersParser = ListParser.newParser(stringParser);
            this.mLocaleListParser = ListParser.newParser(stringParser);
            this.mEnabledDebugModesParser = ListParser.newParser(EnumParser.newParser(DebugMode.class));
            this.mWeblabOverridesParser = MapParser.newParser(stringParser, stringParser);
            this.mTerritoryParser = stringParser;
            this.mDeviceIdParser = stringParser;
            this.mMarketplaceIdParser = stringParser;
            this.mIdentityContextParser = stringParser;
            this.mDeviceTypeIdParser = stringParser;
            this.mGeoTokenParser = stringParser;
            this.mIpAddressParser = stringParser;
            this.mCustomerIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetTitleActionsDataRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleActionsDataRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetTitleActionsDataRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleActionsDataRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetTitleActionsDataRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            ImmutableMap<String, String> immutableMap;
            ImmutableList<String> immutableList;
            ImmutableList<String> immutableList2;
            Features features;
            ImmutableList<DebugMode> immutableList3;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetTitleActionsDataRequest");
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
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -1474512786:
                            if (next.equals("weblabOverrides")) {
                                b = 1;
                            }
                            break;
                        case -976788831:
                            if (next.equals("currentTerritory")) {
                                b = 7;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 10;
                            }
                            break;
                        case -290659267:
                            if (next.equals("features")) {
                                b = 4;
                            }
                            break;
                        case -84730373:
                            if (next.equals("homeTerritory")) {
                                b = 0;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 9;
                            }
                            break;
                        case 479296190:
                            if (next.equals("enabledDebugModes")) {
                                b = 5;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 6;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 2;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 8;
                            }
                            break;
                        case 1634032845:
                            if (next.equals("ipAddress")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 1820815737:
                            if (next.equals("preferredLocaleList")) {
                                b = 3;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                    }
                    str = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    immutableList3 = null;
                    features = null;
                    immutableList2 = null;
                    immutableList = null;
                    immutableMap = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTitleActionsDataRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mTerritoryParser.parse(jsonNode2);
                        }
                        builder.homeTerritory = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mWeblabOverridesParser.parse(jsonNode2);
                        }
                        builder.weblabOverrides = immutableMap;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mIdentifiersParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mLocaleListParser.parse(jsonNode2);
                        }
                        builder.preferredLocaleList = immutableList2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            features = this.mFeaturesParser.parse(jsonNode2);
                        }
                        builder.features = features;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mEnabledDebugModesParser.parse(jsonNode2);
                        }
                        builder.enabledDebugModes = immutableList3;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mDeviceIdParser.parse(jsonNode2);
                        }
                        builder.deviceId = str2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mTerritoryParser.parse(jsonNode2);
                        }
                        builder.currentTerritory = str3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mMarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str4;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mIdentityContextParser.parse(jsonNode2);
                        }
                        builder.identityContext = str5;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mDeviceTypeIdParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str6;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mGeoTokenParser.parse(jsonNode2);
                        }
                        builder.geoToken = str7;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mIpAddressParser.parse(jsonNode2);
                        }
                        builder.ipAddress = str8;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mCustomerIdParser.parse(jsonNode2);
                        }
                        builder.customerId = str9;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTitleActionsDataRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetTitleActionsDataRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -1474512786:
                                    if (currentName.equals("weblabOverrides")) {
                                        b = 1;
                                    }
                                    break;
                                case -976788831:
                                    if (currentName.equals("currentTerritory")) {
                                        b = 7;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 10;
                                    }
                                    break;
                                case -290659267:
                                    if (currentName.equals("features")) {
                                        b = 4;
                                    }
                                    break;
                                case -84730373:
                                    if (currentName.equals("homeTerritory")) {
                                        b = 0;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 9;
                                    }
                                    break;
                                case 479296190:
                                    if (currentName.equals("enabledDebugModes")) {
                                        b = 5;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 6;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 2;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 8;
                                    }
                                    break;
                                case 1634032845:
                                    if (currentName.equals("ipAddress")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 1820815737:
                                    if (currentName.equals("preferredLocaleList")) {
                                        b = 3;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            String str9 = null;
                            ImmutableList<DebugMode> immutableList = null;
                            Features features = null;
                            ImmutableList<String> immutableList2 = null;
                            ImmutableList<String> immutableList3 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mTerritoryParser.parse(jsonParser);
                                    }
                                    builder.homeTerritory = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mWeblabOverridesParser.parse(jsonParser);
                                    }
                                    builder.weblabOverrides = immutableMap;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList3 = this.mIdentifiersParser.parse(jsonParser);
                                    }
                                    builder.identifiers = immutableList3;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mLocaleListParser.parse(jsonParser);
                                    }
                                    builder.preferredLocaleList = immutableList2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        features = this.mFeaturesParser.parse(jsonParser);
                                    }
                                    builder.features = features;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mEnabledDebugModesParser.parse(jsonParser);
                                    }
                                    builder.enabledDebugModes = immutableList;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mDeviceIdParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str9;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mTerritoryParser.parse(jsonParser);
                                    }
                                    builder.currentTerritory = str8;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mMarketplaceIdParser.parse(jsonParser);
                                    }
                                    builder.marketplaceId = str7;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mIdentityContextParser.parse(jsonParser);
                                    }
                                    builder.identityContext = str6;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mDeviceTypeIdParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str5;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mGeoTokenParser.parse(jsonParser);
                                    }
                                    builder.geoToken = str4;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mIpAddressParser.parse(jsonParser);
                                    }
                                    builder.ipAddress = str3;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mCustomerIdParser.parse(jsonParser);
                                    }
                                    builder.customerId = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTitleActionsDataRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTitleActionsDataRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    return builder.build();
                }
            }
        }
    }
}
