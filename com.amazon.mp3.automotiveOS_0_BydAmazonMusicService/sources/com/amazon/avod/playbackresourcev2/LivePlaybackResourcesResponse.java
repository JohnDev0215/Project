package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.playbackresourcev2.LivePlaybackResourcesSessionResponse;
import com.amazon.avod.playbackresourcev2.LivePlaybackUrls;
import com.amazon.avod.playbackresourcev2.PlaybackData;
import com.amazon.avod.playbackresourcev2.Prsv2Error;
import com.amazon.avod.playbackresourcev2.SlateResponseModel;
import com.amazon.avod.playbackresourcev2.SyeUrlResponseV2;
import com.amazon.avod.playbackresourcev2.XRayFragmentBaseV2;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LivePlaybackResourcesResponse {
    private final Optional<Prsv2Error> mError;
    private final Optional<LivePlaybackUrls> mLivePlaybackUrls;
    private final Optional<PlaybackData> mPlaybackData;
    private final Optional<LivePlaybackUrls> mRapidRecapPlaybackUrls;
    private final Optional<LivePlaybackResourcesSessionResponse> mSession;
    private final Optional<SlateResponseModel> mSlateInformation;
    private final Optional<SyeUrlResponseV2> mSyeUrlResponseV2;
    private final Optional<XRayFragmentBaseV2> mXrayMetadata;

    LivePlaybackResourcesResponse(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mLivePlaybackUrls = Optional.fromNullable(builder.mLivePlaybackUrls);
        this.mRapidRecapPlaybackUrls = Optional.fromNullable(builder.mRapidRecapPlaybackUrls);
        this.mSlateInformation = Optional.fromNullable(builder.mSlateResponseModel);
        this.mSyeUrlResponseV2 = Optional.fromNullable(builder.mSyeUrlResponseV2);
        this.mSession = Optional.fromNullable(builder.mSession);
        this.mXrayMetadata = Optional.fromNullable(builder.mXrayMetadata);
        this.mPlaybackData = Optional.fromNullable(builder.mPlaybackData);
        this.mError = Optional.fromNullable(builder.mError);
    }

    @Nonnull
    public Optional<LivePlaybackUrls> getLivePlaybackUrls() {
        return this.mLivePlaybackUrls;
    }

    @Nonnull
    public Optional<SyeUrlResponseV2> getSyeUrls() {
        return this.mSyeUrlResponseV2;
    }

    @Nonnull
    public Optional<LivePlaybackResourcesSessionResponse> getSession() {
        return this.mSession;
    }

    @Nonnull
    public Optional<PlaybackData> getPlaybackData() {
        return this.mPlaybackData;
    }

    @Nonnull
    public Optional<Prsv2Error> getError() {
        return this.mError;
    }

    @Nonnull
    public Optional<XRayFragmentBaseV2> getXrayMetadata() {
        return this.mXrayMetadata;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("livePlaybackUrls", this.mLivePlaybackUrls).add("rapidRecapPlaybackUrls", this.mRapidRecapPlaybackUrls).add("slateInformation", this.mSlateInformation).add("session", this.mSession).add("playbackData", this.mPlaybackData).add("syeUrls", this.mSyeUrlResponseV2).add("globalError", this.mError).add("xRayMetaData", this.mXrayMetadata).toString();
    }

    public static class Builder {
        private Prsv2Error mError;
        private LivePlaybackUrls mLivePlaybackUrls;
        private PlaybackData mPlaybackData;
        private LivePlaybackUrls mRapidRecapPlaybackUrls;
        private LivePlaybackResourcesSessionResponse mSession;
        private SlateResponseModel mSlateResponseModel;
        private SyeUrlResponseV2 mSyeUrlResponseV2;
        private XRayFragmentBaseV2 mXrayMetadata;

        LivePlaybackResourcesResponse build() {
            return new LivePlaybackResourcesResponse(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<LivePlaybackResourcesResponse> {
        private static final String GLOBAL_ERROR_RESPONSE_KEY = "globalError";
        private static final String LIVE_PLAYBACK_URLS_RESPONSE_KEY = "livePlaybackUrls";
        private static final String PLAYBACK_DATA_RESPONSE_KEY = "playbackData";
        private static final String RAPID_RECAP_PLAYBACK_URLS_RESPONSE_KEY = "rapidRecapPlaybackUrls";
        private static final String SESSION_RESPONSE_KEY = "session";
        private static final String SLATES_RESPONSE_KEY = "slates";
        private static final String SYE_URLS_RESPONSE_KEY = "syeUrls";
        private static final String XRAY_META_DATA_RESPONSE_KEY = "xrayMetadata";
        private final Prsv2Error.Parser mErrorParser;
        private final LivePlaybackResourcesSessionResponse.Parser mLivePlaybackResourcesSessionResponseParser;
        private final LivePlaybackUrls.Parser mLivePlaybackUrlsParser;
        private final PlaybackResourcesV2Config mPRSv2Config;
        private final PlaybackData.Parser mPlaybackDataParser;
        private final LivePlaybackUrls.Parser mRapidRecapPlaybackUrlsParser;
        private final SlateResponseModel.Parser mSlatesResponseParser;
        private final SyeUrlResponseV2.Parser mSyeUrlsParser;
        private final XRayFragmentBaseV2.Parser mXrayFragmentBaseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, LivePlaybackResourcesResponse.class);
            this.mLivePlaybackUrlsParser = new LivePlaybackUrls.Parser(objectMapper);
            this.mRapidRecapPlaybackUrlsParser = new LivePlaybackUrls.Parser(objectMapper);
            this.mSlatesResponseParser = new SlateResponseModel.Parser(objectMapper);
            this.mSyeUrlsParser = new SyeUrlResponseV2.Parser(objectMapper);
            this.mLivePlaybackResourcesSessionResponseParser = new LivePlaybackResourcesSessionResponse.Parser(objectMapper);
            this.mPlaybackDataParser = new PlaybackData.Parser(objectMapper);
            this.mErrorParser = new Prsv2Error.Parser(objectMapper);
            this.mXrayFragmentBaseParser = new XRayFragmentBaseV2.Parser(objectMapper);
            this.mPRSv2Config = PlaybackResourcesV2Config.getInstance();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public LivePlaybackResourcesResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            LivePlaybackUrls livePlaybackUrls;
            LivePlaybackUrls livePlaybackUrls2;
            SlateResponseModel slateResponseModel;
            LivePlaybackResourcesSessionResponse livePlaybackResourcesSessionResponse;
            SyeUrlResponseV2 syeUrlResponseV2;
            PlaybackData playbackData;
            XRayFragmentBaseV2 xRayFragmentBaseV2;
            Prsv2Error prsv2Error;
            JsonParsingUtils.throwIfNotObject(jsonNode, "LivePlaybackResourcesResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1794395989:
                            if (next.equals(LIVE_PLAYBACK_URLS_RESPONSE_KEY)) {
                                b = 0;
                            }
                            break;
                        case -1751210941:
                            if (next.equals(SYE_URLS_RESPONSE_KEY)) {
                                b = 4;
                            }
                            break;
                        case -899870214:
                            if (next.equals(SLATES_RESPONSE_KEY)) {
                                b = 2;
                            }
                            break;
                        case -865555419:
                            if (next.equals(GLOBAL_ERROR_RESPONSE_KEY)) {
                                b = 7;
                            }
                            break;
                        case 99701346:
                            if (next.equals(RAPID_RECAP_PLAYBACK_URLS_RESPONSE_KEY)) {
                                b = 1;
                            }
                            break;
                        case 1355002981:
                            if (next.equals(PLAYBACK_DATA_RESPONSE_KEY)) {
                                b = 5;
                            }
                            break;
                        case 1984987798:
                            if (next.equals(SESSION_RESPONSE_KEY)) {
                                b = 3;
                            }
                            break;
                        case 1985040897:
                            if (next.equals(XRAY_META_DATA_RESPONSE_KEY)) {
                                b = 6;
                            }
                            break;
                    }
                    livePlaybackUrls = null;
                    prsv2Error = null;
                    xRayFragmentBaseV2 = null;
                    playbackData = null;
                    syeUrlResponseV2 = null;
                    livePlaybackResourcesSessionResponse = null;
                    slateResponseModel = null;
                    livePlaybackUrls2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LivePlaybackResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            livePlaybackUrls = this.mLivePlaybackUrlsParser.parse(jsonNode2);
                        }
                        builder.mLivePlaybackUrls = livePlaybackUrls;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            livePlaybackUrls2 = this.mLivePlaybackUrlsParser.parse(jsonNode2);
                        }
                        builder.mRapidRecapPlaybackUrls = livePlaybackUrls2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            slateResponseModel = this.mSlatesResponseParser.parse(jsonNode2);
                        }
                        builder.mSlateResponseModel = slateResponseModel;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            livePlaybackResourcesSessionResponse = this.mLivePlaybackResourcesSessionResponseParser.parse(jsonNode2);
                        }
                        builder.mSession = livePlaybackResourcesSessionResponse;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            syeUrlResponseV2 = this.mSyeUrlsParser.parse(jsonNode2);
                        }
                        builder.mSyeUrlResponseV2 = syeUrlResponseV2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            playbackData = this.mPlaybackDataParser.parse(jsonNode2);
                        }
                        builder.mPlaybackData = playbackData;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            xRayFragmentBaseV2 = this.mXrayFragmentBaseParser.parse(jsonNode2);
                        }
                        builder.mXrayMetadata = xRayFragmentBaseV2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            prsv2Error = this.mErrorParser.parse(jsonNode2);
                        }
                        builder.mError = prsv2Error;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LivePlaybackResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.amazon.avod.playbackresourcev2.LivePlaybackResourcesResponse parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.core.JsonParser r8) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 470
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playbackresourcev2.LivePlaybackResourcesResponse.Parser.parseInternal(com.fasterxml.jackson.core.JsonParser):com.amazon.avod.playbackresourcev2.LivePlaybackResourcesResponse");
        }
    }
}
