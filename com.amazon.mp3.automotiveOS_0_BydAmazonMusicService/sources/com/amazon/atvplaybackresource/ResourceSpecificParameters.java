package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.types.LanguageFeature;
import com.amazon.atvplaybackresource.types.LiveManifestType;
import com.amazon.atvplaybackresource.types.SsaiSegmentInfoType;
import com.amazon.atvplaybackresource.types.SsaiStitchType;
import com.amazon.atvplaybackresource.types.SubtitleFormat;
import com.amazon.atvplaybackresource.types.daiLiveManifestType;
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
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceSpecificParameters {
    public final Optional<String> advertisingId;
    public final Optional<ImmutableList<daiLiveManifestType>> daiLiveManifestTypes;
    public final Optional<String> drmSessionId;
    public final Optional<ByteBuffer> fairplayChallenge;
    public final Optional<String> fairplayKeyId;
    public final Optional<String> gdprConsent;
    public final Optional<String> gdprConsentAVL;
    public final Optional<Integer> gdprEnabled;
    public final Optional<Boolean> includeHdcpTestKeyInLicense;
    public final Optional<LanguageFeature> languageFeature;
    public final Optional<String> linearRestrictionEnd;
    public final Optional<String> linearRestrictionStart;
    public final Optional<ImmutableList<LiveManifestType>> liveManifestTypes;
    public final Optional<String> liveScheduleConfigToken;
    public final boolean optOutAdTracking;
    public final Optional<String> playReadyChallenge;
    public final Optional<String> playReadyKeyId;
    public final Optional<String> playbackSettingsFormatVersion;
    public final Optional<ImmutableList<SsaiSegmentInfoType>> ssaiSegmentInfo;
    public final Optional<SsaiStitchType> ssaiStitchType;
    public final Optional<SubtitleFormat> subtitleFormat;
    public final Optional<String> titleDecorationScheme;
    public final Optional<String> userWatchSessionId;
    public final Optional<String> vastTimelineType;
    public final Optional<ByteBuffer> widevine2Challenge;
    public final Optional<String> xrayDeviceClass;
    public final Optional<String> xrayPlaybackMode;
    public final Optional<String> xrayToken;

    private ResourceSpecificParameters(Builder builder) {
        this.gdprEnabled = Optional.fromNullable(builder.gdprEnabled);
        this.fairplayChallenge = Optional.fromNullable(builder.fairplayChallenge);
        this.liveScheduleConfigToken = Optional.fromNullable(builder.liveScheduleConfigToken);
        this.xrayDeviceClass = Optional.fromNullable(builder.xrayDeviceClass);
        this.linearRestrictionEnd = Optional.fromNullable(builder.linearRestrictionEnd);
        this.playReadyKeyId = Optional.fromNullable(builder.playReadyKeyId);
        this.languageFeature = Optional.fromNullable(builder.languageFeature);
        this.playbackSettingsFormatVersion = Optional.fromNullable(builder.playbackSettingsFormatVersion);
        this.daiLiveManifestTypes = Optional.fromNullable(builder.daiLiveManifestTypes);
        this.gdprConsent = Optional.fromNullable(builder.gdprConsent);
        this.linearRestrictionStart = Optional.fromNullable(builder.linearRestrictionStart);
        this.liveManifestTypes = Optional.fromNullable(builder.liveManifestTypes);
        this.gdprConsentAVL = Optional.fromNullable(builder.gdprConsentAVL);
        this.optOutAdTracking = builder.optOutAdTracking;
        this.playReadyChallenge = Optional.fromNullable(builder.playReadyChallenge);
        this.widevine2Challenge = Optional.fromNullable(builder.widevine2Challenge);
        this.userWatchSessionId = Optional.fromNullable(builder.userWatchSessionId);
        this.vastTimelineType = Optional.fromNullable(builder.vastTimelineType);
        this.titleDecorationScheme = Optional.fromNullable(builder.titleDecorationScheme);
        this.drmSessionId = Optional.fromNullable(builder.drmSessionId);
        this.subtitleFormat = Optional.fromNullable(builder.subtitleFormat);
        this.xrayPlaybackMode = Optional.fromNullable(builder.xrayPlaybackMode);
        this.advertisingId = Optional.fromNullable(builder.advertisingId);
        this.ssaiStitchType = Optional.fromNullable(builder.ssaiStitchType);
        this.ssaiSegmentInfo = Optional.fromNullable(builder.ssaiSegmentInfo);
        this.includeHdcpTestKeyInLicense = Optional.fromNullable(builder.includeHdcpTestKeyInLicense);
        this.xrayToken = Optional.fromNullable(builder.xrayToken);
        this.fairplayKeyId = Optional.fromNullable(builder.fairplayKeyId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResourceSpecificParameters)) {
            return false;
        }
        ResourceSpecificParameters resourceSpecificParameters = (ResourceSpecificParameters) obj;
        return Objects.equal(this.gdprEnabled, resourceSpecificParameters.gdprEnabled) && Objects.equal(this.fairplayChallenge, resourceSpecificParameters.fairplayChallenge) && Objects.equal(this.liveScheduleConfigToken, resourceSpecificParameters.liveScheduleConfigToken) && Objects.equal(this.xrayDeviceClass, resourceSpecificParameters.xrayDeviceClass) && Objects.equal(this.linearRestrictionEnd, resourceSpecificParameters.linearRestrictionEnd) && Objects.equal(this.playReadyKeyId, resourceSpecificParameters.playReadyKeyId) && Objects.equal(this.languageFeature, resourceSpecificParameters.languageFeature) && Objects.equal(this.playbackSettingsFormatVersion, resourceSpecificParameters.playbackSettingsFormatVersion) && Objects.equal(this.daiLiveManifestTypes, resourceSpecificParameters.daiLiveManifestTypes) && Objects.equal(this.gdprConsent, resourceSpecificParameters.gdprConsent) && Objects.equal(this.linearRestrictionStart, resourceSpecificParameters.linearRestrictionStart) && Objects.equal(this.liveManifestTypes, resourceSpecificParameters.liveManifestTypes) && Objects.equal(this.gdprConsentAVL, resourceSpecificParameters.gdprConsentAVL) && Objects.equal(Boolean.valueOf(this.optOutAdTracking), Boolean.valueOf(resourceSpecificParameters.optOutAdTracking)) && Objects.equal(this.playReadyChallenge, resourceSpecificParameters.playReadyChallenge) && Objects.equal(this.widevine2Challenge, resourceSpecificParameters.widevine2Challenge) && Objects.equal(this.userWatchSessionId, resourceSpecificParameters.userWatchSessionId) && Objects.equal(this.vastTimelineType, resourceSpecificParameters.vastTimelineType) && Objects.equal(this.titleDecorationScheme, resourceSpecificParameters.titleDecorationScheme) && Objects.equal(this.drmSessionId, resourceSpecificParameters.drmSessionId) && Objects.equal(this.subtitleFormat, resourceSpecificParameters.subtitleFormat) && Objects.equal(this.xrayPlaybackMode, resourceSpecificParameters.xrayPlaybackMode) && Objects.equal(this.advertisingId, resourceSpecificParameters.advertisingId) && Objects.equal(this.ssaiStitchType, resourceSpecificParameters.ssaiStitchType) && Objects.equal(this.ssaiSegmentInfo, resourceSpecificParameters.ssaiSegmentInfo) && Objects.equal(this.includeHdcpTestKeyInLicense, resourceSpecificParameters.includeHdcpTestKeyInLicense) && Objects.equal(this.xrayToken, resourceSpecificParameters.xrayToken) && Objects.equal(this.fairplayKeyId, resourceSpecificParameters.fairplayKeyId);
    }

    public int hashCode() {
        return Objects.hashCode(this.gdprEnabled, this.fairplayChallenge, this.liveScheduleConfigToken, this.xrayDeviceClass, this.linearRestrictionEnd, this.playReadyKeyId, this.languageFeature, this.playbackSettingsFormatVersion, this.daiLiveManifestTypes, this.gdprConsent, this.linearRestrictionStart, this.liveManifestTypes, this.gdprConsentAVL, Boolean.valueOf(this.optOutAdTracking), this.playReadyChallenge, this.widevine2Challenge, this.userWatchSessionId, this.vastTimelineType, this.titleDecorationScheme, this.drmSessionId, this.subtitleFormat, this.xrayPlaybackMode, this.advertisingId, this.ssaiStitchType, this.ssaiSegmentInfo, this.includeHdcpTestKeyInLicense, this.xrayToken, this.fairplayKeyId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("gdprEnabled", this.gdprEnabled).add("fairplayChallenge", this.fairplayChallenge).add("liveScheduleConfigToken", this.liveScheduleConfigToken).add("xrayDeviceClass", this.xrayDeviceClass).add("linearRestrictionEnd", this.linearRestrictionEnd).add("playReadyKeyId", this.playReadyKeyId).add("languageFeature", this.languageFeature).add("playbackSettingsFormatVersion", this.playbackSettingsFormatVersion).add("daiLiveManifestTypes", this.daiLiveManifestTypes).add("gdprConsent", this.gdprConsent).add("linearRestrictionStart", this.linearRestrictionStart).add("liveManifestTypes", this.liveManifestTypes).add("gdprConsentAVL", this.gdprConsentAVL).add("optOutAdTracking", this.optOutAdTracking).add("playReadyChallenge", this.playReadyChallenge).add("widevine2Challenge", this.widevine2Challenge).add("userWatchSessionId", this.userWatchSessionId).add("vastTimelineType", this.vastTimelineType).add("titleDecorationScheme", this.titleDecorationScheme).add("drmSessionId", this.drmSessionId).add("subtitleFormat", this.subtitleFormat).add("xrayPlaybackMode", this.xrayPlaybackMode).add("advertisingId", this.advertisingId).add("ssaiStitchType", this.ssaiStitchType).add("ssaiSegmentInfo", this.ssaiSegmentInfo).add("includeHdcpTestKeyInLicense", this.includeHdcpTestKeyInLicense).add("xrayToken", this.xrayToken).add("fairplayKeyId", this.fairplayKeyId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String advertisingId;
        public ImmutableList<daiLiveManifestType> daiLiveManifestTypes;
        public String drmSessionId;
        public ByteBuffer fairplayChallenge;
        public String fairplayKeyId;
        public String gdprConsent;
        public String gdprConsentAVL;
        public Integer gdprEnabled;
        public Boolean includeHdcpTestKeyInLicense;
        public LanguageFeature languageFeature;
        public String linearRestrictionEnd;
        public String linearRestrictionStart;
        public ImmutableList<LiveManifestType> liveManifestTypes;
        public String liveScheduleConfigToken;
        public boolean optOutAdTracking;
        public String playReadyChallenge;
        public String playReadyKeyId;
        public String playbackSettingsFormatVersion;
        public ImmutableList<SsaiSegmentInfoType> ssaiSegmentInfo;
        public SsaiStitchType ssaiStitchType;
        public SubtitleFormat subtitleFormat;
        public String titleDecorationScheme;
        public String userWatchSessionId;
        public String vastTimelineType;
        public ByteBuffer widevine2Challenge;
        public String xrayDeviceClass;
        public String xrayPlaybackMode;
        public String xrayToken;

        public ResourceSpecificParameters build() {
            return new ResourceSpecificParameters(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ResourceSpecificParameters> {
        private final EnumParser<LanguageFeature> mLanguageFeatureParser;
        private final ListParser<SsaiSegmentInfoType> mSsaiSegmentInfoListParser;
        private final EnumParser<SsaiStitchType> mSsaiStitchTypeParser;
        private final EnumParser<SubtitleFormat> mSubtitleFormatParser;
        private final SimpleParsers.BlobParser mblobTargetParser;
        private final SimpleParsers.BooleanParser mbooleanTargetParser;
        private final ListParser<daiLiveManifestType> mdaiLiveManifestTypeListParser;
        private final SimpleParsers.IntegerParser mintegerTargetParser;
        private final ListParser<LiveManifestType> mliveManifestTypeListParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSsaiStitchTypeParser = EnumParser.newParser(SsaiStitchType.class);
            this.mLanguageFeatureParser = EnumParser.newParser(LanguageFeature.class);
            this.mdaiLiveManifestTypeListParser = ListParser.newParser(EnumParser.newParser(daiLiveManifestType.class));
            this.mliveManifestTypeListParser = ListParser.newParser(EnumParser.newParser(LiveManifestType.class));
            this.mSsaiSegmentInfoListParser = ListParser.newParser(EnumParser.newParser(SsaiSegmentInfoType.class));
            this.mintegerTargetParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mblobTargetParser = SimpleParsers.BlobParser.INSTANCE;
            this.mbooleanTargetParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mSubtitleFormatParser = EnumParser.newParser(SubtitleFormat.class);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ResourceSpecificParameters parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ResourceSpecificParameters:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ResourceSpecificParameters parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ResourceSpecificParameters:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ResourceSpecificParameters parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Integer num;
            ByteBuffer byteBuffer;
            String str;
            String str2;
            String str3;
            String str4;
            LanguageFeature languageFeature;
            String str5;
            ImmutableList<daiLiveManifestType> immutableList;
            String str6;
            String str7;
            ImmutableList<LiveManifestType> immutableList2;
            String str8;
            String str9;
            ByteBuffer byteBuffer2;
            String str10;
            String str11;
            String str12;
            String str13;
            SubtitleFormat subtitleFormat;
            String str14;
            String str15;
            SsaiStitchType ssaiStitchType;
            ImmutableList<SsaiSegmentInfoType> immutableList3;
            Boolean bool;
            String str16;
            String str17;
            JsonParsingUtils.throwIfNotObject(jsonNode, "ResourceSpecificParameters");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2120280012:
                            if (next.equals("liveScheduleConfigToken")) {
                                b = 2;
                            }
                            break;
                        case -2081118962:
                            if (next.equals("widevine2Challenge")) {
                                b = 15;
                            }
                            break;
                        case -2054152706:
                            if (next.equals("liveManifestTypes")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -2041780519:
                            if (next.equals("ssaiSegmentInfo")) {
                                b = 24;
                            }
                            break;
                        case -1595628441:
                            if (next.equals("xrayToken")) {
                                b = 26;
                            }
                            break;
                        case -1373755077:
                            if (next.equals("linearRestrictionStart")) {
                                b = 10;
                            }
                            break;
                        case -1364570124:
                            if (next.equals("linearRestrictionEnd")) {
                                b = 4;
                            }
                            break;
                        case -1138818896:
                            if (next.equals("xrayDeviceClass")) {
                                b = 3;
                            }
                            break;
                        case -1063545260:
                            if (next.equals("playReadyChallenge")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case -1008781632:
                            if (next.equals("includeHdcpTestKeyInLicense")) {
                                b = 25;
                            }
                            break;
                        case -298992782:
                            if (next.equals("drmSessionId")) {
                                b = 19;
                            }
                            break;
                        case -258592889:
                            if (next.equals("vastTimelineType")) {
                                b = 17;
                            }
                            break;
                        case -124344481:
                            if (next.equals("advertisingId")) {
                                b = 22;
                            }
                            break;
                        case 145394012:
                            if (next.equals("gdprConsentAVL")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 282722171:
                            if (next.equals("gdprConsent")) {
                                b = 9;
                            }
                            break;
                        case 565463789:
                            if (next.equals("userWatchSessionId")) {
                                b = 16;
                            }
                            break;
                        case 936941091:
                            if (next.equals("playbackSettingsFormatVersion")) {
                                b = 7;
                            }
                            break;
                        case 1022101555:
                            if (next.equals("ssaiStitchType")) {
                                b = 23;
                            }
                            break;
                        case 1307785717:
                            if (next.equals("optOutAdTracking")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 1348816619:
                            if (next.equals("fairplayChallenge")) {
                                b = 1;
                            }
                            break;
                        case 1380533835:
                            if (next.equals("playReadyKeyId")) {
                                b = 5;
                            }
                            break;
                        case 1543299682:
                            if (next.equals("fairplayKeyId")) {
                                b = 27;
                            }
                            break;
                        case 1805655088:
                            if (next.equals("xrayPlaybackMode")) {
                                b = 21;
                            }
                            break;
                        case 1927331375:
                            if (next.equals("subtitleFormat")) {
                                b = 20;
                            }
                            break;
                        case 2016594594:
                            if (next.equals("gdprEnabled")) {
                                b = 0;
                            }
                            break;
                        case 2029052882:
                            if (next.equals("daiLiveManifestTypes")) {
                                b = 8;
                            }
                            break;
                        case 2062847102:
                            if (next.equals("languageFeature")) {
                                b = 6;
                            }
                            break;
                        case 2085309677:
                            if (next.equals("titleDecorationScheme")) {
                                b = 18;
                            }
                            break;
                    }
                    num = null;
                    str17 = null;
                    str16 = null;
                    bool = null;
                    immutableList3 = null;
                    ssaiStitchType = null;
                    str15 = null;
                    str14 = null;
                    subtitleFormat = null;
                    str13 = null;
                    str12 = null;
                    str11 = null;
                    str10 = null;
                    byteBuffer2 = null;
                    str9 = null;
                    str8 = null;
                    immutableList2 = null;
                    str7 = null;
                    str6 = null;
                    immutableList = null;
                    str5 = null;
                    languageFeature = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    str = null;
                    byteBuffer = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ResourceSpecificParameters so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            num = this.mintegerTargetParser.parse(jsonNode2);
                        }
                        builder.gdprEnabled = num;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            byteBuffer = this.mblobTargetParser.parse(jsonNode2);
                        }
                        builder.fairplayChallenge = byteBuffer;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.liveScheduleConfigToken = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.xrayDeviceClass = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.linearRestrictionEnd = str3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playReadyKeyId = str4;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            languageFeature = (LanguageFeature) this.mLanguageFeatureParser.parse(jsonNode2);
                        }
                        builder.languageFeature = languageFeature;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playbackSettingsFormatVersion = str5;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mdaiLiveManifestTypeListParser.parse(jsonNode2);
                        }
                        builder.daiLiveManifestTypes = immutableList;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.gdprConsent = str6;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.linearRestrictionStart = str7;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mliveManifestTypeListParser.parse(jsonNode2);
                        }
                        builder.liveManifestTypes = immutableList2;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.gdprConsentAVL = str8;
                        continue;
                    case 13:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field optOutAdTracking can't be null");
                        }
                        builder.optOutAdTracking = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                        continue;
                        break;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.playReadyChallenge = str9;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            byteBuffer2 = this.mblobTargetParser.parse(jsonNode2);
                        }
                        builder.widevine2Challenge = byteBuffer2;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.userWatchSessionId = str10;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            str11 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.vastTimelineType = str11;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            str12 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.titleDecorationScheme = str12;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            str13 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.drmSessionId = str13;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            subtitleFormat = (SubtitleFormat) this.mSubtitleFormatParser.parse(jsonNode2);
                        }
                        builder.subtitleFormat = subtitleFormat;
                        continue;
                    case 21:
                        if (!jsonNode2.isNull()) {
                            str14 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.xrayPlaybackMode = str14;
                        continue;
                    case 22:
                        if (!jsonNode2.isNull()) {
                            str15 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.advertisingId = str15;
                        continue;
                    case 23:
                        if (!jsonNode2.isNull()) {
                            ssaiStitchType = (SsaiStitchType) this.mSsaiStitchTypeParser.parse(jsonNode2);
                        }
                        builder.ssaiStitchType = ssaiStitchType;
                        continue;
                    case 24:
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mSsaiSegmentInfoListParser.parse(jsonNode2);
                        }
                        builder.ssaiSegmentInfo = immutableList3;
                        continue;
                    case 25:
                        if (!jsonNode2.isNull()) {
                            bool = this.mbooleanTargetParser.parse(jsonNode2);
                        }
                        builder.includeHdcpTestKeyInLicense = bool;
                        continue;
                    case 26:
                        if (!jsonNode2.isNull()) {
                            str16 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.xrayToken = str16;
                        continue;
                    case 27:
                        if (!jsonNode2.isNull()) {
                            str17 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.fairplayKeyId = str17;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ResourceSpecificParameters so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ResourceSpecificParameters parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2120280012:
                                    if (currentName.equals("liveScheduleConfigToken")) {
                                        b = 2;
                                    }
                                    break;
                                case -2081118962:
                                    if (currentName.equals("widevine2Challenge")) {
                                        b = 15;
                                    }
                                    break;
                                case -2054152706:
                                    if (currentName.equals("liveManifestTypes")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -2041780519:
                                    if (currentName.equals("ssaiSegmentInfo")) {
                                        b = 24;
                                    }
                                    break;
                                case -1595628441:
                                    if (currentName.equals("xrayToken")) {
                                        b = 26;
                                    }
                                    break;
                                case -1373755077:
                                    if (currentName.equals("linearRestrictionStart")) {
                                        b = 10;
                                    }
                                    break;
                                case -1364570124:
                                    if (currentName.equals("linearRestrictionEnd")) {
                                        b = 4;
                                    }
                                    break;
                                case -1138818896:
                                    if (currentName.equals("xrayDeviceClass")) {
                                        b = 3;
                                    }
                                    break;
                                case -1063545260:
                                    if (currentName.equals("playReadyChallenge")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case -1008781632:
                                    if (currentName.equals("includeHdcpTestKeyInLicense")) {
                                        b = 25;
                                    }
                                    break;
                                case -298992782:
                                    if (currentName.equals("drmSessionId")) {
                                        b = 19;
                                    }
                                    break;
                                case -258592889:
                                    if (currentName.equals("vastTimelineType")) {
                                        b = 17;
                                    }
                                    break;
                                case -124344481:
                                    if (currentName.equals("advertisingId")) {
                                        b = 22;
                                    }
                                    break;
                                case 145394012:
                                    if (currentName.equals("gdprConsentAVL")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 282722171:
                                    if (currentName.equals("gdprConsent")) {
                                        b = 9;
                                    }
                                    break;
                                case 565463789:
                                    if (currentName.equals("userWatchSessionId")) {
                                        b = 16;
                                    }
                                    break;
                                case 936941091:
                                    if (currentName.equals("playbackSettingsFormatVersion")) {
                                        b = 7;
                                    }
                                    break;
                                case 1022101555:
                                    if (currentName.equals("ssaiStitchType")) {
                                        b = 23;
                                    }
                                    break;
                                case 1307785717:
                                    if (currentName.equals("optOutAdTracking")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 1348816619:
                                    if (currentName.equals("fairplayChallenge")) {
                                        b = 1;
                                    }
                                    break;
                                case 1380533835:
                                    if (currentName.equals("playReadyKeyId")) {
                                        b = 5;
                                    }
                                    break;
                                case 1543299682:
                                    if (currentName.equals("fairplayKeyId")) {
                                        b = 27;
                                    }
                                    break;
                                case 1805655088:
                                    if (currentName.equals("xrayPlaybackMode")) {
                                        b = 21;
                                    }
                                    break;
                                case 1927331375:
                                    if (currentName.equals("subtitleFormat")) {
                                        b = 20;
                                    }
                                    break;
                                case 2016594594:
                                    if (currentName.equals("gdprEnabled")) {
                                        b = 0;
                                    }
                                    break;
                                case 2029052882:
                                    if (currentName.equals("daiLiveManifestTypes")) {
                                        b = 8;
                                    }
                                    break;
                                case 2062847102:
                                    if (currentName.equals("languageFeature")) {
                                        b = 6;
                                    }
                                    break;
                                case 2085309677:
                                    if (currentName.equals("titleDecorationScheme")) {
                                        b = 18;
                                    }
                                    break;
                            }
                            Integer num = null;
                            String str = null;
                            String str2 = null;
                            Boolean bool = null;
                            ImmutableList<SsaiSegmentInfoType> immutableList = null;
                            SsaiStitchType ssaiStitchType = null;
                            String str3 = null;
                            String str4 = null;
                            SubtitleFormat subtitleFormat = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            ByteBuffer byteBuffer = null;
                            String str9 = null;
                            String str10 = null;
                            ImmutableList<LiveManifestType> immutableList2 = null;
                            String str11 = null;
                            String str12 = null;
                            ImmutableList<daiLiveManifestType> immutableList3 = null;
                            String str13 = null;
                            LanguageFeature languageFeature = null;
                            String str14 = null;
                            String str15 = null;
                            String str16 = null;
                            String str17 = null;
                            ByteBuffer byteBuffer2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mintegerTargetParser.parse(jsonParser);
                                    }
                                    builder.gdprEnabled = num;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        byteBuffer2 = this.mblobTargetParser.parse(jsonParser);
                                    }
                                    builder.fairplayChallenge = byteBuffer2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str17 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.liveScheduleConfigToken = str17;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str16 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.xrayDeviceClass = str16;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str15 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.linearRestrictionEnd = str15;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str14 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playReadyKeyId = str14;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        languageFeature = (LanguageFeature) this.mLanguageFeatureParser.parse(jsonParser);
                                    }
                                    builder.languageFeature = languageFeature;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str13 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playbackSettingsFormatVersion = str13;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList3 = this.mdaiLiveManifestTypeListParser.parse(jsonParser);
                                    }
                                    builder.daiLiveManifestTypes = immutableList3;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str12 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.gdprConsent = str12;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str11 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.linearRestrictionStart = str11;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mliveManifestTypeListParser.parse(jsonParser);
                                    }
                                    builder.liveManifestTypes = immutableList2;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.gdprConsentAVL = str10;
                                    continue;
                                case 13:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field optOutAdTracking can't be null");
                                    }
                                    builder.optOutAdTracking = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    continue;
                                    break;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.playReadyChallenge = str9;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        byteBuffer = this.mblobTargetParser.parse(jsonParser);
                                    }
                                    builder.widevine2Challenge = byteBuffer;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.userWatchSessionId = str8;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.vastTimelineType = str7;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.titleDecorationScheme = str6;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.drmSessionId = str5;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        subtitleFormat = (SubtitleFormat) this.mSubtitleFormatParser.parse(jsonParser);
                                    }
                                    builder.subtitleFormat = subtitleFormat;
                                    continue;
                                case 21:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.xrayPlaybackMode = str4;
                                    continue;
                                case 22:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.advertisingId = str3;
                                    continue;
                                case 23:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        ssaiStitchType = (SsaiStitchType) this.mSsaiStitchTypeParser.parse(jsonParser);
                                    }
                                    builder.ssaiStitchType = ssaiStitchType;
                                    continue;
                                case 24:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mSsaiSegmentInfoListParser.parse(jsonParser);
                                    }
                                    builder.ssaiSegmentInfo = immutableList;
                                    continue;
                                case 25:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mbooleanTargetParser.parse(jsonParser);
                                    }
                                    builder.includeHdcpTestKeyInLicense = bool;
                                    continue;
                                case 26:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.xrayToken = str2;
                                    continue;
                                case 27:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.fairplayKeyId = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ResourceSpecificParameters so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ResourceSpecificParameters so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
