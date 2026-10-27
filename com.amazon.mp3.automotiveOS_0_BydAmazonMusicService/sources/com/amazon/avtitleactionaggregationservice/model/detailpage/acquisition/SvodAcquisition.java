package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.detailpage.OfferStage;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.BenefitAvailabilityWindow;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.SvodAcquisitionOfferLevelSuppression;
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
public class SvodAcquisition {
    public final Optional<BenefitAvailabilityWindow> benefitAvailabilityWindow;
    public final Optional<String> benefitId;
    public final Optional<Boolean> familyHasMultipleEligibleOffers;
    public final Optional<Boolean> isLinear;
    public final Optional<ImmutableList<OfferStage>> offerStages;
    public final Optional<String> offerToken;
    public final Optional<ImmutableList<SvodAcquisitionOfferLevelSuppression>> suppression;

    private SvodAcquisition(Builder builder) {
        this.benefitId = Optional.fromNullable(builder.benefitId);
        this.isLinear = Optional.fromNullable(builder.isLinear);
        this.benefitAvailabilityWindow = Optional.fromNullable(builder.benefitAvailabilityWindow);
        this.offerToken = Optional.fromNullable(builder.offerToken);
        this.familyHasMultipleEligibleOffers = Optional.fromNullable(builder.familyHasMultipleEligibleOffers);
        this.suppression = Optional.fromNullable(builder.suppression);
        this.offerStages = Optional.fromNullable(builder.offerStages);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SvodAcquisition)) {
            return false;
        }
        SvodAcquisition svodAcquisition = (SvodAcquisition) obj;
        return Objects.equal(this.benefitId, svodAcquisition.benefitId) && Objects.equal(this.isLinear, svodAcquisition.isLinear) && Objects.equal(this.benefitAvailabilityWindow, svodAcquisition.benefitAvailabilityWindow) && Objects.equal(this.offerToken, svodAcquisition.offerToken) && Objects.equal(this.familyHasMultipleEligibleOffers, svodAcquisition.familyHasMultipleEligibleOffers) && Objects.equal(this.suppression, svodAcquisition.suppression) && Objects.equal(this.offerStages, svodAcquisition.offerStages);
    }

    public int hashCode() {
        return Objects.hashCode(this.benefitId, this.isLinear, this.benefitAvailabilityWindow, this.offerToken, this.familyHasMultipleEligibleOffers, this.suppression, this.offerStages);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("benefitId", this.benefitId).add("isLinear", this.isLinear).add("benefitAvailabilityWindow", this.benefitAvailabilityWindow).add("offerToken", this.offerToken).add("familyHasMultipleEligibleOffers", this.familyHasMultipleEligibleOffers).add("suppression", this.suppression).add("offerStages", this.offerStages).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public BenefitAvailabilityWindow benefitAvailabilityWindow;
        public String benefitId;
        public Boolean familyHasMultipleEligibleOffers;
        public Boolean isLinear;
        public ImmutableList<OfferStage> offerStages;
        public String offerToken;
        public ImmutableList<SvodAcquisitionOfferLevelSuppression> suppression;

        public SvodAcquisition build() {
            return new SvodAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SvodAcquisition> {
        private final BenefitAvailabilityWindow.Parser mBenefitAvailabilityWindowParser;
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final ListParser<OfferStage> mOfferStageListParser;
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<SvodAcquisitionOfferLevelSuppression> mSvodAcquisitionOfferLevelSuppressionListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSvodAcquisitionOfferLevelSuppressionListParser = ListParser.newParser(new SvodAcquisitionOfferLevelSuppression.Parser(objectMapper));
            this.mOfferStageListParser = ListParser.newParser(new OfferStage.Parser(objectMapper));
            this.mBenefitAvailabilityWindowParser = new BenefitAvailabilityWindow.Parser(objectMapper);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SvodAcquisition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SvodAcquisition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SvodAcquisition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SvodAcquisition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SvodAcquisition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            Boolean bool;
            BenefitAvailabilityWindow benefitAvailabilityWindow;
            String str2;
            Boolean bool2;
            ImmutableList<SvodAcquisitionOfferLevelSuppression> immutableList;
            ImmutableList<OfferStage> immutableList2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SvodAcquisition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -693352926:
                            if (next.equals("benefitAvailabilityWindow")) {
                                b = 2;
                            }
                            break;
                        case -428637969:
                            if (next.equals("isLinear")) {
                                b = 1;
                            }
                            break;
                        case 151374077:
                            if (next.equals("offerToken")) {
                                b = 3;
                            }
                            break;
                        case 373321393:
                            if (next.equals("offerStages")) {
                                b = 6;
                            }
                            break;
                        case 439056691:
                            if (next.equals("suppression")) {
                                b = 5;
                            }
                            break;
                        case 723448850:
                            if (next.equals("benefitId")) {
                                b = 0;
                            }
                            break;
                        case 1792785588:
                            if (next.equals("familyHasMultipleEligibleOffers")) {
                                b = 4;
                            }
                            break;
                    }
                    str = null;
                    immutableList2 = null;
                    immutableList = null;
                    bool2 = null;
                    str2 = null;
                    benefitAvailabilityWindow = null;
                    bool = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitId = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isLinear = bool;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            benefitAvailabilityWindow = this.mBenefitAvailabilityWindowParser.parse(jsonNode2);
                        }
                        builder.benefitAvailabilityWindow = benefitAvailabilityWindow;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.offerToken = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            bool2 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.familyHasMultipleEligibleOffers = bool2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSvodAcquisitionOfferLevelSuppressionListParser.parse(jsonNode2);
                        }
                        builder.suppression = immutableList;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mOfferStageListParser.parse(jsonNode2);
                        }
                        builder.offerStages = immutableList2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SvodAcquisition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -693352926:
                                    if (currentName.equals("benefitAvailabilityWindow")) {
                                        b = 2;
                                    }
                                    break;
                                case -428637969:
                                    if (currentName.equals("isLinear")) {
                                        b = 1;
                                    }
                                    break;
                                case 151374077:
                                    if (currentName.equals("offerToken")) {
                                        b = 3;
                                    }
                                    break;
                                case 373321393:
                                    if (currentName.equals("offerStages")) {
                                        b = 6;
                                    }
                                    break;
                                case 439056691:
                                    if (currentName.equals("suppression")) {
                                        b = 5;
                                    }
                                    break;
                                case 723448850:
                                    if (currentName.equals("benefitId")) {
                                        b = 0;
                                    }
                                    break;
                                case 1792785588:
                                    if (currentName.equals("familyHasMultipleEligibleOffers")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            ImmutableList<OfferStage> immutableList = null;
                            ImmutableList<SvodAcquisitionOfferLevelSuppression> immutableList2 = null;
                            Boolean bool = null;
                            String str2 = null;
                            BenefitAvailabilityWindow benefitAvailabilityWindow = null;
                            Boolean bool2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.benefitId = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool2 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.isLinear = bool2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        benefitAvailabilityWindow = this.mBenefitAvailabilityWindowParser.parse(jsonParser);
                                    }
                                    builder.benefitAvailabilityWindow = benefitAvailabilityWindow;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.offerToken = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.familyHasMultipleEligibleOffers = bool;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mSvodAcquisitionOfferLevelSuppressionListParser.parse(jsonParser);
                                    }
                                    builder.suppression = immutableList2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mOfferStageListParser.parse(jsonParser);
                                    }
                                    builder.offerStages = immutableList;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
