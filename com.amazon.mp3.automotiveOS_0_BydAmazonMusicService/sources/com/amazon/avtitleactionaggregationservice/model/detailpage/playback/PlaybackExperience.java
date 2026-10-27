package com.amazon.avtitleactionaggregationservice.model.detailpage.playback;

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
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackExperience {
    public final Optional<String> correlationId;
    public final Optional<Date> expiryTime;
    public final Optional<String> playbackEnvelope;

    private PlaybackExperience(Builder builder) {
        this.correlationId = Optional.fromNullable(builder.correlationId);
        this.expiryTime = Optional.fromNullable(builder.expiryTime);
        this.playbackEnvelope = Optional.fromNullable(builder.playbackEnvelope);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackExperience)) {
            return false;
        }
        PlaybackExperience playbackExperience = (PlaybackExperience) obj;
        return Objects.equal(this.correlationId, playbackExperience.correlationId) && Objects.equal(this.expiryTime, playbackExperience.expiryTime) && Objects.equal(this.playbackEnvelope, playbackExperience.playbackEnvelope);
    }

    public int hashCode() {
        return Objects.hashCode(this.correlationId, this.expiryTime, this.playbackEnvelope);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("correlationId", this.correlationId).add("expiryTime", this.expiryTime).add("playbackEnvelope", this.playbackEnvelope).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String correlationId;
        public Date expiryTime;
        public String playbackEnvelope;

        public PlaybackExperience build() {
            return new PlaybackExperience(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackExperience> {
        private final SimpleParsers.DateParser mDateTimeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackExperience parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackExperience:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackExperience parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackExperience:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlaybackExperience parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackExperience");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -816254304) {
                        if (iHashCode != -764983747) {
                            if (iHashCode == 1086324491 && next.equals("playbackEnvelope")) {
                                b = 2;
                            }
                        } else if (next.equals("correlationId")) {
                            b = 0;
                        }
                    } else if (next.equals("expiryTime")) {
                        b = 1;
                    }
                    String str = null;
                    String str2 = null;
                    Date date = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.correlationId = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.expiryTime = date;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playbackEnvelope = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackExperience so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PlaybackExperience parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -816254304) {
                                if (iHashCode != -764983747) {
                                    if (iHashCode == 1086324491 && currentName.equals("playbackEnvelope")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("correlationId")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("expiryTime")) {
                                b = 1;
                            }
                            String str = null;
                            String str2 = null;
                            Date date = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.correlationId = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.expiryTime = date;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.playbackEnvelope = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackExperience so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
