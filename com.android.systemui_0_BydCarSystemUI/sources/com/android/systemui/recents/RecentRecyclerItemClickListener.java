package com.android.systemui.recents;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class RecentRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;

    public abstract void onEmptyClick();

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public abstract void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept);

    public RecentRecyclerItemClickListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        this.mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        this.mGestureDetector.onTouchEvent(e);
    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private ItemTouchHelperGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent e) {
            View viewFindChildViewUnder = RecentRecyclerItemClickListener.this.recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (viewFindChildViewUnder != null) {
                RecentRecyclerItemClickListener.this.onItemClick(RecentRecyclerItemClickListener.this.recyclerView.getChildViewHolder(viewFindChildViewUnder));
                return true;
            }
            RecentRecyclerItemClickListener.this.onEmptyClick();
            return true;
        }
    }
}
