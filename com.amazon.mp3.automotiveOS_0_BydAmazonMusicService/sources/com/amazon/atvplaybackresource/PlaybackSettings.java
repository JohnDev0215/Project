package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.Caching;
import com.amazon.atvplaybackresource.Callback;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore;
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
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSettings {
    public final Optional<Caching> caching;
    public final Optional<Callback> callback;
    public final Optional<String> formatVersion;
    public final Optional<ByteBuffer> playbackSettings;
    public final Optional<String> settingsId;

    private PlaybackSettings(Builder builder) {
        this.callback = Optional.fromNullable(builder.callback);
        this.playbackSettings = Optional.fromNullable(builder.playbackSettings);
        this.settingsId = Optional.fromNullable(builder.settingsId);
        this.caching = Optional.fromNullable(builder.caching);
        this.formatVersion = Optional.fromNullable(builder.formatVersion);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackSettings)) {
            return false;
        }
        PlaybackSettings playbackSettings = (PlaybackSettings) obj;
        return Objects.equal(this.callback, playbackSettings.callback) && Objects.equal(this.playbackSettings, playbackSettings.playbackSettings) && Objects.equal(this.settingsId, playbackSettings.settingsId) && Objects.equal(this.caching, playbackSettings.caching) && Objects.equal(this.formatVersion, playbackSettings.formatVersion);
    }

    public int hashCode() {
        return Objects.hashCode(this.callback, this.playbackSettings, this.settingsId, this.caching, this.formatVersion);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("callback", this.callback).add("playbackSettings", this.playbackSettings).add(HeuristicsConfigStore.SETTINGS_ID_KEY, this.settingsId).add("caching", this.caching).add("formatVersion", this.formatVersion).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Caching caching;
        public Callback callback;
        public String formatVersion;
        public ByteBuffer playbackSettings;
        public String settingsId;

        public PlaybackSettings build() {
            return new PlaybackSettings(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackSettings> {
        private final Caching.Parser mCachingParser;
        private final Callback.Parser mCallbackParser;
        private final SimpleParsers.BlobParser mblobTargetParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCachingParser = new Caching.Parser(objectMapper);
            this.mCallbackParser = new Callback.Parser(objectMapper);
            this.mblobTargetParser = SimpleParsers.BlobParser.INSTANCE;
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackSettings parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackSettings:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackSettings parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackSettings:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackSettings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
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
                        builder.callback = callback;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            byteBuffer = this.mblobTargetParser.parse(jsonNode2);
                        }
                        builder.playbackSettings = byteBuffer;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.settingsId = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            caching = this.mCachingParser.parse(jsonNode2);
                        }
                        builder.caching = caching;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.formatVersion = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackSettings parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            String str = null;
                            Callback callback = null;
                            ByteBuffer byteBuffer = null;
                            String str2 = null;
                            Caching caching = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    callback = this.mCallbackParser.parse(jsonParser);
                                }
                                builder.callback = callback;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    byteBuffer = this.mblobTargetParser.parse(jsonParser);
                                }
                                builder.playbackSettings = byteBuffer;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.settingsId = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    caching = this.mCachingParser.parse(jsonParser);
                                }
                                builder.caching = caching;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.formatVersion = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
