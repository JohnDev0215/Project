package com.bydux.api.common.views.window;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import d.a.a.e;
import d.a.a.f.b.c.b;

/* JADX INFO: loaded from: classes.dex */
public class DampingScrollView extends ScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3625b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3626c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3627d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3628e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3629f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3630g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private View f3631h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Rect f3632i;
    private int j;
    private float k;
    private boolean l;
    private boolean m;
    private int n;

    public DampingScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3625b = 0;
        this.f3626c = 0;
        this.f3627d = 0;
        this.f3628e = 0;
        this.f3629f = 0;
        this.f3630g = 0;
        this.f3632i = new Rect();
        this.j = 0;
        this.l = true;
        this.m = true;
        a(context, attributeSet);
        setOverScrollMode(2);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.DampingScrollView);
        this.n = typedArrayObtainStyledAttributes.getLayoutDimension(e.DampingScrollView_maxHeight, this.n);
        this.l = typedArrayObtainStyledAttributes.getLayoutDimension(e.DampingScrollView_orientation, 1) == 1;
        typedArrayObtainStyledAttributes.recycle();
    }

    private void b() {
        TranslateAnimation translateAnimation = this.l ? new TranslateAnimation(0.0f, 0.0f, this.f3631h.getTop(), this.j) : new TranslateAnimation(this.f3631h.getLeft(), this.j, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(b.b());
        this.f3631h.setAnimation(translateAnimation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0093, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0130  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.window.DampingScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            this.f3631h = childAt;
            this.j = this.l ? childAt.getTop() : childAt.getLeft();
        }
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = this.n;
        if (i4 > 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
    }

    public void setScroll(boolean z) {
        this.m = z;
    }
}
