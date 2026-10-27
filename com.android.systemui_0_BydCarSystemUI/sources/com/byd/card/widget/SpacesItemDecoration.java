package com.byd.card.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.utils.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private static final int LAND_SPAN_COUNT = 8;
    private static final int PORT_SPAN_COUNT = 4;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (AppUtils.isLandSpace(view.getContext())) {
            int i = childAdapterPosition % 8;
            return;
        }
        int i2 = childAdapterPosition % 4;
        int dimension = (int) view.getContext().getResources().getDimension(R.dimen.dp_16);
        outRect.left = dimension - ((i2 * dimension) / 4);
        outRect.right = ((i2 + 1) * dimension) / 4;
        outRect.top = (int) view.getContext().getResources().getDimension(R.dimen.dp_12);
    }
}
