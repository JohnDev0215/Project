package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.playbackresourcev2.Prsv2Error;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.joda.time.Period;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackData {
    private final Optional<String> mContentId;
    private final Optional<Prsv2Error> mError;
    private final Optional<Period> mMaxLiveLookback;

    PlaybackData(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mContentId = Optional.fromNullable(builder.mContentId);
        this.mMaxLiveLookback = Optional.fromNullable(builder.mMaxLiveLookback);
        this.mError = Optional.fromNullable(builder.mError);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("contentId", this.mContentId).add("maxLiveLookback", this.mMaxLiveLookback).toString();
    }

    static class Builder {
        private String mContentId;
        private Prsv2Error mError;
        private Period mMaxLiveLookback;

        Builder() {
        }

        PlaybackData build() {
            return new PlaybackData(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<PlaybackData> {
        private static final String CONTENT_ID_FIELD_NAME = "contentId";
        private static final String ERROR_FIELD_NAME = "error";
        private static final String MAX_LIVE_LOOKBACK_FIELD_NAME = "maxLiveLookback";
        private static final String RESULT_FIELD_NAME = "result";
        private final Prsv2Error.Parser mErrorParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, PlaybackData.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mErrorParser = new Prsv2Error.Parser(objectMapper);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public PlaybackData parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "playbackData");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1569861482) {
                        if (iHashCode != -407108748) {
                            if (iHashCode == 96784904 && next.equals("error")) {
                                b = 2;
                            }
                        } else if (next.equals(CONTENT_ID_FIELD_NAME)) {
                            b = 0;
                        }
                    } else if (next.equals(MAX_LIVE_LOOKBACK_FIELD_NAME)) {
                        b = 1;
                    }
                    Prsv2Error prsv2Error = null;
                    String str = null;
                    Period period = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mContentId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            period = Period.parse(this.mStringParser.parse(jsonNode2));
                        }
                        builder.mMaxLiveLookback = period;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            prsv2Error = this.mErrorParser.parse(jsonNode2);
                        }
                        builder.mError = prsv2Error;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public PlaybackData parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            return parseInternal(jsonParser, new Builder());
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private PlaybackData parseInternal(@Nonnull JsonParser jsonParser, @Nonnull Builder builder) throws IOException {
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    JsonToken currentToken = jsonParser.getCurrentToken();
                    byte b = -1;
                    try {
                        switch (currentName.hashCode()) {
                            case -1569861482:
                                if (currentName.equals(MAX_LIVE_LOOKBACK_FIELD_NAME)) {
                                    b = 2;
                                }
                                break;
                            case -934426595:
                                if (currentName.equals(RESULT_FIELD_NAME)) {
                                    b = 0;
                                }
                                break;
                            case -407108748:
                                if (currentName.equals(CONTENT_ID_FIELD_NAME)) {
                                    b = 1;
                                }
                                break;
                            case 96784904:
                                if (currentName.equals("error")) {
                                    b = 3;
                                }
                                break;
                        }
                        if (b != 0) {
                            String str = null;
                            Prsv2Error prsv2Error = null;
                            Period period = null;
                            if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.mContentId = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    period = Period.parse(this.mStringParser.parse(jsonParser));
                                }
                                builder.mMaxLiveLookback = period;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    prsv2Error = this.mErrorParser.parse(jsonParser);
                                }
                                builder.mError = prsv2Error;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } else {
                            parseInternal(jsonParser, builder);
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
