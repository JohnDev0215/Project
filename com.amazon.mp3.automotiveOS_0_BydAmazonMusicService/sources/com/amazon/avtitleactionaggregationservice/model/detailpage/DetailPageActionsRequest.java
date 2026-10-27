package com.amazon.avtitleactionaggregationservice.model.detailpage;

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
import com.amazon.avtitleactionaggregationservice.model.detailpage.DetailPageFeatures;
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
public class DetailPageActionsRequest {
    public final String deviceId;
    public final String deviceTypeId;
    public final Optional<ImmutableList<DebugMode>> enabledDebugModes;
    public final Optional<DetailPageFeatures> features;
    public final String geoToken;
    public final ImmutableList<String> identifiers;
    public final String identityContext;
    public final Optional<ImmutableMap<String, String>> weblabOverrides;

    private DetailPageActionsRequest(Builder builder) {
        this.features = Optional.fromNullable(builder.features);
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
        this.deviceId = (String) Preconditions.checkNotNull(builder.deviceId, "Unexpected null field: deviceId");
        this.enabledDebugModes = Optional.fromNullable(builder.enabledDebugModes);
        this.geoToken = (String) Preconditions.checkNotNull(builder.geoToken, "Unexpected null field: geoToken");
        this.weblabOverrides = Optional.fromNullable(builder.weblabOverrides);
        this.identityContext = (String) Preconditions.checkNotNull(builder.identityContext, "Unexpected null field: identityContext");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DetailPageActionsRequest)) {
            return false;
        }
        DetailPageActionsRequest detailPageActionsRequest = (DetailPageActionsRequest) obj;
        return Objects.equal(this.features, detailPageActionsRequest.features) && Objects.equal(this.deviceTypeId, detailPageActionsRequest.deviceTypeId) && Objects.equal(this.identifiers, detailPageActionsRequest.identifiers) && Objects.equal(this.deviceId, detailPageActionsRequest.deviceId) && Objects.equal(this.enabledDebugModes, detailPageActionsRequest.enabledDebugModes) && Objects.equal(this.geoToken, detailPageActionsRequest.geoToken) && Objects.equal(this.weblabOverrides, detailPageActionsRequest.weblabOverrides) && Objects.equal(this.identityContext, detailPageActionsRequest.identityContext);
    }

    public int hashCode() {
        return Objects.hashCode(this.features, this.deviceTypeId, this.identifiers, this.deviceId, this.enabledDebugModes, this.geoToken, this.weblabOverrides, this.identityContext);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("features", this.features).add("deviceTypeId", this.deviceTypeId).add("identifiers", this.identifiers).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("enabledDebugModes", this.enabledDebugModes).add("geoToken", this.geoToken).add("weblabOverrides", this.weblabOverrides).add("identityContext", this.identityContext).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String deviceId;
        public String deviceTypeId;
        public ImmutableList<DebugMode> enabledDebugModes;
        public DetailPageFeatures features;
        public String geoToken;
        public ImmutableList<String> identifiers;
        public String identityContext;
        public ImmutableMap<String, String> weblabOverrides;

        public DetailPageActionsRequest build() {
            return new DetailPageActionsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DetailPageActionsRequest> {
        private final DetailPageFeatures.Parser mDetailPageFeaturesParser;
        private final SimpleParsers.StringParser mDeviceIdParser;
        private final SimpleParsers.StringParser mDeviceTypeIdParser;
        private final ListParser<DebugMode> mEnabledDebugModesParser;
        private final SimpleParsers.StringParser mGeoTokenParser;
        private final ListParser<String> mIdentifiersParser;
        private final SimpleParsers.StringParser mIdentityContextParser;
        private final MapParser<String, String> mWeblabOverridesParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDetailPageFeaturesParser = new DetailPageFeatures.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mIdentifiersParser = ListParser.newParser(stringParser);
            this.mEnabledDebugModesParser = ListParser.newParser(EnumParser.newParser(DebugMode.class));
            this.mWeblabOverridesParser = MapParser.newParser(stringParser, stringParser);
            this.mDeviceTypeIdParser = stringParser;
            this.mDeviceIdParser = stringParser;
            this.mGeoTokenParser = stringParser;
            this.mIdentityContextParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DetailPageActionsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DetailPageActionsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DetailPageActionsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DetailPageActionsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DetailPageActionsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            DetailPageFeatures detailPageFeatures;
            String str;
            ImmutableList<String> immutableList;
            String str2;
            ImmutableList<DebugMode> immutableList2;
            String str3;
            ImmutableMap<String, String> immutableMap;
            String str4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "DetailPageActionsRequest");
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
                                b = 6;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 1;
                            }
                            break;
                        case -290659267:
                            if (next.equals("features")) {
                                b = 0;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 7;
                            }
                            break;
                        case 479296190:
                            if (next.equals("enabledDebugModes")) {
                                b = 4;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 3;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 2;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 5;
                            }
                            break;
                    }
                    detailPageFeatures = null;
                    str4 = null;
                    immutableMap = null;
                    str3 = null;
                    immutableList2 = null;
                    str2 = null;
                    immutableList = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DetailPageActionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            detailPageFeatures = this.mDetailPageFeaturesParser.parse(jsonNode2);
                        }
                        builder.features = detailPageFeatures;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mDeviceTypeIdParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mIdentifiersParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mDeviceIdParser.parse(jsonNode2);
                        }
                        builder.deviceId = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mEnabledDebugModesParser.parse(jsonNode2);
                        }
                        builder.enabledDebugModes = immutableList2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mGeoTokenParser.parse(jsonNode2);
                        }
                        builder.geoToken = str3;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mWeblabOverridesParser.parse(jsonNode2);
                        }
                        builder.weblabOverrides = immutableMap;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mIdentityContextParser.parse(jsonNode2);
                        }
                        builder.identityContext = str4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DetailPageActionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
            JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
            JsonParsingUtils.checkNotNull(builder.identityContext, this, "identityContext");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DetailPageActionsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 6;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 1;
                                    }
                                    break;
                                case -290659267:
                                    if (currentName.equals("features")) {
                                        b = 0;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 7;
                                    }
                                    break;
                                case 479296190:
                                    if (currentName.equals("enabledDebugModes")) {
                                        b = 4;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 3;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 2;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            DetailPageFeatures detailPageFeatures = null;
                            String str = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str2 = null;
                            ImmutableList<DebugMode> immutableList = null;
                            String str3 = null;
                            ImmutableList<String> immutableList2 = null;
                            String str4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        detailPageFeatures = this.mDetailPageFeaturesParser.parse(jsonParser);
                                    }
                                    builder.features = detailPageFeatures;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mDeviceTypeIdParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str4;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mIdentifiersParser.parse(jsonParser);
                                    }
                                    builder.identifiers = immutableList2;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mDeviceIdParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str3;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mEnabledDebugModesParser.parse(jsonParser);
                                    }
                                    builder.enabledDebugModes = immutableList;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mGeoTokenParser.parse(jsonParser);
                                    }
                                    builder.geoToken = str2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mWeblabOverridesParser.parse(jsonParser);
                                    }
                                    builder.weblabOverrides = immutableMap;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mIdentityContextParser.parse(jsonParser);
                                    }
                                    builder.identityContext = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DetailPageActionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DetailPageActionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
                    JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
                    JsonParsingUtils.checkNotNull(builder.identityContext, this, "identityContext");
                    return builder.build();
                }
            }
        }
    }
}
