package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.PinChallengeDetails;
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
public class DefaultLinearRestriction {
    public final Optional<PinChallengeDetails> fireTVPlaybackChallengeDetails;
    public final Optional<PinChallengeDetails> playbackChallengeDetails;

    private DefaultLinearRestriction(Builder builder) {
        this.fireTVPlaybackChallengeDetails = Optional.fromNullable(builder.fireTVPlaybackChallengeDetails);
        this.playbackChallengeDetails = Optional.fromNullable(builder.playbackChallengeDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultLinearRestriction)) {
            return false;
        }
        DefaultLinearRestriction defaultLinearRestriction = (DefaultLinearRestriction) obj;
        return Objects.equal(this.fireTVPlaybackChallengeDetails, defaultLinearRestriction.fireTVPlaybackChallengeDetails) && Objects.equal(this.playbackChallengeDetails, defaultLinearRestriction.playbackChallengeDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.fireTVPlaybackChallengeDetails, this.playbackChallengeDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("fireTVPlaybackChallengeDetails", this.fireTVPlaybackChallengeDetails).add("playbackChallengeDetails", this.playbackChallengeDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PinChallengeDetails fireTVPlaybackChallengeDetails;
        public PinChallengeDetails playbackChallengeDetails;

        public DefaultLinearRestriction build() {
            return new DefaultLinearRestriction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DefaultLinearRestriction> {
        private final PinChallengeDetails.Parser mPinChallengeDetailsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPinChallengeDetailsParser = new PinChallengeDetails.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DefaultLinearRestriction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DefaultLinearRestriction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DefaultLinearRestriction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DefaultLinearRestriction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DefaultLinearRestriction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DefaultLinearRestriction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -431199078) {
                        if (iHashCode == 2051388402 && next.equals("fireTVPlaybackChallengeDetails")) {
                            b = 0;
                        }
                    } else if (next.equals("playbackChallengeDetails")) {
                        b = 1;
                    }
                    PinChallengeDetails pinChallengeDetails = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonNode2);
                        }
                        builder.fireTVPlaybackChallengeDetails = pinChallengeDetails;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonNode2);
                        }
                        builder.playbackChallengeDetails = pinChallengeDetails;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DefaultLinearRestriction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DefaultLinearRestriction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -431199078) {
                                if (iHashCode == 2051388402 && currentName.equals("fireTVPlaybackChallengeDetails")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("playbackChallengeDetails")) {
                                b = 1;
                            }
                            PinChallengeDetails pinChallengeDetails = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonParser);
                                }
                                builder.fireTVPlaybackChallengeDetails = pinChallengeDetails;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonParser);
                                }
                                builder.playbackChallengeDetails = pinChallengeDetails;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DefaultLinearRestriction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
