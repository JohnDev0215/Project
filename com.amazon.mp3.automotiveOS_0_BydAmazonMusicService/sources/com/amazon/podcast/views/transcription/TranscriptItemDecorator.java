package com.amazon.podcast.views.transcription;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class TranscriptItemDecorator extends RecyclerView.ItemDecoration {
    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        if (childAdapterPosition == 0) {
            outRect.set(0, getEdgePadding(view, parent, true), 0, 0);
        } else if (itemCount != 0 && childAdapterPosition == itemCount - 1) {
            outRect.set(0, 0, 0, getEdgePadding(view, parent, false));
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }

    private final int getEdgePadding(final View view, final RecyclerView parent, final boolean isTop) {
        view.measure(0, 0);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int i = ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin;
        int i2 = ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
        int measuredHeight = view.getMeasuredHeight() / 2;
        int height = parent.getHeight() / 2;
        if (isTop) {
            return Math.max(0, height - (measuredHeight + i));
        }
        return Math.max(0, height - (measuredHeight + i2));
    }
}
