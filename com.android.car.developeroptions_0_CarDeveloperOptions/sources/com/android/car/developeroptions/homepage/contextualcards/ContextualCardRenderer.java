package com.android.car.developeroptions.homepage.contextualcards;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public interface ContextualCardRenderer {
    void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard);

    RecyclerView.ViewHolder createViewHolder(View view, int i);
}
