package com.amazon.mp3.playback.view.lyrics;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsItemDecoration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J \u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "()V", "getEdgePadding", "", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "isTop", "", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "getMaskAlpha", "", "itemPosition", "centerPosition", "onDraw", "c", "Landroid/graphics/Canvas;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsItemDecoration extends RecyclerView.ItemDecoration {
    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        super.onDraw(c, parent, state);
        float height = parent.getHeight() / 2.0f;
        for (View view : ViewGroupKt.getChildren(parent)) {
            if (view instanceof LyricsLineContainer) {
                ((LyricsLineContainer) view).setMaskAlpha(getMaskAlpha((r1.getTop() + r1.getBottom()) / 2.0f, height));
                view.invalidate();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = parent.getAdapter();
        Integer numValueOf = adapter == null ? null : Integer.valueOf(adapter.getItemCount());
        if (childAdapterPosition == 0) {
            outRect.set(0, getEdgePadding(view, parent, true), 0, 0);
        } else if (numValueOf != null && childAdapterPosition == numValueOf.intValue() - 1) {
            outRect.set(0, 0, 0, getEdgePadding(view, parent, false));
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }

    private final int getEdgePadding(View view, RecyclerView parent, boolean isTop) {
        view.measure(0, 0);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int i = ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
        view.getPaddingTop();
        view.getPaddingBottom();
        view.getMeasuredHeight();
        int height = (parent.getHeight() / 2) - (i / 2);
        if (isTop) {
            return Math.max(0, (height - ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin) - view.getPaddingTop());
        }
        return Math.max(0, (height - ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin) - view.getPaddingBottom());
    }

    private final float getMaskAlpha(float itemPosition, float centerPosition) {
        if (centerPosition == 0.0f) {
            return 1.0f;
        }
        return 1 - (Math.abs(itemPosition - centerPosition) / centerPosition);
    }
}
