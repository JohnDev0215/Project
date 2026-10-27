package com.amazon.podcast.views.descriptiveShowcase;

import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;

/* JADX INFO: loaded from: classes5.dex */
public class DescriptiveShowcaseElementViewHolder extends RecyclerView.ViewHolder {
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private DescriptiveShowcaseElementView view;

    public DescriptiveShowcaseElementViewHolder(final DescriptiveShowcaseElementView view, final MethodsDispatcher methodsDispatcher, final String ownerId) {
        super(view);
        this.view = view;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
    }

    public void bind(final DescriptiveShowcaseElement descriptiveShowcaseElement) {
        if (descriptiveShowcaseElement == null) {
            return;
        }
        this.view.setMethodsDispatcher(this.methodsDispatcher);
        this.view.setOwnerId(this.ownerId);
        this.view.bind(descriptiveShowcaseElement);
    }
}
