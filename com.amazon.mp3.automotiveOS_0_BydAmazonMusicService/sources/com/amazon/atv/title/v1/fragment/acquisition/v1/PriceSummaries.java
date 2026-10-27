package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.atv.title.v1.fragment.acquisition.v1.Price;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class PriceSummaries {
    public final Optional<Price> lowestPurchasePrice;
    public final Optional<Price> lowestRentalPrice;

    private PriceSummaries(Builder builder) {
        this.lowestPurchasePrice = Optional.fromNullable(builder.lowestPurchasePrice);
        this.lowestRentalPrice = Optional.fromNullable(builder.lowestRentalPrice);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PriceSummaries)) {
            return false;
        }
        PriceSummaries priceSummaries = (PriceSummaries) obj;
        return Objects.equal(this.lowestPurchasePrice, priceSummaries.lowestPurchasePrice) && Objects.equal(this.lowestRentalPrice, priceSummaries.lowestRentalPrice);
    }

    public int hashCode() {
        return Objects.hashCode(this.lowestPurchasePrice, this.lowestRentalPrice);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("lowestPurchasePrice", this.lowestPurchasePrice).add("lowestRentalPrice", this.lowestRentalPrice).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Price lowestPurchasePrice;
        public Price lowestRentalPrice;

        public PriceSummaries build() {
            return new PriceSummaries(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PriceSummaries> {
        private final Price.Parser mPriceParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PriceSummaries parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PriceSummaries:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PriceSummaries parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PriceSummaries:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PriceSummaries parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PriceSummaries");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -91084429) {
                        if (iHashCode == 311951414 && next.equals("lowestPurchasePrice")) {
                            b = 0;
                        }
                    } else if (next.equals("lowestRentalPrice")) {
                        b = 1;
                    }
                    Price price = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.lowestPurchasePrice = price;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.lowestRentalPrice = price;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PriceSummaries so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PriceSummaries parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -91084429) {
                                if (iHashCode == 311951414 && currentName.equals("lowestPurchasePrice")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("lowestRentalPrice")) {
                                b = 1;
                            }
                            Price price = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.lowestPurchasePrice = price;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.lowestRentalPrice = price;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PriceSummaries so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
