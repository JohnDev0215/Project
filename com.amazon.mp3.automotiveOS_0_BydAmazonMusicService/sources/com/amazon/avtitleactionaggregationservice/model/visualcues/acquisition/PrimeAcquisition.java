package com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
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
public class PrimeAcquisition {
    public final Optional<String> benefitId;
    public final Optional<String> benefitName;
    public final Optional<Boolean> isFreeTrialOffered;
    public final Optional<OfferStages> offerStages;
    public final Optional<Integer> rank;

    private PrimeAcquisition(Builder builder) {
        this.offerStages = Optional.fromNullable(builder.offerStages);
        this.isFreeTrialOffered = Optional.fromNullable(builder.isFreeTrialOffered);
        this.rank = Optional.fromNullable(builder.rank);
        this.benefitName = Optional.fromNullable(builder.benefitName);
        this.benefitId = Optional.fromNullable(builder.benefitId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrimeAcquisition)) {
            return false;
        }
        PrimeAcquisition primeAcquisition = (PrimeAcquisition) obj;
        return Objects.equal(this.offerStages, primeAcquisition.offerStages) && Objects.equal(this.isFreeTrialOffered, primeAcquisition.isFreeTrialOffered) && Objects.equal(this.rank, primeAcquisition.rank) && Objects.equal(this.benefitName, primeAcquisition.benefitName) && Objects.equal(this.benefitId, primeAcquisition.benefitId);
    }

    public int hashCode() {
        return Objects.hashCode(this.offerStages, this.isFreeTrialOffered, this.rank, this.benefitName, this.benefitId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("offerStages", this.offerStages).add("isFreeTrialOffered", this.isFreeTrialOffered).add("rank", this.rank).add("benefitName", this.benefitName).add("benefitId", this.benefitId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String benefitId;
        public String benefitName;
        public Boolean isFreeTrialOffered;
        public OfferStages offerStages;
        public Integer rank;

        public PrimeAcquisition build() {
            return new PrimeAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PrimeAcquisition> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final OfferStages.Parser mOfferStagesParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mOfferStagesParser = new OfferStages.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PrimeAcquisition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PrimeAcquisition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PrimeAcquisition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PrimeAcquisition");
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
                                b = 0;
                            }
                            break;
                        case 723448850:
                            if (next.equals("benefitId")) {
                                b = 4;
                            }
                            break;
                    }
                    String str = null;
                    OfferStages offerStages = null;
                    Boolean bool = null;
                    Integer num = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            offerStages = this.mOfferStagesParser.parse(jsonNode2);
                        }
                        builder.offerStages = offerStages;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isFreeTrialOffered = bool;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.rank = num;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitName = str2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PrimeAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PrimeAcquisition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 0;
                                    }
                                    break;
                                case 723448850:
                                    if (currentName.equals("benefitId")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            OfferStages offerStages = null;
                            Boolean bool = null;
                            Integer num = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    offerStages = this.mOfferStagesParser.parse(jsonParser);
                                }
                                builder.offerStages = offerStages;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.isFreeTrialOffered = bool;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.rank = num;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.benefitName = str2;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.benefitId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PrimeAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
