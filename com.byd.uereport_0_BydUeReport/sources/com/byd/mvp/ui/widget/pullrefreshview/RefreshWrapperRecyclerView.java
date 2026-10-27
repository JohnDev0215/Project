package com.byd.mvp.ui.widget.pullrefreshview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.byd.mvp.R$string;
import com.byd.mvp.ui.widget.pullrefreshview.WrapRecyclerView;
import l4.b;
import o0.t;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshWrapperRecyclerView extends WrapRecyclerView {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f2984k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2985l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f2986m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2987n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f2988o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2989p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2990q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f2991r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f2992s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f2993t;

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
        this.f2984k = 0.3f;
        this.f2987n = 0;
        this.f2990q = false;
        this.f2991r = null;
        this.f2992s = null;
        this.f2993t = null;
        f(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshViewMarginTop(int i8) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f2988o.getLayoutParams();
        int i9 = this.f2987n;
        if (i8 < (-i9) + 1) {
            i8 = (-i9) + 1;
        }
        if (i8 > 10) {
            return;
        }
        marginLayoutParams.topMargin = i8;
        this.f2988o.setLayoutParams(marginLayoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f2989p = (int) motionEvent.getRawY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final boolean e() {
        return Build.VERSION.SDK_INT < 14 ? t.d(this, -1) || getScrollY() > 0 : t.d(this, -1);
    }

    public void f(Context context) {
        this.f2991r = context.getResources().getString(R$string.refresh_success);
        this.f2992s = context.getResources().getString(R$string.load_more_success);
        this.f2993t = context.getResources().getString(R$string.no_more_data);
    }

    public final void g() {
        View view = this.f2988o;
        if (view == null) {
            return;
        }
        int i8 = (-this.f2987n) + 1;
        int i9 = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
        if (this.f2985l == 51) {
            this.f2985l = 68;
            b bVar = this.f2986m;
            if (bVar != null) {
                bVar.b(this.f2988o);
            }
            WrapRecyclerView.a aVar = this.f3001j;
            if (aVar != null) {
                aVar.a();
            }
            i8 = 0;
        }
        ValueAnimator duration = ObjectAnimator.ofFloat(i9, i8).setDuration(Math.abs(i9 - i8));
        duration.addUpdateListener(new a());
        duration.start();
        this.f2990q = false;
    }

    public final void h(int i8) {
        if (i8 <= 0) {
            this.f2985l = 17;
        } else if (i8 < this.f2987n) {
            this.f2985l = 34;
        } else {
            this.f2985l = 51;
        }
        b bVar = this.f2986m;
        if (bVar != null) {
            bVar.a(this.f2988o, i8, this.f2987n);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i8, int i9) {
        int iMakeMeasureSpec;
        super.onMeasure(i8, i9);
        View view = this.f2988o;
        if (view == null || this.f2987n != 0) {
            return;
        }
        int i10 = view.getLayoutParams().height;
        if (i10 == -2) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE);
        } else {
            if (i10 == -1) {
                throw new RuntimeException("RefreshView的高度不能指定为Match_Parent");
            }
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        this.f2988o.measure(i8, iMakeMeasureSpec);
        int measuredHeight = this.f2988o.getMeasuredHeight();
        this.f2987n = measuredHeight;
        if (measuredHeight > 0) {
            setRefreshViewMarginTop((-measuredHeight) + 1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                if (e() || this.f2985l == 68 || this.f2988o == null || this.f2986m == null) {
                    return super.onTouchEvent(motionEvent);
                }
                if (this.f2990q) {
                    scrollToPosition(0);
                }
                int rawY = (int) ((motionEvent.getRawY() - this.f2989p) * this.f2984k);
                if (rawY > 0) {
                    setRefreshViewMarginTop(rawY - this.f2987n);
                    h(rawY);
                    this.f2990q = true;
                    return true;
                }
            }
        } else if (this.f2990q) {
            g();
        }
        return super.onTouchEvent(motionEvent);
    }

    public RefreshWrapperRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2984k = 0.3f;
        this.f2987n = 0;
        this.f2990q = false;
        this.f2991r = null;
        this.f2992s = null;
        this.f2993t = null;
        f(context);
    }

    public RefreshWrapperRecyclerView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2984k = 0.3f;
        this.f2987n = 0;
        this.f2990q = false;
        this.f2991r = null;
        this.f2992s = null;
        this.f2993t = null;
        f(context);
    }
}
