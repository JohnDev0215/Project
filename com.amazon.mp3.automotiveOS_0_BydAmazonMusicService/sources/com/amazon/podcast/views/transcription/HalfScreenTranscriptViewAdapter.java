package com.amazon.podcast.views.transcription;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.scrublib.models.ContentType;
import com.amazon.scrublib.models.ScrubSegment;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class HalfScreenTranscriptViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

    /* JADX INFO: renamed from: com.amazon.podcast.views.transcription.HalfScreenTranscriptViewAdapter$1, reason: invalid class name */
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

    public View getView(TranscriptContentType type, ViewGroup parent) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$podcast$views$transcription$TranscriptContentType[type.ordinal()];
        if (i2 == 1) {
            i = R$layout.podcast_half_screen_transcript_row_item;
        } else {
            i = i2 != 2 ? 0 : R$layout.podcast_untranscribed_text;
        }
        return LayoutInflater.from(this.context).inflate(i, parent, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
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

    public class TranscribedViewHolder extends RecyclerView.ViewHolder {
        public ImageView equalizer;
        private final AnimationDrawable equalizerDrawable;
        public TextView transcript;
        public TranscriptCaptionContainer transcriptCaptionContainer;

        public TranscribedViewHolder(@NonNull TranscriptCaptionContainer transcriptCaptionContainer) {
            super(transcriptCaptionContainer);
            this.transcriptCaptionContainer = transcriptCaptionContainer;
            this.transcript = (TextView) this.itemView.findViewById(R$id.transcript_text);
            if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
                this.equalizer = (ImageView) this.itemView.findViewById(R$id.refresh_half_screen_transcript_equalizer);
            } else {
                this.equalizer = (ImageView) this.itemView.findViewById(R$id.half_screen_transcript_equalizer);
            }
            this.equalizer.setVisibility(0);
            this.equalizerDrawable = (AnimationDrawable) this.equalizer.getDrawable();
        }

        public void bind(final ScrubSegment scrubSegment, final OnItemClickListener itemClickListener, final int position) {
            String caption = scrubSegment.getCaption();
            boolean zEquals = scrubSegment.getContentType().name().equals(ContentType.UNTRANSCRIBED_CONTENT.name());
            if ((!HalfScreenTranscriptViewAdapter.this.untranscribedPosition.isEmpty() && HalfScreenTranscriptViewAdapter.this.adPosition == -1 && HalfScreenTranscriptViewAdapter.this.untranscribedPosition.contains(Integer.valueOf(position))) && StringUtils.isNotBlank(caption)) {
                bindTranscribedText(caption, position);
            } else if (zEquals || StringUtils.isBlank(caption)) {
                bindUntranscribedText(position);
            } else {
                bindTranscribedText(caption, position);
            }
            this.transcript.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptViewAdapter.TranscribedViewHolder.1
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
            this.transcript.setText(HalfScreenTranscriptViewAdapter.this.context.getResources().getString(R$string.content_not_transcribed));
            if (HalfScreenTranscriptViewAdapter.this.currentPosition != postion) {
                this.transcript.setTextColor(HalfScreenTranscriptViewAdapter.this.context.getResources().getColor(R$color.dark_gray));
                this.transcript.setTypeface(Typeface.DEFAULT);
                hideEqualizer();
            } else {
                this.transcript.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
                this.transcript.setTypeface(Typeface.DEFAULT_BOLD);
                startEqualizer();
            }
        }

        private void bindTranscribedText(final String caption, final int position) {
            hideEqualizer();
            this.transcript.setText(caption);
            if (HalfScreenTranscriptViewAdapter.this.currentPosition == position) {
                this.transcript.setTextColor(HalfScreenTranscriptViewAdapter.this.context.getResources().getColor(R$color.white));
                this.transcript.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                this.transcript.setTextColor(HalfScreenTranscriptViewAdapter.this.context.getResources().getColor(R$color.dark_gray));
                this.transcript.setTypeface(Typeface.DEFAULT);
            }
        }

        private void startEqualizer() {
            AnimationDrawable animationDrawable = this.equalizerDrawable;
            if (animationDrawable != null) {
                animationDrawable.start();
            }
            this.equalizer.setVisibility(0);
        }

        private void hideEqualizer() {
            AnimationDrawable animationDrawable = this.equalizerDrawable;
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
            this.equalizer.setVisibility(8);
        }
    }

    private class UntranscribedViewHolder extends RecyclerView.ViewHolder {
        public ImageView equalizer;
        private final AnimationDrawable equalizerDrawable;
        public TranscriptCaptionContainer transcriptCaptionContainer;
        public TextView untranscribedText;

        public UntranscribedViewHolder(@NonNull TranscriptCaptionContainer transcriptCaptionContainer) {
            super(transcriptCaptionContainer);
            this.untranscribedText = (TextView) this.itemView.findViewById(R$id.untranscribed_text);
            this.transcriptCaptionContainer = transcriptCaptionContainer;
            ImageView imageView = (ImageView) this.itemView.findViewById(R$id.full_screen_untranscribe_equalizer);
            this.equalizer = imageView;
            this.equalizerDrawable = (AnimationDrawable) imageView.getDrawable();
        }

        public void bind(int position) {
            this.untranscribedText.setText(HalfScreenTranscriptViewAdapter.this.context.getResources().getString(R$string.content_not_transcribed));
            if (position == HalfScreenTranscriptViewAdapter.this.currentPosition) {
                startEqualizer();
                this.untranscribedText.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
                this.untranscribedText.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                hideEqualizer();
                this.untranscribedText.setTextColor(HalfScreenTranscriptViewAdapter.this.context.getResources().getColor(R$color.dark_grey));
                this.untranscribedText.setTypeface(Typeface.DEFAULT);
            }
            ((ScrubSegment) HalfScreenTranscriptViewAdapter.this.transcriptValue.get(position)).setContentType(ContentType.UNTRANSCRIBED_CONTENT);
            HalfScreenTranscriptViewAdapter.this.untranscribedPosition.add(Integer.valueOf(position));
        }

        private void startEqualizer() {
            AnimationDrawable animationDrawable = this.equalizerDrawable;
            if (animationDrawable != null) {
                animationDrawable.start();
            }
            this.equalizer.setVisibility(0);
        }

        private void hideEqualizer() {
            AnimationDrawable animationDrawable = this.equalizerDrawable;
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
            this.equalizer.setVisibility(8);
        }
    }

    public HalfScreenTranscriptViewAdapter(final List<ScrubSegment> transcriptData, final OnItemClickListener listener, final Context context) {
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
        float dimension = this.context.getResources().getDimension(R$dimen.half_screen_transcript_caption_spacing);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            int i = ((int) dimension) / 2;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = i;
        }
    }
}
