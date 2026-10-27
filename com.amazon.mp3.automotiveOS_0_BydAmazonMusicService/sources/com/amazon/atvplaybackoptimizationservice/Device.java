package com.amazon.atvplaybackoptimizationservice;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class Device {
    public final Optional<String> clientVersion;
    public final Optional<String> deviceChipset;
    public final Optional<String> deviceModel;
    public final String deviceTypeId;
    public final String dsn;
    public final String firmware;
    public final Optional<String> operatingSystemName;
    public final Optional<String> operatingSystemVersion;
    public final Optional<String> playerName;
    public final Optional<String> playerType;
    public final Optional<String> playerVersion;
    public final Optional<String> softwareVersion;
    public final Optional<String> tenantId;
    public final Optional<String> userAgent;

    private Device(Builder builder) {
        this.deviceChipset = Optional.fromNullable(builder.deviceChipset);
        this.playerVersion = Optional.fromNullable(builder.playerVersion);
        this.firmware = (String) Preconditions.checkNotNull(builder.firmware, "Unexpected null field: firmware");
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
        this.operatingSystemName = Optional.fromNullable(builder.operatingSystemName);
        this.clientVersion = Optional.fromNullable(builder.clientVersion);
        this.softwareVersion = Optional.fromNullable(builder.softwareVersion);
        this.playerType = Optional.fromNullable(builder.playerType);
        this.operatingSystemVersion = Optional.fromNullable(builder.operatingSystemVersion);
        this.deviceModel = Optional.fromNullable(builder.deviceModel);
        this.playerName = Optional.fromNullable(builder.playerName);
        this.dsn = (String) Preconditions.checkNotNull(builder.dsn, "Unexpected null field: dsn");
        this.userAgent = Optional.fromNullable(builder.userAgent);
        this.tenantId = Optional.fromNullable(builder.tenantId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.deviceChipset, device.deviceChipset) && Objects.equal(this.playerVersion, device.playerVersion) && Objects.equal(this.firmware, device.firmware) && Objects.equal(this.deviceTypeId, device.deviceTypeId) && Objects.equal(this.operatingSystemName, device.operatingSystemName) && Objects.equal(this.clientVersion, device.clientVersion) && Objects.equal(this.softwareVersion, device.softwareVersion) && Objects.equal(this.playerType, device.playerType) && Objects.equal(this.operatingSystemVersion, device.operatingSystemVersion) && Objects.equal(this.deviceModel, device.deviceModel) && Objects.equal(this.playerName, device.playerName) && Objects.equal(this.dsn, device.dsn) && Objects.equal(this.userAgent, device.userAgent) && Objects.equal(this.tenantId, device.tenantId);
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceChipset, this.playerVersion, this.firmware, this.deviceTypeId, this.operatingSystemName, this.clientVersion, this.softwareVersion, this.playerType, this.operatingSystemVersion, this.deviceModel, this.playerName, this.dsn, this.userAgent, this.tenantId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("deviceChipset", this.deviceChipset).add("playerVersion", this.playerVersion).add("firmware", this.firmware).add("deviceTypeId", this.deviceTypeId).add("operatingSystemName", this.operatingSystemName).add("clientVersion", this.clientVersion).add("softwareVersion", this.softwareVersion).add("playerType", this.playerType).add("operatingSystemVersion", this.operatingSystemVersion).add(DeviceInformation.PARAMS_DEVICE_MODEL, this.deviceModel).add("playerName", this.playerName).add("dsn", this.dsn).add("userAgent", this.userAgent).add("tenantId", this.tenantId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String clientVersion;
        public String deviceChipset;
        public String deviceModel;
        public String deviceTypeId;
        public String dsn;
        public String firmware;
        public String operatingSystemName;
        public String operatingSystemVersion;
        public String playerName;
        public String playerType;
        public String playerVersion;
        public String softwareVersion;
        public String tenantId;
        public String userAgent;

        public Device build() {
            return new Device(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Device> {
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
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
            JsonParsingUtils.throwIfNotObject(jsonNode, "Device");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1766134808:
                            if (next.equals("operatingSystemVersion")) {
                                b = 8;
                            }
                            break;
                        case -1559661965:
                            if (next.equals(DeviceInformation.PARAMS_DEVICE_MODEL)) {
                                b = 9;
                            }
                            break;
                        case -1306693787:
                            if (next.equals("tenantId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -1290384713:
                            if (next.equals("playerVersion")) {
                                b = 1;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 3;
                            }
                            break;
                        case -555337285:
                            if (next.equals("firmware")) {
                                b = 2;
                            }
                            break;
                        case -372113056:
                            if (next.equals("deviceChipset")) {
                                b = 0;
                            }
                            break;
                        case 99775:
                            if (next.equals("dsn")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 303003953:
                            if (next.equals("softwareVersion")) {
                                b = 6;
                            }
                            break;
                        case 311430650:
                            if (next.equals("userAgent")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 771880589:
                            if (next.equals("clientVersion")) {
                                b = 5;
                            }
                            break;
                        case 2095657228:
                            if (next.equals("playerName")) {
                                b = 10;
                            }
                            break;
                        case 2095859131:
                            if (next.equals("playerType")) {
                                b = 7;
                            }
                            break;
                        case 2122745211:
                            if (next.equals("operatingSystemName")) {
                                b = 4;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceChipset = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playerVersion = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.firmware = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.operatingSystemName = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.clientVersion = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.softwareVersion = str;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playerType = str;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.operatingSystemVersion = str;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceModel = str;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playerName = str;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.dsn = str;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.userAgent = str;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.tenantId = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.firmware, this, "firmware");
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            JsonParsingUtils.checkNotNull(builder.dsn, this, "dsn");
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
                                case -1766134808:
                                    if (currentName.equals("operatingSystemVersion")) {
                                        b = 8;
                                    }
                                    break;
                                case -1559661965:
                                    if (currentName.equals(DeviceInformation.PARAMS_DEVICE_MODEL)) {
                                        b = 9;
                                    }
                                    break;
                                case -1306693787:
                                    if (currentName.equals("tenantId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -1290384713:
                                    if (currentName.equals("playerVersion")) {
                                        b = 1;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 3;
                                    }
                                    break;
                                case -555337285:
                                    if (currentName.equals("firmware")) {
                                        b = 2;
                                    }
                                    break;
                                case -372113056:
                                    if (currentName.equals("deviceChipset")) {
                                        b = 0;
                                    }
                                    break;
                                case 99775:
                                    if (currentName.equals("dsn")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 303003953:
                                    if (currentName.equals("softwareVersion")) {
                                        b = 6;
                                    }
                                    break;
                                case 311430650:
                                    if (currentName.equals("userAgent")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 771880589:
                                    if (currentName.equals("clientVersion")) {
                                        b = 5;
                                    }
                                    break;
                                case 2095657228:
                                    if (currentName.equals("playerName")) {
                                        b = 10;
                                    }
                                    break;
                                case 2095859131:
                                    if (currentName.equals("playerType")) {
                                        b = 7;
                                    }
                                    break;
                                case 2122745211:
                                    if (currentName.equals("operatingSystemName")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deviceChipset = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playerVersion = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.firmware = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.operatingSystemName = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.clientVersion = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.softwareVersion = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playerType = str;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.operatingSystemVersion = str;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deviceModel = str;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playerName = str;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.dsn = str;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.userAgent = str;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.tenantId = str;
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
                    JsonParsingUtils.checkNotNull(builder.firmware, this, "firmware");
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    JsonParsingUtils.checkNotNull(builder.dsn, this, "dsn");
                    return builder.build();
                }
            }
        }
    }
}
