package com.bydux.api.common.views.window;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import d.a.a.f.b.c.b;

/* JADX INFO: loaded from: classes.dex */
public class DampingHorizontalScrollView extends ScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3618c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3619d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3620e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3621f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3622g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private View f3623h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Rect f3624i;
    private int j;
    private float k;
    private boolean l;

    public DampingHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3617b = 0;
        this.f3618c = 0;
        this.f3619d = 0;
        this.f3620e = 0;
        this.f3621f = 0;
        this.f3622g = 0;
        this.f3624i = new Rect();
        this.j = 0;
        this.l = true;
        setOverScrollMode(2);
    }

    private void a() {
        TranslateAnimation translateAnimation = new TranslateAnimation(this.f3623h.getLeft(), this.j, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(b.b());
        this.f3623h.setAnimation(translateAnimation);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00d2  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.window.DampingHorizontalScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            this.f3623h = childAt;
            this.j = childAt.getLeft();
        }
    }

    public void setScroll(boolean z) {
        this.l = z;
    }
}
