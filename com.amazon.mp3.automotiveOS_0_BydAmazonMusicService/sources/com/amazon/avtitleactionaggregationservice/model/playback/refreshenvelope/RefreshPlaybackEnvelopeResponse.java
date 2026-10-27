package com.amazon.avtitleactionaggregationservice.model.playback.refreshenvelope;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.PlaybackExperience;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.refreshenvelope.RefreshPlaybackEnvelopeUnavailableReason;
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
public class RefreshPlaybackEnvelopeResponse {
    public final Optional<PlaybackExperience> playbackExperience;
    public final Optional<RefreshPlaybackEnvelopeUnavailableReason> refreshPlaybackEnvelopeUnavailableReason;

    private RefreshPlaybackEnvelopeResponse(Builder builder) {
        this.playbackExperience = Optional.fromNullable(builder.playbackExperience);
        this.refreshPlaybackEnvelopeUnavailableReason = Optional.fromNullable(builder.refreshPlaybackEnvelopeUnavailableReason);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RefreshPlaybackEnvelopeResponse)) {
            return false;
        }
        RefreshPlaybackEnvelopeResponse refreshPlaybackEnvelopeResponse = (RefreshPlaybackEnvelopeResponse) obj;
        return Objects.equal(this.playbackExperience, refreshPlaybackEnvelopeResponse.playbackExperience) && Objects.equal(this.refreshPlaybackEnvelopeUnavailableReason, refreshPlaybackEnvelopeResponse.refreshPlaybackEnvelopeUnavailableReason);
    }

    public int hashCode() {
        return Objects.hashCode(this.playbackExperience, this.refreshPlaybackEnvelopeUnavailableReason);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playbackExperience", this.playbackExperience).add("refreshPlaybackEnvelopeUnavailableReason", this.refreshPlaybackEnvelopeUnavailableReason).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PlaybackExperience playbackExperience;
        public RefreshPlaybackEnvelopeUnavailableReason refreshPlaybackEnvelopeUnavailableReason;

        public RefreshPlaybackEnvelopeResponse build() {
            return new RefreshPlaybackEnvelopeResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RefreshPlaybackEnvelopeResponse> {
        private final PlaybackExperience.Parser mPlaybackExperienceParser;
        private final EnumParser<RefreshPlaybackEnvelopeUnavailableReason> mRefreshPlaybackEnvelopeUnavailableReasonParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPlaybackExperienceParser = new PlaybackExperience.Parser(objectMapper);
            this.mRefreshPlaybackEnvelopeUnavailableReasonParser = EnumParser.newParser(RefreshPlaybackEnvelopeUnavailableReason.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RefreshPlaybackEnvelopeResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RefreshPlaybackEnvelopeResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RefreshPlaybackEnvelopeResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RefreshPlaybackEnvelopeResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RefreshPlaybackEnvelopeResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RefreshPlaybackEnvelopeResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -852187314) {
                        if (iHashCode == 1980065253 && next.equals("playbackExperience")) {
                            b = 0;
                        }
                    } else if (next.equals("refreshPlaybackEnvelopeUnavailableReason")) {
                        b = 1;
                    }
                    RefreshPlaybackEnvelopeUnavailableReason refreshPlaybackEnvelopeUnavailableReason = null;
                    PlaybackExperience playbackExperience = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            playbackExperience = this.mPlaybackExperienceParser.parse(jsonNode2);
                        }
                        builder.playbackExperience = playbackExperience;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            refreshPlaybackEnvelopeUnavailableReason = (RefreshPlaybackEnvelopeUnavailableReason) this.mRefreshPlaybackEnvelopeUnavailableReasonParser.parse(jsonNode2);
                        }
                        builder.refreshPlaybackEnvelopeUnavailableReason = refreshPlaybackEnvelopeUnavailableReason;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RefreshPlaybackEnvelopeResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private RefreshPlaybackEnvelopeResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -852187314) {
                                if (iHashCode == 1980065253 && currentName.equals("playbackExperience")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("refreshPlaybackEnvelopeUnavailableReason")) {
                                b = 1;
                            }
                            RefreshPlaybackEnvelopeUnavailableReason refreshPlaybackEnvelopeUnavailableReason = null;
                            PlaybackExperience playbackExperience = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playbackExperience = this.mPlaybackExperienceParser.parse(jsonParser);
                                }
                                builder.playbackExperience = playbackExperience;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    refreshPlaybackEnvelopeUnavailableReason = (RefreshPlaybackEnvelopeUnavailableReason) this.mRefreshPlaybackEnvelopeUnavailableReasonParser.parse(jsonParser);
                                }
                                builder.refreshPlaybackEnvelopeUnavailableReason = refreshPlaybackEnvelopeUnavailableReason;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RefreshPlaybackEnvelopeResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
