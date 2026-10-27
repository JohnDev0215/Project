package com.amazon.atv.parentalcontrols;

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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ClientDetails {
    public final String deviceId;
    public final String deviceTypeId;

    private ClientDetails(Builder builder) {
        this.deviceId = (String) Preconditions.checkNotNull(builder.deviceId, "Unexpected null field: deviceId");
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientDetails)) {
            return false;
        }
        ClientDetails clientDetails = (ClientDetails) obj;
        return Objects.equal(this.deviceId, clientDetails.deviceId) && Objects.equal(this.deviceTypeId, clientDetails.deviceTypeId);
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceId, this.deviceTypeId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("deviceTypeId", this.deviceTypeId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String deviceId;
        public String deviceTypeId;

        public ClientDetails build() {
            return new ClientDetails(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ClientDetails> {
        private final SimpleParsers.StringParser mdeviceIdParser;
        private final SimpleParsers.StringParser mdeviceTypeIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mdeviceIdParser = stringParser;
            this.mdeviceTypeIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ClientDetails parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ClientDetails:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ClientDetails parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ClientDetails:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ClientDetails parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ClientDetails");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -894884885) {
                        if (iHashCode == 1109191185 && next.equals(Splash.PARAMS_DEVICE_ID)) {
                            b = 0;
                        }
                    } else if (next.equals("deviceTypeId")) {
                        b = 1;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mdeviceIdParser.parse(jsonNode2);
                        }
                        builder.deviceId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mdeviceTypeIdParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ClientDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            return builder.build();
        }

        @Nonnull
        private ClientDetails parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -894884885) {
                                if (iHashCode == 1109191185 && currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                    b = 0;
                                }
                            } else if (currentName.equals("deviceTypeId")) {
                                b = 1;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mdeviceIdParser.parse(jsonParser);
                                }
                                builder.deviceId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mdeviceTypeIdParser.parse(jsonParser);
                                }
                                builder.deviceTypeId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ClientDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.deviceId, this, Splash.PARAMS_DEVICE_ID);
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    return builder.build();
                }
            }
        }
    }
}
