package com.android.systemui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.core.text.TextUtilsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class RecentRecyclerSpace extends RecyclerView.ItemDecoration {
    private int bottom;
    private int left;
    private int right;
    private int top;

    public RecentRecyclerSpace(int bottom) {
        this.bottom = bottom;
    }

    public RecentRecyclerSpace(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        BydLogHelper.logD("positionSlit:" + childAdapterPosition);
        if (childAdapterPosition == 0 || childAdapterPosition == 1) {
            outRect.left = this.left;
            outRect.top = this.top;
            outRect.right = this.right;
            outRect.bottom = this.bottom;
        } else {
            outRect.left = 0;
            outRect.top = this.top;
            outRect.right = this.right;
            outRect.bottom = this.bottom;
        }
        if (TextUtilsCompat.getLayoutDirectionFromLocale(view.getContext().getResources().getConfiguration().locale) == 1) {
            outRect.right = outRect.left ^ outRect.right;
            outRect.left ^= outRect.right;
            outRect.right = outRect.left ^ outRect.right;
        }
    }
}
