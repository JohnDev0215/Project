package com.byd.music.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import com.blankj.utilcode.util.ScreenUtils;
import com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class HeaderExpandListview extends ExpandableListView implements AbsListView.OnScrollListener {
    private static final int MAX_ALPHA = 255;
    private static final String TAG = "HeaderExpandListview";
    private HeaderAdapter mAdapter;
    private float mDownX;
    private float mDownY;
    private View mHeaderView;
    private int mHeaderViewHeight;
    private boolean mHeaderViewVisible;
    private int mHeaderViewWidth;
    private int mOldState;
    public OnScrollListenerExpand mOnScrollListenerExpand;

    public interface HeaderAdapter {
        public static final int PINNED_HEADER_GONE = 0;
        public static final int PINNED_HEADER_PUSHED_UP = 2;
        public static final int PINNED_HEADER_VISIBLE = 1;

        void configureHeader(View view, int i, int i2, int i3);

        int getHeaderState(int i, int i2);

        void headerViewArrowClick();

        void headerViewClick();
    }

    public interface OnScrollListenerExpand {
        void onScrollExpand(int i);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public HeaderExpandListview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOldState = -1;
        registerListener();
    }

    public HeaderExpandListview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOldState = -1;
        registerListener();
    }

    public HeaderExpandListview(Context context) {
        super(context);
        this.mOldState = -1;
        registerListener();
    }

    public void setHeaderView(View view) {
        this.mHeaderView = view;
        view.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        if (this.mHeaderView != null) {
            setFadingEdgeLength(0);
        }
        requestLayout();
    }

    private void registerListener() {
        setOnScrollListener(this);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mHeaderViewVisible) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mDownX = motionEvent.getX();
                float y = motionEvent.getY();
                this.mDownY = y;
                if (this.mDownX <= this.mHeaderViewWidth && y <= this.mHeaderViewHeight) {
                    return true;
                }
            } else if (action == 1) {
                float x = motionEvent.getX();
                float y2 = motionEvent.getY();
                float fAbs = Math.abs(x - this.mDownX);
                float fAbs2 = Math.abs(y2 - this.mDownY);
                LogUtil.e(TAG, "onTouchEvent: " + x + " y=" + y2 + " offsetX=" + fAbs + " offsetY " + fAbs2);
                StringBuilder sb = new StringBuilder();
                sb.append("mHeaderViewWidth=onTouchEvent: ");
                sb.append(this.mHeaderViewWidth);
                sb.append(" mHeaderViewHeight=");
                sb.append(this.mHeaderViewHeight);
                LogUtil.e(TAG, sb.toString());
                int i = this.mHeaderViewWidth;
                if (x <= i - 48) {
                    int i2 = this.mHeaderViewHeight;
                    if (y2 <= i2 && fAbs <= i - 48 && fAbs2 <= i2) {
                        if (this.mHeaderView != null) {
                            this.mAdapter.headerViewClick();
                        }
                        return true;
                    }
                }
                int i3 = this.mHeaderViewWidth;
                if (x > i3 - 48 && x < i3) {
                    int i4 = this.mHeaderViewHeight;
                    if (y2 <= i4 && fAbs < i3 && fAbs2 <= i4) {
                        if (this.mHeaderView != null) {
                            this.mAdapter.headerViewArrowClick();
                        }
                        return true;
                    }
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mHeaderViewVisible) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mDownX = motionEvent.getX();
                float y = motionEvent.getY();
                this.mDownY = y;
                if (this.mDownX <= this.mHeaderViewWidth && y <= this.mHeaderViewHeight) {
                    return true;
                }
            } else if (action == 1) {
                float x = motionEvent.getX();
                float y2 = motionEvent.getY();
                float fAbs = Math.abs(x - this.mDownX);
                float fAbs2 = Math.abs(y2 - this.mDownY);
                LogUtil.e(TAG, "onInterceptTouchEvent: " + x + " y=" + y2 + " offsetX=" + fAbs + " offsetY " + fAbs2);
                StringBuilder sb = new StringBuilder();
                sb.append("onInterceptTouchEvent=onTouchEvent: ");
                sb.append(this.mHeaderViewWidth);
                sb.append(" mHeaderViewHeight=");
                sb.append(this.mHeaderViewHeight);
                LogUtil.e(TAG, sb.toString());
                int i = this.mHeaderViewWidth;
                if (x <= i) {
                    int i2 = this.mHeaderViewHeight;
                    if (y2 <= i2 && fAbs <= i && fAbs2 <= i2) {
                        return true;
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setAdapter(ExpandableListAdapter expandableListAdapter) {
        super.setAdapter((android.widget.ExpandableListAdapter) expandableListAdapter);
        this.mAdapter = (HeaderAdapter) expandableListAdapter;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(((int) ScreenUtils.getScreenDensity()) * 500, Integer.MIN_VALUE);
        super.onMeasure(i, iMakeMeasureSpec);
        View view = this.mHeaderView;
        if (view != null) {
            measureChild(view, i, iMakeMeasureSpec);
            this.mHeaderViewWidth = this.mHeaderView.getMeasuredWidth();
            this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
        }
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        long expandableListPosition = getExpandableListPosition(getFirstVisiblePosition());
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
        int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
        if (packedPositionGroup == -1 && packedPositionChild == -1) {
            return;
        }
        int headerState = this.mAdapter.getHeaderState(packedPositionGroup, packedPositionChild);
        View view = this.mHeaderView;
        if (view != null && this.mAdapter != null && headerState != this.mOldState) {
            this.mOldState = headerState;
            view.layout(0, 0, this.mHeaderViewWidth, this.mHeaderViewHeight);
        }
        configureHeaderView(packedPositionGroup, packedPositionChild);
    }

    public void configureHeaderView(int i, int i2) {
        Object obj;
        int i3;
        if (this.mHeaderView == null || (obj = this.mAdapter) == null || ((ExpandableListAdapter) obj).getGroupCount() == 0 || ((ExpandableListAdapter) this.mAdapter).getGroupCount() < i) {
            return;
        }
        int headerState = this.mAdapter.getHeaderState(i, i2);
        if (headerState != 0) {
            int i4 = 255;
            if (headerState == 1) {
                this.mAdapter.configureHeader(this.mHeaderView, i, i2, 255);
                if (this.mHeaderView.getTop() != 0) {
                    this.mHeaderView.layout(0, 0, this.mHeaderViewWidth, this.mHeaderViewHeight);
                }
                this.mHeaderViewVisible = true;
            } else if (headerState == 2) {
                int bottom = getChildAt(0).getBottom();
                int height = this.mHeaderView.getHeight();
                if (bottom < height) {
                    i3 = bottom - height;
                    i4 = ((height + i3) * 255) / height;
                } else {
                    i3 = 0;
                }
                this.mAdapter.configureHeader(this.mHeaderView, i, i2, i4);
                if (this.mHeaderView.getTop() != i3) {
                    this.mHeaderView.layout(0, i3, this.mHeaderViewWidth, this.mHeaderViewHeight + i3);
                }
                this.mHeaderViewVisible = true;
            }
        } else {
            this.mHeaderViewVisible = false;
        }
        requestLayout();
    }

    @Override // android.widget.ExpandableListView, android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mHeaderViewVisible) {
            drawChild(canvas, this.mHeaderView, getDrawingTime());
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        long expandableListPosition = getExpandableListPosition(i);
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
        int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
        OnScrollListenerExpand onScrollListenerExpand = this.mOnScrollListenerExpand;
        if (onScrollListenerExpand != null) {
            onScrollListenerExpand.onScrollExpand(i);
        }
        configureHeaderView(packedPositionGroup, packedPositionChild);
    }

    public void addOnScrollListenerExpandListener(OnScrollListenerExpand onScrollListenerExpand) {
        this.mOnScrollListenerExpand = onScrollListenerExpand;
    }
}
