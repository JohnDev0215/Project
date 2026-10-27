package me.everything.android.ui.overscroll.adapters;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RecyclerViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final Impl mImpl;
    protected boolean mIsItemTouchInEffect;
    protected final RecyclerView mRecyclerView;

    protected interface Impl {
        boolean isInAbsoluteEnd();

        boolean isInAbsoluteStart();
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView) {
        int orientation;
        this.mIsItemTouchInEffect = false;
        this.mRecyclerView = recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        boolean z = layoutManager instanceof LinearLayoutManager;
        if (!z && !(layoutManager instanceof StaggeredGridLayoutManager)) {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
        if (z) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        } else {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        }
        if (orientation == 0) {
            this.mImpl = new ImplHorizLayout();
        } else {
            this.mImpl = new ImplVerticalLayout();
        }
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl) {
        this.mIsItemTouchInEffect = false;
        this.mRecyclerView = recyclerView;
        this.mImpl = impl;
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, ItemTouchHelper.Callback callback) {
        this(recyclerView);
        setUpTouchHelperCallback(callback);
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl, ItemTouchHelper.Callback callback) {
        this(recyclerView, impl);
        setUpTouchHelperCallback(callback);
    }

    protected void setUpTouchHelperCallback(ItemTouchHelper.Callback callback) {
        new ItemTouchHelper(new ItemTouchHelperCallbackWrapper(callback) { // from class: me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.1
            @Override // me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.ItemTouchHelperCallbackWrapper, androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
                RecyclerViewOverScrollDecorAdapter.this.mIsItemTouchInEffect = i != 0;
                super.onSelectedChanged(viewHolder, i);
            }
        }).attachToRecyclerView(this.mRecyclerView);
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mRecyclerView;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return !this.mIsItemTouchInEffect && this.mImpl.isInAbsoluteStart();
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return !this.mIsItemTouchInEffect && this.mImpl.isInAbsoluteEnd();
    }

    protected class ImplHorizLayout implements Impl {
        protected ImplHorizLayout() {
        }

        @Override // me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteStart() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollHorizontally(-1);
        }

        @Override // me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteEnd() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollHorizontally(1);
        }
    }

    protected class ImplVerticalLayout implements Impl {
        protected ImplVerticalLayout() {
        }

        @Override // me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteStart() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollVertically(-1);
        }

        @Override // me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteEnd() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollVertically(1);
        }
    }

    private static class ItemTouchHelperCallbackWrapper extends ItemTouchHelper.Callback {
        final ItemTouchHelper.Callback mCallback;

        private ItemTouchHelperCallbackWrapper(ItemTouchHelper.Callback callback) {
            this.mCallback = callback;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getMovementFlags(recyclerView, viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return this.mCallback.onMove(recyclerView, viewHolder, viewHolder2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            this.mCallback.onSwiped(viewHolder, i);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int convertToAbsoluteDirection(int i, int i2) {
            return this.mCallback.convertToAbsoluteDirection(i, i2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return this.mCallback.canDropOver(recyclerView, viewHolder, viewHolder2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return this.mCallback.isLongPressDragEnabled();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isItemViewSwipeEnabled() {
            return this.mCallback.isItemViewSwipeEnabled();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getBoundingBoxMargin() {
            return this.mCallback.getBoundingBoxMargin();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getSwipeThreshold(viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getMoveThreshold(viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder viewHolder, List<RecyclerView.ViewHolder> list, int i, int i2) {
            return this.mCallback.chooseDropTarget(viewHolder, list, i, i2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            this.mCallback.onSelectedChanged(viewHolder, i);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
            this.mCallback.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            this.mCallback.clearView(recyclerView, viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            this.mCallback.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            this.mCallback.onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, z);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
            return this.mCallback.getAnimationDuration(recyclerView, i, f, f2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            return this.mCallback.interpolateOutOfBoundsScroll(recyclerView, i, i2, i3, j);
        }
    }
}
