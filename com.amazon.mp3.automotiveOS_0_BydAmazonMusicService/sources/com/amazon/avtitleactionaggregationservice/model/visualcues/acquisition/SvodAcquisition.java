package com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.LinearData;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.OfferStages;
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
public class SvodAcquisition {
    public final Optional<String> benefitId;
    public final Optional<String> benefitName;
    public final Optional<Boolean> isFreeTrialOffered;
    public final Optional<Boolean> isLinear;
    public final Optional<LinearData> linearData;
    public final Optional<OfferStages> offerStages;
    public final Optional<Integer> rank;

    private SvodAcquisition(Builder builder) {
        this.rank = Optional.fromNullable(builder.rank);
        this.isLinear = Optional.fromNullable(builder.isLinear);
        this.linearData = Optional.fromNullable(builder.linearData);
        this.benefitName = Optional.fromNullable(builder.benefitName);
        this.benefitId = Optional.fromNullable(builder.benefitId);
        this.isFreeTrialOffered = Optional.fromNullable(builder.isFreeTrialOffered);
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
        return Objects.equal(this.rank, svodAcquisition.rank) && Objects.equal(this.isLinear, svodAcquisition.isLinear) && Objects.equal(this.linearData, svodAcquisition.linearData) && Objects.equal(this.benefitName, svodAcquisition.benefitName) && Objects.equal(this.benefitId, svodAcquisition.benefitId) && Objects.equal(this.isFreeTrialOffered, svodAcquisition.isFreeTrialOffered) && Objects.equal(this.offerStages, svodAcquisition.offerStages);
    }

    public int hashCode() {
        return Objects.hashCode(this.rank, this.isLinear, this.linearData, this.benefitName, this.benefitId, this.isFreeTrialOffered, this.offerStages);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("rank", this.rank).add("isLinear", this.isLinear).add("linearData", this.linearData).add("benefitName", this.benefitName).add("benefitId", this.benefitId).add("isFreeTrialOffered", this.isFreeTrialOffered).add("offerStages", this.offerStages).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String benefitId;
        public String benefitName;
        public Boolean isFreeTrialOffered;
        public Boolean isLinear;
        public LinearData linearData;
        public OfferStages offerStages;
        public Integer rank;

        public SvodAcquisition build() {
            return new SvodAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SvodAcquisition> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final LinearData.Parser mLinearDataParser;
        private final OfferStages.Parser mOfferStagesParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mLinearDataParser = new LinearData.Parser(objectMapper);
            this.mOfferStagesParser = new OfferStages.Parser(objectMapper);
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
            Integer num;
            Boolean bool;
            LinearData linearData;
            String str;
            String str2;
            Boolean bool2;
            OfferStages offerStages;
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
                                b = 5;
                            }
                            break;
                        case -550207550:
                            if (next.equals("benefitName")) {
                                b = 3;
                            }
                            break;
                        case -428637969:
                            if (next.equals("isLinear")) {
                                b = 1;
                            }
                            break;
                        case 3492908:
                            if (next.equals("rank")) {
                                b = 0;
                            }
                            break;
                        case 210819183:
                            if (next.equals("linearData")) {
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
                    }
                    num = null;
                    offerStages = null;
                    bool2 = null;
                    str2 = null;
                    str = null;
                    linearData = null;
                    bool = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.rank = num;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isLinear = bool;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            linearData = this.mLinearDataParser.parse(jsonNode2);
                        }
                        builder.linearData = linearData;
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
                            bool2 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isFreeTrialOffered = bool2;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            offerStages = this.mOfferStagesParser.parse(jsonNode2);
                        }
                        builder.offerStages = offerStages;
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
                                        b = 5;
                                    }
                                    break;
                                case -550207550:
                                    if (currentName.equals("benefitName")) {
                                        b = 3;
                                    }
                                    break;
                                case -428637969:
                                    if (currentName.equals("isLinear")) {
                                        b = 1;
                                    }
                                    break;
                                case 3492908:
                                    if (currentName.equals("rank")) {
                                        b = 0;
                                    }
                                    break;
                                case 210819183:
                                    if (currentName.equals("linearData")) {
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
                            }
                            Integer num = null;
                            OfferStages offerStages = null;
                            Boolean bool = null;
                            String str = null;
                            String str2 = null;
                            LinearData linearData = null;
                            Boolean bool2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.rank = num;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool2 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.isLinear = bool2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        linearData = this.mLinearDataParser.parse(jsonParser);
                                    }
                                    builder.linearData = linearData;
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
                                        bool = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.isFreeTrialOffered = bool;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        offerStages = this.mOfferStagesParser.parse(jsonParser);
                                    }
                                    builder.offerStages = offerStages;
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
