package com.amazon.atv.titledecoration;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class DecorateTitlesRequest {
    public final Optional<String> clientIp;
    public final Optional<String> currentTerritory;
    public final Optional<String> customerId;
    public final String decorationScheme;
    public final Optional<String> deviceId;
    public final String deviceTypeId;
    public final Optional<String> geoToken;
    public final Optional<String> identityContext;
    public final Optional<String> language;
    public final String marketplaceId;
    public final Optional<String> osLocale;
    public final Optional<String> recordTerritory;
    public final ImmutableList<String> titleIds;

    private DecorateTitlesRequest(Builder builder) {
        this.clientIp = Optional.fromNullable(builder.clientIp);
        this.titleIds = (ImmutableList) Preconditions.checkNotNull(builder.titleIds, "Unexpected null field: titleIds");
        this.decorationScheme = (String) Preconditions.checkNotNull(builder.decorationScheme, "Unexpected null field: decorationScheme");
        this.osLocale = Optional.fromNullable(builder.osLocale);
        this.deviceId = Optional.fromNullable(builder.deviceId);
        this.currentTerritory = Optional.fromNullable(builder.currentTerritory);
        this.marketplaceId = (String) Preconditions.checkNotNull(builder.marketplaceId, "Unexpected null field: marketplaceId");
        this.recordTerritory = Optional.fromNullable(builder.recordTerritory);
        this.geoToken = Optional.fromNullable(builder.geoToken);
        this.customerId = Optional.fromNullable(builder.customerId);
        this.language = Optional.fromNullable(builder.language);
        this.identityContext = Optional.fromNullable(builder.identityContext);
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DecorateTitlesRequest)) {
            return false;
        }
        DecorateTitlesRequest decorateTitlesRequest = (DecorateTitlesRequest) obj;
        return Objects.equal(this.clientIp, decorateTitlesRequest.clientIp) && Objects.equal(this.titleIds, decorateTitlesRequest.titleIds) && Objects.equal(this.decorationScheme, decorateTitlesRequest.decorationScheme) && Objects.equal(this.osLocale, decorateTitlesRequest.osLocale) && Objects.equal(this.deviceId, decorateTitlesRequest.deviceId) && Objects.equal(this.currentTerritory, decorateTitlesRequest.currentTerritory) && Objects.equal(this.marketplaceId, decorateTitlesRequest.marketplaceId) && Objects.equal(this.recordTerritory, decorateTitlesRequest.recordTerritory) && Objects.equal(this.geoToken, decorateTitlesRequest.geoToken) && Objects.equal(this.customerId, decorateTitlesRequest.customerId) && Objects.equal(this.language, decorateTitlesRequest.language) && Objects.equal(this.identityContext, decorateTitlesRequest.identityContext) && Objects.equal(this.deviceTypeId, decorateTitlesRequest.deviceTypeId);
    }

    public int hashCode() {
        return Objects.hashCode(this.clientIp, this.titleIds, this.decorationScheme, this.osLocale, this.deviceId, this.currentTerritory, this.marketplaceId, this.recordTerritory, this.geoToken, this.customerId, this.language, this.identityContext, this.deviceTypeId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("clientIp", this.clientIp).add("titleIds", this.titleIds).add("decorationScheme", this.decorationScheme).add("osLocale", this.osLocale).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("currentTerritory", this.currentTerritory).add("marketplaceId", this.marketplaceId).add("recordTerritory", this.recordTerritory).add("geoToken", this.geoToken).add("customerId", this.customerId).add("language", this.language).add("identityContext", this.identityContext).add("deviceTypeId", this.deviceTypeId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String clientIp;
        public String currentTerritory;
        public String customerId;
        public String decorationScheme;
        public String deviceId;
        public String deviceTypeId;
        public String geoToken;
        public String identityContext;
        public String language;
        public String marketplaceId;
        public String osLocale;
        public String recordTerritory;
        public ImmutableList<String> titleIds;

        public DecorateTitlesRequest build() {
            return new DecorateTitlesRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DecorateTitlesRequest> {
        private final SimpleParsers.StringParser mDeviceTypeIdParser;
        private final SimpleParsers.StringParser mIPAddressParser;
        private final SimpleParsers.StringParser mMarketplaceIdParser;
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<String> mTitleIdListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTitleIdListParser = ListParser.newParser(stringParser);
            this.mIPAddressParser = stringParser;
            this.mStringParser = stringParser;
            this.mMarketplaceIdParser = stringParser;
            this.mDeviceTypeIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DecorateTitlesRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DecorateTitlesRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DecorateTitlesRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DecorateTitlesRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DecorateTitlesRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            ImmutableList<String> immutableList;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            String str10;
            String str11;
            String str12;
            JsonParsingUtils.throwIfNotObject(jsonNode, "DecorateTitlesRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1870021312:
                            if (next.equals("titleIds")) {
                                b = 1;
                            }
                            break;
                        case -1613589672:
                            if (next.equals("language")) {
                                b = 10;
                            }
                            break;
                        case -1581184615:
                            if (next.equals("customerId")) {
                                b = 9;
                            }
                            break;
                        case -976788831:
                            if (next.equals("currentTerritory")) {
                                b = 5;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 101881461:
                            if (next.equals("decorationScheme")) {
                                b = 2;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 908408402:
                            if (next.equals("clientIp")) {
                                b = 0;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 4;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 6;
                            }
                            break;
                        case 1443499358:
                            if (next.equals("osLocale")) {
                                b = 3;
                            }
                            break;
                        case 1724376777:
                            if (next.equals("recordTerritory")) {
                                b = 7;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 8;
                            }
                            break;
                    }
                    str = null;
                    str12 = null;
                    str11 = null;
                    str10 = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DecorateTitlesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mIPAddressParser.parse(jsonNode2);
                        }
                        builder.clientIp = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTitleIdListParser.parse(jsonNode2);
                        }
                        builder.titleIds = immutableList;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.decorationScheme = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.osLocale = str3;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceId = str4;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.currentTerritory = str5;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mMarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str6;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.recordTerritory = str7;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.geoToken = str8;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.customerId = str9;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.language = str10;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str11 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.identityContext = str11;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str12 = this.mDeviceTypeIdParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str12;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DecorateTitlesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.titleIds, this, "titleIds");
            JsonParsingUtils.checkNotNull(builder.decorationScheme, this, "decorationScheme");
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DecorateTitlesRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1870021312:
                                    if (currentName.equals("titleIds")) {
                                        b = 1;
                                    }
                                    break;
                                case -1613589672:
                                    if (currentName.equals("language")) {
                                        b = 10;
                                    }
                                    break;
                                case -1581184615:
                                    if (currentName.equals("customerId")) {
                                        b = 9;
                                    }
                                    break;
                                case -976788831:
                                    if (currentName.equals("currentTerritory")) {
                                        b = 5;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 101881461:
                                    if (currentName.equals("decorationScheme")) {
                                        b = 2;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 908408402:
                                    if (currentName.equals("clientIp")) {
                                        b = 0;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 4;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 6;
                                    }
                                    break;
                                case 1443499358:
                                    if (currentName.equals("osLocale")) {
                                        b = 3;
                                    }
                                    break;
                                case 1724376777:
                                    if (currentName.equals("recordTerritory")) {
                                        b = 7;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 8;
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
                            String str10 = null;
                            String str11 = null;
                            String str12 = null;
                            ImmutableList<String> immutableList = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mIPAddressParser.parse(jsonParser);
                                    }
                                    builder.clientIp = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mTitleIdListParser.parse(jsonParser);
                                    }
                                    builder.titleIds = immutableList;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str12 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.decorationScheme = str12;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str11 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.osLocale = str11;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str10;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.currentTerritory = str9;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mMarketplaceIdParser.parse(jsonParser);
                                    }
                                    builder.marketplaceId = str8;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.recordTerritory = str7;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.geoToken = str6;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.customerId = str5;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.language = str4;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.identityContext = str3;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mDeviceTypeIdParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DecorateTitlesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DecorateTitlesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.titleIds, this, "titleIds");
                    JsonParsingUtils.checkNotNull(builder.decorationScheme, this, "decorationScheme");
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    return builder.build();
                }
            }
        }
    }
}
