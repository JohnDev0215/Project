package com.android.systemui.recents;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.android.systemui.recents.ItemTouchHelper;

/* JADX INFO: loaded from: classes.dex */
public class RecentItemTouchCallBack extends ItemTouchHelper.Callback {
    private static final String TAG = "ItemTouchCallBack";
    private OnItemTouchListener onItemTouchListener;

    public interface OnItemTouchListener {
        void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);

        void onMove(int fromPosition, int toPosition);

        void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState);

        void onSwiped(int position, int direction);
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override // com.android.systemui.recents.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if ((recyclerView.getLayoutManager() instanceof GridLayoutManager) || (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager)) {
            return makeMovementFlags(0, 3);
        }
        return makeMovementFlags(3, 8);
    }

    @Override // com.android.systemui.recents.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        this.onItemTouchListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override // com.android.systemui.recents.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        this.onItemTouchListener.onSwiped(viewHolder.getAdapterPosition(), direction);
    }

    @Override // com.android.systemui.recents.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        this.onItemTouchListener.onSelectedChanged(viewHolder, actionState);
    }

    @Override // com.android.systemui.recents.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        this.onItemTouchListener.clearView(recyclerView, viewHolder);
    }
}
