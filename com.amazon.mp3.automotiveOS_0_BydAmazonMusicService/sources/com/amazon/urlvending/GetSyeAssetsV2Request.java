package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class GetSyeAssetsV2Request {
    public final Optional<String> advertisingId;
    public final Optional<String> deviceCapabilityInfo;
    public final Optional<String> deviceId;
    public final Optional<String> deviceTypeId;
    public final Optional<String> geoToken;
    public final Optional<String> identityContext;
    public final Optional<Boolean> optOutAdTracking;
    public final Optional<String> playbackSessionTicket;
    public final Optional<String> userAgent;

    private GetSyeAssetsV2Request(Builder builder) {
        this.deviceCapabilityInfo = Optional.fromNullable(builder.deviceCapabilityInfo);
        this.deviceTypeId = Optional.fromNullable(builder.deviceTypeId);
        this.playbackSessionTicket = Optional.fromNullable(builder.playbackSessionTicket);
        this.identityContext = Optional.fromNullable(builder.identityContext);
        this.geoToken = Optional.fromNullable(builder.geoToken);
        this.advertisingId = Optional.fromNullable(builder.advertisingId);
        this.optOutAdTracking = Optional.fromNullable(builder.optOutAdTracking);
        this.userAgent = Optional.fromNullable(builder.userAgent);
        this.deviceId = Optional.fromNullable(builder.deviceId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetSyeAssetsV2Request)) {
            return false;
        }
        GetSyeAssetsV2Request getSyeAssetsV2Request = (GetSyeAssetsV2Request) obj;
        return Objects.equal(this.deviceCapabilityInfo, getSyeAssetsV2Request.deviceCapabilityInfo) && Objects.equal(this.deviceTypeId, getSyeAssetsV2Request.deviceTypeId) && Objects.equal(this.playbackSessionTicket, getSyeAssetsV2Request.playbackSessionTicket) && Objects.equal(this.identityContext, getSyeAssetsV2Request.identityContext) && Objects.equal(this.geoToken, getSyeAssetsV2Request.geoToken) && Objects.equal(this.advertisingId, getSyeAssetsV2Request.advertisingId) && Objects.equal(this.optOutAdTracking, getSyeAssetsV2Request.optOutAdTracking) && Objects.equal(this.userAgent, getSyeAssetsV2Request.userAgent) && Objects.equal(this.deviceId, getSyeAssetsV2Request.deviceId);
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceCapabilityInfo, this.deviceTypeId, this.playbackSessionTicket, this.identityContext, this.geoToken, this.advertisingId, this.optOutAdTracking, this.userAgent, this.deviceId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("deviceCapabilityInfo", this.deviceCapabilityInfo).add("deviceTypeId", this.deviceTypeId).add("playbackSessionTicket", this.playbackSessionTicket).add("identityContext", this.identityContext).add("geoToken", this.geoToken).add("advertisingId", this.advertisingId).add("optOutAdTracking", this.optOutAdTracking).add("userAgent", this.userAgent).add(Splash.PARAMS_DEVICE_ID, this.deviceId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String advertisingId;
        public String deviceCapabilityInfo;
        public String deviceId;
        public String deviceTypeId;
        public String geoToken;
        public String identityContext;
        public Boolean optOutAdTracking;
        public String playbackSessionTicket;
        public String userAgent;

        public GetSyeAssetsV2Request build() {
            return new GetSyeAssetsV2Request(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetSyeAssetsV2Request> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetSyeAssetsV2Request parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSyeAssetsV2Request:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetSyeAssetsV2Request parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSyeAssetsV2Request:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetSyeAssetsV2Request parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            Boolean bool;
            String str7;
            String str8;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetSyeAssetsV2Request");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1161269529:
                            if (next.equals("playbackSessionTicket")) {
                                b = 2;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 1;
                            }
                            break;
                        case -356528676:
                            if (next.equals("deviceCapabilityInfo")) {
                                b = 0;
                            }
                            break;
                        case -124344481:
                            if (next.equals("advertisingId")) {
                                b = 5;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 3;
                            }
                            break;
                        case 311430650:
                            if (next.equals("userAgent")) {
                                b = 7;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 8;
                            }
                            break;
                        case 1307785717:
                            if (next.equals("optOutAdTracking")) {
                                b = 6;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 4;
                            }
                            break;
                    }
                    str = null;
                    str8 = null;
                    str7 = null;
                    bool = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetSyeAssetsV2Request so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceCapabilityInfo = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playbackSessionTicket = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.identityContext = str4;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.geoToken = str5;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.advertisingId = str6;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.optOutAdTracking = bool;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.userAgent = str7;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceId = str8;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetSyeAssetsV2Request so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetSyeAssetsV2Request parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1161269529:
                                    if (currentName.equals("playbackSessionTicket")) {
                                        b = 2;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 1;
                                    }
                                    break;
                                case -356528676:
                                    if (currentName.equals("deviceCapabilityInfo")) {
                                        b = 0;
                                    }
                                    break;
                                case -124344481:
                                    if (currentName.equals("advertisingId")) {
                                        b = 5;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 3;
                                    }
                                    break;
                                case 311430650:
                                    if (currentName.equals("userAgent")) {
                                        b = 7;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 8;
                                    }
                                    break;
                                case 1307785717:
                                    if (currentName.equals("optOutAdTracking")) {
                                        b = 6;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            Boolean bool = null;
                            String str4 = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceCapabilityInfo = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str8;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.playbackSessionTicket = str7;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.identityContext = str6;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.geoToken = str5;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.advertisingId = str4;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.optOutAdTracking = bool;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.userAgent = str3;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetSyeAssetsV2Request so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetSyeAssetsV2Request so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
