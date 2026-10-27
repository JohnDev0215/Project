package com.amazon.avtitleactionaggregationservice.model.detailpage;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.detailpage.Price;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Promotion {
    public final Optional<String> id;
    public final Optional<Price> nonPromoPrice;
    public final Optional<Date> promotionEndDate;

    private Promotion(Builder builder) {
        this.id = Optional.fromNullable(builder.id);
        this.nonPromoPrice = Optional.fromNullable(builder.nonPromoPrice);
        this.promotionEndDate = Optional.fromNullable(builder.promotionEndDate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Promotion)) {
            return false;
        }
        Promotion promotion = (Promotion) obj;
        return Objects.equal(this.id, promotion.id) && Objects.equal(this.nonPromoPrice, promotion.nonPromoPrice) && Objects.equal(this.promotionEndDate, promotion.promotionEndDate);
    }

    public int hashCode() {
        return Objects.hashCode(this.id, this.nonPromoPrice, this.promotionEndDate);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", this.id).add("nonPromoPrice", this.nonPromoPrice).add("promotionEndDate", this.promotionEndDate).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String id;
        public Price nonPromoPrice;
        public Date promotionEndDate;

        public Promotion build() {
            return new Promotion(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Promotion> {
        private final Price.Parser mPriceParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.DateParser mTimeStampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
            this.mTimeStampParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Promotion parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Promotion:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Promotion parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Promotion:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Promotion parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Promotion");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3355) {
                        if (iHashCode != 538162438) {
                            if (iHashCode == 2092266535 && next.equals("nonPromoPrice")) {
                                b = 1;
                            }
                        } else if (next.equals("promotionEndDate")) {
                            b = 2;
                        }
                    } else if (next.equals("id")) {
                        b = 0;
                    }
                    Date date = null;
                    String str = null;
                    Price price = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.nonPromoPrice = price;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date = this.mTimeStampParser.parse(jsonNode2);
                        }
                        builder.promotionEndDate = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Promotion so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Promotion parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3355) {
                                if (iHashCode != 538162438) {
                                    if (iHashCode == 2092266535 && currentName.equals("nonPromoPrice")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("promotionEndDate")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("id")) {
                                b = 0;
                            }
                            Date date = null;
                            String str = null;
                            Price price = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.id = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.nonPromoPrice = price;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mTimeStampParser.parse(jsonParser);
                                }
                                builder.promotionEndDate = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Promotion so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
