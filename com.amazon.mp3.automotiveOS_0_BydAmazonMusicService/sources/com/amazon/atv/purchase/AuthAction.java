package com.amazon.atv.purchase;

import com.amazon.atv.purchase.InBand;
import com.amazon.atv.purchase.OutOfBand;
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
public class AuthAction {
    public final Optional<InBand> InBandMFAAction;
    public final Optional<OutOfBand> OutOfBandMFAAction;

    private AuthAction(Builder builder) {
        this.InBandMFAAction = Optional.fromNullable(builder.InBandMFAAction);
        this.OutOfBandMFAAction = Optional.fromNullable(builder.OutOfBandMFAAction);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthAction)) {
            return false;
        }
        AuthAction authAction = (AuthAction) obj;
        return Objects.equal(this.InBandMFAAction, authAction.InBandMFAAction) && Objects.equal(this.OutOfBandMFAAction, authAction.OutOfBandMFAAction);
    }

    public int hashCode() {
        return Objects.hashCode(this.InBandMFAAction, this.OutOfBandMFAAction);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("InBandMFAAction", this.InBandMFAAction).add("OutOfBandMFAAction", this.OutOfBandMFAAction).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public InBand InBandMFAAction;
        public OutOfBand OutOfBandMFAAction;

        public AuthAction build() {
            return new AuthAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AuthAction> {
        private final InBand.Parser mInBandParser;
        private final OutOfBand.Parser mOutOfBandParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mOutOfBandParser = new OutOfBand.Parser(objectMapper);
            this.mInBandParser = new InBand.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AuthAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AuthAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AuthAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AuthAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AuthAction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AuthAction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 672225764) {
                        if (iHashCode == 1595630148 && next.equals("InBandMFAAction")) {
                            b = 0;
                        }
                    } else if (next.equals("OutOfBandMFAAction")) {
                        b = 1;
                    }
                    OutOfBand outOfBand = null;
                    InBand inBand = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            inBand = this.mInBandParser.parse(jsonNode2);
                        }
                        builder.InBandMFAAction = inBand;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            outOfBand = this.mOutOfBandParser.parse(jsonNode2);
                        }
                        builder.OutOfBandMFAAction = outOfBand;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AuthAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private AuthAction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 672225764) {
                                if (iHashCode == 1595630148 && currentName.equals("InBandMFAAction")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("OutOfBandMFAAction")) {
                                b = 1;
                            }
                            OutOfBand outOfBand = null;
                            InBand inBand = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    inBand = this.mInBandParser.parse(jsonParser);
                                }
                                builder.InBandMFAAction = inBand;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    outOfBand = this.mOutOfBandParser.parse(jsonParser);
                                }
                                builder.OutOfBandMFAAction = outOfBand;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AuthAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
