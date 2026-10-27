package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class SyeDevice {
    public final Optional<String> deviceCategory;
    public final Optional<String> deviceId;
    public final Optional<String> devicePlatform;
    public final Optional<String> deviceTypeId;
    public final Optional<String> firmware;
    public final Optional<String> manufacturer;
    public final Optional<String> operatingSystemName;
    public final Optional<String> operatingSystemVersion;
    public final Optional<ImmutableMap<String, String>> playerAttributes;
    public final Optional<String> playerType;
    public final Optional<String> userAgent;

    private SyeDevice(Builder builder) {
        this.playerType = Optional.fromNullable(builder.playerType);
        this.userAgent = Optional.fromNullable(builder.userAgent);
        this.deviceId = Optional.fromNullable(builder.deviceId);
        this.playerAttributes = Optional.fromNullable(builder.playerAttributes);
        this.operatingSystemName = Optional.fromNullable(builder.operatingSystemName);
        this.deviceCategory = Optional.fromNullable(builder.deviceCategory);
        this.devicePlatform = Optional.fromNullable(builder.devicePlatform);
        this.deviceTypeId = Optional.fromNullable(builder.deviceTypeId);
        this.firmware = Optional.fromNullable(builder.firmware);
        this.manufacturer = Optional.fromNullable(builder.manufacturer);
        this.operatingSystemVersion = Optional.fromNullable(builder.operatingSystemVersion);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeDevice)) {
            return false;
        }
        SyeDevice syeDevice = (SyeDevice) obj;
        return Objects.equal(this.playerType, syeDevice.playerType) && Objects.equal(this.userAgent, syeDevice.userAgent) && Objects.equal(this.deviceId, syeDevice.deviceId) && Objects.equal(this.playerAttributes, syeDevice.playerAttributes) && Objects.equal(this.operatingSystemName, syeDevice.operatingSystemName) && Objects.equal(this.deviceCategory, syeDevice.deviceCategory) && Objects.equal(this.devicePlatform, syeDevice.devicePlatform) && Objects.equal(this.deviceTypeId, syeDevice.deviceTypeId) && Objects.equal(this.firmware, syeDevice.firmware) && Objects.equal(this.manufacturer, syeDevice.manufacturer) && Objects.equal(this.operatingSystemVersion, syeDevice.operatingSystemVersion);
    }

    public int hashCode() {
        return Objects.hashCode(this.playerType, this.userAgent, this.deviceId, this.playerAttributes, this.operatingSystemName, this.deviceCategory, this.devicePlatform, this.deviceTypeId, this.firmware, this.manufacturer, this.operatingSystemVersion);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playerType", this.playerType).add("userAgent", this.userAgent).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("playerAttributes", this.playerAttributes).add("operatingSystemName", this.operatingSystemName).add("deviceCategory", this.deviceCategory).add("devicePlatform", this.devicePlatform).add("deviceTypeId", this.deviceTypeId).add("firmware", this.firmware).add("manufacturer", this.manufacturer).add("operatingSystemVersion", this.operatingSystemVersion).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String deviceCategory;
        public String deviceId;
        public String devicePlatform;
        public String deviceTypeId;
        public String firmware;
        public String manufacturer;
        public String operatingSystemName;
        public String operatingSystemVersion;
        public ImmutableMap<String, String> playerAttributes;
        public String playerType;
        public String userAgent;

        public SyeDevice build() {
            return new SyeDevice(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeDevice> {
        private final MapParser<String, String> mPlayerAttributesMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.StringParser mSyeDeviceCategoryParser;
        private final SimpleParsers.StringParser mSyeDevicePlatformParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mPlayerAttributesMapParser = MapParser.newParser(stringParser, stringParser);
            this.mStringParser = stringParser;
            this.mSyeDeviceCategoryParser = stringParser;
            this.mSyeDevicePlatformParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeDevice parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeDevice:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeDevice parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeDevice:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeDevice parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            ImmutableMap<String, String> immutableMap;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            String str10;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeDevice");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1969347631:
                            if (next.equals("manufacturer")) {
                                b = 9;
                            }
                            break;
                        case -1766134808:
                            if (next.equals("operatingSystemVersion")) {
                                b = 10;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 7;
                            }
                            break;
                        case -817448168:
                            if (next.equals("playerAttributes")) {
                                b = 3;
                            }
                            break;
                        case -555337285:
                            if (next.equals("firmware")) {
                                b = 8;
                            }
                            break;
                        case -263747212:
                            if (next.equals("deviceCategory")) {
                                b = 5;
                            }
                            break;
                        case 311430650:
                            if (next.equals("userAgent")) {
                                b = 1;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 2;
                            }
                            break;
                        case 1560425705:
                            if (next.equals("devicePlatform")) {
                                b = 6;
                            }
                            break;
                        case 2095859131:
                            if (next.equals("playerType")) {
                                b = 0;
                            }
                            break;
                        case 2122745211:
                            if (next.equals("operatingSystemName")) {
                                b = 4;
                            }
                            break;
                    }
                    str = null;
                    str10 = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    immutableMap = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeDevice so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playerType = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.userAgent = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceId = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mPlayerAttributesMapParser.parse(jsonNode2);
                        }
                        builder.playerAttributes = immutableMap;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.operatingSystemName = str4;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mSyeDeviceCategoryParser.parse(jsonNode2);
                        }
                        builder.deviceCategory = str5;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mSyeDevicePlatformParser.parse(jsonNode2);
                        }
                        builder.devicePlatform = str6;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str7;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.firmware = str8;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.manufacturer = str9;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.operatingSystemVersion = str10;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeDevice so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeDevice parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1969347631:
                                    if (currentName.equals("manufacturer")) {
                                        b = 9;
                                    }
                                    break;
                                case -1766134808:
                                    if (currentName.equals("operatingSystemVersion")) {
                                        b = 10;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 7;
                                    }
                                    break;
                                case -817448168:
                                    if (currentName.equals("playerAttributes")) {
                                        b = 3;
                                    }
                                    break;
                                case -555337285:
                                    if (currentName.equals("firmware")) {
                                        b = 8;
                                    }
                                    break;
                                case -263747212:
                                    if (currentName.equals("deviceCategory")) {
                                        b = 5;
                                    }
                                    break;
                                case 311430650:
                                    if (currentName.equals("userAgent")) {
                                        b = 1;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 2;
                                    }
                                    break;
                                case 1560425705:
                                    if (currentName.equals("devicePlatform")) {
                                        b = 6;
                                    }
                                    break;
                                case 2095859131:
                                    if (currentName.equals("playerType")) {
                                        b = 0;
                                    }
                                    break;
                                case 2122745211:
                                    if (currentName.equals("operatingSystemName")) {
                                        b = 4;
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
                            ImmutableMap<String, String> immutableMap = null;
                            String str9 = null;
                            String str10 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.playerType = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.userAgent = str10;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str9;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mPlayerAttributesMapParser.parse(jsonParser);
                                    }
                                    builder.playerAttributes = immutableMap;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.operatingSystemName = str8;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mSyeDeviceCategoryParser.parse(jsonParser);
                                    }
                                    builder.deviceCategory = str7;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mSyeDevicePlatformParser.parse(jsonParser);
                                    }
                                    builder.devicePlatform = str6;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str5;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.firmware = str4;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.manufacturer = str3;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.operatingSystemVersion = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeDevice so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeDevice so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
