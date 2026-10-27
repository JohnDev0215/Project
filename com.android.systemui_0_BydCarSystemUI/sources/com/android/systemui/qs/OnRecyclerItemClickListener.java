package com.android.systemui.qs;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    public abstract void onItemLongClick(RecyclerView recyclerView, RecyclerView.ViewHolder vh);

    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
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
        public void onLongPress(MotionEvent e) {
            RecyclerView.ViewHolder childViewHolder;
            View viewFindChildViewUnder = OnRecyclerItemClickListener.this.recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (viewFindChildViewUnder == null || (childViewHolder = OnRecyclerItemClickListener.this.recyclerView.getChildViewHolder(viewFindChildViewUnder)) == null) {
                return;
            }
            OnRecyclerItemClickListener onRecyclerItemClickListener = OnRecyclerItemClickListener.this;
            onRecyclerItemClickListener.onItemLongClick(onRecyclerItemClickListener.recyclerView, childViewHolder);
        }
    }
}
