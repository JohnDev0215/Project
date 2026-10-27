package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.detailpage.OfferStage;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.PrimeAcquisitionOfferLevelSuppression;
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
public class PrimeAcquisition {
    public final Optional<ImmutableList<OfferStage>> offerStages;
    public final Optional<String> offerToken;
    public final Optional<PrimeOfferType> offerType;
    public final Optional<ImmutableList<PrimeAcquisitionOfferLevelSuppression>> suppression;

    private PrimeAcquisition(Builder builder) {
        this.offerToken = Optional.fromNullable(builder.offerToken);
        this.offerStages = Optional.fromNullable(builder.offerStages);
        this.suppression = Optional.fromNullable(builder.suppression);
        this.offerType = Optional.fromNullable(builder.offerType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrimeAcquisition)) {
            return false;
        }
        PrimeAcquisition primeAcquisition = (PrimeAcquisition) obj;
        return Objects.equal(this.offerToken, primeAcquisition.offerToken) && Objects.equal(this.offerStages, primeAcquisition.offerStages) && Objects.equal(this.suppression, primeAcquisition.suppression) && Objects.equal(this.offerType, primeAcquisition.offerType);
    }

    public int hashCode() {
        return Objects.hashCode(this.offerToken, this.offerStages, this.suppression, this.offerType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("offerToken", this.offerToken).add("offerStages", this.offerStages).add("suppression", this.suppression).add("offerType", this.offerType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<OfferStage> offerStages;
        public String offerToken;
        public PrimeOfferType offerType;
        public ImmutableList<PrimeAcquisitionOfferLevelSuppression> suppression;

        public PrimeAcquisition build() {
            return new PrimeAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PrimeAcquisition> {
        private final ListParser<OfferStage> mOfferStageListParser;
        private final ListParser<PrimeAcquisitionOfferLevelSuppression> mPrimeAcquisitionOfferLevelSuppressionListParser;
        private final EnumParser<PrimeOfferType> mPrimeOfferTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mOfferStageListParser = ListParser.newParser(new OfferStage.Parser(objectMapper));
            this.mPrimeAcquisitionOfferLevelSuppressionListParser = ListParser.newParser(new PrimeAcquisitionOfferLevelSuppression.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mPrimeOfferTypeParser = EnumParser.newParser(PrimeOfferType.class);
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
                        case 151374077:
                            if (next.equals("offerToken")) {
                                b = 0;
                            }
                            break;
                        case 373321393:
                            if (next.equals("offerStages")) {
                                b = 1;
                            }
                            break;
                        case 439056691:
                            if (next.equals("suppression")) {
                                b = 2;
                            }
                            break;
                        case 1944555446:
                            if (next.equals("offerType")) {
                                b = 3;
                            }
                            break;
                    }
                    PrimeOfferType primeOfferType = null;
                    String str = null;
                    ImmutableList<OfferStage> immutableList = null;
                    ImmutableList<PrimeAcquisitionOfferLevelSuppression> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.offerToken = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mOfferStageListParser.parse(jsonNode2);
                        }
                        builder.offerStages = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mPrimeAcquisitionOfferLevelSuppressionListParser.parse(jsonNode2);
                        }
                        builder.suppression = immutableList2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            primeOfferType = (PrimeOfferType) this.mPrimeOfferTypeParser.parse(jsonNode2);
                        }
                        builder.offerType = primeOfferType;
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
                                case 151374077:
                                    if (currentName.equals("offerToken")) {
                                        b = 0;
                                    }
                                    break;
                                case 373321393:
                                    if (currentName.equals("offerStages")) {
                                        b = 1;
                                    }
                                    break;
                                case 439056691:
                                    if (currentName.equals("suppression")) {
                                        b = 2;
                                    }
                                    break;
                                case 1944555446:
                                    if (currentName.equals("offerType")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            PrimeOfferType primeOfferType = null;
                            String str = null;
                            ImmutableList<OfferStage> immutableList = null;
                            ImmutableList<PrimeAcquisitionOfferLevelSuppression> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.offerToken = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mOfferStageListParser.parse(jsonParser);
                                }
                                builder.offerStages = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mPrimeAcquisitionOfferLevelSuppressionListParser.parse(jsonParser);
                                }
                                builder.suppression = immutableList2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    primeOfferType = (PrimeOfferType) this.mPrimeOfferTypeParser.parse(jsonParser);
                                }
                                builder.offerType = primeOfferType;
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
