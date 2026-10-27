package com.amazon.mp3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;

/* JADX INFO: loaded from: classes4.dex */
public class DownloadButtonView extends ImageView {
    private Context mContext;
    private MusicTrack mTrack;

    public DownloadButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.DownloadButtonView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DownloadButtonView.this.mTrack != null) {
                    new AddAndDownloadTrackTask(DownloadButtonView.this.mContext, PrimeTrack.fromTrack(DownloadButtonView.this.mTrack), true, true, (OnItemFinishedAddingListener) null).execute(new Void[0]);
                    MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.DOWNLOAD_TRACK_BUTTON).withInteractionType(InteractionType.TAP).withEntityId(DownloadButtonView.this.mTrack.getAsin()).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).build());
                }
            }
        });
    }

    public void setTrack(MusicTrack track) {
        this.mTrack = track;
    }
}
