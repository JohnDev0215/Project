package com.amazon.podcast.nowplayingstage;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.SleepTimerOffsetOption;
import com.amazon.podcast.media.playback.SleepTimerOption;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
final class SleepTimerSelectionView extends RelativeLayout implements Media.MediaMetadataCallback, Playback.SleepTimerOnOffCallback, Playback.SleepTimerDurationCallback {
    private ImageView background;
    private BlurTransformation blurTransformation;
    private View divider;
    private EmberTextView endOfEpisodeSleepTimer;
    private ListView listView;
    private Playback playback;
    private EmberTextView sleepTimerDismiss;
    private EmberTextView title;
    private EmberTextView turnOffSleepTimer;

    public SleepTimerSelectionView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_now_playing_stage_sleep_timer_template, this);
        this.blurTransformation = new BlurTransformation(getContext());
        this.playback = Podcast.getPlayback();
        this.background = (ImageView) findViewById(R$id.podcast_now_playing_sleep_timer_background);
        this.sleepTimerDismiss = (EmberTextView) findViewById(R$id.podcast_sleep_timer_dismiss);
        this.title = (EmberTextView) findViewById(R$id.podcast_sleep_timer_title);
        this.turnOffSleepTimer = (EmberTextView) findViewById(R$id.podcast_sleep_timer_active_turn_off);
        this.listView = (ListView) findViewById(R$id.podcast_sleep_timer_options);
        this.divider = findViewById(R$id.podcast_sleep_timer_active_turn_off_divider);
        this.endOfEpisodeSleepTimer = (EmberTextView) findViewById(R$id.podcast_sleep_timer_end_of_episode);
        if (this.playback.isSleepTimerOn()) {
            bindSleepTimerOn();
        } else {
            bindSleepTimerOff();
        }
        addEndOfEpisodeClickListener();
        this.playback.getMedia().addMediaMetadataCallback(this);
        this.playback.addSleepTimerStateCallback(this);
    }

    public void setDismissClickedListener(final View.OnClickListener listener) {
        this.sleepTimerDismiss.setOnClickListener(listener);
    }

    @Override // com.amazon.podcast.media.playback.Playback.SleepTimerDurationCallback
    public void onSleepTimerDurationChange(final long timeLeft) {
        this.title.setText(String.format(getResources().getString(R$string.podcast_sleep_timer_active_title), formatTime(Long.valueOf(timeLeft))));
    }

    @Override // com.amazon.podcast.media.playback.Playback.SleepTimerOnOffCallback
    public void onSleepTimerOn() {
        this.listView.setVisibility(0);
        this.turnOffSleepTimer.setVisibility(0);
        this.divider.setVisibility(0);
        this.endOfEpisodeSleepTimer.setVisibility(8);
        if (this.playback.isSleepTimerForEndOfEpisode()) {
            String string = getResources().getString(R$string.podcast_sleep_timer_active_title);
            String string2 = getResources().getString(R$string.podcast_playback_sleep_timer_end_of_episode);
            this.listView.setVisibility(8);
            this.title.setText(String.format(string, string2));
        } else {
            setSleepTimerActiveOptions();
            addDeltaDurationClickListener();
        }
        setTurnOffSleepTimerClickListener();
        this.playback.addSleepTimerDurationCallback(this);
    }

    @Override // com.amazon.podcast.media.playback.Playback.SleepTimerOnOffCallback
    public void onSleepTimerOff() {
        this.listView.setVisibility(0);
        this.turnOffSleepTimer.setVisibility(8);
        this.divider.setVisibility(8);
        this.endOfEpisodeSleepTimer.setVisibility(0);
        this.title.setText(getResources().getString(R$string.podcast_playback_sleep_timer_selection_title));
        setSleepTimerInactiveOptions();
        addDurationClickListener();
        this.playback.removeSleepTimerDurationCallback(this);
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        bindBackground(metadataElement);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (visibility == 0) {
            Media media = this.playback.getMedia();
            bindBackground(media.getMediaMetadataElement());
            if (this.playback.isSleepTimerOn()) {
                bindSleepTimerOn();
            } else {
                bindSleepTimerOff();
            }
            media.addMediaMetadataCallback(this);
            this.playback.addSleepTimerStateCallback(this);
            return;
        }
        this.background.setVisibility(4);
        this.playback.getMedia().removeMediaMetadataCallback(this);
        this.playback.removeSleepTimerStateCallback(this);
        this.playback.removeSleepTimerDurationCallback(this);
    }

    private void bindBackground(final MediaMetadataElement mediaMetadataElement) {
        if (mediaMetadataElement == null) {
            return;
        }
        this.background.setVisibility(0);
        Picasso.get().load(mediaMetadataElement.getArtwork()).placeholder(R$drawable.artwork_placeholder).transform(this.blurTransformation).into(this.background);
    }

    private List<String> stringifyDurations() {
        ArrayList arrayList = new ArrayList();
        Resources resources = getResources();
        for (SleepTimerOption sleepTimerOption : SleepTimerOption.values()) {
            arrayList.add(String.format(resources.getString(sleepTimerOption.getResourceId()), Integer.valueOf(sleepTimerOption.getValue())));
        }
        return arrayList;
    }

    private void setTurnOffSleepTimerClickListener() {
        this.turnOffSleepTimer.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.SleepTimerSelectionView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                SleepTimerSelectionView.this.playback.stopSleepTimer();
                SleepTimerSelectionView.this.sleepTimerDismiss.performClick();
            }
        });
    }

    private void addDurationClickListener() {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.amazon.podcast.nowplayingstage.SleepTimerSelectionView.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
                SleepTimerSelectionView.this.playback.setSleepTimer(SleepTimerOption.values()[position].getValueMs());
                SleepTimerSelectionView.this.sleepTimerDismiss.performClick();
            }
        });
    }

    private void addEndOfEpisodeClickListener() {
        this.endOfEpisodeSleepTimer.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.SleepTimerSelectionView.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                SleepTimerSelectionView.this.playback.setSleepTimerToEndOfEpisode();
                SleepTimerSelectionView.this.sleepTimerDismiss.performClick();
            }
        });
    }

    private void setSleepTimerActiveOptions() {
        final List<String> listStringifyDeltaDurations = stringifyDeltaDurations();
        this.listView.setAdapter((ListAdapter) new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_1, listStringifyDeltaDurations) { // from class: com.amazon.podcast.nowplayingstage.SleepTimerSelectionView.4
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(final int position, final View convertView, final ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.getLayoutParams().height = SleepTimerSelectionView.this.getResources().getDimensionPixelSize(R$dimen.podcast_sleep_timer_duration_item_height);
                view.setPadding(0, 0, 0, 0);
                view.setContentDescription((CharSequence) listStringifyDeltaDurations.get(position));
                return view;
            }
        });
    }

    private void setSleepTimerInactiveOptions() {
        final List<String> listStringifyDurations = stringifyDurations();
        this.listView.setAdapter((ListAdapter) new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_1, listStringifyDurations) { // from class: com.amazon.podcast.nowplayingstage.SleepTimerSelectionView.5
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(final int position, final View convertView, final ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.getLayoutParams().height = SleepTimerSelectionView.this.getResources().getDimensionPixelSize(R$dimen.podcast_sleep_timer_duration_item_height);
                view.setPadding(0, 0, 0, 0);
                view.setContentDescription((CharSequence) listStringifyDurations.get(position));
                return view;
            }
        });
    }

    private List<String> stringifyDeltaDurations() {
        ArrayList arrayList = new ArrayList();
        Resources resources = getResources();
        for (SleepTimerOffsetOption sleepTimerOffsetOption : SleepTimerOffsetOption.values()) {
            arrayList.add(String.format(resources.getString(sleepTimerOffsetOption.getResourceId()), Integer.valueOf(sleepTimerOffsetOption.getValue())));
        }
        return arrayList;
    }

    private void addDeltaDurationClickListener() {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.amazon.podcast.nowplayingstage.SleepTimerSelectionView.6
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
                SleepTimerOffsetOption sleepTimerOffsetOption = SleepTimerOffsetOption.values()[position];
                if (sleepTimerOffsetOption == SleepTimerOffsetOption.ADD_FIVE_MINUTES) {
                    SleepTimerSelectionView.this.playback.addSleepTime(sleepTimerOffsetOption.getValueMs());
                } else {
                    SleepTimerSelectionView.this.playback.subtractSleepTime(sleepTimerOffsetOption.getValueMs());
                }
                SleepTimerSelectionView.this.sleepTimerDismiss.performClick();
            }
        });
    }

    private void bindSleepTimerOn() {
        this.listView.setVisibility(0);
        this.turnOffSleepTimer.setVisibility(0);
        this.divider.setVisibility(0);
        this.endOfEpisodeSleepTimer.setVisibility(8);
        if (this.playback.isSleepTimerForEndOfEpisode()) {
            String string = getResources().getString(R$string.podcast_sleep_timer_active_title);
            String string2 = getResources().getString(R$string.podcast_playback_sleep_timer_end_of_episode);
            this.listView.setVisibility(8);
            this.title.setText(String.format(string, string2));
        } else {
            setSleepTimerActiveOptions();
            addDeltaDurationClickListener();
        }
        setTurnOffSleepTimerClickListener();
        this.playback.addSleepTimerDurationCallback(this);
    }

    private void bindSleepTimerOff() {
        this.listView.setVisibility(0);
        this.turnOffSleepTimer.setVisibility(8);
        this.divider.setVisibility(8);
        this.endOfEpisodeSleepTimer.setVisibility(0);
        this.title.setText(getResources().getString(R$string.podcast_playback_sleep_timer_selection_title));
        setSleepTimerInactiveOptions();
        addDurationClickListener();
        this.playback.removeSleepTimerDurationCallback(this);
    }

    private String formatTime(final Long timeInMillis) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(timeInMillis.longValue());
        long jLongValue = timeInMillis.longValue();
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(jLongValue - timeUnit2.toMillis(hours));
        long seconds = timeUnit.toSeconds((timeInMillis.longValue() - timeUnit2.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        if (hours > 0) {
            return String.format("%d:%02d:%02d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds));
        }
        return String.format("%d:%02d", Long.valueOf(minutes), Long.valueOf(seconds));
    }
}
