package com.byd.card.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class PageScrollView extends HorizontalScrollView {
    private static final String TAG = "PageScrollView";
    private Bitmap background;
    private LinearLayout container;
    private GestureDetector gestureDetector;
    private boolean intense;
    private ViewTreeObserver.OnScrollChangedListener onScrollChanged;
    private Paint paint;
    private GestureDetector.OnGestureListener scrollDetector;

    public void destory() {
    }

    public PageScrollView(Context context) {
        super(context);
        this.intense = false;
        this.scrollDetector = new GestureListener();
        this.paint = new Paint(1);
        init();
    }

    public PageScrollView(Context context, AttributeSet attr) {
        super(context, attr);
        this.intense = false;
        this.scrollDetector = new GestureListener();
        this.paint = new Paint(1);
        init();
    }

    public PageScrollView(Context context, AttributeSet attr, int style) {
        super(context, attr, style);
        this.intense = false;
        this.scrollDetector = new GestureListener();
        this.paint = new Paint(1);
        init();
    }

    private LinearLayout getContainer() {
        if (this.container == null) {
            this.container = (LinearLayout) getChildAt(0);
        }
        return this.container;
    }

    private void init() {
        this.gestureDetector = new GestureDetector(getContext(), this.scrollDetector);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int scrollX = getScrollX();
        int width = getWidth() + scrollX;
        LinearLayout container = getContainer();
        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = container.getChildAt(0);
            if (childAt.getLeft() < width && childAt.getRight() > scrollX) {
                childAt.setVisibility(0);
            }
        }
        if (this.background != null) {
            this.paint.setFilterBitmap(true);
            this.background.getWidth();
            int height = this.background.getHeight();
            int scrollX2 = getScrollX();
            canvas.drawBitmap(this.background, new Rect(0, 0, (getWidth() * height) / getHeight(), height), new Rect(scrollX2, 0, getWidth() + scrollX2, getHeight()), this.paint);
        }
        super.dispatchDraw(canvas);
    }

    public void setBackground(Bitmap background) {
        this.background = background;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            super.onTouchEvent(ev);
            return false;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            super.onTouchEvent(ev);
        } catch (IllegalArgumentException unused) {
        }
        return this.gestureDetector.onTouchEvent(ev);
    }

    @Override // android.view.View
    protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = this.onScrollChanged;
        if (onScrollChangedListener == null) {
            return;
        }
        onScrollChangedListener.onScrollChanged();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void scrollTo(int distanceX, int distanceY) {
        if (distanceX < 0) {
            return;
        }
        super.scrollTo(distanceX, distanceY);
    }

    public void setIntenseModeEnabled(boolean enable) {
        this.intense = enable;
    }

    public void setOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChanged = onScrollChangedListener;
    }

    class GestureListener implements GestureDetector.OnGestureListener {
        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent paramMotionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent paramMotionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent ev) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent ev) {
            return false;
        }

        GestureListener() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return PageScrollView.this.intense ? Math.abs(distanceX) <= Math.abs(distanceY) * 6.0f : Math.abs(distanceX) > Math.abs(distanceY);
        }
    }
}
