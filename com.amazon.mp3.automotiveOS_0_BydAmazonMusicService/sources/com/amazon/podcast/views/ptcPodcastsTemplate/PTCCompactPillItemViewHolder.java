package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.CompactPillItemElement;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.R$id;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class PTCCompactPillItemViewHolder extends RecyclerView.ViewHolder {
    private EmberTextView text;

    public PTCCompactPillItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.text = (EmberTextView) itemView.findViewById(R$id.compact_pill_item);
    }

    public void bind(final CompactPillItemElement element) {
        this.text.setText(element.getText());
    }
}
