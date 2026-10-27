package com.amazon.podcast.views.galleryTemplate;

import android.os.Build;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.views.EmberTextView;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
final class HeaderViewHolder extends RecyclerView.ViewHolder {
    private final EmberTextView headerText;
    private final ConstraintLayout headerView;
    private final EmberTextView subheaderText;

    public HeaderViewHolder(final View itemView) {
        super(itemView);
        this.headerView = (ConstraintLayout) itemView.findViewById(R$id.gallery_template_header);
        EmberTextView emberTextView = (EmberTextView) itemView.findViewById(R$id.gallery_template_header_text);
        this.headerText = emberTextView;
        RuntimeStyleSheet runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        emberTextView.setTypeface(runtimeStyleSheet.getHeadline2TypeFace());
        emberTextView.setTextSize(0, runtimeStyleSheet.getHeadline2TextSize());
        emberTextView.setAllCaps(runtimeStyleSheet.isHeadline2AllCaps());
        if (Build.VERSION.SDK_INT >= 28) {
            emberTextView.setLineHeight(runtimeStyleSheet.getHeadline2LineHeight());
        }
        this.subheaderText = (EmberTextView) itemView.findViewById(R$id.gallery_template_subheader_text);
    }

    public final void bind(final String header, final String subheader) {
        if (StringUtils.isBlank(header) && StringUtils.isBlank(subheader)) {
            this.headerView.setVisibility(8);
        } else {
            setHeader(header);
            setSubheader(subheader);
        }
    }

    private void setHeader(final String header) {
        if (StringUtils.isBlank(header)) {
            return;
        }
        this.headerText.setVisibility(0);
        this.headerText.setContentDescription(header);
        this.headerText.setText(header);
    }

    private void setSubheader(final String subheader) {
        if (StringUtils.isBlank(subheader)) {
            return;
        }
        this.subheaderText.setVisibility(0);
        this.subheaderText.setContentDescription(subheader);
        this.subheaderText.setText(subheader);
    }
}
