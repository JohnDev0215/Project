package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.playbackresourcev2.PlaybackSettings;
import com.amazon.avod.playbackresourcev2.Prsv2Error;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.PlaybackUrlSet;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LivePlaybackUrls {
    private final Optional<String> mCacheKey;
    private final Optional<String> mDefaultUrlSetId;
    private final Optional<Prsv2Error> mError;
    private final Optional<PlaybackSettings> mPlaybackSettings;
    private final Optional<ImmutableList<PlaybackUrlSet>> mUrlSets;

    LivePlaybackUrls(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mCacheKey = Optional.fromNullable(builder.mCacheKey);
        this.mDefaultUrlSetId = Optional.fromNullable(builder.mDefaultUrlSetId);
        this.mPlaybackSettings = Optional.fromNullable(builder.mPlaybackSettings);
        this.mUrlSets = Optional.fromNullable(builder.mUrlSets);
        this.mError = Optional.fromNullable(builder.mError);
    }

    @Nonnull
    public Optional<String> getCacheKey() {
        return this.mCacheKey;
    }

    @Nonnull
    public Optional<String> getDefaultUrlSetId() {
        return this.mDefaultUrlSetId;
    }

    @Nonnull
    public Optional<PlaybackSettings> getPlaybackSettings() {
        return this.mPlaybackSettings;
    }

    @Nonnull
    public Optional<ImmutableList<PlaybackUrlSet>> getUrlSets() {
        return this.mUrlSets;
    }

    @Nonnull
    public Optional<Prsv2Error> getError() {
        return this.mError;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cacheKey", this.mCacheKey).add("urlSets", this.mUrlSets).add("defaultUrlSetId", this.mDefaultUrlSetId).add("playbackSettings", this.mPlaybackSettings).toString();
    }

    static class Builder {
        private String mCacheKey;
        private String mDefaultUrlSetId;
        private Prsv2Error mError;
        private PlaybackSettings mPlaybackSettings;
        private ImmutableList<PlaybackUrlSet> mUrlSets;

        Builder() {
        }

        LivePlaybackUrls build() {
            return new LivePlaybackUrls(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<LivePlaybackUrls> {
        private final Prsv2Error.Parser mErrorParser;
        private final PlaybackSettings.Parser mPlaybackSettingsParser;
        private final ListParser<PlaybackUrlSet> mPlaybackUrlSetListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, LivePlaybackUrls.class);
            this.mPlaybackSettingsParser = new PlaybackSettings.Parser(objectMapper);
            this.mPlaybackUrlSetListParser = ListParser.newParser(new PlaybackUrlSet.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mErrorParser = new Prsv2Error.Parser(objectMapper);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public LivePlaybackUrls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LivePlaybackUrls");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -433508483:
                            if (next.equals("cacheKey")) {
                                b = 0;
                            }
                            break;
                        case -170214816:
                            if (next.equals("urlSets")) {
                                b = 1;
                            }
                            break;
                        case 96784904:
                            if (next.equals("error")) {
                                b = 4;
                            }
                            break;
                        case 274770782:
                            if (next.equals("playbackSettings")) {
                                b = 3;
                            }
                            break;
                        case 1016395343:
                            if (next.equals("defaultUrlSetId")) {
                                b = 2;
                            }
                            break;
                    }
                    Prsv2Error prsv2Error = null;
                    String str = null;
                    ImmutableList<PlaybackUrlSet> immutableList = null;
                    String str2 = null;
                    PlaybackSettings playbackSettings = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mCacheKey = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mPlaybackUrlSetListParser.parse(jsonNode2);
                        }
                        builder.mUrlSets = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mDefaultUrlSetId = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            playbackSettings = this.mPlaybackSettingsParser.parse(jsonNode2);
                        }
                        builder.mPlaybackSettings = playbackSettings;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            prsv2Error = this.mErrorParser.parse(jsonNode2);
                        }
                        builder.mError = prsv2Error;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LivePlaybackUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public LivePlaybackUrls parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            return parseInternal(jsonParser, new Builder());
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        protected LivePlaybackUrls parseInternal(@Nonnull JsonParser jsonParser, @Nonnull Builder builder) throws IOException {
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
                            case -934426595:
                                if (currentName.equals("result")) {
                                    b = 4;
                                }
                                break;
                            case -433508483:
                                if (currentName.equals("cacheKey")) {
                                    b = 0;
                                }
                                break;
                            case -170214816:
                                if (currentName.equals("urlSets")) {
                                    b = 1;
                                }
                                break;
                            case 96784904:
                                if (currentName.equals("error")) {
                                    b = 5;
                                }
                                break;
                            case 274770782:
                                if (currentName.equals("playbackSettings")) {
                                    b = 3;
                                }
                                break;
                            case 1016395343:
                                if (currentName.equals("defaultUrlSetId")) {
                                    b = 2;
                                }
                                break;
                        }
                        String str = null;
                        Prsv2Error prsv2Error = null;
                        PlaybackSettings playbackSettings = null;
                        String str2 = null;
                        ImmutableList<PlaybackUrlSet> immutableList = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringParser.parse(jsonParser);
                            }
                            builder.mCacheKey = str;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                immutableList = this.mPlaybackUrlSetListParser.parse(jsonParser);
                            }
                            builder.mUrlSets = immutableList;
                        } else if (b == 2) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str2 = this.mStringParser.parse(jsonParser);
                            }
                            builder.mDefaultUrlSetId = str2;
                        } else if (b == 3) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                playbackSettings = this.mPlaybackSettingsParser.parse(jsonParser);
                            }
                            builder.mPlaybackSettings = playbackSettings;
                        } else if (b == 4) {
                            parseInternal(jsonParser, builder);
                        } else if (b == 5) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                prsv2Error = this.mErrorParser.parse(jsonParser);
                            }
                            builder.mError = prsv2Error;
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LivePlaybackUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
