package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.atv.title.v1.PlaybackFragment;
import com.amazon.atv.title.v1.fragment.playback.v2.AccessControls;
import com.amazon.atv.title.v1.fragment.playback.v2.AudioTrack;
import com.amazon.atv.title.v1.fragment.playback.v2.LanguageGroup;
import com.amazon.atv.title.v1.fragment.playback.v2.PlaybackSummary;
import com.amazon.atv.title.v1.fragment.playback.v2.StreamingOption;
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
public class PlaybackFragmentV2 extends PlaybackFragment {
    public final Optional<AccessControls> accessControls;
    public final Optional<ImmutableList<AudioTrack>> audioTracks;
    public final Optional<LanguageGroup> contentLanguages;
    public final Optional<Boolean> hasTrailer;
    public final Optional<Integer> runtimeSeconds;
    public final Optional<ImmutableList<StreamingOption>> streamingOptions;
    public final Optional<PlaybackSummary> summary;
    public final Optional<String> vcid;

    private PlaybackFragmentV2(Builder builder) {
        super(builder);
        this.runtimeSeconds = Optional.fromNullable(builder.runtimeSeconds);
        this.contentLanguages = Optional.fromNullable(builder.contentLanguages);
        this.vcid = Optional.fromNullable(builder.vcid);
        this.accessControls = Optional.fromNullable(builder.accessControls);
        this.summary = Optional.fromNullable(builder.summary);
        this.streamingOptions = Optional.fromNullable(builder.streamingOptions);
        this.hasTrailer = Optional.fromNullable(builder.hasTrailer);
        this.audioTracks = Optional.fromNullable(builder.audioTracks);
    }

    @Override // com.amazon.atv.title.v1.PlaybackFragment, com.amazon.atv.title.v1.Fragment
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackFragmentV2)) {
            return false;
        }
        PlaybackFragmentV2 playbackFragmentV2 = (PlaybackFragmentV2) obj;
        return super.equals(obj) && Objects.equal(this.runtimeSeconds, playbackFragmentV2.runtimeSeconds) && Objects.equal(this.contentLanguages, playbackFragmentV2.contentLanguages) && Objects.equal(this.vcid, playbackFragmentV2.vcid) && Objects.equal(this.accessControls, playbackFragmentV2.accessControls) && Objects.equal(this.summary, playbackFragmentV2.summary) && Objects.equal(this.streamingOptions, playbackFragmentV2.streamingOptions) && Objects.equal(this.hasTrailer, playbackFragmentV2.hasTrailer) && Objects.equal(this.audioTracks, playbackFragmentV2.audioTracks);
    }

    @Override // com.amazon.atv.title.v1.PlaybackFragment, com.amazon.atv.title.v1.Fragment
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.runtimeSeconds, this.contentLanguages, this.vcid, this.accessControls, this.summary, this.streamingOptions, this.hasTrailer, this.audioTracks);
    }

    @Override // com.amazon.atv.title.v1.PlaybackFragment, com.amazon.atv.title.v1.Fragment
    public String toString() {
        return MoreObjects.toStringHelper(this).add("runtimeSeconds", this.runtimeSeconds).add("contentLanguages", this.contentLanguages).add("vcid", this.vcid).add("accessControls", this.accessControls).add("summary", this.summary).add("streamingOptions", this.streamingOptions).add("hasTrailer", this.hasTrailer).add("audioTracks", this.audioTracks).toString();
    }

    @NotThreadSafe
    public static class Builder extends PlaybackFragment.Builder {
        public AccessControls accessControls;
        public ImmutableList<AudioTrack> audioTracks;
        public LanguageGroup contentLanguages;
        public Boolean hasTrailer;
        public Integer runtimeSeconds;
        public ImmutableList<StreamingOption> streamingOptions;
        public PlaybackSummary summary;
        public String vcid;

        public PlaybackFragmentV2 build() {
            return new PlaybackFragmentV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackFragmentV2> {
        private final AccessControls.Parser mAccessControlsParser;
        private final ListParser<AudioTrack> mAudioTrackListParser;
        private final SimpleParsers.StringParser mFragmentVersionParser;
        private final LanguageGroup.Parser mLanguageGroupParser;
        private final SimpleParsers.BooleanParser mNullableBooleanParser;
        private final SimpleParsers.IntegerParser mNullableIntegerParser;
        private final PlaybackSummary.Parser mPlaybackSummaryParser;
        private final ListParser<StreamingOption> mStreamingOptionListParser;
        private final SimpleParsers.StringParser mValidUntilTimestampParser;
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLanguageGroupParser = new LanguageGroup.Parser(objectMapper);
            this.mStreamingOptionListParser = ListParser.newParser(new StreamingOption.Parser(objectMapper));
            this.mAudioTrackListParser = ListParser.newParser(new AudioTrack.Parser(objectMapper));
            this.mNullableIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mNullableBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mAccessControlsParser = new AccessControls.Parser(objectMapper);
            this.mPlaybackSummaryParser = new PlaybackSummary.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mbasicStringParser = stringParser;
            this.mFragmentVersionParser = stringParser;
            this.mValidUntilTimestampParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackFragmentV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackFragmentV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackFragmentV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackFragmentV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackFragmentV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Integer num;
            String str;
            LanguageGroup languageGroup;
            String str2;
            AccessControls accessControls;
            PlaybackSummary playbackSummary;
            ImmutableList<StreamingOption> immutableList;
            Boolean bool;
            ImmutableList<AudioTrack> immutableList2;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackFragmentV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2074407033:
                            if (next.equals("runtimeSeconds")) {
                                b = 0;
                            }
                            break;
                        case -1857640538:
                            if (next.equals("summary")) {
                                b = 5;
                            }
                            break;
                        case -1016218566:
                            if (next.equals("accessControls")) {
                                b = 4;
                            }
                            break;
                        case -705219620:
                            if (next.equals("streamingOptions")) {
                                b = 6;
                            }
                            break;
                        case -657736798:
                            if (next.equals("contentLanguages")) {
                                b = 2;
                            }
                            break;
                        case -54813502:
                            if (next.equals("validUntil")) {
                                b = 9;
                            }
                            break;
                        case 3613832:
                            if (next.equals("vcid")) {
                                b = 3;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 468584446:
                            if (next.equals("audioTracks")) {
                                b = 8;
                            }
                            break;
                        case 965171513:
                            if (next.equals("hasTrailer")) {
                                b = 7;
                            }
                            break;
                    }
                    num = null;
                    str3 = null;
                    immutableList2 = null;
                    bool = null;
                    immutableList = null;
                    playbackSummary = null;
                    accessControls = null;
                    str2 = null;
                    languageGroup = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackFragmentV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            num = this.mNullableIntegerParser.parse(jsonNode2);
                        }
                        builder.runtimeSeconds = num;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            languageGroup = this.mLanguageGroupParser.parse(jsonNode2);
                        }
                        builder.contentLanguages = languageGroup;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.vcid = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            accessControls = this.mAccessControlsParser.parse(jsonNode2);
                        }
                        builder.accessControls = accessControls;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            playbackSummary = this.mPlaybackSummaryParser.parse(jsonNode2);
                        }
                        builder.summary = playbackSummary;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStreamingOptionListParser.parse(jsonNode2);
                        }
                        builder.streamingOptions = immutableList;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            bool = this.mNullableBooleanParser.parse(jsonNode2);
                        }
                        builder.hasTrailer = bool;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mAudioTrackListParser.parse(jsonNode2);
                        }
                        builder.audioTracks = immutableList2;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mValidUntilTimestampParser.parse(jsonNode2);
                        }
                        builder.validUntil = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackFragmentV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackFragmentV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2074407033:
                                    if (currentName.equals("runtimeSeconds")) {
                                        b = 0;
                                    }
                                    break;
                                case -1857640538:
                                    if (currentName.equals("summary")) {
                                        b = 5;
                                    }
                                    break;
                                case -1016218566:
                                    if (currentName.equals("accessControls")) {
                                        b = 4;
                                    }
                                    break;
                                case -705219620:
                                    if (currentName.equals("streamingOptions")) {
                                        b = 6;
                                    }
                                    break;
                                case -657736798:
                                    if (currentName.equals("contentLanguages")) {
                                        b = 2;
                                    }
                                    break;
                                case -54813502:
                                    if (currentName.equals("validUntil")) {
                                        b = 9;
                                    }
                                    break;
                                case 3613832:
                                    if (currentName.equals("vcid")) {
                                        b = 3;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 468584446:
                                    if (currentName.equals("audioTracks")) {
                                        b = 8;
                                    }
                                    break;
                                case 965171513:
                                    if (currentName.equals("hasTrailer")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            Integer num = null;
                            String str = null;
                            ImmutableList<AudioTrack> immutableList = null;
                            Boolean bool = null;
                            ImmutableList<StreamingOption> immutableList2 = null;
                            PlaybackSummary playbackSummary = null;
                            AccessControls accessControls = null;
                            String str2 = null;
                            LanguageGroup languageGroup = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mNullableIntegerParser.parse(jsonParser);
                                    }
                                    builder.runtimeSeconds = num;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mFragmentVersionParser.parse(jsonParser);
                                    }
                                    builder.version = str3;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        languageGroup = this.mLanguageGroupParser.parse(jsonParser);
                                    }
                                    builder.contentLanguages = languageGroup;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mbasicStringParser.parse(jsonParser);
                                    }
                                    builder.vcid = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        accessControls = this.mAccessControlsParser.parse(jsonParser);
                                    }
                                    builder.accessControls = accessControls;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playbackSummary = this.mPlaybackSummaryParser.parse(jsonParser);
                                    }
                                    builder.summary = playbackSummary;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mStreamingOptionListParser.parse(jsonParser);
                                    }
                                    builder.streamingOptions = immutableList2;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mNullableBooleanParser.parse(jsonParser);
                                    }
                                    builder.hasTrailer = bool;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mAudioTrackListParser.parse(jsonParser);
                                    }
                                    builder.audioTracks = immutableList;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mValidUntilTimestampParser.parse(jsonParser);
                                    }
                                    builder.validUntil = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackFragmentV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackFragmentV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
