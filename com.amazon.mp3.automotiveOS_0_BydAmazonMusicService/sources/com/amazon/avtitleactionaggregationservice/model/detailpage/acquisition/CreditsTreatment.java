package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.detailpage.Price;
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
public class CreditsTreatment {
    public final Optional<Price> priceWithCredits;
    public final Optional<Price> priceWithoutCredits;
    public final Optional<Price> totalCreditAmount;

    private CreditsTreatment(Builder builder) {
        this.priceWithCredits = Optional.fromNullable(builder.priceWithCredits);
        this.priceWithoutCredits = Optional.fromNullable(builder.priceWithoutCredits);
        this.totalCreditAmount = Optional.fromNullable(builder.totalCreditAmount);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreditsTreatment)) {
            return false;
        }
        CreditsTreatment creditsTreatment = (CreditsTreatment) obj;
        return Objects.equal(this.priceWithCredits, creditsTreatment.priceWithCredits) && Objects.equal(this.priceWithoutCredits, creditsTreatment.priceWithoutCredits) && Objects.equal(this.totalCreditAmount, creditsTreatment.totalCreditAmount);
    }

    public int hashCode() {
        return Objects.hashCode(this.priceWithCredits, this.priceWithoutCredits, this.totalCreditAmount);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("priceWithCredits", this.priceWithCredits).add("priceWithoutCredits", this.priceWithoutCredits).add("totalCreditAmount", this.totalCreditAmount).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Price priceWithCredits;
        public Price priceWithoutCredits;
        public Price totalCreditAmount;

        public CreditsTreatment build() {
            return new CreditsTreatment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CreditsTreatment> {
        private final Price.Parser mPriceParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CreditsTreatment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CreditsTreatment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CreditsTreatment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CreditsTreatment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CreditsTreatment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CreditsTreatment");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -322145771) {
                        if (iHashCode != 962290779) {
                            if (iHashCode == 1813827563 && next.equals("priceWithCredits")) {
                                b = 0;
                            }
                        } else if (next.equals("priceWithoutCredits")) {
                            b = 1;
                        }
                    } else if (next.equals("totalCreditAmount")) {
                        b = 2;
                    }
                    Price price = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.priceWithCredits = price;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.priceWithoutCredits = price;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.totalCreditAmount = price;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CreditsTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CreditsTreatment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -322145771) {
                                if (iHashCode != 962290779) {
                                    if (iHashCode == 1813827563 && currentName.equals("priceWithCredits")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("priceWithoutCredits")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("totalCreditAmount")) {
                                b = 2;
                            }
                            Price price = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.priceWithCredits = price;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.priceWithoutCredits = price;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.totalCreditAmount = price;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CreditsTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
