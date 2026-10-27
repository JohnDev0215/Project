package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackdevice.types.DRMKeyScheme;
import com.amazon.atvplaybackresource.DeviceCapabilitiesOverrides;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class Device {
    public final String amazonDeviceId;
    public final Optional<String> authStatus;
    public final Optional<String> clientId;
    public final Optional<Boolean> daiSupportsEmbeddedTrickplay;
    public final Optional<String> deviceApplicationName;
    public final Optional<DeviceCapabilitiesOverrides> deviceCapabilitiesOverrides;
    public final Optional<String> deviceChipset;
    public final Optional<String> deviceModel;
    public final String deviceTypeId;
    public final String dsn;
    public final String firmware;
    public final Optional<String> manufacturer;
    public final Optional<String> operatingSystemName;
    public final Optional<String> operatingSystemVersion;
    public final Optional<ImmutableMap<String, String>> playerAttributes;
    public final Optional<String> playerType;
    public final Optional<String> playerVersion;
    public final Optional<String> preprocessed;
    public final String rawDeviceId;
    public final Optional<String> softwareVersion;
    public final Optional<DRMKeyScheme> supportedDRMKeyScheme;
    public final Optional<Boolean> supportsEmbeddedTrickplay;
    public final Optional<String> userAgent;
    public final Optional<String> version;
    public final Optional<ImmutableMap<String, String>> weblabTreatments;

    private Device(Builder builder) {
        this.deviceModel = Optional.fromNullable(builder.deviceModel);
        this.deviceApplicationName = Optional.fromNullable(builder.deviceApplicationName);
        this.dsn = (String) Preconditions.checkNotNull(builder.dsn, "Unexpected null field: dsn");
        this.amazonDeviceId = (String) Preconditions.checkNotNull(builder.amazonDeviceId, "Unexpected null field: amazonDeviceId");
        this.daiSupportsEmbeddedTrickplay = Optional.fromNullable(builder.daiSupportsEmbeddedTrickplay);
        this.userAgent = Optional.fromNullable(builder.userAgent);
        this.playerType = Optional.fromNullable(builder.playerType);
        this.weblabTreatments = Optional.fromNullable(builder.weblabTreatments);
        this.operatingSystemVersion = Optional.fromNullable(builder.operatingSystemVersion);
        this.authStatus = Optional.fromNullable(builder.authStatus);
        this.manufacturer = Optional.fromNullable(builder.manufacturer);
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
        this.version = Optional.fromNullable(builder.version);
        this.operatingSystemName = Optional.fromNullable(builder.operatingSystemName);
        this.clientId = Optional.fromNullable(builder.clientId);
        this.deviceChipset = Optional.fromNullable(builder.deviceChipset);
        this.preprocessed = Optional.fromNullable(builder.preprocessed);
        this.playerVersion = Optional.fromNullable(builder.playerVersion);
        this.deviceCapabilitiesOverrides = Optional.fromNullable(builder.deviceCapabilitiesOverrides);
        this.supportsEmbeddedTrickplay = Optional.fromNullable(builder.supportsEmbeddedTrickplay);
        this.supportedDRMKeyScheme = Optional.fromNullable(builder.supportedDRMKeyScheme);
        this.playerAttributes = Optional.fromNullable(builder.playerAttributes);
        this.softwareVersion = Optional.fromNullable(builder.softwareVersion);
        this.rawDeviceId = (String) Preconditions.checkNotNull(builder.rawDeviceId, "Unexpected null field: rawDeviceId");
        this.firmware = (String) Preconditions.checkNotNull(builder.firmware, "Unexpected null field: firmware");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.deviceModel, device.deviceModel) && Objects.equal(this.deviceApplicationName, device.deviceApplicationName) && Objects.equal(this.dsn, device.dsn) && Objects.equal(this.amazonDeviceId, device.amazonDeviceId) && Objects.equal(this.daiSupportsEmbeddedTrickplay, device.daiSupportsEmbeddedTrickplay) && Objects.equal(this.userAgent, device.userAgent) && Objects.equal(this.playerType, device.playerType) && Objects.equal(this.weblabTreatments, device.weblabTreatments) && Objects.equal(this.operatingSystemVersion, device.operatingSystemVersion) && Objects.equal(this.authStatus, device.authStatus) && Objects.equal(this.manufacturer, device.manufacturer) && Objects.equal(this.deviceTypeId, device.deviceTypeId) && Objects.equal(this.version, device.version) && Objects.equal(this.operatingSystemName, device.operatingSystemName) && Objects.equal(this.clientId, device.clientId) && Objects.equal(this.deviceChipset, device.deviceChipset) && Objects.equal(this.preprocessed, device.preprocessed) && Objects.equal(this.playerVersion, device.playerVersion) && Objects.equal(this.deviceCapabilitiesOverrides, device.deviceCapabilitiesOverrides) && Objects.equal(this.supportsEmbeddedTrickplay, device.supportsEmbeddedTrickplay) && Objects.equal(this.supportedDRMKeyScheme, device.supportedDRMKeyScheme) && Objects.equal(this.playerAttributes, device.playerAttributes) && Objects.equal(this.softwareVersion, device.softwareVersion) && Objects.equal(this.rawDeviceId, device.rawDeviceId) && Objects.equal(this.firmware, device.firmware);
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceModel, this.deviceApplicationName, this.dsn, this.amazonDeviceId, this.daiSupportsEmbeddedTrickplay, this.userAgent, this.playerType, this.weblabTreatments, this.operatingSystemVersion, this.authStatus, this.manufacturer, this.deviceTypeId, this.version, this.operatingSystemName, this.clientId, this.deviceChipset, this.preprocessed, this.playerVersion, this.deviceCapabilitiesOverrides, this.supportsEmbeddedTrickplay, this.supportedDRMKeyScheme, this.playerAttributes, this.softwareVersion, this.rawDeviceId, this.firmware);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(DeviceInformation.PARAMS_DEVICE_MODEL, this.deviceModel).add("deviceApplicationName", this.deviceApplicationName).add("dsn", this.dsn).add("amazonDeviceId", this.amazonDeviceId).add("daiSupportsEmbeddedTrickplay", this.daiSupportsEmbeddedTrickplay).add("userAgent", this.userAgent).add("playerType", this.playerType).add("weblabTreatments", this.weblabTreatments).add("operatingSystemVersion", this.operatingSystemVersion).add("authStatus", this.authStatus).add("manufacturer", this.manufacturer).add("deviceTypeId", this.deviceTypeId).add("version", this.version).add("operatingSystemName", this.operatingSystemName).add("clientId", this.clientId).add("deviceChipset", this.deviceChipset).add("preprocessed", this.preprocessed).add("playerVersion", this.playerVersion).add("deviceCapabilitiesOverrides", this.deviceCapabilitiesOverrides).add("supportsEmbeddedTrickplay", this.supportsEmbeddedTrickplay).add("supportedDRMKeyScheme", this.supportedDRMKeyScheme).add("playerAttributes", this.playerAttributes).add("softwareVersion", this.softwareVersion).add("rawDeviceId", this.rawDeviceId).add("firmware", this.firmware).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String amazonDeviceId;
        public String authStatus;
        public String clientId;
        public Boolean daiSupportsEmbeddedTrickplay;
        public String deviceApplicationName;
        public DeviceCapabilitiesOverrides deviceCapabilitiesOverrides;
        public String deviceChipset;
        public String deviceModel;
        public String deviceTypeId;
        public String dsn;
        public String firmware;
        public String manufacturer;
        public String operatingSystemName;
        public String operatingSystemVersion;
        public ImmutableMap<String, String> playerAttributes;
        public String playerType;
        public String playerVersion;
        public String preprocessed;
        public String rawDeviceId;
        public String softwareVersion;
        public DRMKeyScheme supportedDRMKeyScheme;
        public Boolean supportsEmbeddedTrickplay;
        public String userAgent;
        public String version;
        public ImmutableMap<String, String> weblabTreatments;

        public Device build() {
            return new Device(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Device> {
        private final EnumParser<DRMKeyScheme> mDRMKeySchemeParser;
        private final DeviceCapabilitiesOverrides.Parser mDeviceCapabilitiesOverridesParser;
        private final SimpleParsers.BooleanParser mbooleanTargetParser;
        private final MapParser<String, String> mstringMapParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDeviceCapabilitiesOverridesParser = new DeviceCapabilitiesOverrides.Parser(objectMapper);
            this.mbooleanTargetParser = SimpleParsers.BooleanParser.INSTANCE;
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mstringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mstringTargetParser = stringParser;
            this.mDRMKeySchemeParser = EnumParser.newParser(DRMKeyScheme.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Device parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Device:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Device parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Device:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Device parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            String str4;
            Boolean bool;
            String str5;
            String str6;
            ImmutableMap<String, String> immutableMap;
            String str7;
            String str8;
            String str9;
            String str10;
            String str11;
            String str12;
            String str13;
            String str14;
            String str15;
            String str16;
            DeviceCapabilitiesOverrides deviceCapabilitiesOverrides;
            Boolean bool2;
            DRMKeyScheme dRMKeyScheme;
            ImmutableMap<String, String> immutableMap2;
            String str17;
            String str18;
            String str19;
            JsonParsingUtils.throwIfNotObject(jsonNode, "Device");
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
                                b = 10;
                            }
                            break;
                        case -1766134808:
                            if (next.equals("operatingSystemVersion")) {
                                b = 8;
                            }
                            break;
                        case -1559661965:
                            if (next.equals(DeviceInformation.PARAMS_DEVICE_MODEL)) {
                                b = 0;
                            }
                            break;
                        case -1493016997:
                            if (next.equals("deviceCapabilitiesOverrides")) {
                                b = 18;
                            }
                            break;
                        case -1290384713:
                            if (next.equals("playerVersion")) {
                                b = 17;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -884416501:
                            if (next.equals("preprocessed")) {
                                b = 16;
                            }
                            break;
                        case -817448168:
                            if (next.equals("playerAttributes")) {
                                b = 21;
                            }
                            break;
                        case -555337285:
                            if (next.equals("firmware")) {
                                b = 24;
                            }
                            break;
                        case -422537351:
                            if (next.equals("supportsEmbeddedTrickplay")) {
                                b = 19;
                            }
                            break;
                        case -380170187:
                            if (next.equals("amazonDeviceId")) {
                                b = 3;
                            }
                            break;
                        case -372113056:
                            if (next.equals("deviceChipset")) {
                                b = 15;
                            }
                            break;
                        case -330806963:
                            if (next.equals("daiSupportsEmbeddedTrickplay")) {
                                b = 4;
                            }
                            break;
                        case 99775:
                            if (next.equals("dsn")) {
                                b = 2;
                            }
                            break;
                        case 259153497:
                            if (next.equals("rawDeviceId")) {
                                b = 23;
                            }
                            break;
                        case 303003953:
                            if (next.equals("softwareVersion")) {
                                b = 22;
                            }
                            break;
                        case 311430650:
                            if (next.equals("userAgent")) {
                                b = 5;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 575001043:
                            if (next.equals("supportedDRMKeyScheme")) {
                                b = 20;
                            }
                            break;
                        case 908408390:
                            if (next.equals("clientId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 1345357204:
                            if (next.equals("weblabTreatments")) {
                                b = 7;
                            }
                            break;
                        case 1701719749:
                            if (next.equals("deviceApplicationName")) {
                                b = 1;
                            }
                            break;
                        case 1994243002:
                            if (next.equals("authStatus")) {
                                b = 9;
                            }
                            break;
                        case 2095859131:
                            if (next.equals("playerType")) {
                                b = 6;
                            }
                            break;
                        case 2122745211:
                            if (next.equals("operatingSystemName")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                    }
                    str = null;
                    str19 = null;
                    str18 = null;
                    str17 = null;
                    immutableMap2 = null;
                    dRMKeyScheme = null;
                    bool2 = null;
                    deviceCapabilitiesOverrides = null;
                    str16 = null;
                    str15 = null;
                    str14 = null;
                    str13 = null;
                    str12 = null;
                    str11 = null;
                    str10 = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    immutableMap = null;
                    str6 = null;
                    str5 = null;
                    bool = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceModel = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceApplicationName = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.dsn = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.amazonDeviceId = str4;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            bool = this.mbooleanTargetParser.parse(jsonNode2);
                        }
                        builder.daiSupportsEmbeddedTrickplay = bool;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.userAgent = str5;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playerType = str6;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mstringMapParser.parse(jsonNode2);
                        }
                        builder.weblabTreatments = immutableMap;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.operatingSystemVersion = str7;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.authStatus = str8;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.manufacturer = str9;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str10;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str11 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.version = str11;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            str12 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.operatingSystemName = str12;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            str13 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.clientId = str13;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            str14 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceChipset = str14;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            str15 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.preprocessed = str15;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            str16 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playerVersion = str16;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            deviceCapabilitiesOverrides = this.mDeviceCapabilitiesOverridesParser.parse(jsonNode2);
                        }
                        builder.deviceCapabilitiesOverrides = deviceCapabilitiesOverrides;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            bool2 = this.mbooleanTargetParser.parse(jsonNode2);
                        }
                        builder.supportsEmbeddedTrickplay = bool2;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            dRMKeyScheme = (DRMKeyScheme) this.mDRMKeySchemeParser.parse(jsonNode2);
                        }
                        builder.supportedDRMKeyScheme = dRMKeyScheme;
                        continue;
                    case 21:
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mstringMapParser.parse(jsonNode2);
                        }
                        builder.playerAttributes = immutableMap2;
                        continue;
                    case 22:
                        if (!jsonNode2.isNull()) {
                            str17 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.softwareVersion = str17;
                        continue;
                    case 23:
                        if (!jsonNode2.isNull()) {
                            str18 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.rawDeviceId = str18;
                        continue;
                    case 24:
                        if (!jsonNode2.isNull()) {
                            str19 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.firmware = str19;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.dsn, this, "dsn");
            JsonParsingUtils.checkNotNull(builder.amazonDeviceId, this, "amazonDeviceId");
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            JsonParsingUtils.checkNotNull(builder.rawDeviceId, this, "rawDeviceId");
            JsonParsingUtils.checkNotNull(builder.firmware, this, "firmware");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Device parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 10;
                                    }
                                    break;
                                case -1766134808:
                                    if (currentName.equals("operatingSystemVersion")) {
                                        b = 8;
                                    }
                                    break;
                                case -1559661965:
                                    if (currentName.equals(DeviceInformation.PARAMS_DEVICE_MODEL)) {
                                        b = 0;
                                    }
                                    break;
                                case -1493016997:
                                    if (currentName.equals("deviceCapabilitiesOverrides")) {
                                        b = 18;
                                    }
                                    break;
                                case -1290384713:
                                    if (currentName.equals("playerVersion")) {
                                        b = 17;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -884416501:
                                    if (currentName.equals("preprocessed")) {
                                        b = 16;
                                    }
                                    break;
                                case -817448168:
                                    if (currentName.equals("playerAttributes")) {
                                        b = 21;
                                    }
                                    break;
                                case -555337285:
                                    if (currentName.equals("firmware")) {
                                        b = 24;
                                    }
                                    break;
                                case -422537351:
                                    if (currentName.equals("supportsEmbeddedTrickplay")) {
                                        b = 19;
                                    }
                                    break;
                                case -380170187:
                                    if (currentName.equals("amazonDeviceId")) {
                                        b = 3;
                                    }
                                    break;
                                case -372113056:
                                    if (currentName.equals("deviceChipset")) {
                                        b = 15;
                                    }
                                    break;
                                case -330806963:
                                    if (currentName.equals("daiSupportsEmbeddedTrickplay")) {
                                        b = 4;
                                    }
                                    break;
                                case 99775:
                                    if (currentName.equals("dsn")) {
                                        b = 2;
                                    }
                                    break;
                                case 259153497:
                                    if (currentName.equals("rawDeviceId")) {
                                        b = 23;
                                    }
                                    break;
                                case 303003953:
                                    if (currentName.equals("softwareVersion")) {
                                        b = 22;
                                    }
                                    break;
                                case 311430650:
                                    if (currentName.equals("userAgent")) {
                                        b = 5;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 575001043:
                                    if (currentName.equals("supportedDRMKeyScheme")) {
                                        b = 20;
                                    }
                                    break;
                                case 908408390:
                                    if (currentName.equals("clientId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 1345357204:
                                    if (currentName.equals("weblabTreatments")) {
                                        b = 7;
                                    }
                                    break;
                                case 1701719749:
                                    if (currentName.equals("deviceApplicationName")) {
                                        b = 1;
                                    }
                                    break;
                                case 1994243002:
                                    if (currentName.equals("authStatus")) {
                                        b = 9;
                                    }
                                    break;
                                case 2095859131:
                                    if (currentName.equals("playerType")) {
                                        b = 6;
                                    }
                                    break;
                                case 2122745211:
                                    if (currentName.equals("operatingSystemName")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            DRMKeyScheme dRMKeyScheme = null;
                            Boolean bool = null;
                            DeviceCapabilitiesOverrides deviceCapabilitiesOverrides = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            String str9 = null;
                            String str10 = null;
                            String str11 = null;
                            String str12 = null;
                            String str13 = null;
                            String str14 = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            String str15 = null;
                            String str16 = null;
                            Boolean bool2 = null;
                            String str17 = null;
                            String str18 = null;
                            String str19 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deviceModel = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str19 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deviceApplicationName = str19;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str18 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.dsn = str18;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str17 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.amazonDeviceId = str17;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool2 = this.mbooleanTargetParser.parse(jsonParser);
                                    }
                                    builder.daiSupportsEmbeddedTrickplay = bool2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str16 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.userAgent = str16;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str15 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playerType = str15;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mstringMapParser.parse(jsonParser);
                                    }
                                    builder.weblabTreatments = immutableMap2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str14 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.operatingSystemVersion = str14;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str13 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.authStatus = str13;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str12 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.manufacturer = str12;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str11 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str11;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.version = str10;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.operatingSystemName = str9;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.clientId = str8;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deviceChipset = str7;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.preprocessed = str6;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playerVersion = str5;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        deviceCapabilitiesOverrides = this.mDeviceCapabilitiesOverridesParser.parse(jsonParser);
                                    }
                                    builder.deviceCapabilitiesOverrides = deviceCapabilitiesOverrides;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mbooleanTargetParser.parse(jsonParser);
                                    }
                                    builder.supportsEmbeddedTrickplay = bool;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        dRMKeyScheme = (DRMKeyScheme) this.mDRMKeySchemeParser.parse(jsonParser);
                                    }
                                    builder.supportedDRMKeyScheme = dRMKeyScheme;
                                    continue;
                                case 21:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mstringMapParser.parse(jsonParser);
                                    }
                                    builder.playerAttributes = immutableMap;
                                    continue;
                                case 22:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.softwareVersion = str4;
                                    continue;
                                case 23:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.rawDeviceId = str3;
                                    continue;
                                case 24:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.firmware = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.dsn, this, "dsn");
                    JsonParsingUtils.checkNotNull(builder.amazonDeviceId, this, "amazonDeviceId");
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    JsonParsingUtils.checkNotNull(builder.rawDeviceId, this, "rawDeviceId");
                    JsonParsingUtils.checkNotNull(builder.firmware, this, "firmware");
                    return builder.build();
                }
            }
        }
    }
}
