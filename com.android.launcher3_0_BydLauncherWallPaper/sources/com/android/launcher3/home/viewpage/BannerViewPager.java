package com.android.launcher3.home.viewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class BannerViewPager extends ViewPager {
    private List<Integer> childCenterXAbs;
    private SparseIntArray childIndex;
    private boolean isFirstLayoutToField;
    private float lastX;
    private float lastY;
    private boolean overlapStyle;
    private int scaledTouchSlop;
    private boolean scrollable;
    private ViewPagerScroller scroller;
    private float startX;
    private float startY;

    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.scrollable = true;
        this.scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() >> 1;
        this.scroller = new ViewPagerScroller(getContext());
        initViewPagerScroll();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            if (this.scrollable) {
                return super.onTouchEvent(ev);
            }
            return false;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            int action = ev.getAction();
            if (action == 0) {
                float rawX = ev.getRawX();
                this.lastX = rawX;
                this.startX = rawX;
                float rawY = ev.getRawY();
                this.lastY = rawY;
                this.startY = rawY;
            } else {
                boolean z = true;
                if (action != 2) {
                    if (action != 3) {
                        if (action == 1) {
                        }
                    }
                    return Math.abs(this.lastX - this.startX) > ((float) this.scaledTouchSlop) || Math.abs(this.lastY - this.startY) > ((float) this.scaledTouchSlop);
                }
                this.lastX = ev.getRawX();
                this.lastY = ev.getRawY();
                if (this.scrollable) {
                    float fAbs = Math.abs(this.lastX - this.startX);
                    float fAbs2 = Math.abs(this.lastY - this.startY);
                    ViewParent parent = getParent();
                    if (fAbs <= this.scaledTouchSlop || fAbs <= fAbs2) {
                        z = false;
                    }
                    parent.requestDisallowInterceptTouchEvent(z);
                }
            }
            if (this.scrollable) {
                return super.onInterceptTouchEvent(ev);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int n) {
        if (this.overlapStyle) {
            if (n == 0 || this.childIndex.size() != childCount) {
                this.childCenterXAbs.clear();
                this.childIndex.clear();
                int viewCenterX = getViewCenterX(this);
                for (int i = 0; i < childCount; i++) {
                    int iAbs = Math.abs(viewCenterX - getViewCenterX(getChildAt(i)));
                    if (this.childIndex.indexOfKey(iAbs) >= 0) {
                        iAbs++;
                    }
                    this.childCenterXAbs.add(Integer.valueOf(iAbs));
                    this.childIndex.append(iAbs, i);
                }
                Collections.sort(this.childCenterXAbs);
            }
            return this.childIndex.get(this.childCenterXAbs.get((childCount - 1) - n).intValue());
        }
        return super.getChildDrawingOrder(childCount, n);
    }

    public void setOverlapStyle(boolean overlapStyle) {
        this.overlapStyle = overlapStyle;
        if (overlapStyle) {
            this.childCenterXAbs = new ArrayList();
            this.childIndex = new SparseIntArray();
        } else {
            this.childCenterXAbs = null;
            this.childIndex = null;
        }
    }

    private int getViewCenterX(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[0] + (view.getWidth() / 2);
    }

    private void initViewPagerScroll() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, this.scroller);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public void setPagerScrollDuration(int duration) {
        this.scroller.setScrollDuration(duration);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isFirstLayoutToField) {
            onAttachedToRestFirstLayout();
        } else {
            this.isFirstLayoutToField = true;
        }
    }

    public void setFirstLayoutToField(boolean firstLayoutToField) {
        this.isFirstLayoutToField = firstLayoutToField;
    }

    private void onAttachedToRestFirstLayout() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mFirstLayout");
            declaredField.setAccessible(true);
            declaredField.set(this, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }
}
