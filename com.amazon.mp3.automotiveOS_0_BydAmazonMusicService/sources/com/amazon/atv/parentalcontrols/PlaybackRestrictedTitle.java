package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.PinChallengeDetails;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackRestrictedTitle {
    public final Optional<PinChallengeDetails> fireTVPlaybackChallengeDetails;
    public final String identifier;
    public final Optional<PinChallengeDetails> playbackChallengeDetails;

    private PlaybackRestrictedTitle(Builder builder) {
        this.fireTVPlaybackChallengeDetails = Optional.fromNullable(builder.fireTVPlaybackChallengeDetails);
        this.identifier = (String) Preconditions.checkNotNull(builder.identifier, "Unexpected null field: identifier");
        this.playbackChallengeDetails = Optional.fromNullable(builder.playbackChallengeDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackRestrictedTitle)) {
            return false;
        }
        PlaybackRestrictedTitle playbackRestrictedTitle = (PlaybackRestrictedTitle) obj;
        return Objects.equal(this.fireTVPlaybackChallengeDetails, playbackRestrictedTitle.fireTVPlaybackChallengeDetails) && Objects.equal(this.identifier, playbackRestrictedTitle.identifier) && Objects.equal(this.playbackChallengeDetails, playbackRestrictedTitle.playbackChallengeDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.fireTVPlaybackChallengeDetails, this.identifier, this.playbackChallengeDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("fireTVPlaybackChallengeDetails", this.fireTVPlaybackChallengeDetails).add("identifier", this.identifier).add("playbackChallengeDetails", this.playbackChallengeDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PinChallengeDetails fireTVPlaybackChallengeDetails;
        public String identifier;
        public PinChallengeDetails playbackChallengeDetails;

        public PlaybackRestrictedTitle build() {
            return new PlaybackRestrictedTitle(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackRestrictedTitle> {
        private final PinChallengeDetails.Parser mPinChallengeDetailsParser;
        private final SimpleParsers.StringParser midentifierParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPinChallengeDetailsParser = new PinChallengeDetails.Parser(objectMapper);
            this.midentifierParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackRestrictedTitle parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackRestrictedTitle:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackRestrictedTitle parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackRestrictedTitle:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlaybackRestrictedTitle parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackRestrictedTitle");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1618432855) {
                        if (iHashCode != -431199078) {
                            if (iHashCode == 2051388402 && next.equals("fireTVPlaybackChallengeDetails")) {
                                b = 0;
                            }
                        } else if (next.equals("playbackChallengeDetails")) {
                            b = 2;
                        }
                    } else if (next.equals("identifier")) {
                        b = 1;
                    }
                    PinChallengeDetails pinChallengeDetails = null;
                    PinChallengeDetails pinChallengeDetails2 = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails2 = this.mPinChallengeDetailsParser.parse(jsonNode2);
                        }
                        builder.fireTVPlaybackChallengeDetails = pinChallengeDetails2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.midentifierParser.parse(jsonNode2);
                        }
                        builder.identifier = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonNode2);
                        }
                        builder.playbackChallengeDetails = pinChallengeDetails;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackRestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
            return builder.build();
        }

        @Nonnull
        private PlaybackRestrictedTitle parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                if (iHashCode != -431199078) {
                                    if (iHashCode == 2051388402 && currentName.equals("fireTVPlaybackChallengeDetails")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("playbackChallengeDetails")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("identifier")) {
                                b = 1;
                            }
                            PinChallengeDetails pinChallengeDetails = null;
                            PinChallengeDetails pinChallengeDetails2 = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails2 = this.mPinChallengeDetailsParser.parse(jsonParser);
                                }
                                builder.fireTVPlaybackChallengeDetails = pinChallengeDetails2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.midentifierParser.parse(jsonParser);
                                }
                                builder.identifier = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonParser);
                                }
                                builder.playbackChallengeDetails = pinChallengeDetails;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackRestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
                    return builder.build();
                }
            }
        }
    }
}
