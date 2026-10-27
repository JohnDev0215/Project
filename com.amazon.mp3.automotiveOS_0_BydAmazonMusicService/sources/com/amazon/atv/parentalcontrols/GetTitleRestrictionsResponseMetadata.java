package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.CustomerPinInfo;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class GetTitleRestrictionsResponseMetadata {
    public final CustomerPinInfo customerPin;

    private GetTitleRestrictionsResponseMetadata(Builder builder) {
        this.customerPin = (CustomerPinInfo) Preconditions.checkNotNull(builder.customerPin, "Unexpected null field: customerPin");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetTitleRestrictionsResponseMetadata) {
            return Objects.equal(this.customerPin, ((GetTitleRestrictionsResponseMetadata) obj).customerPin);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.customerPin);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("customerPin", this.customerPin).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CustomerPinInfo customerPin;

        public GetTitleRestrictionsResponseMetadata build() {
            return new GetTitleRestrictionsResponseMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetTitleRestrictionsResponseMetadata> {
        private final CustomerPinInfo.Parser mCustomerPinInfoParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCustomerPinInfoParser = new CustomerPinInfo.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetTitleRestrictionsResponseMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleRestrictionsResponseMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetTitleRestrictionsResponseMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleRestrictionsResponseMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetTitleRestrictionsResponseMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetTitleRestrictionsResponseMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -1772075817 && next.equals("customerPin")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.customerPin = jsonNode2.isNull() ? null : this.mCustomerPinInfoParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTitleRestrictionsResponseMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.customerPin, this, "customerPin");
            return builder.build();
        }

        @Nonnull
        private GetTitleRestrictionsResponseMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == -1772075817 && currentName.equals("customerPin")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.customerPin = currentToken == JsonToken.VALUE_NULL ? null : this.mCustomerPinInfoParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTitleRestrictionsResponseMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.customerPin, this, "customerPin");
                    return builder.build();
                }
            }
        }
    }
}
