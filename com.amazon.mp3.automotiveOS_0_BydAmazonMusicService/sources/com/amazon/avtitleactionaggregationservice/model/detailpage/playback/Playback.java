package com.amazon.avtitleactionaggregationservice.model.detailpage.playback;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.Bookmark;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.Eligibility;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.EntitlementDetails;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.PlaybackExperience;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.PlaybackMetadata;
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
public class Playback {
    public final Optional<Bookmark> bookmarkDetails;
    public final Optional<Eligibility> eligibility;
    public final Optional<EntitlementDetails> entitlementDetails;
    public final Optional<PlaybackExperience> playbackExperience;
    public final Optional<PlaybackMetadata> playbackMetadata;

    private Playback(Builder builder) {
        this.eligibility = Optional.fromNullable(builder.eligibility);
        this.entitlementDetails = Optional.fromNullable(builder.entitlementDetails);
        this.playbackExperience = Optional.fromNullable(builder.playbackExperience);
        this.playbackMetadata = Optional.fromNullable(builder.playbackMetadata);
        this.bookmarkDetails = Optional.fromNullable(builder.bookmarkDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Playback)) {
            return false;
        }
        Playback playback = (Playback) obj;
        return Objects.equal(this.eligibility, playback.eligibility) && Objects.equal(this.entitlementDetails, playback.entitlementDetails) && Objects.equal(this.playbackExperience, playback.playbackExperience) && Objects.equal(this.playbackMetadata, playback.playbackMetadata) && Objects.equal(this.bookmarkDetails, playback.bookmarkDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.eligibility, this.entitlementDetails, this.playbackExperience, this.playbackMetadata, this.bookmarkDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("eligibility", this.eligibility).add("entitlementDetails", this.entitlementDetails).add("playbackExperience", this.playbackExperience).add("playbackMetadata", this.playbackMetadata).add("bookmarkDetails", this.bookmarkDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Bookmark bookmarkDetails;
        public Eligibility eligibility;
        public EntitlementDetails entitlementDetails;
        public PlaybackExperience playbackExperience;
        public PlaybackMetadata playbackMetadata;

        public Playback build() {
            return new Playback(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Playback> {
        private final Bookmark.Parser mBookmarkParser;
        private final Eligibility.Parser mEligibilityParser;
        private final EntitlementDetails.Parser mEntitlementDetailsParser;
        private final PlaybackExperience.Parser mPlaybackExperienceParser;
        private final PlaybackMetadata.Parser mPlaybackMetadataParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPlaybackExperienceParser = new PlaybackExperience.Parser(objectMapper);
            this.mEntitlementDetailsParser = new EntitlementDetails.Parser(objectMapper);
            this.mBookmarkParser = new Bookmark.Parser(objectMapper);
            this.mEligibilityParser = new Eligibility.Parser(objectMapper);
            this.mPlaybackMetadataParser = new PlaybackMetadata.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Playback parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Playback:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Playback parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Playback:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Playback parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Playback");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1772461995:
                            if (next.equals("entitlementDetails")) {
                                b = 1;
                            }
                            break;
                        case -1609864598:
                            if (next.equals("playbackMetadata")) {
                                b = 3;
                            }
                            break;
                        case -930847859:
                            if (next.equals("eligibility")) {
                                b = 0;
                            }
                            break;
                        case 504740908:
                            if (next.equals("bookmarkDetails")) {
                                b = 4;
                            }
                            break;
                        case 1980065253:
                            if (next.equals("playbackExperience")) {
                                b = 2;
                            }
                            break;
                    }
                    Bookmark bookmark = null;
                    Eligibility eligibility = null;
                    EntitlementDetails entitlementDetails = null;
                    PlaybackExperience playbackExperience = null;
                    PlaybackMetadata playbackMetadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            eligibility = this.mEligibilityParser.parse(jsonNode2);
                        }
                        builder.eligibility = eligibility;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            entitlementDetails = this.mEntitlementDetailsParser.parse(jsonNode2);
                        }
                        builder.entitlementDetails = entitlementDetails;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            playbackExperience = this.mPlaybackExperienceParser.parse(jsonNode2);
                        }
                        builder.playbackExperience = playbackExperience;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            playbackMetadata = this.mPlaybackMetadataParser.parse(jsonNode2);
                        }
                        builder.playbackMetadata = playbackMetadata;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            bookmark = this.mBookmarkParser.parse(jsonNode2);
                        }
                        builder.bookmarkDetails = bookmark;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Playback so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Playback parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1772461995:
                                    if (currentName.equals("entitlementDetails")) {
                                        b = 1;
                                    }
                                    break;
                                case -1609864598:
                                    if (currentName.equals("playbackMetadata")) {
                                        b = 3;
                                    }
                                    break;
                                case -930847859:
                                    if (currentName.equals("eligibility")) {
                                        b = 0;
                                    }
                                    break;
                                case 504740908:
                                    if (currentName.equals("bookmarkDetails")) {
                                        b = 4;
                                    }
                                    break;
                                case 1980065253:
                                    if (currentName.equals("playbackExperience")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            Bookmark bookmark = null;
                            Eligibility eligibility = null;
                            EntitlementDetails entitlementDetails = null;
                            PlaybackExperience playbackExperience = null;
                            PlaybackMetadata playbackMetadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    eligibility = this.mEligibilityParser.parse(jsonParser);
                                }
                                builder.eligibility = eligibility;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entitlementDetails = this.mEntitlementDetailsParser.parse(jsonParser);
                                }
                                builder.entitlementDetails = entitlementDetails;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playbackExperience = this.mPlaybackExperienceParser.parse(jsonParser);
                                }
                                builder.playbackExperience = playbackExperience;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playbackMetadata = this.mPlaybackMetadataParser.parse(jsonParser);
                                }
                                builder.playbackMetadata = playbackMetadata;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bookmark = this.mBookmarkParser.parse(jsonParser);
                                }
                                builder.bookmarkDetails = bookmark;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Playback so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
