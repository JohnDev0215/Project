package com.amazon.atv.catalog.ratings;

import com.amazon.atv.catalog.ratings.InternationalRating;
import com.amazon.atv.catalog.ratings.Rating;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
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
public class InternationalProduct {
    public final Optional<ImmutableList<InternationalRating>> availableRatings;
    public final Optional<Rating> decisionRating;
    public final Optional<String> identifier;

    private InternationalProduct(Builder builder) {
        this.availableRatings = Optional.fromNullable(builder.availableRatings);
        this.decisionRating = Optional.fromNullable(builder.decisionRating);
        this.identifier = Optional.fromNullable(builder.identifier);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InternationalProduct)) {
            return false;
        }
        InternationalProduct internationalProduct = (InternationalProduct) obj;
        return Objects.equal(this.availableRatings, internationalProduct.availableRatings) && Objects.equal(this.decisionRating, internationalProduct.decisionRating) && Objects.equal(this.identifier, internationalProduct.identifier);
    }

    public int hashCode() {
        return Objects.hashCode(this.availableRatings, this.decisionRating, this.identifier);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("availableRatings", this.availableRatings).add("decisionRating", this.decisionRating).add("identifier", this.identifier).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<InternationalRating> availableRatings;
        public Rating decisionRating;
        public String identifier;

        public InternationalProduct build() {
            return new InternationalProduct(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<InternationalProduct> {
        private final Rating.Parser mRatingParser;
        private final SimpleParsers.StringParser midentifierParser;
        private final ListParser<InternationalRating> minternationalRatingListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingParser = new Rating.Parser(objectMapper);
            this.minternationalRatingListParser = ListParser.newParser(new InternationalRating.Parser(objectMapper));
            this.midentifierParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public InternationalProduct parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternationalProduct:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public InternationalProduct parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternationalProduct:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private InternationalProduct parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "InternationalProduct");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1618432855) {
                        if (iHashCode != -1602974867) {
                            if (iHashCode == 1139701913 && next.equals("decisionRating")) {
                                b = 1;
                            }
                        } else if (next.equals("availableRatings")) {
                            b = 0;
                        }
                    } else if (next.equals("identifier")) {
                        b = 2;
                    }
                    String str = null;
                    ImmutableList<InternationalRating> immutableList = null;
                    Rating rating = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.minternationalRatingListParser.parse(jsonNode2);
                        }
                        builder.availableRatings = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            rating = this.mRatingParser.parse(jsonNode2);
                        }
                        builder.decisionRating = rating;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.midentifierParser.parse(jsonNode2);
                        }
                        builder.identifier = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing InternationalProduct so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private InternationalProduct parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1618432855) {
                                if (iHashCode != -1602974867) {
                                    if (iHashCode == 1139701913 && currentName.equals("decisionRating")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("availableRatings")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("identifier")) {
                                b = 2;
                            }
                            String str = null;
                            ImmutableList<InternationalRating> immutableList = null;
                            Rating rating = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.minternationalRatingListParser.parse(jsonParser);
                                }
                                builder.availableRatings = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rating = this.mRatingParser.parse(jsonParser);
                                }
                                builder.decisionRating = rating;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.midentifierParser.parse(jsonParser);
                                }
                                builder.identifier = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing InternationalProduct so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
