package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.Client;
import com.amazon.atv.parentalcontrols.InternationalCustomerRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetIntlParentalControlsSettingsRequest extends InternationalCustomerRequest {
    public final Optional<ImmutableList<Client>> clients;

    private GetIntlParentalControlsSettingsRequest(Builder builder) {
        super(builder);
        this.clients = Optional.fromNullable(builder.clients);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetIntlParentalControlsSettingsRequest) {
            return super.equals(obj) && Objects.equal(this.clients, ((GetIntlParentalControlsSettingsRequest) obj).clients);
        }
        return false;
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.clients);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("clients", this.clients).toString();
    }

    @NotThreadSafe
    public static class Builder extends InternationalCustomerRequest.Builder {
        public ImmutableList<Client> clients;

        public GetIntlParentalControlsSettingsRequest build() {
            return new GetIntlParentalControlsSettingsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetIntlParentalControlsSettingsRequest> {
        private final ListParser<Client> mclientListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mclientListParser = ListParser.newParser(new Client.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetIntlParentalControlsSettingsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlParentalControlsSettingsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetIntlParentalControlsSettingsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlParentalControlsSettingsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetIntlParentalControlsSettingsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetIntlParentalControlsSettingsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 860587528 && next.equals("clients")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.clients = jsonNode2.isNull() ? null : this.mclientListParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetIntlParentalControlsSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetIntlParentalControlsSettingsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 860587528 && currentName.equals("clients")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.clients = currentToken == JsonToken.VALUE_NULL ? null : this.mclientListParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetIntlParentalControlsSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
