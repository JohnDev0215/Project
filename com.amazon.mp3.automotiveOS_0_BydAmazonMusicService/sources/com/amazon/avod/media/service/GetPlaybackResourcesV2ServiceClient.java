package com.amazon.avod.media.service;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.AudioVideoUrlsBuilder;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.error.Prsv2ErrorCode;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.service.prsv2.GlobalParamsCreator;
import com.amazon.avod.media.service.prsv2.LivePlaybackUrlsParamsCreator;
import com.amazon.avod.media.service.prsv2.PlaybackDataParamsCreator;
import com.amazon.avod.media.service.prsv2.PlaybackResourceV2ParamsCreator;
import com.amazon.avod.media.service.prsv2.RapidRecapPlaybackUrlsParamsCreator;
import com.amazon.avod.playbackclient.utils.VideoMaterialTypeUtils;
import com.amazon.avod.playbackresource.PlaybackSettingsTransformer;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.avod.playbackresourcev2.Prsv2Error;
import com.amazon.avod.playbackresourcev2.Prsv2ErrorLookup;
import com.amazon.avod.prs.GetPlaybackResourcesFactory;
import com.amazon.avod.prs.GetPlaybackResourcesV2;
import com.amazon.avod.prs.GetPlaybackResourcesV2PlayerRequest;
import com.amazon.avod.prs.PRSV2ResourceRequest;
import com.amazon.avod.prs.PlaybackResourcesResponseListener;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.bolthttp.BoltException;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackResourcesV2ServiceClient {
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final GetPlaybackResourcesV2 mGetPlaybackResourcesV2;
    private final PlaybackSupportEvaluator mPlaybackSupportEvaluator;
    private final boolean mShouldRequestRapidRecapResourcesFromPRS;

    public GetPlaybackResourcesV2ServiceClient(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator) {
        this(GetPlaybackResourcesFactory.createNonCachingInstance(new GetPlaybackResourcesV2PlayerRequest()), advertisingIdCollector, playbackSupportEvaluator, PlaybackResourcesV2Config.getInstance().shouldRequestRapidRecapResourcesFromPRS());
    }

    GetPlaybackResourcesV2ServiceClient(@Nonnull GetPlaybackResourcesV2 getPlaybackResourcesV2, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, boolean z) {
        this.mGetPlaybackResourcesV2 = (GetPlaybackResourcesV2) Preconditions.checkNotNull(getPlaybackResourcesV2, "getPlaybackResources");
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        this.mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        this.mShouldRequestRapidRecapResourcesFromPRS = z;
    }

    PlaybackResourcesV2Wrapper getResources(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nonnull ConsumptionType consumptionType, @Nonnull Map<String, String> map, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nonnull RendererScheme rendererScheme, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull ContentType contentType, @Nonnull PlaybackEnvelope playbackEnvelope, @Nullable PlaybackSettings playbackSettings, boolean z) throws ContentException {
        try {
            return this.mGetPlaybackResourcesV2.get(new PRSV2ResourceRequest.Builder().titleId(str).videoMaterialType(videoMaterialType).consumptionType(consumptionType).params(constructPrsV2Params(playbackEnvelope, this.mAdvertisingIdCollector, rendererScheme, playbackSupportEvaluator, contentType, str, playbackSettings)).sessionContext(map).responseListener(playbackResourcesResponseListener).forValidation(z).build());
        } catch (BoltException e) {
            throw new ContentException(ContentException.ContentError.NETWORK_ERROR, e.getMessage());
        }
    }

    @Nonnull
    public AudioVideoUrls getUrls(@Nonnull String str, @Nonnull ContentType contentType, @Nonnull ConsumptionType consumptionType, @Nonnull Map<String, String> map, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nonnull RendererScheme rendererScheme, @Nonnull PlaybackEnvelope playbackEnvelope, @Nullable PlaybackSettings playbackSettings, boolean z) throws ContentException {
        PlaybackResourcesV2Wrapper resources = getResources(str, VideoMaterialTypeUtils.fromPlayersContentType(contentType), consumptionType, map, playbackResourcesResponseListener, rendererScheme, this.mPlaybackSupportEvaluator, contentType, playbackEnvelope, playbackSettings, z);
        PlaybackResourcesV2 playbackResourcesV2OrNull = resources.getPlaybackResources().orNull();
        if (playbackResourcesV2OrNull == null) {
            Prsv2Error prsv2ErrorOrNull = resources.getError().orNull();
            if (prsv2ErrorOrNull != null && prsv2ErrorOrNull.getCode().isPresent()) {
                throw new ContentException(Prsv2ErrorLookup.fromPrsv2ErrorCode(Prsv2ErrorCode.fromString(prsv2ErrorOrNull.getCode().get())), String.format("GetLivePlaybackResources call returned an error with error code %s and message %s", prsv2ErrorOrNull.getCode().get(), prsv2ErrorOrNull.getMessage().orNull()));
            }
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, "GetLivePlaybackResources playback resource returned null");
        }
        AudioVideoUrls audioVideoUrlsOrNull = playbackResourcesV2OrNull.getAudioVideoUrls().orNull();
        if (audioVideoUrlsOrNull != null) {
            return playbackSettings != null ? new AudioVideoUrlsBuilder(audioVideoUrlsOrNull).setPlaybackSettingsOverride(new PlaybackSettingsTransformer().transform(playbackSettings)).build() : audioVideoUrlsOrNull;
        }
        PRSException pRSException = playbackResourcesV2OrNull.getErrorsByResource().get(Resource.PlaybackUrls);
        if (pRSException != null && pRSException.errorCode.isPresent()) {
            throw new ContentException(Prsv2ErrorLookup.fromPrsv2ErrorCode(Prsv2ErrorCode.fromString(pRSException.errorCode.get())), String.format("GetLivePlaybackResources playbackUrls returned resource error code %s and message %s", pRSException.errorCode.get(), pRSException.message.orNull()));
        }
        Prsv2Error prsv2ErrorOrNull2 = resources.getError().orNull();
        if (prsv2ErrorOrNull2 != null && prsv2ErrorOrNull2.getCode().isPresent()) {
            throw new ContentException(Prsv2ErrorLookup.fromPrsv2ErrorCode(Prsv2ErrorCode.fromString(prsv2ErrorOrNull2.getCode().get())), String.format("GetLivePlaybackResources playbackUrls returned is null with error code %s and message %s", prsv2ErrorOrNull2.getCode().get(), prsv2ErrorOrNull2.getMessage().orNull()));
        }
        throw new ContentException(ContentException.ContentError.SERVICE_ERROR, "GetLivePlaybackResources playbackUrls returned is null");
    }

    @Nonnull
    private ResourceParams constructPrsV2Params(@Nonnull PlaybackEnvelope playbackEnvelope, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull RendererScheme rendererScheme, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull ContentType contentType, @Nonnull String str, @Nullable PlaybackSettings playbackSettings) throws ContentException {
        GlobalParamsCreator globalParamsCreator = new GlobalParamsCreator(playbackEnvelope);
        PlaybackResourceV2ParamsCreator.Builder builderPlaybackDataParamsCreator = new PlaybackResourceV2ParamsCreator.Builder().globalParamsCreator(globalParamsCreator).livePlaybackUrlsParamsCreator(new LivePlaybackUrlsParamsCreator(advertisingIdCollector, rendererScheme, playbackSupportEvaluator, contentType, str, playbackSettings)).playbackDataParamsCreator(new PlaybackDataParamsCreator());
        if (this.mShouldRequestRapidRecapResourcesFromPRS) {
            builderPlaybackDataParamsCreator.rapidReplayPlaybackUrlsParamsCreator(new RapidRecapPlaybackUrlsParamsCreator(rendererScheme, playbackSupportEvaluator, contentType));
        }
        return builderPlaybackDataParamsCreator.build().create();
    }
}
