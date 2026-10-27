package com.amazon.music.curate.skyfire.views.galleryTemplate;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.account.SonicRushFeature;
import com.amazon.music.curate.R;
import com.amazon.music.curate.skyfire.views.EmberTextView;

/* JADX INFO: loaded from: classes4.dex */
final class HeaderViewHolder extends RecyclerView.ViewHolder {
    private final EmberTextView header;

    public HeaderViewHolder(final View itemView) {
        super(itemView);
        this.header = (EmberTextView) itemView.findViewById(R.id.library_header_text);
    }

    public void bind(final String text) {
        if (text == null) {
            this.header.setVisibility(8);
            return;
        }
        this.header.setVisibility(0);
        this.header.setContentDescription(text);
        this.header.setText(text);
        if (new SonicRushFeature().isEnabled()) {
            this.header.applyFontChangesAsPerSizeKey();
        }
    }

    public EmberTextView getHeaderView() {
        return this.header;
    }
}
