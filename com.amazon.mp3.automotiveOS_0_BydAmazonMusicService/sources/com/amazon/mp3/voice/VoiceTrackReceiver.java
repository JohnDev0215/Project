package com.amazon.mp3.voice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.amazon.mp3.auto.carmode.CarModeUserPresetHandlerImpl;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaSequencer;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaTrackProvider;
import com.amazon.mp3.library.provider.source.nowplaying.LoadCookie;
import com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.MusicPlayQueueFactory;
import com.amazon.mp3.widget.WidgetUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.PlayerPlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.providers.FeatureGateProviderImpl;
import com.amazon.music.voice.AlexaTrackReceiver;
import com.amazon.music.voice.AppSwipeOutListener;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse;
import com.amazon.musicplayqueueservice.model.NonRetriableClientException;
import com.amazon.podcast.ActionBarInfo;
import com.amazon.podcast.AdvertisingPreferencesProviderImpl;
import com.amazon.podcast.DownloadSettings;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.NetworkSettings;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastDynamicMessaging;
import com.amazon.podcast.PodcastSharingProvider;
import com.amazon.podcast.UserInfo;
import com.amazon.podcast.bootstrap.Bootstrap;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class VoiceTrackReceiver implements AlexaTrackReceiver, AppSwipeOutListener {
    private static final String TAG = "VoiceTrackReceiver";
    private boolean cancelAudioPlayback;
    private final Context mContext;
    private AlexaTrackProvider mCurrentTrackProvider;
    private String mCurrentTrackUrl;
    private final PlaybackController mPlaybackController;
    private AlexaTrackProvider mPrefetchedTrackProvider;
    private String mPrefetchedUrl;
    private VoiceSpeechMessagesPlayerStreamManager mVoiceSpeechMessagesPlayerStreamManager;
    private final Action1<? super Pair<StreamInfo, GetTrackAndQueueMetadataResponse>> mOnPrefetchGetTrackAndQueueMetadata = new Action1<Pair<StreamInfo, GetTrackAndQueueMetadataResponse>>() { // from class: com.amazon.mp3.voice.VoiceTrackReceiver.1
        @Override // rx.functions.Action1
        public void call(final Pair<StreamInfo, GetTrackAndQueueMetadataResponse> requestUrlAndResponse) {
            VoiceTrackReceiver.this.handleResponse(requestUrlAndResponse, false);
        }
    };
    private final Action1<? super Pair<StreamInfo, GetTrackAndQueueMetadataResponse>> mOnGetTrackAndQueueMetadata = new Action1<Pair<StreamInfo, GetTrackAndQueueMetadataResponse>>() { // from class: com.amazon.mp3.voice.VoiceTrackReceiver.2
        @Override // rx.functions.Action1
        public void call(final Pair<StreamInfo, GetTrackAndQueueMetadataResponse> requestUrlAndResponse) {
            VoiceTrackReceiver.this.handleResponse(requestUrlAndResponse, true);
        }
    };
    private final Action1<Throwable> mOnGetTrackAndQueueMetadataPrefetchError = new Action1<Throwable>() { // from class: com.amazon.mp3.voice.VoiceTrackReceiver.3
        @Override // rx.functions.Action1
        public void call(final Throwable throwable) {
        }
    };
    private final Action1<Throwable> mOnGetTrackAndQueueMetadataError = new Action1<Throwable>() { // from class: com.amazon.mp3.voice.VoiceTrackReceiver.4
        @Override // rx.functions.Action1
        public void call(final Throwable throwable) {
            if (!(throwable instanceof NonRetriableClientException) || VoiceTrackReceiver.this.mCurrentTrackUrl == null) {
                return;
            }
            VoiceTrackReceiver.this.playStream(true);
        }
    };
    private final TrackProviderListener mTrackProviderListener = new TrackProviderListener() { // from class: com.amazon.mp3.voice.VoiceTrackReceiver.5
        @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
        public void onCollectionChanged() {
        }

        @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
        public void onCollectionNameLoaded(final String collectionName) {
        }

        @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
        public void onCollectionRefreshed(final boolean hasChanged) {
        }

        @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
        public void onCountChanged(final int newCount) {
        }

        @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
        public void onPositionChanged(final int newPosition) {
        }

        @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
        public void onCollectionLoaded(final LoadCookie cookie) {
            VoiceTrackReceiver.this.onTrackCollectionLoaded();
        }
    };

    @Override // com.amazon.music.voice.AlexaTrackReceiver
    public void clearQueue() {
    }

    public VoiceTrackReceiver(final Context context, final VoiceSpeechMessagesPlayerStreamManager voiceSpeechMessagesPlayerStreamManager) {
        Validate.notNull(context);
        this.mContext = context;
        this.mVoiceSpeechMessagesPlayerStreamManager = voiceSpeechMessagesPlayerStreamManager;
        this.mPlaybackController = PlaybackControllerProvider.getController(ControlSource.ALEXA);
    }

    @Override // com.amazon.music.voice.AlexaTrackReceiver
    public void enqueueTrack(final String url, final String token) {
        StreamInfo streamInfo = new StreamInfo(url, token);
        if (isPodcastUrl(url) || url == null || url.equals(this.mPrefetchedUrl) || this.cancelAudioPlayback || getAlexaSequencer() == null || getAlexaSequencer().isUrlInQueue(streamInfo)) {
            return;
        }
        this.mPrefetchedUrl = url;
        this.mPrefetchedTrackProvider = null;
        MusicPlayQueueFactory.getTrackAndQueueMetadataRx(this.mContext, streamInfo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.mOnPrefetchGetTrackAndQueueMetadata, this.mOnGetTrackAndQueueMetadataPrefetchError);
    }

    @Override // com.amazon.music.voice.AlexaTrackReceiver
    public void playTrack(final String url, final String token) {
        StreamInfo streamInfo = new StreamInfo(url, token);
        if (this.cancelAudioPlayback) {
            return;
        }
        VoiceManagerSingleton.getInstance().setKeepUiVisible(true);
        if (isPodcastUrl(url)) {
            Podcast.create(getPodcastBootstrap(url)).startPlayback();
            VoiceManagerSingleton.getInstance().setKeepUiVisible(false);
            return;
        }
        AlexaTrackProvider alexaTrackProvider = this.mCurrentTrackProvider;
        if (alexaTrackProvider != null) {
            alexaTrackProvider.deregisterListener(this.mTrackProviderListener);
        }
        if ((getAlexaSequencer() != null && getAlexaSequencer().isUrlInQueue(streamInfo)) || (url != null && url.equals(this.mCurrentTrackUrl) && this.mCurrentTrackProvider != null && (this.mVoiceSpeechMessagesPlayerStreamManager.getStreamURL() == null || !url.equals(this.mVoiceSpeechMessagesPlayerStreamManager.getStreamURL())))) {
            initiateTrackPlayback(streamInfo);
            return;
        }
        if (url != null && url.equals(this.mPrefetchedUrl) && this.mPrefetchedTrackProvider != null && (this.mVoiceSpeechMessagesPlayerStreamManager.getStreamURL() == null || !url.equals(this.mVoiceSpeechMessagesPlayerStreamManager.getStreamURL()))) {
            this.mCurrentTrackProvider = this.mPrefetchedTrackProvider;
            this.mCurrentTrackUrl = this.mPrefetchedUrl;
            this.mPrefetchedTrackProvider = null;
            this.mPrefetchedUrl = null;
            initiateTrackPlayback(streamInfo);
            return;
        }
        if (url != null && url.equals(this.mPrefetchedUrl)) {
            this.mPrefetchedUrl = null;
            this.mPrefetchedTrackProvider = null;
            this.mCurrentTrackUrl = null;
            this.mCurrentTrackProvider = null;
            return;
        }
        if (url != null && !url.equals(this.mCurrentTrackUrl)) {
            this.mCurrentTrackUrl = url;
            MusicPlayQueueFactory.getTrackAndQueueMetadataRx(this.mContext, streamInfo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.mOnGetTrackAndQueueMetadata, this.mOnGetTrackAndQueueMetadataError);
        } else {
            Log.warning(TAG, "URL is null when calling playTrack");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResponse(final Pair<StreamInfo, GetTrackAndQueueMetadataResponse> requestUrlAndResponse, final boolean startPlayback) {
        int i = AnonymousClass6.$SwitchMap$com$amazon$mp3$voice$MusicPlayQueueFactory$GetTrackAndQueueMetadataResponseType[MusicPlayQueueFactory.getTrackAndQueueMetadataResponseType((GetTrackAndQueueMetadataResponse) requestUrlAndResponse.second).ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            playStream(startPlayback);
            return;
        }
        if (startPlayback) {
            this.mCurrentTrackProvider = new AlexaTrackProvider(this.mContext, requestUrlAndResponse);
            this.mPrefetchedUrl = null;
            this.mPrefetchedTrackProvider = null;
            initiateTrackPlayback((StreamInfo) requestUrlAndResponse.first);
            return;
        }
        this.mPrefetchedTrackProvider = new AlexaTrackProvider(this.mContext, requestUrlAndResponse);
        if (getSequencerPlaybackProvider() != null) {
            if (getAlexaSequencer() != null) {
                getAlexaSequencer().queueNextTrack(this.mPrefetchedTrackProvider);
                return;
            } else {
                getSequencerPlaybackProvider().setSequencer(new AlexaSequencer(this.mContext, this.mCurrentTrackProvider, null), ControlSource.ALEXA);
                return;
            }
        }
        AlexaSequencer alexaSequencer = new AlexaSequencer(this.mContext, this.mCurrentTrackProvider, null);
        SequencerPlaybackProvider sequencerPlaybackProvider = setSequencerPlaybackProvider();
        if (sequencerPlaybackProvider != null) {
            sequencerPlaybackProvider.setSequencer(alexaSequencer, ControlSource.ALEXA);
        } else {
            Log.error(TAG, "Unable to set SequencerPlaybackProvider");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.voice.VoiceTrackReceiver$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$voice$MusicPlayQueueFactory$GetTrackAndQueueMetadataResponseType;

        static {
            int[] iArr = new int[MusicPlayQueueFactory.GetTrackAndQueueMetadataResponseType.values().length];
            $SwitchMap$com$amazon$mp3$voice$MusicPlayQueueFactory$GetTrackAndQueueMetadataResponseType = iArr;
            try {
                iArr[MusicPlayQueueFactory.GetTrackAndQueueMetadataResponseType.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$voice$MusicPlayQueueFactory$GetTrackAndQueueMetadataResponseType[MusicPlayQueueFactory.GetTrackAndQueueMetadataResponseType.STREAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playStream(final boolean startPlayback) {
        VoiceManagerSingleton.getInstance().setKeepUiVisible(true);
        this.mVoiceSpeechMessagesPlayerStreamManager.setStreamURL(this.mCurrentTrackUrl);
        this.mPrefetchedUrl = null;
        this.mPrefetchedTrackProvider = null;
        if (startPlayback) {
            initiateSpeechMessagesPlayback();
        }
    }

    private void initiateTrackPlayback(final StreamInfo streamInfo) {
        AlexaTrackProvider alexaTrackProvider = this.mCurrentTrackProvider;
        if (alexaTrackProvider == null) {
            Log.error(TAG, "Current track provider is null");
            return;
        }
        alexaTrackProvider.registerListener(this.mTrackProviderListener);
        PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, PlaybackPageType.EXTERNAL);
        if (getSequencerPlaybackProvider() != null) {
            AlexaSequencer alexaSequencer = getAlexaSequencer();
            if (alexaSequencer != null) {
                int indexOfTrackInQueue = alexaSequencer.getIndexOfTrackInQueue(streamInfo);
                if (indexOfTrackInQueue != -9999) {
                    alexaSequencer.onAVSResponse(indexOfTrackInQueue);
                } else {
                    alexaSequencer.queueNextTrack(this.mCurrentTrackProvider);
                    alexaSequencer.onAVSResponse(alexaSequencer.getCurrentIndex() + 1);
                }
            } else {
                getSequencerPlaybackProvider().startSequencer(new AlexaSequencer(this.mContext, this.mCurrentTrackProvider, playbackMetricInformation), ControlSource.ALEXA, Clock.now());
            }
        } else {
            AlexaSequencer alexaSequencer2 = new AlexaSequencer(this.mContext, this.mCurrentTrackProvider, playbackMetricInformation);
            SequencerPlaybackProvider sequencerPlaybackProvider = setSequencerPlaybackProvider();
            if (sequencerPlaybackProvider != null) {
                sequencerPlaybackProvider.startSequencer(alexaSequencer2, ControlSource.ALEXA, Clock.now());
            } else {
                Log.error(TAG, "No SequencerPlaybackProvider available, cannot start Alexa playback");
            }
        }
        if (PlayStatus.USER_PAUSED == this.mPlaybackController.getPlayStatus()) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.mp3.voice.VoiceTrackReceiver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$initiateTrackPlayback$0();
                }
            });
        }
        closeAlexaUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initiateTrackPlayback$0() {
        this.mPlaybackController.play();
    }

    private SequencerPlaybackProvider setSequencerPlaybackProvider() {
        PlayerPlaybackProvider defaultPlaybackProvider = Playback.getInstance().getPlaybackConfig().getDefaultPlaybackProvider(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.mContext));
        if (defaultPlaybackProvider instanceof SequencerPlaybackProvider) {
            Log.info(TAG, "setSequencerPlaybackProvider provider instance of SequencerPlaybackProvider, calling setPlaybackProvider -> shutdown(false)");
            Playback.getInstance().setPlaybackProvider(defaultPlaybackProvider);
            return (SequencerPlaybackProvider) defaultPlaybackProvider;
        }
        Log.error(TAG, "PlaybackProvider: " + defaultPlaybackProvider.getClass().getSimpleName() + "is not an instance of SequencerPlaybackProvider");
        return null;
    }

    private AlexaSequencer getAlexaSequencer() {
        if (getSequencerPlaybackProvider() == null || !(getSequencerPlaybackProvider().getSequencer() instanceof AlexaSequencer)) {
            return null;
        }
        return (AlexaSequencer) getSequencerPlaybackProvider().getSequencer();
    }

    private SequencerPlaybackProvider getSequencerPlaybackProvider() {
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            return (SequencerPlaybackProvider) playbackProvider;
        }
        return null;
    }

    private void closeAlexaUi() {
        if (VoiceManagerSingleton.isInitialized()) {
            VoiceManagerSingleton.getInstance().closeAlexaUi();
        }
    }

    @Override // com.amazon.music.voice.AppSwipeOutListener
    public void onAppRestart(final Context context) {
        this.cancelAudioPlayback = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTrackCollectionLoaded() {
        MusicTrack track;
        if (!WidgetUtil.isAlexaWidgetActivity() && (track = this.mCurrentTrackProvider.getTrack(0)) != null && (track instanceof AlexaTrack) && ((AlexaTrack) track).isFirstTrackInQueue()) {
            Context context = this.mContext;
            Activity activity = VoiceManagerSingleton.getInstance().getActivity();
            if (activity != null && !activity.isDestroyed()) {
                context = activity;
            }
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
            if (activity == null || activity.isDestroyed()) {
                intentForContentUri.setFlags(268435456);
            }
            if (!CarModeUtility.isInCarMode()) {
                context.startActivity(intentForContentUri);
            }
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            }
        }
    }

    private void initiateSpeechMessagesPlayback() {
        String str = this.mCurrentTrackUrl;
        this.mCurrentTrackUrl = null;
        if (str == null) {
            return;
        }
        VoiceManagerSingleton.getInstance().prepareWithAlexaSpeechMessagesPlayer(str);
    }

    private boolean isPodcastUrl(final String url) {
        return StringUtils.isNotBlank(url) && url.contains("https://music.amazon.com/podcasts");
    }

    private Bootstrap getPodcastBootstrap(final String url) {
        PodcastDeeplink.Source source = PodcastDeeplink.Source.AOM;
        if (CarModeUtility.isInCarMode()) {
            source = PodcastDeeplink.Source.CARMODE;
        }
        return new Bootstrap(new PodcastDeeplink(url, source), this.mContext, new UserInfo(), ActionBarInfo.INSTANCE, FragmentInfo.INSTANCE, new NetworkSettings(), new DownloadSettings(null), new PodcastSharingProvider(), new FeatureGateProviderImpl(), false, new PodcastDynamicMessaging(AndroidUtils.getCurrentActivity()), new CarModeUserPresetHandlerImpl(), new AdvertisingPreferencesProviderImpl());
    }
}
