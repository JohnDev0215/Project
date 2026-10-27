package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.AudioTrackMetadata;
import com.amazon.urlvending.PlaybackUrlSet;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class GetLivePlaybackUrlsPlaybackUrls {
    public final Optional<ImmutableList<AudioTrackMetadata>> audioTracks;
    public final Optional<String> auxCacheKey;
    public final Optional<String> cacheKey;
    public final Optional<String> defaultAudioTrackId;
    public final Optional<String> defaultUrlSetId;
    public final Optional<ImmutableMap<String, PlaybackUrlSet>> urlSets;

    private GetLivePlaybackUrlsPlaybackUrls(Builder builder) {
        this.urlSets = Optional.fromNullable(builder.urlSets);
        this.defaultUrlSetId = Optional.fromNullable(builder.defaultUrlSetId);
        this.cacheKey = Optional.fromNullable(builder.cacheKey);
        this.auxCacheKey = Optional.fromNullable(builder.auxCacheKey);
        this.audioTracks = Optional.fromNullable(builder.audioTracks);
        this.defaultAudioTrackId = Optional.fromNullable(builder.defaultAudioTrackId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsPlaybackUrls)) {
            return false;
        }
        GetLivePlaybackUrlsPlaybackUrls getLivePlaybackUrlsPlaybackUrls = (GetLivePlaybackUrlsPlaybackUrls) obj;
        return Objects.equal(this.urlSets, getLivePlaybackUrlsPlaybackUrls.urlSets) && Objects.equal(this.defaultUrlSetId, getLivePlaybackUrlsPlaybackUrls.defaultUrlSetId) && Objects.equal(this.cacheKey, getLivePlaybackUrlsPlaybackUrls.cacheKey) && Objects.equal(this.auxCacheKey, getLivePlaybackUrlsPlaybackUrls.auxCacheKey) && Objects.equal(this.audioTracks, getLivePlaybackUrlsPlaybackUrls.audioTracks) && Objects.equal(this.defaultAudioTrackId, getLivePlaybackUrlsPlaybackUrls.defaultAudioTrackId);
    }

    public int hashCode() {
        return Objects.hashCode(this.urlSets, this.defaultUrlSetId, this.cacheKey, this.auxCacheKey, this.audioTracks, this.defaultAudioTrackId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("urlSets", this.urlSets).add("defaultUrlSetId", this.defaultUrlSetId).add("cacheKey", this.cacheKey).add("auxCacheKey", this.auxCacheKey).add("audioTracks", this.audioTracks).add("defaultAudioTrackId", this.defaultAudioTrackId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<AudioTrackMetadata> audioTracks;
        public String auxCacheKey;
        public String cacheKey;
        public String defaultAudioTrackId;
        public String defaultUrlSetId;
        public ImmutableMap<String, PlaybackUrlSet> urlSets;

        public GetLivePlaybackUrlsPlaybackUrls build() {
            return new GetLivePlaybackUrlsPlaybackUrls(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsPlaybackUrls> {
        private final ListParser<AudioTrackMetadata> mAudioTrackMetadataListParser;
        private final MapParser<String, PlaybackUrlSet> mPlaybackUrlSetMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAudioTrackMetadataListParser = ListParser.newParser(new AudioTrackMetadata.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mPlaybackUrlSetMapParser = MapParser.newParser(stringParser, new PlaybackUrlSet.Parser(objectMapper));
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsPlaybackUrls parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsPlaybackUrls:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsPlaybackUrls parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsPlaybackUrls:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsPlaybackUrls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsPlaybackUrls");
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
                                b = 2;
                            }
                            break;
                        case -170214816:
                            if (next.equals("urlSets")) {
                                b = 0;
                            }
                            break;
                        case 468584446:
                            if (next.equals("audioTracks")) {
                                b = 4;
                            }
                            break;
                        case 1016395343:
                            if (next.equals("defaultUrlSetId")) {
                                b = 1;
                            }
                            break;
                        case 1508230129:
                            if (next.equals("defaultAudioTrackId")) {
                                b = 5;
                            }
                            break;
                        case 2052065889:
                            if (next.equals("auxCacheKey")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    ImmutableMap<String, PlaybackUrlSet> immutableMap = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    ImmutableList<AudioTrackMetadata> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mPlaybackUrlSetMapParser.parse(jsonNode2);
                        }
                        builder.urlSets = immutableMap;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.defaultUrlSetId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cacheKey = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.auxCacheKey = str4;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAudioTrackMetadataListParser.parse(jsonNode2);
                        }
                        builder.audioTracks = immutableList;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.defaultAudioTrackId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsPlaybackUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsPlaybackUrls parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -433508483:
                                    if (currentName.equals("cacheKey")) {
                                        b = 2;
                                    }
                                    break;
                                case -170214816:
                                    if (currentName.equals("urlSets")) {
                                        b = 0;
                                    }
                                    break;
                                case 468584446:
                                    if (currentName.equals("audioTracks")) {
                                        b = 4;
                                    }
                                    break;
                                case 1016395343:
                                    if (currentName.equals("defaultUrlSetId")) {
                                        b = 1;
                                    }
                                    break;
                                case 1508230129:
                                    if (currentName.equals("defaultAudioTrackId")) {
                                        b = 5;
                                    }
                                    break;
                                case 2052065889:
                                    if (currentName.equals("auxCacheKey")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            ImmutableMap<String, PlaybackUrlSet> immutableMap = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            ImmutableList<AudioTrackMetadata> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mPlaybackUrlSetMapParser.parse(jsonParser);
                                }
                                builder.urlSets = immutableMap;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.defaultUrlSetId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mStringParser.parse(jsonParser);
                                }
                                builder.cacheKey = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str4 = this.mStringParser.parse(jsonParser);
                                }
                                builder.auxCacheKey = str4;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAudioTrackMetadataListParser.parse(jsonParser);
                                }
                                builder.audioTracks = immutableList;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.defaultAudioTrackId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsPlaybackUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
