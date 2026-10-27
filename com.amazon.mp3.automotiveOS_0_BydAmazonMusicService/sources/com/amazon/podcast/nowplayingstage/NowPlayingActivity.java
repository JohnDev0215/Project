package com.amazon.podcast.nowplayingstage;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement;
import SOACoreInterface.v1_0.Method;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
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
import com.amazon.podcast.nowplayingstage.overlays.NowPlayingAboutFragment;
import com.amazon.podcast.nowplayingstage.overlays.NowPlayingTranscriptFragment;
import com.amazon.podcast.sharing.SharingManager;
import com.amazon.podcast.transformations.BlurTransformation;
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
    private NowPlayingAboutFragment aboutFragment;
    private View artworkPlaceholder;
    private ImageView background;
    private BlurTransformation blurTransformation;
    private ImageView dismiss;
    private ImageView dismissExpanded;
    private View filter;
    private FullScreenTranscriptView fullScreenTranscriptView;
    private BottomSheetBehavior fullscreenTranscriptSheetBehavior;
    private GestureDetector gestureDetector;
    private Intent intent;
    private MetadataView metadataView;
    private List<Method> onViewed;
    private Playback playback;
    private BottomSheetBehavior playbackSpeedSheetBehavior;
    private int previousFullscreenTranscriptSheetState;
    private RibbonNotificationView ribbonNotificationView;
    private View sleepTimerDurationBS;
    private SleepTimerSelectionView sleepTimerSelectionView;
    private PlaybackSpeedSelectionView speedSelectionView;
    private RelativeLayout topBar;
    private int topBarHeight;
    private NowPlayingTranscriptFragment transcriptFragment;
    private TransportView transportView;
    private boolean isAboutActive = false;
    private boolean isTranscriptActive = false;
    private boolean isOverlayExpanded = false;
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
        setContentView(R$layout.activity_podcast_now_playing_stage);
        this.blurTransformation = new BlurTransformation(getApplicationContext());
        this.metadataView = (MetadataView) findViewById(R$id.now_playing_metadata);
        this.background = (ImageView) findViewById(R$id.now_playing_background);
        this.topBar = (RelativeLayout) findViewById(R$id.top_bar);
        this.transportView = (TransportView) findViewById(R$id.now_playing_transport);
        PlaybackSpeedSelectionView playbackSpeedSelectionView = (PlaybackSpeedSelectionView) findViewById(R$id.podcast_now_playing_playback_speed_view);
        this.speedSelectionView = playbackSpeedSelectionView;
        playbackSpeedSelectionView.setOwnerId(this.ownerId);
        this.sleepTimerSelectionView = (SleepTimerSelectionView) findViewById(R$id.podcast_now_playing_sleep_timer_duration_view);
        this.dismiss = (ImageView) findViewById(R$id.cancel_button);
        this.dismissExpanded = (ImageView) findViewById(R$id.close_button);
        this.artworkPlaceholder = findViewById(R$id.podcast_art_placeholder);
        this.filter = findViewById(R$id.podcast_now_playing_filter);
        this.fullScreenTranscriptView = (FullScreenTranscriptView) findViewById(R$id.podcast_full_screen_transcription_view);
        this.ribbonNotificationView = (RibbonNotificationView) findViewById(R$id.now_playing_notification_ribbon);
        this.aboutFragment = new NowPlayingAboutFragment();
        this.transcriptFragment = new NowPlayingTranscriptFragment();
        this.playback = Podcast.getPlayback();
        this.intent = getIntent();
        this.onViewed = Collections.EMPTY_LIST;
        handleOnViewed();
        this.dismiss.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.finish();
            }
        });
        View viewFindViewById = findViewById(R$id.podcast_playback_speed_bottom_sheet);
        View viewFindViewById2 = findViewById(R$id.podcast_full_screen_transcription_bottom_sheet);
        this.sleepTimerDurationBS = findViewById(R$id.podcast_sleep_timer_duration_bottom_sheet);
        this.playbackSpeedSheetBehavior = BottomSheetBehavior.from(viewFindViewById);
        BottomSheetBehavior bottomSheetBehaviorFrom = BottomSheetBehavior.from(viewFindViewById2);
        this.fullscreenTranscriptSheetBehavior = bottomSheetBehaviorFrom;
        this.previousFullscreenTranscriptSheetState = bottomSheetBehaviorFrom.getState();
        bindFilterToBottomSheetBehavior(this.playbackSpeedSheetBehavior, UiMetricAttributes$PageType.PODCASTS_PLAYBACK_SPEED);
        this.topBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                NowPlayingActivity.this.topBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                nowPlayingActivity.topBarHeight = nowPlayingActivity.topBar.getHeight();
            }
        });
        this.metadataView.setAboutOverlayButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                NowPlayingActivity.this.isAboutActive = !r5.isAboutActive;
                NowPlayingActivity.this.isOverlayExpanded = false;
                NowPlayingActivity.this.metadataView.setAboutStyleOnClicked(NowPlayingActivity.this.isAboutActive);
                if (NowPlayingActivity.this.isAboutActive) {
                    NowPlayingActivity.this.showHideFragment("now_playing_transcript_frag", false);
                    NowPlayingActivity.this.showHideFragment("now_playing_about_frag", true);
                    if (!NowPlayingActivity.this.isTranscriptActive) {
                        NowPlayingActivity.this.metadataView.showMinimizeArtwork(false);
                    }
                    NowPlayingActivity.this.isTranscriptActive = false;
                    NowPlayingActivity.this.setPartialMode();
                    NowPlayingActivity.this.setArtworkPlaceholderClickListener(true);
                    ArrayList arrayList = new ArrayList();
                    NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                    arrayList.addAll(nowPlayingActivity.uiMetricClickMethods(nowPlayingActivity.playback.getMedia().mediaId(), UiMetricAttributes$ActionType.EXPAND_PODCAST_EPISODE_ABOUT));
                    NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, arrayList);
                    return;
                }
                NowPlayingActivity.this.setArtworkPlaceholderClickListener(false);
                NowPlayingActivity.this.showHideFragment("now_playing_about_frag", false);
                if (NowPlayingActivity.this.isTranscriptActive || NowPlayingActivity.this.isAboutActive) {
                    return;
                }
                NowPlayingActivity.this.metadataView.showMinimizeArtwork(true);
            }
        });
        this.metadataView.setTranscriptOverlayButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                NowPlayingActivity.this.isTranscriptActive = !r9.isTranscriptActive;
                NowPlayingActivity.this.isOverlayExpanded = false;
                NowPlayingActivity.this.metadataView.setTranscriptStyleOnClicked(NowPlayingActivity.this.isTranscriptActive);
                NowPlayingActivity.this.transcriptFragment.setOwnerId(NowPlayingActivity.this.ownerId);
                if (NowPlayingActivity.this.isTranscriptActive) {
                    NowPlayingActivity.this.showHideFragment("now_playing_about_frag", false);
                    if (!NowPlayingActivity.this.isAboutActive) {
                        NowPlayingActivity.this.metadataView.showMinimizeArtwork(false);
                    }
                    NowPlayingActivity.this.isAboutActive = false;
                    NowPlayingActivity.this.setPartialMode();
                    NowPlayingActivity.this.showHideFragment("now_playing_transcript_frag", true);
                    NowPlayingActivity.this.setArtworkPlaceholderClickListener(true);
                    return;
                }
                NowPlayingActivity.this.setArtworkPlaceholderClickListener(false);
                NowPlayingActivity.this.showHideFragment("now_playing_transcript_frag", false);
                if (!NowPlayingActivity.this.isTranscriptActive && !NowPlayingActivity.this.isAboutActive) {
                    NowPlayingActivity.this.metadataView.showMinimizeArtwork(true);
                }
                NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ActionType.CLOSE_SCRUB, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, NowPlayingActivity.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_HALF_SCRUB));
            }
        });
        this.dismissExpanded.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.setPartialMode();
            }
        });
        this.fullscreenTranscriptSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.6
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
        this.fullScreenTranscriptView.setMinimizeOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NowPlayingActivity.this.fullscreenTranscriptSheetBehavior.getState() == 3) {
                    NowPlayingActivity.this.fullScreenTranscriptView.unblockScrolling();
                    NowPlayingActivity.this.fullscreenTranscriptSheetBehavior.setState(4);
                }
            }
        });
        this.fullScreenTranscriptView.setOwnerId(this.ownerId);
        this.metadataView.setOwnerId(this.ownerId);
        bindPlaybackSpeedView(this.speedSelectionView, this.playbackSpeedSheetBehavior);
        bindSleepTimerDurationView();
        bindContextMenuButton(this.playback.getEpisodeOptionsElement());
        bindCastingButton();
        bindShareButton(this);
        this.gestureDetector = new GestureDetector(getApplicationContext(), this);
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, latencyMetricAttributes$PageType);
        if (Podcast.isFireOS()) {
            return;
        }
        this.transportView.registerCastingListener();
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
        if (this.fullscreenTranscriptSheetBehavior.getState() == 3) {
            this.fullScreenTranscriptView.unblockScrolling();
            this.fullscreenTranscriptSheetBehavior.setState(4);
        } else {
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
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.8
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
        if (e2.getY() - e1.getY() <= 120.0f || Math.abs(velocityY) <= 200.0f) {
            return true;
        }
        finish();
        return true;
    }

    public boolean getIsOverlayExpanded() {
        return this.isOverlayExpanded;
    }

    public void closePreviewTranscript() {
        if (this.isTranscriptActive) {
            return;
        }
        showHideFragment("now_playing_transcript_frag", false);
    }

    public void setPreviewTranscriptVisible(final boolean isVisible) {
        if (this.isTranscriptActive) {
            return;
        }
        if (!isVisible || !this.metadataView.getIsSeekbarTracking()) {
            if (isVisible) {
                return;
            }
            this.metadataView.setTranscriptStyleOnScrub(isVisible);
            showHideFragment("now_playing_transcript_frag", false);
            if (this.isAboutActive) {
                showHideFragment("now_playing_about_frag", true);
            }
            if (this.isAboutActive) {
                return;
            }
            this.metadataView.showMinimizeArtwork(true);
            return;
        }
        this.transcriptFragment.setOwnerId(this.ownerId);
        this.metadataView.setTranscriptStyleOnScrub(isVisible);
        if (this.isAboutActive) {
            showHideFragment("now_playing_about_frag", false);
            showHideFragment("now_playing_transcript_frag", true);
        }
        setPartialMode();
        if (this.isAboutActive) {
            return;
        }
        MetadataView metadataView = this.metadataView;
        metadataView.setAllParentsClip(metadataView.getArtwork(), false);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.metadataView.getArtwork(), (Property<View, Float>) View.SCALE_X, this.metadataView.getRatioForPreview())).with(ObjectAnimator.ofFloat(this.metadataView.getArtwork(), (Property<View, Float>) View.SCALE_Y, this.metadataView.getRatioForPreview())).with(ObjectAnimator.ofFloat(this.metadataView.getArtwork(), (Property<View, Float>) View.TRANSLATION_Y, -this.metadataView.getTranslateForPreview()));
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (NowPlayingActivity.this.metadataView.getIsSeekbarTracking()) {
                    NowPlayingActivity.this.showHideFragment("now_playing_transcript_frag", true);
                }
                super.onAnimationEnd(animation);
            }
        });
        animatorSet.start();
    }

    public void setPreviewTranscriptIndex(final long progress) {
        Integer transcriptIndex;
        NowPlayingTranscriptFragment nowPlayingTranscriptFragment = (NowPlayingTranscriptFragment) getFragment("now_playing_transcript_frag");
        if (nowPlayingTranscriptFragment == null || (transcriptIndex = nowPlayingTranscriptFragment.getTranscriptIndex(progress)) == null) {
            return;
        }
        nowPlayingTranscriptFragment.instantScrollToPosition(transcriptIndex.intValue());
    }

    public void setPartialMode() {
        this.isOverlayExpanded = false;
        this.dismiss.setVisibility(0);
        this.dismissExpanded.setVisibility(8);
        this.metadataView.setPartialMode();
        this.metadataView.setVisibility(0);
        this.transportView.setVisibility(0);
    }

    public void setExpandedMode() {
        this.isOverlayExpanded = true;
        this.dismiss.setVisibility(8);
        this.dismissExpanded.setVisibility(0);
        this.transportView.setVisibility(8);
        this.metadataView.setExpandedMode((getHeightPixels() - this.topBarHeight) - getResources().getDimensionPixelSize(R$dimen.podcast_expanded_controls_height));
    }

    public void setArtworkPlaceholderClickListener(final boolean set) {
        if (set) {
            this.artworkPlaceholder.bringToFront();
            this.artworkPlaceholder.getParent().requestLayout();
            this.artworkPlaceholder.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NowPlayingActivity.this.metadataView.showMinimizeArtwork(true);
                    NowPlayingActivity.this.showHideFragment("now_playing_about_frag", false);
                    NowPlayingActivity.this.showHideFragment("now_playing_transcript_frag", false);
                    NowPlayingActivity.this.isAboutActive = false;
                    NowPlayingActivity.this.isTranscriptActive = false;
                    NowPlayingActivity.this.metadataView.setAboutStyleOnClicked(NowPlayingActivity.this.isAboutActive);
                    NowPlayingActivity.this.metadataView.setTranscriptStyleOnClicked(NowPlayingActivity.this.isTranscriptActive);
                    NowPlayingActivity.this.setPartialMode();
                }
            });
            return;
        }
        this.artworkPlaceholder.setOnClickListener(null);
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
        this.transportView.setSpeedButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                playbackSpeedViewBehavior.setState(3);
                ArrayList arrayList = new ArrayList();
                NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                arrayList.addAll(nowPlayingActivity.uiMetricClickMethods(nowPlayingActivity.playback.getMedia().mediaId(), UiMetricAttributes$ActionType.EXPAND_PODCAST_PLAYBACK_SPEED));
                NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, arrayList);
            }
        });
        playbackSpeedSelectionView.setDismissClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                playbackSpeedViewBehavior.setState(4);
            }
        });
    }

    private void bindFilterToBottomSheetBehavior(final BottomSheetBehavior bottomSheetBehavior, final UiMetricAttributes$PageType pageType) {
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.13
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
        this.transportView.setSleepTimerButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                bottomSheetBehaviorFrom.setState(3);
            }
        });
        this.sleepTimerSelectionView.setDismissClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                bottomSheetBehaviorFrom.setState(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindContextMenuButton(final EpisodeOptionsElement episodeOptionsElement) {
        if (episodeOptionsElement == null) {
            return;
        }
        this.transportView.setOverflowButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpisodeOptionsElement episodeOptionsElement2 = episodeOptionsElement;
                NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                Resources resources = nowPlayingActivity.getResources();
                Playback playback = NowPlayingActivity.this.playback;
                String str = NowPlayingActivity.this.ownerId;
                NowPlayingActivity nowPlayingActivity2 = NowPlayingActivity.this;
                ContextMenus.show(episodeOptionsElement2, nowPlayingActivity, resources, playback, str, nowPlayingActivity2, nowPlayingActivity2.getSupportFragmentManager(), NowPlayingActivity.this.transportView.overflowButton, "NowPlayingActivity", UiMetricAttributes$PageType.PODCAST_NOW_PLAYING);
            }
        });
    }

    private void bindCastingButton() {
        this.transportView.setCastingButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NowPlayingActivity.this.startActivity(new Intent(NowPlayingActivity.this, (Class<?>) CastingDeviceList.class));
            }
        });
    }

    private void bindShareButton(final FragmentActivity fragmentActivity) {
        if (PodcastFeatureGating.SHARING.isEnabled()) {
            this.transportView.setShareButtonClickedListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.18
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SharingManager.handleEpisodeOptionsShareClick(fragmentActivity, NowPlayingActivity.this.playback.getEpisodeOptionsElement().getShare());
                    ArrayList arrayList = new ArrayList();
                    NowPlayingActivity nowPlayingActivity = NowPlayingActivity.this;
                    arrayList.addAll(nowPlayingActivity.uiMetricClickMethods(nowPlayingActivity.playback.getMedia().mediaId(), UiMetricAttributes$ActionType.SHARE_PODCAST_EPISODE));
                    NowPlayingActivity.this.playback.dispatch(NowPlayingActivity.this.ownerId, arrayList);
                }
            });
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
        this.transportView.unregisterCastingListener();
    }

    public void handleOnViewed() {
        try {
            Bundle extras = this.intent.getExtras();
            if (this.playback != null && CollectionUtils.isEmpty(this.onViewed) && extras != null) {
                this.onViewed = (List) Mappers.mapper().readValue(extras.getString("com.amazon.dmpplaybackservice.playback.ON_VIEWED_METHODS"), new TypeReference<List<Method>>() { // from class: com.amazon.podcast.nowplayingstage.NowPlayingActivity.19
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
        this.isTranscriptActive = false;
        setPreviewTranscriptVisible(false);
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

    private int getHeightPixels() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHideFragment(final String tag, final boolean show) {
        Fragment fragment;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment2 = getFragment(tag);
        if (fragment2 == null && show) {
            FragmentTransaction reorderingAllowed = supportFragmentManager.beginTransaction().setReorderingAllowed(true);
            int i = R$id.overlay_container;
            if (tag == "now_playing_about_frag") {
                fragment = this.aboutFragment;
            } else {
                fragment = this.transcriptFragment;
            }
            reorderingAllowed.add(i, fragment, tag).commit();
            return;
        }
        if (fragment2 == null) {
            return;
        }
        if (show) {
            supportFragmentManager.beginTransaction().show(fragment2).commit();
        } else {
            supportFragmentManager.beginTransaction().hide(fragment2).commit();
        }
    }

    private Fragment getFragment(final String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
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
