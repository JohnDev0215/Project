package com.amazon.podcast.nowplayingstage.overlays;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.views.transcription.TranscriptCaptionContainer;
import com.amazon.podcast.views.transcription.TranscriptContentType;
import com.amazon.scrublib.models.ContentType;
import com.amazon.scrublib.models.ScrubSegment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class TranscriptPreviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int adPosition;
    private Context context;
    private int currentPosition;
    private final OnItemClickListener onItemClickListener;
    private List<ScrubSegment> transcriptValue;
    private List<Integer> untranscribedPosition = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(ScrubSegment scrubSegment);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        RecyclerView.ViewHolder transcribedViewHolder;
        TranscriptContentType transcriptContentType = TranscriptContentType.values()[viewType];
        View view = getView(transcriptContentType, viewGroup);
        int i = AnonymousClass1.$SwitchMap$com$amazon$podcast$views$transcription$TranscriptContentType[transcriptContentType.ordinal()];
        if (i == 1) {
            transcribedViewHolder = new TranscribedViewHolder((TranscriptCaptionContainer) view);
        } else {
            if (i != 2) {
                return null;
            }
            transcribedViewHolder = new UntranscribedViewHolder((TranscriptCaptionContainer) view);
        }
        return transcribedViewHolder;
    }

    /* JADX INFO: renamed from: com.amazon.podcast.nowplayingstage.overlays.TranscriptPreviewAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$podcast$views$transcription$TranscriptContentType;

        static {
            int[] iArr = new int[TranscriptContentType.values().length];
            $SwitchMap$com$amazon$podcast$views$transcription$TranscriptContentType = iArr;
            try {
                iArr[TranscriptContentType.TRANSCRIBED_CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$podcast$views$transcription$TranscriptContentType[TranscriptContentType.UNTRANSCRIBED_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public View getView(final TranscriptContentType type, final ViewGroup parent) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$podcast$views$transcription$TranscriptContentType[type.ordinal()];
        if (i2 == 1) {
            i = R$layout.transcript_overlay_row_item;
        } else {
            i = i2 != 2 ? 0 : R$layout.transcript_overlay_untranscribed;
        }
        return LayoutInflater.from(this.context).inflate(i, parent, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$podcast$views$transcription$TranscriptContentType[TranscriptContentType.values()[getItemViewType(position)].ordinal()];
        if (i == 1) {
            if (viewHolder instanceof TranscribedViewHolder) {
                ((TranscribedViewHolder) viewHolder).bind(this.transcriptValue.get(position), this.onItemClickListener, position);
            }
        } else if (i == 2 && (viewHolder instanceof UntranscribedViewHolder)) {
            ((UntranscribedViewHolder) viewHolder).bind(position);
        }
        setVerticalMarginForItemView(viewHolder.itemView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        if (this.adPosition == position && !Podcast.getPlayback().isSeekBarTracking()) {
            return TranscriptContentType.UNTRANSCRIBED_CONTENT.ordinal();
        }
        return TranscriptContentType.TRANSCRIBED_CONTENT.ordinal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.transcriptValue.size();
    }

    public class TranscribedViewHolder extends RecyclerView.ViewHolder implements Playback.StateCallback {
        public ImageView equalizer;
        private final Drawable pausedDrawable;
        private final AnimationDrawable playingDrawable;
        public TextView timestamp;
        public TextView transcript;
        public TranscriptCaptionContainer transcriptCaptionContainer;

        public TranscribedViewHolder(@NonNull final TranscriptCaptionContainer transcriptCaptionContainer) {
            super(transcriptCaptionContainer);
            this.transcriptCaptionContainer = transcriptCaptionContainer;
            this.transcript = (TextView) this.itemView.findViewById(R$id.transcript_text);
            this.timestamp = (TextView) this.itemView.findViewById(R$id.transcript_timestamp);
            this.equalizer = (ImageView) this.itemView.findViewById(R$id.transcript_equalizer);
            if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
                this.pausedDrawable = AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.ic_transcript_refresh_equalizeroff);
                this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.refresh_podcast_transcript_equalizer_primary);
            } else {
                this.pausedDrawable = AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.ic_transcript_equalizeroff);
                this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.podcast_transcript_equalizer_primary);
            }
            this.equalizer.setVisibility(0);
            this.equalizer.setImageDrawable(this.pausedDrawable);
        }

        public void bind(final ScrubSegment scrubSegment, final OnItemClickListener itemClickListener, final int position) {
            String caption = scrubSegment.getCaption();
            boolean zEquals = scrubSegment.getContentType().name().equals(ContentType.UNTRANSCRIBED_CONTENT.name());
            if ((!TranscriptPreviewAdapter.this.untranscribedPosition.isEmpty() && TranscriptPreviewAdapter.this.adPosition == -1 && TranscriptPreviewAdapter.this.untranscribedPosition.contains(Integer.valueOf(position))) && StringUtils.isNotBlank(caption)) {
                bindTranscribedText(caption, position);
            } else if (zEquals || StringUtils.isBlank(caption)) {
                bindUntranscribedText(position);
            } else {
                bindTranscribedText(caption, position);
            }
            this.timestamp.setText(String.valueOf(TranscriptPreviewAdapter.this.formatTime(Long.valueOf(scrubSegment.getStartTime()))));
            this.transcript.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.overlays.TranscriptPreviewAdapter.TranscribedViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OnItemClickListener onItemClickListener = itemClickListener;
                    if (onItemClickListener == null) {
                        return;
                    }
                    onItemClickListener.onItemClick(scrubSegment);
                }
            });
        }

        private void bindUntranscribedText(final int postion) {
            this.transcript.setText(TranscriptPreviewAdapter.this.context.getResources().getString(R$string.content_not_transcribed));
            this.transcript.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
            if (TranscriptPreviewAdapter.this.currentPosition == postion) {
                this.transcript.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
                this.timestamp.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
                startEqualizer();
            } else {
                TextView textView = this.transcript;
                Resources resources = TranscriptPreviewAdapter.this.context.getResources();
                int i = R$color.primary_glass_4;
                textView.setTextColor(resources.getColor(i));
                this.timestamp.setTextColor(TranscriptPreviewAdapter.this.context.getResources().getColor(i));
                hideEqualizer();
            }
        }

        private void bindTranscribedText(final String caption, final int position) {
            hideEqualizer();
            this.transcript.setText(caption);
            this.transcript.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
            if (TranscriptPreviewAdapter.this.currentPosition == position) {
                TextView textView = this.transcript;
                Resources resources = TranscriptPreviewAdapter.this.context.getResources();
                int i = R$color.primary;
                textView.setTextColor(resources.getColor(i));
                this.timestamp.setTextColor(TranscriptPreviewAdapter.this.context.getResources().getColor(i));
                return;
            }
            TextView textView2 = this.transcript;
            Resources resources2 = TranscriptPreviewAdapter.this.context.getResources();
            int i2 = R$color.primary_glass_4;
            textView2.setTextColor(resources2.getColor(i2));
            this.timestamp.setTextColor(TranscriptPreviewAdapter.this.context.getResources().getColor(i2));
        }

        private void startEqualizer() {
            Podcast.getPlayback().addStateCallback(this);
            if (Podcast.getPlayback().getPlaybackState() == 3) {
                this.equalizer.setImageDrawable(this.playingDrawable);
                this.playingDrawable.start();
            } else {
                this.equalizer.setImageDrawable(this.pausedDrawable);
            }
            this.equalizer.setVisibility(0);
        }

        private void hideEqualizer() {
            Podcast.getPlayback().removeStateCallback(this);
            this.equalizer.setVisibility(8);
        }

        @Override // com.amazon.podcast.media.playback.Playback.StateCallback
        public void onPlaybackStateChange(int state) {
            if (state == 3) {
                this.equalizer.setImageDrawable(this.playingDrawable);
                this.playingDrawable.start();
            } else {
                this.equalizer.setImageDrawable(this.pausedDrawable);
            }
        }
    }

    private class UntranscribedViewHolder extends RecyclerView.ViewHolder implements Playback.StateCallback {
        public ImageView equalizer;
        private final Drawable pausedDrawable;
        private final AnimationDrawable playingDrawable;
        public TranscriptCaptionContainer transcriptCaptionContainer;
        public TextView untranscribedText;

        public UntranscribedViewHolder(@NonNull final TranscriptCaptionContainer transcriptCaptionContainer) {
            super(transcriptCaptionContainer);
            this.untranscribedText = (TextView) this.itemView.findViewById(R$id.untranscribed_text);
            this.equalizer = (ImageView) this.itemView.findViewById(R$id.transcript_untranscribed_equalizer);
            this.transcriptCaptionContainer = transcriptCaptionContainer;
            if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
                this.pausedDrawable = AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.ic_transcript_refresh_equalizeroff);
                this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.refresh_podcast_transcript_equalizer_primary);
            } else {
                this.pausedDrawable = AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.ic_transcript_equalizeroff);
                this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(TranscriptPreviewAdapter.this.context, R$drawable.podcast_transcript_equalizer_primary);
            }
            this.equalizer.setVisibility(0);
            this.equalizer.setImageDrawable(this.pausedDrawable);
        }

        public void bind(final int position) {
            this.untranscribedText.setText(TranscriptPreviewAdapter.this.context.getResources().getString(R$string.content_not_transcribed));
            this.untranscribedText.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
            if (position == TranscriptPreviewAdapter.this.currentPosition) {
                startEqualizer();
                this.untranscribedText.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
            } else {
                hideEqualizer();
                this.untranscribedText.setTextColor(TranscriptPreviewAdapter.this.context.getResources().getColor(R$color.primary_glass_4));
            }
            ((ScrubSegment) TranscriptPreviewAdapter.this.transcriptValue.get(position)).setContentType(ContentType.UNTRANSCRIBED_CONTENT);
            TranscriptPreviewAdapter.this.untranscribedPosition.add(Integer.valueOf(position));
        }

        private void startEqualizer() {
            Podcast.getPlayback().addStateCallback(this);
            if (Podcast.getPlayback().getPlaybackState() == 3) {
                this.equalizer.setImageDrawable(this.playingDrawable);
                this.playingDrawable.start();
            } else {
                this.equalizer.setImageDrawable(this.pausedDrawable);
            }
            this.equalizer.setVisibility(0);
        }

        private void hideEqualizer() {
            Podcast.getPlayback().removeStateCallback(this);
            this.equalizer.setVisibility(8);
        }

        @Override // com.amazon.podcast.media.playback.Playback.StateCallback
        public void onPlaybackStateChange(int state) {
            if (state == 3) {
                this.equalizer.setImageDrawable(this.playingDrawable);
                this.playingDrawable.start();
            } else {
                this.equalizer.setImageDrawable(this.pausedDrawable);
            }
        }
    }

    public TranscriptPreviewAdapter(final List<ScrubSegment> transcriptData, final OnItemClickListener listener, final Context context) {
        this.transcriptValue = transcriptData;
        this.onItemClickListener = listener;
        this.context = context;
    }

    public void setCurrentPosition(int index) {
        this.currentPosition = index;
        notifyDataSetChanged();
    }

    public void setCurrentPositionAndAdPosition(final int currentPosition) {
        this.currentPosition = currentPosition;
        this.adPosition = currentPosition;
        notifyDataSetChanged();
    }

    public void setAdIndex(final int adPosition) {
        this.adPosition = adPosition;
        notifyDataSetChanged();
    }

    public void setTranscript(List<ScrubSegment> transcriptValue) {
        this.transcriptValue = transcriptValue;
        notifyDataSetChanged();
    }

    private void setVerticalMarginForItemView(View view) {
        if (view == null) {
            return;
        }
        float dimension = this.context.getResources().getDimension(R$dimen.podcast_spacer_giant);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = (int) dimension;
        }
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
}
