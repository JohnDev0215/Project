package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElement;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$id;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class CompactPillItemViewHolder extends RecyclerView.ViewHolder {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final EmberTextView text;

    public CompactPillItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(view);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.text = (EmberTextView) view.findViewById(R$id.compact_pill_item);
    }

    public void bind(final CompactPillItemElement element) {
        this.text.setText(element.getText());
        this.text.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.CompactPillItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CompactPillItemViewHolder.this.methodsDispatcher.dispatch(CompactPillItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
    }
}
