package com.amazon.avtitleactionaggregationservice.model.acquisitions;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.Price;
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
public class DiscountTreatment {
    public final Optional<Price> priceAfterDiscount;
    public final Optional<ImmutableList<String>> promotionReasons;

    private DiscountTreatment(Builder builder) {
        this.priceAfterDiscount = Optional.fromNullable(builder.priceAfterDiscount);
        this.promotionReasons = Optional.fromNullable(builder.promotionReasons);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DiscountTreatment)) {
            return false;
        }
        DiscountTreatment discountTreatment = (DiscountTreatment) obj;
        return Objects.equal(this.priceAfterDiscount, discountTreatment.priceAfterDiscount) && Objects.equal(this.promotionReasons, discountTreatment.promotionReasons);
    }

    public int hashCode() {
        return Objects.hashCode(this.priceAfterDiscount, this.promotionReasons);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("priceAfterDiscount", this.priceAfterDiscount).add("promotionReasons", this.promotionReasons).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Price priceAfterDiscount;
        public ImmutableList<String> promotionReasons;

        public DiscountTreatment build() {
            return new DiscountTreatment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DiscountTreatment> {
        private final Price.Parser mPriceParser;
        private final ListParser<String> mPromotionReasonListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
            this.mPromotionReasonListParser = ListParser.newParser(SimpleParsers.StringParser.INSTANCE);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DiscountTreatment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DiscountTreatment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DiscountTreatment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DiscountTreatment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DiscountTreatment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DiscountTreatment");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1068211060) {
                        if (iHashCode == -538320780 && next.equals("priceAfterDiscount")) {
                            b = 0;
                        }
                    } else if (next.equals("promotionReasons")) {
                        b = 1;
                    }
                    ImmutableList<String> immutableList = null;
                    Price price = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.priceAfterDiscount = price;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mPromotionReasonListParser.parse(jsonNode2);
                        }
                        builder.promotionReasons = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DiscountTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DiscountTreatment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1068211060) {
                                if (iHashCode == -538320780 && currentName.equals("priceAfterDiscount")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("promotionReasons")) {
                                b = 1;
                            }
                            ImmutableList<String> immutableList = null;
                            Price price = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.priceAfterDiscount = price;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mPromotionReasonListParser.parse(jsonParser);
                                }
                                builder.promotionReasons = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DiscountTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
