package com.amazon.urlvending;

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
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes5.dex */
public class ManifestUrl {
    public final Optional<String> audioFormat;
    public final Optional<String> audioTrackId;
    public final Optional<Integer> bitrate;
    public final Optional<String> bitrateAdaption;
    public final Optional<String> cdn;
    public final Optional<String> cdnOrigin;
    public final Optional<String> compressionStandard;
    public final Optional<String> contiguityType;
    public final Optional<String> drm;
    public final Optional<Long> duration;
    public final Optional<String> dynamicAdInsertion;
    public final Optional<String> dynamicRange;
    public final Optional<String> encodingVersion;
    public final Optional<String> fragmentRepresentation;
    public final Optional<String> frameRate;
    public final Optional<String> livePlaybackStreamId;
    public final Optional<String> lookbackWindow;
    public final Optional<String> midasRegion;
    public final Optional<String> origin;
    public final Optional<Integer> resolutionHeight;
    public final Optional<Integer> resolutionWidth;
    public final Optional<String> segmentListRepresentation;
    public final Optional<String> startTime;
    public final Optional<String> streamingTechnology;
    public final Optional<String> subtitleRepresentation;
    public final Optional<String> url;
    public final Optional<String> videoQuality;

    private ManifestUrl(Builder builder) {
        this.livePlaybackStreamId = Optional.fromNullable(builder.livePlaybackStreamId);
        this.subtitleRepresentation = Optional.fromNullable(builder.subtitleRepresentation);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.fragmentRepresentation = Optional.fromNullable(builder.fragmentRepresentation);
        this.contiguityType = Optional.fromNullable(builder.contiguityType);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.origin = Optional.fromNullable(builder.origin);
        this.startTime = Optional.fromNullable(builder.startTime);
        this.cdnOrigin = Optional.fromNullable(builder.cdnOrigin);
        this.streamingTechnology = Optional.fromNullable(builder.streamingTechnology);
        this.compressionStandard = Optional.fromNullable(builder.compressionStandard);
        this.audioFormat = Optional.fromNullable(builder.audioFormat);
        this.bitrate = Optional.fromNullable(builder.bitrate);
        this.duration = Optional.fromNullable(builder.duration);
        this.dynamicAdInsertion = Optional.fromNullable(builder.dynamicAdInsertion);
        this.lookbackWindow = Optional.fromNullable(builder.lookbackWindow);
        this.dynamicRange = Optional.fromNullable(builder.dynamicRange);
        this.segmentListRepresentation = Optional.fromNullable(builder.segmentListRepresentation);
        this.cdn = Optional.fromNullable(builder.cdn);
        this.encodingVersion = Optional.fromNullable(builder.encodingVersion);
        this.bitrateAdaption = Optional.fromNullable(builder.bitrateAdaption);
        this.resolutionHeight = Optional.fromNullable(builder.resolutionHeight);
        this.resolutionWidth = Optional.fromNullable(builder.resolutionWidth);
        this.frameRate = Optional.fromNullable(builder.frameRate);
        this.drm = Optional.fromNullable(builder.drm);
        this.midasRegion = Optional.fromNullable(builder.midasRegion);
        this.url = Optional.fromNullable(builder.url);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ManifestUrl)) {
            return false;
        }
        ManifestUrl manifestUrl = (ManifestUrl) obj;
        return Objects.equal(this.livePlaybackStreamId, manifestUrl.livePlaybackStreamId) && Objects.equal(this.subtitleRepresentation, manifestUrl.subtitleRepresentation) && Objects.equal(this.videoQuality, manifestUrl.videoQuality) && Objects.equal(this.fragmentRepresentation, manifestUrl.fragmentRepresentation) && Objects.equal(this.contiguityType, manifestUrl.contiguityType) && Objects.equal(this.audioTrackId, manifestUrl.audioTrackId) && Objects.equal(this.origin, manifestUrl.origin) && Objects.equal(this.startTime, manifestUrl.startTime) && Objects.equal(this.cdnOrigin, manifestUrl.cdnOrigin) && Objects.equal(this.streamingTechnology, manifestUrl.streamingTechnology) && Objects.equal(this.compressionStandard, manifestUrl.compressionStandard) && Objects.equal(this.audioFormat, manifestUrl.audioFormat) && Objects.equal(this.bitrate, manifestUrl.bitrate) && Objects.equal(this.duration, manifestUrl.duration) && Objects.equal(this.dynamicAdInsertion, manifestUrl.dynamicAdInsertion) && Objects.equal(this.lookbackWindow, manifestUrl.lookbackWindow) && Objects.equal(this.dynamicRange, manifestUrl.dynamicRange) && Objects.equal(this.segmentListRepresentation, manifestUrl.segmentListRepresentation) && Objects.equal(this.cdn, manifestUrl.cdn) && Objects.equal(this.encodingVersion, manifestUrl.encodingVersion) && Objects.equal(this.bitrateAdaption, manifestUrl.bitrateAdaption) && Objects.equal(this.resolutionHeight, manifestUrl.resolutionHeight) && Objects.equal(this.resolutionWidth, manifestUrl.resolutionWidth) && Objects.equal(this.frameRate, manifestUrl.frameRate) && Objects.equal(this.drm, manifestUrl.drm) && Objects.equal(this.midasRegion, manifestUrl.midasRegion) && Objects.equal(this.url, manifestUrl.url);
    }

    public int hashCode() {
        return Objects.hashCode(this.livePlaybackStreamId, this.subtitleRepresentation, this.videoQuality, this.fragmentRepresentation, this.contiguityType, this.audioTrackId, this.origin, this.startTime, this.cdnOrigin, this.streamingTechnology, this.compressionStandard, this.audioFormat, this.bitrate, this.duration, this.dynamicAdInsertion, this.lookbackWindow, this.dynamicRange, this.segmentListRepresentation, this.cdn, this.encodingVersion, this.bitrateAdaption, this.resolutionHeight, this.resolutionWidth, this.frameRate, this.drm, this.midasRegion, this.url);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("livePlaybackStreamId", this.livePlaybackStreamId).add("subtitleRepresentation", this.subtitleRepresentation).add("videoQuality", this.videoQuality).add("fragmentRepresentation", this.fragmentRepresentation).add("contiguityType", this.contiguityType).add("audioTrackId", this.audioTrackId).add("origin", this.origin).add("startTime", this.startTime).add("cdnOrigin", this.cdnOrigin).add("streamingTechnology", this.streamingTechnology).add("compressionStandard", this.compressionStandard).add("audioFormat", this.audioFormat).add("bitrate", this.bitrate).add("duration", this.duration).add("dynamicAdInsertion", this.dynamicAdInsertion).add("lookbackWindow", this.lookbackWindow).add("dynamicRange", this.dynamicRange).add("segmentListRepresentation", this.segmentListRepresentation).add("cdn", this.cdn).add("encodingVersion", this.encodingVersion).add("bitrateAdaption", this.bitrateAdaption).add("resolutionHeight", this.resolutionHeight).add("resolutionWidth", this.resolutionWidth).add("frameRate", this.frameRate).add("drm", this.drm).add("midasRegion", this.midasRegion).add(ImagesContract.URL, this.url).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioFormat;
        public String audioTrackId;
        public Integer bitrate;
        public String bitrateAdaption;
        public String cdn;
        public String cdnOrigin;
        public String compressionStandard;
        public String contiguityType;
        public String drm;
        public Long duration;
        public String dynamicAdInsertion;
        public String dynamicRange;
        public String encodingVersion;
        public String fragmentRepresentation;
        public String frameRate;
        public String livePlaybackStreamId;
        public String lookbackWindow;
        public String midasRegion;
        public String origin;
        public Integer resolutionHeight;
        public Integer resolutionWidth;
        public String segmentListRepresentation;
        public String startTime;
        public String streamingTechnology;
        public String subtitleRepresentation;
        public String url;
        public String videoQuality;

        public ManifestUrl build() {
            return new ManifestUrl(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ManifestUrl> {
        private final SimpleParsers.StringParser mAudioFormatParser;
        private final SimpleParsers.StringParser mBitrateAdaptionParser;
        private final SimpleParsers.StringParser mCdnParser;
        private final SimpleParsers.StringParser mCompressionStandardParser;
        private final SimpleParsers.StringParser mContiguityTypeParser;
        private final SimpleParsers.StringParser mDrmParser;
        private final SimpleParsers.StringParser mDynamicAdInsertionTypeParser;
        private final SimpleParsers.StringParser mDynamicRangeParser;
        private final SimpleParsers.StringParser mFragmentRepresentationParser;
        private final SimpleParsers.StringParser mFrameRateParser;
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final SimpleParsers.LongParser mLongParser;
        private final SimpleParsers.StringParser mLookbackWindowParser;
        private final SimpleParsers.StringParser mSegmentListRepresentationParser;
        private final SimpleParsers.StringParser mStreamingTechnologyParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.StringParser mSubtitleRepresentationParser;
        private final SimpleParsers.StringParser mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFragmentRepresentationParser = stringParser;
            this.mStringParser = stringParser;
            this.mSubtitleRepresentationParser = stringParser;
            this.mVideoQualityParser = stringParser;
            this.mContiguityTypeParser = stringParser;
            this.mFrameRateParser = stringParser;
            this.mDrmParser = stringParser;
            this.mStreamingTechnologyParser = stringParser;
            this.mBitrateAdaptionParser = stringParser;
            this.mCompressionStandardParser = stringParser;
            this.mAudioFormatParser = stringParser;
            this.mCdnParser = stringParser;
            this.mDynamicAdInsertionTypeParser = stringParser;
            this.mSegmentListRepresentationParser = stringParser;
            this.mDynamicRangeParser = stringParser;
            this.mLookbackWindowParser = stringParser;
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ManifestUrl parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ManifestUrl:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ManifestUrl parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ManifestUrl:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ManifestUrl parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            String str10;
            String str11;
            String str12;
            Integer num;
            Long l;
            String str13;
            String str14;
            String str15;
            String str16;
            String str17;
            String str18;
            String str19;
            Integer num2;
            Integer num3;
            String str20;
            String str21;
            String str22;
            String str23;
            JsonParsingUtils.throwIfNotObject(jsonNode, "ManifestUrl");
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
                                b = 7;
                            }
                            break;
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -1957882301:
                            if (next.equals("compressionStandard")) {
                                b = 10;
                            }
                            break;
                        case -1945743238:
                            if (next.equals("resolutionWidth")) {
                                b = 22;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 9;
                            }
                            break;
                        case -1182696034:
                            if (next.equals("segmentListRepresentation")) {
                                b = 17;
                            }
                            break;
                        case -1008619738:
                            if (next.equals("origin")) {
                                b = 6;
                            }
                            break;
                        case -624324714:
                            if (next.equals("lookbackWindow")) {
                                b = 15;
                            }
                            break;
                        case -621493005:
                            if (next.equals("resolutionHeight")) {
                                b = 21;
                            }
                            break;
                        case -614336461:
                            if (next.equals("bitrateAdaption")) {
                                b = 20;
                            }
                            break;
                        case -102270099:
                            if (next.equals("bitrate")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 98349:
                            if (next.equals("cdn")) {
                                b = 18;
                            }
                            break;
                        case 99743:
                            if (next.equals("drm")) {
                                b = 24;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 26;
                            }
                            break;
                        case 65521517:
                            if (next.equals("audioFormat")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 2;
                            }
                            break;
                        case 545057773:
                            if (next.equals("frameRate")) {
                                b = 23;
                            }
                            break;
                        case 568932414:
                            if (next.equals("dynamicRange")) {
                                b = 16;
                            }
                            break;
                        case 849380787:
                            if (next.equals("cdnOrigin")) {
                                b = 8;
                            }
                            break;
                        case 866553870:
                            if (next.equals("midasRegion")) {
                                b = 25;
                            }
                            break;
                        case 1548615363:
                            if (next.equals("contiguityType")) {
                                b = 4;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = 5;
                            }
                            break;
                        case 1748202501:
                            if (next.equals("encodingVersion")) {
                                b = 19;
                            }
                            break;
                        case 1934845701:
                            if (next.equals("subtitleRepresentation")) {
                                b = 1;
                            }
                            break;
                        case 1959593218:
                            if (next.equals("livePlaybackStreamId")) {
                                b = 0;
                            }
                            break;
                        case 1983886349:
                            if (next.equals("dynamicAdInsertion")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 2127018813:
                            if (next.equals("fragmentRepresentation")) {
                                b = 3;
                            }
                            break;
                    }
                    str = null;
                    str23 = null;
                    str22 = null;
                    str21 = null;
                    str20 = null;
                    num3 = null;
                    num2 = null;
                    str19 = null;
                    str18 = null;
                    str17 = null;
                    str16 = null;
                    str15 = null;
                    str14 = null;
                    str13 = null;
                    l = null;
                    num = null;
                    str12 = null;
                    str11 = null;
                    str10 = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.livePlaybackStreamId = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mSubtitleRepresentationParser.parse(jsonNode2);
                        }
                        builder.subtitleRepresentation = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mFragmentRepresentationParser.parse(jsonNode2);
                        }
                        builder.fragmentRepresentation = str4;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mContiguityTypeParser.parse(jsonNode2);
                        }
                        builder.contiguityType = str5;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str6;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.origin = str7;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.startTime = str8;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cdnOrigin = str9;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mStreamingTechnologyParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = str10;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str11 = this.mCompressionStandardParser.parse(jsonNode2);
                        }
                        builder.compressionStandard = str11;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str12 = this.mAudioFormatParser.parse(jsonNode2);
                        }
                        builder.audioFormat = str12;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.bitrate = num;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.duration = l;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            str13 = this.mDynamicAdInsertionTypeParser.parse(jsonNode2);
                        }
                        builder.dynamicAdInsertion = str13;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            str14 = this.mLookbackWindowParser.parse(jsonNode2);
                        }
                        builder.lookbackWindow = str14;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            str15 = this.mDynamicRangeParser.parse(jsonNode2);
                        }
                        builder.dynamicRange = str15;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            str16 = this.mSegmentListRepresentationParser.parse(jsonNode2);
                        }
                        builder.segmentListRepresentation = str16;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            str17 = this.mCdnParser.parse(jsonNode2);
                        }
                        builder.cdn = str17;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            str18 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.encodingVersion = str18;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            str19 = this.mBitrateAdaptionParser.parse(jsonNode2);
                        }
                        builder.bitrateAdaption = str19;
                        continue;
                    case 21:
                        if (!jsonNode2.isNull()) {
                            num2 = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.resolutionHeight = num2;
                        continue;
                    case 22:
                        if (!jsonNode2.isNull()) {
                            num3 = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.resolutionWidth = num3;
                        continue;
                    case 23:
                        if (!jsonNode2.isNull()) {
                            str20 = this.mFrameRateParser.parse(jsonNode2);
                        }
                        builder.frameRate = str20;
                        continue;
                    case 24:
                        if (!jsonNode2.isNull()) {
                            str21 = this.mDrmParser.parse(jsonNode2);
                        }
                        builder.drm = str21;
                        continue;
                    case 25:
                        if (!jsonNode2.isNull()) {
                            str22 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.midasRegion = str22;
                        continue;
                    case 26:
                        if (!jsonNode2.isNull()) {
                            str23 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.url = str23;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ManifestUrl parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 7;
                                    }
                                    break;
                                case -1992012396:
                                    if (currentName.equals("duration")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -1957882301:
                                    if (currentName.equals("compressionStandard")) {
                                        b = 10;
                                    }
                                    break;
                                case -1945743238:
                                    if (currentName.equals("resolutionWidth")) {
                                        b = 22;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 9;
                                    }
                                    break;
                                case -1182696034:
                                    if (currentName.equals("segmentListRepresentation")) {
                                        b = 17;
                                    }
                                    break;
                                case -1008619738:
                                    if (currentName.equals("origin")) {
                                        b = 6;
                                    }
                                    break;
                                case -624324714:
                                    if (currentName.equals("lookbackWindow")) {
                                        b = 15;
                                    }
                                    break;
                                case -621493005:
                                    if (currentName.equals("resolutionHeight")) {
                                        b = 21;
                                    }
                                    break;
                                case -614336461:
                                    if (currentName.equals("bitrateAdaption")) {
                                        b = 20;
                                    }
                                    break;
                                case -102270099:
                                    if (currentName.equals("bitrate")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 98349:
                                    if (currentName.equals("cdn")) {
                                        b = 18;
                                    }
                                    break;
                                case 99743:
                                    if (currentName.equals("drm")) {
                                        b = 24;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 26;
                                    }
                                    break;
                                case 65521517:
                                    if (currentName.equals("audioFormat")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 2;
                                    }
                                    break;
                                case 545057773:
                                    if (currentName.equals("frameRate")) {
                                        b = 23;
                                    }
                                    break;
                                case 568932414:
                                    if (currentName.equals("dynamicRange")) {
                                        b = 16;
                                    }
                                    break;
                                case 849380787:
                                    if (currentName.equals("cdnOrigin")) {
                                        b = 8;
                                    }
                                    break;
                                case 866553870:
                                    if (currentName.equals("midasRegion")) {
                                        b = 25;
                                    }
                                    break;
                                case 1548615363:
                                    if (currentName.equals("contiguityType")) {
                                        b = 4;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = 5;
                                    }
                                    break;
                                case 1748202501:
                                    if (currentName.equals("encodingVersion")) {
                                        b = 19;
                                    }
                                    break;
                                case 1934845701:
                                    if (currentName.equals("subtitleRepresentation")) {
                                        b = 1;
                                    }
                                    break;
                                case 1959593218:
                                    if (currentName.equals("livePlaybackStreamId")) {
                                        b = 0;
                                    }
                                    break;
                                case 1983886349:
                                    if (currentName.equals("dynamicAdInsertion")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 2127018813:
                                    if (currentName.equals("fragmentRepresentation")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            Integer num = null;
                            Integer num2 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            String str9 = null;
                            String str10 = null;
                            String str11 = null;
                            String str12 = null;
                            Long l = null;
                            Integer num3 = null;
                            String str13 = null;
                            String str14 = null;
                            String str15 = null;
                            String str16 = null;
                            String str17 = null;
                            String str18 = null;
                            String str19 = null;
                            String str20 = null;
                            String str21 = null;
                            String str22 = null;
                            String str23 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.livePlaybackStreamId = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str23 = this.mSubtitleRepresentationParser.parse(jsonParser);
                                    }
                                    builder.subtitleRepresentation = str23;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str22 = this.mVideoQualityParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = str22;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str21 = this.mFragmentRepresentationParser.parse(jsonParser);
                                    }
                                    builder.fragmentRepresentation = str21;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str20 = this.mContiguityTypeParser.parse(jsonParser);
                                    }
                                    builder.contiguityType = str20;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str19 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str19;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str18 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.origin = str18;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str17 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.startTime = str17;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str16 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.cdnOrigin = str16;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str15 = this.mStreamingTechnologyParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnology = str15;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str14 = this.mCompressionStandardParser.parse(jsonParser);
                                    }
                                    builder.compressionStandard = str14;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str13 = this.mAudioFormatParser.parse(jsonParser);
                                    }
                                    builder.audioFormat = str13;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num3 = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.bitrate = num3;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mLongParser.parse(jsonParser);
                                    }
                                    builder.duration = l;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str12 = this.mDynamicAdInsertionTypeParser.parse(jsonParser);
                                    }
                                    builder.dynamicAdInsertion = str12;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str11 = this.mLookbackWindowParser.parse(jsonParser);
                                    }
                                    builder.lookbackWindow = str11;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mDynamicRangeParser.parse(jsonParser);
                                    }
                                    builder.dynamicRange = str10;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mSegmentListRepresentationParser.parse(jsonParser);
                                    }
                                    builder.segmentListRepresentation = str9;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mCdnParser.parse(jsonParser);
                                    }
                                    builder.cdn = str8;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.encodingVersion = str7;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mBitrateAdaptionParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaption = str6;
                                    continue;
                                case 21:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num2 = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.resolutionHeight = num2;
                                    continue;
                                case 22:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.resolutionWidth = num;
                                    continue;
                                case 23:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mFrameRateParser.parse(jsonParser);
                                    }
                                    builder.frameRate = str5;
                                    continue;
                                case 24:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mDrmParser.parse(jsonParser);
                                    }
                                    builder.drm = str4;
                                    continue;
                                case 25:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.midasRegion = str3;
                                    continue;
                                case 26:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.url = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
