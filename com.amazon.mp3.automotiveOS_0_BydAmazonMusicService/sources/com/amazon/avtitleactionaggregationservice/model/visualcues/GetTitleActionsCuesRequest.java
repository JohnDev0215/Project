package com.amazon.avtitleactionaggregationservice.model.visualcues;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.DebugMode;
import com.amazon.avtitleactionaggregationservice.model.visualcues.VisualCuesFeatures;
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
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetTitleActionsCuesRequest {
    public final String deviceId;
    public final String deviceTypeId;
    public final Optional<ImmutableList<DebugMode>> enabledDebugModes;
    public final Optional<VisualCuesFeatures> features;
    public final String geoToken;
    public final ImmutableList<String> identifiers;
    public final String identityContext;
    public final Optional<String> preferredLocale;
    public final Optional<ImmutableMap<String, String>> weblabOverrides;

    private GetTitleActionsCuesRequest(Builder builder) {
        this.deviceId = (String) Preconditions.checkNotNull(builder.deviceId, "Unexpected null field: deviceId");
        this.weblabOverrides = Optional.fromNullable(builder.weblabOverrides);
        this.enabledDebugModes = Optional.fromNullable(builder.enabledDebugModes);
        this.preferredLocale = Optional.fromNullable(builder.preferredLocale);
        this.features = Optional.fromNullable(builder.features);
        this.geoToken = (String) Preconditions.checkNotNull(builder.geoToken, "Unexpected null field: geoToken");
        this.identityContext = (String) Preconditions.checkNotNull(builder.identityContext, "Unexpected null field: identityContext");
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTitleActionsCuesRequest)) {
            return false;
        }
        GetTitleActionsCuesRequest getTitleActionsCuesRequest = (GetTitleActionsCuesRequest) obj;
        return Objects.equal(this.deviceId, getTitleActionsCuesRequest.deviceId) && Objects.equal(this.weblabOverrides, getTitleActionsCuesRequest.weblabOverrides) && Objects.equal(this.enabledDebugModes, getTitleActionsCuesRequest.enabledDebugModes) && Objects.equal(this.preferredLocale, getTitleActionsCuesRequest.preferredLocale) && Objects.equal(this.features, getTitleActionsCuesRequest.features) && Objects.equal(this.geoToken, getTitleActionsCuesRequest.geoToken) && Objects.equal(this.identityContext, getTitleActionsCuesRequest.identityContext) && Objects.equal(this.deviceTypeId, getTitleActionsCuesRequest.deviceTypeId) && Objects.equal(this.identifiers, getTitleActionsCuesRequest.identifiers);
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceId, this.weblabOverrides, this.enabledDebugModes, this.preferredLocale, this.features, this.geoToken, this.identityContext, this.deviceTypeId, this.identifiers);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("weblabOverrides", this.weblabOverrides).add("enabledDebugModes", this.enabledDebugModes).add("preferredLocale", this.preferredLocale).add("features", this.features).add("geoToken", this.geoToken).add("identityContext", this.identityContext).add("deviceTypeId", this.deviceTypeId).add("identifiers", this.identifiers).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String deviceId;
        public String deviceTypeId;
        public ImmutableList<DebugMode> enabledDebugModes;
        public VisualCuesFeatures features;
        public String geoToken;
        public ImmutableList<String> identifiers;
        public String identityContext;
        public String preferredLocale;
        public ImmutableMap<String, String> weblabOverrides;

        public GetTitleActionsCuesRequest build() {
            return new GetTitleActionsCuesRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetTitleActionsCuesRequest> {
        private final SimpleParsers.StringParser mDeviceIdParser;
        private final SimpleParsers.StringParser mDeviceTypeIdParser;
        private final ListParser<DebugMode> mEnabledDebugModesParser;
        private final SimpleParsers.StringParser mGeoTokenParser;
        private final ListParser<String> mIdentifiersParser;
        private final SimpleParsers.StringParser mIdentityContextParser;
        private final SimpleParsers.StringParser mStringParser;
        private final VisualCuesFeatures.Parser mVisualCuesFeaturesParser;
        private final MapParser<String, String> mWeblabOverridesParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEnabledDebugModesParser = ListParser.newParser(EnumParser.newParser(DebugMode.class));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mIdentifiersParser = ListParser.newParser(stringParser);
            this.mWeblabOverridesParser = MapParser.newParser(stringParser, stringParser);
            this.mDeviceIdParser = stringParser;
            this.mStringParser = stringParser;
            this.mGeoTokenParser = stringParser;
            this.mIdentityContextParser = stringParser;
            this.mDeviceTypeIdParser = stringParser;
            this.mVisualCuesFeaturesParser = new VisualCuesFeatures.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetTitleActionsCuesRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleActionsCuesRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetTitleActionsCuesRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleActionsCuesRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetTitleActionsCuesRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            ImmutableMap<String, String> immutableMap;
            ImmutableList<DebugMode> immutableList;
            String str2;
            VisualCuesFeatures visualCuesFeatures;
            String str3;
            String str4;
            String str5;
            ImmutableList<String> immutableList2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetTitleActionsCuesRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1474512786:
                            if (next.equals("weblabOverrides")) {
                                b = 1;
                            }
                            break;
                        case -899358149:
                            if (next.equals("preferredLocale")) {
                                b = 3;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 7;
                            }
                            break;
                        case -290659267:
                            if (next.equals("features")) {
                                b = 4;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 6;
                            }
                            break;
                        case 479296190:
                            if (next.equals("enabledDebugModes")) {
                                b = 2;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 0;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 8;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 5;
                            }
                            break;
                    }
                    str = null;
                    immutableList2 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    visualCuesFeatures = null;
                    str2 = null;
                    immutableList = null;
                    immutableMap = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTitleActionsCuesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mDeviceIdParser.parse(jsonNode2);
                        }
                        builder.deviceId = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mWeblabOverridesParser.parse(jsonNode2);
                        }
                        builder.weblabOverrides = immutableMap;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mEnabledDebugModesParser.parse(jsonNode2);
                        }
                        builder.enabledDebugModes = immutableList;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.preferredLocale = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            visualCuesFeatures = this.mVisualCuesFeaturesParser.parse(jsonNode2);
                        }
                        builder.features = visualCuesFeatures;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mGeoTokenParser.parse(jsonNode2);
                        }
                        builder.geoToken = str3;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mIdentityContextParser.parse(jsonNode2);
                        }
                        builder.identityContext = str4;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mDeviceTypeIdParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str5;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mIdentifiersParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTitleActionsCuesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
            JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
            JsonParsingUtils.checkNotNull(builder.identityContext, this, "identityContext");
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetTitleActionsCuesRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1474512786:
                                    if (currentName.equals("weblabOverrides")) {
                                        b = 1;
                                    }
                                    break;
                                case -899358149:
                                    if (currentName.equals("preferredLocale")) {
                                        b = 3;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 7;
                                    }
                                    break;
                                case -290659267:
                                    if (currentName.equals("features")) {
                                        b = 4;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 6;
                                    }
                                    break;
                                case 479296190:
                                    if (currentName.equals("enabledDebugModes")) {
                                        b = 2;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 0;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 8;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            String str = null;
                            ImmutableList<String> immutableList = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            VisualCuesFeatures visualCuesFeatures = null;
                            String str5 = null;
                            ImmutableList<DebugMode> immutableList2 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mDeviceIdParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mWeblabOverridesParser.parse(jsonParser);
                                    }
                                    builder.weblabOverrides = immutableMap;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mEnabledDebugModesParser.parse(jsonParser);
                                    }
                                    builder.enabledDebugModes = immutableList2;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.preferredLocale = str5;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        visualCuesFeatures = this.mVisualCuesFeaturesParser.parse(jsonParser);
                                    }
                                    builder.features = visualCuesFeatures;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mGeoTokenParser.parse(jsonParser);
                                    }
                                    builder.geoToken = str4;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mIdentityContextParser.parse(jsonParser);
                                    }
                                    builder.identityContext = str3;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mDeviceTypeIdParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mIdentifiersParser.parse(jsonParser);
                                    }
                                    builder.identifiers = immutableList;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTitleActionsCuesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTitleActionsCuesRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
                    JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
                    JsonParsingUtils.checkNotNull(builder.identityContext, this, "identityContext");
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    return builder.build();
                }
            }
        }
    }
}
