package com.amazon.atv.parentalcontrols;

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
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ArtworkObfuscation {
    public final boolean applyLock;
    public final Optional<Rating> certificateBadgeRating;
    public final boolean hideArtwork;
    public final Optional<ImmutableList<String>> tags;
    public final Optional<String> url;

    private ArtworkObfuscation(Builder builder) {
        this.certificateBadgeRating = Optional.fromNullable(builder.certificateBadgeRating);
        this.hideArtwork = builder.hideArtwork;
        this.applyLock = builder.applyLock;
        this.tags = Optional.fromNullable(builder.tags);
        this.url = Optional.fromNullable(builder.url);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArtworkObfuscation)) {
            return false;
        }
        ArtworkObfuscation artworkObfuscation = (ArtworkObfuscation) obj;
        return Objects.equal(this.certificateBadgeRating, artworkObfuscation.certificateBadgeRating) && Objects.equal(Boolean.valueOf(this.hideArtwork), Boolean.valueOf(artworkObfuscation.hideArtwork)) && Objects.equal(Boolean.valueOf(this.applyLock), Boolean.valueOf(artworkObfuscation.applyLock)) && Objects.equal(this.tags, artworkObfuscation.tags) && Objects.equal(this.url, artworkObfuscation.url);
    }

    public int hashCode() {
        return Objects.hashCode(this.certificateBadgeRating, Boolean.valueOf(this.hideArtwork), Boolean.valueOf(this.applyLock), this.tags, this.url);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("certificateBadgeRating", this.certificateBadgeRating).add("hideArtwork", this.hideArtwork).add("applyLock", this.applyLock).add("tags", this.tags).add(ImagesContract.URL, this.url).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public boolean applyLock;
        public Rating certificateBadgeRating;
        public boolean hideArtwork;
        public ImmutableList<String> tags;
        public String url;

        public ArtworkObfuscation build() {
            return new ArtworkObfuscation(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ArtworkObfuscation> {
        private final Rating.Parser mRatingParser;
        private final ListParser<String> mStringListParser;
        private final SimpleParsers.StringParser murlParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingParser = new Rating.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringListParser = ListParser.newParser(stringParser);
            this.murlParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ArtworkObfuscation parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ArtworkObfuscation:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ArtworkObfuscation parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ArtworkObfuscation:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ArtworkObfuscation parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ArtworkObfuscation");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2075896391:
                            if (next.equals("applyLock")) {
                                b = 2;
                            }
                            break;
                        case -852360110:
                            if (next.equals("hideArtwork")) {
                                b = 1;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 4;
                            }
                            break;
                        case 3552281:
                            if (next.equals("tags")) {
                                b = 3;
                            }
                            break;
                        case 678757641:
                            if (next.equals("certificateBadgeRating")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    Rating rating = null;
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            rating = this.mRatingParser.parse(jsonNode2);
                        }
                        builder.certificateBadgeRating = rating;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field hideArtwork can't be null");
                        }
                        builder.hideArtwork = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field applyLock can't be null");
                        }
                        builder.applyLock = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.tags = immutableList;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.murlParser.parse(jsonNode2);
                        }
                        builder.url = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ArtworkObfuscation so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ArtworkObfuscation parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2075896391:
                                    if (currentName.equals("applyLock")) {
                                        b = 2;
                                    }
                                    break;
                                case -852360110:
                                    if (currentName.equals("hideArtwork")) {
                                        b = 1;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 4;
                                    }
                                    break;
                                case 3552281:
                                    if (currentName.equals("tags")) {
                                        b = 3;
                                    }
                                    break;
                                case 678757641:
                                    if (currentName.equals("certificateBadgeRating")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            Rating rating = null;
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rating = this.mRatingParser.parse(jsonParser);
                                }
                                builder.certificateBadgeRating = rating;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field hideArtwork can't be null");
                                }
                                builder.hideArtwork = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field applyLock can't be null");
                                }
                                builder.applyLock = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.tags = immutableList;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.murlParser.parse(jsonParser);
                                }
                                builder.url = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ArtworkObfuscation so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
