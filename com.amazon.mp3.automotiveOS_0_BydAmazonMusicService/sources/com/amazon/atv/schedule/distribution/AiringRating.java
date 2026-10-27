package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.Rating;
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
public class AiringRating {
    public final Optional<String> airingId;
    public final Optional<ImmutableList<Rating>> ratings;

    private AiringRating(Builder builder) {
        this.ratings = Optional.fromNullable(builder.ratings);
        this.airingId = Optional.fromNullable(builder.airingId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AiringRating)) {
            return false;
        }
        AiringRating airingRating = (AiringRating) obj;
        return Objects.equal(this.ratings, airingRating.ratings) && Objects.equal(this.airingId, airingRating.airingId);
    }

    public int hashCode() {
        return Objects.hashCode(this.ratings, this.airingId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("ratings", this.ratings).add("airingId", this.airingId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String airingId;
        public ImmutableList<Rating> ratings;

        public AiringRating build() {
            return new AiringRating(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AiringRating> {
        private final SimpleParsers.StringParser mAiringIdParser;
        private final ListParser<Rating> mRatingListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingListParser = ListParser.newParser(new Rating.Parser(objectMapper));
            this.mAiringIdParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AiringRating parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AiringRating:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AiringRating parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AiringRating:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AiringRating parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AiringRating");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -683412077) {
                        if (iHashCode == 983597686 && next.equals("ratings")) {
                            b = 0;
                        }
                    } else if (next.equals("airingId")) {
                        b = 1;
                    }
                    String str = null;
                    ImmutableList<Rating> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mRatingListParser.parse(jsonNode2);
                        }
                        builder.ratings = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mAiringIdParser.parse(jsonNode2);
                        }
                        builder.airingId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AiringRating so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private AiringRating parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -683412077) {
                                if (iHashCode == 983597686 && currentName.equals("ratings")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("airingId")) {
                                b = 1;
                            }
                            String str = null;
                            ImmutableList<Rating> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mRatingListParser.parse(jsonParser);
                                }
                                builder.ratings = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mAiringIdParser.parse(jsonParser);
                                }
                                builder.airingId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AiringRating so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
