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
public class PrimeAcquisitionGroupLevelSuppression {
    public final Optional<PrimeAcquisitionGroupLevelSuppressionReason> reason;

    private PrimeAcquisitionGroupLevelSuppression(Builder builder) {
        this.reason = Optional.fromNullable(builder.reason);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PrimeAcquisitionGroupLevelSuppression) {
            return Objects.equal(this.reason, ((PrimeAcquisitionGroupLevelSuppression) obj).reason);
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
        public PrimeAcquisitionGroupLevelSuppressionReason reason;

        public PrimeAcquisitionGroupLevelSuppression build() {
            return new PrimeAcquisitionGroupLevelSuppression(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PrimeAcquisitionGroupLevelSuppression> {
        private final EnumParser<PrimeAcquisitionGroupLevelSuppressionReason> mPrimeAcquisitionGroupLevelSuppressionReasonParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPrimeAcquisitionGroupLevelSuppressionReasonParser = EnumParser.newParser(PrimeAcquisitionGroupLevelSuppressionReason.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PrimeAcquisitionGroupLevelSuppression parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisitionGroupLevelSuppression:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PrimeAcquisitionGroupLevelSuppression parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisitionGroupLevelSuppression:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PrimeAcquisitionGroupLevelSuppression parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PrimeAcquisitionGroupLevelSuppression");
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
                        builder.reason = jsonNode2.isNull() ? null : (PrimeAcquisitionGroupLevelSuppressionReason) this.mPrimeAcquisitionGroupLevelSuppressionReasonParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PrimeAcquisitionGroupLevelSuppression so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PrimeAcquisitionGroupLevelSuppression parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                builder.reason = currentToken == JsonToken.VALUE_NULL ? null : (PrimeAcquisitionGroupLevelSuppressionReason) this.mPrimeAcquisitionGroupLevelSuppressionReasonParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PrimeAcquisitionGroupLevelSuppression so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
