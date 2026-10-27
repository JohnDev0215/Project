package com.amazon.video.sdk.avod.playbackclient.trickplay.download;

import android.content.Context;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.image.LiveTrickplayIndex;
import com.amazon.avod.content.image.TrickplayIndex;
import com.amazon.avod.content.image.TrickplayManifest;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.PluginLogConstants$DownloadError;
import com.amazon.avod.media.download.plugin.PluginResponseHolder;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginAction;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginActionBase;
import com.amazon.avod.media.download.plugin.action.ContentPluginActionGetFileFromURL;
import com.amazon.avod.media.download.plugin.action.ContentPluginActionResult;
import com.amazon.avod.media.download.plugin.reporting.PluginErrorType;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.service.cache.PlayerResourcesCache;
import com.amazon.avod.media.service.cache.PlayerResourcesCacheRequest;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playbackclient.displaymode.ScreenSizeUtils;
import com.amazon.avod.playbackclient.trickplay.TrickplayConfig;
import com.amazon.avod.playbackclient.trickplay.VodTrickplayIndex;
import com.amazon.avod.playbackclient.trickplay.download.BifContractException;
import com.amazon.avod.playbackclient.trickplay.download.TrickplayLoadingResponse;
import com.amazon.avod.playbackclient.trickplay.download.TrickplayManifestSerializer;
import com.amazon.avod.playbackclient.trickplay.download.VodTrickplayManifest;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.amazon.video.sdk.avod.playbackclient.trickplay.download.TrickplayPluginLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.io.Closer;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes5.dex */
@NotThreadSafe
public class TrickplayPlugin implements ContentFetcherPlugin {
    private final Provider<CheckPreconditions> mCheckPreconditionsActionProvider;
    private ContentFetcherPluginContext mPluginContext;
    private final TrickplayResponseHolder mResultHolder;
    private static final int ESTIMATED_BIF_SIZE = (int) DataUnit.MEGABYTES.toBytes(3.0f);
    private static final TimeSpan TRICKPLAY_DOWNLOAD_TIMEOUT = TimeSpan.fromMinutes(5);
    private static final Object PLUGIN_CACHE_LOCK = new Object();

    public static class PluginProvider implements Provider<TrickplayPlugin> {
        private final Context mContext;
        private final DownloadService mDownloadService;

        public PluginProvider(@Nonnull Context context, @Nonnull DownloadService downloadService) {
            this.mContext = (Context) Preconditions.checkNotNull(context, "context cannot be null");
            this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService cannot be null");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public TrickplayPlugin get() {
            return new TrickplayPlugin(this.mContext, this.mDownloadService);
        }
    }

    public TrickplayPlugin(@Nonnull Context context, @Nonnull DownloadService downloadService) {
        this(TrickplayConfig.getInstance(), NetworkConnectionManager.getInstance(), new TrickplayManifestSerializer(), downloadService, context);
    }

    TrickplayPlugin(final TrickplayConfig trickplayConfig, final NetworkConnectionManager networkConnectionManager, final TrickplayManifestSerializer trickplayManifestSerializer, final DownloadService downloadService, final Context context) {
        this(new TrickplayResponseHolder(), new Provider<CheckPreconditions>() { // from class: com.amazon.video.sdk.avod.playbackclient.trickplay.download.TrickplayPlugin.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public CheckPreconditions get() {
                return new CheckPreconditions(trickplayConfig, trickplayManifestSerializer, networkConnectionManager, downloadService, context);
            }
        });
    }

    TrickplayPlugin(TrickplayResponseHolder trickplayResponseHolder, Provider<CheckPreconditions> provider) {
        this.mResultHolder = trickplayResponseHolder;
        this.mCheckPreconditionsActionProvider = provider;
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public void init(ContentFetcherPluginContext contentFetcherPluginContext) {
        this.mPluginContext = contentFetcherPluginContext;
        this.mResultHolder.reset();
    }

    public TrickplayLoadingResponse getResult() {
        return this.mResultHolder.getFinishedResult();
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public ContentFetcherPluginAction getFirstAction() {
        CheckPreconditions checkPreconditions = this.mCheckPreconditionsActionProvider.get();
        checkPreconditions.init(this.mPluginContext);
        checkPreconditions.setResultHolder(this.mResultHolder);
        return checkPreconditions;
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public boolean isComplete() {
        return this.mResultHolder.hasData();
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public Object getLock() {
        return PLUGIN_CACHE_LOCK;
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public void clear() {
        DiskUtils.deleteFile(new File(this.mPluginContext.getStoragePath(), "trickplay"));
    }

    static class CheckPreconditions extends ContentFetcherPluginActionBase {
        private final Provider<ExtractManifestFromFile> mExtractExistingManifestActionProvider;
        private TrickplayResponseHolder mResultHolder;
        private final TrickplayConfig mTrickplayConfig;

        public CheckPreconditions(final TrickplayConfig trickplayConfig, final TrickplayManifestSerializer trickplayManifestSerializer, final NetworkConnectionManager networkConnectionManager, final DownloadService downloadService, final Context context) {
            this(trickplayConfig, new Provider<ExtractManifestFromFile>() { // from class: com.amazon.video.sdk.avod.playbackclient.trickplay.download.TrickplayPlugin.CheckPreconditions.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExtractManifestFromFile get() {
                    return new ExtractManifestFromFile(trickplayManifestSerializer, networkConnectionManager, trickplayConfig, downloadService, context);
                }
            });
        }

        CheckPreconditions(TrickplayConfig trickplayConfig, Provider<ExtractManifestFromFile> provider) {
            this.mTrickplayConfig = trickplayConfig;
            this.mExtractExistingManifestActionProvider = provider;
        }

        public void setResultHolder(TrickplayResponseHolder trickplayResponseHolder) {
            this.mResultHolder = trickplayResponseHolder;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ContentPluginActionResult call() {
            this.mResultHolder.transitionToStatus(PluginLoadStatus.Status.LOADING);
            String titleId = getPluginContext().getTitleId();
            DLog.logf("DWNLD_SDK TrickplayPlugin download start asin %s", titleId);
            TrickplayPluginLog.Builder trickplayLogBuilder = this.mResultHolder.getTrickplayLogBuilder();
            ContentFetcherPluginContext pluginContext = getPluginContext();
            trickplayLogBuilder.withAsin(titleId).isTrailer(pluginContext.isTrailer());
            if (pluginContext.isTrailer()) {
                this.mResultHolder.transitionToCancelled(PluginLoadStatus.CancellationReason.NOT_APPLICABLE);
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Trailer content, trickplay does not apply.");
            }
            if (!this.mTrickplayConfig.isTrickplayEnabled()) {
                DLog.logf("Trickplay is Disabled in TrickplayPlugin");
                this.mResultHolder.transitionToCancelled(PluginLoadStatus.CancellationReason.DISABLED_FROM_SERVER);
                trickplayLogBuilder.withTrickplayServerDisabled();
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Trickplay is disabled");
            }
            trickplayLogBuilder.withTrickplayServerEnabled();
            if (getPluginContext().getVideoSpecification().isLiveStream()) {
                GetDynamicTrickplayManifest getDynamicTrickplayManifest = new GetDynamicTrickplayManifest();
                getDynamicTrickplayManifest.init(getPluginContext());
                getDynamicTrickplayManifest.setResultHolder(this.mResultHolder);
                setNextAction(getDynamicTrickplayManifest);
            } else {
                ExtractManifestFromFile extractManifestFromFile = this.mExtractExistingManifestActionProvider.get();
                extractManifestFromFile.init(getPluginContext());
                extractManifestFromFile.setResultHolder(this.mResultHolder);
                setNextAction(extractManifestFromFile);
            }
            return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Trickplay applies to this content. Proceeding with data retrieval.");
        }
    }

    private static class GetDynamicTrickplayManifest extends ContentFetcherPluginActionBase {
        private TrickplayResponseHolder mResultHolder;

        private GetDynamicTrickplayManifest() {
        }

        public void setResultHolder(@Nonnull TrickplayResponseHolder trickplayResponseHolder) {
            this.mResultHolder = (TrickplayResponseHolder) Preconditions.checkNotNull(trickplayResponseHolder);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ContentPluginActionResult call() {
            TrickplayManifest liveTrickplayManifest = getPluginContext().getLiveTrickplayManifest();
            if (liveTrickplayManifest == null || !liveTrickplayManifest.isValid()) {
                this.mResultHolder.transitionToCancelled(PluginLoadStatus.CancellationReason.CONTENT_UNAVAILABLE);
                DLog.logf("DWNLD_SDK live trickplay manifest not available");
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Live trickplay manifest not available");
            }
            TrickplayPluginLog.Builder trickplayLogBuilder = this.mResultHolder.getTrickplayLogBuilder();
            PluginLoadStatus.Source source = PluginLoadStatus.Source.NETWORK;
            trickplayLogBuilder.withManifestSource(source);
            this.mResultHolder.setTrickplayManifest(liveTrickplayManifest);
            this.mResultHolder.setPluginAvailability(PluginLoadStatus.Availability.AVAILABLE);
            this.mResultHolder.setPluginSource(source);
            this.mResultHolder.transitionToStatus(PluginLoadStatus.Status.LOADED);
            DLog.logf("DWNLD_SDK live trickplay manifest available");
            return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Using live trickplay manifest");
        }
    }

    static class ExtractManifestFromFile extends ContentFetcherPluginActionBase {
        private final Provider<DownloadBifFileFromUrl> mDownloadBifFileProvider;
        private TrickplayResponseHolder mResultHolder;
        private final TrickplayManifestSerializer mTrickplayManifestSerializer;

        public ExtractManifestFromFile(final TrickplayManifestSerializer trickplayManifestSerializer, final NetworkConnectionManager networkConnectionManager, final TrickplayConfig trickplayConfig, final DownloadService downloadService, final Context context) {
            this(new Provider<DownloadBifFileFromUrl>() { // from class: com.amazon.video.sdk.avod.playbackclient.trickplay.download.TrickplayPlugin.ExtractManifestFromFile.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public DownloadBifFileFromUrl get() {
                    return new DownloadBifFileFromUrl(networkConnectionManager, trickplayConfig, trickplayManifestSerializer, downloadService, context);
                }
            }, trickplayManifestSerializer);
        }

        ExtractManifestFromFile(Provider<DownloadBifFileFromUrl> provider, TrickplayManifestSerializer trickplayManifestSerializer) {
            this.mDownloadBifFileProvider = provider;
            this.mTrickplayManifestSerializer = trickplayManifestSerializer;
        }

        public void setResultHolder(TrickplayResponseHolder trickplayResponseHolder) {
            this.mResultHolder = trickplayResponseHolder;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ContentPluginActionResult call() throws Throwable {
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
            File file = new File(getPluginContext().getStoragePath(), "trickplay");
            TrickplayPluginLog.Builder trickplayLogBuilder = this.mResultHolder.getTrickplayLogBuilder();
            VodTrickplayManifest vodTrickplayManifestFetchResultFromDisk = this.mTrickplayManifestSerializer.fetchResultFromDisk(file);
            if (vodTrickplayManifestFetchResultFromDisk.isValid()) {
                this.mResultHolder.setTrickplayManifest(vodTrickplayManifestFetchResultFromDisk);
                this.mResultHolder.setPluginAvailability(PluginLoadStatus.Availability.AVAILABLE);
                TrickplayResponseHolder trickplayResponseHolder = this.mResultHolder;
                PluginLoadStatus.Source source = PluginLoadStatus.Source.DISK;
                trickplayResponseHolder.setPluginSource(source);
                this.mResultHolder.transitionToStatus(PluginLoadStatus.Status.LOADED);
                trickplayLogBuilder.withManifestSource(source).withManifestDiskUrl(file).withManifestValidationTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS));
                DLog.logf("DWNLD_SDK Trickplay already available on disk");
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Fetched data from disk in isComplete");
            }
            trickplayLogBuilder.withManifestSource(PluginLoadStatus.Source.NETWORK).withManifestValidationTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS));
            DownloadBifFileFromUrl downloadBifFileFromUrl = this.mDownloadBifFileProvider.get();
            downloadBifFileFromUrl.init(getPluginContext());
            downloadBifFileFromUrl.setResultHolder(this.mResultHolder);
            setNextAction(downloadBifFileFromUrl);
            DLog.logf("DWNLD_SDK Trickplay not on disk, must fetch");
            return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "No data on disk, continue with next action");
        }
    }

    static class DownloadBifFileFromUrl extends ContentPluginActionGetFileFromURL {
        private final NetworkConnectionManager mDataConnection;
        private final Provider<ExtractImagesFromBifFile> mExtractImagesFromBifProvider;
        private final TrickplayUrlFetcher mGetTrickplayUrlFetcher;
        private TrickplayResponseHolder mResultHolder;

        public DownloadBifFileFromUrl(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull TrickplayConfig trickplayConfig, @Nonnull final TrickplayManifestSerializer trickplayManifestSerializer, @Nonnull DownloadService downloadService, @Nonnull Context context) {
            this(networkConnectionManager, new TrickplayUrlFetcher(trickplayConfig, context), new Provider<ExtractImagesFromBifFile>() { // from class: com.amazon.video.sdk.avod.playbackclient.trickplay.download.TrickplayPlugin.DownloadBifFileFromUrl.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExtractImagesFromBifFile get() {
                    return new ExtractImagesFromBifFile(trickplayManifestSerializer);
                }
            }, downloadService);
        }

        DownloadBifFileFromUrl(NetworkConnectionManager networkConnectionManager, TrickplayUrlFetcher trickplayUrlFetcher, Provider<ExtractImagesFromBifFile> provider, DownloadService downloadService) {
            super(downloadService);
            this.mDataConnection = networkConnectionManager;
            this.mGetTrickplayUrlFetcher = trickplayUrlFetcher;
            this.mExtractImagesFromBifProvider = provider;
        }

        public void setResultHolder(TrickplayResponseHolder trickplayResponseHolder) {
            this.mResultHolder = trickplayResponseHolder;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ContentPluginActionResult call() {
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
            TrickplayPluginLog.Builder trickplayLogBuilder = this.mResultHolder.getTrickplayLogBuilder();
            String titleId = getPluginContext().getTitleId();
            File trickplayBifFile = getTrickplayBifFile(titleId);
            if (trickplayBifFile.exists()) {
                TrickplayResponseHolder trickplayResponseHolder = this.mResultHolder;
                PluginLoadStatus.Source source = PluginLoadStatus.Source.DISK;
                trickplayResponseHolder.setPluginSource(source);
                this.mResultHolder.setPluginAvailability(PluginLoadStatus.Availability.AVAILABLE);
                this.mResultHolder.setBifFileSize(trickplayBifFile.length());
                setNextActionToExtractImages(titleId, trickplayBifFile);
                trickplayLogBuilder.withBifFileSource(source).withBifFromUrl(trickplayBifFile.getAbsolutePath()).withBifFileLoadTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withBifFileSize(trickplayBifFile.length());
                DLog.logf("DWNLD_SDK Trickplay Got existing bif file successfully");
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Got bif file successfully");
            }
            TrickplayResponseHolder trickplayResponseHolder2 = this.mResultHolder;
            PluginLoadStatus.Source source2 = PluginLoadStatus.Source.NETWORK;
            trickplayResponseHolder2.setPluginSource(source2);
            trickplayLogBuilder.withBifFileSource(source2);
            if (!this.mDataConnection.hasDataConnection()) {
                this.mResultHolder.transitionToCancelled(PluginLoadStatus.CancellationReason.NO_DATA_CONNECTION);
                trickplayLogBuilder.withBifFileLoadTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withBifDownloadError(PluginLogConstants$DownloadError.NO_DATA_CONNECTION);
                DLog.logf("DWNLD_SDK Trickplay No data connection available");
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "No data connection available");
            }
            ImmutableList<String> immutableListOrNull = this.mGetTrickplayUrlFetcher.fetchTrickplayUrls(getPluginContext()).orNull();
            if (immutableListOrNull == null) {
                DLog.errorf("DWNLD_SDK There was an error getting trickplay urls from PRS for %s", titleId);
                this.mResultHolder.transitionToError(PluginErrorType.DOWNLOAD);
                trickplayLogBuilder.withBifFileLoadTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withBifDownloadException("Error getting URLs from PRS");
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, "Was unable to get frame images url.");
            }
            if (immutableListOrNull.isEmpty()) {
                this.mResultHolder.setPluginAvailability(PluginLoadStatus.Availability.UNAVAILABLE);
                this.mResultHolder.transitionToCancelled(PluginLoadStatus.CancellationReason.CONTENT_UNAVAILABLE);
                trickplayLogBuilder.withBifFileLoadTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withBifDownloadError(PluginLogConstants$DownloadError.NULL_URL);
                DLog.logf("DWNLD_SDK Trickplay unavailable for this title");
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "No trickplay url available");
            }
            this.mResultHolder.setPluginAvailability(PluginLoadStatus.Availability.AVAILABLE);
            File fileDownloadFromURL = downloadFromURL(trickplayBifFile, immutableListOrNull.get(0), TrickplayPlugin.TRICKPLAY_DOWNLOAD_TIMEOUT, TrickplayPlugin.ESTIMATED_BIF_SIZE);
            if (fileDownloadFromURL != null) {
                this.mResultHolder.setBifFileSize(trickplayBifFile.length());
                setNextActionToExtractImages(titleId, fileDownloadFromURL);
                trickplayLogBuilder.withBifFileLoadTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withBifFileSize(fileDownloadFromURL.length());
                DLog.logf("DWNLD_SDK Trickplay downloaded successfully");
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Downloaded bif file successfully");
            }
            DLog.errorf("DWNLD_SDK Failed to download trickplay file for %s", titleId);
            this.mResultHolder.transitionToError(PluginErrorType.DOWNLOAD);
            trickplayLogBuilder.withBifFileLoadTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withBifDownloadError(PluginLogConstants$DownloadError.NULL_FILE);
            return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, "Failed to download bif file");
        }

        private void setNextActionToExtractImages(String str, File file) {
            ExtractImagesFromBifFile extractImagesFromBifFile = this.mExtractImagesFromBifProvider.get();
            extractImagesFromBifFile.setData(this.mResultHolder, new File(getPluginContext().getStoragePath(), "trickplay"), file);
            setNextAction(extractImagesFromBifFile);
        }

        private File getTrickplayBifFile(String str) {
            return new File(new File(getPluginContext().getStoragePath(), "trickplay"), str + ".bif");
        }
    }

    public static class TrickplayUrlFetcher {
        private final PlayerResourcesCache mPlayerResourcesCache;
        private final TrickplayResolutionGenerator mResolutionGenerator;

        public TrickplayUrlFetcher(@Nonnull TrickplayConfig trickplayConfig, @Nonnull Context context) {
            this(new TrickplayResolutionGenerator(trickplayConfig, context), PlayerResourcesCache.getInstance());
        }

        TrickplayUrlFetcher(@Nonnull TrickplayResolutionGenerator trickplayResolutionGenerator, @Nonnull PlayerResourcesCache playerResourcesCache) {
            this.mResolutionGenerator = (TrickplayResolutionGenerator) Preconditions.checkNotNull(trickplayResolutionGenerator, "resolutionGenerator");
            this.mPlayerResourcesCache = (PlayerResourcesCache) Preconditions.checkNotNull(playerResourcesCache, "playerResourcesCache");
        }

        @Nonnull
        protected Optional<ImmutableList<String>> fetchTrickplayUrls(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext) {
            PlaybackResources playbackResourcesOrNull = this.mPlayerResourcesCache.getResources(new PlayerResourcesCacheRequest(contentFetcherPluginContext.getVideoSpecification(), contentFetcherPluginContext.getConsumptionType(), contentFetcherPluginContext.getSessionContext())).orNull();
            Profiler.incrementCounter("PRSRequest:Trickplay");
            if (playbackResourcesOrNull == null) {
                DLog.warnf("DWNLD_SDK Failed to get playback resources from cache in TrickplayPlugin");
                return Optional.absent();
            }
            return Optional.of(playbackResourcesOrNull.getTrickplayUrls().get(this.mResolutionGenerator.getResolution()).asList());
        }
    }

    static class TrickplayResolutionGenerator {
        private final TrickplayConfig mConfig;
        private final Context mContext;

        public TrickplayResolutionGenerator(@Nonnull TrickplayConfig trickplayConfig, @Nonnull Context context) {
            this.mConfig = (TrickplayConfig) Preconditions.checkNotNull(trickplayConfig, "config");
            this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        }

        public String getResolution() {
            String desiredTrickplayResolution;
            if (this.mConfig.shouldUseDeviceTrickplayResolution()) {
                desiredTrickplayResolution = getDeviceResolution();
            } else {
                desiredTrickplayResolution = this.mConfig.getDesiredTrickplayResolution();
            }
            return endsWithP(desiredTrickplayResolution) ? desiredTrickplayResolution.substring(0, desiredTrickplayResolution.length() - 1) : desiredTrickplayResolution;
        }

        private boolean endsWithP(@Nullable String str) {
            if (Strings.isNullOrEmpty(str)) {
                return false;
            }
            return str.toLowerCase().endsWith("p");
        }

        private String getDeviceResolution() {
            return (ScreenSizeUtils.isCompactDevice(this.mContext) ? TrickplayConfig.FrameImageResolution.P480 : TrickplayConfig.FrameImageResolution.P720).getParamValue();
        }
    }

    static class ExtractImagesFromBifFile extends ContentFetcherPluginActionBase {
        private File mBifFile;
        private final BifFileExtractor mBifFileExtractor;
        private File mFolderLocation;
        private TrickplayResponseHolder mResultHolder;
        private final TrickplayManifestSerializer mTrickplayManifestSerializer;

        public ExtractImagesFromBifFile(TrickplayManifestSerializer trickplayManifestSerializer) {
            this(trickplayManifestSerializer, new BifFileExtractor());
        }

        ExtractImagesFromBifFile(TrickplayManifestSerializer trickplayManifestSerializer, BifFileExtractor bifFileExtractor) {
            this.mTrickplayManifestSerializer = trickplayManifestSerializer;
            this.mBifFileExtractor = bifFileExtractor;
        }

        public void setData(TrickplayResponseHolder trickplayResponseHolder, File file, File file2) {
            this.mResultHolder = trickplayResponseHolder;
            this.mFolderLocation = file;
            this.mBifFile = file2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ContentPluginActionResult call() throws Throwable {
            File file;
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
            TrickplayPluginLog.Builder trickplayLogBuilder = this.mResultHolder.getTrickplayLogBuilder();
            if (this.mFolderLocation == null || (file = this.mBifFile) == null || !file.exists()) {
                this.mResultHolder.transitionToError(PluginErrorType.RUNTIME);
                trickplayLogBuilder.withCreateManifestTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withCreateManifestError(PluginLogConstants$DownloadError.NULL_FILE);
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, "Can't process bif file. No valid bif file or folder location was provided.");
            }
            try {
                VodTrickplayManifest result = this.mBifFileExtractor.parseResult(this.mBifFile, this.mFolderLocation, trickplayLogBuilder);
                if (!result.isValid()) {
                    DLog.warnf("DWNLD_SDK Runtime error: The BIF file we parsed did not yield any usable data.");
                    this.mResultHolder.transitionToError(PluginErrorType.RUNTIME);
                    trickplayLogBuilder.withCreateManifestTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withCreateManifestError(PluginLogConstants$DownloadError.INVALID_FILE);
                    deleteInvalidBif();
                    return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, "We received manifest data that did not meet our requirements: at least one valid image.");
                }
                this.mResultHolder.setPluginAvailability(PluginLoadStatus.Availability.AVAILABLE);
                this.mResultHolder.transitionToStatus(PluginLoadStatus.Status.LOADED);
                this.mResultHolder.setTrickplayManifest(result);
                if (this.mTrickplayManifestSerializer.persistManifest(result, this.mFolderLocation)) {
                    trickplayLogBuilder.withCreateManifestTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withManifestPersisted();
                    return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, "Parsed and persisted trickplay manifest");
                }
                trickplayLogBuilder.withCreateManifestTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withManifestNotPersisted();
                DLog.warnf("DWNLD_SDK Unable to persist manifest for %s", getPluginContext().getTitleId());
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, "Parsed but could not persist trickplay manifest");
            } catch (BifContractException e) {
                DLog.exceptionf(e, "DWNLD_SDK Buffer from the BIF file did not have appropriate bits, BIF file likely corrupted.", new Object[0]);
                this.mResultHolder.transitionToError(PluginErrorType.PARSE);
                long jElapsed = stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS);
                getPluginContext().getEventReporter().reportError("TrickPlayBifContractException", String.format("Asin: %s, %s", getPluginContext().getTitleId(), e.toString()), null);
                trickplayLogBuilder.withCreateManifestTime(jElapsed).withCreateManifestException(e);
                deleteInvalidBif();
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, "Unable to parse bif file");
            } catch (IOException e2) {
                DLog.exceptionf(e2);
                this.mResultHolder.transitionToError(PluginErrorType.RUNTIME);
                trickplayLogBuilder.withCreateManifestTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS)).withCreateManifestException(e2);
                deleteInvalidBif();
                return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, "Unable to parse bif file");
            }
        }

        private void deleteInvalidBif() {
            DiskUtils.deleteFile(new File(getPluginContext().getStoragePath(), "trickplay"));
        }
    }

    static class BifFileExtractor {
        private final BifHeaderStreamReader mBifHeaderStreamReader;
        private final BifImageStreamReader mBifImageStreamReader;
        private final BifSegmentStreamReader mBifSegmentStreamReader;
        private final StorageHelper mStorageHelper;
        private final TrickplayConfig mTrickplayConfig;

        BifFileExtractor() {
            this(StorageHelper.getInstance(), TrickplayConfig.getInstance());
        }

        BifFileExtractor(@Nonnull StorageHelper storageHelper, @Nonnull TrickplayConfig trickplayConfig) {
            this.mBifHeaderStreamReader = new BifHeaderStreamReader();
            this.mBifSegmentStreamReader = new BifSegmentStreamReader();
            this.mBifImageStreamReader = new BifImageStreamReader();
            this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
            this.mTrickplayConfig = (TrickplayConfig) Preconditions.checkNotNull(trickplayConfig, "TrickplayConfig");
        }

        public VodTrickplayManifest parseResult(File file, File file2, TrickplayPluginLog.Builder builder) throws Throwable {
            Closer closerCreate = Closer.create();
            try {
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    closerCreate.register(bufferedInputStream);
                    ByteArrayRecycler byteArrayRecycler = new ByteArrayRecycler();
                    byteArrayRecycler.requestByteArray(this.mTrickplayConfig.getPreviousMaxAllocation());
                    HeaderData headerData = this.mBifHeaderStreamReader.read(bufferedInputStream, byteArrayRecycler);
                    VodTrickplayManifest.Builder builder2 = new VodTrickplayManifest.Builder();
                    SegmentsData segments = this.mBifSegmentStreamReader.readSegments(bufferedInputStream, headerData, byteArrayRecycler);
                    ImmutableList<SegmentData> segments2 = segments.getSegments();
                    StringBuilder sb = new StringBuilder();
                    Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
                    int maxImageSize = segments.getMaxImageSize();
                    this.mTrickplayConfig.updateMaxAllocation(maxImageSize);
                    byteArrayRecycler.requestByteArray(maxImageSize);
                    int i = 0;
                    while (i < headerData.getImageCount()) {
                        SegmentData segmentData = segments2.get(i);
                        int i2 = i + 1;
                        addImageToManifest(builder2, segmentData.getTimecodeMillis(), file2, this.mBifImageStreamReader.readImage(bufferedInputStream, segments2.get(i2).getImageStartOffset() - segmentData.getImageStartOffset(), byteArrayRecycler), sb);
                        builder.withNewParsedImage();
                        i = i2;
                    }
                    builder.withParseImagesTime(stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS));
                    return builder2.build();
                } catch (BufferUnderflowException unused) {
                    throw closerCreate.rethrow(new BifContractException(BifContractException.Reason.BUFFER_UNDERFLOW, 0L));
                } catch (Throwable th) {
                    throw closerCreate.rethrow(th);
                }
            } finally {
                closerCreate.close();
            }
        }

        private void addImageToManifest(VodTrickplayManifest.Builder builder, long j, File file, InputStream inputStream, StringBuilder sb) throws Throwable {
            sb.setLength(0);
            sb.append(j);
            sb.append(".jpg");
            File file2 = new File(file, sb.toString());
            Files.createParentDirs(file2);
            if (file2.createNewFile()) {
                DLog.logf("DWNLD_SDK Created ImageFile");
            }
            Files.asByteSink(file2, new FileWriteMode[0]).writeFrom(inputStream);
            builder.putFileAtTimecode(j, file2.getPath());
        }

        private static class BifHeaderStreamReader {
            private BifHeaderStreamReader() {
            }

            public HeaderData read(InputStream inputStream, ByteArrayRecycler byteArrayRecycler) throws IOException, BufferUnderflowException, BifContractException {
                byte[] bArrRequestByteArray = byteArrayRecycler.requestByteArray(8);
                if (inputStream.read(bArrRequestByteArray, 0, 8) < 8) {
                    throw new IOException("BifFile data stream ended prematurely reading the header sanity number");
                }
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrRequestByteArray);
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                byteBufferWrap.order(byteOrder);
                long j = byteBufferWrap.getLong();
                if (j != 727905341903815305L) {
                    throw new BifContractException(BifContractException.Reason.MAGIC_NUMBER, j);
                }
                if (inputStream.skip(4L) < 4) {
                    throw new IOException("BifFile data stream ended prematurely skipping header version");
                }
                byte[] bArrRequestByteArray2 = byteArrayRecycler.requestByteArray(8);
                if (inputStream.read(bArrRequestByteArray2, 0, 8) < 8) {
                    throw new IOException("BifFile data stream ended prematurely reading the header");
                }
                if (inputStream.skip(44L) < 44) {
                    throw new IOException("BifFile data stream ended prematurely skipping the expansion bytes");
                }
                ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(bArrRequestByteArray2);
                byteBufferWrap2.order(byteOrder);
                return new HeaderData(byteBufferWrap2.getInt(), byteBufferWrap2.getInt());
            }
        }

        private static class BifSegmentStreamReader {
            private final TrickplayConfig mTrickplayConfig;

            private BifSegmentStreamReader() {
                this(TrickplayConfig.getInstance());
            }

            BifSegmentStreamReader(@Nonnull TrickplayConfig trickplayConfig) {
                this.mTrickplayConfig = (TrickplayConfig) Preconditions.checkNotNull(trickplayConfig, "TrickplayConfig");
            }

            public SegmentsData readSegments(InputStream inputStream, HeaderData headerData, ByteArrayRecycler byteArrayRecycler) throws IOException, BufferUnderflowException, BifContractException {
                int imageCount = (headerData.getImageCount() + 1) * 8;
                if (imageCount > this.mTrickplayConfig.getMaxTrickplayParseMemoryBytes()) {
                    DLog.logf("DWNLD_SDK Trickplay segment request too large: %d bytes", Integer.valueOf(imageCount));
                    throw new BifContractException(BifContractException.Reason.MEMORY_ALLOCATION, imageCount);
                }
                byte[] bArrRequestByteArray = byteArrayRecycler.requestByteArray(imageCount);
                int i = 0;
                if (inputStream.read(bArrRequestByteArray, 0, imageCount) < imageCount) {
                    throw new IOException("BifFile data stream ended prematurely reading the segment bytes");
                }
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrRequestByteArray);
                byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                ImmutableList.Builder builder = ImmutableList.builder();
                int iMax = 0;
                int i2 = 0;
                while (i < headerData.getImageCount() + 1) {
                    long timecodeMultiplier = ((long) headerData.getTimecodeMultiplier()) * ((long) byteBufferWrap.getInt());
                    int i3 = byteBufferWrap.getInt();
                    builder.add(new SegmentData(timecodeMultiplier, i3));
                    if (i != 0) {
                        iMax = Math.max(iMax, i3 - i2);
                    }
                    i++;
                    i2 = i3;
                }
                if (iMax > this.mTrickplayConfig.getMaxTrickplayParseMemoryBytes()) {
                    DLog.logf("DWNLD_SDK Image size request too large: %d bytes", Integer.valueOf(iMax));
                    throw new BifContractException(BifContractException.Reason.MEMORY_ALLOCATION, iMax);
                }
                return new SegmentsData(builder.build(), iMax);
            }
        }

        private static class BifImageStreamReader {
            private BifImageStreamReader() {
            }

            public InputStream readImage(InputStream inputStream, int i, ByteArrayRecycler byteArrayRecycler) throws IOException {
                byte[] bArrRequestByteArray = byteArrayRecycler.requestByteArray(i);
                if (inputStream.read(bArrRequestByteArray, 0, i) < i) {
                    throw new IOException("BifFile data stream ended prematurely reading the images");
                }
                return new ByteArrayInputStream(bArrRequestByteArray, 0, i);
            }
        }

        private static class HeaderData {
            private final int mImageCount;
            private final int mTimecodeMultiplier;

            public HeaderData(int i, int i2) {
                this.mImageCount = i;
                this.mTimecodeMultiplier = i2;
            }

            public int getImageCount() {
                return this.mImageCount;
            }

            public int getTimecodeMultiplier() {
                return this.mTimecodeMultiplier;
            }
        }

        private static class SegmentData {
            private final int mImageStartOffset;
            private final long mTimecodeMillis;

            public SegmentData(long j, int i) {
                this.mTimecodeMillis = j;
                this.mImageStartOffset = i;
            }

            public long getTimecodeMillis() {
                return this.mTimecodeMillis;
            }

            public int getImageStartOffset() {
                return this.mImageStartOffset;
            }
        }

        private static class SegmentsData {
            private final int mMaxImageSize;
            private final ImmutableList<SegmentData> mSegments;

            public SegmentsData(ImmutableList<SegmentData> immutableList, int i) {
                this.mSegments = immutableList;
                this.mMaxImageSize = i;
            }

            public ImmutableList<SegmentData> getSegments() {
                return this.mSegments;
            }

            public int getMaxImageSize() {
                return this.mMaxImageSize;
            }
        }
    }

    public static class TrickplayResponseHolder extends PluginResponseHolder {
        private TrickplayIndex mCachedTrickplayIndex;
        private long mBifFileSize = -1;
        private TrickplayPluginLog.Builder mLogBuilder = TrickplayPluginLog.newBuilder();

        @Override // com.amazon.avod.media.download.plugin.PluginResponseHolder
        public void reset() {
            super.reset();
            this.mCachedTrickplayIndex = null;
            this.mLogBuilder = TrickplayPluginLog.newBuilder();
        }

        public void setBifFileSize(long j) {
            this.mBifFileSize = j;
        }

        public void setTrickplayManifest(@Nonnull TrickplayManifest trickplayManifest) {
            Preconditions.checkNotNull(trickplayManifest, "Only invoke if we have a manifest.");
            Preconditions.checkArgument(trickplayManifest.isValid(), "Only set trickplay manifest if it is a valid manifest.");
            this.mCachedTrickplayIndex = trickplayManifest.isLive() ? new LiveTrickplayIndex(trickplayManifest) : new VodTrickplayIndex(trickplayManifest);
        }

        public boolean hasData() {
            return this.mCachedTrickplayIndex != null;
        }

        public TrickplayLoadingResponse getFinishedResult() {
            return new TrickplayLoadingResponseImpl(deriveFinishedResult(), this.mCachedTrickplayIndex, this.mBifFileSize);
        }

        @Nonnull
        public TrickplayPluginLog.Builder getTrickplayLogBuilder() {
            return this.mLogBuilder;
        }
    }

    @Immutable
    private static class TrickplayLoadingResponseImpl implements TrickplayLoadingResponse {
        private final long mBifFileSize;
        private final PluginLoadStatus mLoadingStatus;
        private final Optional<TrickplayIndex> mTrickplayIndex;

        public TrickplayLoadingResponseImpl(@Nonnull PluginLoadStatus pluginLoadStatus, @Nullable TrickplayIndex trickplayIndex, long j) {
            this.mLoadingStatus = (PluginLoadStatus) Preconditions.checkNotNull(pluginLoadStatus, "Load status cannot be null");
            this.mTrickplayIndex = Optional.fromNullable(trickplayIndex);
            this.mBifFileSize = j;
        }

        @Override // com.amazon.avod.playbackclient.trickplay.download.TrickplayLoadingResponse
        public PluginLoadStatus getLoadingStatus() {
            return this.mLoadingStatus;
        }

        @Override // com.amazon.avod.playbackclient.trickplay.download.TrickplayLoadingResponse
        public Optional<TrickplayIndex> getTrickplayIndex() {
            return this.mTrickplayIndex;
        }

        @Override // com.amazon.avod.playbackclient.trickplay.download.TrickplayLoadingResponse
        public long getBifFileSize() {
            return this.mBifFileSize;
        }
    }

    @NotThreadSafe
    private static class ByteArrayRecycler {
        private static final ImmutableSortedSet<Integer> BYTE_SIZES = ImmutableSortedSet.of((Comparable) 64, (Comparable) 128, (Comparable) 256, (Comparable) 512, (Comparable) 1024, (Comparable) 2048, (Comparable[]) new Integer[]{4096, 8192, 16384, 32768, 65536, 131072});
        private byte[] mByteArray;

        private ByteArrayRecycler() {
            this.mByteArray = null;
        }

        @SuppressFBWarnings(justification = "internal byte array exposed for reuse for performance reasons", value = {"EI_EXPOSE_REP"})
        public byte[] requestByteArray(int i) {
            byte[] bArr = this.mByteArray;
            if (bArr == null || bArr.length < i) {
                Integer numHigher = BYTE_SIZES.higher(Integer.valueOf(i));
                if (numHigher == null) {
                    if (i < 2097152) {
                        i += i / 4;
                    }
                    this.mByteArray = new byte[i];
                } else {
                    this.mByteArray = new byte[numHigher.intValue()];
                }
            }
            return this.mByteArray;
        }
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public Optional<ContentFetcherPluginAction> getCleanupAction() {
        return Optional.absent();
    }
}
