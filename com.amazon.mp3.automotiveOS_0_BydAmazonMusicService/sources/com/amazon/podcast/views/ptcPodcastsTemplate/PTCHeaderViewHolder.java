package com.amazon.podcast.views.ptcPodcastsTemplate;

import android.os.Build;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.views.EmberTextView;
import org.apache.commons.lang3.text.WordUtils;

/* JADX INFO: loaded from: classes5.dex */
final class PTCHeaderViewHolder extends RecyclerView.ViewHolder {
    private EmberTextView subtitleView;
    private EmberTextView titleView;

    public PTCHeaderViewHolder(final View view) {
        super(view);
        this.titleView = (EmberTextView) view.findViewById(R$id.ptc_header_title);
        RuntimeStyleSheet runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.titleView.setTypeface(runtimeStyleSheet.getHeadline2TypeFace());
        this.titleView.setTextSize(0, runtimeStyleSheet.getHeadline2TextSize());
        this.titleView.setAllCaps(runtimeStyleSheet.isHeadline2AllCaps());
        if (Build.VERSION.SDK_INT >= 28) {
            this.titleView.setLineHeight(runtimeStyleSheet.getHeadline2LineHeight());
        }
        this.subtitleView = (EmberTextView) view.findViewById(R$id.ptc_header_subtitle);
    }

    public final void bind(final String title, final String subtitle) {
        if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
            this.titleView.setText(WordUtils.capitalize(title));
        } else {
            this.titleView.setText(title);
        }
        this.subtitleView.setText(subtitle);
    }
}
