package com.byd.card.floatcard;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "RecyclerViewTouchListener";
    private float downX;
    private float downY;
    private boolean mDownResult;
    private boolean mDuplicationTouch;
    private GestureDetector mGestureDetector;
    private OnItemClickListener mListener;
    private boolean mLongPressed = false;

    public interface OnItemClickListener {
        void onDragPoint(MotionEvent event);

        void onItemLongClick(View view, int position);

        void onItemSingleTapUp(View view, int position);

        boolean onItemTouchUp(View view, int position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        this.mListener = listener;
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.byd.card.floatcard.RecyclerViewTouchListener.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                Log.i(RecyclerViewTouchListener.TAG, "onDown");
                RecyclerViewTouchListener.this.mLongPressed = false;
                return super.onDown(e);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent e) {
                View viewFindChildViewUnder;
                Log.i(RecyclerViewTouchListener.TAG, "onSingleTapUp");
                if (e.getPointerCount() == 1 && (viewFindChildViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY())) != null && RecyclerViewTouchListener.this.mListener != null) {
                    RecyclerViewTouchListener.this.mListener.onItemSingleTapUp(viewFindChildViewUnder, recyclerView.getChildLayoutPosition(viewFindChildViewUnder));
                }
                return super.onSingleTapUp(e);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent e) {
                Log.i(RecyclerViewTouchListener.TAG, "onLongPress mDuplicationTouch = " + RecyclerViewTouchListener.this.mDuplicationTouch);
                if (!RecyclerViewTouchListener.this.mDuplicationTouch && e.getPointerCount() == 1) {
                    View viewFindChildViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (viewFindChildViewUnder != null && RecyclerViewTouchListener.this.mListener != null) {
                        RecyclerViewTouchListener.this.mListener.onItemLongClick(viewFindChildViewUnder, recyclerView.getChildLayoutPosition(viewFindChildViewUnder));
                    }
                    RecyclerViewTouchListener.this.mLongPressed = true;
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
        OnItemClickListener onItemClickListener;
        OnItemClickListener onItemClickListener2 = this.mListener;
        if (onItemClickListener2 != null) {
            onItemClickListener2.onDragPoint(e);
        }
        if (e.getPointerCount() > 1 || e.getAction() == 5 || e.getAction() == 5 || e.getAction() == 261 || e.getAction() == 517) {
            this.mDuplicationTouch = true;
            Log.i(TAG, "onInterceptTouchEvent down mDuplicationTouch!");
            return true;
        }
        this.mDuplicationTouch = false;
        boolean zOnTouchEvent = this.mGestureDetector.onTouchEvent(e);
        int action = e.getAction();
        if (action == 0) {
            this.downX = e.getX();
            this.downY = e.getY();
            Log.i(TAG, "onInterceptTouchEvent down!");
        } else if (action == 1) {
            Log.i(TAG, "onInterceptTouchEvent up!");
            if (this.mLongPressed) {
                return true;
            }
            View viewFindChildViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
            float x = e.getX();
            float y = e.getY();
            float fAbs = Math.abs(this.downX - x);
            float fAbs2 = Math.abs(this.downY - y);
            if (viewFindChildViewUnder != null && (onItemClickListener = this.mListener) != null && (fAbs < 10.0f || fAbs2 < 10.0f)) {
                boolean zOnItemTouchUp = onItemClickListener.onItemTouchUp(viewFindChildViewUnder, recyclerView.getChildLayoutPosition(viewFindChildViewUnder));
                this.mDownResult = zOnItemTouchUp;
                return zOnItemTouchUp;
            }
        }
        return zOnTouchEvent;
    }
}
