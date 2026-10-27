package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.urlvending.types.AudioFormat;
import com.amazon.urlvending.types.BitrateAdaptation;
import com.amazon.urlvending.types.DRMKeyScheme;
import com.amazon.urlvending.types.Drm;
import com.amazon.urlvending.types.Encode;
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
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes5.dex */
public class Device {
    public final Optional<String> advertisingId;

    @Deprecated
    public final Optional<AudioFormat> audioFormatSupported;

    @Deprecated
    public final Optional<BitrateAdaptation> bitrateAdaptationPrefered;

    @Deprecated
    public final Optional<ImmutableList<BitrateAdaptation>> bitrateAdaptations;
    public final Optional<String> clientVersion;

    @Deprecated
    public final Optional<Encode> codecPrefered;
    public final Optional<String> deviceCategory;
    public final Optional<String> deviceId;
    public final Optional<String> devicePlatform;
    public final Optional<String> deviceTypeId;

    @Deprecated
    public final Optional<Drm> drmSupported;

    @Deprecated
    public final Optional<HdrFormat> hdrFormatSupported;

    @Deprecated
    public final Optional<ImmutableList<HdrFormat>> hdrFormats;
    public final boolean legacySubtitleDevice;
    public final Optional<String> operatingSystem;
    public final boolean optOutAdTracking;
    public final Optional<ProtocolType> protocol;
    public final Optional<ImmutableMap<String, ProtocolType>> protocolByResource;

    @Deprecated
    public final Optional<ImmutableList<StreamingTechnology>> streamingTechnologies;
    public final Optional<DRMKeyScheme> supportedDRMKeyScheme;
    public final Optional<String> userAgent;

    @Deprecated
    public final Optional<VideoQuality> videoQualitySupported;

    private Device(Builder builder) {
        this.bitrateAdaptationPrefered = Optional.fromNullable(builder.bitrateAdaptationPrefered);
        this.drmSupported = Optional.fromNullable(builder.drmSupported);
        this.protocol = Optional.fromNullable(builder.protocol);
        this.hdrFormats = Optional.fromNullable(builder.hdrFormats);
        this.hdrFormatSupported = Optional.fromNullable(builder.hdrFormatSupported);
        this.audioFormatSupported = Optional.fromNullable(builder.audioFormatSupported);
        this.clientVersion = Optional.fromNullable(builder.clientVersion);
        this.legacySubtitleDevice = builder.legacySubtitleDevice;
        this.videoQualitySupported = Optional.fromNullable(builder.videoQualitySupported);
        this.deviceTypeId = Optional.fromNullable(builder.deviceTypeId);
        this.deviceId = Optional.fromNullable(builder.deviceId);
        this.devicePlatform = Optional.fromNullable(builder.devicePlatform);
        this.protocolByResource = Optional.fromNullable(builder.protocolByResource);
        this.deviceCategory = Optional.fromNullable(builder.deviceCategory);
        this.advertisingId = Optional.fromNullable(builder.advertisingId);
        this.codecPrefered = Optional.fromNullable(builder.codecPrefered);
        this.bitrateAdaptations = Optional.fromNullable(builder.bitrateAdaptations);
        this.operatingSystem = Optional.fromNullable(builder.operatingSystem);
        this.optOutAdTracking = builder.optOutAdTracking;
        this.userAgent = Optional.fromNullable(builder.userAgent);
        this.supportedDRMKeyScheme = Optional.fromNullable(builder.supportedDRMKeyScheme);
        this.streamingTechnologies = Optional.fromNullable(builder.streamingTechnologies);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.bitrateAdaptationPrefered, device.bitrateAdaptationPrefered) && Objects.equal(this.drmSupported, device.drmSupported) && Objects.equal(this.protocol, device.protocol) && Objects.equal(this.hdrFormats, device.hdrFormats) && Objects.equal(this.hdrFormatSupported, device.hdrFormatSupported) && Objects.equal(this.audioFormatSupported, device.audioFormatSupported) && Objects.equal(this.clientVersion, device.clientVersion) && Objects.equal(Boolean.valueOf(this.legacySubtitleDevice), Boolean.valueOf(device.legacySubtitleDevice)) && Objects.equal(this.videoQualitySupported, device.videoQualitySupported) && Objects.equal(this.deviceTypeId, device.deviceTypeId) && Objects.equal(this.deviceId, device.deviceId) && Objects.equal(this.devicePlatform, device.devicePlatform) && Objects.equal(this.protocolByResource, device.protocolByResource) && Objects.equal(this.deviceCategory, device.deviceCategory) && Objects.equal(this.advertisingId, device.advertisingId) && Objects.equal(this.codecPrefered, device.codecPrefered) && Objects.equal(this.bitrateAdaptations, device.bitrateAdaptations) && Objects.equal(this.operatingSystem, device.operatingSystem) && Objects.equal(Boolean.valueOf(this.optOutAdTracking), Boolean.valueOf(device.optOutAdTracking)) && Objects.equal(this.userAgent, device.userAgent) && Objects.equal(this.supportedDRMKeyScheme, device.supportedDRMKeyScheme) && Objects.equal(this.streamingTechnologies, device.streamingTechnologies);
    }

    public int hashCode() {
        return Objects.hashCode(this.bitrateAdaptationPrefered, this.drmSupported, this.protocol, this.hdrFormats, this.hdrFormatSupported, this.audioFormatSupported, this.clientVersion, Boolean.valueOf(this.legacySubtitleDevice), this.videoQualitySupported, this.deviceTypeId, this.deviceId, this.devicePlatform, this.protocolByResource, this.deviceCategory, this.advertisingId, this.codecPrefered, this.bitrateAdaptations, this.operatingSystem, Boolean.valueOf(this.optOutAdTracking), this.userAgent, this.supportedDRMKeyScheme, this.streamingTechnologies);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("bitrateAdaptationPrefered", this.bitrateAdaptationPrefered).add("drmSupported", this.drmSupported).add("protocol", this.protocol).add("hdrFormats", this.hdrFormats).add("hdrFormatSupported", this.hdrFormatSupported).add("audioFormatSupported", this.audioFormatSupported).add("clientVersion", this.clientVersion).add("legacySubtitleDevice", this.legacySubtitleDevice).add("videoQualitySupported", this.videoQualitySupported).add("deviceTypeId", this.deviceTypeId).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("devicePlatform", this.devicePlatform).add("protocolByResource", this.protocolByResource).add("deviceCategory", this.deviceCategory).add("advertisingId", this.advertisingId).add("codecPrefered", this.codecPrefered).add("bitrateAdaptations", this.bitrateAdaptations).add("operatingSystem", this.operatingSystem).add("optOutAdTracking", this.optOutAdTracking).add("userAgent", this.userAgent).add("supportedDRMKeyScheme", this.supportedDRMKeyScheme).add("streamingTechnologies", this.streamingTechnologies).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String advertisingId;

        @Deprecated
        public AudioFormat audioFormatSupported;

        @Deprecated
        public BitrateAdaptation bitrateAdaptationPrefered;

        @Deprecated
        public ImmutableList<BitrateAdaptation> bitrateAdaptations;
        public String clientVersion;

        @Deprecated
        public Encode codecPrefered;
        public String deviceCategory;
        public String deviceId;
        public String devicePlatform;
        public String deviceTypeId;

        @Deprecated
        public Drm drmSupported;

        @Deprecated
        public HdrFormat hdrFormatSupported;

        @Deprecated
        public ImmutableList<HdrFormat> hdrFormats;
        public boolean legacySubtitleDevice;
        public String operatingSystem;
        public boolean optOutAdTracking;
        public ProtocolType protocol;
        public ImmutableMap<String, ProtocolType> protocolByResource;

        @Deprecated
        public ImmutableList<StreamingTechnology> streamingTechnologies;
        public DRMKeyScheme supportedDRMKeyScheme;
        public String userAgent;

        @Deprecated
        public VideoQuality videoQualitySupported;

        public Device build() {
            return new Device(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Device> {
        private final EnumParser<AudioFormat> mAudioFormatSupportedParser;
        private final EnumParser<BitrateAdaptation> mBitrateAdaptationParser;
        private final ListParser<BitrateAdaptation> mBitrateAdaptationsParser;
        private final EnumParser<DRMKeyScheme> mDRMKeySchemeParser;
        private final EnumParser<Drm> mDrmSupportedParser;
        private final EnumParser<Encode> mEncodeParser;
        private final EnumParser<HdrFormat> mHdrFormatParser;
        private final MapParser<String, ProtocolType> mProtocolByResourceParser;
        private final EnumParser<ProtocolType> mProtocolTypeParser;
        private final ListParser<StreamingTechnology> mStreamingTechnologiesParser;
        private final SimpleParsers.StringParser mStringParser;
        private final EnumParser<VideoQuality> mVideoQualitySupportedParser;
        private final ListParser<HdrFormat> mhdrFormatsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mHdrFormatParser = EnumParser.newParser(HdrFormat.class);
            this.mDrmSupportedParser = EnumParser.newParser(Drm.class);
            this.mAudioFormatSupportedParser = EnumParser.newParser(AudioFormat.class);
            this.mDRMKeySchemeParser = EnumParser.newParser(DRMKeyScheme.class);
            this.mEncodeParser = EnumParser.newParser(Encode.class);
            this.mhdrFormatsParser = ListParser.newParser(EnumParser.newParser(HdrFormat.class));
            this.mBitrateAdaptationsParser = ListParser.newParser(EnumParser.newParser(BitrateAdaptation.class));
            this.mStreamingTechnologiesParser = ListParser.newParser(EnumParser.newParser(StreamingTechnology.class));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mProtocolByResourceParser = MapParser.newParser(stringParser, EnumParser.newParser(ProtocolType.class));
            this.mBitrateAdaptationParser = EnumParser.newParser(BitrateAdaptation.class);
            this.mStringParser = stringParser;
            this.mProtocolTypeParser = EnumParser.newParser(ProtocolType.class);
            this.mVideoQualitySupportedParser = EnumParser.newParser(VideoQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Device parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Device:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Device parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Device:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Device parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Device");
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
                                b = 21;
                            }
                            break;
                        case -1515263098:
                            if (next.equals("hdrFormats")) {
                                b = 3;
                            }
                            break;
                        case -1176666416:
                            if (next.equals("operatingSystem")) {
                                b = 17;
                            }
                            break;
                        case -1066315757:
                            if (next.equals("bitrateAdaptations")) {
                                b = 16;
                            }
                            break;
                        case -1035404739:
                            if (next.equals("protocolByResource")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case -989163880:
                            if (next.equals("protocol")) {
                                b = 2;
                            }
                            break;
                        case -926592561:
                            if (next.equals("drmSupported")) {
                                b = 1;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 9;
                            }
                            break;
                        case -555948790:
                            if (next.equals("videoQualitySupported")) {
                                b = 8;
                            }
                            break;
                        case -263747212:
                            if (next.equals("deviceCategory")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -124344481:
                            if (next.equals("advertisingId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 7419841:
                            if (next.equals("audioFormatSupported")) {
                                b = 5;
                            }
                            break;
                        case 311430650:
                            if (next.equals("userAgent")) {
                                b = 19;
                            }
                            break;
                        case 575001043:
                            if (next.equals("supportedDRMKeyScheme")) {
                                b = 20;
                            }
                            break;
                        case 713259237:
                            if (next.equals("codecPrefered")) {
                                b = 15;
                            }
                            break;
                        case 771880589:
                            if (next.equals("clientVersion")) {
                                b = 6;
                            }
                            break;
                        case 789494575:
                            if (next.equals("bitrateAdaptationPrefered")) {
                                b = 0;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 10;
                            }
                            break;
                        case 1307785717:
                            if (next.equals("optOutAdTracking")) {
                                b = 18;
                            }
                            break;
                        case 1560425705:
                            if (next.equals("devicePlatform")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1626086561:
                            if (next.equals("hdrFormatSupported")) {
                                b = 4;
                            }
                            break;
                        case 1792987319:
                            if (next.equals("legacySubtitleDevice")) {
                                b = 7;
                            }
                            break;
                    }
                    BitrateAdaptation bitrateAdaptation = null;
                    ImmutableList<StreamingTechnology> immutableList = null;
                    DRMKeyScheme dRMKeyScheme = null;
                    String str = null;
                    String str2 = null;
                    ImmutableList<BitrateAdaptation> immutableList2 = null;
                    Encode encode = null;
                    String str3 = null;
                    String str4 = null;
                    ImmutableMap<String, ProtocolType> immutableMap = null;
                    String str5 = null;
                    String str6 = null;
                    String str7 = null;
                    VideoQuality videoQuality = null;
                    String str8 = null;
                    AudioFormat audioFormat = null;
                    HdrFormat hdrFormat = null;
                    ImmutableList<HdrFormat> immutableList3 = null;
                    ProtocolType protocolType = null;
                    Drm drm = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                bitrateAdaptation = (BitrateAdaptation) this.mBitrateAdaptationParser.parse(jsonNode2);
                            }
                            builder.bitrateAdaptationPrefered = bitrateAdaptation;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                drm = (Drm) this.mDrmSupportedParser.parse(jsonNode2);
                            }
                            builder.drmSupported = drm;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                protocolType = (ProtocolType) this.mProtocolTypeParser.parse(jsonNode2);
                            }
                            builder.protocol = protocolType;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                immutableList3 = this.mhdrFormatsParser.parse(jsonNode2);
                            }
                            builder.hdrFormats = immutableList3;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                hdrFormat = (HdrFormat) this.mHdrFormatParser.parse(jsonNode2);
                            }
                            builder.hdrFormatSupported = hdrFormat;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                audioFormat = (AudioFormat) this.mAudioFormatSupportedParser.parse(jsonNode2);
                            }
                            builder.audioFormatSupported = audioFormat;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str8 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.clientVersion = str8;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field legacySubtitleDevice can't be null");
                            }
                            builder.legacySubtitleDevice = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                videoQuality = (VideoQuality) this.mVideoQualitySupportedParser.parse(jsonNode2);
                            }
                            builder.videoQualitySupported = videoQuality;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                str7 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.deviceTypeId = str7;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                str6 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.deviceId = str6;
                            break;
                        case 11:
                            if (!jsonNode2.isNull()) {
                                str5 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.devicePlatform = str5;
                            break;
                        case 12:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mProtocolByResourceParser.parse(jsonNode2);
                            }
                            builder.protocolByResource = immutableMap;
                            break;
                        case 13:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.deviceCategory = str4;
                            break;
                        case 14:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.advertisingId = str3;
                            break;
                        case 15:
                            if (!jsonNode2.isNull()) {
                                encode = (Encode) this.mEncodeParser.parse(jsonNode2);
                            }
                            builder.codecPrefered = encode;
                            break;
                        case 16:
                            if (!jsonNode2.isNull()) {
                                immutableList2 = this.mBitrateAdaptationsParser.parse(jsonNode2);
                            }
                            builder.bitrateAdaptations = immutableList2;
                            break;
                        case 17:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.operatingSystem = str2;
                            break;
                        case 18:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field optOutAdTracking can't be null");
                            }
                            builder.optOutAdTracking = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 19:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.userAgent = str;
                            break;
                        case 20:
                            if (!jsonNode2.isNull()) {
                                dRMKeyScheme = (DRMKeyScheme) this.mDRMKeySchemeParser.parse(jsonNode2);
                            }
                            builder.supportedDRMKeyScheme = dRMKeyScheme;
                            break;
                        case 21:
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mStreamingTechnologiesParser.parse(jsonNode2);
                            }
                            builder.streamingTechnologies = immutableList;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Device parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 21;
                                    }
                                    break;
                                case -1515263098:
                                    if (currentName.equals("hdrFormats")) {
                                        b = 3;
                                    }
                                    break;
                                case -1176666416:
                                    if (currentName.equals("operatingSystem")) {
                                        b = 17;
                                    }
                                    break;
                                case -1066315757:
                                    if (currentName.equals("bitrateAdaptations")) {
                                        b = 16;
                                    }
                                    break;
                                case -1035404739:
                                    if (currentName.equals("protocolByResource")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case -989163880:
                                    if (currentName.equals("protocol")) {
                                        b = 2;
                                    }
                                    break;
                                case -926592561:
                                    if (currentName.equals("drmSupported")) {
                                        b = 1;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 9;
                                    }
                                    break;
                                case -555948790:
                                    if (currentName.equals("videoQualitySupported")) {
                                        b = 8;
                                    }
                                    break;
                                case -263747212:
                                    if (currentName.equals("deviceCategory")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -124344481:
                                    if (currentName.equals("advertisingId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 7419841:
                                    if (currentName.equals("audioFormatSupported")) {
                                        b = 5;
                                    }
                                    break;
                                case 311430650:
                                    if (currentName.equals("userAgent")) {
                                        b = 19;
                                    }
                                    break;
                                case 575001043:
                                    if (currentName.equals("supportedDRMKeyScheme")) {
                                        b = 20;
                                    }
                                    break;
                                case 713259237:
                                    if (currentName.equals("codecPrefered")) {
                                        b = 15;
                                    }
                                    break;
                                case 771880589:
                                    if (currentName.equals("clientVersion")) {
                                        b = 6;
                                    }
                                    break;
                                case 789494575:
                                    if (currentName.equals("bitrateAdaptationPrefered")) {
                                        b = 0;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 10;
                                    }
                                    break;
                                case 1307785717:
                                    if (currentName.equals("optOutAdTracking")) {
                                        b = 18;
                                    }
                                    break;
                                case 1560425705:
                                    if (currentName.equals("devicePlatform")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1626086561:
                                    if (currentName.equals("hdrFormatSupported")) {
                                        b = 4;
                                    }
                                    break;
                                case 1792987319:
                                    if (currentName.equals("legacySubtitleDevice")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            BitrateAdaptation bitrateAdaptation = null;
                            ImmutableList<StreamingTechnology> immutableList = null;
                            DRMKeyScheme dRMKeyScheme = null;
                            String str = null;
                            String str2 = null;
                            ImmutableList<BitrateAdaptation> immutableList2 = null;
                            Encode encode = null;
                            String str3 = null;
                            String str4 = null;
                            ImmutableMap<String, ProtocolType> immutableMap = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            VideoQuality videoQuality = null;
                            String str8 = null;
                            AudioFormat audioFormat = null;
                            HdrFormat hdrFormat = null;
                            ImmutableList<HdrFormat> immutableList3 = null;
                            ProtocolType protocolType = null;
                            Drm drm = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bitrateAdaptation = (BitrateAdaptation) this.mBitrateAdaptationParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaptationPrefered = bitrateAdaptation;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        drm = (Drm) this.mDrmSupportedParser.parse(jsonParser);
                                    }
                                    builder.drmSupported = drm;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        protocolType = (ProtocolType) this.mProtocolTypeParser.parse(jsonParser);
                                    }
                                    builder.protocol = protocolType;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList3 = this.mhdrFormatsParser.parse(jsonParser);
                                    }
                                    builder.hdrFormats = immutableList3;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        hdrFormat = (HdrFormat) this.mHdrFormatParser.parse(jsonParser);
                                    }
                                    builder.hdrFormatSupported = hdrFormat;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        audioFormat = (AudioFormat) this.mAudioFormatSupportedParser.parse(jsonParser);
                                    }
                                    builder.audioFormatSupported = audioFormat;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.clientVersion = str8;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field legacySubtitleDevice can't be null");
                                    }
                                    builder.legacySubtitleDevice = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mVideoQualitySupportedParser.parse(jsonParser);
                                    }
                                    builder.videoQualitySupported = videoQuality;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceTypeId = str7;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceId = str6;
                                    break;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.devicePlatform = str5;
                                    break;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mProtocolByResourceParser.parse(jsonParser);
                                    }
                                    builder.protocolByResource = immutableMap;
                                    break;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.deviceCategory = str4;
                                    break;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.advertisingId = str3;
                                    break;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        encode = (Encode) this.mEncodeParser.parse(jsonParser);
                                    }
                                    builder.codecPrefered = encode;
                                    break;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mBitrateAdaptationsParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaptations = immutableList2;
                                    break;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.operatingSystem = str2;
                                    break;
                                case 18:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field optOutAdTracking can't be null");
                                    }
                                    builder.optOutAdTracking = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.userAgent = str;
                                    break;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        dRMKeyScheme = (DRMKeyScheme) this.mDRMKeySchemeParser.parse(jsonParser);
                                    }
                                    builder.supportedDRMKeyScheme = dRMKeyScheme;
                                    break;
                                case 21:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mStreamingTechnologiesParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnologies = immutableList;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Device so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
