package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.atvplaybackresource.PRSException;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginActionBase;
import com.amazon.avod.media.download.plugin.action.ContentPluginActionResult;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.service.cache.PlayerResourcesCache;
import com.amazon.avod.media.service.cache.PlayerResourcesCacheRequest;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListener;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener;
import com.amazon.avod.playbackclient.subtitle.download.SubtitlePluginLog;
import com.amazon.avod.playbackclient.subtitle.download.SubtitlePluginResponseHolder;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePresets;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class SubtitlePresetsAction extends ContentFetcherPluginActionBase {
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final PlayerResourcesCache mPlayerResourcesCache;
    private final PresetSerializer mPresetSerializer;
    private final SubtitlePluginResponseHolder mSubtitlePluginResponseHolder;
    private final SubtitleUrlAction mUrlAction;

    SubtitlePresetsAction(@Nonnull DownloadService downloadService, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder) {
        this(new SubtitleUrlAction(downloadService, subtitlePluginResponseHolder), NetworkConnectionManager.getInstance(), new PresetSerializer(), subtitlePluginResponseHolder, PlayerResourcesCache.getInstance());
    }

    SubtitlePresetsAction(@Nonnull SubtitleUrlAction subtitleUrlAction, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull PresetSerializer presetSerializer, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder, @Nonnull PlayerResourcesCache playerResourcesCache) {
        this.mUrlAction = (SubtitleUrlAction) Preconditions.checkNotNull(subtitleUrlAction, "subtitleUrlAction");
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mPresetSerializer = (PresetSerializer) Preconditions.checkNotNull(presetSerializer, "presetSerializer");
        this.mSubtitlePluginResponseHolder = (SubtitlePluginResponseHolder) Preconditions.checkNotNull(subtitlePluginResponseHolder, "subtitleResponseHolder");
        this.mPlayerResourcesCache = (PlayerResourcesCache) Preconditions.checkNotNull(playerResourcesCache, "playerResourcesCache");
    }

    public void setLanguageDownloadListener(@Nullable LanguageDownloadListener languageDownloadListener) {
        this.mUrlAction.setLanguageDownloadListener(languageDownloadListener);
    }

    public void setLanguageSupportedListener(@Nullable LanguageSupportedListener languageSupportedListener) {
        this.mUrlAction.setLanguageSupportedListener(languageSupportedListener);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public ContentPluginActionResult call() throws Exception {
        ContentFetcherPluginContext pluginContext = getPluginContext();
        Preconditions.checkState(pluginContext != null, "Must initialize the plugin context");
        this.mSubtitlePluginResponseHolder.transitionToStatus(PluginLoadStatus.Status.LOADING);
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
        SubtitlePluginLog.Builder subtitleLogBuilder = this.mSubtitlePluginResponseHolder.getSubtitleLogBuilder();
        subtitleLogBuilder.withAsin(pluginContext.getTitleId());
        this.mUrlAction.init(pluginContext);
        setNextAction(this.mUrlAction);
        if (!this.mNetworkConnectionManager.hasDataConnection()) {
            return getFailureResult("Subtitle presets could not download because device is offline", subtitleLogBuilder, stopwatchCreateStarted, null);
        }
        PlaybackResources playbackResourcesOrNull = this.mPlayerResourcesCache.getResources(new PlayerResourcesCacheRequest(getPluginContext().getVideoSpecification(), getPluginContext().getConsumptionType(), getPluginContext().getSessionContext())).orNull();
        Profiler.incrementCounter("PRSRequest:SubtitlePresetsAction");
        if (playbackResourcesOrNull == null || playbackResourcesOrNull.hasError() || !playbackResourcesOrNull.getSubtitlePresets().isPresent()) {
            return getFailureResult("Failed retrieving the customers subtitle presets, PRS unreachable or No Subtitle Presets in PRS Response.", subtitleLogBuilder, stopwatchCreateStarted, playbackResourcesOrNull);
        }
        SubtitlePresets subtitlePresetsConvertGetPresetsOutput = SubtitlePresets.convertGetPresetsOutput(playbackResourcesOrNull.getSubtitlePresets().get());
        if (subtitlePresetsConvertGetPresetsOutput == null) {
            return getFailureResult("Failed retrieving the customers subtitle presets, No Subtitle Presets inPRS Response", subtitleLogBuilder, stopwatchCreateStarted, null);
        }
        savePresetsToDisk(subtitlePresetsConvertGetPresetsOutput);
        subtitleLogBuilder.withPresetActionStatus(PluginLoadStatus.Source.NETWORK, stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS));
        DLog.logf("DWNLD_SDK SubtitlePresetsAction Retrieved the customers subtitle presets");
        return ContentFetcherPluginActionBase.createSuccessfulResult("Retrieved the customers subtitle presets");
    }

    @Nonnull
    private ContentPluginActionResult getFailureResult(@Nonnull String str, @Nonnull SubtitlePluginLog.Builder builder, @Nonnull Stopwatch stopwatch, @Nullable PlaybackResources playbackResources) {
        PRSException pRSExceptionOrNull = playbackResources != null ? playbackResources.getError().orNull() : null;
        if (pRSExceptionOrNull != null && pRSExceptionOrNull.message.isPresent()) {
            builder.withPresetActionPRSError(pRSExceptionOrNull);
        }
        builder.withPresetActionStatus(PluginLoadStatus.Source.DISK, stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        DLog.errorf("DWNLD_SDK %s", str);
        return ContentFetcherPluginActionBase.createFailedResult(str);
    }

    private void savePresetsToDisk(final SubtitlePresets subtitlePresets) {
        ThreadPoolExecutor threadPoolExecutorBuild = ExecutorBuilder.newBuilderFor(this, "SerializeToDisk").withFixedThreadPoolSize(1).build();
        try {
            threadPoolExecutorBuild.execute(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitlePresetsAction.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!SubtitlePresetsAction.this.mPresetSerializer.savePresetsToDisk(subtitlePresets)) {
                        DLog.warnf("DWNLD_SDK Unable to save subtitle presets to disk");
                    } else {
                        DLog.logf("DWNLD_SDK Finished saving customer's subtitle presets");
                    }
                }
            });
        } finally {
            threadPoolExecutorBuild.shutdown();
        }
    }
}
