package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.core.AVODRemoteException;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginActionBase;
import com.amazon.avod.media.download.plugin.action.ContentPluginActionResult;
import com.amazon.avod.media.download.plugin.reporting.PluginErrorType;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.playback.ContentType;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class SubtitleUrlAction extends ContentFetcherPluginActionBase {
    private final NetworkConnectionManager mDataConnection;
    private final SubtitleLanguageAction mLanguageAction;
    private final PlayerResourcesCache mPlayerResourcesCache;
    private final SubtitlePluginResponseHolder mSubtitlePluginResponseHolder;
    private final SubtitleSerializer mSubtitleSerializer;

    SubtitleUrlAction(@Nonnull DownloadService downloadService, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder) {
        this(new SubtitleLanguageAction(downloadService, subtitlePluginResponseHolder), NetworkConnectionManager.getInstance(), new SubtitleSerializer(), subtitlePluginResponseHolder, PlayerResourcesCache.getInstance());
    }

    SubtitleUrlAction(@Nonnull SubtitleLanguageAction subtitleLanguageAction, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull SubtitleSerializer subtitleSerializer, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder, @Nonnull PlayerResourcesCache playerResourcesCache) {
        this.mLanguageAction = (SubtitleLanguageAction) Preconditions.checkNotNull(subtitleLanguageAction, "languageAction");
        this.mDataConnection = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "connectionManager");
        this.mSubtitleSerializer = (SubtitleSerializer) Preconditions.checkNotNull(subtitleSerializer, "subtitleSerializer");
        this.mSubtitlePluginResponseHolder = (SubtitlePluginResponseHolder) Preconditions.checkNotNull(subtitlePluginResponseHolder, "subtitleResponseHolder");
        this.mPlayerResourcesCache = (PlayerResourcesCache) Preconditions.checkNotNull(playerResourcesCache, "playerResourcesCache");
    }

    public void setLanguageDownloadListener(@Nullable LanguageDownloadListener languageDownloadListener) {
        this.mLanguageAction.setLanguageDownloadListener(languageDownloadListener);
    }

    public void setLanguageSupportedListener(@Nullable LanguageSupportedListener languageSupportedListener) {
        this.mLanguageAction.setLanguageSupportedListener(languageSupportedListener);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public ContentPluginActionResult call() throws AVODRemoteException {
        ContentPluginActionResult contentPluginActionResultCreateFailedResult;
        PluginLoadStatus.Source source;
        SubtitlePluginLog.Builder subtitleLogBuilder = this.mSubtitlePluginResponseHolder.getSubtitleLogBuilder();
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
        ContentFetcherPluginContext pluginContext = getPluginContext();
        Preconditions.checkState(pluginContext != null, "Must initialize the plugin context");
        setNextAction(null);
        if (!this.mDataConnection.hasDataConnection()) {
            DLog.logf("Device is offline, using offline subtitles");
            getOfflineSubtitles(pluginContext);
            source = PluginLoadStatus.Source.DISK;
            contentPluginActionResultCreateFailedResult = ContentFetcherPluginActionBase.createSuccessfulResult("Device is offline, using offline subtitles");
        } else {
            ImmutableList<Subtitle> immutableListOrNull = getSubtitles(pluginContext).orNull();
            if (immutableListOrNull != null) {
                SubtitlePluginResponseHolder subtitlePluginResponseHolder = this.mSubtitlePluginResponseHolder;
                PluginLoadStatus.Source source2 = PluginLoadStatus.Source.NETWORK;
                subtitlePluginResponseHolder.setPluginSource(source2);
                updateSubtitles(immutableListOrNull);
                saveSubtitlesToDisk(pluginContext, immutableListOrNull);
                contentPluginActionResultCreateFailedResult = ContentFetcherPluginActionBase.createSuccessfulResult("Subtitles successfully downloaded");
                source = source2;
            } else {
                DLog.errorf("No urls in PRS response or PRS unreachable");
                PluginLoadStatus.Source source3 = PluginLoadStatus.Source.DISK;
                getOfflineSubtitles(pluginContext);
                subtitleLogBuilder.withUrlActionError("NoUrls", "No urls in PRS response or PRS unreachable");
                this.mSubtitlePluginResponseHolder.transitionToError(PluginErrorType.DOWNLOAD);
                contentPluginActionResultCreateFailedResult = ContentFetcherPluginActionBase.createFailedResult("Subtitles could not download because: No urls in PRS response or PRS unreachable");
                source = source3;
            }
        }
        subtitleLogBuilder.withUrlActionStatus(source, stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS));
        return contentPluginActionResultCreateFailedResult;
    }

    @Nonnull
    private Optional<ImmutableList<Subtitle>> getSubtitles(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext) {
        PlaybackResources playbackResourcesOrNull = this.mPlayerResourcesCache.getResources(new PlayerResourcesCacheRequest(contentFetcherPluginContext.getVideoSpecification(), contentFetcherPluginContext.getConsumptionType(), contentFetcherPluginContext.getSessionContext())).orNull();
        Profiler.incrementCounter("PRSRequest:SubtitleUrlAction");
        if (playbackResourcesOrNull == null) {
            return Optional.absent();
        }
        return Optional.of(ImmutableList.builder().addAll((Iterable) playbackResourcesOrNull.getSubtitles()).addAll((Iterable) playbackResourcesOrNull.getForcedNarratives()).build());
    }

    private void getOfflineSubtitles(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext) {
        this.mSubtitlePluginResponseHolder.setPluginSource(PluginLoadStatus.Source.DISK);
        updateSubtitles(this.mSubtitleSerializer.readSubtitlesFromDisk(contentFetcherPluginContext));
    }

    private void saveSubtitlesToDisk(@Nonnull final ContentFetcherPluginContext contentFetcherPluginContext, @Nonnull final ImmutableList<Subtitle> immutableList) {
        ThreadPoolExecutor threadPoolExecutorBuild = ExecutorBuilder.newBuilderFor(this, "SerializeToDisk").withFixedThreadPoolSize(1).build();
        try {
            threadPoolExecutorBuild.execute(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleUrlAction.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!SubtitleUrlAction.this.mSubtitleSerializer.saveSubtitlesToDisk(contentFetcherPluginContext, immutableList)) {
                        DLog.warnf("Unable to save subtitle language and urls to disk");
                    } else {
                        DLog.devf("Finished saving subtitle language and urls");
                    }
                }
            });
        } finally {
            threadPoolExecutorBuild.shutdown();
        }
    }

    private void updateSubtitles(@Nonnull ImmutableList<Subtitle> immutableList) {
        this.mLanguageAction.init(getPluginContext());
        this.mLanguageAction.initSubtitles(immutableList);
        setNextAction(this.mLanguageAction);
    }

    static class SubtitleSerializer {
        private final ObjectMapper mObjectMapper;
        private final StorageHelper mStorageHelper;

        SubtitleSerializer() {
            this(StorageHelper.getInstance(), JacksonCache.OBJECT_MAPPER);
        }

        SubtitleSerializer(@Nonnull StorageHelper storageHelper, @Nonnull ObjectMapper objectMapper) {
            this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
            this.mObjectMapper = (ObjectMapper) Preconditions.checkNotNull(objectMapper, "objectMapper");
        }

        @Nonnull
        private static File getSubtitleFile(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext) {
            ContentType contentType = contentFetcherPluginContext.getVideoSpecification().getContentType();
            return new File(new File(contentFetcherPluginContext.getStoragePath(), "subtitles"), "subtitles.json_" + contentType.name());
        }

        public boolean saveSubtitlesToDisk(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext, @Nonnull ImmutableList<Subtitle> immutableList) {
            Preconditions.checkNotNull(contentFetcherPluginContext, "context");
            Preconditions.checkNotNull(immutableList, "subtitles");
            try {
                File subtitleFile = getSubtitleFile(contentFetcherPluginContext);
                Files.createParentDirs(subtitleFile);
                DLog.logf("Saving subtitle language/url file to disk");
                this.mObjectMapper.writeValue(subtitleFile, immutableList);
                return true;
            } catch (IOException e) {
                DLog.exceptionf(e, "IO error trying to persist subtitle presets", new Object[0]);
                return false;
            }
        }

        @Nonnull
        public ImmutableList<Subtitle> readSubtitlesFromDisk(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext) {
            Preconditions.checkNotNull(contentFetcherPluginContext, "context");
            File subtitleFile = getSubtitleFile(contentFetcherPluginContext);
            if (!subtitleFile.exists()) {
                DLog.logf("No subtitle urls or languages exist on disk, returning empty subtitles");
                return ImmutableList.of();
            }
            try {
                return ImmutableList.copyOf((Object[]) this.mObjectMapper.readValue(subtitleFile, Subtitle[].class));
            } catch (IOException e) {
                DLog.exceptionf(e, "IO error trying to read subtitle languages from disk", new Object[0]);
                return ImmutableList.of();
            }
        }
    }
}
