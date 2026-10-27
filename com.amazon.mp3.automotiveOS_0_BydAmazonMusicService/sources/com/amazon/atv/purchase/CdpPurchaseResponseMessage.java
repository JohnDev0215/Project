package com.amazon.atv.purchase;

import com.amazon.atv.purchase.CdpError;
import com.amazon.atv.purchase.PurchaseSuccess;
import com.amazon.atv.purchase.activity.StatusCode;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.skyfire.models.TetheredMessageKey;
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
public class CdpPurchaseResponseMessage {
    public final Optional<CdpError> error;
    public final Optional<PurchaseSuccess> purchaseSuccess;
    public final Optional<StatusCode> statusCode;
    public final long timestamp;

    private CdpPurchaseResponseMessage(Builder builder) {
        this.statusCode = Optional.fromNullable(builder.statusCode);
        this.error = Optional.fromNullable(builder.error);
        this.purchaseSuccess = Optional.fromNullable(builder.purchaseSuccess);
        this.timestamp = builder.timestamp;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CdpPurchaseResponseMessage)) {
            return false;
        }
        CdpPurchaseResponseMessage cdpPurchaseResponseMessage = (CdpPurchaseResponseMessage) obj;
        return Objects.equal(this.statusCode, cdpPurchaseResponseMessage.statusCode) && Objects.equal(this.error, cdpPurchaseResponseMessage.error) && Objects.equal(this.purchaseSuccess, cdpPurchaseResponseMessage.purchaseSuccess) && Objects.equal(Long.valueOf(this.timestamp), Long.valueOf(cdpPurchaseResponseMessage.timestamp));
    }

    public int hashCode() {
        return Objects.hashCode(this.statusCode, this.error, this.purchaseSuccess, Long.valueOf(this.timestamp));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("statusCode", this.statusCode).add("error", this.error).add("purchaseSuccess", this.purchaseSuccess).add(TetheredMessageKey.timestamp, this.timestamp).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CdpError error;
        public PurchaseSuccess purchaseSuccess;
        public StatusCode statusCode;
        public long timestamp;

        public CdpPurchaseResponseMessage build() {
            return new CdpPurchaseResponseMessage(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CdpPurchaseResponseMessage> {
        private final CdpError.Parser mCdpErrorParser;
        private final PurchaseSuccess.Parser mPurchaseSuccessParser;
        private final EnumParser<StatusCode> mstatusCodeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCdpErrorParser = new CdpError.Parser(objectMapper);
            this.mstatusCodeParser = EnumParser.newParser(StatusCode.class);
            this.mPurchaseSuccessParser = new PurchaseSuccess.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CdpPurchaseResponseMessage parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseResponseMessage:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CdpPurchaseResponseMessage parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseResponseMessage:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CdpPurchaseResponseMessage parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CdpPurchaseResponseMessage");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 55126294:
                            if (next.equals(TetheredMessageKey.timestamp)) {
                                b = 3;
                            }
                            break;
                        case 96784904:
                            if (next.equals("error")) {
                                b = 1;
                            }
                            break;
                        case 247507199:
                            if (next.equals("statusCode")) {
                                b = 0;
                            }
                            break;
                        case 1369857986:
                            if (next.equals("purchaseSuccess")) {
                                b = 2;
                            }
                            break;
                    }
                    PurchaseSuccess purchaseSuccess = null;
                    StatusCode statusCode = null;
                    CdpError cdpError = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            statusCode = (StatusCode) this.mstatusCodeParser.parse(jsonNode2);
                        }
                        builder.statusCode = statusCode;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            cdpError = this.mCdpErrorParser.parse(jsonNode2);
                        }
                        builder.error = cdpError;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            purchaseSuccess = this.mPurchaseSuccessParser.parse(jsonNode2);
                        }
                        builder.purchaseSuccess = purchaseSuccess;
                    } else if (b == 3) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field timestamp can't be null");
                        }
                        builder.timestamp = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CdpPurchaseResponseMessage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CdpPurchaseResponseMessage parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 55126294:
                                    if (currentName.equals(TetheredMessageKey.timestamp)) {
                                        b = 3;
                                    }
                                    break;
                                case 96784904:
                                    if (currentName.equals("error")) {
                                        b = 1;
                                    }
                                    break;
                                case 247507199:
                                    if (currentName.equals("statusCode")) {
                                        b = 0;
                                    }
                                    break;
                                case 1369857986:
                                    if (currentName.equals("purchaseSuccess")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            PurchaseSuccess purchaseSuccess = null;
                            StatusCode statusCode = null;
                            CdpError cdpError = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    statusCode = (StatusCode) this.mstatusCodeParser.parse(jsonParser);
                                }
                                builder.statusCode = statusCode;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cdpError = this.mCdpErrorParser.parse(jsonParser);
                                }
                                builder.error = cdpError;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    purchaseSuccess = this.mPurchaseSuccessParser.parse(jsonParser);
                                }
                                builder.purchaseSuccess = purchaseSuccess;
                            } else if (b == 3) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field timestamp can't be null");
                                }
                                builder.timestamp = SimpleParsers.parsePrimitiveLong(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CdpPurchaseResponseMessage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
