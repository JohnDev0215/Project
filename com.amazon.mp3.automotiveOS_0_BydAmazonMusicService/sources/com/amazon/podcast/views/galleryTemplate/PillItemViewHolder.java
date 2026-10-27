package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.PillItemElementInterface.v1_0.PillItemElement;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$id;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class PillItemViewHolder extends RecyclerView.ViewHolder {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final EmberTextView text;

    public PillItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(view);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.text = (EmberTextView) view.findViewById(R$id.pill_item);
    }

    public void bind(final PillItemElement element) {
        this.text.setText(element.getText());
        this.text.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.PillItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PillItemViewHolder.this.methodsDispatcher.dispatch(PillItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
    }
}
