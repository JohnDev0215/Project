package com.amazon.atv.playbackauthority.service.model;

import com.amazon.atv.playbackauthority.service.AssetMaterialType;
import com.amazon.atv.playbackauthority.service.ConsumptionType;
import com.amazon.atv.playbackauthority.service.ContentType;
import com.amazon.atv.playbackauthority.service.DrmType;
import com.amazon.atv.playbackauthority.service.Resolution;
import com.amazon.atv.playbackauthority.service.VideoFeature;
import com.amazon.atv.playbackauthority.service.VideoQuality;
import com.amazon.atv.playbackauthority.service.model.AdConfiguration;
import com.amazon.atv.playbackauthority.service.model.Consumability;
import com.amazon.atv.playbackauthority.service.model.EntitlementMetadata;
import com.amazon.atv.playbackauthority.service.model.PlayerControlsConfiguration;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackConfiguration {
    public final Optional<AdConfiguration> adConfiguration;
    public final Optional<String> adTreatment;
    public final Optional<VideoQuality> assetBundleQuality;
    public final AssetMaterialType assetMaterialType;
    public final Optional<Date> bookmarkUpdateTime;
    public final Consumability consumability;
    public final Optional<EntitlementMetadata> consumedVia;
    public final Optional<Long> consumptionDurationSecond;
    public final ConsumptionType consumptionType;
    public final Optional<ContentType> contentType;
    public final Optional<Date> deleteAssetAfter;
    public final Optional<DrmType> drmType;

    @Deprecated
    public final Optional<String> legacyOfferAsin;
    public final Optional<Resolution> playbackResolution;
    public final Optional<PlayerControlsConfiguration> playerControlsConfig;
    public final Optional<Long> runtimeSec;
    public final Optional<String> tempRawContentType;
    public final Optional<Long> timecodeSec;
    public final Optional<ImmutableList<VideoFeature>> videoFeatures;
    public final VideoQuality videoQuality;
    public final String videoSourceId;

    private PlaybackConfiguration(Builder builder) {
        this.consumability = (Consumability) Preconditions.checkNotNull(builder.consumability, "Unexpected null field: consumability");
        this.playbackResolution = Optional.fromNullable(builder.playbackResolution);
        this.tempRawContentType = Optional.fromNullable(builder.tempRawContentType);
        this.bookmarkUpdateTime = Optional.fromNullable(builder.bookmarkUpdateTime);
        this.drmType = Optional.fromNullable(builder.drmType);
        this.consumptionType = (ConsumptionType) Preconditions.checkNotNull(builder.consumptionType, "Unexpected null field: consumptionType");
        this.adTreatment = Optional.fromNullable(builder.adTreatment);
        this.timecodeSec = Optional.fromNullable(builder.timecodeSec);
        this.legacyOfferAsin = Optional.fromNullable(builder.legacyOfferAsin);
        this.videoQuality = (VideoQuality) Preconditions.checkNotNull(builder.videoQuality, "Unexpected null field: videoQuality");
        this.runtimeSec = Optional.fromNullable(builder.runtimeSec);
        this.deleteAssetAfter = Optional.fromNullable(builder.deleteAssetAfter);
        this.videoFeatures = Optional.fromNullable(builder.videoFeatures);
        this.adConfiguration = Optional.fromNullable(builder.adConfiguration);
        this.playerControlsConfig = Optional.fromNullable(builder.playerControlsConfig);
        this.assetMaterialType = (AssetMaterialType) Preconditions.checkNotNull(builder.assetMaterialType, "Unexpected null field: assetMaterialType");
        this.assetBundleQuality = Optional.fromNullable(builder.assetBundleQuality);
        this.consumedVia = Optional.fromNullable(builder.consumedVia);
        this.consumptionDurationSecond = Optional.fromNullable(builder.consumptionDurationSecond);
        this.videoSourceId = (String) Preconditions.checkNotNull(builder.videoSourceId, "Unexpected null field: videoSourceId");
        this.contentType = Optional.fromNullable(builder.contentType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackConfiguration)) {
            return false;
        }
        PlaybackConfiguration playbackConfiguration = (PlaybackConfiguration) obj;
        return Objects.equal(this.consumability, playbackConfiguration.consumability) && Objects.equal(this.playbackResolution, playbackConfiguration.playbackResolution) && Objects.equal(this.tempRawContentType, playbackConfiguration.tempRawContentType) && Objects.equal(this.bookmarkUpdateTime, playbackConfiguration.bookmarkUpdateTime) && Objects.equal(this.drmType, playbackConfiguration.drmType) && Objects.equal(this.consumptionType, playbackConfiguration.consumptionType) && Objects.equal(this.adTreatment, playbackConfiguration.adTreatment) && Objects.equal(this.timecodeSec, playbackConfiguration.timecodeSec) && Objects.equal(this.legacyOfferAsin, playbackConfiguration.legacyOfferAsin) && Objects.equal(this.videoQuality, playbackConfiguration.videoQuality) && Objects.equal(this.runtimeSec, playbackConfiguration.runtimeSec) && Objects.equal(this.deleteAssetAfter, playbackConfiguration.deleteAssetAfter) && Objects.equal(this.videoFeatures, playbackConfiguration.videoFeatures) && Objects.equal(this.adConfiguration, playbackConfiguration.adConfiguration) && Objects.equal(this.playerControlsConfig, playbackConfiguration.playerControlsConfig) && Objects.equal(this.assetMaterialType, playbackConfiguration.assetMaterialType) && Objects.equal(this.assetBundleQuality, playbackConfiguration.assetBundleQuality) && Objects.equal(this.consumedVia, playbackConfiguration.consumedVia) && Objects.equal(this.consumptionDurationSecond, playbackConfiguration.consumptionDurationSecond) && Objects.equal(this.videoSourceId, playbackConfiguration.videoSourceId) && Objects.equal(this.contentType, playbackConfiguration.contentType);
    }

    public int hashCode() {
        return Objects.hashCode(this.consumability, this.playbackResolution, this.tempRawContentType, this.bookmarkUpdateTime, this.drmType, this.consumptionType, this.adTreatment, this.timecodeSec, this.legacyOfferAsin, this.videoQuality, this.runtimeSec, this.deleteAssetAfter, this.videoFeatures, this.adConfiguration, this.playerControlsConfig, this.assetMaterialType, this.assetBundleQuality, this.consumedVia, this.consumptionDurationSecond, this.videoSourceId, this.contentType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("consumability", this.consumability).add("playbackResolution", this.playbackResolution).add("tempRawContentType", this.tempRawContentType).add("bookmarkUpdateTime", this.bookmarkUpdateTime).add("drmType", this.drmType).add("consumptionType", this.consumptionType).add("adTreatment", this.adTreatment).add("timecodeSec", this.timecodeSec).add("legacyOfferAsin", this.legacyOfferAsin).add("videoQuality", this.videoQuality).add("runtimeSec", this.runtimeSec).add("deleteAssetAfter", this.deleteAssetAfter).add("videoFeatures", this.videoFeatures).add("adConfiguration", this.adConfiguration).add("playerControlsConfig", this.playerControlsConfig).add("assetMaterialType", this.assetMaterialType).add("assetBundleQuality", this.assetBundleQuality).add("consumedVia", this.consumedVia).add("consumptionDurationSecond", this.consumptionDurationSecond).add("videoSourceId", this.videoSourceId).add("contentType", this.contentType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AdConfiguration adConfiguration;
        public String adTreatment;
        public VideoQuality assetBundleQuality;
        public AssetMaterialType assetMaterialType;
        public Date bookmarkUpdateTime;
        public Consumability consumability;
        public EntitlementMetadata consumedVia;
        public Long consumptionDurationSecond;
        public ConsumptionType consumptionType;
        public ContentType contentType;
        public Date deleteAssetAfter;
        public DrmType drmType;

        @Deprecated
        public String legacyOfferAsin;
        public Resolution playbackResolution;
        public PlayerControlsConfiguration playerControlsConfig;
        public Long runtimeSec;
        public String tempRawContentType;
        public Long timecodeSec;
        public ImmutableList<VideoFeature> videoFeatures;
        public VideoQuality videoQuality;
        public String videoSourceId;

        public PlaybackConfiguration build() {
            return new PlaybackConfiguration(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackConfiguration> {
        private final AdConfiguration.Parser mAdConfigurationParser;
        private final EnumParser<AssetMaterialType> mAssetMaterialTypeParser;
        private final Consumability.Parser mConsumabilityParser;
        private final EnumParser<ConsumptionType> mConsumptionTypeParser;
        private final EnumParser<ContentType> mContentTypeParser;
        private final EnumParser<DrmType> mDrmTypeParser;
        private final EntitlementMetadata.Parser mEntitlementMetadataParser;
        private final PlayerControlsConfiguration.Parser mPlayerControlsConfigurationParser;
        private final EnumParser<Resolution> mResolutionParser;
        private final SimpleParsers.StringParser mTempRawContentTypeStringParser;
        private final ListParser<VideoFeature> mVideoFeaturesListParser;
        private final EnumParser<VideoQuality> mVideoQualityParser;
        private final SimpleParsers.StringParser madTreatmentStringParser;
        private final SimpleParsers.DateParser mbookmarkUpdateTimestampParser;
        private final SimpleParsers.LongParser mconsumptionDurationLongParser;
        private final SimpleParsers.DateParser mdeleteAssetAfterTimeStampParser;
        private final SimpleParsers.StringParser mlegacyOfferAsinStringParser;
        private final SimpleParsers.LongParser mruntimeSecLongParser;
        private final SimpleParsers.LongParser mtimecodeSecLongParser;
        private final SimpleParsers.StringParser mvideoSourceIdStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mConsumabilityParser = new Consumability.Parser(objectMapper);
            this.mConsumptionTypeParser = EnumParser.newParser(ConsumptionType.class);
            this.mEntitlementMetadataParser = new EntitlementMetadata.Parser(objectMapper);
            SimpleParsers.DateParser dateParser = SimpleParsers.DateParser.INSTANCE;
            this.mbookmarkUpdateTimestampParser = dateParser;
            this.mdeleteAssetAfterTimeStampParser = dateParser;
            SimpleParsers.LongParser longParser = SimpleParsers.LongParser.INSTANCE;
            this.mtimecodeSecLongParser = longParser;
            this.mruntimeSecLongParser = longParser;
            this.mconsumptionDurationLongParser = longParser;
            this.mAdConfigurationParser = new AdConfiguration.Parser(objectMapper);
            this.mPlayerControlsConfigurationParser = new PlayerControlsConfiguration.Parser(objectMapper);
            this.mResolutionParser = EnumParser.newParser(Resolution.class);
            this.mVideoFeaturesListParser = ListParser.newParser(EnumParser.newParser(VideoFeature.class));
            this.mAssetMaterialTypeParser = EnumParser.newParser(AssetMaterialType.class);
            this.mVideoQualityParser = EnumParser.newParser(VideoQuality.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTempRawContentTypeStringParser = stringParser;
            this.madTreatmentStringParser = stringParser;
            this.mlegacyOfferAsinStringParser = stringParser;
            this.mvideoSourceIdStringParser = stringParser;
            this.mContentTypeParser = EnumParser.newParser(ContentType.class);
            this.mDrmTypeParser = EnumParser.newParser(DrmType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackConfiguration parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackConfiguration:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackConfiguration parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackConfiguration:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackConfiguration parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Consumability consumability;
            Resolution resolution;
            String str;
            Date date;
            DrmType drmType;
            ConsumptionType consumptionType;
            String str2;
            Long l;
            String str3;
            VideoQuality videoQuality;
            Long l2;
            Date date2;
            ImmutableList<VideoFeature> immutableList;
            AdConfiguration adConfiguration;
            PlayerControlsConfiguration playerControlsConfiguration;
            AssetMaterialType assetMaterialType;
            VideoQuality videoQuality2;
            EntitlementMetadata entitlementMetadata;
            Long l3;
            String str4;
            ContentType contentType;
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackConfiguration");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -858823930:
                            if (next.equals("consumedVia")) {
                                b = 17;
                            }
                            break;
                        case -659233025:
                            if (next.equals("tempRawContentType")) {
                                b = 2;
                            }
                            break;
                        case -594463862:
                            if (next.equals("legacyOfferAsin")) {
                                b = 8;
                            }
                            break;
                        case -557534760:
                            if (next.equals("videoFeatures")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case -529840807:
                            if (next.equals("runtimeSec")) {
                                b = 10;
                            }
                            break;
                        case -389131437:
                            if (next.equals("contentType")) {
                                b = 20;
                            }
                            break;
                        case -375871085:
                            if (next.equals("adConfiguration")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -33172463:
                            if (next.equals("assetMaterialType")) {
                                b = 15;
                            }
                            break;
                        case -19034185:
                            if (next.equals("timecodeSec")) {
                                b = 7;
                            }
                            break;
                        case 9266049:
                            if (next.equals("consumability")) {
                                b = 0;
                            }
                            break;
                        case 368095605:
                            if (next.equals("consumptionType")) {
                                b = 5;
                            }
                            break;
                        case 465601831:
                            if (next.equals("playbackResolution")) {
                                b = 1;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 9;
                            }
                            break;
                        case 580782295:
                            if (next.equals("deleteAssetAfter")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 667131609:
                            if (next.equals("playerControlsConfig")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 795437324:
                            if (next.equals("bookmarkUpdateTime")) {
                                b = 3;
                            }
                            break;
                        case 1258521493:
                            if (next.equals("adTreatment")) {
                                b = 6;
                            }
                            break;
                        case 1406696717:
                            if (next.equals("assetBundleQuality")) {
                                b = 16;
                            }
                            break;
                        case 1413338019:
                            if (next.equals("consumptionDurationSecond")) {
                                b = 18;
                            }
                            break;
                        case 1479451697:
                            if (next.equals("videoSourceId")) {
                                b = 19;
                            }
                            break;
                        case 1923064185:
                            if (next.equals("drmType")) {
                                b = 4;
                            }
                            break;
                    }
                    consumability = null;
                    contentType = null;
                    str4 = null;
                    l3 = null;
                    entitlementMetadata = null;
                    videoQuality2 = null;
                    assetMaterialType = null;
                    playerControlsConfiguration = null;
                    adConfiguration = null;
                    immutableList = null;
                    date2 = null;
                    l2 = null;
                    videoQuality = null;
                    str3 = null;
                    l = null;
                    str2 = null;
                    consumptionType = null;
                    drmType = null;
                    date = null;
                    str = null;
                    resolution = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackConfiguration so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            consumability = this.mConsumabilityParser.parse(jsonNode2);
                        }
                        builder.consumability = consumability;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            resolution = (Resolution) this.mResolutionParser.parse(jsonNode2);
                        }
                        builder.playbackResolution = resolution;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mTempRawContentTypeStringParser.parse(jsonNode2);
                        }
                        builder.tempRawContentType = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            date = this.mbookmarkUpdateTimestampParser.parse(jsonNode2);
                        }
                        builder.bookmarkUpdateTime = date;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            drmType = (DrmType) this.mDrmTypeParser.parse(jsonNode2);
                        }
                        builder.drmType = drmType;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            consumptionType = (ConsumptionType) this.mConsumptionTypeParser.parse(jsonNode2);
                        }
                        builder.consumptionType = consumptionType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str2 = this.madTreatmentStringParser.parse(jsonNode2);
                        }
                        builder.adTreatment = str2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            l = this.mtimecodeSecLongParser.parse(jsonNode2);
                        }
                        builder.timecodeSec = l;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mlegacyOfferAsinStringParser.parse(jsonNode2);
                        }
                        builder.legacyOfferAsin = str3;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            l2 = this.mruntimeSecLongParser.parse(jsonNode2);
                        }
                        builder.runtimeSec = l2;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            date2 = this.mdeleteAssetAfterTimeStampParser.parse(jsonNode2);
                        }
                        builder.deleteAssetAfter = date2;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mVideoFeaturesListParser.parse(jsonNode2);
                        }
                        builder.videoFeatures = immutableList;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            adConfiguration = this.mAdConfigurationParser.parse(jsonNode2);
                        }
                        builder.adConfiguration = adConfiguration;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            playerControlsConfiguration = this.mPlayerControlsConfigurationParser.parse(jsonNode2);
                        }
                        builder.playerControlsConfig = playerControlsConfiguration;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            assetMaterialType = (AssetMaterialType) this.mAssetMaterialTypeParser.parse(jsonNode2);
                        }
                        builder.assetMaterialType = assetMaterialType;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            videoQuality2 = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.assetBundleQuality = videoQuality2;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            entitlementMetadata = this.mEntitlementMetadataParser.parse(jsonNode2);
                        }
                        builder.consumedVia = entitlementMetadata;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            l3 = this.mconsumptionDurationLongParser.parse(jsonNode2);
                        }
                        builder.consumptionDurationSecond = l3;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mvideoSourceIdStringParser.parse(jsonNode2);
                        }
                        builder.videoSourceId = str4;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            contentType = (ContentType) this.mContentTypeParser.parse(jsonNode2);
                        }
                        builder.contentType = contentType;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackConfiguration so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.consumability, this, "consumability");
            JsonParsingUtils.checkNotNull(builder.consumptionType, this, "consumptionType");
            JsonParsingUtils.checkNotNull(builder.videoQuality, this, "videoQuality");
            JsonParsingUtils.checkNotNull(builder.assetMaterialType, this, "assetMaterialType");
            JsonParsingUtils.checkNotNull(builder.videoSourceId, this, "videoSourceId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackConfiguration parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -858823930:
                                    if (currentName.equals("consumedVia")) {
                                        b = 17;
                                    }
                                    break;
                                case -659233025:
                                    if (currentName.equals("tempRawContentType")) {
                                        b = 2;
                                    }
                                    break;
                                case -594463862:
                                    if (currentName.equals("legacyOfferAsin")) {
                                        b = 8;
                                    }
                                    break;
                                case -557534760:
                                    if (currentName.equals("videoFeatures")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case -529840807:
                                    if (currentName.equals("runtimeSec")) {
                                        b = 10;
                                    }
                                    break;
                                case -389131437:
                                    if (currentName.equals("contentType")) {
                                        b = 20;
                                    }
                                    break;
                                case -375871085:
                                    if (currentName.equals("adConfiguration")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -33172463:
                                    if (currentName.equals("assetMaterialType")) {
                                        b = 15;
                                    }
                                    break;
                                case -19034185:
                                    if (currentName.equals("timecodeSec")) {
                                        b = 7;
                                    }
                                    break;
                                case 9266049:
                                    if (currentName.equals("consumability")) {
                                        b = 0;
                                    }
                                    break;
                                case 368095605:
                                    if (currentName.equals("consumptionType")) {
                                        b = 5;
                                    }
                                    break;
                                case 465601831:
                                    if (currentName.equals("playbackResolution")) {
                                        b = 1;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 9;
                                    }
                                    break;
                                case 580782295:
                                    if (currentName.equals("deleteAssetAfter")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 667131609:
                                    if (currentName.equals("playerControlsConfig")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 795437324:
                                    if (currentName.equals("bookmarkUpdateTime")) {
                                        b = 3;
                                    }
                                    break;
                                case 1258521493:
                                    if (currentName.equals("adTreatment")) {
                                        b = 6;
                                    }
                                    break;
                                case 1406696717:
                                    if (currentName.equals("assetBundleQuality")) {
                                        b = 16;
                                    }
                                    break;
                                case 1413338019:
                                    if (currentName.equals("consumptionDurationSecond")) {
                                        b = 18;
                                    }
                                    break;
                                case 1479451697:
                                    if (currentName.equals("videoSourceId")) {
                                        b = 19;
                                    }
                                    break;
                                case 1923064185:
                                    if (currentName.equals("drmType")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            Consumability consumability = null;
                            ContentType contentType = null;
                            String str = null;
                            Long l = null;
                            EntitlementMetadata entitlementMetadata = null;
                            VideoQuality videoQuality = null;
                            AssetMaterialType assetMaterialType = null;
                            PlayerControlsConfiguration playerControlsConfiguration = null;
                            AdConfiguration adConfiguration = null;
                            ImmutableList<VideoFeature> immutableList = null;
                            Date date = null;
                            Long l2 = null;
                            VideoQuality videoQuality2 = null;
                            String str2 = null;
                            Long l3 = null;
                            String str3 = null;
                            ConsumptionType consumptionType = null;
                            DrmType drmType = null;
                            Date date2 = null;
                            String str4 = null;
                            Resolution resolution = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        consumability = this.mConsumabilityParser.parse(jsonParser);
                                    }
                                    builder.consumability = consumability;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        resolution = (Resolution) this.mResolutionParser.parse(jsonParser);
                                    }
                                    builder.playbackResolution = resolution;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mTempRawContentTypeStringParser.parse(jsonParser);
                                    }
                                    builder.tempRawContentType = str4;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        date2 = this.mbookmarkUpdateTimestampParser.parse(jsonParser);
                                    }
                                    builder.bookmarkUpdateTime = date2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        drmType = (DrmType) this.mDrmTypeParser.parse(jsonParser);
                                    }
                                    builder.drmType = drmType;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        consumptionType = (ConsumptionType) this.mConsumptionTypeParser.parse(jsonParser);
                                    }
                                    builder.consumptionType = consumptionType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.madTreatmentStringParser.parse(jsonParser);
                                    }
                                    builder.adTreatment = str3;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l3 = this.mtimecodeSecLongParser.parse(jsonParser);
                                    }
                                    builder.timecodeSec = l3;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mlegacyOfferAsinStringParser.parse(jsonParser);
                                    }
                                    builder.legacyOfferAsin = str2;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality2 = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = videoQuality2;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l2 = this.mruntimeSecLongParser.parse(jsonParser);
                                    }
                                    builder.runtimeSec = l2;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        date = this.mdeleteAssetAfterTimeStampParser.parse(jsonParser);
                                    }
                                    builder.deleteAssetAfter = date;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mVideoFeaturesListParser.parse(jsonParser);
                                    }
                                    builder.videoFeatures = immutableList;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        adConfiguration = this.mAdConfigurationParser.parse(jsonParser);
                                    }
                                    builder.adConfiguration = adConfiguration;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playerControlsConfiguration = this.mPlayerControlsConfigurationParser.parse(jsonParser);
                                    }
                                    builder.playerControlsConfig = playerControlsConfiguration;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        assetMaterialType = (AssetMaterialType) this.mAssetMaterialTypeParser.parse(jsonParser);
                                    }
                                    builder.assetMaterialType = assetMaterialType;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                    }
                                    builder.assetBundleQuality = videoQuality;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        entitlementMetadata = this.mEntitlementMetadataParser.parse(jsonParser);
                                    }
                                    builder.consumedVia = entitlementMetadata;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mconsumptionDurationLongParser.parse(jsonParser);
                                    }
                                    builder.consumptionDurationSecond = l;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mvideoSourceIdStringParser.parse(jsonParser);
                                    }
                                    builder.videoSourceId = str;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        contentType = (ContentType) this.mContentTypeParser.parse(jsonParser);
                                    }
                                    builder.contentType = contentType;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackConfiguration so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackConfiguration so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.consumability, this, "consumability");
                    JsonParsingUtils.checkNotNull(builder.consumptionType, this, "consumptionType");
                    JsonParsingUtils.checkNotNull(builder.videoQuality, this, "videoQuality");
                    JsonParsingUtils.checkNotNull(builder.assetMaterialType, this, "assetMaterialType");
                    JsonParsingUtils.checkNotNull(builder.videoSourceId, this, "videoSourceId");
                    return builder.build();
                }
            }
        }
    }
}
