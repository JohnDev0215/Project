package com.amazon.atv.parentalcontrols;

import com.amazon.atv.catalog.ratings.Rating;
import com.amazon.atv.parentalcontrols.LinearRestriction;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RestrictedProgram {
    public final ImmutableList<String> identifiers;
    public final Rating rating;
    public final Optional<LinearRestriction> restriction;
    public final Long startTime;

    private RestrictedProgram(Builder builder) {
        this.rating = (Rating) Preconditions.checkNotNull(builder.rating, "Unexpected null field: rating");
        this.restriction = Optional.fromNullable(builder.restriction);
        this.startTime = (Long) Preconditions.checkNotNull(builder.startTime, "Unexpected null field: startTime");
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RestrictedProgram)) {
            return false;
        }
        RestrictedProgram restrictedProgram = (RestrictedProgram) obj;
        return Objects.equal(this.rating, restrictedProgram.rating) && Objects.equal(this.restriction, restrictedProgram.restriction) && Objects.equal(this.startTime, restrictedProgram.startTime) && Objects.equal(this.identifiers, restrictedProgram.identifiers);
    }

    public int hashCode() {
        return Objects.hashCode(this.rating, this.restriction, this.startTime, this.identifiers);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("rating", this.rating).add("restriction", this.restriction).add("startTime", this.startTime).add("identifiers", this.identifiers).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> identifiers;
        public Rating rating;
        public LinearRestriction restriction;
        public Long startTime;

        public RestrictedProgram build() {
            return new RestrictedProgram(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RestrictedProgram> {
        private final LinearRestriction.Parser mLinearRestrictionParser;
        private final Rating.Parser mRatingParser;
        private final SimpleParsers.LongParser mepochMilliParser;
        private final ListParser<String> midentifierListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingParser = new Rating.Parser(objectMapper);
            this.mLinearRestrictionParser = new LinearRestriction.Parser(objectMapper);
            this.midentifierListParser = ListParser.newParser(SimpleParsers.StringParser.INSTANCE);
            this.mepochMilliParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RestrictedProgram parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RestrictedProgram:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RestrictedProgram parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RestrictedProgram:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RestrictedProgram parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RestrictedProgram");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2129294769:
                            if (next.equals("startTime")) {
                                b = 2;
                            }
                            break;
                        case -1561062452:
                            if (next.equals("restriction")) {
                                b = 1;
                            }
                            break;
                        case -938102371:
                            if (next.equals("rating")) {
                                b = 0;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 3;
                            }
                            break;
                    }
                    ImmutableList<String> immutableList = null;
                    Rating rating = null;
                    LinearRestriction linearRestriction = null;
                    Long l = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            rating = this.mRatingParser.parse(jsonNode2);
                        }
                        builder.rating = rating;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            linearRestriction = this.mLinearRestrictionParser.parse(jsonNode2);
                        }
                        builder.restriction = linearRestriction;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            l = this.mepochMilliParser.parse(jsonNode2);
                        }
                        builder.startTime = l;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.midentifierListParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RestrictedProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.rating, this, "rating");
            JsonParsingUtils.checkNotNull(builder.startTime, this, "startTime");
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RestrictedProgram parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2129294769:
                                    if (currentName.equals("startTime")) {
                                        b = 2;
                                    }
                                    break;
                                case -1561062452:
                                    if (currentName.equals("restriction")) {
                                        b = 1;
                                    }
                                    break;
                                case -938102371:
                                    if (currentName.equals("rating")) {
                                        b = 0;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            ImmutableList<String> immutableList = null;
                            Rating rating = null;
                            LinearRestriction linearRestriction = null;
                            Long l = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rating = this.mRatingParser.parse(jsonParser);
                                }
                                builder.rating = rating;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    linearRestriction = this.mLinearRestrictionParser.parse(jsonParser);
                                }
                                builder.restriction = linearRestriction;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mepochMilliParser.parse(jsonParser);
                                }
                                builder.startTime = l;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.midentifierListParser.parse(jsonParser);
                                }
                                builder.identifiers = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RestrictedProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.rating, this, "rating");
                    JsonParsingUtils.checkNotNull(builder.startTime, this, "startTime");
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    return builder.build();
                }
            }
        }
    }
}
