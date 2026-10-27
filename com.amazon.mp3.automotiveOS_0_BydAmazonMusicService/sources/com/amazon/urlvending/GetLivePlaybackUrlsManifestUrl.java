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
public class GetLivePlaybackUrlsManifestUrl {
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

    private GetLivePlaybackUrlsManifestUrl(Builder builder) {
        this.drm = Optional.fromNullable(builder.drm);
        this.streamingTechnology = Optional.fromNullable(builder.streamingTechnology);
        this.encodingVersion = Optional.fromNullable(builder.encodingVersion);
        this.url = Optional.fromNullable(builder.url);
        this.segmentListRepresentation = Optional.fromNullable(builder.segmentListRepresentation);
        this.livePlaybackStreamId = Optional.fromNullable(builder.livePlaybackStreamId);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.dynamicAdInsertion = Optional.fromNullable(builder.dynamicAdInsertion);
        this.cdn = Optional.fromNullable(builder.cdn);
        this.fragmentRepresentation = Optional.fromNullable(builder.fragmentRepresentation);
        this.origin = Optional.fromNullable(builder.origin);
        this.resolutionWidth = Optional.fromNullable(builder.resolutionWidth);
        this.frameRate = Optional.fromNullable(builder.frameRate);
        this.cdnOrigin = Optional.fromNullable(builder.cdnOrigin);
        this.compressionStandard = Optional.fromNullable(builder.compressionStandard);
        this.lookbackWindow = Optional.fromNullable(builder.lookbackWindow);
        this.audioFormat = Optional.fromNullable(builder.audioFormat);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.bitrate = Optional.fromNullable(builder.bitrate);
        this.midasRegion = Optional.fromNullable(builder.midasRegion);
        this.duration = Optional.fromNullable(builder.duration);
        this.subtitleRepresentation = Optional.fromNullable(builder.subtitleRepresentation);
        this.dynamicRange = Optional.fromNullable(builder.dynamicRange);
        this.contiguityType = Optional.fromNullable(builder.contiguityType);
        this.bitrateAdaption = Optional.fromNullable(builder.bitrateAdaption);
        this.resolutionHeight = Optional.fromNullable(builder.resolutionHeight);
        this.startTime = Optional.fromNullable(builder.startTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsManifestUrl)) {
            return false;
        }
        GetLivePlaybackUrlsManifestUrl getLivePlaybackUrlsManifestUrl = (GetLivePlaybackUrlsManifestUrl) obj;
        return Objects.equal(this.drm, getLivePlaybackUrlsManifestUrl.drm) && Objects.equal(this.streamingTechnology, getLivePlaybackUrlsManifestUrl.streamingTechnology) && Objects.equal(this.encodingVersion, getLivePlaybackUrlsManifestUrl.encodingVersion) && Objects.equal(this.url, getLivePlaybackUrlsManifestUrl.url) && Objects.equal(this.segmentListRepresentation, getLivePlaybackUrlsManifestUrl.segmentListRepresentation) && Objects.equal(this.livePlaybackStreamId, getLivePlaybackUrlsManifestUrl.livePlaybackStreamId) && Objects.equal(this.videoQuality, getLivePlaybackUrlsManifestUrl.videoQuality) && Objects.equal(this.dynamicAdInsertion, getLivePlaybackUrlsManifestUrl.dynamicAdInsertion) && Objects.equal(this.cdn, getLivePlaybackUrlsManifestUrl.cdn) && Objects.equal(this.fragmentRepresentation, getLivePlaybackUrlsManifestUrl.fragmentRepresentation) && Objects.equal(this.origin, getLivePlaybackUrlsManifestUrl.origin) && Objects.equal(this.resolutionWidth, getLivePlaybackUrlsManifestUrl.resolutionWidth) && Objects.equal(this.frameRate, getLivePlaybackUrlsManifestUrl.frameRate) && Objects.equal(this.cdnOrigin, getLivePlaybackUrlsManifestUrl.cdnOrigin) && Objects.equal(this.compressionStandard, getLivePlaybackUrlsManifestUrl.compressionStandard) && Objects.equal(this.lookbackWindow, getLivePlaybackUrlsManifestUrl.lookbackWindow) && Objects.equal(this.audioFormat, getLivePlaybackUrlsManifestUrl.audioFormat) && Objects.equal(this.audioTrackId, getLivePlaybackUrlsManifestUrl.audioTrackId) && Objects.equal(this.bitrate, getLivePlaybackUrlsManifestUrl.bitrate) && Objects.equal(this.midasRegion, getLivePlaybackUrlsManifestUrl.midasRegion) && Objects.equal(this.duration, getLivePlaybackUrlsManifestUrl.duration) && Objects.equal(this.subtitleRepresentation, getLivePlaybackUrlsManifestUrl.subtitleRepresentation) && Objects.equal(this.dynamicRange, getLivePlaybackUrlsManifestUrl.dynamicRange) && Objects.equal(this.contiguityType, getLivePlaybackUrlsManifestUrl.contiguityType) && Objects.equal(this.bitrateAdaption, getLivePlaybackUrlsManifestUrl.bitrateAdaption) && Objects.equal(this.resolutionHeight, getLivePlaybackUrlsManifestUrl.resolutionHeight) && Objects.equal(this.startTime, getLivePlaybackUrlsManifestUrl.startTime);
    }

    public int hashCode() {
        return Objects.hashCode(this.drm, this.streamingTechnology, this.encodingVersion, this.url, this.segmentListRepresentation, this.livePlaybackStreamId, this.videoQuality, this.dynamicAdInsertion, this.cdn, this.fragmentRepresentation, this.origin, this.resolutionWidth, this.frameRate, this.cdnOrigin, this.compressionStandard, this.lookbackWindow, this.audioFormat, this.audioTrackId, this.bitrate, this.midasRegion, this.duration, this.subtitleRepresentation, this.dynamicRange, this.contiguityType, this.bitrateAdaption, this.resolutionHeight, this.startTime);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("drm", this.drm).add("streamingTechnology", this.streamingTechnology).add("encodingVersion", this.encodingVersion).add(ImagesContract.URL, this.url).add("segmentListRepresentation", this.segmentListRepresentation).add("livePlaybackStreamId", this.livePlaybackStreamId).add("videoQuality", this.videoQuality).add("dynamicAdInsertion", this.dynamicAdInsertion).add("cdn", this.cdn).add("fragmentRepresentation", this.fragmentRepresentation).add("origin", this.origin).add("resolutionWidth", this.resolutionWidth).add("frameRate", this.frameRate).add("cdnOrigin", this.cdnOrigin).add("compressionStandard", this.compressionStandard).add("lookbackWindow", this.lookbackWindow).add("audioFormat", this.audioFormat).add("audioTrackId", this.audioTrackId).add("bitrate", this.bitrate).add("midasRegion", this.midasRegion).add("duration", this.duration).add("subtitleRepresentation", this.subtitleRepresentation).add("dynamicRange", this.dynamicRange).add("contiguityType", this.contiguityType).add("bitrateAdaption", this.bitrateAdaption).add("resolutionHeight", this.resolutionHeight).add("startTime", this.startTime).toString();
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

        public GetLivePlaybackUrlsManifestUrl build() {
            return new GetLivePlaybackUrlsManifestUrl(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsManifestUrl> {
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final SimpleParsers.LongParser mLongParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsManifestUrl parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsManifestUrl:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsManifestUrl parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsManifestUrl:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsManifestUrl parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
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
            Integer num;
            String str12;
            String str13;
            String str14;
            String str15;
            String str16;
            String str17;
            Integer num2;
            String str18;
            Long l;
            String str19;
            String str20;
            String str21;
            String str22;
            Integer num3;
            String str23;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsManifestUrl");
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
                                b = 26;
                            }
                            break;
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = 20;
                            }
                            break;
                        case -1957882301:
                            if (next.equals("compressionStandard")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case -1945743238:
                            if (next.equals("resolutionWidth")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 1;
                            }
                            break;
                        case -1182696034:
                            if (next.equals("segmentListRepresentation")) {
                                b = 4;
                            }
                            break;
                        case -1008619738:
                            if (next.equals("origin")) {
                                b = 10;
                            }
                            break;
                        case -624324714:
                            if (next.equals("lookbackWindow")) {
                                b = 15;
                            }
                            break;
                        case -621493005:
                            if (next.equals("resolutionHeight")) {
                                b = 25;
                            }
                            break;
                        case -614336461:
                            if (next.equals("bitrateAdaption")) {
                                b = 24;
                            }
                            break;
                        case -102270099:
                            if (next.equals("bitrate")) {
                                b = 18;
                            }
                            break;
                        case 98349:
                            if (next.equals("cdn")) {
                                b = 8;
                            }
                            break;
                        case 99743:
                            if (next.equals("drm")) {
                                b = 0;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 3;
                            }
                            break;
                        case 65521517:
                            if (next.equals("audioFormat")) {
                                b = 16;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 6;
                            }
                            break;
                        case 545057773:
                            if (next.equals("frameRate")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 568932414:
                            if (next.equals("dynamicRange")) {
                                b = 22;
                            }
                            break;
                        case 849380787:
                            if (next.equals("cdnOrigin")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 866553870:
                            if (next.equals("midasRegion")) {
                                b = 19;
                            }
                            break;
                        case 1548615363:
                            if (next.equals("contiguityType")) {
                                b = 23;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = 17;
                            }
                            break;
                        case 1748202501:
                            if (next.equals("encodingVersion")) {
                                b = 2;
                            }
                            break;
                        case 1934845701:
                            if (next.equals("subtitleRepresentation")) {
                                b = 21;
                            }
                            break;
                        case 1959593218:
                            if (next.equals("livePlaybackStreamId")) {
                                b = 5;
                            }
                            break;
                        case 1983886349:
                            if (next.equals("dynamicAdInsertion")) {
                                b = 7;
                            }
                            break;
                        case 2127018813:
                            if (next.equals("fragmentRepresentation")) {
                                b = 9;
                            }
                            break;
                    }
                    str = null;
                    str23 = null;
                    num3 = null;
                    str22 = null;
                    str21 = null;
                    str20 = null;
                    str19 = null;
                    l = null;
                    str18 = null;
                    num2 = null;
                    str17 = null;
                    str16 = null;
                    str15 = null;
                    str14 = null;
                    str13 = null;
                    str12 = null;
                    num = null;
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
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.drm = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.encodingVersion = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.url = str4;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.segmentListRepresentation = str5;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.livePlaybackStreamId = str6;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.videoQuality = str7;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.dynamicAdInsertion = str8;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cdn = str9;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.fragmentRepresentation = str10;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str11 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.origin = str11;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.resolutionWidth = num;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str12 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.frameRate = str12;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            str13 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cdnOrigin = str13;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            str14 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.compressionStandard = str14;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            str15 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.lookbackWindow = str15;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            str16 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.audioFormat = str16;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            str17 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str17;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            num2 = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.bitrate = num2;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            str18 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.midasRegion = str18;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.duration = l;
                        continue;
                    case 21:
                        if (!jsonNode2.isNull()) {
                            str19 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtitleRepresentation = str19;
                        continue;
                    case 22:
                        if (!jsonNode2.isNull()) {
                            str20 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.dynamicRange = str20;
                        continue;
                    case 23:
                        if (!jsonNode2.isNull()) {
                            str21 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.contiguityType = str21;
                        continue;
                    case 24:
                        if (!jsonNode2.isNull()) {
                            str22 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.bitrateAdaption = str22;
                        continue;
                    case 25:
                        if (!jsonNode2.isNull()) {
                            num3 = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.resolutionHeight = num3;
                        continue;
                    case 26:
                        if (!jsonNode2.isNull()) {
                            str23 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.startTime = str23;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsManifestUrl parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 26;
                                    }
                                    break;
                                case -1992012396:
                                    if (currentName.equals("duration")) {
                                        b = 20;
                                    }
                                    break;
                                case -1957882301:
                                    if (currentName.equals("compressionStandard")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case -1945743238:
                                    if (currentName.equals("resolutionWidth")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 1;
                                    }
                                    break;
                                case -1182696034:
                                    if (currentName.equals("segmentListRepresentation")) {
                                        b = 4;
                                    }
                                    break;
                                case -1008619738:
                                    if (currentName.equals("origin")) {
                                        b = 10;
                                    }
                                    break;
                                case -624324714:
                                    if (currentName.equals("lookbackWindow")) {
                                        b = 15;
                                    }
                                    break;
                                case -621493005:
                                    if (currentName.equals("resolutionHeight")) {
                                        b = 25;
                                    }
                                    break;
                                case -614336461:
                                    if (currentName.equals("bitrateAdaption")) {
                                        b = 24;
                                    }
                                    break;
                                case -102270099:
                                    if (currentName.equals("bitrate")) {
                                        b = 18;
                                    }
                                    break;
                                case 98349:
                                    if (currentName.equals("cdn")) {
                                        b = 8;
                                    }
                                    break;
                                case 99743:
                                    if (currentName.equals("drm")) {
                                        b = 0;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 3;
                                    }
                                    break;
                                case 65521517:
                                    if (currentName.equals("audioFormat")) {
                                        b = 16;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 6;
                                    }
                                    break;
                                case 545057773:
                                    if (currentName.equals("frameRate")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 568932414:
                                    if (currentName.equals("dynamicRange")) {
                                        b = 22;
                                    }
                                    break;
                                case 849380787:
                                    if (currentName.equals("cdnOrigin")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 866553870:
                                    if (currentName.equals("midasRegion")) {
                                        b = 19;
                                    }
                                    break;
                                case 1548615363:
                                    if (currentName.equals("contiguityType")) {
                                        b = 23;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = 17;
                                    }
                                    break;
                                case 1748202501:
                                    if (currentName.equals("encodingVersion")) {
                                        b = 2;
                                    }
                                    break;
                                case 1934845701:
                                    if (currentName.equals("subtitleRepresentation")) {
                                        b = 21;
                                    }
                                    break;
                                case 1959593218:
                                    if (currentName.equals("livePlaybackStreamId")) {
                                        b = 5;
                                    }
                                    break;
                                case 1983886349:
                                    if (currentName.equals("dynamicAdInsertion")) {
                                        b = 7;
                                    }
                                    break;
                                case 2127018813:
                                    if (currentName.equals("fragmentRepresentation")) {
                                        b = 9;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Integer num = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            String str6 = null;
                            Long l = null;
                            String str7 = null;
                            Integer num2 = null;
                            String str8 = null;
                            String str9 = null;
                            String str10 = null;
                            String str11 = null;
                            String str12 = null;
                            String str13 = null;
                            Integer num3 = null;
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
                                    builder.drm = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str23 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnology = str23;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str22 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.encodingVersion = str22;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str21 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.url = str21;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str20 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.segmentListRepresentation = str20;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str19 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.livePlaybackStreamId = str19;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str18 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = str18;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str17 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.dynamicAdInsertion = str17;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str16 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.cdn = str16;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str15 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.fragmentRepresentation = str15;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str14 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.origin = str14;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num3 = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.resolutionWidth = num3;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str13 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.frameRate = str13;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str12 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.cdnOrigin = str12;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str11 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.compressionStandard = str11;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.lookbackWindow = str10;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.audioFormat = str9;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str8;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num2 = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.bitrate = num2;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.midasRegion = str7;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mLongParser.parse(jsonParser);
                                    }
                                    builder.duration = l;
                                    continue;
                                case 21:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtitleRepresentation = str6;
                                    continue;
                                case 22:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.dynamicRange = str5;
                                    continue;
                                case 23:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.contiguityType = str4;
                                    continue;
                                case 24:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaption = str3;
                                    continue;
                                case 25:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.resolutionHeight = num;
                                    continue;
                                case 26:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.startTime = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsManifestUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
