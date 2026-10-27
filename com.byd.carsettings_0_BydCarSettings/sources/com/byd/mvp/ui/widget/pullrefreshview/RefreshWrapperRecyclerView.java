package com.byd.mvp.ui.widget.pullrefreshview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import b.b.m.f.f.c.b;
import com.byd.mvp.R$string;
import com.byd.mvp.ui.widget.pullrefreshview.WrapRecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshWrapperRecyclerView extends WrapRecyclerView {
    public float h;
    public int i;
    public b j;
    public int k;
    public View l;
    public int m;
    public boolean n;
    public String o;
    public String p;
    public String q;

    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            RefreshWrapperRecyclerView.this.setRefreshViewMarginTop((int) ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public RefreshWrapperRecyclerView(Context context) {
        super(context);
        this.h = 0.3f;
        this.k = 0;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        e(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshViewMarginTop(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.l.getLayoutParams();
        int i2 = this.k;
        if (i < (-i2) + 1) {
            i = (-i2) + 1;
        }
        if (i > 10) {
            return;
        }
        marginLayoutParams.topMargin = i;
        this.l.setLayoutParams(marginLayoutParams);
    }

    public final boolean d() {
        return Build.VERSION.SDK_INT < 14 ? ViewCompat.canScrollVertically(this, -1) || getScrollY() > 0 : ViewCompat.canScrollVertically(this, -1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.m = (int) motionEvent.getRawY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void e(Context context) {
        this.o = context.getResources().getString(R$string.refresh_success);
        this.p = context.getResources().getString(R$string.load_more_success);
        this.q = context.getResources().getString(R$string.no_more_data);
    }

    public final void f() {
        View view = this.l;
        if (view == null) {
            return;
        }
        int i = (-this.k) + 1;
        int i2 = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
        if (this.i == 51) {
            this.i = 68;
            b bVar = this.j;
            if (bVar != null) {
                bVar.b(this.l);
            }
            WrapRecyclerView.a aVar = this.g;
            if (aVar != null) {
                aVar.a();
            }
            i = 0;
        }
        ValueAnimator duration = ObjectAnimator.ofFloat(i2, i).setDuration(Math.abs(i2 - i));
        duration.addUpdateListener(new a());
        duration.start();
        this.n = false;
    }

    public final void g(int i) {
        if (i <= 0) {
            this.i = 17;
        } else if (i < this.k) {
            this.i = 34;
        } else {
            this.i = 51;
        }
        b bVar = this.j;
        if (bVar != null) {
            bVar.a(this.l, i, this.k);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.l;
        if (view == null || this.k != 0) {
            return;
        }
        int iMakeMeasureSpec = 0;
        int i3 = view.getLayoutParams().height;
        if (i3 == -2) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE);
        } else if (i3 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        this.l.measure(i, iMakeMeasureSpec);
        int measuredHeight = this.l.getMeasuredHeight();
        this.k = measuredHeight;
        if (measuredHeight > 0) {
            setRefreshViewMarginTop((-measuredHeight) + 1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                if (d() || this.i == 68 || this.l == null || this.j == null) {
                    return super.onTouchEvent(motionEvent);
                }
                if (this.n) {
                    scrollToPosition(0);
                }
                int rawY = (int) ((motionEvent.getRawY() - this.m) * this.h);
                if (rawY > 0) {
                    setRefreshViewMarginTop(rawY - this.k);
                    g(rawY);
                    this.n = true;
                    return true;
                }
            }
        } else if (this.n) {
            f();
        }
        return super.onTouchEvent(motionEvent);
    }

    public RefreshWrapperRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0.3f;
        this.k = 0;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        e(context);
    }

    public RefreshWrapperRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 0.3f;
        this.k = 0;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        e(context);
    }
}
