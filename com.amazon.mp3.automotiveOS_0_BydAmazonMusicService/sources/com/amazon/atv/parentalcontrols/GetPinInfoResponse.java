package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.CustomerPinInfo;
import com.amazon.atv.parentalcontrols.PinInfo;
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
public class GetPinInfoResponse {
    public final CustomerPinInfo customerPinInfo;
    public final PinInfo pinInfo;

    private GetPinInfoResponse(Builder builder) {
        this.customerPinInfo = (CustomerPinInfo) Preconditions.checkNotNull(builder.customerPinInfo, "Unexpected null field: customerPinInfo");
        this.pinInfo = (PinInfo) Preconditions.checkNotNull(builder.pinInfo, "Unexpected null field: pinInfo");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetPinInfoResponse)) {
            return false;
        }
        GetPinInfoResponse getPinInfoResponse = (GetPinInfoResponse) obj;
        return Objects.equal(this.customerPinInfo, getPinInfoResponse.customerPinInfo) && Objects.equal(this.pinInfo, getPinInfoResponse.pinInfo);
    }

    public int hashCode() {
        return Objects.hashCode(this.customerPinInfo, this.pinInfo);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("customerPinInfo", this.customerPinInfo).add("pinInfo", this.pinInfo).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CustomerPinInfo customerPinInfo;
        public PinInfo pinInfo;

        public GetPinInfoResponse build() {
            return new GetPinInfoResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetPinInfoResponse> {
        private final CustomerPinInfo.Parser mCustomerPinInfoParser;
        private final PinInfo.Parser mPinInfoParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPinInfoParser = new PinInfo.Parser(objectMapper);
            this.mCustomerPinInfoParser = new CustomerPinInfo.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetPinInfoResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPinInfoResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetPinInfoResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPinInfoResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetPinInfoResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetPinInfoResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -568870941) {
                        if (iHashCode == 815192613 && next.equals("customerPinInfo")) {
                            b = 0;
                        }
                    } else if (next.equals("pinInfo")) {
                        b = 1;
                    }
                    PinInfo pinInfo = null;
                    CustomerPinInfo customerPinInfo = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            customerPinInfo = this.mCustomerPinInfoParser.parse(jsonNode2);
                        }
                        builder.customerPinInfo = customerPinInfo;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            pinInfo = this.mPinInfoParser.parse(jsonNode2);
                        }
                        builder.pinInfo = pinInfo;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetPinInfoResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.customerPinInfo, this, "customerPinInfo");
            JsonParsingUtils.checkNotNull(builder.pinInfo, this, "pinInfo");
            return builder.build();
        }

        @Nonnull
        private GetPinInfoResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -568870941) {
                                if (iHashCode == 815192613 && currentName.equals("customerPinInfo")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("pinInfo")) {
                                b = 1;
                            }
                            PinInfo pinInfo = null;
                            CustomerPinInfo customerPinInfo = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    customerPinInfo = this.mCustomerPinInfoParser.parse(jsonParser);
                                }
                                builder.customerPinInfo = customerPinInfo;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinInfo = this.mPinInfoParser.parse(jsonParser);
                                }
                                builder.pinInfo = pinInfo;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetPinInfoResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.customerPinInfo, this, "customerPinInfo");
                    JsonParsingUtils.checkNotNull(builder.pinInfo, this, "pinInfo");
                    return builder.build();
                }
            }
        }
    }
}
