package com.amazon.podcast.nowplayingstage;

import Podcast.PlaybackInterface.v1_0.MediaControlsElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$anim;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.cast.Casting;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackSpeed;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class TransportView extends LinearLayout implements Media.MediaControlsCallback, Playback.StateCallback, Playback.SpeedCallback, Playback.SleepTimerOnOffCallback {
    private CastingConnectionCallback callback;
    private ImageView castingButton;
    private ImageButton fastForward;
    public ImageView overflowButton;
    private ImageButton playPause;
    private Playback playback;
    private EmberTextView playbackSpeed;
    private ImageButton rewind;
    private ImageButton shareButton;
    private ImageButton sleepTimer;

    public TransportView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.callback = new CastingConnectionCallback() { // from class: com.amazon.podcast.nowplayingstage.TransportView.1
            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onConnected(CastingDevice castingDevice) {
                TransportView.this.castingButton.setImageDrawable(TransportView.this.getResources().getDrawable(R$drawable.ic_stage_caston));
                TransportView.this.castingButton.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onDisconnected(TerminationReason terminationReason) {
                TransportView.this.castingButton.setImageDrawable(TransportView.this.getResources().getDrawable(R$drawable.ic_stage_castoff));
                TransportView.this.castingButton.setColorFilter(TransportView.this.getResources().getColor(R$color.primary));
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onError(CastingException e) {
                TransportView.this.castingButton.setImageDrawable(TransportView.this.getResources().getDrawable(R$drawable.ic_stage_castoff));
                TransportView.this.castingButton.setColorFilter(TransportView.this.getResources().getColor(R$color.primary));
            }
        };
        init();
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaControlsCallback
    public void onMediaControlsChange(final MediaControlsElement controlsElement) {
        bindControls(controlsElement);
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        if (this.playPause.getAnimation() != null) {
            this.playPause.getAnimation().cancel();
        }
        if (state == 3) {
            this.playPause.setContentDescription(getResources().getString(R$string.podcast_pause_label));
            this.playPause.setImageResource(R$drawable.ic_playback_pause);
            this.playPause.setColorFilter(getResources().getColor(R$color.primary));
            return;
        }
        if (state == 2 || state == 7 || state == 8) {
            this.playPause.setContentDescription(getResources().getString(R$string.podcast_play_label));
            this.playPause.setImageResource(R$drawable.ic_playback_play);
            this.playPause.setColorFilter(getResources().getColor(R$color.primary));
            return;
        }
        this.playPause.setImageResource(R$drawable.ic_other_loader);
        this.playPause.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
        this.playPause.startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.podcast_loading_spinner));
    }

    @Override // com.amazon.podcast.media.playback.Playback.SpeedCallback
    public void onPlaybackSpeedChange(final PlaybackSpeed speed) {
        this.playbackSpeed.setText(speed.getIconText());
        if (speed == PlaybackSpeed.ONE) {
            this.playbackSpeed.setTextColor(getResources().getColor(R$color.primary));
        } else {
            this.playbackSpeed.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        }
    }

    @Override // com.amazon.podcast.media.playback.Playback.SleepTimerOnOffCallback
    public void onSleepTimerOn() {
        this.sleepTimer.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
    }

    @Override // com.amazon.podcast.media.playback.Playback.SleepTimerOnOffCallback
    public void onSleepTimerOff() {
        this.sleepTimer.setColorFilter(getResources().getColor(R$color.primary));
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        Media media = this.playback.getMedia();
        if (visibility == 0) {
            bindControls(media.getMediaControlsElement());
            media.addMediaControlsCallback(this);
            this.playback.addSpeedCallback(this);
            this.playback.addStateCallback(this);
            this.playback.addSleepTimerStateCallback(this);
            return;
        }
        media.removeMediaControlsCallback(this);
        this.playback.removeSpeedCallback(this);
        this.playback.removeStateCallback(this);
        this.playback.removeSleepTimerStateCallback(this);
    }

    public void setSpeedButtonClickedListener(final View.OnClickListener listener) {
        this.playbackSpeed.setOnClickListener(listener);
    }

    public void setSleepTimerButtonClickedListener(final View.OnClickListener listener) {
        this.sleepTimer.setOnClickListener(listener);
    }

    public void setCastingButtonClickedListener(final View.OnClickListener listener) {
        if (Podcast.isFireOS()) {
            this.castingButton.setVisibility(8);
            return;
        }
        this.castingButton.setVisibility(0);
        if (Casting.isCasting()) {
            this.castingButton.setImageDrawable(getResources().getDrawable(R$drawable.ic_stage_caston));
            this.castingButton.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
        } else {
            this.castingButton.setImageDrawable(getResources().getDrawable(R$drawable.ic_stage_castoff));
            this.castingButton.setColorFilter(getResources().getColor(R$color.primary));
        }
        this.castingButton.setOnClickListener(listener);
    }

    public void setShareButtonClickedListener(final View.OnClickListener listener) {
        this.shareButton.setContentDescription(getResources().getString(R$string.podcast_share_episode_prompt));
        this.shareButton.setOnClickListener(listener);
        this.shareButton.setVisibility(0);
    }

    public void setOverflowButtonClickedListener(final View.OnClickListener listener) {
        this.overflowButton.setOnClickListener(listener);
        this.overflowButton.setVisibility(0);
        this.overflowButton.setContentDescription(getResources().getString(R$string.podcast_overflow_button));
    }

    public void registerCastingListener() {
        CastingSessionManager.getInstance().registerListener(this.callback);
    }

    public void unregisterCastingListener() {
        CastingSessionManager.getInstance().unregisterListener(this.callback);
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_transport_stage_view, this);
        this.sleepTimer = (ImageButton) findViewById(R$id.podcast_sleep_timer);
        this.playPause = (ImageButton) findViewById(R$id.podcast_playpause);
        this.playback = Podcast.getPlayback();
        this.fastForward = (ImageButton) findViewById(R$id.podcast_fast_forward);
        this.rewind = (ImageButton) findViewById(R$id.podcast_rewind);
        this.playbackSpeed = (EmberTextView) findViewById(R$id.podcast_speed);
        this.castingButton = (ImageView) findViewById(R$id.now_playing_casting_button);
        this.shareButton = (ImageButton) findViewById(R$id.now_playing_share_button);
        this.overflowButton = (ImageView) findViewById(R$id.now_playing_overflow_button);
        this.playback.getMedia().addMediaControlsCallback(this);
        this.playback.addStateCallback(this);
        this.playback.addSpeedCallback(this);
        this.playback.addSleepTimerStateCallback(this);
        bindControls(this.playback.getMedia().getMediaControlsElement());
    }

    private void bindControls(final MediaControlsElement controlsElement) {
        if (controlsElement != null) {
            if (Casting.isCasting()) {
                this.playbackSpeed.setEnabled(false);
            } else {
                this.playbackSpeed.setEnabled(true);
            }
            onPlaybackStateChange(this.playback.getPlaybackState());
            this.sleepTimer.setContentDescription(getResources().getString(R$string.podcast_sleep_timer_label));
            if (this.playback.isSleepTimerOn()) {
                this.sleepTimer.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
            } else {
                this.sleepTimer.setColorFilter(getResources().getColor(R$color.primary));
            }
            this.rewind.setContentDescription(getResources().getString(R$string.podcast_rewind_label));
            this.playPause.setContentDescription(getResources().getString(R$string.podcast_pause_label));
            this.fastForward.setContentDescription(getResources().getString(R$string.podcast_fast_forward_label));
            this.playbackSpeed.setContentDescription(getResources().getString(R$string.podcast_playback_speed_label));
            onPlaybackSpeedChange(this.playback.getPlaybackSpeed());
            this.playPause.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.TransportView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PodcastHapticsUtil.triggerHaptics();
                    TransportView.this.playback.playPause();
                }
            });
            this.fastForward.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.TransportView.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TransportView.this.playback.fastForward();
                }
            });
            this.rewind.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.TransportView.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TransportView.this.playback.rewind();
                }
            });
        }
    }
}
