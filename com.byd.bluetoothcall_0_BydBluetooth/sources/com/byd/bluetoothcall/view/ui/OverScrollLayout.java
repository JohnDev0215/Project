package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class OverScrollLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient RecyclerView f3470b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient Rect f3471c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private transient boolean f3472d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private transient float f3473e;
    private transient boolean f;
    private transient a g;

    public interface a {
        void a();
    }

    public OverScrollLayout(Context context) {
        this(context, null);
    }

    private boolean a() {
        if (((LinearLayoutManager) this.f3470b.getLayoutManager()).V1() == 0 || this.f3470b.getAdapter().c() == 0) {
            return (this.f3470b.getChildCount() > 0 ? this.f3470b.getChildAt(0).getTop() : 0) >= 0;
        }
        return false;
    }

    private boolean b() {
        int iC = this.f3470b.getAdapter().c() - 1;
        int iB2 = ((LinearLayoutManager) this.f3470b.getLayoutManager()).b2();
        if (iB2 >= iC) {
            View childAt = this.f3470b.getChildAt(Math.min(iB2 - ((LinearLayoutManager) this.f3470b.getLayoutManager()).V1(), this.f3470b.getChildCount() - 1));
            return childAt != null && childAt.getBottom() <= this.f3470b.getBottom() - this.f3470b.getTop();
        }
        return false;
    }

    private void c(MotionEvent motionEvent) {
        motionEvent.setAction(3);
        super.dispatchTouchEvent(motionEvent);
    }

    private void d() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.f3470b.getTop() - this.f3471c.top, 0.0f);
        translateAnimation.setDuration(200L);
        this.f3470b.startAnimation(translateAnimation);
        RecyclerView recyclerView = this.f3470b;
        Rect rect = this.f3471c;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        this.f3472d = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        Rect rect = this.f3471c;
        if (y >= rect.bottom || y <= rect.top) {
            if (this.f3472d) {
                d();
            }
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f3473e = motionEvent.getY();
        } else {
            if (action == 1) {
                if (this.f3472d) {
                    d();
                }
                return !this.f || super.dispatchTouchEvent(motionEvent);
            }
            if (action != 2) {
                return true;
            }
        }
        int y2 = (int) (motionEvent.getY() - this.f3473e);
        boolean z = y2 > 0 && a();
        boolean z2 = y2 < 0 && b();
        if (!z && !z2) {
            this.f3473e = motionEvent.getY();
            this.f3472d = false;
            this.f = true;
            return super.dispatchTouchEvent(motionEvent);
        }
        c(motionEvent);
        int i = (int) (y2 * 0.3f);
        RecyclerView recyclerView = this.f3470b;
        Rect rect2 = this.f3471c;
        recyclerView.layout(rect2.left, rect2.top + i, rect2.right, rect2.bottom + i);
        a aVar = this.g;
        if (aVar != null) {
            aVar.a();
        }
        this.f3472d = true;
        this.f = false;
        return true;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f3470b = (RecyclerView) getChildAt(0);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f3471c.set(this.f3470b.getLeft(), this.f3470b.getTop(), this.f3470b.getRight(), this.f3470b.getBottom());
    }

    public void setScrollListener(a aVar) {
        this.g = aVar;
    }

    public OverScrollLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverScrollLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3471c = new Rect();
        this.f3472d = false;
        this.f = false;
    }
}
