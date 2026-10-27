package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.urlvending.types.BitrateAdaptation;
import com.amazon.urlvending.types.DRMKeyScheme;
import com.amazon.urlvending.types.Drm;
import com.amazon.urlvending.types.Encode;
import com.amazon.urlvending.types.FrameRate;
import com.amazon.urlvending.types.HdrFormat;
import com.amazon.urlvending.types.ProtocolType;
import com.amazon.urlvending.types.StreamingTechnology;
import com.amazon.urlvending.types.VideoQuality;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes5.dex */
public class GetLivePlaybackUrlsDeviceInfo {
    public final Optional<String> advertisingId;
    public final Optional<String> assetBundleQuality;
    public final Optional<ImmutableList<BitrateAdaptation>> bitrateAdaptations;
    public final Optional<String> clientVersion;
    public final Optional<String> deviceCapabilityInfo;
    public final Optional<String> deviceCategory;
    public final Optional<ImmutableList<Encode>> deviceCompressionStandards;
    public final Optional<String> deviceId;
    public final Optional<String> devicePlatform;
    public final Optional<String> deviceTypeId;
    public final Optional<Drm> drmSupported;
    public final Optional<String> fragmentRepresentation;
    public final Optional<ImmutableList<FrameRate>> frameRates;
    public final Optional<ImmutableList<HdrFormat>> hdrFormatsSupported;
    public final Optional<String> operatingSystem;
    public final Optional<Boolean> optOutAdTracking;
    public final Optional<ProtocolType> protocol;
    public final Optional<ImmutableList<StreamingTechnology>> streamingTechnologies;
    public final Optional<DRMKeyScheme> supportedDrmKeyScheme;
    public final Optional<String> userAgent;
    public final Optional<VideoQuality> videoQualitySupported;

    private GetLivePlaybackUrlsDeviceInfo(Builder builder) {
        this.deviceCompressionStandards = Optional.fromNullable(builder.deviceCompressionStandards);
        this.protocol = Optional.fromNullable(builder.protocol);
        this.frameRates = Optional.fromNullable(builder.frameRates);
        this.fragmentRepresentation = Optional.fromNullable(builder.fragmentRepresentation);
        this.hdrFormatsSupported = Optional.fromNullable(builder.hdrFormatsSupported);
        this.clientVersion = Optional.fromNullable(builder.clientVersion);
        this.assetBundleQuality = Optional.fromNullable(builder.assetBundleQuality);
        this.videoQualitySupported = Optional.fromNullable(builder.videoQualitySupported);
        this.deviceTypeId = Optional.fromNullable(builder.deviceTypeId);
        this.drmSupported = Optional.fromNullable(builder.drmSupported);
        this.deviceCapabilityInfo = Optional.fromNullable(builder.deviceCapabilityInfo);
        this.devicePlatform = Optional.fromNullable(builder.devicePlatform);
        this.supportedDrmKeyScheme = Optional.fromNullable(builder.supportedDrmKeyScheme);
        this.deviceCategory = Optional.fromNullable(builder.deviceCategory);
        this.deviceId = Optional.fromNullable(builder.deviceId);
        this.advertisingId = Optional.fromNullable(builder.advertisingId);
        this.operatingSystem = Optional.fromNullable(builder.operatingSystem);
        this.userAgent = Optional.fromNullable(builder.userAgent);
        this.optOutAdTracking = Optional.fromNullable(builder.optOutAdTracking);
        this.streamingTechnologies = Optional.fromNullable(builder.streamingTechnologies);
        this.bitrateAdaptations = Optional.fromNullable(builder.bitrateAdaptations);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsDeviceInfo)) {
            return false;
        }
        GetLivePlaybackUrlsDeviceInfo getLivePlaybackUrlsDeviceInfo = (GetLivePlaybackUrlsDeviceInfo) obj;
        return Objects.equal(this.deviceCompressionStandards, getLivePlaybackUrlsDeviceInfo.deviceCompressionStandards) && Objects.equal(this.protocol, getLivePlaybackUrlsDeviceInfo.protocol) && Objects.equal(this.frameRates, getLivePlaybackUrlsDeviceInfo.frameRates) && Objects.equal(this.fragmentRepresentation, getLivePlaybackUrlsDeviceInfo.fragmentRepresentation) && Objects.equal(this.hdrFormatsSupported, getLivePlaybackUrlsDeviceInfo.hdrFormatsSupported) && Objects.equal(this.clientVersion, getLivePlaybackUrlsDeviceInfo.clientVersion) && Objects.equal(this.assetBundleQuality, getLivePlaybackUrlsDeviceInfo.assetBundleQuality) && Objects.equal(this.videoQualitySupported, getLivePlaybackUrlsDeviceInfo.videoQualitySupported) && Objects.equal(this.deviceTypeId, getLivePlaybackUrlsDeviceInfo.deviceTypeId) && Objects.equal(this.drmSupported, getLivePlaybackUrlsDeviceInfo.drmSupported) && Objects.equal(this.deviceCapabilityInfo, getLivePlaybackUrlsDeviceInfo.deviceCapabilityInfo) && Objects.equal(this.devicePlatform, getLivePlaybackUrlsDeviceInfo.devicePlatform) && Objects.equal(this.supportedDrmKeyScheme, getLivePlaybackUrlsDeviceInfo.supportedDrmKeyScheme) && Objects.equal(this.deviceCategory, getLivePlaybackUrlsDeviceInfo.deviceCategory) && Objects.equal(this.deviceId, getLivePlaybackUrlsDeviceInfo.deviceId) && Objects.equal(this.advertisingId, getLivePlaybackUrlsDeviceInfo.advertisingId) && Objects.equal(this.operatingSystem, getLivePlaybackUrlsDeviceInfo.operatingSystem) && Objects.equal(this.userAgent, getLivePlaybackUrlsDeviceInfo.userAgent) && Objects.equal(this.optOutAdTracking, getLivePlaybackUrlsDeviceInfo.optOutAdTracking) && Objects.equal(this.streamingTechnologies, getLivePlaybackUrlsDeviceInfo.streamingTechnologies) && Objects.equal(this.bitrateAdaptations, getLivePlaybackUrlsDeviceInfo.bitrateAdaptations);
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceCompressionStandards, this.protocol, this.frameRates, this.fragmentRepresentation, this.hdrFormatsSupported, this.clientVersion, this.assetBundleQuality, this.videoQualitySupported, this.deviceTypeId, this.drmSupported, this.deviceCapabilityInfo, this.devicePlatform, this.supportedDrmKeyScheme, this.deviceCategory, this.deviceId, this.advertisingId, this.operatingSystem, this.userAgent, this.optOutAdTracking, this.streamingTechnologies, this.bitrateAdaptations);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("deviceCompressionStandards", this.deviceCompressionStandards).add("protocol", this.protocol).add("frameRates", this.frameRates).add("fragmentRepresentation", this.fragmentRepresentation).add("hdrFormatsSupported", this.hdrFormatsSupported).add("clientVersion", this.clientVersion).add("assetBundleQuality", this.assetBundleQuality).add("videoQualitySupported", this.videoQualitySupported).add("deviceTypeId", this.deviceTypeId).add("drmSupported", this.drmSupported).add("deviceCapabilityInfo", this.deviceCapabilityInfo).add("devicePlatform", this.devicePlatform).add("supportedDrmKeyScheme", this.supportedDrmKeyScheme).add("deviceCategory", this.deviceCategory).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("advertisingId", this.advertisingId).add("operatingSystem", this.operatingSystem).add("userAgent", this.userAgent).add("optOutAdTracking", this.optOutAdTracking).add("streamingTechnologies", this.streamingTechnologies).add("bitrateAdaptations", this.bitrateAdaptations).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String advertisingId;
        public String assetBundleQuality;
        public ImmutableList<BitrateAdaptation> bitrateAdaptations;
        public String clientVersion;
        public String deviceCapabilityInfo;
        public String deviceCategory;
        public ImmutableList<Encode> deviceCompressionStandards;
        public String deviceId;
        public String devicePlatform;
        public String deviceTypeId;
        public Drm drmSupported;
        public String fragmentRepresentation;
        public ImmutableList<FrameRate> frameRates;
        public ImmutableList<HdrFormat> hdrFormatsSupported;
        public String operatingSystem;
        public Boolean optOutAdTracking;
        public ProtocolType protocol;
        public ImmutableList<StreamingTechnology> streamingTechnologies;
        public DRMKeyScheme supportedDrmKeyScheme;
        public String userAgent;
        public VideoQuality videoQualitySupported;

        public GetLivePlaybackUrlsDeviceInfo build() {
            return new GetLivePlaybackUrlsDeviceInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsDeviceInfo> {
        private final ListParser<BitrateAdaptation> mBitrateAdaptationsParser;
        private final EnumParser<DRMKeyScheme> mDRMKeySchemeParser;
        private final EnumParser<Drm> mDrmSupportedParser;
        private final ListParser<Encode> mEncodesParser;
        private final ListParser<FrameRate> mFrameRatesParser;
        private final EnumParser<ProtocolType> mProtocolTypeParser;
        private final ListParser<StreamingTechnology> mStreamingTechnologiesParser;
        private final SimpleParsers.StringParser mStringParser;
        private final EnumParser<VideoQuality> mVideoQualitySupportedParser;
        private final SimpleParsers.BooleanParser mbooleanParser;
        private final ListParser<HdrFormat> mhdrFormatsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDrmSupportedParser = EnumParser.newParser(Drm.class);
            this.mEncodesParser = ListParser.newParser(EnumParser.newParser(Encode.class));
            this.mFrameRatesParser = ListParser.newParser(EnumParser.newParser(FrameRate.class));
            this.mhdrFormatsParser = ListParser.newParser(EnumParser.newParser(HdrFormat.class));
            this.mStreamingTechnologiesParser = ListParser.newParser(EnumParser.newParser(StreamingTechnology.class));
            this.mBitrateAdaptationsParser = ListParser.newParser(EnumParser.newParser(BitrateAdaptation.class));
            this.mbooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mProtocolTypeParser = EnumParser.newParser(ProtocolType.class);
            this.mVideoQualitySupportedParser = EnumParser.newParser(VideoQuality.class);
            this.mDRMKeySchemeParser = EnumParser.newParser(DRMKeyScheme.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsDeviceInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsDeviceInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsDeviceInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsDeviceInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsDeviceInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ImmutableList<Encode> immutableList;
            ProtocolType protocolType;
            ImmutableList<FrameRate> immutableList2;
            String str;
            ImmutableList<HdrFormat> immutableList3;
            String str2;
            String str3;
            VideoQuality videoQuality;
            String str4;
            Drm drm;
            String str5;
            String str6;
            DRMKeyScheme dRMKeyScheme;
            String str7;
            String str8;
            String str9;
            String str10;
            String str11;
            Boolean bool;
            ImmutableList<StreamingTechnology> immutableList4;
            ImmutableList<BitrateAdaptation> immutableList5;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsDeviceInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2049299412:
                            if (next.equals("streamingTechnologies")) {
                                b = 19;
                            }
                            break;
                        case -1650548792:
                            if (next.equals("hdrFormatsSupported")) {
                                b = 4;
                            }
                            break;
                        case -1176666416:
                            if (next.equals("operatingSystem")) {
                                b = 16;
                            }
                            break;
                        case -1066315757:
                            if (next.equals("bitrateAdaptations")) {
                                b = 20;
                            }
                            break;
                        case -989163880:
                            if (next.equals("protocol")) {
                                b = 1;
                            }
                            break;
                        case -926592561:
                            if (next.equals("drmSupported")) {
                                b = 9;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 8;
                            }
                            break;
                        case -555948790:
                            if (next.equals("videoQualitySupported")) {
                                b = 7;
                            }
                            break;
                        case -356528676:
                            if (next.equals("deviceCapabilityInfo")) {
                                b = 10;
                            }
                            break;
                        case -283078106:
                            if (next.equals("frameRates")) {
                                b = 2;
                            }
                            break;
                        case -263747212:
                            if (next.equals("deviceCategory")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -124344481:
                            if (next.equals("advertisingId")) {
                                b = 15;
                            }
                            break;
                        case 311430650:
                            if (next.equals("userAgent")) {
                                b = 17;
                            }
                            break;
                        case 771880589:
                            if (next.equals("clientVersion")) {
                                b = 5;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 1208634835:
                            if (next.equals("supportedDrmKeyScheme")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 1307785717:
                            if (next.equals("optOutAdTracking")) {
                                b = 18;
                            }
                            break;
                        case 1406696717:
                            if (next.equals("assetBundleQuality")) {
                                b = 6;
                            }
                            break;
                        case 1560425705:
                            if (next.equals("devicePlatform")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1596816902:
                            if (next.equals("deviceCompressionStandards")) {
                                b = 0;
                            }
                            break;
                        case 2127018813:
                            if (next.equals("fragmentRepresentation")) {
                                b = 3;
                            }
                            break;
                    }
                    immutableList = null;
                    immutableList5 = null;
                    immutableList4 = null;
                    bool = null;
                    str11 = null;
                    str10 = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    dRMKeyScheme = null;
                    str6 = null;
                    str5 = null;
                    drm = null;
                    str4 = null;
                    videoQuality = null;
                    str3 = null;
                    str2 = null;
                    immutableList3 = null;
                    str = null;
                    immutableList2 = null;
                    protocolType = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsDeviceInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mEncodesParser.parse(jsonNode2);
                        }
                        builder.deviceCompressionStandards = immutableList;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            protocolType = (ProtocolType) this.mProtocolTypeParser.parse(jsonNode2);
                        }
                        builder.protocol = protocolType;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mFrameRatesParser.parse(jsonNode2);
                        }
                        builder.frameRates = immutableList2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.fragmentRepresentation = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mhdrFormatsParser.parse(jsonNode2);
                        }
                        builder.hdrFormatsSupported = immutableList3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.clientVersion = str2;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetBundleQuality = str3;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualitySupportedParser.parse(jsonNode2);
                        }
                        builder.videoQualitySupported = videoQuality;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str4;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            drm = (Drm) this.mDrmSupportedParser.parse(jsonNode2);
                        }
                        builder.drmSupported = drm;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceCapabilityInfo = str5;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.devicePlatform = str6;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            dRMKeyScheme = (DRMKeyScheme) this.mDRMKeySchemeParser.parse(jsonNode2);
                        }
                        builder.supportedDrmKeyScheme = dRMKeyScheme;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceCategory = str7;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.deviceId = str8;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.advertisingId = str9;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.operatingSystem = str10;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            str11 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.userAgent = str11;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            bool = this.mbooleanParser.parse(jsonNode2);
                        }
                        builder.optOutAdTracking = bool;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            immutableList4 = this.mStreamingTechnologiesParser.parse(jsonNode2);
                        }
                        builder.streamingTechnologies = immutableList4;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            immutableList5 = this.mBitrateAdaptationsParser.parse(jsonNode2);
                        }
                        builder.bitrateAdaptations = immutableList5;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsDeviceInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsDeviceInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2049299412:
                                    if (currentName.equals("streamingTechnologies")) {
                                        b = 19;
                                    }
                                    break;
                                case -1650548792:
                                    if (currentName.equals("hdrFormatsSupported")) {
                                        b = 4;
                                    }
                                    break;
                                case -1176666416:
                                    if (currentName.equals("operatingSystem")) {
                                        b = 16;
                                    }
                                    break;
                                case -1066315757:
                                    if (currentName.equals("bitrateAdaptations")) {
                                        b = 20;
                                    }
                                    break;
                                case -989163880:
                                    if (currentName.equals("protocol")) {
                                        b = 1;
                                    }
                                    break;
                                case -926592561:
                                    if (currentName.equals("drmSupported")) {
                                        b = 9;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 8;
                                    }
                                    break;
                                case -555948790:
                                    if (currentName.equals("videoQualitySupported")) {
                                        b = 7;
                                    }
                                    break;
                                case -356528676:
                                    if (currentName.equals("deviceCapabilityInfo")) {
                                        b = 10;
                                    }
                                    break;
                                case -283078106:
                                    if (currentName.equals("frameRates")) {
                                        b = 2;
                                    }
                                    break;
                                case -263747212:
                                    if (currentName.equals("deviceCategory")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -124344481:
                                    if (currentName.equals("advertisingId")) {
                                        b = 15;
                                    }
                                    break;
                                case 311430650:
                                    if (currentName.equals("userAgent")) {
                                        b = 17;
                                    }
                                    break;
                                case 771880589:
                                    if (currentName.equals("clientVersion")) {
                                        b = 5;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 1208634835:
                                    if (currentName.equals("supportedDrmKeyScheme")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 1307785717:
                                    if (currentName.equals("optOutAdTracking")) {
                                        b = 18;
                                    }
                                    break;
                                case 1406696717:
                                    if (currentName.equals("assetBundleQuality")) {
                                        b = 6;
                                    }
                                    break;
                                case 1560425705:
                                    if (currentName.equals("devicePlatform")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1596816902:
                                    if (currentName.equals("deviceCompressionStandards")) {
                                        b = 0;
                                    }
                                    break;
                                case 2127018813:
                                    if (currentName.equals("fragmentRepresentation")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            ImmutableList<Encode> immutableList = null;
                            ImmutableList<BitrateAdaptation> immutableList2 = null;
                            ImmutableList<StreamingTechnology> immutableList3 = null;
                            Boolean bool = null;
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            DRMKeyScheme dRMKeyScheme = null;
                            String str6 = null;
                            String str7 = null;
                            Drm drm = null;
                            String str8 = null;
                            VideoQuality videoQuality = null;
                            String str9 = null;
                            String str10 = null;
                            ImmutableList<HdrFormat> immutableList4 = null;
                            String str11 = null;
                            ImmutableList<FrameRate> immutableList5 = null;
                            ProtocolType protocolType = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mEncodesParser.parse(jsonParser);
                                    }
                                    builder.deviceCompressionStandards = immutableList;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        protocolType = (ProtocolType) this.mProtocolTypeParser.parse(jsonParser);
                                    }
                                    builder.protocol = protocolType;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList5 = this.mFrameRatesParser.parse(jsonParser);
                                    }
                                    builder.frameRates = immutableList5;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str11 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.fragmentRepresentation = str11;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList4 = this.mhdrFormatsParser.parse(jsonParser);
                                    }
                                    builder.hdrFormatsSupported = immutableList4;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.clientVersion = str10;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetBundleQuality = str9;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mVideoQualitySupportedParser.parse(jsonParser);
                                    }
                                    builder.videoQualitySupported = videoQuality;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str8;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        drm = (Drm) this.mDrmSupportedParser.parse(jsonParser);
                                    }
                                    builder.drmSupported = drm;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceCapabilityInfo = str7;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.devicePlatform = str6;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        dRMKeyScheme = (DRMKeyScheme) this.mDRMKeySchemeParser.parse(jsonParser);
                                    }
                                    builder.supportedDrmKeyScheme = dRMKeyScheme;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceCategory = str5;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str4;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.advertisingId = str3;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.operatingSystem = str2;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.userAgent = str;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mbooleanParser.parse(jsonParser);
                                    }
                                    builder.optOutAdTracking = bool;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList3 = this.mStreamingTechnologiesParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnologies = immutableList3;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mBitrateAdaptationsParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaptations = immutableList2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsDeviceInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsDeviceInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
