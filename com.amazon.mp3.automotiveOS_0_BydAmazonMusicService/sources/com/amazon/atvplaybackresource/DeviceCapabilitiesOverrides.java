package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackdevice.types.AudioQuality;
import com.amazon.atvplaybackdevice.types.BitrateAdaptation;
import com.amazon.atvplaybackdevice.types.Codec;
import com.amazon.atvplaybackdevice.types.Drm;
import com.amazon.atvplaybackdevice.types.FragmentRepresentation;
import com.amazon.atvplaybackdevice.types.FrameRate;
import com.amazon.atvplaybackdevice.types.HdrFormat;
import com.amazon.atvplaybackdevice.types.Protocol;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.StreamingTechnology;
import com.amazon.atvplaybackdevice.types.VideoQuality;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceCapabilitiesOverrides {
    public final Optional<String> adInsertionTypeOverride;
    public final Optional<AudioQuality> audioQualityOverride;
    public final Optional<ImmutableList<BitrateAdaptation>> bitrateAdaptationsOverride;
    public final Optional<Drm> drmOverride;
    public final Optional<FragmentRepresentation> fragmentRepresentation;
    public final Optional<FrameRate> frameRateOverride;
    public final Optional<ImmutableList<HdrFormat>> hdrFormatsOverride;
    public final Optional<Integer> pdcsVersion;
    public final Optional<Protocol> protocolOverride;
    public final Optional<ImmutableList<Resource>> protocolOverrideResources;
    public final Optional<StreamingTechnology> streamingTechnologyOverride;
    public final Optional<ImmutableList<Codec>> videoCodecOverride;
    public final Optional<VideoQuality> videoQualityOverride;
    public final Optional<String> vodStreamSupportOverride;

    private DeviceCapabilitiesOverrides(Builder builder) {
        this.protocolOverride = Optional.fromNullable(builder.protocolOverride);
        this.bitrateAdaptationsOverride = Optional.fromNullable(builder.bitrateAdaptationsOverride);
        this.adInsertionTypeOverride = Optional.fromNullable(builder.adInsertionTypeOverride);
        this.protocolOverrideResources = Optional.fromNullable(builder.protocolOverrideResources);
        this.videoQualityOverride = Optional.fromNullable(builder.videoQualityOverride);
        this.audioQualityOverride = Optional.fromNullable(builder.audioQualityOverride);
        this.frameRateOverride = Optional.fromNullable(builder.frameRateOverride);
        this.videoCodecOverride = Optional.fromNullable(builder.videoCodecOverride);
        this.streamingTechnologyOverride = Optional.fromNullable(builder.streamingTechnologyOverride);
        this.hdrFormatsOverride = Optional.fromNullable(builder.hdrFormatsOverride);
        this.fragmentRepresentation = Optional.fromNullable(builder.fragmentRepresentation);
        this.pdcsVersion = Optional.fromNullable(builder.pdcsVersion);
        this.vodStreamSupportOverride = Optional.fromNullable(builder.vodStreamSupportOverride);
        this.drmOverride = Optional.fromNullable(builder.drmOverride);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceCapabilitiesOverrides)) {
            return false;
        }
        DeviceCapabilitiesOverrides deviceCapabilitiesOverrides = (DeviceCapabilitiesOverrides) obj;
        return Objects.equal(this.protocolOverride, deviceCapabilitiesOverrides.protocolOverride) && Objects.equal(this.bitrateAdaptationsOverride, deviceCapabilitiesOverrides.bitrateAdaptationsOverride) && Objects.equal(this.adInsertionTypeOverride, deviceCapabilitiesOverrides.adInsertionTypeOverride) && Objects.equal(this.protocolOverrideResources, deviceCapabilitiesOverrides.protocolOverrideResources) && Objects.equal(this.videoQualityOverride, deviceCapabilitiesOverrides.videoQualityOverride) && Objects.equal(this.audioQualityOverride, deviceCapabilitiesOverrides.audioQualityOverride) && Objects.equal(this.frameRateOverride, deviceCapabilitiesOverrides.frameRateOverride) && Objects.equal(this.videoCodecOverride, deviceCapabilitiesOverrides.videoCodecOverride) && Objects.equal(this.streamingTechnologyOverride, deviceCapabilitiesOverrides.streamingTechnologyOverride) && Objects.equal(this.hdrFormatsOverride, deviceCapabilitiesOverrides.hdrFormatsOverride) && Objects.equal(this.fragmentRepresentation, deviceCapabilitiesOverrides.fragmentRepresentation) && Objects.equal(this.pdcsVersion, deviceCapabilitiesOverrides.pdcsVersion) && Objects.equal(this.vodStreamSupportOverride, deviceCapabilitiesOverrides.vodStreamSupportOverride) && Objects.equal(this.drmOverride, deviceCapabilitiesOverrides.drmOverride);
    }

    public int hashCode() {
        return Objects.hashCode(this.protocolOverride, this.bitrateAdaptationsOverride, this.adInsertionTypeOverride, this.protocolOverrideResources, this.videoQualityOverride, this.audioQualityOverride, this.frameRateOverride, this.videoCodecOverride, this.streamingTechnologyOverride, this.hdrFormatsOverride, this.fragmentRepresentation, this.pdcsVersion, this.vodStreamSupportOverride, this.drmOverride);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("protocolOverride", this.protocolOverride).add("bitrateAdaptationsOverride", this.bitrateAdaptationsOverride).add("adInsertionTypeOverride", this.adInsertionTypeOverride).add("protocolOverrideResources", this.protocolOverrideResources).add("videoQualityOverride", this.videoQualityOverride).add("audioQualityOverride", this.audioQualityOverride).add("frameRateOverride", this.frameRateOverride).add("videoCodecOverride", this.videoCodecOverride).add("streamingTechnologyOverride", this.streamingTechnologyOverride).add("hdrFormatsOverride", this.hdrFormatsOverride).add("fragmentRepresentation", this.fragmentRepresentation).add("pdcsVersion", this.pdcsVersion).add("vodStreamSupportOverride", this.vodStreamSupportOverride).add("drmOverride", this.drmOverride).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String adInsertionTypeOverride;
        public AudioQuality audioQualityOverride;
        public ImmutableList<BitrateAdaptation> bitrateAdaptationsOverride;
        public Drm drmOverride;
        public FragmentRepresentation fragmentRepresentation;
        public FrameRate frameRateOverride;
        public ImmutableList<HdrFormat> hdrFormatsOverride;
        public Integer pdcsVersion;
        public Protocol protocolOverride;
        public ImmutableList<Resource> protocolOverrideResources;
        public StreamingTechnology streamingTechnologyOverride;
        public ImmutableList<Codec> videoCodecOverride;
        public VideoQuality videoQualityOverride;
        public String vodStreamSupportOverride;

        public DeviceCapabilitiesOverrides build() {
            return new DeviceCapabilitiesOverrides(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DeviceCapabilitiesOverrides> {
        private final ListParser<BitrateAdaptation> mBitrateAdaptationsOverrideParser;
        private final ListParser<Resource> mDesiredResourcesListParser;
        private final ListParser<HdrFormat> mHdrFormatsOverrideParser;
        private final ListParser<Codec> mVideoCodecOverrideParser;
        private final SimpleParsers.StringParser madInsertionTypeParser;
        private final EnumParser<AudioQuality> maudioQualityParser;
        private final EnumParser<Drm> mdrmParser;
        private final EnumParser<FragmentRepresentation> mfragmentRepresentationParser;
        private final EnumParser<FrameRate> mframeRateParser;
        private final SimpleParsers.IntegerParser mintegerTargetParser;
        private final EnumParser<Protocol> mprotocolParser;
        private final EnumParser<StreamingTechnology> mstreamingTechnologyParser;
        private final EnumParser<VideoQuality> mvideoQualityParser;
        private final SimpleParsers.StringParser mvodStreamSupportOverrideParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mprotocolParser = EnumParser.newParser(Protocol.class);
            this.mframeRateParser = EnumParser.newParser(FrameRate.class);
            this.mdrmParser = EnumParser.newParser(Drm.class);
            this.mstreamingTechnologyParser = EnumParser.newParser(StreamingTechnology.class);
            this.mfragmentRepresentationParser = EnumParser.newParser(FragmentRepresentation.class);
            this.mBitrateAdaptationsOverrideParser = ListParser.newParser(EnumParser.newParser(BitrateAdaptation.class));
            this.mDesiredResourcesListParser = ListParser.newParser(EnumParser.newParser(Resource.class));
            this.mVideoCodecOverrideParser = ListParser.newParser(EnumParser.newParser(Codec.class));
            this.mHdrFormatsOverrideParser = ListParser.newParser(EnumParser.newParser(HdrFormat.class));
            this.mintegerTargetParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mvideoQualityParser = EnumParser.newParser(VideoQuality.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.madInsertionTypeParser = stringParser;
            this.mvodStreamSupportOverrideParser = stringParser;
            this.maudioQualityParser = EnumParser.newParser(AudioQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DeviceCapabilitiesOverrides parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DeviceCapabilitiesOverrides:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DeviceCapabilitiesOverrides parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DeviceCapabilitiesOverrides:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DeviceCapabilitiesOverrides parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Protocol protocol;
            ImmutableList<BitrateAdaptation> immutableList;
            String str;
            ImmutableList<Resource> immutableList2;
            VideoQuality videoQuality;
            AudioQuality audioQuality;
            FrameRate frameRate;
            ImmutableList<Codec> immutableList3;
            StreamingTechnology streamingTechnology;
            ImmutableList<HdrFormat> immutableList4;
            FragmentRepresentation fragmentRepresentation;
            Integer num;
            String str2;
            Drm drm;
            JsonParsingUtils.throwIfNotObject(jsonNode, "DeviceCapabilitiesOverrides");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1972309742:
                            if (next.equals("adInsertionTypeOverride")) {
                                b = 2;
                            }
                            break;
                        case -1936688902:
                            if (next.equals("streamingTechnologyOverride")) {
                                b = 8;
                            }
                            break;
                        case -1462270606:
                            if (next.equals("hdrFormatsOverride")) {
                                b = 9;
                            }
                            break;
                        case -1302853835:
                            if (next.equals("audioQualityOverride")) {
                                b = 5;
                            }
                            break;
                        case -872771600:
                            if (next.equals("videoQualityOverride")) {
                                b = 4;
                            }
                            break;
                        case -148269753:
                            if (next.equals("videoCodecOverride")) {
                                b = 7;
                            }
                            break;
                        case 160050900:
                            if (next.equals("pdcsVersion")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 489565913:
                            if (next.equals("frameRateOverride")) {
                                b = 6;
                            }
                            break;
                        case 570555393:
                            if (next.equals("protocolOverrideResources")) {
                                b = 3;
                            }
                            break;
                        case 916387467:
                            if (next.equals("drmOverride")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 1018164868:
                            if (next.equals("protocolOverride")) {
                                b = 0;
                            }
                            break;
                        case 1185352959:
                            if (next.equals("bitrateAdaptationsOverride")) {
                                b = 1;
                            }
                            break;
                        case 1747369232:
                            if (next.equals("vodStreamSupportOverride")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 2127018813:
                            if (next.equals("fragmentRepresentation")) {
                                b = 10;
                            }
                            break;
                    }
                    protocol = null;
                    drm = null;
                    str2 = null;
                    num = null;
                    fragmentRepresentation = null;
                    immutableList4 = null;
                    streamingTechnology = null;
                    immutableList3 = null;
                    frameRate = null;
                    audioQuality = null;
                    videoQuality = null;
                    immutableList2 = null;
                    str = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DeviceCapabilitiesOverrides so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            protocol = (Protocol) this.mprotocolParser.parse(jsonNode2);
                        }
                        builder.protocolOverride = protocol;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mBitrateAdaptationsOverrideParser.parse(jsonNode2);
                        }
                        builder.bitrateAdaptationsOverride = immutableList;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.madInsertionTypeParser.parse(jsonNode2);
                        }
                        builder.adInsertionTypeOverride = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mDesiredResourcesListParser.parse(jsonNode2);
                        }
                        builder.protocolOverrideResources = immutableList2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mvideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQualityOverride = videoQuality;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            audioQuality = (AudioQuality) this.maudioQualityParser.parse(jsonNode2);
                        }
                        builder.audioQualityOverride = audioQuality;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            frameRate = (FrameRate) this.mframeRateParser.parse(jsonNode2);
                        }
                        builder.frameRateOverride = frameRate;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mVideoCodecOverrideParser.parse(jsonNode2);
                        }
                        builder.videoCodecOverride = immutableList3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            streamingTechnology = (StreamingTechnology) this.mstreamingTechnologyParser.parse(jsonNode2);
                        }
                        builder.streamingTechnologyOverride = streamingTechnology;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            immutableList4 = this.mHdrFormatsOverrideParser.parse(jsonNode2);
                        }
                        builder.hdrFormatsOverride = immutableList4;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            fragmentRepresentation = (FragmentRepresentation) this.mfragmentRepresentationParser.parse(jsonNode2);
                        }
                        builder.fragmentRepresentation = fragmentRepresentation;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            num = this.mintegerTargetParser.parse(jsonNode2);
                        }
                        builder.pdcsVersion = num;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mvodStreamSupportOverrideParser.parse(jsonNode2);
                        }
                        builder.vodStreamSupportOverride = str2;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            drm = (Drm) this.mdrmParser.parse(jsonNode2);
                        }
                        builder.drmOverride = drm;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DeviceCapabilitiesOverrides so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DeviceCapabilitiesOverrides parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1972309742:
                                    if (currentName.equals("adInsertionTypeOverride")) {
                                        b = 2;
                                    }
                                    break;
                                case -1936688902:
                                    if (currentName.equals("streamingTechnologyOverride")) {
                                        b = 8;
                                    }
                                    break;
                                case -1462270606:
                                    if (currentName.equals("hdrFormatsOverride")) {
                                        b = 9;
                                    }
                                    break;
                                case -1302853835:
                                    if (currentName.equals("audioQualityOverride")) {
                                        b = 5;
                                    }
                                    break;
                                case -872771600:
                                    if (currentName.equals("videoQualityOverride")) {
                                        b = 4;
                                    }
                                    break;
                                case -148269753:
                                    if (currentName.equals("videoCodecOverride")) {
                                        b = 7;
                                    }
                                    break;
                                case 160050900:
                                    if (currentName.equals("pdcsVersion")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 489565913:
                                    if (currentName.equals("frameRateOverride")) {
                                        b = 6;
                                    }
                                    break;
                                case 570555393:
                                    if (currentName.equals("protocolOverrideResources")) {
                                        b = 3;
                                    }
                                    break;
                                case 916387467:
                                    if (currentName.equals("drmOverride")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 1018164868:
                                    if (currentName.equals("protocolOverride")) {
                                        b = 0;
                                    }
                                    break;
                                case 1185352959:
                                    if (currentName.equals("bitrateAdaptationsOverride")) {
                                        b = 1;
                                    }
                                    break;
                                case 1747369232:
                                    if (currentName.equals("vodStreamSupportOverride")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 2127018813:
                                    if (currentName.equals("fragmentRepresentation")) {
                                        b = 10;
                                    }
                                    break;
                            }
                            Protocol protocol = null;
                            Drm drm = null;
                            String str = null;
                            Integer num = null;
                            FragmentRepresentation fragmentRepresentation = null;
                            ImmutableList<HdrFormat> immutableList = null;
                            StreamingTechnology streamingTechnology = null;
                            ImmutableList<Codec> immutableList2 = null;
                            FrameRate frameRate = null;
                            AudioQuality audioQuality = null;
                            VideoQuality videoQuality = null;
                            ImmutableList<Resource> immutableList3 = null;
                            String str2 = null;
                            ImmutableList<BitrateAdaptation> immutableList4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        protocol = (Protocol) this.mprotocolParser.parse(jsonParser);
                                    }
                                    builder.protocolOverride = protocol;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList4 = this.mBitrateAdaptationsOverrideParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaptationsOverride = immutableList4;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.madInsertionTypeParser.parse(jsonParser);
                                    }
                                    builder.adInsertionTypeOverride = str2;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList3 = this.mDesiredResourcesListParser.parse(jsonParser);
                                    }
                                    builder.protocolOverrideResources = immutableList3;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mvideoQualityParser.parse(jsonParser);
                                    }
                                    builder.videoQualityOverride = videoQuality;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        audioQuality = (AudioQuality) this.maudioQualityParser.parse(jsonParser);
                                    }
                                    builder.audioQualityOverride = audioQuality;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        frameRate = (FrameRate) this.mframeRateParser.parse(jsonParser);
                                    }
                                    builder.frameRateOverride = frameRate;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mVideoCodecOverrideParser.parse(jsonParser);
                                    }
                                    builder.videoCodecOverride = immutableList2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        streamingTechnology = (StreamingTechnology) this.mstreamingTechnologyParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnologyOverride = streamingTechnology;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mHdrFormatsOverrideParser.parse(jsonParser);
                                    }
                                    builder.hdrFormatsOverride = immutableList;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        fragmentRepresentation = (FragmentRepresentation) this.mfragmentRepresentationParser.parse(jsonParser);
                                    }
                                    builder.fragmentRepresentation = fragmentRepresentation;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mintegerTargetParser.parse(jsonParser);
                                    }
                                    builder.pdcsVersion = num;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mvodStreamSupportOverrideParser.parse(jsonParser);
                                    }
                                    builder.vodStreamSupportOverride = str;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        drm = (Drm) this.mdrmParser.parse(jsonParser);
                                    }
                                    builder.drmOverride = drm;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DeviceCapabilitiesOverrides so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DeviceCapabilitiesOverrides so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
