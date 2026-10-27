package com.amazon.podcast.nowplaying;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.ShareElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement;
import SOACoreInterface.v1_0.Method;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.cast.Casting;
import com.amazon.podcast.cast.CastingDeviceList;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$DetailPageItemIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.latency.LatencyMetricAttributes$PageType;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazon.podcast.sharing.SharingManager;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.RibbonNotificationView;
import com.amazon.podcast.views.contextMenu.ContextMenus;
import com.amazon.podcast.views.transcription.FullScreenTranscriptView;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.soa.core.OwnerIdGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class NowPlayingActivity extends AppCompatActivity implements Media.MediaMetadataCallback, GestureDetector.OnGestureListener, Playback.SetMediaMethodCallback, Playback.TranscriptsCallback, Playback.PlaybackFinishedCallback {
    private static final Logger logger = LoggerFactory.getLogger("NowPlayingActivity");
    private EmberTextView aboutButton;
    private BottomSheetBehavior aboutSheetBehavior;
    private AboutView aboutView;
    private ImageView background;
    private BlurTransformation blurTransformation;
    private ImageView castingButton;
    private ImageView dismiss;
    private View filter;
    private FullScreenTranscriptView fullScreenTranscriptView;
    private BottomSheetBehavior fullscreenTranscriptSheetBehavior;
    private GestureDetector gestureDetector;
    private Intent intent;
    private MetadataView metadataView;
    private List<Method> onViewed;
    private ImageView overflowButton;
    private Playback playback;
    private BottomSheetBehavior playbackSpeedSheetBehavior;
    private int previousFullscreenTranscriptSheetState;
    private RibbonNotificationView ribbonNotificationView;
    private ImageButton shareButton;
    private View sleepTimerDurationBS;
    private SleepTimerSelectionView sleepTimerSelectionView;
    private PlaybackSpeedSelectionView speedSelectionView;
    private TransportView transportView;
    private CastingConnectionCallback callback = new CastingConnectionCallback() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.9
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(CastingDevice castingDevice) {
            NowPlayingActivity.this.castingButton.setImageDrawable(NowPlayingActivity.this.getResources().getDrawable(R$drawable.ic_playback_caston));
            NowPlayingActivity.this.castingButton.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason terminationReason) {
            NowPlayingActivity.this.castingButton.setImageDrawable(NowPlayingActivity.this.getResources().getDrawable(R$drawable.ic_playback_castoff));
            NowPlayingActivity.this.castingButton.setColorFilter(NowPlayingActivity.this.getResources().getColor(R$color.primary));
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(CastingException e) {
            NowPlayingActivity.this.castingButton.setImageDrawable(NowPlayingActivity.this.getResources().getDrawable(R$drawable.ic_playback_castoff));
            NowPlayingActivity.this.castingButton.setColorFilter(NowPlayingActivity.this.getResources().getColor(R$color.primary));
        }
    };
    private final String ownerId = OwnerIdGenerator.forTemplateList();

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(final MotionEvent e) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(final MotionEvent e) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(final MotionEvent e) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(final MotionEvent e) {
        return false;
    }

    public NowPlayingActivity() {
        Podcast.getTemplates().setNowPlayingFragmentManager(getSupportFragmentManager());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        LatencyTrackingLeg latencyTrackingLeg = LatencyTrackingLeg.RENDERING;
        LatencyMetricAttributes$PageType latencyMetricAttributes$PageType = LatencyMetricAttributes$PageType.PODCAST_NOW_PLAYING;
        LatencyMetricInterface.trackLatency(latencyTrackingLeg, latencyMetricAttributes$PageType);
        super.onCreate(savedInstanceState);
        logger.debug("oncreate called");
        if (Podcast.getRuntimeStyleSheet() == null) {
            Podcast.initializeRuntimeStyleSheet(getApplicationContext());
        }
        setContentView(R$layout.activity_podcast_now_playing);
        this.blurTransformation = new BlurTransformation(getApplicationContext());
        this.metadataView = (MetadataView) findViewById(R$id.now_playing_metadata);
        this.castingButton = (ImageView) findViewById(R$id.now_playing_casting_button);
        this.shareButton = (ImageButton) findViewById(R$id.now_playing_share_button);
        this.background = (ImageView) findViewById(R$id.now_playing_background);
        this.transportView = (TransportView) findViewById(R$id.now_playing_transport);
        PlaybackSpeedSelectionView playbackSpeedSelectionView = (PlaybackSpeedSelectionView) findViewById(R$id.podcast_now_playing_playback_speed_view);
        this.speedSelectionView = playbackSpeedSelectionView;
        playbackSpeedSelectionView.setOwnerId(this.ownerId);
        this.sleepTimerSelectionView = (SleepTimerSelectionView) findViewById(R$id.podcast_now_playing_sleep_timer_duration_view);
        this.dismiss = (ImageView) findViewById(R$id.cancel_button);
        this.overflowButton = (ImageView) findViewById(R$id.now_playing_overflow_button);
        this.aboutView = (AboutView) findViewById(R$id.now_playing_about_view);
        this.aboutButton = (EmberTextView) findViewById(R$id.about_button);
        this.filter = findViewById(R$id.podcast_now_playing_filter);
        this.fullScreenTranscriptView = (FullScreenTranscriptView) findViewById(R$id.podcast_full_screen_transcription_view);
        this.ribbonNotificationView = (RibbonNotificationView) findViewById(R$id.now_playing_notification_ribbon);
        this.playback = Podcast.getPlayback();
        this.intent = getIntent();
        this.onViewed = Collections.EMPTY_LIST;
        handleOnViewed();
        this.dismiss.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.finish();
            }
        });
        View viewFindViewById = findViewById(R$id.about_bottom_sheet);
        View viewFindViewById2 = findViewById(R$id.podcast_playback_speed_bottom_sheet);
        View viewFindViewById3 = findViewById(R$id.podcast_full_screen_transcription_bottom_sheet);
        this.sleepTimerDurationBS = findViewById(R$id.podcast_sleep_timer_duration_bottom_sheet);
        this.aboutSheetBehavior = BottomSheetBehavior.from(viewFindViewById);
        this.playbackSpeedSheetBehavior = BottomSheetBehavior.from(viewFindViewById2);
        BottomSheetBehavior bottomSheetBehaviorFrom = BottomSheetBehavior.from(viewFindViewById3);
        this.fullscreenTranscriptSheetBehavior = bottomSheetBehaviorFrom;
        this.previousFullscreenTranscriptSheetState = bottomSheetBehaviorFrom.getState();
        bindFilterToBottomSheetBehavior(this.playbackSpeedSheetBehavior, UiMetricAttributes$PageType.PODCASTS_PLAYBACK_SPEED);
        this.aboutButton.setTypeface(Typeface.defaultFromStyle(2), 2);
        this.aboutView.setMinimizeOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (NowPlayingActivity.this.aboutSheetBehavior.getState() == 3) {
                    NowPlayingActivity.this.aboutSheetBehavior.setState(4);
                }
            }
        });
        this.aboutButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                NowPlayingActivity.this.aboutSheetBehavior.setState(3);
                ArrayList arrayList = new ArrayList();
                NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                arrayList.addAll(nowPlayingActivity.uiMetricClickMethods(nowPlayingActivity.playback.getMedia().mediaId(), UiMetricAttributes$ActionType.EXPAND_PODCAST_EPISODE_ABOUT));
                NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, arrayList);
            }
        });
        this.fullscreenTranscriptSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.4
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View view, float v) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View view, int state) {
                if (state == 3) {
                    if (!(NowPlayingActivity.this.previousFullscreenTranscriptSheetState == 3)) {
                        NowPlayingActivity.this.fullScreenTranscriptViewOpenedMethods();
                        NowPlayingActivity.this.fullScreenTranscriptView.instantScrollToPosition();
                        NowPlayingActivity.this.previousFullscreenTranscriptSheetState = 3;
                        return;
                    }
                }
                if (state == 4) {
                    if (NowPlayingActivity.this.previousFullscreenTranscriptSheetState == 4) {
                        return;
                    }
                    NowPlayingActivity.this.fullScreenTranscriptViewClosedMethods();
                    NowPlayingActivity.this.previousFullscreenTranscriptSheetState = 4;
                }
            }
        });
        this.fullScreenTranscriptView.setMinimizeOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NowPlayingActivity.this.fullscreenTranscriptSheetBehavior.getState() == 3) {
                    NowPlayingActivity.this.fullScreenTranscriptView.unblockScrolling();
                    NowPlayingActivity.this.metadataView.handlePausedState();
                    NowPlayingActivity.this.fullscreenTranscriptSheetBehavior.setState(4);
                }
            }
        });
        this.fullScreenTranscriptView.setOwnerId(this.ownerId);
        this.metadataView.addTranscriptIngressPaneClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.fullScreenTranscriptView.instantScrollToPosition();
                NowPlayingActivity.this.fullScreenTranscriptView.handlePauseState();
                NowPlayingActivity.this.fullscreenTranscriptSheetBehavior.setState(3);
                NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ActionType.EXPAND_FULL_SCRUB, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, NowPlayingActivity.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_SCRUB_INGRESS));
            }
        });
        this.metadataView.addHalfScreenDismissListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.metadataView.hideHalfScreenTranscriptView();
                NowPlayingActivity.this.metadataView.showTranscriptIngressPane();
                NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ActionType.CLOSE_SCRUB, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, NowPlayingActivity.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_HALF_SCRUB));
            }
        });
        this.metadataView.addFullScreenTranscriptionExpandListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.fullScreenTranscriptView.instantScrollToPosition();
                NowPlayingActivity.this.fullScreenTranscriptView.handlePauseState();
                NowPlayingActivity.this.metadataView.showTranscriptIngressPane();
                NowPlayingActivity.this.metadataView.hideHalfScreenTranscriptView();
                NowPlayingActivity.this.fullscreenTranscriptSheetBehavior.setState(3);
                NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ActionType.EXPAND_FULL_SCRUB, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, NowPlayingActivity.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_HALF_SCRUB));
            }
        });
        this.metadataView.setOwnerId(this.ownerId);
        this.aboutView.setFragmentManager(getSupportFragmentManager());
        bindPlaybackSpeedView(this.speedSelectionView, this.playbackSpeedSheetBehavior);
        bindSleepTimerDurationView();
        bindContextMenuButton(this.playback.getEpisodeOptionsElement());
        bindCastingButton();
        bindShareButton(this);
        this.gestureDetector = new GestureDetector(getApplicationContext(), this);
        addMetadataViewTitleSubtitleOnClickListener();
        addMetadataViewSeekbarOnTouchListener();
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, latencyMetricAttributes$PageType);
        if (Podcast.isFireOS()) {
            return;
        }
        CastingSessionManager.getInstance().registerListener(this.callback);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        logger.debug("on resume called");
        dispatchNowPlayingScreenOpenedMethods();
        if (this.playback.isPodcastTranscriptsDisabledFromSettings()) {
            this.playback.setTranscriptImplementation(null);
            this.playback.setHasFetchedTranscript(false);
            this.metadataView.bindTranscriptIngressPane();
        }
        Media media = this.playback.getMedia();
        bindBackground(media.getMediaMetadataElement());
        media.addMediaMetadataCallback(this);
        this.playback.addTranscriptsCallback(this);
        this.playback.addPlaybackFinishedCallback(this);
        bindContextMenuButton(this.playback.getEpisodeOptionsElement());
        this.playback.setSetMediaMethodCallback(this);
        this.playback.setCastingFromPodcast(true);
        MetadataView metadataView = this.metadataView;
        if (metadataView != null) {
            metadataView.setMaxPlaybackDuration((int) this.playback.getPlaybackDuration());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        ContextMenus.dismissContextMenuPopUp();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.playback.getMedia().removeMediaMetadataCallback(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        int state = this.aboutSheetBehavior.getState();
        int state2 = this.fullscreenTranscriptSheetBehavior.getState();
        if (state == 3) {
            this.aboutSheetBehavior.setState(4);
        } else {
            if (state2 == 3) {
                this.fullScreenTranscriptView.unblockScrolling();
                this.metadataView.handlePausedState();
                this.fullscreenTranscriptSheetBehavior.setState(4);
                return;
            }
            super.onBackPressed();
        }
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        bindContextMenuButton(this.playback.getEpisodeOptionsElement());
        bindBackground(metadataElement);
    }

    @Override // com.amazon.podcast.media.playback.Playback.SetMediaMethodCallback
    public void onSetMediaMethodCalled(final EpisodeOptionsElement episodeOptionsElement) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.10
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingActivity.this.bindContextMenuButton(episodeOptionsElement);
            }
        });
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(final MotionEvent e) {
        return this.gestureDetector.onTouchEvent(e);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
        if (e1 == null || e2 == null) {
            return false;
        }
        if (e1.getY() - e2.getY() > 120.0f && Math.abs(velocityY) > 200.0f) {
            this.aboutSheetBehavior.setState(3);
            return true;
        }
        if (e2.getY() - e1.getY() <= 120.0f || Math.abs(velocityY) <= 200.0f) {
            return true;
        }
        finish();
        return true;
    }

    private void bindBackground(final MediaMetadataElement metadataElement) {
        if (metadataElement == null) {
            Picasso.get().load(R$drawable.artwork_placeholder).into(this.background);
        } else {
            Picasso.get().load(metadataElement.getArtwork()).placeholder(R$drawable.artwork_placeholder).transform(this.blurTransformation).into(this.background);
        }
    }

    private void bindPlaybackSpeedView(final PlaybackSpeedSelectionView playbackSpeedSelectionView, final BottomSheetBehavior playbackSpeedViewBehavior) {
        playbackSpeedViewBehavior.setPeekHeight(0);
        this.transportView.setSpeedButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                playbackSpeedViewBehavior.setState(3);
                ArrayList arrayList = new ArrayList();
                NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                arrayList.addAll(nowPlayingActivity.uiMetricClickMethods(nowPlayingActivity.playback.getMedia().mediaId(), UiMetricAttributes$ActionType.EXPAND_PODCAST_PLAYBACK_SPEED));
                NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, arrayList);
            }
        });
        playbackSpeedSelectionView.setDismissClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                playbackSpeedViewBehavior.setState(4);
            }
        });
    }

    private void bindFilterToBottomSheetBehavior(final BottomSheetBehavior bottomSheetBehavior, final UiMetricAttributes$PageType pageType) {
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.13
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(@NonNull final View view, final int state) {
                List<Method> listPlaybackSpeedPageOpenedMethods;
                if (state == 4) {
                    NowPlayingActivity.this.filter.setVisibility(4);
                }
                if (state == 3) {
                    if (pageType == UiMetricAttributes$PageType.PODCASTS_PLAYBACK_SPEED) {
                        listPlaybackSpeedPageOpenedMethods = NowPlayingActivity.this.playbackSpeedPageOpenedMethods();
                    } else {
                        listPlaybackSpeedPageOpenedMethods = Collections.EMPTY_LIST;
                    }
                    NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, listPlaybackSpeedPageOpenedMethods);
                }
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(@NonNull final View view, final float slideOffset) {
                NowPlayingActivity.this.filter.setVisibility(0);
                NowPlayingActivity.this.filter.setAlpha(slideOffset);
            }
        });
    }

    private void bindSleepTimerDurationView() {
        final BottomSheetBehavior bottomSheetBehaviorFrom = BottomSheetBehavior.from(this.sleepTimerDurationBS);
        bindFilterToBottomSheetBehavior(bottomSheetBehaviorFrom, null);
        bottomSheetBehaviorFrom.setPeekHeight(0);
        this.transportView.setSleepTimerButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                bottomSheetBehaviorFrom.setState(3);
            }
        });
        this.sleepTimerSelectionView.setDismissClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                bottomSheetBehaviorFrom.setState(4);
            }
        });
    }

    private void addMetadataViewTitleSubtitleOnClickListener() {
        this.metadataView.addTitleSubtitleOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                NowPlayingActivity.this.aboutSheetBehavior.setState(3);
            }
        });
    }

    private void addMetadataViewSeekbarOnTouchListener() {
        this.metadataView.addSeekbarOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.17
            private float initialY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action != 0) {
                    if (action == 1) {
                        boolean z = this.initialY - event.getY() < 120.0f;
                        NowPlayingActivity.this.metadataView.setIsSeeking(z);
                        if (!z) {
                            NowPlayingActivity.this.aboutSheetBehavior.setState(3);
                        }
                    }
                } else {
                    this.initialY = event.getY();
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindContextMenuButton(final EpisodeOptionsElement episodeOptionsElement) {
        if (episodeOptionsElement == null) {
            return;
        }
        this.overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpisodeOptionsElement episodeOptionsElement2 = episodeOptionsElement;
                NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                Resources resources = nowPlayingActivity.getResources();
                Playback playback = NowPlayingActivity.this.playback;
                String str = NowPlayingActivity.this.ownerId;
                NowPlayingActivity nowPlayingActivity2 = NowPlayingActivity.this;
                ContextMenus.show(episodeOptionsElement2, nowPlayingActivity, resources, playback, str, nowPlayingActivity2, nowPlayingActivity2.getSupportFragmentManager(), NowPlayingActivity.this.overflowButton, "NowPlayingActivity", UiMetricAttributes$PageType.PODCAST_NOW_PLAYING);
            }
        });
        this.overflowButton.setVisibility(0);
        this.overflowButton.setContentDescription(getResources().getString(R$string.podcast_overflow_button));
    }

    private void bindCastingButton() {
        if (Podcast.isFireOS()) {
            this.castingButton.setVisibility(8);
            return;
        }
        this.castingButton.setVisibility(0);
        if (Casting.isCasting()) {
            this.castingButton.setImageDrawable(getDrawable(R$drawable.ic_playback_caston));
            this.castingButton.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
        } else {
            this.castingButton.setImageDrawable(getDrawable(R$drawable.ic_playback_castoff));
            this.castingButton.setColorFilter(getResources().getColor(R$color.primary));
        }
        this.castingButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.startActivity(new Intent(NowPlayingActivity.this, (Class<?>) CastingDeviceList.class));
            }
        });
    }

    private void bindShareButton(final FragmentActivity fragmentActivity) {
        if (PodcastFeatureGating.SHARING.isEnabled()) {
            this.shareButton.setContentDescription(getResources().getString(R$string.podcast_share_episode_prompt));
            this.shareButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.20
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ShareElement shareElementFromEpisodeOptions = NowPlayingActivity.this.playback.getShareElementFromEpisodeOptions();
                    if (shareElementFromEpisodeOptions == null) {
                        return;
                    }
                    SharingManager.handleEpisodeOptionsShareClick(fragmentActivity, shareElementFromEpisodeOptions);
                    ArrayList arrayList = new ArrayList();
                    NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                    arrayList.addAll(nowPlayingActivity.uiMetricClickMethods(nowPlayingActivity.playback.getMedia().mediaId(), UiMetricAttributes$ActionType.SHARE_PODCAST_EPISODE));
                    NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, arrayList);
                }
            });
            this.shareButton.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Method> uiMetricClickMethods(final String id, final UiMetricAttributes$ActionType actionType) {
        return UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, actionType, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, id, (Integer) null, UiMetricAttributes$ContentName.PODCAST_NOW_PLAYING);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Podcast.getTemplates().setNowPlayingFragmentManager(null);
        this.playback.setSetMediaMethodCallback(null);
        this.playback.removeTranscriptsCallback(this);
        this.playback.removePlaybackFinishedCallback(this);
        if (Podcast.isFireOS()) {
            return;
        }
        CastingSessionManager.getInstance().unregisterListener(this.callback);
    }

    public void handleOnViewed() {
        try {
            Bundle extras = this.intent.getExtras();
            if (this.playback != null && CollectionUtils.isEmpty(this.onViewed) && extras != null) {
                this.onViewed = (List) Mappers.mapper().readValue(extras.getString("com.amazon.dmpplaybackservice.playback.ON_VIEWED_METHODS"), new TypeReference<List<Method>>() { // from class: com.amazon.podcast.nowplaying.NowPlayingActivity.21
                });
                this.playback.dispatch(extras.getString("com.amazon.dmpplaybackservice.playback.ON_VIEWED_OWNER_ID"), this.onViewed);
                logger.info("OnViewed Now Playing Template Methods Dispatched");
            }
        } catch (IOException e) {
            logger.error("Failed deserializing on viewed methods" + e.getMessage());
        }
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptsCallback
    public void onTranscriptFetched(List<ScrubSegment> scrubSegments) {
        logger.debug("onTranscriptFetched");
        restartNowPlayingActivity();
    }

    private void restartNowPlayingActivity() {
        Intent intent = getIntent();
        if (this.ribbonNotificationView.getVisibility() == 8) {
            intent.removeExtra("com.amazon.dmpplaybackservice.playback.ON_VIEWED_METHODS");
            intent.removeExtra("com.amazon.dmpplaybackservice.playback.ON_VIEWED_OWNER_ID");
        }
        overridePendingTransition(0, 0);
        intent.addFlags(65536);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override // com.amazon.podcast.media.playback.Playback.PlaybackFinishedCallback
    public void onPlaybackFinished() {
        if (this.fullscreenTranscriptSheetBehavior.getState() == 3) {
            this.fullScreenTranscriptView.unblockScrolling();
            this.fullscreenTranscriptSheetBehavior.setState(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Method> playbackSpeedPageOpenedMethods() {
        ArrayList arrayList = new ArrayList();
        if (this.playback.getMedia() == null || this.playback.getMedia().mediaId() == null) {
            return Collections.EMPTY_LIST;
        }
        arrayList.addAll(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCASTS_PLAYBACK_SPEED, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, this.playback.getMedia().mediaId()));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fullScreenTranscriptViewOpenedMethods() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCASTS_FULL_SCRUB, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, this.ownerId));
        arrayList.addAll(UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ContainerType.NOW_PLAYING, UiMetricAttributes$ContentName.PODCASTS_FULL_SCRUB));
        this.playback.dispatch(this.ownerId, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fullScreenTranscriptViewClosedMethods() {
        this.playback.dispatch(this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_FULL_SCRUB, UiMetricAttributes$ActionType.CLOSE_SCRUB, UiMetricAttributes$ContentName.PODCASTS_FULL_SCRUB));
    }

    private void dispatchNowPlayingScreenOpenedMethods() {
        ArrayList arrayList = new ArrayList();
        String strMediaId = this.playback.getMedia().mediaId();
        arrayList.addAll(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, strMediaId));
        this.playback.dispatch(strMediaId, arrayList);
    }

    public void handleTemplateMethod(final String ownerId, final MethodsDispatcher methodsDispatcher, final Method method) {
        if (method instanceof ShowNotificationMethod) {
            NotificationElement notification = ((ShowNotificationMethod) method).getNotification();
            if (notification instanceof RibbonNotificationElement) {
                this.ribbonNotificationView.bind((RibbonNotificationElement) notification, methodsDispatcher, ownerId);
                this.ribbonNotificationView.setVisibility(0);
                return;
            }
            return;
        }
        if (method instanceof CloseNotificationMethod) {
            this.ribbonNotificationView.setVisibility(8);
        }
    }
}
