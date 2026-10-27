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
import b.a.d.e.c.b.b;
import com.byd.mvp.R$string;
import com.byd.mvp.ui.widget.pullrefreshview.WrapRecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshWrapperRecyclerView extends WrapRecyclerView {
    public float i;
    public int j;
    public b k;
    public int l;
    public View m;
    public int n;
    public boolean o;
    public String p;
    public String q;
    public String r;

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
        this.i = 0.3f;
        this.l = 0;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        e(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshViewMarginTop(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.m.getLayoutParams();
        int i2 = this.l;
        if (i < (-i2) + 1) {
            i = (-i2) + 1;
        }
        if (i > 10) {
            return;
        }
        marginLayoutParams.topMargin = i;
        this.m.setLayoutParams(marginLayoutParams);
    }

    public final boolean d() {
        return Build.VERSION.SDK_INT < 14 ? ViewCompat.canScrollVertically(this, -1) || getScrollY() > 0 : ViewCompat.canScrollVertically(this, -1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.n = (int) motionEvent.getRawY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void e(Context context) {
        this.p = context.getResources().getString(R$string.refresh_success);
        this.q = context.getResources().getString(R$string.load_more_success);
        this.r = context.getResources().getString(R$string.no_more_data);
    }

    public final void f() {
        View view = this.m;
        if (view == null) {
            return;
        }
        int i = (-this.l) + 1;
        int i2 = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
        if (this.j == 51) {
            this.j = 68;
            b bVar = this.k;
            if (bVar != null) {
                bVar.b(this.m);
            }
            WrapRecyclerView.a aVar = this.h;
            if (aVar != null) {
                aVar.a();
            }
            i = 0;
        }
        ValueAnimator duration = ObjectAnimator.ofFloat(i2, i).setDuration(Math.abs(i2 - i));
        duration.addUpdateListener(new a());
        duration.start();
        this.o = false;
    }

    public final void g(int i) {
        if (i <= 0) {
            this.j = 17;
        } else if (i < this.l) {
            this.j = 34;
        } else {
            this.j = 51;
        }
        b bVar = this.k;
        if (bVar != null) {
            bVar.a(this.m, i, this.l);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.m;
        if (view == null || this.l != 0) {
            return;
        }
        int iMakeMeasureSpec = 0;
        int i3 = view.getLayoutParams().height;
        if (i3 == -2) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE);
        } else if (i3 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        this.m.measure(i, iMakeMeasureSpec);
        int measuredHeight = this.m.getMeasuredHeight();
        this.l = measuredHeight;
        if (measuredHeight > 0) {
            setRefreshViewMarginTop((-measuredHeight) + 1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                if (d() || this.j == 68 || this.m == null || this.k == null) {
                    return super.onTouchEvent(motionEvent);
                }
                if (this.o) {
                    scrollToPosition(0);
                }
                int rawY = (int) ((motionEvent.getRawY() - this.n) * this.i);
                if (rawY > 0) {
                    setRefreshViewMarginTop(rawY - this.l);
                    g(rawY);
                    this.o = true;
                    return true;
                }
            }
        } else if (this.o) {
            f();
        }
        return super.onTouchEvent(motionEvent);
    }

    public RefreshWrapperRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 0.3f;
        this.l = 0;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        e(context);
    }

    public RefreshWrapperRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 0.3f;
        this.l = 0;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        e(context);
    }
}
