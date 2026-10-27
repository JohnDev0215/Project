package com.amazon.avtitleactionaggregationservice.model.acquisitions;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.LanguageProperties;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.BenefitAvailabilityWindow;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.OfferStage;
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
    public final Optional<String> benefitName;
    public final Optional<Boolean> isFreeTrialOffered;
    public final Optional<LanguageProperties> languageProperties;
    public final Optional<ImmutableList<OfferStage>> offerStages;
    public final Optional<Integer> rank;

    private SvodAcquisition(Builder builder) {
        this.isFreeTrialOffered = Optional.fromNullable(builder.isFreeTrialOffered);
        this.benefitAvailabilityWindow = Optional.fromNullable(builder.benefitAvailabilityWindow);
        this.rank = Optional.fromNullable(builder.rank);
        this.benefitName = Optional.fromNullable(builder.benefitName);
        this.benefitId = Optional.fromNullable(builder.benefitId);
        this.languageProperties = Optional.fromNullable(builder.languageProperties);
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
        return Objects.equal(this.isFreeTrialOffered, svodAcquisition.isFreeTrialOffered) && Objects.equal(this.benefitAvailabilityWindow, svodAcquisition.benefitAvailabilityWindow) && Objects.equal(this.rank, svodAcquisition.rank) && Objects.equal(this.benefitName, svodAcquisition.benefitName) && Objects.equal(this.benefitId, svodAcquisition.benefitId) && Objects.equal(this.languageProperties, svodAcquisition.languageProperties) && Objects.equal(this.offerStages, svodAcquisition.offerStages);
    }

    public int hashCode() {
        return Objects.hashCode(this.isFreeTrialOffered, this.benefitAvailabilityWindow, this.rank, this.benefitName, this.benefitId, this.languageProperties, this.offerStages);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("isFreeTrialOffered", this.isFreeTrialOffered).add("benefitAvailabilityWindow", this.benefitAvailabilityWindow).add("rank", this.rank).add("benefitName", this.benefitName).add("benefitId", this.benefitId).add("languageProperties", this.languageProperties).add("offerStages", this.offerStages).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public BenefitAvailabilityWindow benefitAvailabilityWindow;
        public String benefitId;
        public String benefitName;
        public Boolean isFreeTrialOffered;
        public LanguageProperties languageProperties;
        public ImmutableList<OfferStage> offerStages;
        public Integer rank;

        public SvodAcquisition build() {
            return new SvodAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SvodAcquisition> {
        private final BenefitAvailabilityWindow.Parser mBenefitAvailabilityWindowParser;
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final LanguageProperties.Parser mLanguagePropertiesParser;
        private final ListParser<OfferStage> mOfferStageListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mOfferStageListParser = ListParser.newParser(new OfferStage.Parser(objectMapper));
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mBenefitAvailabilityWindowParser = new BenefitAvailabilityWindow.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mLanguagePropertiesParser = new LanguageProperties.Parser(objectMapper);
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
            Boolean bool;
            BenefitAvailabilityWindow benefitAvailabilityWindow;
            Integer num;
            String str;
            String str2;
            LanguageProperties languageProperties;
            ImmutableList<OfferStage> immutableList;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SvodAcquisition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -771462693:
                            if (next.equals("isFreeTrialOffered")) {
                                b = 0;
                            }
                            break;
                        case -693352926:
                            if (next.equals("benefitAvailabilityWindow")) {
                                b = 1;
                            }
                            break;
                        case -550207550:
                            if (next.equals("benefitName")) {
                                b = 3;
                            }
                            break;
                        case 3492908:
                            if (next.equals("rank")) {
                                b = 2;
                            }
                            break;
                        case 373321393:
                            if (next.equals("offerStages")) {
                                b = 6;
                            }
                            break;
                        case 723448850:
                            if (next.equals("benefitId")) {
                                b = 4;
                            }
                            break;
                        case 1110683307:
                            if (next.equals("languageProperties")) {
                                b = 5;
                            }
                            break;
                    }
                    bool = null;
                    immutableList = null;
                    languageProperties = null;
                    str2 = null;
                    str = null;
                    num = null;
                    benefitAvailabilityWindow = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isFreeTrialOffered = bool;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            benefitAvailabilityWindow = this.mBenefitAvailabilityWindowParser.parse(jsonNode2);
                        }
                        builder.benefitAvailabilityWindow = benefitAvailabilityWindow;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.rank = num;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitName = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitId = str2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            languageProperties = this.mLanguagePropertiesParser.parse(jsonNode2);
                        }
                        builder.languageProperties = languageProperties;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mOfferStageListParser.parse(jsonNode2);
                        }
                        builder.offerStages = immutableList;
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
                                case -771462693:
                                    if (currentName.equals("isFreeTrialOffered")) {
                                        b = 0;
                                    }
                                    break;
                                case -693352926:
                                    if (currentName.equals("benefitAvailabilityWindow")) {
                                        b = 1;
                                    }
                                    break;
                                case -550207550:
                                    if (currentName.equals("benefitName")) {
                                        b = 3;
                                    }
                                    break;
                                case 3492908:
                                    if (currentName.equals("rank")) {
                                        b = 2;
                                    }
                                    break;
                                case 373321393:
                                    if (currentName.equals("offerStages")) {
                                        b = 6;
                                    }
                                    break;
                                case 723448850:
                                    if (currentName.equals("benefitId")) {
                                        b = 4;
                                    }
                                    break;
                                case 1110683307:
                                    if (currentName.equals("languageProperties")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            Boolean bool = null;
                            ImmutableList<OfferStage> immutableList = null;
                            LanguageProperties languageProperties = null;
                            String str = null;
                            String str2 = null;
                            Integer num = null;
                            BenefitAvailabilityWindow benefitAvailabilityWindow = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.isFreeTrialOffered = bool;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        benefitAvailabilityWindow = this.mBenefitAvailabilityWindowParser.parse(jsonParser);
                                    }
                                    builder.benefitAvailabilityWindow = benefitAvailabilityWindow;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.rank = num;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.benefitName = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.benefitId = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        languageProperties = this.mLanguagePropertiesParser.parse(jsonParser);
                                    }
                                    builder.languageProperties = languageProperties;
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
