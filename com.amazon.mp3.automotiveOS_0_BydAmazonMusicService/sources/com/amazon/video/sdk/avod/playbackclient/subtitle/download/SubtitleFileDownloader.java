package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.core.Subtitle;
import com.amazon.avod.events.proxy.EventListenerProxy;
import com.amazon.avod.events.proxy.HandlerBasedListenerProxyFactory;
import com.amazon.avod.perf.PlaybackSubtitleMetrics;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playbackclient.listeners.SubtitleLoadListener;
import com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListener;
import com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListenerProxy;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListenerProxy;
import com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader;
import com.amazon.avod.playbackclient.subtitle.download.SubtitleLoadListenerProxy;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleDataProvider;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.threading.ATVAndroidAsyncTask;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitleLoader;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import java.io.File;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleFileDownloader implements SubtitleDownloader {
    private final HandlerBasedListenerProxyFactory mHandlerBasedListenerProxyFactory;
    private boolean mIsInitialized;
    private final LanguageDownloadListener mLanguageDownloadListener;
    private final LanguageDownloadListenerProxy mLanguageDownloadListenerProxy;
    private final LanguageSupportedListener mLanguageSupportedListener;
    private final LanguageSupportedListenerProxy mLanguageSupportedListenerProxy;
    private final SubtitleDataProvider mSubtitleDataProvider;
    private final SubtitleLoadListener mSubtitleLoadListener;
    private final SubtitleLoadListenerProxy mSubtitleLoadListenerProxy;
    private final SubtitleLoadListener mSubtitleParseLoadListener;
    private final Map<SubtitleLanguage, SubtitleParseTask> mSubtitleParseTasks;
    private final SubtitleContentPlugin mSubtitlePlugin;
    private final Stopwatch mTimer;

    public SubtitleFileDownloader(@Nonnull LanguageSupportedListener languageSupportedListener, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull SubtitleContentPlugin subtitleContentPlugin) {
        this(new SubtitleDataProvider(), new SubtitleLoadListenerProxy(), new HandlerBasedListenerProxyFactory(), new LanguageSupportedListenerProxy(), new LanguageDownloadListenerProxy(), languageSupportedListener, subtitleLoadListener, subtitleContentPlugin);
    }

    SubtitleFileDownloader(@Nonnull SubtitleDataProvider subtitleDataProvider, @Nonnull SubtitleLoadListenerProxy subtitleLoadListenerProxy, @Nonnull HandlerBasedListenerProxyFactory handlerBasedListenerProxyFactory, @Nonnull LanguageSupportedListenerProxy languageSupportedListenerProxy, @Nonnull LanguageDownloadListenerProxy languageDownloadListenerProxy, @Nonnull LanguageSupportedListener languageSupportedListener, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull SubtitleContentPlugin subtitleContentPlugin) {
        this.mTimer = Stopwatch.createUnstarted(Tickers.androidTicker());
        this.mSubtitleParseLoadListener = new SubtitleLoadListener() { // from class: com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleFileDownloader.1
            @Override // com.amazon.avod.playbackclient.listeners.SubtitleLoadListener
            public void onDataLoadResponded(SubtitleLanguage subtitleLanguage, SubtitleCollection subtitleCollection, Optional<Long> optional, Optional<Long> optional2) {
                SubtitleFileDownloader.this.mSubtitleParseTasks.remove(subtitleLanguage);
            }
        };
        this.mLanguageDownloadListener = new LanguageDownloadListener() { // from class: com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleFileDownloader.2
            @Override // com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListener
            public void onLanguageDownload(Subtitle subtitle, long j, File file) {
                if (SubtitleFileDownloader.this.mIsInitialized) {
                    DLog.logf("%s ready to parse (took %s to download)", subtitle, Long.valueOf(j));
                    SubtitleFileDownloader.this.startSubtitleParseTask(file, subtitle);
                }
            }
        };
        this.mSubtitleDataProvider = (SubtitleDataProvider) Preconditions.checkNotNull(subtitleDataProvider, "SubtitleDataProvider cannot be null");
        this.mSubtitleLoadListenerProxy = (SubtitleLoadListenerProxy) Preconditions.checkNotNull(subtitleLoadListenerProxy, "SubtitleLoadListenerProxy cannot be null");
        this.mHandlerBasedListenerProxyFactory = (HandlerBasedListenerProxyFactory) Preconditions.checkNotNull(handlerBasedListenerProxyFactory, "HandlerBasedListenerProxyFactory cannot be null");
        this.mLanguageSupportedListenerProxy = (LanguageSupportedListenerProxy) Preconditions.checkNotNull(languageSupportedListenerProxy, "LanguageSupportedListenerProxy cannot be null");
        this.mLanguageDownloadListenerProxy = (LanguageDownloadListenerProxy) Preconditions.checkNotNull(languageDownloadListenerProxy, "LanguageDownloadListenerProxy cannot be null");
        this.mSubtitleParseTasks = Maps.newHashMap();
        this.mLanguageSupportedListener = (LanguageSupportedListener) Preconditions.checkNotNull(languageSupportedListener, "languageSupportedListener");
        this.mSubtitleLoadListener = (SubtitleLoadListener) Preconditions.checkNotNull(subtitleLoadListener, "subtitleLoadListener");
        this.mSubtitlePlugin = (SubtitleContentPlugin) Preconditions.checkNotNull(subtitleContentPlugin, "subtitlePlugin");
    }

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void initialize() {
        this.mSubtitleLoadListenerProxy.addListener(this.mSubtitleParseLoadListener);
        this.mLanguageSupportedListenerProxy.addListener(this.mLanguageSupportedListener);
        this.mSubtitleLoadListenerProxy.addListener(this.mSubtitleLoadListener);
        this.mIsInitialized = true;
    }

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void startDownload() {
        Profiler.trigger(PlaybackSubtitleMetrics.BEGIN_SUBTITLE_DOWNLOAD_TASK);
        this.mTimer.start();
        this.mLanguageDownloadListenerProxy.addListener(this.mLanguageDownloadListener);
        LanguageSupportedListener languageSupportedListener = (LanguageSupportedListener) this.mHandlerBasedListenerProxyFactory.createUiProxy(LanguageSupportedListener.class, this.mLanguageSupportedListenerProxy);
        LanguageDownloadListener languageDownloadListener = (LanguageDownloadListener) this.mHandlerBasedListenerProxyFactory.createUiProxy(LanguageDownloadListener.class, this.mLanguageDownloadListenerProxy);
        EventListenerProxy.startDispatchingEvents(languageSupportedListener);
        EventListenerProxy.startDispatchingEvents(languageDownloadListener);
        this.mSubtitlePlugin.setLanguageSupportedListener(languageSupportedListener);
        this.mSubtitlePlugin.setLanguageDownloadListener(languageDownloadListener);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void downloadFinished() {
        Profiler.trigger(PlaybackSubtitleMetrics.END_SUBTITLE_DOWNLOAD_TASK);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void destroy() {
        if (this.mIsInitialized) {
            ATVAndroidAsyncTask.cancelAsyncTasksIfNeeded(this.mSubtitleParseTasks.values());
            this.mSubtitleParseTasks.clear();
            if (this.mSubtitlePlugin != null) {
                this.mLanguageDownloadListenerProxy.clear();
                this.mLanguageSupportedListenerProxy.clear();
            }
            this.mSubtitleLoadListenerProxy.clear();
            this.mIsInitialized = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSubtitleParseTask(File file, Subtitle subtitle) {
        SubtitleLanguage subtitleLanguage = new SubtitleLanguage(subtitle);
        if (this.mSubtitleParseTasks.containsKey(subtitleLanguage)) {
            return;
        }
        SubtitleParseTask subtitleParseTask = new SubtitleParseTask(file, subtitle, this.mSubtitleLoadListenerProxy, this.mSubtitleDataProvider);
        subtitleParseTask.execute(new Void[0]);
        this.mSubtitleParseTasks.put(subtitleLanguage, subtitleParseTask);
        DLog.logf("Started parsing: %s for %s", subtitleParseTask, subtitleLanguage.toString().toLowerCase());
    }

    private static class SubtitleParseTask extends ATVAndroidAsyncTask<Void, Void, SubtitleCollection> {
        private final File mFile;
        private final Subtitle mSubtitleData;
        private final SubtitleLoadListener mSubtitleLoadListener;
        private final SubtitleLoader mSubtitleLoader;

        SubtitleParseTask(@Nonnull File file, @Nonnull Subtitle subtitle, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull SubtitleDataProvider subtitleDataProvider) {
            this(file, subtitle, subtitleLoadListener, new SubtitleLoader(subtitleDataProvider));
        }

        SubtitleParseTask(@Nonnull File file, @Nonnull Subtitle subtitle, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull SubtitleLoader subtitleLoader) {
            this.mFile = (File) Preconditions.checkNotNull(file, "subtitleFile");
            this.mSubtitleData = (Subtitle) Preconditions.checkNotNull(subtitle, "subtitleData");
            this.mSubtitleLoadListener = (SubtitleLoadListener) Preconditions.checkNotNull(subtitleLoadListener, "SubtitleLoadListener cannot be null");
            this.mSubtitleLoader = (SubtitleLoader) Preconditions.checkNotNull(subtitleLoader, "subtitleLoader");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.avod.threading.ATVAndroidAsyncTask
        public SubtitleCollection doInBackground(Void... voidArr) {
            return this.mSubtitleLoader.loadFromFile(this.mFile, this.mSubtitleData);
        }

        @Override // com.amazon.avod.threading.ATVAndroidAsyncTask
        public void onPostExecute(SubtitleCollection subtitleCollection) {
            this.mSubtitleLoadListener.onDataLoadResponded(new SubtitleLanguage(this.mSubtitleData), subtitleCollection, Optional.absent(), Optional.absent());
        }
    }
}
