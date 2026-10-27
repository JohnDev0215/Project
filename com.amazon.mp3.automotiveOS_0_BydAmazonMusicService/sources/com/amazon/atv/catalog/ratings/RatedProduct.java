package com.amazon.atv.catalog.ratings;

import com.amazon.atv.catalog.Product;
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
public class RatedProduct extends Product {
    public final boolean adult;
    public final Optional<ImmutableList<Rating>> ratings;
    public final Optional<Rating> simplifiedRating;
    public final Optional<String> title;

    private RatedProduct(Builder builder) {
        super(builder);
        this.simplifiedRating = Optional.fromNullable(builder.simplifiedRating);
        this.title = Optional.fromNullable(builder.title);
        this.ratings = Optional.fromNullable(builder.ratings);
        this.adult = builder.adult;
    }

    @Override // com.amazon.atv.catalog.Product
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RatedProduct)) {
            return false;
        }
        RatedProduct ratedProduct = (RatedProduct) obj;
        return super.equals(obj) && Objects.equal(this.simplifiedRating, ratedProduct.simplifiedRating) && Objects.equal(this.title, ratedProduct.title) && Objects.equal(this.ratings, ratedProduct.ratings) && Objects.equal(Boolean.valueOf(this.adult), Boolean.valueOf(ratedProduct.adult));
    }

    @Override // com.amazon.atv.catalog.Product
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.simplifiedRating, this.title, this.ratings, Boolean.valueOf(this.adult));
    }

    @Override // com.amazon.atv.catalog.Product
    public String toString() {
        return MoreObjects.toStringHelper(this).add("simplifiedRating", this.simplifiedRating).add("title", this.title).add("ratings", this.ratings).add("adult", this.adult).toString();
    }

    @NotThreadSafe
    public static class Builder extends Product.Builder {
        public boolean adult;
        public ImmutableList<Rating> ratings;
        public Rating simplifiedRating;
        public String title;

        @Override // com.amazon.atv.catalog.Product.Builder
        public RatedProduct build() {
            return new RatedProduct(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RatedProduct> {
        private final Rating.Parser mRatingParser;
        private final SimpleParsers.StringParser mbasicStringParser;
        private final ListParser<Rating> mratingsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingParser = new Rating.Parser(objectMapper);
            this.mratingsListParser = ListParser.newParser(new Rating.Parser(objectMapper));
            this.mbasicStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RatedProduct parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RatedProduct:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RatedProduct parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RatedProduct:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RatedProduct parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RatedProduct");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 3003607:
                            if (next.equals("asin")) {
                                b = 2;
                            }
                            break;
                        case 92676538:
                            if (next.equals("adult")) {
                                b = 4;
                            }
                            break;
                        case 110371416:
                            if (next.equals("title")) {
                                b = 1;
                            }
                            break;
                        case 983597686:
                            if (next.equals("ratings")) {
                                b = 3;
                            }
                            break;
                        case 1637553941:
                            if (next.equals("simplifiedRating")) {
                                b = 0;
                            }
                            break;
                    }
                    ImmutableList<Rating> immutableList = null;
                    Rating rating = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            rating = this.mRatingParser.parse(jsonNode2);
                        }
                        builder.simplifiedRating = rating;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.title = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.asin = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mratingsListParser.parse(jsonNode2);
                        }
                        builder.ratings = immutableList;
                    } else if (b == 4) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field adult can't be null");
                        }
                        builder.adult = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RatedProduct so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.adult), this, "adult");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RatedProduct parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 3003607:
                                    if (currentName.equals("asin")) {
                                        b = 2;
                                    }
                                    break;
                                case 92676538:
                                    if (currentName.equals("adult")) {
                                        b = 4;
                                    }
                                    break;
                                case 110371416:
                                    if (currentName.equals("title")) {
                                        b = 1;
                                    }
                                    break;
                                case 983597686:
                                    if (currentName.equals("ratings")) {
                                        b = 3;
                                    }
                                    break;
                                case 1637553941:
                                    if (currentName.equals("simplifiedRating")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            ImmutableList<Rating> immutableList = null;
                            Rating rating = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rating = this.mRatingParser.parse(jsonParser);
                                }
                                builder.simplifiedRating = rating;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.title = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.asin = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mratingsListParser.parse(jsonParser);
                                }
                                builder.ratings = immutableList;
                            } else if (b == 4) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field adult can't be null");
                                }
                                builder.adult = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RatedProduct so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.adult), this, "adult");
                    return builder.build();
                }
            }
        }
    }
}
