package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import com.bydux.api.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DampingScrollView extends ScrollView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2896a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2897b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2898c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2899d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2900e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2901f;
    public View g;
    public final Rect h;
    public int i;
    public float j;
    public boolean k;
    public boolean l;
    public int m;
    public a n;

    public interface a {
        void onScroll();

        void onScrollEnd();

        void onScrollStart();
    }

    public DampingScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2896a = 0;
        this.f2897b = 0;
        this.f2898c = 0;
        this.f2899d = 0;
        this.f2900e = 0;
        this.f2901f = 0;
        this.h = new Rect();
        this.i = 0;
        this.k = true;
        this.l = true;
        a(context, attributeSet);
        setOverScrollMode(2);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DampingScrollView);
        this.m = typedArrayObtainStyledAttributes.getLayoutDimension(0, this.m);
        this.k = typedArrayObtainStyledAttributes.getLayoutDimension(1, 1) == 1;
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b() {
        TranslateAnimation translateAnimation = this.k ? new TranslateAnimation(0.0f, 0.0f, this.g.getTop(), this.i) : new TranslateAnimation(this.g.getLeft(), this.i, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(PathInterpolatorUtils.getStandard());
        this.g.setAnimation(translateAnimation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0093, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0137  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.ui.activity.DampingScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            this.g = childAt;
            this.i = this.k ? childAt.getTop() : childAt.getLeft();
        }
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.m;
        if (i3 > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }

    public void setScroll(boolean z) {
        this.l = z;
    }

    public void setScrollListener(a aVar) {
        this.n = aVar;
    }
}
