package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.AdUrlsProperties;
import com.amazon.urlvending.AudioVideoUrls;
import com.amazon.urlvending.DebStep;
import com.amazon.urlvending.PlaybackUrls;
import com.amazon.urlvending.PlaylistComponent;
import com.amazon.urlvending.SubtitleUrlLanguageOption;
import com.amazon.urlvending.TrickplayUrls;
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
public class GetAssetsResponse {

    @Deprecated
    public final Optional<ImmutableMap<String, AdUrlsProperties>> adUrls;
    public final Optional<AudioVideoUrls> audioVideoUrls;
    public final Optional<ImmutableMap<String, ImmutableList<PlaylistComponent>>> cuepoints;
    public final Optional<ImmutableMap<String, ImmutableList<DebStep>>> debInfo;
    public final Optional<ImmutableList<SubtitleUrlLanguageOption>> forcedNarrativeUrls;
    public final Optional<ImmutableMap<String, String>> holdbacks;
    public final Optional<PlaybackUrls> playbackUrls;
    public final Optional<ImmutableList<SubtitleUrlLanguageOption>> subtitleUrls;
    public final Optional<TrickplayUrls> trickPlayUrls;

    private GetAssetsResponse(Builder builder) {
        this.trickPlayUrls = Optional.fromNullable(builder.trickPlayUrls);
        this.forcedNarrativeUrls = Optional.fromNullable(builder.forcedNarrativeUrls);
        this.playbackUrls = Optional.fromNullable(builder.playbackUrls);
        this.cuepoints = Optional.fromNullable(builder.cuepoints);
        this.debInfo = Optional.fromNullable(builder.debInfo);
        this.audioVideoUrls = Optional.fromNullable(builder.audioVideoUrls);
        this.subtitleUrls = Optional.fromNullable(builder.subtitleUrls);
        this.adUrls = Optional.fromNullable(builder.adUrls);
        this.holdbacks = Optional.fromNullable(builder.holdbacks);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAssetsResponse)) {
            return false;
        }
        GetAssetsResponse getAssetsResponse = (GetAssetsResponse) obj;
        return Objects.equal(this.trickPlayUrls, getAssetsResponse.trickPlayUrls) && Objects.equal(this.forcedNarrativeUrls, getAssetsResponse.forcedNarrativeUrls) && Objects.equal(this.playbackUrls, getAssetsResponse.playbackUrls) && Objects.equal(this.cuepoints, getAssetsResponse.cuepoints) && Objects.equal(this.debInfo, getAssetsResponse.debInfo) && Objects.equal(this.audioVideoUrls, getAssetsResponse.audioVideoUrls) && Objects.equal(this.subtitleUrls, getAssetsResponse.subtitleUrls) && Objects.equal(this.adUrls, getAssetsResponse.adUrls) && Objects.equal(this.holdbacks, getAssetsResponse.holdbacks);
    }

    public int hashCode() {
        return Objects.hashCode(this.trickPlayUrls, this.forcedNarrativeUrls, this.playbackUrls, this.cuepoints, this.debInfo, this.audioVideoUrls, this.subtitleUrls, this.adUrls, this.holdbacks);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("trickPlayUrls", this.trickPlayUrls).add("forcedNarrativeUrls", this.forcedNarrativeUrls).add("playbackUrls", this.playbackUrls).add("cuepoints", this.cuepoints).add("debInfo", this.debInfo).add("audioVideoUrls", this.audioVideoUrls).add("subtitleUrls", this.subtitleUrls).add("adUrls", this.adUrls).add("holdbacks", this.holdbacks).toString();
    }

    @NotThreadSafe
    public static class Builder {

        @Deprecated
        public ImmutableMap<String, AdUrlsProperties> adUrls;
        public AudioVideoUrls audioVideoUrls;
        public ImmutableMap<String, ImmutableList<PlaylistComponent>> cuepoints;
        public ImmutableMap<String, ImmutableList<DebStep>> debInfo;
        public ImmutableList<SubtitleUrlLanguageOption> forcedNarrativeUrls;
        public ImmutableMap<String, String> holdbacks;
        public PlaybackUrls playbackUrls;
        public ImmutableList<SubtitleUrlLanguageOption> subtitleUrls;
        public TrickplayUrls trickPlayUrls;

        public GetAssetsResponse build() {
            return new GetAssetsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAssetsResponse> {
        private final MapParser<String, AdUrlsProperties> mAdUrlsParser;
        private final AudioVideoUrls.Parser mAudioVideoUrlsParser;
        private final MapParser<String, ImmutableList<PlaylistComponent>> mCuepointMapParser;
        private final MapParser<String, ImmutableList<DebStep>> mDebInfoMapParser;
        private final MapParser<String, String> mHoldbacksMapParser;
        private final PlaybackUrls.Parser mPlaybackUrlsParser;
        private final ListParser<SubtitleUrlLanguageOption> mSubtitleUrlsLanguageListParser;
        private final TrickplayUrls.Parser mTrickplayUrlsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTrickplayUrlsParser = new TrickplayUrls.Parser(objectMapper);
            this.mSubtitleUrlsLanguageListParser = ListParser.newParser(new SubtitleUrlLanguageOption.Parser(objectMapper));
            this.mPlaybackUrlsParser = new PlaybackUrls.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mCuepointMapParser = MapParser.newParser(stringParser, ListParser.newParser(new PlaylistComponent.Parser(objectMapper)));
            this.mDebInfoMapParser = MapParser.newParser(stringParser, ListParser.newParser(new DebStep.Parser(objectMapper)));
            this.mAdUrlsParser = MapParser.newParser(stringParser, new AdUrlsProperties.Parser(objectMapper));
            this.mHoldbacksMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAudioVideoUrlsParser = new AudioVideoUrls.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAssetsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAssetsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAssetsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAssetsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAssetsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            TrickplayUrls trickplayUrls;
            ImmutableList<SubtitleUrlLanguageOption> immutableList;
            PlaybackUrls playbackUrls;
            ImmutableMap<String, ImmutableList<PlaylistComponent>> immutableMap;
            ImmutableMap<String, ImmutableList<DebStep>> immutableMap2;
            AudioVideoUrls audioVideoUrls;
            ImmutableList<SubtitleUrlLanguageOption> immutableList2;
            ImmutableMap<String, AdUrlsProperties> immutableMap3;
            ImmutableMap<String, String> immutableMap4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAssetsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2044469988:
                            if (next.equals("subtitleUrls")) {
                                b = 6;
                            }
                            break;
                        case -1422942297:
                            if (next.equals("adUrls")) {
                                b = 7;
                            }
                            break;
                        case 1204273933:
                            if (next.equals("holdbacks")) {
                                b = 8;
                            }
                            break;
                        case 1355525535:
                            if (next.equals("playbackUrls")) {
                                b = 2;
                            }
                            break;
                        case 1540387919:
                            if (next.equals("debInfo")) {
                                b = 4;
                            }
                            break;
                        case 1557868745:
                            if (next.equals("audioVideoUrls")) {
                                b = 5;
                            }
                            break;
                        case 1889870365:
                            if (next.equals("forcedNarrativeUrls")) {
                                b = 1;
                            }
                            break;
                        case 1956785366:
                            if (next.equals("cuepoints")) {
                                b = 3;
                            }
                            break;
                        case 1998560939:
                            if (next.equals("trickPlayUrls")) {
                                b = 0;
                            }
                            break;
                    }
                    trickplayUrls = null;
                    immutableMap4 = null;
                    immutableMap3 = null;
                    immutableList2 = null;
                    audioVideoUrls = null;
                    immutableMap2 = null;
                    immutableMap = null;
                    playbackUrls = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            trickplayUrls = this.mTrickplayUrlsParser.parse(jsonNode2);
                        }
                        builder.trickPlayUrls = trickplayUrls;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSubtitleUrlsLanguageListParser.parse(jsonNode2);
                        }
                        builder.forcedNarrativeUrls = immutableList;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            playbackUrls = this.mPlaybackUrlsParser.parse(jsonNode2);
                        }
                        builder.playbackUrls = playbackUrls;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mCuepointMapParser.parse(jsonNode2);
                        }
                        builder.cuepoints = immutableMap;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mDebInfoMapParser.parse(jsonNode2);
                        }
                        builder.debInfo = immutableMap2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            audioVideoUrls = this.mAudioVideoUrlsParser.parse(jsonNode2);
                        }
                        builder.audioVideoUrls = audioVideoUrls;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mSubtitleUrlsLanguageListParser.parse(jsonNode2);
                        }
                        builder.subtitleUrls = immutableList2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableMap3 = this.mAdUrlsParser.parse(jsonNode2);
                        }
                        builder.adUrls = immutableMap3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableMap4 = this.mHoldbacksMapParser.parse(jsonNode2);
                        }
                        builder.holdbacks = immutableMap4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAssetsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2044469988:
                                    if (currentName.equals("subtitleUrls")) {
                                        b = 6;
                                    }
                                    break;
                                case -1422942297:
                                    if (currentName.equals("adUrls")) {
                                        b = 7;
                                    }
                                    break;
                                case 1204273933:
                                    if (currentName.equals("holdbacks")) {
                                        b = 8;
                                    }
                                    break;
                                case 1355525535:
                                    if (currentName.equals("playbackUrls")) {
                                        b = 2;
                                    }
                                    break;
                                case 1540387919:
                                    if (currentName.equals("debInfo")) {
                                        b = 4;
                                    }
                                    break;
                                case 1557868745:
                                    if (currentName.equals("audioVideoUrls")) {
                                        b = 5;
                                    }
                                    break;
                                case 1889870365:
                                    if (currentName.equals("forcedNarrativeUrls")) {
                                        b = 1;
                                    }
                                    break;
                                case 1956785366:
                                    if (currentName.equals("cuepoints")) {
                                        b = 3;
                                    }
                                    break;
                                case 1998560939:
                                    if (currentName.equals("trickPlayUrls")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            TrickplayUrls trickplayUrls = null;
                            ImmutableMap<String, String> immutableMap = null;
                            ImmutableMap<String, AdUrlsProperties> immutableMap2 = null;
                            ImmutableList<SubtitleUrlLanguageOption> immutableList = null;
                            AudioVideoUrls audioVideoUrls = null;
                            ImmutableMap<String, ImmutableList<DebStep>> immutableMap3 = null;
                            ImmutableMap<String, ImmutableList<PlaylistComponent>> immutableMap4 = null;
                            PlaybackUrls playbackUrls = null;
                            ImmutableList<SubtitleUrlLanguageOption> immutableList2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        trickplayUrls = this.mTrickplayUrlsParser.parse(jsonParser);
                                    }
                                    builder.trickPlayUrls = trickplayUrls;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mSubtitleUrlsLanguageListParser.parse(jsonParser);
                                    }
                                    builder.forcedNarrativeUrls = immutableList2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playbackUrls = this.mPlaybackUrlsParser.parse(jsonParser);
                                    }
                                    builder.playbackUrls = playbackUrls;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap4 = this.mCuepointMapParser.parse(jsonParser);
                                    }
                                    builder.cuepoints = immutableMap4;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mDebInfoMapParser.parse(jsonParser);
                                    }
                                    builder.debInfo = immutableMap3;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        audioVideoUrls = this.mAudioVideoUrlsParser.parse(jsonParser);
                                    }
                                    builder.audioVideoUrls = audioVideoUrls;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mSubtitleUrlsLanguageListParser.parse(jsonParser);
                                    }
                                    builder.subtitleUrls = immutableList;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mAdUrlsParser.parse(jsonParser);
                                    }
                                    builder.adUrls = immutableMap2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mHoldbacksMapParser.parse(jsonParser);
                                    }
                                    builder.holdbacks = immutableMap;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
