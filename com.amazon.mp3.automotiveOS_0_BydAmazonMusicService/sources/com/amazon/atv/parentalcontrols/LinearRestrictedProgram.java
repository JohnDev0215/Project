package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.PinChallengeDetails;
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
public class LinearRestrictedProgram {
    public final Optional<PinChallengeDetails> fireTVPlaybackChallengeDetails;
    public final Optional<ImmutableList<String>> identifiers;
    public final Optional<PinChallengeDetails> playbackChallengeDetails;
    public final Optional<Long> startTime;

    private LinearRestrictedProgram(Builder builder) {
        this.playbackChallengeDetails = Optional.fromNullable(builder.playbackChallengeDetails);
        this.startTime = Optional.fromNullable(builder.startTime);
        this.identifiers = Optional.fromNullable(builder.identifiers);
        this.fireTVPlaybackChallengeDetails = Optional.fromNullable(builder.fireTVPlaybackChallengeDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinearRestrictedProgram)) {
            return false;
        }
        LinearRestrictedProgram linearRestrictedProgram = (LinearRestrictedProgram) obj;
        return Objects.equal(this.playbackChallengeDetails, linearRestrictedProgram.playbackChallengeDetails) && Objects.equal(this.startTime, linearRestrictedProgram.startTime) && Objects.equal(this.identifiers, linearRestrictedProgram.identifiers) && Objects.equal(this.fireTVPlaybackChallengeDetails, linearRestrictedProgram.fireTVPlaybackChallengeDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.playbackChallengeDetails, this.startTime, this.identifiers, this.fireTVPlaybackChallengeDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playbackChallengeDetails", this.playbackChallengeDetails).add("startTime", this.startTime).add("identifiers", this.identifiers).add("fireTVPlaybackChallengeDetails", this.fireTVPlaybackChallengeDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PinChallengeDetails fireTVPlaybackChallengeDetails;
        public ImmutableList<String> identifiers;
        public PinChallengeDetails playbackChallengeDetails;
        public Long startTime;

        public LinearRestrictedProgram build() {
            return new LinearRestrictedProgram(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LinearRestrictedProgram> {
        private final PinChallengeDetails.Parser mPinChallengeDetailsParser;
        private final SimpleParsers.LongParser mepochMilliParser;
        private final ListParser<String> midentifierListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPinChallengeDetailsParser = new PinChallengeDetails.Parser(objectMapper);
            this.midentifierListParser = ListParser.newParser(SimpleParsers.StringParser.INSTANCE);
            this.mepochMilliParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LinearRestrictedProgram parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearRestrictedProgram:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LinearRestrictedProgram parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearRestrictedProgram:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearRestrictedProgram parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LinearRestrictedProgram");
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
                                b = 1;
                            }
                            break;
                        case -431199078:
                            if (next.equals("playbackChallengeDetails")) {
                                b = 0;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 2;
                            }
                            break;
                        case 2051388402:
                            if (next.equals("fireTVPlaybackChallengeDetails")) {
                                b = 3;
                            }
                            break;
                    }
                    PinChallengeDetails pinChallengeDetails = null;
                    PinChallengeDetails pinChallengeDetails2 = null;
                    Long l = null;
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails2 = this.mPinChallengeDetailsParser.parse(jsonNode2);
                        }
                        builder.playbackChallengeDetails = pinChallengeDetails2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            l = this.mepochMilliParser.parse(jsonNode2);
                        }
                        builder.startTime = l;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.midentifierListParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonNode2);
                        }
                        builder.fireTVPlaybackChallengeDetails = pinChallengeDetails;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearRestrictedProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearRestrictedProgram parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 1;
                                    }
                                    break;
                                case -431199078:
                                    if (currentName.equals("playbackChallengeDetails")) {
                                        b = 0;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 2;
                                    }
                                    break;
                                case 2051388402:
                                    if (currentName.equals("fireTVPlaybackChallengeDetails")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            PinChallengeDetails pinChallengeDetails = null;
                            PinChallengeDetails pinChallengeDetails2 = null;
                            Long l = null;
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails2 = this.mPinChallengeDetailsParser.parse(jsonParser);
                                }
                                builder.playbackChallengeDetails = pinChallengeDetails2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mepochMilliParser.parse(jsonParser);
                                }
                                builder.startTime = l;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.midentifierListParser.parse(jsonParser);
                                }
                                builder.identifiers = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonParser);
                                }
                                builder.fireTVPlaybackChallengeDetails = pinChallengeDetails;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearRestrictedProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
