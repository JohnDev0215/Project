package com.amazon.podcast.nowplaying;

import Podcast.PlaybackInterface.v1_0.MediaControlsElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
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
    private ImageButton fastForward;
    private ImageButton playPause;
    private Playback playback;
    private EmberTextView playbackSpeed;
    private ImageButton rewind;
    private ImageButton sleepTimer;

    public TransportView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
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

    public void setSpeedButtonClickedListener(final View.OnClickListener listener) {
        this.playbackSpeed.setOnClickListener(listener);
    }

    public void setSleepTimerButtonClickedListener(final View.OnClickListener listener) {
        this.sleepTimer.setOnClickListener(listener);
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

    private void init() {
        View.inflate(getContext(), R$layout.podcast_transport_view, this);
        this.sleepTimer = (ImageButton) findViewById(R$id.podcast_sleep_timer);
        this.playPause = (ImageButton) findViewById(R$id.podcast_playpause);
        this.playback = Podcast.getPlayback();
        this.fastForward = (ImageButton) findViewById(R$id.podcast_fast_forward);
        this.rewind = (ImageButton) findViewById(R$id.podcast_rewind);
        this.playbackSpeed = (EmberTextView) findViewById(R$id.podcast_speed);
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
            this.playPause.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.TransportView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PodcastHapticsUtil.triggerHaptics();
                    TransportView.this.playback.playPause();
                }
            });
            this.fastForward.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.TransportView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TransportView.this.playback.fastForward();
                }
            });
            this.rewind.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.TransportView.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TransportView.this.playback.rewind();
                }
            });
        }
    }
}
