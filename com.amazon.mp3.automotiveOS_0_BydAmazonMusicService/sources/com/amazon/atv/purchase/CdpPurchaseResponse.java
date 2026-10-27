package com.amazon.atv.purchase;

import com.amazon.atv.purchase.CdpPurchaseResponseMessage;
import com.amazon.atv.purchase.activity.Signature;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class CdpPurchaseResponse {
    public final Optional<CdpPurchaseResponseMessage> message;
    public final Optional<Signature> signature;

    private CdpPurchaseResponse(Builder builder) {
        this.signature = Optional.fromNullable(builder.signature);
        this.message = Optional.fromNullable(builder.message);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CdpPurchaseResponse)) {
            return false;
        }
        CdpPurchaseResponse cdpPurchaseResponse = (CdpPurchaseResponse) obj;
        return Objects.equal(this.signature, cdpPurchaseResponse.signature) && Objects.equal(this.message, cdpPurchaseResponse.message);
    }

    public int hashCode() {
        return Objects.hashCode(this.signature, this.message);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("signature", this.signature).add("message", this.message).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CdpPurchaseResponseMessage message;
        public Signature signature;

        public CdpPurchaseResponse build() {
            return new CdpPurchaseResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CdpPurchaseResponse> {
        private final CdpPurchaseResponseMessage.Parser mCdpPurchaseResponseMessageParser;
        private final EnumParser<Signature> msignatureParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCdpPurchaseResponseMessageParser = new CdpPurchaseResponseMessage.Parser(objectMapper);
            this.msignatureParser = EnumParser.newParser(Signature.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CdpPurchaseResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CdpPurchaseResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CdpPurchaseResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CdpPurchaseResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 954925063) {
                        if (iHashCode == 1073584312 && next.equals("signature")) {
                            b = 0;
                        }
                    } else if (next.equals("message")) {
                        b = 1;
                    }
                    CdpPurchaseResponseMessage cdpPurchaseResponseMessage = null;
                    Signature signature = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            signature = (Signature) this.msignatureParser.parse(jsonNode2);
                        }
                        builder.signature = signature;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            cdpPurchaseResponseMessage = this.mCdpPurchaseResponseMessageParser.parse(jsonNode2);
                        }
                        builder.message = cdpPurchaseResponseMessage;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CdpPurchaseResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CdpPurchaseResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 954925063) {
                                if (iHashCode == 1073584312 && currentName.equals("signature")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("message")) {
                                b = 1;
                            }
                            CdpPurchaseResponseMessage cdpPurchaseResponseMessage = null;
                            Signature signature = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    signature = (Signature) this.msignatureParser.parse(jsonParser);
                                }
                                builder.signature = signature;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cdpPurchaseResponseMessage = this.mCdpPurchaseResponseMessageParser.parse(jsonParser);
                                }
                                builder.message = cdpPurchaseResponseMessage;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CdpPurchaseResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
