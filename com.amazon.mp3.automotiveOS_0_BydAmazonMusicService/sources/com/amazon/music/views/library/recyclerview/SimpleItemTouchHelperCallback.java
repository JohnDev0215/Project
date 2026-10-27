package com.amazon.music.views.library.recyclerview;

import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleItemTouchHelperCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0016J@\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0016J\u0018\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0004H\u0016R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/SimpleItemTouchHelperCallback;", "Landroidx/recyclerview/widget/ItemTouchHelper$Callback;", "()V", "dragFrom", "", "Ljava/lang/Integer;", "dragTo", "clearView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getDragFlags", "itemView", "Landroid/view/View;", "getMovementFlags", "isItemViewSwipeEnabled", "", "onMove", "target", "onMoved", "fromPos", "toPos", "x", "y", "onSwiped", "p0", "p1", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private Integer dragFrom;
    private Integer dragTo;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder p0, int p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        View view = viewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "viewHolder.itemView");
        return ItemTouchHelper.Callback.makeMovementFlags(getDragFlags(view), 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        Object adapter = recyclerView.getAdapter();
        if (viewHolder.getItemViewType() != target.getItemViewType() || !(adapter instanceof SimpleItemTouchHelperAdapter)) {
            return false;
        }
        return ((SimpleItemTouchHelperAdapter) adapter).onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        if (this.dragFrom == null) {
            this.dragFrom = Integer.valueOf(viewHolder.getAdapterPosition());
        }
        this.dragTo = Integer.valueOf(target.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        super.clearView(recyclerView, viewHolder);
        Integer num = this.dragFrom;
        Integer num2 = this.dragTo;
        if (num != null && num2 != null) {
            Object adapter = recyclerView.getAdapter();
            SimpleItemTouchHelperAdapter simpleItemTouchHelperAdapter = adapter instanceof SimpleItemTouchHelperAdapter ? (SimpleItemTouchHelperAdapter) adapter : null;
            if (simpleItemTouchHelperAdapter != null) {
                simpleItemTouchHelperAdapter.onDroppedWithItemMoved(num.intValue(), num2.intValue());
            }
        }
        this.dragFrom = null;
        this.dragTo = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int getDragFlags(View itemView) {
        return ((itemView instanceof DraggableItemView) && ((DraggableItemView) itemView).isDraggingEnabled()) ? 3 : 0;
    }
}
