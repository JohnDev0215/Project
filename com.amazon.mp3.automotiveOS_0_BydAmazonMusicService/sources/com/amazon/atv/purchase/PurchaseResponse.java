package com.amazon.atv.purchase;

import com.amazon.atv.purchase.EMPAction;
import com.amazon.atv.purchase.MFAAction;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PurchaseResponse {
    public final Optional<MFAAction> MFAAction;
    public final Optional<EMPAction> empAction;
    public final String orderToken;

    private PurchaseResponse(Builder builder) {
        this.orderToken = (String) Preconditions.checkNotNull(builder.orderToken, "Unexpected null field: orderToken");
        this.MFAAction = Optional.fromNullable(builder.MFAAction);
        this.empAction = Optional.fromNullable(builder.empAction);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PurchaseResponse)) {
            return false;
        }
        PurchaseResponse purchaseResponse = (PurchaseResponse) obj;
        return Objects.equal(this.orderToken, purchaseResponse.orderToken) && Objects.equal(this.MFAAction, purchaseResponse.MFAAction) && Objects.equal(this.empAction, purchaseResponse.empAction);
    }

    public int hashCode() {
        return Objects.hashCode(this.orderToken, this.MFAAction, this.empAction);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("orderToken", this.orderToken).add("MFAAction", this.MFAAction).add("empAction", this.empAction).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public MFAAction MFAAction;
        public EMPAction empAction;
        public String orderToken;

        public PurchaseResponse build() {
            return new PurchaseResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PurchaseResponse> {
        private final EMPAction.Parser mEMPActionParser;
        private final MFAAction.Parser mMFAActionParser;
        private final SimpleParsers.StringParser morderTokenParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEMPActionParser = new EMPAction.Parser(objectMapper);
            this.mMFAActionParser = new MFAAction.Parser(objectMapper);
            this.morderTokenParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PurchaseResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PurchaseResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PurchaseResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PurchaseResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -483320290) {
                        if (iHashCode != 746103627) {
                            if (iHashCode == 1395539230 && next.equals("empAction")) {
                                b = 2;
                            }
                        } else if (next.equals("orderToken")) {
                            b = 0;
                        }
                    } else if (next.equals("MFAAction")) {
                        b = 1;
                    }
                    EMPAction eMPAction = null;
                    String str = null;
                    MFAAction mFAAction = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.morderTokenParser.parse(jsonNode2);
                        }
                        builder.orderToken = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            mFAAction = this.mMFAActionParser.parse(jsonNode2);
                        }
                        builder.MFAAction = mFAAction;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            eMPAction = this.mEMPActionParser.parse(jsonNode2);
                        }
                        builder.empAction = eMPAction;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PurchaseResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.orderToken, this, "orderToken");
            return builder.build();
        }

        @Nonnull
        private PurchaseResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -483320290) {
                                if (iHashCode != 746103627) {
                                    if (iHashCode == 1395539230 && currentName.equals("empAction")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("orderToken")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("MFAAction")) {
                                b = 1;
                            }
                            EMPAction eMPAction = null;
                            String str = null;
                            MFAAction mFAAction = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.morderTokenParser.parse(jsonParser);
                                }
                                builder.orderToken = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    mFAAction = this.mMFAActionParser.parse(jsonParser);
                                }
                                builder.MFAAction = mFAAction;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    eMPAction = this.mEMPActionParser.parse(jsonParser);
                                }
                                builder.empAction = eMPAction;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PurchaseResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.orderToken, this, "orderToken");
                    return builder.build();
                }
            }
        }
    }
}
