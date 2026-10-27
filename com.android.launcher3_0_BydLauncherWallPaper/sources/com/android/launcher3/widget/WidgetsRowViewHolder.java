package com.android.launcher3.widget;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.R;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetsRowViewHolder extends RecyclerView.ViewHolder {
    public final ViewGroup cellContainer;
    public final BubbleTextView title;

    public WidgetsRowViewHolder(ViewGroup v) {
        super(v);
        this.cellContainer = (ViewGroup) v.findViewById(R.id.widgets_cell_list);
        BubbleTextView bubbleTextView = (BubbleTextView) v.findViewById(R.id.section);
        this.title = bubbleTextView;
        bubbleTextView.setAccessibilityDelegate(null);
    }
}
