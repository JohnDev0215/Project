package com.amazon.music.voice;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.ViewGroup;
import com.amazon.alexa.voice.ExoAudioPlayer;
import com.amazon.alexa.voice.bluefront.vad.PCML16BluefrontSpeechSource;
import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.AudioSink;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.AudioTranscoder;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Function;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.Scheduler;
import com.amazon.alexa.voice.core.audio.AudioPipeline;
import com.amazon.alexa.voice.core.audio.AudioRecorder;
import com.amazon.alexa.voice.core.audio.PCML16AudioRecorder;
import com.amazon.alexa.voice.core.audio.PCML16VolumeSource;
import com.amazon.alexa.voice.core.audio.SimpleAudioFormat;
import com.amazon.alexa.voice.core.channel.Channel;
import com.amazon.alexa.voice.core.channel.ChannelCoordinator;
import com.amazon.alexa.voice.core.http.TLSv12HttpClient;
import com.amazon.alexa.voice.core.internal.audio.AudioUtils;
import com.amazon.alexa.voice.core.processor.superbowl.AccessTokenProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlProcessor;
import com.amazon.alexa.voice.core.responders.CompositeResponder;
import com.amazon.alexa.voice.core.schedulers.ThreadPoolScheduler;
import com.amazon.alexa.voice.superbowl.Voice;
import com.amazon.alexa.voice.superbowl.context.SpeakerVolumeContextResolver;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin;
import com.amazon.alexa.voice.superbowl.plugins.SystemPlugin;
import com.amazon.alexa.voice.superbowl.plugins.SystemRepository;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.voice.MusicAudioPlayerPlugin;
import com.amazon.music.voice.audioactivitytracker.AudioActivityTrackerContextResolver;
import com.amazon.music.voice.capability.CapabilityHandler;
import com.amazon.music.voice.emp.EMPPlaybackPlugin;
import com.amazon.music.voice.emp.directives.EMPPlaybackPlayDirective;
import com.amazon.music.voice.emp.events.EMPEventReporterPlugin;
import com.amazon.music.voice.emp.playbackstatecontext.EMPPlaybackStateContextResolver;
import com.amazon.music.voice.launcher.AlexaLaunchTargetDirective;
import com.amazon.music.voice.launcher.AlexaLauncherPlugin;
import com.amazon.music.voice.metrics.AlexaMetricsProvider;
import com.amazon.music.voice.metrics.SystemTimeProvider;
import com.amazon.music.voice.playbackstate.PlaybackStateContextResolver;
import com.amazon.music.voice.playbackstate.PlaybackStateReporterPlugin;
import com.amazon.music.voice.playbackstate.PlayerStateProvider;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackAdjustSeekPositionDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackDisableRepeatDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackDisableShuffleDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackEnableRepeatDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackEnableRepeatOneDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackEnableShuffleDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackFastForwardDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackFavoriteDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackNextDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackPauseDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackPlayDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackPreviousDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackRewindDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackSetSeekPositionDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackStartOverDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackStopDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackUnFavoriteDirective;
import com.amazon.music.voice.text.TextPlugin;
import com.amazon.music.voice.ui.AlexaHintsViewModelProvider;
import com.amazon.music.voice.ui.AlexaUIStyleProvider;
import com.amazon.music.voice.ui.AlexaUiListener;
import com.amazon.music.voice.ui.scrim.ScrimProvider;
import com.amazon.music.voice.util.AlexaLocaleUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.time.DurationKt;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class VoiceManager {
    private static final long NEARLY_FINISHED_BEGINNING_THRESHOLD;
    private static final long NEARLY_FINISHED_INTERVAL;
    private AlexaAccessProvider alexaAccessProvider;
    private AlexaEarconToneController alexaEarconToneController;
    private AlexaErrorHandler alexaErrorHandler;
    private AlexaLauncherPlugin alexaLauncherPlugin;
    private AlexaMetricsProvider alexaMetricsProvider;
    private AlexaUiController alexaUiController;
    private Collection<AppSwipeOutListener> appSwipeOutListeners;
    private AudioPlayerDelegate audioPlayerDelegate;
    private MusicAudioPlayerPlugin audioPlayerPlugin;
    private AudioPlayerProxy audioPlayerProxy;
    private AudioPlayerQueueListener audioPlayerQueueListener;
    private CapabilityHandler capabilityHandler;
    private Channel contentChannel;
    private final Context context;
    private Channel dialogChannel;
    private EMPEventReporterPlugin empEventReporterPlugin;
    private EMPPlaybackPlugin empPlaybackPlugin;
    private PlaybackEventProcessor eventProcessor;
    private ExoAudioPlayer exoAudioPlayer;
    private GUIPlaybackPlugin guiPlaybackPlugin;
    private boolean initRequired;
    private final AlexaHintsViewModelProvider mAlexaHintsViewModelProvider;
    private SystemRepository mSystemRepository;
    private boolean nonCriticalLoggingEnabled;
    private PlaybackStateReporterPlugin playbackStateReporterPlugin;
    private RecognizeSpeechController recognizeSpeechController;
    private ScrimProvider scrimProvider;
    private SpeechRecognizerPlugin speechRecognizerPlugin;
    private SpeechSynthesizerPlugin speechSynthesizerPlugin;
    private SystemPlugin systemPlugin;
    private TextPlugin textPlugin;
    private Voice voice;
    private WhistleHandler whistleHandler;
    private static final Logger LOG = LoggerFactory.getLogger(VoiceManager.class.getSimpleName());
    private static final long NEARLY_FINISHED_END_THRESHOLD = TimeUnit.SECONDS.toMillis(1);

    static {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        NEARLY_FINISHED_INTERVAL = timeUnit.toMillis(100L);
        NEARLY_FINISHED_BEGINNING_THRESHOLD = timeUnit.toMillis(200L);
    }

    private VoiceManager(Builder builder) {
        this.dialogChannel = new Channel(0, 2);
        this.contentChannel = new Channel(1, 1);
        Context context = builder.context;
        this.context = context;
        this.alexaAccessProvider = builder.alexaAccessProvider;
        boolean z = builder.nonCriticalLoggingEnabled;
        this.nonCriticalLoggingEnabled = z;
        setNonCriticalLoggingEnabled(z);
        this.exoAudioPlayer = new ExoAudioPlayer(context);
        new ChannelCoordinator().attach(this.dialogChannel, this.contentChannel);
        this.appSwipeOutListeners = builder.appSwipeOutListeners;
        this.eventProcessor = new PlaybackEventProcessor();
        this.scrimProvider = new ScrimProvider();
        this.speechRecognizerPlugin = buildSpeechRecognizerPlugin();
        this.speechSynthesizerPlugin = buildSpeechSynthesizerPlugin();
        AudioPlayerDelegate audioPlayerDelegate = new AudioPlayerDelegate(this.eventProcessor);
        this.audioPlayerDelegate = audioPlayerDelegate;
        audioPlayerDelegate.setAlexaTrackReceiver(builder.trackReceiver);
        this.audioPlayerDelegate.setAlexaPlaystateProvider(builder.playstateProvider);
        this.audioPlayerDelegate.setVolumeController(builder.volumeController);
        this.audioPlayerDelegate.setAlexaPlaybackController(builder.alexaPlaybackController);
        this.audioPlayerProxy = new AudioPlayerProxy(this.audioPlayerDelegate, this.exoAudioPlayer);
        this.systemPlugin = buildSystemPlugin(builder.endpoint, builder.locale);
        AudioPlayerQueueListener audioPlayerQueueListener = new AudioPlayerQueueListener();
        this.audioPlayerQueueListener = audioPlayerQueueListener;
        audioPlayerQueueListener.setAlexaTrackReceiver(builder.trackReceiver);
        MusicAudioPlayerPlugin musicAudioPlayerPluginBuild = new MusicAudioPlayerPlugin.Builder(context).audioPlayer(this.audioPlayerProxy).configureNearlyFinishedInterval(NEARLY_FINISHED_BEGINNING_THRESHOLD, NEARLY_FINISHED_END_THRESHOLD, NEARLY_FINISHED_INTERVAL).channel(this.contentChannel).build();
        this.audioPlayerPlugin = musicAudioPlayerPluginBuild;
        musicAudioPlayerPluginBuild.addAudioPlayerListener(this.audioPlayerQueueListener);
        this.guiPlaybackPlugin = new GUIPlaybackPlugin(builder.guiPlaybackController);
        this.empPlaybackPlugin = new EMPPlaybackPlugin(builder.empPlaybackController);
        this.alexaLauncherPlugin = new AlexaLauncherPlugin(context, builder.contextAwareDeeplinkDelegate);
        this.textPlugin = new TextPlugin();
        this.recognizeSpeechController = new RecognizeSpeechController(this.dialogChannel, this.speechRecognizerPlugin);
        this.alexaEarconToneController = new AlexaEarconToneController(context);
        this.alexaUiController = new AlexaUiController(context, this.scrimProvider, this.speechSynthesizerPlugin, this.speechRecognizerPlugin, this.recognizeSpeechController, this.audioPlayerPlugin, this.textPlugin, this.exoAudioPlayer, this.alexaEarconToneController.getEarconSpeechRecordingListener());
        AlexaErrorHandler alexaErrorHandler = new AlexaErrorHandler(context, this.alexaUiController);
        this.alexaErrorHandler = alexaErrorHandler;
        alexaErrorHandler.setAlexaAccessProvider(this.alexaAccessProvider);
        addAlexaUiListener(this.alexaErrorHandler);
        this.speechRecognizerPlugin.addListener(new AlexaSpeechRecognizerListener(this.alexaUiController, this.alexaErrorHandler));
        this.speechSynthesizerPlugin.addListener(new AlexaSpeechSynthesizerListener(this.alexaUiController, this.alexaErrorHandler));
        this.alexaUiController.setAlexaAccessProvider(builder.alexaAccessProvider);
        this.alexaUiController.setAlexaUIStyleProvider(builder.alexaUIStyleProvider);
        this.alexaUiController.setAlexaHintsViewModelProvider(builder.alexaHintsViewModelProvider);
        this.mAlexaHintsViewModelProvider = builder.alexaHintsViewModelProvider;
        NoOpDownchannel noOpDownchannel = new NoOpDownchannel();
        PlaybackStateReporterPlugin playbackStateReporterPlugin = new PlaybackStateReporterPlugin(builder.playerStateProvider);
        this.playbackStateReporterPlugin = playbackStateReporterPlugin;
        this.audioPlayerPlugin.addAudioPlayerListener(playbackStateReporterPlugin.getAlexaPlayerListener());
        PlaybackStateContextResolver playbackStateContextResolver = new PlaybackStateContextResolver(builder.context, builder.playerStateProvider, builder.isAlexaEmpPlaybackEnabled);
        EMPPlaybackStateContextResolver eMPPlaybackStateContextResolver = new EMPPlaybackStateContextResolver(getPlayerVersion(context), builder.playerStateProvider, this.empPlaybackPlugin.getEMPSkillTokenObservable());
        this.empEventReporterPlugin = new EMPEventReporterPlugin(builder.playerStateProvider, builder.guiPlaybackController, this.empPlaybackPlugin.getEMPSkillTokenObservable(), this.empPlaybackPlugin.getAomEmpPlaybackSessionIdObservable(), playbackStateContextResolver, eMPPlaybackStateContextResolver);
        AudioActivityTrackerContextResolver audioActivityTrackerContextResolver = new AudioActivityTrackerContextResolver(builder.playerStateProvider, builder.systemTimeProvider);
        SuperbowlProcessor.Builder builderHttpClient = new SuperbowlProcessor.Builder().endpoint(builder.endpoint).accessToken(builder.accessTokenProvider).httpClient(TLSv12HttpClient.create(10, 20, 10));
        enableGUIPlaybackDirectives(builderHttpClient);
        enableEMPPlaybackDirectives(builderHttpClient);
        SuperbowlProcessor superbowlProcessorBuild = builderHttpClient.build();
        this.capabilityHandler = new CapabilityHandler(builder.accessTokenProvider, this.alexaAccessProvider);
        Voice.Builder builderAddPlugins = new Voice.Builder().processor(superbowlProcessorBuild).downchannel(noOpDownchannel).eventScheduler(getEventScheduler()).addContextResolver(new SpeakerVolumeContextResolver(context)).addContextResolver(playbackStateContextResolver).addContextResolver(audioActivityTrackerContextResolver).sequencingFilter(new DirectiveFilter(this.alexaAccessProvider)).addPlugins(this.speechRecognizerPlugin, this.speechSynthesizerPlugin, this.audioPlayerPlugin, this.systemPlugin, this.playbackStateReporterPlugin, this.guiPlaybackPlugin, this.textPlugin);
        if (builder.isAlexaEmpPlaybackEnabled) {
            builderAddPlugins.addContextResolver(eMPPlaybackStateContextResolver).addPlugins(this.empPlaybackPlugin, this.empEventReporterPlugin);
        }
        if (this.alexaAccessProvider.isHyperloopFeatureSupported()) {
            builderAddPlugins.addPlugin(this.alexaLauncherPlugin);
        }
        Voice voiceBuild = builderAddPlugins.build();
        this.voice = voiceBuild;
        voiceBuild.addDirectiveProcessingListener(new AlexaDirectiveProcessingListener(this.alexaUiController, this.alexaErrorHandler, this.audioPlayerQueueListener, this.speechRecognizerPlugin));
        this.whistleHandler = new WhistleHandler(this.voice, this.alexaErrorHandler);
        AlexaSpeechMessagesPlayerHandler alexaSpeechMessagesPlayerHandler = new AlexaSpeechMessagesPlayerHandler(this.voice, this.alexaUiController, this.audioPlayerProxy, this.speechRecognizerPlugin);
        addAlexaSpeechMessagesPlayerCallback(alexaSpeechMessagesPlayerHandler.getSimpleCallback());
        addAlexaUiListener(alexaSpeechMessagesPlayerHandler.getAlexaUiListener());
        updateAlexaLocale(builder.locale);
        AlexaMetricsProvider alexaMetricsProvider = new AlexaMetricsProvider(builder.systemTimeProvider, builder.empPlaybackController.getEmpPlaybackStateObservable());
        this.alexaMetricsProvider = alexaMetricsProvider;
        this.speechRecognizerPlugin.addListener(alexaMetricsProvider.getRecognizeSpeechListener());
        this.speechSynthesizerPlugin.addListener(this.alexaMetricsProvider.getSynthesizeSpeechListener());
        this.speechSynthesizerPlugin.addListener(audioActivityTrackerContextResolver.getSynthesizeSpeechListener());
        this.audioPlayerPlugin.addAudioPlayerListener(this.alexaMetricsProvider.getAudioPlayerListener());
        this.audioPlayerPlugin.addAudioPlayerListener(audioActivityTrackerContextResolver.getAudioPlayerListener());
        attachWhistleHandler();
        attachEarconController();
    }

    public void updateAlexaLocale(Locale locale) {
        Locale savedLocale = AlexaLocaleUtil.getSavedLocale(this.context);
        if (savedLocale == null || savedLocale.getLanguage() == null || savedLocale.getCountry() == null || !savedLocale.getLanguage().equals(locale.getLanguage()) || !savedLocale.getCountry().equals(locale.getCountry())) {
            LOG.info("Sending an UpdateSettings event to AVS");
            this.mSystemRepository.setLocale(locale);
            this.systemPlugin.updateSettings();
            AlexaLocaleUtil.saveLocale(this.context, locale);
            this.mAlexaHintsViewModelProvider.getAlexaHintsViewModel(this.context).clearHints();
        }
    }

    protected static class DirectiveFilter implements CompositeResponder.Filter {
        private AlexaAccessProvider alexaAccessProvider;

        public DirectiveFilter(AlexaAccessProvider alexaAccessProvider) {
            this.alexaAccessProvider = alexaAccessProvider;
        }

        @Override // com.amazon.alexa.voice.core.responders.CompositeResponder.Filter
        public boolean isSupported(Directive directive) {
            String namespace = ((SuperbowlDirective) directive).getNamespace();
            boolean z = "SpeechSynthesizer".equals(namespace) || "SpeechRecognizer".equals(namespace) || "Alexa.PlaybackController".equals(namespace) || "Alexa.PlaylistController".equals(namespace) || "Alexa.SeekController".equals(namespace) || "Alexa.FavoritesController".equals(namespace) || "AudioPlayer".equals(namespace) || "ExternalMediaPlayer".equals(namespace);
            if (this.alexaAccessProvider.isHyperloopFeatureSupported()) {
                return z || "Alexa.Launcher".equals(namespace);
            }
            return z;
        }
    }

    private void enableGUIPlaybackDirectives(SuperbowlProcessor.Builder builder) {
        builder.addFactory("Alexa.PlaybackController", "Play", new GUIPlaybackPlayDirective.Factory()).addFactory("Alexa.PlaybackController", "Pause", new GUIPlaybackPauseDirective.Factory()).addFactory("Alexa.PlaybackController", "Stop", new GUIPlaybackStopDirective.Factory()).addFactory("Alexa.PlaybackController", "Next", new GUIPlaybackNextDirective.Factory()).addFactory("Alexa.PlaybackController", "Previous", new GUIPlaybackPreviousDirective.Factory()).addFactory("Alexa.SeekController", "AdjustSeekPosition", new GUIPlaybackAdjustSeekPositionDirective.Factory()).addFactory("Alexa.PlaylistController", "DisableRepeat", new GUIPlaybackDisableRepeatDirective.Factory()).addFactory("Alexa.PlaylistController", "DisableShuffle", new GUIPlaybackDisableShuffleDirective.Factory()).addFactory("Alexa.PlaylistController", "EnableRepeat", new GUIPlaybackEnableRepeatDirective.Factory()).addFactory("Alexa.PlaylistController", "EnableRepeatOne", new GUIPlaybackEnableRepeatOneDirective.Factory()).addFactory("Alexa.PlaylistController", "EnableShuffle", new GUIPlaybackEnableShuffleDirective.Factory()).addFactory("Alexa.PlaybackController", "StartOver", new GUIPlaybackStartOverDirective.Factory()).addFactory("Alexa.PlaybackController", "Rewind", new GUIPlaybackRewindDirective.Factory()).addFactory("Alexa.PlaybackController", "FastForward", new GUIPlaybackFastForwardDirective.Factory()).addFactory("Alexa.SeekController", "SetSeekPosition", new GUIPlaybackSetSeekPositionDirective.Factory()).addFactory("Alexa.FavoritesController", "Favorite", new GUIPlaybackFavoriteDirective.Factory()).addFactory("Alexa.FavoritesController", "Unfavorite", new GUIPlaybackUnFavoriteDirective.Factory());
        if (this.alexaAccessProvider.isHyperloopFeatureSupported()) {
            builder.addFactory("Alexa.Launcher", "LaunchTarget", new AlexaLaunchTargetDirective.Factory());
        }
    }

    private void enableEMPPlaybackDirectives(SuperbowlProcessor.Builder builder) {
        builder.addFactory("ExternalMediaPlayer", "Play", new EMPPlaybackPlayDirective.Factory());
    }

    public void attachView(Activity activity, ViewGroup viewGroup) {
        this.alexaUiController.attach(activity, viewGroup, this.voice);
    }

    public void setPodcastPlaybackControllerAndMediaSessionStateProvider(GUIPlaybackController gUIPlaybackController, MediaSessionStateProvider mediaSessionStateProvider) {
        this.guiPlaybackPlugin.setPodcastPlaybackController(gUIPlaybackController);
        this.guiPlaybackPlugin.setPodcastMediaSessionStateProvider(mediaSessionStateProvider);
        this.empEventReporterPlugin.subscribeToEventsOfPodCastPlaybackController(gUIPlaybackController);
    }

    private void attachEarconController() {
        this.speechRecognizerPlugin.addListener(this.alexaEarconToneController.getSimpleRecognizeSpeechListener());
        this.guiPlaybackPlugin.addUnsupportedActionHandler(this.alexaEarconToneController.getSimpleUnsupportedActionHandler());
        this.recognizeSpeechController.addEarconSpeechRecordingListener(this.alexaEarconToneController.getEarconSpeechRecordingListener());
        this.empPlaybackPlugin.addUnsupportedActionHandler(this.alexaEarconToneController.getSimpleUnsupportedActionHandler());
        addAlexaUiListener(this.alexaEarconToneController.getSimpleAlexaUiListener());
    }

    private String getPlayerVersion(Context context) {
        context.getApplicationContext();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName + "." + (packageInfo.versionCode % DurationKt.NANOS_IN_MILLIS);
        } catch (PackageManager.NameNotFoundException unused) {
            LOG.debug("Unable to get player version from ApplicationContext");
            return null;
        }
    }

    public void handleReopenMusicApp(Context context) {
        LOG.debug("handleReopenMusicApp");
        Iterator<AppSwipeOutListener> it = this.appSwipeOutListeners.iterator();
        while (it.hasNext()) {
            it.next().onAppRestart(context);
        }
        PlaybackStateReporterPlugin playbackStateReporterPlugin = this.playbackStateReporterPlugin;
        if (playbackStateReporterPlugin != null) {
            this.voice.addPlugin(playbackStateReporterPlugin);
        }
        this.initRequired = false;
    }

    private void attachWhistleHandler() {
        this.speechRecognizerPlugin.addListener(this.whistleHandler.getRecognizeSpeechListener());
        this.voice.addDirectiveProcessingListener(this.whistleHandler.getDirectiveProcessingListener());
        addAlexaUiListener(this.whistleHandler.getAlexaUiListener());
    }

    public Activity getActivity() {
        return this.alexaUiController.getActivity();
    }

    private Scheduler getEventScheduler() {
        return new ThreadPoolScheduler(Integer.MAX_VALUE);
    }

    private SpeechRecognizerPlugin buildSpeechRecognizerPlugin() {
        return new SpeechRecognizerPlugin.Builder(this.context).audioTranscoder(new Function<AudioSink, AudioTranscoder>() { // from class: com.amazon.music.voice.VoiceManager.1
            @Override // com.amazon.alexa.voice.core.Function
            public AudioTranscoder apply(AudioSink audioSink) throws Exception {
                SimpleAudioFormat simpleAudioFormat = new SimpleAudioFormat("audio/L16", 16000, AudioUtils.getPCMBitRate(16000, 16, 1), 1);
                PCML16AudioRecorder pCML16AudioRecorder = new PCML16AudioRecorder(simpleAudioFormat);
                VoiceManager voiceManager = VoiceManager.this;
                return new AudioPipeline(voiceManager.attachVolumeMeasurement(voiceManager.attachSpeechDetection(pCML16AudioRecorder)), audioSink, simpleAudioFormat);
            }
        }).channel(this.dialogChannel).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioSource attachVolumeMeasurement(AudioSource audioSource) {
        return new PCML16VolumeSource.Builder(audioSource).onVolumeChanged(new PCML16VolumeSource.OnVolumeChangedListener() { // from class: com.amazon.music.voice.VoiceManager.2
            @Override // com.amazon.alexa.voice.core.audio.PCML16VolumeSource.OnVolumeChangedListener
            public void onVolumeChanged(float f) {
                VoiceManager.this.scrimProvider.soundLevel.set(f);
            }
        }).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioSource attachSpeechDetection(final AudioRecorder audioRecorder) {
        Action action = new Action() { // from class: com.amazon.music.voice.VoiceManager.3
            @Override // com.amazon.alexa.voice.core.Action
            public void call() throws Exception {
                audioRecorder.signalEndOfStream();
            }
        };
        return new PCML16BluefrontSpeechSource.Builder(audioRecorder).sampleRate(audioRecorder.getAudioFormat().getSampleRate()).audioTimeoutMillis(90000L).onSpeechEnd(action).onSpeechTimeout(action).build();
    }

    private SpeechSynthesizerPlugin buildSpeechSynthesizerPlugin() {
        return new SpeechSynthesizerPlugin.Builder(this.context).audioCacheDirectory(new File(this.context.getCacheDir(), PageUriUtils.SOURCE_QUERY_VOICE_VALUE)).channel(this.dialogChannel).build();
    }

    public void addAlexaSpeechMessagesPlayerCallback(AudioPlayer.SimpleCallback simpleCallback) {
        this.exoAudioPlayer.addCallback(simpleCallback);
    }

    public void setKeepUiVisible(boolean z) {
        this.alexaUiController.setKeepUiVisible(z);
    }

    private SystemPlugin buildSystemPlugin(String str, Locale locale) {
        MusicPersistentRepository musicPersistentRepository = new MusicPersistentRepository(this.context.getSharedPreferences("com.amazon.music.voice.VoiceManager", 0));
        this.mSystemRepository = musicPersistentRepository;
        musicPersistentRepository.setEndpoint(str);
        this.mSystemRepository.setLocale(locale);
        return new SystemPlugin(this.mSystemRepository);
    }

    public void startRecording(AlexaEntryPoint alexaEntryPoint) {
        this.alexaErrorHandler.setAlexaEntryPoint(alexaEntryPoint);
        if (this.alexaErrorHandler.hasNetworkConnectionAndShowErrorMessage()) {
            this.recognizeSpeechController.startRecognizeSpeech();
            this.alexaUiController.startListeningAnimation();
            toggleHintsVisibility(true);
        }
    }

    public boolean isAlexaListening() {
        return this.speechRecognizerPlugin.isListening();
    }

    public void prepareWithAlexaSpeechMessagesPlayer(String str) {
        this.audioPlayerProxy.prepareWithAlexaSpeechMessagesPlayer(str);
    }

    public boolean isAlexaUiVisible() {
        return this.alexaUiController.isAlexaUiVisible();
    }

    public void closeAlexaUi() {
        this.alexaUiController.closeAlexaUi();
    }

    public void softCloseAlexaUi() {
        this.alexaUiController.softCloseAlexaUi();
    }

    public void addAlexaUiListener(AlexaUiListener alexaUiListener) {
        this.alexaUiController.addAlexaUiListener(alexaUiListener);
    }

    public AlexaErrorHandler getAlexaErrorHandler() {
        return this.alexaErrorHandler;
    }

    public void removeAlexaUiListener(AlexaUiListener alexaUiListener) {
        this.alexaUiController.removeAlexaUiListener(alexaUiListener);
    }

    public void onSaveInstanceState() {
        this.alexaUiController.onSaveInstanceState();
    }

    public void toggleHintsVisibility(boolean z) {
        this.alexaUiController.toggleHintsVisibility(z);
    }

    public PlaybackEventProcessor getPlaybackEventProcessor() {
        return this.eventProcessor;
    }

    public void issueNextCommand() {
        this.audioPlayerPlugin.issueNextCommand();
    }

    public void issuePreviousCommand() {
        this.audioPlayerPlugin.issuePreviousCommand();
    }

    public void issueShuffleCommand(boolean z) {
        this.audioPlayerPlugin.issueShuffleCommand(z);
    }

    public void clearQueue() {
        this.audioPlayerPlugin.cancel();
    }

    public AlexaMetricsProvider getAlexaMetricsProvider() {
        return this.alexaMetricsProvider;
    }

    public boolean isInitRequired() {
        return this.initRequired;
    }

    public void setNonCriticalLoggingEnabled(boolean z) {
        this.nonCriticalLoggingEnabled = z;
        if (z) {
            com.amazon.alexa.voice.core.Logger.setLevel(Logger.Level.DEBUG);
            LOG.warn("Enabled Alexa debug logging.");
        } else {
            com.amazon.alexa.voice.core.Logger.setLevel(Logger.Level.BASIC);
            LOG.debug("Enabled Alexa basic logging.");
        }
    }

    public void setRefMarkerAndAssociateTag(String str, String str2) {
        this.alexaUiController.setRefMarkerAndAssociateTag(str, str2);
    }

    public static final class Builder {
        AccessTokenProvider accessTokenProvider;
        AlexaAccessProvider alexaAccessProvider;
        AlexaHintsViewModelProvider alexaHintsViewModelProvider;
        AlexaPlaybackController alexaPlaybackController;
        AlexaUIStyleProvider alexaUIStyleProvider;
        Collection<AppSwipeOutListener> appSwipeOutListeners = new ArrayList();
        Context context;
        ContextAwareDeeplinkDelegate contextAwareDeeplinkDelegate;
        EMPPlaybackController empPlaybackController;
        String endpoint;
        GUIPlaybackController guiPlaybackController;
        boolean isAlexaEmpPlaybackEnabled;
        Locale locale;
        boolean nonCriticalLoggingEnabled;
        PlayerStateProvider playerStateProvider;
        AlexaPlaystateProvider playstateProvider;
        SystemTimeProvider systemTimeProvider;
        AlexaTrackReceiver trackReceiver;
        AlexaVolumeController volumeController;

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder contextAwareDeeplinkDelegate(ContextAwareDeeplinkDelegate contextAwareDeeplinkDelegate) {
            this.contextAwareDeeplinkDelegate = contextAwareDeeplinkDelegate;
            return this;
        }

        public Builder accessTokenProvider(AccessTokenProvider accessTokenProvider) {
            this.accessTokenProvider = accessTokenProvider;
            return this;
        }

        public Builder endpoint(String str) {
            this.endpoint = str;
            return this;
        }

        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder alexaTrackReceiver(AlexaTrackReceiver alexaTrackReceiver) {
            this.trackReceiver = alexaTrackReceiver;
            return this;
        }

        public Builder alexaVolumeController(AlexaVolumeController alexaVolumeController) {
            this.volumeController = alexaVolumeController;
            return this;
        }

        public Builder addAppSwipeOutListener(AppSwipeOutListener appSwipeOutListener) {
            this.appSwipeOutListeners.add(appSwipeOutListener);
            return this;
        }

        public Builder alexaPlaystateProvider(AlexaPlaystateProvider alexaPlaystateProvider) {
            this.playstateProvider = alexaPlaystateProvider;
            return this;
        }

        public Builder enableNonCriticalLogging(boolean z) {
            this.nonCriticalLoggingEnabled = z;
            return this;
        }

        public Builder alexaPlaybackController(AlexaPlaybackController alexaPlaybackController) {
            this.alexaPlaybackController = alexaPlaybackController;
            return this;
        }

        public Builder guiPlaybackController(GUIPlaybackController gUIPlaybackController) {
            this.guiPlaybackController = gUIPlaybackController;
            return this;
        }

        public Builder empPlaybackController(EMPPlaybackController eMPPlaybackController) {
            this.empPlaybackController = eMPPlaybackController;
            return this;
        }

        public Builder alexaAccessProvider(AlexaAccessProvider alexaAccessProvider) {
            this.alexaAccessProvider = alexaAccessProvider;
            return this;
        }

        public Builder playerStateProvider(PlayerStateProvider playerStateProvider) {
            this.playerStateProvider = playerStateProvider;
            return this;
        }

        public Builder systemTimeProvider(SystemTimeProvider systemTimeProvider) {
            this.systemTimeProvider = systemTimeProvider;
            return this;
        }

        public Builder enableAlexaEmpPlayback(boolean z) {
            this.isAlexaEmpPlaybackEnabled = z;
            return this;
        }

        public Builder alexaUIStyleProvider(AlexaUIStyleProvider alexaUIStyleProvider) {
            this.alexaUIStyleProvider = alexaUIStyleProvider;
            return this;
        }

        public Builder alexaHintsViewModelProvider(AlexaHintsViewModelProvider alexaHintsViewModelProvider) {
            this.alexaHintsViewModelProvider = alexaHintsViewModelProvider;
            return this;
        }

        public VoiceManager build() {
            Validate.notNull(this.context);
            Validate.notNull(this.contextAwareDeeplinkDelegate);
            Validate.notNull(this.accessTokenProvider);
            Validate.notNull(this.endpoint);
            Validate.notNull(this.locale);
            Validate.notNull(this.trackReceiver);
            Validate.notNull(this.volumeController);
            Validate.notNull(this.appSwipeOutListeners);
            Validate.notNull(this.playstateProvider);
            Validate.notNull(this.alexaPlaybackController);
            Validate.notNull(this.guiPlaybackController);
            Validate.notNull(this.alexaAccessProvider);
            Validate.notNull(this.systemTimeProvider);
            Validate.notNull(this.empPlaybackController);
            return new VoiceManager(this);
        }
    }

    protected void registerCapabilities() {
        this.capabilityHandler.registerCapabilities();
    }
}
