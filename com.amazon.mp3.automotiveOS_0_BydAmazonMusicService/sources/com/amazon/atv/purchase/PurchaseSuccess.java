package com.amazon.atv.purchase;

import com.amazon.atv.purchase.CdpPurchaseStreamingURLInfoSet;
import com.amazon.atv.purchase.Metadata;
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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PurchaseSuccess {
    public final Optional<String> amountCharged;
    public final Optional<Metadata> metadata;
    public final Optional<String> orderID;
    public final Optional<CdpPurchaseStreamingURLInfoSet> streamingURLInfoSet;

    private PurchaseSuccess(Builder builder) {
        this.streamingURLInfoSet = Optional.fromNullable(builder.streamingURLInfoSet);
        this.orderID = Optional.fromNullable(builder.orderID);
        this.metadata = Optional.fromNullable(builder.metadata);
        this.amountCharged = Optional.fromNullable(builder.amountCharged);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PurchaseSuccess)) {
            return false;
        }
        PurchaseSuccess purchaseSuccess = (PurchaseSuccess) obj;
        return Objects.equal(this.streamingURLInfoSet, purchaseSuccess.streamingURLInfoSet) && Objects.equal(this.orderID, purchaseSuccess.orderID) && Objects.equal(this.metadata, purchaseSuccess.metadata) && Objects.equal(this.amountCharged, purchaseSuccess.amountCharged);
    }

    public int hashCode() {
        return Objects.hashCode(this.streamingURLInfoSet, this.orderID, this.metadata, this.amountCharged);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("streamingURLInfoSet", this.streamingURLInfoSet).add("orderID", this.orderID).add("metadata", this.metadata).add("amountCharged", this.amountCharged).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String amountCharged;
        public Metadata metadata;
        public String orderID;
        public CdpPurchaseStreamingURLInfoSet streamingURLInfoSet;

        public PurchaseSuccess build() {
            return new PurchaseSuccess(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PurchaseSuccess> {
        private final CdpPurchaseStreamingURLInfoSet.Parser mCdpPurchaseStreamingURLInfoSetParser;
        private final Metadata.Parser mMetadataParser;
        private final SimpleParsers.StringParser mamountChargedParser;
        private final SimpleParsers.StringParser morderIDParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCdpPurchaseStreamingURLInfoSetParser = new CdpPurchaseStreamingURLInfoSet.Parser(objectMapper);
            this.mMetadataParser = new Metadata.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.morderIDParser = stringParser;
            this.mamountChargedParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PurchaseSuccess parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseSuccess:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PurchaseSuccess parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseSuccess:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PurchaseSuccess parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PurchaseSuccess");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1207110423:
                            if (next.equals("orderID")) {
                                b = 1;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 2;
                            }
                            break;
                        case -372046233:
                            if (next.equals("streamingURLInfoSet")) {
                                b = 0;
                            }
                            break;
                        case 834201784:
                            if (next.equals("amountCharged")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    CdpPurchaseStreamingURLInfoSet cdpPurchaseStreamingURLInfoSet = null;
                    String str2 = null;
                    Metadata metadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            cdpPurchaseStreamingURLInfoSet = this.mCdpPurchaseStreamingURLInfoSetParser.parse(jsonNode2);
                        }
                        builder.streamingURLInfoSet = cdpPurchaseStreamingURLInfoSet;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.morderIDParser.parse(jsonNode2);
                        }
                        builder.orderID = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            metadata = this.mMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = metadata;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mamountChargedParser.parse(jsonNode2);
                        }
                        builder.amountCharged = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PurchaseSuccess so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PurchaseSuccess parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1207110423:
                                    if (currentName.equals("orderID")) {
                                        b = 1;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 2;
                                    }
                                    break;
                                case -372046233:
                                    if (currentName.equals("streamingURLInfoSet")) {
                                        b = 0;
                                    }
                                    break;
                                case 834201784:
                                    if (currentName.equals("amountCharged")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            CdpPurchaseStreamingURLInfoSet cdpPurchaseStreamingURLInfoSet = null;
                            String str2 = null;
                            Metadata metadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cdpPurchaseStreamingURLInfoSet = this.mCdpPurchaseStreamingURLInfoSetParser.parse(jsonParser);
                                }
                                builder.streamingURLInfoSet = cdpPurchaseStreamingURLInfoSet;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.morderIDParser.parse(jsonParser);
                                }
                                builder.orderID = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    metadata = this.mMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = metadata;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mamountChargedParser.parse(jsonParser);
                                }
                                builder.amountCharged = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PurchaseSuccess so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
