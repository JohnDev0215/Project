package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.IntlCustomerPinInfo;
import com.amazon.atv.parentalcontrols.IntlPinInfoMetadata;
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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetIntlPinInfoResponse {
    public final Optional<IntlCustomerPinInfo> customerPinInfo;
    public final Optional<IntlPinInfoMetadata> metadata;

    private GetIntlPinInfoResponse(Builder builder) {
        this.customerPinInfo = Optional.fromNullable(builder.customerPinInfo);
        this.metadata = Optional.fromNullable(builder.metadata);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetIntlPinInfoResponse)) {
            return false;
        }
        GetIntlPinInfoResponse getIntlPinInfoResponse = (GetIntlPinInfoResponse) obj;
        return Objects.equal(this.customerPinInfo, getIntlPinInfoResponse.customerPinInfo) && Objects.equal(this.metadata, getIntlPinInfoResponse.metadata);
    }

    public int hashCode() {
        return Objects.hashCode(this.customerPinInfo, this.metadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("customerPinInfo", this.customerPinInfo).add("metadata", this.metadata).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public IntlCustomerPinInfo customerPinInfo;
        public IntlPinInfoMetadata metadata;

        public GetIntlPinInfoResponse build() {
            return new GetIntlPinInfoResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetIntlPinInfoResponse> {
        private final IntlCustomerPinInfo.Parser mIntlCustomerPinInfoParser;
        private final IntlPinInfoMetadata.Parser mIntlPinInfoMetadataParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntlCustomerPinInfoParser = new IntlCustomerPinInfo.Parser(objectMapper);
            this.mIntlPinInfoMetadataParser = new IntlPinInfoMetadata.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetIntlPinInfoResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlPinInfoResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetIntlPinInfoResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlPinInfoResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetIntlPinInfoResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetIntlPinInfoResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -450004177) {
                        if (iHashCode == 815192613 && next.equals("customerPinInfo")) {
                            b = 0;
                        }
                    } else if (next.equals("metadata")) {
                        b = 1;
                    }
                    IntlPinInfoMetadata intlPinInfoMetadata = null;
                    IntlCustomerPinInfo intlCustomerPinInfo = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            intlCustomerPinInfo = this.mIntlCustomerPinInfoParser.parse(jsonNode2);
                        }
                        builder.customerPinInfo = intlCustomerPinInfo;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            intlPinInfoMetadata = this.mIntlPinInfoMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = intlPinInfoMetadata;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetIntlPinInfoResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetIntlPinInfoResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -450004177) {
                                if (iHashCode == 815192613 && currentName.equals("customerPinInfo")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("metadata")) {
                                b = 1;
                            }
                            IntlPinInfoMetadata intlPinInfoMetadata = null;
                            IntlCustomerPinInfo intlCustomerPinInfo = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    intlCustomerPinInfo = this.mIntlCustomerPinInfoParser.parse(jsonParser);
                                }
                                builder.customerPinInfo = intlCustomerPinInfo;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    intlPinInfoMetadata = this.mIntlPinInfoMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = intlPinInfoMetadata;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetIntlPinInfoResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
