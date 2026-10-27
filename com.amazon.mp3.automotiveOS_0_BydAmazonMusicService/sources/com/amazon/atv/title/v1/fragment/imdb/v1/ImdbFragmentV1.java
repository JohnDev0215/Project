package com.amazon.atv.title.v1.fragment.imdb.v1;

import com.amazon.atv.title.v1.ImdbFragment;
import com.amazon.atv.title.v1.fragment.imdb.v1.Rating;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
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
public class ImdbFragmentV1 extends ImdbFragment {
    public final Optional<Rating> rating;

    private ImdbFragmentV1(Builder builder) {
        super(builder);
        this.rating = Optional.fromNullable(builder.rating);
    }

    @Override // com.amazon.atv.title.v1.ImdbFragment, com.amazon.atv.title.v1.Fragment
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImdbFragmentV1) {
            return super.equals(obj) && Objects.equal(this.rating, ((ImdbFragmentV1) obj).rating);
        }
        return false;
    }

    @Override // com.amazon.atv.title.v1.ImdbFragment, com.amazon.atv.title.v1.Fragment
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.rating);
    }

    @Override // com.amazon.atv.title.v1.ImdbFragment, com.amazon.atv.title.v1.Fragment
    public String toString() {
        return MoreObjects.toStringHelper(this).add("rating", this.rating).toString();
    }

    @NotThreadSafe
    public static class Builder extends ImdbFragment.Builder {
        public Rating rating;

        public ImdbFragmentV1 build() {
            return new ImdbFragmentV1(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ImdbFragmentV1> {
        private final SimpleParsers.StringParser mFragmentVersionParser;
        private final Rating.Parser mRatingParser;
        private final SimpleParsers.StringParser mValidUntilTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFragmentVersionParser = stringParser;
            this.mValidUntilTimestampParser = stringParser;
            this.mRatingParser = new Rating.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ImdbFragmentV1 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImdbFragmentV1:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ImdbFragmentV1 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImdbFragmentV1:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ImdbFragmentV1 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ImdbFragmentV1");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -938102371) {
                        if (iHashCode != -54813502) {
                            if (iHashCode == 351608024 && next.equals("version")) {
                                b = 0;
                            }
                        } else if (next.equals("validUntil")) {
                            b = 1;
                        }
                    } else if (next.equals("rating")) {
                        b = 2;
                    }
                    Rating rating = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mValidUntilTimestampParser.parse(jsonNode2);
                        }
                        builder.validUntil = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            rating = this.mRatingParser.parse(jsonNode2);
                        }
                        builder.rating = rating;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ImdbFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        @Nonnull
        private ImdbFragmentV1 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -938102371) {
                                if (iHashCode != -54813502) {
                                    if (iHashCode == 351608024 && currentName.equals("version")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("validUntil")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("rating")) {
                                b = 2;
                            }
                            Rating rating = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mFragmentVersionParser.parse(jsonParser);
                                }
                                builder.version = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mValidUntilTimestampParser.parse(jsonParser);
                                }
                                builder.validUntil = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rating = this.mRatingParser.parse(jsonParser);
                                }
                                builder.rating = rating;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ImdbFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
