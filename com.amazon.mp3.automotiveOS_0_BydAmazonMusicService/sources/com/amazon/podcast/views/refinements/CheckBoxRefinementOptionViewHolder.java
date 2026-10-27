package com.amazon.podcast.views.refinements;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
class CheckBoxRefinementOptionViewHolder extends RecyclerView.ViewHolder {
    private final EmberTextView defaultLabel;
    private final EmberTextView primaryText;
    private final ImageView radioCheckBox;

    public CheckBoxRefinementOptionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.primaryText = (EmberTextView) itemView.findViewById(R$id.podcast_refinement_option_primary_text);
        this.defaultLabel = (EmberTextView) itemView.findViewById(R$id.podcast_refinement_option_tertiary_text);
        this.radioCheckBox = (ImageView) itemView.findViewById(R$id.podcast_refinement_option_check_box);
    }

    public void bind(final String text, final boolean isPopUp, final boolean isDefault, final boolean isSelected) {
        this.primaryText.setText(text);
        this.defaultLabel.setVisibility(isDefault ? 0 : 8);
        if (isSelected) {
            this.radioCheckBox.setImageResource(R$drawable.ic_action_radiocheck);
            this.radioCheckBox.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
        } else {
            this.radioCheckBox.setImageResource(R$drawable.ic_action_radiouncheck);
            this.radioCheckBox.setColorFilter(this.itemView.getResources().getColor(R$color.primary));
        }
        if (isPopUp) {
            this.primaryText.setMaxLines(1);
            this.primaryText.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    public void setOnClickListener(final View.OnClickListener clickListener) {
        this.itemView.setOnClickListener(clickListener);
    }
}
