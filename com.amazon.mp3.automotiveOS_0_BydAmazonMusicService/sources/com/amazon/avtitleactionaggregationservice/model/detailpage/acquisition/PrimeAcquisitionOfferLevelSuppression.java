package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

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
public class PrimeAcquisitionOfferLevelSuppression {
    public final Optional<PrimeAcquisitionOfferLevelSuppressionReason> reason;

    private PrimeAcquisitionOfferLevelSuppression(Builder builder) {
        this.reason = Optional.fromNullable(builder.reason);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PrimeAcquisitionOfferLevelSuppression) {
            return Objects.equal(this.reason, ((PrimeAcquisitionOfferLevelSuppression) obj).reason);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.reason);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("reason", this.reason).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PrimeAcquisitionOfferLevelSuppressionReason reason;

        public PrimeAcquisitionOfferLevelSuppression build() {
            return new PrimeAcquisitionOfferLevelSuppression(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PrimeAcquisitionOfferLevelSuppression> {
        private final EnumParser<PrimeAcquisitionOfferLevelSuppressionReason> mPrimeAcquisitionOfferLevelSuppressionReasonParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPrimeAcquisitionOfferLevelSuppressionReasonParser = EnumParser.newParser(PrimeAcquisitionOfferLevelSuppressionReason.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PrimeAcquisitionOfferLevelSuppression parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisitionOfferLevelSuppression:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PrimeAcquisitionOfferLevelSuppression parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisitionOfferLevelSuppression:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PrimeAcquisitionOfferLevelSuppression parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PrimeAcquisitionOfferLevelSuppression");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -934964668 && next.equals("reason")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.reason = jsonNode2.isNull() ? null : (PrimeAcquisitionOfferLevelSuppressionReason) this.mPrimeAcquisitionOfferLevelSuppressionReasonParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PrimeAcquisitionOfferLevelSuppression so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PrimeAcquisitionOfferLevelSuppression parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == -934964668 && currentName.equals("reason")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.reason = currentToken == JsonToken.VALUE_NULL ? null : (PrimeAcquisitionOfferLevelSuppressionReason) this.mPrimeAcquisitionOfferLevelSuppressionReasonParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PrimeAcquisitionOfferLevelSuppression so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
