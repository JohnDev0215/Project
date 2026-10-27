package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.MessageElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class MessageViewHolder extends RecyclerView.ViewHolder {
    private final MessageView messageView;

    public MessageViewHolder(final MessageView messageView) {
        super(messageView);
        this.messageView = messageView;
    }

    public void bind(final MessageElement element) {
        this.messageView.bind(element);
    }
}
