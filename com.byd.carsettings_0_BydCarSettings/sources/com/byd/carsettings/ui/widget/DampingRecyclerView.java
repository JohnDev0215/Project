package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DampingRecyclerView extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RecyclerView f3383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Rect f3384b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3385c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3386d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3387e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f3388f;

    public interface a {
        void onScroll();
    }

    public DampingRecyclerView(Context context) {
        this(context, null);
    }

    public final boolean a() {
        if (this.f3383a.getLayoutManager() == null || this.f3383a.getAdapter() == null) {
            return false;
        }
        if (((LinearLayoutManager) this.f3383a.getLayoutManager()).findFirstVisibleItemPosition() == 0 || this.f3383a.getAdapter().getItemCount() == 0) {
            return (this.f3383a.getChildCount() > 0 ? this.f3383a.getChildAt(0).getTop() : 0) >= 0;
        }
        return false;
    }

    public final boolean b() {
        if (this.f3383a.getLayoutManager() == null) {
            return false;
        }
        int itemCount = this.f3383a.getAdapter().getItemCount() - 1;
        int iFindLastVisibleItemPosition = ((LinearLayoutManager) this.f3383a.getLayoutManager()).findLastVisibleItemPosition();
        if (iFindLastVisibleItemPosition < itemCount) {
            return false;
        }
        View childAt = this.f3383a.getChildAt(Math.min(iFindLastVisibleItemPosition - ((LinearLayoutManager) this.f3383a.getLayoutManager()).findFirstVisibleItemPosition(), this.f3383a.getChildCount() - 1));
        return childAt != null && childAt.getBottom() <= this.f3383a.getBottom() - this.f3383a.getTop();
    }

    public final void c(MotionEvent motionEvent) {
        motionEvent.setAction(3);
        super.dispatchTouchEvent(motionEvent);
    }

    public final void d() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.f3383a.getTop() - this.f3384b.top, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(PathInterpolatorUtils.getStandard());
        this.f3383a.startAnimation(translateAnimation);
        RecyclerView recyclerView = this.f3383a;
        Rect rect = this.f3384b;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        this.f3385c = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        Rect rect = this.f3384b;
        if (y >= rect.bottom || y <= rect.top) {
            if (this.f3385c) {
                d();
            }
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f3386d = motionEvent.getY();
        } else {
            if (action == 1) {
                if (this.f3385c) {
                    d();
                }
                return !this.f3387e || super.dispatchTouchEvent(motionEvent);
            }
            if (action != 2) {
                return true;
            }
        }
        int y2 = (int) (motionEvent.getY() - this.f3386d);
        boolean z = y2 > 0 && a();
        boolean z2 = y2 < 0 && b();
        if (!z && !z2) {
            this.f3386d = motionEvent.getY();
            this.f3385c = false;
            this.f3387e = true;
            return super.dispatchTouchEvent(motionEvent);
        }
        c(motionEvent);
        int i = (int) (y2 * 0.5f);
        RecyclerView recyclerView = this.f3383a;
        Rect rect2 = this.f3384b;
        recyclerView.layout(rect2.left, rect2.top + i, rect2.right, rect2.bottom + i);
        a aVar = this.f3388f;
        if (aVar != null) {
            aVar.onScroll();
        }
        this.f3385c = true;
        this.f3387e = false;
        return true;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f3383a = (RecyclerView) getChildAt(0);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f3384b.set(this.f3383a.getLeft(), this.f3383a.getTop(), this.f3383a.getRight(), this.f3383a.getBottom());
    }

    public void setScrollListener(a aVar) {
        this.f3388f = aVar;
    }

    public DampingRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DampingRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3384b = new Rect();
        this.f3385c = false;
        this.f3387e = false;
    }
}
