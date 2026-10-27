package com.amazon.podcast.views.followsTemplate;

import Podcast.FollowInterface.v1_0.FooterElement;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.views.PTCWidgetView;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCWidgetViewHolder extends RecyclerView.ViewHolder {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final PTCWidgetView ptcView;

    public PTCWidgetViewHolder(final PTCWidgetView view, final MethodsDispatcher methodsDispatcher, final String ownerId) {
        super(view);
        this.methodsDispatcher = methodsDispatcher;
        this.ownerId = ownerId;
        this.ptcView = view;
    }

    public void bind(final FooterElement element, final boolean isViewClickable) {
        this.ptcView.bind(element.getText(), new View.OnClickListener() { // from class: com.amazon.podcast.views.followsTemplate.PTCWidgetViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                PTCWidgetViewHolder.this.methodsDispatcher.dispatch(PTCWidgetViewHolder.this.ownerId, element.getOnItemSelected());
            }
        }, isViewClickable);
    }
}
