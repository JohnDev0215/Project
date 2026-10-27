package com.amazon.video.sdk.avod.playbackclient.activity.feature;

import android.content.Context;
import android.view.ViewGroup;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutor;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorFactory;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorListener;
import com.amazon.avod.media.download.plugin.reporting.SubtitleChangeCause;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.perf.PlaybackSubtitleMetrics;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.IllegalPlayerStateException;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.sye.SyePlaybackExperienceController;
import com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener;
import com.amazon.avod.playbackclient.listeners.SubtitleLoadListener;
import com.amazon.avod.playbackclient.subtitle.SubtitlePresetSource;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener;
import com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitlePreferences;
import com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer;
import com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Throwables2;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatch;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatchFactory;
import com.amazon.video.sdk.avod.playbackclient.control.states.PlaybackControllerContext;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitleEventReporter;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePresets;
import com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleContentPlugin;
import com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleDownloaderFactory;
import com.amazon.video.sdk.avod.playbackclient.subtitle.presenters.SubtitlePresenter;
import com.amazon.video.sdk.avod.playbackclient.subtitle.presenters.rendering.SubtitleRendererFactory;
import com.amazon.video.sdk.avod.playbackclient.subtitle.views.DefaultSubtitleTextController;
import com.amazon.video.sdk.avod.playbackclient.subtitle.views.TTMLV2SubtitleTextController;
import com.amazon.video.sdk.avod.playbackclient.subtitle.views.util.SubtitleRenderPresetsAivFactory;
import com.amazon.video.sdk.avod.playbackclient.subtitle.views.util.SubtitleRenderPresetsDeviceRetriever;
import com.amazon.video.sdk.avod.playbackclient.utils.LoopRunner;
import com.amazon.video.sdk.avod.playbackclient.utils.SubtitlesLanguageHelper;
import com.amazon.video.sdk.stream.AudioStreamGroup;
import com.amazon.video.sdk.stream.StreamPreferencesProvider;
import com.amazon.video.sdk.stream.TimedTextConfig;
import com.amazon.video.sdk.stream.TimedTextStream;
import com.amazon.video.sdk.stream.TimedTextStreamData;
import com.amazon.video.sdk.stream.TimedTextStreamMatcher;
import com.amazon.video.sdk.stream.TimedTextSubtype;
import com.amazon.video.sdk.stream.TimedTextType;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class PlaybackSubtitleFeature {
    private final AudioStreamGroup mAudioStreamGroup;
    private String mAudioTrackId;
    private ContentFetcherPluginContext mContentFetcherPluginContent;
    private Context mContext;
    private boolean mHasPlaybackStarted;
    private boolean mIsFeatureActive;
    private PlaybackEventDispatch mPlaybackEventDispatch;
    private PlaybackExperienceController mPlaybackExperienceController;
    private String mSpokenLanguage;
    private AudioTrackChangeListener mSpokenLanguageChangeListener;
    private SubtitleDownloader mSubtitleDownloader;
    private SubtitleEventReporter mSubtitleEventReporter;
    private SubtitleContentPlugin mSubtitlePlugin;
    private SubtitlePresenter mSubtitlePresenter;
    private SubtitleRenderer mSubtitleRenderer;
    private SubtitleTextController mSubtitleTextController;
    private LoopRunner mSubtitleUpdater;
    private TimedTextConfig mTimedTextConfig;
    private final StreamPreferencesProvider<TimedTextStreamMatcher> mTimedTextStreamPreferencesProvider;
    private VideoPresentation mVideoPresentation;
    private ImmutableSet<SubtitleLanguage> mSupportedSubtitleLanguages = ImmutableSet.of();
    private ImmutableSet<SubtitleLanguage> mSupportedNarrativeLanguages = ImmutableSet.of();
    private ImmutableList<AudioTrackMetadata> mAudioTrackMetadataList = ImmutableList.of();
    private final AtomicBoolean mIsFeatureDestroyed = new AtomicBoolean();
    private final Object mMutex = new Object();
    private final LanguageSupportedListener mLanguageSupportedListener = new LanguageSupportedListener() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackSubtitleFeature.1
        @Override // com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener
        public void onLanguagesAvailable(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet2) {
            if (PlaybackSubtitleFeature.this.mIsFeatureDestroyed.get() || !PlaybackSubtitleFeature.this.hasPlaybackStarted()) {
                return;
            }
            PlaybackSubtitleFeature.this.mSupportedSubtitleLanguages = (ImmutableSet) Preconditions.checkNotNull(immutableSet, "supportedSubtitleLanguages");
            PlaybackSubtitleFeature.this.mSupportedNarrativeLanguages = (ImmutableSet) Preconditions.checkNotNull(immutableSet2, "supportedNarrativeLanguages");
            PlaybackSubtitleFeature playbackSubtitleFeature = PlaybackSubtitleFeature.this;
            playbackSubtitleFeature.setAvailableFilteredByTrackGroupId(playbackSubtitleFeature.mAudioTrackId, PlaybackSubtitleFeature.this.mSupportedSubtitleLanguages, PlaybackSubtitleFeature.this.mSupportedNarrativeLanguages);
        }
    };
    private final PlaybackStateEventListener mPlaybackStateEventListener = new BasePlaybackStateEventListener() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackSubtitleFeature.3
        @Override // com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener, com.amazon.avod.playback.PlaybackStateEventListener
        public void onStart(PlaybackEventContext playbackEventContext) {
            PlaybackSubtitleFeature.this.notifyStart();
        }

        @Override // com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener, com.amazon.avod.playback.PlaybackStateEventListener
        public void onResume(PlaybackEventContext playbackEventContext) {
            if (PlaybackSubtitleFeature.this.mIsFeatureDestroyed.get()) {
                return;
            }
            boolean zAreSubtitlesEnabled = PlaybackSubtitleFeature.this.mSubtitleConfig.getSubtitlePreferences().areSubtitlesEnabled();
            PlaybackSubtitleFeature.this.onPlaybackResume();
            if (PlaybackSubtitleFeature.this.areForcedNarrativesAvailable() || (zAreSubtitlesEnabled && PlaybackSubtitleFeature.this.areSubtitlesAvailable())) {
                PlaybackSubtitleFeature.this.mSubtitleUpdater.start();
            }
        }

        @Override // com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener, com.amazon.avod.playback.PlaybackStateEventListener
        public void onPause(PlaybackEventContext playbackEventContext) {
            if (PlaybackSubtitleFeature.this.mIsFeatureDestroyed.get()) {
                return;
            }
            PlaybackSubtitleFeature.this.mSubtitleUpdater.stop();
        }
    };
    private final SubtitleLoadListener mSubtitleLoadListener = new SubtitleLoadListener() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackSubtitleFeature.4
        @Override // com.amazon.avod.playbackclient.listeners.SubtitleLoadListener
        public void onDataLoadResponded(@Nonnull SubtitleLanguage subtitleLanguage, @Nullable SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, @Nonnull Optional<Long> optional2) {
            if (PlaybackSubtitleFeature.this.mIsFeatureDestroyed.get() || PlaybackSubtitleFeature.this.mSubtitlePresenter == null) {
                return;
            }
            Preconditions.checkNotNull(subtitleLanguage, "language");
            PlaybackSubtitleFeature.this.mSubtitlePresenter.addDataSource(subtitleLanguage, subtitleCollection, optional, optional2);
            if (subtitleCollection == null || subtitleCollection.isEmpty()) {
                return;
            }
            Profiler.trigger(PlaybackSubtitleMetrics.SUBTITLE_DATA_LOADED);
            DLog.logf("%s successfully loaded", subtitleLanguage);
        }
    };
    private final DownloadService mDownloadService = MediaSystem.getInstance().getDownloadService();
    private final SubtitleConfig mSubtitleConfig = SubtitleConfig.getInstance();
    private final LoopRunner.Factory mLoopRunnerFactory = new LoopRunner.Factory();
    private final SubtitlesLanguageHelper mSubtitlesLanguageHelper = new SubtitlesLanguageHelper();

    public PlaybackSubtitleFeature(@Nonnull Context context, @Nonnull StreamPreferencesProvider<TimedTextStreamMatcher> streamPreferencesProvider, @Nonnull TimedTextConfig timedTextConfig, @Nonnull AudioStreamGroup audioStreamGroup) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mTimedTextStreamPreferencesProvider = (StreamPreferencesProvider) Preconditions.checkNotNull(streamPreferencesProvider, "timeTextPreferencesProvider");
        this.mTimedTextConfig = (TimedTextConfig) Preconditions.checkNotNull(timedTextConfig, "timedTextConfig");
        this.mAudioStreamGroup = (AudioStreamGroup) Preconditions.checkNotNull(audioStreamGroup, "audioStreamGroup");
    }

    public void prepareForPlayback(VideoPresentation videoPresentation, @Nonnull ViewGroup viewGroup) {
        File file;
        Preconditions.checkNotNull(videoPresentation, "videoPresentation");
        Preconditions.checkNotNull(viewGroup, "viewGroup");
        if (this.mIsFeatureDestroyed.get()) {
            return;
        }
        this.mSubtitleUpdater = this.mLoopRunnerFactory.newLoopRunner(this.mSubtitleConfig.getSubtitleUpdatePollMillis(), new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackSubtitleFeature$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$prepareForPlayback$0();
            }
        });
        SubtitleTextController tTMLV2SubtitleTextController = this.mSubtitleConfig.isTTMLV2FullySupportedInRothko() ? new TTMLV2SubtitleTextController(viewGroup) : new DefaultSubtitleTextController(viewGroup);
        this.mSubtitleTextController = tTMLV2SubtitleTextController;
        this.mSubtitlePresenter = new SubtitlePresenter(this.mContext, tTMLV2SubtitleTextController, this.mTimedTextConfig);
        this.mVideoPresentation = videoPresentation;
        VideoPlayer player = videoPresentation.getPlayer();
        PlaybackEventReporter reporter = videoPresentation.getReporter();
        VideoSpecification specification = videoPresentation.getSpecification();
        VideoOptions videoOptions = videoPresentation.getVideoOptions();
        this.mPlaybackExperienceController = player.getPlaybackExperienceController();
        if (this.mVideoPresentation.getStoragePath() != null) {
            file = this.mVideoPresentation.getStoragePath();
        } else {
            file = new File(StorageHelper.getInstance().getGeneralFileDir(), String.format("streaming-plugins/%s", specification.getTitleId()));
        }
        this.mContentFetcherPluginContent = new ContentFetcherPluginContext(specification, file, reporter, ContentFetcherPluginContext.PluginSessionType.PLAYBACK, true, null, videoOptions.getSessionContext(), null);
        SubtitleContentPlugin subtitleContentPlugin = new SubtitleContentPlugin.PluginProvider(this.mDownloadService).get();
        this.mSubtitlePlugin = subtitleContentPlugin;
        subtitleContentPlugin.init(this.mContentFetcherPluginContent);
        this.mSubtitlesLanguageHelper.initialize(specification, this.mContentFetcherPluginContent.getConsumptionType(), videoOptions, this.mPlaybackExperienceController);
        PlaybackEventDispatch playbackEventDispatchCreateAndAttachDispatch = new PlaybackEventDispatchFactory().createAndAttachDispatch(player, new PlaybackControllerContext("Main"));
        this.mPlaybackEventDispatch = playbackEventDispatchCreateAndAttachDispatch;
        playbackEventDispatchCreateAndAttachDispatch.initialize();
        this.mPlaybackEventDispatch.addPlaybackStateEventListener(this.mPlaybackStateEventListener);
        SubtitleEventReporter subtitleEventReporter = new SubtitleEventReporter(this.mPlaybackExperienceController.getAloysiusReporter().getAloysiusTimedTextReporter());
        this.mSubtitleEventReporter = subtitleEventReporter;
        this.mPlaybackEventDispatch.addPlaybackStateEventListener(subtitleEventReporter);
        SubtitleDownloader subtitleDownloaderCreateDownloader = SubtitleDownloaderFactory.createDownloader(this.mPlaybackExperienceController, this.mLanguageSupportedListener, this.mSubtitleLoadListener, getSupportedSubtitleLanguagesFromContext(), this.mSubtitlePlugin);
        this.mSubtitleDownloader = subtitleDownloaderCreateDownloader;
        subtitleDownloaderCreateDownloader.initialize();
        this.mIsFeatureActive = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareForPlayback$0() {
        SubtitlePresenter subtitlePresenter = this.mSubtitlePresenter;
        if (subtitlePresenter != null) {
            subtitlePresenter.updateSubtitles();
        }
    }

    public ImmutableList<TimedTextStream> getCurrentTimedTextStreams() {
        if (this.mSubtitlePresenter == null) {
            DLog.warnf("getCurrentTimedTextStreams() called before prepareForPlayback() can instantiate a SubtitlePresenter instance");
            return ImmutableList.of();
        }
        ImmutableList<Subtitle> immutableListOr = this.mSubtitlesLanguageHelper.getAvailableSubtitles().or(ImmutableList.of());
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<Subtitle> it = immutableListOr.iterator();
        while (it.hasNext()) {
            Subtitle next = it.next();
            DLog.logf("Found time text language %s, %s, %s", next.getLanguageCode(), next.getType(), next.getSubType());
            TimedTextSubtype timedTextSubtypeFrom = TimedTextSubtype.INSTANCE.from(next.getSubType().name());
            arrayList.add(new TimedTextStreamData(next.getDisplayName(), next.getLanguageCode(), TimedTextType.INSTANCE.from(next.getType().name()), timedTextSubtypeFrom, new TimedTextStreamMatcher(next.getLanguageCode(), timedTextSubtypeFrom), next.getDisplayName()));
        }
        LinkedHashSet<SubtitleLanguage> availableSubtitles = this.mSubtitlePresenter.getAvailableSubtitles();
        if (arrayList.isEmpty() && availableSubtitles != null) {
            for (SubtitleLanguage subtitleLanguage : availableSubtitles) {
                DLog.logf("Found time text language %s, %s, %s", subtitleLanguage.getLanguageCode(), subtitleLanguage.getSubtitleType(), subtitleLanguage.getSubtitleSubtype());
                TimedTextSubtype timedTextSubtypeFrom2 = TimedTextSubtype.INSTANCE.from(subtitleLanguage.getSubtitleSubtype().name());
                arrayList.add(new TimedTextStreamData(subtitleLanguage.getDisplayName(), subtitleLanguage.getLanguageCode(), TimedTextType.INSTANCE.from(subtitleLanguage.getSubtitleType().name()), timedTextSubtypeFrom2, new TimedTextStreamMatcher(subtitleLanguage.getLanguageCode(), timedTextSubtypeFrom2), subtitleLanguage.getDisplayName()));
            }
        }
        return ImmutableList.copyOf((Collection) arrayList);
    }

    public boolean isShowingSubtitles() {
        return this.mSubtitleConfig.getSubtitlePreferences().areSubtitlesEnabled();
    }

    @Nullable
    public SubtitleLanguage getCurrentSubtitleLanguage() {
        SubtitleRenderer subtitleRenderer = this.mSubtitleRenderer;
        if (subtitleRenderer != null) {
            return subtitleRenderer.getCurrentSubtitleLanguage();
        }
        return null;
    }

    public void showSubtitles(boolean z) {
        if (this.mSubtitlePresenter == null) {
            DLog.warnf("Cannot show subtitles because SubtitlePresenter has not yet been instantiated");
            return;
        }
        SubtitlePreferences subtitlePreferences = this.mSubtitleConfig.getSubtitlePreferences();
        subtitlePreferences.setSubtitlesEnabled(z);
        this.mSubtitleConfig.setSubtitlePreferences(subtitlePreferences);
        this.mSubtitlePresenter.updateUserPreferences(subtitlePreferences, Optional.of(SubtitleChangeCause.SYSTEM_VALUE_CHANGE));
        startUpdaterIfNecessary(subtitlePreferences);
    }

    public void setTimedTextConfig(@Nonnull TimedTextConfig timedTextConfig) {
        if (this.mSubtitlePresenter == null) {
            DLog.warnf("Cannot set timed text config because SubtitlePresenter has not yet been instantiated");
        } else {
            this.mTimedTextConfig = (TimedTextConfig) Preconditions.checkNotNull(timedTextConfig, "timedTextConfig");
            this.mSubtitlePresenter.setAvailableTimedTexPreferences(timedTextConfig);
        }
    }

    protected void onPlaybackStarting() {
        if (this.mIsFeatureActive) {
            ContentFetcherPluginExecutor contentFetcherPluginExecutorNewPluginExecutor = new ContentFetcherPluginExecutorFactory().newPluginExecutor(this.mContentFetcherPluginContent, ImmutableSet.of(this.mSubtitlePlugin));
            contentFetcherPluginExecutorNewPluginExecutor.addPluginListener(new ContentFetcherPluginExecutorListener() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackSubtitleFeature.2
                @Override // com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorListener
                public void onDownloadStart(@Nonnull ContentFetcherPlugin contentFetcherPlugin) {
                    DLog.devf("onDownloadStart plugin: %s", contentFetcherPlugin);
                }

                @Override // com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorListener
                public void onDownloadComplete(@Nonnull ContentFetcherPlugin contentFetcherPlugin, @Nonnull TimeSpan timeSpan) {
                    DLog.devf("onDownloadComplete plugin: %s timespan: %s ", contentFetcherPlugin, timeSpan);
                    PlaybackSubtitleFeature.this.mSubtitleDownloader.downloadFinished();
                }
            });
            contentFetcherPluginExecutorNewPluginExecutor.start();
            try {
                this.mAudioTrackMetadataList = this.mPlaybackExperienceController.getAudioTrackMetadataList();
                this.mAudioTrackId = getAudioTrackId();
                this.mSpokenLanguage = getCurrentAudioLanguage();
            } catch (IllegalPlayerStateException e) {
                this.mAudioTrackMetadataList = ImmutableList.of();
                Throwables2.propagateIfWeakMode(e);
            }
            this.mSubtitleTextController.initialize(null);
            SubtitleRenderer subtitleRendererCreateSubtitleRenderer = SubtitleRendererFactory.createSubtitleRenderer(this.mVideoPresentation.getPlayer(), this.mSubtitleTextController, this.mPlaybackExperienceController, this.mSpokenLanguage);
            this.mSubtitleRenderer = subtitleRendererCreateSubtitleRenderer;
            SpokenLanguageChangeListener spokenLanguageChangeListener = new SpokenLanguageChangeListener(subtitleRendererCreateSubtitleRenderer);
            this.mSpokenLanguageChangeListener = spokenLanguageChangeListener;
            this.mPlaybackEventDispatch.addAudioTrackChangeListener(spokenLanguageChangeListener);
            if (this.mSubtitlePresenter != null) {
                DLog.logf("SubtitlePresenter has been properly instantiated. Proceeding with initializing SubtitlePresenter");
                this.mSubtitlePresenter.initialize(new SubtitleRenderPresetsAivFactory(this.mContext).create(SubtitlePresets.DEFAULT_PRESETS), SubtitleRenderPresetsDeviceRetriever.DEFAULT_RETRIEVER.getDefaultPresets(), this.mSubtitleRenderer, this.mSubtitleEventReporter);
            }
            this.mSubtitleDownloader.startDownload();
            SubtitlePreferences subtitlePreferences = this.mSubtitleConfig.getSubtitlePreferences();
            if (this.mPlaybackExperienceController instanceof SyePlaybackExperienceController) {
                subtitlePreferences.setSubtitlesSettingsEnabled(false);
            } else {
                subtitlePreferences.setSubtitlesSettingsEnabled(true);
            }
            this.mSubtitleConfig.setSubtitlePreferences(subtitlePreferences);
        }
    }

    protected void onPlaybackResume() {
        String audioTrackId = getAudioTrackId();
        if (Objects.equal(audioTrackId, this.mAudioTrackId)) {
            return;
        }
        this.mAudioTrackId = audioTrackId;
        setAvailableFilteredByTrackGroupId(audioTrackId, this.mSupportedSubtitleLanguages, this.mSupportedNarrativeLanguages);
    }

    @Nonnull
    private String getAudioTrackId() {
        return this.mAudioStreamGroup.getCurrentAudioStream().getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nonnull
    public String getCurrentAudioLanguage() {
        return this.mAudioStreamGroup.getCurrentAudioStream().getLanguage();
    }

    static String getMatchingTrackGroupId(@Nonnull ImmutableList<AudioTrackMetadata> immutableList, @Nullable String str) throws IllegalPlayerStateException {
        Preconditions.checkNotNull(immutableList, "audioTrackMetadataList");
        if (immutableList.isEmpty()) {
            DLog.warnf("SubtitleFeature: MLFv2 metadata list is empty");
            return null;
        }
        if (str == null) {
            DLog.warnf("SubtitleFeature: Null audioTrackId from catalog metadata");
            return null;
        }
        int i = 0;
        UnmodifiableIterator<AudioTrackMetadata> it = immutableList.iterator();
        while (it.hasNext()) {
            AudioTrackMetadata next = it.next();
            if (next.getAudioTrackId().isEmpty()) {
                i++;
            }
            if (Objects.equal(next.getAudioTrackId(), str)) {
                return next.getTrackGroupId();
            }
        }
        if (i == immutableList.size() && !str.isEmpty()) {
            DLog.warnf("SubtitleFeature: AudioTrackId is empty in ALL MLFv2 metadata, but non-empty in catalog metadata");
            return null;
        }
        if (i < immutableList.size() && !str.isEmpty()) {
            throw new IllegalPlayerStateException("MLFv2 metadata list has missing audioTrackId in some tracks");
        }
        throw new IllegalPlayerStateException("No matching audioTrackId from catalog metadata is found in MLFv2 metadata");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAvailableFilteredByTrackGroupId(String str, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet2) {
        ImmutableSet<SubtitleLanguage> immutableSetOf;
        ImmutableSet<SubtitleLanguage> languageSetFilteredByTrackGroupId;
        if (this.mSubtitlePresenter == null) {
            DLog.warnf("Cannot set available subtitle languages filtered by trackId because SubtitlePresenter has not yet been instantiated");
            return;
        }
        Preconditions.checkNotNull(immutableSet, "supportedSubtitleLanguages");
        Preconditions.checkNotNull(immutableSet2, "supportedNarrativeLanguages");
        try {
            String matchingTrackGroupId = getMatchingTrackGroupId(this.mAudioTrackMetadataList, str);
            immutableSetOf = getLanguageSetFilteredByTrackGroupId(immutableSet, matchingTrackGroupId, this.mSubtitleConfig.isSubtitleLanguageFilteringEnabled());
            languageSetFilteredByTrackGroupId = getLanguageSetFilteredByTrackGroupId(immutableSet2, matchingTrackGroupId, this.mSubtitleConfig.isNarrativeLanguageFilteringEnabled());
        } catch (IllegalPlayerStateException e) {
            immutableSetOf = this.mSubtitleConfig.isSubtitleLanguageFilteringEnabled() ? ImmutableSet.of() : immutableSet;
            ImmutableSet<SubtitleLanguage> immutableSetOf2 = ImmutableSet.of();
            DLog.exceptionf(e, "Can't find trackGroupId for given audioTrackId", new Object[0]);
            languageSetFilteredByTrackGroupId = immutableSetOf2;
        }
        this.mSubtitlePresenter.setAvailableLanguages(immutableSetOf, languageSetFilteredByTrackGroupId);
        SubtitleLanguage currentSubtitleLanguage = getCurrentSubtitleLanguage();
        if (currentSubtitleLanguage == null) {
            currentSubtitleLanguage = getUserPreferredLanguage(immutableSet, this.mTimedTextStreamPreferencesProvider.get());
        }
        SubtitlePreferences subtitlePreferences = this.mSubtitleConfig.getSubtitlePreferences();
        if (currentSubtitleLanguage != null) {
            subtitlePreferences.setLanguageCode(currentSubtitleLanguage.getLanguageCode());
            subtitlePreferences.setSubtitleType(currentSubtitleLanguage.getSubtitleType());
        }
        this.mSubtitlePresenter.updateUserPreferences(subtitlePreferences, Optional.of(SubtitleChangeCause.PLAYBACK_START));
        if (areForcedNarrativesAvailable() || (areSubtitlesAvailable() && subtitlePreferences.areSubtitlesEnabled())) {
            this.mSubtitleUpdater.start();
        } else {
            this.mSubtitleUpdater.stop();
        }
    }

    @Nullable
    static SubtitleLanguage getUserPreferredLanguage(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nullable List<TimedTextStreamMatcher> list) {
        SubtitleLanguage subtitleLanguage = null;
        if (list != null && !list.isEmpty()) {
            UnmodifiableIterator<SubtitleLanguage> it = immutableSet.iterator();
            while (it.hasNext()) {
                SubtitleLanguage next = it.next();
                for (TimedTextStreamMatcher timedTextStreamMatcher : list) {
                    if (timedTextStreamMatcher.getLanguage().equalsIgnoreCase(next.getLanguageCode())) {
                        if (timedTextStreamMatcher.getSubType().toString().equalsIgnoreCase(next.getSubtitleSubtype().toString())) {
                            return next;
                        }
                        if (subtitleLanguage == null) {
                            subtitleLanguage = next;
                        }
                    }
                }
            }
        }
        return subtitleLanguage;
    }

    static ImmutableSet<SubtitleLanguage> getLanguageSetFilteredByTrackGroupId(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nullable String str, boolean z) {
        if (!z) {
            return immutableSet;
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<SubtitleLanguage> it = immutableSet.iterator();
        while (it.hasNext()) {
            SubtitleLanguage next = it.next();
            if (Strings.isNullOrEmpty(str) || Objects.equal(next.getTrackGroupId(), str)) {
                builder.add(next);
            }
        }
        return builder.build();
    }

    private void startUpdaterIfNecessary(SubtitlePreferences subtitlePreferences) {
        if (areForcedNarrativesAvailable() || (areSubtitlesAvailable() && subtitlePreferences.areSubtitlesEnabled())) {
            this.mSubtitleUpdater.start();
        } else {
            this.mSubtitleUpdater.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean areSubtitlesAvailable() {
        ImmutableSet<SubtitleLanguage> immutableSet = this.mSupportedSubtitleLanguages;
        return (immutableSet == null || immutableSet.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean areForcedNarrativesAvailable() {
        ImmutableSet<SubtitleLanguage> immutableSet = this.mSupportedNarrativeLanguages;
        return (immutableSet == null || immutableSet.isEmpty()) ? false : true;
    }

    @Nonnull
    private ImmutableSet<SubtitleLanguage> getSupportedSubtitleLanguagesFromContext() {
        return this.mSubtitlesLanguageHelper.transformToSubtitleLanguage(this.mSubtitlesLanguageHelper.getAvailableSubtitles().orNull());
    }

    public void reset() {
        this.mSubtitlesLanguageHelper.reset();
        if (this.mIsFeatureActive) {
            SubtitlePreferences subtitlePreferences = this.mSubtitleConfig.getSubtitlePreferences();
            if (!subtitlePreferences.areSubtitlesEnabled()) {
                Profiler.incrementCounter("Subtitle-Disabled");
            } else {
                Profiler.incrementCounter(subtitlePreferences.getPresetSource() == SubtitlePresetSource.AIV_SERVICE ? "Subtitle-Enabled-Presets-AIVService" : "Subtitle-Enabled-Presets-Device");
            }
            this.mSubtitleDownloader.destroy();
            this.mPlaybackEventDispatch.removePlaybackStateEventListener(this.mPlaybackStateEventListener);
            this.mPlaybackEventDispatch.removePlaybackStateEventListener(this.mSubtitleEventReporter);
            AudioTrackChangeListener audioTrackChangeListener = this.mSpokenLanguageChangeListener;
            if (audioTrackChangeListener != null) {
                this.mPlaybackEventDispatch.removeAudioTrackChangeListener(audioTrackChangeListener);
            }
            if (this.mSubtitlePresenter != null) {
                DLog.logf("Clearing SubtitlePresenter");
                this.mSubtitlePresenter.clear();
            }
            this.mSubtitleUpdater.stop();
            this.mSubtitlePlugin.clear();
            this.mIsFeatureActive = false;
            this.mHasPlaybackStarted = false;
            this.mSpokenLanguage = null;
            this.mSubtitleEventReporter = null;
        }
    }

    public void destroy() {
        if (this.mIsFeatureDestroyed.getAndSet(true)) {
            return;
        }
        reset();
        LoopRunner loopRunner = this.mSubtitleUpdater;
        if (loopRunner != null) {
            loopRunner.stop();
            this.mSubtitleUpdater = null;
        }
        this.mSubtitlePresenter = null;
        this.mContext = null;
    }

    protected boolean hasPlaybackStarted() {
        return this.mHasPlaybackStarted;
    }

    public void notifyStart() {
        synchronized (this.mMutex) {
            if (this.mIsFeatureDestroyed.get()) {
                return;
            }
            if (this.mHasPlaybackStarted) {
                return;
            }
            this.mHasPlaybackStarted = true;
            onPlaybackStarting();
            boolean zAreSubtitlesEnabled = this.mSubtitleConfig.getSubtitlePreferences().areSubtitlesEnabled();
            if (areForcedNarrativesAvailable() || (zAreSubtitlesEnabled && areSubtitlesAvailable())) {
                this.mSubtitleUpdater.start();
            }
        }
    }

    private class SpokenLanguageChangeListener implements AudioTrackChangeListener {
        private final SubtitleRenderer mSubtitleRenderer;

        @Override // com.amazon.avod.playback.AudioTrackChangeListener
        public void onAudioTrackChangeStarted(@Nonnull Optional<String> optional, @Nonnull Optional<String> optional2) {
        }

        public SpokenLanguageChangeListener(@Nonnull SubtitleRenderer subtitleRenderer) {
            this.mSubtitleRenderer = (SubtitleRenderer) Preconditions.checkNotNull(subtitleRenderer, "subtitleRenderer");
        }

        @Override // com.amazon.avod.playback.AudioTrackChangeListener
        public void onAudioTrackChangeCompleted(@Nonnull AudioTrackChangeListener.Status status) {
            PlaybackSubtitleFeature playbackSubtitleFeature = PlaybackSubtitleFeature.this;
            playbackSubtitleFeature.mSpokenLanguage = playbackSubtitleFeature.getCurrentAudioLanguage();
            this.mSubtitleRenderer.updateSpokenLanguage(PlaybackSubtitleFeature.this.mSpokenLanguage);
        }
    }
}
