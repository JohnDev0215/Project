package com.android.systemui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.core.text.TextUtilsCompat;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerSpace extends RecyclerView.ItemDecoration {
    private int bottom;
    private int left;
    private int right;
    private int top;

    public RecyclerSpace(int bottom) {
        this.bottom = bottom;
    }

    public RecyclerSpace(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = this.left;
        outRect.top = this.top;
        outRect.right = this.right;
        outRect.bottom = this.bottom;
        if (TextUtilsCompat.getLayoutDirectionFromLocale(view.getContext().getResources().getConfiguration().locale) == 1) {
            outRect.right = outRect.left ^ outRect.right;
            outRect.left ^= outRect.right;
            outRect.right = outRect.left ^ outRect.right;
        }
    }
}
