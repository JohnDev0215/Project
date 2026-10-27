package com.amazon.atv.parentalcontrols.legacysupport;

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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class LegacyClientParentalControlsSettings {
    public final ImmutableList<Rating> allowedRatings;
    public final boolean ratingsRestricted;

    private LegacyClientParentalControlsSettings(Builder builder) {
        this.allowedRatings = (ImmutableList) Preconditions.checkNotNull(builder.allowedRatings, "Unexpected null field: allowedRatings");
        this.ratingsRestricted = builder.ratingsRestricted;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LegacyClientParentalControlsSettings)) {
            return false;
        }
        LegacyClientParentalControlsSettings legacyClientParentalControlsSettings = (LegacyClientParentalControlsSettings) obj;
        return Objects.equal(this.allowedRatings, legacyClientParentalControlsSettings.allowedRatings) && Objects.equal(Boolean.valueOf(this.ratingsRestricted), Boolean.valueOf(legacyClientParentalControlsSettings.ratingsRestricted));
    }

    public int hashCode() {
        return Objects.hashCode(this.allowedRatings, Boolean.valueOf(this.ratingsRestricted));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("allowedRatings", this.allowedRatings).add("ratingsRestricted", this.ratingsRestricted).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<Rating> allowedRatings;
        public boolean ratingsRestricted;

        public LegacyClientParentalControlsSettings build() {
            return new LegacyClientParentalControlsSettings(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LegacyClientParentalControlsSettings> {
        private final ListParser<Rating> mratingsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mratingsListParser = ListParser.newParser(new Rating.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LegacyClientParentalControlsSettings parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LegacyClientParentalControlsSettings:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LegacyClientParentalControlsSettings parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LegacyClientParentalControlsSettings:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LegacyClientParentalControlsSettings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LegacyClientParentalControlsSettings");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 1096641454) {
                        if (iHashCode == 1615809393 && next.equals("ratingsRestricted")) {
                            b = 1;
                        }
                    } else if (next.equals("allowedRatings")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.allowedRatings = jsonNode2.isNull() ? null : this.mratingsListParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field ratingsRestricted can't be null");
                        }
                        builder.ratingsRestricted = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LegacyClientParentalControlsSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.allowedRatings, this, "allowedRatings");
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.ratingsRestricted), this, "ratingsRestricted");
            return builder.build();
        }

        @Nonnull
        private LegacyClientParentalControlsSettings parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 1096641454) {
                                if (iHashCode == 1615809393 && currentName.equals("ratingsRestricted")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("allowedRatings")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.allowedRatings = currentToken == JsonToken.VALUE_NULL ? null : this.mratingsListParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field ratingsRestricted can't be null");
                                }
                                builder.ratingsRestricted = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LegacyClientParentalControlsSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.allowedRatings, this, "allowedRatings");
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.ratingsRestricted), this, "ratingsRestricted");
                    return builder.build();
                }
            }
        }
    }
}
