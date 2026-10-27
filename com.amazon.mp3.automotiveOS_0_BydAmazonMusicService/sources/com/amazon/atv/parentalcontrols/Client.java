package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.GenericClient;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Client extends GenericClient {
    public final Optional<String> clientId;

    private Client(Builder builder) {
        super(builder);
        this.clientId = Optional.fromNullable(builder.clientId);
    }

    @Override // com.amazon.atv.parentalcontrols.GenericClient
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Client) {
            return super.equals(obj) && Objects.equal(this.clientId, ((Client) obj).clientId);
        }
        return false;
    }

    @Override // com.amazon.atv.parentalcontrols.GenericClient
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.clientId);
    }

    @Override // com.amazon.atv.parentalcontrols.GenericClient
    public String toString() {
        return MoreObjects.toStringHelper(this).add("clientId", this.clientId).toString();
    }

    @NotThreadSafe
    public static class Builder extends GenericClient.Builder {
        public String clientId;

        @Override // com.amazon.atv.parentalcontrols.GenericClient.Builder
        public Client build() {
            return new Client(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Client> {
        private final SimpleParsers.StringParser mclientIdParser;
        private final EnumParser<ClientType> mclientTypeParser;
        private final SimpleParsers.StringParser mdeviceIdParser;
        private final SimpleParsers.StringParser mdeviceTypeIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mclientTypeParser = EnumParser.newParser(ClientType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mclientIdParser = stringParser;
            this.mdeviceIdParser = stringParser;
            this.mdeviceTypeIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Client parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Client:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Client parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Client:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Client parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Client");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 2;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 3;
                            }
                            break;
                        case 908408390:
                            if (next.equals("clientId")) {
                                b = 0;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 1;
                            }
                            break;
                    }
                    ClientType clientType = null;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mclientIdParser.parse(jsonNode2);
                        }
                        builder.clientId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mdeviceIdParser.parse(jsonNode2);
                        }
                        builder.deviceId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mdeviceTypeIdParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            clientType = (ClientType) this.mclientTypeParser.parse(jsonNode2);
                        }
                        builder.type = clientType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Client so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Client parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 2;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 3;
                                    }
                                    break;
                                case 908408390:
                                    if (currentName.equals("clientId")) {
                                        b = 0;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ClientType clientType = null;
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mclientIdParser.parse(jsonParser);
                                }
                                builder.clientId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mdeviceIdParser.parse(jsonParser);
                                }
                                builder.deviceId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mdeviceTypeIdParser.parse(jsonParser);
                                }
                                builder.deviceTypeId = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    clientType = (ClientType) this.mclientTypeParser.parse(jsonParser);
                                }
                                builder.type = clientType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Client so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
