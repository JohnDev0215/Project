package com.amazon.podcast.nowplaying;

import Podcast.PlaybackInterface.v1_0.MediaControlsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.cast.ChromecastMediaProvider;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.soa.core.OwnerIdGenerator;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class MiniPlayerView extends RelativeLayout implements Media.MediaMetadataCallback, Media.MediaControlsCallback, Playback.PositionCallback, Playback.DurationCallback, Playback.StateCallback, Playback.BufferedPositionCallback, GestureDetector.OnGestureListener {
    private ImageView artwork;
    private ImageView artworkOverlay;
    private ImageView background;
    private ImageView backgroundFilter;
    private ImageView backgroundGradient;
    private BlurTransformation blurTransformation;
    private EmberTextView castingDeviceName;
    private CastingSessionManager castingSessionManager;
    private ImageView fastForward;
    private GestureDetector gestureDetector;
    private boolean isSingleTapFunctionalityEnabled;
    private ImageView minimize;
    private List<Method> onViewed;
    private String ownerId;
    private ImageView playPause;
    private Playback playback;
    private ProgressBar progressBar;
    private RoundedCornersTransformation roundedCornersTransformation;
    private EmberTextView subtitle;
    private EmberTextView title;

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

    public MiniPlayerView(final Context context) {
        super(context);
        init();
    }

    public MiniPlayerView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        View.inflate(getContext(), R$layout.podcast_mini_player_view, this);
        this.isSingleTapFunctionalityEnabled = true;
        this.roundedCornersTransformation = new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_mini_player_artwork_corner_radius));
        this.blurTransformation = new BlurTransformation(getContext());
        this.artwork = (ImageView) findViewById(R$id.mini_player_artwork);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.mini_player_title);
        this.title = emberTextView;
        emberTextView.setSelected(true);
        this.subtitle = (EmberTextView) findViewById(R$id.mini_player_subtitle);
        this.minimize = (ImageView) findViewById(R$id.mini_player_minimize);
        this.playPause = (ImageView) findViewById(R$id.mini_player_play_pause_button);
        this.fastForward = (ImageView) findViewById(R$id.mini_player_ff_button);
        this.background = (ImageView) findViewById(R$id.mini_player_background);
        this.backgroundFilter = (ImageView) findViewById(R$id.mini_player_background_filter);
        this.backgroundGradient = (ImageView) findViewById(R$id.mini_player_background_gradient);
        this.artworkOverlay = (ImageView) findViewById(R$id.mini_player_artwork_overlay);
        this.progressBar = (ProgressBar) findViewById(R$id.mini_player_progress_bar);
        this.castingDeviceName = (EmberTextView) findViewById(R$id.mini_player_casting_device_name);
        this.progressBar.setPadding(0, 0, 0, 0);
        Playback playback = Podcast.getPlayback();
        this.playback = playback;
        Media media = playback.getMedia();
        media.addMediaMetadataCallback(this);
        this.playback.addStateCallback(this);
        this.playback.addPositionCallback(this);
        this.playback.addDurationCallback(this);
        media.addMediaControlsCallback(this);
        bindControls();
        this.gestureDetector = new GestureDetector(getContext(), this);
        setVisibility(0);
        this.minimize.setVisibility(8);
        this.castingSessionManager = CastingSessionManager.getInstance();
        this.ownerId = OwnerIdGenerator.forTemplateList();
        this.onViewed = miniPlayerOnViewedMethods();
        this.playback.registerOwner(this.ownerId);
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaControlsCallback
    public void onMediaControlsChange(final MediaControlsElement controlsElement) {
        bindControls();
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        if (state == 3) {
            this.playPause.setContentDescription(getResources().getString(R$string.podcast_pause_label));
            this.playPause.setImageResource(R$drawable.ic_playback_pause_mini_player);
        } else {
            this.playPause.setContentDescription(getResources().getString(R$string.podcast_play_label));
            this.playPause.setImageResource(R$drawable.ic_playback_play_mini_player);
        }
    }

    public void setMinimizeOnClickListener(View.OnClickListener listener) {
        this.minimize.setVisibility(0);
        this.minimize.setOnClickListener(listener);
    }

    public void setFromAboutView() {
        this.background.setVisibility(8);
        this.backgroundFilter.setVisibility(8);
        this.backgroundGradient.setVisibility(8);
        this.artworkOverlay.setVisibility(0);
        setVisibility(0);
        setOnClickListener(null);
    }

    private void launchNowPlayingActivity() {
        Intent intent;
        if (PodcastFeatureGating.PODCAST_YA_IMMERSE_NPS.isEnabled()) {
            intent = new Intent(getContext(), (Class<?>) com.amazon.podcast.nowplayingstage.NowPlayingActivity.class);
        } else {
            intent = new Intent(getContext(), (Class<?>) NowPlayingActivity.class);
        }
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        bindMediaMetadata(metadataElement);
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(final long timeInMillis) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.nowplaying.MiniPlayerView.1
            @Override // java.lang.Runnable
            public void run() {
                MiniPlayerView.this.progressBar.setProgress((int) timeInMillis);
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.DurationCallback
    public void onPlaybackDurationChange(final long duration) {
        if (duration > 0) {
            this.progressBar.setMax((int) duration);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(final MotionEvent e) {
        return this.gestureDetector.onTouchEvent(e);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(final MotionEvent e) {
        if (!this.isSingleTapFunctionalityEnabled) {
            return false;
        }
        launchNowPlayingActivity();
        onClickedExpandMiniPlayer();
        return false;
    }

    private void onClickedExpandMiniPlayer() {
        this.playback.dispatch(this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.NONE, UiMetricAttributes$ActionType.EXPAND_PODCAST_MINI_PLAYER, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, this.playback.getMedia().mediaId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_MINI_PLAYER));
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
        if (e1.getY() - e2.getY() > 120.0f && Math.abs(velocityY) > 200.0f) {
            launchNowPlayingActivity();
            onClickedExpandMiniPlayer();
        }
        if (e1.getX() - e2.getX() > 120.0f && Math.abs(velocityX) > 200.0f) {
            handleDismissTranscriptView();
            this.playback.setTranscriptImplementation(null);
            this.playback.setHasFetchedTranscript(false);
            this.playback.next();
            return true;
        }
        if (e2.getX() - e1.getX() <= 120.0f || Math.abs(velocityX) <= 200.0f) {
            return true;
        }
        handleDismissTranscriptView();
        this.playback.setTranscriptImplementation(null);
        this.playback.setHasFetchedTranscript(false);
        this.playback.previous();
        return true;
    }

    private void handleDismissTranscriptView() {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.nowplaying.MiniPlayerView.2
            @Override // java.lang.Runnable
            public void run() {
                for (Playback.DismissFullScreenTranscriptCallback dismissFullScreenTranscriptCallback : Playback.getDismissFullScreenTranscriptCallbacks()) {
                    dismissFullScreenTranscriptCallback.onDismissTranscriptView();
                    MiniPlayerView.this.playback.removeDismissTranscriptViewCallback(dismissFullScreenTranscriptCallback);
                }
            }
        });
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        Playback playback = Podcast.getPlayback();
        Media media = playback.getMedia();
        if (visibility == 0) {
            bindMediaMetadata(media.getMediaMetadataElement());
            media.addMediaMetadataCallback(this);
            media.addMediaControlsCallback(this);
            playback.addStateCallback(this);
            playback.addPositionCallback(this);
            playback.addDurationCallback(this);
            return;
        }
        media.removeMediaMetadataCallback(this);
        media.removeMediaControlsCallback(this);
        playback.removeStateCallback(this);
        playback.removePositionCallback(this);
        playback.removeDurationCallback(this);
        playback.removeBufferedPositionCallback(this);
    }

    public void handleOnViewed() {
        this.playback.dispatch(this.ownerId, this.onViewed);
    }

    private static List<Method> miniPlayerOnViewedMethods() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.incrementalSyncOnViewed());
        return arrayList;
    }

    private void bindControls() {
        onPlaybackStateChange(this.playback.getPlaybackState());
        this.fastForward.setContentDescription(getResources().getString(R$string.podcast_fast_forward_label));
        this.playPause.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.MiniPlayerView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PodcastHapticsUtil.triggerHaptics();
                ChromecastMediaProvider.setPlaybackOnChromecastPaused(false);
                MiniPlayerView.this.playback.playPause();
            }
        });
        this.fastForward.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.MiniPlayerView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MiniPlayerView.this.playback.fastForward();
            }
        });
    }

    private void bindMediaMetadata(final MediaMetadataElement metadataElement) {
        if (metadataElement == null) {
            return;
        }
        onPlaybackStateChange(this.playback.getPlaybackState());
        String artwork = metadataElement.getArtwork();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.podcast_medium_icon_button);
        Picasso.get().load(artwork).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize(dimensionPixelSize, dimensionPixelSize).transform(this.roundedCornersTransformation).into(this.artwork);
        Picasso.get().load(artwork).placeholder(R$drawable.artwork_placeholder).transform(this.blurTransformation).into(this.background);
        this.progressBar.setMax(Long.valueOf(TimeUnit.SECONDS.toMillis(metadataElement.getDurationSeconds().longValue())).intValue());
        this.progressBar.setProgress((int) this.playback.getPlaybackPosition());
        this.title.setText(metadataElement.getTitle());
        this.subtitle.setText(metadataElement.getSubtitle());
    }

    public ImageView getPlayPause() {
        return this.playPause;
    }

    public ImageView getFastForward() {
        return this.fastForward;
    }

    public void setIsSingleTapFunctionalityEnabled(boolean status) {
        this.isSingleTapFunctionalityEnabled = status;
    }

    @Override // com.amazon.podcast.media.playback.Playback.BufferedPositionCallback
    public void onBufferedPositionChange(long position) {
        this.progressBar.setSecondaryProgress((int) position);
    }
}
