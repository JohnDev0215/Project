package com.amazon.atv.title.v1.fragment.playback.v2;

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
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSummary {
    public final Optional<String> displayString;
    public final Optional<Boolean> isPlayable;

    private PlaybackSummary(Builder builder) {
        this.displayString = Optional.fromNullable(builder.displayString);
        this.isPlayable = Optional.fromNullable(builder.isPlayable);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackSummary)) {
            return false;
        }
        PlaybackSummary playbackSummary = (PlaybackSummary) obj;
        return Objects.equal(this.displayString, playbackSummary.displayString) && Objects.equal(this.isPlayable, playbackSummary.isPlayable);
    }

    public int hashCode() {
        return Objects.hashCode(this.displayString, this.isPlayable);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("displayString", this.displayString).add("isPlayable", this.isPlayable).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String displayString;
        public Boolean isPlayable;

        public PlaybackSummary build() {
            return new PlaybackSummary(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackSummary> {
        private final SimpleParsers.BooleanParser mNullableBooleanParser;
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNullableBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mbasicStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackSummary parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackSummary:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackSummary parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackSummary:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlaybackSummary parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackSummary");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1809429645) {
                        if (iHashCode == 1085869528 && next.equals("isPlayable")) {
                            b = 1;
                        }
                    } else if (next.equals("displayString")) {
                        b = 0;
                    }
                    Boolean bool = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.displayString = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mNullableBooleanParser.parse(jsonNode2);
                        }
                        builder.isPlayable = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackSummary so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PlaybackSummary parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1809429645) {
                                if (iHashCode == 1085869528 && currentName.equals("isPlayable")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("displayString")) {
                                b = 0;
                            }
                            Boolean bool = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.displayString = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mNullableBooleanParser.parse(jsonParser);
                                }
                                builder.isPlayable = bool;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackSummary so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
