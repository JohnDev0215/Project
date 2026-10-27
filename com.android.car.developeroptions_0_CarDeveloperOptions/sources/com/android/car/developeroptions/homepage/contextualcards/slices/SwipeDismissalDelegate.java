package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.slices.SliceFullCardRendererHelper;
import com.android.car.developeroptions.homepage.contextualcards.slices.SliceHalfCardRendererHelper;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissalDelegate extends ItemTouchHelper.Callback {
    private final Listener mListener;

    public interface Listener {
        void onSwiped(int i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return false;
    }

    public SwipeDismissalDelegate(Listener listener) {
        this.mListener = listener;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        switch (viewHolder.getItemViewType()) {
            case R.layout.contextual_slice_full_tile /* 2131558500 */:
            case R.layout.contextual_slice_half_tile /* 2131558501 */:
                if (viewHolder.itemView.findViewById(R.id.dismissal_view).getVisibility() != 0) {
                    break;
                }
                break;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        this.mListener.onSwiped(viewHolder.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(getSwipeableView(viewHolder));
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        View swipeableView = getSwipeableView(viewHolder);
        View viewFindViewById = viewHolder.itemView.findViewById(R.id.dismissal_icon_start);
        View viewFindViewById2 = viewHolder.itemView.findViewById(R.id.dismissal_icon_end);
        if (f > 0.0f) {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(8);
        } else if (f < 0.0f) {
            viewFindViewById.setVisibility(8);
            viewFindViewById2.setVisibility(0);
        }
        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(canvas, recyclerView, swipeableView, f, f2, i, z);
    }

    private View getSwipeableView(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == R.layout.contextual_slice_half_tile) {
            return ((SliceHalfCardRendererHelper.HalfCardViewHolder) viewHolder).content;
        }
        return ((SliceFullCardRendererHelper.SliceViewHolder) viewHolder).sliceView;
    }
}
