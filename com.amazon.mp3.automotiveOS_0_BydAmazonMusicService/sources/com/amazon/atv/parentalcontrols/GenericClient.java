package com.amazon.atv.parentalcontrols;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
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

/* JADX INFO: loaded from: classes2.dex */
public class GenericClient {
    public final Optional<String> deviceId;
    public final Optional<String> deviceTypeId;
    public final ClientType type;

    protected GenericClient(Builder builder) {
        this.deviceId = Optional.fromNullable(builder.deviceId);
        this.deviceTypeId = Optional.fromNullable(builder.deviceTypeId);
        this.type = (ClientType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GenericClient)) {
            return false;
        }
        GenericClient genericClient = (GenericClient) obj;
        return Objects.equal(this.deviceId, genericClient.deviceId) && Objects.equal(this.deviceTypeId, genericClient.deviceTypeId) && Objects.equal(this.type, genericClient.type);
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceId, this.deviceTypeId, this.type);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("deviceTypeId", this.deviceTypeId).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String deviceId;
        public String deviceTypeId;
        public ClientType type;

        public GenericClient build() {
            return new GenericClient(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GenericClient> {
        private final EnumParser<ClientType> mclientTypeParser;
        private final SimpleParsers.StringParser mdeviceIdParser;
        private final SimpleParsers.StringParser mdeviceTypeIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mclientTypeParser = EnumParser.newParser(ClientType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mdeviceIdParser = stringParser;
            this.mdeviceTypeIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GenericClient parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GenericClient:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GenericClient parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GenericClient:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GenericClient parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GenericClient");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -894884885) {
                        if (iHashCode != 3575610) {
                            if (iHashCode == 1109191185 && next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 0;
                            }
                        } else if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                            b = 2;
                        }
                    } else if (next.equals("deviceTypeId")) {
                        b = 1;
                    }
                    ClientType clientType = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mdeviceIdParser.parse(jsonNode2);
                        }
                        builder.deviceId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mdeviceTypeIdParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            clientType = (ClientType) this.mclientTypeParser.parse(jsonNode2);
                        }
                        builder.type = clientType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GenericClient so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        @Nonnull
        private GenericClient parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                if (iHashCode != 3575610) {
                                    if (iHashCode == 1109191185 && currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 0;
                                    }
                                } else if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                    b = 2;
                                }
                            } else if (currentName.equals("deviceTypeId")) {
                                b = 1;
                            }
                            ClientType clientType = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mdeviceIdParser.parse(jsonParser);
                                }
                                builder.deviceId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mdeviceTypeIdParser.parse(jsonParser);
                                }
                                builder.deviceTypeId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    clientType = (ClientType) this.mclientTypeParser.parse(jsonParser);
                                }
                                builder.type = clientType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GenericClient so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
