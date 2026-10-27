package com.amazon.atv.parentalcontrols;

import com.amazon.atv.catalog.ratings.Rating;
import com.amazon.atv.parentalcontrols.LinearRestriction;
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
public class DefaultRestriction {
    public final Optional<Rating> rating;
    public final Optional<LinearRestriction> restriction;

    private DefaultRestriction(Builder builder) {
        this.restriction = Optional.fromNullable(builder.restriction);
        this.rating = Optional.fromNullable(builder.rating);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultRestriction)) {
            return false;
        }
        DefaultRestriction defaultRestriction = (DefaultRestriction) obj;
        return Objects.equal(this.restriction, defaultRestriction.restriction) && Objects.equal(this.rating, defaultRestriction.rating);
    }

    public int hashCode() {
        return Objects.hashCode(this.restriction, this.rating);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("restriction", this.restriction).add("rating", this.rating).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Rating rating;
        public LinearRestriction restriction;

        public DefaultRestriction build() {
            return new DefaultRestriction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DefaultRestriction> {
        private final LinearRestriction.Parser mLinearRestrictionParser;
        private final Rating.Parser mRatingParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingParser = new Rating.Parser(objectMapper);
            this.mLinearRestrictionParser = new LinearRestriction.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DefaultRestriction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DefaultRestriction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DefaultRestriction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DefaultRestriction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DefaultRestriction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DefaultRestriction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1561062452) {
                        if (iHashCode == -938102371 && next.equals("rating")) {
                            b = 1;
                        }
                    } else if (next.equals("restriction")) {
                        b = 0;
                    }
                    Rating rating = null;
                    LinearRestriction linearRestriction = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            linearRestriction = this.mLinearRestrictionParser.parse(jsonNode2);
                        }
                        builder.restriction = linearRestriction;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            rating = this.mRatingParser.parse(jsonNode2);
                        }
                        builder.rating = rating;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DefaultRestriction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DefaultRestriction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1561062452) {
                                if (iHashCode == -938102371 && currentName.equals("rating")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("restriction")) {
                                b = 0;
                            }
                            Rating rating = null;
                            LinearRestriction linearRestriction = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    linearRestriction = this.mLinearRestrictionParser.parse(jsonParser);
                                }
                                builder.restriction = linearRestriction;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rating = this.mRatingParser.parse(jsonParser);
                                }
                                builder.rating = rating;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DefaultRestriction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
