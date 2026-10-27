package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.PrimeAcquisition;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.PrimeAcquisitionGroupLevelSuppression;
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
public class PrimeAcquisitions {
    public final Optional<ImmutableList<PrimeAcquisition>> offers;
    public final Optional<ImmutableList<PrimeAcquisitionGroupLevelSuppression>> suppression;

    private PrimeAcquisitions(Builder builder) {
        this.suppression = Optional.fromNullable(builder.suppression);
        this.offers = Optional.fromNullable(builder.offers);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrimeAcquisitions)) {
            return false;
        }
        PrimeAcquisitions primeAcquisitions = (PrimeAcquisitions) obj;
        return Objects.equal(this.suppression, primeAcquisitions.suppression) && Objects.equal(this.offers, primeAcquisitions.offers);
    }

    public int hashCode() {
        return Objects.hashCode(this.suppression, this.offers);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("suppression", this.suppression).add("offers", this.offers).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<PrimeAcquisition> offers;
        public ImmutableList<PrimeAcquisitionGroupLevelSuppression> suppression;

        public PrimeAcquisitions build() {
            return new PrimeAcquisitions(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PrimeAcquisitions> {
        private final ListParser<PrimeAcquisitionGroupLevelSuppression> mPrimeAcquisitionGroupLevelSuppressionListParser;
        private final ListParser<PrimeAcquisition> mPrimeAcquisitionListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPrimeAcquisitionGroupLevelSuppressionListParser = ListParser.newParser(new PrimeAcquisitionGroupLevelSuppression.Parser(objectMapper));
            this.mPrimeAcquisitionListParser = ListParser.newParser(new PrimeAcquisition.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PrimeAcquisitions parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisitions:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PrimeAcquisitions parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PrimeAcquisitions:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PrimeAcquisitions parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PrimeAcquisitions");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1019793001) {
                        if (iHashCode == 439056691 && next.equals("suppression")) {
                            b = 0;
                        }
                    } else if (next.equals("offers")) {
                        b = 1;
                    }
                    ImmutableList<PrimeAcquisition> immutableList = null;
                    ImmutableList<PrimeAcquisitionGroupLevelSuppression> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mPrimeAcquisitionGroupLevelSuppressionListParser.parse(jsonNode2);
                        }
                        builder.suppression = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mPrimeAcquisitionListParser.parse(jsonNode2);
                        }
                        builder.offers = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PrimeAcquisitions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PrimeAcquisitions parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1019793001) {
                                if (iHashCode == 439056691 && currentName.equals("suppression")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("offers")) {
                                b = 1;
                            }
                            ImmutableList<PrimeAcquisition> immutableList = null;
                            ImmutableList<PrimeAcquisitionGroupLevelSuppression> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mPrimeAcquisitionGroupLevelSuppressionListParser.parse(jsonParser);
                                }
                                builder.suppression = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mPrimeAcquisitionListParser.parse(jsonParser);
                                }
                                builder.offers = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PrimeAcquisitions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
