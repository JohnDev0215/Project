package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PlayButtonElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.views.EmberTextView;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
final class PTCBottomSheetPlayView extends LinearLayout implements Media.MediaMetadataCallback, Playback.StateCallback, Playback.DurationCallback, Playback.PositionCallback {
    private Boolean callbacksAdded;
    private EmberTextView durationText;
    private Boolean isInvokeDispatched;
    private boolean isSeeking;
    private boolean isTrailerPlayed;
    private ImageView playButtonImage;
    private String playId;
    private LinearLayout playLayout;
    private ImageView playPauseButton;
    private EmberTextView playTrailerTextView;
    private RelativeLayout playTrailerWholeButtonLayout;
    private Playback playback;
    private EmberTextView progressText;
    private SeekBar seekBar;
    private LinearLayout seekBarLayout;

    public PTCBottomSheetPlayView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        Boolean bool = Boolean.FALSE;
        this.isInvokeDispatched = bool;
        this.callbacksAdded = bool;
        this.isSeeking = false;
        this.isTrailerPlayed = false;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.ptc_bottom_sheet_play_trailer_view, this);
        this.playLayout = (LinearLayout) findViewById(R$id.ptc_bottom_sheet_playbutton_layout);
        this.playTrailerTextView = (EmberTextView) findViewById(R$id.ptc_trailer_button_text);
        this.playTrailerWholeButtonLayout = (RelativeLayout) findViewById(R$id.ptc_trailer_play_button_layout);
        this.playButtonImage = (ImageView) findViewById(R$id.ptc_trailer_button_image);
        this.playPauseButton = (ImageView) findViewById(R$id.ptc_trailer_pause_button);
        this.progressText = (EmberTextView) findViewById(R$id.podcast_progress_text);
        this.durationText = (EmberTextView) findViewById(R$id.podcast_duration_text);
        this.seekBarLayout = (LinearLayout) findViewById(R$id.ptc_trailer_seek_bar_layout);
        this.seekBar = (SeekBar) findViewById(R$id.podcast_ptc_bottomsheet_seekbar);
        this.playback = Podcast.getPlayback();
    }

    public void bind(final PlayButtonElement playButtonElement, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        this.playId = playButtonElement.getMediaId();
        this.playTrailerTextView.setText(playButtonElement.getText());
        this.playTrailerTextView.setVisibility(0);
        this.playTrailerWholeButtonLayout.setVisibility(0);
        this.playLayout.setVisibility(0);
        addSeekBarTouchListener();
        addSeekBarListener();
        this.playback.getMedia().addMediaMetadataCallback(this);
        this.playTrailerWholeButtonLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheetPlayView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!PTCBottomSheetPlayView.this.isInvokeDispatched.booleanValue()) {
                    methodsDispatcher.dispatch(ownerId, playButtonElement.getOnItemSelected());
                }
                PTCBottomSheetPlayView pTCBottomSheetPlayView = PTCBottomSheetPlayView.this;
                pTCBottomSheetPlayView.bindCallbacks(pTCBottomSheetPlayView.playId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindCallbacks(final String playId) {
        if (playId == null || this.playback.getMedia() == null || !playId.equals(this.playback.getMedia().mediaId())) {
            return;
        }
        if (!this.callbacksAdded.booleanValue()) {
            this.playback.addStateCallback(this);
            this.playback.addDurationCallback(this);
            this.playback.addPositionCallback(this);
            this.callbacksAdded = Boolean.TRUE;
        }
        if (this.isInvokeDispatched.booleanValue()) {
            this.playback.playPause();
        }
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        if (metadataElement == null) {
            return;
        }
        String mediaId = metadataElement.getMediaId();
        String str = this.playId;
        if (str != null && str.equals(mediaId)) {
            this.playback.addDurationCallback(this);
            this.playback.addStateCallback(this);
            this.playback.addPositionCallback(this);
            this.callbacksAdded = Boolean.TRUE;
            return;
        }
        removePlaybackCallbacks();
        this.callbacksAdded = Boolean.FALSE;
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        if (state == 3) {
            this.playButtonImage.setVisibility(8);
            this.playPauseButton.setImageResource(R$drawable.ic_playback_pause);
            this.playPauseButton.setContentDescription(getResources().getString(R$string.podcast_pause_label));
            this.seekBarLayout.setVisibility(0);
            this.durationText.setText(String.format("%s", formatTime(100L)));
            return;
        }
        if (!this.isInvokeDispatched.booleanValue()) {
            this.playTrailerTextView.setVisibility(8);
            this.playButtonImage.setVisibility(8);
            this.isInvokeDispatched = Boolean.TRUE;
            this.isTrailerPlayed = true;
        }
        this.playPauseButton.setImageResource(R$drawable.ic_playback_play);
        this.playPauseButton.setVisibility(0);
        this.playPauseButton.setContentDescription(getResources().getString(R$string.podcast_play_label));
    }

    @Override // com.amazon.podcast.media.playback.Playback.DurationCallback
    public void onPlaybackDurationChange(final long duration) {
        this.seekBar.setMax((int) duration);
        long playbackPosition = duration - this.playback.getPlaybackPosition();
        this.seekBar.setProgress((int) this.playback.getPlaybackPosition());
        this.durationText.setText(String.format("%s", formatTime(Long.valueOf(playbackPosition))));
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(final long position) {
        this.seekBar.setProgress((int) position);
        this.progressText.setText(formatTime(Long.valueOf(position)));
        this.durationText.setText(String.format("%s", formatTime(Long.valueOf(((long) this.seekBar.getMax()) - position))));
    }

    public void addSeekBarListener() {
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheetPlayView.2
            private int prevProgress;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                if (!fromUser || seekBar.getMax() == 0) {
                    return;
                }
                if (!PTCBottomSheetPlayView.this.playback.isHasFetchedTranscript()) {
                    PTCBottomSheetPlayView.this.playback.seekTo(seekBar.getProgress());
                }
                String time = PTCBottomSheetPlayView.this.formatTime(new Long(progress));
                String time2 = PTCBottomSheetPlayView.this.formatTime(new Long(seekBar.getMax() - progress));
                PTCBottomSheetPlayView.this.progressText.setText(time);
                PTCBottomSheetPlayView.this.durationText.setText(String.format("%s", time2));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(final SeekBar seekBar) {
                this.prevProgress = seekBar.getProgress();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(final SeekBar seekBar) {
                if (PTCBottomSheetPlayView.this.isSeeking) {
                    PTCBottomSheetPlayView.this.isSeeking = false;
                } else {
                    seekBar.setProgress(this.prevProgress);
                }
                PTCBottomSheetPlayView.this.playback.seekTo(seekBar.getProgress());
            }
        });
    }

    private void addSeekBarTouchListener() {
        this.seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheetPlayView.3
            private float initialY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action != 0) {
                    if (action == 1) {
                        PTCBottomSheetPlayView.this.isSeeking = this.initialY - event.getY() < 120.0f;
                    }
                } else {
                    this.initialY = event.getY();
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatTime(final Long timeInMillis) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(timeInMillis.longValue());
        long jLongValue = timeInMillis.longValue();
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(jLongValue - timeUnit2.toMillis(hours));
        long seconds = timeUnit.toSeconds((timeInMillis.longValue() - timeUnit2.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        if (hours > 0) {
            return String.format("%d:%02d:%02d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds));
        }
        if (seconds < 0) {
            return String.format("-%d:%02d", Long.valueOf(minutes), Long.valueOf(Math.abs(seconds)));
        }
        return String.format("%d:%02d", Long.valueOf(minutes), Long.valueOf(seconds));
    }

    void stopTrailerPlaybackOnAboutDismiss() {
        String strMediaId = this.playback.getMedia().mediaId();
        String str = this.playId;
        if (str == null || !str.equals(strMediaId)) {
            return;
        }
        removePlaybackCallbacks();
        if (this.playback.getPlaybackState() != 3 && this.playback.getPlaybackState() != 6) {
            this.playback.setDispatchOnStoppedMethods(false);
        }
        this.playback.stop();
        this.playback.clearLockScreen();
    }

    private void removePlaybackCallbacks() {
        this.playback.getMedia().removeMediaMetadataCallback(this);
        this.playback.removeDurationCallback(this);
        this.playback.removePositionCallback(this);
        this.playback.removeStateCallback(this);
    }

    final boolean getIsTrailerPlayed() {
        return this.isTrailerPlayed;
    }
}
