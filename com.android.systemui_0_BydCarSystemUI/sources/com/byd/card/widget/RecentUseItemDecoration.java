package com.byd.card.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.utils.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public class RecentUseItemDecoration extends RecyclerView.ItemDecoration {
    private static final int LAND_SPAN_COUNT = 3;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (AppUtils.isLandSpace(view.getContext())) {
            int i = childAdapterPosition % 3;
            outRect.right = (int) view.getContext().getResources().getDimension(R.dimen.dp_8);
            if (childAdapterPosition == 0 || 1 == childAdapterPosition || 2 == childAdapterPosition) {
                outRect.top = (int) view.getContext().getResources().getDimension(R.dimen.dp_32);
                return;
            } else {
                outRect.top = (int) view.getContext().getResources().getDimension(R.dimen.dp_24);
                return;
            }
        }
        outRect.right = (int) view.getContext().getResources().getDimension(R.dimen.dp_32);
    }
}
