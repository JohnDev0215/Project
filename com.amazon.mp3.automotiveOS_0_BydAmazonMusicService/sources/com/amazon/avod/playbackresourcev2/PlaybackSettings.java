package com.amazon.avod.playbackresourcev2;

import com.amazon.atvplaybackresource.Caching;
import com.amazon.atvplaybackresource.Callback;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore;
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
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSettings {
    private final Optional<Caching> mCaching;
    private final Optional<Callback> mCallback;
    private final Optional<String> mFormatVersion;
    private final Optional<ByteBuffer> mPlaybackSettings;
    private final Optional<String> mSettingsId;

    private PlaybackSettings(Builder builder) {
        this.mCallback = Optional.fromNullable(builder.mCallback);
        this.mPlaybackSettings = Optional.fromNullable(builder.mPlaybackSettings);
        this.mSettingsId = Optional.fromNullable(builder.mSettingsId);
        this.mCaching = Optional.fromNullable(builder.mCaching);
        this.mFormatVersion = Optional.fromNullable(builder.mFormatVersion);
    }

    @Nonnull
    public Optional<Callback> getCallback() {
        return this.mCallback;
    }

    @Nonnull
    public Optional<ByteBuffer> getPlaybackSettings() {
        return this.mPlaybackSettings;
    }

    @Nonnull
    public Optional<String> getSettingsId() {
        return this.mSettingsId;
    }

    @Nonnull
    public Optional<Caching> getCaching() {
        return this.mCaching;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("callback", this.mCallback).add("playbackSettings", this.mPlaybackSettings).add(HeuristicsConfigStore.SETTINGS_ID_KEY, this.mSettingsId).add("caching", this.mCaching).add("formatVersion", this.mFormatVersion).toString();
    }

    static class Builder {
        public Caching mCaching;
        public Callback mCallback;
        public String mFormatVersion;
        public ByteBuffer mPlaybackSettings;
        public String mSettingsId;

        Builder() {
        }

        PlaybackSettings build() {
            return new PlaybackSettings(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<PlaybackSettings> {
        private final Caching.Parser mCachingParser;
        private final Callback.Parser mCallbackParser;
        private final SimpleParsers.StringParser mStringTargetParser;
        private final SimpleParsers.BlobParser mblobTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, PlaybackSettings.class);
            this.mCachingParser = new Caching.Parser(objectMapper);
            this.mCallbackParser = new Callback.Parser(objectMapper);
            this.mblobTargetParser = SimpleParsers.BlobParser.INSTANCE;
            this.mStringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public PlaybackSettings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackSettings");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -172220347:
                            if (next.equals("callback")) {
                                b = 0;
                            }
                            break;
                        case -3913570:
                            if (next.equals(HeuristicsConfigStore.SETTINGS_ID_KEY)) {
                                b = 2;
                            }
                            break;
                        case 274770782:
                            if (next.equals("playbackSettings")) {
                                b = 1;
                            }
                            break;
                        case 540210111:
                            if (next.equals("caching")) {
                                b = 3;
                            }
                            break;
                        case 1736190177:
                            if (next.equals("formatVersion")) {
                                b = 4;
                            }
                            break;
                    }
                    String str = null;
                    Callback callback = null;
                    ByteBuffer byteBuffer = null;
                    String str2 = null;
                    Caching caching = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            callback = this.mCallbackParser.parse(jsonNode2);
                        }
                        builder.mCallback = callback;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            byteBuffer = this.mblobTargetParser.parse(jsonNode2);
                        }
                        builder.mPlaybackSettings = byteBuffer;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringTargetParser.parse(jsonNode2);
                        }
                        builder.mSettingsId = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            caching = this.mCachingParser.parse(jsonNode2);
                        }
                        builder.mCaching = caching;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringTargetParser.parse(jsonNode2);
                        }
                        builder.mFormatVersion = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public PlaybackSettings parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            return parseInternal(jsonParser, new Builder());
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private PlaybackSettings parseInternal(@Nonnull JsonParser jsonParser, @Nonnull Builder builder) throws IOException {
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
                                    b = 5;
                                }
                                break;
                            case -172220347:
                                if (currentName.equals("callback")) {
                                    b = 0;
                                }
                                break;
                            case -3913570:
                                if (currentName.equals(HeuristicsConfigStore.SETTINGS_ID_KEY)) {
                                    b = 2;
                                }
                                break;
                            case 274770782:
                                if (currentName.equals("playbackSettings")) {
                                    b = 1;
                                }
                                break;
                            case 540210111:
                                if (currentName.equals("caching")) {
                                    b = 3;
                                }
                                break;
                            case 1736190177:
                                if (currentName.equals("formatVersion")) {
                                    b = 4;
                                }
                                break;
                        }
                        Callback callback = null;
                        String str = null;
                        Caching caching = null;
                        String str2 = null;
                        ByteBuffer byteBuffer = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                callback = this.mCallbackParser.parse(jsonParser);
                            }
                            builder.mCallback = callback;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                byteBuffer = this.mblobTargetParser.parse(jsonParser);
                            }
                            builder.mPlaybackSettings = byteBuffer;
                        } else if (b == 2) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str2 = this.mStringTargetParser.parse(jsonParser);
                            }
                            builder.mSettingsId = str2;
                        } else if (b == 3) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                caching = this.mCachingParser.parse(jsonParser);
                            }
                            builder.mCaching = caching;
                        } else if (b == 4) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringTargetParser.parse(jsonParser);
                            }
                            builder.mFormatVersion = str;
                        } else if (b == 5) {
                            parseInternal(jsonParser, builder);
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
