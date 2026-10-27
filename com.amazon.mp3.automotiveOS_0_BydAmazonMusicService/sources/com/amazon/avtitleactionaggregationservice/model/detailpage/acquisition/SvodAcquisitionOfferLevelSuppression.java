package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avtitleactionaggregationservice.model.detailpage.Benefit;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SvodAcquisitionOfferLevelSuppression {
    public final Optional<SvodAcquisitionOfferLevelSuppressionReason> reason;
    public final Optional<ImmutableList<Benefit>> suppressedBenefits;

    private SvodAcquisitionOfferLevelSuppression(Builder builder) {
        this.suppressedBenefits = Optional.fromNullable(builder.suppressedBenefits);
        this.reason = Optional.fromNullable(builder.reason);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SvodAcquisitionOfferLevelSuppression)) {
            return false;
        }
        SvodAcquisitionOfferLevelSuppression svodAcquisitionOfferLevelSuppression = (SvodAcquisitionOfferLevelSuppression) obj;
        return Objects.equal(this.suppressedBenefits, svodAcquisitionOfferLevelSuppression.suppressedBenefits) && Objects.equal(this.reason, svodAcquisitionOfferLevelSuppression.reason);
    }

    public int hashCode() {
        return Objects.hashCode(this.suppressedBenefits, this.reason);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("suppressedBenefits", this.suppressedBenefits).add("reason", this.reason).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public SvodAcquisitionOfferLevelSuppressionReason reason;
        public ImmutableList<Benefit> suppressedBenefits;

        public SvodAcquisitionOfferLevelSuppression build() {
            return new SvodAcquisitionOfferLevelSuppression(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SvodAcquisitionOfferLevelSuppression> {
        private final ListParser<Benefit> mBenefitListParser;
        private final EnumParser<SvodAcquisitionOfferLevelSuppressionReason> mSvodAcquisitionOfferLevelSuppressionReasonParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSvodAcquisitionOfferLevelSuppressionReasonParser = EnumParser.newParser(SvodAcquisitionOfferLevelSuppressionReason.class);
            this.mBenefitListParser = ListParser.newParser(new Benefit.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SvodAcquisitionOfferLevelSuppression parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SvodAcquisitionOfferLevelSuppression:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SvodAcquisitionOfferLevelSuppression parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SvodAcquisitionOfferLevelSuppression:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SvodAcquisitionOfferLevelSuppression parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SvodAcquisitionOfferLevelSuppression");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -934964668) {
                        if (iHashCode == -452605712 && next.equals("suppressedBenefits")) {
                            b = 0;
                        }
                    } else if (next.equals("reason")) {
                        b = 1;
                    }
                    SvodAcquisitionOfferLevelSuppressionReason svodAcquisitionOfferLevelSuppressionReason = null;
                    ImmutableList<Benefit> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mBenefitListParser.parse(jsonNode2);
                        }
                        builder.suppressedBenefits = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            svodAcquisitionOfferLevelSuppressionReason = (SvodAcquisitionOfferLevelSuppressionReason) this.mSvodAcquisitionOfferLevelSuppressionReasonParser.parse(jsonNode2);
                        }
                        builder.reason = svodAcquisitionOfferLevelSuppressionReason;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SvodAcquisitionOfferLevelSuppression so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private SvodAcquisitionOfferLevelSuppression parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -934964668) {
                                if (iHashCode == -452605712 && currentName.equals("suppressedBenefits")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("reason")) {
                                b = 1;
                            }
                            SvodAcquisitionOfferLevelSuppressionReason svodAcquisitionOfferLevelSuppressionReason = null;
                            ImmutableList<Benefit> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mBenefitListParser.parse(jsonParser);
                                }
                                builder.suppressedBenefits = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    svodAcquisitionOfferLevelSuppressionReason = (SvodAcquisitionOfferLevelSuppressionReason) this.mSvodAcquisitionOfferLevelSuppressionReasonParser.parse(jsonParser);
                                }
                                builder.reason = svodAcquisitionOfferLevelSuppressionReason;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SvodAcquisitionOfferLevelSuppression so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
