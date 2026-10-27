package com.amazon.podcast.nowplaying;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackSpeed;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class PlaybackSpeedSelectionView extends RelativeLayout implements Media.MediaMetadataCallback {
    private static final String TAG;
    private static final Logger logger;
    private ImageView background;
    private BlurTransformation blurTransformation;
    private ListView listView;
    private String ownerId;
    private Playback playback;
    private EmberTextView playbackSpeedDismiss;
    private List<String> stringifiedSpeedMultipliers;

    static {
        String simpleName = PlaybackSpeedSelectionView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public PlaybackSpeedSelectionView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_now_playing_playback_speed_template, this);
        this.blurTransformation = new BlurTransformation(getContext());
        this.playback = Podcast.getPlayback();
        this.background = (ImageView) findViewById(R$id.now_playing_playback_speed_background);
        this.playbackSpeedDismiss = (EmberTextView) findViewById(R$id.dismiss);
        this.listView = (ListView) findViewById(R$id.playback_speed_options);
        this.stringifiedSpeedMultipliers = stringifyMultipliers();
        this.listView.setAdapter((ListAdapter) generateAdapter());
        addMultiplierClickListener();
        this.playback.getMedia().addMediaMetadataCallback(this);
    }

    public void bindBackground(final MediaMetadataElement mediaMetadataElement) {
        if (mediaMetadataElement == null) {
            return;
        }
        this.background.setVisibility(0);
        Picasso.get().load(mediaMetadataElement.getArtwork()).placeholder(R$drawable.artwork_placeholder).transform(this.blurTransformation).into(this.background);
    }

    public void setDismissClickedListener(final View.OnClickListener listener) {
        this.playbackSpeedDismiss.setOnClickListener(listener);
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
            media.addMediaMetadataCallback(this);
        } else {
            this.background.setVisibility(4);
            this.playback.getMedia().removeMediaMetadataCallback(this);
        }
    }

    private List<String> stringifyMultipliers() {
        ArrayList arrayList = new ArrayList();
        for (PlaybackSpeed playbackSpeed : PlaybackSpeed.values()) {
            arrayList.add(playbackSpeed.getDisplayText());
        }
        return arrayList;
    }

    private void addMultiplierClickListener() {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.amazon.podcast.nowplaying.PlaybackSpeedSelectionView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
                PlaybackSpeedSelectionView.this.playback.setPlaybackSpeed(PlaybackSpeed.values()[position]);
                PlaybackSpeedSelectionView.this.listView.setAdapter((ListAdapter) PlaybackSpeedSelectionView.this.generateAdapter());
                PlaybackSpeedSelectionView.this.playbackSpeedDismiss.performClick();
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_PLAYBACK_SPEED, UiMetricAttributes$ActionType.SELECT_PODCAST_PLAYBACK_SPEED, UiMetricAttributes$ContentName.PODCASTS_PLAYBACK_SPEED));
                PlaybackSpeedSelectionView.this.playback.dispatch(PlaybackSpeedSelectionView.this.ownerId, arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayAdapter<String> generateAdapter() {
        return new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_1, this.stringifiedSpeedMultipliers) { // from class: com.amazon.podcast.nowplaying.PlaybackSpeedSelectionView.2
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(final int position, final View convertView, final ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.getLayoutParams().height = PlaybackSpeedSelectionView.this.getResources().getDimensionPixelSize(R$dimen.podcast_playback_speed_item_height);
                if (PlaybackSpeedSelectionView.this.playback.getPlaybackSpeed() == PlaybackSpeed.values()[position]) {
                    ((TextView) view).setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
                } else {
                    ((TextView) view).setTextColor(PlaybackSpeedSelectionView.this.getResources().getColor(R$color.primary));
                }
                return view;
            }
        };
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
