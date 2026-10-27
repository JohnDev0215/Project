package com.amazon.atvplaybackresource;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atv.parentalcontrols.GetLinearRestrictionsResponse;
import com.amazon.atv.schedule.ChannelSchedule;
import com.amazon.atv.title.Title;
import com.amazon.atvbookmarking.TitleBookmark;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackresource.AudioVideoUrls;
import com.amazon.atvplaybackresource.DebugInfo;
import com.amazon.atvplaybackresource.DevicePlaybackConfiguration;
import com.amazon.atvplaybackresource.FairPlayApplicationCertificate;
import com.amazon.atvplaybackresource.FairPlayLicense;
import com.amazon.atvplaybackresource.LegacyMetadata;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.PlayReadyLicense;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.atvplaybackresource.ResponseTitleRendition;
import com.amazon.atvplaybackresource.SubtitleUrlLanguageOption;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.atvplaybackresource.TrickplayUrls;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseResource;
import com.amazon.atvsubtitlepresets.GetPresetsOutput;
import com.amazon.atvtransitiontimecodeservice.TTSResponse;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.XRayFragmentBase;
import com.amazon.urlvending.PlaybackUrls;
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

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesResponse {
    public final Optional<AudioVideoUrls> audioVideoUrls;
    public final Optional<Title> catalogMetadata;
    public final Optional<CuepointPlaylist> cuepointPlaylist;
    public final Optional<ImmutableMap<String, DebugInfo>> debugInfos;
    public final Optional<DevicePlaybackConfiguration> devicePlaybackConfiguration;
    public final Optional<PRSException> error;
    public final Optional<ImmutableMap<Resource, PRSException>> errorsByResource;
    public final Optional<FairPlayApplicationCertificate> fairPlayApplicationCertificate;
    public final Optional<FairPlayLicense> fairPlayLicense;
    public final Optional<ImmutableList<SubtitleUrlLanguageOption>> forcedNarratives;
    public final Optional<LegacyMetadata> legacyMetadata;
    public final Optional<GetLinearRestrictionsResponse> linearRestrictions;
    public final Optional<ChannelSchedule> liveSchedule;
    public final Optional<PlayReadyLicense> playReadyLicense;
    public final Optional<PlaybackSettings> playbackSettings;
    public final Optional<PlaybackUrls> playbackUrls;
    public final Optional<ResponseTitleRendition> returnedTitleRendition;
    public final Optional<GetPresetsOutput> subtitlePresets;
    public final Optional<ImmutableList<SubtitleUrlLanguageOption>> subtitleUrls;
    public final Optional<SyeUrlResponse> syeUrlsV2;
    public final Optional<TTSResponse> transitionTimecodes;
    public final Optional<TrickplayUrls> trickplayUrls;
    public final Optional<TitleBookmark> whisperSyncBookmark;
    public final Optional<WidevineLicenseResource> widevine2License;
    public final Optional<XRayFragmentBase> xrayMetadata;

    private PlaybackResourcesResponse(Builder builder) {
        this.widevine2License = Optional.fromNullable(builder.widevine2License);
        this.subtitleUrls = Optional.fromNullable(builder.subtitleUrls);
        this.whisperSyncBookmark = Optional.fromNullable(builder.whisperSyncBookmark);
        this.error = Optional.fromNullable(builder.error);
        this.playbackUrls = Optional.fromNullable(builder.playbackUrls);
        this.legacyMetadata = Optional.fromNullable(builder.legacyMetadata);
        this.xrayMetadata = Optional.fromNullable(builder.xrayMetadata);
        this.forcedNarratives = Optional.fromNullable(builder.forcedNarratives);
        this.errorsByResource = Optional.fromNullable(builder.errorsByResource);
        this.fairPlayLicense = Optional.fromNullable(builder.fairPlayLicense);
        this.catalogMetadata = Optional.fromNullable(builder.catalogMetadata);
        this.transitionTimecodes = Optional.fromNullable(builder.transitionTimecodes);
        this.cuepointPlaylist = Optional.fromNullable(builder.cuepointPlaylist);
        this.syeUrlsV2 = Optional.fromNullable(builder.syeUrlsV2);
        this.fairPlayApplicationCertificate = Optional.fromNullable(builder.fairPlayApplicationCertificate);
        this.liveSchedule = Optional.fromNullable(builder.liveSchedule);
        this.playReadyLicense = Optional.fromNullable(builder.playReadyLicense);
        this.linearRestrictions = Optional.fromNullable(builder.linearRestrictions);
        this.subtitlePresets = Optional.fromNullable(builder.subtitlePresets);
        this.returnedTitleRendition = Optional.fromNullable(builder.returnedTitleRendition);
        this.trickplayUrls = Optional.fromNullable(builder.trickplayUrls);
        this.audioVideoUrls = Optional.fromNullable(builder.audioVideoUrls);
        this.devicePlaybackConfiguration = Optional.fromNullable(builder.devicePlaybackConfiguration);
        this.playbackSettings = Optional.fromNullable(builder.playbackSettings);
        this.debugInfos = Optional.fromNullable(builder.debugInfos);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackResourcesResponse)) {
            return false;
        }
        PlaybackResourcesResponse playbackResourcesResponse = (PlaybackResourcesResponse) obj;
        return Objects.equal(this.widevine2License, playbackResourcesResponse.widevine2License) && Objects.equal(this.subtitleUrls, playbackResourcesResponse.subtitleUrls) && Objects.equal(this.whisperSyncBookmark, playbackResourcesResponse.whisperSyncBookmark) && Objects.equal(this.error, playbackResourcesResponse.error) && Objects.equal(this.playbackUrls, playbackResourcesResponse.playbackUrls) && Objects.equal(this.legacyMetadata, playbackResourcesResponse.legacyMetadata) && Objects.equal(this.xrayMetadata, playbackResourcesResponse.xrayMetadata) && Objects.equal(this.forcedNarratives, playbackResourcesResponse.forcedNarratives) && Objects.equal(this.errorsByResource, playbackResourcesResponse.errorsByResource) && Objects.equal(this.fairPlayLicense, playbackResourcesResponse.fairPlayLicense) && Objects.equal(this.catalogMetadata, playbackResourcesResponse.catalogMetadata) && Objects.equal(this.transitionTimecodes, playbackResourcesResponse.transitionTimecodes) && Objects.equal(this.cuepointPlaylist, playbackResourcesResponse.cuepointPlaylist) && Objects.equal(this.syeUrlsV2, playbackResourcesResponse.syeUrlsV2) && Objects.equal(this.fairPlayApplicationCertificate, playbackResourcesResponse.fairPlayApplicationCertificate) && Objects.equal(this.liveSchedule, playbackResourcesResponse.liveSchedule) && Objects.equal(this.playReadyLicense, playbackResourcesResponse.playReadyLicense) && Objects.equal(this.linearRestrictions, playbackResourcesResponse.linearRestrictions) && Objects.equal(this.subtitlePresets, playbackResourcesResponse.subtitlePresets) && Objects.equal(this.returnedTitleRendition, playbackResourcesResponse.returnedTitleRendition) && Objects.equal(this.trickplayUrls, playbackResourcesResponse.trickplayUrls) && Objects.equal(this.audioVideoUrls, playbackResourcesResponse.audioVideoUrls) && Objects.equal(this.devicePlaybackConfiguration, playbackResourcesResponse.devicePlaybackConfiguration) && Objects.equal(this.playbackSettings, playbackResourcesResponse.playbackSettings) && Objects.equal(this.debugInfos, playbackResourcesResponse.debugInfos);
    }

    public int hashCode() {
        return Objects.hashCode(this.widevine2License, this.subtitleUrls, this.whisperSyncBookmark, this.error, this.playbackUrls, this.legacyMetadata, this.xrayMetadata, this.forcedNarratives, this.errorsByResource, this.fairPlayLicense, this.catalogMetadata, this.transitionTimecodes, this.cuepointPlaylist, this.syeUrlsV2, this.fairPlayApplicationCertificate, this.liveSchedule, this.playReadyLicense, this.linearRestrictions, this.subtitlePresets, this.returnedTitleRendition, this.trickplayUrls, this.audioVideoUrls, this.devicePlaybackConfiguration, this.playbackSettings, this.debugInfos);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("widevine2License", this.widevine2License).add("subtitleUrls", this.subtitleUrls).add("whisperSyncBookmark", this.whisperSyncBookmark).add("error", this.error).add("playbackUrls", this.playbackUrls).add("legacyMetadata", this.legacyMetadata).add("xrayMetadata", this.xrayMetadata).add("forcedNarratives", this.forcedNarratives).add("errorsByResource", this.errorsByResource).add("fairPlayLicense", this.fairPlayLicense).add("catalogMetadata", this.catalogMetadata).add("transitionTimecodes", this.transitionTimecodes).add("cuepointPlaylist", this.cuepointPlaylist).add("syeUrlsV2", this.syeUrlsV2).add("fairPlayApplicationCertificate", this.fairPlayApplicationCertificate).add("liveSchedule", this.liveSchedule).add("playReadyLicense", this.playReadyLicense).add("linearRestrictions", this.linearRestrictions).add("subtitlePresets", this.subtitlePresets).add("returnedTitleRendition", this.returnedTitleRendition).add("trickplayUrls", this.trickplayUrls).add("audioVideoUrls", this.audioVideoUrls).add("devicePlaybackConfiguration", this.devicePlaybackConfiguration).add("playbackSettings", this.playbackSettings).add("debugInfos", this.debugInfos).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AudioVideoUrls audioVideoUrls;
        public Title catalogMetadata;
        public CuepointPlaylist cuepointPlaylist;
        public ImmutableMap<String, DebugInfo> debugInfos;
        public DevicePlaybackConfiguration devicePlaybackConfiguration;
        public PRSException error;
        public ImmutableMap<Resource, PRSException> errorsByResource;
        public FairPlayApplicationCertificate fairPlayApplicationCertificate;
        public FairPlayLicense fairPlayLicense;
        public ImmutableList<SubtitleUrlLanguageOption> forcedNarratives;
        public LegacyMetadata legacyMetadata;
        public GetLinearRestrictionsResponse linearRestrictions;
        public ChannelSchedule liveSchedule;
        public PlayReadyLicense playReadyLicense;
        public PlaybackSettings playbackSettings;
        public PlaybackUrls playbackUrls;
        public ResponseTitleRendition returnedTitleRendition;
        public GetPresetsOutput subtitlePresets;
        public ImmutableList<SubtitleUrlLanguageOption> subtitleUrls;
        public SyeUrlResponse syeUrlsV2;
        public TTSResponse transitionTimecodes;
        public TrickplayUrls trickplayUrls;
        public TitleBookmark whisperSyncBookmark;
        public WidevineLicenseResource widevine2License;
        public XRayFragmentBase xrayMetadata;

        public PlaybackResourcesResponse build() {
            return new PlaybackResourcesResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackResourcesResponse> {
        private final AudioVideoUrls.Parser mAudioVideoUrlsParser;
        private final ChannelSchedule.Parser mChannelScheduleParser;
        private final CuepointPlaylist.Parser mCuepointPlaylistParser;
        private final MapParser<String, DebugInfo> mDebugInfoListParser;
        private final DevicePlaybackConfiguration.Parser mDevicePlaybackConfigurationParser;
        private final FairPlayApplicationCertificate.Parser mFairPlayApplicationCertificateParser;
        private final FairPlayLicense.Parser mFairPlayLicenseParser;
        private final GetLinearRestrictionsResponse.Parser mGetLinearRestrictionsResponseParser;
        private final GetPresetsOutput.Parser mGetPresetsOutputParser;
        private final LegacyMetadata.Parser mLegacyMetadataParser;
        private final PRSException.Parser mPRSExceptionParser;
        private final PlayReadyLicense.Parser mPlayReadyLicenseParser;
        private final PlaybackSettings.Parser mPlaybackSettingsParser;
        private final PlaybackUrls.Parser mPlaybackUrlsParser;
        private final ResponseTitleRendition.Parser mResponseTitleRenditionParser;
        private final ListParser<SubtitleUrlLanguageOption> mSubtitleUrlsLanguageListParser;
        private final SyeUrlResponse.Parser mSyeUrlResponseParser;
        private final TTSResponse.Parser mTTSResponseParser;
        private final TitleBookmark.Parser mTitleBookmarkParser;
        private final Title.Parser mTitleParser;
        private final TrickplayUrls.Parser mTrickplayUrlsParser;
        private final WidevineLicenseResource.Parser mWidevineLicenseResourceParser;
        private final XRayFragmentBase.Parser mXRayFragmentBaseParser;
        private final MapParser<Resource, PRSException> mexceptionMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mChannelScheduleParser = new ChannelSchedule.Parser(objectMapper);
            this.mPlayReadyLicenseParser = new PlayReadyLicense.Parser(objectMapper);
            this.mTitleBookmarkParser = new TitleBookmark.Parser(objectMapper);
            this.mAudioVideoUrlsParser = new AudioVideoUrls.Parser(objectMapper);
            this.mGetLinearRestrictionsResponseParser = new GetLinearRestrictionsResponse.Parser(objectMapper);
            this.mGetPresetsOutputParser = new GetPresetsOutput.Parser(objectMapper);
            this.mXRayFragmentBaseParser = new XRayFragmentBase.Parser(objectMapper);
            this.mLegacyMetadataParser = new LegacyMetadata.Parser(objectMapper);
            this.mTitleParser = new Title.Parser(objectMapper);
            this.mTrickplayUrlsParser = new TrickplayUrls.Parser(objectMapper);
            this.mPlaybackSettingsParser = new PlaybackSettings.Parser(objectMapper);
            this.mFairPlayApplicationCertificateParser = new FairPlayApplicationCertificate.Parser(objectMapper);
            this.mFairPlayLicenseParser = new FairPlayLicense.Parser(objectMapper);
            this.mResponseTitleRenditionParser = new ResponseTitleRendition.Parser(objectMapper);
            this.mSubtitleUrlsLanguageListParser = ListParser.newParser(new SubtitleUrlLanguageOption.Parser(objectMapper));
            this.mWidevineLicenseResourceParser = new WidevineLicenseResource.Parser(objectMapper);
            this.mCuepointPlaylistParser = new CuepointPlaylist.Parser(objectMapper);
            this.mPlaybackUrlsParser = new PlaybackUrls.Parser(objectMapper);
            this.mexceptionMapParser = MapParser.newParser(EnumParser.newParser(Resource.class), new PRSException.Parser(objectMapper));
            this.mDebugInfoListParser = MapParser.newParser(SimpleParsers.StringParser.INSTANCE, new DebugInfo.Parser(objectMapper));
            this.mSyeUrlResponseParser = new SyeUrlResponse.Parser(objectMapper);
            this.mTTSResponseParser = new TTSResponse.Parser(objectMapper);
            this.mDevicePlaybackConfigurationParser = new DevicePlaybackConfiguration.Parser(objectMapper);
            this.mPRSExceptionParser = new PRSException.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackResourcesResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackResourcesResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackResourcesResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackResourcesResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackResourcesResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            WidevineLicenseResource widevineLicenseResource;
            ImmutableList<SubtitleUrlLanguageOption> immutableList;
            TitleBookmark titleBookmark;
            PRSException pRSException;
            PlaybackUrls playbackUrls;
            LegacyMetadata legacyMetadata;
            XRayFragmentBase xRayFragmentBase;
            ImmutableList<SubtitleUrlLanguageOption> immutableList2;
            ImmutableMap<Resource, PRSException> immutableMap;
            FairPlayLicense fairPlayLicense;
            Title title;
            TTSResponse tTSResponse;
            CuepointPlaylist cuepointPlaylist;
            SyeUrlResponse syeUrlResponse;
            FairPlayApplicationCertificate fairPlayApplicationCertificate;
            ChannelSchedule channelSchedule;
            PlayReadyLicense playReadyLicense;
            GetLinearRestrictionsResponse getLinearRestrictionsResponse;
            GetPresetsOutput getPresetsOutput;
            ResponseTitleRendition responseTitleRendition;
            TrickplayUrls trickplayUrls;
            AudioVideoUrls audioVideoUrls;
            DevicePlaybackConfiguration devicePlaybackConfiguration;
            PlaybackSettings playbackSettings;
            ImmutableMap<String, DebugInfo> immutableMap2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackResourcesResponse");
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
                                b = 1;
                            }
                            break;
                        case -1860720910:
                            if (next.equals("debugInfos")) {
                                b = 24;
                            }
                            break;
                        case -1619650968:
                            if (next.equals("catalogMetadata")) {
                                b = 10;
                            }
                            break;
                        case -1575353941:
                            if (next.equals("returnedTitleRendition")) {
                                b = 19;
                            }
                            break;
                        case -993422289:
                            if (next.equals("cuepointPlaylist")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case -813533723:
                            if (next.equals("devicePlaybackConfiguration")) {
                                b = 22;
                            }
                            break;
                        case -712844372:
                            if (next.equals("widevine2License")) {
                                b = 0;
                            }
                            break;
                        case -297271127:
                            if (next.equals("fairPlayLicense")) {
                                b = 9;
                            }
                            break;
                        case -212731398:
                            if (next.equals("forcedNarratives")) {
                                b = 7;
                            }
                            break;
                        case -198067732:
                            if (next.equals("linearRestrictions")) {
                                b = 17;
                            }
                            break;
                        case -23102916:
                            if (next.equals("subtitlePresets")) {
                                b = 18;
                            }
                            break;
                        case 96784904:
                            if (next.equals("error")) {
                                b = 3;
                            }
                            break;
                        case 274770782:
                            if (next.equals("playbackSettings")) {
                                b = 23;
                            }
                            break;
                        case 434542783:
                            if (next.equals("fairPlayApplicationCertificate")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 530671218:
                            if (next.equals("playReadyLicense")) {
                                b = 16;
                            }
                            break;
                        case 713468447:
                            if (next.equals("syeUrlsV2")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 714776979:
                            if (next.equals("whisperSyncBookmark")) {
                                b = 2;
                            }
                            break;
                        case 1138359203:
                            if (next.equals("liveSchedule")) {
                                b = 15;
                            }
                            break;
                        case 1350250320:
                            if (next.equals("errorsByResource")) {
                                b = 8;
                            }
                            break;
                        case 1355525535:
                            if (next.equals("playbackUrls")) {
                                b = 4;
                            }
                            break;
                        case 1546320408:
                            if (next.equals("legacyMetadata")) {
                                b = 5;
                            }
                            break;
                        case 1557868745:
                            if (next.equals("audioVideoUrls")) {
                                b = 21;
                            }
                            break;
                        case 1933916811:
                            if (next.equals("trickplayUrls")) {
                                b = 20;
                            }
                            break;
                        case 1955157924:
                            if (next.equals("transitionTimecodes")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1985040897:
                            if (next.equals("xrayMetadata")) {
                                b = 6;
                            }
                            break;
                    }
                    widevineLicenseResource = null;
                    immutableMap2 = null;
                    playbackSettings = null;
                    devicePlaybackConfiguration = null;
                    audioVideoUrls = null;
                    trickplayUrls = null;
                    responseTitleRendition = null;
                    getPresetsOutput = null;
                    getLinearRestrictionsResponse = null;
                    playReadyLicense = null;
                    channelSchedule = null;
                    fairPlayApplicationCertificate = null;
                    syeUrlResponse = null;
                    cuepointPlaylist = null;
                    tTSResponse = null;
                    title = null;
                    fairPlayLicense = null;
                    immutableMap = null;
                    immutableList2 = null;
                    xRayFragmentBase = null;
                    legacyMetadata = null;
                    playbackUrls = null;
                    pRSException = null;
                    titleBookmark = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            widevineLicenseResource = this.mWidevineLicenseResourceParser.parse(jsonNode2);
                        }
                        builder.widevine2License = widevineLicenseResource;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSubtitleUrlsLanguageListParser.parse(jsonNode2);
                        }
                        builder.subtitleUrls = immutableList;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            titleBookmark = this.mTitleBookmarkParser.parse(jsonNode2);
                        }
                        builder.whisperSyncBookmark = titleBookmark;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            pRSException = this.mPRSExceptionParser.parse(jsonNode2);
                        }
                        builder.error = pRSException;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            playbackUrls = this.mPlaybackUrlsParser.parse(jsonNode2);
                        }
                        builder.playbackUrls = playbackUrls;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            legacyMetadata = this.mLegacyMetadataParser.parse(jsonNode2);
                        }
                        builder.legacyMetadata = legacyMetadata;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            xRayFragmentBase = this.mXRayFragmentBaseParser.parse(jsonNode2);
                        }
                        builder.xrayMetadata = xRayFragmentBase;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mSubtitleUrlsLanguageListParser.parse(jsonNode2);
                        }
                        builder.forcedNarratives = immutableList2;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mexceptionMapParser.parse(jsonNode2);
                        }
                        builder.errorsByResource = immutableMap;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            fairPlayLicense = this.mFairPlayLicenseParser.parse(jsonNode2);
                        }
                        builder.fairPlayLicense = fairPlayLicense;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            title = this.mTitleParser.parse(jsonNode2);
                        }
                        builder.catalogMetadata = title;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            tTSResponse = this.mTTSResponseParser.parse(jsonNode2);
                        }
                        builder.transitionTimecodes = tTSResponse;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            cuepointPlaylist = this.mCuepointPlaylistParser.parse(jsonNode2);
                        }
                        builder.cuepointPlaylist = cuepointPlaylist;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            syeUrlResponse = this.mSyeUrlResponseParser.parse(jsonNode2);
                        }
                        builder.syeUrlsV2 = syeUrlResponse;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            fairPlayApplicationCertificate = this.mFairPlayApplicationCertificateParser.parse(jsonNode2);
                        }
                        builder.fairPlayApplicationCertificate = fairPlayApplicationCertificate;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            channelSchedule = this.mChannelScheduleParser.parse(jsonNode2);
                        }
                        builder.liveSchedule = channelSchedule;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            playReadyLicense = this.mPlayReadyLicenseParser.parse(jsonNode2);
                        }
                        builder.playReadyLicense = playReadyLicense;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            getLinearRestrictionsResponse = this.mGetLinearRestrictionsResponseParser.parse(jsonNode2);
                        }
                        builder.linearRestrictions = getLinearRestrictionsResponse;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            getPresetsOutput = this.mGetPresetsOutputParser.parse(jsonNode2);
                        }
                        builder.subtitlePresets = getPresetsOutput;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            responseTitleRendition = this.mResponseTitleRenditionParser.parse(jsonNode2);
                        }
                        builder.returnedTitleRendition = responseTitleRendition;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            trickplayUrls = this.mTrickplayUrlsParser.parse(jsonNode2);
                        }
                        builder.trickplayUrls = trickplayUrls;
                        continue;
                    case 21:
                        if (!jsonNode2.isNull()) {
                            audioVideoUrls = this.mAudioVideoUrlsParser.parse(jsonNode2);
                        }
                        builder.audioVideoUrls = audioVideoUrls;
                        continue;
                    case 22:
                        if (!jsonNode2.isNull()) {
                            devicePlaybackConfiguration = this.mDevicePlaybackConfigurationParser.parse(jsonNode2);
                        }
                        builder.devicePlaybackConfiguration = devicePlaybackConfiguration;
                        continue;
                    case 23:
                        if (!jsonNode2.isNull()) {
                            playbackSettings = this.mPlaybackSettingsParser.parse(jsonNode2);
                        }
                        builder.playbackSettings = playbackSettings;
                        continue;
                    case 24:
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mDebugInfoListParser.parse(jsonNode2);
                        }
                        builder.debugInfos = immutableMap2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackResourcesResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 1;
                                    }
                                    break;
                                case -1860720910:
                                    if (currentName.equals("debugInfos")) {
                                        b = 24;
                                    }
                                    break;
                                case -1619650968:
                                    if (currentName.equals("catalogMetadata")) {
                                        b = 10;
                                    }
                                    break;
                                case -1575353941:
                                    if (currentName.equals("returnedTitleRendition")) {
                                        b = 19;
                                    }
                                    break;
                                case -993422289:
                                    if (currentName.equals("cuepointPlaylist")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case -813533723:
                                    if (currentName.equals("devicePlaybackConfiguration")) {
                                        b = 22;
                                    }
                                    break;
                                case -712844372:
                                    if (currentName.equals("widevine2License")) {
                                        b = 0;
                                    }
                                    break;
                                case -297271127:
                                    if (currentName.equals("fairPlayLicense")) {
                                        b = 9;
                                    }
                                    break;
                                case -212731398:
                                    if (currentName.equals("forcedNarratives")) {
                                        b = 7;
                                    }
                                    break;
                                case -198067732:
                                    if (currentName.equals("linearRestrictions")) {
                                        b = 17;
                                    }
                                    break;
                                case -23102916:
                                    if (currentName.equals("subtitlePresets")) {
                                        b = 18;
                                    }
                                    break;
                                case 96784904:
                                    if (currentName.equals("error")) {
                                        b = 3;
                                    }
                                    break;
                                case 274770782:
                                    if (currentName.equals("playbackSettings")) {
                                        b = 23;
                                    }
                                    break;
                                case 434542783:
                                    if (currentName.equals("fairPlayApplicationCertificate")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 530671218:
                                    if (currentName.equals("playReadyLicense")) {
                                        b = 16;
                                    }
                                    break;
                                case 713468447:
                                    if (currentName.equals("syeUrlsV2")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 714776979:
                                    if (currentName.equals("whisperSyncBookmark")) {
                                        b = 2;
                                    }
                                    break;
                                case 1138359203:
                                    if (currentName.equals("liveSchedule")) {
                                        b = 15;
                                    }
                                    break;
                                case 1350250320:
                                    if (currentName.equals("errorsByResource")) {
                                        b = 8;
                                    }
                                    break;
                                case 1355525535:
                                    if (currentName.equals("playbackUrls")) {
                                        b = 4;
                                    }
                                    break;
                                case 1546320408:
                                    if (currentName.equals("legacyMetadata")) {
                                        b = 5;
                                    }
                                    break;
                                case 1557868745:
                                    if (currentName.equals("audioVideoUrls")) {
                                        b = 21;
                                    }
                                    break;
                                case 1933916811:
                                    if (currentName.equals("trickplayUrls")) {
                                        b = 20;
                                    }
                                    break;
                                case 1955157924:
                                    if (currentName.equals("transitionTimecodes")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1985040897:
                                    if (currentName.equals("xrayMetadata")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            WidevineLicenseResource widevineLicenseResource = null;
                            ImmutableMap<String, DebugInfo> immutableMap = null;
                            PlaybackSettings playbackSettings = null;
                            DevicePlaybackConfiguration devicePlaybackConfiguration = null;
                            AudioVideoUrls audioVideoUrls = null;
                            TrickplayUrls trickplayUrls = null;
                            ResponseTitleRendition responseTitleRendition = null;
                            GetPresetsOutput getPresetsOutput = null;
                            GetLinearRestrictionsResponse getLinearRestrictionsResponse = null;
                            PlayReadyLicense playReadyLicense = null;
                            ChannelSchedule channelSchedule = null;
                            FairPlayApplicationCertificate fairPlayApplicationCertificate = null;
                            SyeUrlResponse syeUrlResponse = null;
                            CuepointPlaylist cuepointPlaylist = null;
                            TTSResponse tTSResponse = null;
                            Title title = null;
                            FairPlayLicense fairPlayLicense = null;
                            ImmutableMap<Resource, PRSException> immutableMap2 = null;
                            ImmutableList<SubtitleUrlLanguageOption> immutableList = null;
                            XRayFragmentBase xRayFragmentBase = null;
                            LegacyMetadata legacyMetadata = null;
                            PlaybackUrls playbackUrls = null;
                            PRSException pRSException = null;
                            TitleBookmark titleBookmark = null;
                            ImmutableList<SubtitleUrlLanguageOption> immutableList2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        widevineLicenseResource = this.mWidevineLicenseResourceParser.parse(jsonParser);
                                    }
                                    builder.widevine2License = widevineLicenseResource;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mSubtitleUrlsLanguageListParser.parse(jsonParser);
                                    }
                                    builder.subtitleUrls = immutableList2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        titleBookmark = this.mTitleBookmarkParser.parse(jsonParser);
                                    }
                                    builder.whisperSyncBookmark = titleBookmark;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        pRSException = this.mPRSExceptionParser.parse(jsonParser);
                                    }
                                    builder.error = pRSException;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playbackUrls = this.mPlaybackUrlsParser.parse(jsonParser);
                                    }
                                    builder.playbackUrls = playbackUrls;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        legacyMetadata = this.mLegacyMetadataParser.parse(jsonParser);
                                    }
                                    builder.legacyMetadata = legacyMetadata;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        xRayFragmentBase = this.mXRayFragmentBaseParser.parse(jsonParser);
                                    }
                                    builder.xrayMetadata = xRayFragmentBase;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mSubtitleUrlsLanguageListParser.parse(jsonParser);
                                    }
                                    builder.forcedNarratives = immutableList;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mexceptionMapParser.parse(jsonParser);
                                    }
                                    builder.errorsByResource = immutableMap2;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        fairPlayLicense = this.mFairPlayLicenseParser.parse(jsonParser);
                                    }
                                    builder.fairPlayLicense = fairPlayLicense;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        title = this.mTitleParser.parse(jsonParser);
                                    }
                                    builder.catalogMetadata = title;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        tTSResponse = this.mTTSResponseParser.parse(jsonParser);
                                    }
                                    builder.transitionTimecodes = tTSResponse;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cuepointPlaylist = this.mCuepointPlaylistParser.parse(jsonParser);
                                    }
                                    builder.cuepointPlaylist = cuepointPlaylist;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        syeUrlResponse = this.mSyeUrlResponseParser.parse(jsonParser);
                                    }
                                    builder.syeUrlsV2 = syeUrlResponse;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        fairPlayApplicationCertificate = this.mFairPlayApplicationCertificateParser.parse(jsonParser);
                                    }
                                    builder.fairPlayApplicationCertificate = fairPlayApplicationCertificate;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        channelSchedule = this.mChannelScheduleParser.parse(jsonParser);
                                    }
                                    builder.liveSchedule = channelSchedule;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playReadyLicense = this.mPlayReadyLicenseParser.parse(jsonParser);
                                    }
                                    builder.playReadyLicense = playReadyLicense;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        getLinearRestrictionsResponse = this.mGetLinearRestrictionsResponseParser.parse(jsonParser);
                                    }
                                    builder.linearRestrictions = getLinearRestrictionsResponse;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        getPresetsOutput = this.mGetPresetsOutputParser.parse(jsonParser);
                                    }
                                    builder.subtitlePresets = getPresetsOutput;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        responseTitleRendition = this.mResponseTitleRenditionParser.parse(jsonParser);
                                    }
                                    builder.returnedTitleRendition = responseTitleRendition;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        trickplayUrls = this.mTrickplayUrlsParser.parse(jsonParser);
                                    }
                                    builder.trickplayUrls = trickplayUrls;
                                    continue;
                                case 21:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        audioVideoUrls = this.mAudioVideoUrlsParser.parse(jsonParser);
                                    }
                                    builder.audioVideoUrls = audioVideoUrls;
                                    continue;
                                case 22:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        devicePlaybackConfiguration = this.mDevicePlaybackConfigurationParser.parse(jsonParser);
                                    }
                                    builder.devicePlaybackConfiguration = devicePlaybackConfiguration;
                                    continue;
                                case 23:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playbackSettings = this.mPlaybackSettingsParser.parse(jsonParser);
                                    }
                                    builder.playbackSettings = playbackSettings;
                                    continue;
                                case 24:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mDebugInfoListParser.parse(jsonParser);
                                    }
                                    builder.debugInfos = immutableMap;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
