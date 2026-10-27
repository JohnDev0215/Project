package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.DeviceCapability;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class PrewarmPasCacheForGtiRequest {
    public final DeviceCapability deviceCapability;
    public final String gti;
    public final String territory;

    private PrewarmPasCacheForGtiRequest(Builder builder) {
        this.territory = (String) Preconditions.checkNotNull(builder.territory, "Unexpected null field: territory");
        this.gti = (String) Preconditions.checkNotNull(builder.gti, "Unexpected null field: gti");
        this.deviceCapability = (DeviceCapability) Preconditions.checkNotNull(builder.deviceCapability, "Unexpected null field: deviceCapability");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrewarmPasCacheForGtiRequest)) {
            return false;
        }
        PrewarmPasCacheForGtiRequest prewarmPasCacheForGtiRequest = (PrewarmPasCacheForGtiRequest) obj;
        return Objects.equal(this.territory, prewarmPasCacheForGtiRequest.territory) && Objects.equal(this.gti, prewarmPasCacheForGtiRequest.gti) && Objects.equal(this.deviceCapability, prewarmPasCacheForGtiRequest.deviceCapability);
    }

    public int hashCode() {
        return Objects.hashCode(this.territory, this.gti, this.deviceCapability);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("territory", this.territory).add("gti", this.gti).add("deviceCapability", this.deviceCapability).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public DeviceCapability deviceCapability;
        public String gti;
        public String territory;

        public PrewarmPasCacheForGtiRequest build() {
            return new PrewarmPasCacheForGtiRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PrewarmPasCacheForGtiRequest> {
        private final DeviceCapability.Parser mDeviceCapabilityParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDeviceCapabilityParser = new DeviceCapability.Parser(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PrewarmPasCacheForGtiRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrewarmPasCacheForGtiRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PrewarmPasCacheForGtiRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrewarmPasCacheForGtiRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PrewarmPasCacheForGtiRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PrewarmPasCacheForGtiRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2138199026) {
                        if (iHashCode != -2115639270) {
                            if (iHashCode == 102684 && next.equals("gti")) {
                                b = 1;
                            }
                        } else if (next.equals("territory")) {
                            b = 0;
                        }
                    } else if (next.equals("deviceCapability")) {
                        b = 2;
                    }
                    DeviceCapability deviceCapability = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.territory = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.gti = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            deviceCapability = this.mDeviceCapabilityParser.parse(jsonNode2);
                        }
                        builder.deviceCapability = deviceCapability;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PrewarmPasCacheForGtiRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.territory, this, "territory");
            JsonParsingUtils.checkNotNull(builder.gti, this, "gti");
            JsonParsingUtils.checkNotNull(builder.deviceCapability, this, "deviceCapability");
            return builder.build();
        }

        @Nonnull
        private PrewarmPasCacheForGtiRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2138199026) {
                                if (iHashCode != -2115639270) {
                                    if (iHashCode == 102684 && currentName.equals("gti")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("territory")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("deviceCapability")) {
                                b = 2;
                            }
                            DeviceCapability deviceCapability = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.territory = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.gti = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    deviceCapability = this.mDeviceCapabilityParser.parse(jsonParser);
                                }
                                builder.deviceCapability = deviceCapability;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PrewarmPasCacheForGtiRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.territory, this, "territory");
                    JsonParsingUtils.checkNotNull(builder.gti, this, "gti");
                    JsonParsingUtils.checkNotNull(builder.deviceCapability, this, "deviceCapability");
                    return builder.build();
                }
            }
        }
    }
}
