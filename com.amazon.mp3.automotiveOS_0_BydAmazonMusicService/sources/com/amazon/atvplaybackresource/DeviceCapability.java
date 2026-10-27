package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.types.SupportedVideoQuality;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
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
public class DeviceCapability {
    public final SupportedVideoQuality maxSupportedQuality;
    public final Boolean supportsDai;
    public final Boolean supportsInsecureWidevine;

    private DeviceCapability(Builder builder) {
        this.supportsDai = (Boolean) Preconditions.checkNotNull(builder.supportsDai, "Unexpected null field: supportsDai");
        this.supportsInsecureWidevine = (Boolean) Preconditions.checkNotNull(builder.supportsInsecureWidevine, "Unexpected null field: supportsInsecureWidevine");
        this.maxSupportedQuality = (SupportedVideoQuality) Preconditions.checkNotNull(builder.maxSupportedQuality, "Unexpected null field: maxSupportedQuality");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceCapability)) {
            return false;
        }
        DeviceCapability deviceCapability = (DeviceCapability) obj;
        return Objects.equal(this.supportsDai, deviceCapability.supportsDai) && Objects.equal(this.supportsInsecureWidevine, deviceCapability.supportsInsecureWidevine) && Objects.equal(this.maxSupportedQuality, deviceCapability.maxSupportedQuality);
    }

    public int hashCode() {
        return Objects.hashCode(this.supportsDai, this.supportsInsecureWidevine, this.maxSupportedQuality);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("supportsDai", this.supportsDai).add("supportsInsecureWidevine", this.supportsInsecureWidevine).add("maxSupportedQuality", this.maxSupportedQuality).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public SupportedVideoQuality maxSupportedQuality;
        public Boolean supportsDai;
        public Boolean supportsInsecureWidevine;

        public DeviceCapability build() {
            return new DeviceCapability(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DeviceCapability> {
        private final SimpleParsers.BooleanParser mbooleanTargetParser;
        private final EnumParser<SupportedVideoQuality> msupportedVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mbooleanTargetParser = SimpleParsers.BooleanParser.INSTANCE;
            this.msupportedVideoQualityParser = EnumParser.newParser(SupportedVideoQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DeviceCapability parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DeviceCapability:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DeviceCapability parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DeviceCapability:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DeviceCapability parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DeviceCapability");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1850387864) {
                        if (iHashCode != 746857973) {
                            if (iHashCode == 1270841501 && next.equals("supportsInsecureWidevine")) {
                                b = 1;
                            }
                        } else if (next.equals("maxSupportedQuality")) {
                            b = 2;
                        }
                    } else if (next.equals("supportsDai")) {
                        b = 0;
                    }
                    SupportedVideoQuality supportedVideoQuality = null;
                    Boolean bool = null;
                    Boolean bool2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mbooleanTargetParser.parse(jsonNode2);
                        }
                        builder.supportsDai = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool2 = this.mbooleanTargetParser.parse(jsonNode2);
                        }
                        builder.supportsInsecureWidevine = bool2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            supportedVideoQuality = (SupportedVideoQuality) this.msupportedVideoQualityParser.parse(jsonNode2);
                        }
                        builder.maxSupportedQuality = supportedVideoQuality;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DeviceCapability so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.supportsDai, this, "supportsDai");
            JsonParsingUtils.checkNotNull(builder.supportsInsecureWidevine, this, "supportsInsecureWidevine");
            JsonParsingUtils.checkNotNull(builder.maxSupportedQuality, this, "maxSupportedQuality");
            return builder.build();
        }

        @Nonnull
        private DeviceCapability parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -1850387864) {
                                if (iHashCode != 746857973) {
                                    if (iHashCode == 1270841501 && currentName.equals("supportsInsecureWidevine")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("maxSupportedQuality")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("supportsDai")) {
                                b = 0;
                            }
                            SupportedVideoQuality supportedVideoQuality = null;
                            Boolean bool = null;
                            Boolean bool2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mbooleanTargetParser.parse(jsonParser);
                                }
                                builder.supportsDai = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool2 = this.mbooleanTargetParser.parse(jsonParser);
                                }
                                builder.supportsInsecureWidevine = bool2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    supportedVideoQuality = (SupportedVideoQuality) this.msupportedVideoQualityParser.parse(jsonParser);
                                }
                                builder.maxSupportedQuality = supportedVideoQuality;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DeviceCapability so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.supportsDai, this, "supportsDai");
                    JsonParsingUtils.checkNotNull(builder.supportsInsecureWidevine, this, "supportsInsecureWidevine");
                    JsonParsingUtils.checkNotNull(builder.maxSupportedQuality, this, "maxSupportedQuality");
                    return builder.build();
                }
            }
        }
    }
}
