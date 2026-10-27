package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.core.constants.UrlType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.DownloadData;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.PluginLogConstants$DownloadError;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginActionBase;
import com.amazon.avod.media.download.plugin.action.ContentPluginActionResult;
import com.amazon.avod.media.download.plugin.action.RetriablePluginDownloader;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleTimerMetric;
import com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListener;
import com.amazon.avod.playbackclient.subtitle.download.SubtitleLanguageLog;
import com.amazon.avod.playbackclient.subtitle.download.SubtitlePluginLog;
import com.amazon.avod.playbackclient.subtitle.download.SubtitlePluginResponseHolder;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.io.File;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class SubtitleDownloadAction extends RetriablePluginDownloader {
    private static final int ESTIMATED_SUBTITLE_SIZE = (int) DataUnit.KILOBYTES.toBytes(200.0f);
    private static final TimeSpan SUBTITLE_DOWNLOAD_TIMEOUT = TimeSpan.fromMinutes(5);
    private LanguageDownloadListener mLanguageDownloadListener;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final StorageHelper mStorageHelper;
    private final SubtitleConfig mSubtitleConfig;
    private List<Subtitle> mSubtitleList;
    private final SubtitlePluginResponseHolder mSubtitlePluginResponseHolder;

    SubtitleDownloadAction(@Nonnull DownloadService downloadService, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder) {
        this(downloadService, StorageHelper.getInstance(), SubtitleConfig.getInstance(), NetworkConnectionManager.getInstance(), subtitlePluginResponseHolder);
    }

    SubtitleDownloadAction(@Nonnull DownloadService downloadService, @Nonnull StorageHelper storageHelper, @Nonnull SubtitleConfig subtitleConfig, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder) {
        super(downloadService);
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
        this.mSubtitleConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mSubtitlePluginResponseHolder = (SubtitlePluginResponseHolder) Preconditions.checkNotNull(subtitlePluginResponseHolder, "subtitlePluginResponseHolder");
    }

    public void setSubtitleList(@Nonnull List<Subtitle> list) {
        this.mSubtitleList = (List) Preconditions.checkNotNull(list, "subtitleList");
    }

    public void setLanguageDownloadListener(@Nullable LanguageDownloadListener languageDownloadListener) {
        this.mLanguageDownloadListener = languageDownloadListener;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public ContentPluginActionResult call() {
        DownloadData<File> downloadDataDownloadFileDataFromURL;
        ContentFetcherPluginContext pluginContext = getPluginContext();
        Preconditions.checkState(pluginContext != null, "Must initialize the plugin context");
        if (this.mSubtitleList.isEmpty()) {
            setNextAction(null);
            this.mSubtitlePluginResponseHolder.transitionToStatus(PluginLoadStatus.Status.LOADED);
            return ContentFetcherPluginActionBase.createSuccessfulResult("All subtitle languages downloaded");
        }
        setNextAction(this);
        SubtitlePluginLog.Builder subtitleLogBuilder = this.mSubtitlePluginResponseHolder.getSubtitleLogBuilder();
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
        Subtitle subtitleRemove = this.mSubtitleList.remove(0);
        String languageCode = subtitleRemove.getLanguageCode();
        SubtitleLanguage subtitleLanguage = new SubtitleLanguage(subtitleRemove);
        UrlType urlType = pluginContext.isTrailer() ? UrlType.TRAILER : UrlType.CONTENT;
        SubtitleLanguageLog.SubtitleLanguageLogBuilder subtitleLanguageLogBuilderNewBuilder = SubtitleLanguageLog.newBuilder(subtitleLanguage);
        File file = new File(pluginContext.getStoragePath(), "subtitles");
        File subtitleFileForTitle = this.mSubtitleConfig.getSubtitleFileForTitle(file, subtitleLanguage, urlType);
        boolean zExists = subtitleFileForTitle.exists();
        File legacySubtitleFile = this.mSubtitleConfig.getLegacySubtitleFile(file, subtitleLanguage, urlType);
        if (zExists || legacySubtitleFile.exists()) {
            if (!zExists) {
                subtitleFileForTitle = legacySubtitleFile;
            }
            DLog.logf("Subtitle file %s already exists, skipping download", languageCode);
            notifyDownloadedListener(subtitleRemove, 0L, subtitleFileForTitle);
            subtitleLogBuilder.withIndividualSubtitleLanguageLog(subtitleLanguageLogBuilderNewBuilder.withSubtitleFileSource(PluginLoadStatus.Source.DISK).withSubtitleFileSize(subtitleFileForTitle.length()).build());
            return ContentFetcherPluginActionBase.createSuccessfulResult(String.format("Subtitle file %s already exists, skipping download", subtitleRemove));
        }
        if (!this.mNetworkConnectionManager.hasDataConnection()) {
            Object[] objArr = new Object[2];
            objArr[0] = subtitleRemove.isForcedNarrative() ? "Narrative" : "Subtitle";
            objArr[1] = languageCode;
            DLog.warnf("%s file for language %s does not exist and we do not have a data connection to download it", objArr);
            subtitleLogBuilder.withIndividualSubtitleLanguageLog(subtitleLanguageLogBuilderNewBuilder.withSubtitleFileDownloadError(PluginLogConstants$DownloadError.NO_DATA_CONNECTION).withSubtitleFileSource(PluginLoadStatus.Source.NETWORK).build());
            return ContentFetcherPluginActionBase.createFailedResult(String.format("No data connection, cannot download subtitle", new Object[0]));
        }
        try {
            String strCreateEncodedURL = URLUtils.createEncodedURL(subtitleRemove.getURL());
            DLog.logf("DWNLD_SDK SubtitleDownloadAction downloading subtitle: %s", languageCode);
            if (this.mSubtitleConfig.isRetriableSubtitleDownloadsEnabled()) {
                downloadDataDownloadFileDataFromURL = downloadFileDataFromURL(subtitleFileForTitle, strCreateEncodedURL, SUBTITLE_DOWNLOAD_TIMEOUT, ESTIMATED_SUBTITLE_SIZE);
            } else {
                downloadDataDownloadFileDataFromURL = downloadFileDataFromURL(subtitleFileForTitle, strCreateEncodedURL, SUBTITLE_DOWNLOAD_TIMEOUT, ESTIMATED_SUBTITLE_SIZE, PriorityTier.AUXILIARY_REQUIRED);
            }
            File downloadData = downloadDataDownloadFileDataFromURL.getDownloadData();
            reportPlayersDownloadTime(downloadDataDownloadFileDataFromURL.getDownloadTime());
            if (downloadData == null) {
                DLog.warnf("DWNLD_SDK Subtitle file failed to download for language %s", languageCode);
                subtitleLogBuilder.withIndividualSubtitleLanguageLog(subtitleLanguageLogBuilderNewBuilder.withSubtitleFileDownloadError(PluginLogConstants$DownloadError.NULL_FILE).withSubtitleFileSource(PluginLoadStatus.Source.NETWORK).build());
                return ContentFetcherPluginActionBase.createFailedResult(String.format("Subtitle file failed to download for subtitle: %s", subtitleRemove));
            }
            TimeSpan timeSpan = new TimeSpan(stopwatchCreateStarted.stop());
            notifyDownloadedListener(subtitleRemove, timeSpan.getTotalMilliseconds(), downloadData);
            subtitleLogBuilder.withIndividualSubtitleLanguageLog(subtitleLanguageLogBuilderNewBuilder.withSubtitleFileSize(subtitleFileForTitle.length()).withSubtitleFileDownloadTime(timeSpan.getTotalMilliseconds()).withSubtitleFileSource(PluginLoadStatus.Source.NETWORK).build());
            return ContentFetcherPluginActionBase.createSuccessfulResult(String.format("Subtitle file %s downloaded", subtitleRemove));
        } catch (Exception e) {
            DLog.exceptionf(e, "DWNLD_SDK Failed to download subtitles due to invalid URL: %s", subtitleRemove);
            subtitleLogBuilder.withIndividualSubtitleLanguageLog(subtitleLanguageLogBuilderNewBuilder.withSubtitleFileDownloadException(e).withSubtitleFileSource(PluginLoadStatus.Source.NETWORK).build());
            return ContentFetcherPluginActionBase.createFailedResult(String.format("Failed to download subtitles due to invalid URL: %s", subtitleRemove));
        }
    }

    private void notifyDownloadedListener(Subtitle subtitle, long j, File file) {
        LanguageDownloadListener languageDownloadListener = this.mLanguageDownloadListener;
        if (languageDownloadListener != null) {
            languageDownloadListener.onLanguageDownload(subtitle, j, file);
        }
    }

    private void reportPlayersDownloadTime(long j) {
        Profiler.reportTimerMetric(new SimpleTimerMetric("Subtitles-Download-PlayerTime", -1L, j));
    }
}
