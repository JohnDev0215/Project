package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackCache;
import com.amazon.podcast.views.Accessibility;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.Strings;
import com.amazonaws.util.StringUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class PlayView extends ConstraintLayout implements Playback.StateCallback, Playback.PositionCallback, Playback.DurationCallback, Media.MediaMetadataCallback, Observer<Bookmark> {
    private String accessibilitySpacer;
    private BookmarkElement bookmarkElement;
    private LiveData<Bookmark> bookmarkLiveData;
    private Accessibility callback;
    private CompletedElement completedElement;
    private LiveData<Completed> completedLiveData;
    private long completedUpdatedTime;
    private String episodeName;
    private boolean isCompleted;
    private LifecycleOwner lifecycleOwner;
    private Logger logger;
    private List<Method> onPauseSelectedMethods;
    private List<Method> onPlaySelectedMethods;
    private List<Method> onResumeSelectedMethods;
    private ImageView playButton;
    private String playId;
    private Playback playback;
    private ProgressBar progressBar;
    private String publishDate;
    private EmberTextView remainingDuration;
    private String showName;

    public PlayView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.logger = LoggerFactory.getLogger((Class<?>) PlayView.class);
        this.isCompleted = false;
        this.completedUpdatedTime = 0L;
        this.publishDate = "";
        this.showName = "";
        this.accessibilitySpacer = ", ";
        init();
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        bindPlaybackState(this.playId, this.playback.getMedia().mediaId(), state, true, false);
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(final long position) {
        String strMediaId = this.playback.getMedia().mediaId();
        String str = this.playId;
        if (str == null || str.equals(strMediaId)) {
            bindProgress(this.playback.getPlaybackDuration(), position);
        }
    }

    @Override // com.amazon.podcast.media.playback.Playback.DurationCallback
    public void onPlaybackDurationChange(final long duration) {
        String strMediaId = this.playback.getMedia().mediaId();
        String str = this.playId;
        if (str == null || str.equals(strMediaId)) {
            bindProgress(duration, this.playback.getPlaybackPosition());
        }
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        if (metadataElement == null) {
            return;
        }
        String mediaId = metadataElement.getMediaId();
        bindPlaybackState(this.playId, mediaId, this.playback.getPlaybackState(), false, false);
        String str = this.playId;
        if (str != null && str.equals(mediaId)) {
            this.playback.addDurationCallback(this);
            this.playback.addStateCallback(this);
            bindCompletedLiveData();
        } else {
            removeCallbacks();
            this.completedLiveData.removeObservers(this.lifecycleOwner);
        }
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(final Bookmark bookmark) {
        bindBookmark(bookmark);
    }

    public void bind(final String playId, final String episodeName, final List<Method> onPlaySelectedMethods, final List<Method> onPauseSelectedMethods, final List<Method> onResumeSelectedMethods, final BookmarkElement bookmarkElement, final CompletedElement completedElement, final LifecycleOwner lifecycleOwner, final MethodsDispatcher methodsDispatcher, final String ownerId, final String publishDate, final String showName) {
        if (!StringUtils.isBlank(this.playId) && !this.playId.equals(playId)) {
            clearPlayView();
            removeCallbacks();
        }
        this.playId = playId;
        this.episodeName = episodeName;
        this.onPlaySelectedMethods = onPlaySelectedMethods;
        this.onPauseSelectedMethods = onPauseSelectedMethods;
        this.onResumeSelectedMethods = onResumeSelectedMethods;
        this.bookmarkElement = bookmarkElement;
        if (bookmarkElement == null) {
            return;
        }
        this.completedElement = completedElement;
        this.lifecycleOwner = lifecycleOwner;
        String strMediaId = this.playback.getMedia().mediaId();
        this.publishDate = publishDate;
        this.showName = showName;
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.PlayView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayView.this.handlePlayClick(methodsDispatcher, ownerId);
            }
        });
        this.playButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.PlayView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PodcastHapticsUtil.triggerHaptics();
                PlayView.this.handlePlayClick(methodsDispatcher, ownerId);
            }
        });
        this.bookmarkLiveData = Podcast.getAppSync().bookmark().readLive(getContext(), bookmarkElement.getId());
        this.completedLiveData = Podcast.getAppSync().completed().readLive(getContext(), completedElement.getId());
        bindCallbacks(strMediaId);
        bindPlaybackState(playId, strMediaId, this.playback.getPlaybackState(), false, true);
        this.playButton.setVisibility(0);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (this.playId == null || this.bookmarkElement == null) {
            return;
        }
        Media media = this.playback.getMedia();
        String strMediaId = media.mediaId();
        bindPlaybackState(this.playId, strMediaId, this.playback.getPlaybackState(), false, false);
        if (visibility == 0) {
            String str = this.playId;
            if (str != null && str.equals(strMediaId)) {
                this.playback.addDurationCallback(this);
                this.playback.addStateCallback(this);
                bindCompletedLiveData();
            }
            media.addMediaMetadataCallback(this);
            return;
        }
        this.playback.removeDurationCallback(this);
        this.playback.removePositionCallback(this);
        this.playback.removeStateCallback(this);
        this.completedLiveData.removeObservers(this.lifecycleOwner);
        media.removeMediaMetadataCallback(this);
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_episode_row_item_play_view, this);
        this.playButton = (ImageView) findViewById(R$id.podcast_episode_row_item_play_button);
        this.progressBar = (ProgressBar) findViewById(R$id.podcast_episode_row_item_circular_progress_bar);
        this.remainingDuration = (EmberTextView) findViewById(R$id.podcast_episode_row_item_remaining_duration);
        this.playback = Podcast.getPlayback();
    }

    private void clearPlayView() {
        this.bookmarkLiveData.removeObserver(this);
        this.completedLiveData.removeObservers(this.lifecycleOwner);
        this.remainingDuration.setVisibility(4);
        this.progressBar.setVisibility(4);
    }

    private void bindCallbacks(final String mediaId) {
        this.playback.getMedia().addMediaMetadataCallback(this);
        String str = this.playId;
        if (str == null || str.equals(mediaId)) {
            this.playback.addStateCallback(this);
            this.playback.addDurationCallback(this);
            bindCompletedLiveData();
        }
    }

    private void removeCallbacks() {
        Playback playback = this.playback;
        if (playback == null) {
            return;
        }
        playback.removeStateCallback(this);
        this.playback.removeDurationCallback(this);
        this.playback.removePositionCallback(this);
        if (this.playback.getMedia() == null) {
            return;
        }
        this.playback.getMedia().removeMediaMetadataCallback(this);
    }

    private void bindPlaybackState(final String playId, final String mediaId, final int state, final boolean onPlaybackStateChanged, final boolean isFirstBind) {
        GradientDrawable gradientDrawable = (GradientDrawable) ((RotateDrawable) this.progressBar.getProgressDrawable()).getDrawable();
        Playback playback = Podcast.getPlayback();
        String str = String.format(getResources().getString(R$string.podcast_play_episode_button), this.episodeName);
        if (playId != null && !playId.equals(mediaId)) {
            this.playButton.setImageResource(R$drawable.ic_playback_play_tiny);
            gradientDrawable.setColor(getResources().getColor(R$color.primary_glass_5));
            this.playButton.setContentDescription(str);
            removeCallbacks();
            this.bookmarkLiveData.observe(this.lifecycleOwner, this);
            return;
        }
        if (!onPlaybackStateChanged) {
            this.bookmarkLiveData.observe(this.lifecycleOwner, this);
        } else {
            this.bookmarkLiveData.removeObserver(this);
        }
        if (isFirstBind && state == 0) {
            this.playButton.setImageResource(R$drawable.ic_playback_play_tiny);
            gradientDrawable.setColor(getResources().getColor(R$color.primary_glass_5));
            this.playButton.setContentDescription(str);
        } else {
            if (state == 6 || state == 0) {
                return;
            }
            if (state == 3) {
                String str2 = String.format(getResources().getString(R$string.podcast_pause_episode_button), this.episodeName);
                this.playButton.setImageResource(R$drawable.ic_playback_pause_tiny);
                gradientDrawable.setColor(Podcast.getRuntimeStyleSheet().getAccentColor());
                this.playButton.setContentDescription(str2);
                playback.addPositionCallback(this);
                return;
            }
            this.playButton.setImageResource(R$drawable.ic_playback_play_tiny);
            gradientDrawable.setColor(getResources().getColor(R$color.primary_glass_5));
            this.playButton.setContentDescription(str);
        }
    }

    private void bindRemainingDurationText(final Long remainingDurationMilliseconds) {
        updateAccessibilityLabel(Strings.getRemainingDurationString(getResources(), remainingDurationMilliseconds.longValue()));
        this.remainingDuration.setText("");
        this.remainingDuration.setText(Strings.getTextWithRemainingDuration(getResources(), remainingDurationMilliseconds, this.remainingDuration));
        this.remainingDuration.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindTotalDurationText(final Long totalDurationMilliseconds) {
        updateAccessibilityLabel(Strings.getDurationString(getResources(), totalDurationMilliseconds.longValue()));
        this.remainingDuration.setText("");
        this.remainingDuration.setText(Strings.getTextWithTotalDuration(getResources(), totalDurationMilliseconds, this.remainingDuration));
        this.remainingDuration.setVisibility(0);
    }

    private void bindBookmark(final Bookmark bookmark) {
        if (bookmark == null) {
            if (this.bookmarkElement.getTotalDurationMilliseconds() == null) {
                return;
            }
            bindTotalDurationText(this.bookmarkElement.getTotalDurationMilliseconds());
        } else if (bookmark.getId().equals(this.bookmarkElement.getId())) {
            bindProgress(bookmark.getTotalDurationMilliseconds(), bookmark.getProgressMilliseconds());
        }
    }

    private void bindProgress(final long totalDurationMilliseconds, final long progressMilliseconds) {
        long j = totalDurationMilliseconds - progressMilliseconds;
        boolean z = progressMilliseconds == 0 && this.playback.isCompleted(this.playId);
        if (j <= 0 || z) {
            int i = (int) totalDurationMilliseconds;
            bindProgressIndicator(i, i);
            bindTotalDurationText(Long.valueOf(totalDurationMilliseconds));
            bindProgressCompleted();
            return;
        }
        bindProgressIndicator((int) totalDurationMilliseconds, (int) progressMilliseconds);
        if (progressMilliseconds == 0) {
            bindTotalDurationText(Long.valueOf(j));
        } else {
            bindRemainingDurationText(Long.valueOf(j));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindProgressIndicator(final int maxMilliseconds, final int progressMilliseconds) {
        this.progressBar.setMax(maxMilliseconds);
        this.progressBar.setProgress(progressMilliseconds);
        this.progressBar.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindProgressCompleted() {
        ((GradientDrawable) ((RotateDrawable) this.progressBar.getProgressDrawable()).getDrawable()).setColor(getResources().getColor(R$color.primary));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlayClick(final MethodsDispatcher methodsDispatcher, final String ownerId) {
        Playback playback = Podcast.getPlayback();
        if ((playback.getMedia().mediaId() == null ? "" : playback.getMedia().mediaId()).equals(this.bookmarkElement.getId())) {
            int playbackState = playback.getPlaybackState();
            if (playbackState == 2) {
                methodsDispatcher.dispatch(ownerId, this.onResumeSelectedMethods);
                return;
            } else if (playbackState == 3) {
                methodsDispatcher.dispatch(ownerId, this.onPauseSelectedMethods);
                return;
            } else {
                methodsDispatcher.dispatch(ownerId, this.onPlaySelectedMethods);
                return;
            }
        }
        methodsDispatcher.dispatch(ownerId, this.onPlaySelectedMethods);
    }

    private void bindCompletedLiveData() {
        this.completedLiveData.observe(this.lifecycleOwner, new Observer<Completed>() { // from class: com.amazon.podcast.views.episodeRowItemsListView.PlayView.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(Completed completed) {
                if (completed == null) {
                    return;
                }
                String strMediaId = PlayView.this.playback.getMedia().mediaId();
                int playbackState = PlayView.this.playback.getPlaybackState();
                if ((PlayView.this.playId == null || PlayView.this.playId.equals(strMediaId)) && playbackState != 3) {
                    if (!completed.getCompleted()) {
                        PlayView.this.isCompleted = false;
                        PlayView.this.completedUpdatedTime = completed.getUpdatedTime();
                    } else {
                        if (PlayView.this.isCompleted || PlayView.this.completedUpdatedTime <= 0 || !completed.getCompleted()) {
                            return;
                        }
                        long durationMilliseconds = PlaybackCache.get(completed.getId()).getDurationMilliseconds();
                        int i = (int) durationMilliseconds;
                        PlayView.this.bindProgressIndicator(i, i);
                        PlayView.this.bindTotalDurationText(Long.valueOf(durationMilliseconds));
                        PlayView.this.bindProgressCompleted();
                        PlayView.this.isCompleted = true;
                        PlayView.this.completedUpdatedTime = completed.getUpdatedTime();
                    }
                }
            }
        });
    }

    private void updateAccessibilityLabel(final String remainingDuration) {
        long progress = this.progressBar.getProgress();
        long jLongValue = this.bookmarkElement.getTotalDurationMilliseconds().longValue();
        String str = this.publishDate + this.accessibilitySpacer + this.episodeName + this.accessibilitySpacer;
        if (progress >= jLongValue) {
            str = str + getResources().getString(R$string.podcast_played_button) + this.accessibilitySpacer;
        }
        if (!StringUtils.isBlank(this.showName)) {
            str = str + String.format(getResources().getString(R$string.podcast_now_playing_title_text), this.showName) + this.accessibilitySpacer;
        }
        if (progress == 0) {
            str = str + getResources().getString(R$string.podcast_episode_length_button) + this.accessibilitySpacer;
        }
        this.callback.onAccessibilityLabelChange(str + remainingDuration + this.accessibilitySpacer + getResources().getString(R$string.podcast_view_episode_details_button));
    }

    public void setAccessibilityCallback(final Accessibility accCallBack) {
        this.callback = accCallBack;
    }
}
