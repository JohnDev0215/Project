package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.avod.media.framework.memory.ByteBufferInputStream;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.subtitles.SubtitlesListener;
import com.amazon.avod.playbackclient.listeners.SubtitleLoadListener;
import com.amazon.avod.playbackclient.live.LiveLanguageTransformer;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener;
import com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleDataProvider;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.ProfiledRunnable;
import com.amazon.avod.threading.UIThreadUtils;
import com.amazon.avod.util.Preconditions2;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitleLoader;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleStreamingDownloader implements SubtitleDownloader {
    private final ImmutableSet<SubtitleLanguage> mAvailableSubtitleLanguages;
    private final ExecutorService mExecutorService;
    private final LanguageSupportedListener mLanguageSupportedListener;
    private final PlaybackExperienceController mPlaybackExperienceController;
    private final DataLoadedSubtitleListener mSubtitlesListener;

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void downloadFinished() {
    }

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void initialize() {
    }

    public SubtitleStreamingDownloader(@Nonnull PlaybackExperienceController playbackExperienceController, @Nonnull LanguageSupportedListener languageSupportedListener, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet) {
        this(playbackExperienceController, new SubtitleDataProvider(), languageSupportedListener, subtitleLoadListener, immutableSet, ExecutorBuilder.newBuilderFor(SubtitleStreamingDownloader.class, new String[0]).withFixedThreadPoolSize(1).build());
    }

    SubtitleStreamingDownloader(@Nonnull PlaybackExperienceController playbackExperienceController, @Nonnull SubtitleDataProvider subtitleDataProvider, @Nonnull LanguageSupportedListener languageSupportedListener, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull ExecutorService executorService) {
        this(playbackExperienceController, new DataLoadedSubtitleListener(subtitleDataProvider, subtitleLoadListener, executorService, playbackExperienceController), languageSupportedListener, immutableSet, executorService);
    }

    SubtitleStreamingDownloader(@Nonnull PlaybackExperienceController playbackExperienceController, @Nonnull DataLoadedSubtitleListener dataLoadedSubtitleListener, @Nonnull LanguageSupportedListener languageSupportedListener, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull ExecutorService executorService) {
        this.mPlaybackExperienceController = (PlaybackExperienceController) Preconditions.checkNotNull(playbackExperienceController, "playbackExperienceController");
        this.mSubtitlesListener = (DataLoadedSubtitleListener) Preconditions.checkNotNull(dataLoadedSubtitleListener, "subtitlesListener");
        this.mLanguageSupportedListener = (LanguageSupportedListener) Preconditions.checkNotNull(languageSupportedListener, "languageSupportedListener");
        this.mAvailableSubtitleLanguages = (ImmutableSet) Preconditions.checkNotNull(immutableSet, "subtitlesFromContext");
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
    }

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void startDownload() {
        this.mLanguageSupportedListener.onLanguagesAvailable(this.mAvailableSubtitleLanguages, ImmutableSet.of());
        this.mPlaybackExperienceController.registerSubtitleListener(this.mSubtitlesListener);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader
    public void destroy() {
        this.mPlaybackExperienceController.deregisterSubtitleListener(this.mSubtitlesListener);
        this.mSubtitlesListener.clear();
        this.mExecutorService.shutdown();
    }

    static class DataLoadedSubtitleListener implements SubtitlesListener {
        private static final Profiler.TraceLevel TRACE_LEVEL = Profiler.TraceLevel.INFO;
        private final ExecutorService mExecutorService;
        private final LiveLanguageTransformer mLiveLanguageTransformer;
        private final PlaybackExperienceController mPlaybackExperienceController;
        private final SubtitleLoadListener mSubtitleLoadListener;
        private final SubtitleLoader mSubtitleLoader;
        private final Object mToken;

        DataLoadedSubtitleListener(@Nonnull SubtitleDataProvider subtitleDataProvider, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull ExecutorService executorService, @Nonnull PlaybackExperienceController playbackExperienceController) {
            this(new SubtitleLoader(subtitleDataProvider), subtitleLoadListener, new LiveLanguageTransformer(), new Object(), executorService, playbackExperienceController);
        }

        DataLoadedSubtitleListener(@Nonnull SubtitleLoader subtitleLoader, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull LiveLanguageTransformer liveLanguageTransformer, @Nonnull Object obj, @Nonnull ExecutorService executorService, @Nonnull PlaybackExperienceController playbackExperienceController) {
            this.mSubtitleLoader = (SubtitleLoader) Preconditions.checkNotNull(subtitleLoader, "subtitleLoader");
            this.mSubtitleLoadListener = (SubtitleLoadListener) Preconditions.checkNotNull(subtitleLoadListener, "subtitleLoadListener");
            this.mLiveLanguageTransformer = (LiveLanguageTransformer) Preconditions.checkNotNull(liveLanguageTransformer, "liveLanguageTransformer");
            this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
            this.mPlaybackExperienceController = (PlaybackExperienceController) Preconditions.checkNotNull(playbackExperienceController, "playbackExperienceController");
            this.mToken = obj;
        }

        @Override // com.amazon.avod.playback.subtitles.SubtitlesListener
        public void onRenderSubtitles(ByteBuffer byteBuffer, final long j, long j2, final long j3, String str) {
            Preconditions2.checkNotMainThread();
            if (Strings.isNullOrEmpty(str)) {
                this.mPlaybackExperienceController.onSubtitlesCallbackComplete();
                return;
            }
            final String strTransformManifestCodeToLanguageCode = this.mLiveLanguageTransformer.transformManifestCodeToLanguageCode(str);
            if (strTransformManifestCodeToLanguageCode == null) {
                this.mPlaybackExperienceController.onSubtitlesCallbackComplete();
                return;
            }
            final ByteBufferInputStream byteBufferInputStream = new ByteBufferInputStream(byteBuffer);
            final Optional optionalOf = Optional.of(Long.valueOf(j));
            final Optional optionalOf2 = Optional.of(Long.valueOf(j2));
            this.mExecutorService.execute(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleStreamingDownloader.DataLoadedSubtitleListener.1SubtitleParseRunnable
                @Override // java.lang.Runnable
                public void run() {
                    final SubtitleLanguage subtitleLanguage = new SubtitleLanguage(strTransformManifestCodeToLanguageCode, SubtitleType.SUBTITLE, null, null, false, "");
                    final SubtitleCollection subtitleCollectionLoadFromStream = DataLoadedSubtitleListener.this.mSubtitleLoader.loadFromStream(byteBufferInputStream, subtitleLanguage, j3, j);
                    DataLoadedSubtitleListener.this.mPlaybackExperienceController.onSubtitlesCallbackComplete();
                    if (subtitleCollectionLoadFromStream != null) {
                        UIThreadUtils.postToUIThreadWithToken(new ProfiledRunnable(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleStreamingDownloader.DataLoadedSubtitleListener.1SubtitleParseRunnable.1OnDataLoadedRunnable
                            @Override // java.lang.Runnable
                            public void run() {
                                SubtitleLoadListener subtitleLoadListener = DataLoadedSubtitleListener.this.mSubtitleLoadListener;
                                SubtitleLanguage subtitleLanguage2 = subtitleLanguage;
                                SubtitleCollection subtitleCollection = subtitleCollectionLoadFromStream;
                                C1SubtitleParseRunnable c1SubtitleParseRunnable = C1SubtitleParseRunnable.this;
                                subtitleLoadListener.onDataLoadResponded(subtitleLanguage2, subtitleCollection, optionalOf, optionalOf2);
                            }
                        }, DataLoadedSubtitleListener.TRACE_LEVEL, "Live Subtitle Parse Runnable", new Object[0]), DataLoadedSubtitleListener.this.mToken);
                    }
                }
            });
        }

        public void clear() {
            UIThreadUtils.removeCallbacksAndMessages(this.mToken);
        }
    }
}
