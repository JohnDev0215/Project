package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class NavigationBarView extends FrameLayout {
    public static final float DEF_VALUE = -1.0f;
    private Context mContext;
    private EdgeBackGestureHandler mEdgeBackGestureHandler;
    protected LayoutInflater mLayoutInflater;

    public void setMenuVisibility(boolean showMenu) {
    }

    public NavigationBarView(Context context) {
        this(context, null, 0);
    }

    public NavigationBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, null, 0);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        EdgeBackGestureHandler edgeBackGestureHandler = new EdgeBackGestureHandler(context, null, null, null);
        this.mEdgeBackGestureHandler = edgeBackGestureHandler;
        edgeBackGestureHandler.onNavigationModeChanged(2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mEdgeBackGestureHandler.onNavBarAttached();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mEdgeBackGestureHandler.onNavBarDetached();
    }
}
