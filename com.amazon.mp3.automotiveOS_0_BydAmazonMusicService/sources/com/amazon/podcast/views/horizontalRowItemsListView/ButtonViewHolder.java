package com.amazon.podcast.views.horizontalRowItemsListView;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.ButtonElement;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$id;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class ButtonViewHolder extends RecyclerView.ViewHolder {
    private EmberTextView button;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public ButtonViewHolder(final View itemView, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(itemView);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.button = (EmberTextView) itemView.findViewById(R$id.button_item);
    }

    public void bind(final ButtonElement element) {
        this.button.setText(element.getText());
        this.button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.horizontalRowItemsListView.ButtonViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ButtonViewHolder.this.methodsDispatcher.dispatch(ButtonViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
    }
}
