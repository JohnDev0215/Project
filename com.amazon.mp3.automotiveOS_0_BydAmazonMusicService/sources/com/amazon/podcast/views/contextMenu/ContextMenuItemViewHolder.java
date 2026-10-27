package com.amazon.podcast.views.contextMenu;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
class ContextMenuItemViewHolder extends RecyclerView.ViewHolder {
    private final ImageView imageView;
    private final ImageView rightCaret;
    private final EmberTextView textView;

    public ContextMenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView.findViewById(R$id.podcast_context_menu_item_image);
        this.textView = (EmberTextView) itemView.findViewById(R$id.podcast_context_menu_item_text);
        this.rightCaret = (ImageView) itemView.findViewById(R$id.podcast_context_menu_item_chevron_caret_right);
    }

    public void bindImage(final int image) {
        this.imageView.setImageResource(image);
    }

    public void bindText(final String text, final boolean isPopUp) {
        this.textView.setText(text);
        if (isPopUp) {
            this.textView.setMaxLines(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    public void bindClickerListener(final View.OnClickListener clickListener) {
        this.itemView.setOnClickListener(clickListener);
    }

    public void showRightCaret() {
        this.rightCaret.setImageResource(R$drawable.ic_chevron_caretright);
        this.rightCaret.setVisibility(0);
    }
}
